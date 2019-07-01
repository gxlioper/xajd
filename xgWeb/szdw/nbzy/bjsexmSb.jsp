<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
var zzjf_length = "1";
var zyjf_length = "1";
function xmSb(){
	var xgbjfys = "";
	var zyjfys = "";
	
	for(var i=0;i < zzjf_length;i++){
		var zz_xm = $("zz_xm"+i).value;
		if(zz_xm == ""){
			alert("学工部资助经费预算第"+(i+1)+"行支出项目为空，请确认！！！");
			return false;
		}
		var zz_sbzz = $("zz_sbzz"+i).value;
		var zz_xbzz = $("zz_xbzz"+i).value;
		var zz_hj = $("zz_hj"+i).value;		
		xgbjfys += zz_xm + "!!@!!" + zz_sbzz + "!!@!!" + zz_xbzz + "!!@!!" +zz_hj + "!!@@!!";	
	}
	
	for(var i=0;i < zyjf_length;i++){
		var zy_xm = $("zy_xm"+i).value;
		if(zy_xm == ""){
			alert("班级自有经费预算第"+(i+1)+"行支出项目为空，请确认！！！");
			return false;
		}
		var zy_sbzz = $("zy_sbzz"+i).value;
		var zy_xbzz = $("zy_xbzz"+i).value;
		var zy_hj = $("zy_hj"+i).value;		
		zyjfys += zy_xm + "!!@!!" + zy_sbzz + "!!@!!" + zy_xbzz + "!!@!!" +zy_hj + "!!@@!!";	
	}
	var zzhj = $("zz_sbhj").value + "!!@!!"+ $("zz_xbhj").value + "!!@!!"+ $("zz_zhj").value;
	var zyhj = $("zy_sbhj").value + "!!@!!"+ $("zy_xbhj").value + "!!@!!"+ $("zy_zhj").value;
	$("xgbjfys").value = xgbjfys + zzhj;
	$("zyjfys").value = zyjfys + zyhj;
	refreshForm("bjtsxm.do?method=bjtsxmSb&type=save&pk="+$("pkValue").value);
}
function xmSh(yj){
	var userType=$("userType").value;
	if(userType == "teacher"){
		if($("bzryj").value == ""){
			alert("班主任意见不能为空，请确认！！！");
			return false;
		}
	}else if(userType == "xy"){
		if($("xyyj").value == ""){
			alert("<bean:message key="lable.xsgzyxpzxy" />意见不能为空，请确认！！！");
			return false;
		}
	}else if(userType == "xx" || userType == "admin" ){
		if($("xxyj").value == ""){
			alert("学工部意见不能为空，请确认！！！");
			return false;
		}
	}
	refreshForm("bjtsxm.do?method=bjtsxmSb&type=save&shzt="+yj+"&pk="+$("pkValue").value);
}
function chld(){
	document.getElementById('cs').disabled=false;
}
function initQsList(){
	var cs = "";
	var lddm="";
	document.getElementById('qsh').disabled=false;	
	if($("cs")){cs = $("cs").value};
	if($("lddm")){lddm = $("lddm").value};
	getBjtsxmDAO.getSsFpQsList(cs,lddm,function initTjList(data){
		if (data != null && typeof data == 'object') {
			var objId = data[0].dm;
			if($(objId) && $(objId).tagName.toLowerCase() == "select"){
				DWRUtil.removeAllOptions(objId);			
				DWRUtil.addOptions(objId,data,"dm","mc");			
			}
		}else{
			showMsgWin("有错误出现：远程数据读取失败！");
		}
	});
}
function addZzjf(){
	var addZzjf = new Array();
	if(zzjf_length<5){
		addZzjf[0]= document.createElement("input");
		addZzjf[0].style.width="150px";
		addZzjf[0].name="zz_xm" +zzjf_length;
		addZzjf[0].id="zz_xm" +zzjf_length;
		addZzjf[0].maxLength="20";
		addZzjf[0].onblur=function(){addZzhj(this.id)};
		addZzjf[0].value="";
		
		addZzjf[1]= document.createElement("input");
		addZzjf[1].style.width="150px";
		addZzjf[1].name="zz_sbzz" +zzjf_length;
		addZzjf[1].id="zz_sbzz" +zzjf_length;
		addZzjf[1].maxLength="10";
		addZzjf[1].onblur=function(){addZzhj(this.id)};
		addZzjf[1].onfocus=function(){setValue(this)};
		addZzjf[1].onkeyup=function(){chValue(this)};
		addZzjf[1].value="";
		
		addZzjf[2]= document.createElement("input");
		addZzjf[2].style.width="150px";
		addZzjf[2].name="zz_xbzz" +zzjf_length;
		addZzjf[2].id="zz_xbzz" +zzjf_length;
		addZzjf[2].maxLength="10";
		addZzjf[2].onblur=function(){addZzhj(this.id)};
		addZzjf[2].onfocus=function(){setValue(this)};
		addZzjf[2].onkeyup=function(){chValue(this)};
		addZzjf[2].value="";
		
		addZzjf[3]= document.createElement("input");
		addZzjf[3].style.width="150px";
		addZzjf[3].readOnly="ture";
		addZzjf[3].name="zz_hj" +zzjf_length;
		addZzjf[3].id="zz_hj" +zzjf_length;
		addZzjf[3].maxLength="10";
		addZzjf[3].value="";
		
		DWRUtil.addRows("zzjf",['dd'],addZzjf);
	
		zzjf_length++;
	}
}
function addZyjf(){
	var addZyjf = new Array();
	if(zyjf_length<5){
		addZyjf[0]= document.createElement("input");
		addZyjf[0].style.width="150px";
		addZyjf[0].name="zy_xm" +zyjf_length;
		addZyjf[0].id="zy_xm" +zyjf_length;
		addZyjf[0].maxLength="20";
		addZyjf[0].onblur=function(){addZyhj(this.id)};
		addZyjf[0].value="";
		
		addZyjf[1]= document.createElement("input");
		addZyjf[1].style.width="150px";
		addZyjf[1].name="zy_sbzz" +zyjf_length;
		addZyjf[1].id="zy_sbzz" +zyjf_length;
		addZyjf[1].maxLength="10";
		addZyjf[1].onblur=function(){addZyhj(this.id)};
		addZyjf[1].onfocus=function(){setValue(this)};
		addZyjf[1].onkeyup=function(){chValue(this)};
		addZyjf[1].value="";
		
		addZyjf[2]= document.createElement("input");
		addZyjf[2].style.width="150px";
		addZyjf[2].name="zy_xbzz" +zyjf_length;
		addZyjf[2].id="zy_xbzz" +zyjf_length;
		addZyjf[2].maxLength="10";
		addZyjf[2].onblur=function(){addZyhj(this.id)};
		addZyjf[2].onfocus=function(){setValue(this)};
		addZyjf[2].onkeyup=function(){chValue(this)};
		addZyjf[2].value="";
		
		addZyjf[3]= document.createElement("input");
		addZyjf[3].style.width="150px";
		addZyjf[3].readOnly="ture";
		addZyjf[3].name="zy_hj" +zyjf_length;
		addZyjf[3].id="zy_hj" +zyjf_length;
		addZyjf[3].maxLength="10";
		addZyjf[3].value="";
		
		DWRUtil.addRows("zyjf",['dd'],addZyjf);
	
		zyjf_length++;
	}
}
function delZzjf(){
    var tabobj = document.getElementById("zzjf");
    var length = tabobj.rows.length;   
    if(length==1){
      	return false;
    }
   	if(confirm("确定要删除行？")){    
   	  	zzjf_length --;
   	  	$("zz_sbhj").value=parseInt($("zz_sbhj").value)-parseInt($("zz_sbzz"+zzjf_length).value);
   	  	$("zz_xbhj").value=parseInt($("zz_xbhj").value)-parseInt($("zz_xbzz"+zzjf_length).value);
   	  	$("zz_zhj").value=parseInt($("zz_zhj").value)-(parseInt($("zz_sbzz"+zzjf_length).value)+parseInt($("zz_xbzz"+zzjf_length).value));
       	tabobj.deleteRow(tabobj.rows.length-1);
    }
}
function delZyjf(){
    var tabobj = document.getElementById("zyjf");
    var length = tabobj.rows.length;   
    if(length==1){
      	return false;
    }
   	if(confirm("确定要删除行？")){  
   		zyjf_length --; 
   		$("zy_sbhj").value=parseInt($("zy_sbhj").value)-parseInt($("zy_sbzz"+zyjf_length).value);
   	  	$("zy_xbhj").value=parseInt($("zy_xbhj").value)-parseInt($("zy_xbzz"+zyjf_length).value);
   	  	$("zy_zhj").value=parseInt($("zy_zhj").value)-(parseInt($("zy_sbzz"+zyjf_length).value)+parseInt($("zy_xbzz"+zyjf_length).value));   
       	tabobj.deleteRow(tabobj.rows.length-1);
    }
}
function chValue(obj){
	$(obj.id).value = obj.value.replace(/[^\d]/g,'');
}
function addZzhj(value){
	var id = "";
	if(value.length>6){
		id=value.substr(7,1);
	}else{
		id=value.substr(5,1);
	}
	zz_sbzz = $("zz_sbzz"+id).value;
	zz_xbzz = $("zz_xbzz"+id).value;
	if(zz_sbzz == ""){
		zz_sbzz = 0;
		$("zz_sbzz"+id).value = 0;
	}else if(zz_sbzz.length>1 && zz_sbzz.substr(0,1)=="0"){
		zz_sbzz = zz_sbzz.substr(1,zz_sbzz.length-1);
		$("zz_sbzz"+id).value = zz_sbzz;
	}
	if(zz_xbzz == ""){
		zz_xbzz = 0;
		$("zz_xbzz"+id).value = 0;
	}else if(zz_xbzz.length>1 && zz_xbzz.substr(0,1)=="0"){
		zz_xbzz = zz_xbzz.substr(1,zz_xbzz.length-1);
		$("zz_xbzz"+id).value = zz_xbzz;
	}
	$("zz_hj"+id).value= parseInt(zz_sbzz)+parseInt(zz_xbzz);
	
	var zz_sbhj = "0";
	var zz_xbhj = "0";
	for(var i=0;i < zzjf_length;i++){
		if($("zz_sbzz"+i).value != ""){
			zz_sbhj = parseInt(zz_sbhj) + parseInt($("zz_sbzz"+i).value);
		}
		if($("zz_xbzz"+i).value != ""){
			zz_xbhj = parseInt(zz_xbhj) + parseInt($("zz_xbzz"+i).value);
		}
	}
	$("zz_sbhj").value= zz_sbhj;
	$("zz_xbhj").value= zz_xbhj;
	$("zz_zhj").value= parseInt(zz_sbhj)+parseInt(zz_xbhj);
}
function addZyhj(value){
	var id = "";
	if(value.length>6){
		id=value.substr(7,1);
	}else{
		id=value.substr(5,1);
	}
	zy_sbzz = $("zy_sbzz"+id).value;
	zy_xbzz = $("zy_xbzz"+id).value;
	if(zy_sbzz == ""){
		zy_sbzz = 0;
		$("zy_sbzz"+id).value = 0;
	}else if(zy_sbzz.length>1 && zy_sbzz.substr(0,1)=="0"){
		zy_sbzz = zy_sbzz.substr(1,zy_sbzz.length-1);
		$("zy_sbzz"+id).value = zy_sbzz;
	}
	if(zy_xbzz == ""){
		zy_xbzz = 0;
		$("zy_xbzz"+id).value = 0;
	}else if(zy_xbzz.length>1 && zy_xbzz.substr(0,1)=="0"){
		zy_xbzz = zy_xbzz.substr(1,zy_xbzz.length-1);
		$("zy_xbzz"+id).value = zy_xbzz;
	}
	$("zy_hj"+id).value= parseInt(zy_sbzz)+parseInt(zy_xbzz);
	
	var zy_sbhj = "0";
	var zy_xbhj = "0";
	for(var i=0;i < zyjf_length;i++){
		if($("zy_sbzz"+i).value != ""){
			zy_sbhj = parseInt(zy_sbhj) + parseInt($("zy_sbzz"+i).value);
		}
		if($("zy_xbzz"+i).value != ""){
			zy_xbhj = parseInt(zy_xbhj) + parseInt($("zy_xbzz"+i).value);
		}
	}
	$("zy_sbhj").value= zy_sbhj;
	$("zy_xbhj").value= zy_xbhj;
	$("zy_zhj").value= parseInt(zy_sbhj)+parseInt(zy_xbhj);
}
function chcd(obj,leng){
	if(obj.value.length > leng){
		alert("该项目最大字数为"+leng+",现已经超过，请确认！！");
		obj.focus();
	}
}

