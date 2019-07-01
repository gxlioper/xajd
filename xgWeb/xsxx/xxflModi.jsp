<%@ page language="java" pageEncoding="GBK"%>

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
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>

	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="style/main.css" type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	
	<script type="text/javascript" src="js/String.js"></script>
	<script type="text/javascript" src="js/commanFunction.js"></script>	
	<script type="text/javascript" src="js/function.js"></script>
	<script type="text/javascript">		
		function send(){	
			refreshForm("xsxxflgl.do?method=xxflModi&type=save");	
		}
	</script>
	<body>		
		<html:form action="/xsxxflgl" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ�ã�ѧ����Ϣ - ��Ϣ���� - ��Ϣά��
			</div>
		</div>			
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="5" align="center">
							<center>
								��Ϣ�޸�
							<center>
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right" width="50%">
						ѧ�ţ�
					</td>
					<td>
						${rs.xh}
						<html:hidden property="xh" name="rs"/>
						<input type="hidden" name="pkValue" value="${rs.pkValue}"/>
					</td>
				</tr>
				<tr>
					<td align="right" width="15%">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td>
						${rs.zrxy}
					</td>
				</tr>
				<tr>
					<td align="right" width="15%">
						רҵ��
					</td>
					<td>
						${rs.zrzy}
					</td>
				</tr>
				<tr>
					<td align="right" width="15%">
						<font color="red">*</font>��Ȼ�༶��
					</td>
					<td>
						<html:select property="zrbjdm" name="rs">
							<html:options collection="bjList" property="bjdm" labelProperty="bjmc"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right" width="15%">
						ԭʼ�༶��
					</td>
					<td>
						${rs.ysbjmc}
					</td>
				</tr>
				<tr>
					<td align="right" width="15%">
						ģ��༶��
					</td>
					<td>
						${rs.mkbjmc}
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn">
					<button type="button" class="button2" style="height:20px;width:80px"
						onclick="send();">
						�� ��
					</button>	
					&nbsp;&nbsp;&nbsp;&nbsp;	
					<button type="button" class="button2" style="height:20px;width:80px"
						onclick="Close();return false;">
						�� ��
					</button>
			</div>
			<logic:present name="result">
				<input type="hidden" id="message" value="${message}"/>
				<script>
						alert(document.getElementById("message").value);
						Close();
						if(window.dialogArguments){
							window.dialogArguments.document.getElementById('search_go').click();
						}						
				</script>
			</logic:present>
		</html:form>
	</body>
</html>
