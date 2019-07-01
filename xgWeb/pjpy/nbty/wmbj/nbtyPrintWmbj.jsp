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
  <body bgcolor="#FFFFFF" class="Normal" lang=ZH-CN> 
  		<p align=center style='text-align:center;'>
  			<b><span style='font-size:18.0pt;'>
  				宁波天一职业技术<bean:message key="lable.xsgzyxpzxy" />
  				<logic:empty name="rs">
  				＿＿
  				</logic:empty>
  				<logic:notEmpty name="rs">
  				<U>
  					<bean:write name="rs" property="xn"/>
  				</U>
  				</logic:notEmpty>
  				学年度
  				</span>
  			</b>
  		</p> 
  		<p align=center style='text-align:center;'><b><span style='font-size:24.0pt;'>文明班级登记表</span></b></p> 
  	<center>
  	<html:form action="nbtyWmbj" method="post">	
  		<table  class="tbstyle" width=85% height=780> 
   		 <tr> 
      		<td width=7%  height=6% valign="middle" align="center" class="Normal">
      			院/系
      		</td> 
    	    <td width=33% colspan="2"  valign="middle" align="center" class="Normal">
    	   		<logic:empty name="rs">
  				&nbsp; 
  				</logic:empty>
  				<logic:notEmpty name="rs">
  					<bean:write name="rs" property="xymc"/>
  				</logic:notEmpty>
    	    	
    	    </td> 
    	    <td width=7%  valign="middle" align="center"  class="Normal">
    	    	班　级
    	    </td> 
            <td width=33%  valign="middle" align="center" class="Normal">
				<logic:empty name="rs">
  				&nbsp; 
  				</logic:empty>
  				<logic:notEmpty name="rs">
  					<bean:write name="rs" property="bjmc"/>
  				</logic:notEmpty>
			</td> 
   		 </tr> 
    	 <tr> 
      		<td width="7%" heigth="15%" valign="middle" align="center"  class="Normal">
      			<span style="text-align:center">主<br>要<br>事<br>迹</span>
      		</td> 
      		<td width=95% colspan=4  class="Normal">
      			<logic:notEmpty name="rs">
  					<bean:write name="rs" property="zysj"/>
  				</logic:notEmpty>
  				<br><br><br><br><br><br>
      		<p style=' text-align:right'>
      		班主任:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		<br>
      		日期:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		</p>
    </tr> 
    <tr> 
      <td width="7%" heigth="15%" valign="middle" align="center" class="Normal">
      		奖<br>励<br>记<br>录
      </td> 
      <td width=552 colspan=4 valign=top class="Normal">
				<logic:notEmpty name="rs">
  					<bean:write name="rs" property="jljl"/>
  				</logic:notEmpty>
	  </td> 
    </tr> 
    <tr> 
      <td width="5%" heigth="15%" valign="middle" align="center" class="Normal">
            分<br>院<br>系<br>意<br>见
      </td> 
      <td width=552 colspan=4  class="Normal">
      		<logic:notEmpty name="rs">
  					<bean:write name="rs" property="xyyj"/>
  				</logic:notEmpty>
  				<br><br><br><br><br><br>
      		<p style=' text-align:right'>
      		盖章:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		<br>
      		日期:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		</p>
      </td> 
    </tr> 
    <tr> 
      <td width="7%" heigth="20%" valign="middle" align="center" class="Normal">
      		学<br>生<br>处<br>意<br>见
      </td> 
      <td width=252 colspan=2  class="Normal">
 	  			<logic:notEmpty name="rs">
  					<bean:write name="rs" property="xscyj"/>
  				</logic:notEmpty>
      			<span style="float:right;">
      			<br><br><br><br><br><br>
      		<p style=' text-align:right'>
      		盖章:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		<br>
      		日期:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		</p>
 	  </td> 
      <td width="5%" valign="middle" align="center" class="Normal">
      		学<br>院<br>意<br>见
      </td> 
      <td width=240  class="Normal"> 
    		 <logic:notEmpty name="rs">
  					<bean:write name="rs" property="xxyj"/>
  				</logic:notEmpty>
      			
      		<br><br><br><br><br><br>
      		<p style=' float:right'>
      		盖章:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		<br>
      		日期:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		</p>
      		</td> 
    </tr> 
  </table> 
  </html:form> 
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
