<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<thead onclick="hiddenMk('lxfs')">
	<tr><th colspan="5" style="cursor:hand"><span>��ϵ��ʽ</span></th></tr>
</thead>
<tbody id="lxfs" style="display:none">
	<tr>
		<th>�̶��绰</th>
		<td>
			<html:text property="lxdh" maxlength="16" value="${rs.lxdh}"></html:text>
		</td>
		<th>�ֻ�����</th>
		<td colspan="2">
			<html:text property="yddh" maxlength="11" value="${rs.yddh}"></html:text>
		</td>
	</tr>
	<tr>
		<th>��������</th>
		<td>
			<html:text property="dzyx" maxlength="50" value="${rs.dzyx}"></html:text>
		</td>
		<th>�ʱ�</th>
		<td colspan="2">
			<html:text property="yzbm" maxlength="6" value="${rs.yzbm}"></html:text>
		</td>
	</tr>
	<tr>
		<th>�칫�绰</th>
		<td>
			<html:text property="bgdh" maxlength="16" value="${rs.bgdh}"></html:text>
		</td>
		<th>����</th>
		<td colspan="2">
			<html:text property="cz" maxlength="25" value="${rs.cz}"></html:text>
		</td>
	</tr>
	<tr>
		<th>�칫�ص�</th>
		<td colspan="4">
			<html:text property="bgdd" maxlength="25" value="${rs.bgdd}" style="width:90%"></html:text>
		</td>
	</tr>
	<tr>
		<th>��ͥ��ַ</th>
		<td colspan="4">
			<html:text property="jtzz" maxlength="75" value="${rs.jtzz}" style="width:90%"></html:text>
		</td>
	</tr>
	<tr>
		<th>ͨѶ��ַ</th>
		<td colspan="4">
			<html:text property="txdz" maxlength="25" value="${rs.txdz}" style="width:90%"></html:text>
		</td>
	</tr>
</tbody>