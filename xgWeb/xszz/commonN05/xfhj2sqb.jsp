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
		function back(){
			document.forms[0].action = "/xgxt/n05_xszz.do?method=xfhj2sq";
			document.forms[0].submit();
		}
	</script>
<body>
	<html:form action="/n05_xszz" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
						<h2>
							<strong> <bean:write name='rs' property="xxmc" />经济困难学生缓交学费申请表</strong>
						</h2>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<br />
					<br />
					<div align="right">
						填表时间：&nbsp;&nbsp;
						<logic:empty name="rs" property="sqsj">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						<logic:notEmpty name="rs" property="sqsj">
							<bean:write name='rs' property="sqsj" />
						</logic:notEmpty>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col" height="80%">
					<table width="100%" class="tbstyle">
						<tr>
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
									<bean:message key="lable.xsgzyxpzxy" />
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<bean:write name='rs' property="xymc" />
								</div>
							</td>
							<td width="13%">
								<div align="center">
									学号
								</div>
							</td>
							<td width="21%">
								<div align="center">
									<bean:write name='rs' property="xh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									性别
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td>
								<div align="center">
									年级
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="nj" />
								</div>
							</td>
							<td>
								<div align="center">
									班别
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="bjmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								&nbsp;&nbsp;缓缴原因：
								<br />
								<logic:empty name='rs' property="hjyy">
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
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name='rs' property="hjyy">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="hjyy" />
								</logic:notEmpty>
								<br />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								&nbsp;&nbsp;缓交学费金额（元）
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="hjxf" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								&nbsp;&nbsp;缓交住宿费金额（元）
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="hjzsf" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								&nbsp;&nbsp;缓交学杂费期限：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<logic:equal name="rs" property="hjxzfqx" value="三个月">
									√
								</logic:equal>
								<logic:notEqual name="rs" property="hjxzfqx" value="三个月">
									1
								</logic:notEqual>
								、三个月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<logic:equal name="rs" property="hjxzfqx" value="半年">
									√
								</logic:equal>
								<logic:notEqual name="rs" property="hjxzfqx" value="半年">
									2
								</logic:notEqual>
								、半年
							</td>
						</tr>
						<tr>
							<td colspan="2">
								&nbsp;&nbsp;是否获得国家助学贷款
							</td>
							<td colspan="2">
								&nbsp;&nbsp;是&nbsp;&nbsp;&nbsp;&nbsp;
								<logic:equal name="rs" property="sfhdgjzxdk" value="是">
									√
								</logic:equal>
							</td>
							<td colspan="2">
								&nbsp;&nbsp;否&nbsp;&nbsp;&nbsp;&nbsp;
								<logic:equal name="rs" property="sfhdgjzxdk" value="否">
									√
								</logic:equal>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								&nbsp;&nbsp;有无申请国家助学贷款
							</td>
							<td colspan="2">
								&nbsp;&nbsp;是&nbsp;&nbsp;&nbsp;&nbsp;
								<logic:equal name="rs" property="ywsqgjzxdk" value="是">
									√
								</logic:equal>
							</td>
							<td colspan="2">
								&nbsp;&nbsp;否&nbsp;&nbsp;&nbsp;&nbsp;
								<logic:equal name="rs" property="ywsqgjzxdk" value="否">
									√
								</logic:equal>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />意见：
								<br />
								<logic:empty name='rs' property="xyshyj">
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name='rs' property="xyshyj">
									<bean:write name='rs' property="xyshyj" />
								</logic:notEmpty>
								<br />
								<div align="right">
									签&nbsp;&nbsp;&nbsp;&nbsp;章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								&nbsp;&nbsp;学生处意见：
								<br />
								<logic:empty name='rs' property="xxshyj">
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name='rs' property="xxshyj">
									<bean:write name='rs' property="xxshyj" />
								</logic:notEmpty>
								<br />
								<br />
								<div align="right">
									签&nbsp;&nbsp;&nbsp;&nbsp;章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div align="left">
						&nbsp;&nbsp;注：学生先将此表交<bean:message key="lable.xsgzyxpzxy" />加意见，再由<bean:message key="lable.xsgzyxpzxy" />收齐统一交学生处。
					</div>
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center">
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','')" />
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input  value="返回" onclick="back();" />
	</div>
</body>
</html:html>
