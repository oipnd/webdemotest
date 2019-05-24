package com.sei.web.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Stack;

/**
 * Servlet implementation class CollectServlet
 */
@WebServlet("/CollectServlet")
public class CollectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CollectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = request.getParameter("method");
		//System.out.println("method=" + method);
		if (method == null) {
			method = "";
		}
		if (method.equals("loginCollect")) {
			loginTests(request, response);
		} else if (method.equals("changePwdCollect")) {
			changePwdTest(request, response);
		}else if (method.equals("miaoshaCollect")) {
			miaoshaTest(request, response);
		}else if (method.equals("trackCollect")) {
			trackCollect(request, response);
		}
		
	}
	public String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
	public Stack<Integer> process_str(String str) {
		Stack<Integer> arry=new Stack<Integer>();
		 int pred=0;
		 int m=0,n=0;
		 for (int i=0;i<str.length();i++) {
			 if(str.charAt(i)=='1') {
				 m=m+1;
				 arry.push(1);
			 }
			 if(str.charAt(i)=='0') {
				 n=n+1;
				 arry.push(0);
			 }
		 }
		 if(n==0&&m==0) {
			 pred=2;
		 }
		 else{
			 float ratio=m/(float)(m+n);
			 if(ratio>=0.5)
			 pred=1;
		 }
		 arry.push(m+n);
		 arry.push(pred);
		return arry;
	}
	private void miaoshaTest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");

		String data=request.getParameter("StrData");
		//String data1=request.getParameter("StrData2");
