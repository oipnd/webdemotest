URL=" /WebDemoTest/JSONServlet"

	function createXMLHttpRequest() {
	try {
		return new XMLHttpRequest();//大多数浏览器
	} catch (e) {
		try {
			return ActvieXObject("Msxml2.XMLHTTP");//IE6.0
		} catch (e) {
			try {
				return ActvieXObject("Microsoft.XMLHTTP");//IE5.5及更早版本	
			} catch (e) {
				alert("no way in ajax!");
				throw e;
			}
		}
	}
}

//Ajax.request({
//                url:"ajax-07.txt",
//                params:{id:userid},
//                type:'json',
//                callback:process
//            });

//定时触发活动
var buyer = null;
//计算事件
var countTime = null;
//剩余时间事件
var leftTimeOut = null;

//var target=null;
var leftime=10;

var now1=0;
var judge=0;
function xx() {
	if(judge!=0){
	var nowtt=new Date().valueOf();
	var ff=nowtt-now1;

	if(ff>500){
		document.getElementById("item-time").innerHTML = '秒杀结束';
		document.getElementById("item-time").style.backgroundColor = '#999';
		
	}
	}
	
}
setInterval(xx,250);

	

function start()
{
	var iteminfo = document.getElementById('mp-item-link').getAttribute('data-click');
	var	itemobj = eval("("+ iteminfo + ")");
	var itemid = itemobj.item;
	var uid = 888;
	var xmlHttp = createXMLHttpRequest();
	//2. 连接
	xmlHttp.open("POST", URL, true);
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded"); 	
	xmlHttp.send(itemid+" "+uid);
	
	xmlHttp.onreadystatechange = function(){
		if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
	        var datatest = xmlHttp.responseText;
	        var obj = eval("(" + datatest + ")");	
		   if (obj.errno == 0) {
			 judge=1;
			document.getElementById("item-time").innerHTML = '立即秒杀';
			document.getElementById("item-time").style.backgroundColor = 'red';

//			document.getElementById("mp-item-link").href = obj.data.url;
//			document.getElementById("item-his-safe").innerHTML = obj.data.token;
			//js尝试性秒杀
		      now1 = new Date().valueOf();
			document.getElementById("mp-item-link").onclick=function(){
				var now2 = new Date().valueOf();
		
				var purse=now2-now1;
//				alert(purse);
				if(purse<320){
					strCilent+="\n"+"success in miaosha,time: "+purse+"ms \n";
					StringData+="\n"+"uccess in miaosha,time:"+purse+"ms \n";
					document.getElementById("StrData2").value = strCilent;	
					document.getElementById("StrData").value = StringData;
					swal({    title: "秒杀成功", 
						text: "你的反应时间是"+purse+" 毫秒！",  
						type: "success",  
						showCancelButton: false,   
						confirmButtonColor: "#DD6B55", 
						confirmButtonText: "我知道了", 
						closeOnConfirm: false,  
						closeOnCancel: false
						},					
					function(){  document.getElementById("form1").submit(); 
					});
						
				}	
				else{
					strCilent+="\n"+"failure in miaosha, time:"+purse+"ms \n";
					StringData+="\n"+"failure in miaosha, time:"+purse+"ms \n";
					document.getElementById("StrData2").value = strCilent;	
					document.getElementById("StrData").value = StringData;
					swal({    title: "秒杀失败", 
						text: "你的反应时间太慢了！",  
						type: "error",  
						showCancelButton: false,   
						confirmButtonColor: "#DD6B55", 
						confirmButtonText: "我知道了", 
						closeOnConfirm: false,  
						closeOnCancel: false
						},					
					function(){  document.getElementById("form1").submit(); 
					});
				}
				
			}
			clearInterval(buyer);
		   } 
	   }
	}
	buyer = setInterval(start, 1000);	
}	
	


//计算开始时间
function timer()
{
	var data = document.getElementById("mp-item-link").getAttribute('data-click');
	var obj = eval("(" + data + ")");
        target = obj.time;
	var now = parseInt(new Date().valueOf()/1000);
//	alert(now);

//	var leftime = target - now;
	 leftime = leftime-1;	
	
	if (leftime <= 0) {
		clearInterval(countTime);
	}
	if (null != leftTimeOut ) {
		clearTimeout(leftTimeOut);
	}
	var begin = leftime ;
	if (begin <= 0) {
		//5秒钟提前调用活动的start()函数 。
		start();
	} else {
		//抵消请求
		begin = begin * 1000;
		leftTimeOut = setTimeout(start, begin);
	}
	html = timeCount(leftime);
	document.getElementById('item-time').innerHTML = html;
}

function timeCount(maxtime)
{
	var html = '';
	if (maxtime <=0 ) {
		return html;
	}
	var day = Math.floor(maxtime/86400);
	leftime = Math.floor(maxtime%86400);
	var hour = Math.floor(leftime/3600);
	leftime = Math.floor(leftime%3600);
	var minute = Math.floor(leftime/60);
	var second = Math.floor(leftime%60);
	html = "离开始：";
	if (day > 0 ) {
		html += day + '天';
	}
	html += hour + '小时';
	html += minute + '分';
	html += second + '秒';
	return html;
}

countTime = setInterval(timer, 1000);

//var isClick = false;
//function buyItem()
//{
//	var byurl = document.getElementById('mp-item-link').getAttribute('href');
//	var data = document.getElementById('mp-item-link').getAttribute('data-click');
//	var obj = eval("("+data+")")
//	var itemid = obj.item;
//	var bytoken = document.getElementById('item-his-safe').innerHTML;
//	if (!isClick) {
//		isClick = true;
//
//		Ajax.request({
//			url:byurl,
//			params:{token:bytoken,id:itemid},
//			type:'json',
//			callback: buyCallBack
//		});
//	}
//}
//
//function buyCallBack(obj)
//{
//	if (obj.errno == 0) {
//	//	location.href= obj.data.url;
//	}
//}
