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
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>	
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<body>
		<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
        %>
		<center>			
			<br><html:form action="/sztzcj_hz_query" method="post">					
				<span align="center" style="font:17px;">
			     大学生素质拓展学分汇总表
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
					<div id="rsTable">					
						<table width="100%"  class="tbstyle" >						   					    
					        <tr>
					        <td colspan="6" align="center">
					        <bean:message key="lable.xsgzyxpzxy" />：<bean:write name="xy"/>
					        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					        年级：<bean:write name="nj"/>
					        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					        班级：<bean:write name="bj"/>
					        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					        学号：<bean:write name="xh"/>
					        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					        姓名：<bean:write name="xm"/>						        
					        </tr>
					        <logic:iterate name="rs" id="s">																										
								<tr>
								<td colspan="6"><bean:write name="s" property="nj"/></td>								
								</tr>
								<tr><td colspan="3">第一学期</td><td colspan="3">第二学期</td></tr>
								<tr><td>参与项目</td><td>学分</td><td>备注</td><td>参与项目</td><td>学分</td><td>备注</td></tr>
								<logic:iterate id="v" name="s" property="njList">
								<tr style="cursor:hand">
										<td align="left">
											<bean:write name="v" property="xmmc"/>
										</td>
										<td align="left">
											<bean:write name="v" property="xf"/>
										</td>
										<td align="left">
											<bean:write name="v" property="bz"/>
										</td>
										<td align="left">
											<bean:write name="v" property="xmmc1"/>
										</td>
										<td align="left">
											<bean:write name="v" property="xf1"/>
										</td>
										<td align="left">
											<bean:write name="v" property="bz1"/>
										</td>
								 </tr>								 								 												 
								</logic:iterate>
								<tr style="cursor:hand">
								<logic:iterate id="vn" name="s" property="oenxqcjzhList">
								    <td align="left" colspan="3">
									合计：
									<bean:write name="vn" property="xfzh"/>
									分
									</td>
								 </logic:iterate>
								<logic:iterate id="vm" name="s" property="towxqcjzhList">
									<td align="left" colspan="3">
									合计：
									<bean:write name="vm" property="xfzh"/>
									分	
									</td>
								 </logic:iterate> 										
								 </tr>								 								
							    </logic:iterate>
							    <logic:notEmpty name="allcj">							   
							    <tr style="cursor:hand">							       
							    <td colspan="6" align="right">
							        总计：
							        <bean:write name="allcj"/>
							        分
							    </td>		　　　　　　　　　　　　　　　	 				    
							    </tr>
							    </logic:notEmpty>								    							    
						</table>
						<br>
						<table width="100%"  class="tbstyle" >	
							<logic:iterate name="zxfrs" id="skm">
							<td ><bean:write name="skm" property="kmm"/> </td>
							</logic:iterate>
							<td>获得学分</td>	
							<td>是否合格</td>
							<tr>
							<logic:iterate name="zxfrs" id="szxf">
							<td><bean:write name="szxf" property="zxf"/> </td>
							</logic:iterate>
							<td></td>	
							<td></td>
							</tr>	
						</table>
						<br>
						<div align="left">
						            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									认证人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									打印日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									盖章：
						</div>
						</div>
						<div class="buttontool" align="center">						
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="expTab('rsTable','大学生素质拓展学分汇总表','')">
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
