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
		<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>
	<body onload="pjpy_initCheck()">
		<html:form action="/zjlgPjpy" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：党团建设 - 业余党校 - 审核 - 信息查看
				</div>
			</div>

			<table width="99%" id="rsT" class="tbstyle">
				<thead>
					<div align="right">
						<logic:equal value="up" name="view">
							<input  id="up" value="上一条" disabled="true">
						</logic:equal>
						<logic:notEqual value="up" name="view">
							<input  id="up" value="上一条"
								onclick="showTips('刷新数据中，请稍候...');pjpy_ChangeRecord('up','/xgxt/jhzyYydx.do?method=jhzyYydxView');">
						</logic:notEqual>
						&nbsp; &nbsp;
						<logic:equal value="next" name="view">
							<input  id="next" value="下一条" disabled="true">
						</logic:equal>
						<logic:notEqual value="next" name="view">
							<input  id="next" value="下一条"
								onclick="showTips('刷新数据中，请稍候...');pjpy_ChangeRecord('next','/xgxt/jhzyYydx.do?method=jhzyYydxView');">
						</logic:notEqual>
						&nbsp; &nbsp;&nbsp; &nbsp;
						<logic:equal value="true" name="sel">
							<input type="checkbox" id="selected" onclick="pjpy_Shot(this);"
								checked="true" />&nbsp;选中
					    </logic:equal>
						<logic:notEqual value="true" name="sel">
							<input type="checkbox" id="selected" onclick="pjpy_Shot(this);" />&nbsp;选中
					    </logic:notEqual>
					</div>
					<tr style="height:22px">
						<td colspan="4" align="center">
							<b>业余党校申请信息</b>
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right" style="width: 10%">
						学号：
					</td>
					<td align="left">
						<bean:write name='rs' property="xh" />
						<input type="hidden" name = "xh" id="xh" value="<bean:write name='rs' property="xh" />" />
					</td>
					<td align="right" style="width: 10%">
						学年：
					</td>
					<td align="left" style="width: 40%">
						<html:select name="rs" property="xn" disabled="true">
							<html:options collection="xnList" property="xn"
								labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						姓名：
					</td>
					<td align="left">
						<bean:write name='rs' property="xm" />
					</td>
					<td align="right">
						学期：
					</td>
					<td align="left" style="width: 40%">
						<html:select name="rs" property="xq" disabled="true">
							<html:options collection="xqList" property="xqdm"
								labelProperty="xqmc" />
						</html:select>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						性别：
					</td>
					<td align="left">
						<bean:write name='rs' property="xb" />
					</td>
					<td align="right">
						<font color="red">*</font>期届：
					</td>
					<td align="left">
						<bean:write name='rs' property="qj" />期
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						年级：
					</td>
					<td align="left">
						<bean:write name='rs' property="nj" />
					</td>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<bean:write name='rs' property="xymc" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						专业：
					</td>
					<td align="left">
						<bean:write name='rs' property="zymc" />
					</td>
					<td align="right">
						班级：
					</td>
					<td align="left">
						<bean:write name='rs' property="bjmc" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						申请时间：
					</td>
					<td align="left">
						<bean:write name='rs' property="lrrq" />
					</td>
					<td align="right">
						最近入党申请时间：
					</td>
					<td align="left">
						<bean:write name='rs' property="djsqsj" />
					</td>
				</tr>
				<tr>
					<td scope="row" align="right" width="15%">
							学年-学期
					</td>
					<td align="left">
							成绩排名(班级)(名次/总人数)
					</td>	
					<td align="right" width="15%">
						综合考评成绩(分)
					</td>
					<td align="left">
						综合考评排名(班级)(名次/总人数) 
					</td>
				</tr>
				<logic:iterate id="cj" name = "rsCjList">
					<tr>
					<td align="right">
						<bean:write name ="cj" property="xn"/>/<bean:write name ="cj" property="xqmc"/>
					</td>
					<td align="left">
						<bean:write name ="cj" property="zypm"/>/<bean:write name ="cj" property="num"/>
					</td>
					<td align="right">
						<bean:write name ="cj" property="zhf"/>
					</td>
					<td align="left">
						<bean:write name ="cj" property="zhpm"/>/<bean:write name ="cj" property="num"/>
					</td>
				</tr>
				</logic:iterate>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />审核
					</td>
					<td align="left">
						<bean:write name='rs' property="xysh" />
					</td>
					<td align="right">
						学校审核
					</td>
					<td align="left">
						<bean:write name='rs' property="xxsh" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						申请理由：
					</td>
					<td colspan="3" align="left">
						<bean:write name='rs' property="sqly" />
					</td>
				</tr>
				</table>
			<div class="buttontool" align="center">

				<button type="button" class="button2" id="buttonClose" onclick="Close();return false;">
					关 闭
				</button>
				<button type="button" id="btn_cj" class="button2" onclick="showTopWin('ahjg_xscjb.do?xh='+document.getElementById('xh').value,'500','400')">
							学 生 成 绩
				</button>
				&nbsp;&nbsp;
			</div>
		</html:form>
	</body>
</html>
