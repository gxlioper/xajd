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
		<title>思政队伍评优</title>
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
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<html:form action="/jxgljz" method="post">
		<div class="title">
				<div class="title_img" id="title_m">
					当前位置：军训管理-军训编制-单个学生军训信息
				</div>
			</div>
			<logic:empty name="rs">
				<div align="center"><font color="red">只有学生才能访问本页面</font></div>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						<title>学生军训信息</title>
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								学号：
							</td>
							<td align="left">
								<bean:write name = "rs" property="xh" scope="request"/>
							</td>
							<td align="right">
								姓名：
							</td>
							<td align="left">
								<bean:write name = "rs" property="xm" scope="request"/>
							</td>
						</tr>
						
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />：
							</td>
							<td align="left">
								<bean:write name = "rs" property="xymc" scope="request"/>
							</td>
							<td align="right">
								年级:
							</td>
							<td align="left">
								<bean:write name = "rs" property="nj" scope="request"/>
							</td>
						</tr>
						
						<tr>
							<td align="right">
								民族名称：
							</td>
							<td align="left">
								<bean:write name = "rs" property="mzmc" scope="request"/>
							</td>
							<td align="right">
								来源地区:
							</td>
							<td align="left">
								<bean:write name = "rs" property="csdm" scope="request"/>
							</td>
						</tr>
						
						<tr>
							<td align="right">
								专业：
							</td>
							<td align="left">
								<bean:write name = "rs" property="zymc" scope="request"/>
							</td>
							<td align="right">
								班级:
							</td>
							<td align="left">
								<bean:write name = "rs" property="bjmc" scope="request"/>
							</td>
						</tr>
						
						<tr>
							<td align="right">
								教官名称：
							</td>
							<td align="left">
								<bean:write name = "rs" property="jgmc" />
							</td>
							<td align="right">
								指导员:
							</td>
							<td align="left">
								<bean:write name = "rs" property="ljzdymc" />
							</td>
						</tr>
						<tr>
							<td align="right">
								营长：
							</td>
							<td align="left">
								<bean:write name = "rs" property="yzmc" />
							</td>
							<td align="right">
								连长:
							</td>
							<td align="left">
								<bean:write name = "rs" property="lzmc" />
							</td>
						</tr>
						<tr>
							<td align="right">
								所属营：
							</td>
							<td align="left">
								<bean:write name = "rs" property="yjmc" />
							</td>
							<td align="right">
								所属连:
							</td>
							<td align="left">
								<bean:write name = "rs" property="ljmc" />
							</td>
						</tr>
						<tr>
							<td align="right">
								性别：
							</td>
							<td align="left">
								<bean:write name = "rs" property="xb" />
							</td>
							<td align="right">
								教导员:
							</td>
							<td align="left">
								<bean:write name = "rs" property="yjjdy" />
							</td>
						</tr>
					</table>
				</fieldset>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
