<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title><bean:message key="lable.title" /></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<style type="text/css" >
		.td{
			font-size: 15px;	
		}
	</style>
	<style media='print'>
		.noPrin{display:none;}
	</style>
		<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>	

	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
  </head>
  <body>
    <html:form action="/wjcfhhgxywh">
      <div align="center" ><font style="font-size:22px;font:'����' "><b>${xxmc}ѧ��Υ�ʹ���Ԥ�ȸ�֪��</b></font><font style="font-size:17px; ">(<b>���</b>)</font></div>
      <br/>
      <div align="right">No.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;</div>
<br/>
<div>
<table width="95%" class="" align="center" border="0" cellpadding="0" cellspacing="0">
  <tr align="">
    <td height="40px" align="center" class="td" >
    	�⴦��ѧ��������<u style="width: 100px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	${rs.xm }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
    	�Ա�<u>&nbsp;&nbsp;&nbsp;&nbsp;${rs.xb }&nbsp;&nbsp;&nbsp;&nbsp;</u>
    	ѧ��<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.xh }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
    </td>
  </tr>
  <tr>
    <td height="40px" align="center" class="td">
    	ѧ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ժ<u style="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.xymc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
    	��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��<u style="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.bjmc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </td>
  </tr>
  <tr>
  	<td height="40px" align="center" class="td"> 
   	 	Υ�����<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.bz }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
   	 	����账��<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.cflbmc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��&nbsp;
	</td>
  </tr>
  <tr>
  	<td height="50px" align="center" class="td">
  		<br/>
  		<div align="right">
  			������<bean:message key="lable.xsgzyxpzxy" />ѧ������ίԱ�ᣨѧ�������£�
  		</div>
  		<br/>
  		<div align="right">
  			��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		</div>
  	</td>
  </tr>
  <tr>
  	<td height="40px" align="center" class="td">
  	  		<br/>
  		��ȡ��ǩ��<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	</td>
  </tr>
</table>
<br/>
<hr>
<br/>
<div align="center" style="font-size:22px;font:'���Ŀ���' "><b>${xxmc}ѧ��Υ�ʹ���Ԥ�ȸ�֪��</b></div>
      <br/>
      <div align="right">No.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;</div>
<br/>
<div>
<table width="95%" class="" align="center" border="0" cellpadding="0" cellspacing="0">
  <tr align="">
    <td height="40px" align="center" class="td" >
    	�⴦��ѧ��������<u style="width: 100px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	${rs.xm }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
    	�Ա�<u>&nbsp;&nbsp;&nbsp;&nbsp;${rs.xb }&nbsp;&nbsp;&nbsp;&nbsp;</u>
    	ѧ��<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.xh }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
    </td>
  </tr>
  <tr>
    <td height="40px" align="center" class="td">
    	ѧ&nbsp;&nbsp;&nbsp;&nbsp;Ժ<u style="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.xymc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
    	��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��<u style="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.bjmc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </td>
  </tr>
  <tr>
  	<td height="40px" align="center" class="td"> 
   	 	����ԭ�ɺ�����<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   	 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.bz }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
   	 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</td>
  </tr>
  <tr>
  	<td height="40px" align="center" class="td"> 
   	 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   	 	&nbsp;&nbsp;&nbsp;&nbsp;���ݡ�������<bean:message key="lable.xsgzyxpzxy" />ѧ��Υ�ʹ���ʵʩϸ�򡷵�<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>����<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��Ĺ涨��&nbsp;&nbsp;&nbsp;
	</td>
  </tr>
  <tr>
  	<td height="40px" align="center" class="td"> 
   	 	Ӧ����<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   	 	${rs.bz }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
   	 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   	 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</td>
  </tr>
  <tr>
  	<td height="40px" align="center" class="td"> 
   	 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��Դ������ʵ�����������飬�ڽӵ���֪ͨ�������������ڿ���
	</td>
  </tr>
  <tr>
  	<td height="40px" align="center" class="td"> 
   	 	�־���������ѧ������硣&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   	 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   	 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</td>
  </tr>
  <tr>
  	<td height="50px" align="center" class="td">
  		<br/>
  		<div align="right">
  			������<bean:message key="lable.xsgzyxpzxy" />ѧ������ίԱ�ᣨѧ�������£�
  		</div>
  		<br/>
  		<div align="right">
  			��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		</div>
  	</td>
  </tr>
  <tr>
  	<td height="40px" align="center" class="td">
  		<br/>
  		������ǩ��<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	</td>
  </tr>
  <tr>
  	<td height="40px" align="center" style="font: ����_GB2312 ;font-size: 13px">
  	  		<br/>
  		˵����1.������ǩ����ȡ�Ķ����뽫��֪�齻��������<bean:message key="lable.xsgzyxpzxy" />��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		&nbsp;&nbsp;&nbsp;&nbsp;<br/>
		     2.����������<bean:message key="lable.xsgzyxpzxy" />�뽫�ջصĸ�֪�鱣�汸�顣&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		&nbsp;&nbsp;&nbsp;&nbsp;
  		
  	</td>
  </tr>
</table>
<div align="center" class='noPrin'>
	<button type="button" class='button2'  onclick="WebBrowser.ExecWB(8,1);return false;">ҳ������</button>
    <button type="button" class='button2'  onclick="WebBrowser.ExecWB(7,1);return false;">��ӡԤ��</button>
    <button type="button" class='button2'  onclick="WebBrowser.ExecWB(6,6);return false;">ֱ�Ӵ�ӡ</button> 
    </div>
<!-- ע���˱�һʽ���ݣ�ϵ��Ժ����Ժѧ������һ�� -->
</div></div>
    </html:form>
  </body>
</html:html>