function setValue(obj){
	//obj.value="";
}

function onShow(){
	var xgbjfys = $("xgbjfys").value;
	var zyjfys = $("zyjfys").value;
	var arrXgb = new Array();
	var arrZy = new Array();
	if(xgbjfys != ""){
		arrXgb = xgbjfys.split("!!@@!!");
		for(var i = 0; i<arrXgb.length;i++){
			var xgbNr = new Array();
			var addZzjf = new Array();
			xgbNr=arrXgb[i].split("!!@!!");
			
			if(i == 0){
				$("zz_xm0").value=xgbNr[0];
				$("zz_sbzz0").value=xgbNr[1];
				$("zz_xbzz0").value=xgbNr[2];
				$("zz_hj0").value=xgbNr[3];
			}else if(i == arrXgb.length-1){
				$("zz_sbhj").value=xgbNr[0];
				$("zz_xbhj").value=xgbNr[1];
				$("zz_zhj").value=xgbNr[2];
			}
			else{
				addZzjf[0]= document.createElement("input");
				addZzjf[0].style.width="150px";
				addZzjf[0].name="zz_xm" +zzjf_length;
				addZzjf[0].id="zz_xm" +zzjf_length;
				addZzjf[0].maxLength="20";
				addZzjf[0].onblur=function(){addZzhj(this.id)};
				addZzjf[0].value=xgbNr[0];
				
				addZzjf[1]= document.createElement("input");
				addZzjf[1].style.width="150px";
				addZzjf[1].name="zz_sbzz" +zzjf_length;
				addZzjf[1].id="zz_sbzz" +zzjf_length;
				addZzjf[1].maxLength="10";
				addZzjf[1].onblur=function(){addZzhj(this.id)};
				addZzjf[1].onfocus=function(){setValue(this)};
				addZzjf[1].onkeyup=function(){chValue(this)};
				addZzjf[1].value=xgbNr[1];
				
				addZzjf[2]= document.createElement("input");
				addZzjf[2].style.width="150px";
				addZzjf[2].name="zz_xbzz" +zzjf_length;
				addZzjf[2].id="zz_xbzz" +zzjf_length;
				addZzjf[2].maxLength="10";
				addZzjf[2].onblur=function(){addZzhj(this.id)};
				addZzjf[2].onfocus=function(){setValue(this)};
				addZzjf[2].onkeyup=function(){chValue(this)};
				addZzjf[2].value=xgbNr[2];
				
				addZzjf[3]= document.createElement("input");
				addZzjf[3].style.width="150px";
				addZzjf[3].readOnly="ture";
				addZzjf[3].name="zz_hj" +zzjf_length;
				addZzjf[3].id="zz_hj" +zzjf_length;
				addZzjf[3].maxLength="10";
				addZzjf[3].value=xgbNr[3];
				
				DWRUtil.addRows("zzjf",['dd'],addZzjf);
				
				zzjf_length++;
			}
			
		}
	
	}
	if(zyjfys != ""){
		arrZy = zyjfys.split("!!@@!!");
		for(var i = 0; i<arrZy.length;i++){
			var zyNr = new Array();
			var addZyjf = new Array();
			zyNr=arrZy[i].split("!!@!!");
			
			if(i == 0){
				$("zy_xm0").value=zyNr[0];
				$("zy_sbzz0").value=zyNr[1];
				$("zy_xbzz0").value=zyNr[2];
				$("zy_hj0").value=zyNr[3];
			}else if(i == arrXgb.length-1){
				$("zy_sbhj").value=zyNr[0];
				$("zy_xbhj").value=zyNr[1];
				$("zy_zhj").value=zyNr[2];
			}
			else{
				addZyjf[0]= document.createElement("input");
				addZyjf[0].style.width="150px";
				addZyjf[0].name="zy_xm" +zyjf_length;
				addZyjf[0].id="zy_xm" +zyjf_length;
				addZyjf[0].maxLength="20";
				addZyjf[0].onblur=function(){addZyhj(this.id)};
				addZyjf[0].value=zyNr[0];
				
				addZyjf[1]= document.createElement("input");
				addZyjf[1].style.width="150px";
				addZyjf[1].name="zy_sbzz" +zyjf_length;
				addZyjf[1].id="zy_sbzz" +zyjf_length;
				addZyjf[1].maxLength="10";
				addZyjf[1].onblur=function(){addZyhj(this.id)};
				addZyjf[1].onfocus=function(){setValue(this)};
				addZyjf[1].onkeyup=function(){chValue(this)};
				addZyjf[1].value=zyNr[1];
				
				addZyjf[2]= document.createElement("input");
				addZyjf[2].style.width="150px";
				addZyjf[2].name="zy_xbzz" +zyjf_length;
				addZyjf[2].id="zy_xbzz" +zyjf_length;
				addZyjf[2].maxLength="10";
				addZyjf[2].onblur=function(){addZyhj(this.id)};
				addZyjf[2].onfocus=function(){setValue(this)};
				addZyjf[2].onkeyup=function(){chValue(this)};
				addZyjf[2].value=zyNr[2];
				
				addZyjf[3]= document.createElement("input");
				addZyjf[3].style.width="150px";
				addZyjf[3].readOnly="ture";
				addZyjf[3].name="zy_hj" +zyjf_length;
				addZyjf[3].id="zy_hj" +zyjf_length;
				addZyjf[3].maxLength="10";
				addZyjf[3].value=zyNr[3];
				
				DWRUtil.addRows("zyjf",['dd'],addZyjf);
				
				zyjf_length++;
			}
			
		}
	
	}
}

