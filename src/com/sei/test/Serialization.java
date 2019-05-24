package com.sei.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Serialization {
	public static void ModelSerialization(ArrayList<ArrayList<Float>> ModelData,String ModelName,String Type,String method) throws IOException{
		 int sampleNum=ModelData.size();
		  int featureNum=ModelData.get(0).size();
		
		    String path = "E:\\ModelForWeb\\"+method+"\\" ;
		    File filePath = new File(path);
			if (!filePath.exists()) {
				filePath.mkdirs();
			}
			File files = new File( path +Type+ "\\" +ModelName+".txt");	
			if (!files.exists()) {
	   			  files.createNewFile();
	   		 }
			FileWriter writer = new FileWriter(files.getPath(), false);
			for(int i=0;i<sampleNum;i++){
				String temp="";
				for(int j=0;j<featureNum;j++){
					temp=temp+ModelData.get(i).get(j).toString()+" ";
				}
				writer.write(temp+"\n");
			}
			writer.close();		
	}
	
	public static void NormalDataSerialization(ArrayList<Float> ModelData,String NorminationName,String Type,String method) throws IOException{
		 int sampleNum=ModelData.size();		
		    String path = "E:\\ModelForWeb\\"+method+"\\" +Type+"\\";
		    File filePath = new File(path);
			if (!filePath.exists()) {
				filePath.mkdirs();
			}
			File files = new File( path + "\\" +NorminationName+".txt");	
			if (!files.exists()) {
	   			  files.createNewFile();
	   		 }
			//System.out.println(files.getPath());
			FileWriter writer = new FileWriter(files.getPath(), false);
			String temp="";
			for(int i=0;i<sampleNum;i++){
			    temp=temp+ModelData.get(i).toString()+" ";		
			}	
			writer.write(temp+"\n");			
			writer.close();		
	}
	
	
	public static ArrayList<Float>AntiNormalDataSerialization(String NorminationName,String Type,String method){
		ArrayList<Float> out=new ArrayList<Float>();
		String path = "E:\\ModelForWeb\\"+method+"\\" +Type+"\\";
		File files = new File( path + "\\" +NorminationName+".txt");
		
		String ss;
		BufferedReader in=null;
		try {
			 in =new BufferedReader(new FileReader(new File(files.getPath())));
			while((ss=in.readLine())!=null&&ss.length()!=0){
				String[] Str=ss.split(" ");
				for(int k=0;k<Str.length;k++){
					out.add(Float.valueOf(Str[k]));
				}
			}
		
		} catch (IOException e) {
			throw new RuntimeException(e);  
		}
		finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return out;
	}
	
	public static ArrayList<ArrayList<Float>>AntiModelSerialization( String ModelName,String Type,String method){
		ArrayList<ArrayList<Float>> out=new ArrayList<ArrayList<Float>>();
		String path = "E:\\ModelForWeb\\"+method+"\\" +Type+"\\";
		File files = new File( path + "\\" +ModelName+".txt");
		
		String ss;
		BufferedReader in=null;
		try {
			 in =new BufferedReader(new FileReader(new File(files.getPath())));
			while((ss=in.readLine())!=null&&ss.length()!=0){
				ArrayList<Float> temp=new ArrayList<Float>();
				String[] Str=ss.split(" ");
				for(int k=0;k<Str.length;k++){
					temp.add(Float.valueOf(Str[k]));
				}
				out.add(temp);
			}
		
		} catch (IOException e) {
			throw new RuntimeException(e);  
		}
		finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return out;
	}

	
	public static void DistThresholdSerialization(float DistThreshold,String Type,String method) throws IOException{
		String path = "E:\\ModelForWeb\\"+method+"\\" +Type+"\\" ;
	    File filePath = new File(path);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		File files = new File( path + "\\" +"DistThreshold.txt");	
		if (!files.exists()) {
   			  files.createNewFile();
   		 }
		FileWriter writer = new FileWriter(files.getPath(), false);
		writer.write(DistThreshold+"\n");
		writer.close();	
		
	}
	
	public static float AntidistThresholdSerialization(String Type,String method) throws IOException{
		String path = "E:\\ModelForWeb\\"+method+"\\" +Type+"\\";
		File files = new File( path + "\\" +"DistThreshold.txt");
		String out="";
		String ss;
		BufferedReader in=null;
		try {
			 in =new BufferedReader(new FileReader(new File(files.getPath())));
			while((ss=in.readLine())!=null&&ss.length()!=0){
				out=ss;
			}
		
		} catch (IOException e) {
			throw new RuntimeException(e);  
		}
		finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return Float.valueOf(out);
		
	}

}
