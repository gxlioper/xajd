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
var ry_length = "1";
var zc_length = "1";
function chcd(obj,leng){
	if(obj.value.length > leng){
		alert("该项目最大字数为"+leng+",现已经超过，请确认！！");
		obj.focus();
	}
}
function saveXm(){
	if($("yyssj").value > $("yqyssj").value){
			alert("延期验收时间早于原验收时间，请确认！！！");
			return false;
	}
	if($("sqly").value.length < 500){
		alert("项目的建设详细过程不能少于500字");
		return false;
	}
	refreshForm("bjtsxm.do?method=bjtsxmYqysSq&type=save&pk="+$("pkValue").value);
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
	refreshForm("bjtsxm.do?method=bjtsxmYqysSq&type=save&shzt="+yj+"&pk="+$("pkValue").value);
}
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getBjtsxmDAO.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script language="javascript" src="js/xsgyglFunction.js"></script>
		<html:form action="/viewArmyStu" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：<bean:write name = "title" />
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
				<fieldset>
					<legend>
						
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr valign="middle" >
								<td align="center" colspan="4">
									<b style="font-size:14">基本信息</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="right">
								项目代码：
							</td>
							<td align="left">
								<bean:write name="rs" property="xmdm"/>
							</td>
							<td align="right">
								项目名称：
							</td>
							<td align="left">
								<bean:write name="rs" property="xmmc"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								建设班级：
							</td>
							<td align="left">
								<bean:write name="rs" property="bjmc"/>
							</td>
							<td align="right">
								所属分院（系）：
							</td>
							<td align="left">
								<bean:write name="rs" property="xymc"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								项目责任人：
							</td>
							<td align="left">
								<html:text name="rs" property="xmfzr" styleId="xmfzr"
									 maxlength="10"/>
							</td>
							<td align="right">
								联系电话：
							</td>
							<td align="left">
								<html:text name="rs" property="lxdh" styleId="lxdh"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="20"/>
							</td>
						</tr>
						<thead>
							<tr valign="middle" >
								<td align="center" colspan="4">
									<b style="font-size:14">申请理由（不少于500字）</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="right" colspan="4">
								<html:textarea property="sqly" style="width:100%" rows='8' name="rs"  
								onblur="chcd(this,'2000')"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								原验收时间：
							</td>
							<td align="left">
								<input type="text" id="yyssj" name="yyssj" value="<bean:write name="rs" property="yyssj"/>"
									onblur="dateFormatChg(this)" style="cursor:hand;width:100%"
									onclick="return showCalendar('yyssj','y-mm-dd');">
							</td>
							<td align="right">
								延期验收时间：
							</td>
							<td align="left">
								<input type="text" id="yqyssj" name="yqyssj" value="<bean:write name="rs" property="yqyssj"/>"
									onblur="dateFormatChg(this)" style="cursor:hand;width:100%"
									onclick="return showCalendar('yqyssj','y-mm-dd');">
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
							<tr valign="middle" >
								<td align="center" colspan="4">
									<b style="font-size:14">班主任意见</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="left" colspan="4">
								<html:textarea property="bzryj" style="width:100%" rows='5' name="rs"  
								onblur="chcd(this,'250')"/>
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="userType" value="xy">
						<thead>
							<tr valign="middle" >
								<td align="center" colspan="4">
									<b style="font-size:14">班主任意见</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="left" colspan="4">
								<html:textarea property="bzryj" style="width:100%" rows='5' name="rs"  
								onblur="chcd(this,'250')"/>
							</td>
						</tr>
						<thead>
							<tr valign="middle" >
								<td align="center" colspan="4">
									<b style="font-size:14"><bean:message key="lable.xsgzyxpzxy" />意见</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="left" colspan="4">
								<html:textarea property="xyyj" style="width:100%" rows='5' name="rs"  
								onblur="chcd(this,'250')"/>
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="userType" value="xx">
						<thead>
							<tr valign="middle" >
								<td align="center" colspan="4">
									<b style="font-size:14">班主任意见</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="left" colspan="4">
								<html:textarea property="bzryj" style="width:100%" rows='5' name="rs"  
								onblur="chcd(this,'250')"/>
							</td>
						</tr>
						<thead>
							<tr valign="middle" >
								<td align="center" colspan="4">
									<b style="font-size:14"><bean:message key="lable.xsgzyxpzxy" />意见</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="left" colspan="4">
								<html:textarea property="xyyj" style="width:100%" rows='5' name="rs"  
								onblur="chcd(this,'250')"/>
							</td>
						</tr>
						<thead>
							<tr valign="middle" >
								<td align="center" colspan="4">
									<b style="font-size:14">学工部意见</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="left" colspan="4">
								<html:textarea property="xxyj" style="width:100%" rows='5' name="rs"  
								onblur="chcd(this,'250')"/>
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="userType" value="admin">
						<thead>
							<tr valign="middle" >
								<td align="center" colspan="4">
									<b style="font-size:14">班主任意见</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="left" colspan="4">
								<html:textarea property="bzryj" style="width:100%" rows='5' name="rs"  
								onblur="chcd(this,'250')"/>
							</td>
						</tr>
						<thead>
							<tr valign="middle" >
								<td align="center" colspan="4">
									<b style="font-size:14"><bean:message key="lable.xsgzyxpzxy" />意见</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="left" colspan="4">
								<html:textarea property="xyyj" style="width:100%" rows='5' name="rs"  
								onblur="chcd(this,'250')"/>
							</td>
						</tr>
						<thead>
							<tr valign="middle" >
								<td align="center" colspan="4">
									<b style="font-size:14">学工部意见</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="left" colspan="4">
								<html:textarea property="xxyj" style="width:100%" rows='5' name="rs"  
								onblur="chcd(this,'250')"/>
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
							<button type="button" class="button2"
								onclick="saveXm();"
								style="width:80px" id="buttonSave">
								保 存
							</button>
							</logic:equal>
						</logic:notEqual>
					</logic:equal>
					<logic:notEqual name="userType" value="stu">
						<button type="button" class="button2" onclick="xmSh('tg');" 
							style="width:80px" id="buttonSave">
								审核通过
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="xmSh('btg');" 
							style="width:80px" id="buttonSave">
								审核不通过
						</button>
					</logic:notEqual>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<logic:notEqual name="doType" value="add">
						<button type="button" class="button2"
								onclick="showOpenWindow('bjtsxm.do?method=bjtsxmYqsqPrint&pk='+$('pkValue').value,680,600);"
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
