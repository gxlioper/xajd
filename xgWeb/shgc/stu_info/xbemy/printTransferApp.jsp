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
<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
</head>
<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>
<body>
	<html:form action="/xbemyStuStatus.do" method="post">
		<p align="center">
			<strong>���Ļ�����������ͨ�ߵ�ѧУѧ��תѧ������ </strong>
		</p>
		<p align="right">
			������ѧ���� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��
		</p>
		<table cellspacing="0" cellpadding="0" class="tbstyle" width="100%">
			<tr>
				<td width="43">
					<p align="center">
						����
					</p>
				</td>
				<td width="72">
					<p align="center">
						${rs.xm}
					</p>
				</td>
				<td width="48" colspan="2">
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
				<td width="60" colspan="2">
					<p align="center">
						${rs.mzmc}
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						׼��֤��
					</p>
				</td>
				<td width="192" colspan="4">
					<p align="center">
						${rs.zkzh}
					</p>
				</td>
			</tr>
			<tr>
				<td width="43">
					<p align="center">
						��ѧʱ��
					</p>
				</td>
				<td width="72">
					<p align="center">
						${rs.rxsj}
					</p>
				</td>
				<td width="48" colspan="2">
					<p align="center">
						ѧ��
					</p>
				</td>
				<td width="156" colspan="5">
					<p align="center">
						${rs.xh}
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						��ϵ�绰
					</p>
				</td>
				<td width="192" colspan="4">
					<p align="center">
						${rs.lxdh}
					</p>
				</td>
			</tr>
			<tr>
				<td width="43" rowspan="4">
					<p align="center">
						ת
					</p>
					<p align="center">
						��
					</p>
					<p align="center">
						ѧ
					</p>
					<p align="center">
						У
					</p>
				</td>
				<td width="72">
					<p align="center">
						У &nbsp;&nbsp;&nbsp; ��
					</p>
				</td>
				<td width="168" colspan="6">
					<p align="center">
						${rs.zcxxmc}
					</p>
				</td>
				<td width="48" colspan="2" rowspan="4" valign="top">
					<p align="center">
						ת
					</p>
					<p align="center">
						��
					</p>
					<p align="center">
						ѧ
					</p>
					<p align="center">
						У
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						У��
					</p>
				</td>
				<td width="180" colspan="3">
					<p align="center">
						${rs.zrxxmc}
					</p>
				</td>
			</tr>
			<tr>
				<td width="72">
					<p align="center">
						ר &nbsp;&nbsp;&nbsp; ҵ
					</p>
				</td>
				<td width="168" colspan="6">
					<p align="center">
						${rs.zczymc}
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						רҵ
					</p>
				</td>
				<td width="180" colspan="3">
					<p align="center">
						${rs.zrzymc}
					</p>
				</td>
			</tr>
			<tr>
				<td width="72">
					<p align="center">
						ת���꼶
					</p>
				</td>
				<td width="168" colspan="6">
					<p align="center">
						${rs.zcnj}
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						ת���꼶
					</p>
				</td>
				<td width="180" colspan="3">
					<p align="center">
						${rs.zrnj}
					</p>
				</td>
			</tr>
			<tr>
				<td width="72">
					<p align="center">
						ѧ &nbsp;&nbsp;&nbsp; ��
					</p>
				</td>
				<td width="36">
					<p align="center">
						${rs.zcxz}
					</p>
				</td>
				<td width="72" colspan="3">
					<p align="center">
						ѧ�����
					</p>
				</td>
				<td width="60" colspan="2">
					<p align="center">
						${rs.zcxlcc}
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						ѧ��
					</p>
				</td>
				<td width="36">
					<p align="center">
						${rs.zrxz}
					</p>
				</td>
				<td width="84">
					<p align="center">
						ѧ�����
					</p>
				</td>
				<td width="60">
					<p align="center">
						${rs.zrxlcc}
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td width="43">
					<p align="center">
						תѧ���루���ɣ�
					</p>
				</td>
				<td width="540" colspan="14">
					<p style="height:145px ">
						${rs.sqly}
					</p>
					<p>
						�����ˣ� ${rs.xm}
					</p>
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp; ${rs.dqn} ��&nbsp;&nbsp;&nbsp; ${rs.dqy}�� &nbsp;&nbsp;&nbsp;
						${rs.dqr}��
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td width="43">
					<p align="center">
						ת
					</p>
					<p align="center">
						��
					</p>
					<p align="center">
						ѧ
					</p>
					<p align="center">
						У
					</p>
					<p align="center">
						��
					</p>
					<p align="center">
						��
					</p>
				</td>
				<td width="240" colspan="7">
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					<p>
						ѧУ�쵼ǩ�֣�
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						�����£�
					</p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						�� &nbsp;&nbsp; �� &nbsp;&nbsp; ��
					</p>
				</td>
				<td width="48" colspan="2">
					<p align="center">
						ת
					</p>
					<p align="center">
						��
					</p>
					<p align="center">
						ѧ
					</p>
					<p align="center">
						У
					</p>
					<p align="center">
						��
					</p>
					<p align="center">
						��
					</p>
				</td>
				<td width="252" colspan="5">
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					<p>
						ѧУ�쵼ǩ�֣�
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						�����£�
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						�� &nbsp;&nbsp; �� &nbsp;&nbsp; ��
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td width="43">
					<p align="center">
						ת��ʡ���������������
					</p>
				</td>
				<td width="240" colspan="7">
						<br/>
						<br/>
						�����ˣ�					
						<br/>
						<br/>
						������
						<br/>
						<br/>
						������ ( ί ) �쵼��
						<br/>
						<br/>
					
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����£�
					
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						�� &nbsp;&nbsp; �� &nbsp;&nbsp; ��
					</p>
				</td>
				<td width="48" colspan="2">
					<p align="center">
						ת��ʡ���������������
					</p>
				</td>
				<td colspan="5">
					<br/>
						<br/>
						�����ˣ�					
						<br/>
						<br/>
						������
						<br/>
						<br/>
						������ ( ί ) �쵼��
						<br/>
						<br/>
					
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����£�
					
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						�� &nbsp;&nbsp; �� &nbsp;&nbsp; ��
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td width="43">
					<p align="center">
						��
					</p>
					<p align="center">
						ע
					</p>
				</td>
				<td width="540" colspan="14">
					<p>
						1 ������תѧ����ʱЯ���˱�����¼���ӡ�����Ӹ�ѧУѧ��������ӡ�¡�
					</p>
					<p>
						2
						����������תѧһʽ�ķݣ���ʡתѧһʽ��ݣ�ת��ѧУ��ת��ѧУ��ʡ�����������ż�ѧУ���ڵع����������Ÿ���һ�ݡ�ת��ѧУ��ת��ѧУ�ִ˱�ѧУ���ڵع����������Ű�����Ǩ��������
					</p>
					<p>
						3 ��ѧ�������ָ��ʿ��˶ʿ�����ơ�ר�ƣ���ְ����
					</p>
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

