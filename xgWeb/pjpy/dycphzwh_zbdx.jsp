<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <base target="_self" />
  <head>
    <title><bean:message key="lable.title" /></title>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
  </head>
  <link rel="icon" href="images/icon.ico" type="image/x-icon" />
  <link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
  <script language="javascript" src="/xgxt/js/function.js"></script>
  <script language="javascript" src="/xgxt/js/jsFunction.js"></script>
  <script language="javascript" src="/xgxt/js/pjpyFunction.js"></script>
  <script language="javascript" src="/xgxt/js/sharedFunction.js"></script>    
  <script language="javascript" src="/xgxt/dwr/interface/zbdxZhcpJfwh.js"></script>		
  <script language="javascript" src="/xgxt/dwr/engine.js"></script>
  <script language="javascript" src="/xgxt/dwr/util.js"></script>  
  <body onload="getCurrentRowNum()">
  
  <html:form action="/dyszwh">
  <logic:present name="doresult">
  	<logic:equal value="true" name="doresult">
  	   <script>
  	      alert("保存成功！");
  	   </script>
  	</logic:equal>
  	<logic:equal value="false" name="doresult">
  	   <script>
  	      alert("保存失败！");
  	   </script>
  	</logic:equal>  	
  </logic:present>
  <div class="title">
      <div class="title_img" id="title_m">
		<bean:message bundle="pjpy" key="dyszjfwh"/> 
	  </div>
  </div>
  
    <table width="100%" class="tbstyle" >
      
	  <tr>
	    <td width="17%"><div align="center"><font color=red>*</font>学号：</div></td>
	    <td width="33%" id="xh"><bean:write name="rs" property="xh" /></td>
	    <td width="14%" ><div align="center"><font color=red>*</font>姓名：</div></td>
	    <td width="36%" id="xm"><bean:write name="rs" property="xm" /></td>
	  </tr>
	  <tr>
	  	<td align=center><font color=red>*</font>学年：</td>
	    <td>
	       <html:select name="rs" property="xn" style="width:92%" styleId="xn">
	           <html:options collection="xnList" property="xn" labelProperty="xn" />
	       </html:select>
	    </td>
	    <td align=center></td>
	    <td>
	    </td>
	  </tr>
	  <thead>
        <tr><td colspan="4" align="center">体 育 素 质 加 分</td></tr>
      </thead>
	  <tr>
	    <td colspan='4'>
	    	<select size="8" id="tyjf" name="tyjf" ondblclick="dblClickOnSelect(this)">
	    		<option>体育项目－－－－－－－－－－－－－－－－－－－－－－－－－－得分</option>
	    		<logic:iterate name="tyjfxmList" id="v">
	    			<option value="${v.tyjsdm}">${v.opt}</option>
	    		</logic:iterate>
	    	</select>
	    </td>
	  </tr>
	  <tr>  
	    <td colspan='2'>
	    	<html:select property="tyjsdm" styleId="tyjsdm">
	    		<html:option value="所获的体育加分项目" />
	    		<html:options collection="tyxmList" property="tyjsdm" labelProperty="tyjsmc"/><!-- 体育项目列表 -->
	    	</html:select>
	    </td>
	    <td>
	    	<input type="text" id="tyjft" value="输入所加分值" onclick="dataCheckInit(this)" onblur="dataCheckExist(this)"/>
	    </td>
	    <td>
	    	<button class="button2" id="tyjfb" name="tyjfb" onclick="addedCredit(this)">确定</button>
	    </td>
	  </tr>
	  <thead>
	    <tr><td colspan="4" align="center">人 文 素 质 加 分</td></tr>
	  </thead>
	  <tbody>
	   <tr>
	    <td colspan='4'>
	    	<select size="8" id="rwjf" name="rwjf" ondblclick="dblClickOnSelect(this)">
	    		<option>人文项目－－－－－－－－－－－－－－－－－－－－－－－－－－得分</option>
	    		<logic:iterate name="rwjfxmList" id="v">
	    			<option value="${v.rwjsdm}">${v.opt}</option>
	    		</logic:iterate>
	    	</select>
	    </td>
	  </tr>
	  <tr>  
	    <td colspan='2'>
	    	<html:select property="rwjsdm" styleId="zyjsdm" >
	    		<html:option value="所获的人文素质加分项目" />
	    		<html:options collection="rwxmList" property="rwjsdm" labelProperty="rwjsmc"/><!-- 人文项目列表 -->
	    	</html:select>
	    </td>
	    <td>
	    	<input type="text" id="rwjft" value="输入所加分值" onclick="dataCheckInit(this)" onblur="dataCheckExist(this)">
	    </td>
	    <td>
	    	<button class="button2" id="rwjfb" name="rwjfb" onclick="addedCredit(this)">确定</button>
	    </td>
	  </tr>
	  </tbody>
	  <thead>
	   <tr>
	     <td colspan="4" align="center">创 新 素 质 加 分</td>
	   </tr>
	  </thead>
	  <tbody>
	  <tr>
	    <td colspan='4'>
	    	<select size="8" id="cxjf" name="cxjf" ondblclick="dblClickOnSelect(this)">
	    		<option>创新项目－－－－－－－－－－－－－－－－－－－－－－－－－－得分</option>
	    		<logic:iterate name="cxjfxmList" id="v">
	    			<option value="${v.xmdm}">${v.opt}</option>
	    		</logic:iterate>
	    	</select>
	    </td>
	  </tr>
	  <tr>  
	    <td colspan='2'>
	    	<html:select property="xmdm" styleId="cxxmdm">
	    		<html:option value="所获的创新加分项目" />
	    		<html:options collection="cxxmList" property="xmdm" labelProperty="xmmc"/><!-- 创新项目列表 -->
	    	</html:select>
	    </td>
	    <td>
	    	<input type="text" id="cxjft" value="输入所加分值" onclick="dataCheckInit(this)" onblur="dataCheckExist(this)"/>
	    </td>
	    <td>
	    	<button class="button2" id="cxjfb" name="cxjfb" onclick="addedCredit(this)">确定</button>
	    </td>
	  </tr>
	  </tbody>
	</table>
	<center>
		<div class="buttontool">
			<button class="button2" onclick="getNextStuJfxx()">下一个</button>&nbsp;&nbsp;
			<button class="button2" onclick="window.close();return false;">关 闭</button>
		</div>
	</center>
	</html:form>
  </body>
</html:html>
