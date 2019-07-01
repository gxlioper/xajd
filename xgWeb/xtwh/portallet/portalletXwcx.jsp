<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<base target="_self">
		<script language="javascript" src="js/function.js"></script>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<%@include file="/syscommon/pagehead_V4.ini"%>       
	</head>
	
	<body>			
			<html:form action="/xtwhPortallet.do?method=queryNews" method="post">
				<logic:notEmpty name="rs">
					<ul class="list">
					<logic:iterate name="rs" id="s">
						<li>
							<span class="type"></span>	
							<a 
								href="newsContent.do?newsId=<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"
								target="_blank"
								title="<logic:iterate id="v" name="s" offset="5" length="1"><bean:write name="v"/></logic:iterate>">
								<logic:iterate id="v" name="s" offset="2" length="1"><bean:write name="v"/><!-- ĞÂÎÅtitle --></logic:iterate> </a>														
							<span class="time"> <logic:iterate id="v" name="s"
									offset="3" length="1">
									<bean:write name="v" />
								</logic:iterate> 
							</span>
						</li>
						</logic:iterate>
					</ul>
				</logic:notEmpty>
			</html:form>
	</body>
</html>
									
