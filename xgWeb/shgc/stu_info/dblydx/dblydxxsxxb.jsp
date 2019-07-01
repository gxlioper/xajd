<%@ page language="java" pageEncoding="GB2312"
	contentType="text/html;charset=GBK"%>

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
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="/style/main.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/commFunction.js"></script>
	<script language="javascript" src="js/webPrint.js"></script>
</head>
<style media="print" type="text/css">
.brk{
	page-break-after:always;
}
</style>
<body>
	<html:form action="stu_info_query.do" method="post">
		<center>
			<h3>
				������ҵ��ѧѧ��������Ϣ�ǼǱ�
			</h3>
			<table �� width="100%" class="tbstyle" id="rsTable">
				<tr>
					<td align="center" width="80">
						<bean:message key="lable.xsgzyxpzxy" />��������
					</td>
					<td width="120">
						<bean:write name="rs" property="XYMC" />
					</td>
					<td align="center" width="80">
						רҵ��
					</td>
					<td width="120">
						<bean:write name="rs" property="ZYMC" />
					</td>
					<td align="center" width="80">
						�༶��
					</td>
					<td width="120">
						<bean:write name="rs" property="BJMC" />
					</td>
					<td align="center" width="80">
						ѧ�ţ�<bean:write name="rs" property="XH" />
					</td>
				</tr>
				<tr height="30">
					<td align="center" width="80">
						����
					</td>
					<td width="120">
						<bean:write name="rs" property="XM" />
					</td>
					<td align="center" width="80">
						��������
					</td>
					<td width="120">
						<bean:write name="rs" property="CSRQ" />
					</td>
					<td align="center" width="80">
						�Ա�:
					</td>
					<td width="120">
						<bean:write name="rs" property="XB" />
					</td>
					<td rowspan="4" width="120">
						<img src="123.jpg" style="width:100%;height:100%">
					</td>
				</tr>
				<tr height="30">
					<td align="center">
						������ò
					</td>
					<td>
						<bean:write name="rs" property="ZZMMMC" />
					</td>
					<td align="center">
						����
					</td>
					<td>
						<bean:write name="rs" property="MZMC" />
					</td>
					<td align="center">
						�뵳����ʱ��:
					</td>
					<td>
						<bean:write name="rs" property="JRGCDSJ" />
					</td>
				</tr>
				<tr height="40">
					<td align="center">
						���֤����
					</td>
					<td colspan="3">
						<bean:write name="rs" property="SFZHM" />
					</td>
					<td align="center">
						�Ƿ����
					</td>
					<td>
						<bean:write name="rs" property="SFDK" />
					</td>
				</tr>
				<tr height="30">
					<td align="center">
						�ֻ�����
					</td>
					<td>
						<bean:write name="rs" property="SJHM" />
					</td>
					<td align="center">
						E-mail
					</td>
					<td colspan="3">
						<bean:write name="rs" property="DZYX" />
					</td>
				</tr>
				<tr height="30">
					<td align="center">
						��Ȥ����
					</td>
					<td colspan="3">
						<bean:write name="rs" property="AH" />
					</td>
					<td align="center">
						�س�
					</td>
					<td colspan="2">
						<bean:write name="rs" property="TC" />
					</td>
				</tr>
				<tr height="30">
					<td align="center">
						�� ��
					</td>
					<td colspan="3">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
						<bean:write name="rs" property="qslh" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp; ��¥&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;
						<bean:write name="rs" property="qssh" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;��
					</td>
					<td align="center">
						���ҵ绰
					</td>
					<td colspan="2">
						<bean:write name="rs" property="QSDH" />
					</td>
				</tr>
				<tr height="80">
					<td align="center">
						��ͥ��ϸ��ַ
					</td>
					<td colspan="6">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						ʡ
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						�أ�����
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						���磩
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�壨�ֵ���
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;С��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;��Ԫ
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;��
					</td>
				</tr>
				<tr height="30">
					<td align="center">
						��������
					</td>
					<td>
						<bean:write name="rs" property="JTYB" />
					</td>
					<td align="center">
						��ͥ�绰
					</td>
					<td>
						<bean:write name="rs" property="JTDH" />
					</td>
					<td align="center">
						��ͥ�������
					</td>
					<td colspan="2">
						�� �� ԣ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; �� һ ��
						<br/>
						�� �� �� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� �� ��
					</td>
				</tr>
				<tr height="30">
					<td align="center">
						��ͥ��Ҫ��Ա����
					</td>
					<td align="center">
						�뱾�˹�ϵ
					</td>
					<td colspan="2" align="center">
						������λ
					</td>
					<td align="center">
						ְ��
					</td>
					<td align="center">
						��λ�绰
					</td>
					<td align="center">
						�ֻ�����
					</td>
				</tr>
				<tr height="30">
					<td height="30">
						<bean:write name="rs" property="JTCY1_XM" />
					</td>
					<td height="30">
						<bean:write name="rs" property="JTCY1_CH" />
					</td>
					<td height="30" colspan="2">
						<bean:write name="rs" property="JTCY1_GZDZ" />
					</td>
					<td height="30">
						<bean:write name="rs" property="JTCY1_ZW" />
					</td>
					<td height="30">
						<bean:write name="rs" property="JTCY1_LXDH2" />
					</td>
					<td height="30">
						<bean:write name="rs" property="JTCY1_LXDH1" />
					</td>
				</tr>
				<tr height="30">
					<td height="30">
						<bean:write name="rs" property="JTCY2_XM" />
					</td>
					<td height="30">
						<bean:write name="rs" property="JTCY2_CH" />
					</td>
					<td height="30" colspan="2">
						<bean:write name="rs" property="JTCY2_GZDZ" />
					</td>
					<td height="30">
						<bean:write name="rs" property="JTCY2_ZW" />
					</td>
					<td height="30">
						<bean:write name="rs" property="JTCY2_LXDH2" />
					</td>
					<td height="30">
						<bean:write name="rs" property="JTCY2_LXDH1" />
					</td>
				</tr>
				<tr height="30">
					<td height="30">
						<bean:write name="rs" property="JTCY3_XM" />
					</td>
					<td height="30">
						<bean:write name="rs" property="JTCY3_CH" />
					</td>
					<td height="30" colspan="2">
						<bean:write name="rs" property="JTCY3_GZDZ" />
					</td>
					<td height="30">
						<bean:write name="rs" property="JTCY3_ZW" />
					</td>
					<td height="30">
						<bean:write name="rs" property="JTCY3_LXDH2" />
					</td>
					<td height="30">
						<bean:write name="rs" property="JTCY3_LXDH1" />
					</td>
				</tr>
				<tr height="30">
					<td align="center">
						����������Ҫ
						<br/>
						����ϵ
						<br/>
						�� ��
					</td>
					<td align="center">
						�뱾�˹�ϵ
					</td>
					<td colspan="2" align="center">
						������λ
					</td>
					<td align="center">
						ְ��
					</td>
					<td align="center">
						��λ�绰
					</td>
					<td align="center">
						�ֻ�����
					</td>
				</tr>
				<tr height="30">
					<td height="30">
						<bean:write name="rs" property="SHGXXM1" />
					</td>
					<td height="30">
						<bean:write name="rs" property="SHGXGX1" />
					</td>
					<td height="30" colspan="2">
						<bean:write name="rs" property="SHGXGZDW1" />
					</td>
					<td height="30">
						<bean:write name="rs" property="SHGXZW1" />
					</td>
					<td height="30">
						<bean:write name="rs" property="SHGXDWDH1" />
					</td>
					<td height="30">
						<bean:write name="rs" property="SHGXSJHM1" />
					</td>
				</tr>
				<tr height="30">
					<td height="30">
						<bean:write name="rs" property="SHGXXM2" />
					</td>
					<td height="30">
						<bean:write name="rs" property="SHGXGX2" />
					</td>
					<td height="30" colspan="2">
						<bean:write name="rs" property="SHGXGZDW2" />
					</td>
					<td height="30">
						<bean:write name="rs" property="SHGXZW2" />
					</td>
					<td height="30">
						<bean:write name="rs" property="SHGXDWDH2" />
					</td>
					<td height="30">
						<bean:write name="rs" property="SHGXSJHM2" />
					</td>
				</tr>
				<tr height="80">
					<td align="center">
						���˼���ͥ
						<br/>
						������
						<br/>
						��100�֣�
					</td>
					<td colspan="6">
						<bean:write name="rs" property="JTQKJJ" />
					</td>
				</tr>
				<tr height="80">
					<td align="center">
						����ý���
						<br/>
						���м����ϣ�
					</td>
					<td colspan="6">
						<bean:write name="rs" property="JLCFJL" />
					</td>
				</tr>
				<tr height="80">
					<td align="center">
						�� ע
					</td>
					<td colspan="6">
						<bean:write name="rs" property="BZ" />
					</td>
				</tr>
				<tr height="40">
					<td colspan="7" align="center">
						�� �� �� ֤ �� �� �� Ϣ �� �� �� �� Ū �� �� �� ���� �� �� �� �� һ �� �� �� ��
						<br/>
						&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						�����ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp;&nbsp; &nbsp;��
					</td>
				</tr>
			</table>
		</center>
		<center>
			<div class="buttontool" id="btn"
				style="position: absolute;left:1%;width:100%">
				<button class="button2"
					onclick="expTab('rsTable','������ҵ��ѧѧ��������Ϣ�ǼǱ�','')"
					style="width:80px">
					��ӡ
				</button>
			</div>
		</center>
	</html:form>
</body>
</html:html>
