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
		<script type="text/javascript">
		function findpassword(){
		
		   var yhm = document.getElementById("yhm").value;
		   if(yhm==""){
		     alert("����д�û���!");
		     return false;
		   }
		   document.forms[0].action = "findpassword.do?method=findpassword&doType=find&jytype=jyweb";
	       document.forms[0].submit();
		
		
		}
		
		</script>

	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body>
		<html:form action="/findpassword">
			<jsp:include flush="true" page="head.jsp"></jsp:include>
            <input type="hidden" name="webtype" value="mmzh" />
			<div class="mainframe">
				<div class="ny_midframe">
					<div class="leftframe">
						<jsp:include flush="true" page="contrl.jsp"></jsp:include>
						<div class="rdxw">
							<h1></h1>
						</div>
						<div class="yqlj">
							<h1></h1>
							<span></span>
						</div>
					</div>
					<div class="ny_rightframe">
						<div class="ny_con">
								<h3>
									��ǰλ�ã�
									<a href="index.do">��ҳ</a>ѡ�� ʵʱͳ��
								</h3>

							<table width="50%" align="center" class="tbborder">
						<tr>
							<td align="left">
								�û�����
							</td>
							<td>
								<html:text name="rs" property="yhm"
									onkeypress="if(window.event.keyCode==13) {  return findpassword();}" />
							</td>
						<tr>
						<tr>
							<td align="right">
								�û����ͣ�
							</td>
							<td align="left">
								<html:select name="rs" property="usertype">
									<html:option value="xs">ѧ��</html:option>
									<html:option value="dw">��λ</html:option>
									<html:option value="admin">����Ա</html:option>
								</html:select>
							</td>
						<tr>
						<tr>
							<td align="right">
								���ҽ����
							</td>
							<td align="center">
								&nbsp;
								<logic:notEmpty name="you">
		                 ����&nbsp;&nbsp;&nbsp;<font color="red"><b><bean:write
												name="rs" property="mm" /></b>
									</font>
								</logic:notEmpty>
								<logic:notEmpty name="mei">
									<div align="center">
										<font color="red">���û��������ڣ�</font>
									</div>
								</logic:notEmpty>
							</td>
						<tr>
					</table>
					<br>
					<button onclick="findpassword();">
						����
					</button>
							<br>
							<br>

						</div>
						<h2></h2>
					</div>
				</div>
			</div>



			<jsp:include flush="true" page="foot.jsp"></jsp:include>
		</html:form>
	</body>
</html>
