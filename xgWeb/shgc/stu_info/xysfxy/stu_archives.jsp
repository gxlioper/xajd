<%@ page language="java" contentType="text/html; charset=GBK"%>
<tr>
	<th>��ҵʱ��</th>
	<td>
		<html:text name="rs" styleId="bynf" property="bynf" onclick="return showCalendar('bynf','y-mm-dd');" readonly='true'/>					
	</td>
	<th>��Դ��</th>
	<td>
		${rs.syd}					
	</td>
<tr>
	<th>�����Ƿ���У</th>
	<td>
		<html:select name="rs" property="dasfzx" styleId="dasfzx"
				style="width:160px">
				<html:option value="����У">����У</html:option>
				<html:option value="��У">��У</html:option>
			</html:select>
	</td>
	<th>ת��ʱ��</th>
	<td>
		<html:text name="rs"
			onclick="return showCalendar('zysj','y-mm-dd');" property="zysj"
			styleId="zysj" readonly="true" />
	</td>
</tr>

<tr>
	<th>���������Ƿ���ȫ</th>
	<td>
		<html:select name="rs" property="zlsfqq" styleId="zlsfqq"
				style="width:160px">
				<html:option value="��">��</html:option>
				<html:option value="��">��</html:option>
			</html:select>
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
	<th>ת����λ����</th>
	<td>
		<html:text name="rs" styleId="zddwmc" property="zddwmc" maxlength="50" />
	</td>
	<th>���޴����¼</th>
	<td>
		<html:select name="rs" property="dkjl" styleId="dkjl"
				style="width:160px">
				<html:option value="��">��</html:option>
				<html:option value="��">��</html:option>
			</html:select>
	</td>
</tr>
<tr>
	<th>ѧ��</th>
	<td >
		<html:text name="rs" styleId="xl" property="xl" maxlength='10'/>
	</td>
	<th>��λ����</th>
	<td >
		<html:text name="rs" styleId="dwxz" property="dwxz" maxlength='10'/>
	</td>
</tr>
<tr>
	<th>��ע</th>
	<td colspan="3">
		<html:textarea name="rs" styleId="bz" property="bz" rows="5" style="width:100%" onblur="chLeng(this,10)"/>
	</td>
</tr>
