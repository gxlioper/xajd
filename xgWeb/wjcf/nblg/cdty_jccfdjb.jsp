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
		<html:form action="/wjcfcsmzwh" method="post">

			<p align=center style='text-align:center'>
				<span style='font-size:16.0pt;font-family:����;"Times New Roman"'>�ɶ�����ѧԺѧ�����(����)���ֵǼǱ�</span>
			</p>
			<p align="center">
				<span
					style='font-size:14.0pt;font-family:����;"Times New Roman";"Times New Roman"'>${rs.xymc}<logic:empty name="rs" property="xymc">&nbsp;&nbsp;&nbsp;&nbsp;</logic:empty>ϵ(��)&nbsp;&nbsp;
				</span>
				<span
					style='font-size:14.0pt;font-family:����;"Times New Roman";"Times New Roman"'>${rs.nj}�꼶&nbsp;&nbsp;
					${rs.bjmc }<logic:empty name="rs" property="xymc">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:empty>��&nbsp;&nbsp;</span>
				<span
					style='font-size:14.0pt;font-family:����;"Times New Roman";"Times New Roman"'>
					���ʱ��:
					<logic:empty name="rs" property="cxsqtime">
						&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;��
					</logic:empty> <logic:notEmpty name="rs" property="cxsqtime">
						${rs.cxsqtime }
					</logic:notEmpty> </span>
			</p>
			<table width="100%" id="rsT" class="printstyle">
				<tr>
					<td width=49 class="Normal">
						<p align=center style='text-align:center'>
							<span
								style='font-size:14.0pt;font-family:����;"Times New Roman";"Times New Roman"'>����</span>
						</p>
					</td>
					<td width=126 colspan=2 class="Normal">
						&nbsp;${rs.xm }
					</td>
					<td width=72 class="Normal">
						<p align=center style='text-align:center'>
							<span
								style='font-size:14.0pt;font-family:����;"Times New Roman";"Times New Roman"'>�Ա�</span>
						</p>
					</td>
					<td width=62 class="Normal">
						&nbsp;${rs.xb }
					</td>
					<td width=90 class="Normal">
						<p align=center style='text-align:center'>
							<span
								style='font-size:14.0pt;font-family:����;"Times New Roman";"Times New Roman"'>����<br />����</span>
						</p>
					</td>
					<td width=105 class="Normal">
						&nbsp;${rs.csrq }
					</td>
					<td width=35 class="Normal">
						<p align=center style='text-align:center'>
							<span
								style='font-size:14.0pt;font-family:����;"Times New Roman";"Times New Roman"'>����</span>
						</p>
					</td>
					<td width=80>
						&nbsp;${rs.mzmc }
					</td>
				</tr>
				<tr>
					<td width=49 class="Normal">
						<p align=center style='text-align:center'>
							<span
								style='font-size:14.0pt;font-family:����;"Times New Roman";"Times New Roman"'>ѧ��</span>
						</p>
					</td>
					<td width=126 colspan=2 class="Normal">
						&nbsp;${rs.xh }
					</td>
					<td width=72 class="Normal">
						<p align=center style='text-align:center'>
							<span
								style='font-size:14.0pt;font-family:����;"Times New Roman";"Times New Roman"'>����<br />��ò</span>
						</p>
					</td>
					<td width=62 class="Normal">
						&nbsp;${rs.zzmmmc }
					</td>
					<td width=90 class="Normal">
						<p align=center style='text-align:center'>
							<span
								style='font-size:14.0pt;font-family:����;"Times New Roman";"Times New Roman"'>��ʱ���ܺδ���</span>
						</p>
					</td>
					<td width=220 colspan=3 class="Normal">
						&nbsp;
						<logic:notEmpty name="rs">
						${rs.cfsj}��Ϊ${rs.cfyymc}�ܵ�${rs.cflbmc}����
						</logic:notEmpty>
					</td>
				</tr>
				<tr>
					<td width=49 height="300px" class="Normal">
						<p align=center style='text-align:center'>
							<span
								style='font-size:14.0pt;font-family:����;"Times New Roman";"Times New Roman"'>��Ҫ������ʵ</span>
						</p>
					</td>
					<td width=590 colspan=8 valign="top">
						&nbsp;${rs.bz }
					</td>
				</tr>
				<tr>
					<td width=49 rowspan=3 class="Normal">
						<p align=center style='text-align:center'>
							<span
								style='font-size:14.0pt;font-family:����;"Times New Roman";"Times New Roman"'>�������</span>
						</p>
					</td>
					<td height="135px" align="center">
						<p align=center style='text-align:center'>
							<span
								style='font-size:14.0pt;font-family:����;"Times New Roman";"Times New Roman"'>ϵ</span><span
								lang=EN-US style='font-size:14.0pt;'>(</span><span
								style='font-size:14.0pt;font-family:����;"Times New Roman";"Times New Roman"'>��</span><span
								lang=EN-US style='font-size:14.0pt;'>)</span>
						</p>
						<p align=center style='text-align:center'>
							<span
								style='font-size:14.0pt;font-family:����;"Times New Roman";"Times New Roman"'>���</span>
						</p>
					</td>
					<td width=479 colspan=7 valign=bottom class="Normal">
						<p style='word-break:break-all'>
							<span
								style='font-size:14.0pt;font-family:����;"Times New Roman";"Times New Roman"'>ϵ�������쵼ǩ�֣�</span><span
								lang=EN-US style='font-size:14.0pt;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span><span style='font-size:14.0pt;  font-family:����;"Times New Roman"'>�����£�</span>
						</p>
						<p align=right style='text-align:right;'>
							<span lang=EN-US style='font-size:14.0pt;'>&nbsp;</span><span
								style='font-size:14.0pt;
  font-family:����;
  &quot;Times New Roman&quot;'>��</span><span
								lang=EN-US style='font-size:14.0pt;'>&nbsp; </span><span
								style='font-size:14.0pt;
  font-family:����;
  &quot;Times New Roman&quot;'>��</span><span
								lang=EN-US style='font-size:14.0pt;'>&nbsp; </span><span
								style='font-size:14.0pt;
  font-family:����;
  &quot;Times New Roman&quot;'>��</span><span
								lang=EN-US style='font-size:14.0pt;'>&nbsp; </span>
						</p>
					</td>
				</tr>
				<tr>
					<td height="135px" align="center">
						<p align=center style='text-align:center'>
							<span
								style='font-size:14.0pt;font-family:����;"Times New Roman";"Times New Roman"'>ѧ����</span>
						</p>
						<p align=center style='text-align:center'>
							<span
								style='font-size:14.0pt;font-family:����;"Times New Roman";"Times New Roman"'>���</span>
						</p>
					</td>
					<td width=479 colspan=7 valign=bottom class="Normal">
						<p align=right style='text-align:right;
  word-break:break-all'>
							<span style='font-size:14.0pt;  font-family:����;"Times New Roman"'>���쵼ǩ�֣�</span><span
								lang=EN-US style='font-size:14.0pt;
  '>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span><span style='font-size:14.0pt;  font-family:����;"Times New Roman"'>�����£�</span>
						</p>
						<p align=right style='text-align:right'>
							<span lang=EN-US style='font-size:14.0pt;'>&nbsp;</span><span
								style='font-size:14.0pt;
  font-family:����;
  &quot;Times New Roman&quot;'>��</span><span
								lang=EN-US style='font-size:14.0pt;'>&nbsp; </span><span
								style='font-size:14.0pt;
  font-family:����;
  &quot;Times New Roman&quot;'>��</span><span
								lang=EN-US style='font-size:14.0pt;'>&nbsp; </span><span
								style='font-size:14.0pt;
  font-family:����;
  &quot;Times New Roman&quot;'>��</span><span
								lang=EN-US style='font-size:14.0pt;'>&nbsp; </span>
						</p>
					</td>
				</tr>
				<tr>
					<td height="135px" align="center">
						<p align=center style='text-align:center'>
							<span
								style='font-size:14.0pt;font-family:����;"Times New Roman";"Times New Roman"'><bean:message key="lable.xb" /></span>
						</p>
						<p align=center style='text-align:center'>
							<span
								style='font-size:14.0pt;font-family:����;"Times New Roman";"Times New Roman"'>���</span>
						</p>
					</td>
					<td width=479 colspan=7 valign=bottom class="Normal">
						<p align=right style='text-align:right;word-break:break-all'>
							<span
								style='font-size:14.0pt;font-family:����;"Times New Roman";"Times New Roman"'>��</span><span
								lang=EN-US style='font-size:14.0pt;'>&nbsp;</span><span
								style='font-size:14.0pt;
  font-family:����;
  &quot;Times New Roman&quot;'>��</span><span
								lang=EN-US style='font-size:14.0pt;'>&nbsp; </span><span
								style='font-size:14.0pt;
  font-family:����;
  &quot;Times New Roman&quot;'>��</span><span
								lang=EN-US style='font-size:14.0pt;'>&nbsp; </span>
						</p>
					</td>
				</tr>
				<tr>
					<td width=49 height="65px" align="center">
						<p align=center style='text-align:center'>
							<span
								style='font-size:14.0pt;font-family:����;"Times New Roman";"Times New Roman"'>��ע</span>
						</p>
					</td>
					<td width=570 colspan=8 class="Normal">
						&nbsp;
					</td>
				</tr>
				<tr height=0>
					<td width=49></td>
					<td width=91></td>
					<td width=35></td>
					<td width=72></td>
					<td width=62></td>
					<td width=90></td>
					<td width=105></td>
					<td width=35></td>
					<td width=80></td>
				</tr>
			</table>
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
		</html:form>
	</body>
</html>

</html>
