<%@ page language="java" contentType="text/html; charset=GBK"%>
<table class="formlist" border="0" align="center" style="width: 100%">
	<logic:notEmpty name="jxjList">
	<thead>
		<tr>
			<th colspan="4">
				<span>��ѧ��</span>				
			</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<th>
				<div align="center">��ȡʱ��</th>
			<th>
				<div align="center">����ѧ��</th>
		</tr>
		<logic:iterate name="jxjList" id="jxj">
		<tr>
			<td >
				<div align="center">${jxj.zqxx }</div>
			</td>
			<td colspan="3">
				<div align="center">${jxj.xmmc }</div>
			</td>
		</tr>
		</logic:iterate>
	</tbody>
	</logic:notEmpty>
	<logic:notEmpty name="rychList">
	<thead>
		<tr>
			<th colspan="4">
				<span>���������ƺ�</span>				
			</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<th>
				<div align="center">��ȡʱ��</th>
			<th>
				<div align="center">����ѧ��</th>
		</tr>
		<logic:iterate name="rychList" id="rych">
		<tr>
			<td >
				<div align="center">${rych.zqxx }</div>
			</td>
			<td colspan="3">
				<div align="center">${rych.xmmc }</div>
			</td>
		</tr>
		</logic:iterate>
	</tbody>
	</logic:notEmpty>
</table>