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
	<html:form action="zzsf_xsxfjmsqb.do" method="post">
		<input type="hidden" name="tableName" id="tableName"
			value="<bean:write name="sqlb" />" />
		<table width="100%" border="1" class="tbstyle" id="theTable">
			<tr>
				<td colspan="8">
					<p align="center" style="font-size:24px">
						����ʦԺ&nbsp;
						<bean:write name='rs' property="nd" />
						&nbsp;ѧ��Ⱦ�������ѧ��������ѧ�ѵǼǱ�
					</p>
				</td>
			</tr>
			<tr>
				<td scope="col" width="10%">
					<div align="center">
						ϵ��
					</div>
				</td>
				<td scope="col" width="15%">
					<div align="center">
						<bean:write name='rs' property="zymc" />
					</div>
				</td>
				<td scope="col" width="10%">
					<div align="center">
						ѧ�����
					</div>
				</td>
				<td scope="col" width="15%">
					<div align="center">
						<bean:write name='rs' property="xslbmc" />
					</div>
				</td>
				<td scope="col" width="10%">
					<div align="center">
						�༶
					</div>
				</td>
				<td scope="col" width="15%">
					<div align="center">
						<bean:write name='rs' property="bjmc" />
					</div>
				</td>
				<td scope="col" width="10%">
					<div align="center">
						ѧ��
					</div>
				</td>
				<td scope="col" width="15%">
					<div align="center">
						<bean:write name='rs' property="xh" />
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						רҵ
					</div>
				</td>
				<td colspan="7">
					<bean:write name='rs' property="zymc" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						����
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="xm" />
					</div>
				</td>
				<td>
					<div align="center">
						��������
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="csrq" />
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						���֤��
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="sfzh" />
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						��Դ��
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="syd" />
					</div>
				</td>
				<td>
					<div align="center">
						������ò
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="zzmm" />
					</div>
				</td>
				<td>
					<div align="center">
						�Ա�
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="xb" />
					</div>
				</td>
				<td>
					<div align="center">
						�ҳ�����
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jzxm" />
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						��ͥסַ
					</div>
				</td>
				<td colspan="5">
					<bean:write name='rs' property="jtdz" />
				</td>
				<td>
					<div align="center">
						�ʱ�
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="yzbm" />
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						��ͥ�绰
					</div>
				</td>
				<td colspan="2">
					<bean:write name='rs' property="jtdh" />
				</td>
				<td>
					<div align="center">
						�����ַ
					</div>
				</td>
				<td colspan="2">
					<bean:write name='rs' property="ssdz" />
				</td>
				<td>
					<div align="center">
						����绰
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="ssdh" />
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						��ͥ����������˾������룩
					</div>
				</td>
				<td colspan="7">
					<bean:write name='rs' property="jtjjqk" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						Ժ��ϵ���Ѳ������
					</div>
				</td>
				<td colspan="3">
					<bean:write name='rs' property="yxknbzqk" />
				</td>
				<td>
					<div align="center">
						�ڹ���λ
					</div>
				</td>
				<td colspan="3">
					<bean:write name='rs' property="qggw" />
				</td>
			</tr>
			<tr>
				<td rowspan="2" scope="row">
					<div align="center">
						ƽʱ�������
					</div>
				</td>
				<td colspan="5" rowspan="2">
					<bean:write name='rs' property="psbxqk" />
				</td>
				<td>
					<div align="center">
						��������
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="sqzl" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						������
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="sqje" />
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						���˻����
					</div>
				</td>
				<td colspan="3">
					<bean:write name='rs' property="gehjqk" />
				</td>
				<td>
					<div align="center">
						ѧϰ�ɼ�(�༶����)
					</div>
				</td>
				<td colspan="3">
					<bean:write name='rs' property="xxcj" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />���
					</div>
				</td>
				<td colspan="7">
					<div align="left">
						ͬ�����ѧ��&nbsp;
						<bean:write name='rs' property="tyjmje" />
						Ԫ&nbsp;&nbsp; (��&nbsp;
						<bean:write name='rs' property="tyjmjedx" />
						)
					</div>
					<bean:write name='rs' property="xyshyj" />
					<div align="right">
						�ֹ��쵼ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						���£�&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						ѧУ���
					</div>
				</td>
				<td colspan="7">
					<bean:write name='rs' property="xxshyj" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						Ժ�쵼���
					</div>
				</td>
				<td colspan="7">
					&nbsp;
					<br />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						��ע
					</div>
				</td>
				<td colspan="7">
					<bean:write name='rs' property="bz" />
				</td>
			</tr>
			<tr>
				<td colspan="8" scope="row">
					ע����1������ѧϰ�ɼ���д�߿��������༶������
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
