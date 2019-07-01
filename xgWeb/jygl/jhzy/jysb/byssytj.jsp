<%@ include file="/syscommon/pagehead.ini"%>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<html>
	<head>
		<title>就业管理信息系统</title>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<script language="javascript">
		function expdata(){
			var nd = document.getElementById('nd').value;
			var xymc = document.getElementById('xydm').options[document.getElementById('xydm').selectedIndex].innerText;
			if(nd == ''){
				alert('年度不能为空！');
				return false;
			}
			document.forms[0].action = "/xgxt/jhzyjysb.do?method=byssytj&act=expdata&xymc="+xymc;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
	</script>
<%--	<body onload="jsl('qyrs');jsl('yprs');jsl('jyrs')">--%>
<body onload="">
		<html:form action="/jhzyjysb" method="post">
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td>
							年度：
							<html:select property="nd" style="width:80px" styleId="nd">
								<html:options collection="ndList" property="nd"
									labelProperty="nd" />
							</html:select>
							&nbsp;&nbsp; <bean:message key="lable.xsgzyxpzxy" />名称：
							<logic:equal value="xy" name="userType">
								<html:select property="xydm" styleId="xydm" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</logic:equal>
							<logic:notEqual value="xy" name="userType">
								<html:select property="xydm" styleId="xydm">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</logic:notEqual>

							&nbsp;&nbsp; 生源地：
							<html:select property="syddm" styleId="syddm">
								<html:option value=""></html:option>
								<html:options collection="ssList" property="ssdm"
									labelProperty="ssmc" />
							</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="expdata();" style="width:80px">
								生成报表
							</button>
						</td>
					</tr>
				</thead>
			</table>
		</html:form>
	</body>
</html>

