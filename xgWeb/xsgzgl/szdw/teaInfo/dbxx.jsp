<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"prefix="logic"%>
<thead onclick="hiddenMk('dbxx')" >
	<tr><th colspan="5" style="cursor:hand"><span>带班信息</span></th></tr>
</thead>
<tbody id="dbxx" style="display:none">
	<tr>
		<td colspan="5">
			<table width="100%">
				<thead>
					<tr>
						<td>班级代码</td>
						<td>班级名称</td>
						<td>带班标志</td>
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