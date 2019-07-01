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
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<style media="print">
.noprint{
	display:none
}
</style>
	<script language="javascript">
function printZS(){
	var rowsNum = rsTable.rows.length;
	for(i = 1;i<rowsNum;i++){
		cont.innerText = rsTable.rows[i].cells[3].innerText;
		WebBrowser.ExecWB(6,6);
	}
}
</script>
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<body>
		<html:form action="/log_search" method="post">
			<fieldset>
				<legend>
					查 询
				</legend>
				<table width="100%" border="0" class="tbstyle">
					<thead>
						<tr>
							<td nowrap id="cont">
								操作员：
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<table width="100%" id="rsTable" class="tbstyle">
				<thead>
					<tr align="center" style="cursor:hand">
						<logic:iterate id="tit" name="topTr">
							<td id="<bean:write name="tit" property="en"/>"
								onclick="tableSort(this)" nowrap>
								<bean:write name="tit" property="cn" />
							</td>
						</logic:iterate>
					</tr>
				</thead>
				<logic:iterate name="rs" id="s">
					<tr onclick="rowOnClick(this)" style="cursor:hand">
						<td>
							<bean:write name="s" property="行号" />
						</td>
						<td>
							<bean:write name="s" property="RZXH" />
						</td>
						<td>
							<bean:write name="s" property="YHM" />
						</td>
						<td>
							<bean:write name="s" property="YHCZ" />
						</td>
						<td>
							<bean:write name="s" property="CZSJ" />
						</td>
						<td nowrap>
							<bean:write name="s" property="IP" />
						</td>
						<td nowrap>
							<bean:write name="s" property="MAC" />
						</td>
					</tr>
				</logic:iterate>
			</table>
			<div class='noPrin'>
				<input type='button' class='button2' value='打印预览'
					onclick="printZS()">
			</div>
		</html:form>
	</body>
</html>
