<%@ page language="java" contentType="text/html; charset=GBK"%>

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
    <base target="_self" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script type="text/javascript" src="js/pjpyFunction.js"></script>
	<script type="text/javascript" src="js/sharedFunction.js"></script>
  </head>
  <%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
  <body>
  <script language="javascript" src="js/function.js"></script>
    <div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã��������� - �������� - ��������
		</div>
	</div>
    <html:form action="/save_js_bl">
      <table class="tbstyle" align=center>
         <tr>
           <td align=right>ѧ�꣺</td><td><div id="xnPart" name="xnPart"></div></td>
         </tr>
         <tr>
         	<td>�ɼ�������ռ������</td>
         	<td><html:text property="cjbl" styleId="cjbl" maxlength="3" onkeyup="onlyNum(this)" />%</td>
         </tr>
         <tr>
         	<td>�ۺ�������ռ������</td>
         	<td><html:text property="qtbl" styleId="qtbl" maxlength="3" onkeyup="onlyNum(this)" />%</td>
         </tr>
      </table>
      <div class="buttontool" align="center">
         <button class="button2" onclick="if(!checkBlSz()) return false;gen_submit($('xn').value)">����</button><!-- '/xgxt/save_js_bl.do','saveJsBl', -->
         <button class="button2" onclick="window.close();return false;">�ر�</button>
      </div>
      <html:hidden property="result" styleId="result" />
    </html:form>
  </body>
  <script>
     getParentXnList();
  </script>
  
  <logic:equal value="true" name="result">
  <script>
  	alert("����ɹ���");
  	window.close();
  	dialogArgumentsQueryChick();
  </script>
  </logic:equal>
  <logic:equal value="false" name="result">
  <script>
  	alert("����ʧ�ܣ�");
  </script>
  </logic:equal>
  
</html:html>
