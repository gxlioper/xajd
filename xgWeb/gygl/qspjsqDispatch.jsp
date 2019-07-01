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
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script>
		function pageDispatch(){
			var path = val('sqxm');
			refreshForm(path);
		}
	</script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body >	
		<html:form action="/XsgyglDispatch" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
						��ǰ����λ�ã���Ԣ���� - ���� - ��Ԣ��������
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							<b>��ѡ����Ҫ�������Ŀ</b>
						</td>
					</tr>
				</thead>					
				<tr>
					<td align="center">
						<html:select property="sqxm" style="width:160px">
							<html:option value="XsgyglDispatch.do?method=ydwmqssq">�����������</html:option>
							<html:option value="XsgyglDispatch.do?method=xqwmqssq">ѧ����������</html:option>
							<html:option value="XsgyglDispatch.do?method=xnwmqssq">ѧ����������</html:option>
						</html:select>							
					</td>				
				</tr>
				<tr>
					<td align="center">
						<button class="button2" onclick="pageDispatch()" id="buttonSave">
							ȷ ��
						</button>							
					</td>				
				</tr>		
			</table>
		</html:form>
  </body>
</html>
