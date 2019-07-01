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

	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
</script>
	<body>
		<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<div align="center"><h3>漳州师范<bean:message key="lable.xsgzyxpzxy" /><bean:write name="rs" property="xn"/>学年<bean:write name="rs" property="xq" />学期荣誉称号申请表</h3></div>
<br>
<table width="90%"  border="0" class="tbstyle" align="center">
  <tr>
    <td width="100"><div align="right">学号</div></td>
    <td width="104"><bean:write name="rs" property="xh"/></td>
    <td width="90"><div align="right">姓名</div></td>
    <td width="91"><bean:write name="rs" property="xm"/></td>
    <td width="75"><div align="right">性别</div></td>
    <td width="42"><bean:write name="rs" property="xb"/></td>
    <td width="67"><div align="right">党团员</div></td>
    <td colspan="2"><bean:write name="rs" property="zzmmmc"/></td>
  </tr>
  <tr>
    <td><div align="right"><bean:message key="lable.xsgzyxpzxy" />专业班级</div></td>
    <td colspan="4"><bean:write name="rs" property="xymc"/><bean:write name="rs" property="zymc"/><bean:write name="rs" property="bjmc"/></td>
    <td colspan="2"><div align="right">外语水平</div></td>
    <td colspan="2"><bean:write name="rs" property="wysp"/></td>
  </tr>
  <tr>
    <td><div align="right">担任职务</div></td>
    <td colspan="4"><bean:write name="rs" property="drzw"/></td>
    <td colspan="2"><div align="right">申请荣誉称号</div></td>
    <td colspan="2"><bean:write name="rs" property="rychmc"/></td>
  </tr>
  <tr>
    <td width="85" height="106" rowspan="6" align="center" valign="middle">个人成绩</td>
    <td colspan="4"><div align="center">学习情况</div></td>
    <td colspan="4"><div align="center">综合素质考评情况</div></td>
  </tr>
  <tr>
   <td height="32" colspan="1"><div align="center">学习成绩</div></td>
    <td height="32" colspan="1"><div align="center">班级排序</div></td>
    <td height="32" colspan="2"><div align="center">专业排序</div></td>
    <td colspan="2"><div align="center">综合素质总分</div></td>
    <td width="168"><div align="center">班级排序</div></td>
    <td width="169"><div align="center">专业排序</div></td>
  </tr>

  <tr>
  	<td height="25" colspan="1"><bean:write name="rs" property="pjcj1" /></td>
    <td height="25" colspan="1"><bean:write name="rs" property="bjcjpx1" /></td>
    <td height="25" colspan="2"><bean:write name="rs" property="zycjpx1" /></td>
    <td colspan="2"><bean:write name="rs" property="zhkpzf1" /></td>
    <td><bean:write name="rs" property="zhkpbjpx1" /></td>
    <td><bean:write name="rs" property="zhkpzypx1" /></td>
  </tr>
  <tr>
  <td height="25" colspan="1"><bean:write name="rs" property="pjcj2" /></td>
    <td height="25" colspan="1"><bean:write name="rs" property="bjcjpx2" /></td>
    <td height="25" colspan="2"><bean:write name="rs" property="zycjpx2" /></td>
    <td colspan="2"><bean:write name="rs" property="zhkpzf2" /></td>
    <td><bean:write name="rs" property="zhkpbjpx2" /></td>
    <td><bean:write name="rs" property="zhkpzypx2" /></td>
  </tr>
  <tr>
  <td height="25" colspan="1"><bean:write name="rs" property="pjcj3" /></td>
    <td height="25" colspan="1"><bean:write name="rs" property="bjcjpx3" /></td>
    <td height="25" colspan="2"><bean:write name="rs" property="zycjpx3" /></td>
    <td colspan="2"><bean:write name="rs" property="zhkpzf3" /></td>
    <td><bean:write name="rs" property="zhkpbjpx3" /></td>
    <td><bean:write name="rs" property="zhkpzypx3" /></td>
  </tr>
  <tr>
  <td height="25" colspan="1"><bean:write name="rs" property="pjcj4" /></td>
    <td height="25" colspan="1"><bean:write name="rs" property="bjcjpx4" /></td>
    <td height="25" colspan="2"><bean:write name="rs" property="zycjpx4" /></td>
    <td colspan="2"><bean:write name="rs" property="zhkpzf4" /></td>
    <td><bean:write name="rs" property="zhkpbjpx4" /></td>
    <td><bean:write name="rs" property="zhkpzypx4" /></td>
  </tr>
  <tr>
    <td width="85" align="center" >参<br>
    加<br>
    科<br>
    研<br>
    发<br>
    表<br>
    论<br>
    文<br>
    情<br>
    况</td>
    <td colspan="8"><bean:write name="rs" property="hjqk" /></td>
  </tr>
  <tr>
    <td width="85" align="center" >系<br>
    意<br>
    见</td>
    <td colspan="8"><bean:write name="rs" property="" /></td>
  </tr>
  <tr>
    <td width="85" align="center" >学<br>
    校<br>
    意<br>
    见</td>
    <td colspan="8"><bean:write name="rs" property="" /></td>
  </tr>
</table>
</body>
</html>