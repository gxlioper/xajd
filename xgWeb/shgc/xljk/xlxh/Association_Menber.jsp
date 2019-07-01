<%@ page language="java"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
 
<html> 
	<head>
		<title>JSP for AssociationForm form</title>
	</head>
	<body>
		<html:form action="/[ACTION_PATH]">
			<html:text property="STAND_A"/><html:errors property="STAND_A"/><br/>
			<html:text property="STAND_B"/><html:errors property="STAND_B"/><br/>
			<html:text property="MEMBER_AGE"/><html:errors property="MEMBER_AGE"/><br/>
			<html:text property="act"/><html:errors property="act"/><br/>
			<html:text property="STAND_D"/><html:errors property="STAND_D"/><br/>
			<html:text property="MEMBER_ID"/><html:errors property="MEMBER_ID"/><br/>
			<html:text property="XN_ID"/><html:errors property="XN_ID"/><br/>
			<html:text property="MEMBER_NAME"/><html:errors property="MEMBER_NAME"/><br/>
			<html:text property="STAND_C"/><html:errors property="STAND_C"/><br/>
			<html:text property="MENBER_SEX"/><html:errors property="MENBER_SEX"/><br/>
			<html:submit/><html:cancel/>
		</html:form>
	</body>
</html>

