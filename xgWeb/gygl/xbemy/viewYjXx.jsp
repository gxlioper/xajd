<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
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
<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>ѧ�������</a>
			</p>
		</div>
		<!-- ���� end-->

<p align="center">ѧ����������&nbsp;&nbsp; <font color="red"><bean:write name="rsYj" property="title" /></font> </p>
<hr style="width:80%"> 
<center>
  ����ʱ�䣺<bean:write name="rsYj" property="pubdate" />&nbsp;&nbsp;&nbsp;&nbsp; �����ˣ�<bean:write name="rsYj" property="xh" /> <br /> 
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
<p align="center">��ʦ�ظ���&nbsp;&nbsp; Re��&nbsp;<bean:write name="rsYj" property="title" /> </p>
<hr style="width:80%"> 
<center>
  �ظ�ʱ�䣺<bean:write name="rsRe" property="repubdate" />&nbsp;&nbsp;&nbsp;&nbsp; �ظ��ˣ�<bean:write name="rsRe" property="yhm" /> <br /> 
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
