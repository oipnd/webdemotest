/**
 * Powered by xjtu;
 * Copyright @zbcai;
 */

var enter = "\r\n";
var txt; //保存文件路径，代表时分秒
var UID = "uid"; //账号ID
var fpath; //文件夹路径

function inferBrowser() {//判断是否为移动端浏览器
    var browser = {
        versions: function () {
            var u = navigator.userAgent;
            return {//移动终端浏览器版本信息
                trident: u.indexOf('Trident') > -1, //IE内核
                presto: u.indexOf('Presto') > -1, //opera内核
                webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
                gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
                mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
                ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
                android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
                iPhone: u.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览器
                iPad: u.indexOf('iPad') > -1, //是否iPad
                webApp: u.indexOf('Safari') == -1, //是否web应该程序，没有头部与底部
                weixin: u.indexOf('MicroMessenger') > -1, //是否微信
                qq: u.match(/\sQQ/i) == " qq" //是否QQ
            };
        }(),
        language: (navigator.browserLanguage || navigator.language).toLowerCase()
    };

    if (browser.versions.mobile || browser.versions.ios || browser.versions.android ||
        browser.versions.iPhone || browser.versions.iPad) {
        return "mobile";
    } else {
        return "pc";
    }
}

function init() {//主函数
    var date = new Date();
    UID = document.getElementById("name").value;
    var folder = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();//年月日命名
    if (UID == null || UID == "" || UID == "undefined") { //游客模式
        fpath = "guest/" + new Fingerprint({canvas: true}).get() + "/" + folder;
    } else {
        fpath = UID + "/" + folder;
    }
    txt = date.getHours() + "-" + date.getMinutes() + "-" + date.getSeconds();

    var bType = inferBrowser();
    if (bType == "mobile") {
        browserInfo(bType);
        document.addEventListener('touchstart', touch, false);
        document.addEventListener('touchmove', touch, false);
        document.addEventListener('touchend', touch, false);
//	    document.onkeydown = keyDown;
    } else {
        browserInfo(bType);
        document.onkeydown = keyDown;
        document.onkeyup = keyUp;
        document.onmousedown = mouseDown;
        document.onmouseup = mouseUp;
        document.onmousemove = mouseMove;
        if (document.addEventListener) {//FireFox
            document.addEventListener('DOMMouseScroll', mouseWheel, false);
        }
        document.onmousewheel = mouseWheel;
    }
}

function browserInfo(bType) {//获取浏览器信息
    var x = navigator;
    var behInfo = "";
    behInfo += "CodeName:" + x.appCodeName + enter;//浏览器的代码名
    behInfo += "MinorVersion:" + x.appMinorVersion + enter;//浏览器的次级版本
    behInfo += "AppName:" + x.appName + enter;//浏览器的名称
    behInfo += "AppVersion:" + x.appVersion + enter;//浏览器的平台和版本信息
    behInfo += "JavaEnabled:" + x.javaEnabled() + enter;//表示当前浏览器中是否启用了java
    behInfo += "Vendor:" + x.vendor + enter;//浏览器的品牌
    behInfo += "UA:" + x.userAgent + enter;//客户机发送服务器的 user-agent 头部的值
    behInfo += "Platform:" + x.platform + enter;//运行浏览器的操作系统平台。
    behInfo += "CPU Class:" + x.cpuClass + enter;//浏览器系统的 CPU 等级
    behInfo += "OnLine:" + x.onLine + enter; //指明系统是否处于脱机模式的布尔值
    behInfo += "PluginNum:" + x.plugins.length + enter;//浏览器安装的插件数
    behInfo += "Language:" + x.language + enter;//OS 的自然语言设置
    behInfo += "MIMETypes:" + x.mimeTypes.length + enter;// 浏览器支持的所有MIME类型的数组
    behInfo += "Product:" + x.product + enter;//浏览器产品名称
    behInfo += "ProductSub:" + x.productSub + enter;//浏览器产品其他信息
    behInfo += "UsingCookie:" + x.cookieEnabled + enter;//浏览器是否开启cookie
    behInfo += "Screen Resolution:" + window.screen.width + "*" + window.screen.height + enter;//屏幕分辨率
    behInfo += "ColorDepth:" + window.screen.colorDepth + enter;//颜色质量
    behInfo += "DeviceXDPI:" + window.screen.deviceXDPI + enter;//像素

    function checkePlugs(pluginname) {//检查插件情况
        var f = "-";
        var plugins = navigator.plugins;
        if (plugins.length > 0) {
            for (var i = 0; i < navigator.plugins.length; i++) {
                if (navigator.plugins[i].name.indexOf(pluginname) >= 0) {
                    f = navigator.plugins[i].description.split(pluginname)[1];
                    return f;
                    break;
                }
            }
        }
        return false;
    }

    behInfo += "Flash Plugin:" + checkePlugs('Shockwave Flash') + enter;//flash插件情况

    //页面URL
    var url = window.location.href;
    url = url.replace('http://', '');
    behInfo += "URL:" + url + enter;

    //IP地址
    behInfo += "IP Address:" + returnCitySN["cip"] + enter;

    //浏览器指纹信息
    var fp1 = new Fingerprint();
    var fp2 = new Fingerprint({canvas: true});
    var fp3 = new Fingerprint({ie_activex: true});
    var fp4 = new Fingerprint({screen_resolution: true});
    behInfo += enter;
    behInfo += "browser fingerprint with default settings (canvas, screen resolutin, activeX disabled:   " + fp1.get() + enter;
    behInfo += "browser fingerprint with canvas enabled:   " + fp2.get() + enter;
    behInfo += "browser fingerprint with ie_activex enabled:   " + fp3.get() + enter;
    behInfo += "browser fingerprint with screen_resolution enabled:   " + fp4.get() + enter;
    behInfo += enter;

    collect(behInfo, bType);
}

