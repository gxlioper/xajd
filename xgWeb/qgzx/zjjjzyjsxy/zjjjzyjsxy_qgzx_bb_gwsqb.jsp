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
<html:html locale="true">
<head>

	<title>qgzx_bb_gwsqb.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

</head>
<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<base target="_self">
<script language="javascript" src="js/function.js"></script>
<body>
	<h2 align="center">
		ѧ���ڹ���ѧ�����
	</h2>
	<h2 align="center">
		(${rsTime.year}��${rsTime.month} ��)
	</h2>
	<table class="tbstyle" align="center" width="80%;height:100%">
		<tr>
			<td width="55" height="34">
				����
			</td>
			<td width="116">
				${rs.xm}
			</td>
			<td width="37">
				�Ա�
			</td>
			<td width="100">
				${rs.xb}
			</td>
			<td width="72">
				��Ժ������
			</td>
			<td width="71">
				${rs.xymc }
			</td>
			<td width="55">
				�༶
			</td>
			<td width="98">
				${rs.bjmc}
			</td>
			<td width="69">
				ѧ��
			</td>
			<td width="43">
				${rs.xh}
			</td>
		</tr>
		<tr>
			<td height="39">
				����
			</td>
			<td>
				${rs.jg}
			</td>
			<td>
				רҵ
			</td>
			<td colspan="3">
				${rs.zymc}
			</td>
			<td>
				ӦƸ��λ
			</td>
			<td>
				${rs.gwmc }
			</td>
			<td>
				�Ƿ�������
			</td>
			<td>
				${rs.isPks }
			</td>
		</tr>
		<tr>
			<td height="36">
				����
			</td>
			<td colspan="9">
				${rs.kh}
			</td>
		</tr>
		<tr>
			<td height="36">
				�س�
			</td>
			<td colspan="9">
				${rs.yhtc}
			</td>
		</tr>
		<tr>
			<td>
				<p align="center">
					��
				</p>
				<p align="center">
					ͥ
				</p>
				<p align="center">
					��
				</p>
				<p align="center">
					��
				</p>
			</td>
			<td colspan="9">
				${rs.jtjjknqk}
			</td>
		</tr>
		<tr>
			<td>
				<p align="center">
					Ӧ
				</p>
				<p align="center">
					Ƹ
				</p>
				<p align="center">
					��
				</p>
				<p align="center">
					��
				</p>
			</td>
			<td colspan="9">
				${rs.xssq}
			</td>
		</tr>
	</table>
	<p>
		&nbsp;
	</p>
</body>
</html:html>
