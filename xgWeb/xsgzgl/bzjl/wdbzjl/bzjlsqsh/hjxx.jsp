<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<logic:iterate id="i" name="hjjgList">
	<tr>
		<td>${i.hjmc}</td>
		<td>${i.hjsj}</td>
		<td>${i.fjdw}</td>
	</tr>									
</logic:iterate>
