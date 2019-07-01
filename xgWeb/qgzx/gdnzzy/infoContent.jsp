<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
	</head>
<body> 
<logic:empty name="infotitle">
<br/>
<br/>
<center>对不起,还没有相关信息发布!</center>
</logic:empty>
<logic:notEmpty name="infotitle">
<div class="tab_cur" id="title_m">
				<p class="location">
				<em>您的当前位置：</em><a>${title}</a>
				</p>
			</div>
<br/><br/>
<p align="center" > <bean:write name="infotitle" /> </p> 
<hr style="width:60%"> 
<center>
    <br /> 
  <br /> 
  <table width="80%" align="center" border="0"> 
    <tr> 
      <td align="left"><bean:write name="infocontent" filter="false"/></td> 
    </tr> 
  </table> 
</center> 
</logic:notEmpty>
</body>
</html>
