<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>
	<!-- ��ӡ�ؼ�begin -->
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
		.noPrin{display:none;}
	</style>
	<!-- end -->
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
</head>
<body>
	<html:form action="/pjpynblgwh">
		<div align="center" style="font-size:20px;font:'����' ">
			<b>${xxmc } �������ҵ�������</b>
		</div>
		<br />
		<div>
			<table width="100%" class="printstyle">
				<tr>
					<th height="30" colspan="2" width="14%" scope="col">
						��&nbsp;&nbsp;&nbsp;��
					</th>
					<td height="30" colspan="2" style="width:14%" scope="col"
						align="center">
						&nbsp;
						<bean:write name='rs' property="xm" />
					</td>
					<th height="30" width="11%" scope="col">
						��&nbsp;&nbsp;&nbsp;��
					</th>
					<td height="30" colspan="2" scope="col" style="width:12%"
						align="center">
						&nbsp;
						<bean:write name='rs' property="xb" />
					</td>
					<th height="30" width="12%" scope="col">
						��������
					</th>
					<td height="30" colspan="2" scope="col" width="12%" align="center">
						&nbsp;
						<bean:write name='rs' property="csrq" />
					</td>
					<th height="30" colspan="2" width="12%" scope="col">
						ѧ&nbsp;&nbsp;&nbsp;��
					</th>
					<td height="30" colspan="2" style="width:14%" scope="col"
						align="center">
						&nbsp;
						<bean:write name='rs' property="xh" />
					</td>
				</tr>
				<tr>
					<th height="30" colspan="2" width="14%" scope="col">
						��&nbsp;&nbsp;&nbsp;��
						<br />
						��&nbsp;&nbsp;&nbsp;Ժ
					</th>
					<td height="30" colspan="2" style="width:14%" scope="col"
						align="center">
						&nbsp;
					</td>
					<th height="30" width="11%" scope="col">
						ר&nbsp;&nbsp;&nbsp;ҵ
						<br />
						��&nbsp;&nbsp;&nbsp;��
					</th>
					<td height="30" colspan="5" scope="col" style="width:12%"
						align="center">
						&nbsp;
					</td>
					<th height="30" colspan="2" width="12%" scope="col">
						������
						<br />
						����
					</th>
					<td height="30" colspan="2" style="width:14%" scope="col"
						align="center">
						&nbsp;
					</td>
				</tr>
				<tr>
					<th height="30" colspan="2" width="14%" scope="col">
						��&nbsp;&nbsp;&nbsp;��
						<br />
						��&nbsp;&nbsp;&nbsp;��
					</th>
					<td height="30" colspan="2" style="width:14%" scope="col"
						align="center">
						&nbsp;
						<bean:write name='rs' property="xm" />
					</td>
					<th height="30" width="11%" scope="col">
						����Υ
						<br />
						�ʹ���
					</th>
					<td height="30" colspan="2" scope="col" style="width:12%"
						align="center">
						&nbsp;
						<bean:write name='rs' property="xb" />
					</td>
					<th height="30" width="12%" scope="col">
						�������
						<br />
						�������
					</th>
					<td height="30" colspan="6" scope="col" width="12%" align="center">
						&nbsp;
						<bean:write name='rs' property="csrq" />
					</td>

				</tr>
				<tr>
					<th scope="row" style="width:6%" colspan="2">
						<p>
							&nbsp;
						</p>
						<p>
							��
						</p>
						<p>
							��
						</p>
						<p>
							��
						</p>
						<p>
							��
						</p>
						<p>
							&nbsp;
						</p>
					</th>
					<th colspan="12" scope="row">

					</th>
				</tr>
				<tr>
					<th scope="row" style="width:6%" colspan="2">
						<p>
							&nbsp;
						</p>
						<p>
							��
						</p>
						<p>
							��
						</p>
						<p>
							��
						</p>
						<p>
							Ҫ
						</p>
						<p>
							��
						</p>
						<p>
							��
						</p>
						<p>
							&nbsp;
						</p>
					</th>
					<th colspan="12" scope="row">
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<p align="right">
							&nbsp;ǩ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							�� &nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;��
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</th>
				</tr>
				<tr>
					<th align="" colspan="2">
						<p>��</p>
						<p>��</p>
						<p>��</p>
						<p>Ժ</p>
						<p>��</p>
						<p>��</p>
					</th>
					<th colspan="2" width="20%">
						<br/><br/><br/><br/><br/>
						<p align="left">ǩ��(����):</p>
						<p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;</p>
					</th>
					<th align="" colspan="2">
						<p>ѧ</p>
						<p>��</p>
						<p>��</p>
						<p>��</p>
						<p>��</p>
					</th>
					<th colspan="2" width="20%">
						<br/><br/><br/><br/><br/>
						<p align="left">ǩ��(����):</p>
						<p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;</p>
					</th>
					<th align="" colspan="2">
						<p>ѧ</p>
						<p>Ժ</p>
						<p>��</p>
						<p>��</p>
					</th>
					<th colspan="3" width="20%">
						<br/><br/><br/><br/><br/>
						<p align="left">ǩ��(����):</p>
						<p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;</p>
					</th>
				</tr>
			</table>
			<div>
				<b>ע��</b>1.�������ú�ɫ�ֱʻ��ɫˮ����ʵ��д�����ݲ���Ϳ�ģ�Ҫ���ּ�����������
				<br />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				2.������Ҫ�¼���Ҫ���ø�ҳ����ʽ���˱����A4ֽ��ӡ��ӡ���걨ʱ���ϻ�֤�鸴ӡ����

			</div>
			<div align="center" class='noPrin'>
				<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
					ҳ������
				</button>
				<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
					��ӡԤ��
				</button>
				<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
					ֱ�Ӵ�ӡ
				</button>
			</div>
		</div>
	</html:form>
</body>
</html:html>
