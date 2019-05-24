package com.sei.test;

import java.util.ArrayList;

public class OneClassMainTest {
	public static void main(String[] args) {
		String modetype="login";
	    String  HumDataPath="C:\\Users\\Administrator\\Desktop\\HWbot4\\retree\\retree\\"+modetype+"\\";
	    String BotDataPath="C:\\Users\\Administrator\\Desktop\\HWbot4\\logintql\\login\\";
	    
	    //��������
	    ArrayList<ArrayList<Float>> KeyFeaBot=feaGet.BotFeaFinal(modetype,BotDataPath);	    
	    ArrayList<ArrayList<Float>> KeyFeaHuman=feaGet.HumFeaFinal(modetype,HumDataPath);
	    int totalSampleNum=KeyFeaHuman.size();
//	    System.out.println(totalSampleNum);	  
	    int[] randomList=RandomList.getRandomSeq(totalSampleNum);
	    
	    int TrainNum = 30;//ѵ������Ŀ
	    int NK=15;
	    //����ѵ����
	    ArrayList<ArrayList<Float>> TrainData=new ArrayList<ArrayList<Float>>();	    
	    for(int i=0;i<TrainNum;i++){
	    	//System.out.println(randomList[i]);
//	    	System.out.println(KeyFeaHuman.get(randomList[i]));
	    	TrainData.add(KeyFeaHuman.get(randomList[i]));
	    }
	    
	    //���ɺϷ����Լ�
	    ArrayList<ArrayList<Float>> LegalTestData=new ArrayList<ArrayList<Float>>();
	    for(int i=TrainNum;i<totalSampleNum;i++){
	    	LegalTestData.add(KeyFeaHuman.get(randomList[i]));
	    }
	    
//	    System.out.println(LegalTestData.size());
	    
	    //���ɷǷ����Լ�
	    ArrayList<ArrayList<Float>> IllegalTestData=KeyFeaBot;
	    
	   
	    //��ȡ��һ������
	    ArrayList<ArrayList<Float>> NormalizationData =DataProc.FeaNormalizationGetData(TrainData);
	    
	    
	    //ѵ������һ��
	    ArrayList<ArrayList<Float>> KeyLegalTrain=DataProc.FeaNormalization(NormalizationData, TrainData) ;
	    System.out.println(KeyLegalTrain);
	    //�Ϸ����Լ���һ��
	    ArrayList<ArrayList<Float>> KeyLegalTest=DataProc.FeaNormalization(NormalizationData, LegalTestData) ;
	    //�Ƿ����Լ���һ��
	    ArrayList<ArrayList<Float>> KeyIllegalTest=DataProc.FeaNormalization(NormalizationData, IllegalTestData) ;
	    
	    //����÷�
	    ArrayList<Float> Key_ScoreTrain =DataProc.ScoreGet_KNN_Train (KeyLegalTrain,15);
	    ArrayList<Float> Key_ScoreLegal =DataProc.ScoreGet_KNN_Test (KeyLegalTest,KeyLegalTrain,15);
	    ArrayList<Float> Key_ScoreIllegal =DataProc.ScoreGet_KNN_Test (KeyIllegalTest,KeyLegalTrain,15 );
	    
	    //�õ���ֵ
	    
	    float DistThreshold =JStatistic.getMeanF(Key_ScoreTrain);
	    System.out.println(DistThreshold);
	    System.out.println("Key_ScoreLegal");
	    System.out.println(Key_ScoreLegal);
//	    sysy
	    }
	    

	

}
