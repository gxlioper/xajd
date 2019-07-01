<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：wujian -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		function expHjxsmdtj(){
			var url = "pjpy_tjcx_hjxsmdtj.do?method=expHjmdtj";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";	
		}
		</script>
	</head>
	<body >

		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/pjpy_hjxsmdtj" method="post">
			<div class="buttonbox">
				评奖学年：<html:select name="hjxsmdForm" property="xn" style="width:100px">
					<html:options collection="xnList" property="pjsj" labelProperty="pjsj"/>
				</html:select><%--
				<bean:message key="lable.xb" />名称：
				<html:select name="hjxsmdForm" property="xy" style="width:100px">
					<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
				</html:select>
				
				--%>
					<a href="#" class="btn_dc" onclick="expHjxsmdtj();return false;">导出获奖学生名单</a>
			</div>
		
		</html:form>
	</body>
	
</html>