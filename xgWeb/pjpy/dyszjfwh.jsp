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
  <script>
  	function save(){
  		var xn = document.getElementById("xn").value;
  		var xq = document.getElementById("xq").value;
  		if(xn==""){
  			alert('�뽫��\*�ŵ���Ŀ��д������');
  			return false;
  		}
  		if(xq == ""){
  			alert('�뽫��\*�ŵ���Ŀ��д������');
  			return false;
  		}
  		document.forms[0].action='dyszwh.do?doType=add&xh='+document.getElementById('xh').innerText;
  		document.forms[0].submit();
  	}
  </script>
  <body onload="getCurrentRowNum()">
  
  <html:form action="/dyszwh">
  <logic:present name="doresult">
  	<logic:equal value="true" name="doresult">
  	   <script>
  	      alert("����ɹ���");
  	   </script>
  	</logic:equal>
  	<logic:equal value="false" name="doresult">
  	   <script>
  	      alert("����ʧ�ܣ�");
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
        <tr><td colspan="4" align="center">�����Ӽ���</td></tr>
      </thead>
      <tbody>
	  <tr>
	    <td width="17%"><div align="center"><font color=red>*</font>ѧ�ţ�</div></td>
	    <td width="33%" id="xh"><bean:write name="rs" property="xh" /></td>
	    <td width="14%" ><div align="center"><font color=red>*</font>������</div></td>
	    <td width="36%" id="xm"><bean:write name="rs" property="xm" /></td>
	  </tr>
	  <tr>
	  	<td align=center><font color=red>*</font>ѧ�꣺</td>
	    <td>
	       <html:select name="rs" property="xn" style="width:92%" styleId="xn">
	           <html:options collection="xnList" property="xn" labelProperty="xn" />
	       </html:select>
	    </td>
	    <td align=center><font color=red>*</font>ѧ�ڣ�</td>
	    <td>
		    <html:select name="rs" property="xq" style="width:90%" styleId="xq">
		    	   <html:option value=""></html:option>
		           <html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
		    </html:select>
	    </td>
	  </tr>
	  <tr>
	    <td><div align="center">�����ӷ֣�</div></td>
	    <td><html:text name="rs" property="ryjf" styleId="ryjf"/></td>
	    <td><div align="center">�ӷ�ԭ��</div></td>
	    <td>
	      <html:select property="rychdm" name="rs" styleId="rychdm">
	         <html:option value=""></html:option>
	         <html:options collection="rychList" property="rychdm" labelProperty="rychmc"/>
	      </html:select>
	    </td>
	  </tr>
	  <tr>	    
	    <td><div align="center">��ְ�ӷ֣�</div></td>
	    <td><html:text name="rs" property="rzjf" styleId="rzjf"/></td>
	    <td><div align="center">����ְ��</div></td>
	    <td><html:text name="rs" property="rzlb" styleId="rzlb"/></td>
	  </tr>
	  <tr>
	    <td><div align="center">�����ӷ֣�</div></td>
	    <td><html:text name="rs" property="bsjf" styleId="bsjf"/></td>
	    <td><div align="center">������Ŀ��</div></td>
	    <td>
	      <html:select property="bsdm" name="rs" styleId="bsdm">
	         <html:option value=""></html:option>
	         <html:options collection="bsList" property="bsdm" labelProperty="bsmc"/>
	      </html:select>
	    </td>
	  </tr>
	  <tr>	    
	    <td><div align="center">�������ȼӷ֣�</div></td>
	    <td><html:text name="rs" property="qsbsjf" styleId="qsbsjf"/></td>
	    <td><div align="center">�������ȣ�</div></td>
	    <td>
	      <html:select property="mcdm" name="rs" styleId="mcdm">
	         <html:option value=""></html:option>
	         <html:options collection="qsPbList" property="mcdm" labelProperty="mcmc"/>
	      </html:select>
	    </td>
	  </tr>
	  <tr>
	    <td><div align="center">�μӻ�ӷ֣�</div></td>
	    <td><html:text name="rs" property="cjhdjf" styleId="cjhdjf"/></td>
	    <td><div align="center">�μӻ���ƣ�</div></td>
	    <td>
	      <html:select property="cjhddm" name="rs" styleId="cjhddm">
	         <html:option value=""></html:option>
	         <html:options collection="hdList" property="cjhddm" labelProperty="cjhdmc"/>
	      </html:select>
	    </td>
	  </tr>
	  <tr>	    
	    <td><div align="center">Υ�Ϳ۷֣�</div></td>
	    <td><html:text name="rs" property="wjkf" styleId="wjkf"/></td>
	    <td><div align="center">Υ�Ϳ۷�ԭ��</div></td>
	    <td>
	      <html:select property="cfdm" name="rs" styleId="cfdm">
	         <html:option value=""></html:option>
	         <html:options collection="wjcfList" property="cfdm" labelProperty="cfmc"/>
	      </html:select>
	    </td>
	  </tr>
	  <tr>
	    <td><div align="center">����ͨ��������</div></td>
	    <td><html:text name="rs" property="jttbkf" styleId="jttbkf"/></td>
	    <td><div align="center">����ͨ��ԭ��</div></td>
	    <td><html:text name="rs" property="jttbyy" styleId="jttbyy"/></td>
	  </tr>
	  <tr>	    
	    <td><div align="center">�����Ӽ��֣�</div></td>
	    <td><html:text name="rs" property="qtjjfxx" styleId="qtjjfxx"/></td>
	    <td><div align="center">�Ӽ���ԭ��</div></td>
	    <td><html:text name="rs" property="qtjjfyy" styleId="qtjjfyy"/></td>
	  </tr>
	  </tbody>
	  <thead>
	    <tr><td colspan="4" align="center">�����Ӽ���</td></tr>
	  </thead>
	  <tbody>
	   <tr>
	    <td><div align="center">�񽱼ӷ�</div></td>
	    <td><html:text name="rs" property="hjjf" styleId="hjjf"/></td>
	    <td><div align="center">�����</div></td>
	    <td>
	      <html:select property="zyjsdm" name="rs" styleId="zyjsdm">
	         <html:option value=""></html:option>
	         <html:options collection="zyjsList" property="jsdm" labelProperty="jsmc"/>
	      </html:select>
	    </td>
	  </tr>
	  <tr>
	  	<td><div align="center">�����ӷ֣�</div></td>
	    <td><html:text name="rs" property="fkjf" styleId="fkjf"/></td>
	    <td><div align="center">���������:</div></td>
	    <td><html:text name="rs" property="zyfkjfyy" styleId="zyfkjfyy"/></td>	    
	  </tr>
	  <tr>
	    <td><div align="center">�γ̳ɼ�������۷֣�</div></td>
	    <td><html:text name="rs" property="kccjbjgkf" styleId="kccjbjgkf"/></td>
	    <td><div align="center"></div></td>
	    <td></td>
	  </tr>
	  <tr>	    
	    <td><div align="center">���������۷֣�</div></td>
	    <td><html:text name="rs" property="qtzykf" styleId="qtzykf"/></td>
	    <td><div align="center">���������۷�ԭ��</div></td>
	    <td><html:text name="rs" property="zykfyy" styleId="zykfyy"/></td>
	  </tr>
	  </tbody>
	  <thead>
	   <tr>
	     <td colspan="4" align="center">�����ɼ��Ӽ���</td>
	   </tr>
	  </thead>
	  <tbody>
	  <tr>
	    <td><div align="center">���������ӷ֣�</div></td>
	    <td><html:text name="rs" property="tybsjf" styleId="tybsjf"/></td>
	    <td><div align="center">���μӵı�����</div></td>
	    <td>
	      <html:select property="tyjsdm" name="rs" styleId="tyjsdm">
	         <html:option value=""></html:option>
	         <html:options collection="tyjsList" property="jsdm" labelProperty="jsmc"/>
	      </html:select>
	    </td>
	  </tr>
	  </tbody>
	  <thead>
	    <tr><td colspan="4" align="center">���ܼӷ�</td></tr>
	  </thead>
	  <tbody>
	  	<tr>
	    <td><div align="center">���ܼӷ֣�</div></td>
	    <td><html:text name="rs" property="jnjf" styleId="jnjf"/></td>
	    <td><div align="center">���ܼӷ�ԭ��</div></td>
	    <td><html:text name="rs" property="jnjfyy" styleId="jnjfyy"/></td>
	    </tr>
	  </tbody>
	</table>
	<center>
		<div class="buttontool" id='btn' >
			<button class="button2" onclick="save();return false;">�� ��</button>&nbsp;&nbsp;
			<button class="button2" onclick="getNext()">��һ��</button>&nbsp;&nbsp;
			<button class="button2" onclick="window.close();return false;">�� ��</button>
		</div>
	</center>
	</html:form>
  </body>
</html:html>
