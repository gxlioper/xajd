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
    
    <title><bean:message key="lable.title" /></title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
  	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<script type="text/javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/pjpyFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
  </head>
  
  <body>
    <html:form action="/pjpy_zbdx_sssppdsjsz" method="post">
      <div class="title">
         <div class="title_img" id="title_m">
           <logic:equal value="11049" name="xxdm">

	      		��ǰλ�ã��������� - �������� - ˼��Ʒ������ʱ��
	      	</logic:equal>
	      	<logic:notEqual value="11049" name="xxdm">
	      			      		��ǰλ�ã��������� - �������� - ʦ��˼Ʒ����ʱ������
	      	</logic:notEqual>
         </div>
      </div>
      <logic:present name="doresult">
      	<logic:equal name="doresult" value="true">
      		<script type="text/javascript">
      			alert("����ɹ���");
      		</script>
      	</logic:equal>
      	<logic:notEqual name="doresult" value="true">
      		<script type="text/javascript">
      			alert("����ɹ���");
      		</script>
      	</logic:notEqual>
      </logic:present>
      <fieldset>
	      <legend>
	      	<logic:equal value="11049" name="xxdm">
	      			      		˼��Ʒ������ʱ��
	      	</logic:equal>
	      	<logic:notEqual value="11049" name="xxdm">

	      		ʦ��˼������ʱ���趨
	      	</logic:notEqual>
	      </legend>
      	  <table class="tbstyle" align="center" >
      	  	<tr>
      	  	<td align='right'>��ѧ������ѧ�꣺</td>
      	  	<td><html:text property="xn" /></td>
      	  	</tr>
      	  	<tr>
      	  	<td align='right'>ѧ��������ʼʱ��:</td>
      	  	<td><html:text style="cursor:hand" readonly="true" property="xspdkssj" styleId="xspdkssj" onclick="return showCalendar('xspdkssj','y-mm-dd');" onblur="dateFormatChg(this)" ></html:text></td>
      	  	</tr>
      	  	<tr>
      	  	<td align='right'>ѧ����������ʱ��:</td>
      	  	<td><html:text style="cursor:hand"readonly="true" property="xspdjssj" styleId="xspdjssj" onclick="return showCalendar('xspdjssj','y-mm-dd');" onblur="dateFormatChg(this)" ></html:text></td>
      	  	</tr>
      	  	<tr>
      	  	<td align='right'>��ʦ������ʼʱ��:</td>
      	  	<td><html:text style="cursor:hand" readonly="true" property="jspdkssj" styleId="jspdkssj" onclick="return showCalendar('jspdkssj','y-mm-dd');" onblur="dateFormatChg(this)" ></html:text></td>
      	  	</tr>
      	  	<tr>
      	  	<td align='right'>��ʦ��������ʱ��:</td>
      	  	<td><html:text style="cursor:hand" readonly="true" property="jspdjssj" styleId="jspdjssj" onclick="return showCalendar('jspdjssj','y-mm-dd');" onblur="dateFormatChg(this)" ></html:text></td>
      	  	</tr>
      	  </table>
      </fieldset>
      <div class="buttontool" >
        <button class="button2" onclick="checkValue();actFun('save');"> �� �� </button>
      </div>
    </html:form>
  </body>
</html:html>
