<%@ page language="java" pageEncoding="GBK"%>
<%--��������ѧԺ--%>
<logic:equal value="10388" name="xxdm">
	<tr>
		<th>ת����λ����</th>
		<td>
			<html:text styleId="zddwmc" property="zddwmc" maxlength="15"/>
		</td>
		<th>��Ҫͨ�ź�</th>
		<td>
			<html:text styleId="jyh" property="jyh" maxlength="30" onkeyup="value=value.replace(/[^a-zA-Z|\d]/g,'')"/>
		</td>	
		<th></th>
		<td></td>
	</tr>
</logic:equal>