<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:form action="/jtpjsq" styleId="innerForm">
		<html:hidden property="jtpjdw" value="qt"/>
		<table width="" border="0" class="formlist">
			<tbody>
				<tr>
					<th width="20%" style="border-top: 0px;">
						��������
					</th>
					<td colspan="3" style="border-top: 0px;">
						${data.pjjtmc}
					</td>
				</tr>
				<tr>
					<th width="20%">
						������
					</th>
					<td colspan="3">
						${data.jtjj}
					</td>
				</tr>
				<tr>
					<th width="20%">
						����
					</th>
					<td colspan="3">
						${data.rs}
					</td>
				</tr>
		</table>
</html:form>