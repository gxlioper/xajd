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
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/qgzxFunction.js"></script>
		<html:form action="/data_search" method="post">
			<input type="hidden" id="realTable" value="${realTable}" />
			<input type="hidden" id="tableName" value="${tableName}" />
			<input type="hidden" id="pk" value="${pk}" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：助学贷款 - 申请 - 国家助学贷款申请结果查询
				</div>
			</div>
			<logic:notPresent name="showbjlh">
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="center">
								学号：
								<bean:write name="userName" scope="session" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								姓名：
								<bean:write name="userNameReal" scope="session" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
					</thead>
				</table>
			</logic:notPresent>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					未找到任何记录！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>

					</legend>
					<table width="100%" id="rsTable" class="tbstyle">

						
							<thead>
								<tr>
									<td align="center" colspan="10">
										学生信息
									</td>
								</tr>
								<tr>
									<td align="center">
										学号
									</td>
									<td align="center">
										学年
									</td>
									<td align="center">
										姓名
									</td>
									<td align="center">
										性别
									</td>
									<td align="center">
										班级
									</td>
									<td align="center">
										贷款金额
									</td>
									<td align="center">
										还款方式
									</td>
									<td align="center">
										辅导员审核
									</td>
									<td align="center">
										<bean:message key="lable.xsgzyxpzxy" />审核
									</td>
									<td align="center">
										学校审核
									</td>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
								<logic:iterate id="v" name="s">
									<td align="center">
										${v }
									</td>
								</logic:iterate>							
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
<%--				<div class="buttontool">--%>
<%----%>
<%--					&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--					<button type="button" class="button2" onclick="dataExport()" style="width:80px">--%>
<%--						导出数据--%>
<%--					</button>--%>
<%--				</div>--%>
		</html:form>
	</body>
</html>
