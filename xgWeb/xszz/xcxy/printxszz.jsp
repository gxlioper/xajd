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
<html:html>
<base target="_self" />
<head>


	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function yz(){
			var nd = document.getElementById('nd').value;
			var xmdm = document.getElementById('xmdm').value;
			var bbys = document.getElementById('bbys').value;
			if(nd == null || nd == "" ||
			   xmdm == null || xmdm == ''|| 
			   bbys == null || bbys == '' ){
				alert("所有录入均不能为空!");
				return false;
			}
			document.forms[0].action = "/xgxt/xcxyXszz.do?method=excutePrint";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
	</script>
</head>

<body>
	<html:form action="xcxyXszz.do" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置：学生资助 - 统计分析 - 学生报表打印
			</div>
		</div>

		<fieldset>
			<legend>
				资助报表
			</legend>
			<table class="tbstyle" width="100%">
				<thead>
					<tr>
						<td>
							年度：
							<html:select property="nd" style="width:60px">
								<html:options collection="ndList" property="nd"
									labelProperty="nd" />
							</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;资助项目：
							<html:select property="xmdm" style="width:180px"
								onchange="refreshForm('/xgxt/xcxyXszz.do?method=printXszz')">
								<html:option value=""></html:option>
								<html:options collection="zzxmList" property="xmdm"
									labelProperty="xmmc" />
							</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;报表类型：
							<html:select property="bbys" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="bblxList" property="dm"
									labelProperty="mc" />
							</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" style="height:20px" id="print"
								onclick="yz()">
								生成报表
							</button>
						</td>
					</tr>
				</thead>
			</table>
		</fieldset>
	</html:form>
</body>
</html:html>
