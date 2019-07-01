<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
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
	<style type="text/css">
		.tbstyle {
					border-collapse: collapse;
				}
		.tbstyle td {
	border: 1px #97B7DB solid;
	padding: 3px;
}		
	</style>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/shgc/td.css" type="text/css" media="all" />
  <body>
  <center>
	<table width="100%" class="tbstyle" border="1">
		<tr>
			<td colspan="8" align="center">
				<font size="5"><strong>学生行政处分材料不归本人档案审批表</strong></font>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right"><logic:present name="rs"><bean:write name="rs" property="xymc"/></logic:present>&nbsp;系</td>
			<td colspan="3" align="right"><logic:present name="rs"><bean:write name="rs" property="zymc"/></logic:present>&nbsp;专业</td>
			<td colspan="1">班学号&nbsp;<logic:present name="rs"><bean:write name="rs" property="xh"/></logic:present></td>
			<td width="23%">政治面貌&nbsp;<logic:present name="rs"><bean:write name="rs" property="zzmm"/></logic:present></td>
		</tr>
  		<tr>
  			<td colspan="2" width="17%">姓名&nbsp;<logic:present name="rs"><bean:write name="rs" property="xm"/></logic:present></td>
  			<td width="13%">性别&nbsp;<logic:present name="rs"><bean:write name="rs" property="xb"/></logic:present></td>
  			<td colspan="3" width="23%">出生年月&nbsp;<logic:present name="rs"><bean:write name="rs" property="csrq"/></logic:present></td>
  			<td colspan="1">籍贯&nbsp;<logic:present name="rs"><bean:write name="rs" property="jg"/></logic:present></td>
  			<td>民族&nbsp;<logic:present name="rs"><bean:write name="rs" property="mz"/></logic:present></td>
    	</tr>
    	<tr>
    		<td colspan="8">
    			原处分等级及原因：
    		</td>
    	</tr>
    	<tr>
    		<td colspan="8">
    			<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;<%--<logic:present name="rs"><bean:write name="rs" property="cflbmc"/></logic:present>--%></p>
    			<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;<%--<logic:present name="rs"><bean:write name="rs" property="cfyymc"/></logic:present>--%></p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    		</td>
    	</tr>
    	<tr>
    		<td colspan="8">
    			处分后现实表现：
    		</td>
    	</tr>
    	<tr>
    		<td colspan="8">
    			<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;<%--<logic:present name="rs"><bean:write name="rs" property="cfhbx"/></logic:present>--%></p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    		</td>
    	</tr>
    	<tr>
    		<td colspan="8">
    			其它奖惩记录：
    		</td>
    	</tr>
    	<tr>
    		<td colspan="8">
    			<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;<%--<logic:present name="rs"><bean:write name="rs" property="jcjl"/></logic:present>--%></p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    		</td>
    	</tr>
    	<tr>	
    		<td colspan="8">
    			备注：
    		</td>
    	</tr>
    	<tr>
    		<td colspan="8">
    			<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;<%--<logic:present name="rs"><bean:write name="rs" property="bz"/></logic:present>--%></p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    		</td>
    	</tr>
	</table>
	<p align="center">&nbsp;</p>
	<p align="center">&nbsp;</p><p align="center">&nbsp;</p><p align="center">&nbsp;</p>
	<table width="100%" class="tbstyle" border="1">
    	<tr>
    		<td colspan="9">
    			辅导员意见及评语：
    		</td>
    	</tr>
    	<tr>
    		<td colspan="9">
    			<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;<%--<logic:present name="rs"><bean:write name="rs" property="bzryj"/></logic:present>--%></p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			</td>
    	</tr>
    	<tr>
    		<td colspan="6" rowspan="2">&nbsp;</td>
    		<td colspan="3">
    			<p align="right">辅导员签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    		<td>
    	</tr>
    	<tr>
    		<td colspan="3">		
    			<p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;</p>
    		</td>
    	</tr>
    	<tr>
    		<td colspan="9">
    			<bean:message key="lable.xsgzyxpzxy" />审查意见：
    		</td>
    	</tr>
    	<tr>
    		<td colspan="9">
    			<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;<%--<logic:present name="rs"><bean:write name="rs" property="xyyj"/></logic:present>--%></p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    		</td>
    	</tr>
    	<tr>
    		<td colspan="5" rowspan="2">&nbsp;</td>
    		<td colspan="4">
    			<p align="right">负责人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    		</td>
    	</tr>
    	<tr>
    		<td colspan="4">
    			<p align="right">院、系盖章&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;</p>
    		</td>
    	</tr>
    	<tr>
    		<td colspan="3">
    			会办部门审查意见：
    		</td>
    		<td colspan="3">
    			学生处审查意见：
    		</td>
    		<td colspan="3">
    			校审查意见：
    		</td>
    	</tr>
    	<tr>
    		<td colspan="3">
    			<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;<%--<logic:present name="rs"><bean:write name="rs" property="kbbmyj"/></logic:present>--%></p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    		</td>    	
    		<td colspan="3">
    			<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;<%--<logic:present name="rs"><bean:write name="rs" property="xscyj"/></logic:present>--%></p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    		</td>
    		<td colspan="3">
    			<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;<%--<logic:present name="rs"><bean:write name="rs" property="xxyj"/></logic:present>--%></p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    		</td>
    	</tr>
    	<tr>
    		<td colspan="3">
    			<p align="center">签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    		</td>
    		<td colspan="3">
    			<p align="center">签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    		</td>
    		<td colspan="3">
    			<p align="center">签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    		</td>
    	</tr>
    	<tr>
    		<td colspan="3">
    			<p align="left">盖章&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日</p>
    		</td>
    		<td colspan="3">
    			<p align="left">盖章&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日</p>
    		</td>
    		<td colspan="3">
    			<p align="left">盖章&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日</p>
    		</td>
    	</tr>
    	<tr>
    		<td colspan="9">
    			<p align="left">说明：1.根据校有关规定,学生行政处分,材料不归本人档案的审批仅在毕业学期进行.</p>			  	
    		</td>
    	</tr>
    	<tr>
    		<td colspan="9">
    			<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.各<bean:message key="lable.xsgzyxpzxy" />对拟执行处分材料不归本人档案的名单经预审后,到学生处领取本审批表.</p>
    		</td>
    	</tr>
    	<tr>
    		<td colspan="9">
    			<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.学生本人自我鉴定,小结材料另附（A4纸或报告纸）.</p>	
    		</td>
    	</tr>
	</table>
	</center>
  </body>
</html:html>
