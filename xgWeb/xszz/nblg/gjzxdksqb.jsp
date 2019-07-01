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
<script language="javascript">
		function back(){
			document.forms[0].action = "/xgxt/nblg_xszz.do?method=gjzxdksq";
			document.forms[0].submit();
		}
	</script>
<body>
	<html:form action="nblg_xszz.do?method=gjzxdksqb" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
							<h3>
						<strong>
								�й��������й�����ѧ���������
						</strong>
							</h3>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="12%">
								<div align="center">
									����������
								</div>
							</td>
							<td width="22%">
								<div align="center">
									<bean:write name="rs" property="xm" />
								</div>
							</td>
							<td width="12%">
								<div align="center">
									�Ա�
								</div>
							</td>
							<td width="12%">
								<div align="center">
									<bean:write name="rs" property="xb" />
								</div>
							</td>
							<td width="12%">
								<div align="center">
									��������
								</div>
							</td>
							<td width="30%">
								<div align="center">
									<bean:write name="rs" property="csny" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									���֤����
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="sfzh" />
								</div>
							</td>
							<td>
								<div align="center">
									ѧ��֤����
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="xh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jg" />
								</div>
							</td>
							<td>
								<div align="center">
									�������ڵ�
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="hjszd" />
								</div>
							</td>
						</tr>
					</table>
					<table width="100%" class="tbstyle">
						<tr>
							<td rowspan="6" width="6%">
								<div align="center">
									����
									<br />
									���
								</div>
							</td>
							<td width="18%">
								<div align="center">
									����״��
								</div>
							</td>
							<td colspan="5">
								&nbsp;
								<logic:empty name="rs" property="xh">
								��&nbsp;&nbsp;����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;һ��
								</logic:empty>
								<logic:notEmpty name="rs" property="xh">
									<logic:equal name="rs" property="jkzk" value="����">
										��
									</logic:equal>
									<logic:notEqual name="rs" property="jkzk" value="����">
										��
									</logic:notEqual>
									&nbsp;&nbsp;����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:equal name="rs" property="jkzk" value="һ��">
										��
									</logic:equal>
									<logic:notEqual name="rs" property="jkzk" value="һ��">
										��
									</logic:notEqual>
									&nbsp;&nbsp;һ��
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��ͥ�绰
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="jtdh" />
								</div>
							</td>
							<td width="16%">
								<div align="center">
									�ƶ��绰
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="yddh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��ͥͨѶ��ַ
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jttxdz" />
								</div>
							</td>
							<td width="14%">
								<div align="center">
									��������
								</div>
							</td>
							<td width="16%">
								<div align="center">
									<bean:write name="rs" property="yzbm" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									E-Mail��ַ
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<bean:write name="rs" property="email" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��ѧʱ��
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<bean:write name="rs" property="rxny" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									ѧ��
								</div>
							</td>
							<td>
								<div align="right">
									<bean:write name="rs" property="xz" />&nbsp;&nbsp;&nbsp;��
								</div>
							</td>
							<td>
								<div align="center">
									רҵ
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="zymc" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									�Ͷ�ѧ�������꼶
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="nj" />
								</div>
							</td>
							<td>
								<div align="center">
									����<bean:message key="lable.xsgzyxpzxy" />�༶
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="xymc" />
									<br />
									<bean:write name="rs" property="bjmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="2">
								<div align="center">
									����
									<br />
									���
									<br />
									���
								</div>
							</td>
							<td colspan="6">
								&nbsp;&nbsp;&nbsp;&nbsp;����ܽ�Сд����&nbsp;&nbsp;
								<logic:empty name="rs" property="sqje">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="sqje">
									<bean:write name="rs" property="sqje" />
								</logic:notEmpty>
								&nbsp;&nbsp;Ԫ
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									�����
								</div>
							</td>
							<td colspan="5">
								&nbsp;&nbsp;��&nbsp;�ȶϢ���&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;�ȶ�𻹿
							</td>
						</tr>
					</table>
					<table width="100%" class="tbstyle">
						<tr>
							<td width="15%">
								<div align="center">
									ѧ��
								</div>
							</td>
							<td width="35%">
								<div align="center">
									&nbsp;&nbsp;&nbsp;&nbsp;0&nbsp;&nbsp;Ԫ/��
								</div>
							</td>
							<td width="15%">
								<div align="center">
									ס�޷�
								</div>
							</td>
							<td width="35%">
								<div align="center">
									&nbsp;&nbsp;&nbsp;&nbsp;0&nbsp;&nbsp;Ԫ/��
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									�����
								</div>
							</td>
							<td colspan="3">
								&nbsp;&nbsp;&nbsp;&nbsp;ÿ&nbsp;&nbsp;��&nbsp;��/��&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0&nbsp;&nbsp;
								Ԫ
							</td>
						</tr>
						<tr>
							<td colspan="4">
								��֤�����
								<br />
								����
								<u>&nbsp;
								<logic:empty name="rs" property="jzr1_xm">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="jzr1_xm">
									<bean:write name="rs" property="jzr1_xm" />
								</logic:notEmpty>
								&nbsp;</u>�Ա�
								<u>&nbsp;
								<logic:empty name="rs" property="jzr1_xm">
								&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="jzr1_xm">
									<bean:write name="rs" property="jzr1_xb" />
								</logic:notEmpty>
								&nbsp;</u>���֤����
								<u>
								<logic:empty name="rs" property="jzr1_sfzh">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="jzr1_sfzh">
									<bean:write name="rs" property="jzr1_sfzh" />
								</logic:notEmpty>
								</u>�����˹�ϵ
								<u>&nbsp;
								<logic:empty name="rs" property="jzr1_gx">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="jzr1_gx">
									<bean:write name="rs" property="jzr1_gx" />
								</logic:notEmpty>
								&nbsp;</u>
								<br />
								��סַ
								<u>&nbsp;
								<logic:empty name="rs" property="jzr1_xzz">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="jzr1_xzz">
									<bean:write name="rs" property="jzr1_xzz" />
								</logic:notEmpty>
								&nbsp;</u>��ϵ�绰
								<u>&nbsp;
								<logic:empty name="rs" property="jzr1_lxdh">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="jzr1_lxdh">
									<bean:write name="rs" property="jzr1_lxdh" />
								</logic:notEmpty>
								&nbsp;</u>
								<br />
								����
								<u>&nbsp;
								<logic:empty name="rs" property="jzr2_xm">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="jzr2_xm">
									<bean:write name="rs" property="jzr2_xm" />
								</logic:notEmpty>
								&nbsp;</u>�Ա�
								<u>&nbsp;
								<logic:empty name="rs" property="jzr2_xm">
								&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="jzr2_xm">
									<bean:write name="rs" property="jzr2_xb" />
								</logic:notEmpty>
								&nbsp;</u>���֤����
								<u>
								<logic:empty name="rs" property="jzr2_sfzh">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="jzr2_sfzh">
									<bean:write name="rs" property="jzr2_sfzh" />
								</logic:notEmpty>
								</u>�����˹�ϵ
								<u>&nbsp;
								<logic:empty name="rs" property="jzr2_gx">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="jzr2_gx">
									<bean:write name="rs" property="jzr2_gx" />
								</logic:notEmpty>
								&nbsp;</u>
								<br />
								��סַ
								<u>&nbsp;
								<logic:empty name="rs" property="jzr2_xzz">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="jzr2_xzz">
									<bean:write name="rs" property="jzr2_xzz" />
								</logic:notEmpty>
								&nbsp;</u>��ϵ�绰
								<u>&nbsp;
								<logic:empty name="rs" property="jzr2_lxdh">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="jzr2_lxdh">
									<bean:write name="rs" property="jzr2_lxdh" />
								</logic:notEmpty>
								&nbsp;</u>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								ѧУ���
								<br />
								<br />
								<div align="right">
									������ǩ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ѧУ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<div align="right">
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
					</table>
					<br />
					<br />
					<table width="100%" class="tbstyle">
						<tr>
							<td>
								<p>
									���������������
								</p>
								<p>
									&nbsp;&nbsp;&nbsp;&nbsp;1
									�����������鼰������������������Ϊ�����������ȫ��ʵ�����˳е�����д��ʵ�����µ�һ�����Σ�
								</p>
								<p>
									&nbsp;&nbsp;&nbsp;&nbsp;2
									�����˳����Դ���������Ϊ������н������ݡ����͵����ϸ�ӡ�������������������ƾ֤��
								</p>
								<p>
									&nbsp;&nbsp;&nbsp;&nbsp;3 ������������鲻���Ϲ涨�Ľ��������δ������ʱ�����������飻
								</p>
								<p>
									&nbsp;&nbsp;&nbsp;&nbsp;4 �����˱�֤��ȡ�����д���󣬰�ʱ�������Ϣ��
								</p>
								<p>
									&nbsp;&nbsp;&nbsp;&nbsp;5
									������ͬ�⽨�����н�����������Ϣ�ṩ���й��������и���������Ϣ�������ݿ⼰�Ŵ��������ܲ�����׼���������������������ݿ⡣��ͬ�⽨�����������������������ݿ���йص�λ�����ż����˲�ѯ���˵�����״������ѯ��õ����ñ����������й��������а䲼�ġ�����������Ϣ�������ݿ�������а취���涨��;��Χ�ڡ�
								</p>
								<div align="center">
									������ǩ�֣�____________&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����໤��ǩ��(�������)��______________
								</div>
								<div align="center">
									______��____��____��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;______��____��____��
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center">
		<input  value="��ӡ" name="button_print"
			onclick="expTab('theTable','')" />
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input  value="����" onclick="back();" />
	</div>
</body>
</html:html>
