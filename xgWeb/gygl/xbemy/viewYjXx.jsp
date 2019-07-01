<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>
		<script language="javascript" src="js/check.js"></script>
	</head>
<body> 
<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>学生意见箱</a>
			</p>
		</div>
		<!-- 标题 end-->

<p align="center">学生相关意见：&nbsp;&nbsp; <font color="red"><bean:write name="rsYj" property="title" /></font> </p>
<hr style="width:80%"> 
<center>
  发表时间：<bean:write name="rsYj" property="pubdate" />&nbsp;&nbsp;&nbsp;&nbsp; 发表人：<bean:write name="rsYj" property="xh" /> <br /> 
  <br /> 
  <table width="80%" align="center" border="0"> 
    <tr> 
      <td align="left"><bean:write name="rsYj" property="content" filter="false"/></td> 
    </tr> 
  </table> 
</center> 
<logic:notEmpty name="rsRe" >
<hr style="width:80%"> 
<p></p><p></p><p></p>
<p align="center">老师回复：&nbsp;&nbsp; Re：&nbsp;<bean:write name="rsYj" property="title" /> </p>
<hr style="width:80%"> 
<center>
  回复时间：<bean:write name="rsRe" property="repubdate" />&nbsp;&nbsp;&nbsp;&nbsp; 回复人：<bean:write name="rsRe" property="yhm" /> <br /> 
  <br /> 
  <table width="80%" align="center" border="0"> 
    <tr> 
      <td align="left"><bean:write name="rsRe" property="recontent" filter="false"/></td> 
    </tr> 
  </table>
</center>
</logic:notEmpty>
<hr style="width:80%"> 
</body>
</html>
