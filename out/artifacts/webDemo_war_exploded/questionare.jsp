<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/WebDemoTest/CSS/miao.css" charset="utf-8">
<link rel="stylesheet" type="text/css" href="/WebDemoTest/CSS/sweet-alert.css"><br>
<title>miaosha</title>
	<style>
	body{  
    overflow-x: hidden;  
    overflow-y: hidden;  
} 
	</style>
<script src="<%=request.getContextPath() %>/js/fingerprint.js"></script>	
</head>
<body onload="alertNum();browerInfo()" onkeydown="keyDown()" onkeyup="keyUp()"  onmousedown="mousedown_sendXML()" onmousemove="mousemove_sendXML()" onmouseup="mouseup_sendXML()">
    <form id="form1" name="form1" method="post" action="/WebDemoTest/CollectServlet">
        <input type="hidden" name="method" value="miaoshaCollect" />
        <input type="hidden" name="StrData" id="StrData"> 
        <input type="hidden"   name="StrData2" id="StrData2">  
    </form>


    <div class="mp-content">
    <ul class="itemlist border-list" id="anti-encrypt" data-click="{act:'a',  mod:'mainItem'}">
    <li class="mp-single-item" data-id="2839496677" data-click="{pn:'1'}" style="bottom: 0px; z-index: 1;">
      <div class="item-info">
        <img data-src="/WebDemoTest/images/tata.jpg" class="item-logo" src="/WebDemoTest/images/tata.jpg" style="display: inline;">    
        <h4 class="item-discount">秒杀价<em>3.8元</em>&nbsp; &nbsp; &nbsp;&nbsp;原价：98元</h4>
        <h4 class="item-stitle">交大纪念品折扣1-5折 京东配货</h4>
        <p class="item-time" id="item-time">离开始: 0小时0分15秒</p>
        <p class="item-source"><span class="item-source-inner">上品折扣</span>    </p> 
        <span id="item-his-safe" class="item-his-safe" style="display:none;"></span>
    </div> 
    <div class="item-pic">
        <iframe height="1200" width="800" src="https://wj.qq.com/s2/2885356/2b08/" frameborder="0" allowfullscreen></iframe>
      <img class="load-bear" src="/WebDemoTest/images/miao.jpg" style="display: inline;"> 
    </div>
    <div class="linkhover favorite-icon brand-collected favorite-16471559275081143995" data-id="16471559275081143995" data-type="index">
      <span class="empty-heart-icon"></span>关注
    </div>
    <a href="####"  class="mp-item-link" id="mp-item-link" title="交大纪念品折扣1-5折 京10店配货" data-click="{time:1476015447, item:'2839496677'}" ></a></li>
  </ul>
</div>

<%String warning = (String)request.getAttribute("textnum"); %>>
<script src="/WebDemoTest/js/sweet-alert.min.js"></script> 
<script>
 document.write("<s"+"cript type='text/javascript' src='/WebDemoTest/js/miao.js?"+Math.random()+"'></scr"+"ipt>");
 
 function alertNum(){
	  var warning="<%= warning%>";
	  if(warning!="null"&&warning!="0"){		 
		  swal({   title: "采集成功", 
				  text: "目前已有"+warning+"个样本", 
				  timer: 5000,
				  type: "success",   showCancelButton: true, 
				  confirmButtonColor: "#DD6B55", 
				  confirmButtonText: "返回菜单", 
				  cancelButtonText: "继续采集" }, 
				  function(){  
					  window.location.href="/WebDemoTest/menu_Collect.jsp";	});
		  }
     }

 var StringData="";
 var strCilent="";
 
