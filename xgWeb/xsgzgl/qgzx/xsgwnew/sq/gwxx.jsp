<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<logic:empty name="userlx">
	<logic:notEqual  name="lx" value="tz">
	<tr>
		<td colspan="4"><button class="btn_01" onclick="wdgwxzCxF();return false;" type="button">选择岗位</button><font color="red">${message}</font></td>
	</tr>
	</logic:notEqual>
</logic:empty>	

<tr>
	<th width="16%">
		学年
		<input type="hidden" id="gwdm" name="gwdm" value="${model.gwdm }">
	</th>
	<td width="34%">
		${model.xn }
	</td>
	<th width="16%">
		用人单位
	</th>
	<td width="34%">
		${model.yrdwmc }
	</td>
</tr>
<tr>
	<th width="16%">
		岗位名称
	</th>
	<td width="34%">
		${model.gwmc }
	</td>
	<th width="16%">
		岗位性质
	</th>
	<td width="34%">
		${model.gwxzmc }
	</td>
</tr>
<tr>
	<th width="16%">
		需求人数
	</th>
	<td width="34%">
		${model.xqrs}
	</td>

	<th width="16%">
		困难生数
	</th>
	<td width="34%">
		${model.knss}
	</td>
</tr>
<tr>
	<th width="16%">
		是否受岗位申请数限制
	</th>
	<td colspan="3">
		${model.sfsgwsqsxzmc}
	</td>
</tr>
<logic:equal name="isshow" value="true" >
<tr id="gwcjsxTr">
	<th width="16%">
		岗位酬金上限
	</th>
	<td width="34%" colspan="6">
		<span id="gwcjsxh">${model.gwcjsx}</span>
		<span>元/月  &nbsp;&nbsp;(该岗位每人每月酬金上限)</span>
	</td>
</tr>
</logic:equal>
<tr>
	<th width="16%">
		当前在岗人数
	</th>
	<td width="34%" colspan="3">
		${model.zgrs}
	</td>

</tr>
<tr>
	<th width="16%">
		岗位描述
	</th>
	<td width="34%" colspan="3">
		${model.gwms }
	</td>
</tr>
<tr>
	<th width="16%">
		岗位人员要求
	</th>
	<td width="34%" colspan="3">
		${model.gwryyq }
	</td>
</tr>
<tr>
	<th width="16%">
		岗位预期人员效果
	</th>
	<td width="34%" colspan="3">
		${model.gwyqryxg }
	</td>
</tr>
<tr>
	<th width="16%">
		备注
	</th>
	<td width="34%" colspan="3">
		${model.bz}
	</td>
</tr>
