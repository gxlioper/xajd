<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script>
function returnPage(){
	window.open(document.getElementById('url').value);
}
</script>


<body onload="returnPage()">
	<% 
		String url = request.getParameter("url");
	%>
<input type="hidden" id="url" value=<%= url %>>
		
</body>

