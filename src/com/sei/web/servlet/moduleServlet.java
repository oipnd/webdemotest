package com.sei.web.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sei.test.DataProc;
import com.sei.test.JStatistic;
import com.sei.test.RandomList;
import com.sei.test.Serialization;
import com.sei.test.feaGet;

import com.sei.test.*;

/**
 * Servlet implementation class moduleServlet
 */
@WebServlet("/moduleServlet")
public class moduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public moduleServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		String WebType=request.getParameter("WebType");
		
		if(WebType.equals("userLogin")){			
        	loginModelBuild(request,response);
        }else if(WebType.equals("userRegister")){			
        	registerModelBuild(request,response);
        }else if(WebType.equals("userChangePwd")){        	
        	changePwdModelBuild(request,response);      	
        }else if(WebType.equals("userMiaosha")){
        	miaoshaModelBuild(request,response);  
        }else if(WebType.equals("outCollect")){  	
          response.sendRedirect("/WebDemoTest/menu_Main.jsp");
        }
            	
	}

	private void registerModelBuild(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String modetype="register";
		String  HumDataPath="E:\\WebTestData\\register\\";
		//首先判定文件数目是否足够
		String path="E:\\WebTestData\\register\\" ;
		int filenum=getFile(path);
		if(filenum<30){
			request.setAttribute("message", "训练数据不足30，无法生成模型");
			request.getRequestDispatcher("/modelBuild.jsp").forward(request, response);
			return;
			
		}else{
			 ArrayList<ArrayList<Float>> KeyFeaHuman=feaGet.KeyDataGetFinal(modetype,path);
			 ArrayList<ArrayList<Float>> MouseFeaHuman = MousefeaGet.HumanSectionData(modetype, HumDataPath);//鼠标数据
			 //System.out.println(MouseFeaHuman.size());
			 int totalSampleNum=KeyFeaHuman.size();
			 int mousefilenum=MouseFeaHuman.size();
			System.out.println("mousefilenum "+mousefilenum);
			// System.out.println(MouseFeaHuman);
			 int[] randomList=RandomList.getRandomSeq(totalSampleNum);
			 int[] randomList2=RandomList.getRandomSeq(mousefilenum);   
			 int TrainNum = 30;

			 //优化条件，目前是随机获取训练的特征矩阵
			 //此处对于鼠标模型现在的处理策略是在鼠标特征数据小于现在的文件数应用0补齐
			 //改进思路，可以在生成模型是设置标志位，存在session域中，在判定阶段除了判断模型文件是否存在也要判断session域是否为空
			 //在获得标志位之后考虑是否采纳mouse模型
			    ArrayList<ArrayList<Float>> TrainData_key=new ArrayList<ArrayList<Float>>();
			    ArrayList<ArrayList<Float>> TrainData_mouse=new ArrayList<ArrayList<Float>>();
			    if(filenum>30){
			    	for(int i=0;i<TrainNum;i++){
			    		TrainData_key.add(KeyFeaHuman.get(randomList[i]));
			    		if(mousefilenum>30){
			    		TrainData_mouse.add(MouseFeaHuman.get(randomList2[i]));
			    		}
			    	}
			    		if(mousefilenum==30)TrainData_mouse=MouseFeaHuman;
				    	if(mousefilenum<30){
				    		TrainData_mouse=MouseFeaHuman;				    						    					    		
				    }
			    }else{
			    	TrainData_key=KeyFeaHuman;
			    	if(mousefilenum==30)TrainData_mouse=MouseFeaHuman;
			    	if(mousefilenum<30){
			    		TrainData_mouse=MouseFeaHuman;			    		
			    	}			    	
			    }
			    //System.out.println(TrainData_mouse.size());
			    //击键模型生成
			    ArrayList<ArrayList<Float>> NormalizationData_key =DataProc.FeaNormalizationGetData(TrainData_key);
			    ArrayList<ArrayList<Float>> KeyLegalTrain=DataProc.FeaNormalization(NormalizationData_key, TrainData_key) ;
			    ArrayList<Float> Key_ScoreTrain =DataProc.ScoreGet_KNN_Train (KeyLegalTrain ,15);
			    float DistThreshold_key =JStatistic.getMeanF(Key_ScoreTrain);
			    
			     // 保存击键模型
			    Serialization.NormalDataSerialization(NormalizationData_key.get(0),"Numerator","key","register");
			    Serialization.NormalDataSerialization(NormalizationData_key.get(1),"Denominator","key","register");
			    Serialization. ModelSerialization(KeyLegalTrain,"KeyLegalTrain","key","register");
			    Serialization.DistThresholdSerialization(DistThreshold_key,"key","register");
			    
			    
			    
			    //鼠标模型生成
			    ArrayList<ArrayList<Float>> NormalizationData_mouse =DataProc.FeaNormalizationGetData(TrainData_mouse);
			    ArrayList<ArrayList<Float>> MouseLegalTrain=DataProc.FeaNormalization(NormalizationData_mouse, TrainData_mouse) ;
			    ArrayList<Float> Mouse_ScoreTrain =null;
			    if(MouseLegalTrain.size()>=15){
			    	Mouse_ScoreTrain =DataProc.ScoreGet_KNN_Train (MouseLegalTrain,15 );
			    }else{
			    	Mouse_ScoreTrain =DataProc.ScoreGet_KNN_Train (MouseLegalTrain,MouseLegalTrain.size());
			    }
			   
			    float DistThreshold_mouse =JStatistic.getMeanF(Mouse_ScoreTrain);
			    
			    //保存鼠标模型
			    Serialization.NormalDataSerialization(NormalizationData_mouse.get(0),"Numerator","mouse","register");
			    Serialization.NormalDataSerialization(NormalizationData_mouse.get(1),"Denominator","mouse","register");
			    Serialization. ModelSerialization(MouseLegalTrain,"MouseLegalTrain","mouse","register");
			    Serialization.DistThresholdSerialization(DistThreshold_mouse,"mouse","register");
			    
			    request.setAttribute("message", "注册模型生成成功！");
				request.getRequestDispatcher("/modelBuild.jsp").forward(request,response);
				return;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		doGet(request, response);
	}



	private void miaoshaModelBuild(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("message", "秒杀模型待定！");
		request.getRequestDispatcher("/modelBuild.jsp").forward(request,response);
		return;
	}


	private void changePwdModelBuild(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String modetype="changePwd";
		String  HumDataPath="E:\\WebTestData\\changePwd\\";
		//首先判定文件数目是否足够
		String path="E:\\WebTestData\\changePwd\\" ;
		int filenum=getFile(path);
		if(filenum<30){
			request.setAttribute("message", "训练数据不足30，无法生成模型");
			request.getRequestDispatcher("/modelBuild.jsp").forward(request, response);
			return;
			
		}else{
			 ArrayList<ArrayList<Float>> KeyFeaHuman=feaGet.KeyDataGetFinal(modetype,path);
			 ArrayList<ArrayList<Float>> MouseFeaHuman = MousefeaGet.HumanSectionData(modetype, HumDataPath);//鼠标数据
			 //System.out.println(MouseFeaHuman.size());
			 int totalSampleNum=KeyFeaHuman.size();
			 int mousefilenum=MouseFeaHuman.size();
			System.out.println("mousefilenum "+mousefilenum);
			// System.out.println(MouseFeaHuman);
			 int[] randomList=RandomList.getRandomSeq(totalSampleNum);
			 int[] randomList2=RandomList.getRandomSeq(mousefilenum);   
			 int TrainNum = 30;

			 //优化条件，目前是随机获取训练的特征矩阵
			 //此处对于鼠标模型现在的处理策略是在鼠标特征数据小于现在的文件数应用0补齐
			 //改进思路，可以在生成模型是设置标志位，存在session域中，在判定阶段除了判断模型文件是否存在也要判断session域是否为空
			 //在获得标志位之后考虑是否采纳mouse模型
			    ArrayList<ArrayList<Float>> TrainData_key=new ArrayList<ArrayList<Float>>();
			    ArrayList<ArrayList<Float>> TrainData_mouse=new ArrayList<ArrayList<Float>>();
			    if(filenum>30){
			    	for(int i=0;i<TrainNum;i++){
			    		TrainData_key.add(KeyFeaHuman.get(randomList[i]));
			    		if(mousefilenum>30){
			    		TrainData_mouse.add(MouseFeaHuman.get(randomList2[i]));
			    		}
			    	}
			    		if(mousefilenum==30)TrainData_mouse=MouseFeaHuman;
				    	if(mousefilenum<30){
				    		TrainData_mouse=MouseFeaHuman;				    						    					    		
				    }
			    }else{
			    	TrainData_key=KeyFeaHuman;
			    	if(mousefilenum==30)TrainData_mouse=MouseFeaHuman;
			    	if(mousefilenum<30){
			    		TrainData_mouse=MouseFeaHuman;			    		
			    	}			    	
			    }
			    //System.out.println(TrainData_mouse.size());
			    //击键模型生成
			    ArrayList<ArrayList<Float>> NormalizationData_key =DataProc.FeaNormalizationGetData(TrainData_key);
			    ArrayList<ArrayList<Float>> KeyLegalTrain=DataProc.FeaNormalization(NormalizationData_key, TrainData_key) ;
			    ArrayList<Float> Key_ScoreTrain =DataProc.ScoreGet_KNN_Train (KeyLegalTrain ,15);
			    float DistThreshold_key =JStatistic.getMeanF(Key_ScoreTrain);
			    
			     // 保存击键模型
			    Serialization.NormalDataSerialization(NormalizationData_key.get(0),"Numerator","key","changePwd");
			    Serialization.NormalDataSerialization(NormalizationData_key.get(1),"Denominator","key","changePwd");
			    Serialization. ModelSerialization(KeyLegalTrain,"KeyLegalTrain","key","changePwd");
			    Serialization.DistThresholdSerialization(DistThreshold_key,"key","changePwd");
			    
			    
			    
			    //鼠标模型生成
			    ArrayList<ArrayList<Float>> NormalizationData_mouse =DataProc.FeaNormalizationGetData(TrainData_mouse);
			    ArrayList<ArrayList<Float>> MouseLegalTrain=DataProc.FeaNormalization(NormalizationData_mouse, TrainData_mouse) ;
			    ArrayList<Float> Mouse_ScoreTrain =null;
			    if(MouseLegalTrain.size()>=15){
			    	Mouse_ScoreTrain =DataProc.ScoreGet_KNN_Train (MouseLegalTrain,15 );
			    }else{
			    	Mouse_ScoreTrain =DataProc.ScoreGet_KNN_Train (MouseLegalTrain,MouseLegalTrain.size());
			    }
			   
			    float DistThreshold_mouse =JStatistic.getMeanF(Mouse_ScoreTrain);
			    
			    //保存鼠标模型
			    Serialization.NormalDataSerialization(NormalizationData_mouse.get(0),"Numerator","mouse","changePwd");
			    Serialization.NormalDataSerialization(NormalizationData_mouse.get(1),"Denominator","mouse","changePwd");
			    Serialization. ModelSerialization(MouseLegalTrain,"MouseLegalTrain","mouse","changePwd");
			    Serialization.DistThresholdSerialization(DistThreshold_mouse,"mouse","changePwd");
			    
			    request.setAttribute("message", "改密模型生成成功！");
				request.getRequestDispatcher("/modelBuild.jsp").forward(request,response);
				return;
		}
	}



	
	private void loginModelBuild(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String modetype="login";
		String  HumDataPath="E:\\WebTestData\\login\\";
		//首先判定文件数目是否足够
		String path="E:\\WebTestData\\login\\" ;
		int filenum=getFile(path);
		if(filenum<30){
			request.setAttribute("message", "训练数据不足30，无法生成模型");
			request.getRequestDispatcher("/modelBuild.jsp").forward(request, response);
			return;
			
		}else{
			 ArrayList<ArrayList<Float>> KeyFeaHuman=feaGet.KeyDataGetFinal(modetype,path);
			 ArrayList<ArrayList<Float>> MouseFeaHuman = MousefeaGet.HumanSectionData(modetype, HumDataPath);//鼠标数据
			 //System.out.println(MouseFeaHuman.size());
			 int totalSampleNum=KeyFeaHuman.size();
			 int mousefilenum=MouseFeaHuman.size();
			// System.out.println("mousefilenum "+mousefilenum);
			 //System.out.println(MouseFeaHuman);
			 int[] randomList=RandomList.getRandomSeq(totalSampleNum);
			 int[] randomList1=RandomList.getRandomSeq(mousefilenum);     
			 int TrainNum = 30;

			 //优化条件，目前是随机获取训练的特征矩阵
			 //此处对于鼠标模型现在的处理策略是在鼠标特征数据小于现在的文件数应用0补齐
			 //改进思路，可以在生成模型是设置标志位，存在session域中，在判定阶段除了判断模型文件是否存在也要判断session域是否为空
			 //在获得标志位之后考虑是否采纳mouse模型
			    ArrayList<ArrayList<Float>> TrainData_key=new ArrayList<ArrayList<Float>>();
			    ArrayList<ArrayList<Float>> TrainData_mouse=new ArrayList<ArrayList<Float>>();
			    if(filenum>30){
			    	for(int i=0;i<TrainNum;i++){
			    		TrainData_key.add(KeyFeaHuman.get(randomList[i]));
			    		if(mousefilenum>30){
			    		TrainData_mouse.add(MouseFeaHuman.get(randomList1[i]));
			    		}
			    	}
			    		if(mousefilenum==30)TrainData_mouse=MouseFeaHuman;
				    	if(mousefilenum<30){
				    		TrainData_mouse=MouseFeaHuman;				    						    					    		
				    }
			    }else{
			    	TrainData_key=KeyFeaHuman;
			    	if(mousefilenum==30)TrainData_mouse=MouseFeaHuman;
			    	if(mousefilenum<30){
			    		TrainData_mouse=MouseFeaHuman;			    		
			    	}			    	
			    }
			    //System.out.println(TrainData_mouse.size());
			    //击键模型生成
			    ArrayList<ArrayList<Float>> NormalizationData_key =DataProc.FeaNormalizationGetData(TrainData_key);
			    ArrayList<ArrayList<Float>> KeyLegalTrain=DataProc.FeaNormalization(NormalizationData_key, TrainData_key) ;
			    ArrayList<Float> Key_ScoreTrain =DataProc.ScoreGet_KNN_Train (KeyLegalTrain ,15);
			    float DistThreshold_key =JStatistic.getMeanF(Key_ScoreTrain);
			    
			     // 保存击键模型
			    Serialization.NormalDataSerialization(NormalizationData_key.get(0),"Numerator","key","login");
			    Serialization.NormalDataSerialization(NormalizationData_key.get(1),"Denominator","key","login");
			    Serialization. ModelSerialization(KeyLegalTrain,"KeyLegalTrain","key","login");
			    Serialization.DistThresholdSerialization(DistThreshold_key,"key","login");
			    
			    
			    
			    //鼠标模型生成
			    ArrayList<ArrayList<Float>> NormalizationData_mouse =DataProc.FeaNormalizationGetData(TrainData_mouse);
			    ArrayList<ArrayList<Float>> MouseLegalTrain=DataProc.FeaNormalization(NormalizationData_mouse, TrainData_mouse) ;
			    ArrayList<Float> Mouse_ScoreTrain =null;
			    if(MouseLegalTrain.size()>=15){
			    	Mouse_ScoreTrain =DataProc.ScoreGet_KNN_Train (MouseLegalTrain,15 );
			    }else{
			    	Mouse_ScoreTrain =DataProc.ScoreGet_KNN_Train (MouseLegalTrain,MouseLegalTrain.size());
			    }
			   
			    float DistThreshold_mouse =JStatistic.getMeanF(Mouse_ScoreTrain);
			    
			    //保存鼠标模型
			    Serialization.NormalDataSerialization(NormalizationData_mouse.get(0),"Numerator","mouse","login");
			    Serialization.NormalDataSerialization(NormalizationData_mouse.get(1),"Denominator","mouse","login");
			    Serialization. ModelSerialization(MouseLegalTrain,"MouseLegalTrain","mouse","login");
			    Serialization.DistThresholdSerialization(DistThreshold_mouse,"mouse","login");
			    
			    request.setAttribute("message", "登录模型生成成功！");
				request.getRequestDispatcher("/modelBuild.jsp").forward(request,response);
				return;
	    }

	}
	
	 public int getFile(String filepath) {
	     int filecount=0;

        File file = new File(filepath);  
        File[] listfile = file.listFiles();  
        for (int i = 0; i < listfile.length; i++) {  
        	//System.out.println(listfile[i].getPath()); 
	        File downfile = new File(listfile[i].getPath());  
	        File[] downlistfile = downfile.listFiles();
	        for(int j=0;j<downlistfile.length;j++){ 
                filecount++;  
	        }

        }
        return filecount;
    } 
	 
	 

}
