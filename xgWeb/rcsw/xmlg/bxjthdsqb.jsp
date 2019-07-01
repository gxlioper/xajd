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


	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<style media="print">
		.noprint{
			display:none;
		}
		.print{
			display:block;
		}
		.tbstyle {
	border-collapse: collapse;
}
		.tbstyle td {
	border: 1px #97B7DB solid;
	padding: 3px;
	font-size: 14px;
}
	</style>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>

</head>
<body>
	<div align="center">
		<h3 align="center">
			������<bean:message key="lable.xsgzyxpzxy" />ѧ�����⼯�����������
			<br>
		</h3>
		<logic:equal value="bx" name="lb">
			<p>
				<font size="3">��ϵ���༯������ר�ã�</font>
			</p>
		</logic:equal>
		<logic:equal value="kx" name="lb">
			<p>
				<font size="3">��ϵ��ϵѧ����������ר�ã�</font>
			</p>
		</logic:equal>

		<table cellspacing=0 cellpadding=0 align=center class="tbstyle" style="width: 100%">
			<tr height="42px">
				<td align="center" width="14%">
					��֯��λ
				</td>
				<td align="left" width="20%">
					${form.zzdw}
				</td>
				<td align="center" width="13%">
					������
				</td>
				<td align="left" width="20%">
					${form.fzr}
				</td>
				<td align="center" width="13%">
					��ϵ�绰
				</td>
				<td align="left" width="20%">
					${form.fzrdh}
				</td>
			</tr>
			<tr height="42px">
				<td align="center">
					���ӽ�ʦ
				</td>
				<td align="left">
					${form.ddjs}
				</td>
				<td align="center">
					��ϵ�绰
				</td>
				<td align="left" colspan="3">
					${form.ddjsdh}
				</td>
			</tr>
			<tr height="42px">
				<td align="center">
					����ʱ��
				</td>
				<td align="left" colspan="5">
					&nbsp;&nbsp;${form.cxsj}
				</td>
			</tr>
			<tr height="42px">
				<td>
					<div align="center">
						
						<br>
						<br>
						<br>
						<br>
						�����
						<br>
						<br>
						<br>
						<br>
						<br>
					</div>
				</td>
				<td colspan="5" valign="top">
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;${form.hdnr}
					<br><br>
				</td>
			</tr>
			<tr height="42px">
				<td>
					<div align="center">
						
						<br>
						<br>
						<br>
						<br>
						��ȫ��ʩ
						<br>
						<br>
						<br>
						<br>
						<br>
					</div>
				</td>
				<td colspan="5" valign="top">
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;${form.aqcs}
					<br><br>
				</td>
			</tr>
			<logic:equal value="bx" name="lb">
				<tr height="42px">
					<td>
						<div align="center">
							<br><br>
							��<br>
							��<br>
							��<br>
							��<br>
							��<br>
							<br><br>
						</div>
					</td>
					<td colspan="2" valign="top">
						<br><br><br><br><br><br><br>
						<p align="right">ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
						&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>		
					</td>
					<td>
						<div align="center">
							<br><br>
							��<br>
							��<br>
							Ա<br>
							��<br>
							��<br>
							<br><br>
						</div>
					</td>
					<td colspan="2" valign="top">
						<br><br><br><br><br><br><br>
						<p align="right">ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
						&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>	
					</td>
				</tr>
				<tr height="42px">
					<td>
						<div align="center">
							<br><br>
							ϵ��<br>
							��֧<br>
							����<br>
							���<br>
							<br><br>
						</div>
					</td>
					<td colspan="5" valign="top">
						<br><br><br><br><br><br>
						<p align="right">ǩ�£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
						&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>	
					</td>
				</tr>
			</logic:equal>
			<logic:equal value="kx" name="lb">
				<tr height="42px">
					<td>
						<div align="center">
							<br>
							��<br>
							��<br>
							��<br>
							��<br>
							��<br>
							��<br>
							<br>
						</div>
					</td>
					<td colspan="5" valign="top">
						<br><br><br><br><br><br>
						<p align="right">ǩ�£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
						&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>	
					</td>
				</tr>
				<tr height="42px">
					<td>
						<div align="center">
							<br>
							ѧ<br>
							��<br>
							��<br>
							��<br>
							��<br>
							��<br>
							��<br>
							<br>
						</div>
					</td>
					<td colspan="5" valign="top">
						<br><br><br><br><br><br>
						<p align="right">ǩ�£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
						&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>	
					</td>
				</tr>
			</logic:equal>
		</table>
		<logic:equal value="bx" name="lb">
		<p align="left" style="font-size: 14px">
			ע�����
			<br>
			1.ѧ����������������д�����������ƻ�����ȫԤ����һʽ���ݣ���һ��ϵ��浵��<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�������ݱ�ѧ������������������
			<br>
			2.ϵ����ѧ���������Ӧ�а������ʦ������ӣ�

		</p>
		</logic:equal>
		<logic:equal value="kx" name="lb">
		<p align="left" style="font-size: 14px">
			ע�����
			<br>
			1.ѧ����������������д�����������ƻ�����ȫԤ����һʽ���ݣ���һ��ϵ��浵��<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�������ݱ�ѧ������������������
			<br>
			2.ѧ����������Ӧ�д�����ʦ������ӣ�

		</p>
		</logic:equal>
	</div>
	<br>
	<div class="buttontool noprint" align="center">
		<input  class="button2" value="ҳ������"
			onclick="WebBrowser.ExecWB(8,1);">
		<input  class="button2" value="��ӡԤ��"
			onclick="WebBrowser.ExecWB(7,1)">
		<input  class="button2" value="ֱ�Ӵ�ӡ"
			onclick="WebBrowser.ExecWB(6,6)">
	</div>
</body>
</html:html>
