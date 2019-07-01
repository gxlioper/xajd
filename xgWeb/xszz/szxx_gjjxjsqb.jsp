<%@ page language="java" contentType="text/html;charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html >
<base target="_self" >
  <head>
    <title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
  </head>
<%
   response.setHeader("Pragma","no-cache");
   response.setHeader("Cache-Control","no-cache");
   response.setDateHeader("Expires", 0);
 %>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
  <body>
    <html:form action="gjjzxjsqb.do" method="post">
    		<input type="hidden" name="tableName" id="tableName" value="<bean:write name="sqlb" />"/>
			<p align="center" style="font-size:24px"><bean:write name="sqlb" />�����</p>
			<table width="100%" class="tbstyle" id="theTable">
			  <tr>
			    <td width="46" rowspan="4"><p align="center">��</p>
			        <p align="center">��</p>
			        <p align="center">��</p>
			        <p align="center">��</p></td>
			    <td width="58" height="25" nowrap><p align="center">����</p></td>
			    <td height="25"><div align="center"><bean:write name="rs" property="xm" />
			    </div></td>
			    <td width="134" height="25"><p align="center">�Ա� </p></td>
			    <td width="44" height="25"><bean:write name="rs" property="xb" /></td>
			    <td height="25"><p align="center">��������</p></td>
			    <td height="25"><bean:write name="rs" property="csny" /></td>
			    <td width="62" height="25"><p align="center">�� ��</p></td>
			    <td width="65" height="25" align="center"><bean:write name="rs" property="mzmc" /></td>
			  </tr>
			  <tr>
			    <td height="25"><p align="center">ѧ��</p></td>
			    <td height="25" colspan="3"><bean:write name="rs" property="xh" /></td>
			    <td height="25"><p align="center">��ѧʱ�� </p></td>
			    <td height="25" colspan="3" align="center"><bean:write name="rs" property="rxny" /></td>
			  </tr>
			  <tr>
			    <td height="28" colspan="8" nowrap><p align="center">&nbsp;<bean:write name="rs" property="xxmc" />&nbsp;��ѧ&nbsp;<bean:write name="rs" property="xy" />&nbsp;<bean:message key="lable.xsgzyxpzxy" />&nbsp;<bean:write name="rs" property="xmc" />&nbsp;ϵ&nbsp;<bean:write name="rs" property="bjmc" />&nbsp;�� </p></td>
			  </tr>
			  <tr>
			    <td width="58" height="3%"><p align="center">�������</p>
			      <p align="center">�� ��</p></td>
			    <td height="3%" colspan="7"><bean:write name="rs" property="jlxx" /></td>
			  </tr>
			  <tr>
			    <td width="46" rowspan="3"><p align="center">��</p>
			        <p align="center">ͥ</p>
			        <p align="center">��</p>
			        <p align="center">��</p>
			        <p align="center">��</p>
			        <p align="center">��</p></td>
			    <td width="58" height="66"><p align="center">��ͥ���� </p></td>
			    <td height="66" colspan="4"><p align="center">A������&nbsp;&nbsp;&nbsp;&nbsp;B��ũ�� </p></td>
			    <td width="107" height="66"><p align="center">��ͥ�˿����� </p></td>
			    <td height="66" colspan="2"><div align="center"><bean:write name="rs" property="hkrs" />
			    </div></td>
			  </tr>
			  <tr>
			    <td width="58" height="43"><p align="center">��ͥ��������</p></td>
			    <td width="120" height="43"><bean:write name="rs" property="jtyzsr" /></td>
			    <td height="43" colspan="2"><p align="center">�˾�������</p></td>
			    <td width="109" height="43"><bean:write name="rs" property="jtrjsr" /></td>
			    <td width="107" height="43" align="center">������Դ</td>
			    <td height="43" colspan="2"><div align="center"><bean:write name="rs" property="jtsrly" /></div></td>
			  </tr>
			  <tr>
			    <td width="58" height="55"><p align="center">��ͥסַ </p></td>
			    <td height="55" colspan="4"><div align="center"><bean:write name="rs" property="jtzz" /></div></td>
			    <td width="107" height="55"><p align="center">�������� </p></td>
			    <td height="55" colspan="2"><div align="center"><bean:write name="rs" property="yzbm" /></div></td>
			  </tr>
			  <tr>
			    <td width="46" rowspan="6"><p align="center">��</p>
			        <p align="center">ͥ</p>
			        <p align="center">��</p>
			        <p align="center">Ա</p>
			        <p align="center">��</p>
			        <p align="center">��</p></td>
			    <td width="58" height="20"><p align="center">���� </p></td>
			    <td width="120" height="30"><p align="center">���� </p></td>
			    <td height="30" colspan="2"><p align="center">�뱾�˹�ϵ </p></td>
			    <td height="30" colspan="4"><p align="center">������ѧϰ��λ </p></td>
			  </tr>
			  <tr>
			    <td width="58" height="30"><div align="center"><bean:write name="rs" property="jtcy1_xm" /></div></td>
			    <td width="120" height="30"><div align="center">&nbsp;<bean:write name="rs" property="jtcy1_nl" />&nbsp;</div></td>
			    <td height="30" colspan="2"><div align="center"><bean:write name="rs" property="jtcy1_gx" /></div></td>
			    <td width="109" height="30" colspan="4"><div align="center"><bean:write name="rs" property="jtcy1_gzdz" /></div></td>
			  </tr>
			  <tr>
			    <td width="58" height="30"><div align="center"><bean:write name="rs" property="jtcy2_xm" /></div></td>
			    <td width="120" height="30"><div align="center">&nbsp;<bean:write name="rs" property="jtcy2_nl" />&nbsp;</div></td>
			    <td height="30" colspan="2"><div align="center"><bean:write name="rs" property="jtcy2_gx" /></div></td>
			    <td width="109" height="30" colspan="4"><div align="center"><bean:write name="rs" property="jtcy2_gzdz" /></div></td>
			  </tr>
			  <tr>
			    <td width="58" height="30"><div align="center"><bean:write name="rs" property="jtcy3_xm" /></div></td>
			    <td width="120" height="30"><div align="center">&nbsp;<bean:write name="rs" property="jtcy3_nl" />&nbsp;</div></td>
			    <td height="30" colspan="2"><div align="center"><bean:write name="rs" property="jtcy3_gx" /></div></td>
			    <td width="109" height="30" colspan="4"><div align="center"><bean:write name="rs" property="jtcy3_gzdz" /></div></td>
			  </tr>
			  <tr>
			    <td width="58" height="30"><div align="center"><bean:write name="rs" property="jtcy4_xm" /></div></td>
			    <td width="120" height="30"><div align="center">&nbsp;<bean:write name="rs" property="jtcy4_nl" />&nbsp;</div></td>
			    <td height="30" colspan="2"><div align="center"><bean:write name="rs" property="jtcy4_gx" /></div></td>
			    <td width="109" height="30" colspan="4"><div align="center"><bean:write name="rs" property="jtcy4_gzdz" /></div></td>
			  </tr>
			  <tr>
			    <td width="58" height="30"><div align="center"><bean:write name="rs" property="jtcy5_xm" /></div></td>
			    <td width="120" height="30"><div align="center">&nbsp;<bean:write name="rs" property="jtcy5_nl" />&nbsp;</div></td>
			    <td height="30" colspan="2"><div align="center"><bean:write name="rs" property="jtcy5_gx" /></div></td>
			    <td width="109" height="30" colspan="4"><div align="center"><bean:write name="rs" property="jtcy5_gzdz" /></div></td>
			  </tr>
			  <tr>
			    <td height="60" colspan="9" valign="top"><p>�������ɣ� </p>
			        <bean:write name="rs" property="sqly" />  
			        <p>  
			        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			        ������ǩ���� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;�� </p></td>
			  </tr>
			  <tr>
			    <td height="89" colspan="9" valign="top"><p ><bean:message key="lable.xsgzyxpzxy" />�������� </p>
			        <bean:write name="rs" property="xyshyj" />  
			        <p>
			        <br><br>
			      <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			      ��ί�����ǩ���� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;�� </p></td>
			  </tr>
			  <tr>
			    <td colspan="9" valign="top"><p>ѧУ�������� </p>
			        <bean:write name="rs" property="xxshyj" />   
			        <p>  
			        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			        �����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;�� </p></td>
			  </tr>
			</table>
    </html:form>
	<div align=center>
		<input  value="��ӡ" name="button_print" onclick="expTab('theTable','���ҽ�ѧ�������')" />
	</div>    
  </body>
</html:html>
