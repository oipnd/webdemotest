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
		  background: #000000;
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
 .login-help {
	float: left;
    width: 400px;
    margin-bottom: 20px;
    margin-left: 20px;
 }
	</style>
</head>
<body>
<center><p style="font-family: '微软雅黑';font-weight:bold;font-size:60px;color:#DC143C">系统判定您为人类</p></center>
<br>
<% 
String message = (String) request.getAttribute("message");
String img_dir=(String) request.getAttribute("img_dir");
			if (message != null && img_dir != null) {
				String[] str=message.split(" ");
				String all_tracks=str[1];
				out.print("<div><p style='font-size:22px;margin: auto;text-align: center;color: #FFFFFF;'> 共采到" + all_tracks
						+ "条有效轨迹</p></div>");
				for(int i=2;i<str.length;i++){
					int img_name=i-2;
					String img_path=img_dir+"/"+img_name+".jpg";
					out.print("<div class='login-help'><p style='font-size:22px;color: #FFFFFF;'> " + str[i]
							+ "</p>");
					out.print("<img src="+img_path+" style='height: 300px;width: 300px;'></div>");
				}
			}%>
<br>
<br>
<div style="/* top: 10px; */width: 25%;position: absolute;top: 45px;right: 0px;"><a href="/WebDemoTest/menu_Collect.jsp"><input class="button" type="submit"  id="Submit" name="Submit" value="返回目录"></a></div>
</body>
</html>