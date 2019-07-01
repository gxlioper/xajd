<%@ page language="java" pageEncoding="GB2312" contentType="text/html;charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title><bean:message key="lable.title" /></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	</head>
<style media="print" type="text/css">
.brk{
	page-break-after:always;
}
</style>
  <body> 
    <html:form action="specialprise.do" method="post" >
<center>
<h3><bean:write name="map" property="xxmc"/> <bean:write name="map" property="nd"/>���  <bean:write name="map" property="jxjmc"/> ��ѧ������ǼǱ�</h3>
<table width="90%" class="tbstyle">  
  <tr>
    <td width="110" align="center" height="35">ѧ��</td>
    <td align="center"><bean:write name="map" property="xh"/></td>
    <td width="50" align="center">����</td>
    <td width="110" align="center" ><bean:write name="map" property="xm"/></td>
    <td width="50" align="center">�Ա�</td>
    <td width="70" align="center"><bean:write name="map" property="xb"/></td>
    <td width="70" align="center">����</td>
    <td align="center"><bean:write name="map" property="mzmc"/></td>
    <td width="70" align="center">����Ա</td>
    <td width="70" align="center"><bean:write name="map" property="zzmmmc"/></td>
  </tr>
  <tr>
    <td align="center" height="35"><bean:message key="lable.xsgzyxpzxy" />��ϵ<br>
    רҵ���༶</td>
    <td colspan="5"><bean:write name="map" property="xymc"/><bean:write name="map" property="zymc"/><br><bean:write name="map" property="bjmc"/></td>
    <td align="center">����ˮƽ</td>
    <td colspan="3"><bean:write name="map" property="wysp"/></td>
  </tr>
  <tr>
    <td align="center" height="35">����绰</td>
    <td colspan="5"></td>
    <td align="center">�ֻ�</td>
    <td colspan="3"><bean:write name="map" property="sjhm"/></td>
  </tr>
  <tr>
    <td rowspan="2" height="35"></td>
    <td colspan="2" rowspan="2" align="center">��ѧ��</td>
    <td colspan="2" rowspan="2" align="center">����<br>��<br>�Ÿ�</td>
    <td rowspan="2" align="center">�µȼ�</td>
    <td rowspan="2" align="center">���޿�<br>ƽ��<br>�ɼ�</td>
    <td colspan="2" align="center">ѧϰ�ɼ�����</td>
    <td rowspan="2" align="center">����<br>�ϸ�<br>��׼</td>
  </tr>
  <tr>
    <td width="6%" align="center" >�༶</td>
    <td width="6%" align="center">רҵ</td>
  </tr>
  <tr>
    <td align="center" height="35">��һѧ��</td>
    <td colspan="2"><bean:write name='map' property="jxj1" /></td>
    <td colspan="2"><bean:write name='map' property="shyg1" /></td>
    <td><bean:write name='map' property="ddj1" /></td>
    <td><bean:write name='map' property="bxkpjcj1" /></td>
    <td><bean:write name='map' property="bjcjpx1" /></td>
    <td><bean:write name='map' property="zycjpx1" /></td>
    <td><bean:write name='map' property="tyhgbz1" /></td>
  </tr>
  <tr>
    <td align="center" height="35">�ڶ�ѧ��</td>
    <td colspan="2"><bean:write name='map' property="jxj2" /></td>
    <td colspan="2"><bean:write name='map' property="shyg2" /></td>
    <td><bean:write name='map' property="ddj2" /></td>
    <td><bean:write name='map' property="bxkpjcj2" /></td>
    <td><bean:write name='map' property="bjcjpx2" /></td>
    <td><bean:write name='map' property="zycjpx2" /></td>
    <td><bean:write name='map' property="tyhgbz2" /></td>
  </tr>
  <tr>
    <td align="center" height="35">����ѧ��</td>
    <td colspan="2"><bean:write name='map' property="jxj3" /></td>
    <td colspan="2"><bean:write name='map' property="shyg3" /></td>
    <td><bean:write name='map' property="ddj3" /></td>
    <td><bean:write name='map' property="bxkpjcj3" /></td>
    <td><bean:write name='map' property="bjcjpx3" /></td>
    <td><bean:write name='map' property="zycjpx3" /></td>
    <td><bean:write name='map' property="tyhgbz3" /></td>
  </tr>
  <tr>
    <td align="center" height="35">����ѧ��</td>
    <td colspan="2"><bean:write name='map' property="jxj4" /></td>
    <td colspan="2"><bean:write name='map' property="shyg4" /></td>
    <td><bean:write name='map' property="ddj4" /></td>
    <td><bean:write name='map' property="bxkpjcj4" /></td>
    <td><bean:write name='map' property="bjcjpx4" /></td>
    <td><bean:write name='map' property="zycjpx4" /></td>
    <td><bean:write name='map' property="tyhgbz4" /></td>
  </tr>
  <tr>
    <td height="35" align="right">��Ṥ�����<br>(����ְ��):</td>
    <td colspan="4"><bean:write name="map" property="drzw"/></td>
    <td colspan="3">רҵ�꼶��������<br> ��</td>
    <td colspan="2"><bean:write name='map' property="zyrsNum" /></td>
  </tr>
  <tr>
    <td height="570">����������</td>
    <td colspan="9">&nbsp;<bean:write name="map" property="xxjl"/></td>
  </tr>
</table>
<div class="brk"></div>
<table width="90%" class="tbstyle">
       <tr>
         <td width="3%" height="156"><bean:message key="lable.xsgzyxpzxy" />ͨ������</td>
         <td width="731">&nbsp;<bean:write name="map" property="xyshyj"/></td>
       </tr >
       <tr>
         <td height="157">�����Ρ�����Ա��ʦ�Ƽ����</td>
         <td height="157" align="right" valign="bottom"><bean:write name="map" property="fdyyj"/><p>ǩ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <p>��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��</p></td>
       </tr>
       <tr>
         <td height="315"><bean:message key="lable.xsgzyxpzxy" />��ѧ������ίԱ�����</td>
         <td height="315" align="right" valign="bottom"><bean:write name="map" property="xypswyhyj"/><p>����ίԱ������ǩ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <p>��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��</p></td>
       </tr >
       <tr>
         <td height="315">ѧУ���</td>
         <td height="315" align="right" valign="bottom"><bean:write name="map" property="xxshyj"/><p>ѧУ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <p>��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��</p></td>
       </tr >
</table>
</center>
</html:form>
</body>
</html:html>