function touch(event) {//获取移动端行为信息
    var e = event || window.event;
    var behInfo = "";
    switch (e.type) {
        case "touchstart":
            var x = e.targetTouches[0].screenX;
            var y = e.targetTouches[0].screenY;
            behInfo = "touchstart\t" + x + "\t" + y + "\t" + new Date().valueOf() + enter;
            collect(behInfo, "mobile");
            break;
        case "touchmove":
            e.preventDefault();
            var x = e.targetTouches[0].screenX;
            var y = e.targetTouches[0].screenY;
            behInfo = "touchmove\t" + x + "\t" + y + "\t" + new Date().valueOf() + enter;
            collect(behInfo, "mobile");
            break;
        case "touchend":
            var x = e.changedTouches[0].screenX;
            var y = e.changedTouches[0].screenY;
            behInfo = "touchend\t" + x + "\t" + y + "\t" + new Date().valueOf() + enter;
            collect(behInfo, "mobile");
            break;
    }
}

function mouseMove(event) {
    var e = event || window.event;
    var x = e.screenX;
    var y = e.screenY;
    var behInfo = "mousemove\t" + x + "\t" + y + "\t" + new Date().valueOf() + enter;
    collect(behInfo, "pc");
}

function mouseDown(event) {
    var e = event || window.event;
    var x = e.screenX;
    var y = e.screenY;
    //获取事件点击元素
    var targ = e.target;
    //获取元素名称
    var tname = targ;
    var text = targ.innerText;
    //alert(tname);
    var behInfo = "";
    if (e.button == 0) {
        behInfo = "mousedown_0\t" + x + "\t" + y + "\t" + new Date().valueOf() + "\t" + "click:" + tname + "\t" + text + enter;
    } else if (e.button == 1) {
        behInfo = "mousedown_1\t" + x + "\t" + y + "\t" + new Date().valueOf() + "\t" + "click:" + tname + "\t" + text + enter;
    } else {
        behInfo = "mousedown_2\t" + x + "\t" + y + "\t" + new Date().valueOf() + "\t" + "click:" + tname + "\t" + text + enter;
    }
    collect(behInfo, "pc");
}

function mouseUp(event) {
    var e = event || window.event;
    var x = e.screenX;
    var y = e.screenY;
    var behInfo = "";
    if (e.button == 0) {
        behInfo = "mouseup_0\t" + x + "\t" + y + "\t" + new Date().valueOf() + enter;
    } else if (e.button == 1) {
        behInfo = "mouseup_1\t" + x + "\t" + y + "\t" + new Date().valueOf() + enter;
    } else {
        behInfo = "mouseup_2\t" + x + "\t" + y + "\t" + new Date().valueOf() + enter;
    }
    collect(behInfo, "pc");
}

function mouseWheel(event) {
    var e = event || window.event;
    var behInfo = "";
    if (e.wheelDelta) {//120的倍数，向上是120，向下是-120
        behInfo = "mousewheel\t" + e.wheelDelta + "\t" + new Date().valueOf() + enter;
    } else {//FireFox: 3的倍数，向上是-3，向下是3
        behInfo = "mousewheel\t" + e.detail + "\t" + new Date().valueOf() + enter;
    }
    collect(behInfo, "pc");
}

function keyDown(event) {
    var e = event || window.event || arguments.callee.caller.arguments[0];
    var code = e.keyCode;
    var behInfo = "keydown\t" + code + "\t" + new Date().valueOf() + enter;
    collect(behInfo, "pc");
}

function keyUp(event) {
    var e = event || window.event || arguments.callee.caller.arguments[0];
    var code = e.keyCode;
    var behInfo = "keyup\t" + code + "\t" + new Date().valueOf() + enter;
    collect(behInfo, "pc");
}

function collect(behInfo, bType) {//异步提交行为信息
    var xmlhttp;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
//			document.getElementById("test").innerHTML = xmlhttp.responseText;http://202.117.3.78:8080
        }
    };
    xmlhttp.open("POST", "http://localhost:8080/WebDemoTest/AJAXServlet", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("behInfo=" + behInfo + "&bType=" + bType + "&fpath=" + fpath + "&txt=" + txt);
}

