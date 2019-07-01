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
	<title><bean:message key="lable.title" /></title>
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
			<strong>���Ļ�����������ͨ�ߵ�ѧУѧ��תרҵ���루�������� </strong>
		</p>
		<div>�����ڶ�����<bean:message key="lable.xsgzyxpzxy" />�ñ�</div>	
		<div align="right">������ѧ���� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��</div>
		<table cellspacing="0" cellpadding="0" class="tbstyle" width="100%">
			<tr>
				<td width="43">
					<p align="center">
						����
					</p>
				</td>
				<td width="65" colspan="2">
					<p align="center">
						${rs.xm}
					</p>
				</td>
				<td width="48">
					<p align="center">
						�Ա�
					</p>
				</td>
				<td width="48">
					<p align="center">
						${rs.xb}
					</p>
				</td>
				<td width="48" colspan="2">
					<p align="center">
						����
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						${rs.mzmc}
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						ѧ &nbsp;&nbsp; У
					</p>
				</td>
				<td width="187" colspan="3">
					<p align="center">
						${xxmc}
					</p>
				</td>
			</tr>
			<tr>
				<td width="43">
					<p align="center">
						��ѧʱ��
					</p>
				</td>
				<td width="65" colspan="2">
					<p align="center">
						${rs.rxsj}
					</p>
				</td>
				<td width="48">
					<p align="center">
						ѧ��
					</p>
				</td>
				<td width="168" colspan="5">
					<p align="center">
						${rs.xh}
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						׼��֤��
					</p>
				</td>
				<td width="187" colspan="3">
					<p align="center">
						${rs.zkzh}
					</p>
				</td>
			</tr>
			<tr>
				<td width="108" colspan="3">
					<p align="center">
						¼ȡרҵ
					</p>
				</td>
				<td width="216" colspan="6">
					<p align="center">
						${rs.zczymc}
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						ת��רҵ
					</p>
				</td>
				<td width="187" colspan="3">
					<p align="center">
						${rs.zrzymc}
					</p>
				</td>
			</tr>
			<tr>
				<td width="108" colspan="3">
					<p align="center">
						ת���꼶
					</p>
				</td>
				<td width="216" colspan="6">
					<p align="center">
						${rs.zcnj}
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						ת���꼶
					</p>
				</td>
				<td width="187" colspan="3">
					<p align="center">
						${rs.zrnj}
					</p>
				</td>
			</tr>
			<tr>
				<td width="108" colspan="3">
					<p align="center">
						ѧ &nbsp;&nbsp; &nbsp; ��
					</p>
				</td>
				<td width="48">
					<p align="center">
						${rs.zcxz}
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						ѧ�����
					</p>
				</td>
				<td width="96" colspan="3">
					<p align="center">
						${rs.zcxlcc}
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						ѧ &nbsp;&nbsp;&nbsp; ��
					</p>
				</td>
				<td width="48">
					<p align="center">
						${rs.zrxz}
					</p>
				</td>
				<td width="72">
					<p align="center">
						ѧ�����
					</p>
				</td>
				<td width="67">
					<p align="center">
						${rs.zrxlcc}
					</p>
				</td>
			</tr>
			<tr height="50px">
				<td width="84" colspan="2">
					<p align="center">
						תרҵ���루���ɣ�
					</p>
				</td>
				<td width="499" colspan="12" valign="top">
					<p align="center" style="height:45px ">
						${rs.sqly}
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						�����ˣ� ${rs.xm}
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp; ${rs.dqn}�� &nbsp;&nbsp; ${rs.dqy}�� &nbsp;&nbsp; ${rs.dqr}��
					</p>
				</td>
			</tr>
			<tr height="50px">
				<td width="84" colspan="2">
					<p align="center">
						ת��רҵԺ��ϵ�� ���
					</p>
				</td>
				<td width="199" colspan="6" valign="top">
					<br/>
					<br/>
					<br/>
						&nbsp;&nbsp;&nbsp; ������ǩ�֣�
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; �����£�
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��
						&nbsp;&nbsp; �� &nbsp;&nbsp; ��
				</td>
				<td width="84" colspan="2">
					<p align="center">
						ת��רҵ Ժ��ϵ�����
					</p>
				</td>
				<td width="216" colspan="4" valign="top">
					<br/>
					<br/>
						&nbsp;&nbsp;&nbsp; ������ǩ�֣�
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; �����£�
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��
						&nbsp;&nbsp; �� &nbsp;&nbsp; ��					
				</td>
			</tr>
			<tr height="50px">
				<td width="84" colspan="2">
					<p align="center">
						�оʹ� &nbsp; ���
					</p>
				</td>
				<td width="199" colspan="6" valign="top">
					<br/>
					<br/>
					<br/>
						&nbsp;&nbsp;&nbsp; ������ǩ�֣�
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; �����£�
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��
						&nbsp;&nbsp; �� &nbsp;&nbsp; ��
				</td>
				<td width="84" colspan="2">
					<p align="center">
						���� &nbsp; ���
					</p>
				</td>
				<td width="216" colspan="4" valign="top">
					<br/>
					<br/>
					<br/>
						&nbsp;&nbsp;&nbsp; ������ǩ�֣�
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; �����£�
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��
						&nbsp;&nbsp; �� &nbsp;&nbsp; ��
				</td>
			</tr>
			<tr height="50px">
				<td width="84" colspan="2">
					<p align="center">
						���� &nbsp; ���
					</p>
				</td>
				<td width="199" colspan="6" valign="top">
					<br/>
					<br/>
						&nbsp;&nbsp;&nbsp; ������ǩ�֣�
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; �����£�
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��
						&nbsp;&nbsp; �� &nbsp;&nbsp; ��					
				</td>
				<td width="84" colspan="2">
					<p align="center">
						ѧ���� &nbsp; ���
					</p>
				</td>
				<td width="216" colspan="4" valign="top">
					<br/><br/><br/>
						&nbsp;&nbsp;&nbsp; ������ǩ�֣�
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; �����£�
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; ��
						&nbsp;&nbsp; �� &nbsp;&nbsp; ��					
				</td>
			</tr>
			<tr height="50px">
				<td width="84" colspan="2">
					<p align="center">
						�ߵ�ѧУ�������
					</p>
				</td>
				<td width="199" colspan="6" valign="top">
					<br/>
					<br/>
					<br/>
						����У�������
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; �����£�
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��
						&nbsp;&nbsp; �� &nbsp;&nbsp; ��
				</td>
				<td width="84" colspan="2">
						�������������������
				</td>
				<td width="216" colspan="4" valign="top">
					<br/>
						�����ˣ�
					<br/>
					<br/>
						�� &nbsp; ����
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; �����£�
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��
						&nbsp;&nbsp; �� &nbsp;&nbsp; ��					
				</td>
			</tr>
			<tr height="50px">
				<td width="84" colspan="2">
						��ע
				</td>
				<td width="499" colspan="12" valign="top">
				<br/>
						&#149;&nbsp; ����תרҵ����ʱЯ���˱�����¼���ӡ�����Ӹ�ѧ��ѧ��������ӡ�¡�
					<br/>
						&#149;&nbsp; �˱�ǩ����ԭ�� ��ѧ�� �������񴦡����񴦡��оʹ���ѧ������<bean:message key="lable.xsgzyxpzxy" />�����˸��ָ�ӡ��һ��
					<br/>
						&#149;&nbsp; ѧ�������ָ��ʿ��˶ʿ�����ơ�ר�ƣ���ְ����
				</td>
			</tr>
		</table>
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

