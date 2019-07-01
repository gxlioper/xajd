<%@ page language="java" 	contentType="text/html;charset=GBK"%>

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
	<base target="_self">
	<title><bean:message key="lable.title" />
	</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Expires", "0");
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
</head>
<style media="print">
.brk{
page-break-after:always;
}
</style>
<body>
	<html:form action="bzsqb.do" method="post">
	<input type="hidden" name="bzlb" id="bzlb" value="<bean:write name="bzlb" />">

		<div align="center">
			<p>
				<strong><bean:write name="rs" property="xxmc"/>��ͥ��������ѧ�����������</strong>
			</p>
		</div>
		<p>
			<strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;У��
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;רҵ
			</strong>
		</p>
		<table id="theTable" class="tbstyle" width="100%">
			<tr>
				<td height="29" colspan="2">
					<p align="center">
						ѧ��
					</p>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name="rs" property="xh" />
					</div>
				</td>
				<td width="28">
					<p align="center">
						����
					</p>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name="rs" property="xm" />
					</div>
				</td>
				<td width="51">
					<p align="center">
						�Ԅe
					</p>
				</td>
				<td width="65">
					<div align="center"><bean:write name="rs" property="xb" /></div>
				</td>
				<td colspan="2">
					<p align="center">
						����
					</p>
				</td>
				<td width="61">
					<div align="center"><bean:write name="rs" property="mzmc" /></div>
				</td>
				<td colspan="4">
					<p align="center">
						��ҵʱ��
					</p>
				</td>
				<td width="98" valign="top">
					<bean:write name="rs" property="bysj" />
				</td>
			</tr>
			<tr>
				<td height="29" colspan="2">
					<p align="center">
						רҵ�꼶
					</p>
				</td>
				<td colspan="4">
					<bean:write name="rs" property="zynj" />
				</td>
				<td colspan="2">
					<p align="center">
						���Һ���
					</p>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="qsh" />
				</td>
				<td colspan="4">
					<p align="center">
						���ҵ绰
					</p>
				</td>
				<td colspan="2" valign="top">
					<bean:write name="rs" property="qsdh" />
				</td>
			</tr>
			<tr>
				<td height="30" colspan="2">
					<p align="center">
						��ͥ��ַ
					</p>
				</td>
				<td colspan="4">
					<bean:write name="rs" property="jtdz" />
				</td>
				<td colspan="2">
					<p align="center">
						�ʱ�
					</p>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="yzbm" />
				</td>
				<td colspan="3">
					<p align="center">
						���֤����
					</p>
				</td>
				<td colspan="4" valign="top">
					<bean:write name="rs" property="sfzh" />
				</td>
			</tr>
			<tr>
				<td width="52" rowspan="6">
					<div align="center">
						<br>
						��
						<br>
						ͥ
						<br>
						��
						<br>
						Ա
					</div>
				</td>
				<td height="31" colspan="2">
					<p align="center">
						�� ��
					</p>
				</td>
				<td colspan="3">
					<p align="center">
						�� ν
					</p>
				</td>
				<td colspan="8">
					<p align="center">
						�� �� �� λ
					</p>
				</td>
				<td colspan="3">
					<p align="center">
						�� �� ��
					</p>
				</td>
			</tr>
			<tr>
				<td height="30" colspan="2" valign="top">
					<bean:write name="rs" property="jtcy1_xm" />
				</td>
				<td colspan="3" valign="top">
					<bean:write name="rs" property="jtcy1_gx" />
				</td>
				<td colspan="8" valign="top">
					<bean:write name="rs" property="jtcy1_gzdw" />
				</td>
				<td colspan="3" valign="top">
					&nbsp;<bean:write name="rs" property="jtcy1_ysr" />&nbsp;
				</td>
			</tr>
			<tr>
				<td height="31" colspan="2" valign="top">
					<bean:write name="rs" property="jtcy2_xm" />
				</td>
				<td colspan="3" valign="top">
					<bean:write name="rs" property="jtcy2_gx" />
				</td>
				<td colspan="8" valign="top">
					<bean:write name="rs" property="jtcy2_gzdw" />
				</td>
				<td colspan="3" valign="top">
					&nbsp;<bean:write name="rs" property="jtcy2_ysr" />&nbsp;
				</td>
			</tr>
			<tr>
				<td height="29" colspan="2" valign="top">
					<bean:write name="rs" property="jtcy3_xm" />
				</td>
				<td colspan="3" valign="top">
					<bean:write name="rs" property="jtcy3_gx" />
				</td>
				<td colspan="8" valign="top">
					<bean:write name="rs" property="jtcy3_gzdw" />
				</td>
				<td colspan="3" valign="top">
					&nbsp;<bean:write name="rs" property="jtcy3_ysr" />&nbsp;
				</td>
			</tr>
			<tr>
				<td height="28" colspan="2" valign="top">
					<bean:write name="rs" property="jtcy4_xm" />
				</td>
				<td colspan="3" valign="top">
					<bean:write name="rs" property="jtcy4_gx" />
				</td>
				<td colspan="8" valign="top">
					<bean:write name="rs" property="jtcy4_gzdw" />
				</td>
				<td colspan="3" valign="top">
					&nbsp;<bean:write name="rs" property="jtcy4_ysr" />&nbsp;
				</td>
			</tr>
			<tr>
				<td colspan="2" valign="top">
					<bean:write name="rs" property="jtcy5_xm" />
				</td>
				<td height="31" colspan="3" valign="top">
					<bean:write name="rs" property="jtcy5_gx" />
				</td>
				<td colspan="8" valign="top">
					<bean:write name="rs" property="jtcy5_gzdw" />
				</td>
				<td colspan="3" valign="top">
					&nbsp;<bean:write name="rs" property="jtcy5_ysr" />&nbsp;
				</td>
			</tr>
			<tr>
				<td width="52">
					<div align="center">
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
					</div>
				</td>
				<td colspan="16">
					<p>
						��ѧ��
					</p>
					<p align="center">
						��ͥ�ṩ <u>&nbsp;<bean:write  name="rs" property="jttgje"/> &nbsp;</u>Ԫ /�� &nbsp;&nbsp;&nbsp;�� ѧ ��<u>&nbsp; <bean:write  name="rs" property="zxje"/>&nbsp;</u>Ԫ
					</p>
					<p align="center">
						��ѧ��<u>&nbsp; <bean:write  name="rs" property="jxje"/>&nbsp;</u>Ԫ &nbsp;&nbsp;�ڹ���ѧ����<u>&nbsp;<bean:write  name="rs" property="qgzxje"/>&nbsp;</u>Ԫ
					</p>
					<p align="center">
						У����Ϣ��ѧ��<u>&nbsp;<bean:write  name="rs" property="xnwxdkje"/>&nbsp;</u>Ԫ &nbsp;&nbsp;&nbsp;&nbsp;�� �� �� ��<u>&nbsp;<bean:write  name="rs" property="qtsrje"/>&nbsp;</u>Ԫ
					</p>
					<p align="center">
						��ѧ��������ʱ��<u>&nbsp;<bean:write  name="rs" property="zxdkje"/>&nbsp;&nbsp;<bean:write name="rs" property="zxdksj" />&nbsp; </u>�ѷ��Ž�ʱ��<u>&nbsp;<bean:write name="rs" property="yffzxdkje"/>&nbsp;&nbsp;<bean:write name="rs" property="yffzxdksj" /></u>
					</p>
				</td>
			</tr>
			<tr>
				<td width="52">
					<div align="center">
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
					</div>
				</td>
				<td colspan="16" valign="top">
					<bean:write name="rs" property="sqzzly" />
				</td>
			</tr>
		</table>
		<div class="brk">
		<table cellspacing="0" cellpadding="0" class="tbstyle" width="100%">
			<tr>
				<td width="46">
					<div align="center">
						<br>
						��
						<br>
						��
						<br>
						��
						<br>
						Ը
					</div>
				</td>
				<td width="508" valign="top">
					<p>
						&nbsp;
					</p>
					<p>
						(���������� ��ѧУ��ѧ�� ��ѧ�Ѳ��� ����ʱ���Ѳ���)
					</p>
					<logic:equal value="xfbz" name="bzlb">
					<p>
						��һ־Ը��<u>&nbsp;&nbsp;��&nbsp;&nbsp;</u>���&nbsp;<u>&nbsp;<bean:write name="rs" property="zzff1qsje" />&nbsp;&nbsp;</u>Ԫ��&nbsp;<u>&nbsp;<bean:write name="rs" property="zzff1jsje" />&nbsp;&nbsp;</u>Ԫ
					</p>
					</logic:equal>
					<logic:equal value="lsknbz" name="bzlb">
					<p>
						��һ־Ը��<u>&nbsp;&nbsp;��&nbsp;&nbsp;</u>���&nbsp;<u>&nbsp;<bean:write name="rs" property="zzff1qsje" />&nbsp;&nbsp;</u>Ԫ��&nbsp;<u>&nbsp;<bean:write name="rs" property="zzff1jsje" />&nbsp;&nbsp;</u>Ԫ
					</p>
					</logic:equal>
					<logic:equal value="xndxj" name="bzlb">
					<p>
						��һ־Ը��<u>&nbsp;&nbsp;��&nbsp;&nbsp;</u>���&nbsp;<u>&nbsp;<bean:write name="rs" property="zzff1qsje" />&nbsp;&nbsp;</u>Ԫ��&nbsp;<u>&nbsp;<bean:write name="rs" property="zzff1jsje" />&nbsp;&nbsp;</u>Ԫ
					</p>
					</logic:equal>
					<p>
						&nbsp;
					</p>
					<p align="right">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ѧ������ǩ�����ߣߣߣߣߣߣߣߣߣߣߣ�
					</p>
					<p align="right">
						�� &nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;��
					</p>
				</td>
			</tr>
			<tr>
				<td width="46">
					<div align="center">
						<br>
						��
						<br>
						ѧ
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
					</div>
				</td>
				<td width="508" valign="top">
					<p align="left">
						&nbsp;
					</p>
					<p align="left">
						����ҵǰһ���Ի���
					</p>
					<p align="left">
						����ҵ��ߣߣߣߣߣ����ڻ���
					</p>
				</td>
			</tr>
			<tr>
				<td width="46">
					<div align="center">
						<br>
						ѧ
						<br>
						Ժ
						<br>
						��
						<br>
						��
					</div>
				</td>
				<td width="508" valign="top">
					<p>
						&nbsp;
					</p>
					<p>
						ͬ���ͬѧ����
					</p>
					<p align="center">
						�� ѧ �� �ߣߣߣߣߣ�Ԫ
					</p>
					<p align="center">
						ѧ �� �� ���ߣߣߣߣߣ�Ԫ
					</p>
					<p align="center">
						��ʱ���Ѳ����ߣߣߣߣߣ�Ԫ
					</p>
					<p align="center">
						�� ���ߣߣߣߣߣ�
					</p>
					<p align="right">
						�����ˣ����£���
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;��
					</p>
				</td>
			</tr>
			<tr>
				<td width="46">
					<div align="center">
						<br>
						ѧ
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
					</div>
				</td>
				<td width="508" valign="top">
					<p>
						&nbsp;
					</p>
					<p>
						���ͬ�⣺
					</p>
					<p align="center">
						�� ѧ �� �ߣߣߣߣߣ�Ԫ
					</p>
					<p align="center">
						ѧ �� �� ���ߣߣߣߣߣ�Ԫ
					</p>
					<p align="center">
						�� ���ߣߣߣߣߣ�
					</p>
					<p align="right">
						����ˣ����£���
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;��
					</p>
				</td>
			</tr>
		</table>
		<p align="left">
			����ѧУ��ѧ���ѧ�Ѳ����ģ���д����һʽ���ݣ���<bean:message key="lable.xsgzyxpzxy" />��ѧ��������һ�ݡ�������ʱ���Ѳ����ģ���д����һʽһ�ݣ���<bean:message key="lable.xsgzyxpzxy" />���档
		</p>
		</div>
	</html:form>
	<div align=center>
		<input  value="��ӡ" onclick="expTab('theTable','�㽭��ѧ��ͥ��������ѧ�����������')"/>
	</div>
</body>
</html:html>
