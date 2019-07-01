if (BatAlert == null) var BatAlert = {};
BatAlert.H = function() {
	return (self.innerHeight || document.documentElement.clientHeight || document.body.clientHeight);
};

BatAlert.W = function () {
	return (document.documentElement.offsetWidth || document.body.offsetWidth);
};

BatAlert.MyAlert = function (msg, tit, okCallbak, isConfirm, def, cancelCallbak) {
	if($("dvMyAlert")){
		return;
	}
	BatAlert.setHideEleByTagName("select","none");
	var br = document.createElement("DIV");
	br.id = "dvMyAlert";		
	document.body.appendChild(br);	
	tit = tit || "系统提示";
	var u = "";
	u += "<div class=\"main1\" style=\"width:300px;position:absolute;z-index:999;bgcolor:yellow\" id=\"sysMsgWin\">";
	u += "<div class=\"title\" id=\"sysMsgWin_Tit\" style=\"cursor:move;width:315px\"><div class=\"title_l\"></div><div class=\"title_m\">";
	u += tit + "</div><div id=\"btnSysInfoClose\" title=\"关闭\" class=\"title_c\"></div></div>";
	u += "<div class=\"buttontool\"><table width=\"300px\" border=\"0\"><tr><td height=\"80\" width=\"50\" valign=\"middle\">";
	u += "<div class=\"main1\" style=\"width:50px;height:50px\"></div></td><td width=\"2450\" align=\"left\" style=\"word-break:break-all;\" >";
	u += msg + "</td></tr><tr><td colspan=\"2\" align=\"center\">";
	u += "<input type=\"button\" value=\" 确 定 \" class=\"button2\" id=\"btnSysMsgOk\" />";
	if(isConfirm){
		u += "&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"button\" value=\" 取 消 \" class=\"button2\" id=\"btnSysMsgNo\" />";
	}
	u += "</td></tr></table></div></div>";
	var Aok = "100%";
	var Anc = "100%";
	if (document.body.scrollHeight > BatAlert.H()) {
		Anc = document.body.scrollHeight;
	}
	var Abc = "";
	Abc = "-moz-opacity:0.07;";
	Abc += "filter:alpha(opacity=7);";
	u += "<div style=\"position:absolute;z-index:998;top:0;left:0;width:" + Aok + ";height:" + Anc + ";" + Abc + "background-color:#0000000\"></div>";
	br.innerHTML = u;
	var ag = br.firstChild;
	
	br.oncontextmenu = function(){
		return false;
	};
	br.onselectstart = function(){
		return false;
	};
	br.onkeydown = function(){
		if(event.keyCode == 9){
			$("btnSysMsgOk").focus();
			return false;
		}
	};
	var h = ag.offsetHeight;
	var w = ag.offsetWidth;
	var scrollTop = document.body.scrollTop;
	var x = (BatAlert.W() - w) / 2;
	var y = (BatAlert.H() - h) / 2 + scrollTop;
	ag.style.left = x + "px";
	ag.style.top = y + "px";
	var l;
	var t;
	var obj;
	$("sysMsgWin_Tit").onmousedown = function(){
		obj = ag;
	  	l = event.x - ag.style.pixelLeft; 
	  	t = event.y - ag.style.pixelTop; 		
	}
	$("sysMsgWin_Tit").onmousemove = function(){
		if(obj){
			ag.style.left = event.x - l; 
	    	ag.style.top = event.y - t;
		}
	}
	$("sysMsgWin_Tit").onmouseup = function(){
		if(obj){
			obj = null;
		}
	}
	if(!isConfirm){
		$("btnSysMsgOk").focus();
		$("btnSysInfoClose").onclick = function () {
			document.body.removeChild(br);
			if (okCallbak) {
				okCallbak();
			}
			BatAlert.setHideEleByTagName("select","");
			return false;
		};
		$("btnSysMsgOk").onclick = function () {
			$("btnSysInfoClose").onclick();
		};
	}else{
		$("btnSysMsgNo").focus();
		if(def){
			$("btnSysMsgOk").focus();
		}
		$("btnSysMsgOk").onclick = function () {
			document.body.removeChild(br);
			if (okCallbak) {
				okCallbak();
			}
			BatAlert.setHideEleByTagName("select","");
			return false;
		};
		$("btnSysInfoClose").onclick = function(){
			document.body.removeChild(br);
			if (cancelCallbak) {
				cancelCallbak();
			}
			BatAlert.setHideEleByTagName("select","");
			return false;
		}
		$("btnSysMsgNo").onclick = function () {
			$("btnSysInfoClose").onclick();
		};
	}
	return false;
};

BatAlert.setHideEleByTagName = function (tagName,sty1){
	var tmp = document.getElementsByTagName(tagName);
	for(var i = 0; i < tmp.length; i++){
		tmp[i].style.display = sty1;
	} 
};

