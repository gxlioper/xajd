<%@ page language="java" pageEncoding="GBK"%>
<%--��������ѧԺ--%>
<logic:equal value="10388" name="xxdm">
	<tr>
		<th><bean:message key="lable.xb" />�����ƽ���</th>
		<td>
			<html:text name="rs" styleId="dayjr" property="dayjr" maxlength="15"/>
		</td>
		<th>��Ҫͨ�ź�</th>
		<td>
			<html:text name="rs" styleId="jyh" property="jyh" maxlength="30" onkeyup="value=value.replace(/[^a-zA-Z|\d]/g,'')"/>
		</td>	
	</tr>
	<tr>
		<th>У�ڵ������</th>
		<td>
			<html:text name="rs" maxlength="30" property="xndabh" styleId="xndabh"  onkeyup="value=value.replace(/[^a-zA-Z|\d]/g,'')"/>
		</td>
		<th>�Ǽ���</th>
		<td>
			<html:text name="rs" maxlength="15" property="djr" styleId="djr" />
		</td>	
	</tr>
	<tr>
		<th>�ռ���</th>
		<td>
			<html:text name="rs" maxlength="15" property="sjr" styleId="sjr" />
		</td>	
		<th>�ռ�ʱ��</th>
		<td>
			<html:text name="rs" maxlength="10" property="jssj" styleId="jssj" onclick="return showCalendar('jssj','y-mm-dd');" onblur="dateFormatChg(this)"/>
		</td>	
	</tr>
</logic:equal>