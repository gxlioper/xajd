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
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" http-equiv="no-cache" />
	<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
	<meta http-equiv="Expires" http-equiv="0" />

	<meta name="Copyright" content="������� zfsoft" />

	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<style media='print'>.noPrin{display:none}
	</style>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
</head>
<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>
<body>
	<html:form action="/xbemyStuStatus.do" method="post">
		<p align="center">
			�����ڶ�����<bean:message key="lable.xsgzyxpzxy" />
		</p>
		<p align="center">
			ѧ �� ѧ �� �� �� �� �� ��
		</p>
		<table cellspacing="0" cellpadding="0" class="tbstyle" width="100%">
			<tr>
				<td width="88" colspan="2">
					<p align="center">
						�� &nbsp; ��
					</p>
				</td>
				<td width="120" colspan="2">
					<p align="center">
						${rs.xm}
					</p>
				</td>
				<td width="60">
					<p align="center">
						�� &nbsp; ��
					</p>
				</td>
				<td width="48" colspan="2">
					<p align="center">
						${rs.xb}
					</p>
				</td>
				<td width="60" colspan="3">
					<p align="center">
						����
					</p>
				</td>
				<td width="84" colspan="2">
					<p align="center">
						${rs.mzmc}
					</p>
				</td>
				<td width="84">
					<p align="center">
						����������
					</p>
				</td>
				<td width="92" colspan="2">
					<p align="center">
						${rs.csrq}
					</p>
				</td>
			</tr>
			<tr>
				<td width="88" colspan="2">
					<p align="center">
						ѧ &nbsp; ��
					</p>
				</td>
				<td width="120" colspan="2">
					<p align="center">
						${rs.xh}
					</p>
				</td>
				<td width="156" colspan="5">
					<p align="center">
						�䶯ǰ����רҵ
					</p>
					<p align="center">
						�꼶���༶
					</p>
				</td>
				<td colspan="2">					
					${rs.ydqzymc}${rs.ydqnj}${rs.ydqbjmc}					
				</td>
				<td width="98" colspan="3">
					<p align="center">
						��Դ��
					</p>
				</td>
				<td width="90">
					<p align="center">
						${rs.syd}
					</p>
				</td>
			</tr>
			<tr>
				<td width="136" colspan="3">
					<p align="center">
						ѧ���䶯����
					</p>
				</td>
				<td width="72">
					<p align="center">
						${rs.ydlbmc}
					</p>
				</td>
				<td width="120" colspan="4">
					<p align="center">
						��ֹʱ��
					</p>
				</td>
				<td width="120" colspan="3">
					<p align="center">
						${rs.qssj}- ${rs.jzsj}
					</p>
				</td>
				<td width="98" colspan="3">
					<p align="center">
						�䶯��רҵ
					</p>
					<p align="center">
						�꼶���༶
					</p>
				</td>
				<td>
					<p align="center">
						${rs.ydhzymc}${rs.ydhnj}${rs.ydhbjmc}
					</p>
				</td>
			</tr>
			<tr>
				<td width="136" colspan="3">
					<p align="center">
						��ͥסַ���绰
					</p>
				</td>
				<td width="500" colspan="12">
					<p align="center">
						${rs.jtdz} &nbsp; &nbsp; &nbsp; ${rs.jtdh}
					</p>
				</td>
			</tr>
			<tr>
				<td width="76">
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						ԭ
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						��
					</p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
				<td colspan="14">
					<p align="center" style="height:125px">
						${rs.sqly}
					</p>
					<p>
						������ǩ����${rs.xm}
					</p>
					<p align="right">
						${rs.dqn}�� &nbsp;&nbsp;&nbsp;${rs.dqy}��
						&nbsp;&nbsp;&nbsp;${rs.dqr} ��
					</p>
				</td>
			</tr>
			<tr>
				<td width="76">
					<p align="center">
						ѧ��
					</p>
					<p align="center">
						����
					</p>
					<p align="center">
						Ժ���
					</p>
				</td>
				<td width="228" colspan="5">
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp; ǩ�£�
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						�� &nbsp; �� &nbsp; ��
					</p>
				</td>
				<td width="72" colspan="4">
					<p align="center">
						���ڴ�
					</p>
					<p align="center">
						�� ��
					</p>
				</td>
				<td width="260" colspan="5">
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ǩ�£�
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						�� &nbsp; �� &nbsp; ��
					</p>
				</td>
			</tr>
			<tr>
				<td width="76">
					<p align="center">
						�оʹ�
					</p>
					<p align="center">
						�� ��
					</p>
				</td>
				<td width="228" colspan="5">
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp; ǩ�£�
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						�� &nbsp; �� &nbsp; ��
					</p>
				</td>
				<td width="72" colspan="4">
					<p align="center">
						����
					</p>
					<p align="center">
						�� ��
					</p>
				</td>
				<td width="260" colspan="5">
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ǩ�£�
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						�� &nbsp; �� &nbsp; ��
					</p>
				</td>
			</tr>
			<tr>
				<td width="76">
					<p align="center">
						����
					</p>
					<p align="center">
						�� ��
					</p>
				</td>
				<td width="228" colspan="5">
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p>
						ǩ�£�
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						�� &nbsp; �� &nbsp; ��
					</p>
				</td>
				<td width="72" colspan="4">
					<p align="center">
						ѧ����
					</p>
					<p align="center">
						�� ��
					</p>
				</td>
				<td width="260" colspan="5">
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						ǩ�£�
					</p>
					<p align="center">
						�� &nbsp; �� &nbsp; ��
					</p>
				</td>
			</tr>
			<tr>
				<td width="76">
					<p align="center">
						У�쵼
					</p>
					<p align="center">
						�� ʾ
					</p>
				</td>
				<td width="560" colspan="14">
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						ǩ�£�
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						�� &nbsp; �� &nbsp; ��
					</p>
				</td>
			</tr>
		</table>
		<p>
			<strong>˵�����˱�ǩ����ԭ�� ��ѧ�� �������񴦡����񴦡��оʹ������ڴ���ѧ������<bean:message key="lable.xsgzyxpzxy" />�����˸��ָ�ӡ��һ�� </strong>
		</p>
	</html:form>
</body>
<div class='noPrin' align="center">
	<input type='button' class='button2' value='ҳ������'
		onclick="WebBrowser.ExecWB(8,1);return false;">
	<input type='button' class='button2' value='��ӡԤ��'
		onclick="WebBrowser.ExecWB(7,1);return false;">
	<input type='button' class='button2' value='ֱ�Ӵ�ӡ'
		onclick="WebBrowser.ExecWB(6,6);return false;">
</div>
</html:html>

