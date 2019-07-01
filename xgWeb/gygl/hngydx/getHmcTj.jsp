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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<base target="_self" />
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/hngydx_gygl" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã���Ԣ���� - ��Ϣά�� - ѧ��ס����Ϣ - ѡ���Ա�
				</div>
			</div>
			<fieldset>
				<legend>
					�Ա�ѡ��
				</legend>
				<div class="buttontool" id="btn" align="center">
					<table class="tbstyle" >
						<tr >
							<td>
								�Ա�
							</td>
							<td>
								<html:select property="xb" 
									styleId="xb" style="width:80px;">
									<html:option value=""></html:option>
									<html:option value="Ů">Ů</html:option>
									<html:option value="��">��</html:option>
									<html:option value="���">���</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td colspan=2 align=center>
								<button class="button2" onclick="sendTj()">
									ȷ ��
								</button>
								&nbsp;&nbsp;
								<button class="button2" onclick="Close();return false;">
									�� ��
								</button>
							</td>
						</tr>
					</table>
				</div>
			</fieldset>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
<script type="text/javascript">
  function sendTj(){
       var url="/xgxt/hngydx_gygl.do?method=HmcTj"; 
    document.forms[0].action = url;
    document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
  }
</script>

</html>


