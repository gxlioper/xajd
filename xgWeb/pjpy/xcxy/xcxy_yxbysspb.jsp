<%@ page language="java" pageEncoding="GB2312" contentType="text/html;charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title><bean:message key="lable.title" /></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	</head>
<style media="print" type="text/css">
.brk{
	page-break-after:always;
}
td{ font-size:15px;}
</style>
<body>
	<html:form action="/rychqsb" method="post">
		<p>
			<font size="+1">����һ��</font>
		</p>
		<div align="center">
			<font size="+1"><b>${xxmc }�����ѧ��ҵ���Ƽ�������</b>
			</font>
		</div>
		<table width="100%" class="tbstyle">
			<tr>
				<td align="center" height="30px">
					����
				</td>
				<td width="10%">
				${map.xm }
					
				</td>
				<td align="center">
					�Ա�
				</td>
				<td width="10%">
					${map.xb }
				</td>
				<td align="center">
					��������
				</td>
				<td width="10%">
					${map.csrq }
				</td>
				<td align="center">
					����
				</td>
				<td width="10%">
					${map.mzmc }
				</td>
				<td align="center">
					������ò
				</td>
				<td width="13%">
					${map.zzmmmc }
				</td>
			</tr>
			<tr>
				<td align="center">
					<p>
						ϵ(��)
					</p>
					<p>
						רҵ
					</p>
				</td>
				<td colspan="6">
					${map.zymc }
				</td>
				<td align="center">
					<p>
						ѧ��
					</p>
					<p>
						����
					</p>
				</td>
				<td colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td align="center">
					<p>
						��ͥ
					</p>
					<p>
						סַ
					</p>
				</td>
				<td colspan="9">
					${map.syd }
				</td>
			</tr>
			<tr>
				<td align="center">
					<br/><br/>
					<p>
						��У
					</p>
					<p>
						�ڼ�
					</p>
					<p>
						��Ҫ
					</p>
					<p>
						����
					</p>
					<br/>
				</td>
				<td colspan="9">
					<p>
						&nbsp;

					</p>
					<p>
						&nbsp;

					</p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;${map.hjqk }

					</p>
					<p>
						&nbsp;

					</p>
					<p>
						&nbsp;

					</p>
					<br/>
					<p align="right">
						����Ա(������)ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp; ��
						&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td align="center">
					<p>
						<bean:message key="lable.xb" />
					</p>
					<p>
						����
					</p>
					<p>
						�Ƽ�
					</p>
					<p>
						���
					</p>
					<br/>
				</td>
				<td colspan="4">
					<p>
						&nbsp;

					</p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;${map.xyyj }

					</p>
					<p>
						&nbsp;

					</p>
					<p>
					</p><br/>
					<p>
						(����)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;${map.xyn }&nbsp; ��&nbsp;&nbsp;${map.xyy }&nbsp; ��
						&nbsp;&nbsp;${map.xyr }&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
				<td align="center">
					<p>
						<bean:message key="lable.xsgzyxpzxy" />
					</p>
					<p>
						����
					</p>
					<p>
						���
					</p>
					<br/>
				</td>
				<td colspan="4">
					<p>
						&nbsp;

					</p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;${map.xxyj }

					</p>
					<p>
						&nbsp;

					</p>
					<p>
					</p>
					<br/>
					<p>
						(����)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;${map.xxn }&nbsp; ��&nbsp;&nbsp;${map.xxy }&nbsp; ��
						&nbsp;&nbsp;${map.xxr }&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
		</table>
		<ul>
			<font size="+1">ע: </font> 1������һʽ���ݣ�(һ��<bean:message key="lable.xsgzyxpzxy" />�浵��һ��װ��ѧ������)
		</ul>
		<ul>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2�����ϸ��մ˱��ʽ���ߴ���д��ѧ���������ʿ��˶ʿ�����ơ�ר��
		</ul>
	</html:form>
</body>
</html:html>
