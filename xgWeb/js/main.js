
var expandState = 0;
var ie = document.all ? 1 : 0;
var ns = document.layers ? 1 : 0;
var menuContent = new Object("element");
menuContent.curLeft = -150;
menuContent.curTop = 10;
menuContent.gapLeft = 0;
menuContent.gapTop = 0;
menuContent.timer = null;

function expand() {
	if (expandState == 0) {
		setPace("menuContent", -10, 10, 10);
		if (ie) {
			menutop.className = "menuOut";
		}
		expandState = 1;
	} else {
		setPace("menuContent", -145, 10, 10);
		if (ie) {
			menutop.className = "menuIn";
		}
		expandState = 0;
	}
}
function setPace(layerName, fromLeft, fromTop, motionSpeed) {
	eval(layerName).gapLeft = (Math.max(eval(layerName).curLeft, fromLeft) - Math.min(eval(layerName).curLeft, fromLeft)) / motionSpeed;
	eval(layerName).gapTop = (Math.max(eval(layerName).curTop, fromTop) - Math.min(eval(layerName).curTop, fromTop)) / motionSpeed;
	moveAlong(layerName, eval(layerName).gapLeft, eval(layerName).gapTop, fromLeft, fromTop);
}
function moveAlong(layerName, paceLeft, paceTop, fromLeft, fromTop) {
	clearTimeout(eval(layerName).timer);
	if (eval(layerName).curLeft != fromLeft) {
		if ((Math.max(eval(layerName).curLeft, fromLeft) - Math.min(eval(layerName).curLeft, fromLeft)) < paceLeft) {
			eval(layerName).curLeft = fromLeft;
		} else {
			if (eval(layerName).curLeft < fromLeft) {
				eval(layerName).curLeft = eval(layerName).curLeft + paceLeft;
			} else {
				if (eval(layerName).curLeft > fromLeft) {
					eval(layerName).curLeft = eval(layerName).curLeft - paceLeft;
				}
			}
		}
		if (ie) {
			document.all[layerName].style.left = eval(layerName).curLeft;
		}
		if (ns) {
			document[layerName].left = eval(layerName).curLeft;
		}
	}
	if (eval(layerName).curTop != fromTop) {
		if ((Math.max(eval(layerName).curTop, fromTop) - Math.min(eval(layerName).curTop, fromTop)) < paceTop) {
			eval(layerName).curTop = fromTop;
		} else {
			if (eval(layerName).curTop < fromTop) {
				eval(layerName).curTop = eval(layerName).curTop + paceTop;
			} else {
				if (eval(layerName).curTop > fromTop) {
					eval(layerName).curTop = eval(layerName).curTop - paceTop;
				}
			}
		}
		if (ie) {
			document.all[layerName].style.top = eval(layerName).curTop;
		}
		if (ns) {
			document[layerName].top = eval(layerName).curTop;
		}
	}
	eval(layerName).timer = setTimeout("moveAlong(\"" + layerName + "\"," + paceLeft + "," + paceTop + "," + fromLeft + "," + fromTop + ")", 30);
}
function setTsxx(gnmkdm) {
	var xxdm = jQuery("#xxdm").val();
	if ("10491" == xxdm && "N06" == gnmkdm) {
		getXjydInfo.getQgzxTsxx(function (data) {
			var text = "";
			for (var i = 0; i < data.length; i++) {
				text += i + 1 + ": " + data[i] + "<br/>";
			}
			if (data.length > 0) {
				var msg1 = new class_message("", 200, 120, "\u6d88\u606f\u63d0\u793a\uff1a", text, "", "");
				msg1.rect(null, null, null, screen.height - 50);
				msg1.speed = 60;
				msg1.step = 10;
				msg1.show();
			}
		});
	}
}
function shgcKnsShow() {
	var msg1 = new class_message("", 200, 120, "\u6d88\u606f\u63d0\u793a\uff1a", "\u4f60\u7684\u56f0\u96be\u751f\u8ba4\u5b9a\u5df2\u8fc7\u671f\uff0c\u8bf7\u91cd\u65b0\u7533\u8bf7\u3002", "", "false", "");
	msg1.rect(null, null, null, screen.height - 50);
	msg1.speed = 100;
	msg1.step = 20;
	msg1.show();
}
function singleLogin() {
	var openType = document.getElementById("openType").value;
	var xxdm = document.getElementById("xxdm").value;
	if (openType == "1") {
		document.getElementById("showTop").style.display = "none";
		document.getElementById("showtool").style.display = "none";
		document.getElementById("showtooltd").height = "1";
	}
	if (xxdm == "128720") {
		var fbnr = document.getElementById("fbnr").value;
		var fbsj = document.getElementById("fbsj").value;
		if (fbnr != "") {
			alert(fbnr + "\n\t---" + fbsj);
		}
	}
}
function zjcmwjcfMsg() {
		//先调用DWR验证是否有处分
	var userDep = document.getElementById("userDep").value;
	var userType = document.getElementById("userType").value;
	var isFdy = document.getElementById("fdyQx").value;
	var userName = document.getElementById("userName").value;
	getLxckDcls.getLxckjcDjcxx(userType, userDep, isFdy, userName, function (data) {
		if (data != "" && data != null) {
			var msg1 = new class_message("", 200, 120, "\u6d88\u606f\u63d0\u793a\uff1a", "\u8fdd\u7eaa\u5904\u5206\u7559\u6821\u5bdf\u770b\u63d0\u9192", "\u8ddd\u5f53\u524d\u65f6\u95f4" + data + "\u5929\u4ee5\u5185\uff0c\u90e8\u5206\u5b66\u751f\u8fdd\u7eaa\u5904\u5206\u7559\u6821\u5bdf\u770b\u4fe1\u606f\u9700\u8981\u89e3\u9664,\u8bf7\u70b9\u51fb\u8fdb\u884c\u5904\u7406\u3002", "true", "/xgxt/wjcflxcktj.do");
			msg1.rect(null, null, null, screen.height - 50);
			msg1.speed = 60;
			msg1.step = 10;
			msg1.show();
		}
	});
}
function zgdzdxFxMsg() {
	var userDep = document.getElementById("userDep").value;
	var userType = document.getElementById("userType").value;
	var isFdy = document.getElementById("fdyQx").value;
	var userName = document.getElementById("userName").value;
	getXjydInfo.getWfxxx(userType, userDep, isFdy, userName, function (data) {
		if (data != "" && data != null && data != "0") {
			var msg1 = new class_message("", 200, 120, "\u6d88\u606f\u63d0\u793a\uff1a", "\u5b66\u751f\u4f11\u5b66\u540e\u672a\u590d\u5b66\u63d0\u9192", "\u5728\u4f11\u5b66\u622a\u6b62\u65e5\u671f\u5185\u6709" + data + "\u4f4d\u5b66\u751f\u672a\u590d\u5b66\uff0c\u8bf7\u70b9\u51fb\u5904\u7406\u3002", "true", "/xgxt/stu_status_different.do?ydlbdm=117&sffx=\u5426&go=go");
			msg1.rect(null, null, null, screen.height - 50);
			msg1.speed = 60;
			msg1.step = 10;
			msg1.show();
		}
	});
}

function XstsShow(){
        var msg1 = new class_message("",200,120,"消息提示：","请您先完善自己的学生信息，之后才能查看使用其他功能模块","","false","");  
        msg1.rect(null,null,null,screen.height-50); 
        msg1.speed    = 100; 
        msg1.step    = 20; 
        msg1.show();         
    }


//加载内页时，如果左菜单隐藏时内页宽度设置
function loadWin(){
	//var o = document.getElementsByTagName('frames').document;
	var o = document.frames[1].document;
	var div = getElementByClassName(o,"div","con_overlfow");
	
	if ($("left").className == "hide") {
		$("right").style.width = "100%";
		if (div){
			div.style.width="100%";
		}
		scrollDivWidth = "100%";
	}
}