function browerInfo(){
	  var x=navigator;
	  
	  
	  StringData+="CodeName:"+x.appCodeName+"\n";//浏览器的代码名
	  StringData+="MinorVersion:"+x.appMinorVersion+"\n";//浏览器的次级版本
	  StringData+="AppName:"+x.appName+"\n";//浏览器的名称
	  StringData+="appVersion:"+x.appVersion+"\n";//浏览器的平台和版本信息
	  StringData+="javaEnabled:"+x.javaEnabled+"\n";//表示当前浏览器中是否启用了java
	  StringData+="vendor:"+x.vendor+"\n";//浏览器的品牌
	  StringData+="UA:"+x.userAgent+"\n";//客户机发送服务器的 user-agent 头部的值
	  StringData+="Platform:" + x.platform+"\n";//运行浏览器的操作系统平台。
	  StringData+="OnLine:" + x.onLine+"\n"; //指明系统是否处于脱机模式的布尔值
	  StringData+="PluginNum:" + x.plugins.length+"\n";//浏览器安装的插件数
	  StringData+="Language:" + x.language+"\n";//OS 的自然语言设置
	  StringData+="mimeTypes:" + x.mimeTypes+"\n";// 浏览器支持的所有MIME类型的数组
	  StringData+="Product:" + x.product+"\n";//浏览器产品名称
	  StringData+="ProductSub:" + x.productSub+"\n";//浏览器产品其他信息
	  StringData+="UsingCookie:"+x.cookieEnabled+"\n";//浏览器是否开启cookie
	  StringData+="Screen resolution:"+window.screen.width + "*" + window.screen.height+"\n";//屏幕分辨率
	  StringData+="ColorDepth:"+window.screen.colorDepth+"\n";//颜色质量
	  StringData+="DeviceXDPI:"+window.screen.deviceXDPI+"\n";//像素
    
	  function checkePlugs(pluginname) {
        var f = "-"
        var plugins = navigator.plugins;
        if (plugins.length > 0) {
            for (i = 0; i < navigator.plugins.length; i++) {
                if (navigator.plugins[i].name.indexOf(pluginname) >= 0) {
                    f = navigator.plugins[i].description.split(pluginname)[1];
                    return f;
                    break;
                }
            }
        }
        return false;
    }
	  
	  StringData+="Flash Plugin:"+checkePlugs('Shockwave Flash')+"\n";//flash插件情况

	  var referer;  
	    if (document.referrer.length > 0) {  
	        referer = document.referrer;  
	    }  
	    try {  
	        if (referer.length == 0 && opener.location.href.length > 0) {  
	            referer = opener.location.href;  
	        }  
	    } catch (e) {  
	        referer = window.location.href;  
	    }  
	  
	    referer = referer.replace('http://', '');
	    StringData+="refer:"+referer+"\n";
	    
	    
	    var fp1 = new Fingerprint();
	    var fp2 = new Fingerprint({canvas: true});
	    var fp3 = new Fingerprint({ie_activex: true});
	    var fp4 = new Fingerprint({screen_resolution: true});
	    StringData+="\n";
	    StringData+="browser fingerprint with default settings (canvas, screen resolutin, activeX disabled:   "+ fp1.get()+"\n";   
	    StringData+="browser fingerprint with canvas enabled:   "+fp2.get()+"\n";
	    StringData+="browser fingerprint with ie_activex enabled:   "+fp3.get()+"\n";
	    StringData+="browser fingerprint with screen_resolution enabled:   "+fp4.get()+"\n";
	    
	    StringData+="\n";
	    strCilent=StringData;
	    
}


//鼠标移动数据获取
function mousemove_sendXML(){

	  var d = (new Date()).valueOf();
		 
	  var e =  window.event || arguments.callee.caller.arguments[0];
 
	   var x = e.screenX ;
     var y = e.screenY ;
		
	 StringData=StringData+"miaosha"+" "+"mousemove"+" "+x+" "+y+" "+d+"\n";
	 document.getElementById("StrData").value = StringData;
		
     var clientX=e.clientX;
     var clientY=e.clientY;	 
	 strCilent=strCilent+"miaosha"+" "+"mousemove"+" "+clientX+" "+clientY+" "+d+"\n";
	 document.getElementById("StrData2").value = strCilent;	
	  
}

//鼠标点击数据获取
//down
function mousedown_sendXML(){

	  var d = (new Date()).valueOf();
	  var e = window.event || arguments.callee.caller.arguments[0];
	 
	  var x = e.screenX ;
    var y = e.screenY ;
	  
    StringData=StringData+"miaosha"+" "+"mousedown"+" "+x+" "+y+" "+d+"\n";
    document.getElementById("StrData").value = StringData;

    var clientX=e.clientX;
    var clientY=e.clientY;	 
	strCilent=strCilent+"miaosha"+" "+"mousedown"+" "+clientX+" "+clientY+" "+d+"\n";
	document.getElementById("StrData2").value = strCilent;
	  
}


//鼠标点击数据获取
//up
  function mouseup_sendXML(){

	  var d = (new Date()).valueOf();

	  var e =  window.event || arguments.callee.caller.arguments[0];
    
	  
	  var x = e.screenX ;
    var y = e.screenY ;
    StringData=StringData+"miaosha"+" "+"mouseup"+" "+x+" "+y+" "+d+"\n"; 
    document.getElementById("StrData").value = StringData;

    var clientX=e.clientX;
    var clientY=e.clientY;	 
    strCilent=strCilent+"miaosha"+" "+"mouseup"+" "+clientX+" "+clientY+" "+d+"\n";
    document.getElementById("StrData2").value = strCilent;		
		
	  
}
	 
	//击键数据获取
	 function keyDown(){
		    
        var e = window.event || arguments.callee.caller.arguments[0];
          
        var d = (new Date()).valueOf();
    		
    		StringData=StringData+"miaosha"+" "+"keydown"+" "+e.keyCode+" "+d+"\n"; 
    		document.getElementById("StrData").value = StringData;
    		
        	  strCilent=strCilent+"miaosha"+" "+"keydown"+" "+e.keyCode+" "+d+"\n";
           	 document.getElementById("StrData2").value = strCilent;

             }
	
	
	 function keyUp(){
		    
       var e = window.event || arguments.callee.caller.arguments[0];
       
       var d = (new Date()).valueOf();
        StringData=StringData+"miaosha"+" "+"keyUp"+" "+e.keyCode+" "+d+"\n";
        document.getElementById("StrData").value = StringData;

        strCilent=strCilent+"miaosha"+" "+"keyup"+" "+e.keyCode+" "+d+"\n";
        document.getElementById("StrData2").value = strCilent;  

       }
</script>
	  </body>
</html>