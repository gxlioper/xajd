<%@ page language="java" pageEncoding="GBK"%>
<%--��������ѧԺ--%>
<logic:equal value="10388" name="xxdm">
	<tr>
		<th>�Ƿ�۰�̨��</th>
		<td>
			<html:select property="sfgat" name="rs" styleId="sfgat">
				<html:option value=""></html:option>
				<html:option value="��">��</html:option>
				<html:option value="��">��</html:option>
			</html:select>
		</td>
		<th>�Ƿ���������</th>
		<td colspan="2">
			<html:select property="sfssmz" name="rs" styleId="sfssmz">
				<html:option value=""></html:option>
				<html:option value="��">��</html:option>
				<html:option value="��">��</html:option>
			</html:select>
		</td>		
	</tr>
	<tr>
		<th>�߿�������</th>
		<td>
			<html:text property="ksh" name="rs" styleId="ksh" maxlength="32" onkeyup="value=value.replace(/[^a-zA-Z|\d]/g,'') "/>
		</td>
		<th>��ҵ��ѧ</th>
		<td colspan="2">
			<html:text property="rxqdw" name="rs" styleId="rxqdw" maxlength="127"/>
		</td>
	</tr>
</logic:equal>