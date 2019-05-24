package com.sei.test;

import java.util.ArrayList;

public class DataProc {
	//�˴���һ����ԭ�������ֳ�������д��һ�ദ��ѵ������һ�ദ����Լ�
	public static ArrayList<ArrayList<Float>> FeaNormalizationGetData(ArrayList<ArrayList<Float>> TrainData){
		
		ArrayList<Float> Minltr =new ArrayList<Float>();
		ArrayList<Float> Maxltr =new ArrayList<Float>();
		ArrayList<Float> Denominator =new ArrayList<Float>();
		ArrayList<ArrayList<Float>> NormalizationData=new ArrayList<ArrayList<Float>>();
		
		int sampleNum=TrainData.size();
		//System.out.println(TrainData);
		int featureNum=TrainData.get(0).size();
		
		for(int i=0;i<featureNum;i++){
			float MinFeature=TrainData.get(0).get(i);
			for(int j=0;j<sampleNum;j++){
//				featureSampleSum=featureSampleSum+TrainData.get(j).get(i);
				if(TrainData.get(j).get(i)<MinFeature){
					MinFeature=TrainData.get(j).get(i);
				}
			}
			Minltr.add(MinFeature);
		}
		
		for(int i=0;i<featureNum;i++){
			float MaxFeature=TrainData.get(0).get(i);
			for(int j=0;j<sampleNum;j++){
//				featureSampleSum=featureSampleSum+TrainData.get(j).get(i);
				if(TrainData.get(j).get(i)>MaxFeature){
					MaxFeature=TrainData.get(j).get(i);
				}
			}
			Maxltr.add(MaxFeature);
		}
				 
		for(int i=0;i<featureNum;i++){
			Denominator.add(Maxltr.get(i)-Minltr.get(i));
		}
		for(int i=0;i<featureNum;i++){
			if(Denominator.get(i)==0)Denominator.set(i, (float)1);
		}
		
		NormalizationData.add(Minltr);
		NormalizationData.add(Denominator);		
		return NormalizationData;		
	}
	
	public static ArrayList<ArrayList<Float>> FeaNormalization(ArrayList<ArrayList<Float>> NormalizationData,ArrayList<ArrayList<Float>> OptData){
		ArrayList<Float> Numerator=NormalizationData.get(0);
		ArrayList<Float> Denominator=NormalizationData.get(1);
		//System.out.println(Denominator.size());
		int featureNum=Denominator.size();
		int sampleNum=OptData.size();
		if(sampleNum!=0){
			for(int j=0;j<featureNum;j++){
				for(int i=0;i<sampleNum;i++){
					float newValue=(OptData.get(i).get(j)-Numerator.get(j))/Denominator.get(j);
					//System.out.println(newValue);
					OptData.get(i).set(j,newValue);
				}
			}			
		}else{
			System.out.println("样本数为空");
		}		
		return OptData;
	}
	
	public static ArrayList<Float> ScoreGet_KNN_Train (ArrayList<ArrayList<Float>> OptData,int NK ){
		//int NK=15;
		ArrayList<Float>OutDistance =new ArrayList<Float>();
		int sampleNum=OptData.size();
		for(int i=0;i<sampleNum;i++){
			ArrayList<ArrayList<Float>> temp=new ArrayList<ArrayList<Float>>();
			for(int j=0;j<sampleNum;j++){
					if(j!=i)temp.add(OptData.get(j));				
			}
			ArrayList<Float> tempDist=OneClassKNN.kNNClassify(OptData.get(i),temp,NK);
			OutDistance.add(JStatistic.getMeanF(tempDist));
		}
		return OutDistance;
	}
	
	public static ArrayList<Float> ScoreGet_KNN_Test (ArrayList<ArrayList<Float>> OptData ,ArrayList<ArrayList<Float>> TrainData,int NK ){
		//int NK=15;
		ArrayList<Float>OutDistance =new ArrayList<Float>();
		int sampleNum=OptData.size();
		for(int i=0;i<sampleNum;i++){
			ArrayList<Float> tempDist=OneClassKNN.kNNClassify(OptData.get(i),TrainData,NK);
			OutDistance.add(JStatistic.getMeanF(tempDist));
		}

		return OutDistance;
	}
}
