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


	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
<style media='print'>
	.noPrin{
	display:none;}
</style>	
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>		
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
	
	</script>
	
</head>

<body>
	<html:form action="xcxyXszz.do?method=printSqb" method="post">
		<div id="titName">
			<logic:notEqual value="������ѧ��" name="rs" property="xmlc">
				<div align="center">
					<h3><bean:write name='rs'
							property="xmmc" />����������<bean:write name="LoginXn" />ѧ�꣩</h3>
				</div>
				<div align="center">
					<b>ѧУ��</b>
					<bean:write name='rs' property="xxmc" />
					&nbsp;&nbsp;
					<b><bean:message key="lable.xsgzyxpzxy" />��</b>
					<bean:write name='rs' property="xymc" />
					&nbsp;&nbsp;
					<b>רҵ��</b>
					<bean:write name='rs' property="zymc" />
					&nbsp;&nbsp;
					<b>�༶��</b>
					<bean:write name='rs' property="bjmc" />
				</div>
				<br>
			</logic:notEqual>
			<logic:equal value="������ѧ��" name="rs" property="xmlc">
				<div align="center">
					<h3><bean:write name='rs'
							property="xmmc" />�����</h3>
				</div>
				<br>
			</logic:equal>
		</div>
		<table class="tbstyle" width="100%" id="theTable">
			<logic:notEqual value="������ѧ��" name="rs" property="xmlc">
				<tr align="center" height="35">
					<td rowspan="4" width="4%">
						��
						<br>
						��
						<br>
						��
						<br>
						��
					</td>
					<td width="16%">
						����
					</td>
					<td width="16%">
						<bean:write name="rs" property="xm" />
					</td>
					<td width="16%">
						�Ա�
					</td>
					<td width="16%">
						<bean:write name="rs" property="xb" />
					</td>
					<td width="16%">
						��������
					</td>
					<td width="16%">
						<bean:write name="rs" property="csrq" />
					</td>
				</tr>
				<tr height="35">
					<td align="center">
						ѧ��
					</td>
					<td align="center">
						<bean:write name="rs" property="xh" />
					</td>
					<td align="center">
						����
					</td>
					<td align="center">
						<bean:write name="rs" property="mzmc" />
					</td>
					<td align="center">
						��ѧʱ��
					</td>
					<td align="center">
						<bean:write name="rs" property="rxrq" />
					</td>
				</tr>
				<tr height="35">
					<td align="center">
						���֤��
					</td>
					<td align="center" colspan="5">
						<bean:write name="rs" property="sfzh" />
					</td>

				</tr>
				<tr height="35">
					<td align="center">
						������ò
					</td>
					<td align="center">
						<bean:write name="rs" property="zzmmmc" />
					</td>
					<td align="center" colspan="2">
						��ϵ�绰
					</td>
					<td align="center" colspan="2">
						<bean:write name="rs" property="lxdh" />
					</td>
				</tr>
				<logic:equal value="������־��ѧ��" name="rs" property="xmlc">
					<tr height="35" align="center">
						<td rowspan="3">
							��<br>ͥ<br>��<br>��<br>��<br>��
						</td>
						<td align="center">
							��ͥ����
						</td>
						<td align="center" colspan="3">
							<bean:write name="rs" property="hkxz" />
						</td>
						<td align="center">
							��ͥ�˿�����
						</td>
						<td>
							<bean:write name="rs" property="jtzrs" />
						</td>
					</tr>
					<tr height="35">
						<td align="center">
							��ͥ��������
						</td>
						<td align="center">
							<bean:write name="rs" property="jtysr" />
						</td>
						<td align="center">
							�˾�������
						</td>
						<td align="center">
							<bean:write name="rs" property="rjysr" />
						</td>
						<td align="center">
							������Դ
						</td>
						<td align="center">
							<bean:write name="rs" property="srly" />
						</td>
					</tr>
					<tr height="35">
						<td align="center">
							��ͥ��ַ
						</td>
						<td align="center" colspan="3">
							<bean:write name="rs" property="jtdz" />
						</td>
						<td align="center">
							��������
						</td>
						<td align="center">
							<bean:write name="rs" property="yzbm" />
						</td>
					</tr>
				</logic:equal>
				<tr align="center" height="35">
					<td>
						ѧ
						<br>
						ϰ
						<br>
						��
						<br>
						��
					</td>
					<td colspan="6" align="left">
						<br>
						��ѧ����޿γ���
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�ţ����У�����
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�ţ�����
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
						<br><br>
						�ɼ�������<bean:write name="rs" property="jsxx" />��רҵ����
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="pjcjpm" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>������/�����������ۺϿ����ɼ�
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="zcj" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�֣�<br><br>����
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="zcjpm" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>������/��������
						<br>
						<br>
					</td>
				</tr>
				<tr align="center" height="35">
					<td>
						<br>
						<br>
						��
						<br>
						��
						<br>
						��
						<br>
						��
						<br>
						<br>
					</td>
					<td colspan="6" align="left">
						<br>
						��Ҫ���
						<br><br>
						<div style="line-height: 28px;"><bean:write name="rs" property="chjl" /></div>
						<br>
						���У�Ժ������
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>� У������
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>� ʡ�����Ͻ���
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
						<br>
						<br>
					</td>
				</tr>
				<tr align="center" height="35">
					<td>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						��
						<br>
						��
						<br>
						��
						<br>
						��
						<br>
						��
						<br>
						ȫ
						<br>
						��
						<br>
						��
						<br>
						ӳ
						<br>
						��
						<br>
						��
						<br>
						��
						<br>
						��
						<br>
						��
						<br>
						��
						<br>
						��
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
					</td>
					<td colspan="6" align="left" style="vertical-align: top;">
						<br>
						<div style="line-height: 28px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="sqly" /></div>
						<br>
						
						<div align="right">
							������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<br>
						<div align="right">
							��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
					</td>
				</tr>
				<tr align="center" height="35">
					<td>
						<br>
						<br>
						<br>
						��
						<br>
						��
						<br>
						��
						<br>
						ר
						<br>
						ҵ
						<br>
						��
						<br>
						��
						<br>
						��
						<br>
						��
						<br>
						��
						<br>
						<br>
						<br>
					</td>
					<td colspan="6">
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<div align="right">
							�Ƽ��ˣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����ְ��
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<br>
						<div align="right">
							��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<br>
					</td>
				</tr>
				<tr align="center" height="35">
					<td>
						<br>
						<br>
						<br>
						Ժ
						<br>
						��
						<br>
						ϵ
						<br>
						��
						<br>
						��
						<br>
						��
						<br>
						<br>
						<br>
					</td>
					<td colspan="6">
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<div align="right">
							����
							�£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<br>
						<div align="right">
							��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<br>
					</td>
				</tr>
				<tr align="center" height="35">
					<td>
						<br>
						<br>
						ѧ
						<br>
						У
						<br>
						��
						<br>
						��
						<br>
						<br>
					</td>
					<td colspan="6" align="left">
						<br>
						<br>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����󣬲���
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;</u>��Χ�ڹ�ʾ
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�죬�����飬�ֱ���
						<br><br>
						ͬ���ͬѧ���
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write
								name="LoginXn" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>ѧ��ȹ��ҽ�ѧ��<br><br>
						<div align="right">
							����
							�£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<br>
						<div align="right">
							��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<br>
					</td>
				</tr>
			</logic:notEqual>
			<logic:equal value="������ѧ��" name="rs" property="xmlc">
				<tr align="center" height="35">
					<td rowspan="4" width="4%">
						��
						<br>
						��
						<br>
						��
						<br>
						��
					</td>
					<td width="12%">
						����
					</td>
					<td width="12%">
						<bean:write name="rs" property="xm" />
					</td>
					<td width="12%">
						�Ա�
					</td>
					<td width="12%">
						<bean:write name="rs" property="xb" />
					</td>
					<td width="12%">
						��������
					</td>
					<td width="12%">
						<bean:write name="rs" property="csrq" />
					</td>
					<td width="12%">
						����
					</td>
					<td width="12%">
						<bean:write name="rs" property="mzmc" />
					</td>
				</tr>
				<tr align="center" height="35">
					<td>
						ѧ��
					</td>
					<td colspan="4">
						<bean:write name="rs" property="xh" />
					</td>

					<td>
						��ѧʱ��
					</td>
					<td colspan="2">
						<bean:write name="rs" property="rxrq" />
					</td>
				</tr>
				<tr align="left" height="35">
					<td colspan="8">
						<bean:write name="rs" property="xxmc" />
						��ѧ&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:write name="rs" property="xymc" />
						&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:write name="rs" property="zymc" />
						ϵ&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:write name="rs" property="bjmc" />
						��
					</td>
				</tr>
				<tr height="35">
					<td align="left" colspan="8">
						������ֽ���
						<br>
						<div style="line-height: 28px;"><bean:write name="rs" property="chjl" /></div>
					</td>

				</tr>
				<tr align="center" height="35">
					<td rowspan="3">
						��
						<br>
						ͥ
						<br>
						��
						<br>
						��
						<br>
						��
						<br>
						��
					</td>
					<td>
						��ͥ����
					</td>
					<td colspan="4">
						<bean:write name="rs" property="hkxz" />
					</td>
					<td>
						��ͥ�˿�����
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtzrs" />
					</td>
				</tr>
				<tr align="center" height="35">
					<td>
						��ͥ��������
					</td>
					<td>
						<bean:write name="rs" property="jtysr" />
					</td>
					<td colspan="2">
						�˾�������
					</td>
					<td>
						<bean:write name="rs" property="rjysr" />
					</td>
					<td>
						������Դ
					</td>
					<td colspan="2">
						<bean:write name="rs" property="srly" />
					</td>
				</tr>
				<tr align="center" height="35">
					<td align="center">
						��ͥ��ַ
					</td>
					<td align="left" colspan="4">
						<bean:write name="rs" property="jtdz" />
					</td>
					<td align="center">
						��������
					</td>
					<td colspan="2">
						<bean:write name="rs" property="yzbm" />
					</td>
				</tr>

				<tr align="center" height="35">
					<td rowspan="7">
						��
						<br>
						ͥ
						<br>
						��
						<br>
						Ա
						<br>
						��
						<br>
						��
					</td>
					<td colspan="2">
						����
					</td>
					<td>
						����
					</td>
					<td colspan="2">
						�뱾�˹�ϵ
					</td>
					<td colspan="3">
						������ѧϰ��λ
					</td>
				</tr>
				<tr align="center" height="35">
					<td colspan="2">
						<bean:write name="rs" property="jtcy1_xm" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy1_nl" />
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy1_gx" />
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtcy1_dw" />
					</td>
				</tr>
				<tr align="center" height="35">
					<td colspan="2">
						<bean:write name="rs" property="jtcy2_xm" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy2_nl" />
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy2_gx" />
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtcy2_dw" />
					</td>
				</tr>
				<tr align="center" height="35">
					<td colspan="2">
						<bean:write name="rs" property="jtcy3_xm" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy3_nl" />
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy3_gx" />
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtcy3_dw" />
					</td>
				</tr>
				<tr align="center" height="35">
					<td colspan="2">
						<bean:write name="rs" property="jtcy4_xm" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy4_nl" />
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy4_gx" />
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtcy4_dw" />
					</td>
				</tr>
				<tr align="center" height="35">
					<td colspan="2">
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td colspan="3">
						&nbsp;
					</td>
				</tr>
				<tr align="center" height="35">
					<td colspan="2">
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td colspan="3">
						&nbsp;
					</td>
				</tr>
				<tr height="35">
					<td colspan="9">
						��������:
						<br>
						<div style="line-height: 28px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:write name="rs" property="sqly" /></div>
						<br>
						<br>
						
						<div align="right">
							������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<br>
						<div align="right">
							��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
					</td>
				</tr>
				<tr height="35">
					<td colspan="9">
						ѧУ��������
						<br>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:write name="rs" property="xxshyj" />
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<div align="right">
							����
							�£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<br>
						<div align="right">
							��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<br>
					</td>
				</tr>
			</logic:equal>
		</table>
		<br>
		<div align=center class='noPrin'>
				<input type='button' class='button2' value='ҳ������'
					onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='��ӡԤ��'
					onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='ֱ�Ӵ�ӡ'
					onclick="WebBrowser.ExecWB(6,6);return false;">
		</div>
	</html:form>
</body>
</html:html>
