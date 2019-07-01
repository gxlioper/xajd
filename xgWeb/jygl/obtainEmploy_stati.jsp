<%@ page language="java" autoFlush="true" contentType="text/html; charset=GBK"%>
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
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<body>
		<html:form action="/obtainEmploy_stati" method="post">
			<div align="left" style="margin-top: 20px;margin-left: 10px">
				月旬：
			<html:select property="yx" onchange="refreshForm('/xgxt/obtainEmploy_stati.do') ">
					<html:option value="one">
						上旬
					</html:option>
					<html:option value="tow">
						中旬
					</html:option>
					<html:option value="three">
						下旬
					</html:option>
				</html:select>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="打印"
					onclick="expTab('theTable','');" />
			</div>
			<br>
			<br>
			<div id="theTable" >
			<div align="center" style="font-size: 20px">
			<bean:write name="clinText"/>
			<br>
			  </div>             
				<table width="100%" bordercolor="red">
					<tr>
						<td align="right">
						    <div align="right" style="font-size: 15px;font-weight: bold;">统&nbsp;&nbsp;计&nbsp;&nbsp;表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
							<table width="250px" class="tbstyle" height="450px">
								
								<tr  style="cursor:hand">
										<td>

										</td>
										<td>
											<bean:write name="nd1" />年
										</td>
										<td>
											<bean:write name="nd2" />年
										</td>
										<td>
											<bean:write name="nd3" />年
										</td>
									</tr>
								<tr>
									<td>
										04月
									</td>
									<td>
										<bean:write name="nd14" />
									</td>
									<td>
										<bean:write name="nd24" />
									</td>
									<td>
										<bean:write name="nd34" />
									</td>
								</tr>
								<tr>
									<td>
										05月
									</td>
									<td>
										<bean:write name="nd15" />
									</td>
									<td>
										<bean:write name="nd25" />
									</td>
									<td>
										<bean:write name="nd35" />
									</td>
								</tr>
								<tr>
									<td>
										06月
									</td>
									<td>
										<bean:write name="nd16" />
									</td>
									<td>
										<bean:write name="nd26" />
									</td>
									<td>
										<bean:write name="nd36" />
									</td>
								</tr>
							</table>
						</td>
						<td align="left">

							<img src="DisplayChart?filename=<bean:write name="filename" />"
								width="450" height="500" />
						</td>
					</tr>
				</table>
			</div>
		</html:form>
	</body>
</html>

