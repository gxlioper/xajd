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
function pubNews(){
	if(document.getElementById("hd_Title").value == ""){
		alert("请填写活动标题！");
			document.getElementById("hd_Title").focus();
		return false;
	}
	if(document.getElementById("mxdx").value == ""){
		alert("请选择所属模块！");
			document.getElementById("mxdx").focus();
		return false;
	}
	document.getElementById('content1').value = frames('eWebEditor1').getHTML();
	if(document.getElementById("content1").value == ""){
		alert("请填写活动正文！");
		return false;
	}
	refreshForm('saveHdjh.do');
}
	</script>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<body> 
<FORM method="POST" id="myform" action="/xgxt/saveHdjh.do"> 
  <div class="title"> 
    <div class="title_img" id="title_m"> 当前所在位置： 宿舍精神文明建设 - 制定活动计划 </div> 
  </div> 
  <fieldset> 
  <legend> &nbsp;&nbsp;活 动 计 划&nbsp;&nbsp; </legend> 
  <table class="tbstyle" id="rsTable" width="100%" style="cursor:hand"> 
    <thead> 
      <tr align="center" style="cursor:hand"> 
        <td onclick="tableSort(this)"> 活动标题 </td> 
        <td width="100" onclick="tableSort(this)"> 模块归属 </td> 
        <td width="140" onclick="tableSort(this)"> 发布时间 </td> 
        <td width="80" onclick="tableSort(this)"> 发布人 </td> 
        <td width="80" onclick="tableSort(this)"> 操作 </td> 
      </tr> 
    </thead> 
    <logic:present name="rs"> <logic:iterate id="list" name="rs"> 
    <tr onclick="rowOnClick(this)" style="cursor:hand;"> 
      <td> <a href="hd_Content.do?hd_Id=<bean:write name="list" property="hd_Id"/>" target="_blank"> <bean:write name="list" property="hd_title" /> </a> </td> 
      <td> <bean:write name="list" property="hd_part" /> </td> 
      <td> <bean:write name="list" property="hd_pubtime" /> </td> 
      <td> <bean:write name="list" property="hd_puber" /> </td> 
      <td>
        <a href="#" onclick="location='active_plan_manage.do?hd_Id=<bean:write name="list" property="hd_Id"/>&tagId='+tagId.value;">修改</a>/ <a href="#" onclick="if(confirm('确实要删除当前新闻吗？'))location='saveHdjh.do?act=del&hd_Id=<bean:write name="list" property="hd_Id"/>&tagId='+tagId.value;" >删除</a> </td> 
    </tr> 
    </logic:iterate> </logic:present> 
  </table> 
  </fieldset> 
  <fieldset> 
  <legend> 添加活动计划 </legend> 
  <TABLE class="tbstyle" width="100%"> 
    <TR> 
      <TD align=right width="100"> 所属模块： </TD> 
      <TD align="left" colspan="3"> <html:select name="ShgcForm" property="xmdm" styleId="mxdx" onchange="tagId.value=this.value" disabled="true"> <html:options collection="modList" property="gnmkdm"
									labelProperty="gnmkmc" /> </html:select> <html:hidden name="ShgcForm" property="xmdm" /> 
        <input type="hidden" name="tagId" id="tagId" value="<bean:write name="ShgcForm" property="xmdm" />" /> 
        <input type="hidden" name="isModi" id="isModi" value="<bean:write name="isModi" />" /> 
        <input type="hidden" name="hd_Id" id="hd_Id" value="<bean:write name="hd_Id" />" /> </TD> 
    </TR> 
    <TR> 
      <TD align=right width="100"> 活动标题： </TD> 
      <TD align=left colspan="3"> <input type="text" name="hd_Title" id="hd_Title" style="width:100%" value="<bean:write name="hd_tit"/>"> </TD> 
    </TR> 
    <TR> 
      <TD align=right width="100"> 编辑内容： </TD> 
      <TD align=center colspan="3"> <INPUT type="hidden" name="content1" value="<bean:write name="hd_cont"/>"> 
        <IFRAME ID="eWebEditor1"
								src="BatEditor" frameborder="0"
								scrolling="no" width="100%" height="350"></IFRAME> </TD> 
    </TR> 
    <TR> 
      <TD colspan=4 align=center> <button class="button2" onclick="pubNews();return false;"> 发 布 </button>
        <INPUT type=reset name=b2 value="重填" class="button2"> </TD> 
    </TR> 
  </TABLE> 
  </fieldset> 
</FORM> 
</body>
</html>
