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
<html:html locale="true">
<base target="_self" />
<head>
	<title><bean:message key="lable.title" />
	</title>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta name="Copyright" content="正方软件 zfsoft" />
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script type="text/javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/xljkFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/XljkDWR.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
</head>
<body>
	<html:form action="/xljk_zgmsxy">
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置：心理健康 - 心理咨询 - 咨询师信息 - 增加咨询师资源
			</div>
		</div>	
		<input type="hidden" name="bh" value="<bean:write name="rs" property="bh"/>" id="bh">
		<table class="tbstyle" style="width:100%;" align="center" id="tab">
			<tr>
				<td align="right">
					<font color="red">*</font>星期几:
				</td>
				<td>
					<html:text property="xq" name="rs" styleId="xq"/>
				</td>
			</tr>
			<tr>	
				<td align="right">
					<font color="red">*</font>时间段:
				</td>
				<td>
					<html:text property="sjd" name="rs" styleId="sjd"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					<font color="red">*</font>地点:
				</td>
				<td><html:text property="dd" name="rs" styleId="dd"/>
				</td>
			</tr>
		</table>
		<div id="tmpdiv"></div>
		<div class="buttontool" align=center>
			<logic:equal value="modi" name="rs" property="doType">
				<button class="button2" style="width:80px"
				onclick="modiSaveZxszy('xljk_zgmsxy.do?method=zxszypre&doType=add')">
					修改
				</button>&nbsp;&nbsp;&nbsp;&nbsp;
			</logic:equal>
			<logic:empty name="rs" property="doType">
				<button class="button2" onclick="isZxszyExist()" style="width:80px">
					保存
				</button>&nbsp;&nbsp;&nbsp;&nbsp;
			</logic:empty>
			<button class="button2" onclick="Close();return false;" style="width:80px">
				关闭
			</button>
		</div>
		<logic:notEmpty name="ok">
			<logic:equal name="ok" value="ok">
				<script>
					alert("保存成功!");
					window.dialogArguments.document.getElementById("refreshPage").click();
					Close();
				</script>
			</logic:equal>
			<logic:equal name="ok" value="no">
				<script>
					alert("保存失败!");
					document.getElementById("tmpdiv").innerHTML = "";	
				</script>
			</logic:equal>
		</logic:notEmpty>
	</html:form>
</body>
</html:html>
