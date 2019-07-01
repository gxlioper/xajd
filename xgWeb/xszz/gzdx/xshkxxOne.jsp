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


	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
	</script>
</head>

<body>
	<html:form action="gzdx_xszz.do?method=xshkxxOne" method="post">
		<table class="tbstyle" width="100%">
			<tr>
				<td align="center" width="16%">
					��ͬ��
				</td>
				<td width="34%">
					<bean:write name="rs" property="htbh"/>
				</td>
				<td width="16%">
					<div align="center">
						�������
					</div>
				</td>
				<td width="34%">
					<bean:write name="rs" property="dkcs"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						����ѧ��
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xn"/>
				</td>
				<td>
					<div align="center">
						��������
					</div>
				</td>
				<td>
					<bean:write name="rs" property="dkyh"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						ѧ��
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xh"/>
				</td>
				<td>
					<div align="center">
						����
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xm"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xymc"/>
				</td>
				<td>
					<div align="center">
						רҵ
					</div>
				</td>
				<td>
					<bean:write name="rs" property="zymc"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						�༶
					</div>
				</td>
				<td>
					<bean:write name="rs" property="bjmc"/>
				</td>
				<td>
					<div align="center">
						�꼶
					</div>
				</td>
				<td>
					<bean:write name="rs" property="nj"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						���п���
					</div>
				</td>
				<td>
					<bean:write name="rs" property="yhkh"/>
				</td>
				<td>
					<div align="center">
						�����ܶ�
					</div>
				</td>
				<td>
					<bean:write name="rs" property="dkze"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						�����꼶
					</div>
				</td>
				<td>
					<bean:write name="rs" property="dknj"/>
				</td>
				<td>
					<div align="center">
						�������
					</div>
				</td>
				<td>
					<bean:write name="rs" property="dkffr"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						�������
					</div>
				</td>
				<td>
					<bean:write name="rs" property="dkdqr"/>
				</td>
				<td>
					<div align="center">
						��Ϣ��ֹ��
					</div>
				</td>
				<td>
					<bean:write name="rs" property="txzzr"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						չ��ԭ��
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="zqyy"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						չ�ڵ�������
					</div>
				</td>
				<td>
					<bean:write name='rs' property="zqdqrq" />
				</td>
				<td>
					<div align="center">
						չ����Ϣ��ֹ����
					</div>
				</td>
				<td>
					<bean:write name='rs' property="zqtxzzrq" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						��ǰ����ʱ��
					</div>
				</td>
				<td>
					<bean:write name='rs' property="tqhksj" />
				</td>
				<td>
					<div align="center">
						��ǰ������
					</div>
				</td>
				<td>
					<bean:write name="rs" property="tqhkje"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						����ͬ��������
					</div>
				</td>
				<td>
					<div align="center">
						��1����Ϣ�黹���
					</div>
				</td>
				<td>
					<bean:write name="rs" property="lxghqk_1"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						��2����Ϣ�黹���
					</div>
				</td>
				<td>
					<bean:write name="rs" property="lxghqk_2"/>
				</td>
				<td>
					<div align="center">
						��3����Ϣ�黹���
					</div>
				</td>
				<td>
					<bean:write name="rs" property="lxghqk_3"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						��4����Ϣ�黹���
					</div>
				</td>
				<td>
					<bean:write name="rs" property="lxghqk_4"/>
				</td>
				<td>
					<div align="center">
						��5����Ϣ�黹���
					</div>
				</td>
				<td>
					<bean:write name="rs" property="lxghqk_5"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						��6����Ϣ�黹���
					</div>
				</td>
				<td>
					<bean:write name="rs" property="lxghqk_6"/>
				</td>
				<td>
					<div align="center">
						��7�걾Ϣ�黹���
					</div>
				</td>
				<td>
					<bean:write name="rs" property="lxghqk_7"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						ΥԼʱ��
					</div>
				</td>
				<td>
					<bean:write name='rs' property="wysj" />
				</td>
				<td>
					<div align="center">
						ΥԼ���
					</div>
				</td>
				<td>
					<bean:write name="rs" property="wyje"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						ΥԼԭ��
					</div>
				</td>
				<td>
					<bean:write name="rs" property="wyyy"/>
				</td>
				<td>
					<div align="center">
						�Ƿ�ȫ���������
					</div>
				</td>
				<td>
					<bean:write name="rs" property="sfqbhqdk"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						��ע
					</div>
				</td>
				<td colspan="3">
					<bean:write name='rs' property="bz" />
				</td>
			</tr>
		</table>
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
			<button class="button2" onClick="Close();" id="buttonPrint">
				��&nbsp;&nbsp;&nbsp;&nbsp;��
			</button>
		</div>
	</html:form>
</body>
</html:html>
