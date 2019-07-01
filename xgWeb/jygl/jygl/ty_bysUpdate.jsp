<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<logic:equal value="13275" name="xxdm" scope="session">
			<jsp:include page="/jygl/zjxy/bysUpdate.jsp" />
		</logic:equal>
		
		<logic:equal value="10388" name="xxdm" scope="session">
			<jsp:include page="/jygl/fjgc/bysUpdate.jsp" />
		</logic:equal>
		
<%--		<logic:equal value="11733" name="xxdm" scope="session">--%>
<%--			<jsp:include page="/jygl/shcbys/bysUpdate.jsp" />--%>
<%--		</logic:equal>--%>
		
		<logic:notEqual value="10388" name="xxdm" scope="session">
			<logic:notEqual value="13275" name="xxdm" scope="session">	
<%--				<logic:notEqual value="11733" name="xxdm" scope="session">--%>
				<jsp:include page="/jygl/jygl/bysUpdate.jsp" />
<%--				</logic:notEqual>--%>
			</logic:notEqual>
		</logic:notEqual>
	</body>
</html>	


