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

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
  </head>
  	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
  <script language="javascript" src="js/function.js"></script>
  <body>
    <html:form action="/pjpy_apply_print">
       <div class="title">
          <div class="title_img" id="title_m">
             当前所在位置：评奖评优 - 奖学金申请 - 填写申请表
          </div>
       </div>
       <p align="center"><strong>上海工程技术大学优秀学生奖学金、自强奖学金申请表</strong></p>
<p align="right">填表日期： 年 月 日 </p>
<table  class="tbstyle" style="width:100%">
  <tr>
    <td width="35" rowspan="3"><p align="center">本 </p>
        <p align="center">人 </p>
        <p align="center">情 </p>
        <p align="center">况 </p></td>
    <td width="58" height="30"><p align="center">姓名 </p></td>
    <td colspan="2"><html:text name="rs" property="xh" /></td>
    <td><div align="center">性别</div></td>
    <td colspan="2"><html:text name="rs" property="xb" /></td>
    <td><div align="center">出生年月日</div></td>
    <td colspan="4"><html:text name="rs" property="csrq"/></td>
    <td width="22"><p align="center">民族 </p></td>
    <td width="48"><html:text name="rs" property="mz" /></td>
  </tr>
  <tr>
    <td width="58" height="36"><p align="center">学号 </p></td>
    <td colspan="4">&nbsp;</td>
    <td colspan="5"><p align="center">入学时间 </p></td>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="13"><p>大学 <bean:message key="lable.xsgzyxpzxy" /> 系 班 </p></td>
  </tr>
  <tr>
    <td width="35"><p align="center">成绩绩点</p></td>
    <td colspan="13"><p>按教务处教学管理系统，各科成绩，并包括思想品德评定成绩、体育成绩 </p></td>
  </tr>
  <tr>
    <td width="35" rowspan="5"><p align="center">申请等级</p></td>
    <td height="29" colspan="8"><p align="center">优秀学生奖学金 </p></td>
    <td height="29" colspan="5"><p align="center">自 强 </p></td>
  </tr>
  <tr>
    <td width="58" height="29" valign="top"><p align="center">特等奖 </p></td>
    <td width="73" height="29" valign="top"><p align="center">一等奖 </p></td>
    <td width="69" height="29" valign="top"><p align="center">二等奖 </p></td>
    <td height="29" colspan="2" valign="top"><p align="center">三等奖 </p></td>
    <td height="29" colspan="2" valign="top"><p align="center">创新甲 </p></td>
    <td width="93" height="29" valign="top"><p align="center">创新乙 </p></td>
    <td height="29" colspan="2" valign="top"><p align="center">甲 </p></td>
    <td height="29" colspan="3" valign="top"><p align="center">乙 </p></td>
  </tr>
  <tr>
    <td width="58" height="32" valign="top">&nbsp;</td>
    <td width="73" height="32" valign="top">&nbsp;</td>
    <td width="69" height="32" valign="top">&nbsp;</td>
    <td height="32" colspan="2" valign="top">&nbsp;</td>
    <td height="32" colspan="2" valign="top">&nbsp;</td>
    <td width="93" height="32" valign="top">&nbsp;</td>
    <td height="32" colspan="2" valign="top">&nbsp;</td>
    <td height="32" colspan="3" valign="top">&nbsp;</td>
  </tr>
  <tr>
    <td width="58" height="31" valign="top">&nbsp;</td>
    <td width="73" height="31" valign="top">&nbsp;</td>
    <td width="69" height="31" valign="top">&nbsp;</td>
    <td height="31" colspan="2" valign="top">&nbsp;</td>
    <td height="31" colspan="2" valign="top">&nbsp;</td>
    <td width="93" height="31" valign="top">&nbsp;</td>
    <td height="31" colspan="2" valign="top">&nbsp;</td>
    <td height="31" colspan="3" valign="top">&nbsp;</td>
  </tr>
  <tr>
    <td width="58" height="34" valign="top">&nbsp;</td>
    <td width="73" height="34" valign="top">&nbsp;</td>
    <td width="69" height="34" valign="top">&nbsp;</td>
    <td height="34" colspan="2" valign="top">&nbsp;</td>
    <td height="34" colspan="2" valign="top">&nbsp;</td>
    <td width="93" height="34" valign="top">&nbsp;</td>
    <td height="34" colspan="2" valign="top">&nbsp;</td>
    <td height="34" colspan="3" valign="top">&nbsp;</td>
  </tr>
  <tr>
    <td height="123" colspan="14" valign="top"><p><bean:message key="lable.xsgzyxpzxy" />审核意见： </p></td>
  </tr>
  <tr>
    <td height="216" colspan="14" valign="top"><p>学校审核意见： </p></td>
  </tr>
</table>
    </html:form>
  </body>
</html:html>
