<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<logic:present name="stuZcxx">
<thead onclick="hiddenMk('mk_zcxx')">
	<tr><th colspan="4" style="cursor:hand"><span>вш╡Бпео╒</span></th></tr>
</thead>
<tbody id="mk_zcxx" style="display: none">
	<%
		List stuZcxx = (List)request.getAttribute("stuZcxx");
	 %>
	<logic:iterate name="stuZcxx" id="zcxxMap" indexId="index">
		<%if(index%2 == 0){ %>
			<tr>
		<%} %>
			<th width="20%">${zcxxMap.mc }</th>
			<td width="30%">${zcxxMap.fs }</td>
			
		<%if(index%2 == 0 && stuZcxx.size() == (index+1)){ %>
			<th></th>
			<td></td>
			</tr>
		<%} %>	
		<%if(index%2 == 1){ %>
		</tr>
		<%} %>
	</logic:iterate>
	
</tbody>
</logic:present>
