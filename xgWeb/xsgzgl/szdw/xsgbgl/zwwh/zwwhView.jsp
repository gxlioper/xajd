<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<tr>
	<th width="16%">
		<font color="red">*</font>ְ������
	</th>
	<td>
		<html:select property="lxdm" name="model" styleId="lxdm" onchange="on_change()" style="width: 100px;">
			<option value=""></option>
			<html:options collection="zwlx" property="lxdm" labelProperty="lxmc"/>
		</html:select>
	</td>
	<th width="16%">
		<font color="red">*</font>ְ������
	</th>
	<td>
		<html:select property="zwid" name="model" styleId="zwid" onchange="on_getZwwh_model()" style="width: 250px;">
			<option value=""></option>
			<html:options collection="zwList" property="zwid" labelProperty="zwmc"/>
		</html:select>							
	</td>
</tr>
<tr>
	<th width="16%">
		ְ��ְ��
	</th>
	<td colspan="3" >
		${model.zwzz}
	</td>
</tr>
<tr>
	<th width="16%">
		��ע
	</th>
	<td colspan="3">
		${model.bz}
	</td>
</tr>
				
					