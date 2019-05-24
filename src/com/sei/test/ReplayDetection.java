package com.sei.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReplayDetection {
	File mFile;
	FileWriter mFW;
	BufferedWriter mWriter;
	ArrayList<ArrayList<Float>> featureList = new ArrayList<ArrayList<Float>>();
	float thresValue = 1;

	public ReplayDetection(String motionType,String sceneType,ArrayList<ArrayList<Float>> testFeatureList){
		mFile = CreateFile(motionType,sceneType);
        try {
			mFW = new FileWriter(mFile,true);
			mWriter = new BufferedWriter(mFW);
			featureList = testFeatureList;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断当前测试样本是否为回放攻击
	 * <P/>
	 * 方法：将当前样本特征向量与本小时内的样本特征向量分别相减后求和，若和值小于阈值则判定为回放攻击，否则判定为正常操作
	 *   
	 * @return 判定结果，TRUE为回放攻击，FALSE为正常操作
	 */
	public boolean IsReplayAttack(){
		try {
            BufferedReader mBR = new BufferedReader(new FileReader(mFile.getAbsolutePath()));
            String bufStr = null;
            if(mFile.length()==0){
            	WriteToFile();
                return false;
            }
            double minSum = Double.MAX_VALUE;
            while ((bufStr=mBR.readLine())!=null){
                String[] tempArray = bufStr.split(" ");
                double[] tempDoubleArray = new double[tempArray.length];
                double sum = 0;
                for (int index = 0;index<tempArray.length;index++){
                    tempDoubleArray[index] = Double.parseDouble(tempArray[index]);
                    sum += Math.abs(featureList.get(0).get(index)-tempDoubleArray[index]);
                }
                minSum=minSum<sum?minSum:sum;
                if(sum<thresValue){
                	mBR.close();
                    return true;
                }
            }
            mBR.close();
            if(minSum>thresValue)
            {
                WriteToFile();
                return false;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return false;
	}
	
	/**
	 * 将特征写入文件
	 */
	public void WriteToFile(){
        String wStr = "";
        for (Float tNum:featureList.get(0)){
            wStr += tNum+" ";
        }
        wStr+="\n";
        try {
			mWriter.write(wStr);
			mWriter.flush();
	        mWriter.close();
	        mFW.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成特征记录文件，在D盘的ReplayData文件夹下，分为mouse和key两种类型存放，二级目录名为操作场景，文件名为当前时间
	 * <P/>
	 * 方法：每次判断目录或相应文件是否存在，若不存在则生成
	 * 
	 * @param motionType 一级子目录名，特征类型，取值"mouse","key"
	 * @param sceneType  二级子目录名，场景类型，取值"register","changePwd","login","seckill"
	 *    
	 * @return dir 生成的文件
	 */
	public File CreateFile(String motionType,String sceneType) {
        String dirPath = "D:\\ReplayData\\"+motionType+"\\"+sceneType+"\\";
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();//生成目录
        }
        String fullPathName = dirPath + GetTime() + ".txt";
        File dir = new File(fullPathName);
        if (!dir.exists()) {
            try {
                dir.createNewFile();//生成文件
                dir.setWritable(true);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return dir;
    }

	/**
	 * 获取当前时间，精确到小时（24时制）
	 * <P/>
	 * 
	 * @return timeString 时间字符串
	 */
    public String GetTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHH");//年月日小时
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String timeString = formatter.format(curDate);
        return timeString;
    }

}
