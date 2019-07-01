        <%@ page language="java" contentType="text/html; charset=gb2312"%>
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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
		function chgEditer(obj){
			var url = "eWebEditor/eWebEditor.jsp?color=" + obj.value;
			eWebEditor1.location = url;
		}
function pubNews(){
	if(document.getElementById("newsTitle").value == ""){
		alert("请填写新闻标题！");
			document.getElementById("newsTitle").focus();
		return false;
	}
	if(document.getElementById("mxdx").value == ""){
		alert("请选择所属模块！");
			document.getElementById("mxdx").focus();
		return false;
	}
	document.getElementById('content1').value = frames('eWebEditor1').getHTML();
	if(document.getElementById("content1").value == ""){
		alert("请填写新闻正文！");
		return false;
	}
	refreshForm('saveNewsManage.do');
	
}

function cha(url){
			refreshForm(url);
		}
	</script>
<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<html:form action="/newsManage" method="post">
		
			<div class="title">
				<div class="title_img" id="title_m">
					相关新闻
				</div>
			</div>
				<logic:empty name="inserted">
			<fieldset>
				<legend>
					新闻修改
				</legend>
				<TABLE class="tbstyle" width="100%">
					<TR>
						<TD align=right width="100">
							所属模块：
						</TD>
						<TD align="left" colspan="3">
								<html:select name="commanForm" property="xmdm" styleId="mxdx"
									onchange="cha('newsPub.do?tagId='+this.value);">
									<html:options collection="modList" property="gnmkdm"
										labelProperty="gnmkmc" />
								</html:select>
							<html:hidden name="commanForm" property="xmdm" />
							<input type="hidden" name="newsId" id="newsId"
								value="<bean:write name="newsId" />" />
						&nbsp;&nbsp;&nbsp;
						</TD>
					</TR>
					<TR>
						<TD align=right width="100">
							新闻标题：
						</TD>
						<TD align=left colspan="3">
							<input type="text" name="newsTitle" id="newsTitle"
								style="width:100%" value="<bean:write name="newstit"/>">
						</TD>
					</TR>
					<TR>
						<TD align=right width="100">
							编辑内容：
						</TD>
						<TD align=center colspan="3">
								<INPUT type="hidden" name="content1"
									value="<bean:write name="content1" />">
							<IFRAME ID="eWebEditor1" src="BatEditor" frameborder="0"
								scrolling="no" width="100%" height="350"></IFRAME>
						</TD>
					</TR>
					<TR>
						<TD colspan=4 align=center>
							<button class="button2" onclick="pubNews();return false;">
								发 布
							</button>
							<INPUT type=reset name=b2 value="重填" class="button2">
						</TD>
					</TR>
				</TABLE>
			</fieldset>
			</logic:empty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("提交成功！");
    dialogArgumentsQueryChick();
	Close();
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("提交失败！");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
