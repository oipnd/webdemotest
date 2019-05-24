package com.sei.web.servlet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
			loginTests(request, response);}	
	}

	private void loginTests(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String data=request.getParameter("StrData");
		
		String count=null;
		
		//�����֤�����
		String sessionCode=(String) request.getSession().getAttribute("session_vcode");
		//System.out.println(sessionCode);
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
			
			if(!paramCode.equalsIgnoreCase(sessionCode)) {
				request.setAttribute("message", "验证码错误！");
				request.getRequestDispatcher("/loginCollect.jsp").forward(request, response);
				return;
			}

			 DataToFile(data,username,"login","screen");
			 String path="E:\\WebTestData\\login\\" ;
			 int filenum=getFile(path);
			 String num= Integer.toString(filenum);
			request.setAttribute("textnum", num); 
			request.getRequestDispatcher("/loginCollect.jsp").forward(request,
					response);
		}  else {

			request.setAttribute("message", "用户名或密码不正确！");
			request.getRequestDispatcher("/loginCollect.jsp").forward(request,
					response);
		}
		
	}
	
	
	public void DataToFile(String s,String name,String method,String type) throws IOException{
		String path=null;
		File filePath;
		
		path = "E:\\WebTestData\\login\\" + name;
			filePath = new File(path);
			if (!filePath.exists()) {
				filePath.mkdirs();
			}
			File[] fileList=new File(path).listFiles();
			int fileNum=fileList.length;
			fileNum=fileNum+1;
			File files = new File( path + "\\" + name + "_"+fileNum+".txt");	
			if (!files.exists()) {
	   			  files.createNewFile();
	   		  }
			FileWriter writer = new FileWriter(path + "\\" + name + "_"+fileNum+".txt", true);// ׷��
			writer.write(s);
			writer.close();
			
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
