<%@ page language="java" contentType="text/html;charset=GBK"%>

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
<base target="_self">
<head>
	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" http-equiv="no-cache" />
	<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
	<meta http-equiv="Expires" http-equiv="0" />
	<meta name="Copyright" content="������� zfsoft" />
</head>
<%
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/xszzFunction.js"></script>
<body>
	<html:form action="lyjszxjsqb.do" method="post">
		<p align="center">
			�й����й�����ѧ�����Э��
		</p>

		<table width="100%" class="tbstyle" id="theTable">
			<tr>
				<td>
					<p align="left">
						����ˣ��׷�����<u>&nbsp;&nbsp;
						<bean:write name="rs" property="xm" />&nbsp;&nbsp;</u>
					</p>
					<p align="left">
						��Ч֤�����룺<u>&nbsp;&nbsp;
						<bean:write name="rs" property="sfzh" />&nbsp;&nbsp;</u>
					</p>
					<p align="left">
						ס����<u>&nbsp;&nbsp;
						<bean:write name="rs" property="dwdz" />&nbsp;&nbsp;</u>
					</p>
					<p align="left">
						������λ��<u>&nbsp;&nbsp;
						<bean:write name="rs" property="gzdw" />&nbsp;&nbsp;</u>
					</p>
					<p align="left">
						�������룺<u>&nbsp;&nbsp;
						<bean:write name="rs" property="dwyzbm" />&nbsp;&nbsp;</u>
					</p>
					<p align="left">
						��ϵ�绰��<u>&nbsp;&nbsp;
						<bean:write name="rs" property="lxdh" />&nbsp;&nbsp;</u>
					</p>
					<p align="left">
						�����ˣ��ҷ�����_______________________________________
					</p>
					<p align="left">
						��ַ��_______________________________________
					</p>
					<p align="left">
						�������룺_______________________________________
					</p>
					<p align="left">
						��ϵ�绰��_______________________________________
					</p>
					<br />
					<br />
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;��Э��Ϊ�׷����ҷ�ǩ���ġ�����ͬ������ͬ��ţ�&nbsp;
						<bean:write name="rs" property="hth" />&nbsp;
						��Լ���Ĵ���Э�飬������ȷ�׷����ҷ��黹������ѧ����ƻ������ס�������Э��ͬ��󣬶������»���Э�飺
					</p>
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;һ����ֹ&nbsp;
						<bean:write name="rs" property="zhfkrqYear" />&nbsp;
						��&nbsp;
						<bean:write name="rs" property="zhfkrqMon" />&nbsp;
						��&nbsp;
						<bean:write name="rs" property="zhfkrqDay" />&nbsp;
						�գ��׷����ҷ���ù�����ѧ����������&nbsp;
						<bean:write name="rs" property="fkzje" />&nbsp;
						��
					</p>
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;�����׷���&nbsp;
						<bean:write name="rs" property="lxsjYear" />&nbsp;
						��&nbsp;
						<bean:write name="rs" property="lxsjMon" />&nbsp;
						��&nbsp;
						<bean:write name="rs" property="lxsjDay" />&nbsp;
						����&nbsp;
						<bean:write name="rs" property="lxyy" />&nbsp;
						ԭ����ʽ�뿪&nbsp;
						<bean:write name="rs" property="xxmc" />&nbsp;
						������ѧУ�����׷���ŵ��&nbsp;
						<bean:write name="rs" property="hkkssjYear" />&nbsp;
						��&nbsp;
						<bean:write name="rs" property="hkkssjMon" />&nbsp;
						��&nbsp;
						<bean:write name="rs" property="hkkssjDay" />&nbsp;
						�տ�ʼ�黹�����
					</p>
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;�����׷��������µ�&nbsp;
						<bean:write name="rs" property="hkfs1" />&nbsp;
						��ʽ��&nbsp;
						<bean:write name="rs" property="hkfs2" />&nbsp;
						���� /������&nbsp;
						<bean:write name="rs" property="hkcs" />&nbsp;
						�ڹ黹���Ϣ���������޹�&nbsp;
						<bean:write name="rs" property="hkqx" />&nbsp;
						�£���&nbsp;
						<bean:write name="rs" property="hkkssjYear" />&nbsp;
						��&nbsp;
						<bean:write name="rs" property="hkkssjMon" />&nbsp;
						��&nbsp;
						<bean:write name="rs" property="hkkssjDay" />&nbsp;
						����&nbsp;
						<bean:write name="rs" property="hkjssjYear" />&nbsp;
						��&nbsp;
						<bean:write name="rs" property="hkjssjMon" />&nbsp;
						��&nbsp;
						<bean:write name="rs" property="hkjssjDay" />&nbsp;
						��ֹ��
					</p>
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��һ���ȶϢ���
					</p>
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�������ȶ�𻹿
					</p>
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;�ġ���˫��ȷ�ϵĽ��ڱ�����Э�������ڼ䣬����������ִ���й���������ͬ���η������ʡ������������ʵ������ҷ���ִ�е���������ʣ���������֪ͨ�׷���
					</p>
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;�塢�׷���Ȩ�ҷ�ֱ�ӴӼ׷����ҷ��������˻��пۿ���ڹ黹��Ϣ���˻�����Ϊ��&nbsp;
						<bean:write name="rs" property="zffm" />&nbsp;
						���˻���Ϊ��&nbsp;
						<bean:write name="rs" property="zfh" />&nbsp;
						��
					</p>
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;�����׷���ŵ����У�������׺�һ�����ڽ����й����й�����ѧ������ϵ��ʽȷ�Ϻ������ͻ��ҷ���
					</p>
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;�ߡ���Э����������׷��Ѿ����ҷ������˳�ֵ�Э�̣�
					</p>
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;�ˡ���Э����Ϊ��������ѧ�������ͬ������ɲ��֣��롶������ѧ�������ͬ������ͬ�ȷ���Ч����
					</p>
					<br />
					<br />
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;�׷�����ǩ�ֻ���£�
					</p>
					<br />
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��
					</p>
					<br />
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;�ҷ��������£�
					</p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;��Ȩǩ���ˣ���ǩ�֣�
					</p>
					<br />
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��
					</p>
				</td>
			</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="��ӡ" name="button_print"
			onclick="expTab('theTable','�й����й�����ѧ�����Э��')" />
	</div>
</body>
</html:html>
