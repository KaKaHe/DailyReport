/**
 * 
 */
package com.kaka.common;

/**
 * @author KaKa
 *
 */
public class DataHandle {
/******Global Variables Declaration***************************************************************************/
	private static String strActiveStatus = "Status=\"A\"";
	private static String strInactiveStatus = "Status=\"I\"";
	private static String strErrorStatus = "Status=\"E\"";
	private static String strFile_User = "register";
	private static String strLineSeparator = "line.separator";
/************************************************************************************************************/

	public static String validatePassword(String UserName, String Password) {
		//Validate the login username and password, if succeesed, return all user information
		String strList = FileHandle.readDataFile(strFile_User);
		
		strList = strList.replace("<DairyRecord>", "");
		strList = strList.replace("</DairyRecord>", "");
		
		String strUP = "";
		String strRest = "";
		String strInfo = "";
		
		while(!strList.isEmpty()) {
			int start = strList.indexOf("<Register>") + 10;
			int end = strList.indexOf("</Register>");
			
			String singleRecord = strList.substring(start, end);
			strList = strList.substring(end + 11);
			//System.out.println(singleRecord);
			
			int UPend = singleRecord.indexOf("</Password>") + 11;
			
			strUP = singleRecord.substring(0,  singleRecord.indexOf("</Password>") + 11);
			strRest = singleRecord.substring(UPend);
			String strCompare = "<UserName>" + UserName + "</UserName><Password>" + Password + "</Password>";
			if(strCompare.equals(strUP)) {
//				Return all user information but username and password,
//				Different values are delimetered by ",", and title and value are delimetered by "="
//				The values are quoted.
				if(strRest.indexOf(strActiveStatus) > 0) {
					strInfo = strRest.substring(strRest.indexOf("UserId"), strRest.indexOf("></infos>"));
				}
				break;
			}
		}
		
		return strInfo;
	}
	
	public static int deleteExistingUser(String UserName, String Password){
		/* Try to delete the given user from the data file if the password is validated.
		 * 0: fail
		 * 1: success
		 * 50:account error
		 * 99:non exist
		 */
		int iRet = 0;
		
		String strList = FileHandle.readDataFile(strFile_User);
//		String strCurrStatus = "Status=\"A\"";
//		String strNewStatus = "Status=\"I\"";
//		String strErrStatus = "Status=\"E\"";
		
		strList = strList.replace("<DairyRecord>", "");
		strList = strList.replace("</DairyRecord>", "");
		
		String strUP = "";
		StringBuilder sbUpdated = new StringBuilder();
		
		sbUpdated.append("<DairyRecord>");
		sbUpdated.append(System.getProperty(strLineSeparator));
		sbUpdated.append(strList.substring(0, strList.indexOf("<Register>")));
		sbUpdated.append(System.getProperty(strLineSeparator));
		
		while(!strList.isEmpty()) {
			int start = strList.indexOf("<Register>") + 10;
			int end = strList.indexOf("</Register>");
			
			String singleRecord = strList.substring(start, end);
			strList = strList.substring(end + 11);
			//System.out.println(singleRecord);
			
			strUP = singleRecord.substring(0,  singleRecord.indexOf("</Password>") + 11);
			String strCompare = "<UserName>" + UserName + "</UserName><Password>" + Password + "</Password>";
			if(strCompare.equals(strUP)) {
				//If the user name and password are validated and the user is actived, change the user status to inactive.
				if(singleRecord.indexOf(strActiveStatus) > 0){
					singleRecord = singleRecord.replace(strActiveStatus, strInactiveStatus);
					iRet = 1;
				} else if (singleRecord.indexOf(strErrorStatus) > 0) {
					iRet = 50;
				} else {
					iRet = 99;
				}
			}
			//Otherwise, just append the record for preparing write back.
			singleRecord = "<Register>" + singleRecord + "</Register>";
			sbUpdated.append(singleRecord);
			sbUpdated.append(System.getProperty(strLineSeparator));
			
			if(iRet != 0) {
				sbUpdated.append(strList);
				sbUpdated.append(System.getProperty(strLineSeparator));
				break;
			}
		}
		
		sbUpdated.append("</DairyRecord>");
		
		//After validating the username and password, rewrite the register file.
		if(iRet == 1) {
//			if(!FileHandle.writeDataFileBW(strFile_User, sbUpdated.toString())) {
			if(!FileHandle.writeDataFileFos(strFile_User, sbUpdated.toString())) {
				iRet = 0;
			}
		}

		return iRet; //0 means fail to delete the user.
	}

	public static Object[] getData(String tblName, String[] outVal, String[] condField, String[] condValue) {
		String strList = FileHandle.readDataFile(tblName);
		
		strList = strList.replace("<DairyRecord>", "");
		strList = strList.replace("</DairyRecord>", "");
		
		String[] strLine = strList.split(System.getProperty(strLineSeparator));
		
		return null;
	}
}
