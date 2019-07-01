<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    
    <title>wjcf_shgc_xswjcfqkb.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="Copyright" content="正方软件 zfsoft" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style media='print'>
		.noPrin{display:none;}
	</style>
  </head>
  	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/shgc/td.css" type="text/css" media="all" />
		<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
  <body>
  		<script language="javascript" src="js/function.js"></script>
			<p align="center"><font size="5">学生违纪处分预审表</font></p>
			<div>
			<table width="96%" align="center" class="tbstyle">
  			<tr>
    			<td width="59" align="center">学&nbsp;院<br/></td>
    			<td width="140" align="center">&nbsp;<bean:write name="rs" property="xymc"/></td>
    			<td width="70" align="center">专&nbsp;业</td>
    			<td width="140" align="center">&nbsp;<bean:write name="rs" property="zymc"/></td>
    			<td width="64" align="center">班&nbsp;级</td>
    			<td width="102"align="center">&nbsp;<bean:write name="rs" property="bjmc"/></td>
    			
  			</tr>
  			<tr>
    			<td width="59" align="center">姓&nbsp;名<br/></td>
    			<td width="140" align="center">&nbsp;<bean:write name="rs" property="xm"/></td>
    			<td width="70" align="center">进校时间</td>
   				<td width="140" align="center">&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="sjxy"/>&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="sjxmon"/>&nbsp;&nbsp;&nbsp;月</td>
   				<td width="64" align="center">性&nbsp;别</td>
    			<td width="102" align="center">&nbsp;<bean:write name="rs" property="xb"/></td>
  			</tr>
  			<tr>
    			<td width="59"><p align="center">学&nbsp;号 </p></td>
    			<td width="140" align="center" >&nbsp;<bean:write name="rs" property="xh"/></td>
    			<td width="70" align="center">出生日期<br/></td>
    			<td align="center" colspan="3" >&nbsp;&nbsp;<bean:write name="rs" property="scsy"/>&nbsp;年&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="scsm"/>&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="scsd"/>&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
  			</tr>
  			<tr>
    			<td width="450" colspan="6">&nbsp;违纪事由:
    				<p align="">&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="jtwjsy"/> </p>
    				<p align="center">&nbsp; </p>
        			<p align="center">&nbsp; </p>
    			</td>
  			</tr>
  			<tr>
    			<td width="450" colspan="6">&nbsp;治安处罚情况或其它处罚情况:
    				<p align="">&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="zacfqk"/> </p>
    				<p align="">&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="zacfqk"/> </p>
    				<p align="center">&nbsp; </p>
    			</td>
  			</tr>
  			<!-- 非南宁职业学院 -->
  			<logic:notEqual name="xxdm" value="11355">
  			<tr>
    			<td colspan="6">&nbsp;<bean:message key="lable.xb" />意见:
    				<p align="">&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="bz"/> </p>
    				<p align="">&nbsp;</p>
    				<p align="right"><bean:message key="lable.xb" />主管领导签名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    				<p align="right"><bean:message key="lable.xb" />盖章:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    				<p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日 </p>
    			</td>
  			</tr>
  			<tr>
    			<td colspan="6">&nbsp;学生处审核:
    				<p align="center">&nbsp; </p>
    				<p align="right">审核人签名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    				<p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日 </p>
    			</td>
  			</tr>
  			</logic:notEqual>
  			<logic:equal name="xxdm" value="11355">
  			<tr>
    			<td colspan="6">&nbsp;系意见:
    				<p align="center">&nbsp; </p>
    				<p align="right">审核人签名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    				<p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日 </p>
    			</td>
  			</tr>
  			<tr>
    			<td colspan="6">&nbsp;辅导员班主任意见:
    				<p align="center">&nbsp; </p>
    				<p align="right">审核人签名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    				<p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日 </p>
    			</td>
  			</tr>
  			<tr>
    			<td colspan="6">&nbsp;学工处意见:
    				<p align="center">&nbsp; </p>
    				<p align="right">审核人签名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    				<p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日 </p>
    			</td>
  			</tr>
  			<tr>
    			<td colspan="6">&nbsp;<bean:message key="lable.xb" />意见:
    				<p align="center">&nbsp; </p>
    				<p align="right">审核人签名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    				<p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日 </p>
    			</td>
  			</tr>
  			<tr>
    			<td colspan="6">&nbsp;学生处审核:
    				<p align="center">&nbsp; </p>
    				<p align="right">审核人签名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    				<p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日 </p>
    			</td>
  			</tr>
  			<tr>
    			<td colspan="6">&nbsp;备注:
    				<p align="center">&nbsp; </p>
    				<p align="right">审核人签名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    				<p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日 </p>
    			</td>
  			</tr>
  			</logic:equal>
  			<tr>
  				<td colspan="6">&nbsp;<bean:message key="lable.xb" />告之经学校审核同意的拟处理意见情况:
    				<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>已告知该生学校拟处分意见，并可在接到拟处分意见之日起的3个工作日内，提出书面申辩。</b></p>
    				<p align="right">告之人签名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    				<p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日 </p>
    				<p align="right">学生签名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    				<p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日 </p>
    			</td>
  			</tr>
		</table>
		</div>
		<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：附违纪学生检查（A4纸）；旷课须附任课教师点名记录复印件.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
  </body>
  <div class="noPrin" align="center">
			<input type='button' class='button2' value='页面设置'
				onclick="WebBrowser.ExecWB(8,1);return false;">
			<input type='button' class='button2' value='打印预览'
				onclick="WebBrowser.ExecWB(7,1);return false;">
			<input type='button' class='button2' value='直接打印'
				onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>
</html:html>
