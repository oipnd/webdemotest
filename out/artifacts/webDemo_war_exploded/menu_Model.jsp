<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html >
  <head>
    <base href="<%=basePath%>">
    
    <title>menu</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
   <link rel="stylesheet" href="/WebDemoTest/CSS/lrtk.css">
   <link rel="stylesheet" type="text/css" href="/WebDemoTest/CSS/sweet-alert.css">
	<script type="text/javascript" src="/WebDemoTest/js/yezi.js"></script>
	<script src="/WebDemoTest/js/sweet-alert.min.js"></script> 
	<style>
	body{  
    overflow-x: hidden;  
         overflow-y: hidden;   
    } 
	</style>

  </head>
  
  
  
  
  <body >
   <div id="botAgregar"></div>
	

     <br>
     <br>
     <center><p style="font-family: '微软雅黑';-webkit-text-stroke: 1px #FFF68F;font-weight:bold;font-size:70px;color:#ffcc33">数据采集目录</p></center>
<!--      <center><font color=red size=8>WEB数据采集测试</font></center> -->
    <br>
    <br>
    <br>
    <br>
    <br>
    <center><a style="text-decoration:none" title="点我生成注册模块模型" href=/WebDemoTest/moduleServlet?WebType=userRegister >
	<p style="font-family: '微软雅黑';font-weight:bold;font-size:50px;color:#445cbb">生成注册模型</p>
	</a></center>
	<br>
	<center><a style="text-decoration:none" title="点我生成登录模块模型" href=/WebDemoTest/moduleServlet?WebType=userLogin >
	<p style="font-family: '微软雅黑';font-weight:bold;font-size:50px;color:#20B2AA">生成登录模型</p>
	</a></center>
	<br>

	<center><a style="text-decoration:none"  title="点我生成改密模块模型" href=/WebDemoTest/moduleServlet?WebType=userChangePwd >
	<p style="font-family: '微软雅黑';font-weight:bold;font-size:50px;color:#B03060">生成改密模型 </p>
	</a></center>
	<br>
	<center><a style="text-decoration:none"  title="点我生成秒杀模块模型" href=/WebDemoTest/moduleServlet?WebType=userMiaosha >
	<p style="font-family: '微软雅黑';font-weight:bold;font-size:50px;color:#ff6666">生成秒杀模型</p>
	</a></center>
	<br>
	<center><a style="text-decoration:none"  title="点我返回" href=/WebDemoTest/moduleServlet?WebType=outCollect>
	<p style="font-family: '微软雅黑';font-weight:bold;font-size:50px;color:#0ca3d2">返回</p>
	</a></center>

  </body>


</html>
