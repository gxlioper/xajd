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
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
							<h2>
						<strong>
								<bean:write name='rs' property="nd" />
								&nbsp;���ƶ��ѧ�����Ѳ��������
						</strong>
							</h2>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="6%" rowspan="5" scope="col">
								<div align="center">
									��<br />��<br />��<br />��
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									����
								</div>
							</td>
							<td scope="col" width="20%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									�Ա�
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									��������
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									<bean:write name='rs' property="csny" />
								</div>
							</td>
							<td width="16%" rowspan="3" scope="col">
								<div align="center">
									��Ƭ
								</div>
							</td>
						</tr>
						<tr>
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
									����
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="mzmc" />
								</div>
							</td>
							<td>
								<div align="center">
									��ѧʱ��
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="rxny" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<div align="center">
									<bean:write name='rs' property="xymc" />
									&nbsp;<bean:message key="lable.xsgzyxpzxy" />&nbsp;&nbsp;
									<bean:write name='rs' property="zymc" />
									&nbsp;ϵ&nbsp;&nbsp;
									<bean:write name='rs' property="bjmc" />
									&nbsp;��
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									�Ƿ�������ѧ����
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="sfcjzxdk" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									�Ƿ�μ��ڹ���ѧ
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="sfcjqgzx" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									�ֻ�����
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="sjhm" />
								</div>
							</td>
							<td>
								<div align="center">
									����绰
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jldh" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="6" scope="row">
								<div align="center">
									��<br />ͥ<br />ֱ<br />ϵ<br />��<br />��<br />��<br />��
								</div>
							</td>
							<td colspan="2">
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
									�뱾�˹�ϵ
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									����(��ѧϰ)��λ
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtcy1_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy1_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy1_gzdw" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtcy2_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy2_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy2_gzdw" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtcy3_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy3_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy3_gzdw" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtcy4_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy4_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy4_gzdw" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtcy5_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy5_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy5_gzdw" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="3" scope="row">
								<div align="center">
									��<br />ͥ<br />��<br />��<br />��<br />��
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									��ͥ����
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jthk" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									��ͥ�˿�����
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtrks" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									��ͥ��������
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtyzsr" />
								</div>
							</td>
							<td>
								<div align="center">
									�˾�������
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="rjysr" />
								</div>
							</td>
							<td>
								<div align="center">
									������Դ
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="srly" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									��ͥסַ
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtzz" />
								</div>
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
					</table>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="6%" rowspan="7" scope="col">
								<div align="center">
									��<br />ѧ<br />��<br />ѧ<br />ϰ<br />��<br />��<br />��<br />��
								</div>
							</td>
							<td scope="col" width="20%">
								<div align="center">
									�γ�
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									�ɼ�
								</div>
							</td>
							<td scope="col" width="20%">
								<div align="center">
									�γ�
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									�ɼ�
								</div>
							</td>
							<td scope="col" width="20%">
								<div align="center">
									�γ�
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									�ɼ�
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="kc1_mc" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="kc1_cj" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="kc2_mc" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="kc2_cj" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="kc3_mc" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="kc3_cj" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="kc4_mc" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="kc4_cj" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="kc5_mc" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="kc5_cj" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="kc6_mc" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="kc6_cj" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="kc7_mc" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="kc7_cj" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="kc8_mc" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="kc8_cj" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="kc9_mc" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="kc9_cj" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="kc10_mc" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="kc10_cj" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="kc11_mc" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="kc11_cj" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="kc12_mc" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="kc12_cj" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="kc13_mc" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="kc13_cj" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="kc14_mc" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="kc14_cj" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="kc15_mc" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="kc15_cj" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="kc16_mc" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="kc16_cj" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="kc17_mc" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="kc17_cj" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="kc18_mc" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="kc18_cj" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
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
									<br />
									��
									<br />
									��
								</div>
							</td>
							<td colspan="6">
								<br />
								<bean:write name='rs' property="sqly" />
								<br />
								<div align="right">
									������ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
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
								</div>
							</td>
							<td colspan="6">
								<br />
								<br />
								<br />
								<br />
								<div align="right">
									��&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									ϵ
									<br />
									(��)
									<br />
									��
									<br />
									��
								</div>
							</td>
							<td colspan="6">
								<br />
								<bean:write name='rs' property="xyshyj" />
								<br />
								<div align="right">
									��&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
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
							<td colspan="6">
								<br />
								<br />
								<br />
								<br />
								<div align="right">
									��&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									ѧ
									<br />
									Ժ
									<br />
									��
									<br />
									��
								</div>
							</td>
							<td colspan="6">
								<br />
								<bean:write name='rs' property="xxshyj" />
								<br />
								<div align="right">
									��&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;
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
	</div>
</body>
</html:html>
