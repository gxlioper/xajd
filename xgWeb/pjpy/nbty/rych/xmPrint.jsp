<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">
<head>
	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" http-equiv="no-cache" />
	<meta http-equiv="Cache-Control" http-equiv="no-cache" />
	<meta http-equiv="Expires" http-equiv="0" />
	<meta name="Copyright" content="������� zfsoft" />
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="../skin1/style/main.css" type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/sztzFunction.js"></script>
	
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	</head>
	<style media="print">
			.noprint{
				display:none
			}
			.print{
				display:block
			}
		</style>
	<body>
	<center>
		<div align="center">
			<h3>������һְҵ����ѧԺ<logic:notEmpty name="rs"><bean:write name="rs" property="xn" /></logic:notEmpty>ѧ���</h3> 
		</div>
		<h2>
		<div align="center" style=" font-size:23px;">
			<logic:notEmpty name="rs" >
				<bean:write name="rs" property="rychmc" />�ǼǱ�
			</logic:notEmpty>
			<logic:empty name="rs">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ǼǱ�
			</logic:empty>
		</div>
		</h2>
		<table class="tbstyle" height="750">
			<tr>
				<td width=5%>
					����
				</td>
				<td width=7%>
					&nbsp;
					<logic:notEmpty name="rs">
						<bean:write name="rs" property="xm" />
					</logic:notEmpty>
				</td>
				<td width=5%>
					�Ա�
				</td>
				<td width=7%>
					&nbsp;
					<logic:notEmpty name="rs">
						<bean:write name="rs" property="xb" />
					</logic:notEmpty>
				</td>
				<td width=9%>
					��������
				</td>
				<td width=5%>
					&nbsp;
					<logic:notEmpty name="rs">
						<bean:write name="rs" property="csrq" />
				</td>
				</logic:notEmpty>
				<td width=8%>
					����
				</td>
				<td width=8%>
					&nbsp;
					<logic:notEmpty name="rs">
						<bean:write name="rs" property="mzmc" />
					</logic:notEmpty>
				</td>
				<td width=8%>
					����
				</td>
				<td width=8%>
					&nbsp;
					<logic:notEmpty name="rs">
						<bean:write name="rs" property="jg" />
					</logic:notEmpty>
				</td>
			</tr>
			<tr>
				<td >
					������ò
				</td>
				<td colspan=2>
					&nbsp;
					<logic:notEmpty name="rs">
						<bean:write name="rs" property="zzmmmc" />
					</logic:notEmpty>
				</td>
				<td>
					����ְ��
				</td>
				<td colspan="2">
					&nbsp;
					<logic:notEmpty name="rs">
						<bean:write name="rs" property="xrzw" />
					</logic:notEmpty>
				</td>
				<td colspan=2>
					 ����ְʱ��
				</td>
				<td colspan=2>
					&nbsp;
					<logic:notEmpty name="rs">
						<bean:write name="rs" property="rxzsj" />
					</logic:notEmpty>
				</td>
			</tr>
			<tr>
				<td height=7%>
					רҵ���༶
				</td>
				<td colspan=3>
					<logic:notEmpty name="rs">
						<bean:write name="rs" property="zymc" /><br/>
					</logic:notEmpty>
					&nbsp;
					<logic:notEmpty name="rs">
						<bean:write name="rs" property="bjmc" />
					</logic:notEmpty>
				</td>
				<td>
					�ۺ�����
				</td>
				<td>
					&nbsp;
				</td>
				<td colspan=2>
					ѧ��ƽ���ɼ�
				</td>
				<td colspan=2>
					&nbsp;
					<logic:notEmpty name="rs">
						<bean:write name="rs" property="xnpjcj" />
					</logic:notEmpty>
				</td>
			</tr>
			<tr height=10%>
				<td colspan=2>
					�����Ƽ�ʱ��
				</td>
				<td colspan=8>
					&nbsp;
					<logic:notEmpty name="rs">
						<bean:write name="rs" property="hjsjmc" />
					</logic:notEmpty>
				</td>
			</tr>
			<tr height=25% width=5%>
				<td align=center>
					��
					<br>
					Ҫ
					<br>
					��
					<br>
					��
				</td>
				<td colspan=9>
					&nbsp;
					<logic:notEmpty name="rs">
						<bean:write name="rs" property="zysj" />
					</logic:notEmpty>
				</td>
			</tr>
			<tr height=25% width=5%>
				<td align=center>
					��
					<br>
					��
					<br>
					��
					<br>
					��
					<br>
					��
				</td>
				<td colspan=4>
					&nbsp;
						<span style="float:right; " ><br><br><br>ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
                        ����:</span>
				</td>
				<td align=center width=5%>
					��
					<br>
					Ժ
					<br>
					ϵ
					<br>
					��
					<br>
					��
				</td>
				<td colspan=4>
					&nbsp;
						<span style="float:right; " ><br><br><br>����:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
                        ����:</span>
				</td>
			</tr>
			<tr height=25%>
				<td align=center >
					ѧ
					<br>
					��
					<br>
					��
					<br>
					��
					<br>
					��
				</td>
				<td colspan=4>
					&nbsp;
						<span style="float:right; " ><br><br><br>����:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
                        ����:</span>
				</td>
				<td align=center  width=5%>
					ѧ
					<br>
					Ժ
					<br>
					��
					<br>
					��
				</td>
				<td colspan=4>
					&nbsp;
						<span style="float:right; " ><br><br><br>����:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
                        ����:</span>
				</td>
			</tr>
		</table>
		</center>
		<div class="noprint" align="center">
			<input type='button' class='button2' value='ҳ������'
				onclick="WebBrowser.ExecWB(8,1);return false;">
			<input type='button' class='button2' value='��ӡԤ��'
				onclick="WebBrowser.ExecWB(7,1);return false;">
			<input type='button' class='button2' value='ֱ�Ӵ�ӡ'
				onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>
	</body>
</html>
