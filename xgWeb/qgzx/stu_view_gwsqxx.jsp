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
			��ǰ����λ�ã��ڹ���ѧ - ���� - ��������ѯ - ѧ��������ϸ��Ϣ
		</div>
	</div>
    <table class="tbstyle" style="width:100%">
    <tr>
    	<td align="right">
    		ѧ�ţ�
    	</td>
    	<td>
    		${rs.xh}
    	</td>
    	<td align="right">
    		������
    	</td>
    	<td>
    		${rs.xm}
    	</td>
    </tr>
    <tr>
    	<td align="right">
    		ѧ�꣺
    	</td>
    	<td>
    		${rs.xn}
    	</td>
    	<td align="right">
    		��ȣ�
    	</td>
    	<td>
    		${rs.nd}
    	</td>
    </tr>
    <tr>
    	<td align="right">
    		ѧ�ڣ�
    	</td>
    	<td>
    		${rs.xqmc}
    	</td>
    	<td align="right">
    		ƶ������
    	</td>
    	<td>
    		${rs.sfpk}
    	</td>
    </tr>
    <tr>
    	<td align="right">
    		��λ���ƣ�
    	</td>
    	<td>
    		${rs.gwdm}
    	</td>
    	<td align="right">
    		��ϵ�绰��
    	</td>
    	<td>
    		${rs.lxdh}
    	</td>
    </tr>
    <tr>
    	<td align="right">
    		�ɲμ��ڹ���ѧʱ�䣺
    	</td>
    	<td colspan="3">
    		${rs.kcjqgzxsj}
    	</td>    	
    </tr>
    <tr>
    	<td align="right">
    		�������ɣ�
    	</td>
    	<td colspan="3">
    		${rs.xssq}
    	</td>    	
    </tr>
    <tr>
    	<td align="right">
    		��ע��
    	</td>
    	<td colspan="3">
    		${rs.bz}
    	</td>
    </tr>    
    <tr>
    	<td align="right">
    		��λ����ʱ�䣺
    	</td>
    	<td>
    		${rs.gzsj}
    	</td>
    	<td align="right">
    		��λ�������ݣ�
    	</td>
    	<td>
    		${rs.gznr}
    	</td>
    </tr>
    <tr>
    	<td align="right">
    		��λ��ԱҪ��
    	</td>
    	<td colspan="3">
    		${rs.yrrq}
    	</td>
    </tr>
    </table>
    <div class="buttontool" align="center">
		<button type="button" class="button2" onclick="Close();return false;">
				�ر�
		</button>
	</div>
	</html:form>
  </body>
</html:html>
