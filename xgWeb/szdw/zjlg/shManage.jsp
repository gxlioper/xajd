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
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
        function yz(){
       		var xjchdm = document.getElementById("xjchdm").value;
       		if((xjchdm == null) || (xjchdm=="")){
       			alert("请先选择要申请的奖学金!");
       			return false;
       		}
			document.forms[0].action = "/xgxt/szdw_zjlg.do?method=xjchsh";
		 	document.forms[0].submit();
	}
    </script>
	<base target="_self">
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>


	<body>
		<html:form action="/szdw_zjlg" method="post">
			<table width="100%" class="tbstyle">
				<thead>
					<tr valign="middle" >
						<td align="center">
							<b style="font-size:14">请选择要审核的先进称号</b>
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center">
						<html:select property="xjchdm" styleId="xjchdm">
								<html:option value="">---------请选择--------</html:option>
								<html:option value="mffdy">模范辅导员</html:option>
								<html:option value="yxbzr">优秀班主任</html:option>
							</html:select>
					</td>
				</tr>
				<tr>
					<td align="center">
						<input  class="button2" onclick=yz()
							style="width:80px" value="确  定" />
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>
