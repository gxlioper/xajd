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
			var zxjdm = document.getElementById('zxjdm').value;
			document.forms[0].action = "/xgxt/n05_xszz.do?method=wszxj2sq&xmdm="+zxjdm;
			document.forms[0].submit();
		}
	</script>
<body>
	<html:form action="zxdksq.do" method="post">
		<input type="hidden" id="zxjdm" name="zxjdm"
			value="<bean:write name="rs" property="zxjdm" />">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
						<h2>
							<strong><bean:write name='rs' property="xxmc" />��ͥ��������ѧ����������ѧ�������</strong>
						</h2>
					</div>
				</td>
			</tr>
			<tr>
				<td height="40px">
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;��������ѧ�����ƣ�<bean:write name='rs' property="zxjmc" />
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="15%">
								<div align="center">
									����
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									�Ա�
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td width="15%">
								<div align="center">
									ѧ��
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="xh" />
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
									רҵ
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:empty name="rs" property="nj">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="nj">
										<bean:write name='rs' property="nj" />
									</logic:notEmpty>
									��
									<logic:empty name="rs" property="bjmc">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="bjmc">
										<bean:write name='rs' property="bjmc" />
									</logic:notEmpty>
									��
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��ͥ��ַ
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtdz" />
								</div>
							</td>
							<td>
								<div align="center">
									��������
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="yzbm" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��ϵ�绰
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="lxdh" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									��ѧ���ú�������
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="bxnhdhzzz" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��������
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="bkks" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									��ѧ��༶�ۺϲ�������
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="sxnbjzhcppm" />
								</div>
							</td>
							<td width="17%">
								<div align="center">
									��У�Ƿ��ܹ�����
								</div>
							</td>
							<td width="8%">
								<div align="center">
									<bean:write name='rs' property="zxfscf" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��ͥ�˿�
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtrk" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									��ͥ�˾�������
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtrjysr" />
								</div>
							</td>
						</tr>
					</table>
					<table width="100%" class="tbstyle">
						<tr>
							<td rowspan="6" width="4%">
								<div align="center">
									��<br />ͥ<br />��<br />Ҫ<br />��<br />Ա
								</div>
							</td>
							<td width="20%">
								<div align="center">
									����
								</div>
							</td>
							<td width="11%">
								<div align="center">
									����
								</div>
							</td>
							<td width="20%">
								<div align="center">
									�뱾�˹�ϵ
								</div>
							</td>
							<td width="20%">
								<div align="center">
									ְҵ
								</div>
							</td>
							<td width="25%">
								<div align="center">
									������(Ԫ)
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_gx" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy1_sr" />&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_gx" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy2_sr" />&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_gx" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy3_sr" />&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_gx" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy4_sr" />&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_gx" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy5_sr" />&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								&nbsp;&nbsp;��������������ԭ����дҪ���ּ������������ʵ��Ϳ����Ч����
								<br />
								<logic:empty name='rs' property="sqyy">
									<br />
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name='rs' property="sqyy">
									&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="sqyy" />
								</logic:notEmpty>
								<br />
								<div align="right">
									������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<div align="right">
									�������ڣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
							</td>
						</tr>
						<tr>
							<td colspan="6">
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��������
								<br />
								&nbsp;&nbsp;1������ѧ���Ƿ��м�ͥ��������֤����
								<u>
									<logic:empty name='rs' property="xy_sfyknzm">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name='rs' property="xy_sfyknzm">
										&nbsp;&nbsp;<bean:write name='rs' property="xy_sfyknzm" />&nbsp;&nbsp;
									</logic:notEmpty>
								</u>��<br />
								&nbsp;&nbsp;2��ƶ�������������Ƿ�������ѧ����������ݣ�
								<u>
									<logic:empty name='rs' property="xy_sfkns">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name='rs' property="xy_sfkns">
										&nbsp;&nbsp;<bean:write name='rs' property="xy_sfkns" />&nbsp;&nbsp;
									</logic:notEmpty>
								</u>��<br />
								&nbsp;&nbsp;3��������ѧ��༶�ۺϲ�������Ϊ
								<u>
									<logic:empty name='rs' property="xy_sxnzhcppm">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name='rs' property="xy_sxnzhcppm">
										&nbsp;&nbsp;<bean:write name='rs' property="xy_sxnzhcppm" />&nbsp;&nbsp;
									</logic:notEmpty>
								</u>%��<br />
								&nbsp;&nbsp;4���༶��������ͨ����
								<u>
									<logic:empty name='rs' property="xy_bjmzpytgl">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name='rs' property="xy_bjmzpytgl">
										&nbsp;&nbsp;<bean:write name='rs' property="xy_bjmzpytgl" />&nbsp;&nbsp;
									</logic:notEmpty>
								</u>%��<br />
								&nbsp;&nbsp;5���Ƿ�ͬ��������룺��
								<logic:equal name='rs' property="xy_sftjsq" value="��">
									&nbsp;��
								</logic:equal>
								//��
								<logic:equal name='rs' property="xy_sftjsq" value="��">
									&nbsp;��
								</logic:equal>
								<br />
								&nbsp;&nbsp;6���������˵����<br />
								<logic:empty name='rs' property="qtsm">
									<br /><br /><br />
								</logic:empty>
								<logic:notEmpty name='rs' property="qtsm">
									&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="qtsm" />
								</logic:notEmpty>
								<br />
								<div align="right">
									��λ���£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<div align="right">
									�����λ��꼶����Աǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<div align="right">
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
							</td>
						</tr>
						<tr>
							<td colspan="6">
								&nbsp;&nbsp;ѧ����������ѧ�����ίԱ�ᣩ�����
								<br />
								<logic:empty name='rs' property="xxshyj">
									<br /><br /><br />
								</logic:empty>
								<logic:notEmpty name='rs' property="xxshyj">
									&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="xxshyj" />
								</logic:notEmpty>
								<br />
								<div align="right">
									ǩ�£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<div align="right">
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div align="left">
						&nbsp;&nbsp;ע����Ҫ�󸽼�ͥ����״��֤����ӡ��
					</div>
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
