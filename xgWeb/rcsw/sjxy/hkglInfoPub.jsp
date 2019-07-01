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
<script language="javascript">
		function chgEditer(obj){
			var url = "eWebEditor/eWebEditor.jsp?color=" + obj.value;
			eWebEditor1.location = url;
		}
		function pubInfo(){
			if(document.getElementById("title").value == ""){
				alert("����д���⣡");
				document.getElementById("title").focus();
				return false;
			}
			document.getElementById('content1').value = frames('eWebEditor1').getHTML();
			if(document.getElementById("content1").value == ""){
				alert("����дҪ��������Ϣ��");
				return false;
			}
			refreshForm('xshkgl.do?method=showHkglInfoPub&doType=save');
		}
	</script>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<body>
<html:form method="post" action="/xshkgl"> 
  <div class="title"> 
    <div class="title_img" id="title_m"> �����Ϣ </div> 
  </div>   
  <TABLE class="tbstyle" width="100%"> 
    <TR> 
      <TD align=right width="100"> ���⣺ </TD> 
      <TD align=left colspan="3">
      		<logic:notEqual value="11078" name="xxdm"> 
      		<select name="title" id="title" style="width:20%">
       			<option value="���ڹ�����֪">���ڹ�����֪</option>
       		</select>
       		</logic:notEqual>
       		<logic:equal value="11078" name="xxdm"> 
       		<select name="title" id="title" style="width:20%">
       			<option value="֪ͨ����">֪ͨ����</option>
       		</select>
       		</logic:equal>
      </TD> 
    </TR> 
    <TR> 
      <TD align=right width="100"> �༭���ݣ� </TD> 
      <TD align=center colspan="3">
      	<INPUT type="hidden" name="content1" value="<bean:write name="info"/>" id="content1"> 
        <IFRAME ID="eWebEditor1"
								src="BatEditor" frameborder="0"
								scrolling="no" width="100%" height="350"></IFRAME> 
	  </TD> 
    </TR> 
    <TR> 
      <TD colspan=4 align=center>
      	<button type="button" class="button2" onclick="pubInfo()" > �� �� </button>
        <INPUT type=reset name=b2 value="�� ��" class="button2"> 
      </TD> 
    </TR> 
  </TABLE> 
  <logic:present name="result">
	  <logic:equal value="true" name="result">
	  	<script>	
	  		alert("�����ɹ�!");
	  	</script>
	  </logic:equal>
	  <logic:equal value="false" name="result">
	  	<script>	
	  		alert("����ʧ��!");
	  	</script>
	  </logic:equal>
  </logic:present>
</html:form> 
</body>
</html>
