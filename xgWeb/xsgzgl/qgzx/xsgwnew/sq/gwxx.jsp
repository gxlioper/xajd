<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<logic:empty name="userlx">
	<logic:notEqual  name="lx" value="tz">
	<tr>
		<td colspan="4"><button class="btn_01" onclick="wdgwxzCxF();return false;" type="button">ѡ���λ</button><font color="red">${message}</font></td>
	</tr>
	</logic:notEqual>
</logic:empty>	

<tr>
	<th width="16%">
		ѧ��
		<input type="hidden" id="gwdm" name="gwdm" value="${model.gwdm }">
	</th>
	<td width="34%">
		${model.xn }
	</td>
	<th width="16%">
		���˵�λ
	</th>
	<td width="34%">
		${model.yrdwmc }
	</td>
</tr>
<tr>
	<th width="16%">
		��λ����
	</th>
	<td width="34%">
		${model.gwmc }
	</td>
	<th width="16%">
		��λ����
	</th>
	<td width="34%">
		${model.gwxzmc }
	</td>
</tr>
<tr>
	<th width="16%">
		��������
	</th>
	<td width="34%">
		${model.xqrs}
	</td>

	<th width="16%">
		��������
	</th>
	<td width="34%">
		${model.knss}
	</td>
</tr>
<tr>
	<th width="16%">
		�Ƿ��ܸ�λ����������
	</th>
	<td colspan="3">
		${model.sfsgwsqsxzmc}
	</td>
</tr>
<logic:equal name="isshow" value="true" >
<tr id="gwcjsxTr">
	<th width="16%">
		��λ�������
	</th>
	<td width="34%" colspan="6">
		<span id="gwcjsxh">${model.gwcjsx}</span>
		<span>Ԫ/��  &nbsp;&nbsp;(�ø�λÿ��ÿ�³������)</span>
	</td>
</tr>
</logic:equal>
<tr>
	<th width="16%">
		��ǰ�ڸ�����
	</th>
	<td width="34%" colspan="3">
		${model.zgrs}
	</td>

</tr>
<tr>
	<th width="16%">
		��λ����
	</th>
	<td width="34%" colspan="3">
		${model.gwms }
	</td>
</tr>
<tr>
	<th width="16%">
		��λ��ԱҪ��
	</th>
	<td width="34%" colspan="3">
		${model.gwryyq }
	</td>
</tr>
<tr>
	<th width="16%">
		��λԤ����ԱЧ��
	</th>
	<td width="34%" colspan="3">
		${model.gwyqryxg }
	</td>
</tr>
<tr>
	<th width="16%">
		��ע
	</th>
	<td width="34%" colspan="3">
		${model.bz}
	</td>
</tr>
