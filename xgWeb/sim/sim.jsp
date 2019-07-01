<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.net.URLEncoder"%>
<%@ include file="common.jsp" %>

<%
String targetUrl = request.getParameter("targetUrl");
if (targetUrl==null || targetUrl.equals("")) {
	targetUrl = defaultTargetUrl;
}
if (targetUrl.startsWith("http://")||targetUrl.startsWith("https://")){
	targetUrl = CommonUtil.encodeServiceURI(targetUrl);
}

String loginUrl = request.getParameter("loginUrl");
if (loginUrl==null || loginUrl.equals("")) {
	loginUrl = defaultLoginUrl;
}
if (loginUrl.startsWith("http://")||loginUrl.startsWith("https://")){
	loginUrl = CommonUtil.encodeServiceURI(loginUrl);
}

String errorCode = request.getParameter("errorCode");
if (CommonUtil.isBlank(errorCode)) {

} else {
	loginUrl = CommonUtil.decodeServiceURI(loginUrl);
	response.sendRedirect(loginUrl);
	return;
}

String ticket = request.getParameter("ticket");
if (!CommonUtil.isBlank(ticket)) {
	// 解析ticket
	String serviceUrl = appServerUrlPrefix+"sim/sim.jsp?targetUrl="+targetUrl+"&loginUrl="+loginUrl;
	String validateUrl = casServerUrlPrefix+"serviceValidate?service="+URLEncoder.encode(serviceUrl, "UTF-8")
			+ "&ticket="+ticket;
	String validateXML = CommonUtil.validateTicket(validateUrl);
	
	String error = XmlUtils.getTextForElement(validateXML, "authenticationFailure");
	
	if (!CommonUtil.isBlank(error))
		return;
	
	final String principal = XmlUtils.getTextForElement(validateXML, "user");
	final Map<String,Object> attributes = XmlUtils.extractCustomAttributes(validateXML);
	
	if (attributes!=null && attributes.size()>0){
		String localAccount = attributes.containsKey("localAccount") ? attributes.get("localAccount").toString().trim() : "";
		String localPass = attributes.containsKey("localPass") ? attributes.get("localPass").toString().trim() : "";
		
		String loginParams = attributes.containsKey("loginParams") ? attributes.get("loginParams").toString().trim() : "";
	
		session.setAttribute("CAS_SESSION_TICKET", ticket);
		session.setAttribute("CAS_SESSION_PRINCIPAL", principal);
		session.setAttribute("CAS_SESSION_USER", localAccount);
		session.setAttribute("CAS_SESSION_PASS", localPass);
		session.setAttribute("CAS_SESSION_PARAMS", loginParams);
	}
}

// 到CAS认证
if (session.getAttribute("CAS_SESSION_TICKET")==null 
		|| CommonUtil.isBlank(session.getAttribute("CAS_SESSION_TICKET").toString())) {
	// 判断CAS是否有效
	String casUrl = casServerUrlPrefix+"login";
	if(CommonUtil.isCasAlive(casUrl, 0)) {
		String serviceUrl = appServerUrlPrefix+"sim/sim.jsp?targetUrl="+targetUrl+"&loginUrl="+loginUrl;
		String casLoginUrl = casServerUrlPrefix+"login?service="+URLEncoder.encode(serviceUrl, "UTF-8");
		response.sendRedirect(casLoginUrl);
	} else {
		loginUrl = CommonUtil.decodeServiceURI(loginUrl);
		response.sendRedirect(loginUrl);
	}
} else {
	if (session.getAttribute("LOCAL_SESSION_IS_LOGON")==null
			|| CommonUtil.isBlank(session.getAttribute("LOCAL_SESSION_IS_LOGON").toString())) {
		response.sendRedirect("simLogin.jsp?targetUrl="+targetUrl+"&loginUrl="+loginUrl);
	} else {
		targetUrl = CommonUtil.decodeServiceURI(targetUrl);
		response.sendRedirect(targetUrl);
	}
}
return;
%>