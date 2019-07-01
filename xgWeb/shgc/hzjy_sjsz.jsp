<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html >
  <head>
    <title><bean:message key="lable.title" /></title>
		<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta name="Copyright" content="正方软件 zfsoft" />

  </head>
  	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
<script language="javascript" src="js/commanFunction.js"></script>
<script language="javascript" src="js/xszzFunction.js"></script>
<script language="javascript" src="js/calendar.js"></script>
<script language="javascript" src="js/calendar-zh.js"></script>
<script language="javascript" src="js/calendar-setup.js"></script>
<script language="javascript" src="js/jsFunction.js"></script>
  <body>
    <div class="title">
		<div class="title_img" id="title_m">
			<bean:message bundle="shgc" key="hzjy_sjsz"/>
		</div>
	</div>
	<logic:equal name="view" value="no">
		<script language="javascript">
			alert('该页面您没权限访问！');
		</script>
	</logic:equal>
	<logic:notEqual name="view" value="no">
	<logic:equal name="flag" value="no">
		 <script language="javascript">
			alert('学校没设置申请时间前您不能对时间进行设置！');
		</script>
	</logic:equal>
	<logic:notEqual name="flag" value="no">
	<html:form action="/hzjy_sjsz">
		<input type="hidden" value="<bean:write name="userType"/>" id="userType"/>
		<div align=center style="font-size:15px">合作教育时间设置</div>
		<table border="0" class="tbstyle" align="center" style="width:60%">
		
		<tr align=center>
			<td style="width:50%">合作教育申请开始时间：</td>
			<td style="width:50%;text-align:left" ><input type="text" name="sqkssj" id="sqkssj" style="width:90%" onclick="return showCalendar('sqkssj','y-mm-dd');" onblur="dateFormat(this)" readonly="readonly" style="cursor:hand " value="<bean:write name="rs" property="sqkssj"/>"></td>
			<logic:equal value="xy" name="userType">
				<td style="width:50%"><font color="red">学校设置时间为：<bean:write name="info" property="sqkssj"/></font></td>
				<input type="hidden" value="<bean:write name="info" property="sqkssj"/>" id="sqkssj1"/>
			</logic:equal>
		</tr>
		<tr align=center>	
			<td style="width:50%">合作教育申请结束时间：</td>
			<td style="width:50%;text-align:left"><input type="text" name="sqjssj" id="sqjssj" style="width:90%" onclick="return showCalendar('sqjssj','y-mm-dd');" onblur="dateFormat(this)" readonly="readonly" style="cursor:hand " value="<bean:write name="rs" property="sqjssj"/>"></td>
			<logic:equal value="xy" name="userType">
				<td style="width:50%"><font color="red">学校设置时间为：<bean:write name="info" property="sqjssj"/></font></td>
				<input type="hidden" value="<bean:write name="info" property="sqjssj"/>" id="sqjssj1"/>
			</logic:equal>
		</tr>
		<br/>
		<tr align=center>
			<td style="width:50%">合作教育学生安置登记开始时间：</td>
			<td style="width:50%;text-align:left" ><input type="text" name="xsazsqkssj" id="xsazsqkssj" style="width:90%" onclick="return showCalendar('xsazsqkssj','y-mm-dd');" onblur="dateFormat(this)" readonly="readonly" style="cursor:hand " value="<bean:write name="rs" property="xsazsqkssj"/>"></td>
			<logic:equal value="xy" name="userType">
				<td style="width:50%"><font color="red">学校设置时间为：<bean:write name="info" property="xsazsqkssj"/></font></td>
				<input type="hidden" value="<bean:write name="info" property="xsazsqkssj"/>" id="xsazsqkssj1"/>
			</logic:equal>
		</tr>
		<tr align=center>	
			<td style="width:50%">合作教育学生安置登记结束时间：</td>
			<td style="width:50%;text-align:left"><input type="text" name="xsazsqjssj" id="xsazsqjssj" style="width:90%" onclick="return showCalendar('xsazsqjssj','y-mm-dd');" onblur="dateFormat(this)" readonly="readonly" style="cursor:hand " value="<bean:write name="rs" property="xsazsqjssj"/>"></td>
			<logic:equal value="xy" name="userType">
				<td style="width:50%"><font color="red">学校设置时间为：<bean:write name="info" property="xsazsqjssj"/></font></td>
				<input type="hidden" value="<bean:write name="info" property="xsazsqjssj"/>" id="xsazsqjssj1"/>
			</logic:equal>
		</tr>
		<br/>
		<tr align=center>
			<td style="width:50%">合作教育变更雇主申请开始时间：</td>
			<td style="width:50%;text-align:left"><input type="text" name="bgkssj" id="bgkssj" style="width:90%" onclick="return showCalendar('bgkssj','y-mm-dd');" onblur="dateFormat(this)" readonly="readonly" style="cursor:hand " value="<bean:write name="rs" property="bgkssj"/>"></td>
			<logic:equal value="xy" name="userType">
				<td style="width:50%"><font color="red">学校设置时间为：<bean:write name="info" property="bgkssj"/></font></td>
				<input type="hidden" value="<bean:write name="info" property="bgkssj"/>" id="bgkssj1"/>
			</logic:equal>
		</tr>
		<tr align=center>	
			<td style="width:50%">合作教育变更雇主申请结束时间：</td>
			<td style="width:50%;text-align:left"><input type="text" name="bgjssj" id="bgjssj" style="width:90%" onclick="return showCalendar('bgjssj','y-mm-dd');" onblur="dateFormat(this)" readonly="readonly" style="cursor:hand " value="<bean:write name="rs" property="bgjssj"/>"></td>
			<logic:equal value="xy" name="userType">
				<td style="width:50%"><font color="red">学校设置时间为：<bean:write name="info" property="bgjssj"/></font></td>
				<input type="hidden" value="<bean:write name="info" property="bgjssj"/>" id="bgjssj1"/>
			</logic:equal>
		</tr>
		
	</table>
	<div class="buttontool" align=center>
		<button style="width:10%" onclick="if(chkVal()){document.forms[0].action='hzjy_sjsz.do?doType=save';document.forms[0].submit();}" class="button2">保存</button>
	</div>
	</html:form>
	<logic:equal name="result" value="true">
		 <script language="javascript">
			alert('保存成功！');
		</script>
	</logic:equal>
	<logic:equal name="result" value="false">
		 <script language="javascript">
			alert('保存失败！');
		</script>
	</logic:equal>
	</logic:notEqual>
	</logic:notEqual>
  </body>
</html:html>
