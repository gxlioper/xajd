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
    <html:form action="/pjpynblgwh">
      <div align="center" style="font-size:18px;font:'����' ">${xxmc}${xn }ѧ�������༶�걨��</div>
		<br/>
	  <div>
	  <div align="center">
	  	&nbsp;&nbsp;��Ժ<u>&nbsp;&nbsp;${rs.xymc }&nbsp;&nbsp;</u>
	  	&nbsp;&nbsp;רҵ�༶<u>&nbsp;&nbsp;${rs.zymc }${rs.bjmc }&nbsp;&nbsp;</u>
	  	&nbsp;&nbsp;�༶����<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
	  </div>
<table width="100%" class="printstyle">
  <tr>
    <td colspan="2" align="center" width="30%">
    	��&nbsp;&nbsp;&nbsp;&nbsp;Ŀ
    </td>
    <td align="center" width="70%">
    	��&nbsp;&nbsp;Ҫ&nbsp;&nbsp;��&nbsp;&nbsp;��
    </td>
  </tr>
  <tr>
  	<td align="center" width="10%">
  		˼��<br/>����<br/>����
  	</td>
  	<td align="center" width="20%">
  		�����뵳ѧ��������<br/>���ӡ���Ա��������<br/>��ѧϰС����������<br/>��־Ը������
  	</td>
  	<td width="70%">
  		&nbsp;
  	</td>
  </tr>
  <tr>
  	<td align="center" width="10%" rowspan="3">
  		���<br/>����<br/>���
  	</td>
  	<td align="center" width="20%">
  		�༶����������
  	</td>
  	<td width="70%">
  		&nbsp;
  	</td>
  </tr>
  <tr>
  	<td align="center" width="20%">
  		��Ὺչ���
  	</td>
  	<td width="70%">
  		&nbsp;
  	</td>
  </tr>
  <tr>
  	<td align="center" width="20%">
  		ʦ����ͬѧ��ϵ
  	</td>
  	<td width="70%">
  		&nbsp;
  	</td>
  </tr>
  <tr>
  	<td align="center" width="10%">
  		ѧ��<br/>����<br/>���
  	</td>
  	<td align="center" width="20%">
  		��ѧ���������š�Ӣ<br/>��ȼ����ԡ������<br/>�ȼ����ԡ�����ѧ��<br/>���С����ޡ�������<br/>�������
  	</td>
  	<td width="70%">
  		&nbsp;
  	</td>
  </tr>
  <tr>
  	<td align="center" width="10%">
  		���<br/>�ط�
  	</td>
  	<td align="center" width="20%">
  		����ѧУ�Ĺ�����<br/>�ȡ���Ϊ�淶��
  	</td>
  	<td width="70%">
  		&nbsp;
  	</td>
  </tr>
  <tr>
  	<td align="center" width="10%">
  		�༶<br/>��ɫ
  	</td>
  	<td align="center" width="20%">
  		�༶�����Ĵ��¾ٴ�
  	</td>
  	<td width="70%">
  		&nbsp;
  	</td>
  </tr>
  <tr>
  <td colspan="3">
    �༶С�ᣨ�����������
    	<br/><br/><br/>
	</td>
  </tr>
  <tr>
  <td colspan="3">
��Ժ���
    	<br/><br/>
    	<br/>
    	<p align="right">(����)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
  		<p align="right">��  &nbsp;&nbsp;&nbsp;��  &nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
	</td>
  </tr>
  <tr>
  <td colspan="3">
<bean:message key="lable.xsgzyxpzxy" />���
    	<br/><br/>
    	<br/>
    	<p align="right">(����)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
  		<p align="right">��  &nbsp;&nbsp;&nbsp;��  &nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
	</td>
  </tr>
</table>
<div>

</div></div>
    </html:form>
  </body>
</html:html>
