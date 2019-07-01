<%@ page language="java" pageEncoding="GBK"%>
<%--福建工程学院--%>
<logic:equal value="10388" name="xxdm">
	<tr>
		<th>转档单位名称</th>
		<td>
			<html:text styleId="zddwmc" property="zddwmc" maxlength="15"/>
		</td>
		<th>机要通信号</th>
		<td>
			<html:text styleId="jyh" property="jyh" maxlength="30" onkeyup="value=value.replace(/[^a-zA-Z|\d]/g,'')"/>
		</td>	
		<th></th>
		<td></td>
	</tr>
</logic:equal>