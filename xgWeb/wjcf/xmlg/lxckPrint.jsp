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
	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<style media='print'>
		.noPrin{display:none;}
	</style>
	<style>
	<!--
	.Section1
		{page:Section1;}
	.style1 {
		font-size: 24px;
		font-weight: bold;
	}
	-->
	</style>
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>

	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
</head>
<body>
	<html:form action="/wjcfxmlgwh">
		<div align="center" style="font-size:22px;font:'����' ">
			<b>${xxmc }ѧ�������У�쿴�������</b>
		</div>
		<p align="right">
			���ʱ��
			<u>&nbsp;&nbsp;&nbsp;${rs.year }&nbsp;&nbsp;&nbsp; </u>��
			<u>&nbsp;&nbsp;${rs.mon }&nbsp;&nbsp; </u>��
			<u>&nbsp;&nbsp;&nbsp;${rs.date }&nbsp;&nbsp; </u>��&nbsp;&nbsp;
		</p>
		<div align=center>
			<table class="printstyle" width="98%">
				<tr>
					<th width=85 colspan=2 class="Normal">
						<div align="center">
							ѧ������
						</div>
					</th>
					<td width=84 class="Normal" align="center">
						&nbsp;${rs.xm }
					</td>
					<th width=60 class="Normal">
						<div align="center">
							��&nbsp; ��
						</div>
					</th>
					<td width=48 class="Normal" align="center">
						&nbsp;${rs.xb }
					</td>
					<th width=60 class="Normal">
						<div align="center">
							ѧ&nbsp; ��
						</div>
					</th>
					<td width=72 colspan=2 class="Normal" align="center">
						&nbsp;${rs.xh }
					</td>
					<th width=72 colspan=2 class="Normal">
						<div align="center">
							������ò
						</div>
					</th>
					<td width=75 class="Normal" align="center">
						&nbsp;${rs.zzmmmc }
					</td>
				</tr>
				<tr>
					<th width=85 colspan=2 class="Normal">
						<div align="center">
							Ժ&nbsp;&nbsp;&nbsp; ϵ
						</div>
					</th>
					<td width=84 class="Normal" align="center">
						&nbsp;${rs.xymc }
					</td>
					<th width=60 class="Normal">
						<div align="center">
							��&nbsp; ��
						</div>
					</th>
					<td width=108 colspan=2 class="Normal" align="center">
						&nbsp;${rs.bjmc }
					</td>
					<th width=72 colspan=2 class="Normal">
						<div align="center">
							��&nbsp; ��
						</div>
					</th>
					<td width=147 colspan=3 class="Normal" align="center">
						&nbsp;${rs.jg }
					</td>
				</tr>
				<tr>
					<th width=169 colspan=3 class="Normal">
						<div align="center">
							ԭ�����ļ�(�ļ���)
						</div>
					</th>
					<td width=387 colspan=8 class="Normal" align="center">
						&nbsp;${rs.cfwh }
					</td>
				</tr>
				<tr>
					<th width=85 colspan=2 class="Normal">
						<div align="center">
							�ܴ�������
						</div>
					</th>
					<td width=288 colspan=5 class="Normal" align="center">
						&nbsp;${rs.cflbmc }
					</td>
					<th width=96 colspan=2 class="Normal">
						<div align="center">
							�ܴ���ʱ��
						</div>
					</th>
					<td width=87 colspan=2 class="Normal" align="center">
						&nbsp;${rs.cfsj }
					</td>
				</tr>
				<tr>
					<th width=85 colspan=2 class="Normal">
						<div align="center">
							��У�쿴��
						</div>
					</th>
					<td width=471 colspan=9 class="Normal">
						<div align="center">
							${rs.lxcfsj }��${rs.lxcksj }
						</div>
					</td>
				</tr>
				<tr>
					<th width=52 class="Normal">
						<div align="center">
							<br>
							<br>
							<br>
							<br>
							ѧ
							<br />
							��
							<br />
							��
							<br />
							��
							<br />
							��
							<br />
							��
							<br />
							Ҫ
							<br />
							��
							<br>
							<br>
							<br>
							<br>
							<br>
						</div>
					</th>
					<td width=503 colspan=10 valign=top class="Normal">
						��˵���ܴ�������������ı��������ȡ�õĽ����������������ɣ�
						<br />
						�����������������棩
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<p align="center">
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p align="center">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.xsbx }
						</p>
						<p align="center">
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p align="center">
							&nbsp;
						</p>
						<p align="center">
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p align="center">
							&nbsp;
						</p>
						<p align="center">
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p align="center">
							&nbsp;
						</p>
						
						<div align="right">
							����ǩ����
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						
						
						<div align="right">
							��&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp; ��
						</div>
						
					</td>
				</tr>
			</table>
			<div style='page-break-before:always;'>
				&nbsp;
			</div>
			<table class="printstyle" width="98%">
				<tr>
					<th width=52 class="Normal">
						<div align="center">
							��
							<br />
							��
							<br />
							��
							<br />
							��
							<br />
							��
							<br />
							��
						</div>
					</th>
					<td width=503 colspan=10 valign=top class="Normal">
						���������ܴ���һ�����İ༶���֡�ѧҵ�������������
						<p align="center">
							&nbsp;${rs.bjpyyj }
						</p>
						<p>
							&nbsp;
						</p>
						<p align="center">
							&nbsp;
						</p>
						
						
						<div align="right">
							������ǩ����
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						
						
						<div align="right">
							��&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp; ��
						</div>
						
					</td>
				</tr>
				<tr>
					<th width=52 class="Normal">
						<div align="center">
							��
							<br />
							��
							<br />
							Ա
							<br />
							��
							<br />
							��
							<br />
							��
							<br />
							��
						</div>
					</th>
					<td width=503 colspan=10 class="Normal">
						���������ܴ���һ�����ĸ�����������������
						<p align="center">
							&nbsp;${rs.fdyjdyj }
						</p>
						<p>
							&nbsp;
						</p>
						<p align="center">
							&nbsp;
						</p>
						
						
						<div align="right">
							����Աǩ����
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						
						
						<div align="right">
							��&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp; ��
						</div>
						
					</td>
				</tr>
				<tr>
					<th width=52 class="Normal">
						<div align="center">
							Ժ
							<br />
							ϵ
							<br />
							��
							<br />
							��
						</div>
					</th>
					<td width=503 colspan=10 class="Normal">
						���԰���������븨��Ա����������,�������������Ƿ����ã��Ƿ���ϰ��ڽ���쿴��������������������
						<p align="center">
							&nbsp;${rs.xyyj }
						</p>
						<p>
							&nbsp;
						</p>
						<p align="center">
							&nbsp;
						</p>
						
						<div align="right">
							������ǩ����<bean:message key="lable.xsgzyxpzxy" />���£���
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						
						
						<div align="right">
							��&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp; ��
						</div>
						
					</td>
				</tr>
				<tr>
					<th width=52 class="Normal">
						<div align="center">
							ѧ
							<br />
							��
							<br />
							��
							<br />
							��
							<br />
							��
						</div>
					</th>
					<td width=503 colspan=10 class="Normal">
						���ݡ�������<bean:message key="lable.xsgzyxpzxy" />ѧ��Υ�ʹ��ֹ���涨��������֮�涨,ͬ����
						<br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ͬѧ����У�쿴�ڡ�
						
						<div align="right">
							������ǩ��(����)��
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						
						
						<div align="right">
							��&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp; ��
						</div>
						
					</td>
				</tr>
				<tr height=0>
					<td width=52 class="Normal"></td>
					<td width=33 class="Normal"></td>
					<td width=84 class="Normal"></td>
					<td width=60 class="Normal"></td>
					<td width=48 class="Normal"></td>
					<td width=60 class="Normal"></td>
					<td width=36 class="Normal"></td>
					<td width=36 class="Normal"></td>
					<td width=60 class="Normal"></td>
					<td width=12 class="Normal"></td>
					<td width=75 class="Normal"></td>
				</tr>
			</table>
		</div> 
		<div>
  &nbsp;&nbsp;��ע:1������һʽ����,ѧ������<bean:message key="lable.xsgzyxpzxy" />����һ�ݴ浵;2����ѧ������������һ��,��ѧ�����浵.</div>
	</html:form>
	<br />
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
		<%--    </div>--%>
</body>
</html:html>
