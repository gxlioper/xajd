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
				<td>
					<div align="center">
							<h3>
						<strong>
								����ʦ��<bean:message key="lable.xsgzyxpzxy" />������ѧ"��ɫͨ��"�����
						</strong>
							</h3> 
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td width="4%" rowspan="3" scope="col">
								<div align="center">
									�������
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									����
								</div>
							</td>
							<td scope="col" width="12%">
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
									<bean:write name='rs' property="xsrq" />
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									ѧ��
								</div>
							</td>
							<td colspan="2" scope="col">
								<div align="center">
									<bean:write name='rs' property="xh" />
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
									<bean:write name='rs' property="mzmc" />
								</div>
							</td>
							<td>
								<div align="center">
									������ò
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="zzmmmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									ϵ��
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
									<bean:write name='rs' property="zymc" />
								</div>
							</td>
							<td>
								<div align="center">
									�༶
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="bjmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="3" scope="row">
								<div align="center">
									��ͥ�������
								</div>
							</td>
							<td>
								<div align="center">
									��ͥ����
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jthk" />
								</div>
							</td>
							<td>
								<div align="center">
									��ͥ�˿�����
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtzrks" />
								</div>
							</td>
							<td>
								<div align="center">
									��������
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="nzsr" />
								</div>
							</td>
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
						</tr>
						<tr>
							<td>
								<div align="center">
									��ͥסַ
								</div>
							</td>
							<td colspan="5">
								<bean:write name='rs' property="jtzz" />
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
									��ͥ��������
									<br />
									����Ҫԭ��
									<br />
									������ԭ��
								</div>
							</td>
							<td colspan="8">
								<bean:write name='rs' property="jtjjknzyyy" />
							</td>
						</tr>
						<tr>
							<td rowspan="6" scope="row">
								<div align="center">
									��ͥ��Ҫ��Ա���
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
									��ν
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									���ڵ�λ
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									������
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
									<bean:write name='rs' property="jtcy1_cw" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jtcy1_szdw" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy1_ysr" />&nbsp;
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
									<bean:write name='rs' property="jtcy2_cw" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jtcy2_szdw" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy2_ysr" />&nbsp;
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
									<bean:write name='rs' property="jtcy3_cw" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jtcy3_szdw" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy3_ysr" />&nbsp;
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
									<bean:write name='rs' property="jtcy4_cw" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jtcy4_szdw" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy4_ysr" />&nbsp;
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
									<bean:write name='rs' property="jtcy5_cw" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jtcy5_szdw" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy5_ysr" />&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									Ƿ�����
								</div>
							</td>
							<td>
								<div align="center">
									ѧ��
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="qfqk_xf" />
								</div>
							</td>
							<td>
								<div align="center">
									ס�޷�
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="qfqk_zsf" />
								</div>
							</td>
							<td>
								<div align="center">
									�̲ķ�
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="qfqk_jcf" />
								</div>
							</td>
							<td width="5%">
								<div align="center">
									�ϼ�
								</div>
							</td>
							<td width="7%">
								<div align="center">
									<bean:write name='rs' property="qfqk_hj" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									�ɷѼƻ�
								</div>
							</td>
							<td colspan="8">
								<bean:write name='rs' property="jfjh" />
							</td>
						</tr>
						<tr>
							<td colspan="5" scope="row">
								<div align="right">
									��ĸ���ڵ�λ�����ί�ᡢ��ί�ᣩ�绰
								</div>
							</td>
							<td colspan="5">
								<bean:write name='rs' property="fmszdwdh" />
							</td>
						</tr>
						<tr>
							<td colspan="10" scope="row">
								<div align="left">
									���˱�֤�������������ʵ��Ч��
								</div>
								<div align="right">
									������ǩ����
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									ϵ������
								</div>
							</td>
							<td colspan="8">
								<bean:write name='rs' property="xysh" />
								<br />
								<bean:write name='rs' property="xyshyj" />
								<br />
								<div align="right">
									�ǹ��£�
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									ϵ����֧���
								</div>
							</td>
							<td colspan="8">
								<br />
								<bean:write name='rs' property="xyzzfzryj" />
								<br />
								<div align="right">
									�ǹ��£�
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									ѧ����������
								</div>
							</td>
							<td colspan="8">
								<bean:write name='rs' property="xxsh" />
								<br />
								<bean:write name='rs' property="xxshyj" />
								<br />
								<div align="right">
									�ǹ��£�
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
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
