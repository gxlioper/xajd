<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    
    <title>wjcf_shgc_xswjcfqkb.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="Copyright" content="������� zfsoft" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style media='print'>
		.noPrin{display:none;}
	</style>
  </head>
  	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/shgc/td.css" type="text/css" media="all" />
		<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
  <body>
  		<script language="javascript" src="js/function.js"></script>
			<p align="center"><font size="5">ѧ��Υ�ʹ���Ԥ���</font></p>
			<div>
			<table width="96%" align="center" class="tbstyle">
  			<tr>
    			<td width="59" align="center">ѧ&nbsp;Ժ<br/></td>
    			<td width="140" align="center">&nbsp;<bean:write name="rs" property="xymc"/></td>
    			<td width="70" align="center">ר&nbsp;ҵ</td>
    			<td width="140" align="center">&nbsp;<bean:write name="rs" property="zymc"/></td>
    			<td width="64" align="center">��&nbsp;��</td>
    			<td width="102"align="center">&nbsp;<bean:write name="rs" property="bjmc"/></td>
    			
  			</tr>
  			<tr>
    			<td width="59" align="center">��&nbsp;��<br/></td>
    			<td width="140" align="center">&nbsp;<bean:write name="rs" property="xm"/></td>
    			<td width="70" align="center">��Уʱ��</td>
   				<td width="140" align="center">&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="sjxy"/>&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="sjxmon"/>&nbsp;&nbsp;&nbsp;��</td>
   				<td width="64" align="center">��&nbsp;��</td>
    			<td width="102" align="center">&nbsp;<bean:write name="rs" property="xb"/></td>
  			</tr>
  			<tr>
    			<td width="59"><p align="center">ѧ&nbsp;�� </p></td>
    			<td width="140" align="center" >&nbsp;<bean:write name="rs" property="xh"/></td>
    			<td width="70" align="center">��������<br/></td>
    			<td align="center" colspan="3" >&nbsp;&nbsp;<bean:write name="rs" property="scsy"/>&nbsp;��&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="scsm"/>&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="scsd"/>&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
  			</tr>
  			<tr>
    			<td width="450" colspan="6">&nbsp;Υ������:
    				<p align="">&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="jtwjsy"/> </p>
    				<p align="center">&nbsp; </p>
        			<p align="center">&nbsp; </p>
    			</td>
  			</tr>
  			<tr>
    			<td width="450" colspan="6">&nbsp;�ΰ���������������������:
    				<p align="">&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="zacfqk"/> </p>
    				<p align="">&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="zacfqk"/> </p>
    				<p align="center">&nbsp; </p>
    			</td>
  			</tr>
  			<!-- ������ְҵѧԺ -->
  			<logic:notEqual name="xxdm" value="11355">
  			<tr>
    			<td colspan="6">&nbsp;<bean:message key="lable.xb" />���:
    				<p align="">&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="bz"/> </p>
    				<p align="">&nbsp;</p>
    				<p align="right"><bean:message key="lable.xb" />�����쵼ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    				<p align="right"><bean:message key="lable.xb" />����:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    				<p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� </p>
    			</td>
  			</tr>
  			<tr>
    			<td colspan="6">&nbsp;ѧ�������:
    				<p align="center">&nbsp; </p>
    				<p align="right">�����ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    				<p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� </p>
    			</td>
  			</tr>
  			</logic:notEqual>
  			<logic:equal name="xxdm" value="11355">
  			<tr>
    			<td colspan="6">&nbsp;ϵ���:
    				<p align="center">&nbsp; </p>
    				<p align="right">�����ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    				<p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� </p>
    			</td>
  			</tr>
  			<tr>
    			<td colspan="6">&nbsp;����Ա���������:
    				<p align="center">&nbsp; </p>
    				<p align="right">�����ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    				<p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� </p>
    			</td>
  			</tr>
  			<tr>
    			<td colspan="6">&nbsp;ѧ�������:
    				<p align="center">&nbsp; </p>
    				<p align="right">�����ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    				<p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� </p>
    			</td>
  			</tr>
  			<tr>
    			<td colspan="6">&nbsp;<bean:message key="lable.xb" />���:
    				<p align="center">&nbsp; </p>
    				<p align="right">�����ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    				<p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� </p>
    			</td>
  			</tr>
  			<tr>
    			<td colspan="6">&nbsp;ѧ�������:
    				<p align="center">&nbsp; </p>
    				<p align="right">�����ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    				<p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� </p>
    			</td>
  			</tr>
  			<tr>
    			<td colspan="6">&nbsp;��ע:
    				<p align="center">&nbsp; </p>
    				<p align="right">�����ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    				<p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� </p>
    			</td>
  			</tr>
  			</logic:equal>
  			<tr>
  				<td colspan="6">&nbsp;<bean:message key="lable.xb" />��֮��ѧУ���ͬ����⴦��������:
    				<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>�Ѹ�֪����ѧУ�⴦������������ڽӵ��⴦�����֮�����3���������ڣ����������硣</b></p>
    				<p align="right">��֮��ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    				<p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� </p>
    				<p align="right">ѧ��ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    				<p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� </p>
    			</td>
  			</tr>
		</table>
		</div>
		<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ע����Υ��ѧ����飨A4ֽ���������븽�ον�ʦ������¼��ӡ��.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
  </body>
  <div class="noPrin" align="center">
			<input type='button' class='button2' value='ҳ������'
				onclick="WebBrowser.ExecWB(8,1);return false;">
			<input type='button' class='button2' value='��ӡԤ��'
				onclick="WebBrowser.ExecWB(7,1);return false;">
			<input type='button' class='button2' value='ֱ�Ӵ�ӡ'
				onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>
</html:html>
