<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:form action="/jtpjsq" styleId="innerForm">
	<table width="" border="0" class="formlist">
		<html:hidden property="jtpjdw" value="qs"/>
		<html:hidden property="pjjtmc" styleId="pjjtmc" />
		<html:hidden property="pjjtid"  styleId="pjjtid"/>
		<html:hidden property="rs"  styleId="rowConut"/><!-- ������ֵ -->
		
		<tbody>
			<tr>
				<th width="20%" style="border-top: 0px;">
					¥��
				</th>
				<td width="30%" style="border-top: 0px;">
					${ldmc }
					<input type="hidden" id="lddm"/>
				</td>
				<th width="20%" style="border-top: 0px;">
					���Һ�
				</th>
				<td width="30%" style="border-top: 0px;">
					${qsh}
					<input type="hidden" id="qsh" />
				</td>
			</tr>
			<tr>
				<th width="20%">
					����ѧ����Ϣ
				</th>
				<td colspan="3">
					<div id="qsxsxx">
						
					</div>
				</td>
			</tr>
	</table>
</html:form>