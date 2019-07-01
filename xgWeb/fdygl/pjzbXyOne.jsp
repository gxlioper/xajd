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
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta name="Copyright" content="正方软件 zfsoft" />
</head>
<base target="_self">
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<script language="javascript" src="js/commanFunction.js"></script>
<script language="javascript" src="js/fdyglFunction.js"></script>
<script>
function chkPjzbXyDoType(){
	var pjh = $("pjh").value;
	var d_html = pjh + "<input type='hidden' name='pjh' value='" + pjh + "'>";
	$("doRow").innerHTML = d_html;
}
</script>
<body onload="chkPjzbXyDoType();xyDisabled('xy')"> 
<html:form action="/setPjzb" method="post"> 
<div class="title"> 
  <div class="title_img" id="title_m"> 当前所在位置：辅导员管理 - 思政考核 - 评价指标设置 </div> 
</div> 
<fieldset> 
<legend>评价指标设置</legend> 
<input type="hidden" id="doType" name="doType" value="<bean:write name="doType" scope="request"/>">
<input type="hidden" name="userType" value="${userType}" />
<table class="tbstyle" width="100%"> 
    <tr> 
      <td align="right" width="60">评价号:</td> 
      <td align="left" colspan="3" id="doRow">
        <html:text property="pjh" name="fdyglForm" styleId="pjh" style="width:100%" ></html:text>
      </td> 
    </tr> 
    <tr> 
      <td align="right" width="60">面向对象:</td> 
      <td align="left">
      <html:select property="mxdx" name="fdyglForm" style="width:100px">
        <html:options collection="mxdxList" property="qtdm" labelProperty="qtmc"/>
      </html:select>
      </td> 
      <td align="right" width="60">对应分值:</td> 
      <td align="left">
        <html:text onblur="numFormatChk(this,0)" property="fz" name="fdyglForm"  maxlength ="4" style="width:90px" styleId="fz"></html:text>
      </td> 
    </tr>
    <tr> 
      <td align="right" width="60">评价指标:</td> 
      <td align="left">
      <html:select property="stlbdm" name="fdyglForm" style="width:100px" >
        <html:options collection="stlblist" property="stlbdm" labelProperty="stlbmc"/>
      </html:select>
      </td> 
      	<td align="right" width="60">所属<bean:message key="lable.xsgzyxpzxy" />:</td> 
      	<td align="left">
       	<html:select property="xydm" name="fdyglForm" styleId="xy" style="width:100px">
      		<option value=""> </option>
       	 	<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
      	</html:select>
        </td>
    </tr>  
    <tr> 
      <td align="right" width="60">评价内容:</td> 
      <td align="left" colspan="3">
        <html:textarea property="pjnr" name="fdyglForm" rows="6" style="width:480px" styleId="pjnr"></html:textarea>
      </td> 
    </tr>
   	<tr> 
      <td align="right" width="60">具体评价指标信息:</td> 
      <td align="left" colspan="3">
        <html:textarea property="jtzb" name="fdyglForm" rows="9" style="width:480px" styleId="jtxx"></html:textarea>
      </td> 
    </tr>
</table> 
</fieldset> 

<logic:equal value="yes" name="writeAble" scope="request"> 
<div class="buttontool" width="100%"> 
  <button class="button2" onclick="savePjzb()">保存</button> 
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
  <button class="button2" onclick="Close()">关闭</button> 
</div> 
</logic:equal>

  <logic:present name="ok" scope="request">
    <logic:equal value="ok" name="ok" scope="request">
      <script>alert("保存成功！");window.dialogArguments.location="setPjzb.do";Close();</script>
    </logic:equal>
    <logic:equal value="no" name="ok" scope="request">
      <script>alert("保存失败，请重试！");</script>
    </logic:equal>
  </logic:present>
  
</html:form> 
</body>
</html>
