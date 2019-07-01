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
<%@ page import = "xgxt.DAO.JspDAO" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onselectstart="return false" ondragstart="return false" onbeforecopy="return false" oncopy="document.selection.empty()" onselect="document.selection.empty()">
		<div class="bottomframe">
			<div style="float:left;margin:2px 0px 0px 5px;">
				��¼�ţ�
				${userName }
				, ������
				${userNameReal }
				<% JspDAO dao = new JspDAO();
				String userDep = null != session.getAttribute("userDep") ? session.getAttribute("userDep").toString() : "";
			  	String bmmc = dao.getBmmcByDmForJsp(userDep);%>
			  , �������ţ�
				<%=bmmc %>
				<%--��ɳ����--%>
				<logic:equal value="10827" name="xxdm">
				<logic:equal value="student" name="userOnLine">
				, �����༶��
				${bjmc }
				</logic:equal>
				</logic:equal>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<script language="javascript">
dayName = new Array("", "��һ", "�ܶ�", "����", "����", "����", "����", "����")
monName = new Array("1��", "2��", "3��", "4��", "5��", "6��", "7��", "8��", "9��", "10��", "11��", "12��")
now = new Date();
document.write(now.getFullYear() + "�� " + monName[now.getMonth()] + " " + now.getDate() + "�� " + dayName[now.getDay()]);
</script> 
			
			<logic:equal value="11417" name="xxdm">
				<logic:notEqual value="�ż�" name="LoginZc">
				(��<b>${LoginZc }</b>��ѧ��)
				</logic:notEqual>
				<logic:equal value="�ż�" name="LoginZc">
				<b>�ż�</b>
				</logic:equal>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<b>${LoginXn }</b>ѧ���&nbsp;��<b>${LoginXq }</b>ѧ��
			</logic:equal>
			</div>
			<a href="http://www.zfsoft.com" target="_blank"><div class="zflogo"></div>
			</a>
		</div>
	</body>
</html>
