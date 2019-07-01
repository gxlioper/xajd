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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
		<script language="javascript">
        function yz(){
       		var temp = document.getElementById("bbbh").value;
       		if((temp == null) || (temp=="")){
       			alert("请先选择要打印的报表!");
       			return false;
       		}
       		
       		document.forms[0].target="_black";
			document.forms[0].action = "/xgxt/loftyReport.do?method=loftyReportOne";
		 	document.forms[0].submit();
	}
    </script>	
		
	<base target="_self">
	<body >
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>			
			<script language="javascript" src="/xgxt/js/function.js"></script>
			<html:form action="/loftyReport.do" method="post">
				<table width="100%" class="tbstyle">
				<thead>
					<tr valign="middle" >
						<td align="center">
							<b style="font-size:14">请选择要打印的报表</b>
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center">
						<html:select property="bbbh" styleId="bbbh">
								<html:option value="">--------------请选择--------------</html:option>
								<html:options collection="list" property="bbbh"
									labelProperty="bbmc" />
							</html:select>
					</td>
				</tr>
				<tr>
					<td align="center">
						<input  class="button2" onclick=yz()
							style="width:80px" value="确  定" />
					</td>
				</tr>
			</table>
				
			</html:form>
		</center>
	</body>
</html>
