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
	<title><bean:message key="lable.title" />
	</title>
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
<script language="javascript">

	</script>
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
						<h2>
						<strong>�й����йɷ����޹�˾������ѧ����չ��Э��</strong>
						</h2>
					</div>
					<br />
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;�����(���³Ƽ׷�)��
					<logic:empty name="rs" property="xm">
					_________________________________________
					</logic:empty>
					<logic:notEmpty name="rs" property="xm">
						<u>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="xm" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					</logic:notEmpty>
					(���ѧ��)
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;������(���³��ҷ�)��
					<u>�й����йɷ����޹�˾�人����������֧��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>(��������)
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;������(���³Ʊ���)��
					<u>�й����ʴ�ѧ(�人)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>(ѧУ����)
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�׷����������������ѧ����ס��ҡ���������
					<logic:empty name="rs" property="dkqx1_year">
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					</logic:empty>
					<logic:notEmpty name="rs" property="dkqx1_year">
						<u>&nbsp;<bean:write name="rs" property="dkqx1_year" />&nbsp;</u>
					</logic:notEmpty>
					��
					<logic:empty name="rs" property="dkqx1_mon">
						<u>&nbsp;&nbsp;&nbsp;</u>
					</logic:empty>
					<logic:notEmpty name="rs" property="dkqx1_mon">
						<u>&nbsp;<bean:write name="rs" property="dkqx1_mon" />&nbsp;</u>
					</logic:notEmpty>
					��
					<logic:empty name="rs" property="dkqx1_day">
						<u>&nbsp;&nbsp;&nbsp;</u>
					</logic:empty>
					<logic:notEmpty name="rs" property="dkqx1_day">
						<u>&nbsp;<bean:write name="rs" property="dkqx1_day" />&nbsp;</u>
					</logic:notEmpty>
					��ǩ�����й����йɷ����޹�˾������ѧ�������ͬ������ͬ��Ϊ��
					<u>&nbsp;<bean:write name="rs" property="htbh" />&nbsp;</u>
					�ţ�������Ϊ(��д)
					<u>&nbsp;<bean:write name="rs" property="dkye_dx" />&nbsp;</u>
					Ԫ(Сд
					<u>&nbsp;<bean:write name="rs" property="dkye" />&nbsp;</u>
					Ԫ)������Ϊ
					<u>&nbsp;<bean:write name="rs" property="dkqxy" />&nbsp;</u>
					���¡����ݺ�ͬԼ�����ñʴ���Ӧ��
					<u>
					<logic:empty name="rs" property="byny_year">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:empty>
					<logic:notEmpty name="rs" property="byny_year">
					&nbsp;<bean:write name="rs" property="byny_year" />&nbsp;
					</logic:notEmpty>
					</u>��
					<u>&nbsp;${rs.bynyn_mon }&nbsp;</u>��
					<u>&nbsp;${rs.bynyn_day }&nbsp;</u>�ս��뻹���ڣ�����
					<u>&nbsp;��������ѧλ&nbsp;</u>(��������ѧλ������ѧ���ξ���֧Ԯ�������衢����ԭ��)���׷�������ͬ�����ҷ��������չ�����룬�ҷ������ͬ��Ϊ�׷�����չ�ڴ��Ϊά���������棬��ȷ���Σ��������ã����ݹ��ҷ��ɡ����桢������ѧ�������ߺ��ҷ������ϼ�������ȫ��ѧ�������������ǩ���ġ�������ѧ����ҵ�����Э���顷����Э��һ�£�������¸��
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;һ���׷�����
					<u>&nbsp;<bean:write name="rs" property="htbh" />&nbsp;</u>
					�ź�ͬ���ҷ���������Ҵ����д��
					<u>&nbsp;<bean:write name="rs" property="sqdkje_dx" />&nbsp;</u>
					Ԫ��������
					<u>&nbsp;<bean:write name="rs" property="gfcs" />&nbsp;</u>
					�η��š���ֹ
					<u>&nbsp;<bean:write name="rs" property="byny_year" />&nbsp;</u>��
					<u>&nbsp;${rs.byny_mon }&nbsp;</u>��
					<u>&nbsp;${rs.byny_day }&nbsp;</u>�գ��ѷ���
					<u>&nbsp;<bean:write name="rs" property="yfcs" />&nbsp;</u>
					�Σ������������δ������Ϊ����д��
					<u>&nbsp;<bean:write name="rs" property="dkye_dx" />&nbsp;</u>
					Ԫ������Լ�����е�
					<u>&nbsp;<bean:write name="rs" property="dkye" />&nbsp;</u> Ԫչ�ڵ�
					<u>&nbsp;<bean:write name="rs" property="zqhbyny_year" />&nbsp;</u> ��
					<u>&nbsp;<bean:write name="rs" property="zqhbyny_mon" />&nbsp;</u> ��
					<u>&nbsp;${rs.byny_day }&nbsp;</u> �ա�
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����ѧ���ξ���֧Ԯ��������Ȱ������չ�ڣ��׷�Լ����
					<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
					<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
					<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��֮ǰ�ָ�ѧҵ������Ӧ��ʱ֪ͨ�ҷ����ҷ����ɸ���
					<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�ź�ͬΪ�׷���������������
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����չ�ں�����ʴ�չ��֮����________%ִ�С�
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����չ�ں�ס��ҡ�����������Ȩ����������������ŵ�Լ��й�����԰�
					<u>&nbsp;<bean:write name="rs" property="htbh" />&nbsp;</u>�Ž���ͬԼ��������ִ�С�
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ġ��ס��ҡ��������̶����������
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1���׷�Ӧ���ҷ��ṩ�׷���֤��ʵ�������ϵ��ʽ����ϵ��ַ����ϵ�绰�ȣ���Ӧ��ǰ���й����Ϸ����䶯��ʱ�ԹҺ��š��ʼ����ʼ�������ӦҪ���ҷ�ǩ�ջ�ִ������ʽ���������֪ͨ�ҷ����ҷ����ܺ���׷�ȷ����Ϣ���գ�����֪ͨ��Ч���������ҷ���Ȩ��ֹ��Э�鲢����������ǰ���ڣ�
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2���׷�Ӧ�����������ҷ��ľ�����ϵ����ϵ��ʽ��Ϊ���Һ��š��ʼ����ʼ�����ӦҪ���ҷ�ǩ�ջ�ִ������ʽ���Ҹ���ϵ��ʽӦ���п�֤���ԣ��ҷ��Լ׷����ڷ�������Ϣ���еǼǣ������׷����������ڲ����ҷ�������ϵ�����ҷ���Ȩ��ֹ��Э�飬������������ǰ���ڡ�
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3���׷���
					<u>&nbsp;<bean:write name="rs" property="htbh" />&nbsp;</u>�š��й����йɷ����޹�˾������ѧ�������ͬ�����´���չ���ڼ����Ϣ������������ѧУ�Ըñʴ�������е���������ѧ����ҵ�����Э���顷�涨��������κ�����
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4���׷�����ѧ���ξ���֧Ԯ�����������չ�ڣ�Ӧ��Э��Լ��ʱ���ڻָ�ѧҵ�������ҷ���Ȩ��ֹ��Э�飬ֹͣ������δʹ�õĴ������������ǰ���ڡ�
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�塢��Э����˫��ǩ�ֲ��Ӹǹ��£�����ӡ��֮������Ч��
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;������Э�鹹��
					<u>&nbsp;<bean:write name="rs" property="htbh" />&nbsp;</u>�š��й����йɷ����޹�˾������ѧ�������ͬ������ɲ��֣���֮����ͬ�ȵķ���Ч����
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ߡ���Э��һʽ���ݣ��ס��ҡ���������ִһ�ݣ�����ͬ��Ч����
					<br />
					<br />
					<br />
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�׷���
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ǩ����ӡ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
					<br />
					<br />
					<br />
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ҷ���
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���������ˣ�����Ȩ�����ˣ���ǩ��
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
					<br />
					<br />
					<br />
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;������ǩ��
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ǩ����ͬ�ص㣺�й����ʴ�ѧ���人��
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center">
		<input  value="��ӡ" name="button_print"
			onclick="expTab('theTable','')" />
	</div>
</body>
</html:html>
