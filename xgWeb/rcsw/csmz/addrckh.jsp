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
			<base target="_self">
		<title>长沙民政-日常考核</title>
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

		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script type="text/javascript">
			function addrckh(){

				document.getElementById('content1').value = frames('eWebEditor1').getHTML();
				var bt= $("bt").value;

                if(bt==""){
                 alert("标题不能为空！");
                 return false;
                }


				if(document.getElementById("content1").value == ""){
					alert("内容不能为空！");
					return false;
				}
				url = "addRckh.do?doType=save";
		 	    document.forms[0].action = url;
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
		<html:form action="/addRckh" method="post">
			<table width="99%" align="center" class="tbborder">
				<tr>
					<td align="right">
						发布人：
					</td>
					<td>
						<DIV align="left">
							<input name="fbr" value="<bean:write name="fbr" scope="request"/>" maxlength="10" readonly="readonly">
						</DIV>
					</td>
				</tr>
				<tr>
					<td align="right">
					   标    题：
					</td>
					<td>
					   <input  name="bt" type="text" maxlength="30"  style="width:99%" />
					</td>
				</tr>
				<TR>
					<TD align=right width="100">
						编辑内容：
					</TD>
					<TD align=center colspan="3"><INPUT type="hidden" name="content1"  id="content1">
						<IFRAME ID="eWebEditor1" src="BatEditor" frameborder="0"
							scrolling="no" width="100%" height="350">
						</IFRAME>
					</TD>
				</TR>
				<TR>
					<TD colspan=4 align=center>
						<button type="button" onclick="addrckh();" id="buttonSave">
							提交
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" type="reset" id="buttonReset">
							关闭
						</button>
					</TD>
				</TR>
			</table>
			<div id="tmpdiv"></div>
		</html:form>
		<logic:notEmpty name="save">
			<logic:equal name="save" value="ok">
				<script>
                      alert("添加成功！");
                      Close();
                      window.dialogArguments.document.getElementById('search_go').click();
                    </script>
			</logic:equal>
			<logic:equal name="save" value="on">
				<script>
                      alert("添加失败！");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
