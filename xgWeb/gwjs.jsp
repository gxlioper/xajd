<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="xgxt.wjsc.WjscService" />
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="java.util.List" />
<jsp:directive.page import="xgxt.utils.String.StringUtils"/>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<html>

<%
	WjscService service = new WjscService();
	String userName = session.getAttribute("userName") != null ? session.getAttribute("userName").toString() : "";
	List<HashMap<String, String>> rs = service.queryWjscList(userName);
	
 %>
		
			<%
				if (rs != null && rs.size() > 0) {
				%>
				<ul class="index_list_01">
				<% 
					for (int i=0;i<5;i++) {
						if (rs.size()>i && rs.get(i) != null && StringUtils.isNotNull(rs.get(i).get("wjh"))) {
						%>
							<li>
								<a href="fileView.do?wjh=<%=rs.get(i).get("wjh") %>&yd=yes"
										target="_blank" >&nbsp;<%=rs.get(i).get("wjh") %></a><span
									class="time"><%=rs.get(i).get("wjffsj") %></span>
							</li>
						<%
						} else {
						%>
							<li>&nbsp;&nbsp;&nbsp;</li>
						<%
						}
					}
					%>
					</ul>
					<%
				} else {
				%>
				<ul class="index_list_01">
					<li>&nbsp;&nbsp;&nbsp;ÔÝÎÞ¹«ÎÄ
					</li>
					<li>&nbsp;&nbsp;&nbsp;
					</li>
					<li>&nbsp;&nbsp;&nbsp;
					</li>
				</ul>
							<%
				}
			 %>
		
</html>
