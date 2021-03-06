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

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<base target="_self"/>
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
		<script language="javascript" src="js/sztzFunction.js"></script>
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：素质拓展 - 申请 - 学生申请 - 详细信息
				</div>
			</div>
			<fieldset>
					<legend>
						详细信息
					</legend>
					<table width="100%" class="tbstyle">
							<tr valign="middle">
									<td align="right" width="15%">
										学号：
									</td>
									<td align="left">
										<bean:write name='rs' property="xh"/>
									</td>
								<td align="right" width="25%">
									年度：
								</td>
								<td align="left">
									<bean:write name="nd" scope="request"/>
								</td>								
						</tr>
						<tr valign="middle">
								<td align="right">
									姓名：
								</td>
								<td align="left">
									<bean:write name='rs' property="xm" />
								</td>
								<td align="right">
									学年：
								</td>
								<td align="left">
                                <bean:write name="xn" scope="request"/>
								</td>
							</tr>
						<tr style="height:22px">
								<td align="right">
									性别：
								</td>
								<td align="left">
									<bean:write name='rs' property="xb" />
								</td>
								<td align="right" nowrap>
									学期：
								</td>
								<td align="left">
                                <bean:write name="xqmc" scope="request"/>
								</td>
								
						</tr>
						<tr style="height:22px">
								<td align="right">
									年级：
								</td>
								<td align="left">
									<bean:write name='rs' property="nj" />
								</td>
								<td align="right" nowrap>
									素质拓展班级：
								</td>
								<td align="left">
									<bean:write name="mc" scope="request"/>
								</td>								
						</tr>
						<tr style="height:22px">
								<td align="right">
									<bean:message key="lable.xsgzyxpzxy" />：
								</td>
								<td align="left">
									<bean:write name='rs' property="xymc" />
								</td>
								<td align="right" nowrap>
									申请时间：
								</td>
								<td align="left">
									<bean:write name='sqsj' scope="request" />
								</td>
						</tr>
						<tr style="height:22px">
								<td align="right">
									专业：
								</td>
								<td align="left">
									<bean:write name='rs' property="zymc" />
								</td>								
								<td align="right" nowrap>
									
								</td>
								<td align="left">
									
								</td>
						</tr>
						<tr style="height:22px">
								<td align="right">
									班级：
								</td>
								<td align="left">
									<bean:write name='rs' property="bjmc" />
								</td>
								<td align="right" nowrap>
									
								</td>
								<td align="left">
									
								</td>
						</tr>																														
						<tr align="left" valign="top">
							<td  align="right">
								 申请理由：
							</td>
							<td colspan="4">	 
								<bean:write name="sqly" scope="request"/>
							</td>
						</tr>							
					</table>
				</fieldset>
				<div class="buttontool" id="btn" align="center">
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
				</div>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>

		
		
		
		
