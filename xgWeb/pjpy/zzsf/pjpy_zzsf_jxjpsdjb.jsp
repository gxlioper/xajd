<%@ page language="java" pageEncoding="GB2312" contentType="text/html;charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><head>
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

<html:html>
  <style media="print" type="text/css">
.brk{
	page-break-after:always;
}
</style>
  <body> 
    <html:form action="specialprise.do" method="post" >
<center>
<h3><bean:write name="map" property="xxmc"/> <bean:write name="map" property="xn"/>ѧ��<bean:write name="map" property="xq"/>ѧ�� <bean:write name="map" property="jxjmc"/>����ǼǱ�</h3>
<br>
<table width="90%" class="tbstyle">
  <tr>
    <td height="35" colspan="2" width="15%" align="center">ѧ��</td>
    <td width="89" align="center"><bean:write name="map" property="xh"/></td>
    <td width="50" align="center">����</td>
    <td colspan="2" align="center" ><bean:write name="map" property="xm"/></td>
    <td width="77" align="center">�Ա�</td>
    <td colspan="2" align="center"><bean:write name="map" property="xb"/></td>
    <td width="87" align="center">����</td>
    <td width="58" align="center"><bean:write name="map" property="mzmc"/></td>
    <td width="136" align="center">����Ա</td>
    <td colspan="2" width="173" align="center"><bean:write name="map" property="zzmmmc"/></td>
    </tr>
  <tr>
    <td height="35" colspan="2" align="center"><bean:message key="lable.xsgzyxpzxy" />��ϵ<br>
    רҵ���༶</td>
    <td colspan="7"><bean:write name="map" property="xymc"/><bean:write name="map" property="zymc"/><br><bean:write name="map" property="bjmc"/></td>
    <td align="center">����ˮƽ</td>
    <td colspan="4"><bean:write name="map" property="wysp"/></td>
  </tr>
  <tr>
    <td height="35" colspan="2" align="center">����绰</td>
    <td colspan="7"></td>
    <td align="center">�ֻ�</td>
    <td colspan="4"><bean:write name="map" property="sjhm"/></td>
  </tr>
  <tr>
    <td height="35" colspan="2" align="right">����ְ��:</td>
    <td colspan="2"><bean:write name="map" property="drzw"/></td>
    <td colspan="5">רҵ�꼶��������</td>
    <td colspan="2" height="35" align="right"><br><bean:write name='map' property="zyrsNum" /> ��</td>
    <td colspan="3"></td>
  </tr>
  <tr>
    <td width="25" height="200" rowspan="7"><p>��</p>
      <p>��</p>
      <p>ѧ</p>
      <p>ϰ</p>
      <p>��</p>
      <p>��</p></td>
    <td width="91">&nbsp;</td>
    <td colspan="7"><div align="center">ѧϰ���</div></td>
    <td colspan="5"><div align="center">�ۺϿ������</div></td>
    </tr>
  <tr>
    <td width="91">&nbsp;</td>
	<td height="18" colspan="1" rowspan="2"><div align="center">ѧϰ�ɼ�</div></td>
    <td height="18" colspan="6"><div align="center">����</div></td>
    <td colspan="2" rowspan="2"><div align="center">�ۺϿ����ɼ�</div></td>
    <td colspan="3"><div align="center">����</div></td>
    </tr>
  <tr>
    <td width="91">&nbsp;</td>
    <td height="20" colspan="3"><div align="center">�༶</div></td>
    <td height="20" colspan="3"><div align="center">רҵ</div></td>
    <td colspan="2" width="100px"><div align="center">�༶</div></td>
    <td width="100px"><div align="center">רҵ</div></td>
    </tr>
  
  <tr>
    <td width="91"><div align="center">��һѧ��</div></td>
    <td></td>
    <td height="20" colspan="3"><bean:write name="map" property="bjcjpx1"/></td>
    <td height="20" colspan="3"><bean:write name="map" property="zycjpx1"/></td>
    <td colspan="2"><bean:write name="map" property="zhkpzf1"/></td>
    <td colspan="2"><bean:write name="map" property="zhkpbjpx1"/></td>
    <td><bean:write name="map" property="zhkpzypx1"/></td>
    </tr>
  <tr>
  
    <td width="91"><div align="center">�ڶ�ѧ��</div></td>
    <td></td>
    <td height="19" colspan="3"><bean:write name="map" property="bjcjpx2"/></td>
    <td height="19" colspan="3"><bean:write name="map" property="zycjpx2"/></td>
    <td colspan="2"><bean:write name="map" property="zhkpzf2"/></td>
    <td colspan="2"><bean:write name="map" property="zhkpbjpx2"/></td>
    <td><bean:write name="map" property="zhkpzypx2"/></td>
    </tr>
  <tr>
  
    <td width="91"><div align="center">����ѧ��</div></td>
    <td></td>
    <td height="23" colspan="3"><bean:write name="map" property="bjcjpx3"/></td>
    <td height="23" colspan="3"><bean:write name="map" property="zycjpx3"/></td>
    <td colspan="2"><bean:write name="map" property="zhkpzf3"/></td>
    <td colspan="2"><bean:write name="map" property="zhkpbjpx3"/></td>
    <td><bean:write name="map" property="zhkpzypx3"/></td>
    </tr>
  <tr>
 
    <td width="91"><div align="center">����ѧ��</div></td>
    <td></td>
    <td height="23" colspan="3"><bean:write name="map" property="bjcjpx4"/></td>
    <td height="23" colspan="3"><bean:write name="map" property="zycjpx4"/></td>
    <td colspan="2"><bean:write name="map" property="zhkpzf4"/></td>
    <td colspan="2"><bean:write name="map" property="zhkpbjpx4"/></td>
    <td><bean:write name="map" property="zhkpzypx4"/></td>
    </tr>
    <logic:present name="isAHJG">
    <tr>
    
	    <td width="91"><div align="center">����ѧ��</div></td>
	    <td></td>
	    <td height="23" colspan="3"><bean:write name="map" property="bjcjpx5"/></td>
	    <td height="23" colspan="3"><bean:write name="map" property="zycjpx5"/></td>
	    <td colspan="2"><bean:write name="map" property="zhkpzf5"/></td>
	    <td colspan="2"><bean:write name="map" property="zhkpbjpx5"/></td>
	    <td><bean:write name="map" property="zhkpzypx5"/></td>
    </tr>
    </logic:present>
  <tr>
    <td height="105" colspan="2"><div align="center">��Ҫ�ɼ�</div></td>
    <td colspan="12"><bean:write name="map" property="xxjl"/></td>
  </tr>
  <tr>
    <td height="119" colspan="2"><div align="center">��������</div></td>
    <td colspan="12">&nbsp;<bean:write name="map" property="sqly"/></td>
  </tr>
</table>
<!--<div class="brk"></div>-->
<table width="90%" class="tbstyle">
       <tr>
         <td width="15%" height="107">
          <p align="center">ϵ���</p></td>
         <td width="775" height="107" align="right" valign="bottom"><bean:write name="map" property="fdyyj"/><p>ǩ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <p>��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��</p></td>
       </tr>
       <tr>
         <td height="133"><div align="center">ѧУ���</div></td>
         <td height="133" align="right" valign="bottom"><bean:write name="map" property="xxshyj"/><p>ѧУ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <p>��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��</p></td>
       </tr >
</table>
</center>
</html:form>
</body>
</html:html>
