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

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/XsGyGlLogic.do?method=xsGyWlRy" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置： 公寓管理 - 信息维护 - 外来人员来访登记
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					未找到任何记录！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<center id="rsTable">
						<h4>
							外来人员来访登记表
						</h4>
						<div align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<bean:write name="ldmc" scope="request" />
							<bean:write name="nd" scope="request" />
							<bean:write name="yf" scope="request" />
							<bean:write name="rq" scope="request" />
						</div>
						<table width="99%" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td nowrap>
										来访时间
									</td>
									<td nowrap>
										姓 名
									</td>
									<td nowrap>
										证 件
									</td>
									<td nowrap>
										会 见 人
									</td>
									<td nowrap>
										来 访 事 由
									</td>
									<td nowrap>
										日 期
									</td>
									<td nowrap>
										离开时间
									</td>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr>
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="1">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</center>
					<div class="buttontool" align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="打印" name="button_print"
							onclick="expTab('rsTable','','')">
					</div>
				</fieldset>
			</logic:notEmpty>
		</html:form>
	</body>
</html>		

		
