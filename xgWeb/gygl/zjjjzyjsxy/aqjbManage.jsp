<%@ page language="java" contentType="text/html; charset=gb2312"%>
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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
<title><bean:message key="lable.title" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta http-equiv="Content-Language" content="GBK" />
<meta name="Copyright" content="������� zfsoft" />
</head>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/sxjyFunction.js"></script>
<script language="javascript">
</script>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<body> 
<FORM method="POST" name="myform" action="eWebEditor/submit.jsp"> 
  <div class="title"> 
    <div class="title_img" id="title_m"> �� Ԣ �� �� �� Ϣ ά �� </div> 
  </div> 
  <fieldset> 
  <legend> &nbsp;&nbsp;�� �� �� �� �� �� ��&nbsp;&nbsp; </legend> 
  <logic:notEmpty name="rs"> 
  <table border="0" id="rsTable" width="100%" > 
    <thead align="center" class="tbstyle">
    <tr bgcolor="#D0E0EE" style="cursor:hand">
    <td onclick="tableSort(this)">����</td>
    <td onclick="tableSort(this)">ʱ��</td>
    <td onclick="tableSort(this)">�ύ��</td>
    <td onclick="tableSort(this)">���״̬</td>
    <td>����</td>
    </tr>
    </thead>
    <logic:iterate id="list" name="rs">  
    <tr onmouseover="rowOnClick(this)"> 
      <td> 
      <a href="zjjjzy_Gygl.do?method=aqjbShow&documentId=<bean:write name="list" property="jbid"/>" target="_blank"> 
      <bean:write name="list" property="jbtitle" /> 
      </a>											 </td> 
      <td width="140"> <bean:write name="list" property="jbpubtime" /> </td> 
      <td width="80"> <bean:write name="list" property="jbpubername" /> </td>
      <td width="80"> <bean:write name="list" property="xxsh" /> </td>
  	  <logic:notEmpty name="writeAble"> 
       <td align=right style="width: 80px">
       <logic:equal value="no" name="list" property="sfpass">
       	<a href="#" onclick="location='zjjjzy_Gygl.do?method=aqjbManage&documentId=<bean:write name="list" property="jbid"/>'">�޸�</a>/
       </logic:equal>	
       	<a href="#" onclick="if(confirm('ȷʵҪɾ����ǰ����'))location='zjjjzy_Gygl.do?method=aqjbDel&documentId=<bean:write name="list" property="jbid"/>'" >ɾ��</a> </td>
      </logic:notEmpty>
    </tr> 
    </logic:iterate> 
  </table> 
  </logic:notEmpty> <logic:empty name="rs"> 
  <center>
     ������Ϣ
  </center> 
  </logic:empty> 
  </fieldset> 
  <logic:notEmpty name="writeAble"> 
  <fieldset> 
  <legend> �����Ϣ </legend> 
  <TABLE class="tbstyle" width="100%"> 
<%--    <input type="hidden" name="type" id="type" value="<bean:write name="type" />" />--%>
    <TR>
      <TD align=right width="100"> ���⣺ </TD> 
      <TD align=left colspan="3">
    <input type="hidden" name="isModi" id="isModi" value="<bean:write name="isModi" />" /> 
    <input type="hidden" name="documentId" id="documentId" value="<bean:write name="documentId" />" />
       <input type="text" name="title" id="title" style="width:100%" maxlength="100" value="<bean:write name="documenttit"/>"> 
       
       </TD> 
    </TR> 
    <TR> 
      <TD align=right width="100"> ���ݣ� </TD> 
      <TD align=center colspan="3"> 
      
      <INPUT type="hidden" name="content1" value="<bean:write name="documentcont"/>">
      
      <IFRAME ID="eWebEditor1"
								src="BatEditor" frameborder="0"
								scrolling="no" width="100%" height="350"></IFRAME> </TD> 
    </TR> 
    <TR> 
      <TD colspan=4 align=center> 
      <button class="button2" onclick="jbpub()">       
      �� �� 
      </button>
      <button class="button2" onclick="clear()">       
      �� �� 
      </button>
      </TD> 
    </TR> 
  </TABLE> 
  </fieldset> 
  </logic:notEmpty>
</FORM> 
<script type="text/javascript">
function jbpub(){
	if(document.getElementById("title").value == ""){
		alert("����д���⣡");
		document.getElementById("title").focus();
		return false;
	}
	document.getElementById('content1').value = frames('eWebEditor1').getHTML();
	if(document.getElementById("content1").value == ""){
		alert("����д���ݣ�");
		return false;
	}
	refreshForm('/xgxt/zjjjzy_Gygl.do?method=aqjbManage&doType=save');
}	
function clear(){
   refreshForm('/xgxt/zjjjzy_Gygl.do?method=aqjbManage');
}
</script>	
</body>
</html>
