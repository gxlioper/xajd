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
        <tr><td colspan="4" align="center">ƽ ʱ �� ��  60��</td></tr>
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
	    <td><div align="center">������</div></td>
	    <td><html:text name="rs" property="jthdbx" styleId="jthdbx" onblur="countHj()"/></td>
	    <td><div align="center">�Ͽ�ѧϰ��</div></td>
	    <td><html:text name="rs" property="skxxbx" styleId="skxxbx" onblur="countHj()"/></td>
	  </tr>
	  <tr>
	    <td><div align="center">�Ͷ�������</div></td>
	    <td><html:text name="rs" property="ldwsbx" styleId="ldwsbx" onblur="countHj()"/></td>
	    <td><div align="center">У��У�棺</div></td>
	    <td><html:text name="rs" property="xjxgbx" styleId="xjxgbx" onblur="countHj()"/></td>
	  </tr>
	  <tr>
	    <td><div align="center">������</div></td>
	    <td><html:text name="rs" property="qtbx" styleId="qtbx" onblur="countHj()"/></td>
	    <td><div align="center">�ϼƣ�</div></td>
	    <td><html:text name="rs" property="psbxhj" styleId="psbxhj"/></td>
	  </tr>
	  </tbody>
	  <thead>
	    <tr><td colspan="4" align="center">�� �� �� ��  40��</td></tr>
	  </thead>
	  <tbody>
	   <tr>
	    <td><div align="center">����ѧϰ</div></td>
	    <td><html:text name="rs" property="zzxx" styleId="zzxx" onblur="countHj()"/></td>
	    <td><div align="center">ѧϰ̬�ȣ�</div></td>
	    <td><html:text name="rs" property="xxtd" styleId="xxtd" onblur="countHj()"/></td>
	  </tr>
	  <tr>
	    <td><div align="center">����������</div></td>
	    <td><html:text name="rs" property="ddxy" styleId="ddxy" onblur="countHj()"/></td>
	    <td><div align="center">�����</div></td>
	    <td><html:text name="rs" property="shhd" styleId="shhd" onblur="countHj()"/></td>
	  </tr>
	  <tr>
	    <td><div align="center">���ƹ��</div></td>
	    <td><html:text name="rs" property="fzgn" styleId="fzgn" onblur="countHj()"/></td>
	    <td><div align="center">�ϼƣ�</div></td>
	    <td><html:text name="rs" property="zhpjhj" styleId="zhpjhj" onblur="countHj()"/></td>
	  </tr>
	  </tbody>
	  <thead>
	   <tr>
	     <td colspan="4" align="center">�� ��(��) ��</td>
	   </tr>
	  </thead>
	  <tbody>
	  <tr>
	    <td><div align="center">�ɲ���ְ��</div></td>
	    <td><html:text name="rs" property="gbrzjf" styleId="gbrzjf" onblur="countHj()"/></td>
	    <td><div align="center">У��ѧ�磺</div></td>
	    <td><html:text name="rs" property="xfxfjf" styleId="xfxfjf" onblur="countHj()"/></td>
	  </tr>
	  <tr>
	    <td><div align="center">�����Ͻ���</div></td>
	    <td><html:text name="rs" property="jjsjjf" styleId="jjsjjf" onblur="countHj()"/></td>
	    <td><div align="center">���������</div></td>
	    <td><html:text name="rs" property="glbsjf" styleId="glbsjf" onblur="countHj()"/></td>
	  </tr>
	  <tr>
	    <td><div align="center">Υ�ʹ���</div></td>
	    <td><html:text name="rs" property="wjcljf" styleId="wjcljf" onblur="countHj()"/></td>
	    <td><div align="center">�ϼƣ�</div></td>
	    <td><html:text name="rs" property="fjfhj" styleId="fjfhj"/></td>
	  </tr>
	  <tr>
	    <td><div align="center"><font color="red">�����ܷ֣�</font></div></td>
	    <td><html:text name="rs" property="dyzf" styleId="dyzf" onblur="countHj()"/></td>
	    <td></td>
	    <td></td>
	  </tr>
	  </tbody>
	</table>
	<center>
		<div class="buttontool">
			<button class="button2" onclick="if(needData()&&dataFormat()){document.forms[0].action='dyszwh.do?doType=add&xh='+document.getElementById('xh').innerText;document.forms[0].submit();}">�� ��</button>&nbsp;&nbsp;
			<button class="button2" onclick="getNext('jsxx')">��һ��</button>&nbsp;&nbsp;
			<button class="button2" onclick="window.close();return false;">�� ��</button>
		</div>
	</center>
	</html:form>
  </body>
</html:html>
