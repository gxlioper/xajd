<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    
    <title>MyJsp.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
  	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
  <body>
    <html:form action="/qgzx_bb_gwsbb">
    <div id="rsT">
    <p align="center">ѧ������λ�걨�� </p>
	<table class="tbstyle" align="center" width="70%">
  		<tr>
    		<td width="142"><p align="center">У���� </p></td>
    		<td width="142"><p align="center">�����˵� </p>
        		<p align="center">�����á���У������ </p></td>
    		<td width="142"><p align="center">* ��λ���ƣ� </p></td>
    		<td width="142"><p align="center">����ҳ˵�� </p></td>
  		</tr>
  		<tr>
    		<td width="142"><p align="center">��λ���ʣ� </p></td>
    		<td width="142"><p align="center">�����˵� </p>
        		<p align="center">�����á�У�ڹ̶��ڡ��� </p></td>
    		<td width="142"><p align="center">���뵥λ�� </p></td>
    		<td width="142"><p align="center">��<bean:message key="lable.xsgzyxpzxy" />���� </p></td>
  		</tr>
  		<tr>
    		<td width="142"><p align="center">ѧ�꣺ </p></td>
    		<td width="142">&nbsp;</td>
    		<td width="142"><p align="center">��ȣ� </p></td>
    		<td width="142">&nbsp;</td>
  		</tr>
  		<tr>
   		 	<td width="142"><p align="center">�·ݣ� </p></td>
    		<td width="142">&nbsp;</td>
    		<td width="142"><p align="center">* ����ʱ�䣺 </p></td>
    		<td width="142">&nbsp;</td>
  		</tr>
  		<tr>
    		<td width="142"><p align="center">* ��Ҫ������ </p></td>
    		<td width="142">&nbsp;</td>
    		<td width="142"><p align="center">�ܾ��ѣ� </p></td>
    		<td width="142">&nbsp;</td>
  		</tr>
  		<tr>
   		 	<td width="142"><p align="center">������ʼ���ڣ� </p></td>
    		<td width="142">&nbsp;</td>
    		<td width="142"><p align="center">�����������ڣ� </p></td>
    		<td width="142">&nbsp;</td>
  		</tr>
  		<tr>
    		<td width="142"><p align="center">* �Ƴ귽ʽ�� </p></td>
    		<td width="142">&nbsp;</td>
    		<td width="142"><p align="center">ʹ������������ </p></td>
    		<td width="142">&nbsp;</td>
  		</tr>
  		<tr>
    		<td width="142"><p align="center">����ʱ�䣺 </p></td>
    		<td width="142">&nbsp;</td>
    		<td width="142"><p align="center">��ϵ�绰�� </p></td>
    		<td width="142">&nbsp;</td>
  		</tr>
  		<tr>
    		<td width="142"><p align="center">�������� </p></td>
    		<td width="426" colspan="3">&nbsp;</td>
    	</tr>
    	<tr>
    		<td width="142"><p align="center">���뱨�棺 </p></td>
    		<td width="426" colspan="3">&nbsp;</td>
  		</tr>
  		<tr>
    		<td width="142"><p align="center">���뵥λ����� </p></td>
    		<td width="426" colspan="3">&nbsp;</td>
  		</tr>
  		<tr>
    		<td width="142"><p align="center">��ע�� </p></td>
    		<td width="426" colspan="3">&nbsp;</td>
  		</tr>
    </table>
    </div>
    <div class="buttontool" align="center">
		<button type="button" class="button2" onclick="expAppTab('rsT','�ڹ���ѧ��λ�����','')">
				�� ӡ �� ��
		</button>
	</div>
	</html:form>
  </body>
</html:html>
