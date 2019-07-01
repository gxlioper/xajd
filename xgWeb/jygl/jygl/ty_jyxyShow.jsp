<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	
	
	
		<logic:equal value="10388" name="xxdm" scope="session">
			<jsp:forward page="/jygl/fjgc/jyxyShow.jsp" />
		</logic:equal>
		<logic:equal value="11733" name="xxdm" scope="session">
			<jsp:forward page="/jygl/shcbys/jyxyShow.jsp" />
		</logic:equal>	
		<logic:notEqual value="11733"  name="xxdm">
			<logic:notEqual value="10388"  name="xxdm">
				<%@ include file="/jygl/jygl/jyxyShow.jsp" %>
			</logic:notEqual>
		</logic:notEqual>
