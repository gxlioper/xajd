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
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'jtjjknsPrint.jsp' starting page</title>
    	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/sztzFunction.js"></script>
	
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	</head>
	<style media="print">
			.noprint{
				display:none
			}
			.print{
				display:block
			}
 	 </style>
  <body bgcolor="#FFFFFF" border=1 class="Normal" lang=ZH-CN> 
  <center>
  	<div class=Section1 style='layout-grid:15.6pt'> 
  	<p align=center style='text-align:center'>
  		<b>
  		<span style='font-size:18.0pt;font-family:宋体'>
  			宁波天一职业技术<bean:message key="lable.xsgzyxpzxy" />
  			<logic:empty name="rs">
  				______
  			</logic:empty>
  			<logic:notEmpty name="rs">
  				<U><bean:write name="rs" property="xn"/></U>
  			</logic:notEmpty>
  			学年度
  		</span>
  		</b>
  	</p> 
  <p align=center style='text-align:center'><b><span
style='font-size:26.0pt;font-family:宋体'>文明寝室登记表</span></b></p> 
  <html:form action="nbtyWmqs" method="post">
  <input type="hidden" name="pkValue" id="pkValue" value="${pkValue}"/>
  <table   class="tbstyle"  width=80% height=780> 
    <tr  height="5%"> 
      <td width=5% valign=middle class="Normal"><p align=center style='
  text-align:center'> 院/系</p></td>
      <td width=25% colspan=2 valign=middle class="Normal">
      	<logic:empty name="rs">
      		&nbsp; 
      	</logic:empty>
      	<logic:notEmpty name="rs">
      		<p align=center style='text-align:center'>
      			<bean:write name="rs" property="xymc"/>
      		</p>
      	</logic:notEmpty>
      </td> 
      <td width=59 colspan=1 valign=middle class="Normal"><p align=center style='
  text-align:center'>
      		班级</p>
      </td> 
      <td width=117 colspan=4 valign=middle class="Normal">
		<logic:empty name="rs">
      		&nbsp; 
      	</logic:empty>
      	<logic:notEmpty name="rs">
      		<p align=center style='text-align:center'>
      			<bean:write name="rs" property="bjmc"/>
      			</p>
      	</logic:notEmpty>
	  </td> 
      <td width=60 valign=middle class="Normal"><p align=center style='text-align:center'>
      		寝室</p>
      </td> 
      <td width=150 valign=middle class="Normal"> 
		<logic:empty name="rs">
      		&nbsp; 
      	</logic:empty>
      	<logic:notEmpty name="rs">
      		<p align=center style='text-align:center'>
      			<bean:write name="rs" property="ldmc"/><bean:write name="rs" property="qsh"/>
      		</p>
      	</logic:notEmpty>
	  </td> 
    </tr> 
    <tr  height="20%"> 
      <td width=5% valign="middle" class="Normal"><p align=center style='
  text-align:center'>
         主<br>要<br>事<br>迹<br></p></td> 
      <td width=540 colspan=9   class="Normal">
      <logic:notEmpty name="rs">
      	${rs.zysj}
      </logic:notEmpty>
       <br>
       <br>
       <br>
       <br>
      <p  style='text-align:right'>
      寝室长签名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
      日期:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
      </td> 
    </tr> 
    <tr height="20%"> 
      <td width=5% valign=middle class="Normal"> <p align=center style='
  text-align:center'>
      	班<br>主<br>任<br>意<br>见</p></td> 
      <td width=36% colspan=5 valign=bottom class="Normal">  <p style='
  text-align:right'>
      盖章:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
      日期:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p></td> 
      <td width=5% valign="middle"  class="Normal">  <p style='
  text-align:center'>
      辅<br>导<br>员<br>意<br>见</p>
      </td> 
      <td width=36% colspan=3 valign=bottom class="Normal"> <p style='
  text-align:right'>
      盖章:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
      日期:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p></td> 
    </tr> 
    <tr  height="20%"> 
      <td width=5% valign=middle class="Normal"> <p align=center style='
  text-align:center'>
  		分<br>院<br>系<br>意<br>见</p></td> 
      <td width=25% valign=bottom  class="Normal"> <p style='
  text-align:right'>
      盖章:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
      日期:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p></td> 
      <td width=5% colspan=2 valign="middle" class="Normal"><p align=center style='
  text-align:center'>
      	学<br>生<br>处<br>意<br>见</p></td> 
      <td width=25% colspan=4 valign=bottom class="Normal"> <p style='
  text-align:right'>
      盖章:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
      日期:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p></td> 
      <td width=5% valign="middle" class="Normal"> <p align=center style='
  text-align:center'>
  		学<br>院<br>意<br>见</p></td> 
      <td width=25% valign=bottom class="Normal"> <p style='
  text-align:right'>
      		盖章:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		<br>日期:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
      </td> 
    </tr> 
  </table>
  </html:form> 
</div> 
</center>
		<div class="noprint" align="center">
			<input type='button' class='button2' value='页面设置'
				onclick="WebBrowser.ExecWB(8,1);return false;">
			<input type='button' class='button2' value='打印预览'
				onclick="WebBrowser.ExecWB(7,1);return false;">
			<input type='button' class='button2' value='直接打印'
				onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>
</body>
</html>
