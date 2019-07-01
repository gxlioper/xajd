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
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
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
</script>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var user = document.getElementById('user').value;
			var xxsh = document.getElementById('xxsh').value;
			var xysh = document.getElementById('xysh').value;
			var fdyshyj = document.getElementById('fdyshyj').value;
			var xyshyj = document.getElementById('xyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			if((("贫困生" == xysh)||("特困生" == xysh)) && (user == "fdy")){
				alert("学校审核已通过，不能再修改审核结果!");
	          	return false;
			}
			if((("贫困生" == xxsh)||("特困生" == xxsh)) && (user == "xy")){
				alert("学校审核已通过，不能再修改审核结果!");
	          	return false;
			}
			if(fdyshyj != null){
	         	if(fdyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("辅导员审核意见不能大于200个字符！");
	          		 return false;
	       		 }
	       	}
	       	if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />审核意见不能大于200个字符！");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("学校审核意见不能大于200个字符！");
	          		 return false;
	       		 }
	       	}
			refreshForm('/xgxt/csmz_xszz.do?method=gjjxjshXxxx&actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 - 国家奖学金审核 - 单个审核
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName" value="<bean:write name="tName" />" />
			<input type="hidden" name="xxsh" value="<bean:write name='rs' property='xxsh' />" />
			<input type="hidden" name="xysh" value="<bean:write name='rs' property='xysh' />" />
			<input type="hidden" name="user" value="<bean:write name="user" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td scope="row" width="16%">
						<div align="center">
							学年
						</div>
					</td>
					<td width="34%">
						<bean:write name='rs' property="xn" />
					</td>
					<td width="16%">
						<div align="center">
							申请时间
						</div>
					</td>
					<td width="34%">
						<bean:write name='rs' property="sqsj" />
					</td>
				</tr>
				<tr>
					<td align="center">
						学号
					</td>
					<td align="left">
						<bean:write name='rs' property="xh" />
					</td>
					<td scope="col">
						<div align="center">
							姓名
						</div>
					</td>
					<td scope="col">
						<bean:write name='rs' property="xm" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							性别
						</div>
					</td>
					<td>
						<bean:write name='rs' property="xb" />
					</td>
					<td>
						<div align="center">
							身份证号
						</div>
					</td>
					<td>
						<bean:write name='rs' property="sfzh" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td>
						<bean:write name='rs' property="xymc" />
					</td>
					<td>
						<div align="center">
							专业
						</div>
					</td>
					<td>
						<bean:write name='rs' property="zymc" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							班级
						</div>
					</td>
					<td>
						<bean:write name='rs' property="bjmc" />
					</td>
					<td>
						<div align="center">
							年级
						</div>
					</td>
					<td>
						<bean:write name='rs' property="nj" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							入学年月
						</div>
					</td>
					<td>
						<bean:write name='rs' property="rxny" />
					</td>
					<td>
						<div align="center">
							出生年月
						</div>
					</td>
					<td>
						<bean:write name='rs' property="csny" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							民族
						</div>
					</td>
					<td>
						<bean:write name='rs' property="mzmc" />
					</td>
					<td>
						<div align="center">
							政治面貌
						</div>
					</td>
					<td>
						<bean:write name='rs' property="zzmmmc" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							联系电话
						</div>
					</td>
					<td>
						<bean:write name='rs' property="lxdh" />
					</td>
					<td>
						<div align="center">
							院级奖励(项)
						</div>
					</td>
					<td>
						<bean:write name='rs' property="yjjx" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							校级奖励(项)
						</div>
					</td>
					<td>
						<bean:write name='rs' property="xjjx" />
					</td>
					<td>
						<div align="center">
							省级以上奖励(项)
						</div>
					</td>
					<td>
						<bean:write name='rs' property="sjjx" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							主要奖项
						</div>
					</td>
					<td colspan="3">
						<bean:write name='rs' property="zyjx" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							本学年必修<br />课程(门)
						</div>
					</td>
					<td>
						<bean:write name='rs' property="gxnbxkcs" />
					</td>
					<td>
						<div align="center">
							优秀(门)
						</div>
					</td>
					<td>
						<bean:write name='rs' property="yxkc" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							良好(门)
						</div>
					</td>
					<td>
						<bean:write name='rs' property="lhkc" />
					</td>
					<td>
						<div align="center">
							成绩排名(专业或年级)<br />(名次/总人数)
						</div>
					</td>
					<td>
						<bean:write name='rs' property="cjpm" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							综合考评成绩(分)
						</div>
					</td>
					<td>
						<bean:write name='rs' property="zhkpcj" />
					</td>
					<td>
						<div align="center">
							综合考评排名<br />(名次/总人数)
						</div>
					</td>
					<td>
						<bean:write name='rs' property="zhkppm" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							申请理由<br />
							(全面反映德智体美情况)
						</div>
					</td>
					<td colspan="3">
						<bean:write name='rs' property="sqly" />
					</td>
				</tr>
				<logic:equal name="user" value="xy">
					<tr>
						<td>
							<div align="center">
								辅导员审核
							</div>
						</td>
						<td>
							<bean:write name="rs" property="fdysh" />
						</td>
						<td colspan="2">
							<div align="center">
								&nbsp;
							</div>
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="user" value="xx">
					<tr>
						<td>
							<div align="center">
								辅导员审核
							</div>
						</td>
						<td>
							<bean:write name="rs" property="fdysh" />
						</td>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />审核
							</div>
						</td>
						<td>
							<bean:write name="rs" property="xysh" />
						</td>
					</tr>
				</logic:equal>
				<tr>
					<td>
						<div align="center">
							审核结果
						</div>
					</td>
					<td>
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<logic:equal name="user" value="fdy">
				<tr>
					<td scope="row">
						<div align="center">
							辅导员审核意见
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="fdyshyj" rows='3' style="width:100%" onblur="yzdx(this,'fdyshyj')"/>
						<input type="hidden" id="fdyshyj" name="fdyshyj" value="">
						<input type="hidden" name="xyshyj" value="<bean:write name='rs' property='xyshyj' />" />
						<input type="hidden" name="xxshyj" value="<bean:write name='rs' property='xxshyj' />" />
					</td>
				</tr>
				</logic:equal>
				<logic:equal name="user" value="xy">
				<tr>
					<td scope="row">
						<div align="center">
							辅导员审核意见
						</div>
					</td>
					<td colspan="3">
						<bean:write name='rs' property='fdyshyj' />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />审核意见
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="xyshyj" rows='3' style="width:100%" onblur="yzdx(this,'xyshyj')"/>
						<input type="hidden" id="xyshyj" name="xyshyj" value="">
						<input type="hidden" name="fdyshyj" value="<bean:write name='rs' property='fdyshyj' />" />
						<input type="hidden" name="xxshyj" value="<bean:write name='rs' property='xxshyj' />" />
					</td>
				</tr>
				</logic:equal>
				<logic:equal name="user" value="xx">
				<tr>
					<td scope="row">
						<div align="center">
							辅导员审核意见
						</div>
					</td>
					<td colspan="3">
						<bean:write name='rs' property='fdyshyj' />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />审核意见
						</div>
					</td>
					<td colspan="3">
						<bean:write name='rs' property='xyshyj' />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							学校审核意见
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="xxshyj" rows='3' style="width:100%" onblur="yzdx(this,'xxshyj')"/>
						<input type="hidden" id="xxshyj" name="xxshyj" value="">
						<input type="hidden" name="fdyshyj" value="<bean:write name='rs' property='fdyshyj' />" />
						<input type="hidden" name="xyshyj" value="<bean:write name='rs' property='xyshyj' />" />
					</td>
				</tr>
				</logic:equal>
			</table>
			<div class="buttontool" align="center">
				<button class="button2" onclick="yz()" style="width:80px"
					id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
	</body>
</html>
