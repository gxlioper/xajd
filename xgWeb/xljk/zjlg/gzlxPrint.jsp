<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<base target="_self">
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/pjpyFunction.js"></script>

		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
		<script language="javascript" >
			function whoscheck(){
				var usertype = document.getElementById("userType").value;
				if(usertype == "stu"){
					document.getElementById("xycljgV").style.display = "none";
					document.getElementById("bzV").style.display = "none";
				}
			 }
		</script>
	</head>
	
	 
	<body bgcolor="#FFFFFF" class="Normal" lang=ZH-CN onload="whoscheck();">
		<html:form action="/zjlg_xljk" method="post">
			
			<div align=center>
				<input type="hidden" id="tableName" name="tableName"
					value="zjlg_gzlxb" />
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope="session"/>" />
				<input type="hidden" name="zyV" id="zyV" />
				<input type="hidden" name="bjV" id="bjV" />
				<h3 align="center">�㽭����ѧ����ίԱ������ϵ��</h3>
				<table width="90%" class="printstyle">
					<tr>
						<td class="Normal" align="right">
							����ίԱ����
						</td>
						<td class="Normal">
							<bean:write name="rs" property="xlwyxm"/>
						</td>
						<td class="Normal" align="right">
							<bean:message key="lable.xsgzyxpzxy" />
						</td>
						<td class="Normal">
							<bean:write name="rs" property="xymc"/>
						</td>
					</tr>
					<tr>
						<td class="Normal" align="right">
							�༶
						</td>
						<td class="Normal">
							<bean:write name="rs" property="bjmc"/>
						</td>
						<td class="Normal" align="right" nowrap="nowrap">
							��ϵ��ʽ
						</td>
						<td class="Normal">
							&nbsp;<bean:write name="rs" property="lxfs"/>
						</td>
					</tr>
					<tr>
						<td class="Normal" align="right" nowrap="nowrap">
							��¼ʱ��
						</td>
						<td class="Normal" align="right" colspan="3">

							&nbsp;<bean:write name="rs" property="jlsd"/>
						</td>
					</tr>
					<tr id="txdtV">
						<td class="Normal" align="right">
							�༶�����
							<br>
							ͬѧ��̬��¼
							<br>
							����������ҳ��
						</td>
						<td class="Normal" colspan="3" style="height: 200px">
								&nbsp;<bean:write name="rs" property="txdt"/>
						</td>
					</tr>
					<tr id="txqkV">
						<td class="Normal" align="right" nowrap="nowrap">
							��Ҫ��ע��
							<br>
							ͬѧ�����¼
						</td>
						<td class="Normal" colspan="3"  style="height: 200px">
								&nbsp;<bean:write name="rs" property="txqk"/>
						</td>
					</tr>
					<tr id="xycljgV">
						<td class="Normal" align="right" nowrap="nowrap" rowspan="2">
							<bean:message key="lable.xsgzyxpzxy" />������
						</td>
						<td class="Normal" colspan="3" style="height: 200px">
								&nbsp;<bean:write name="rs" property="xycljg"/>
						</td>
					</tr>
					<tr>
						<td class="Normal" colspan="2" style="height:">
							<bean:message key="lable.xsgzyxpzxy" />������ָ����ʦ��&nbsp;<bean:write name="rs" property="xyxljkzdls"/>
						</td>
						<td class="Normal" colspan="2" style="height:">
							ʱ�䣺&nbsp;<bean:write name="rs" property="sj"/>
						</td>
					</tr>
					<tr id="bzV">
						<td class="Normal" align="right" rowspan="1">
							�Ƿ���Ҫ��
							<br>
							��У����
							<br>
							����������
						</td>
						<td class="Normal" colspan="3" style="height:" rowspan="2">
							&nbsp;<bean:write name="rs" property="sffk"/>
						</td>
					</tr>
				</table>
			</div>
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
