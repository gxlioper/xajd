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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<base target="_self">
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
<script language="javascript">
window.onload = function(){
	if(window.dialogArguments && window.dialogArguments.curr_row){
		$("xmS").innerText = window.dialogArguments.curr_row.cells[1].innerText;
	}else{
		alert("内部错误，请重试！");
		Close();
	}
}
</script>
	<script language="javascript" src="js/function.js"></script>
	<body>
		<html:form action="/data_search" method="post">
		<logic:present name="errMsg">
<script language="javascript">
alert("<bean:write name="errMsg" />");
Close();
</script>
		</logic:present>
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：添加学生备注
				</div>
			</div>
			<fieldset>
				<legend>
					学生备注添加
				</legend>
				<table width="100%" class="tbstyle">
					<tr>
						<td align="right">
							学号：
						</td>
						<td align="left" valign="middle">
							<input type="hidden" name="xh" id="xh" value="<bean:write name="xh" />"><bean:write name="xh" />
						</td>
					</tr>
					<tr>
						<td align="right">
							姓名：
						</td>
						<td align="left" valign="middle">
							<span id="xmS"></span>
						</td>
					</tr>
					<tr>
						<td align="right">
							备注：
						</td>
						<td align="left" valign="middle">
							<textarea name="bz" rows="8" style="width:100%" id="bz"><bean:write name="bz" /></textarea>
						</td>
					</tr>
				</table>
			</fieldset>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" />
			</div>
			<div class="buttontool" id="btn" width="100%">
					<button type="button" class="button2"
						onclick="refreshForm('addStuInfo.do')"
						style="width:80px">
						修 改
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="Close();return false;"
						style="width:80px">
						关 闭
					</button>
			</div>
		</html:form>
	</body>
</html>
