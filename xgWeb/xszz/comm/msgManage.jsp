<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript">
function actionForward(){
	showTips('ͳ������������ȴ�......');
	var url = "/xgxt/commXszz.do?method=xmtjManage";
	refreshForm(url);
}
</script>
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">
	<body onload="actionForward()">
		<html:form action="/commXszz">
		
		</html:form>
	</body>
</html>