<%@ page language="java" contentType="text/html; charset=GBK"%>
<thead onclick="hiddenMk('mk_sqxx')">
	<tr><th colspan="4" style="cursor:hand"><span>申请信息</span></th></tr>
</thead>
<tbody id="mk_sqxx">
	<tr>
		<th width="20%"><span class="red">*</span>可参加勤工助学时间</th>
		<td width="30%">
			${rs.kcjqgzxsj }
		</td>
		<th width="20%">
			是否贫困生
		</th>
		<td width="30%">
			${rs.sfpks}
		</td>
	</tr>
	<tr>
		<th>
			在校月均生活费
		</th>
		<td>
			${rs.yjshf }
		</td>
		<th>
			家庭情况
		</th>
		<td>
			<input type="checkbox" id="save_sfgr" name="save_sfgr" value="是" <logic:equal value="是" name="rs" property="sfgr">checked</logic:equal> disabled="disabled"/>孤儿
			<input type="checkbox" id="save_sfdq" name="save_sfdq" value="是" <logic:equal value="是" name="rs" property="sfdq">checked</logic:equal> disabled="disabled"/>单亲<br/>
			<input type="checkbox" id="save_sfdbh" name="save_sfdbh" value="是" <logic:equal value="是" name="rs" property="sfdbh">checked</logic:equal> disabled="disabled"/>低保户
			<input type="checkbox" id="save_sfyfdx" name="save_sfyfdx" value="是" <logic:equal value="是" name="rs" property="sfyfdx">checked</logic:equal> disabled="disabled"/>优扶对象
		</td>
	</tr>
	<tr>
		<th>
			爱好特长
		</th>
		<td colspan="3">
			${rs.yhtc }
		</td>
	</tr>
	<tr>
		<th>
			<span class="red">*</span>申请理由
		</th>
		<td colspan="3" nowrap="nowrap">
			<textarea rows="5" id="save_xssq" name="save_xssq" style="width: 95%;word-break:break-all;" onblur="chLeng(this,'500')" disabled="disabled">${rs.xssq }</textarea>
		</td>
	</tr>
	<tr>
		<th>
			备注
		</th>
		<td colspan="3" nowrap="nowrap">
			<textarea rows="5" id="save_bz" name="save_bz" style="width: 95%;word-break:break-all;" onblur="chLeng(this,'500')" disabled="disabled">${rs.bz }</textarea>
		</td>
	</tr>
</tbody>
