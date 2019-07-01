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
					<span class="red">*</span>${xylable}
				</th>
				<td colspan="3" style="border-top: 0px;">
					<html:select property="pjjtid" styleId="pjjtid" value="${xydm}" onchange="loadXypjxx();">
						<html:option value=""></html:option>
						<html:options collection="xyList" property="xydm" labelProperty="xymc" />
					</html:select>
				</td>
			</tr>
			<tr>
				<th width="20%">
					${xylable}ÈËÊý
				</th>
				<td width="30%">
					${xyrs}
					<html:hidden property="rs" value="${xyrs}" />
				</td>
				<th width="20%">
					ÇÞÊÒÊý
				</th>
				<td width="30%">
					${qss}
				</td>
			</tr>
	</table>
</html:form>