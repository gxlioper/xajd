<%@ page language="java" pageEncoding="GBK"%>
<%--��������ѧԺ--%>
<logic:equal value="10388" name="xxdm">
	<tr style="display:none">
		<th>�Ƿ�۰�̨��</th>
		<td>
			<html:select property="sfgat" styleId="sfgat" style="width:150px">
				<html:option value=""></html:option>
				<html:option value="��">��</html:option>
				<html:option value="��">��</html:option>
			</html:select>
		</td>
		<th>�Ƿ���������</th>
		<td>
			<html:select property="sfssmz" styleId="sfssmz" style="width:150px">
				<html:option value=""></html:option>
				<html:option value="��">��</html:option>
				<html:option value="��">��</html:option>
			</html:select>
		</td>	
		<th></th>
		<td></td>	
	</tr>
</logic:equal>