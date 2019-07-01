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
		<script type="text/javascript">
			function ImpFile(){
				var tmp = document.getElementById("file").value;
				document.getElementById("filePath").value = tmp;
				if (tmp.value == "" || tmp.substring(tmp.length - 4, tmp.length).toLowerCase() != ".xls") {
					alert("您选择的文件不合法，请重新选择！");
					document.getElementById("file").value = "";
					return false;
				} else {
					showTips();
					document.forms[0].action = "/xgxt/hzzy_cjimpcommit.do";
					document.forms[0].submit();
					return true;
				}
			}
		</script>
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
		<html:form action='/hzzy_cjimp.do' method='post' enctype='multipart/form-data'>
			<div class="title"> 
  			<div class="title_img" id="title_m"> 当前所在位置：评奖评优 - 信息维护 - 成绩导入</div> 
			</div> 
			<logic:equal value="ok" name="dataImported">
				<script>
					alert("数据导入成功！");
					window.close();
				</script>
			</logic:equal>
			<logic:equal value="no" name="dataImported">
				<script>
					alert("数据导入失败！");
					window.close();
				</script>
			</logic:equal>
			<div id="search_m">
				<div class="searchcontent">
					<br />
					<table width="99%" class="tbstyle">
						<thead>
							<tr>
								<td align="center">
									请选择要导入的文件
								</td>
							</tr>
						</thead>
						<tr>
							<td align="center">
								<input type="hidden" name="filePath" id="filePath" />
								<input type="file" name="file" id="file" value="" />
							</td>
						</tr>
						<tr>
							<td align="center">
								<button class="button2" onclick="ImpFile()">
									数据导入
								</button>
								<button class="button2" onclick="refreshForm('/xgxt/hzzy_cjQlcommit.do')">
									数据整理
								</button>
							</td>
						</tr>
					</table>
					<br />
				</div>
			</div>
			<logic:present name="del">
					<logic:equal name="del" value="ok">
						<script>
							alert("整理成功!");	
						</script>
					</logic:equal>
					<logic:equal name="del" value="no">
						<script>
							alert("更新成功!");
						</script>
					</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
