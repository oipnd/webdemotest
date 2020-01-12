package com.sei.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Servlet implementation class menuCollectServlet
 */
@WebServlet("/menuCollectServlet")
public class menuCollectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String WebType=request.getParameter("WebType");
//		System.out.println(WebType);
		
		if(WebType.equals("userLogin")){			
        	response.sendRedirect("/WebDemoTest/loginCollect.jsp");
        }else if(WebType.equals("userRegister")){			
        	response.sendRedirect("/WebDemoTest/registerCollect.jsp");
        }else if(WebType.equals("userChangePwd")){        	
        	response.sendRedirect("/WebDemoTest/changePwdCollect.jsp");      	
        }else if(WebType.equals("userMiaosha")){
        	response.sendRedirect("/WebDemoTest/miaoshaCollect.jsp");    
        }else if(WebType.equals("outCollect")){  	
          response.sendRedirect("/WebDemoTest/menu_Main.jsp");
		}else if (WebType.equals("wenjuan")){
			response.sendRedirect("/WebDemoTest/wenjuan.jsp");
		} else if (WebType.equals("xinli")) {
			response.sendRedirect("/WebDemoTest/xinli/xinli.html");
		}
            	

		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		}
}
