/**
 * 
 */
package com.kaka.common;

import java.text.*;
import java.util.Date;

/**
 * @author KaKa
 *
 */
public class DataHandle {
/******Global Variables Declaration***************************************************************************/
	private static String strUserID = "UserId=";
	private static String strFirstName = "FirstName=";
	private static String strLastName = "LastName=";
	private static String strEmail = "Email=";
	private static String strSecurityQ = "SecurityQ=";
	private static String strSecurityA = "SecurityA=";
	private static String strBirthday = "Birthday=";
	private static String strFirstReg = "FirstReg=";
	private static String strGender = "Gender=";
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
	
	public static int operateUser(Boolean bFlag, String strUser, String[] strValues) {
		/********************************************************************************************************************************************
		 * Description of Flags, variable <Boolean> bFlag
		 * True, it means add new user
		 * False, it means update existing user
		 ********************************************************************************************************************************************/
		/********************************************************************************************************************************************
		 * Description of Values <Array index>
		 * There is no user name in the value array, because it is passed in by the variable <strUser>
		 * 0: Password
		 * 1: First Name
		 * 2: Last Name
		 * 3: Email
		 * 4: Security Question
		 * 5: Security Answer
		 * 6: Birthday
		 * 7: Gender 
		 * When adding new user, if the value is null, it means there is no value. The mandatory validation is done before calling this function.
		 * When updating user, if the value is null, it means the value is not changed.
		 ********************************************************************************************************************************************/
		/********************************************************************************************************************************************
		 * Decription of return value <iRet>
		 * 0: opearting failed with unknown reason either adding or updating
		 * 1: successful operating either adding or updating
		 * 22:Unique check failed while adding
		 * 44:File writing error either adding or updating
		 * 50:User account in error status (Status = "E") while updating
		 * 99:User doens't exist while updating
		 ********************************************************************************************************************************************/
		//Result flag
		int iRet = -1;
		
		//no mater adding new or updating existing, open the data file first.
		String strList = FileHandle.readDataFile(strFile_User);
		int iMaxUID = 0;
		
		strList = strList.replace("<DairyRecord>", "");
		strList = strList.replace("</DairyRecord>", "");
		
		String strData = "";
		StringBuilder sbData = new StringBuilder(); //Whole data string
		StringBuilder sbSingle; //singel new data string

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
			
			//extract the first record and erase it from the data string preparing for file.
			String singleRecord = strList.substring(start, end);
			strList = strList.substring(end + 11);
			
			sbSingle = new StringBuilder();	//initialize the new string builder
			
			//Form the user name string for comparing.
			strData = singleRecord.substring(0, singleRecord.indexOf("</UserName>") + 11);
			String strCompare = "<UserName>" + strUser + "</UserName>";
			
			if(strCompare.equals(strData) && singleRecord.indexOf(strActiveStatus) > 0) {
				//If the user name can be matched, it is going to update the information when updating. it is going to return an unique error when adding.
				if(bFlag) {
					//If this is for adding new user, return unique check failed error.
					iRet = 22;
					break;
				} else {
					//If this is for updating user, form the string and update.
					//Append user name to the writing queue.
					sbSingle.append(strCompare);
					
					// 0: Password
					if(strValues[0].isEmpty()) {
						sbSingle.append(singleRecord.substring(singleRecord.indexOf("<Password>"), singleRecord.indexOf("</Password>") + 11));
					} else {
						sbSingle.append("<Password>" + strValues[0] + "</Password>");
					}
					
					// form the general information string
					sbSingle.append(singleRecord.substring(singleRecord.indexOf("<infos"), singleRecord.indexOf(strFirstName)));
					
					// 1: First Name
					if(strValues[1].isEmpty()) {
						sbSingle.append(singleRecord.substring(singleRecord.indexOf(strFirstName), singleRecord.indexOf(strLastName)));
					} else {
						sbSingle.append(strFirstName + "\"" + strValues[1] + "\",");
					}
					
					// 2: Last Name
					if(strValues[2].isEmpty()) {
						sbSingle.append(singleRecord.substring(singleRecord.indexOf(strLastName), singleRecord.indexOf(strEmail)));
					} else {
						sbSingle.append(strLastName + "\"" + strValues[2] + "\",");
					}
					
					// 3: Email
					if(strValues[3].isEmpty()) {
						sbSingle.append(singleRecord.substring(singleRecord.indexOf(strEmail), singleRecord.indexOf(strSecurityQ)));
					} else {
						sbSingle.append(strEmail + "\"" + strValues[3] + "\",");
					}
					
					// 4: Security Question
					if(strValues[4].isEmpty()) {
						sbSingle.append(singleRecord.substring(singleRecord.indexOf(strSecurityQ), singleRecord.indexOf(strSecurityA)));
					} else {
						sbSingle.append(strSecurityQ + "\"" + strValues[4] + "\",");
					}
					
					// 5: Security Answer
					if(strValues[5].isEmpty()) {
						sbSingle.append(singleRecord.substring(singleRecord.indexOf(strSecurityA), singleRecord.indexOf(strBirthday)));
					} else {
						sbSingle.append(strSecurityA + "\"" + strValues[5] + "\",");
					}
					
					// 6: Birthday
					if(strValues[6].isEmpty()) {
						sbSingle.append(singleRecord.substring(singleRecord.indexOf(strBirthday), singleRecord.indexOf(strGender)));
					} else {
						sbSingle.append(strBirthday + "\"" + strValues[6] + "\",");
					}
					
					// 7: Gender
					if(strValues[7].isEmpty()) {
						sbSingle.append(singleRecord.substring(singleRecord.indexOf(strGender), singleRecord.indexOf(strFirstReg)));
					} else {
						sbSingle.append(strGender + "\"" + strValues[7].substring(0, 1) + "\",");
					}
					
					//Append the rest parts of string
					sbSingle.append(singleRecord.substring(singleRecord.indexOf(strFirstReg)));
					iRet = 1;
				}
			} else if(strCompare.equals(strData) && singleRecord.indexOf(strErrorStatus) > 0) {
				//The user account has error, need to contact administrator.
				iRet = 50;
			} else {
				//no matter adding or updating, it needs to keep the old user's data staying in the string
				sbSingle.append(singleRecord);
			}
			
			//Append quote elements
			sbData.append("<Register>" + sbSingle.toString() + "</Register>");
			sbData.append(System.getProperty(strLineSeparator));
		}
		
