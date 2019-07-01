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
		<title><%=session.getAttribute("xxmc")%>就业网</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<meta content="all" name="robots" />
		<meta name="author" content="杭州正方电子工程有限公司 hzzfsoft@126.com" />
		<meta name="Copyright" content="Copyright" />
		<meta name="description" content="description" />
		<meta name="keywords" content="description" />

		<link id="csss" rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link href="jyweb/style/module.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/base.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/forms.css" rel="stylesheet" type="text/css">
		<base target="_self">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
		   function save(){
		   
		    var bt = document.getElementById("bt").value;
		    var choice = document.getElementById("choice").value;
		    if( bt==""){
		     alert("标题不能为空！");
		     return false;
		    }
		    if( choice==""){
		     alert("第一个选项不能为空！");
		     return false;
		    }
		    document.forms[0].action = "newwjdc.do?method=newwjdc&doType=save&jytype=jyweb";
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
		<html:form action="/newwjdc" method="post">
			<fieldset>
				<legend>
					<font color="red"><b>发起问卷调查</b> </font>
				</legend>
				<br>
				<logic:equal name="first" value="first">
					<table>
						<tr>
							<td align="right">
								请输入调查标题:
							</td>
							<td align="left">
								<input type="text" name="bt" maxlength="11" />
							</td>
						</tr>
						<tr>
							<td align="right">
								请输入第一条选项:
							</td>
							<td align="left">
								<input type="text" name="choice" maxlength="11" />
							</td>
						</tr>
					</table>
					<br>
					<button onclick="save();return false;">
						提交
					</button>
				</logic:equal>
				<logic:equal name="save" value="ok">
				提交成功！请点击关闭按钮后进行下一步操作→
				</logic:equal>
				<logic:equal name="save" value="no">
				提交失败！请点击关闭按钮后重新操作→
				</logic:equal>
				&nbsp;&nbsp;
				<button
					onclick="Close();window.dialogArguments.document.getElementById('query_go').click();">
					关闭
				</button>
				<br>
				注：标题及选项请控制在11个字符长度，以免影响美观。
				<br>
				<br>
				<br>
			</fieldset>
		</html:form>
	</body>

</html>
