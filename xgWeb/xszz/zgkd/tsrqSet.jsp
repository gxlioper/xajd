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
<html:html>
<base target="_self" />
<head>


	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function yz(){
			var tsrqdm = document.getElementById('tsrqdm').value;
			if((tsrqdm == null) || (tsrqdm == "")){
				alert("请选择特殊人群!");
				return false;
			}
			document.forms[0].action = "/xgxt/zgkydx_xszz.do?method=tsrqSave";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 基础数据维护 - 特殊人群设置
		</div>
	</div>
	<html:form action="zgkydx_xszz.do?method=tsrqSet" method="post">
		<input type="hidden" id="pkVal" name="pkVal"
			value="<bean:write name="pkVal" />">
		<logic:present name="ok">
			<logic:match value="ok" name="ok">
				<script language="javascript">
	         	alert("设置完成！");
	         	</script>
			</logic:match>
		</logic:present>
		<table class="tbstyle" width="100%">
			<tr>
				<td colspan="2" align="center">
					请选择
				</td>
			</tr>
			<tr>
				<td width="40%" align="right">
					特殊人群
				</td>
				<td width="60%">
					<html:select name="rs" property="tsrqdm" style="width:100%" >
						<html:options collection="tsrqList" property="dm"
							labelProperty="mc" />
					</html:select>
				</td>
			</tr>
		</table>
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
			<button type="button" class="button2" onClick="yz();" style="width:80px">
				提 交
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" class="button2"
				onclick="Close();window.dialogArguments.document.getElementById('search_go').click();"
				style="width:80px" id="buttonClose">
				关 闭
			</button>
		</div>

	</html:form>
</body>
</html:html>
