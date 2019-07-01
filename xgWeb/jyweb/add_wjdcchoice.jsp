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
		   
		    var choice = document.getElementById("choice").value;
		    if(choice==""){
		     alert("选项不能为空！");
		     return false;
		    }
		   
		    document.forms[0].action = "addwjdcchoice.do?method=addwjdcchoice&jytype=jyweb&doType=save";
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
		<html:form action="/addwjdcchoice" method="post">
			<fieldset>
				<legend>
					<font color="red"><b>增加问卷调查选项</b>
					</font>
				</legend>

				<font color="black"><bean:write name="bt" property="bt" /> </font>
				<input type="hidden" name="pkValue"
					value="<bean:write name="bt" property="bt" />" />
				<br>
				<br>
				请输入调查选项:
				<html:text name="rs" property="choice" maxlength="11"/>&nbsp;
				<logic:equal name="first" value="first">
					<button onclick="save();return false;">
						增加
					</button>
				</logic:equal>
				<logic:equal name="goon" value="goon">
					<button onclick="save();return false;">
						继续增加
					</button>
				</logic:equal>
				&nbsp;&nbsp;
				<button
					onclick="Close();window.dialogArguments.document.getElementById('query_go').click();">
					关闭
				</button>
				<br>注意：字数请不要超过11个字以免影响美观
				<br><br><br>
			</fieldset>
		</html:form>
		<logic:notEmpty name="save">
			<logic:equal name="save" value="ok">
				<script>
    alert("提交成功！");
    </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
    alert("提交失败！");
    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>

</html>
