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
<body> 
<FORM method="POST" id="myform" action="/xgxt/saveNews.do"> 
  <div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã� �������� - ��ѧ������ - ��ѧ������
				</div>
  </div> 
  <fieldset> 
  <legend> &nbsp;&nbsp;�� ѧ �� �� ��&nbsp;&nbsp; </legend> 
  <table class="tbstyle" id="rsTable" width="100%" style="cursor:hand"> 
    <thead> 
      <tr align="center" style="cursor:hand"> 
        <td width="100" onclick="tableSort(this)"> ��ѧ������ </td> 
        <td  onclick="tableSort(this)"> ��˵�� </td> 
        <td width="140" onclick="tableSort(this)"> ���� </td> 
        <td width="80" onclick="tableSort(this)"> ��ѧ����� </td> 
        <td width="80" onclick="tableSort(this)"> ��ѧ���� </td> 
        <td width="140" onclick="tableSort(this)"> ��������״̬ </td> 
      </tr> 
    </thead> 
    <logic:present name="rs"> <logic:iterate id="list" name="rs"> 
    <tr onclick="rowOnClick(this)" style="cursor:hand;"> 
      <td> <a href="newsContent.do?newsId=<bean:write name="list" property="newsid"/>" target="_blank"> <bean:write name="list" property="newstitle" /> </a> </td> 
      <td> <bean:write name="list" property="newspart" /> </td> 
      <td> <bean:write name="list" property="pubtime" /> </td> 
      <td> <bean:write name="list" property="puber" /> </td> 
      <td>
        <a href="#" onclick="location='newsPub.do?newsId=<bean:write name="list" property="newsid"/>&tagId='+tagId.value;">�޸�</a>/ <a href="#" onclick="if(confirm('ȷʵҪɾ����ǰ������'))location='saveNews.do?act=del&newsId=<bean:write name="list" property="newsid"/>&tagId='+tagId.value;" >ɾ��</a> </td> 
    </tr> 
    </logic:iterate> </logic:present> 
  </table> 
  </fieldset> 
</FORM> 
</body>
</html>
