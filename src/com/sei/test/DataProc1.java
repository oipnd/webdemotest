package com.sei.test;

import java.util.ArrayList;
import java.util.Collections;

/*
 * Îº°º
 */

public class DataProc1{
public static ArrayList<Double>FusionScore(ArrayList<ArrayList<Double>>FTestNor,ArrayList<String>kw){
	String strs=new String();
	ArrayList<Double>STestNor=new ArrayList<Double>();
	int len=FTestNor.size();
	if(kw.size()==1){
		strs=kw.get(0);
		if(strs.equals("mouse")){
			for(int i=0;i<len;i++)
				STestNor.add(FTestNor.get(i).get(0));
		}
		if(strs.equals("key")){
			for(int i=0;i<len;i++)
				STestNor.add(FTestNor.get(i).get(1));
		}
		if(strs.equals("key")){
			for(int i=0;i<len;i++)
				STestNor.add(FTestNor.get(i).get(2));
		}
		else if(strs.equals("min")){
			for(int i=0;i<len;i++){
				Collections.sort(FTestNor.get(i));
				STestNor.add(FTestNor.get(i).get(0));
			}
		}
	}
	return STestNor;
}
public static ArrayList<ArrayList<ArrayList<Double>>>SingleVecNor(ArrayList<ArrayList<Double>>FSTrain,ArrayList<ArrayList<Double>>FSTest,String strs){
	ArrayList<ArrayList<ArrayList<Double>>>sub=new ArrayList<ArrayList<ArrayList<Double>>>();
	ArrayList<ArrayList<Double>>FTrainNor=new ArrayList<ArrayList<Double>>();
	ArrayList<ArrayList<Double>>FTestNor=new ArrayList<ArrayList<Double>>();
	ArrayList<Double>Mtrain=new ArrayList<Double>();
	ArrayList<Double>Strain=new ArrayList<Double>();
	int num=FSTrain.size();
	int num1=FSTrain.get(0).size();
	for(int j=0;j<num1;j++){
		double sum=0;
		for(int i=0;i<num;i++){
			sum+=FSTrain.get(i).get(j);
		}
		Mtrain.add(sum/num);
	}
	for(int j=0;j<num1;j++){
		double sum=0;
		for(int i=0;i<num;i++){
			sum+=Math.pow((FSTrain.get(i).get(j)-Mtrain.get(j)), 2);
		}
		sum=Math.sqrt(sum/num);
		if(sum==0)
			sum=1;
		Strain.add(sum);
	}
	if(strs.equals("mean")){
		for(int i=0;i<num;i++){
			ArrayList<Double>temp=new ArrayList<Double>();
			for(int j=0;j<num1;j++){
				temp.add(FSTrain.get(i).get(j)/Mtrain.get(j));
			}
			FTrainNor.add(temp);
		}
		for(int i=0;i<num;i++){
			ArrayList<Double>temp=new ArrayList<Double>();
			for(int j=0;j<num1;j++){
				temp.add(FSTest.get(i).get(j)/Mtrain.get(j));
			}
			FTestNor.add(temp);
		}
	}
	if(strs.equals("std")){
		for(int i=0;i<num;i++){
			ArrayList<Double>temp=new ArrayList<Double>();
			for(int j=0;j<num1;j++){
				temp.add((FSTrain.get(i).get(j)-Mtrain.get(j))/Strain.get(j));
			}
			FTrainNor.add(temp);
		}
		for(int i=0;i<num;i++){
			ArrayList<Double>temp=new ArrayList<Double>();
			for(int j=0;j<num1;j++){
				 temp.add((FSTest.get(i).get(j)-Mtrain.get(j))/Strain.get(j));
			}
			FTestNor.add(temp);
		}
	}
	else if(strs.equals("none")){
		for(int i=0;i<num;i++){
			ArrayList<Double>temp=new ArrayList<Double>();
			for(int j=0;j<num1;j++){
				temp.add(FSTrain.get(i).get(j));
			}
			FTrainNor.add(temp);
		}
		for(int i=0;i<num;i++){
			ArrayList<Double>temp=new ArrayList<Double>();
			for(int j=0;j<num1;j++){
				 temp.add(FSTest.get(i).get(j));
			}
			FTestNor.add(temp);
		}
	}
	sub.add(FTrainNor);
	sub.add(FTestNor);
	return sub;
}
}
