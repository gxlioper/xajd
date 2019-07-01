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
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/sxjyFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
	function rsjs(){//人数计算
	var zsn1st = parseInt(document.getElementById("zsn1st").value);
	var zsm1st = parseInt(document.getElementById("zsm1st").value);
	var zsn2st = parseInt(document.getElementById("zsn2st").value);
	var zsm2st = parseInt(document.getElementById("zsm2st").value);
	var zsn3st = parseInt(document.getElementById("zsn3st").value);
	var zsm3st = parseInt(document.getElementById("zsm3st").value);
	document.getElementById("1strs").innerText = zsn1st+zsm1st;
	document.getElementById("2strs").innerText = zsn2st+zsm2st;
	document.getElementById("3strs").innerText = zsn3st+zsm3st;
	var nszs = zsn1st+zsn2st+zsn3st;
	var mszs = zsm1st+zsm2st+zsm3st;
	var zrs = nszs + mszs;
	document.getElementById("rszj").innerText = zrs+"人，其中男生"+nszs+"人，女生"+mszs+"人";
   }
</script>
    <body onload = "rsjs()">
    <html:form action="/stgl" method="post">
    <input type="hidden" id="zsn1st" name="zsn1st" value="<bean:write  name="rs" property="zsn1st" />" />
    <input type="hidden" id="zsm1st" name="zsm1st" value="<bean:write  name="rs" property="zsm1st" />" />
    <input type="hidden" id="zsn2st" name="zsn2st" value="<bean:write  name="rs" property="zsn2st" />" />
    <input type="hidden" id="zsm2st" name="zsm2st" value="<bean:write  name="rs" property="zsm2st" />" /> 
    <input type="hidden" id="zsn3st" name="zsn3st" value="<bean:write  name="rs" property="zsn3st" />" />
    <input type="hidden" id="zsm3st" name="zsm3st" value="<bean:write  name="rs" property="zsm3st" />" />
