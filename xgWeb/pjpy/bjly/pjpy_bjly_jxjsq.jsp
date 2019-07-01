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
<body> 
<FORM method="POST" id="myform" action="/xgxt/saveNews.do"> 
  <div class="title">
				<div class="title_img" id="title_m">
					当前位置： 评奖评优 - 奖学金申请 - 奖学金申请
				</div>
  </div> 
  <fieldset> 
  <legend> &nbsp;&nbsp;奖 学 金 列 表&nbsp;&nbsp; </legend> 
  <table class="tbstyle" id="rsTable" width="100%" style="cursor:hand"> 
    <thead> 
      <tr align="center" style="cursor:hand"> 
        <td width="100" onclick="tableSort(this)"> 奖学金名称 </td> 
        <td  onclick="tableSort(this)"> 简单说明 </td> 
        <td width="140" onclick="tableSort(this)"> 申请 </td> 
        <td width="80" onclick="tableSort(this)"> 奖学金类别 </td> 
        <td width="80" onclick="tableSort(this)"> 奖学金金额 </td> 
        <td width="140" onclick="tableSort(this)"> 现在申请状态 </td> 
      </tr> 
    </thead> 
    <logic:present name="rs"> <logic:iterate id="list" name="rs"> 
    <tr onclick="rowOnClick(this)" style="cursor:hand;"> 
      <td> <a href="newsContent.do?newsId=<bean:write name="list" property="newsid"/>" target="_blank"> <bean:write name="list" property="newstitle" /> </a> </td> 
      <td> <bean:write name="list" property="newspart" /> </td> 
      <td> <bean:write name="list" property="pubtime" /> </td> 
      <td> <bean:write name="list" property="puber" /> </td> 
      <td>
        <a href="#" onclick="location='newsPub.do?newsId=<bean:write name="list" property="newsid"/>&tagId='+tagId.value;">修改</a>/ <a href="#" onclick="if(confirm('确实要删除当前新闻吗？'))location='saveNews.do?act=del&newsId=<bean:write name="list" property="newsid"/>&tagId='+tagId.value;" >删除</a> </td> 
    </tr> 
    </logic:iterate> </logic:present> 
  </table> 
  </fieldset> 
</FORM> 
</body>
</html>
