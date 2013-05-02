/**
 * 
 */
package com.kaka.common;

/**
 * @author KaKa
 *
 */
public class DataHandle {

	public static boolean validatePassword(String UserName, String Password) {
		String strList = FileHandle.readDataFile("register");
		
		strList = strList.replace("<DairyRecord>", "");
		strList = strList.replace("</DairyRecord>", "");
		
		String strUP = "";
		
		while(!strList.isEmpty()) {
			int start = strList.indexOf("<Register>");
			int end = strList.indexOf("</Register>");
			
			String singleRecord = strList.substring(start, end + 11);
			strList = strList.substring(end + 11);
			System.out.println(singleRecord);
			
			strUP = singleRecord.substring(start + 10,  singleRecord.indexOf("</Password>") + 11);
			String strCompare = "<UserName>" + UserName + "</UserName><Password>" + Password + "</Password>";
			if(strCompare.equals(strUP)) {
				 return true;
			}
			
		}
		
		if(strUP.equals("")) {
			return true;
		}
		
		return false;
	}
}
