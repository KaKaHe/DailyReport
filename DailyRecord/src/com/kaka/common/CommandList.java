/**
 * 
 */
package com.kaka.common;

/**
 * @author KaKa
 * phrase constants
 */
public final class CommandList {

	public static final String TOTAL_TITLE = "Daily Record";
	public static final String UNKNOW_ERROR = "Unknown error occurred. Please contact support.";
/***Label area:********************************************************************************/
//	Login window
	public static final String DR001_USERNAME = "User Name ";
	public static final String DR001_PASSWORD = "Password ";
	
//	Register window
	public static final String DR002_PERSONAL_PANEL = "Personal";
	public static final String DR002_CREDENTIAL_PANEL = "Credential";
	public static final String DR002_SECURITY_PANEL = "Security";
	public static final String DR002_CONTROL_PANEL = "Control";
	public static final String DR002_USERNAME = "UserName ";
	public static final String DR002_PASSWORD = "Password ";
	public static final String DR002_CONFIRMPASSWORD = "Confirm ";
	public static final String DR002_PASSWORDINFO = " 8-16 long a-z A-Z 0-9 &*_@.#";
	public static final String DR002_SECURITY_QUESTION = "Security Question ";
	public static final String DR002_SECURITY_ANSWER = "Security Answer ";
	public static final String DR002_FIRSTNAME = "First Name ";
	public static final String DR002_LASTNAME = "Last Name ";
	public static final String DR002_GENDER = "Gender ";
	public static final String DR002_GENDER_MALE = "Male";
	public static final String DR002_GENDER_FEMALE = "Female";
	public static final String DR002_GENDER_OTHER = "Other";
	public static final String DR002_BIRTHDAY = "Birthday ";
	public static final String DR002_BIRTHDAYEXAMPLE = " Format: YYYY-MM-DD";
	public static final String DR002_EMAIL = "Email ";
	
//	Account Summary
/**********************************************************************************************/
	
/***Button area:*******************************************************************************/
//	Login window
	public static final String DR001_SIGNIN = "Sign In";
	public static final String DR001_DELETE = "Delete";
	public static final String DR001_FORGET = "Forget?";
	public static final String DR001_SIGNUP = "Sign Up";
	
//	Register window
	public static final String DR002_RESET = "Reset";
	public static final String DR002_SUBMIT = "Submit";
	public static final String DR002_CANCEL = "Cancel";
	
//	Account Summary
/*********************************************************************************************/
	
/***Error Message:****************************************************************************/
//	Login window
	public static final String DR001_MSG001 = "User Name or Password is incorrect.";
	public static final String DR001_MSG002 = "The user does not exist.";
	public static final String DR001_MSG003 = "There is error about this account, please contact administrator.";
	public static final String DR001_MSG004 = "Deleting user failed.";
	public static final String DR001_MSG005 = "User has been deleted.";
	public static final String DR001_MSG006 = "User name has already existed.";
	public static final String DR001_MSG007 = "Data file error.";
	public static final String DR001_MSG008 = "Setting new password failed.";
	public static final String DR001_MSG009 = "New password setup succeed.";
	public static final String DR001_MSG010 = "The passwords are not same.";
	public static final String DR001_MSG011 = "The answer is not correct!";
	public static final String DR001_MSG012 = "The password cannot be accepted.";

//	Register window
	public static final String DR002_ERROR = "Error";
	public static final String DR002_ERROR_FIRSTNAME = "The input First Name is invalid.";
	public static final String DR002_ERROR_LASTNAME = "The input Last Name is invalid.";
	public static final String DR002_ERROR_GENDER = "The selected Gender is invalid.";
	public static final String DR002_ERROR_BIRTHDAY = "The input Birthday date is invalid. Please confirm the format.";
	public static final String DR002_ERROR_USERNAME = "The input Username format is invalid. (At least 3 characters.)";
	public static final String DR002_ERROR_PASSWORD = "The input Password is invalid. Please confirm the format.";
	public static final String DR002_ERROR_EMAIL = "The input Email is invalid. Please confirm the format.";
	public static final String DR002_ERROR_SECURITYQUESTION = "The input Security Question is invalid. Please confirm the format.";
	public static final String DR002_ERROR_SECURITYANSWER = "The input Security Answer is invalid. Please confirm the format.";
	
//	Account Summary
/*********************************************************************************************/
}
