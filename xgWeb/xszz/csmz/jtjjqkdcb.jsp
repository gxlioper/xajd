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
			document.forms[0].action = "/xgxt/csmz_xszz.do?method=knssq";
			document.forms[0].submit();
		}
	</script>
<body>
	<html:form action="csmz_xszz.do?method=jtjjqkdcb" method="post">
	<input type="hidden" id="nd" name="nd"
			value="<bean:write name="rs" property="nd" />">
	<input type="hidden" id="xh" name="xh"
			value="<bean:write name="rs" property="xh" />">
		<table width="100%" id="theTable" border="0">
			<tr>
				<td>
					<div align="center">
						<h3>
							<strong>��ɳ����ְҵ����<bean:message key="lable.xsgzyxpzxy" />ѧ����ͥ������������</strong>
						</h3>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<strong>Ժ(ϵ): </strong>
						<bean:write name='rs' property="xymc" />
						&nbsp;&nbsp;
						<strong>�༶: </strong>
						<bean:write name='rs' property="bjmc" />
						&nbsp;&nbsp;
						<strong>��ѧʱ�䣺 </strong>
						<bean:write name='rs' property="rxny" />
						&nbsp;&nbsp;
						<strong>���ʱ�䣺 </strong>
						<bean:write name='rs' property="sqsj" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="tbstyle" height="1600px">
						<tr>
							<td width="6%">
								<div align="center">
									����
								</div>
							</td>
							<td width="14%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="8%">
								<div align="center">
									�Ա�
								</div>
							</td>
							<td width="8%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td width="12%">
								<div align="center">
									��������
								</div>
							</td>
							<td width="10%">
								<div align="center">
									<bean:write name='rs' property="csrq" />
								</div>
							</td>
							<td width="8%">
								<div align="center">
									����
								</div>
							</td>
							<td width="9%">
								<div align="center">
									<bean:write name='rs' property="mzmc" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									������ò
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name='rs' property="zzmmmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									��ס��ͥ��ϸ��ַ
								</div>
							</td>
							<td colspan="6">
								<bean:write name='rs' property="xzjtxxdz" />
							</td>
							<td>
								<div align="center">
									��������
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="yzbm" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									ԭѧϰѧУ
								</div>
							</td>
							<td colspan="3">
								<bean:write name='rs' property="yxxxx" />
							</td>
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jg" />
								</div>
							</td>
							<td>
								<div align="center">
									��ͥ�绰
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="6">
								<div align="center">
									ֱ
									<br />
									ϵ
									<br />
									��
									<br />
									ͥ
									<br />
									��
									<br />
									Ա
								</div>
							</td>
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td>
								<div align="center">
									��ϵ
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									���ںδ�������ְ��
								</div>
							</td>
							<td>
								<div align="center">
									��ϵ�绰
								</div>
							</td>
							<td>
								<div align="center">
									ÿ�¹���
									<br />
									����(Ԫ)
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy1_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy1_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy1_gx" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="zsjtcy1_gzdwjzw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy1_lxdh" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="zsjtcy1_ysr" />&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy2_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy2_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy2_gx" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="zsjtcy2_gzdwjzw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy2_lxdh" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="zsjtcy2_ysr" />&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy3_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy3_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy3_gx" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="zsjtcy3_gzdwjzw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy3_lxdh" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="zsjtcy3_ysr" />&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy4_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy4_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy4_gx" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="zsjtcy4_gzdwjzw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy4_lxdh" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="zsjtcy4_ysr" />&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy5_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy5_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy5_gx" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="zsjtcy5_gzdwjzw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy5_lxdh" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="zsjtcy5_ysr" />&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="2">
								<div align="center">
									��ͥ��
									<br />
									������
								</div>
							</td>
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td colspan="8">
								��ͥ�˿ڹ�
								<logic:equal name="fkqk" value="cz">
									<u> &nbsp; <bean:write name='rs' property="jtrks" />
										&nbsp; </u>
								</logic:equal>
								<logic:equal name="fkqk" value="nc">
									<u> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>
								</logic:equal>
								�ˣ�ȫ��������
								<logic:equal name="fkqk" value="cz">
									<u> &nbsp; <bean:write name='rs' property="jtjj_cz_qjnsr" />
										&nbsp; </u>
								</logic:equal>
								<logic:equal name="fkqk" value="nc">
									<u> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>
								</logic:equal>
								Ԫ���˾�������
								<logic:equal name="fkqk" value="cz">
									<u> &nbsp; <bean:write name='rs' property="jtjj_cz_rjysr" />
										&nbsp; </u>
								</logic:equal>
								<logic:equal name="fkqk" value="nc">
									<u> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>
								</logic:equal>
								Ԫ
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									ũ��
								</div>
							</td>
							<td colspan="8">
								��ͥ�˿ڹ�
								<logic:equal name="fkqk" value="nc">
									<u> &nbsp; <bean:write name='rs' property="jtrks" />
										&nbsp; </u>
								</logic:equal>
								<logic:equal name="fkqk" value="cz">
									<u> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>
								</logic:equal>
								�ˣ������������ܼ�
								<logic:equal name="fkqk" value="nc">
									<u> &nbsp; <bean:write name='rs' property="jtjj_nc_dnzsr" />
										&nbsp; </u>
								</logic:equal>
								<logic:equal name="fkqk" value="cz">
									<u> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>
								</logic:equal>
								Ԫ���˾�������
								<logic:equal name="fkqk" value="nc">
									<u> &nbsp; <bean:write name='rs' property="jtjj_nc_rjnsr" />
										&nbsp; </u>
								</logic:equal>
								<logic:equal name="fkqk" value="cz">
									<u> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>
								</logic:equal>
								Ԫ
								<br />
								��ע����ũ��Ա�����ũ�������������ת��Ϊ�������룬���벻��Ϊ�㡣��
							</td>
						</tr>
						<tr>
							<td colspan="10">
								<div align="center">
									���������������Ϊÿ��
									<logic:empty name="rs" property="ddzdshshbz">
										<u> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>
									</logic:empty>
									<logic:notEmpty name="rs" property="ddzdshshbz">
										<u> &nbsp;&nbsp;<bean:write name='rs'
												property="ddzdshshbz" />&nbsp;&nbsp; </u>
									</logic:notEmpty>
									Ԫ
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="5">
								<div align="center">
									��
									<br />
									Ҫ
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									ϵ
								</div>
							</td>
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td>
								<div align="center">
									��ϵ
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									���ںδ�������ְ��
								</div>
							</td>
							<td>
								<div align="center">
									ÿ�¹���
									<br />
									����(Ԫ)
								</div>
							</td>
							<td>
								<div align="center">
									����Ҿ���
									<br />
									��ϵ����
									<br />
									���
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx1_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx1_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx1_gx" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="zyshgx1_gzdwjzw" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="zyshgx1_ysr" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx1_ynjtjjlxhgyqk" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx2_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx2_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx2_gx" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="zyshgx2_gzdwjzw" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="zyshgx2_ysr" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx2_ynjtjjlxhgyqk" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx3_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx3_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx3_gx" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="zyshgx3_gzdwjzw" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="zyshgx3_ysr" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx3_ynjtjjlxhgyqk" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx4_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx4_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx4_gx" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="zyshgx4_gzdwjzw" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="zyshgx4_ysr" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx4_ynjtjjlxhgyqk" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
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
									��
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									ԭ
									<br />
									��
								</div>
							</td>
							<td colspan="9">
								<br />
								<bean:write name='rs' property="jtjjknqkjyy" />
								<br />
								<br />
								���˱�֤������д������ʵ���󣬲������Ͽɡ�
								<div align="right">
									ѧ��(ǩ��)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="3">
								<div align="center">
									֤
									<br />
									��
									<br />
									��
									<br />
									λ
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
							<td>
								<div align="center">
									���׵�λ
								</div>
							</td>
							<td colspan="3">
								<bean:write name='rs' property="fqdw" />
							</td>
							<td rowspan="3">
								<div align="center">
									֤
									<br />
									��
									<br />
									��
									<br />
									λ
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
							<td colspan="2">
								<div align="center">
									ĸ�׵�λ
								</div>
							</td>
							<td colspan="2">
								<bean:write name='rs' property="mqdw" />
							</td>
						</tr>
						<tr>
							<td colspan="4">
								�����ǩ����
								<br />
								��ϵ�绰��
								<br />
								��ί����£�
								<br />
								<br />
								<div align="right">
									��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
								</div>
							</td>
							<td colspan="4">
								�����ǩ����
								<br />
								��ϵ�绰��
								<br />
								��ί����£�
								<br />
								<br />
								<div align="right">
									��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								�����ǩ����
								<br />
								��ϵ�绰��
								<br />
								���������������ֵ����ء��������ֹ��£�
								<br />
								<br />
								<div align="right">
									��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
								</div>
							</td>
							<td colspan="4">
								�����ǩ����
								<br />
								��ϵ�绰��
								<br />
								���������������ֵ����ء��������ֹ��£�
								<br />
								<br />
								<div align="right">
									��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<p align="left">
						<strong>ע�����۸�ĸ���޾��幤����λ����������֤����λ��Ϊ��ί�ᡢ���������������ֵ����ء��������֣����ϵ�λ��д�͸��¡��˱�����ú�ɫ����д��</strong>
					</p>
				</td>
			</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="��ӡ" name="button_print"
			onclick="expTab('theTable','')" />
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input  value="����"
			onclick="back();" />
	</div>
</body>
</html:html>
