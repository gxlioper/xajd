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

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<base target="_self" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>	
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
<style media="print" type="text/css">
  .brk{
	page-break-after:always;
   }
   .noPrin{display:none}
</style>		
	<body bgcolor="#FFFFFF" class="Normal" lang=ZH-CN>
		<div class=Section1 style='layout-grid:15.6pt' align="center">
		<br><br><br><br>
			
			<b>����ְҵ����<bean:message key="lable.xsgzyxpzxy" />�ڶ��������ʽ���������</b>
			<table class=MsoTableGrid border=1 cellspacing=0 cellpadding=0
				width=648>
				<tr>
					<td width=108 class="Normal">
						���λ����
					</td>
					<td width=168 class="Normal">
						${rs.xymc}
					</td>
					<td width=110 class="Normal">
						ָ����ʦ
					</td>
					<td width=76 class="Normal">
						${rs.xmsbr}
					</td>
					<td width=81 class="Normal">
						�ʱ��
					</td>
					<td width=105 class="Normal">
						${rs.zbsj}
					</td>
				</tr>
				<tr>
					<td width=108 class="Normal">
					   ���Ŀ����
					</td>
					<td width=168 class="Normal">
						${rs.xmmc}
					</td>
					<td width=110 class="Normal">
						���Ŀ����
					</td>
					<td width=76 class="Normal">
						${rs.xmjb}
					</td>
					<td width=81 class="Normal">
						���ֵ
					</td>
					<td width=105 class="Normal">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td width=108 class="Normal">
						��μӶ���
					</td>
					<td width=168 class="Normal">
						${rs.hddx}
					</td>
					<td width=110 class="Normal">
						��ƻ�����
					</td>
					<td width=76 class="Normal">
						${rs.hdjhrs}
					</td>
					<td width=81 class="Normal">
						���ʽ
					</td>
					<td width=105 class="Normal">
						${rs.hdxs}
					</td>
				</tr>
				<tr>
					<td class="Normal" align="center">
						<br> <br><br> <br>�����<br>����Ҫ���� <br> <br><br><br>
					</td>
					<td  class="Normal" colspan="5"  valign="bottom">
<%--						���л�������븽�������д��--%>
						<p>
						${rs.xmms}
						</p>
						<p align="right" >
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						ǩ��:
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��
						</p>
					</td>
				</tr>
				<tr>
					<td  class="Normal" align="center"  >
						<br><br>ϵ������ <br>�쵼��� <br><br> 
					</td>
					<td  class="Normal" align="right" valign="bottom" colspan="5">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						ǩ��:
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��
					</td>
				</tr>
				<tr>
					<td  class="Normal" align="center" >
						 <br><br>ѧ��������<br>(��ί)��� <br><br>
					</td>
					<td  class="Normal" align="right"  valign="bottom" colspan="5">
					    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						ǩ��:
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��
					</td>
				</tr>
			</table>
			<div align="center">
			ע:�˱�һʽ����,һ�������벿������,һ����ѧ��������(��ί)����
			</div>
		</div>
		<div class='noPrin' align="center">
				<input type='button' class='button2' value='ҳ������'
					onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='��ӡԤ��'
					onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='ֱ�Ӵ�ӡ'
					onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>
	</body>
</html>

