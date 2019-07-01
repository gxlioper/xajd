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
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script language="javascript">
	function delInfo(){
		var xn = document.getElementById('xn').value;
		var nd = document.getElementById('nd').value;
		var xq = document.getElementById('xq').options[document.getElementById('xq').selectedIndex].text;
		var mes = "";
		if(nd!=null && nd!=""){
			mes += nd + "年度";
		}
		if(xn!=null && xn!=""){
			mes += xn + "学年";
		}		
		if(xq!=null && xq!=""){
			mes += xq ;
		}
		if(mes==""){
			alert("请设置条件！");
			return false;
		}
		if(confirm("您确定删除"+mes+"的全部信息吗?")){
			refreshForm("ticket_book_appsucess.do?method=delCpydInfo&doType=del");
		}
	}
</script>

	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<center>
			<html:form action="/chgPriseBat" method="post">
				<div class="title">
					<div class="title_img" id="title_m">
						当前所在位置：日常事务 - 票务管理 - 历史记录删除
					</div>
				</div>
				<fieldset>
					<legend>
						参数设置
					</legend>
					<table width="80%" align="center" class="tbstyle">
						<thead>
							<tr align="center">
								<td height="25" colspan="2">历史记录删除条件设置</td>
							</tr>
						</thead>
						<tr>
							<td width="45%" height="25" align="right">
								学年：
							</td>
							<td height="25" align="left">
								<html:select property="xn" styleId="xn">
									<html:options collection="xnndList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="45%" height="25" align="right">
								年度：
							</td>
							<td height="25" align="left">
								<html:select property="nd" styleId="nd">
									<html:options collection="xnndList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="45%" height="25" align="right">
								学期：
							</td>
							<td height="25" align="left">
								<html:select property="xq" styleId="xq">
									<option value="">
									</option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<thead>
							<tr>
								<td height="25" colspan="2" align="center">
									<button type="button" class="button2"
										onclick="delInfo()">
										删除</button>
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
				<logic:present name="result">
					<logic:equal name="result" value="true">
						<script>
						alert("操作成功!");
						Close();
						window.dialogArguments.document.getElementById('search_go').click();	
						</script>
					</logic:equal>
					<logic:equal name="result" value="false">
						<script>
						alert("操作失败!");
						Close();
						</script>
					</logic:equal>
				</logic:present>
			</html:form>
		</center>
	</body>
</html>
