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
	<style media='print'>.noPrin{display:none}
	</style>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main2.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/commFunction.js"></script>
	<object id="WebBrowser" name="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	</head>
<style media="print" type="text/css">
.brk{
	page-break-after:always;
}
</style>
  <body> 
    <html:form action="specialprise.do" method="post" >
<center>
<h3>�Ϻ����̼�����ѧѧ���Էѳ�����ѧ�����Σ�������</h3>
<table width="100%" class="tbstyle" id="rsTable">
	<tr>
    <td align="left" height="35" width="80px"><bean:message key="lable.xsgzyxpzxy" />��ϵ</td>
    <td colspan="2">${rs.xymc }</td>
    <td align="center" height="35">רҵ</td>
    <td>${rs.zymc }</td>
    <td align="center" height="35" >�༶</td>
    <td colspan="2">${rs.bjmc }</td>   
  </tr>
  <tr>
    <td align="left" height="35">ѧ��</td>
    <td align="center" width="80">${rs.xh }</td>
    <td align="center">����</td>
    <td align="center" width="50">${rs.xm }</td>
    <td align="center">�Ա�</td>
    <td align="center">${rs.xb }</td>    
    <td align="center" >������ò</td>
    <td align="center" >${rs.zzmm }</td>    
  </tr>
 <tr>
 	<td align="left" height="35">��ͥסַ</td>
 	<td colspan="3">${rs.jtdz }</td>   
 	<td align="center">�绰</td>
 	<td colspan="3">${rs.lxdh }</td>   	
 </tr>
 <tr>
 	<td align="left" height="35">����ԭ��</td>
 	<td colspan="3">${rs.cgyy }</td>   
 	<td align="center">ȥ�ι��ң�ѧУ��</td>
 	<td colspan="3">${rs.qhgj }</td>   	
 </tr>
 <tr height="35">
 	<td align="left" width="80px"> ���õ���<br/>�������<br/>�����õ�<br/>���˹�ϵ��
	</td>
 	<td colspan="7" height="45">${rs.jjdbqk }</td> 
 </tr>
 <tr> 
         <td height="157" width="40%" align="left" valign="bottom" colspan="4"><bean:message key="lable.xsgzyxpzxy" />�������һ�� <p style="height:65">${rs.xyyj}</p>&nbsp;<p align="right">���ǡ��¡�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <p align="left">ǩ���ߣߣߣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><p align="right">��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��</p></td>
         <td height="157" width="40%" align="left" valign="bottom" colspan="4">ѧ��������������� <p style="height:65">${rs.xxyj}</p>&nbsp;<p align="right">���ǡ��¡�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <p align="left">ǩ���ߣߣߣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><p align="right">��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��</p></td>
       
 </tr>
 <tr>
 	<td height="157" align="left" valign="bottom" colspan="8">У�쵼�����������<br/><br/><br/><br/><br/>
         <p align="left">&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>&nbsp;</p><p align="right"><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p></td>
 </tr>
 <tr>
 <td colspan="8" align="left" >
 	<p align="left">��ע��*����������Ű���</p>
 </td>
 <tr>
	 <td colspan="8" align="left" >
	 	<p align="left"><b>˵����</b>1�����������ߣ�Ӧ��ѧУ�涨�ڽڼ��պ�У�����򰴿��δ���<br/>
&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;2��������ѧ�ߣ�Ӧ��ѧУ�涨������У����������������У����
<br/>&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;3���ύ�˱�ʱ���븽�ϱ���ǩ֤��ӡ���������ѧ¼ȡ֪ͨ�鸴ӡ����
</p>

	 </td>
 </tr>

</table>

</center>
</html:form>
			<div class='noPrin' align="center">
				<input type='button' class='button2' value='ҳ������' onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='��ӡԤ��' onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='ֱ�Ӵ�ӡ' onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>	
</body>
</html:html>
