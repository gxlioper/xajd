<%@ page language="java" contentType="text/html; charset=GBK"%>
<table width="100%" border="0" class="formlist">
	<tbody id="tbody_jbxx">
		<tr>
			<th width="20%">
				项目名称
				<input type="hidden" id="xmdm" name="xmdm" value="${model.xmdm}">
			</th>
			<td width="34%">
				${model.xmmc}
			</td>
			<th width="13%">
				培训地点
			</th>
			<td width="37%" >
				${model.pxdd}
			</td>
		</tr>
		<tr>
			<th width="16%">
				培训时间
			</th>
			<td width="34%" colspan="3">
				${model.pxsj}
			</td>
			
		</tr>
		<tr>
			<th width="16%">
				培训简介
			</th>
			<td width="34%" colspan="3">
				${model.pxjj}
			</td>
		</tr>
		
		
	</tbody>
</table>
