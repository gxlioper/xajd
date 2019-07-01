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

<%
		response.setHeader("prama", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setDateHeader("expires", 0);
%>


<body>
	<html:form action="/txsqb.do" method="post">
		<input type="hidden" name="tableName" id="tableName"
			value="<bean:write name="sqlb" />" />
		<p align="center" style="font-size:24px">
			<bean:write name="sqlb" />
			�����
		</p>
		<table width="100%" class="tbstyle" id="theTable">
			<tr>
				<td width="77">
					<p align="center">
						����
					</p>
				</td>
				<td width="112">
					<p align="center">
						<bean:write name='rs' property="xm" />
					</p>
				</td>
				<td width="149">
					<p align="center">
						�Ա�
					</p>
				</td>
				<td width="141">
					<p align="center">
						<bean:write name='rs' property="xb" />
					</p>
				</td>
				<td width="125">
					<p align="center">
						���֤����
					</p>
				</td>
				<td width="355">
					<p align="center">
						<bean:write name='rs' property="sfzh" />
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<p align="center">
						����ԺУ����
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						<bean:write name='rs' property="xxmc" />
					</p>
				</td>
				<td width="125">
					<p align="center">
						ѧ��
					</p>
				</td>
				<td width="355">
					<p align="center">
						<bean:write name='rs' property="xh" />
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<p align="center">
						�������У�֧��)
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						<bean:write name='rs' property="dkyh" />
					</p>
				</td>
				<td width="125">
					<p align="center">
						�����ܽ��
					</p>
				</td>
				<td width="355">
					<p align="center">
						<bean:write name='rs' property="dkzje" />
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<p align="center">
						��������
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						<bean:write name='rs' property="dkqx" />
					</p>
				</td>
				<td width="125">
					<p align="center">
						Ӧ����Ϣ
					</p>
				</td>
				<td width="355">
					<p align="center">
						<bean:write name='rs' property="yflx" />
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<p align="center">
						��������ǩ��
					</p>
				</td>
				<td colspan="4">
					<p align="center">
						<br><br><br><br><br><br>
					</p>
				</td>
			</tr>
			<tr>
				<td width="77" rowspan="3">
					<p align="center">
						��������
					</p>
					<p align="center">
						������������д�����йز��ϣ�
					</p>
				</td>
				<td width="112">
					<p align="center">
						�� ��
					</p>
					<p align="center">
						�� Ϣ
					</p>
				</td>
				<td colspan="4">
					<p align="center">
						<bean:write name='rs' property="bktx" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="112">
					<p align="center">
						�� ��
					</p>
					<p align="center">
						�� Ϣ
					</p>
				</td>
				<td colspan="4">
					<p align="center">
						<bean:write name='rs' property="jytx" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="112">
					<p align="center">
						�� ��
					</p>
					<p align="center">
						�� Ϣ
					</p>
				</td>
				<td colspan="4">
					<p align="center">
						<bean:write name='rs' property="zctx" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="89">
					<p align="center">
						ѧУ���
					</p>
				</td>
				<td colspan="5">
					<bean:write name="rs" property="xyshyj" />
					<br><br>
					<p align="right">
						ѧУ����ǩ�£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
					</p>
				</td>
			</tr>
			<tr>
				<td width="89">
					<p align="center">
						ѧУ���
					</p>
				</td>
				<td colspan="5">
					<bean:write name="rs" property="xxshyj" />
					<br><br>
					<p align="right">
						<bean:message key="lable.xsgzyxpzxy" />����ǩ�£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
					</p>
				</td>
			</tr>
			<tr>
				<td width="77">
					<p align="center">
						��ί���
					</p>
				</td>
				<td colspan="5">
					<p>&nbsp;
					</p>
					<p align="center">
						ͬ����Ϣ �ߣߣߣ� ��
					</p>
					<p align="center">
						�ϼ�����ң���д�� �ߣߣߣߣߣ� Ԫ��Сд�� �ߣߣߣ� Ԫ
					</p>
					<p align="right">
						��ί����ǩ�£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						�����ˣ���ǩ�£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
					</p>
					
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<p align="right">
					 ע������һʽ���ݣ���ί��ѧУ�������˸�һ�ݡ�
					</p>
				</td>
			</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="��ӡ"
			onclick="expTab('theTable','�� ѧ �� �� �� Ϣ �� �� �� �� ��')" />
	</div>
</body>
</html:html>