		//If this function is called for adding, the program will reach here only when the user name pass the unique check.
		if(bFlag && iRet == -1) {
			DateFormat todayF = new SimpleDateFormat("yyyyMMdd");
			Date today = new Date();
			sbSingle = new StringBuilder();
			
			sbSingle.append("<UserName>" + strUser + "</UserName>");
			sbSingle.append("<Password>" + strValues[0] + "</Password>");
			sbSingle.append("<infos ");
			sbSingle.append(strUserID + "\"" + iMaxUID + "\",");
			sbSingle.append(strFirstName + "\"" + strValues[1] + "\",");
			sbSingle.append(strLastName + "\"" + strValues[2] + "\",");
			sbSingle.append(strEmail + "\"" + strValues[3] + "\",");
			sbSingle.append(strSecurityQ + "\"" + strValues[4] + "\",");
			sbSingle.append(strSecurityA + "\"" + strValues[5] + "\",");
			sbSingle.append(strBirthday + "\"" + strValues[6] + "\",");
			sbSingle.append(strGender + "\"" + strValues[7].substring(0, 1) + "\",");
			sbSingle.append(strFirstReg + "\"" + todayF.format(today) + "\",");
			sbSingle.append(strActiveStatus + "></infos>");
			
			sbData.append("<Register>" + sbSingle.toString() + "</Register>");
			sbData.append(System.getProperty(strLineSeparator));
			iRet = 1;
		}
		
		//Append end element
		sbData.append("</DairyRecord>");
		
		//User doesn't exist while updating
		if(!bFlag && iRet == -1) {
			iRet = 99;
		}
		
		//Write to File
		if(iRet == 1 && FileHandle.writeDataFileFos(strFile_User, sbData.toString())) {
			iRet = 1;
		} else {
			//Writing to file failed
			iRet = 44;
		}
		
		return iRet;
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
