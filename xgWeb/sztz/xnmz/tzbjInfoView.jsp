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
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：素质拓展 - 信息发布 - 班级信息查询 - 详细信息
				</div>
			</div>
				<fieldset>
				    <legend>
						详细信息
					</legend>					
					<table width="100%" class="tbstyle">
						<tr valign="middle">
							<td width="15%" align="right" nowrap >
								学年：
							</td>
							<td width="35%" align="left">
								<bean:write name="xn" scope="request" />
							</td>
							<td width="15%" align="right" nowrap>
								学期：
							</td>
							<td width="35%" align="left">
								<bean:write name="xqmc" scope="request" />
							</td>
						</tr>
						<tr valign="middle">
							<td align="right" nowrap>
								年度：
							</td>
							<td align="left">
							    <bean:write name="nd" scope="request" />
							</td>
							<td align="right" nowrap>
								素质拓展班级：
							</td>
							<td align="left">
								<bean:write name="mc" scope="request" />
							</td>
						</tr>
						
						<tr valign="middle">							
							<td align="right" nowrap>
								开课时间：
							</td>
							<td align="left">
								<bean:write name="kssj" scope="request" />
							</td>
							<td align="right" nowrap>
								结业时间：
							</td>
							<td align="left">
                                 <bean:write name="jssj" scope="request" />
 							</td>
						</tr>						
						<tr align="left" valign="top">
						    <td align="right">
							   内容：
						    </td>
						    <td colspan="3">
							   <bean:write name="nr" scope="request" />
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

