package com.sei.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class feaGet {
//	public static int num=0;
	public static ArrayList<ArrayList<Float>> HumFeaFinal(String modetype,String ori_path){
		ArrayList<ArrayList<Float>> fea= new ArrayList<ArrayList<Float>>();
		File[] users=new File(ori_path).listFiles();
		for(File user:users){
			String timepath=user.getPath()+"\\";
			File[] timelist=new File(timepath).listFiles();
			for(File time:timelist){
				String txtpath=time.getPath()+"\\";
				File[] txtlist=new File(txtpath).listFiles();
				for(File txt:txtlist){
					String path=txt.getPath();
					ArrayList<ArrayList<Integer>> keyTimeData=featureGet(modetype,path);
					ArrayList<Float> keyHoldfea=feaEx.featureEx(keyTimeData.get(0));
					ArrayList<Float> keyinterfea=feaEx.featureEx(keyTimeData.get(1));
//					special目前没有用
//					ArrayList<Float> specialfea=feaEx.specialfeaEx(keyTimeData.get(0),keyTimeData.get(1));
					keyHoldfea.addAll(keyinterfea);
					fea.add(keyHoldfea);
				}
			}
		}
		return fea;
	}
	
	public static ArrayList<ArrayList<Float>> BotFeaFinal(String modetype,String ori_path){
//		C:\\Users\\Administrator\\Desktop\\HWbot4\\logintql\\login
		ArrayList<ArrayList<Float>> Botfea= new ArrayList<ArrayList<Float>>();
		File[] txtlist=new File(ori_path).listFiles();
		for(File txt:txtlist){
			String path=txt.getPath();
			ArrayList<ArrayList<Integer>> keyTimeData=featureGet(modetype,path);
			ArrayList<Float> keyHoldfea=feaEx.featureEx(keyTimeData.get(0));
			ArrayList<Float> keyinterfea=feaEx.featureEx(keyTimeData.get(1));
//			special目前没有用
//			ArrayList<Float> specialfea=feaEx.specialfeaEx(keyTimeData.get(0),keyTimeData.get(1));
			keyHoldfea.addAll(keyinterfea);
			Botfea.add(keyHoldfea);
		}

		return Botfea;
	}
	
	public static ArrayList<ArrayList<Float>> KeyDataGetFinal(String modetype,String ori_path){
		//E:\WebTestData
		ArrayList<ArrayList<Float>> Botfea= new ArrayList<ArrayList<Float>>();

		File[] users=new File(ori_path).listFiles();
		for(File user:users){
			String timepath=user.getPath()+"\\";
			File[] txtlist=new File(timepath).listFiles();
			for(File txt:txtlist){
				String path=txt.getPath();
				ArrayList<ArrayList<Integer>> keyTimeData=featureGet(modetype,path);
				//System.out.println(keyTimeData);
				ArrayList<Float> keyHoldfea=feaEx.featureEx(keyTimeData.get(0));
				ArrayList<Float> keyinterfea=feaEx.featureEx(keyTimeData.get(1));
//				ArrayList<Float> specialfea=feaEx.specialfeaEx(keyTimeData.get(0),keyTimeData.get(1));
				keyHoldfea.addAll(keyinterfea);
				Botfea.add(keyHoldfea);
			}
		}
		
		return Botfea;
	}
	
	
	public static ArrayList<ArrayList<Integer>> featureGet(String modetype,String path){
		//注意此函数将getKeyDown_Up，featureGet写到了一起。
		ArrayList<Integer> keyHoldTime=new ArrayList<Integer>();
		ArrayList<String> keyDownList=new ArrayList<String>();
		ArrayList<String> keyUpList=new ArrayList<String>();
		
		ArrayList<Integer> keyDown_Up=new ArrayList<Integer>();
		ArrayList<ArrayList<String>> keyCollect=new ArrayList<ArrayList<String>>();//缓冲记录，String[0]记录键值，String[]记录时间
		
		String ss=null;
		BufferedReader bf =null;
		
		try {
           bf=new BufferedReader(new FileReader(path));
		   while((ss=bf.readLine())!=null){	
			   String[] Str=ss.split(" ");			  
			   if(Str[0].equals(modetype)){
				   if(Str[1].equals("keydown")&&(!Str[2].equals("13"))){
					   if((!Str[2].equals("8"))&&(!Str[2].equals("16"))){
						  keyCollect.add(new ArrayList<String>(){{add(Str[2]);add(Str[3]);}}) ;
					   }
				   }
				   if(Str[1].equals("keyup")||Str[1].equals("keyUp")){
					   if(!Str[2].equals("13")){
					   int flag=-1;
					   for(int item = 0;item<keyCollect.size();item++){
						   if(Str[2].equals(keyCollect.get(item).get(0))){
							   flag=item;
							   break;
						   }
					   }
					   if(flag!=-1){
						   if((!Str[2].equals("8"))&&(!Str[2].equals("16"))){
							   keyDownList.add(keyCollect.get(flag).get(1));
							   keyUpList.add(Str[3]);
						   }
						   int holdtime=(int) (Long.parseLong(Str[3])-Long.parseLong(keyCollect.get(flag).get(1)));
						   keyHoldTime.add(holdtime);
						   keyCollect.remove(flag);
					   }
					   
				      }
				   }

			   }			 			   
		   }
	    } catch (IOException e) {

		throw new RuntimeException(e);  
	    }
		finally{
			if(bf!=null){
				try {
					bf.close();
				} catch (IOException e) {
				e.printStackTrace();
				}
			}
		}
		for(int i=0;i<(keyDownList.size()-1);i++){
			int keyTime=(int) (Long.parseLong(keyDownList.get(i+1))-Long.parseLong(keyUpList.get(i)));
			if(Math.abs(keyTime)<1000){
				keyDown_Up.add(keyTime);
			}
		}
		
		return  new ArrayList<ArrayList<Integer>>(){{add(keyHoldTime);add(keyDown_Up);}};
		
	}
	
	public static ArrayList<ArrayList<Float>> KeyDataGetFinal_File(String modetype,String ori_path){
//		C:\\Users\\Administrator\\Desktop\\HWbot4\\logintql\\login
		ArrayList<ArrayList<Float>> Botfea= new ArrayList<ArrayList<Float>>();

//		File[] users=new File(ori_path).listFiles();
////		System.out.println(users[0]);
//		for(File user:users){
//			String timepath=user.getPath()+"\\";
//			File[] txtlist=new File(ori_path).listFiles();
//			for(File txt:txtlist){
				String path=ori_path;
				ArrayList<ArrayList<Integer>> keyTimeData=featureGet(modetype,path);
				ArrayList<Float> keyHoldfea=feaEx.featureEx(keyTimeData.get(0));
				ArrayList<Float> keyinterfea=feaEx.featureEx(keyTimeData.get(1));
//				specialĿǰû����
//				ArrayList<Float> specialfea=feaEx.specialfeaEx(keyTimeData.get(0),keyTimeData.get(1));
				keyHoldfea.addAll(keyinterfea);
				Botfea.add(keyHoldfea);
//			}
//		}
		
//		System.out.println(Botfea.get(0).size());
		return Botfea;
	}	
	
	public static void main(String[] args) {
		String modetype="login";
	    String  HumDataPath="E:\\WebTestData\\";
	    String BotDataPath="E:\\WebAttackData\\";
	    
	    
	   // ArrayList<ArrayList<Float>> KeyFeaBot=feaGet.KeyDataGetFinal(modetype,BotDataPath);	 
	    ArrayList<ArrayList<Float>> KeyFeaHuman=feaGet.KeyDataGetFinal(modetype,HumDataPath);
	    
//	    ArrayList<ArrayList<Float>> ff=HumFeaFinal(modetype,HumDataPath);
//	    System.out.println(ff.get(0).size());
	}
	

}


