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
<meta name="Copyright" content="������� zfsoft" />
</head>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<script language="javascript" src="js/commanFunction.js"></script>
<script language="javascript" src="js/xszzFunction.js"></script>
<body onload="xyDisabled('xy')"> 
<html:form action="knsSh.do" method="post"> 
<div class="title"> 
  <div class="title_img" id="title_m"> ��ǰ����λ�ã� <bean:write name="tips" scope="request"/></div> 
</div> 
<input type="hidden" value="<bean:write name="userType" scope="session"/>" name="userType"/> 
<logic:notEqual value="stu" name="userType" scope="session"> 
<fieldset> 
<legend> ��ѯ </legend> 
<table width="100%" class="tbstyle"> 
  <thead> 
    <tr> 
      <td>ѧ�꣺ 
        <html:select property="xn" styleId="xn" onchange="refreshForm('knsSh.do')"> 
          <html:options collection="xnndList" property="xn" labelProperty="xn" /> 
        </html:select> &nbsp;&nbsp;&nbsp;&nbsp;��ȣ� 
        <html:select property="nd" styleId="nd" onchange="refreshForm('knsSh.do')"> 
          <html:options collection="xnndList" property="nd" labelProperty="nd" /> 
        </html:select> &nbsp;&nbsp;&nbsp;&nbsp;ѧ�ڣ� 
        <html:select property="xq" styleId="xq" onchange="refreshForm('knsSh.do')"> 
          <html:option value=""></html:option> 
          <html:options collection="xqList" property="xqdm" labelProperty="xqmc" /> 
        </html:select> &nbsp;&nbsp;&nbsp;&nbsp;�꼶�� 
	    <html:select property="nj" onchange="refreshForm('knsSh.do')"> 
	      <html:option value=""></html:option> 
	      <html:options collection="njList" property="nj" labelProperty="nj" /> 
	    </html:select> &nbsp;&nbsp;&nbsp;&nbsp; <bean:message key="lable.xsgzyxpzxy" />�� 
	    <html:select property="xydm" styleId="xy" onchange="refreshForm('knsSh.do')"> 
	      <html:option value=""></html:option> 
	      <html:options collection="xyList" property="xydm" labelProperty="xymc" /> 
	    </html:select> </td> 
    </tr> 
  </thead> 
</table> 
</fieldset> 
</logic:notEqual> 

<logic:empty name="rs"> <br /> 
<br /> 
<p align="center"> δ�ҵ��κμ�¼�� </p> 
</logic:empty> 

<logic:notEmpty name="rs"> 
<fieldset> 
<legend> ��¼���� <bean:write name="rsNum" /> </legend> 
<table width="100%" id="rsTable" class="tbstyle"> 
  <thead> 
    <tr align="center" style="cursor:hand"> 
      <logic:iterate id="tit" name="topTr"> 
      <td id="<bean:write name="tit" property="en"/>" onclick="tableSort(this)" nowrap> <bean:write name="tit" property="cn" /></td> 
      </logic:iterate> </tr> 
  </thead> 
  <logic:iterate name="rs" id="s"> 
  <tr onclick="rowOnClick(this)" style="cursor:hand;"> 
    <logic:iterate id="v" name="s"> 
    <td> <bean:write name="v" /> </td> 
    </logic:iterate> </tr> 
  </logic:iterate> 
</table> 
</fieldset> 
</logic:notEmpty> 

  <logic:equal value="yes" name="writeAble">
  <div class="buttontool" id="btn" style="position: absolute;left:0px;top:100px" width="99%">
	<button class="button2" onclick="viewKnsList()">��ʼ���</button>
  </div>
  </logic:equal>

</html:form> 
  
  <script type="text/javascript" src="js/bottomButton.js"></script> 
</body>
</html>
