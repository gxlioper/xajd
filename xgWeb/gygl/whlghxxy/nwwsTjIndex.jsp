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
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
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
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/nwwstj.js"></script>
	<script language="javascript">
</script>
	<body>
		<form action="/whlghxxy_Gygl" method="post">
			<input type="hidden" name="dm" value="" />			
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
					<bean:write name="titName" scope="request" />
				</div>
			</div>
			<div class="buttontool" id="btn"
				style="position: absolute;left:1%;top:100px" width="100%">
				<button class="button2" onclick="showAytj(420,220);">
					按月统计住宿情况
				</button>
				<button class="button2" onclick="showAztj(420,180);">
					按周统计卫生情况
				</button>
				<button class="button2" onclick="showZXFDYTj();">
					按周、系、辅导员统计
				</button>
				<button class="button2" onclick="showZXTj()">
					按周、系统计
				</button>
			</div>
			 <script language="javascript">
              document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
              document.getElementById("btn").style.width = "96%";
              window.setInterval("initBTNTool('btn')",1);
             </script>
		</form>
	</body>
</html>
