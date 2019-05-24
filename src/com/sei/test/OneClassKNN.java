package com.sei.test;

import java.util.ArrayList;
import java.util.Collections;

public class OneClassKNN {
	public static ArrayList<Float>kNNClassify(ArrayList<Float>newInput,ArrayList<ArrayList<Float>>dataSet,int k){
	int TrainSamples=dataSet.size();
	/*
	 * 每维特征做距离，所有维距离求和，作为样本距离，返回前k个距离
	 */
	ArrayList<ArrayList<Float>>newInput2N=new ArrayList<ArrayList<Float>>();
	ArrayList<ArrayList<Float>> diff=new ArrayList<ArrayList<Float>>();
	
	for(int i=0;i<TrainSamples;i++)newInput2N.add(newInput);
	//newInput2N N*M维特征向量
	for(int i=0;i<TrainSamples;i++){
		ArrayList<Float>d1=new ArrayList<Float>();
		int number1=dataSet.get(i).size();
		for(int j=0;j<number1;j++){
			float temp=(float)Math.abs(newInput2N.get(i).get(j)-dataSet.get(i).get(j));
			d1.add(temp);
	      }
		diff.add(d1);
	}	
	
	ArrayList<Float>distance=new ArrayList<Float>();
	for(int i=0;i<TrainSamples;i++){
		float sum=0;
		for(int j=0;j<diff.get(i).size();j++){
			sum+=diff.get(i).get(j);
		}
	distance.add(sum);	
}
	ArrayList<Float>kNNnear=new ArrayList<Float>();
	Collections.sort(distance);
	for(int i=0;i<k;i++){
		float temp=distance.get(i);
		kNNnear.add(temp);
	}
//	System.out.println("kNNnear");
//	System.out.println(kNNnear);
return kNNnear;		
}
}
