package com.kaka.common;

import java.io.*;

/**
 * a File Handle class, including reading file and writing file.
 * @author TEAM 
 *
 */
public class FileHandle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3037750085175560169L;
	
	private static FileHandle instance = null;
	
	/**
	 * Constructor
	 * @param f_Obj file object
	 */
	private FileHandle() {
		
	}
	
	public static FileHandle getInstance(){
		if(instance == null){
			instance = new FileHandle();
		}
		return instance;
	}
	
	
	/**
	 * @param strFileName	the file name want to be read
	 * @return	all file content
	 * @throws Exception
	 */
	public static String readDataFile(String strFileName) {
//		The extension of the data file .drd
//		The reading path of the data file is "./data"
		File f = new File("./data/" + strFileName + ".drd");
		try {
//			FileInputStream fis = new FileInputStream(f);
//			InputStreamReader isr = new InputStreamReader(fis);
//			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			StringBuilder whole = new StringBuilder();
			String result = "";
			while ((result = br.readLine()) != null) {
				whole.append(result);
			}
//			System.out.println(whole.toString());
			return whole.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//throw e;
		}
		return null;
	}
	
	/**
	 * @param strDirectory file directory
	 * @param objSave the object want to be saved.
	 * @param strFileName file name
	 * @return a flag of succeed.
	 * @throws Exception
	 */
	public boolean outputToFile(String strDirectory, int[] objSave, String strFileName, String[] strStatistics) throws Exception {
		try {
			File fObjSave = new File(strDirectory);
			String strSaved = "";
			if(strStatistics != null) {
				strSaved = "Statistics:\r\n";
				strSaved += (strStatistics[0] + "\r\n");
				strSaved += (strStatistics[1] + "\r\n");
				strSaved += (strStatistics[2] + "\r\n");
				strSaved += "Sorted List: \r\n";
			}
			
			for(int i = 0; i < objSave.length; i ++) {
				if(i < objSave.length - 1) {
					strSaved += objSave[i] + ",";
				} else {
					strSaved += objSave[i];
				}
			}
			
			return writeObject(strFileName, strSaved, fObjSave);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * Output string stream to file.
	 * @param str_FileName file name
	 * @param obj_Save the saving object
	 * @param str_Extension the file's extension
	 * @return true if the object is successfully written on the disk otherwise false
	 * @throws Exception 
	 */
	private boolean writeObject(String str_FileName, String obj_Save, File f_ObjSave) throws Exception {
		try {			
			String str_FullName = f_ObjSave.getPath() + "\\" + str_FileName + ".txt";
			
			FileOutputStream fos_FileOut = new FileOutputStream(str_FullName);
			byte[] buffer = obj_Save.getBytes();

			fos_FileOut.write(buffer, 0, buffer.length);
			fos_FileOut.close();
			return true;
		}catch(FileNotFoundException ex){ 
			ex.printStackTrace();
			throw ex;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Load String stream from a file
	 * @return the object that read from the file.
	 * @throws Exception 
	 */
	public int[] loadFromFile(String strDirectory, String str_FileName) throws Exception {
		try{
			File f_ObjSave = new File(strDirectory);
			String str_FullName = f_ObjSave.getPath() + "\\" + str_FileName + ".txt";
			
			FileReader f_Reader = new FileReader(str_FullName);
			BufferedReader br_Reader = new BufferedReader(f_Reader);
			
			String strUnsorted = br_Reader.readLine();
			String[] unsortedArray = strUnsorted.split(",");
			int[] i_unsorted = new int[unsortedArray.length];
			
			for(int i = 0; i < unsortedArray.length; i ++) {
				i_unsorted[i] = Integer.parseInt(unsortedArray[i]);
			}
			
			br_Reader.close();
			f_Reader.close();
			
			return i_unsorted;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}