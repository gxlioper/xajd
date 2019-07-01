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
    
    <title><bean:message key="lable.title" /></title>
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
    <html:form action="/qgzxGwgl">
    <div class="title">
		<div class="title_img" id="title_m">					
			当前所在位置：勤工助学 - 申请 - 申请结果查询 - 学生申请详细信息
		</div>
	</div>
    <table class="tbstyle" style="width:100%">
    <tr>
    	<td align="right">
    		学号：
    	</td>
    	<td>
    		${rs.xh}
    	</td>
    	<td align="right">
    		姓名：
    	</td>
    	<td>
    		${rs.xm}
    	</td>
    </tr>
    <tr>
    	<td align="right">
    		学年：
    	</td>
    	<td>
    		${rs.xn}
    	</td>
    	<td align="right">
    		年度：
    	</td>
    	<td>
    		${rs.nd}
    	</td>
    </tr>
    <tr>
    	<td align="right">
    		学期：
    	</td>
    	<td>
    		${rs.xqmc}
    	</td>
    	<td align="right">
    		贫困生：
    	</td>
    	<td>
    		${rs.sfpk}
    	</td>
    </tr>
    <tr>
    	<td align="right">
    		岗位名称：
    	</td>
    	<td>
    		${rs.gwdm}
    	</td>
    	<td align="right">
    		联系电话：
    	</td>
    	<td>
    		${rs.lxdh}
    	</td>
    </tr>
    <tr>
    	<td align="right">
    		可参加勤工助学时间：
    	</td>
    	<td colspan="3">
    		${rs.kcjqgzxsj}
    	</td>    	
    </tr>
    <tr>
    	<td align="right">
    		申请理由：
    	</td>
    	<td colspan="3">
    		${rs.xssq}
    	</td>    	
    </tr>
    <tr>
    	<td align="right">
    		备注：
    	</td>
    	<td colspan="3">
    		${rs.bz}
    	</td>
    </tr>    
    <tr>
    	<td align="right">
    		岗位工作时间：
    	</td>
    	<td>
    		${rs.gzsj}
    	</td>
    	<td align="right">
    		岗位工作内容：
    	</td>
    	<td>
    		${rs.gznr}
    	</td>
    </tr>
    <tr>
    	<td align="right">
    		岗位人员要求：
    	</td>
    	<td colspan="3">
    		${rs.yrrq}
    	</td>
    </tr>
    </table>
    <div class="buttontool" align="center">
		<button type="button" class="button2" onclick="Close();return false;">
				关闭
		</button>
	</div>
	</html:form>
  </body>
</html:html>
