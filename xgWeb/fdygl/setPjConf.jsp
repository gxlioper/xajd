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
<%@ include file="/syscommon/pagehead_V4.ini"%>
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
<body> 
<html:form action="/prise_conf_rs" method="post"> 
<div class="tab_cur">
	<p class="location">
		<em>您的当前位置:</em><a>辅导员管理 - 思政考核 - 评价参数设置</a>
	</p>
</div> 
<div class="toolbox">
		 <div class="buttonbox">
			    <ul>
				<li> <a href="#" onclick="refreshForm('setPjConf.do?act=save')" class="btn_ccg">保存 </a> </li>
				<li> <a href="#" onclick="showTopWin('setPfbz.do',800,600,1)" class="btn_sq">维护评分标准 </a> </li>
			    </ul>
		 </div>
		 <!-- 过滤条件 -->
		<div class="searchtab">
			<table width="100%" border="0">
			     
		 <thead> 
	    <tr> 
	      <th width="100" align="right">评价是否开放</th> 
	      <td align="left"> <input type="radio" name="sfkpj" value="是"
							<logic:equal name="sfkpj" value="是"> checked </logic:equal> >开放 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			   <input type="radio" name="sfkpj" value="否"
							<logic:equal name="sfkpj" value="否"> checked </logic:equal> >关闭 </td>  
	    </tr> 
	  </thead> 
</table> 
</div>
<div class="formbox">
	 <h3 class="datetitle_01">
			    <span>
			    	评价群体及比例设置&nbsp;&nbsp;
			    	<logic:empty name="cpqtList">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			    </span>
			    </h3>
 <table summary="" class="dateline" align="" width="100%">
  <thead> 
    <tr> 
      <td align="center">群体代码</td> 
      <td align="center">群体名称</td>
      <td align="center">所属群组</td>  
      <td align="center">是否参评</td> 
      <td align="center">评分比例</td> 
      <td align="center">评分标准</td> 
    </tr> 
  </thead> 
  <tbody>
  <logic:present name="cpqtList"> 
    <logic:iterate id="cpqt" name="cpqtList"> 
    <tr> 
      <td> <bean:write name="cpqt" property="qtdm" /> <html:hidden name="cpqt" property="qtdm" /> </td> 
      <td><bean:write name="cpqt" property="qtmc" /></td> 
      <td> <html:select name="cpqt" property="khqzdm"> 
      		  <html:option value=""></html:option> 
             <html:options collection="khqzList" property="qzdm" labelProperty="qzmc"/> 
           </html:select> </td> 
      <td> <html:select name="cpqt" property="sfcp"> 
             <html:options collection="yesNoList" property="en" labelProperty="cn"/> 
           </html:select> </td> 
      <td><html:text style="width:60px" name="cpqt" property="pfbl" maxlength="5" onblur="numFormatChk(this,0,1)"/></td> 
      <td> <html:select name="cpqt" property="pfbz"> 
             <html:options collection="pfbzList" property="bzdm" labelProperty="bzmc"/> 
           </html:select> </td> 
    </tr> 
    </logic:iterate> 
  </logic:present> 
  </tbody>
</table> 
</div>
  <logic:present name="ok" scope="request">
    <logic:equal value="ok" name="ok" scope="request">
      <script>alert("保存成功！");Close();</script>
    </logic:equal>
    <logic:equal value="no" name="ok" scope="request">
      <script>alert("保存失败，请重试！");</script>
    </logic:equal>
  </logic:present>
</html:form> 
 <script type="text/javascript" src="js/bottomButton.js"></script> 
</body>
</html>
