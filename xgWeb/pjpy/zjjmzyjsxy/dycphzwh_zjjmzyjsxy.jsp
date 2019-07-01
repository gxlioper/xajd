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
  <script language="javascript" >	
  	function commit(){
  		if($("xn")){
			if($("xn").value == "" || $("xn").value == null){
				alert("带＊号字段不能为空");
				return false;
			}
		}
		if($("xq")){
			if($("xq").value == "" || $("xq").value == null){
				alert("带＊号字段不能为空");
				return false;
			}
		}
  		//if(dataFormat()){
  			document.forms[0].action='dyszwh.do?doType=add&xh='+document.getElementById('xh').innerText;
  			document.forms[0].submit();
  		//}
  	}
  	
  	function dataFormat(){
		if($("psbxhj").value.match(/^\d+\.{0,1}\d{0,3}$/)==null || $("psbxhj").value>60){
			alert("平时表现数据格式有误或已经超过最高分，请确认");
			return false;
		}else if($("zhpjhj").value.match(/^\d+\.{0,1}\d{0,3}$/)==null || $("zhpjhj").value>40){
			alert("综合评价数据格式有误或已经超过最高分，请确认");
			return false;
		}else if($("fjfhj").value.match(/^\d+\.{0,1}\d{0,3}$/)==null){
			alert("附加分数据格式有误，请确认");
			return false;
		}else{
			return true;
		}
  	}
  </script>
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
        <tr><td colspan="4" align="center">纪实考评</td></tr>
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
	    <td><div align="center">思想品德表现：</div></td>
	    <td><html:text name="rs" property="sxpdbx" styleId="sxpdbx"/></td>
	    <td><div align="center">荣誉称号加分：</div></td>
	    <td><html:text name="rs" property="ryjf" styleId="ryjf"/></td>
	  </tr>
	  <tr>
	    <td><div align="center">担任社会工作：</div></td>
	    <td><html:text name="rs" property="rzjf" styleId="rzjf"/></td>
	    <td><div align="center">其它减分：</div></td>
	    <td><html:text name="rs" property="qtjf" styleId="qtjf"/></td>
	  </tr>	 
	  </tbody>
	  <thead>
	    <tr><td colspan="4" align="center">智育考评</td></tr>
	  </thead>
	  <tbody>
	   <tr>
	    <td><div align="center">计算机类加分：</div></td>
	    <td><html:text name="rs" property="jsjjf" styleId="jsjjf"/></td>
	    <td><div align="center">英语等级加分：</div></td>
	    <td><html:text name="rs" property="yydjjf" styleId="yydjjf"/></td>
	  </tr>
	  <tr>
	    <td><div align="center">竞赛加分：</div></td>
	    <td><html:text name="rs" property="bsjf" styleId="bsjf"/></td>
	    <td><div align="center">发刊加分：</div></td>
	    <td><html:text name="rs" property="fkjf" styleId="fkjf"/></td>
	  </tr>
	  <tr>
	    <td><div align="center">自学考试加分：</div></td>
	    <td><html:text name="rs" property="zxksjf" styleId="zxksjf"/></td>
	    <td><div align="center">科研加分：</div></td>
	    <td><html:text name="rs" property="kyjf" styleId="kyjf"/></td>
	  </tr>
	   <tr>
	    <td><div align="center">补考重考减分：</div></td>
	    <td><html:text name="rs" property="kccjbjgkf" styleId="kccjbjgkf"/></td>
	    <td><div align="center">计算机类减分：</div></td>
	    <td><html:text name="rs" property="jsjjianf" styleId="jsjjianf"/></td>
	  </tr>
	  <tr>
	    <td><div align="center">英语等级减分：</div></td>
	    <td colspan="3"><html:text name="rs" property="yydjjianf" styleId="yydjjianf"/></td>	    
	  </tr>	 
	  </tbody>
	  <thead>
	   <tr>
	     <td colspan="4" align="center">体育考评</td>
	   </tr>
	  </thead>
	  <tbody>
	  <tr>
	    <td><div align="center">活动情况得分：</div></td>
	    <td><html:text name="rs" property="hdqk" styleId="hdqk"/></td>
	    <td><div align="center">锻炼情况：</div></td>
	    <td><html:text name="rs" property="dlqk" styleId="dlqk"/></td>
	  </tr>
	  <tr>
	    <td><div align="center">身体素质：</div></td>
	    <td><html:text name="rs" property="stsz" styleId="stsz"/></td>
	    <td><div align="center">晨跑出勤：</div></td>
	    <td><html:text name="rs" property="cpcq" styleId="cpcq"/></td>
	  </tr>
	  <tr>
	    <td><div align="center">比赛加分：</div></td>
	    <td><html:text name="rs" property="tybsjf" styleId="tybsjf"/></td>
	    <td><div align="center">破纪录加分：</div></td>
	    <td><html:text name="rs" property="pjljf" styleId="pjljf"/></td>
	  </tr>	  
	  </tbody>
	</table>
	<center>
		<div class="buttontool">
			<button class="button2" onclick="commit()">保 存</button>&nbsp;&nbsp;
			<button class="button2" onclick="getNext('zjjmzyjsxy')">下一个</button>&nbsp;&nbsp;
			<button class="button2" onclick="window.close();return false;">关 闭</button>
		</div>
	</center>
	</html:form>
  </body>
</html:html>
