package com.sei.web.servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class JSONServlet
 */
@WebServlet("/JSONServlet")
public class JSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Random r = new Random();
	private String codes="23456789abcdefghijkmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVW";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JSONServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * 向客户端发送json串
		 */
		MD5 getMD5 = new MD5();
		String errno=String.valueOf(0);
/*		String ClientData = request.getReader().readLine();
		String[] tmp=ClientData.split(" ");
		String itemid=tmp[0];
		String uid=tmp[1];*/
		
		String itemid=String.valueOf(232);
		
		if(itemid==null){
			errno=String.valueOf(10001);
		}
		String token=getToken();
		String hashToken=getMD5.GetMD5Code(token);
		
		JSONObject obj=new JSONObject();
		obj.put("url","http://localhost:8080/WebDataTest/buy.jsp?v='"+token+"'");
		obj.put("token",hashToken);
		obj.put("itemid", itemid);
		
		JSONObject finalobj = new JSONObject();
		finalobj.put("errno", errno);
		finalobj.put("data", obj);
		
/*		String str = "{\"erron\":\""+errno+"\", \"age\":18, \"sex\":\"male\"}";*/
		response.getWriter().print(finalobj.toString());
//		System.out.println(str);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private char randomChar () {
		
		int index = r.nextInt(codes.length());
		return codes.charAt(index);
	}
	
   private String getToken(){
        
		StringBuilder sb = new StringBuilder();//
		for(int i=0;i<15;i++){
			String s = randomChar() + "";//随机生成一个字母 
			sb.append(s); //把字母添加到sb中			
		}
	 return sb.toString();
	}

}
