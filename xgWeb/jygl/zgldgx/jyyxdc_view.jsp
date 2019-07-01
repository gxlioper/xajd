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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<style media="print" type="text/css">
  .brk{
	page-break-after:always;
   }
   .noPrin{display:none}
</style>
	<body>
<html:form action="/yxjzyjs.do" method="post">
		
		<logic:iterate id="rs1" name="rs1">
			<table width="100%" class="tbstyle"  id="rtable">
				<thead>
					<tr>
						<td align="center" colspan="4" height=""><b>ϵ��Ժ����רҵ����¼��</b></td>
					</tr>
				</thead>
				<tr>
					<td align="right">ϵ(Ժ)���ƣ�</td>
					<td colspan="3"><bean:write name="rs1" property="xymc"/>
					</td>
				</tr>
				<tr>
					<td align="right">ϵ(Ժ)���������</td>
					<td align="right" colspan="3"></td>
				</tr>
				<tr>
					<td colspan="1"></td>
					<td colspan="3"><bean:write name="rs1" property="xyjbqk"/>
					</td>
				</tr>
				<tr>
					<td align="right">רҵ���ƣ�</td>
					<td><bean:write name="rs1" property="zymc"/></td>
					<td align="right">������</td>
					<td><bean:write name="rs1" property="rs1"/>
					��</td>
				</tr>
				<tr>
					<td align="right">������Σ�</td>
					<td><bean:write name="rs1" property="pycc"/></td>
					<td align="right">ѧ�ƣ�</td>
					<td><bean:write name="rs1" property="xz"/>��</td>
				</tr>
				<tr>
					<td align="right">ѧλ��</td>
					<td colspan="3"><bean:write name="rs1" property="xw"/>
						</td>

				</tr>
				<tr>
					<td align="right">����Ŀ�꼰��ɫ��</td>
					<td align="right" colspan="3"></td>
				</tr>
				<tr>
					<td colspan="1"></td>
					<td colspan="3"><bean:write name="rs1" property="pymb"/></td>
				</tr>
				<tr>
					<td align="right">��Ҫ�γ̣�</td>
					<td align="right" colspan="3"></td>
				</tr>
				<tr>
					<td colspan="1"></td>
					<td colspan="3"><<bean:write name="rs1" property="zykc"/></td>
				</tr>
				<tr>
					<td align="right">��ҵǰ��������</td>
					<td align="right" colspan="3"></td>
				</tr>
				<tr>
					<td colspan="1"></td>
					<td colspan="3"><bean:write name="rs1" property="jyqj"/></td>
				</tr>
			</table>
		</logic:iterate>
<!--							<button class="button2"-->
<!--								onclick="expTab('rtable','������','')">-->
<!--								�� ӡ �� ��-->
<!--							</button>-->
				<div class='noPrin' align="center">
				<input type='button' class='button2' value='ҳ������'
					onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='��ӡԤ��'
					onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='ֱ�Ӵ�ӡ'
					onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>
		</html:form>
	</body>
</html>
