<%@ page language="java" contentType="text/html; charset=GBK"%>
<thead onclick="hiddenMk('mk_sqxx')">
	<tr><th colspan="4" style="cursor:hand"><span>������Ϣ</span></th></tr>
</thead>
<tbody id="mk_sqxx">
	<tr>
		<th width="20%">��˲���</th>
		<td colspan="3">${shlc }</td>
	</tr>
	<tr>
		<th>��������</th>
		<td colspan="3">
			<html:textarea property="sqly" cols="3" rows="4" styleId="sqly" onblur="chLeng(this,500)"
			 style="width: 95%;word-break:break-all;" value="${sqxmInfo.sqly}"></html:textarea>
		</td>
	</tr>
</tbody>
