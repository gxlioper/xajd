<%@ page language="java" contentType="text/html; charset=GBK"%>
<tr>
	<th>������Դ</th>
	<td>
		<html:text name="rs" property="daly" styleId="daly" maxlength="100" />
	</td>
	<th>���������Ƿ���ȫ</th>
	<td>
		<html:select name="rs" property="zlsfqq" styleId="zlsfqq"
				style="width:160px">
				<html:option value="��">��</html:option>
				<html:option value="��">��</html:option>
		</html:select>
	</td>
</tr>
<tr>
	<th>������</th>
	<td>
		<html:text name="rs" property="jsr" styleId="jsr" maxlength="10" />
	</td>
	<th>������</th>
	<td>
		<html:text name="rs" property="glr" styleId="glr" maxlength="10" />
	</td>
</tr>
<tr>
	<th>��������λ��</th>
	<td>
		<html:text name="rs" property="dabcwz" styleId="dabcwz" maxlength="112" />
	</td>
	<th>�Ƿ��е�Ա����</th>
	<td>
		<html:select name="rs" property="sfydycl" styleId="sfydycl"
				style="width:160px">
				<html:option value="��">��</html:option>
				<html:option value="��">��</html:option>
			</html:select>
	</td>
</tr>
<tr>
	<th>��ע</th>
	<td colspan="3">
		<html:textarea name="rs" property="bz" styleId="bz" rows="5" style="width:100%" onblur="checkLen(this,300)"/>
	</td>
</tr>
							
