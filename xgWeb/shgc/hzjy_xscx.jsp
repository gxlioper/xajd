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
		<meta name="Copyright" content="正方软件 zfsoft" />
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
	<script language="javascript">

</script>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<body>
		<html:form action="hzjy_sqxx.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<bean:message bundle="shgc" key="hzjy_sqxx" />
				</div>
				</div>
			<fieldset>
				<legend>
					申请结果
				</legend>
				<logic:notPresent name="rs">
					<p align=center>
						没有相应的申请信息！
					</p>	
				</logic:notPresent>
				<logic:present name="rs">
				   <table class="tbstyle" align=center width="90%">
				     <thead>
				   		<tr>
					        <logic:iterate id="s" name="title">
					        	<td align=center>
					        		<bean:write name="s" />
					        	</td>
					        </logic:iterate>
					    </tr>
					  </thead>
					  <tbody>
					    <logic:iterate id="x" name="rs">
					    	<tr>
					    		<logic:iterate name="x" id="y">
					    			<td>
					    				<bean:write name="y"/>
					    			</td>
					    		</logic:iterate>
					    	</tr>
					    </logic:iterate> 
					  </tbody>     
				   </table>
				 </logic:present>  
			</fieldset>
		</html:form>
	</body>
</html>
