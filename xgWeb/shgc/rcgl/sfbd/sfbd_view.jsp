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
	<base target="_self">
	<script language="javascript">
	</script>
	<body>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsfunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/rcgl_sfbd" method="post">
				<table  class="tbstyle" align="center">
					<thead>
						<tr style="height:22px">
							<td colspan="6" align="center">
								<b>学生报到信息</b>
							</td>
						</tr>
					</thead>
					<tr style="height:22px" name="aa" id="a1">
							<td align="right" colspan="2">
								学 号：
							</td>
							<td align="left">
								<html:text  property="xh" styleId="xh"
									onblur="" onkeypress=""  readonly="true"/>
							</td>
							
						<td align="right">
							姓 名 ：
						</td>
						<td align="left" colspan="2">
							<html:text  property="xm" styleId="xm" readonly="true"/>
						</td>
					</tr >
					<tr style="height:22px" name="aa" id="a2">
						<td align="right" colspan="2" readonly="true" >
							性 别：
						</td>
						<td align="left">
							<html:text  property="xb" styleId="xb" readonly="true"/>
						</td>
						<td align="right">
							班 级:
						</td>
						<td align="left" colspan="2">
							<html:text  property="bjmc" styleId="bjmc" readonly="true"/>
						</td>
					</tr>
					<tr style="height:22px" name="aa" id="a3">
						<td align="right" colspan="2">
							学 院：
						</td>
						<td align="left">
							<html:text  property="xymc" styleId="xymc"  readonly="true"/>
						</td>
						<td align="right" >
							是 否 报 到：
						</td>
						<td align="left" colspan="2">
							<html:text  property="sfbd" styleId="sfbd"  readonly="true"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" colspan="2">
							学 年:
						</td>
						<td align="left">
							<html:text property="xn" styleId="xn"  readonly="true"/>
						</td>
						<td align="right" >
							学 期：
						</td>
						<td align="left"  colspan="2">
							<html:text property="xq"  styleId="xq"  readonly="true"/>
						</td>
					</tr>			
				</table>
				<div class="buttontool" align="center">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"  onclick="Close();return false;">
						关 闭
					</button>
				</div>
		</html:form>
	</body>
</html>
