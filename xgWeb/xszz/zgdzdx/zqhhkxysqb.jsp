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
						<h3>
							<strong>�й����йɷ����޹�˾������ѧ����չ�ں󻹿�Э��</strong>
						</h3>
					</div>
					<br />
					<br />
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;�����(�׷�)��
					<logic:empty name="rs" property="xm">
					__________________________________��
					</logic:empty>
					<logic:notEmpty name="rs" property="xm">
					<u>&nbsp;&nbsp;<bean:write name="rs" property="xm" />&nbsp;&nbsp;<bean:write name="rs" property="bjmc" />&nbsp;&nbsp;<bean:write name="rs" property="xh" />&nbsp;&nbsp;&nbsp;&nbsp;</u>
					</logic:notEmpty>
					&nbsp;�����ʺţ�
					<logic:empty name="rs" property="khh">
					__________________
					</logic:empty>
					<logic:notEmpty name="rs" property="khh">
					<u>&nbsp;&nbsp;<bean:write name="rs" property="khh" />&nbsp;&nbsp;&nbsp;&nbsp;</u>
					</logic:notEmpty>
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;��Ч֤�����룺
					<logic:empty name="rs" property="sfzh">
					_________________________________________________________
					</logic:empty>
					<logic:notEmpty name="rs" property="sfzh">
					<u>&nbsp;&nbsp;<bean:write name="rs" property="sfzh" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					</logic:notEmpty>
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;��ͥסַ���������룺
					<logic:empty name="rs" property="jtxxzz">
					____________________________________________________
					</logic:empty>
					<logic:notEmpty name="rs" property="jtxxzz">
					<u>&nbsp;&nbsp;<bean:write name="rs" property="jtxxzz" />&nbsp;&nbsp;<bean:write name="rs" property="jtyb" />&nbsp;&nbsp;</u>
					</logic:notEmpty>
					<br /><br />
					<logic:notEmpty name="rs" property="fqxm">
						&nbsp;&nbsp;&nbsp;&nbsp;��ͥ��Ч��ϵ��������
						<u>&nbsp;&nbsp;<bean:write name="rs" property="fqxm" />&nbsp;&nbsp;&nbsp;&nbsp;</u>
						&nbsp;&nbsp;�����˹�ϵ��
						<u>&nbsp;&nbsp;����&nbsp;&nbsp;&nbsp;&nbsp;</u>
					</logic:notEmpty>
					<logic:empty name="rs" property="fqxm">
						<logic:notEmpty name="rs" property="mqxm">
							&nbsp;&nbsp;&nbsp;&nbsp;��ͥ��Ч��ϵ��������
							<u>&nbsp;&nbsp;<bean:write name="rs" property="mqxm" />&nbsp;&nbsp;&nbsp;&nbsp;</u>
							&nbsp;&nbsp;�����˹�ϵ��
							<u>&nbsp;&nbsp;ĸ��&nbsp;&nbsp;&nbsp;&nbsp;</u>
						</logic:notEmpty>
						<logic:empty name="rs" property="mqxm">
							&nbsp;&nbsp;&nbsp;&nbsp;��ͥ��Ч��ϵ��������
							_______________________
							&nbsp;&nbsp;�����˹�ϵ��
							________________
						</logic:empty>
					</logic:empty>
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;��ϵ�绰��
					<logic:empty name="rs" property="dwdh">
						<logic:empty name="rs" property="yddh">
							_________________________
						</logic:empty>
						<logic:notEmpty name="rs" property="yddh">
							<u>&nbsp;&nbsp;<bean:write name="rs" property="yddh" />&nbsp;&nbsp;</u>
						</logic:notEmpty>
					</logic:empty>
					<logic:notEmpty name="rs" property="dwdh">
						<logic:empty name="rs" property="yddh">
							<u>&nbsp;&nbsp;<bean:write name="rs" property="dwdh" />&nbsp;&nbsp;</u>
						</logic:empty>
						<logic:notEmpty name="rs" property="yddh">
							<u>&nbsp;&nbsp;<bean:write name="rs" property="dwdh" />&nbsp;&nbsp;<bean:write name="rs" property="yddh" />&nbsp;&nbsp;</u>
						</logic:notEmpty>
					</logic:notEmpty>
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;������λ��
					<logic:empty name="rs" property="gzdw">
					_____________________________________________________________
					</logic:empty>
					<logic:notEmpty name="rs" property="gzdw">
					<u>&nbsp;&nbsp;<bean:write name="rs" property="gzdw" />&nbsp;&nbsp;<bean:write name="rs" property="gzdz" /></u>
					</logic:notEmpty>
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;�������룺
					<logic:empty name="rs" property="dwyb">
					__________________________
					</logic:empty>
					<logic:notEmpty name="rs" property="dwyb">
					<u>&nbsp;&nbsp;<bean:write name="rs" property="dwyb" />&nbsp;&nbsp;</u>
					</logic:notEmpty>
					��ϵ�绰��
					<logic:empty name="rs" property="dwdh">
					_________________________
					</logic:empty>
					<logic:notEmpty name="rs" property="dwdh">
					<u>&nbsp;&nbsp;<bean:write name="rs" property="dwdh" />&nbsp;&nbsp;</u>
					</logic:notEmpty>
					<br />
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;�����(�ҷ�)��
					<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�й����йɷ����޹�˾�人����������֧��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;��ַ��
					<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�人�к�ɽ��׿��Ȫ��·39��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;�������뼰���䣺
					<u>&nbsp;&nbsp;430079&nbsp;&nbsp;&nbsp;&nbsp;donghuboc@sina.com&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;��ϵ�绰��
					<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;027-87521649��87521897&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					<br />
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;��Э��Ϊ�׷����ҷ�ǩ���ġ��й����йɷ����޹�˾������ѧ�������ͬ������ͬ��ţ�
					<logic:empty name="rs" property="htbh">
					<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					</logic:empty>
					<logic:notEmpty name="rs" property="htbh">
					<u>&nbsp;<bean:write name="rs" property="htbh" />&nbsp;</u>
					</logic:notEmpty>
					�ţ�Լ���Ĵ���Э�飬������ȷ�׷����ҷ��黹������ѧ����ƻ������׷����ҷ�Э��ͬ��󣬶������»���Э�飺
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;һ����ֹ
					<u>&nbsp;
					<logic:empty name="rs" property="zqyear">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:empty>
					<logic:notEmpty name="rs" property="zqyear">
					${rs.zqyear }
					</logic:notEmpty>
					&nbsp;</u>��
					<u>&nbsp;
					${rs.byny_mon }
					&nbsp;</u>��
					<u>&nbsp;${rs.byny_day }&nbsp;</u>�գ��׷����ҷ���ù�����ѧ���������ң���д��
					<logic:equal name="rs" property="sjffje" value="0">
					__________________
					</logic:equal>
					<logic:notEqual name="rs" property="sjffje" value="0">
					<u>&nbsp;<bean:write name="rs" property="sjffje_dx" />&nbsp;</u>
					</logic:notEqual>
					Ԫ��
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;�����׷���
					<u>&nbsp;
					<logic:empty name="rs" property="zqyear">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:empty>
					<logic:notEmpty name="rs" property="zqyear">
						${rs.zqyear }
					</logic:notEmpty>
					&nbsp;</u>��
					<u>&nbsp;
					${rs.byny_mon }
					&nbsp;</u>��
					<u>&nbsp;${rs.byny_day }&nbsp;</u>����
					<u>&nbsp;��ҵ&nbsp;</u>ԭ����ʽ�뿪
					<logic:notEqual name="rs" property="zqszdw" value="">
					<u>&nbsp;<bean:write name="rs" property="zqszdw" />&nbsp;</u>
					</logic:notEqual>
					<logic:equal name="rs" property="zqszdw" value="">
					___________________
					</logic:equal>
					������ѧУ����
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;�����׷��������µ�
					<u>&nbsp;������&nbsp;</u>��ʽ��
					<u>&nbsp;��&nbsp;</u>����/������
					<u>&nbsp;/&nbsp;</u>�ڹ黹�������Ϣ����
					<u>&nbsp;
					<logic:empty name="rs" property="hkyear">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:empty>
					<logic:notEmpty name="rs" property="hkyear">
						${rs.hkyear }
					</logic:notEmpty>
					&nbsp;</u>��
					<u>&nbsp;
					<logic:empty name="rs" property="bynyn_mon">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:empty>
					<logic:notEmpty name="rs" property="bynyn_mon">
						${rs.bynyn_mon }
					</logic:notEmpty>
					&nbsp;</u>��
					<u>&nbsp;${rs.bynyn_day }&nbsp;</u>����
					<logic:empty name="rs" property="hkjsyear">
					____
					</logic:empty>
					<logic:notEmpty name="rs" property="hkjsyear">
					<u>${rs.hkjsyear }</u>
					</logic:notEmpty>
					��
					<logic:empty name="rs" property="hkjsmonth">
					____
					</logic:empty>
					<logic:notEmpty name="rs" property="hkjsmonth">
					<u>${rs.hkjsmonth }&nbsp;</u>
					</logic:notEmpty>
					��
					<u>&nbsp;${rs.hkjsday }&nbsp;</u>�չ�
					<logic:empty name="rs" property="month">
					____
					</logic:empty>
					<logic:notEmpty name="rs" property="month">
					<u>&nbsp;${rs.month }&nbsp;</u>
					</logic:notEmpty>
					�¹黹������Ϣ����
					<logic:empty name="rs" property="hkyear2">
					____
					</logic:empty>
					<logic:notEmpty name="rs" property="hkyear2">
					<u>${rs.hkyear2 }</u>
					</logic:notEmpty>
					��
					<logic:empty name="rs" property="bynyn_mon">
					____
					</logic:empty>
					<logic:notEmpty name="rs" property="bynyn_mon">
					<u>&nbsp;${rs.bynyn_mon }&nbsp;</u>
					</logic:notEmpty>
					��
					<u>&nbsp;${rs.bynyn_day }&nbsp;</u>����
					<logic:empty name="rs" property="hkjsyear">
					____
					</logic:empty>
					<logic:notEmpty name="rs" property="hkjsyear">
					<u>${rs.hkjsyear }</u>
					</logic:notEmpty>
					��
					<logic:empty name="rs" property="hkjsmonth">
					____
					</logic:empty>
					<logic:notEmpty name="rs" property="hkjsmonth">
					<u>&nbsp;${rs.hkjsmonth }&nbsp;</u>
					</logic:notEmpty>
					��
					<u>&nbsp;${rs.hkjsday }&nbsp;</u>�չ�
					<logic:empty name="rs" property="month2">
					____
					</logic:empty>
					<logic:notEmpty name="rs" property="month2">
					<u>&nbsp;${rs.month2 }&nbsp;</u>
					</logic:notEmpty>
					�¹黹�������Ϣ��
					<br />
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��һ���ȶϢ���
					<br />
					<br /><br />
					<div align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��������
						<br />
						������*��1+�����ʣ�&nbsp;&nbsp;&nbsp;&nbsp;
						<br />
						ÿ�ڻ����&nbsp;=&nbsp;��������������������������������&nbsp;*&nbsp;����
						<br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��������
						<br />
						��1+�����ʣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;1
						<br />
						<br />
						ÿ�´�����Ϣ��=��������*������/360*ÿ������
						<br />
						<br />
					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�������ȶ�𻹿
					<br />
					<br /><br />
					<div align="center">
						����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<br />
						ÿ�ڻ����&nbsp;=&nbsp;������������&nbsp;+&nbsp;������&nbsp;-&nbsp;�ۼ��ѹ黹����&nbsp;*&nbsp;������
						<br />
						��������&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<br />
						<br />
						ÿ�´�����Ϣ��=��������*������/360*ÿ������
						<br /><br />
					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;�ġ���˫��ȷ�ϵĽ��ڱ�����Э�������ڼ䣬����������ִ���й���������ͬ���η������ʡ�����������ʵ������ҷ���ִ�е���������ʣ���������ͬ־�׷���
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;�塢�׷���Ȩ�ҷ�ֱ�ӴӼ׷����ҷ��������ʻ��пۿ���ڹ黹��Ϣ���ʻ�����Ϊ��
					<logic:empty name="rs" property="xm">
					___________________
					</logic:empty>
					<logic:notEmpty name="rs" property="xm">
					<u>&nbsp;<bean:write name="rs" property="xm" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					</logic:notEmpty>
					���ʻ���Ϊ��
					<logic:empty name="rs" property="hkczh">
					_____________________________
					</logic:empty>
					<logic:notEmpty name="rs" property="hkczh">
					<u>&nbsp;<bean:write name="rs" property="hkczh" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					</logic:notEmpty>
					��
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;�����׷���ŵ����У�������׺�һ�����ڽ����й������人�������������֣�֧�й�����ѧ�����ҵ������ȷ���顷���ͻ��ҷ�����׷�������λ����ϵ��ʽ�б䶯�����뼰ʱ֪ͨ�ҷ���
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;�ߡ���Э����������׷��Ѿ����ҷ������˳�ֵ�Э�̣�
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;�ˡ���Э����Ϊ���й����йɷ����޹�˾������ѧ�������ͬ������ɲ��֣��롶�й����йɷ����޹�˾������ѧ�������ͬ������ͬ�ȷ���Ч����
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					&nbsp;&nbsp;�׷�����ǩ�ֻ���£�
					<br />
					<br />
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
					<br />
					<br />
					<br />
					<br />
					<br />
					&nbsp;&nbsp;�ҷ��������£�
					<br />
					<br />
					&nbsp;&nbsp;��Ȩǩ���ˣ���ǩ�֣�
					<br />
					<br />
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
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
