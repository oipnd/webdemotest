package com.sei.web.servlet;

import java.io.FileWriter;
import java.io.IOException;

public class WriterDBFile {

	public boolean WriterFileUse(String info) {
		FileWriter writer = null;

		try {
			writer = new FileWriter("/data/questionare_data/mousetrack/huawei/userID/dbfile.txt", true);// ׷��
			writer.write(info);
			writer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	
	public boolean WriterFileCover(String buf){
		FileWriter writer = null;
		
		try {
			writer = new FileWriter("/data/weiang_data/mousetrack/huawei/userID/dbfile.txt", false);//����
			writer.write(buf);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return true;
        
	}
}
