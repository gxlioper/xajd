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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<body>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
		
			function uploadFile() {
				var tmp = document.getElementById("file").value;
				if (tmp.value == "" || tmp.substring(tmp.length - 4, tmp.length).toLowerCase() != ".jpg") {
					alert("您选择的文件不合法，只能上传.jpg格式的图片，您选择的是" +  tmp.substring(tmp.length - 4, tmp.length).toLowerCase() +"格式的图片！");
					document.getElementById("file").value = "";
					return false;
				} else {
					var xsxh =  document.getElementById("pkxsxh").value;
					if(xsxh==""){
						alert("没有找到上传照片的学生 ，请返回上一页");
					}else{
						document.forms[0].action = "/xgxt/bysjytjb.do?act=sczp&doType=upload";
						document.forms[0].submit();
						return true;
					}
				}
			}
		</script>
		<html:form action='/uploadPicture.do' method='post'
			enctype='multipart/form-data'>
			<logic:present name="moditag">
				<input type="hidden" id="moditag" name="moditag"
					value="<bean:write name="moditag"/>" />
			</logic:present>
			<logic:present name="pkxsxh">
				<input type="hidden" id="pkxsxh" name="pkxsxh"
					value="<bean:write name="pkxsxh"/>" />
			</logic:present>
			<logic:notPresent name="pkxsxh">
				<input type="hidden" id="pkxsxh" name="pkxsxh"
					value="" />
			</logic:notPresent>
			<logic:notPresent name="moditag">
				<input type="hidden" id="moditag" name="moditag" value="" />
			</logic:notPresent>
			<div class="title">
				<div class="title_img" id="title_m">
				当前位置：就业管理 - 就业协议方案 - 毕业生就业推荐-上传照片
				</div>
			</div>
			<logic:notEmpty name="dataImported">
				<script>
	alert("操作成功！");
	//document.forms[0].action="/xgxt/bysjytjb.do?act=query&doType=query";
	//document.forms[0].submit();
	</script>
			</logic:notEmpty>
			<div id="search_m">
				<div class="searchcontent">
					<br />
					<table width="99%" class="tbstyle">
						<thead>
							<tr>
								<td align="center">
									请选择要导入的图片
								</td>
							</tr>
						</thead>
						<tr>
							<td align="center">
								<input type="file" name="file" id="file" value="" />
							</td>
						</tr>
						<tr>
							<td align="center">
								<button class="button2" onclick="uploadFile()">
									上传
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" value="重置" class="button2">
							</td>
						</tr>
					</table>
					<br />
				</div>
			</div>
		</html:form>
	</body>
</html>
