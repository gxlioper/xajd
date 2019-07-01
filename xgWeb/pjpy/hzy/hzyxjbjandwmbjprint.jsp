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
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>	
<style media="print">
	.tbstyle{display:none}
	</style>
	<style media="print">
.noprint{
	display:none;
}
.print{
	display:block;
}
</style>
	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
  </head>
  <body>
    <html:form action="/dxjxjsp">
      <div align="center" style="font-size:18px;font:'黑体' ">${title }</div>
<br/>

<table width="98%" class="printstyle">
  <tr>
    <th height="30" width="20%" colspan="2" scope="col">班级</th>
    <td height="30" width="30%" colspan="2" scope="col" align="center">&nbsp;${bjmc }</td>
    <th height="30" width="20%" colspan="2" scope="col">班长</th>
    <td height="30" width="30%" colspan="2" scope="col" align="center">&nbsp;${bzxm }</td>
    
  </tr>
  <tr>
    <th height="30" colspan="2" scope="col">学生人数</th>
    <td height="30" colspan="2" scope="col" align="center">&nbsp;${xsrs }</td>
    <th height="30" colspan="2" scope="col">班主任</th>
    <td height="30" colspan="2" scope="col" align="center">&nbsp;${bzr }</td>
  </tr>
  <logic:equal value="12682" name="xxdm">
  <tr>
    <th height="30" colspan="2" scope="col">集体称号</th>
    <td height="30" colspan="6" scope="col" align="center">&nbsp;${jtch }</td>
  </tr>
  </logic:equal>
  <tr>
    <th width="10%" height="121" rowspan="" scope="row">
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
	<p>主</p>
    <p>要</p>
    <p>事</p>
    <p>迹</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p></th>
    <th colspan="7"><p align="left" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${zysj }</p></th>
  </tr>
  <tr>
    <th scope="row" width="10%" colspan="">
    <p>&nbsp;</p>
    <p>(系)</p>
    <p>院</p>
    <p>意</p>
    <p>见</p>
    <p>&nbsp;</p>
    </th>
    <th colspan="7" style="" >
   
    <p align="left" ><br/><br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${xyyj }</p>
    
    <div align="right">签名(盖章)：${xyqm}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
    <br>
    <p align="right"> &nbsp;${xyshyear } &nbsp;年&nbsp; ${xyshmon } &nbsp;月&nbsp; ${xyshdate } &nbsp;日 </p></div></th>
    
   </tr>
   <tr> 
    <th scope="row" width="10%" colspan="">
    <p>&nbsp;</p>
    <p>学</p>
    <p>院</p>
    <p>意</p>
    <p>见</p>
   <p>&nbsp;</p>
    </th>
    <th colspan="7" scope="row" style="">
    
    <p align="left"><br/><br/><br/>
    
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${xxyj }</p>
    <div align="right">
    (盖章) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
   <p align="right"> ${xxshyear } &nbsp;年&nbsp; ${xxshmon } &nbsp;月&nbsp; ${xxshdate } &nbsp;日</p> </div></th>
  </tr>
</table>
		<br>
		<br>
		<div align="center" class="noprint">
			<input type='button' class='button2' value='页面设置'
				onclick="WebBrowser.ExecWB(8,1);return false;">
			<input type='button' class='button2' value='打印预览'
				onclick="WebBrowser.ExecWB(7,1);return false;">
			<input type='button' class='button2' value='直接打印'
				onclick="WebBrowser.ExecWB(6,6);return false;">
		</div>
    </html:form>
  </body>
</html:html>