BatAlert.showTips = function (msg) {
	BatAlert.setHideEleByTagName("select","none");
	msg = (msg == null)?"数据处理中,请稍候...":msg;
	var dd_html = "";
	dd_html += "<table border=0 cellpadding=0 cellspacing=1 bgcolor=\"#000000\"";
	dd_html += "width=\"100%\" height=\"100%\"><tr>";
	dd_html += "<td bgcolor=#5C8DBE>";
	dd_html += "<marquee align=\"middle\" behavior=\"alternate\" scrollamount=\"2\" style=\"font-size:9pt\">";
	dd_html += "<font color=yellow>";
	dd_html += msg + "</font>";
	dd_html += "</marquee></td></tr></table>";
	if(!$("tipsConv")){
		var tipsConv = document.createElement("DIV");
		tipsConv.id = "tipsConv";
		tipsConv.oncontextmenu = function(){return false;};
		tipsConv.onSelectstart = function(){return false;};	
		tipsConv.style.cssText = "background-color:#CCCCCC;position:absolute;z-index:100;filter:alpha(opacity=20);";
		tipsConv.style.width = document.body.offsetWidth;
		tipsConv.style.height = document.body.scrollHeight;
		tipsConv.style.pixelTop = 0;
		tipsConv.style.left = 0;
		tipsConv.style.display = "block";
		document.body.appendChild(tipsConv);
	}
	if($("tipDiv")){
		$("tipDiv").innerHTML = dd_html;
	}else{
		var tips = document.createElement("DIV");
		tips.id = "tipDiv";
		tips.innerHTML = dd_html;
		tips.style.cssText = "width:300px;height:30px;position:absolute;z-index:100;filter:alpha(opacity=70);";
		tips.style.pixelTop = lastScrollY + 120;
		tips.style.left = (BatAlert.W() - 200) / 2;
		tips.style.display = "block";
		document.body.appendChild(tips);
	}
};
BatAlert.closeTips = function (){
	BatAlert.setHideEleByTagName("select","");
	if($("tipsConv")){
		document.body.removeChild($("tipsConv"));
	}
	if($("tipDiv")){
		document.body.removeChild($("tipDiv"));
	}
};
BatAlert.showInfo = function (){
	if($("infoConv")){
		document.body.removeChild($("infoConv"));
	}
	if(arguments.length == 0){
		return false;
	}
	var infoConv = document.createElement("DIV");
	infoConv.id = "infoConv";
	var dd_html = "<table border='0' width='200'>";
	for(var i = 0;i < arguments.length;i++){
		dd_html += "<tr><td style='word-break:break-all;'>" + arguments[i] + "</td></tr>";
	}
	dd_html += "</table>";
    x = event.x + document.body.scrollLeft;
    if(document.body.clientWidth&&x+200>document.body.clientWidth){
    	x-=220;
    }
    y = event.y + document.body.scrollTop;
	infoConv.innerHTML = dd_html;
	infoConv.style.cssText = "border:1px solid #DDD891;background-color:#FEF5DA;position:absolute;z-index:100;filter:alpha(opacity=80);";
	infoConv.style.left = x + 10;
	infoConv.style.top = y + 10;
	document.body.appendChild(infoConv);
}

BatAlert.getValueTip = function (tit,x1,y1,dataT,dataV,func,funcCl){
	if($("infoConv")){
		BatAlert.setHideEleByTagName("button","");
		BatAlert.setHideEleByTagName("select","");
		document.body.removeChild($("infoConv"));
	}
	BatAlert.setHideEleByTagName("select","none");
	BatAlert.setHideEleByTagName("button","none");
	var infoConvTb = document.createElement("div");
	infoConvTb.id = "infoConv";
	document.body.appendChild(infoConvTb);
	var u = "";
	u += "<div class=\"main1\" style=\"width:300px;position:absolute;z-index:999\" id=\"sysMsgWin\">";
	u += "<div class=\"title\" id=\"sysMsgWin_Tit\" style=\"cursor:move;\"><div class=\"title_l\"></div><div class=\"title_m\">";
	u += tit + "</div><div id=\"btnSysInfoClose\" title=\"关闭\" class=\"title_c\"></div></div>";
	u += "<div class=\"content_no\"><table class=\"tbstyle\" width=\"280\" style=\"border:0px\" border=\"0\">";
	u += "<thead><tr><td align=center>" + dataV[0] + "</td><td align=center>" + dataT[0] + "</td></tr></thead><tbody id=\"valueConvTable\">";
	for(var i = 1;i < dataT.length;i++){
		u += "<tr onmouseover='rowOnClick(this)' onclick='rowOnClick(this)' style='cursor:hand'><td nowrap>";
		u += dataV[i];
		u += "</td><td nowrap>";
		u += dataT[i];
		u += "</td></tr>";
	}
	var Anc = "100%";
	if (document.body.scrollHeight > BatAlert.H()) {
		Anc = document.body.scrollHeight;
	}
	u += "</tbody></table></div></div>";
	u += "<div style=\"position:absolute;z-index:998;top:0;left:0;width:100%;height:" + Anc + ";-moz-opacity:0.07;filter:alpha(opacity=7);background-color:#000000\"></div>";
	infoConvTb.innerHTML = u;
	var ag = infoConvTb.firstChild;
	ag.style.left = x1;
	ag.style.top = y1;
	$("btnSysInfoClose").onclick = function(){
		BatAlert.setHideEleByTagName("button","");
		BatAlert.setHideEleByTagName("select","");
		document.body.removeChild(infoConvTb);
		if(funcCl){
			funcCl();
		}
		return false;
	};
	if(func){
	  	$("valueConvTable").ondblclick = func;
	}
}