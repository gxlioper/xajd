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
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />		
	<base target="_self">
	<body style="overflow:hidden" onload="subWinOnLoad()">
		<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzDao.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/sxjyFunction.js"></script>
		<script type='text/javascript' src='js/xtwh/codeYz.js'></script>

		<html:form action="/codeConf" method="post">
			<div class="title">
				<div class="title_img" id="title_m"></div>
			</div>
			<input type="hidden" name="key" id="key" value="key" />
			<input type="hidden" name="tName" id="tName" value="tName" />
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }" />
			<input type="hidden" name="codeType" id="codeType" value="codeType" />
			<input type="hidden" name="tmpSel_1" id="tmpsel_1" value="tmpsel_1" />
			<input type="hidden" name="tmpSel_2" id="tmpsel_2" value="tmpsel_2" />
			<input type="hidden" name="tmpSel_3" id="tmpsel_3" value="tmpsel_3" />
			<input type="hidden" name="tmpSel_4" id="tmpsel_4" value="tmpsel_4" />
			<input type="hidden" name="tmpSel_5" id="tmpsel_5" value="tmpsel_5" />
			<input type="hidden" name="sel2V" id="sel2V" value="" />
			<input type="hidden" name="sel3V" id="sel3V" value="" />
			<fieldset>
				<legend>
					基础代码维护
				</legend>
				<div id="tmpdiv">
				</div>
			</fieldset>
			<div class="buttontool" align="center">
				<button type="button" class="button2" onclick="if(codeYz()){chkCodeConf(this)};">
					&nbsp;&nbsp;保&nbsp;&nbsp;存&nbsp;&nbsp;
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;">
					&nbsp;&nbsp;关&nbsp;&nbsp;闭&nbsp;&nbsp;
				</button>
			</div>
			<logic:notEmpty name="result">
				<logic:equal value="NO" name="result">
					<script language="javascript">
		alert("操作失败！");
		</script>
				</logic:equal>
				<logic:equal value="OK" name="result">
					<script language="javascript">
		alert("操作成功！");
		</script>
				</logic:equal>
				<script language="javascript">
			Close();
			var tN = window.dialogArguments.document.forms[0].tname.value;
			window.dialogArguments.document.getElementById(tN).click();
		</script>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
