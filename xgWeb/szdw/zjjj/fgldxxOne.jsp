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
	<body onload="checkWinType();">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/zjjj_xsgzdw" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<logic:notEmpty name="rs">
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="pk" scope="request"/>" />
				<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope = "session"/>" />
				<fieldset>
					<legend>
						校级分管领导信息单个修改	
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								<font color="red">*</font>职工号:
							</td>
							<td align="left">
								<html:text name = "rs" property="zgh" />
							</td>
							<td align="right">
								<font color="red">*</font>姓名:
							</td>
							<td align="left">
								<html:text name = "rs" property="xm" />
							</td>
						</tr>
						<tr>
							<td align="right">
								联系电话:
							</td>
							<td align="left">
								<html:text name = "rs" property="lxdh" />
							</td>
							<td align="right">
								职务:
							</td>
							<td align="left">
	        						<html:select name = "rs" property="zwdm" style="width:230px" styleId="zwdm"> 
	          						<html:option value=""></html:option> 
	          						<html:options collection="zwList" property="zwdm" labelProperty="zwmc" /> 
	        						</html:select>
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								分管事务 
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='fgsw' style="width:99%"
									rows='10' />
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								备注
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='bz' style="width:99%"
									rows='5' />
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">
					<button type="button" class="button2"
						onclick="szsxDataDoSave('zjjj_xsgzdw.do?method=saveFgldxxOne','zgh-xm');"
						style="width:80px" id="buttonSave">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
			</logic:notEmpty>
			<logic:present name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("提交成功！");
    dialogArgumentsQueryChick();
	Close()
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("提交失败！");
    </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
