<%@ page language="java" pageEncoding="GBK"%>
<%--北京青年政治学院--%>
<logic:equal value="11626" name="xxdm">
	<tr>
		<th>家庭人均月收入</th>
		<td>
			<html:text property="jtrjsr" name="rs" styleId="jtrjsr" maxlength="20" onkeyup="value=value.replace(/[^\d|.]/g,'') " disabled="true"/>(元)
		</td>
		<th></th>
		<td>
			
		</td>		
	</tr>
</logic:equal>