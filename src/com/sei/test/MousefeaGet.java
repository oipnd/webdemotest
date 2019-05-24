package com.sei.test;

import java.util.ArrayList;

/*
 * ����bug�ܽ�:
 * 1.int�ͼ�ʱת��Ϊfloat��
 * 2.�µ�ArrayListҪ��ʼ������Ҫֱ���������е�ArrayList�������ڸı��µ�ArrayList�Ὣԭʼ��ArrayList�ı䣬���𲻱�Ҫ�鷳
 * 3.+1-1һ��Ҫ�����
 * 4.�Զ������������ʼλ�ú���ֹλ��Ҫע��д��
 * 5.java��̬������������
 * 
 * �Ժ���д���ĳ�����������https://github.com/fatfishZhao/
 * ����Ϣ��
 * 2016.12.16
 */


public class MousefeaGet {
	/*
	 * ȡ��HumanNamesChosen�������������modetype�Ѿ�û������
	 */
	public static ArrayList<ArrayList<Float>> HumanSectionData(String modetype,String FilePath){
		ArrayList<ArrayList<ArrayList<String>>> AllUsers = FileOp.ReadDataFromTextFile(FilePath);
		ArrayList<ArrayList<String>> HumanData = new ArrayList<ArrayList<String>>();
		//��ά����
		int firstLength = AllUsers.size();
		for(int i=0;i<firstLength;i++){
			int secondLength = AllUsers.get(i).size();
			for(int j=0;j<secondLength;j++){
				HumanData.add(AllUsers.get(i).get(j));
			}
		}
		//��CalFeature.DataFormation ���������޸ģ�����1ֻ��������Ϊ2������
		ArrayList<ArrayList<ArrayList<Integer>>> HumanOperatingChosen = CalFeature.DataFormation(HumanData,1);
		ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> SectionDataHuman = CalFeature.SectionProc(HumanOperatingChosen);
		//System.out.println(SectionDataHuman.get(0));
		ArrayList<ArrayList<Float>> SectionData = CalFeaFromSectionData(SectionDataHuman);
		return SectionData;
	}
	
	public static ArrayList<ArrayList<Float>> BotSectionData(String modetype,String FilePathBot){
		ArrayList<ArrayList<ArrayList<String>>> AllBots = FileOp.ReadDataFromTextFile_file(FilePathBot);
		ArrayList<ArrayList<String>> BotData = new ArrayList<ArrayList<String>>();
		//��ά����
		int firstLength = AllBots.size();
		for(int i=0;i<firstLength;i++){
			int secondLength = AllBots.get(i).size();
			for(int j=0;j<secondLength;j++){
				BotData.add(AllBots.get(i).get(j));
			}
		}
		//��CalFeature.DataFormation ���������޸ģ�����1ֻ��������Ϊ2������
		ArrayList<ArrayList<ArrayList<Integer>>> HumanOperatingChosen = CalFeature.DataFormation(BotData,1);
		ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> SectionDataHuman = CalFeature.SectionProc(HumanOperatingChosen);
		//System.out.println(SectionDataHuman.get(0));
		ArrayList<ArrayList<Float>> SectionData = CalFeaFromSectionData(SectionDataHuman);
		return SectionData;
	}
	
