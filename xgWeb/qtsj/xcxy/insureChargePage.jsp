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
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
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
	<base target="_self">
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<script language="javascript" src="js/function.js"></script>
	<body>
		<html:form action="/insureChargeList.do" method="post">
			<input type="hidden" name="dm" value="" />			
			<p><center><h3>投保交费清单（学生险专用）</h3></center>
			
				<p>
				汇交申请书号：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;				
				汇交人：<bean:write name="userNameReal" scope="session"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;				
				学校：<bean:write name="xxmc" scope="request"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;				
				班级：<bean:write name="bjmc" scope="request"/>
				</p>
			
			<table height="265" class="tbstyle" id="tb">
			  <tr>			  
			  <logic:lessEqual value="4" name="insureNum">
			 	  <td height="116" colspan="4" rowspan="3">		    
			        <p> 每一被保险人交保险费</p>			       
			    </td>
			    <td width="109" rowspan="3"> <bean:write name="totalmoney" scope="request"/>元 </td>
			    <td height="23" colspan="5"> 保险金额 : (人民币 元)</td>
			  </logic:lessEqual>
			  
			  <logic:greaterThan value="4" name="insureNum">
			  	 <td height="116" colspan="5" rowspan="5">		    
			        <p> 每一被保险人交保险费</p>
			    </td>
			    <td width="109" rowspan="5"> <bean:write name="totalmoney" scope="request"/>元 </td>
			    <td height="23" colspan="4"> <center>保险金额 : (人民币 元)</center></td>
			  </logic:greaterThan>	
			 
			  </tr>
				<%--小于四--%>
			   <logic:lessEqual value="4" name="insureNum">
			   <tr>
			   	 <logic:iterate id="xzmc" name="bxxzList" offset="0" length="${insureNum}">
			   		<td>${xzmc.bxxzmc}</td>
			  	 </logic:iterate>
			  	 <td colspan="${5-insureNum}"></td>
			   </tr>
			   <tr>
			   	 <logic:iterate id="xzmc" name="bxxzList" offset="0" length="${insureNum}">
			   		<td>${xzmc.bxje}</td>
			  	 </logic:iterate>
			  	 <td colspan="${5-insureNum}"></td>
			   </tr>			  	
			   </logic:lessEqual>
			   
			    <%--大于四--%>
			   <logic:greaterThan value="4" name="insureNum">
			   	<tr>
				   <logic:iterate id="xzmc" name="bxxzList" offset="0" length="4">
				   	<td>${xzmc.bxxzmc}</td>
				   </logic:iterate>
				<tr>
					 <logic:iterate id="xzmc" name="bxxzList" offset="0" length="4">
				   		<td>${xzmc.bxje}</td>
				   	</logic:iterate>
				</tr>
				
				<tr>
				   <logic:iterate id="xzmc" name="bxxzList" offset="4" length="4">
				   	<td>${xzmc.bxxzmc}</td>
				   </logic:iterate>
				   <td colspan="${4-(insureNum-4)}"></td>
				<tr>
					 <logic:iterate id="xzmc" name="bxxzList" offset="4" length="4">
				   		<td>${xzmc.bxje}</td>
				   	</logic:iterate>
				   	<td colspan="${4-(insureNum-4)}"></td>
				</tr>
			   </logic:greaterThan>		
			<%--小于25  --%>
			  <logic:lessEqual value="25" name="bxxxNum">
			  <tr>
			    <td width="13" height="30">序号</td>
			    <td width="36">保险单号</td>
			    <td width="48">被保险人姓名</td>
			    <td width="81">性别</td>
			    <td height="30">出生年月日 </td>
			    <td height="30">序号</td>
			    <td height="30">保险单号</td>
			    <td height="30">被保险人姓名</td>
			    <td width="57" height="30">性别</td>
			    <td width="105">出生年月日</td>
			  </tr>
			  <logic:iterate id="bx" name="bxxxList" offset="0" length="${bxxxNum}">
			  <tr>			  	
			  		<td>${bx.num}</td>
			  		<td>${bx.bxpzh}</td>
			  		<td>${bx.xm}</td>
			  		<td>${bx.xb}</td>
			  		<td>${bx.csrq}</td>			  	
			  		<td></td>
			  		<td></td>
			  		<td></td>
			  		<td></td>
			  		<td></td>			  		
			  </tr>
			  </logic:iterate>
			  </logic:lessEqual>
			<%-- 大于25--%>
			  <logic:greaterThan value="25" name="bxxxNum">		
			  <tr>
			  <td colspan="7" valign="top">
			  <table class="tbstyle" width="100%" align="top">
			  	<tr>
			  	<td width="13" height="30">序号</td>
			    <td width="36">保险单号</td>
			    <td width="48">被保险人姓名</td>
			    <td width="81">性别</td>
			    <td height="30">出生年月日 </td>
			    </tr>
			  	<logic:iterate id="bxxx" name="bxxxList" offset="0" length="25">
			  	<tr>
			  		<td>${bxxx.num}</td>
			  		<td>${bxxx.bxpzh}</td>
			  		<td>${bxxx.xm}</td>
			  		<td>${bxxx.xb}</td>
			  		<td>${bxxx.csrq}</td>
			  	</tr>
			  	</logic:iterate>
			  </table>
			  </td>			  
			  <td colspan="3" valign="top">
			  <table class="tbstyle" width="100%" align="top">
			  	<tr valign="top">
			  	<td width="13" height="30">序号</td>
			    <td width="36">保险单号</td>
			    <td width="48">被保险人姓名</td>
			    <td width="81">性别</td>
			    <td height="30">出生年月日 </td>
			    </tr>
			  	<logic:iterate id="bxxx" name="bxxxList" offset="25" length="25">
			  		<tr>
			  		<td>${bxxx.num}</td>
			  		<td>${bxxx.bxpzh}</td>
			  		<td>${bxxx.xm}</td>
			  		<td>${bxxx.xb}</td>
			  		<td>${bxxx.csrq}</td>
			  		</tr>
			  	</logic:iterate>	
			  </table>
			  </td>	  
			  </tr>				  	
			  </logic:greaterThan>	
			  <tr>
			  	<td colspan="10">
			  		<p>
			  			本页人数合计：${bxxxNum}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  			人数总计：${bxxxNum}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  			本页保费小计：${totalmoney*bxxxNum}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  			保费合计：${totalmoney*bxxxNum}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  		</p>
			  	</td>
			  </tr>
			  <tr>
			  	<td colspan="10">
			  		<p>
			  			填写说明，每一汇交件的投保清单填写保额、保费必须相同&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  			${year}年&nbsp;&nbsp;&nbsp;&nbsp;
			  			${month}月&nbsp;&nbsp;&nbsp;&nbsp;
			  			${day}日 
			  		</p>
			  	</td>
			  </tr>				
			</table>
		</html:form>
			<div class='noPrin' align="center">
				<input type='button' class='button2' value='页面设置' onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='打印预览' onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='直接打印' onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>	
	</body>
</html>
