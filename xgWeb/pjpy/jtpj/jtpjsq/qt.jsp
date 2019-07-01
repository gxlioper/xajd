<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:form action="/jtpjsq" styleId="innerForm">
		<html:hidden property="jtpjdw" value="qt"/>
		<table width="" border="0" class="formlist">
				<tr>
					<th width="20%" style="border-top: 0px;">
						<span class="red">*</span>集体名称
					</th>
					<td colspan="3" style="border-top: 0px;">
						<html:hidden property="pjjtid" styleId="pjjtid" value="-1"/>
						<html:text property="pjjtmc" styleId="pjjtmc" maxlength="50" style="width: 92%;"></html:text>
					</td>
				</tr>
				<tr>
					<th width="20%">
						集体简介
						<br />
						<span class="red">(限500字)</span>
					</th>
					<td colspan="3">
						<html:textarea property="jtjj" rows="4" cols="70" styleId="jtjj"  onblur="checkLen(this,500);"></html:textarea>
					</td>
				</tr>
				<tr>
					<th width="20%">
						人数
					</th>
					<td colspan="3">
						<html:text property="rs" styleId="rs" onblur="checkInt(this);" maxlength="5" style="width: 8%;"></html:text>
					</td>
				</tr>
	</table>
</html:form>