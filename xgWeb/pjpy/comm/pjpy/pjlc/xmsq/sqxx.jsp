<%@ page language="java" contentType="text/html; charset=GBK"%>
<thead onclick="hiddenMk('mk_sqxx')">
	<tr><th colspan="4" style="cursor:hand"><span>申请信息</span></th></tr>
</thead>
<tbody id="mk_sqxx">
	<tr>
		<th width="20%">审核步骤</th>
		<td colspan="3">${shlc }</td>
	</tr>
	<tr>
		<th>申请理由</th>
		<td colspan="3">
			<html:textarea property="sqly" cols="3" rows="4" styleId="sqly" onblur="chLeng(this,500)"
			 style="width: 95%;word-break:break-all;" value="${sqxmInfo.sqly}"></html:textarea>
		</td>
	</tr>
</tbody>
