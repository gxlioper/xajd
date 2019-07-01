<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- Í·ÎÄ¼þ -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	</head>
	<body>
		<logic:equal value="10388" name="xxdm" scope="session">
			<jsp:include page="/jygl/fjgc/jyxyView.jsp" />
		</logic:equal>
		<logic:equal value="11733" name="xxdm" scope="session">
			<jsp:include page="/jygl/shcbys/jyxyView.jsp" />
		</logic:equal>	
		
		<logic:notEqual value="10388"  name="xxdm" scope="session">
			<logic:notEqual value="10388"  name="xxdm" scope="session">
				<jsp:include page="/jygl/jygl/jyxyView.jsp"/>
			</logic:notEqual>
		</logic:notEqual>
	</body>
</html>