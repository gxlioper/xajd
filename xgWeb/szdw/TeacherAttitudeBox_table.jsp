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
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<html:form action="/hdzj_report_save" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：思政队伍-导师其他相关-导师意见工作箱管理表
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" name="pk" property="pk" value="<bean:write name="rs" property="pk"/>" />
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="12" align="center">
								<b>导师意见工作箱管理表</b>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
							<td align="right" colspan="2">
								<bean:message key="lable.xsgzyxpzxy" />:
							</td>
							<td align="left">
							<logic:equal name="userType" value="xy" scope="session">
							<html:select property="xydm" style="width:180px" styleId="xy" disabled="true" onchange="refreshForm('teacherAttitudeBox_updata.do?act=dsyjx')">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
							</html:select>
							</logic:equal>
							<logic:notEqual name="userType" value="xy" scope="session">
							<html:select property="xydm" style="width:180px" styleId="xy" onchange="refreshForm('teacherAttitudeBox_updata.do?act=dsyjx')">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
							</html:select>
							</logic:notEqual>
							</td>
						<td align="right" >
							<font color="red">*</font>职工
						</td>
						<td align="left" colspan="4">	
						<html:select name="rs" property="zxm" style="width:120px" styleId="zxm"  >
									<html:option value=""></html:option>
									<html:options collection="zgList" property="zgh"
										labelProperty="xm" />
								</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<font color="red">*</font>建议标题:
						</td>
						<td align="left" colspan="7">
							<html:text name="rs" property="title" style="width: 680px" styleId="title"/>
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
							<font color="red">*</font>建议内容：
						</td>
						<td colspan="7">
							<html:textarea name='rs' property='gdsyj' style="width:99%" styleId="gdsyj"
								rows='15' />
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button type="button" class="button2"
						onclick="sxjyReportDataDoSave('/xgxt/teacherAttitudeBox_save.do?type=dsyjx','zxm-title-gdsyj')">
						 提 交 
					</button>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("提交成功！");
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("提交失败！");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>