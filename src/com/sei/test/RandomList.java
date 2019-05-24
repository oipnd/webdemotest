package com.sei.test;

import java.util.ArrayList;
import java.util.Random;

/*
 *  生成0~length的随机序列
 */
public class RandomList {
	public static int[] getRandomSeq(int length){
		int[] array = new int[length];
	    for (int i = 0; i < array.length; i++) {
	        array[i] = i;
	    }
	    int x = 0, tmp = 0;
	    Random random = new Random();
	    
	    for (int i = array.length - 1; i > 0; i--) {
	        x = random.nextInt(i + 1);
	        tmp = array[i];
	        array[i] = array[x];
	        array[x] = tmp;
	    }
	    return array;
	    
	} 
	
	public static void main(String[] args) {
		for(int i=0;i<10;i++){
			int[] m=getRandomSeq(50);
			ArrayList<Integer> list=new ArrayList<Integer>();
			for(int j=0;j<m.length ;j++){
				list.add(m[j]);
			}
			System.out.println(list);
		}
	}
	

}
