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
    <html:form action="" method="post" focus="login">
      <div align="center" style="font:'黑体';font-size:18px;">优秀学生奖学金审批表</div>
<br>
<div>_______系（院）______________学年第_____学期</div>
<table width="100%"  border="1">
  <tr>
    <th colspan="2" scope="col">班级</th>
    <th width="25%" colspan="2" scope="col">&nbsp;</th>
    <th width="11%" scope="col">姓名</th>
    <th colspan="2" scope="col">&nbsp;</th>
    <th width="11%" scope="col">职务</th>
    <th colspan="2" scope="col">&nbsp;</th>
  </tr>
  <tr>
    <th colspan="2" scope="row">成绩名次</th>
    <td colspan="2">&nbsp;</td>
    <td><div align="center">综合评分名次</div></td>
    <td colspan="2">&nbsp;</td>
    <td><div align="center">奖学金等级</div></td>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <th width="6%" height="121" rowspan="9" scope="row">
	<p>上</p>
    <p>学</p>
    <p>期</p>
    <p>各</p>
    <p>学</p>
    <p>科</p>
    <p>成</p>
    <p>绩</p></th>
    <th colspan="3" scope="row">课&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;程</th>
    <th colspan="2" scope="row">成 绩</th>
    <th colspan="3" scope="row">课&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;程</th>
    <th width="12%" scope="row">成 绩</th>
  </tr>
  <tr>
    <th height="29" colspan="3" scope="row">&nbsp;</th>
    <th colspan="2" scope="row">&nbsp;</th>
    <th colspan="3" scope="row">&nbsp;</th>
    <th scope="row">&nbsp;</th>
  </tr>
  <tr>
    <th height="28" colspan="3" scope="row">&nbsp;</th>
    <th colspan="2" scope="row">&nbsp;</th>
    <th colspan="3" scope="row">&nbsp;</th>
    <th scope="row">&nbsp;</th>
  </tr>
  <tr>
    <th height="35" colspan="3" scope="row">&nbsp;</th>
    <th colspan="2" scope="row">&nbsp;</th>
    <th colspan="3" scope="row">&nbsp;</th>
    <th scope="row">&nbsp;</th>
  </tr>
  <tr>
    <th height="33" colspan="3" scope="row">&nbsp;</th>
    <th colspan="2" scope="row">&nbsp;</th>
    <th colspan="3" scope="row">&nbsp;</th>
    <th scope="row">&nbsp;</th>
  </tr>
  <tr>
    <th height="44" colspan="3" scope="row">&nbsp;</th>
    <th colspan="2" scope="row">&nbsp;</th>
    <th colspan="3" scope="row">&nbsp;</th>
    <th scope="row">&nbsp;</th>
  </tr>
  <tr>
    <th height="32" colspan="3" scope="row">&nbsp;</th>
    <th colspan="2" scope="row">&nbsp;</th>
    <th colspan="3" scope="row">&nbsp;</th>
    <th scope="row">&nbsp;</th>
  </tr>
  <tr>
    <th height="22" colspan="3" scope="row">&nbsp;</th>
    <th colspan="2" scope="row">&nbsp;</th>
    <th colspan="3" scope="row">&nbsp;</th>
    <th scope="row">&nbsp;</th>
  </tr>
  <tr>
    <th height="28" colspan="3" scope="row">&nbsp;</th>
    <th colspan="2" scope="row">&nbsp;</th>
    <th colspan="3" scope="row">&nbsp;</th>
    <th scope="row">&nbsp;</th>
  </tr>
  <tr>
    <th scope="row"><p>奖</p>
    <p>罚</p>
    <p>情</p>
    <p>况</p></th>
    <th colspan="9" scope="row"> 年 月 日 </th>
  </tr>
  <tr>
    <th scope="row"><p>系</p>
    <p>（院）</p>
    <p>意</p>
    <p>见</p></th>
    <th colspan="9" scope="row"><p>签名</p>
    <p>年 月 日</p></th>
  </tr>
  <tr>
    <th scope="row"><p>学</p>
    <p>生</p>
    <p>处</p>
    <p>意</p>
    <p>见</p></th>
    <th colspan="9" scope="row">年 月 日 </th>
  </tr>
</table>
注：此表一式两份，系（院）、院学生处各一份
<table width="100%"  border="1">
  <tr>
    <th scope="col">&nbsp;</th>
    <th scope="col">&nbsp;</th>
    <th scope="col">&nbsp;</th>
    <th scope="col">&nbsp;</th>
  </tr>
  <tr>
    <th scope="row">&nbsp;</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th scope="row">&nbsp;</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th scope="row">&nbsp;</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th scope="row">&nbsp;</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
    </html:form>
  </body>
</html:html>
