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
	private static String strSecurityQ = "SecurityQ=";
	private static String strSecurityA = "SecurityA=";
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
		String strInfo = "";	//Store the information that is returned to caller.
		
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

	public static String[] forgetPassword(String UserName) {
		String strQuestion[] = new String[2];
		String strList = FileHandle.readDataFile(strFile_User);
		
		strList = strList.replace("<DairyRecord>", "");
		strList = strList.replace("</DairyRecord>", "");
		
		//String[] strLine = strList.split(System.getProperty(strLineSeparator));
		while(!strList.isEmpty()) {
			int start = strList.indexOf("<Register>") + 10;
			int end = strList.indexOf("</Register>");
			
			//intercept one record
			String singleRecord = strList.substring(start, end);
			//erase the current record from the base string
			strList = strList.substring(end + 11);
			
			//intercept username
			String strUser = singleRecord.substring(singleRecord.indexOf("<UserName>") + 10, singleRecord.indexOf("</UserName>"));
			
			//Check Username
			if(UserName.equals(strUser)){
				//erase xml element from single record
				singleRecord = singleRecord.substring(singleRecord.indexOf("infos") + 5, singleRecord.indexOf("><", singleRecord.indexOf("infos") + 5));
				
				String[] strProperties = singleRecord.split(",");
				
				for (int i = 0; i < strProperties.length; i ++) {
					int iOP = strProperties[i].indexOf("=");
					//if(strProperties[i].substring(0, iOP).equals("SecurityQ")) {
					if(strProperties[i].substring(0, iOP + 1).equals(strSecurityQ)) {
						strQuestion[0] = strProperties[i].replace("\"", "").substring(iOP + 1);
					//} else if(strProperties[i].substring(0, iOP).equals("SecurityA")) {
					} else if(strProperties[i].substring(0, iOP + 1).equals(strSecurityA)) {
						strQuestion[1] = strProperties[i].replace("\"", "").substring(iOP + 1);
						break;
					}
				}
				
				//Stop searching
				break;
			}
		}
		
		return strQuestion;
	}
	
	public static void operateUser(Boolean bFlag, String strUser, String[] strValues) {
		//if bFlag is true, it means add new user
		//if bFlag is false, it means update existing user
		
		//no mater adding new or updating existing, open the data file first.
		String strList = FileHandle.readDataFile(strFile_User);
		int iMaxUID = 0;
		
		strList = strList.replace("<DairyRecord>", "");
		strList = strList.replace("</DairyRecord>", "");
		
		String strData = "";
		StringBuilder sbData = new StringBuilder();

		sbData.append("<DairyRecord>");
		sbData.append(System.getProperty(strLineSeparator));
		if(bFlag) {
			//When adding new user, get the maximum user id and add one.
			iMaxUID = 1 + Integer.parseInt(strList.substring(strList.indexOf("<MaxId>") + 7), strList.indexOf("</MaxId>"));
			sbData.append("<MaxId>" + Integer.toString(iMaxUID) + "</MaxId>");
			sbData.append(System.getProperty(strLineSeparator));
			strList = strList.substring(strList.indexOf("</MaxId>") + 8);
		}
		sbData.append(strList.substring(0, strList.indexOf("<Register>")));
		sbData.append(System.getProperty(strLineSeparator));
		
		//traverse the users data.
		//For adding user, this is used to check the unique username.
		//For updating user, this is used to get the current user data.
		while(!strList.isEmpty()) {
			int start = strList.indexOf("<Register>") + 10;
			int end = strList.indexOf("</Register>");
			
			String singleRecord = strList.substring(start, end);
			strList = strList.substring(end + 11);
			strData = singleRecord.substring(0, singleRecord.indexOf("</UserName>") + 11);
			String strCompare = "<UserName>" + strUser + "</UserName>";
			
			if(strCompare.equals(strData)) {
				//If the user name can be matched, it is going to update the information when updating. it is going to return an unique error when adding.
				if(bFlag) {
					//If this is for adding new user, return unique check failed error.
				} else {
					//If this is for updating user, form the string and update.
				}
			}
		}
		
	}
	
	/**
	 * This function is used to select data from corresponding data file.
	 * @param tblName			Table name, it is the file name
	 * @param outCols			Output columns
	 * @param condField			Fields that are used in where clause
	 * @param condOperator		Operators that are used to indicator how to filter the data
	 * @param condValue			Boundary values
	 * @return	the result set
	 */
	public static Object[] getData(String tblName, String[] outCols, String[] condField, String[] condOperator, String[] condValue) {
		String strList = FileHandle.readDataFile(tblName);
		
		strList = strList.replace("<DairyRecord>", "");
		strList = strList.replace("</DairyRecord>", "");
		
		String[] strLine = strList.split(System.getProperty(strLineSeparator));
		
		return null;
	}
}
