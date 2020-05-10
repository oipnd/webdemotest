<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>

    <base href="<%=basePath%>">
    
    <title>login</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link rel="stylesheet"	type="text/css" href="<%=request.getContextPath() %>/CSS/Collect.css">
        <link rel="stylesheet" type="text/css" href="/WebDataTest3/CSS/sweet-alert.css"><br>
    <script src="/WebDataTest3/js/sweet-alert.min.js"></script> 
    <script src="<%=request.getContextPath() %>/js/fingerprint.js"></script>
   <style>
		body {
		  font: 13px/20px 'Lucida Grande', Tahoma, Verdana, sans-serif;
		  color: #404040;
		  background: #8968CD;
		}
	</style>
</head>

<body id="thisbody" onload="getCoordInDocumentExample()" onmousedown="mousedown_sendXML()" onmousemove="mousemove_sendXML()" onmouseup="mouseup_sendXML()" >
  <section class="container">
     <br>
     <br>
     <br>
    <div class="login">
      <h1 id="title_h1"><font id="title_font" size="6.8">欢迎进入登陆数据采集</font>
<!--           <font id="title_font" size="3">应用缓冲采集</font> -->
      </h1>
      
      <form id="form1" name="form1"  method="post" action="/WebDemoTest/CollectServlet">
        <input type="hidden" name="method" value="trackCollect" />
        <input type="hidden"   name="StrData" id="StrData"> 
        <p ><input type="text"  name="username" id="username" onpaste="return false" autocomplete="off" value="" placeholder="请输入相应用户名"></p>
        <p ><button type="button"  id="Submit2" name="Submit2" value="开始" style="width:40px;height:40px;margin-left:200px;margin-top:120px" onclick="start()"></p>
      </form>
    </div>  
 </section>

  </body>
  
  <script>
  var StringData="";
  var flag=false;
  var i=0
  function start(){
	  flag=true;
	  i=i+1
	  document.getElementById("Submit2").innerHTML=i+""
	  //document.getElem//entById("Submit2").disabled=true
	  //document.getElementById("Submit2").disabled=true;
    }
//鼠标移动数据获取
  function mousemove_sendXML(){

	  var d = (new Date()).valueOf();		 
	  var e =  window.event || arguments.callee.caller.arguments[0];  
	   var x = e.screenX ;
       var y = e.screenY ;
	if(flag==true){
		StringData=StringData+"login"+" "+"mousemove"+" "+x+" "+y+" "+d+"\n";
	}	
	 document.getElementById("StrData").value = StringData;

	  
  }
  
  //鼠标点击数据获取
  //down
  function mousedown_sendXML(){
	  var d = (new Date()).valueOf();
	  var e = window.event || arguments.callee.caller.arguments[0];
	 
	  var x = e.screenX ;
      var y = e.screenY ;
	  if(flag==true){
		  StringData=StringData+"login"+" "+"mousedown"+" "+x+" "+y+" "+d+"\n";
	  }
      document.getElementById("StrData").value = StringData;

  }
  
  //鼠标点击数据获取
  //up
    function mouseup_sendXML(){
	  flag=false;
	  var d = (new Date()).valueOf();
	  var e =  window.event || arguments.callee.caller.arguments[0];    	  
	  var x = e.screenX ;
      var y = e.screenY ;
      if(flag==true){
    	  StringData=StringData+"login"+" "+"mouseup"+" "+x+" "+y+" "+d+"\n"; 
      }
      document.getElementById("StrData").value = StringData;
     // if (i==5){
    	  //document.getElementById("Submit2").type="submit";
   //   }
     
  }
  
  var getCoordInDocumentExample = function(){
		  var datacollect = document.getElementById("thisbody");
		  thisbody.onmousemove = function(){
			  mousemove_sendXML();

	   }
	 }

	 function checkNum(){
		 
		 var passwordNum= document.getElementById("password").value;
		 if(passwordNum.length<6) {
		 
		 swal({    title: "密码位数设置有误",    text: "请改用六位以上密码再次操作", confirmButtonText: "我知道了" },
					function(){ window.location.reload()});  
	 }
	 }
  </script>
  
</html>