<p class="MsoNormal" align="center"><strong><span style="font-family:宋体;font-size:22.0pt;">社团注册登记申请表 </span></strong><strong><span style="font-size:22.0pt;"></span></strong></p><br/>
<table border="1" cellpadding="0" cellspacing="0" class="MsoNormalTable">
  <tr>
    <td height="33" colspan="2"><p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">社团名称 </span><span style="font-size:12.0pt;"></span></p>&nbsp;</td>
    <td colspan="6"><p class="MsoNormal" align="center"> <bean:write name="rs" property="stmc"/></p>&nbsp;</td>
    <td colspan="3"><p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">成立时间 </span><span style="font-size:12.0pt;"></span></p>&nbsp;</td>
    <td colspan="2"><p class="MsoNormal" align="center"> <bean:write name="rs" property="clsj"/></p>&nbsp;</td>
  </tr>
  <tr>
    <td width="79" rowspan="3"><p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">社 </span><span style="font-size:12.0pt;"></span></p>
        <p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">团 </span><span style="font-size:12.0pt;"></span></p>
        <p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">负 </span><span style="font-size:12.0pt;"></span></p>
        <p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">责 </span><span style="font-size:12.0pt;"></span></p>
        <p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">人 </span><span style="font-size:12.0pt;"></span></p>
        <p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">情 </span><span style="font-size:12.0pt;"></span></p>
        <p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">况 </span><span style="font-size:12.0pt;"></span></p>&nbsp;</td>
    <td width="104" height="28"><p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">姓名 </span><span style="font-size:12.0pt;"></span></p>&nbsp;</td>
    <td colspan="3"><p class="MsoNormal" align="center"><bean:write name="rs" property="xm"/></p>&nbsp;</td>
    <td colspan="5"><p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">政治面目 </span><span style="font-size:12.0pt;"></span></p>&nbsp;</td>
    <td colspan="3"><p class="MsoNormal" align="center"><bean:write name="rs" property="zzmmmc"/></p>&nbsp;</td>
  </tr>
  <tr>
    <td width="104" height="27"><p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">系别 </span><span style="font-size:12.0pt;"></span></p>&nbsp;</td>
    <td colspan="3"><p class="MsoNormal" align="center"> <bean:write name="rs" property="xymc"/></p>&nbsp;</td>
    <td colspan="5"><p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">联系方式 </span><span style="font-size:12.0pt;"></span></p>&nbsp;</td>
    <td colspan="3"><p class="MsoNormal" align="center"><bean:write name="rs" property="lxfs"/>&nbsp;</p>&nbsp;</td>
  </tr>
  <tr>
    <td width="104" height="115"><p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">个 </span><span style="font-size:12.0pt;"></span></p>
        <p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">人 </span><span style="font-size:12.0pt;"></span></p>
        <p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">简 </span><span style="font-size:12.0pt;"></span></p>
    <p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">历 </span><span style="font-size:12.0pt;"></span></p>&nbsp;</td>
    <td colspan="11"><p class="MsoNormal" align="center"><span style="font-size:12.0pt;">&nbsp; </span></p>
        <p class="MsoNormal" align="center"><bean:write name="rs" property="grjl"/></p>&nbsp;</td>
  </tr>
  <tr>
    <td width="79" rowspan="5"><p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">社 </span><span style="font-size:12.0pt;"></span></p>
        <p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">团 </span><span style="font-size:12.0pt;"></span></p>
        <p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">概 </span><span style="font-size:12.0pt;"></span></p>
        <p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">况 </span><span style="font-size:12.0pt;"></span></p>&nbsp;</td>
    <td width="104" height="30"><p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">类别 </span><span style="font-size:12.0pt;"></span></p>&nbsp;</td>
    <td colspan="11" >  <p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;"><bean:write name="rs" property="lbmc"/></span></p><p class="MsoNormal">&nbsp;</p>
    &nbsp;</td>
  </tr>
  <tr>
    <td width="104" height="109"><p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">主 </span><span style="font-size:12.0pt;"></span></p>
        <p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">要 </span><span style="font-size:12.0pt;"></span></p>
        <p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">活 </span><span style="font-size:12.0pt;"></span></p>
        <p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">动 </span><span style="font-size:12.0pt;"></span></p>
        <p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">内 </span><span style="font-size:12.0pt;"></span></p>
    <p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">容 </span><span style="font-size:12.0pt;"></span></p>&nbsp;</td>
    <td colspan="11"><p class="MsoNormal" align="center"><bean:write name="rs" property="hdnr"/></p>&nbsp;</td>
  </tr>
  <tr>
    <td width="104" height="35"><p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">指导老师 </span><span style="font-size:12.0pt;"></span></p>&nbsp;</td>
    <td width="90"><p class="MsoNormal" align="center"> <bean:write name="rs" property="lsxm"/></p>&nbsp;</td>
    <td colspan="3"><p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">性别 </span><span style="font-size:12.0pt;"></span></p>&nbsp;</td>
    <td colspan="3"><p class="MsoNormal" align="center"> <bean:write name="rs" property="xb"/>&nbsp;</p>&nbsp;</td>
    <td colspan="3"><p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">部门 </span><span style="font-size:12.0pt;">/ </span><span style="font-family:宋体;font-size:12.0pt;">职务 </span><span style="font-size:12.0pt;"></span></p>&nbsp;</td>
    <td width="135"><p class="MsoNormal" align="center"> <bean:write name="rs" property="bmmc"/><bean:write name="rs" property="zwmc"/></p>&nbsp;</td>
  </tr>
  <tr>
    <td width="104" rowspan="2"><p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">招收 </span><span style="font-size:12.0pt;"></span></p>
        <p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">人数 </span><span style="font-size:12.0pt;"></span></p>&nbsp;</td>
    <td width="90" height="32"><p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">一年级 </span><span style="font-size:12.0pt;"></span></p>&nbsp;</td>
    <td colspan="3"><p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">二年级 </span><span style="font-size:12.0pt;"></span></p>&nbsp;</td>
    <td colspan="3"><p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">三年级 </span><span style="font-size:12.0pt;"></span></p>&nbsp;</td>
    <td colspan="4" rowspan="2"><p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">总人数 </span><span style="font-size:12.0pt;"></span></p>      <p class="MsoNormal" id = "rszj"></p>&nbsp;</td>
  </tr>
  <tr>
    <td width="90" height="33"><p class="MsoNormal" align="center" id = "1stRs"> </p>&nbsp;</td>
    <td colspan="3"><p class="MsoNormal" align="center" id = "2stRs"></p>&nbsp;</td>
    <td colspan="3"><p class="MsoNormal" align="center" id = "3stRs"></p>&nbsp;</td>
  </tr>
  <tr>
    <td width="79" height="154"><p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">学生会社团部意见 </span><span style="font-size:12.0pt;"></span></p>&nbsp;</td>
    <td colspan="3"><bean:write name="rs" property="xshyj"/>
        <p class="MsoNormal" align="right"><span style="font-family:宋体;">部长： </span><span style="font-family:宋体;">（签名） </span></p>&nbsp;</td>
    <td colspan="3"><p class="MsoNormal" align="center"><span style="font-size:12.0pt;">&nbsp;</span></p>
      <p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">院团委 </span></p>      <p class="MsoNormal" align="center"><span style="font-family:宋体;font-size:12.0pt;">意见 </span></p>
        <p class="MsoNormal" align="right">&nbsp; </p>&nbsp;</td>
    <td colspan="6"><bean:write name="rs" property="tywyj"/>
        <p class="MsoNormal" align="right"><span style="font-family:宋体;">（</span><span style="font-family:宋体;">盖章） </span></p>        &nbsp;</td>
  </tr>
</table>
<p class="MsoNormal" align="center">&nbsp; </p>
</html:form>
</body>
</html>
