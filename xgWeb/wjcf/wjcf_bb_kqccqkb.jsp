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
    <title>wjcf_bb_kqccqkb.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
 	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
  
  <body>
  	<script language="javascript" src="js/function.js"></script>
  	<html:form action="/kqxxwh.do" method="post">
  	<table width="90%" align="center">
  		<tr>
    		<td align="center"><strong><font size="3"><bean:write name="xxmc"/>&nbsp;&nbsp;ѧ��ϵͳ </font></strong></td>
    	</tr>
    	<tr>
			<td align="center"><strong><font size="3"><bean:write name="month"/>�� <bean:write name="day"/> ��ѧ�����ڳ�����ͳ�Ʊ� </font></strong></td>
		</tr>
		<tr>
			<td align="left">&nbsp;&nbsp;&nbsp;���ʱ�䣺 </td>
		</tr>
	</table>
	<table width="90%" class="tbstyle" align="center">
	 	<thead>
  			<tr>
    			<td width="50" align="center"><p>���� </p></td>
    			<td width="50" align="center"><p>���� </p></td>
    			<td width="108" align="center"><p>ϵ�� </p></td>
    			<td width="108" align="center"><p>�༶ </p></td>
    			<td width="50" align="center"><p>�ർʦ </p></td>
    			<td width="50" align="center"><p>�ڿ���ʦ </p></td>
    			<td width="176" align="center"><p>�γ��� </p></td>
   				<td width="30" align="center"><p>Ӧ��(��)</p></td>
    			<td width="30" align="center"><p>ʵ��(��)</p></td>
    			<td width="50" align="center"><p>������(%)</p></td>
    			<td width="30" align="center"><p>ȱ�� </p></td>
    			<td width="30" align="center"><p>��� </p></td>
    			<td width="95" align="center"><p>��ע </p></td>
  			</tr>
  		</thead>
  		<tbody id="tbTable">
  			<logic:iterate id="v" name="rs">
  			<tr>
    			<td align="center"></td>
    			<td align="center"><bean:write name="v" property="skdd"/></td>
    			<td align="center"><bean:write name="v" property="xymc"/></td>
    			<td align="center"><bean:write name="v" property="bjmc"/></td>
    			<td align="center"><bean:write name="v" property="bzr"/></td>
    			<td align="center"><bean:write name="v" property="rkjs"/></td>
    			<td align="center"><bean:write name="v" property="kcmc"/></td>
    			<td align="center"><bean:write name="v" property="rs"/></td>
   				<td align="center"><bean:write name="v" property="sdrs"/></td>
    			<td align="center"><bean:write name="v" property="cql"/></td>
    			<td align="center"><bean:write name="v" property="qq"/></td>
    			<td align="center"><bean:write name="v" property="qjrs"/></td>
    			<td align="center"><bean:write name="v" property="bz"/></td>
  			</tr>
  			</logic:iterate>
  		</tbody>
	</table>
	&nbsp;
	<table width="90%" align="center">
		<tr>
			<td colspan="13">&nbsp;&nbsp;&nbsp;����ˣ�ѧ����</td>
		</tr>
	</table>
	</html:form>
  </body>
</html:html>
