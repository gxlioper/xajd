function baseDataInit(lb) {
	if (confirm("初始化操作将会清空当前学年、学期的所有数据，并重新生成。\n可能耗费较长时间，确定要初始化吗?")) {
		var dd_html = "";
		dd_html += "<table width='200' class='tbstyle'><tr><td height='60' align='center'>正在初始化数据，请稍候......<br><br>";
		dd_html += "<span class='roll_tip'></span>";
		dd_html += "</td></tr></table>";
		for (i = 1; i < document.getElementsByTagName("table").length; i++) {
			document.getElementsByTagName("table")[i].style.display = "none";
		}
		showDiv(dd_html, 300, 120);
		refreshForm("pjpyzjkj.do?method=baseDataInit&lb="+lb);
		return true;
	}
	return false;
}	

function rstz(url,lx){
	//用户类型
	var userType = val('userType');
	var dm = val("dm");
	//必须选择要调整人数的奖学金或荣誉称号
	if(dm == ""){
		var msg = "请选择" + (lx == "jxj" ? "奖学金！" : "荣誉称号！");
		alert(msg);
		return false;		
	}
	//显示范围
	var fs = val('fs');
	
	//页面数据检测
	if("xy" == userType){//学院用户		
		if("xy" == fs){
			alert(jQuery("#xbmc").val()+"用户只能调整专业或班级人数！");
			return false;
		}else if("zy" == fs){
			if(val('xy') == null || val('xy') == ""){
				alert("请选择"+jQuery("#xbmc").val()+"！");
				return false;
			}
			if(val('nj') == null || val('nj') == ""){
				alert("请选择年级！");
				return false;
			}
		}else if("bj" == fs){
			if(val('zy') == null || val('zy') == ""){
				alert("请选择专业！");
				return false;
			}
			if(val('nj') == null || val('nj') == ""){
				alert("请选择年级！");
				return false;
			}
		}
	}else{//学校用户
		if("xy" != fs){
			alert("学校用户只能调整"+jQuery("#xbmc").val()+"人数！");
			return false;
		}
	}
	//提交
	refreshForm(url);
}