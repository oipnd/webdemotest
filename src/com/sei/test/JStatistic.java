package com.sei.test;

import java.util.ArrayList;

public class JStatistic {
			
		public  static int getSum(ArrayList<Integer> list){
			int ht_sum=0;
			for(Integer item:list){
				ht_sum=ht_sum+Integer.valueOf(item);
			}
			return ht_sum;
		}
		
		public static float getMean(ArrayList<Integer> list){
			int ht_sum=getSum(list);
			int ht_item_num=list.size();
			float ht_mean=(float)ht_sum/ht_item_num;
			return ht_mean;
		}
		
		public static float getMeanF(ArrayList<Float> list){
			float ht_sum=0;
			for(Float item:list){
				ht_sum=ht_sum+Float.valueOf(item);
			}
			int ht_item_num=list.size();
			float ht_mean=(float)ht_sum/ht_item_num;
			return ht_mean;
		}
		
		 public static int getMin(ArrayList<Integer> list) {
//			  if (list == null || list.size()== 0)
//			   return -1;
			  int len = list.size();
			  int min = Integer.valueOf(list.get(0));
			 for(Integer item:list){
				 int temp=Integer.valueOf(item);
				 if (min > temp) min = temp;
				}
			  return min;
			 }
		 
		 public static float getMinF(ArrayList<Float> list) {
//			  if (list == null || list.size()== 0)
//			   return -1;
			  int len = list.size();
			  float min = Float.valueOf(list.get(0));
			 for(Float item:list){
				 float temp=Float.valueOf(item);
				 if (min > temp) min = temp;
				}
			  return min;
			 }
		 
		 public static int getMax(ArrayList<Integer> list) {
//			  if (inputData == null || inputData.length == 0)
//			   return -1;
			  int len = list.size();
			  //System.out.println(len);
			  int max =  Integer.valueOf(list.get(0));
				 for(Integer item:list){
					 int temp=Integer.valueOf(item);
					 if (max<temp) max = temp;
					}
			  return max;
			 }
		 
		 public static float getMaxF(ArrayList<Float> list) {
//			  if (inputData == null || inputData.length == 0)
//			   return -1;
			  int len = list.size();
			  float max =  Float.valueOf(list.get(0));
				 for(Float item:list){
					 float temp=Float.valueOf(item);
					 if (max<temp) max = temp;
					}
			  return max;
			 }
		 
		 public static int getSquareSum(ArrayList<Integer> list) {

			  int len=list.size();
			  int sqrsum = 0;
			  for(Integer item:list){
				int temp=Integer.valueOf(item);
				sqrsum= sqrsum+temp*temp;
			 }		  
			  return sqrsum;
			 }
		 
		 public static float getVariance(ArrayList<Integer> list) {
			  int count = list.size();
			  float average = getMean(list);
			  float result=0;
			  for(Integer item:list){
				int temp=Integer.valueOf(item);
				result= result+(float)(temp-average)*(temp-average);
			 }	
              result=result/count;
			  return result; 
			 }
		 
		 public static float getStandardDiviation(ArrayList<Integer> list) {
			  float result;
	
			  result = (float) Math.sqrt(Math.abs(getVariance(list)));
			  
			  return result;
		 }
		 
		 
	    public static void main(String[] args) {
			ArrayList<Integer> list=new ArrayList<Integer>();
	        for(int i = 0;i<10;i++){
	        	list.add(i+1);
	        }
	        
	        System.out.println(getSum(list));
	        System.out.println(getMean(list));
	        System.out.println(getMin(list));
	        System.out.println(getMax(list));
	        System.out.println(getSquareSum(list));
	        System.out.println(getVariance(list));
	        System.out.println(getStandardDiviation(list));
		}
	


}