/*		System.out.println(data);*/
		//String count=null;		

			 DataToFile(data,"crane_sei","miaosha","screen");
			// DataToFile(data1,registerName,"miaosha","client");
			 String path="/data/weiang_data/mousetrack/huawei/WebTestData/miaosha/" ;
			 int filenum=getFile(path);
			 String num= Integer.toString(filenum);
			request.setAttribute("textnum", num); 
			request.getRequestDispatcher("/miaoshaCollect.jsp").forward(request,response);
			//request.getRequestDispatcher("/menu_Collect.jsp").forward(request,response);
		 
	}

	private void changePwdTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	
		System.out.println("username="+username);
		System.out.println("oldpassword="+oldpassword);
		System.out.println("newpassword1="+newpassword1);
		System.out.println("newpassword2="+newpassword2);
		
		
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
			request.getRequestDispatcher("/changePwdCollect.jsp").forward(request, response);
			return;
		}
		if (oldpassword==null||oldpassword.length()==0){
			message = "原密码为空，更改失败！";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/changePwdCollect.jsp").forward(request, response);
			return;
		}
		if (newpassword1==""||newpassword2==""){
			message = "新密码输入有误，请重新输入！";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/changePwdCollect.jsp").forward(request, response);
			return;
		}

		else if(!newpassword1.equals(newpassword2)) {
			message = "两次密码输入不同，请重新填写！";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/changePwdCollect.jsp").forward(request, response);
			return;
		}
		u = (User) rdf.ReaderFileUse(username, oldpassword,count);
	   if (u.getUsername() == username&&u.getPassword()==oldpassword){
		   
			if(paramCode==null) {
				request.setAttribute("message", "请填写验证码！");
				request.getRequestDispatcher("/changePwdCollect.jsp").forward(request, response);
				return;
			}
			
			if(!paramCode.equalsIgnoreCase(sessionCode)) {
				request.setAttribute("message", "验证码错误！");
				request.getRequestDispatcher("/changePwdCollect.jsp").forward(request, response);
				return;
			}
		   
			br = new BufferedReader(new FileReader("/data/weiang_data/mousetrack/huawei/userID/dbfile.txt"));
			             
	       
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

			 String filename=DataToFile(data,username,"changePwd","screen");
//			 String str=sendPost("http://192.168.100.5:5000/predict", "image="+filename);
//			 Stack<Integer> stack=new Stack<Integer>();
//			 stack=process_str(str);
			 //DataToFile(data1,username,"changePwd","client");
			 String path="/data/weiang_data/mousetrack/huawei/WebTestData/changePwd/" ;
			 int filenum=getFile(path);
			 String num= Integer.toString(filenum);
			request.setAttribute("textnum", num); 		
		    request.getRequestDispatcher("/changePwdCollect.jsp").forward(request, response);			
		}else{
			request.setAttribute("message", "用户名或密码不正确！");		
			request.getRequestDispatcher("/changePwdCollect.jsp").forward(request,response);
		}
	}


	private void loginTests(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String data=request.getParameter("StrData");
		
		String count=null;
		
		//�����֤�����
		String sessionCode=(String) request.getSession().getAttribute("session_vcode");
		System.out.println(sessionCode);
		String paramCode = request.getParameter("verifyCode");
		
		
		User u = null;
		ReaderDBFile rdf = new ReaderDBFile();
		u = (User) rdf.ReaderFileUse(username, password,count);
	
		
		if (u.getUsername() == username&&u.getPassword()==password) {
			if(paramCode==null) {
				request.setAttribute("message", "请填写验证码！");
				request.getRequestDispatcher("/loginCollect.jsp").forward(request, response);
				return;
			}
			
			/*if(!paramCode.equalsIgnoreCase(sessionCode)) {
				request.setAttribute("message", "验证码错误！");
				request.getRequestDispatcher("/loginCollect.jsp").forward(request, response);
				return;
			}*/                                  

			 String filename=DataToFile(data,username,"login","screen");
			 String str=sendPost("http://192.168.100.5:5000/predict", "image="+filename);
			 String img_dir1="images/"+filename.split("\\/")[7]+"_image"+"/"+filename.split("\\/")[8].replaceAll(".txt", "");
			 Stack<Integer> stack=new Stack<Integer>();
			 stack=process_str(str);
			 String path="/data/weiang_data/mousetrack/huawei/WebTestData/login/" ;
			 int filenum=getFile(path);
			 String num= Integer.toString(filenum);
			request.setAttribute("textnum", num); 
			int pred=stack.pop();
			int all=stack.pop();
			String message="";
			while(! stack.isEmpty()) {
				int temp=stack.pop();
				message=temp+" "+message;
			}
//			String predict=sendPost("http://192.168.100.5:5000/predict", "image=/data/zcr_data/WebTestData/login/dingjie/dingjie_1.txt");
//			System.out.println(predict);
//			request.getRequestDispatcher("/loginCollect.jsp").forward(request,
//					response);
//			System.out.println("isHuman");
			if(pred==1) {
				
				request.setAttribute("message", pred+" "+all+" "+message);
				request.setAttribute("img_dir", img_dir1);
				request.getRequestDispatcher("/isHuman.jsp").forward(request,
						response);
			}
			else if(pred==0){
				request.setAttribute("message", pred+" "+all+" "+message);
				request.setAttribute("message1", "您的行为可疑");
				request.setAttribute("img_dir", img_dir1);
				request.getRequestDispatcher("/isRobot.jsp").forward(request,
						response);;
			}
			else {
				request.setAttribute("message", pred+" "+all+" "+message);
				request.setAttribute("message1", "没有采集到足够轨迹数据");
				request.setAttribute("img_dir", img_dir1);
				request.getRequestDispatcher("/isRobot.jsp").forward(request,
						response);
			}
		}  else {

			request.setAttribute("message", "用户名或密码不正确！");
			request.getRequestDispatcher("/loginCollect.jsp").forward(request,
					response);
		}
		
	}
	
	private void trackCollect(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String data=request.getParameter("StrData");
		
		String count=null;
		
		//�����֤�����
		String sessionCode=(String) request.getSession().getAttribute("session_vcode");
		System.out.println(sessionCode);
	    DataToFile(data,username,"trackCollect","screen");
//		String path="/data/WebTestData/login/" ;
		request.getRequestDispatcher("/track_collect.jsp").forward(request,
					response);
	}
	public String DataToFile(String s,String name,String method,String type) throws IOException{
		String path=null;
		File filePath;
		
		path = "/data/weiang_data/mousetrack/huawei/WebTestData/"+method+"/" + name;
			filePath = new File(path);
			if (!filePath.exists()) {
				filePath.mkdirs();
			}
			File[] fileList=new File(path).listFiles();
			int fileNum=fileList.length;
			fileNum=fileNum+1;
			File files = new File( path + "/" + name + "_"+fileNum+".txt");	
			if (!files.exists()) {
	   			  files.createNewFile();
	   		  }
			FileWriter writer = new FileWriter(path + "/" + name + "_"+fileNum+".txt", true);// ׷��
			writer.write(s);
			writer.close();
			return path + "/" + name + "_"+fileNum+".txt";
			
//		return fileNum;
	}
	
	 public int getFile(String filepath) {
		     int filecount=0;
	        //com.bizwink.cms.util.Convert con = new com.bizwink.cms.util.Convert();  
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
