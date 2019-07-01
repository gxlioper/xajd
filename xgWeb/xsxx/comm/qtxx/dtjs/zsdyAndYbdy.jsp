<%@ page language="java" contentType="text/html; charset=GBK"%>

<!-- 正式党员信息 -->
<table border="0" class="formlist" align="center" style="width: 100%">
	<thead>
		<tr onclick="showHiddenNr('tb_dyxx')" style="cursor: hand">
			<th colspan="4">
				<span>正式党员</span>				
			</th>
		</tr>
	</thead>
	<tbody id="tb_dyxx">
		<tr>
			<th width="16%">学年</th>
			<td width="34%">
				<p id="p_dyxx_xn"></p>
			</td>	
			<th width="16%">学期</th>
			<td>
				<p id="p_dyxx_xqmc"></p>
			</td>
		</tr>
		<tr>
			<th width="16%">年度</th>
			<td>
				<p id="p_dyxx_nd"></p>
			</td>
			<th width="16%">入党时间</th>
			<td>
				<p id="p_dyxx_rdsj"></p>
			</td>
		</tr>
		<tr>
			<th width="16%">备注</th>
			<td colspan="3">
				<p id="p_dyxx_bz" class="breakword"></p>
			</td>
		</tr>
	</tbody>
</table>
<!-- 正式党员信息 end-->

<!-- 预备党员信息 -->
<table border="0" class="formlist" align="center" style="width: 100%">
	<thead>
		<tr onclick="showHiddenNr('tb_ybdy')" style="cursor: hand">
			<th colspan="4">
				<span>预备党员</span>				
			</th>
		</tr>
	</thead>
	<tbody id="tb_ybdy">
		<tr>
			<th width="16%">学年</th>
			<td width="34%">
				<p id="p_ybdy_xn"></p>
			</td>	
			<th width="16%">学期</th>
			<td>
				<p id="p_ybdy_xqmc"></p>
			</td>
		</tr>
		<tr>
			<th width="16%">年度</th>
			<td>
				<p id="p_ybdy_nd"></p>
			</td>
			<th width="16%">目前是否预备党员</th>
			<td>
				<p id="p_ybdy_zzzt"></p>
			</td>
		</tr>
		<tr>
			<th width="16%">开始时间</th>
			<td>
				<p id="p_ybdy_kssj"></p>
			</td>
			<th width="16%">结束时间</th>
			<td>
				<p id="p_ybdy_jssj"></p>
			</td>
		</tr>
		<tr>
			<th width="16%">备注</th>
			<td colspan="3">
				<p id="p_ybdy_bz" class="breakword"></p>
			</td>
		</tr>
	</tbody>
</table>
<!-- 预备党员信息 end-->