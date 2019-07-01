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
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
							<h4>
						<strong>
								<bean:write name='rs' property="nd" />
								&nbsp;年度开发区慈善总会“金秋助学”救助学生登记表
						</strong>
							</h4>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td colspan="2" scope="col">
								<div align="center">
									姓名
								</div>
							</td>
							<td colspan="2" scope="col">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td scope="col" width="13%">
								<div align="center">
									性别
								</div>
							</td>
							<td scope="col" width="10%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td colspan="2" scope="col">
								<div align="center">
									身份证号
								</div>
							</td>
							<td colspan="3" scope="col">
								<div align="center">
									<bean:write name='rs' property="sfzh" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									入学时间
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="rxny" />
								</div>
							</td>
							<td>
								<div align="center">
									学习专业
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="zymc" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									政治面貌
								</div>
							</td>
							<td width="19%">
								<div align="center">
									<bean:write name='rs' property="zzmmmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									家庭住址
								</div>
							</td>
							<td colspan="3">
								<bean:write name='rs' property="jtzz" />
							</td>
							<td colspan="2">
								<div align="center">
									家庭人口数
								</div>
							</td>
							<td width="9%">
								<div align="center">
									<bean:write name='rs' property="jtrks" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									联系电话
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="lxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									申请理由
									<br />
									(家庭情况)
								</div>
							</td>
							<td colspan="9">
								<br />
								<logic:empty name="rs" property="sqly">
								<br /><br /><br /><br /><br /><br /><br />
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly">
								<bean:write name='rs' property="sqly" />
								</logic:notEmpty>
								<br />
							</td>
						</tr>
						<tr>
							<td scope="row" width="5%">
								<div align="center">
									系
									<br />
									行
									<br />
									政
									<br />
									意
									<br />
									见
								</div>
							</td>
							<td colspan="2">
								<br />
								<br />
								<logic:empty name="rs" property="xyshyj">
								<br /><br />
								</logic:empty>
								<logic:notEmpty name="rs" property="xyshyj">
								<bean:write name='rs' property="xyshyj" />
								</logic:notEmpty>
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<div align="center">
									签章&nbsp;&nbsp;年&nbsp;月&nbsp;日
								</div>
							</td>
							<td width="5%">
								<div align="center">
									系
									<br />
									党
									<br />
									组
									<br />
									织
									<br />
									意
									<br />
									见
								</div>
							</td>
							<td colspan="2">
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<div align="center">
									签章&nbsp;&nbsp;年&nbsp;月&nbsp;日
								</div>
							</td>
							<td width="5%">
								<div align="center">
									院
									<br />
									校
									<br />
									行
									<br />
									政
									<br />
									意
									<br />
									见
								</div>
							</td>
							<td colspan="2">
								<br />
								<br />
								<logic:empty name="rs" property="xxshyj">
								<br /><br />
								</logic:empty>
								<logic:notEmpty name="rs" property="xxshyj">
								<bean:write name='rs' property="xxshyj" />
								</logic:notEmpty>
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<div align="center">
									签章&nbsp;&nbsp;年&nbsp;月&nbsp;日
								</div>
							</td>
							<td width="5%">
								<div align="center">
									院
									<br />
									校
									<br />
									党
									<br />
									组
									<br />
									织
									<br />
									意
									<br />
									见
								</div>
							</td>
							<td width="19%">
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<div align="center">
									签章&nbsp;&nbsp;年&nbsp;月&nbsp;日
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									开发区高教管委会党委意见
								</div>
							</td>
							<td colspan="4">
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<div align="right">
									盖章&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									开发区慈善总会意见
								</div>
							</td>
							<td colspan="3">
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<div align="right">
									盖章&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;
								</div>
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
