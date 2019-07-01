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
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/xsgbForNblg" method="post">
			<input type="hidden" id="method" name="method"
				value="xsgbsq" />
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<logic:empty name="rs">
				<div align="center"><font color="red"><bean:write name = "message" /></font></div>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						学生组织干部申请
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								<font color="red">*</font>学号：
							</td>
							<td align="left">
								<html:text name='rs' property="xh" readonly="readonly"
									styleId="xh"/>
							</td>
							<td align="right">
								姓名：
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" readonly = "true"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								性别：
							</td>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" readonly = "true" />
							</td>
							<td align="right">
								年级：
							</td>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj"  readonly = "true" />
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />：
							</td>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" readonly = "true"/>
							</td>
							<td align="right">
								专业：
							</td>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" readonly = "true"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								班级：
							</td>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" readonly = "true"/>
							</td>
							<td align="right">
								组织所属部门:
							</td>
							<td align="left">
								<html:select property="bmdm" style="width:230px" styleId="bmdm" onchange="refreshForm('/xgxt/xsgbForNblg.do')"> 
	          					<html:option value=""></html:option> 
	          					<html:options collection="bmList" property="bmdm" labelProperty="bmmc" /> 
	          					</html:select> 
							</td>
						</tr>
						<tr>
							<td align="right">
								组织名称:
							</td>
							<td align="left">
								<html:select property="zzdm" style="width:230px" styleId="zzdm" onchange="refreshForm('/xgxt/xsgbForNblg.do')"> 
	          					<html:option value=""></html:option> 
	          					<html:options collection="xszzList" property="zzdm" labelProperty="zzmc" /> 
	          					</html:select> 
							</td>
							<td align="right">
								可申请学生干部:
							</td>
							<td align="left">
								<html:select property="bgbdm" style="width:230px" styleId="bgbdm"> 
	          					<html:option value=""></html:option> 
	          					<html:options collection="xsgbList" property="bgbdm" labelProperty="bgbmc" /> 
	          					</html:select> 
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								已经担任职务
							</td>
							<td colspan="3">
								<logic:iterate id="v" name="yrxsgbList" offset="0">
											&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="v" /></br>
								</logic:iterate>
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">
					<button class="button2"
						onclick="szsxDataDoSave('xsgbForNblg.do?method=saveXsgbsqOne','xh-bgbdm');"
						style="width:80px" id="buttonSave">
						申　请
					</button>
				</div>
			</logic:notEmpty>
			<logic:present name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("申请成功！");
	Close()
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("申请失败！");
    </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
