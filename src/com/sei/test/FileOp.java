package com.sei.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




public class FileOp {

	
public static ArrayList<ArrayList<ArrayList<String>>> ReadDataFromTextFile(String FilePath){
		
		String[] HumanList = (new File(FilePath)).list();
		//System.out.println(HumanList[0]);
		ArrayList<ArrayList<ArrayList<String>>> HumanData = new ArrayList<ArrayList<ArrayList<String>>>();
		
	for(int i=0;i<HumanList.length;i++){

				String HumanPath = FilePath + "\\" + HumanList[i]+"\\";
				
				String[] FileList = (new File(HumanPath)).list();
				//System.out.println(FileList[0]);
				ArrayList<ArrayList<String>> SingleFileData = new ArrayList<ArrayList<String>>();
				for(int j=0;j<FileList.length;j++){
					File f = new File(HumanPath+"\\"+FileList[j]);
					//System.out.println(f.getAbsolutePath());
					BufferedReader reader = null;
					try{
						reader = new BufferedReader(new FileReader(f));
						String tempString = null;
						ArrayList<String> lineList = new ArrayList<String>();
						//���ж�ȡ����
						while((tempString = reader.readLine()) != null) {
							lineList.add(tempString);
						}
						reader.close();
						SingleFileData.add(lineList);
					} catch (IOException e){
						e.printStackTrace();
					} finally {
						if(reader!=null){
							try{
								reader.close();
							} catch (IOException e1){
							}
						}
					}
				}//j��forѭ������
				HumanData.add(SingleFileData);
//			}
		}
		return HumanData;
	}



public static ArrayList<ArrayList<ArrayList<String>>> ReadDataFromTextFile_file(String FilePath){
	
	ArrayList<ArrayList<ArrayList<String>>> HumanData = new ArrayList<ArrayList<ArrayList<String>>>();
	

			ArrayList<ArrayList<String>> SingleFileData = new ArrayList<ArrayList<String>>();

				File f = new File(FilePath);
				//System.out.println(f.getAbsolutePath());
				BufferedReader reader = null;
				try{
					reader = new BufferedReader(new FileReader(f));
					String tempString = null;
					ArrayList<String> lineList = new ArrayList<String>();
					while((tempString = reader.readLine()) != null) {
						lineList.add(tempString);
					}
					reader.close();
					SingleFileData.add(lineList);
				} catch (IOException e){
					e.printStackTrace();
				} finally {
					if(reader!=null){
						try{
							reader.close();
						} catch (IOException e1){
						}
					}
				}
			HumanData.add(SingleFileData);

	return HumanData;
}
public static void main(String[] args) {
	
	ReadDataFromTextFile("C:\\Users\\Administrator\\Desktop\\HWbot4\\retree\\login\\");
}

}
