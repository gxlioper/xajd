<%@ page language="java" contentType="text/html; charset=GBK"%>
<thead onclick="hiddenMk('mk_sqxx')">
	<tr><th colspan="4" style="cursor:hand"><span>������Ϣ</span></th></tr>
</thead>
<tbody id="mk_sqxx">
	<tr>
		<th width="20%"><span class="red">*</span>�ɲμ��ڹ���ѧʱ��</th>
		<td width="30%">
			<input type="text" id="save_kcjqgzxsj" name="save_kcjqgzxsj" value="${rs.kcjqgzxsj }" maxlength="200"/>
			(������һ�ϣ��ܶ���...)
			<input type="hidden" id="pkValue" name="pkValue" value="${rs.pkValue }"/>
		</td>
		<th width="20%">
			�Ƿ�ƶ����
		</th>
		<td width="30%">
			<input type="text" name="save_sfpks" id="save_sfpks" value="${rs.sfpks}" readonly="readonly"/>
		</td>
	</tr>
	<tr>
		<th>
			��У�¾������
		</th>
		<td>
			<input type="text" id="save_yjshf" name="save_yjshf" value="${rs.yjshf }" onkeyup="value=value.replace(/[^\d]/g,'') "/>
		</td>
		<th>
			��ͥ���
		</th>
		<td>
			<input type="checkbox" id="save_sfgr" name="save_sfgr" value="��" <logic:equal value="��" name="rs" property="sfgr">checked</logic:equal> />�¶�
			<input type="checkbox" id="save_sfdq" name="save_sfdq" value="��" <logic:equal value="��" name="rs" property="sfdq">checked</logic:equal> />����<br/>
			<input type="checkbox" id="save_sfdbh" name="save_sfdbh" value="��" <logic:equal value="��" name="rs" property="sfdbh">checked</logic:equal> />�ͱ���
			<input type="checkbox" id="save_sfyfdx" name="save_sfyfdx" value="��" <logic:equal value="��" name="rs" property="sfyfdx">checked</logic:equal> />�ŷ�����
		</td>
	</tr>
	<tr>
		<th>
			�����س�
		</th>
		<td colspan="3">
			<input type="text" id="save_yhtc" name="save_yhtc" value="${rs.yhtc }" maxlength="100" style="width:95%" />
		</td>
	</tr>
	<tr>
		<th>
			<span class="red">*</span>��������
		</th>
		<td colspan="3" nowrap="nowrap">
			<textarea rows="5" id="save_xssq" name="save_xssq" style="width: 95%;word-break:break-all;" onblur="chLeng(this,'500')" >${rs.xssq }</textarea>
		</td>
	</tr>
	<tr>
		<th>
			��ע
		</th>
		<td colspan="3" nowrap="nowrap">
			<textarea rows="5" id="save_bz" name="save_bz" style="width: 95%;word-break:break-all;" onblur="chLeng(this,'500')" >${rs.bz }</textarea>
		</td>
	</tr>
</tbody>
