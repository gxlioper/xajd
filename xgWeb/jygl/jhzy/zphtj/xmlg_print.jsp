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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
		<style media="print" type="text/css">
  .brk{
	page-break-after:always;
   }
   .noPrin{display:none}
</style>
	<script language="javascript">
</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<html:form action="/jhzyzphcs" method="post">
			<center><h3>������<bean:message key="lable.xsgzyxpzxy" /><bean:write name="rs" property="xn"/>���ҵ��������У�����</h3></center>
			<fieldset>
				<table class="tbstyle" border=1 cellspacing=0 cellpadding=0
					width='100%'>
					<tr>
						<td width=63 class="Normal">
							<p align=center style='text-align: center; line-height: 21.0pt;'>
								<span style='font-size: 12.0pt; font-family: ����'>����</span>
							</p>
						</td>
						<td width=135 colspan=3 class="Normal">
						<p align=center style='text-align: center; line-height: 21.0pt;'>
							<span style='font-size: 12.0pt; font-family: ����'>
							<bean:write name="rs" property="xm"/></span>
							</p>
						</td>
						<td width=55 colspan=2 class="Normal">
							<p align=center style='text-align: center; line-height: 21.0pt;'>
								<span style='font-size: 12.0pt; font-family: ����'>רҵ</span>
							</p>
						</td>
						<td width=161 colspan=2 class="Normal">
							<span style='font-size: 12.0pt; font-family: ����'>
							<bean:write name="rs" property="zydm"/></span>
						</td>
						<td width=55 class="Normal">
							<p align=center style='text-align: center; line-height: 21.0pt;'>
								<span style='font-size: 12.0pt; font-family: ����'>ѧ��</span>
							</p>
						</td>
						<td width=129 colspan=2 class="Normal">
							<span style='font-size: 12.0pt; font-family: ����'>
							<bean:write name="rs" property="xh"/></span>
						</td>
					</tr>
					<tr>
						<td width=109 colspan=2 class="Normal">
							<p align=center style='text-align: center; line-height: 21.0pt;'>
								<span style='font-size: 12.0pt; font-family: ����'>��Դ���ڵ�</span>
							</p>
						</td>
						<td width=291 colspan=5 class="Normal">
							<p align=center style='text-align: center; line-height: 21.0pt;'>
								<span style='font-size: 12.0pt; font-family: ����'><bean:write name="rs" property="sysheng"/></span>
							</p>
						</td>
						<td width=81 colspan=3 class="Normal">
							<p align=center style='text-align: center; line-height: 21.0pt;'>
								<span class=GramE><span
									style='font-size: 12.0pt; font-family: ����'>��</span>
								</span><span lang=EN-US style='font-size: 12.0pt; font-family: ����'>&nbsp;
								</span><span style='font-size: 12.0pt; font-family: ����'>��</span>
							</p>
						</td>
						<td width=116 class="Normal">
						<span style='font-size: 12.0pt; font-family: ����'>
						<bean:write name="rs" property="yzbm"/></span>
						</td>
					</tr>
					<tr>
						<td width=152 colspan=3 class="Normal">
							<p align=center style='text-align: center; line-height: 21.0pt;'>
								<span class=GramE><span
									style='font-size: 12.0pt; font-family: ����'>�ּ�ͥ</span>
								</span><span style='font-size: 12.0pt; font-family: ����'>��ϸ��ַ</span>
							</p>
						</td>
						<td width=445 colspan=8 class="Normal">
							<span style='font-size: 12.0pt; font-family: ����'>
							<bean:write name="rs" property="jtdz"/></span>
						</td>
					</tr>
					<tr>
						<td width=211 colspan=5 class="Normal">
							<p align=center style='text-align: center; line-height: 21.0pt;'>
								<span style='font-size: 12.0pt; font-family: ����'>��ϵ��ʽ���ֻ���<span
									lang=EN-US>E-mail</span>��</span>
							</p>
						</td>
						<td width=386 colspan=6 class="Normal">
							<span style='font-size: 12.0pt; font-family: ����'>
							<bean:write name="rs" property="lxfs"/></span>
						</td>
					</tr>
					<tr>
						<td width=152 colspan=3 class="Normal">
							<p align=center style='text-align: center; line-height: 16.0pt;'>
								<span style='font-size: 12.0pt; font-family: ����'>�� �� �� ��</span>
							</p>
						</td>
						<td width=445 colspan=8 class="Normal">
							<p style='line-height: 16.0pt;'>
								<span style='font-size: 12.0pt; font-family: ����'><html:checkbox name="rs" property="sqly1" value="1" disabled="true"></html:checkbox><span
									lang=EN-US>&nbsp; </span>��λ���ܽ��յ���</span>
							</p>
							<p style='line-height: 16.0pt;'>
								<span style='font-size: 12.0pt; font-family: ����'><html:checkbox name="rs" property="sqly2" value="1" disabled="true"></html:checkbox><span
									lang=EN-US>&nbsp; </span>�ݲ���ҵ</span>
							</p>
							<p style='line-height: 16.0pt;'>
								<span style='font-size: 12.0pt; font-family: ����'><html:checkbox name="rs" property="sqly3" value="1" disabled="true"></html:checkbox><span
									lang=EN-US>&nbsp; </span>���ڰ����������</span>
							</p>
							<p style='line-height: 16.0pt;'>
								<span style='font-size: 12.0pt; font-family: ����'><html:checkbox name="rs" property="sqly4" value="1" disabled="true"></html:checkbox><span
									lang=EN-US>&nbsp; </span>�����������ҹ���</span><span style='font-family: ����'>����Է�������Դ��</span>
							</p>
							<p style='line-height: 16.0pt;'>
								<span style='font-size: 12.0pt; font-family: ����'><html:checkbox name="rs" property="sqly5" value="1" disabled="true"></html:checkbox><span
									lang=EN-US>&nbsp; </span>������������ԺУ</span><span style='font-family: ����'>��ר�������У�</span>
							</p>
							<p style='line-height: 16.0pt;'>
								<span style='font-size: 12.0pt; font-family: ����'><html:checkbox name="rs" property="sqly" value="1" disabled="true"></html:checkbox><span
									lang=EN-US>&nbsp; </span>���������ע��<span lang=EN-US>
									<bean:write name="rs" property="sqly6"/>
									</span>
								</span>
							</p>
						</td>
					</tr>
					<tr>
						<td width=152 colspan=3 class="Normal">
							<p align=center style='text-align: center; line-height: 16.0pt;'>
								<span style='font-size: 12.0pt; font-family: ����'>��������У����</span>
							</p>
						</td>
						<td width=445 colspan=8 class="Normal">
							<span style='font-size: 12.0pt; font-family: ����'>
							<bean:write name="rs" property="lxqx"/>   </span>
						</td>
					</tr>
					<tr>
						<td width=152 colspan=3 class="Normal">
						<p >
								<span style='font-size: 12.0pt; font-family: ����'>����ǩ����</span>
						</p>
						</td>
						<td width=445 colspan=8 class="Normal">
							<span style='font-size: 12.0pt; font-family: ����'>
							<bean:write name="rs" property="brqm"/></span>
						</td>
					</tr>
					<tr>
						<td width=152 colspan=3 class="Normal">
							<p style='line-height: 16.0pt;'>
								<span style='font-size: 12.0pt; font-family: ����'><bean:message key="lable.xsgzyxpzxy" />�������:</span>
							</p>
							<p style='line-height: 16.0pt;'></p>
						</td>
						<td width=800 colspan=8 class="Normal" height="150">
							<span style='font-size: 12.0pt; font-family: ����'>
							<bean:write name="rs" property="xyyj"/></span>
						</td>
					</tr>
					<tr>
						<td width=152 colspan=3 class="Normal">
							<p style='line-height: 16.0pt;'>
								<span style='font-size: 12.0pt; font-family: ����'>ѧ�������:</span>
							</p>
							<p style='line-height: 16.0pt;'></p>
						</td>
						<td width=800 colspan=8 class="Normal" height="150">
							<span style='font-size: 12.0pt; font-family: ����'>
							<bean:write name="rs" property="xxyj"/></span>
						</td>
					</tr>
					<tr height=0>
						<td width=63 height="2" class="Normal"></td>
						<td width=46 class="Normal"></td>
						<td width=43 class="Normal"></td>
						<td width=46 class="Normal"></td>
						<td width=13 class="Normal"></td>
						<td width=42 class="Normal"></td>
						<td width=147 class="Normal"></td>
						<td width=14 class="Normal"></td>
						<td width=55 class="Normal"></td>
						<td width=13 class="Normal"></td>
						<td width=116 class="Normal"></td>
					</tr>
					<tr>
						<td colspan="12">
							<p>
								<span style='font-family: ����'>��ע��</span>
							</p>
							<p>
								<span lang=EN-US style='font-family: ����'>1.</span><span
									style='font-family: ����'>�����ɱ�ҵ��������д��һʽ���ݣ�ѧ�����˺�ѧ������ҵָ�����ĸ�����һ�ݣ���Ϊ�й������������ݣ�</span>
							</p>
							<p style='line-height: 16.0pt;'>
								<span lang=EN-US style='font-family: ����'>2.</span><span
									style='font-family: ����'>������У�ڼ�<span lang=EN-US>,</span>У��ֻ�ṩ��ҵ�й�����Ĵ���<span
									lang=EN-US>,</span>��ҵ�������������ڼ����������顢���е���Ҫ���������ģ����涨����������������Ӧת����ϵ�����ܵ�λ������ҵ����������У�ڼ䣬ѧ��û��ѧ������������У��������</span>
							</p>
							<p>
								<span lang=EN-US style='font-family: ����'>3.</span><span
									style='font-family: ����'>��ҵ��������У�ڼ䣬��ҵ���Կɼ�����ʵǩԼ��ҵ��λ��������ǲ��Ҳ����ʱ����ת��ԭ�����йس����˲��г��������´������仧�����ҵ������ǩ��ҵЭ����йز��ϣ���ʱ�����ʼģ�������ؿ�ר���ʼ������ˡ�ί�����ˣ�������֤����ί���飩��У��ҵָ�����Ľ�������</span>
							</p>
							<p style='line-height: 16.0pt;'>
								<span lang=EN-US style='font-family: ����'>4.</span><span
									style='font-family: ����'>������У����������δ��ʵ��λ�ı�ҵ��������У����<span
									class=GramE>Ǩ��</span>����Դ���ڵ��������¾֣�</span>
							</p>
							<p style='line-height: 16.0pt;'>
								<span lang=EN-US style='font-family: ����'>5.</span><span
									style='font-family: ����;'>��������δ�����ˣ�������<bean:message key="lable.xsgzyxpzxy" />��ҵָ���������ľ������ս���Ȩ��</span>
							</p>
						</td>
					</tr>
				</table>
			</fieldset>
			<div class='noPrin' align="center">
				<input type='button' class='button2' value='ҳ������'
					onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='��ӡԤ��'
					onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='ֱ�Ӵ�ӡ'
					onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>
		</html:form>
	</body>
</html>
