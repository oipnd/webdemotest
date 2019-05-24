package com.sei.test;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class KNNTest {
	public static void main(String[] args) throws IOException {
		String modetype="login";
	    String  HumDataPath="E:\\WebTestData\\";
	    String BotDataPath="E:\\WebAttackData\\2150300072\\robot3_001.txt";
	    
	    //获取原始特征数据
	    ArrayList<ArrayList<Float>> KeyFeaBot=feaGet.KeyDataGetFinal_File(modetype,BotDataPath);	    
	    ArrayList<ArrayList<Float>> KeyFeaHuman=feaGet.KeyDataGetFinal(modetype,HumDataPath);
	    int totalSampleNum=KeyFeaHuman.size();
  
	    int[] randomList=RandomList.getRandomSeq(totalSampleNum);
	    
	    int TrainNum = 25;//训练集数量
	    int NK=15;
	    //获取合法训练集
	    ArrayList<ArrayList<Float>> TrainData=new ArrayList<ArrayList<Float>>();	    
	    for(int i=0;i<TrainNum;i++){
	    	TrainData.add(KeyFeaHuman.get(randomList[i]));
	    }
	    
	    //获取合法数据集
	    ArrayList<ArrayList<Float>> LegalTestData=new ArrayList<ArrayList<Float>>();
	    for(int i=TrainNum;i<totalSampleNum;i++){
	    	LegalTestData.add(KeyFeaHuman.get(randomList[i]));
	    }
	    
//	    System.out.println(LegalTestData.size());
	    
	    //获取非法数据集
	    ArrayList<ArrayList<Float>> IllegalTestData=KeyFeaBot;
	    
	   
	    //获取数据归一化参数
	    ArrayList<ArrayList<Float>> NormalizationData =DataProc.FeaNormalizationGetData(TrainData);
	    
	    
	    //归一化合法训练集
	    ArrayList<ArrayList<Float>> KeyLegalTrain=DataProc.FeaNormalization(NormalizationData, TrainData) ;
	    System.out.println(KeyLegalTrain);
	    //归一化合法测试集
	    ArrayList<ArrayList<Float>> KeyLegalTest=DataProc.FeaNormalization(NormalizationData, LegalTestData) ;
	    //归一化非法测试集
	    ArrayList<ArrayList<Float>> KeyIllegalTest=DataProc.FeaNormalization(NormalizationData, IllegalTestData) ;
	    
	    //获取得分
	    ArrayList<Float> Key_ScoreTrain =DataProc.ScoreGet_KNN_Train (KeyLegalTrain,15);
	    ArrayList<Float> Key_ScoreLegal =DataProc.ScoreGet_KNN_Test (KeyLegalTest,KeyLegalTrain ,15);
	    ArrayList<Float> Key_ScoreIllegal =DataProc.ScoreGet_KNN_Test (KeyIllegalTest,KeyLegalTrain,15 );
	    
	    //获取训练集阈值ֵ
	    
	    float DistThreshold =JStatistic.getMeanF(Key_ScoreTrain);
	    System.out.println(Key_ScoreIllegal.size());
	    System.out.println("DistThreshold "+DistThreshold);
	    
	    // 存储信息
	    Serialization.NormalDataSerialization(NormalizationData.get(0),"Numerator","key","login");
	    Serialization.NormalDataSerialization(NormalizationData.get(1),"Denominator","key","login");
	    Serialization. ModelSerialization(KeyLegalTrain,"KeyLegalTrain","key","login");
		
		//AntiNormalDataSerialization("Numerator");
	    ArrayList<ArrayList<Float>>cc=Serialization.AntiModelSerialization("KeyLegalTrain","key","login");

	    DistThresholdSerialization(DistThreshold);
	    System.out.println(AntidistThresholdSerialization());
	    				    
	}
	
	public static void DistThresholdSerialization(float DistThreshold) throws IOException{
		String path = "E:\\ModelForWeb\\" ;
	    File filePath = new File(path);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		File files = new File( path +"DistThreshold.txt");	
		if (!files.exists()) {
   			  files.createNewFile();
   		 }
		FileWriter writer = new FileWriter(files.getPath(), false);
		writer.write(DistThreshold+"\n");
		writer.close();	
		
	}
	
	public static float AntidistThresholdSerialization() throws IOException{
		String path = "E:\\ModelForWeb\\";
		File files = new File( path +"DistThreshold.txt");
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
	
	public static void ModelSerialization(ArrayList<ArrayList<Float>> ModelData) throws IOException{
		  int sampleNum=ModelData.size();
		  int featureNum=ModelData.get(0).size();
		
		    String path = "E:\\ModelForWeb\\" ;
		    File filePath = new File(path);
			if (!filePath.exists()) {
				filePath.mkdirs();
			}
			File files = new File( path  +"KeyLegalTrain.txt");	
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
	
	public static void NormalDataSerialization(ArrayList<Float> ModelData,String NorminationName) throws IOException{
		 int sampleNum=ModelData.size();		
		    String path = "E:\\ModelForWeb\\" ;
		    File filePath = new File(path);
			if (!filePath.exists()) {
				filePath.mkdirs();
			}
			File files = new File( path  +NorminationName+".txt");	
			if (!files.exists()) {
	   			  files.createNewFile();
	   		 }
			FileWriter writer = new FileWriter(files.getPath(), false);
			String temp="";
			for(int i=0;i<sampleNum;i++){
			    temp=temp+ModelData.get(i).toString()+" ";		
			}	
			writer.write(temp+"\n");			
			writer.close();		
	}
	
	
	public static ArrayList<Float>AntiNormalDataSerialization(String NorminationName){
		ArrayList<Float> out=new ArrayList<Float>();
		String path = "E:\\ModelForWeb\\";
		File files = new File( path +NorminationName+".txt");
		
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
	
	public static ArrayList<ArrayList<Float>>AntiModelSerialization( ){
		ArrayList<ArrayList<Float>> out=new ArrayList<ArrayList<Float>>();
		String path = "E:\\ModelForWeb\\";
		File files = new File( path+"KeyLegalTrain.txt");
		
		String ss;
		BufferedReader in=null;
		try {
			 in =new BufferedReader(new FileReader(new File(files.getPath())));
			while((ss=in.readLine())!=null&&ss.length()!=0){
				ArrayList<Float> temp=new ArrayList<Float>();
				String[] Str=ss.split(" ");
				for(int k=0;k<Str.length;k++){
					temp.add(Float.valueOf(Str[k]));
					//System.out.println(Str[k]);
				}
				out.add(temp);
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);  
		}
		finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return out;
	}

}

