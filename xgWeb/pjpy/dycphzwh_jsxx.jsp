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
  <script language="javascript" src="/xgxt/dwr/interface/getStuDyInfo.js"></script>		
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
      <thead>
        <tr><td colspan="4" align="center">平 时 表 现  60分</td></tr>
      </thead>
      <tbody>
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
	    <td align=center><font color=red>*</font>学期：</td>
	    <td>
		    <html:select name="rs" property="xq" style="width:90%" styleId="xq">
		    	   <html:option value=""></html:option>
		           <html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
		    </html:select>
	    </td>
	  </tr>
	  <tr>
	    <td><div align="center">集体活动：</div></td>
	    <td><html:text name="rs" property="jthdbx" styleId="jthdbx" onblur="countHj()"/></td>
	    <td><div align="center">上课学习：</div></td>
	    <td><html:text name="rs" property="skxxbx" styleId="skxxbx" onblur="countHj()"/></td>
	  </tr>
	  <tr>
	    <td><div align="center">劳动卫生：</div></td>
	    <td><html:text name="rs" property="ldwsbx" styleId="ldwsbx" onblur="countHj()"/></td>
	    <td><div align="center">校纪校规：</div></td>
	    <td><html:text name="rs" property="xjxgbx" styleId="xjxgbx" onblur="countHj()"/></td>
	  </tr>
	  <tr>
	    <td><div align="center">其它：</div></td>
	    <td><html:text name="rs" property="qtbx" styleId="qtbx" onblur="countHj()"/></td>
	    <td><div align="center">合计：</div></td>
	    <td><html:text name="rs" property="psbxhj" styleId="psbxhj"/></td>
	  </tr>
	  </tbody>
	  <thead>
	    <tr><td colspan="4" align="center">综 合 评 价  40分</td></tr>
	  </thead>
	  <tbody>
	   <tr>
	    <td><div align="center">政治学习</div></td>
	    <td><html:text name="rs" property="zzxx" styleId="zzxx" onblur="countHj()"/></td>
	    <td><div align="center">学习态度：</div></td>
	    <td><html:text name="rs" property="xxtd" styleId="xxtd" onblur="countHj()"/></td>
	  </tr>
	  <tr>
	    <td><div align="center">道德修养：</div></td>
	    <td><html:text name="rs" property="ddxy" styleId="ddxy" onblur="countHj()"/></td>
	    <td><div align="center">社会活动：</div></td>
	    <td><html:text name="rs" property="shhd" styleId="shhd" onblur="countHj()"/></td>
	  </tr>
	  <tr>
	    <td><div align="center">法制观念：</div></td>
	    <td><html:text name="rs" property="fzgn" styleId="fzgn" onblur="countHj()"/></td>
	    <td><div align="center">合计：</div></td>
	    <td><html:text name="rs" property="zhpjhj" styleId="zhpjhj" onblur="countHj()"/></td>
	  </tr>
	  </tbody>
	  <thead>
	   <tr>
	     <td colspan="4" align="center">附 加(扣) 分</td>
	   </tr>
	  </thead>
	  <tbody>
	  <tr>
	    <td><div align="center">干部任职：</div></td>
	    <td><html:text name="rs" property="gbrzjf" styleId="gbrzjf" onblur="countHj()"/></td>
	    <td><div align="center">校风学风：</div></td>
	    <td><html:text name="rs" property="xfxfjf" styleId="xfxfjf" onblur="countHj()"/></td>
	  </tr>
	  <tr>
	    <td><div align="center">积极上进：</div></td>
	    <td><html:text name="rs" property="jjsjjf" styleId="jjsjjf" onblur="countHj()"/></td>
	    <td><div align="center">各类比赛：</div></td>
	    <td><html:text name="rs" property="glbsjf" styleId="glbsjf" onblur="countHj()"/></td>
	  </tr>
	  <tr>
	    <td><div align="center">违纪处理：</div></td>
	    <td><html:text name="rs" property="wjcljf" styleId="wjcljf" onblur="countHj()"/></td>
	    <td><div align="center">合计：</div></td>
	    <td><html:text name="rs" property="fjfhj" styleId="fjfhj"/></td>
	  </tr>
	  <tr>
	    <td><div align="center"><font color="red">德育总分：</font></div></td>
	    <td><html:text name="rs" property="dyzf" styleId="dyzf" onblur="countHj()"/></td>
	    <td></td>
	    <td></td>
	  </tr>
	  </tbody>
	</table>
	<center>
		<div class="buttontool">
			<button class="button2" onclick="if(needData()&&dataFormat()){document.forms[0].action='dyszwh.do?doType=add&xh='+document.getElementById('xh').innerText;document.forms[0].submit();}">保 存</button>&nbsp;&nbsp;
			<button class="button2" onclick="getNext('jsxx')">下一个</button>&nbsp;&nbsp;
			<button class="button2" onclick="window.close();return false;">关 闭</button>
		</div>
	</center>
	</html:form>
  </body>
</html:html>
