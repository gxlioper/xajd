<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title><bean:message key="lable.title" /></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>	

	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
  </head>
  <body>
    <html:form action="/pjpynblgwh">
      <div align="center" style="font-size:18px;font:'黑体' ">${xxmc}${xn }学年文明班级申报表</div>
		<br/>
	  <div>
	  <div align="center">
	  	&nbsp;&nbsp;分院<u>&nbsp;&nbsp;${rs.xymc }&nbsp;&nbsp;</u>
	  	&nbsp;&nbsp;专业班级<u>&nbsp;&nbsp;${rs.zymc }${rs.bjmc }&nbsp;&nbsp;</u>
	  	&nbsp;&nbsp;班级人数<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
	  </div>
<table width="100%" class="printstyle">
  <tr>
    <td colspan="2" align="center" width="30%">
    	项&nbsp;&nbsp;&nbsp;&nbsp;目
    </td>
    <td align="center" width="70%">
    	简&nbsp;&nbsp;要&nbsp;&nbsp;情&nbsp;&nbsp;况
    </td>
  </tr>
  <tr>
  	<td align="center" width="10%">
  		思想<br/>政治<br/>建设
  	</td>
  	<td align="center" width="20%">
  		申请入党学生、积极<br/>分子、党员比例，党<br/>章学习小组人数，青<br/>年志愿者人数
  	</td>
  	<td width="70%">
  		&nbsp;
  	</td>
  </tr>
  <tr>
  	<td align="center" width="10%" rowspan="3">
  		班风<br/>建设<br/>情况
  	</td>
  	<td align="center" width="20%">
  		班级凝聚力建设
  	</td>
  	<td width="70%">
  		&nbsp;
  	</td>
  </tr>
  <tr>
  	<td align="center" width="20%">
  		班会开展情况
  	</td>
  	<td width="70%">
  		&nbsp;
  	</td>
  </tr>
  <tr>
  	<td align="center" width="20%">
  		师生、同学关系
  	</td>
  	<td width="70%">
  		&nbsp;
  	</td>
  </tr>
  <tr>
  	<td align="center" width="10%">
  		学风<br/>建设<br/>情况
  	</td>
  	<td align="center" width="20%">
  		本学年评奖评优、英<br/>语等级考试、计算机<br/>等级考试、参与学生<br/>科研、重修、降级试<br/>读等情况
  	</td>
  	<td width="70%">
  		&nbsp;
  	</td>
  </tr>
  <tr>
  	<td align="center" width="10%">
  		遵纪<br/>守法
  	</td>
  	<td align="center" width="20%">
  		遵守学校的规章制<br/>度、行为规范等
  	</td>
  	<td width="70%">
  		&nbsp;
  	</td>
  </tr>
  <tr>
  	<td align="center" width="10%">
  		班级<br/>特色
  	</td>
  	<td align="center" width="20%">
  		班级工作的创新举措
  	</td>
  	<td width="70%">
  		&nbsp;
  	</td>
  </tr>
  <tr>
  <td colspan="3">
    班级小结（具体材料另附）
    	<br/><br/><br/>
	</td>
  </tr>
  <tr>
  <td colspan="3">
分院意见
    	<br/><br/>
    	<br/>
    	<p align="right">(盖章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
  		<p align="right">年  &nbsp;&nbsp;&nbsp;月  &nbsp;&nbsp;&nbsp;日 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
	</td>
  </tr>
  <tr>
  <td colspan="3">
<bean:message key="lable.xsgzyxpzxy" />意见
    	<br/><br/>
    	<br/>
    	<p align="right">(盖章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
  		<p align="right">年  &nbsp;&nbsp;&nbsp;月  &nbsp;&nbsp;&nbsp;日 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
	</td>
  </tr>
</table>
<div>

</div></div>
    </html:form>
  </body>
</html:html>
