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
			document.forms[0].action = "/xgxt/jhzyjsxy_xszz.do?method=knssq";
			document.forms[0].submit();
		}
	</script>
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
						<h2>
							<strong> ��ְҵ����<bean:message key="lable.xsgzyxpzxy" />��ͥ��������ѧ���ǼǱ� </strong>
						</h2>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
					<br />
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						�������:&nbsp;&nbsp;
						<logic:equal name='rs' property="sqsjyear" value="null">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:equal>
						<logic:notEqual name='rs' property="sqsjyear" value="null">
							<bean:write name='rs' property="sqsjyear" />
						</logic:notEqual>
						��&nbsp;&nbsp;
						<logic:equal name='rs' property="sqsjmon" value="null">
							&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:equal>
						<logic:notEqual name='rs' property="sqsjmon" value="null">
							<bean:write name='rs' property="sqsjmon" />
						</logic:notEqual>
						��&nbsp;&nbsp;
						<logic:equal name='rs' property="sqsjday" value="null">
							&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:equal>
						<logic:notEqual name='rs' property="sqsjday" value="null">
							<bean:write name='rs' property="sqsjday" />
						</logic:notEqual>
						��&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
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
							<td width="6%">
								<div align="center">
									�Ա�
								</div>
							</td>
							<td width="14%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td width="14%">
								<div align="center">
									���֤��
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<bean:write name='rs' property="sfzh" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									��ѧ����
								</div>
							</td>
							<td width="16%">
								<div align="center">
									<bean:write name='rs' property="rxrq" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="xymc" />
								</div>
							</td>
							<td>
								<div align="center">
									�༶
								</div>
							</td>
							<td>
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
									<bean:write name='rs' property="xh" />
								</div>
							</td>
							<td>
								<div align="center">
									��ҵʱ��
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="byrq" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									���п���(ѧУ��)
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="yhkh" />
								</div>
							</td>
							<td>
								<div align="center">
									һ��ͨ��
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="ykthm" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									��ͥסַ���ʱ�
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jtzzjyb" />
								</div>
							</td>
							<td>
								<div align="center">
									���Һ�
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="qsh" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									ѧϰ�������
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									��&nbsp;(&nbsp;
									<logic:equal name='rs' property="xxztqk" value="��">
										��
									</logic:equal>
									&nbsp;)&nbsp;&nbsp;��&nbsp;(&nbsp;
									<logic:equal name='rs' property="xxztqk" value="��">
										��
									</logic:equal>
									&nbsp;)&nbsp;&nbsp;һ��&nbsp;(&nbsp;
									<logic:equal name='rs' property="xxztqk" value="һ��">
										��
									</logic:equal>
									&nbsp;)&nbsp;&nbsp;�ϲ�&nbsp;(&nbsp;
									<logic:equal name='rs' property="xxztqk" value="�ϲ�">
										��
									</logic:equal>
									&nbsp;)
								</div>
							</td>
							<td>
								<div align="center">
									��ϵ�绰
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="lxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									����ֽ���
								</div>
							</td>
							<td colspan="3">
								<bean:write name='rs' property="hhzjl" />
							</td>
							<td>
								<div align="center">
									���ܺ��ִ���
								</div>
							</td>
							<td colspan="2">
								<bean:write name='rs' property="cshzcf" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									������ѧ����
									<br />
									�����
								</div>
							</td>
							<td colspan="2">
								<bean:write name='rs' property="ywzxdkjje" />
							</td>
							<td>
								<div align="center">
									���������
									<br />
									(��λ)����
									<br />
									�����
								</div>
							</td>
							<td>
								<bean:write name='rs' property="ywsshzzjhe" />
							</td>
							<td>
								<div align="center">
									ѧ�ѽ���
									<br />
									���
								</div>
							</td>
							<td>
								<bean:write name='rs' property="xfjnqk" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									�����������
								</div>
							</td>
							<td colspan="2">
								<div align="right">
									<bean:write name='rs' property="xzxfqk" />&nbsp;
									Ԫ/��&nbsp;
								</div>
							</td>
							<td colspan="4">
								&nbsp;(1)&nbsp;��ʳ��&nbsp;&nbsp;
								<logic:empty name='rs' property="hsf">
									&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name='rs' property="hsf">
									<bean:write name='rs' property="hsf" />
								</logic:notEmpty>
								Ԫ/�£�&nbsp;(2)&nbsp;��������&nbsp;&nbsp;
								<logic:empty name='rs' property="qtfy">
									&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name='rs' property="qtfy">
									<bean:write name='rs' property="qtfy" />
								</logic:notEmpty>
								Ԫ/��
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									�������Դ
								</div>
							</td>
							<td colspan="6">
								&nbsp;��ͥ����&nbsp;&nbsp;
								<logic:empty name='rs' property="shf_jtgg">
									&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name='rs' property="shf_jtgg">
									<bean:write name='rs' property="shf_jtgg" />
								</logic:notEmpty>
								Ԫ/�£�&nbsp;������Դ:&nbsp;&nbsp;
								<logic:empty name='rs' property="shf_qtly">
									&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name='rs' property="shf_qtly">
									<bean:write name='rs' property="shf_qtly" />
								</logic:notEmpty>
								Ԫ/��
							</td>
						</tr>
					</table>
					<table width="100%" class="tbstyle">
						<tr>
							<td rowspan="6" width="4%">
								<div align="center">
									��
									<br />
									ͥ
									<br />
									��
									<br />
									Ա
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
							<td width="10%">
								<div align="center">
									��ν
								</div>
							</td>
							<td width="16%">
								<div align="center">
									��&nbsp;&nbsp;��
								</div>
							</td>
							<td width="10%">
								<div align="center">
									����
								</div>
							</td>
							<td width="20%">
								<div align="center">
									������(Ԫ)
								</div>
							</td>
							<td width="40%">
								<div align="center">
									��&nbsp;&nbsp;λ
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy1_cw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy1_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jicy1_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy1_sr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy1_dw" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy2_cw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy2_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jicy2_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy2_sr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy2_dw" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy3_cw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy3_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jicy3_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy3_sr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy3_dw" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy4_cw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy4_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jicy4_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy4_sr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy4_dw" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy5_cw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy5_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jicy5_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy5_sr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy5_dw" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									��ͥ�˿�����
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtzrks" />
								</div>
							</td>
							<td>
								<div align="center">
									�˾�������(Ԫ/��.��)
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="rjnsr" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div align="center">
									��ͥ����(�������ڴ򡰡̡�)
								</div>
							</td>
							<td colspan="3">
								&nbsp;˫��&nbsp;(&nbsp;
								<logic:equal name='rs' property="jtlx1" value="1">
									��
								</logic:equal>
								&nbsp;)������&nbsp;(&nbsp;
								<logic:equal name='rs' property="jtlx2" value="1">
									��
								</logic:equal>
								&nbsp;)������&nbsp;(&nbsp;
								<logic:equal name='rs' property="jtlx3" value="1">
									��
								</logic:equal>
								&nbsp;)������&nbsp;(&nbsp;
								<logic:equal name='rs' property="jtlx4" value="1">
									��
								</logic:equal>
								&nbsp;)���¶�&nbsp;(&nbsp;
								<logic:equal name='rs' property="jtlx5" value="1">
									��
								</logic:equal>
								&nbsp;)
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
								</div>
							</td>
							<td colspan="5">
								<bean:write name='rs' property="jtknqk" />
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
