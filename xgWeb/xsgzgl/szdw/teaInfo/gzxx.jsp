<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"prefix="logic"%>
<thead onclick="hiddenMk('gzxx')" >
	<tr><th colspan="5" style="cursor:hand"><span>������Ϣ</span></th></tr>
</thead>
<tbody id="gzxx" style="display:none">
	<tr>
		<th>���ڲ���</th>
		<td>
			<html:select property="bmdm" value="${rs.bmdm}" style="width:200px" onmouseover="void(0);">
				<html:option value=""></html:option>
				<!-- �������ϴ�ѧ -->
				<logic:equal name="xxdm" value="11417">
					<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
				</logic:equal>
				<!-- �Ǳ������ϴ�ѧ -->
				<logic:notEqual name="xxdm" value="11417">
					<html:options collection="bmList" property="bmdm" labelProperty="bmmc"/>
				</logic:notEqual>

			</html:select>
		</td>
		<th>�ϼ���λ</th>
		<td colspan="2">
			<html:text property="sjdw" value="${rs.sjdw}" maxlength="25"></html:text>
		</td>
	</tr>
	<tr>
		<th>ְ��</th>
		<td>
			<html:select property="zw" value="${rs.zw}">
				<html:option value=""></html:option>
				<logic:present name="zwList">
					<html:options collection="zwList" property="zwdm" labelProperty="zwmc"/>
				</logic:present>
			</html:select>
		</td>
		<th>��ְʱ��</th>
		<td colspan="2">
			<html:text property="zwrzsj" readonly="true" value="${rs.zwrzsj}"
					   styleId="zwrzsj" onclick="showCalendar(this.id,'y-mm-dd')" 
			></html:text>
		</td>
	</tr>
	<tr>
		<th>ѧ������<br/>�о�����</th>
		<td colspan="4">
			<html:text property="xsgzyjfx" value="${rs.xsgzyjfx}" maxlength="20" style="width:80%"></html:text>
		</td>
	</tr>
	<tr>
		<th>ְ��</th>
		<td>
			<html:text property="zc" value="${rs.zc}" maxlength="10"></html:text>
		</td>
		<th>ְ�ƻ��ʱ��</th>
		<td colspan="2">
			<html:text property="jsrzsj" readonly="true" value="${rs.jsrzsj}"
					   styleId="jsrzsj" onclick="showCalendar(this.id,'y-mm-dd')" 
			></html:text>
		</td>
	</tr>
	<tr>
		<th>�Ƿ����<br/>Ժϵ��ʦ</th>
		<td>
			<html:radio name="rs" property="sfjryx" value="true" />��
			<html:radio name="rs" property="sfjryx" value="false" />��
		</td>
		<th></th>
		<td colspan="2">
			
		</td>
	</tr>
</tbody>