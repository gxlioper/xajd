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
<meta name="Copyright" content="正方软件 zfsoft" />
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
				alert("请填写标题！");
				document.getElementById("title").focus();
				return false;
			}
			document.getElementById('content1').value = frames('eWebEditor1').getHTML();
			if(document.getElementById("content1").value == ""){
				alert("请填写要发布的信息！");
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
    <div class="title_img" id="title_m"> 相关信息 </div> 
  </div>   
  <TABLE class="tbstyle" width="100%"> 
    <TR> 
      <TD align=right width="100"> 标题： </TD> 
      <TD align=left colspan="3">
      		<logic:notEqual value="11078" name="xxdm"> 
      		<select name="title" id="title" style="width:20%">
       			<option value="户口管理须知">户口管理须知</option>
       		</select>
       		</logic:notEqual>
       		<logic:equal value="11078" name="xxdm"> 
       		<select name="title" id="title" style="width:20%">
       			<option value="通知公告">通知公告</option>
       		</select>
       		</logic:equal>
      </TD> 
    </TR> 
    <TR> 
      <TD align=right width="100"> 编辑内容： </TD> 
      <TD align=center colspan="3">
      	<INPUT type="hidden" name="content1" value="<bean:write name="info"/>" id="content1"> 
        <IFRAME ID="eWebEditor1"
								src="BatEditor" frameborder="0"
								scrolling="no" width="100%" height="350"></IFRAME> 
	  </TD> 
    </TR> 
    <TR> 
      <TD colspan=4 align=center>
      	<button type="button" class="button2" onclick="pubInfo()" > 发 布 </button>
        <INPUT type=reset name=b2 value="重 填" class="button2"> 
      </TD> 
    </TR> 
  </TABLE> 
  <logic:present name="result">
	  <logic:equal value="true" name="result">
	  	<script>	
	  		alert("发布成功!");
	  	</script>
	  </logic:equal>
	  <logic:equal value="false" name="result">
	  	<script>	
	  		alert("发布失败!");
	  	</script>
	  </logic:equal>
  </logic:present>
</html:form> 
</body>
</html>
