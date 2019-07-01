<%@ page language="java" pageEncoding="GBK"%>
<%--福建工程学院--%>
<logic:equal value="10388" name="xxdm">
	<tr>
		<th>机要通信编号</th>
		<td>
			<html:text name="rs" styleId="jyh" property="jyh" maxlength="30" onkeyup="value=value.replace(/[^a-zA-Z|\d]/g,'')"/>
		</td>
		<th>校内档案编号</th>
		<td>
			<html:text name="rs" styleId="dabh" property="dabh" maxlength="30" onkeyup="value=value.replace(/[^a-zA-Z|\d]/g,'')"/>
		</td>	
	</tr>
	<tr>
		<th>登记人</th>
		<td>
			<html:text name="rs" maxlength="15" property="daclr" styleId="daclr" />
		</td>
		<th>收件人</th>
		<td>
			<html:text name="rs" maxlength="15" property="sjr" styleId="sjr" />
		</td>	
	</tr>
	<tr>
		<th>收件机关</th>
		<td>
			<html:text name="rs" maxlength="100" property="sjjgmc" styleId="sjjgmc" />
		</td>
		<th>收件时间</th>
		<td>
			<html:text name="rs" maxlength="10" property="sjsj" styleId="sjsj" onclick="return showCalendar('sjsj','y-mm-dd');" onblur="dateFormatChg(this)"/>
		</td>	
	</tr>
</logic:equal>