jQuery(function(){
	onShow();
})
	
</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getBjtsxmDAO.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script language="javascript" src="js/xsgyglFunction.js"></script>
		<html:form action="/viewArmyStu" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
					<bean:write name="title" />
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					你不是班长，本模块只能由班长访问！！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" name="xgbjfys" id="xgbjfys"
					value="<bean:write name="rs" property="xgbjfys"/>">
				<input type="hidden" name="zyjfys" id="zyjfys"
					value="<bean:write name="rs" property="zyjfys"/>">
				<fieldset>
					<legend>
						申请班级基本情况
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr valign="middle">
								<td align="center" colspan="4">
									<b style="font-size:14">基本信息</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="right">
								班级名称：
							</td>
							<td align="left">
								<html:text name="rs" property="bjmc" styleId="bjmc"
									style="width:100%" readonly="true" />
								<html:hidden name="rs" property="bjdm" styleId="bjdm"
									style="width:100%" />
							</td>
							<td align="right">
								班主任：
							</td>
							<td align="left">
								<html:text name='rs' property="bzrxm" styleId="bzrxm"
									style="width:100%" readonly="true" />
								<html:hidden name="rs" property="bzrzgh" styleId="bzrzgh"
									style="width:100%" />
							</td>
						</tr>
						<tr>
							<td align="right">
								班长：
							</td>
							<td align="left">
								<html:text name="rs" property="bzxm" styleId="bzxm"
									style="width:100%" readonly="true" />
								<html:hidden name="rs" property="bzxh" styleId="bzxh"
									style="width:100%" />
							</td>
							<td align="right">
								团支书：
							</td>
							<td align="left">
								<html:text name="rs" property="tzsxm" styleId="tzsxm"
									style="width:100%" readonly="true" />
								<html:hidden name="rs" property="tzsxh" styleId="tzsxh"
									style="width:100%" />
							</td>
						</tr>
						<tr>
							<td align="right">
								所属分院：
							</td>
							<td align="left">
								<html:text name="rs" property="xymc" styleId="xymc"
									style="width:100%" readonly="true" />
								<html:hidden name="rs" property="xydm" styleId="xydm"
									style="width:100%" />
							</td>
							<td align="right">
								班级人数：
							</td>
							<td>
								<html:text name="rs" property="bjrs" styleId="bjrs"
									style="width:100%" readonly="true" />
							</td>
						</tr>
						<thead>
							<tr valign="middle">
								<td align="center" colspan="4">
									<b style="font-size:14">联系人信息</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="right">
								姓名：
							</td>
							<td align="left">
								<html:text name="rs" property="lxrxm" styleId="lxrxm"
									maxlength="10" />
							</td>
							<td align="right">
								职务：
							</td>
							<td>
								<html:text name="rs" property="lxrzw" styleId="lxrzw"
									maxlength="10" />
							</td>
						</tr>
						<tr>
							<td align="right">
								手机号码：
							</td>
							<td align="left">
								<html:text name="rs" property="lxrsj" styleId="lxrsj"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="20" />
							</td>
							<td align="right">
								手机短号：
							</td>
							<td>
								<html:text name="rs" property="lxrsjdh" styleId="lxrsjdh"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10" />
							</td>
						</tr>
						<tr>
							<td align="right">
								楼栋名：
							</td>
							<td align="left">
								<html:select name="rs" property="lddm" styleId="ld"
									style="width:100px" onchange="initSsFpFpCsList();chld();">
									<html:option value=""></html:option>
									<html:options collection="ldList" property="lddm"
										labelProperty="ldmc" />
								</html:select>
							</td>
							<td align="right">
								层号：
							</td>
							<td>
								<html:select name="rs" property="cs" styleId="cs"
									style="width:100px" onchange="initQsList();" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="csList" property="cs"
										labelProperty="cs" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								寝室号：
							</td>
							<td align="left">
								<html:select name="rs" property="qsh" styleId="qsh"
									style="width:100px" disabled="true"
									onchange="if(this.value!=''&&document.forms[0].lddm.value!='') document.forms[0].lxrqs.value = document.forms[0].lddm.value+'-'+this.value">
									<html:option value=""></html:option>
									<html:options collection="qsList" property="qsh"
										labelProperty="qsh" />
								</html:select>
							</td>
							<td align="right">
								宿舍编号：
							</td>
							<td>
								<html:text name="rs" property="lxrqs" styleId="lxrqs"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<td align="right">
								寝室电话：
							</td>
							<td>
								<html:text name="rs" property="lxrqsdh" styleId="lxrqsdh"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="20" />
							</td>
							<td align="right">
								电邮地址：
							</td>
							<td align="left">
								<html:text name="rs" property="lxremail" styleId="lxremail"
									maxlength="50" />
							</td>
						</tr>
						<thead>
							<tr valign="middle">
								<td align="center" colspan="4">
									<b style="font-size:14">班级进校后主要成绩和项目建设已具备条件</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="right" colspan="4">
								<html:textarea property="bjcjtj" style="width:100%" rows='5'
									name="rs" onblur="chcd(this,'2000')" />
							</td>
						</tr>
					</table>
				</fieldset>
				<fieldset>
					<legend>
						项目建设方案
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr valign="middle">
								<td align="center" colspan="4">
									<b style="font-size:14">项目建设基本方案、特色及进度</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="right" style="width:15%">
								项目名称：
							</td>
							<td align="left" colspan="3">
								<html:text name="rs" property="xmmc" styleId="xmmc"
									style="width:100%" maxlength="20" />
							</td>
						</tr>
						<tr>
							<td align="right">
								项目特色：
							</td>
							<td align="left" colspan="3">
								<html:textarea property="xmts" style="width:100%" rows='3'
									name="rs" onblur="chcd(this,'200')" />
							</td>
						</tr>
						<tr>
							<td align="right">
								项目建设基本方案：
							</td>
							<td align="left" colspan="3">
								<html:textarea property="xmjsfa" style="width:100%" rows='3'
									name="rs" onblur="chcd(this,'500')" />
							</td>
						</tr>
						<tr>
							<td align="right">
								进度：
							</td>
							<td align="left" colspan="3">
								<html:textarea property="xmjd" style="width:100%" rows='3'
									name="rs" onblur="chcd(this,'200')" />
							</td>
						</tr>
						<thead>
							<tr valign="middle">
								<td align="center" colspan="4">
									<b style="font-size:14">全班同学对方案的认同</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="left" colspan="4">
								<html:textarea property="qbrt" style="width:100%" rows='5'
									name="rs" onblur="chcd(this,'250')" />
							</td>
						</tr>
					</table>
				</fieldset>
				<fieldset>
					<legend>
						项目预期效果与验收要点
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr valign="middle">
								<td align="center" colspan="4">
									<b style="font-size:14">项目预期效果</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="left" colspan="4">
								<html:textarea property="yqxg" style="width:100%" rows='5'
									name="rs" onblur="chcd(this,'250')" />
							</td>
						</tr>
						<thead>
							<tr valign="middle">
								<td align="center" colspan="4">
									<b style="font-size:14">项目验收要点</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="left" colspan="4">
								<html:textarea property="ysyd" style="width:100%" rows='5'
									name="rs" onblur="chcd(this,'500')" />
							</td>
						</tr>
					</table>
				</fieldset>
				<fieldset>
					<legend>
						项目经费预算
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr valign="middle">
								<td align="center" colspan="4">
									<b style="font-size:14">申请学工部资助经费预算</b>
									<logic:equal name="userType" value="stu">
										<input  value="+" title="增加行"
											style="height:18px;width:20px" id="add" onclick="addZzjf();">
										<input  value="-" title="删除行"
											style="height:18px;width:20px" id="del" onclick="delZzjf();">
									</logic:equal>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="left" style="width:25%">
								支出项目
							</td>
							<td align="left" style="width:25%">
								上半年度
							</td>
							<td align="left" style="width:25%">
								下半年度
							</td>
							<td align="left" style="width:25%">
								合计
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<table width="100%" class="tbstyle">
									<tbody width="100%" class="tbstyle" id="zzjf">
										<tr>
											<th align="left" style="width:194px">
												<input type="text" style="width:150px" id="zz_xm0"
													name="zz_xm0" value="" onblur="addZzhj(this.id)"
													; maxlength="20">
											</th>
											<th align="left" style="width:198px">
												<input type="text" style="width:150px" id="zz_sbzz0"
													name="zz_sbzz0" onblur="addZzhj(this.id);"
													onkeyup="value=value.replace(/[^\d]/g,'') " value=""
													onfocus="setValue(this)" maxlength="10">
											</th>
											<th align="left" style="width:198px">
												<input type="text" style="width:150px" id="zz_xbzz0"
													name="zz_xbzz0" onblur="addZzhj(this.id);"
													onkeyup="value=value.replace(/[^\d]/g,'') " value=""
													onfocus="setValue(this)" maxlength="10">
											</th>
											<th align="left">
												<input type="text" style="width:150px" id="zz_hj0"
													name="zz_hj0" value="" readOnly="ture">
											</th>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<td align="left">
								合计
							</td>
							<td align="left">
								<input type="text" id="zz_sbhj" name="zz_sbhj" value=""
									readOnly="ture">
							</td>
							<td align="left" style="width:100px">
								<input type="text" id="zz_xbhj" name="zz_xbhj" value=""
									readOnly="ture">
							</td>
							<td align="left" style="width:100px">
								<input type="text" id="zz_zhj" name="zz_zhj" value=""
									readOnly="ture">
							</td>
						</tr>
						<thead>
							<tr valign="middle">
								<td align="center" colspan="4">
									<b style="font-size:14">班级自有经费预算</b>
									<logic:equal name="userType" value="stu">
										<input  value="+" title="增加行"
											style="height:18px;width:20px" id="add" onclick="addZyjf();">
										<input  value="-" title="删除行"
											style="height:18px;width:20px" id="del" onclick="delZyjf();">
									</logic:equal>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="left" style="width:100px">
								支出项目
							</td>
							<td align="left" style="width:100px">
								上半年度
							</td>
							<td align="left" style="width:100px">
								下半年度
							</td>
							<td align="left" style="width:100px">
								合计
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<table width="100%" class="tbstyle">
									<tbody width="100%" class="tbstyle" id="zyjf">
										<tr>
											<th align="left" style="width:194px">
												<input type="text" style="width:150px" id="zy_xm0"
													name="zy_xm0" value="" onblur="addZyhj(this.id)"
													; maxlength="20">
											</th>
											<th align="left" style="width:198px">
												<input type="text" style="width:150px" id="zy_sbzz0"
													name="zy_sbzz0" onblur="addZyhj(this.id);"
													onkeyup="value=value.replace(/[^\d]/g,'') " value=""
													onfocus="setValue(this)" maxlength="10">
											</th>
											<th align="left" style="width:198px">
												<input type="text" style="width:150px" id="zy_xbzz0"
													name="zy_xbzz0" onblur="addZyhj(this.id);"
													onkeyup="value=value.replace(/[^\d]/g,'') " value=""
													onfocus="setValue(this)" maxlength="10">
											</th>
											<th align="left">
												<input type="text" style="width:150px" id="zy_hj0"
													name="zy_hj0" value="" readOnly="ture">
											</th>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<td align="left">
								合计
							</td>
							<td align="left">
								<input type="text" id="zy_sbhj" name="zy_sbhj" value=""
									readOnly="ture">
							</td>
							<td align="left" style="width:100px">
								<input type="text" id="zy_xbhj" name="zy_xbhj" value=""
									readOnly="ture">
							</td>
							<td align="left" style="width:100px">
								<input type="text" id="zy_zhj" name="zy_zhj" value=""
									readOnly="ture">
							</td>
						</tr>
					</table>
				</fieldset>
				<logic:notEqual name="userType" value="stu">
					<fieldset>
						<legend>
							项目审批
						</legend>
						<table width="100%" class="tbstyle">
							<logic:equal name="userType" value="teacher">
								<thead>
									<tr valign="middle">
										<td align="center" colspan="4">
											<b style="font-size:14">班主任意见</b>
										</td>
									</tr>
								</thead>
								<tr>
									<td align="left" colspan="4">
										<html:textarea property="bzryj" style="width:100%" rows='5'
											name="rs" onblur="chcd(this,'250')" />
									</td>
								</tr>
							</logic:equal>
							<logic:equal name="userType" value="xy">
								<thead>
									<tr valign="middle">
										<td align="center" colspan="4">
											<b style="font-size:14">班主任意见</b>
										</td>
									</tr>
								</thead>
								<tr>
									<td align="left" colspan="4">
										<html:textarea property="bzryj" style="width:100%" rows='5'
											name="rs" onblur="chcd(this,'250')" />
									</td>
								</tr>
								<thead>
									<tr valign="middle">
										<td align="center" colspan="4">
											<b style="font-size:14"><bean:message key="lable.xsgzyxpzxy" />意见</b>
										</td>
									</tr>
								</thead>
								<tr>
									<td align="left" colspan="4">
										<html:textarea property="xyyj" style="width:100%" rows='5'
											name="rs" onblur="chcd(this,'250')" />
									</td>
								</tr>
							</logic:equal>
							<logic:equal name="userType" value="xx">
								<thead>
									<tr valign="middle">
										<td align="center" colspan="4">
											<b style="font-size:14">班主任意见</b>
										</td>
									</tr>
								</thead>
								<tr>
									<td align="left" colspan="4">
										<html:textarea property="bzryj" style="width:100%" rows='5'
											name="rs" onblur="chcd(this,'250')" />
									</td>
								</tr>
								<thead>
									<tr valign="middle">
										<td align="center" colspan="4">
											<b style="font-size:14"><bean:message key="lable.xsgzyxpzxy" />意见</b>
										</td>
									</tr>
								</thead>
								<tr>
									<td align="left" colspan="4">
										<html:textarea property="xyyj" style="width:100%" rows='5'
											name="rs" onblur="chcd(this,'250')" />
									</td>
								</tr>
								<thead>
									<tr valign="middle">
										<td align="center" colspan="4">
											<b style="font-size:14">学工部意见</b>
										</td>
									</tr>
								</thead>
								<tr>
									<td align="left" colspan="4">
										<html:textarea property="xxyj" style="width:100%" rows='5'
											name="rs" onblur="chcd(this,'250')" />
									</td>
								</tr>
							</logic:equal>
							<logic:equal name="userType" value="admin">
								<thead>
									<tr valign="middle">
										<td align="center" colspan="4">
											<b style="font-size:14">班主任意见</b>
										</td>
									</tr>
								</thead>
								<tr>
									<td align="left" colspan="4">
										<html:textarea property="bzryj" style="width:100%" rows='5'
											name="rs" onblur="chcd(this,'250')" />
									</td>
								</tr>
								<thead>
									<tr valign="middle">
										<td align="center" colspan="4">
											<b style="font-size:14"><bean:message key="lable.xsgzyxpzxy" />意见</b>
										</td>
									</tr>
								</thead>
								<tr>
									<td align="left" colspan="4">
										<html:textarea property="xyyj" style="width:100%" rows='5'
											name="rs" onblur="chcd(this,'250')" />
									</td>
								</tr>
								<thead>
									<tr valign="middle">
										<td align="center" colspan="4">
											<b style="font-size:14">学工部意见</b>
										</td>
									</tr>
								</thead>
								<tr>
									<td align="left" colspan="4">
										<html:textarea property="xxyj" style="width:100%" rows='5'
											name="rs" onblur="chcd(this,'250')" />
									</td>
								</tr>
							</logic:equal>
						</table>
					</fieldset>
				</logic:notEqual>
				<div class="buttontool">
					<logic:equal name="userType" value="stu">
						<logic:notEqual name="doType" value="view">
							<logic:equal name="noSh" value="yes">
								<button type="button" class="button2" onclick="xmSb();" style="width:80px"
									id="buttonSave">
									保 存
								</button>
							</logic:equal>
						</logic:notEqual>
					</logic:equal>
					<logic:notEqual name="userType" value="stu">
						<button type="button" class="button2" onclick="xmSh('tg');" style="width:80px"
							id="buttonSave">
							审核通过
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="xmSh('btg');" style="width:80px"
							id="buttonSave">
							审核不通过
						</button>
					</logic:notEqual>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<logic:notEqual name="doType" value="add">
						<button type="button" class="button2"
							onclick="showOpenWindow('/xgxt/bjtsxm.do?method=bjtsxmsbPrint&pk='+$('pkValue').value,680,600);"
							style="width:80px" id="buttonSave">
							打 印
						</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							关 闭
						</button>
					</logic:notEqual>
				</div>
			</logic:notEmpty>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				alert("操作成功!");
				dialogArgumentsQueryChick();
				window.close();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("操作失败!");
			</script>
		</logic:equal>
	</body>
</html>
