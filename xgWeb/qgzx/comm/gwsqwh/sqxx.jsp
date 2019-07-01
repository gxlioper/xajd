<%@ page language="java" contentType="text/html; charset=GBK"%>
<thead onclick="hiddenMk('mk_sqxx')">
	<tr><th colspan="4" style="cursor:hand"><span>申请信息</span></th></tr>
</thead>
<tbody id="mk_sqxx">
	<tr>
		<th width="20%"><span class="red">*</span>可参加勤工助学时间</th>
		<td width="30%">
			<input type="text" id="save_kcjqgzxsj" name="save_kcjqgzxsj" value="${rs.kcjqgzxsj }" maxlength="200"/>
			(例：周一上，周二下...)
			<input type="hidden" id="pkValue" name="pkValue" value="${rs.pkValue }"/>
		</td>
		<th width="20%">
			是否贫困生
		</th>
		<td width="30%">
			<input type="text" name="save_sfpks" id="save_sfpks" value="${rs.sfpks}" readonly="readonly"/>
		</td>
	</tr>
	<tr>
		<th>
			在校月均生活费
		</th>
		<td>
			<input type="text" id="save_yjshf" name="save_yjshf" value="${rs.yjshf }" onkeyup="value=value.replace(/[^\d]/g,'') "/>
		</td>
		<th>
			家庭情况
		</th>
		<td>
			<input type="checkbox" id="save_sfgr" name="save_sfgr" value="是" <logic:equal value="是" name="rs" property="sfgr">checked</logic:equal> />孤儿
			<input type="checkbox" id="save_sfdq" name="save_sfdq" value="是" <logic:equal value="是" name="rs" property="sfdq">checked</logic:equal> />单亲<br/>
			<input type="checkbox" id="save_sfdbh" name="save_sfdbh" value="是" <logic:equal value="是" name="rs" property="sfdbh">checked</logic:equal> />低保户
			<input type="checkbox" id="save_sfyfdx" name="save_sfyfdx" value="是" <logic:equal value="是" name="rs" property="sfyfdx">checked</logic:equal> />优扶对象
		</td>
	</tr>
	<tr>
		<th>
			爱好特长
		</th>
		<td colspan="3">
			<input type="text" id="save_yhtc" name="save_yhtc" value="${rs.yhtc }" maxlength="100" style="width:95%" />
		</td>
	</tr>
	<tr>
		<th>
			<span class="red">*</span>申请理由
		</th>
		<td colspan="3" nowrap="nowrap">
			<textarea rows="5" id="save_xssq" name="save_xssq" style="width: 95%;word-break:break-all;" onblur="chLeng(this,'500')" >${rs.xssq }</textarea>
		</td>
	</tr>
	<tr>
		<th>
			备注
		</th>
		<td colspan="3" nowrap="nowrap">
			<textarea rows="5" id="save_bz" name="save_bz" style="width: 95%;word-break:break-all;" onblur="chLeng(this,'500')" >${rs.bz }</textarea>
		</td>
	</tr>
</tbody>
