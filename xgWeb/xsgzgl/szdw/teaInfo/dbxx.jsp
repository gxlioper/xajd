<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"prefix="logic"%>
<thead onclick="hiddenMk('dbxx')" >
	<tr><th colspan="5" style="cursor:hand"><span>������Ϣ</span></th></tr>
</thead>
<tbody id="dbxx" style="display:none">
	<tr>
		<td colspan="5">
			<table width="100%">
				<thead>
					<tr>
						<td>�༶����</td>
						<td>�༶����</td>
						<td>�����־</td>
					</tr>
				</thead>
				<tbody>
					<logic:iterate id="c" name="classList">
						<tr>
							<td>${c.bjdm }</td>
							<td>${c.bjmc }</td>
							<td>${c.dbbz }</td>
						</tr>
					</logic:iterate>
				</tbody>
			</table>
		</td>
	</tr>
</tbody>