	public static ArrayList<ArrayList<Float>> CalFeaFromSectionData(ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> DataProc){
		ArrayList<ArrayList<Float>> featureHuman = new ArrayList<ArrayList<Float>>();
		
		int firstLength = DataProc.size();
		for (int i=0;i<firstLength;i++){
			ArrayList<Float> RatioStraiIndividual = new ArrayList<Float>();
			ArrayList<Float> MoveDistanceIndividual = new ArrayList<Float>();
			ArrayList<Float> MoveEfficiencyIndividual = new ArrayList<Float>();
			ArrayList<Float> MoveTimeIndividual = new ArrayList<Float>();
			ArrayList<Float> tmpMov = new ArrayList<Float>();
			ArrayList<Float> VeloSeries = new ArrayList<Float>();
			ArrayList<Float> Vvari = new ArrayList<Float>();
			ArrayList<ArrayList<Float>> VelocityVarIndividual = new ArrayList<ArrayList<Float>>();
			ArrayList<Float> AccSeries = new ArrayList<Float>();
			ArrayList<Float> Accvari = new ArrayList<Float>();
			ArrayList<Float> AccVarIndividual = new ArrayList<Float>();
			ArrayList<Float> WholeDuationIndividual = new ArrayList<Float>();
			ArrayList<Float> tmpList = new ArrayList<Float>();
			ArrayList<Float> Theta = new ArrayList<Float>();
			ArrayList<Float> ThetaIndividual = new ArrayList<Float>();
			ArrayList<Float> Omiga = new ArrayList<Float>();
			ArrayList<Float> OmigaIndividual = new ArrayList<Float>();
			int secondLength = DataProc.get(i).size();
			for(int j=0;j<secondLength;j++){
				
				ArrayList<ArrayList<Integer>> SingleSectionData = DataProc.get(i).get(j);
			
				ArrayList<Float> CurV = CalFeature.CalCurvature(SingleSectionData);
				float RBT = CalFeature.RatioBelowTh(CurV, (float)-0.95);
				if(RBT==0){}
				else RatioStraiIndividual.add(RBT);
				tmpMov = CalFeature.CalMovementEfficiency(SingleSectionData);
				if(tmpMov.isEmpty()){}
				else{
					MoveDistanceIndividual.add(tmpMov.get(0));
					MoveEfficiencyIndividual.add(tmpMov.get(1));
					MoveTimeIndividual.add(tmpMov.get(2));
				}
				VeloSeries = CalFeature.GetVeloSeries(SingleSectionData);
				Vvari = CalFeature.CalVeloVariation(VeloSeries);
				if(Vvari.isEmpty()){}
				else{
					VelocityVarIndividual.add(Vvari);
				}
				AccSeries = CalFeature.CalAcelaration(SingleSectionData);
				Accvari = CalFeature.CalAccVariation(AccSeries);
				if(Accvari.isEmpty()){}
				else
					AccVarIndividual.addAll(Accvari);
				float WholeTime = CalFeature.CalWholeDuation(SingleSectionData);
				WholeDuationIndividual.add(WholeTime);
				Theta = CalFeature.CalTheta(SingleSectionData);
				if(Theta.size()==0){}
				else
					ThetaIndividual.add(numJv.Mean(Theta, 0, Theta.size()));
				Omiga = CalFeature.CalOmiga(SingleSectionData, Theta);
				if(Omiga.size()==0){}
				else
					OmigaIndividual.add(numJv.Mean(Omiga, 0, Omiga.size()));
			}
			
			//������뷽ʽ��ԭʼpy���벻һ����ԭ�����ǡ����򡱣������ǡ����򡰣���Ϊ���������Ǻ���ģ���������õ�ʱ�����Ȼ��ArrayList������py����һ��˫�������ȡ��
			tmpList.add(numJv.Mean(RatioStraiIndividual,0,RatioStraiIndividual.size()));			//1
			tmpList.addAll(numJv.Mean2d(VelocityVarIndividual,1));									//2,3
			tmpList.add(numJv.Mean(MoveEfficiencyIndividual,0,MoveEfficiencyIndividual.size()));	//4
			tmpList.add(numJv.Mean(MoveTimeIndividual,0,MoveTimeIndividual.size()));				//5
			tmpList.add(numJv.Sum1dFloat(WholeDuationIndividual));									//6
			tmpList.add(numJv.Std(MoveEfficiencyIndividual,0,MoveEfficiencyIndividual.size()));		//7
			tmpList.add(numJv.Mean(AccVarIndividual,0,AccVarIndividual.size()));					//8
			tmpList.add(numJv.Mean(ThetaIndividual, 0, ThetaIndividual.size()));					//9
			tmpList.add(numJv.Mean(OmigaIndividual, 0, OmigaIndividual.size()));					//10
			featureHuman.add(tmpList);
		}
		return featureHuman;
		
	}
}
