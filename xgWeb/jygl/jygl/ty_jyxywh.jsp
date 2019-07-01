<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
	<logic:equal value="10388" name="xxdm" scope="session">
		<jsp:include page="/jygl/fjgc/jyxywh.jsp" />
	</logic:equal>
	<logic:equal value="11733" name="xxdm" scope="session">
		<jsp:include page="/jygl/shcbys/jyxywh.jsp" />
	</logic:equal>
	<logic:notEqual value="10388"  name="xxdm" scope="session">
		<logic:notEqual value="11733" name="xxdm" scope="session">
		<jsp:include page="/jygl/jygl/jyxywh.jsp"/>
		</logic:notEqual>
	</logic:notEqual>
