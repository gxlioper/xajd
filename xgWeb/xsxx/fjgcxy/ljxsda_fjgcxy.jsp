<%@ page language="java" pageEncoding="GBK"%>
<%--��������ѧԺ--%>
<logic:equal value="10388" name="xxdm">
	<tr>
		<th>��Ҫͨ�ű��</th>
		<td>
			<html:text name="rs" styleId="jyh" property="jyh" maxlength="30" onkeyup="value=value.replace(/[^a-zA-Z|\d]/g,'')"/>
		</td>
		<th>У�ڵ������</th>
		<td>
			<html:text name="rs" styleId="dabh" property="dabh" maxlength="30" onkeyup="value=value.replace(/[^a-zA-Z|\d]/g,'')"/>
		</td>	
	</tr>
	<tr>
		<th>�Ǽ���</th>
		<td>
			<html:text name="rs" maxlength="15" property="daclr" styleId="daclr" />
		</td>
		<th>�ռ���</th>
		<td>
			<html:text name="rs" maxlength="15" property="sjr" styleId="sjr" />
		</td>	
	</tr>
	<tr>
		<th>�ռ�����</th>
		<td>
			<html:text name="rs" maxlength="100" property="sjjgmc" styleId="sjjgmc" />
		</td>
		<th>�ռ�ʱ��</th>
		<td>
			<html:text name="rs" maxlength="10" property="sjsj" styleId="sjsj" onclick="return showCalendar('sjsj','y-mm-dd');" onblur="dateFormatChg(this)"/>
		</td>	
	</tr>
</logic:equal>