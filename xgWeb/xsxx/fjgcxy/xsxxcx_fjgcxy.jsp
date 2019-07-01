<%@ page language="java" pageEncoding="GBK"%>
<%--福建工程学院--%>
<logic:equal value="10388" name="xxdm">
	<tr style="display:none">
		<th>是否港澳台胞</th>
		<td>
			<html:select property="sfgat" styleId="sfgat" style="width:150px">
				<html:option value=""></html:option>
				<html:option value="是">是</html:option>
				<html:option value="否">否</html:option>
			</html:select>
		</td>
		<th>是否少数民族</th>
		<td>
			<html:select property="sfssmz" styleId="sfssmz" style="width:150px">
				<html:option value=""></html:option>
				<html:option value="是">是</html:option>
				<html:option value="否">否</html:option>
			</html:select>
		</td>	
		<th></th>
		<td></td>	
	</tr>
</logic:equal>