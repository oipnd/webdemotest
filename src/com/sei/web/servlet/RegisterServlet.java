package com.sei.web.servlet;

import java.io.BufferedReader;
import java.io.File;
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

import com.sei.web.servlet.ReaderDBFile;
import com.sei.web.servlet.User;
import com.sei.web.servlet.WriterDBFile;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		register(request,response);
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
	public int process_str(String str) {
		 int pred=0;
		 int m=0,n=0;
		 for (int i=0;i<str.length();i++) {
			 if(str.charAt(i)=='1') {
				 m=m+1;
			 }
			 if(str.charAt(i)=='0') {
				 n=n+1;
			 }
		 }
		 float ratio=m/(float)(m+n);
		 if(ratio>=0.5) {
			 pred=1;
		 }
		return pred;
	}
	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		if (username == ""){
			message = "用户名为空，注册失败";
			request.setAttribute("message", message);
			if (method.equals("registerFirst")) {
				request.getRequestDispatcher("/registerFirst.jsp").forward(request, response);
			} else if (method.equals("registerAfter")) {
				request.getRequestDispatcher("/registerCollect.jsp").forward(request, response);
			} 
		}
		
		else if(password1==""||password2=="") {
			message = "密码为空，注册失败！";
			request.setAttribute("message", message);
			if (method.equals("registerFirst")) {
				request.getRequestDispatcher("/registerFirst.jsp").forward(request, response);
			} else if (method.equals("registerAfter")) {
				request.getRequestDispatcher("/registerCollect.jsp").forward(request, response);
			} 
		}
		else if(!password1.equals(password2)) {
			message = "两次密码输入不同，请再次输入！";
			request.setAttribute("message", message);
			if (method.equals("registerFirst")) {
				request.getRequestDispatcher("/registerCollect.jsp").forward(request, response);
			} else if (method.equals("registerAfter")) {
				request.getRequestDispatcher("/registerCollect.jsp").forward(request, response);
			} 
		}
		else if (u.getUsername() == username){
			message = "用户名已存在，注册失败！";
			System.out.println("u.getUsername()="+u.getUsername());
			request.setAttribute("message", message);
			if (method.equals("registerFirst")) {
				request.getRequestDispatcher("/registerCollect.jsp").forward(request, response);
			} else if (method.equals("registerAfter")) {
				request.getRequestDispatcher("/registerCollect.jsp").forward(request, response);
			} 
		}

		else{
			
			if(paramCode==null) {
				request.setAttribute("message", "请填写验证码！");
				if (method.equals("registerFirst")) {
					request.getRequestDispatcher("/registerCollect.jsp").forward(request, response);
				} else if (method.equals("registerAfter")) {
					request.getRequestDispatcher("/registerCollect.jsp").forward(request, response);
				} 
				return;
			}
			
			if(!paramCode.equalsIgnoreCase(sessionCode)) {
				request.setAttribute("message", "验证码错误！");
				if (method.equals("registerFirst")) {
					request.getRequestDispatcher("/registerCollect.jsp").forward(request, response);
				} else if (method.equals("registerAfter")) {
					request.getRequestDispatcher("/registerCollect.jsp").forward(request, response);
				} 
				return;
			}
			
			
		WriterDBFile wdf = new WriterDBFile();
		wdf.WriterFileUse(username + "\t\t" + password1 + "\t\t"+count+"\r\n");
		
		String filename=DataToFile(data,username,"register","screen");
//		String str=sendPost("http://192.168.100.5:5000/predict", "image="+filename);
//		int pred=process_str(str);
		 String path="/data/weiang_data/mousetrack/huawei/WebTestData/register/" ;
		 int filenum=getFile(path);
		 String num= Integer.toString(filenum);
		request.setAttribute("textnum", num); 
		
	    request.getRequestDispatcher("/registerCollect.jsp").forward(request, response);
		} 

//     
		}
		


	public String DataToFile(String s,String name,String method,String type) throws IOException{
		String path=null;
		File filePath;
		
		path = "/data/weiang_data/mousetrack/huawei/WebTestData/register/" + name;
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
