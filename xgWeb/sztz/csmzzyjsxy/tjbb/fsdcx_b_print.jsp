<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<base target="_self">
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/pjpyFunction.js"></script>

		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/xszzFunction.js"></script>
	<body>
		<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
        %>
		<center>			
			<br><html:form action="/csmz_sztz.do?method=tzxf_cxtj" method="post">
				<table width="100%" border="0" id="rsTable" >
					<tr>
						<td>
							<div align="center">
									<h3>
								<strong>
										<logic:notEmpty name="rs">
											<bean:write name="title" scope="request" />
										</logic:notEmpty>
										<br/>										
									 </strong></h3>
							</div>
							<div align="right">
														    <logic:present name="title1">
							    <bean:write name="title1" scope="request" />
							    </logic:present>
							</div>
						</td>																																
					<tr>
						<td>
							<div align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;${printTitle}
							</div>
							<div align="center">
								<table width="100%" class="printstyle">
									<thead>
										<tr align="center">
											<logic:iterate id="tit" name="topTr" offset="0">
												<td id="<bean:write name="tit" property="en"/>">
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
										</tr>
									</thead>
									<logic:iterate name="rs" id="s">
										<tr>
											<td align="center" >

												<logic:iterate id="v" name="s" offset="0" length="1">
													<bean:write name="v" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="1">
												<td align="center">
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</table>
							</div>
							<logic:present name="maxXf">
								<div align="left">
                                 &nbsp;&nbsp;&nbsp;&nbsp;班级平均分&nbsp;<bean:write name="avgxf" scope="request" />&nbsp;&nbsp;&nbsp;&nbsp;班级最高分&nbsp;<bean:write name="maxXf" scope="request" />
								</div>
							</logic:present>
						</td>
					</tr>					
				</table>
				
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="打印" name="button_print" style="cursor:hand;"
							onclick="expTab('rsTable','')">															
						
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>				
	</body>
</html>
