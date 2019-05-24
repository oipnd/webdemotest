package com.sei.web.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sei.test.*;



/**
 * Servlet implementation class AttackServlet
 */
@WebServlet("/AttackServlet")
public class AttackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public AttackServlet() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		String WebType=request.getParameter("method");

		if(WebType.equals("userLogin")){			
			LoginAttackTest(request,response);
		}else if(WebType.equals("userRegister")){			
			registerAttackTest(request,response);
		}else if(WebType.equals("userChangePwd")){        	
			changeAttackTest(request,response);      	
		}else if(WebType.equals("userMiaosha")){
			miaoshaAttackTest(request,response);  
		}else if(WebType.equals("outCollect")){  	
			response.sendRedirect("/WebDemoTest/menu_Main.jsp");
		}

	}

	private void registerAttackTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = request.getParameter("method");
		String message = null;

		String username = request.getParameter("username");
		//System.out.println("username="+username);
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		//		System.out.println("password="+password);
		String data=request.getParameter("StrData");

		//锟斤拷锟斤拷锟街わ拷锟斤拷锟斤拷
		String sessionCode=(String) request.getSession().getAttribute("session_vcode");
		//System.out.println(sessionCode);
		String paramCode = request.getParameter("verifyCode");

		String count="0";
		User u = null;

		ReaderDBFile rdf = new ReaderDBFile();
		u = (User) rdf.ReaderFileUse(username, password1,count);

		int filenum1=getFile("E:\\ModelForWeb\\register\\key\\");
		if(filenum1!=4){
			request.setAttribute("message", "未生成模型,不能进行检测操作！");
			request.getRequestDispatcher("/registerTest.jsp").forward(request, response);
			return;
		}

		if (username == ""){
			message = "用户名为空，注册失败";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/registerTest.jsp").forward(request, response);

		}

		else if(password1==""||password2=="") {
			message = "密码为空，注册失败！";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/registerTest.jsp").forward(request, response);

		}
		else if(!password1.equals(password2)) {
			message = "两次密码输入不同，请再次输入！";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/registerTest.jsp").forward(request, response);

		}
		else if (u.getUsername() == username){
			message = "用户名已存在，注册失败！";
			System.out.println("u.getUsername()="+u.getUsername());
			request.setAttribute("message", message);
			request.getRequestDispatcher("/registerTest.jsp").forward(request, response);

		}

		else{

			if(paramCode==null) {
				request.setAttribute("message", "请填写验证码！");
				request.getRequestDispatcher("/registerTest.jsp").forward(request, response);				 
				return;
			}

			if(!paramCode.equalsIgnoreCase(sessionCode)) {
				request.setAttribute("message", "验证码错误！");
				request.getRequestDispatcher("/registerTest.jsp").forward(request, response);
				return;
			}


			WriterDBFile wdf = new WriterDBFile();
			wdf.WriterFileUse(username + "\t\t" + password1 + "\t\t"+count+"\r\n");

			String attackFile=DataToFile(data,username,"register");
			//String attackFile=DataToFile(data,username,"login","screen");
			// System.out.println(attackFile);
			int judge=RobotDetection("register",attackFile);
			//System.out.println("judge "+judge);
			if(judge==1){
				System.out.println("isRobot");
				response.sendRedirect("/WebDemoTest/isRobot.jsp");
				//request.getRequestDispatcher("/isRobot.jsp").forward(request,response);
				// return;
			}
			if(judge==0){
				System.out.println("isHuman");
				response.sendRedirect("/WebDemoTest/isHuman.jsp");
				//request.getRequestDispatcher("/isHuman.jsp").forward(request,response);
				// return;
			} 

		} 
	}


	private void changeAttackTest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");

		String message = null;
		BufferedReader br=null;
		String line;
		String buf = "";

		String username = request.getParameter("username");
		String oldpassword = request.getParameter("oldpassword");
		String newpassword1 = request.getParameter("newpassword1");
		String newpassword2 = request.getParameter("newpassword2");
		String data=request.getParameter("StrData");

		//		System.out.println("username="+username);
		//		System.out.println("oldpassword="+oldpassword);
		//		System.out.println("newpassword1="+newpassword1);
		//		System.out.println("newpassword2="+newpassword2);


		//�����֤�����
		String sessionCode=(String) request.getSession().getAttribute("session_vcode");
		System.out.println(sessionCode);
		String paramCode = request.getParameter("verifyCode");

		String count="0";
		User u = null;
		ReaderDBFile rdf = new ReaderDBFile();

		if (username==null||username.length()==0){
			message = "用户名为空，更改失败！";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/changePwdTest.jsp").forward(request, response);
			return;
		}
		if (oldpassword==null||oldpassword.length()==0){
			message = "原密码为空，更改失败！";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/changePwdTest.jsp").forward(request, response);
			return;
		}
		if (newpassword1==""||newpassword2==""){
			message = "新密码输入有误，请重新输入！";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/changePwdTest.jsp").forward(request, response);
			return;
		}

		else if(!newpassword1.equals(newpassword2)) {
			message = "两次密码输入不同，请重新填写！";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/changePwdTest.jsp").forward(request, response);
			return;
		}
		u = (User) rdf.ReaderFileUse(username, oldpassword,count);
		if (u.getUsername() == username&&u.getPassword()==oldpassword){

			if(paramCode==null) {
				request.setAttribute("message", "请填写验证码！");
				request.getRequestDispatcher("/changePwdTest.jsp").forward(request, response);
				return;
			}

			if(!paramCode.equalsIgnoreCase(sessionCode)) {
				request.setAttribute("message", "验证码错误！");
				request.getRequestDispatcher("/changePwdTest.jsp").forward(request, response);
				return;
			}

			br = new BufferedReader(new FileReader("D://apache-tomcat-7.0.70//webapps//WebDemoTest//userID//dbfile.txt"));


			while ((line = br.readLine()) != null) {

				String[] linesarr = line.split("\\s+");
				//				System.out.println(ll);
				if (linesarr[linesarr.length - 3].equals(username)){
					buf+=username+"\t\t"+newpassword1+"\t\t"+linesarr[linesarr.length - 1]+"\n";
					System.out.println(buf);
				}else{
					buf+=line+"\n";
				}
			}
			br.close();

			WriterDBFile wdf = new WriterDBFile();
			wdf.WriterFileCover(buf);


			String attackFile= DataToFile(data,username,"changePwd");
			//String attackFile=DataToFile(data,username,"login","screen");
			// System.out.println(attackFile);
			int judge=RobotDetection("changePwd",attackFile);
			//System.out.println("judge "+judge);
			if(judge==1){
				System.out.println("isRobot");
				response.sendRedirect("/WebDemoTest/isRobot.jsp");
				//request.getRequestDispatcher("/isRobot.jsp").forward(request,response);
				// return;
			}
			if(judge==0){
				System.out.println("isHuman");
				response.sendRedirect("/WebDemoTest/isHuman.jsp");
				//request.getRequestDispatcher("/isHuman.jsp").forward(request,response);
				// return;
			} 

		}else{
			request.setAttribute("message", "用户名或密码不正确！");		
			request.getRequestDispatcher("/changePwdTest.jsp").forward(request,response);
		}

	}


	private void miaoshaAttackTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		response.sendRedirect("/WebDemoTest/menu_Attack.jsp");
		return;
	}


	private void LoginAttackTest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String data=request.getParameter("StrData");

		String count=null;
		String modetype="login";
		//�����֤�����
		//		String sessionCode=(String) request.getSession().getAttribute("session_vcode");
		//		System.out.println(sessionCode);
		//		String paramCode = request.getParameter("verifyCode");



		User u = null;
		ReaderDBFile rdf = new ReaderDBFile();
		u = (User) rdf.ReaderFileUse(username, password,count);

		int filenum1=getFile("E:\\ModelForWeb\\login\\key\\");
		if(filenum1!=4){
			request.setAttribute("message", "未生成模型,不能进行检测操作！");
			request.getRequestDispatcher("/loginTest.jsp").forward(request, response);
			return;
		}


		if (u.getUsername() == username&&u.getPassword()==password) {
			//			if(paramCode==null) {
			//				request.setAttribute("message", "请填写验证码！");
			//				request.getRequestDispatcher("/loginTest.jsp").forward(request, response);
			//				return;
			//			}
			//			
			//			if(!paramCode.equalsIgnoreCase(sessionCode)) {
			//				request.setAttribute("message", "验证码错误！");
			//				request.getRequestDispatcher("/loginTest.jsp").forward(request, response);
			//				return;
			//			}
			//开始进行检测分析
			String attackFile=DataToFile(data,username,"login");
			// System.out.println(attackFile);
			int judge=RobotDetection("login",attackFile);
			//System.out.println("judge "+judge);
			if(judge==1){
				System.out.println("isRobot");
				response.sendRedirect("/WebDemoTest/isRobot.jsp");
				//request.getRequestDispatcher("/isRobot.jsp").forward(request,response);
				// return;
			}
			if(judge==0){
				System.out.println("isHuman");
				response.sendRedirect("/WebDemoTest/isHuman.jsp");
				//request.getRequestDispatcher("/isHuman.jsp").forward(request,response);
				// return;
			}

		}else{
			request.setAttribute("message", "用户名或密码不正确！");
			request.getRequestDispatcher("/loginTest.jsp").forward(request,response);
		}

	}


	public String DataToFile(String s,String name,String method) throws IOException{
		String path=null;
		File filePath;

		path = "E:\\WebAttackData\\"+method+"\\" + name;
		filePath = new File(path);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		File[] fileList=new File(path).listFiles();
		int fileNum=fileList.length;
		fileNum=fileNum+1;
		String newpath=path + "\\" + name + "_"+fileNum+".txt";
		File files = new File(newpath);	
		if (!files.exists()) {
			files.createNewFile();
		}
		FileWriter writer = new FileWriter(newpath, true);// ׷��
		writer.write(s);
		writer.close();

		return newpath ;
	}

	public int getFile(String filepath) {
		int filecount=0;
		//com.bizwink.cms.util.Convert con = new com.bizwink.cms.util.Convert();  
		File file = new File(filepath); 
		if (!file.exists()) {
			file.mkdirs();
		}
		File[] listfile = file.listFiles();  
		filecount=listfile.length;


		return filecount;
	}

	public int RobotDetection(String modetype,String file) throws IOException{
		int outflag=0;
		//获取攻击数据
		ArrayList<ArrayList<Float>> KeyFeaBot=feaGet.KeyDataGetFinal_File(modetype, file);
		ArrayList<ArrayList<Float>> MouseFeaBot = MousefeaGet.BotSectionData(modetype, file);
		//System.out.println(MouseFeaBot);
		float testScore_mouse=1000;
		ArrayList<ArrayList<Float>>MouseLegalTrain=Serialization.AntiModelSerialization("MouseLegalTrain","mouse",modetype);
		if((!MouseFeaBot.isEmpty())&&MouseLegalTrain.size()>=15){
			ArrayList<ArrayList<Float>> IllegalTestData_mouse=MouseFeaBot;

			//获取鼠标归一化参数
			ArrayList<Float> Numerator_mouse=Serialization.AntiNormalDataSerialization("Numerator","mouse",modetype);
			ArrayList<Float> Denominator_mouse=Serialization.AntiNormalDataSerialization("Denominator","mouse",modetype);
			ArrayList<ArrayList<Float>> NormalizationData_mouse=new ArrayList<ArrayList<Float>>();
			NormalizationData_mouse.add(Numerator_mouse);
			NormalizationData_mouse.add(Denominator_mouse);
			ArrayList<ArrayList<Float>> MouseIllegalTest=DataProc.FeaNormalization(NormalizationData_mouse, IllegalTestData_mouse);	
			//				ArrayList<ArrayList<Float>>MouseLegalTrain=Serialization.AntiModelSerialization("MouseLegalTrain","mouse");
			ArrayList<Float> Mouse_ScoreIllegal =DataProc.ScoreGet_KNN_Test (MouseIllegalTest,MouseLegalTrain,15 );
			testScore_mouse=Float.valueOf(Mouse_ScoreIllegal.get(0));
		}
		//获取攻击数据集
		ArrayList<ArrayList<Float>> IllegalTestData_key=KeyFeaBot;

		//获取击键归一化参数
		ArrayList<Float> Numerator_key=Serialization.AntiNormalDataSerialization("Numerator","key",modetype);
		ArrayList<Float> Denominator_key=Serialization.AntiNormalDataSerialization("Denominator","key",modetype);
		ArrayList<ArrayList<Float>> NormalizationData_key=new ArrayList<ArrayList<Float>>();
		NormalizationData_key.add(Numerator_key);
		NormalizationData_key.add(Denominator_key);
		ArrayList<ArrayList<Float>> KeyIllegalTest=DataProc.FeaNormalization(NormalizationData_key, IllegalTestData_key);


		//获取训练集

		ArrayList<ArrayList<Float>>KeyLegalTrain=Serialization.AntiModelSerialization("KeyLegalTrain","key",modetype);
		//获取得分
		ArrayList<Float> Key_ScoreIllegal =DataProc.ScoreGet_KNN_Test (KeyIllegalTest,KeyLegalTrain ,15);



		//鼠标输出

		System.out.println("======================================");
		if(testScore_mouse!=1000)System.out.println("testScore_Mouse: "+testScore_mouse);
		else System.out.println("攻击行为不服符合鼠标行为提取或不存在鼠标行为！");
		float DistThreshold_mouse=Serialization.AntidistThresholdSerialization("mouse","login");
		System.out.println("DistThreshold_Mouse: "+DistThreshold_mouse);
		System.out.println("--------------------------------------");
		//键盘输出
		float testScore_key=Float.valueOf(Key_ScoreIllegal.get(0));
		System.out.println("testScore_Key: "+testScore_key);
		float DistThreshold_key=Serialization.AntidistThresholdSerialization("key","login");
		System.out.println("DistThreshold_Key: "+DistThreshold_key);
		float Adj_DistThreshold=(float) (DistThreshold_key*1.4);
		System.out.println("Adj_DistThreshold_Key "+Adj_DistThreshold);
		System.out.println("======================================");


		if(testScore_key>Adj_DistThreshold){
			outflag=1;
		}else{
			ReplayDetection mReDetection = new ReplayDetection("key",modetype,KeyFeaBot);
			if(mReDetection.IsReplayAttack()){
				System.out.println("*****键盘特征判定：回放攻击*****");
				outflag = 1;
			}else{
				System.out.println("*****键盘特征判定：正常操作*****");
				outflag = 0;
			}
			if(testScore_mouse!=1000){
				mReDetection = new ReplayDetection("mouse",modetype,MouseFeaBot);
				if(mReDetection.IsReplayAttack()){
					System.out.println("*****鼠标特征判定：回放攻击*****");
					outflag = 1;
				}else{
					System.out.println("*****鼠标特征判定：正常操作*****");
					outflag = 0;
				}
			}
		}


		return outflag;
	} 

}
