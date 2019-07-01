<%@ page language="java" pageEncoding="GBK"%>
<%--福建工程学院--%>
<logic:equal value="10388" name="xxdm">
	<tr>
		<th><bean:message key="lable.xb" />档案移交人</th>
		<td>
			<html:text name="rs" styleId="dayjr" property="dayjr" maxlength="15"/>
		</td>
		<th>机要通信号</th>
		<td>
			<html:text name="rs" styleId="jyh" property="jyh" maxlength="30" onkeyup="value=value.replace(/[^a-zA-Z|\d]/g,'')"/>
		</td>	
	</tr>
	<tr>
		<th>校内档案编号</th>
		<td>
			<html:text name="rs" maxlength="30" property="xndabh" styleId="xndabh"  onkeyup="value=value.replace(/[^a-zA-Z|\d]/g,'')"/>
		</td>
		<th>登记人</th>
		<td>
			<html:text name="rs" maxlength="15" property="djr" styleId="djr" />
		</td>	
	</tr>
	<tr>
		<th>收件人</th>
		<td>
			<html:text name="rs" maxlength="15" property="sjr" styleId="sjr" />
		</td>	
		<th>收件时间</th>
		<td>
			<html:text name="rs" maxlength="10" property="jssj" styleId="jssj" onclick="return showCalendar('jssj','y-mm-dd');" onblur="dateFormatChg(this)"/>
		</td>	
	</tr>
</logic:equal>