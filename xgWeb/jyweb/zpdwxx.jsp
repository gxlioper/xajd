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

<html>
	<head>
		<title><%=session.getAttribute("xxmc")%>��ҵ��</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<meta content="all" name="robots" />
		<meta name="author" content="�����������ӹ������޹�˾ hzzfsoft@126.com" />
		<meta name="Copyright" content="Copyright" />
		<meta name="description" content="description" />
		<meta name="keywords" content="description" />

		<link id="csss" rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link href="jyweb/style/module.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/base.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/forms.css" rel="stylesheet" type="text/css">

		<script language="javascript" src="js/function.js"></script>
	    <base target="_self">

	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body>
		<html:form action="/yhgl">
							<div align="center">
								��˾����&nbsp;&nbsp;<html:text property="gsmc"/>
								&nbsp;&nbsp;&nbsp;&nbsp;
								�Ƿ�ע��&nbsp;&nbsp;
								<html:select property="sfzc" style="width:100px">
									<html:option value=""></html:option>
									<html:option value="δע��">δע��</html:option>
									<html:option value="��ע��">��ע��</html:option>
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button onclick="refreshForm('yhgl.do?method=yhgl&jytype=jyweb&doType=zpdw&query=yes')" class="btn_search">��ѯ</button>
							</div>
							<table width="98%" align="center" class="tbborder" id="tb1">
								<tr align="center">
									<td>
										<font color="red">��λ����</font>
									</td>
									<td>
										<font color="red">������Ƹ��</font>
									</td>
									<td>
										<font color="red">�Ƿ�ע��</font>
									</td>
								</tr>
								<logic:iterate name="list" id="s">
									<tr align="center">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<td title="${v}">
												${v}
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1">
											<td>
												${v}
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
<%--								<logic:present name="list">--%>
<%--									<script language="javascript">--%>
<%--										changeView('tb1',0,25,'no','yes');--%>
<%--									</script>--%>
<%--								</logic:present>--%>
							</table>
		</html:form>
	</body>
</html>
