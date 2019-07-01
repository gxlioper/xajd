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
				<font size="+1">�������</font>
			</p>
			<div align="center">
				<font size="+1"><b>�Ĵ�ʡ��ͨ�ߵ�ѧУ��ר��ʡ�����ҵ���ǼǱ�</b>
				</font>
			</div>
			<table width="100%" class="tbstyle">
				<tr>
					<td >
						ѧУ����
					</td>
					<td colspan="3">
						${xxmc }
					</td>
					<td >
						רҵ����
					</td>
					<td colspan="3">
					${map.zymc }
					</td>
				</tr>
				<tr>
					<td width="10%">
						����
					</td>
					<td width="10%">
					${map.xm }
					</td>
					<td width="10%">
						�Ա�
					</td>
					<td width="20%">
						${map.xb }
						
					</td>
					<td width="10%">
						��������
					</td>
					<td width="10%">
					${map.csrq }
						
					</td>
					<td width="10%">
						����
					</td>
					<td width="10%">
						${map.mzmc }
					</td>
				</tr>
				<tr>
					<td>
						��Դ��
					</td>
					<td colspan="2">
						${map.syd }
					</td>
					<td>
						������ò
					</td>
					<td>
						${map.zzmmmc }
					</td>
					<td>
						ְ��
					</td>
					<td colspan="2">
						${map.drzw }
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<p>
							��Ҫ�¼���
						</p>
						<p>
							&nbsp;

						</p>
						<p>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${map.zysj }

						</p>
						<p>
							&nbsp;

						</p>
						<p>
						</p>
						<p align="right">
							����&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
							${map.sn }��&nbsp;&nbsp;${map.sy }&nbsp; �� &nbsp;&nbsp;${map.sr }&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<p>
							<bean:message key="lable.xsgzyxpzxy" />(ϵ)�����
						</p>
						<p>
							&nbsp;

						</p>
						<p>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${map.xyyj }

						</p>
						<p>
							&nbsp;

						</p>
						<p>
						</p>
						<p align="right">
							����&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
							${map.xyn }��&nbsp;&nbsp;${map.xyy }&nbsp; �� &nbsp;&nbsp;${map.xyr }&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<p>
							ѧУ���������
						</p>
						<p>
							&nbsp;

						</p>
						<p>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${map.xxyj }

						</p>
						<p>
							&nbsp;

						</p>
						<p>
						</p>
						<p align="right">
							����&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
							${map.xxn }��&nbsp;&nbsp;${map.xxy }&nbsp; �� &nbsp;&nbsp;${map.xxr }&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<p>
							�Ĵ�ʡ���������������
						</p>
						<p>
							&nbsp;

						</p>
						<p>
							&nbsp;

						</p>
						<p>
							&nbsp;

						</p>
						<p>
						</p>
						<p align="right">
							����&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
							��&nbsp;&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
			</table>
			<ul>
				<font size="+1">ע: 
				</font>1���˱�һʽ���ݣ���ʡ�����������󷵻�ѧУ��һ��ѧϰ���棬��һ��װ���ҵ�����˵���
			</ul>
			<ul>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2���������ݿɴ�ӡ���øֱ���д���ּ�Ҫ���
			</ul>
	</html:form>
</body>
</html:html>
