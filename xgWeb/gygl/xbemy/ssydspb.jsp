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
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body>
		<center id="theTable">
			<p>
			<h3 align="center">
				<bean:write name="rs" property="xxmc" />
				ѧ��ס�޵���������
			</h3>
			<div>
				<b>����ʱ�䣺 &nbsp;&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp; ��
					&nbsp;&nbsp;&nbsp; ��
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					��ţ� </b>
			</div>
			<table width="100%" border="0" id="theTable"  class="printstyle">
				<tr>
					<td width="15%" align="center">
						����
					</td>
					<td width="10%">
						<bean:write name="rs" property="xm" />
					</td>
					<td width="10%">
						�Ա�
					</td>
					<td width="13%">
						<bean:write name="rs" property="xb" />
					</td>
					<td width="10%">
						����
					</td>
					<td width="10%">
						<bean:write name="rs" property="csnl" />
					</td>
					<td width="10%">
						��Դ��
					</td>
					<td width="22%">
						<bean:write name="rs" property="lydq" />
					</td>
				</tr>
				<tr>
					<td align="center">
						Ժ ( ϵ )
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xymc" />
					</td>
					<td>
						רҵ
					</td>
					<td>
						<bean:write name="rs" property="zymc" />
					</td>
					<td>
						ѧ��
					</td>
					<td>
						<bean:write name="rs" property="xh" />
					</td>
				</tr>
				<tr>
					<td align="center">
						�꼶
					</td>
					<td colspan="3">
						<bean:write name="rs" property="nj" />
					</td>
					<td>
						��ϵ�绰
					</td>
					<td>
						<bean:write name="rs" property="lxdh" />
					</td>
					<td>
						ԭ�ޱ��
					</td>
					<td>
						<bean:write name="rs" property="ssbh" />
					</td>
				</tr>
				<tr>
					<td align="center" height="200px">
						<div style="margin:10px ">
							����
						</div>
						<div style="margin:10px ">
							����
						</div>
						<div style="margin:10px ">
							����
						</div>
						<div style="margin:10px ">
							ԭ��
						</div>
					</td>
					<td colspan="7">
						<bean:write name="rs" property="ydly" />
					</td>
				</tr>
				<tr>
					<td align="center">
						<div style="margin:10px ">
							ͬ��
						</div>
						<div style="margin:10px ">
							����
						</div>
					</td>
					<td colspan="7">
						<p>
							1 �������Ծ����ء����������ѧס�޹���涨����ѧУ�����ƶȣ���˽�Ե�����λ��<br>
							2 ����������ִ���������������ƶȣ��Ծ�ά���������ࣻ<br>
							3 �������Ծ�����ѧУ��Ϣʱ�䣬�Ž�ͬѧ����������𻵹����Ծ��⳥��<br>
							4 �����˰�ʱ����ס�޷Ѻ������йط��ã������õ簲ȫ�涨��<br>
							5 ������Ը�����ѧ�����޹ܿƵİ��ź͵��䡣
						</p>
						<p align="right">
							ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
							��&nbsp;&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr>
					<td align="center">
						<div style="margin:10px ">
							<bean:message key="lable.xb" />
						</div>
						<div style="margin:10px ">
							����
						</div>
						<div style="margin:10px ">
							���
						</div>
					<td colspan="3">
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
							&nbsp;
						</p>
						<p align="right">
							ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
							��&nbsp;&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
					<td align="center">
						<div style="margin:10px ">
							�޹ܿ�
						</div>
						<div style="margin:10px ">
							���
						</div>
					<td colspan="3">
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
							&nbsp;
						</p>
						<p align="right">
							ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
							��&nbsp;&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
						
					</td>
				</tr>
				<tr>
					<td align="center">
						<div style="margin:10px ">
							����
						</div>
						<div style="margin:10px "></div>
						<div style="margin:10px ">
							����
						</div>
					<td colspan="3">
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
							&nbsp;
						</p>
						<p align="right">
							ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
							��&nbsp;&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
						
					</td>
					<td align="center">
						<div style="margin:10px ">
							�޹ܿ�
						</div>
						<div style="margin:10px ">
							����
						</div>
						<div style="margin:10px ">
							���
						</div>
					<td colspan="3">
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							���ŵ�_____¥______����
						</p>
						<p>
							&nbsp;
						</p>
						<p align="right">
							ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
							��&nbsp;&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
						
					</td>
				</tr>
				<tr>
					<td align="center">
						<div style="margin:10px ">
							����
						</div>
						<div style="margin:10px ">
							����
						</div>
					</td>
					<td colspan="7">
						<p>
							1���������Ҫ����д������ݣ�<br>
							2����ͨ����֮��������õ����񴦸��£�<br>
							3����ñ���ֱ𵽱�<bean:message key="lable.xb" />���޹ܿơ�����ǩ�������<br>
							4������ͨ���󣬰�Ҫ��ԭ����¥ֵ���Ұ������������������¥������ס������<br>	
							5����������� , ���˱�ӡ�ķ� ( ���ˡ����񴦡�����<bean:message key="lable.xb" />�������¥¥�� ) ԭ�����޹ܿơ�
						</p>
					</td>
				</tr>

			</table>
		</center>
	</body>
</html>
