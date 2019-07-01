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
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<meta http-equiv="Pragma" http-equiv="no-cache"/>
		<meta http-equiv="Cache-Control" http-equiv="no-cache"/>
		<meta http-equiv="Expires" http-equiv="0"/>
		<meta name="Copyright" content="正方软件 zfsoft"/>
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon"/>
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all"/>
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all"/>
	<script language="javascript"  src="js/function.js"></script>
	<body>
		<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
        %>
		<center>			
			<br><html:form action="/wmqspb_result" method="post">									
		        <logic:empty name="rs">
					<br />
					<br />  
					<p align="center">
						未找到任何记录！
					</p>
				</logic:empty>
				  <logic:present name="rs">				
				   <logic:notEmpty name="rs">	
				   <div align="center" id="rsTable" >
				   <h4><bean:write name="nd"/>年<bean:write name="yf"/>月份文明寝室	</h4>        		     		 				
						<table width="100%"  class="tbstyle" >
							<tr>
								<td align="center">
								系别
								</td>
								<td align="center">
								班级
								</td>
								<td align="center">
								文明寝室
								</td>
							</tr>
							<logic:iterate name="rs" id="s">
								  <bean:size id="tSize" name="s" property="qsList"/>								 																						
								  <logic:iterate id="v" name="s" property="xyList">	
								 <tr style="cursor:hand">
								  <td rowspan="<%=tSize%>"><bean:write name="v" property="xymc"/></td>
								<logic:notEqual name="tSize" value="0">								
									<logic:iterate id="b" name="s" property="qsList" >										
									<td>&nbsp;<bean:write name="b" property="bjmc"/></td>
									<td>&nbsp;<bean:write name="b" property="cy"  /></td>																									
									<% out.print("</tr>"); %>								
									</logic:iterate>
								</logic:notEqual>
								<logic:equal name="tSize" value="0">									
									<td>&nbsp;</td>
									<td>&nbsp;</td>									
								</logic:equal>
								</tr>																																																									   																										
								</logic:iterate>
								</logic:iterate>
								<tr>
									<td align="left" colspan="3">
										备注：
									</td>

								</tr>
							</table>
						</div>
						<div class="buttontool" align="center">						
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="expTab('rsTable','','')">
							打 印 报 表
						</button>
					</div>	
				</logic:notEmpty>
			  </logic:present>																				
			</html:form>
		 </center>
		<script type="text/javascript" src="js/bottomButton.js"></script>				
	</body>
</html>
