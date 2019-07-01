<%@ page language="java" contentType="text/html; charset=GBK"%>

<!-- 家庭基本信息 -->
<table border="0" class="formlist" align="center" style="width: 100%">
	<thead>
		<tr onclick="showHiddenNr('tb_jtxx')" style="cursor: hand">
			<th colspan="4">
				<span>家庭信息</span>				
			</th>
		</tr>
	</thead>
	<tbody id='tb_jtxx'>
		<tr>
			<th width="16%">家庭电话</th>
			<td width="34%">
				<p id="p_lxdh1"></p>
			</td>	
			<th width="16%">邮政编码</th>
			<td>
				<p id="p_yb"></p>
			</td>
		</tr>
		<tr>
			<th width="16%">家庭地址</th>
			<td colspan="3">
				<p id="p_jtszd"></p>
			</td>
		</tr>
		<tr>
			<th width="16%">家庭经济状况</th>
			<td colspan="3">
				<p id="p_jjzk"></p>
			</td>
		</tr>
	</tbody>
</table>
<!-- 家庭基本信息 end-->

<!-- 家庭成员1 -->
<table border="0" class="formlist" align="center" style="width: 100%">
	<thead>
		<tr onclick="showHiddenNr('tb_jtcy1')" style="cursor: hand">
			<th colspan="4">
				<span>家庭成员1</span>				
			</th>
		</tr>
	</thead>
	<tbody id="tb_jtcy1">
		<tr>
			<th width="16%">成员姓名</th>
			<td width="34%">
				<p id="p_jtcy1_xm"></p>
			</td>	
			<th width="16%">与本人关系</th>
			<td>
				<p id="p_jtcy1_gx"></p>
			</td>
		</tr>
		<tr>
			<th width="16%">出生日期</th>
			<td>
				<p id="p_jtcy1_nl"></p>
			</td>
			<th width="16%">身份证号</th>
			<td>
				<p id="p_jtcy1_sfzh"></p>
			</td>
		</tr>
		<tr>
			<th width="16%">民族</th>
			<td>
				<p id="p_jtcy1_mzmc"></p>
			</td>
			<th width="16%">政治面貌</th>
			<td>
				<p id="p_jtcy1_zzmmmc"></p>
			</td>
		</tr>
		<tr>
			<th width="16%">职业</th>
			<td>
				<p id="p_jtcy1_zy"></p>
			</td>
			<th width="16%">职务</th>
			<td>
				<p id="p_jtcy1_zw"></p>
			</td>
		</tr>
		<tr>
			<th width="16%">工作单位电话</th>
			<td>
				<p id="p_jtcy1_lxdh1"></p>
			</td>
			<th width="16%">个人电话</th>
			<td>
				<p id="p_jtcy1_lxdh2"></p>
			</td>
		</tr>
		<tr>
			<th width="16%">工作地址</th>
			<td colspan="3">
				<p id="p_jtcy1_gzdz"></p>
			</td>
		</tr>
	</tbody>
</table>
<!-- 家庭成员1 end-->