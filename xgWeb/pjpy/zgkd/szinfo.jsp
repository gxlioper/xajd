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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript">
</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/pjpyzgkdwh" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��������� - ��Ϣά�� - ���ʲ���
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="left" nowrap="nowrap">
							&nbsp;&nbsp;
							ѧ�ţ�
							${userName }
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							������
							${userNameReal }
						</td>
					</tr>
				</thead>
			</table>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						<font color="blue">��ʾ��������ͷ��������</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
									<td align="center" onclick="tableSort(this)" nowrap>
										ѧ��
									</td>
									<td align="center" onclick="tableSort(this)" nowrap>
										Ʒ�²����÷�
									</td>
									<td align="center" onclick="tableSort(this)" nowrap>
										�γ�ѧϰ�ɼ������÷�
									</td>
									<td align="center" onclick="tableSort(this)" nowrap>
										���Ĳ����÷�
									</td>
									<td align="center" onclick="tableSort(this)" nowrap>
										�������ʲ����÷�
									</td>
									<td align="center" onclick="tableSort(this)" nowrap>
										�������ʲ����ȼ�
									</td>
									<td align="center" onclick="tableSort(this)" nowrap>
										��չ���ʲ����÷�
									</td>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr align="center" onclick="rowMoreClick(this,'',event);" style="cursor:hand">
									<logic:iterate id="v" name="s" >
									<td>
										<bean:write name="v" />
									</td>
									</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>			
		</html:form>
	</body>
</html>
