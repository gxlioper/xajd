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
<body>
	<html:form action="csmz_xszz.do?method=jtjjqkdcb" method="post">
		<table width="100%" id="theTable" border="0" height="90%">
			<tr>
				<td>
					<div align="center">
						<h3>
							<strong><bean:write name='rs' property="nd" />��ȹ��ҿ������й�����ѧ������������</strong>
						</h3>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						��ţ�10827<bean:write name='rs' property="nd" /><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;ѧУ���ƣ���ɳ����ְҵ����<bean:message key="lable.xsgzyxpzxy" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="tbstyle" height="85%">
						<tr>
							<td width="4%" rowspan="10">
								<div align="center">
									��
									<br />
									��
									<br />
									ѧ
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									ͥ
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									Ϣ
								</div>
							</td>
							<td width="13%">
								<div align="center">
									ѧ������
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="12%">
								<div align="center">
									���֤��
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="sfzh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									�Ա�
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td>
								<div align="center">
									��������
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="csrq" />
								</div>
							</td>
							<td width="15%">
								<div align="center">
									��ѧʱ��
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name='rs' property="rxny" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />����
								</div>
							</td>
							<td width="9%">
								<div align="center">
									<bean:write name='rs' property="xymc" />
								</div>
							</td>
							<td width="9%">
								<div align="center">
									רҵ
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zymc" />
								</div>
							</td>
							<td width="11%">
								<div align="center">
									�༶
								</div>
							</td>
							<td width="12%">
								<div align="center">
									<bean:write name='rs' property="bjmc" />
								</div>
							</td>
							<td>
								<div align="center">
									ѧ��
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="xz" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									ѧ��
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="xh" />
								</div>
							</td>
							<td>
								<div align="center">
									��������
									<br />
									��QQ��
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="dzyxhqqh" />
								</div>
							</td>
							<td>
								<div align="center">
									���(�ڡ����)
								</div>
							</td>
							<td>
								<div align="center">
									��&nbsp;ר&nbsp;&nbsp;��&nbsp;��
									<br />
									��&nbsp;˶&nbsp;&nbsp;��&nbsp;��
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									ѧ���绰
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="xsdh" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									��������
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="szss" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��ͥ����
									<br />
									��ϸ��ַ
								</div>
							</td>
							<td colspan="5">
								<bean:write name='rs' property="jtfk" />
							</td>
							<td>
								<div align="center">
									��������
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtfkyb" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��ͥ����
									<br />
									��ϸ��ַ
								</div>
							</td>
							<td colspan="5">
								<bean:write name='rs' property="jtxxxzz" />
							</td>
							<td>
								<div align="center">
									��������
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtxxxzzyb" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="3">
								<div align="center">
									��ĸ��໤��
								</div>
							</td>
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td>
								<div align="center">
									��ν
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									���֤��
								</div>
							</td>
							<td>
								<div align="center">
									�̶��绰
								</div>
							</td>
							<td>
								<div align="center">
									�ֻ��绰
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="fmhjhr1_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="fmhjhr1_cw" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="fmhjhr1_sfzh" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="fmhjhr1_gddh" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="fmhjhr1_sjhm" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="fmhjhr2_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="fmhjhr2_cw" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="fmhjhr2_sfzh" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="fmhjhr2_gddh" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="fmhjhr2_sjhm" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="5">
								<div align="center">
									��
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									��
								</div>
							</td>
							<td rowspan="2">
								<div align="center">
									����ʵ��
									<br />
									������
									<br />
									(��λ:Ԫ)
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									��ѧ��һ��
								</div>
							</td>
							<td>
								<div align="center">
									�ڶ���
								</div>
							</td>
							<td>
								<div align="center">
									������
								</div>
							</td>
							<td>
								<div align="center">
									������
								</div>
							</td>
							<td>
								<div align="center">
									������
								</div>
							</td>
							<td>
								<div align="center">
									������
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="ywsjje1" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="ywsjje2" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="ywsjje3" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="ywsjje4" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="ywsjje5" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="ywsjje6" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="3">
								<div align="center">
									��������
									<br />
									������
									<br />
									(��λ:Ԫ)
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									(��д)
									&nbsp;<bean:write name='rs' property="sqjedx" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									Сд&nbsp;&nbsp;
									<bean:write name='rs' property="sqje" />
									&nbsp;Ԫ
								</div>
							</td>
							<td rowspan="3">
								<div align="center">
									�������
									<br />
									����
									<br />
									(��λ:��)
								</div>
							</td>
							<td rowspan="3">
								<div align="center">
									<u>&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="sqdknx" />&nbsp;&nbsp;&nbsp;</u>��
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="2">
								<div align="center">
									����
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									ѧ��
								</div>
							</td>
							<td>
								<div align="center">
									ס�޷�
								</div>
							</td>
							<td>
								<div align="center">
									�����
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="sqje" />
								</div>
							</td>
							<td>
								<div align="center">
									0
								</div>
							</td>
							<td>
								<div align="center">
									0
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="9">
								<div align="left">
									����ѧ�����������˱�֤��������������ʵ���󣬱���������������ʵ��˼��ʾ�����в�ʵ��Ը�е��������Ρ�
								</div>
								<div align="right">
									ѧ��ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:empty name="rs" property="sqsj">
										&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
									</logic:empty>
									<logic:notEmpty name="rs" property="sqsj">
										<bean:write name='rs' property="sqsj" />
									</logic:notEmpty>
									&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="9">
								<div align="center">
									<strong>ѧ&nbsp;У&nbsp;��&nbsp;��&nbsp;��&nbsp;��</strong>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<div align="center">
									����Ա������
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />������
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									У��������������
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<br />
								<div align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;����������Ϣ��ѧ����Ϣ��ʵ������������Ϣ�ͼ�ͥ�������������ʵ��������д���ݺ˶�����������ȫ��������������������
								</div>
								<br />
								<br />
								<br />
								<div align="center">
									ǩ����&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									<logic:empty name="rs" property="fdyshsj">
										��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
									</logic:empty>
									<logic:notEmpty name="rs" property="fdyshsj">
										<bean:write name='rs' property="fdyshsj" />
									</logic:notEmpty>
									&nbsp;
								</div>
								<div align="right">
									����Ա�绰��
									<logic:empty name="rs" property="fdydh">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									</logic:empty>
									<logic:notEmpty name="rs" property="fdydh">
									<u>&nbsp;&nbsp;<bean:write name='rs' property="fdydh" />&nbsp;&nbsp;</u>
									</logic:notEmpty>
									&nbsp;&nbsp;
								</div>
								<br />
							</td>
							<td colspan="3">
								<br />
								<div align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;�������������<bean:message key="lable.xsgzyxpzxy" />�Ͷ�ѧ������������������ʵ��ͬ�⽫ѧ������������ϱ����������ġ�
								</div>
								<br />
								<br />
								<br />
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />�쵼ǩ����
								</div>
								<div align="center">
									��<bean:message key="lable.xsgzyxpzxy" />���£�&nbsp;
								</div>
								<br />
								<div align="right">
									<logic:empty name="rs" property="xyshsj">
										��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
									</logic:empty>
									<logic:notEmpty name="rs" property="xyshsj">
										<bean:write name='rs' property="xyshsj" />
									</logic:notEmpty>
									&nbsp;
								</div>
								<div align="right">
									<bean:message key="lable.xsgzyxpzxy" />�绰��
									<logic:empty name="rs" property="xydh">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									</logic:empty>
									<logic:notEmpty name="rs" property="xydh">
									<u>&nbsp;&nbsp;<bean:write name='rs' property="xydh" />&nbsp;&nbsp;</u>
									</logic:notEmpty>
									&nbsp;&nbsp;
								</div>
								<br />
							</td>
							<td colspan="2">
								<br />
								<div align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;����ˣ������������������ѧ����������ͬ���ѧ�����������ѧ���
								</div>
								<br />
								<br />
								<br />
								<div align="center">
									������ǩ����
								</div>
								<div align="center">
									�����ĸ��£�&nbsp;
								</div>
								<br />
								<div align="right">
									<logic:empty name="rs" property="xxshsj">
										��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
									</logic:empty>
									<logic:notEmpty name="rs" property="xxshsj">
										<bean:write name='rs' property="xxshsj" />
									</logic:notEmpty>
									&nbsp;
								</div>
								<div align="right">
									У�������ĵ绰��
									<logic:empty name="rs" property="xxdh">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									</logic:empty>
									<logic:notEmpty name="rs" property="xxdh">
									<u>&nbsp;&nbsp;<bean:write name='rs' property="xxdh" />&nbsp;&nbsp;</u>
									</logic:notEmpty>
									&nbsp;&nbsp;
								</div>
								<br />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="��ӡ" name="button_print"
			onclick="expTab('theTable','')" />
	</div>
</body>
</html:html>
