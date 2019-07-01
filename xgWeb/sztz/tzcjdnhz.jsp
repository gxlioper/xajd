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
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>	
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript"  src="js/function.js"></script>
	<body>
		<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
        %>
		<center>			
			<br><html:form action="/sztzcj_hz_query" method="post">					
				<span align="center" style="font:17px;"> 
			     大学生素质培养拓展学分登记表
		        </span>
		        <logic:empty name="rs">
					<br />
					<br />  
					<p align="center">
						未找到任何记录！
					</p>
				</logic:empty>
				  <logic:present name="rs">				
				   <logic:notEmpty name="rs">		        
		     		 <fieldset>						
						<table width="100%" id="rsTable" class="tbstyle" >						   					    
					        <tr>
					        <td colspan="6" align="center">
					        <bean:message key="lable.xsgzyxpzxy" />：<bean:write name="xy"/>
					        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					        年级：<bean:write name="nj"/>
					        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					        班级：<bean:write name="bj"/>
					        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					        学号：<bean:write name="xh"/>
					        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					        姓名：<bean:write name="xm"/>		        
					        </tr>
								<tr>
								<td colspan="4" align="center">第一学期<br></td>								
								</tr>
								<tr><td>科目</td><td>参与项目</td><td>学分</td><td >备注</td></tr>
								<logic:iterate name="rs" id="s">
								  <bean:size id="tSize" name="s" property="xmList"/>								 																						
								  <logic:iterate id="v" name="s" property="kmList">	
								 <tr style="cursor:hand">
								  <td rowspan="<%=tSize%>"><bean:write name="v" property="kmm"/></td>
								<logic:notEqual name="tSize" value="0">								
									<logic:iterate id="b" name="s" property="xmList" >										
									<td>&nbsp;<bean:write name="b" property="xmmc"/></td>
									<td>&nbsp;<bean:write name="b" property="xf"  /></td>
									<td style="width:10%">&nbsp;<bean:write name="b" property="bz"  /></td>																		
									<% out.print("</tr>"); %>								
									</logic:iterate>
								</logic:notEqual>
								<logic:equal name="tSize" value="0">									
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>										
								</logic:equal>
								</tr>																																																									   																										
								</logic:iterate>
								</logic:iterate>				
						        <tr>
								        <td colspan="4" align="center">
								        登记人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										审核：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										盖章：
										</td>								
								</tr>	
								<tr>
								<td colspan="4" align="center">第二学期</td>								
								</tr>
								<tr><td>科目</td><td>参与项目</td><td>学分</td><td >备注</td></tr>								
								<logic:iterate name="rs" id="s">
								  <bean:size id="tSizeb" name="s" property="xmListb"/>							
								  <logic:iterate id="v" name="s" property="kmListb">
								    <tr style="cursor:hand">
									<td rowspan="<%=tSizeb %>"><bean:write name="v" property="kmm"/></td>								
								<logic:notEqual name="tSizeb" value="0">
								    <logic:iterate id="b" name="s" property="xmListb">
									<td>&nbsp;<bean:write name="b" property="xmmc"/></td>
									<td>&nbsp;<bean:write name="b" property="xf"  /></td>
									<td>&nbsp;<bean:write name="b" property="bz"  /></td>																																							
									<% out.print("</tr>"); %>	
									</logic:iterate>
								</logic:notEqual>	
								<logic:equal name="tSizeb" value="0">
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</logic:equal>
								</tr>																																																															
								</logic:iterate>
								</logic:iterate>																																
						        <tr>
								        <td colspan="4" align="center">
								        登记人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										审核：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										盖章：
										</td>								
								</tr>					    
						</table>
						<div class="buttontool" align="center">						
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="expTab('rsTable','大学生素质拓展学分登记表','')">
							打 印 报 表
						</button>
					</div>	
					</fieldset>
				</logic:notEmpty>
			  </logic:present>																				
			</html:form>
		 </center>
		<script type="text/javascript" src="js/bottomButton.js"></script>				
	</body>
</html>