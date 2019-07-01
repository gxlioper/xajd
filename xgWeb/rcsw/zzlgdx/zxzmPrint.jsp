<%@ page language="java" contentType="text/html;charset=GBK"%>

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
<html:html>
<base target="_self">
<head>
	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" http-equiv="no-cache" />
	<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
	<meta http-equiv="Expires" http-equiv="0" />
	<meta name="Copyright" content="正方软件 zfsoft" />
</head>
<%
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/xszzFunction.js"></script>
<script language="javascript">
</script>
<body>
	<html:form action="/zzlgdx_rcsw" method="post">
		<table width="70%" border="0" id="theTable" align="center">
			<tr>
				<td scope="col" align="center">
						<h3> <bean:write name='rs' property="xxmc" />学生在校表现及文化程度鉴定表</h3>
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr height="40px">
							<td width="13%">
								<div align="center">
									姓名
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="13%">
								<div align="center">
									性别
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td width="13%">
								<div align="center">
									身份证号
								</div>
							</td>
							<td width="21%">
								<div align="center">
									<bean:write name='rs' property="sfzh" />
								</div>
							</td>
						</tr>
						<tr height="40px">
							<td>
								<div align="center">
									学号
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="xh" />
								</div>
							</td>
							<td>
								<div align="center">
									民族
								</div>
							</td>
							<td>
								<bean:write name='rs' property="mzmc" />
							</td>
							<td>
								<div align="center">
									入校时间
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="rxrq" />
								</div>
							</td>
						</tr>
						<tr height="40px">
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="xymc" />
								</div>
							</td>
							<td>
								<div align="center">
									班级
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="bjmc" />
								</div>
							</td>
						</tr>
						<tr height="300px">
							<td colspan="6" valign="top" >
								&nbsp;在校表现及文化程度鉴定情况：
								<br /><br />
								<logic:empty name='rs' property="zxbx">
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name='rs' property="zxbx">
									<bean:write name='rs' property="zxbx" />
								</logic:notEmpty>
								<br />
								<div style="margin-top: 250px;margin-right:5px">
								<div align="right">
									鉴定人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									(<bean:message key="lable.xsgzyxpzxy" />公章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								</div>
								<br />
							</td>
						</tr>
						<tr height="80px">
							<td>
								<div align="center">
								<br /><br />
									学&nbsp;&nbsp;校
									<br /><br />
									意&nbsp;&nbsp;见
									<br /><br />
								</div>
							</td>
							<td colspan="5">
								<br />
								<br />
								<br />
								&nbsp;&nbsp;&nbsp;&nbsp;以上所述情况属实，同意<bean:message key="lable.xsgzyxpzxy" />对该生在校表现及文化程度的鉴定
								<br />
								<br />
								<br />
								<br />
								<div align="right">
									(公章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<br />
								<br />
								<br />
								<div align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<br />
								<br />
								<br />
								<br />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center">
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','')" />
	</div>
</body>
</html:html>
