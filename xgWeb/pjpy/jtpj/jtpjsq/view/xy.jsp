<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:form action="/jtpjsq" styleId="innerForm">
	<table width="" border="0" class="formlist">
		<html:hidden property="jtpjdw" value="xy"/>
		<html:hidden property="pjjtmc" styleId="pjjtmc" />
		<tbody>
			<tr>
				<th width="20%" style="border-top: 0px;">
					${xylable}
				</th>
				<td colspan="3" style="border-top: 0px;">
					${data.pjjtmc}
				</td>
			</tr>
			<tr>
				<th width="20%">
					${xylable}ÈËÊı
				</th>
				<td width="30%">
					${xyrs}
				</td>
				<th width="20%">
					ÇŞÊÒÊı
				</th>
				<td width="30%">
					${qss}
				</td>
			</tr>
	</table>
</html:form>