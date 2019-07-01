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
			当前所在位置：评奖评优 - 条件设置 - 比例设置
		</div>
	</div>
    <html:form action="/save_js_bl">
      <table class="tbstyle" align=center>
         <tr>
           <td align=right>学年：</td><td><div id="xnPart" name="xnPart"></div></td>
         </tr>
         <tr>
         	<td>成绩部分所占比例：</td>
         	<td><html:text property="cjbl" styleId="cjbl" maxlength="3" onkeyup="onlyNum(this)" />%</td>
         </tr>
         <tr>
         	<td>综合素质所占比例：</td>
         	<td><html:text property="qtbl" styleId="qtbl" maxlength="3" onkeyup="onlyNum(this)" />%</td>
         </tr>
      </table>
      <div class="buttontool" align="center">
         <button class="button2" onclick="if(!checkBlSz()) return false;gen_submit($('xn').value)">保存</button><!-- '/xgxt/save_js_bl.do','saveJsBl', -->
         <button class="button2" onclick="window.close();return false;">关闭</button>
      </div>
      <html:hidden property="result" styleId="result" />
    </html:form>
  </body>
  <script>
     getParentXnList();
  </script>
  
  <logic:equal value="true" name="result">
  <script>
  	alert("保存成功！");
  	window.close();
  	dialogArgumentsQueryChick();
  </script>
  </logic:equal>
  <logic:equal value="false" name="result">
  <script>
  	alert("保存失败！");
  </script>
  </logic:equal>
  
</html:html>
