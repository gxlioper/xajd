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
	<script type="text/javascript" src="js/BatAlert.js"></script>

	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
		<html:form action="/zxdk_xnmz" method="post">

			<p align=center style='text-align:center'>
				<b> <span
					style='font-size:16.0pt;font-family:����;"Times New Roman";"Times New Roman"'>������ѧ����ѧ����Ϣ��</span>
				</b>
			</p>
			<p align="right" style='font-family:����;"Times New Roman";'>
				���*�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</p>
			<span style='font-family:����;'>����ѧ������<bean:message key="lable.xb" />��ϵ*��${xxmc}${rs.xymc }</span>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<span style='font-family:����;'>����������*��${rs.xm }</span>

			<table width="100%" id="rsT" class="printstyle">
				<tr>
					<td colspan=15 valign=bottom>
						<p align='center'>
							<b> ѧ��������Ϣ </b>
						</p>
					</td>
				</tr>
				<tr height="35px">
					<td width=105   colspan='3' valign=bottom >
						���֤����
					</td>
					<td width=140 colspan='4' valign=bottom>
						&nbsp;${rs.sfzh}
					</td>
					<td width=52 colspan=2 valign=bottom >
						�Ա�
					</td>
					<td width=52 valign=bottom >
						&nbsp;${rs.xb}
					</td>
					<td width=52 valign=bottom >
						����
					</td>
					<td width=34 colspan=2 valign=bottom>
						&nbsp;${rs.nl}
					</td>
					<td width=52 valign=bottom >
						����
					</td>
					<td width=52 valign=bottom >
						&nbsp;${rs.mzmc}
					</td>
				</tr>
				<tr height="35px">
					<td width=105 colspan=3 valign=bottom >
						������ϸ��ַ
					</td>
					<td width=70 colspan=3 valign=bottom >
						&nbsp;${rs.sfzh}
					</td>
					<td width=70 valign=bottom >
						���ҵ绰
					</td>
					<td width=97 colspan=3 valign=bottom >
						&nbsp;${rs.sfzh}
					</td>
					<td width=74 colspan=2 valign=bottom >
						�ƶ��绰
					</td>
					<td width=106 colspan=3 valign=bottom >
						&nbsp;${rs.sfzh}
					</td>
				</tr>
				<tr height="35px">
					<td width=140 colspan=4 valign=bottom >
						ѧ����ͥ��ϸ��ַ
					</td>
					<td width=250 colspan=6 valign=bottom >
						&nbsp;${rs.jtdz}
					</td>
					<td width=74 colspan=2 valign=bottom >
						ѧ��ԭ��
					</td>
					<td width=106 colspan=3 valign=bottom >
						&nbsp;${rs.sfzh}
					</td>
				</tr>
				<tr height="35px">
					<td width=105 colspan=3 valign=bottom >
						OICQ����
					</td>
					<td width=187 colspan=4 valign=bottom >
						&nbsp;${rs.qqhm}
					</td>
					<td width=97 colspan=3 valign=bottom >
						�����ʼ�
					</td>
					<td width=180 colspan=5 valign=bottom >
						&nbsp;${rs.dzyj}
					</td>
				</tr>
				<tr height="35px">
					<td width=105 colspan=3 valign=bottom >
							������ַ��ַ
					</td>
					<td width=464 colspan=12 valign=bottom >
						${rs.grwzdz }
					</td>
				</tr>
				<tr height="35px">
					<td colspan='15' valign='bottom'>
						<p align='center'>
							<b>��ͥ��Ϣ </b>
						</p>
					</td>
				</tr>
				<tr height="35px">
					<td width=105 colspan=3 valign=bottom >
						ĸ������
					</td>
					<td width=70 colspan=3 valign=bottom >
						${rs.mqxm }
					</td>
					<td width=136 colspan=2 valign=bottom >
						ĸ�����֤����
					</td>
					<td width=238 colspan=7 valign=bottom>
						${rs.mqsfzh }
					</td>
				</tr>
				<tr height="35px">
					<td width=105 colspan=3 valign=bottom >
						��������
					</td>
					<td width=70 colspan=3 valign=bottom >
						${rs.fqxm }
					</td>
					<td width=136 colspan=2 valign=bottom >
						�������֤����
					</td>
					<td width=238 colspan=7 valign=bottom>
						${rs.fqsfzh }
					</td>
				</tr>
				<tr height="35px">
					<td width=175 colspan=6 valign=bottom >
						ĸ�׹�����λ����ϵ�绰
					</td>
					<td width=374 colspan=9 valign=bottom >
						&nbsp;
						<logic:notEmpty name="rs" property="mqgzdw">
							��λ��${rs.mqgzdw}
						</logic:notEmpty>
						<logic:notEmpty name="rs" property="mqlxfs">
							�绰��${rs.mqlxfs}
						</logic:notEmpty>
					</td>
				</tr>
				<tr height="35px">
					<td width=175 colspan=6 valign=bottom >
						���׹�����λ����ϵ�绰
					</td>
					<td width=374 colspan=9 valign=bottom >
						&nbsp;
						<logic:notEmpty name="rs" property="fqgzdw">
							��λ��${rs.fqgzdw}
						</logic:notEmpty>
						<logic:notEmpty name="rs" property="fqlxfs">
							�绰��${rs.fqlxfs}
						</logic:notEmpty>
					</td>
				</tr>
				<tr height="35px">
					<td colspan='15' valign='bottom'>
						<p align='center'>
							<b>�����ϵ����Ϣ </b>
						</p>
					</td>
				</tr>
				<tr height="35px">
					<td width=79 colspan=2 valign=bottom >
						����
					</td>
					<td width=108 colspan=3 valign=bottom >
						${rs.lxrxm }
					</td>
					<td width=105 colspan=2 valign=bottom >
						��ϵ�̶��绰
					</td>
					<td width=97 colspan=3 valign=bottom >
						${rs.lxrgddh }
					</td>
					<td width=74 colspan=2  valign=bottom>
						�ƶ��绰
					</td>
					<td width=106 colspan=3 valign=bottom>
						${rs.lxryddh }
					</td>
				</tr>
				<tr height="35px">
					<td width=187 colspan=5 valign=bottom >
						������λ��ѧϰԺУ
					</td>
					<td width=382 colspan=10 valign=bottom >
						&nbsp;${rs.lxrgzdw }
					</td>
				</tr>
				<tr height="35px">
					<td width=187 colspan=5 valign=bottom >
						��ַ
					</td>
					<td width=382 colspan=10 valign=bottom >
						&nbsp;${rs.lxrjtzz}
					</td>
				</tr>
				<tr height="35px">
					<td colspan='15' valign='bottom'>
						<p align='center'>
							<b>ѧ��������Ϣ��������ʼ���������й�����Ա��д�� </b>
						</p>
					</td>
				</tr>
				<tr height="35px">
					<td width=105 colspan=3 valign=bottom >
						<p>
							<span style='font-family:����;"Times New Roman"'>������ʼ����</span>
						</p>
					</td>
					<td width=187 colspan=4 valign=bottom >
						&nbsp;
					</td>
					<td width=97 colspan=3 valign=bottom >
						��������
					</td>
					<td width=180 colspan=5 valign=bottom >
						&nbsp;${rs.dknx }
					</td>
				</tr>
				<tr height="35px">
					<td width=105 colspan=3 valign=bottom >
						�����ܽ��
					</td>
					<td width=464 colspan=12 valign=bottom >
						&nbsp;${rs.dkzje }
					</td>
				</tr>
				<tr height="35px">
					<td width=52 valign=bottom >
						����
					</td>
					<td width=514 colspan=14 valign=bottom >
						
							<span style='font-family:����;"Times New Roman"'>��һѧ�꣺</span><span
								lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><span
								style='font-family:����;"Times New Roman"'>�ڶ�ѧ�꣺</span><span
								lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><span
								style='font-family:����;"Times New Roman"'>����ѧ�꣺</span><span
								lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><span
								style='font-family:����;"Times New Roman"'>����ѧ�꣺</span><span
								lang=EN-US> </span>
						
						<p>
							<span style='font-family:����;"Times New Roman"'>����ѧ�꣺</span>
						</p>
					</td>
				</tr>
				<tr height="180px">
					<td width=569  colspan=15 valign=bottom
						>

					</td>
				</tr>
				
			</table>
			
		</html:form>
	</body>
</html>
