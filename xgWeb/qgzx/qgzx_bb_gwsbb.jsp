<%@ page language="java" contentType="text/html; charset=GBK"%>

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
<html:html locale="true">
  <head>
    
    <title>MyJsp.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
  	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
  <body>
    <html:form action="/qgzx_bb_gwsbb">
    <div id="rsT">
    <p align="center">学生处岗位申报表 </p>
	<table class="tbstyle" align="center" width="70%">
  		<tr>
    		<td width="142"><p align="center">校区： </p></td>
    		<td width="142"><p align="center">下拉菜单 </p>
        		<p align="center">（内置“本校区”） </p></td>
    		<td width="142"><p align="center">* 岗位名称： </p></td>
    		<td width="142"><p align="center">见上页说明 </p></td>
  		</tr>
  		<tr>
    		<td width="142"><p align="center">岗位性质： </p></td>
    		<td width="142"><p align="center">下拉菜单 </p>
        		<p align="center">（内置“校内固定岗”） </p></td>
    		<td width="142"><p align="center">申请单位： </p></td>
    		<td width="142"><p align="center">各<bean:message key="lable.xsgzyxpzxy" />名称 </p></td>
  		</tr>
  		<tr>
    		<td width="142"><p align="center">学年： </p></td>
    		<td width="142">&nbsp;</td>
    		<td width="142"><p align="center">年度： </p></td>
    		<td width="142">&nbsp;</td>
  		</tr>
  		<tr>
   		 	<td width="142"><p align="center">月份： </p></td>
    		<td width="142">&nbsp;</td>
    		<td width="142"><p align="center">* 申请时间： </p></td>
    		<td width="142">&nbsp;</td>
  		</tr>
  		<tr>
    		<td width="142"><p align="center">* 需要人数： </p></td>
    		<td width="142">&nbsp;</td>
    		<td width="142"><p align="center">总经费： </p></td>
    		<td width="142">&nbsp;</td>
  		</tr>
  		<tr>
   		 	<td width="142"><p align="center">工作开始日期： </p></td>
    		<td width="142">&nbsp;</td>
    		<td width="142"><p align="center">工作结束日期： </p></td>
    		<td width="142">&nbsp;</td>
  		</tr>
  		<tr>
    		<td width="142"><p align="center">* 计酬方式： </p></td>
    		<td width="142">&nbsp;</td>
    		<td width="142"><p align="center">使用困难生数： </p></td>
    		<td width="142">&nbsp;</td>
  		</tr>
  		<tr>
    		<td width="142"><p align="center">工作时间： </p></td>
    		<td width="142">&nbsp;</td>
    		<td width="142"><p align="center">联系电话： </p></td>
    		<td width="142">&nbsp;</td>
  		</tr>
  		<tr>
    		<td width="142"><p align="center">工作内容 </p></td>
    		<td width="426" colspan="3">&nbsp;</td>
    	</tr>
    	<tr>
    		<td width="142"><p align="center">申请报告： </p></td>
    		<td width="426" colspan="3">&nbsp;</td>
  		</tr>
  		<tr>
    		<td width="142"><p align="center">申请单位意见： </p></td>
    		<td width="426" colspan="3">&nbsp;</td>
  		</tr>
  		<tr>
    		<td width="142"><p align="center">备注： </p></td>
    		<td width="426" colspan="3">&nbsp;</td>
  		</tr>
    </table>
    </div>
    <div class="buttontool" align="center">
		<button type="button" class="button2" onclick="expAppTab('rsT','勤工助学岗位申请表','')">
				打 印 报 表
		</button>
	</div>
	</html:form>
  </body>
</html:html>
