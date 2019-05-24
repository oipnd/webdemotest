package com.sei.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class menuServlet
 */
@WebServlet("/menuServlet")
public class menuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String WebType=request.getParameter("WebType");
//		System.out.println(WebType);

		
		if(WebType.equals("dataCollect")){			
        	response.sendRedirect("/WebDemoTest/Collect_menu.jsp");
        }else if(WebType.equals("userRegister")){
        	response.sendRedirect("/WebDemoTest/registerAfter.jsp");      	
        }else if(WebType.equals("moduleBuild")){        	
        	response.sendRedirect("/WebDemoTest/moduleServlet");      	
        }else if(WebType.equals("systemTest")){
        	response.sendRedirect("/WebDemoTest/systemTest.jsp");    
        }else if(WebType.equals("outSystem")){  	
          response.sendRedirect("/WebDemoTest/out.jsp");
        }
            	

		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		}

}
