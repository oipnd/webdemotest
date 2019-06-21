<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

   <style>
		body {
		  font: 13px/20px 'Lucida Grande', Tahoma, Verdana, sans-serif;
		  color: #404040;
		  background: #F0FFF0;
		}
		.button {
   width: 200px; 
    height: 60px;
        line-height:30px;
  font-family: Georgia;
  color: #ffffff;
  font-size: 28px;
    font-family: '微软雅黑';
   padding: 10px; 
  text-decoration: none;
  -webkit-border-radius: 38px;
  -moz-border-radius: 38px;
  border-radius: 38px;
  -webkit-box-shadow: 0px 1px 3px #666666;
  -moz-box-shadow: 0px 1px 3px #666666;
  box-shadow: 0px 1px 3px #666666;
  text-shadow: 1px 1px 3px #666666;
  border: solid #fafafa 1px;
  background: -webkit-gradient(linear, 0 0, 0 100%, from(#6495ED), to(#6495ED));
  background: -moz-linear-gradient(top, #6495ED, #6495ED);


}
.button:hover {
  background: #7EC0EE;
}
	</style>
</head>
<body>
<br>
<br>
<br>
<center><p style="font-family: '微软雅黑';font-weight:bold;font-size:75px;color:#436EEE">欢迎使用WEB机器人检测系统</p></center>

<br>
<br>
<br>

    <%      
            String warning = (String)request.getAttribute("textnum");
			String message = (String) request.getAttribute("message");
			if (message != null) {
				out.print("<center><p style=\"font-family: '微软雅黑';font-weight:bold;font-size:60px;color:#CD5555\"> " + message
						+ "</p></center>");
			}else{
				out.print("<center><p style=\"font-family: '微软雅黑';font-weight:bold;font-size:60px;color:#436EEE#7A7A7A\">您还没有生成模型</p></center>");
			}
				
		%>
  <br>
  <br>
   <br>
     <br>
  <br>
		<center><a href="/WebDemoTest/menu_Model.jsp"><input class="button" type="submit"  id="Submit" name="Submit" value="返回目录"></a></center>
</body>

</html>