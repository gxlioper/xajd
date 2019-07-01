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
	<p style='line-height:16.0pt;'>
		<span style='font-size:14.0pt;font-family:仿宋_GB2312;'><br></span>
	</p> 
  	<p align=center style='text-align:center;line-height:16.0pt;'>
  		<b>
  			<span style='font-size:18.0pt;font-family:宋体;&quot;Times New Roman&quot;'>　　　
  			宁波天一职业技术学院
  			</span>
  		</b>
  	</p> 
  	<p align=center style='text-align:center'>
  		<b>
  		<span style='font-size:18.0pt;font-family:宋体;&quot;Times New Roman&quot;'>
  			<logic:empty name="rs">
  				_________
  			</logic:empty>
  			<logic:notEmpty name="rs">
  				<U>${rs.xn}</U>
  			</logic:notEmpty>
  			</span>
  		</b>
  		<b>
  			<span style='font-size:18.0pt;font-family:宋体;&quot;Times New Roman&quot;'>学年家庭经济困难学生困难补助审批表</span>
  		</b>
  	</p> 
  	<div align="center" style=" font-size:23px;">
  	<table width=80%  class="tbstyle" height=680> 
    <tr> 
      <td height=16% width=5% rowspan=4 class="Normal" > 
      	<br>本<br>人<br>情<br>况
      </td> 
      <td width=12% valign=top class="Normal">
      		姓名
      </td> 
      <td width=14% colspan=2 valign=top class="Normal">
      		<logic:empty name="rs">
      		&nbsp; 
      		</logic:empty>
      		<logic:notEmpty name="rs">
      			<bean:write name="rs" property="xm"/>
      		</logic:notEmpty>
      </td> 
      <td width=7% valign=top class="Normal">
      		性别
      </td> 
      <td width=14% valign=top class="Normal">
     		<logic:empty name="rs">
      		&nbsp; 
      		</logic:empty>
      		<logic:notEmpty name="rs">
      			<bean:write name="rs" property="xb"/>
      		</logic:notEmpty>
      </td> 
      <td width=18% colspan=2 valign=top class="Normal">
     	 出生年月
      </td> 
      <td width=11% valign=top class="Normal">
      	<logic:empty name="rs">
      		&nbsp; 
      		</logic:empty>
      	 <logic:notEmpty name="rs">
      			<bean:write name="rs" property="csrq"/>
      		</logic:notEmpty> 
      </td> 
      <td width=8% colspan=2 valign=top class="Normal">
      	民族
      </td> 
      <td width=8% valign=top class="Normal">
         	<logic:empty name="rs">
      		&nbsp; 
      		</logic:empty>
      		<logic:notEmpty name="rs">
      			<bean:write name="rs" property="mzmc"/>
      		</logic:notEmpty>
      </td> 
    </tr> 
    <tr> 
      <td width=12% valign=top class="Normal">
        学号
      </td> 
      <td width=14% colspan=2 valign=top class="Normal">
      	<logic:empty name="rs">
      		&nbsp; 
      		</logic:empty>
      	<logic:notEmpty name="rs">
      			<bean:write name="rs" property="xh"/>
      		</logic:notEmpty>
      </td> 
      <td width=26% colspan=3 valign=top class="Normal">
        专业、年级、班级
      </td> 
      <td width=42% colspan=5 valign=top class="Normal">
      	<logic:empty name="rs">
      		&nbsp; 
      		</logic:empty>
      	<logic:notEmpty name="rs">
      			<bean:write name="rs" property="zymc"/>&nbsp;<bean:write name="rs" property="nj"/>&nbsp;<bean:write name="rs" property="bjmc"/>
      	</logic:notEmpty>
      </td> 
    </tr> 
    <tr> 
      <td width=34% colspan=4 valign=top class="Normal">
      	申请补助的名称
      </td> 
      <td width=32% colspan=3 valign=top class="Normal">
      	<logic:empty name="rs">
      		助学金<input type="checkbox" disabled  name="bzlx"/>
      		临时困难补助<input type="checkbox" disabled name="bzlx"/>
      	</logic:empty>
      	<logic:notEmpty name="rs">
      	   <logic:notEqual name="rs" property="bzlx" value="002">
      		助学金<input type="checkbox" disabled checked name="bzlx"/>
      		临时困难补助<input type="checkbox" disabled name="bzlx"/>
      	   </logic:notEqual>
        	<logic:equal name="rs" property="bzlx" value="002">
      		助学金<input type="checkbox" disabled name="bzlx"/>
      		临时困难补助<input type="checkbox" disabled checked name="bzlx"/>
      		</logic:equal>
      	</logic:notEmpty>
      </td> 
      <td width=16% colspan=2 class="Normal"> 
      	品德等第
      </td> 
      <td width=12% colspan=2 valign=top class="Normal">
      	    <logic:empty name="rs">
      		&nbsp; 
      		</logic:empty>
      		<logic:notEmpty name="rs">
      			<bean:write name="rs" property="pddd"/>
      	</logic:notEmpty>
      </td> 
    </tr> 
    <tr> 
      <td width=18% colspan=2 valign=top class="Normal">
      	何学年获何种资助（写明受助金额）
      </td> 
      <td width=48% colspan=4 valign=top class="Normal">
      	<logic:empty name="rs">
      		&nbsp; 
      	</logic:empty>
      	<logic:notEmpty name="rs">
      			<bean:write name="rs" property="xnzz"/>
      	</logic:notEmpty>	
      </td> 
      <td width=16% colspan=2 class="Normal">
      	是否参加勤工助学和申请助学贷款
      </td> 
      <td colspan=3>
     	 <logic:empty name="rs">
      		&nbsp; 
      	</logic:empty>
      	<logic:notEmpty name="rs">
      		<bean:write name="rs" property="cjqgdg"/>
      	</logic:notEmpty>	 
      </td> 
    </tr> 
    <tr> 
      <td width=100% height="18%" colspan=12 valign=top class="Normal">
        申请理由:(应写明家庭具体困难情况，本人及家庭成员健康状况，目前的学习，生活状态。)<br>
        <logic:notEmpty name="rs">
          <bean:write name="rs" property="sqly"/>
         </logic:notEmpty>
       <br> <br><br> <br><br> <br><br> <br> 
        本人保证以上内容与事实相符。<br>
      
       <span style="float:right; " >申请人签名：　　　　　　　　　　　　年　　月　　日</span></td> 
    </tr> 
    <tr> 
      <td width=100% height=18% colspan=12 valign=top class="Normal">
      		班级评议：（应写明家庭困难情况及程度、品行表现、学习生活现状。）<br> <br><br> <br><br> <br><br> <br> 　　　　　　　　　　　　　　　　　　
   			<span style="float:right; " >签名：　　　　　　　　　　　　
			年&nbsp;&nbsp;
 			月&nbsp;&nbsp;
 			日&nbsp;&nbsp;</span></td> 
    </tr> 
    <tr> 
      <td width=100% height="18%" colspan=12 valign=top class="Normal">
      		 院系意见及公示结果：<br> <br><br> <br><br> <br><br> <br> 　　　　　　　　　　　　　　　　　
   			<span style="float:right; " >签名：　　　　　　　　　　　　
			年&nbsp;&nbsp;
 			月&nbsp;&nbsp;
 			日&nbsp;&nbsp;</span></td>
    </tr> 
    <tr> 
      <td width=100% height="9%" colspan=12 valign=top class="Normal">
      		学生资助管理中心审核意见：<br>
            &nbsp;&nbsp;&nbsp;&nbsp;情况属实，建议给予享受：（１）
            <logic:notEmpty name="rs">
            <logic:notEqual name="rs" property="bzmc" value="助学金">
      		<input type=checkbox disabled  name="bzlx"/>
        	</logic:notEqual>
        	<logic:equal name="rs" property="bzmc" value="助学金">
      		<input type=checkbox disabled checked name="bzlx"/>
        	</logic:equal>
        	</logic:notEmpty>
        	<logic:empty name="rs">
        		<input type=checkbox disabled name="bzlx"/>
        	</logic:empty>
            <bean:message key="lable.xsgzyxpzxy" />助学金，每月生活补助
            <logic:empty name="rs">________</logic:empty>
            <logic:notEmpty name="rs">
            	<logic:notEqual name="rs" property="bzmc" value="助学金">
            	________
            	</logic:notEqual>
            	<logic:equal name="rs" property="bzmc" value="助学金">
            	<U>${rs.bzje}</U>
            	</logic:equal>
            </logic:notEmpty>
            元。（２）
            <logic:notEmpty name="rs">
            <logic:notEqual name="rs" property="bzmc" value="临时补助">
      		<input type=checkbox disabled  name="bzlx"/>
        	</logic:notEqual>
        	<logic:equal name="rs" property="bzmc" value="临时补助">
      		<input type=checkbox disabled checked name="bzlx"/>
        	</logic:equal>
        	</logic:notEmpty>
        	<logic:empty name="rs">
        		<input type=checkbox disabled name="bzlx"/>
        	</logic:empty>
            <bean:message key="lable.xsgzyxpzxy" />临时困难补助，补助金额
            <logic:empty name="rs">
             	________
            </logic:empty>
            <logic:notEmpty name="rs">
            	<logic:notEqual name="rs" property="bzmc" value="临时补助">
            	________
            	</logic:notEqual>
            	<logic:equal name="rs" property="bzmc" value="临时补助">
            	<U>${rs.bzje}</U>
            	</logic:equal>
            </logic:notEmpty>
            元，发放方式为：
             <logic:empty name="rs">________</logic:empty>
            <logic:notEmpty name="rs">
            	<logic:notEqual name="rs" property="bzlx" value="002">
            	________
            	</logic:notEqual>
            	<logic:empty name="rs" property="fffs">
            	    <logic:equal name="bzlx" value="002">
            		________
            		</logic:equal>
            	</logic:empty>
            	<logic:equal name="rs" property="bzlx" value="002">
            		<U>${rs.fffs}</U>
            	</logic:equal>
            </logic:notEmpty>
            <span style="float:right; " >签名：　　　　　　　　　　　　
            年&nbsp;&nbsp;
            月&nbsp;&nbsp;
            日&nbsp;&nbsp;</span>
            </td> 
    </tr> 
    <tr> 
      <td width=100% height="7%" colspan=12 valign=top class="Normal">
      院领导审核意见：<br> <br><br> <br><br> <br><br> <br> 
       <span style="float:right; " >签名：　　　　　　　　　　　　
       年&nbsp;&nbsp;
       月&nbsp;&nbsp;
       日
       </td> 
    </tr> 
  </table>  
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
