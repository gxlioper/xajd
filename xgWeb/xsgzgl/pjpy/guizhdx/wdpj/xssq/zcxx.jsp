<%@ page language="java" contentType="text/html; charset=GBK"%>
<%--<logic:present name="wdpjXssqInfo.zcInfo">1111111111111111111111111111111111111111111111111111--%>
<thead onclick="hiddenMk('mk_zcxx')">
	<tr><th colspan="4" style="cursor:hand"><span>вш╡Бпео╒</span></th></tr>
</thead>
<tbody id="mk_zcxx" style="display: none">

	<logic:iterate name="wdpjXssqInfo" property="zcInfo" id="zcxxMap" indexId="index">
		
			<tr>
	
			<th width="20%">${zcxxMap.left.mc }</th>
			<td width="30%">${zcxxMap.left.fs }</td>
			<th width="20%">${zcxxMap.right.mc }</th>
			<td width="30%">${zcxxMap.right.fs }</td>
			</tr>
		
	</logic:iterate>
</tbody>
<%--</logic:present>--%>
