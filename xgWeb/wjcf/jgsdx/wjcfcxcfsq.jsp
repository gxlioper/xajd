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
	<base target="_self">
	<script language="javascript">
	
</script>
	<body>

		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/qgzxFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/wjcfjgsdxwh" method="post">
		<center>
		<br/><br/>
			<b>����ɽ��ѧѧ�������У�쿴���������</b>
			<br/>
		
				<table width="98%" id="rsT" class="tbstyle">
					<tr style="height:40px">
					    <td width="98"><div align="center"><bean:message key="lable.xsgzyxpzxy" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </div></td>
					    <td width="208" align="center">&nbsp;<bean:write name="rs" property="xymc"/></td>
					    <td width="81"> <div align="center">�༶ </div></td>
					    <td width="183" align="center">&nbsp;<bean:write name="rs" property="bjmc"/></td>
					    <td width="137"> <div align="center">���� </div></td>
					    <td width="242" align="center">&nbsp;<bean:write name="rs" property="xm"/></td>
					  </tr>
					  <tr style="height: 40px">
					    <td> <div align="left">�ܴ���ԭ�� </div></td>
					    <td colspan="3" align="center">&nbsp;<bean:write name="rs" property="cfyymc"/></td>
					    <td> <div align="center">�ܴ���ʱ�� </div></td>
					    <td align="center">&nbsp;<bean:write name="rs" property="cfsj"/></td>
					  </tr>
					  <tr style="">
					    <td>
					    <br/><br/><br/><br/><br/><br/><br/><br/>
					    <p>�����ڼ�</p>
					    <p>��ʵ����</p>
					    <br/><br/><br/><br/><br/><br/><br/><br/>
					    </td>
					    
					    <td colspan="5">&nbsp;</td>
					  </tr>
					  <tr>
					    <td>
					    <br/><br/><br/><br/><br/><br/><br/><br/>
					     ����Ա���
					     <br/><br/><br/><br/><br/><br/><br/><br/>
					     </td>
					     
					    <td colspan="5">&nbsp;</td>
					  </tr>
					  <tr>
					    <td>
					    <br/><br/><br/><br/><br/><br/><br/><br/>
					    <bean:message key="lable.xsgzyxpzxy" />���
					     <br/><br/><br/><br/><br/><br/><br/><br/>
					     </td>
					     
					    <td colspan="5">&nbsp;</td>
					  </tr>
				</table>
				</center>
				<div class="buttontool" align="center">
					<button type="button" class="button2"
						onclick="expTab('rsT','����ɽ��ѧѧ�������У�쿴���������','')">
						�� ӡ/Ԥ ��
					</button>
				</div>
			</html:form>
	</body>
</html>
