<%@ page language="java" pageEncoding="GB2312" contentType="text/html;charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <base target="_self" />
    
    <title><bean:message key="lable.title" /></title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <% 
	response.setHeader("Pragma","no-cache");
	response.setHeader("Cache-Control","no-cache");	
	response.setHeader("Expires","0");	
	%>	
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
  </head>
  
  <body>
    <html:form action="/xpjjdksqb" method="post">
		<p align="center" style="font-size:24px" ><b>浙江大学心平基金自立助学贷款借款申请表</b> </p>
		<p align="center">&nbsp; </p>
		<input type="hidden" id="xb" value="<bean:write name="rs" property="xb" />">
		<table id="theTable" width="100%" class="tbstyle">
				  <tr>
				    <td height="31" colspan="2"><p>借款人姓名&nbsp;&nbsp;<bean:write name="rs" property="xm"/></p></td>
				    <td height="31" colspan="4">
				   	  <logic:equal value="男" name="xb">
				   	     <p align="center">性别&nbsp;&nbsp;&nbsp;&nbsp;[■] 男 [ ] 女</p>
				   	  </logic:equal>
				      <logic:equal value="女" name="xb">
				         <p align="center">性别&nbsp;&nbsp;&nbsp;&nbsp;[ ] 男 [■] 女</p>
				      </logic:equal>
				    </td>
				    <td height="31">出生日期&nbsp;&nbsp;<bean:write name="rs" property="csrq"/></td>
				  </tr>
				  <tr>
				    <td height="31" colspan="2"><p>就读学校：浙江大学 </p></td>
				    <td height="31" colspan="4"><p>身份证号码&nbsp;&nbsp;<bean:write name="rs" property="sfzh"/></p></td>
				    <td width="183" height="31"><p>年级&nbsp;&nbsp;<bean:write name="rs" property="nj" /></p></td>
				  </tr>
				  <tr>
				    <td width="161" height="31"><p><bean:message key="lable.xsgzyxpzxy" />&nbsp;&nbsp;<bean:write name="rs" property="xy"/></p></td>
				    <td height="31" colspan="4">专业&nbsp;&nbsp;<bean:write name="rs" property="zymc"/>&nbsp;&nbsp;</td>
				    <td width="158" height="31">宿舍电话&nbsp;&nbsp;<bean:write name="rs" property="qsdh" /></td>
				    <td width="183" height="31"><p>学号&nbsp;&nbsp;<bean:write name="rs" property="xh"/></p></td>
				  </tr>
				  <tr>
				    <td width="161" height="31" align="left"><p>学制</p></td>
				    <td height="31" colspan="6"><p>本科 （&nbsp;<bean:write name="rs" property="xz"/>&nbsp;）年 </p></td>
				  </tr>
				  <tr>
				    <td width="161" height="31"><p>申请贷款金额 </p></td>
				    <td height="31" colspan="6"><p>总额 &nbsp;<bean:write name="rs" property="sqdkje" />&nbsp;元 </p></td>
				  </tr>
				  <tr>
				    <td width="161" height="31"><p>贷款期限 </p></td>
				    <td height="31" colspan="6"><p>贷款期限 &nbsp;<bean:write name="rs" property="dkqx" />&nbsp;个月 </p></td>
				  </tr>
				  <tr>
				    <td colspan="3" rowspan="3" valign="top"><p>家庭详细住址： </p>
				        <p>&nbsp;<bean:write name="rs" property="szzq"/> 省（自治区） </p>
				        <p>&nbsp;<bean:write name="rs" property="sxq"/>市（县、区） </p>
				        <p>邮编：&nbsp;<bean:write name="rs" property="yzbm"/>&nbsp; 家庭电话：&nbsp;<bean:write name="rs" property="jtdh"/>&nbsp; </p>
				        <p>家长或法定监护人姓名 ：&nbsp;<bean:write name="rs" property="jhr1_xm"/>&nbsp;</p>
				        <p>职业：&nbsp;<bean:write name="rs" property="jhr1_zy"/>&nbsp; </p>
				        <p>公民身份证号码：&nbsp;<bean:write name="rs" property="jhr1_sfzh"/>&nbsp;</p>
				        <p>工作单位名称：&nbsp;<bean:write name="rs" property="jhr1_gzdw"/>&nbsp;</p>
				        <p>家长或法定监护人姓名 ：&nbsp;<bean:write name="rs" property="jhr2_xm"/>&nbsp;</p>
				        <p>职业：&nbsp;<bean:write name="rs" property="jhr2_zy"/>&nbsp;</p>
				        <p>公民身份证号码：&nbsp;<bean:write name="rs" property="jhr2_sfzh"/>&nbsp;</p>
				        <p>工作单位名称：&nbsp;<bean:write name="rs" property="jhr2_gzdw"/>&nbsp;</p></td>
				    <td colspan="4" valign="top"><p>&nbsp;</p>
				      <p><strong>本人保证以上填写内容真实无误</strong><strong>。 </strong></p>
				      <p>&nbsp;</p>
				      <p>借款申请人（签字）： </p>
				      <p align="right">年 　月 　日</p></td>
				  </tr>
				  <tr>
				    <td colspan="4" valign="top"><p>家长意见： </p>
				        <p>同意 申请 <strong>心平基金自立助学贷款。 </strong><strong></strong></p>
				        <p><strong>家长（签字）： </strong><strong></strong></p>
				        <p><strong></strong> 　 　 　 　 　 　 　 　 　 　 　 　 　 　 　 　 　 　 　 　 　年 　月 　日 <strong></strong></p></td>
				  </tr>
				  <tr>
				    <td colspan="4" valign="top"><p><bean:message key="lable.xsgzyxpzxy" />意见： </p>
				        <p><strong>借款申请人系我院就读学生 </strong><strong>, </strong><strong>表内所填资料属实并且符合心平基金自立助学贷款申请条件。 </strong></p>
				        <p>&nbsp; </p>
				        <p>负责人： <bean:message key="lable.xsgzyxpzxy" /> ( 签章 ): </p>
				        <p align="right">年　 月　 日 </p></td>
				  </tr>
				  <tr>
				    <td colspan="7"><p>浙江大学学生工作处负责人意见： </p>
				      <p>&nbsp;</p>      
				      <p> ( 签章 ) ：　　　　　　　　　　（签字）： 　　　　　　　　　　　　 　 　 　 　 　 　 　 　 　 　 　年 　月 　日 </p></td>
				  </tr>
				  <tr>
				    <td colspan="7"><p>浙江大学心平基金自立助学贷款管理委员会意见： </p>
				      <p>&nbsp;</p>      <p>负责人（签字）：　　　　　　　　　　　　　　　　　　　　　　　 　 　 　 　 　 　 　 　 　 　 　 　 年 　月 　日 </p></td>
				  </tr>
				  <tr>
				    <td colspan="7"><p>&nbsp; 备注： </p>
				      <p>&nbsp;</p></td>
				  </tr>
		</table>
		<p align="right">&nbsp; 本表壹式肆份，申请者所在<bean:message key="lable.xsgzyxpzxy" />、校学生工作处、教育基金会及申请者本人各壹份。 </p>
    </html:form>
    <div align=center>
    	<input type=button value=打印 onclick="expTab('theTable','浙江大学心平基金自立助学贷款借款申请表');" />
    </div>
  </body>
</html:html>
