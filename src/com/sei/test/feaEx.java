package com.sei.test;

import java.util.ArrayList;
import java.util.Collections;

public class feaEx {
	public static ArrayList<Float> featureEx(ArrayList<Integer> keyHoldTime){
		ArrayList<Float> featureFinal=new ArrayList<Float>();
		
		float ht_mean=JStatistic.getMean(keyHoldTime);
		int ht_max=JStatistic.getMax(keyHoldTime);
		int ht_min=JStatistic.getMin(keyHoldTime);
		float ht_var=JStatistic.getStandardDiviation(keyHoldTime);
		int ht_range=ht_max-ht_min;
		
		Collections.sort(keyHoldTime);
		int n=keyHoldTime.size();
		int ht_med=0;
		if(n%2==0){
			ht_med=keyHoldTime.get((n-1)/2);
		}else{
			ht_med=(keyHoldTime.get(n/2)+keyHoldTime.get(n/2-1))/2;
		}
		
		int t_Dymean=0;
		int t_Xymean=0;
		int t_Eqmean=0;
		for(Integer item:keyHoldTime){
			int tempnum=Integer.valueOf(item);
			if(tempnum>ht_mean)t_Dymean++;
			else if (tempnum<ht_mean)t_Xymean++;
			else t_Eqmean++;
		}
		float ht_Dymean=(float)t_Dymean/n;
	    float ht_Xymean=(float)t_Xymean/n;
		float ht_Eqmean=(float)t_Eqmean/n;
		
		int Q20=(int) (Math.ceil(n*0.2)-1);
		int Q40=(int) (Math.ceil(n*0.4)-1);
		int Q60=(int) (Math.ceil(n*0.6)-1);
		int Q80=(int) (Math.ceil(n*0.8)-1);
		
	    int ht_Q20=keyHoldTime.get(Q20);
	    int ht_Q40=keyHoldTime.get(Q40);
	    int ht_Q60=keyHoldTime.get(Q60);
	    int ht_Q80=keyHoldTime.get(Q80);
		
	    featureFinal.add((float)ht_mean);
	    featureFinal.add((float)ht_med);
	    featureFinal.add((float)ht_max);
	    featureFinal.add((float)ht_min);
	    featureFinal.add((float)ht_Dymean);
	    featureFinal.add((float)ht_Xymean);
	    featureFinal.add((float)ht_Eqmean);
	    featureFinal.add((float)ht_Q20);
	    featureFinal.add((float)ht_Q40);
	    featureFinal.add((float)ht_Q60);
	    featureFinal.add((float)ht_Q80);
	    featureFinal.add((float)ht_var);
	    featureFinal.add((float)ht_range);
	    
	    
//	    System.out.println(featureFinal);
		return featureFinal;
	}
	
	public static ArrayList<Float> specialfeaEx(ArrayList<Integer> keyHoldTime, ArrayList<Integer> keyDown_Up){
		ArrayList<Float> specialfea =new ArrayList<Float>();
		int count=0;
		int total=keyDown_Up.size();
		for(Integer item:keyDown_Up){
			if(Integer.valueOf(item)<0)count++;
		}
		specialfea.add((float)count/total);
		/////////////////////////////////////////////////////////////////////
		/*
		 * 补充加入特殊键值的延迟时间的代码
		 * 新的特征 specialradio shiftdata.get(0),shiftdata.get(1)......
		 * keyHoldTime类型 ArrayList<ArrayList<String>>,表示[["keyCode","timeup1","timeup2"...]...]
		 * 涉及 specialitems
		 * 
		 */
		
		/* 
		      ArrayList<Float> shiftdata=specialitems(keyHoldTime,'10')
              ArrayList<Float> tabdata=specialitems(keyHoldTime,'9')
              ArrayList<Float> backdata=specialitems(keyHoldTime,'8')
              int total=0
              for(int i=0;i<keyHoldTime.size();i++){
                  
                 total=total+keyHoldTime.get(i).size()-1;
              }
              specialradio=(float)(shiftdata.get(0)+tabdata.get(0)+backdata.get(0))/total;
		      
		 */
		
		
		return specialfea;
	}
	
	public static ArrayList<Float> specialitems(ArrayList<ArrayList<String>> keyHoldTime, String keyvalue){
		ArrayList<Float> specialitems =new ArrayList<Float>();
		float len=0;
		float mean=0;
		for(ArrayList<String> temp :keyHoldTime){
			if(temp.get(0).equals(keyvalue)){
				int sum=0;
				for(int i=1;i<temp.size();i++){
					sum=sum+Integer.valueOf(temp.get(i));
				}
				mean=(float)sum/(temp.size()-1);
				len=(float)(temp.size()-1);
				break;
			}
			
		}
		
		specialitems.add(len);
		specialitems.add(mean);
		
		return specialitems;
	}
	
}
