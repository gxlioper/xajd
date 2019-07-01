<%@ page language="java" pageEncoding="GBK"%>
<%--福建工程学院--%>
<logic:equal value="10388" name="xxdm">
	<tr>
		<th>是否港澳台胞</th>
		<td>
			<html:select property="sfgat" name="rs" styleId="sfgat">
				<html:option value=""></html:option>
				<html:option value="是">是</html:option>
				<html:option value="否">否</html:option>
			</html:select>
		</td>
		<th>是否少数民族</th>
		<td colspan="2">
			<html:select property="sfssmz" name="rs" styleId="sfssmz">
				<html:option value=""></html:option>
				<html:option value="是">是</html:option>
				<html:option value="否">否</html:option>
			</html:select>
		</td>		
	</tr>
	<tr>
		<th>高考考生号</th>
		<td>
			<html:text property="ksh" name="rs" styleId="ksh" maxlength="32" onkeyup="value=value.replace(/[^a-zA-Z|\d]/g,'') "/>
		</td>
		<th>毕业中学</th>
		<td colspan="2">
			<html:text property="rxqdw" name="rs" styleId="rxqdw" maxlength="127"/>
		</td>
	</tr>
</logic:equal>