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
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<body onload="initZje();initNext();" >
		<html:form action="/cjff" styleId="ygzff">
			<input type="hidden" value="<bean:write name="xxdm"/>" id="xxdm" />
			<input type="hidden" value="<bean:write name="userType"/>" id="userType" />
			<input type="hidden" value="<bean:write name="gwxzmc"/>" id="gwxzmc" />
			<input type="hidden" value="<bean:write name="pTotal"/>" id="count"  name="count"/>
			<input type="hidden" value="<bean:write name="currentPage"/>" id="currentPage"  name="currentPage"/>
			<input type="hidden" value="<bean:write name="startPage"/>" id="startPage"  name="startPage"/>
			<input type="hidden" value="<bean:write name="pageSize"/>" id="pageSize"  name="pageSize"/>
			<input type="hidden" value="<bean:write name="post" property="spbcbz"/>" id="spbcbz"  name="spbcbz"/>
			<input type="hidden" value="<bean:write name="pkValue"/>" id="pkValue" name="pkValue"/>
			<input type="hidden" value="<bean:write name="pk"/>" id="pk" name="pk"/>
			<input type="hidden" value="<bean:write name="zgcjje"/>" id="zgcjje" name="zgcjje"/>
			<input type="hidden" value="<bean:write name="zggzsj"/>" id="zggzsj" name="zggzsj"/>
			<input type="hidden" value="" id="printFlag" />
			
			<table border="0" id="rsTable" align="center" style="width:100%">
			<tr>
			<td>
			<div align="center">
   <p><font size="5"><b>（ <bean:write name="year"/> ）年（ <bean:write name="month"/>  ）月<bean:write name="title"/> </b> </font>
</p>
   <p>&nbsp;   </p>
 </div>
 <logic:equal value="ygzff" name="type">
<table class="tbstyle" align="center" style="width:100%">
  <tr>
    <td height="29" colspan="11">
    总人数：<bean:write name="pTotal"/> 
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    总金额：
    <span id="zje"> <bean:write name="zje"/></span>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    负责老师签名盖章：
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    负责老师联系电话：</td>
  </tr>
  <tr>
    <td>序号</td>
    <td>工作单位</td>
    <td>工作部门</td>
    <td>班级序号</td>
    <td>学号</td>
    <td>姓名</td>
    <td>月工作总时数</td>
    <td>金额</td>
    <td>联系电话</td>
    <td>学生签名</td>
    <td>备注</td>
  </tr>
  <logic:empty name="rs">
  	<tr>
  		<td colspan="11" align="center">
  			暂无记录！
  		</td>
  	</tr>
  </logic:empty>
  
  <logic:notEmpty name="rs">
	  <logic:iterate id="list" name="rs" indexId="index" offset="${startPage}" length="${pageSize}">
	  <tr>
	  	<logic:iterate id="v" name="list" offset="4" length="1">
	  		<input type="hidden" id="xh${index}" value="<bean:write name="v" />" name="xh${index}"/>	  
	  	</logic:iterate>
	  	<logic:iterate id="v" name="list" offset="0" length="1">
	  	<td>
	  		${index+1}
	  	</td>
	  	</logic:iterate>
	  	
	  	<logic:iterate id="v" name="list" offset="1" length="5">
	  	<td>
	  		<bean:write name="v"/>
	  	</td>
	  	</logic:iterate>
	  	<td>
	  	<logic:iterate id="v" name="list" offset="6" length="1">	  
	  		<input type="text" id="gzsj@@!!${index}" style="width:80px" value="<bean:write name="v" />" name="gzsj${index}" onchange="changeCjje(this)"/>	  	
	  	</logic:iterate>
	  	</td>
	  	<td>
	  	<logic:iterate id="v" name="list" offset="7" length="1">	  
	  		<input type="text" id="cjje${index}" style="width:40px" value="<bean:write name="v" />" name="cjje${index}"/>	  	
	  	</logic:iterate>
	  	</td>
	  	<logic:iterate id="v" name="list" offset="8" length="2">
	  	<td>
	  		<bean:write name="v" />
	  	</td>
	  	</logic:iterate>
	  	<td>
	  	<logic:iterate id="v" name="list" offset="10" length="1">	  
	  		<input type="text" id="bz${index}" style="width:80px" value="<bean:write name="v" />" name="bz${index}"/>	  	
	  	</logic:iterate>
	  	</td>
	  </tr>
	  </logic:iterate>
  </logic:notEmpty>
  </table>
  </logic:equal>
   <logic:equal value="lsggzff" name="type">
