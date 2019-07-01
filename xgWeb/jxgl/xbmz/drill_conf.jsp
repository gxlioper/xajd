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
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
	var delbm="";
	function addBm(){
	var fromIndx = $("bm").selectedIndex;
	if(fromIndx < 0){
		return false;
	}
	$("qxTList").options[$("qxTList").options.length] = new Option($("bm").options[fromIndx].text,$("bm").options[fromIndx].value);
	$("bm").options[fromIndx] = null;
	if($("bm").options.length > 0){
		$("bm").options[0].selected = true;
		$("delLdBjB").disabled = false;
	}else{		
		$("addLdBjB").disabled = true;
	}
	if($("qxTList").options.length > 0){
		$("qxTList").options[0].selected = true;
	}
}	
function delBm(){
	var toIndx = $("qxTList").selectedIndex;
	if(toIndx < 0){
		return false;
	}
	$("bm").options[$("bm").options.length] = new Option($("qxTList").options[toIndx].text,$("qxTList").options[toIndx].value);
	delbm = delbm+$("qxTList").options[toIndx].value+splitSignOne;
	$("qxTList").options[toIndx] = null;
	if($("qxTList").options.length > 0){
		$("qxTList").options[0].selected = true;
		$("addLdBjB").disabled = false;
	}else{		
		$("delLdBjB").disabled = true;
	}	
	if($("bm").options.length > 0){
		$("bm").options[0].selected = true;
	}
}

function saveXbmzBm(){
	setEleDisable("button");
	for(var i = 0 ; i < $("qxTList").length; i++){
		var tmp = document.createElement("input");
		tmp.type = "hidden";
		tmp.name = "bjdmList";
		tmp.value = $("qxTList").options[i].value;
		document.forms[0].appendChild(tmp);
	}
	refreshForm("jxgljz_xbmz.do?method=xbmz_yhsz&act=save&delbm="+delbm);
}
function setEleDisable(sTagName){
	var subNum = document.getElementsByTagName(sTagName).length;
	for(var i = 0; i < subNum; i++){
		document.getElementsByTagName(sTagName)[i].disabled = true;
	}
}
</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<center>
			<html:form action="/drill_conf" method="post">
				<div class="title">
					<div class="title_img" id="title_m">
						当前位置：军训管理 - 参数设置 - 用户设置
					</div>
				</div>
				<fieldset>
					<legend>
						用户设定
					</legend>
					<table width="80%" class="tbstyle" align="center">
						<thead>
							<tr align="center">
								<td height="25" colspan="5">
									用户设置
								</td>
							</tr>
						</thead>
						<tr>
						<td align="center" width="6%" valign="middle">
							<p>
								部
							</p>
							<p>
								门
							</p>
						</td>
						<td width="40%">
							<html:select name="BmInfo" property="bmmc" styleId="bm"
								size="12" style="width:100% ">
								<html:options collection="bmList" property="bmdm"
									labelProperty="bmmc" />
							</html:select>
						</td>
						<td nowrap width="14%">
							<button type="button" class="button2" style="width:100%" id="addLdBjB"
								onclick="addBm()">
								&gt;&nbsp;&gt;
							</button>
							<br />
							<br />
							<br />
							<button type="button" class="button2" style="width:100%" id="delLdBjB"
								onclick="delBm()">
								&lt;&nbsp;&lt;
							</button>
						</td>
						<td align="center" width="6%" valign="middle">
							<p>
								审
							</p>
							<p>
								核
							</p>
							<p>
								部
							</p>
							<p>
								门
							</p>
						</td>
						<td width="40%">
							<html:select name="BmInfo" property="qxList" size="12"
								 styleId="qxTList" style="width:100% ">
								<html:options collection="qxList" property="bmdm"
									labelProperty="bmmc" />
							</html:select>
						</td>
					</tr>
						<thead>
							<tr>
								<td height="25" colspan="5" align="center">
									<button type="button" class="button2"
										onclick="saveXbmzBm()">
										保存
									</button>
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
				<logic:notEmpty name="result">
					<logic:equal name="result" value="true">
						<script>alert("保存成功!")</script>
					</logic:equal>
					<logic:equal name="result" value="false">
						<script>alert("保存失败!")</script>
					</logic:equal>
				</logic:notEmpty>
			</html:form>
			<logic:present name="ok" scope="request">
				<logic:equal value="ok" name="ok" scope="request">
					<script>alert("保存成功！");</script>
				</logic:equal>
				<logic:equal value="no" name="ok" scope="request">
					<script>alert("保存失败，请重试！");</script>
				</logic:equal>
			</logic:present>
		</center>
	</body>
</html>