<table class="tbstyle" align="center" style="width:100%">
  <tr>
    <td height="29" colspan="10">
    总人数：<bean:write name="pTotal"/> 
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    总金额：
    <span id="zje"> <bean:write name="zje"/></span>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    负责老师签名盖章：
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    负责老师联系电话：</td>
  </tr>
  <tr>
    <td>序号</td>
    <td>工作部门</td>    
    <td>学号</td>
    <td>姓名</td>
    <td><bean:message key="lable.xsgzyxpzxy" /></td>
    <td>班级序号</td>
    <td>月工作总时数</td>
    <td>金额</td>
    <td>联系电话</td>
    <td>备注</td>
  </tr>
  <logic:empty name="rs">
  	<tr>
  		<td colspan="11" align="center">
  			暂无记录！
  		</td>
  	</tr>
  </logic:empty>
  
  <logic:notEmpty name="rs">
	  <logic:iterate id="list" name="rs" indexId="index" offset="${startPage}" length="${pageSize}">
	  <tr>
	  	<logic:iterate id="v" name="list" offset="1" length="1">
	  		<input type="hidden" id="xh${index}" value="<bean:write name="v" />" name="xh${index}"/>	  
	  	</logic:iterate>
	  	<logic:iterate id="v" name="list" offset="0" length="1">
	  	<td>
	  		${index+1}
	  	</td>
	  	</logic:iterate>
	  	
	  	<logic:iterate id="v" name="list" offset="1" length="5">
	  	<td>
	  		<bean:write name="v"/>
	  	</td>
	  	</logic:iterate>
	  	<td>
	  	<logic:iterate id="v" name="list" offset="6" length="1">	  
	  		<input type="text" id="gzsj@@!!${index}" style="width:80px" value="<bean:write name="v" />" name="gzsj${index}" onchange="changeCjje(this)"/>	  	
	  	</logic:iterate>
	  	</td>
	  	<td>
	  	<logic:iterate id="v" name="list" offset="7" length="1">	  
	  		<input type="text" id="cjje${index}" style="width:40px" value="<bean:write name="v" />" name="cjje${index}" onchange="checkRange(this)"/>	  	
	  	</logic:iterate>
	  	</td>
	  	<logic:iterate id="v" name="list" offset="8" length="1">
	  	<td>
	  		<bean:write name="v" />
	  	</td>
	  	</logic:iterate>
	  	<td>
	  	<logic:iterate id="v" name="list" offset="9" length="1">	  
	  		<input type="text" id="bz${index}" style="width:80px" value="<bean:write name="v" />" name="bz${index}"/>	  	
	  	</logic:iterate>
	  	</td>
	  </tr>
	  </logic:iterate>
  </logic:notEmpty>
  </table>
  
  </logic:equal>
  </td>
  </tr>
  </table>
  <logic:equal value="yes" name="writeAble">
	  <logic:notEmpty name="rs">
	  	<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
			<button type="button" class="button2" onclick="saveYgzbb()" style="width:80px">
					保 存 
			</button>
			&nbsp;&nbsp;&nbsp;
			<button type="button" class="button2" onclick="goUp();" style="width:80px" disabled="disabled" id="up">
					上一页 
			</button>
			&nbsp;&nbsp;&nbsp;
			<button type="button" class="button2" onclick="goNext();" style="width:80px" disabled="disabled" id="nextOne">
					下一页 
			</button>
			&nbsp;&nbsp;&nbsp; 
			<button type="button" class="button2" onclick="printYgzbb()" style="width:80px">
					打 印
			</button>
		</div>
	  </logic:notEmpty>
  </logic:equal>
  <logic:present name="result">
  <logic:equal value="true" name="result">
  	<script>
  		alert('操作成功！');
  	</script>
  </logic:equal>
  <logic:equal value="false" name="result">
  	<logic:present name="msg">
  		<input type="hidden" id="msg" name="msg" value="<bean:write name="msg"/>"/>
  		<script>
  			alert(document.getElementById('msg').value);
  		</script>
  	</logic:present>
  	<logic:notPresent name="msg">
  		<script>
  			alert('操作失败！');
  		</script>
  	</logic:notPresent>
  </logic:equal>
  </logic:present>
</html:form>
<script language="javascript" src="js/bottomButton.js"></script>		
</body>
</html>
