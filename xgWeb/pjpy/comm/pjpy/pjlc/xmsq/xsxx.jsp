<%@ page language="java" contentType="text/html; charset=GBK"%>
<thead onclick="hiddenMk('mk_xsxx')">
	<tr><th colspan="4" style="cursor:hand"><span>ѧ��������Ϣ</span></th></tr>
</thead>
<tbody id="mk_xsxx">
	<tr>
		<logic:notPresent name="lssq">
			<th width="20%">ѧ��</th>
			<td width="30%">
				<input type="hidden" name="xh" value="${stuJbxx.xh }" />
				${stuJbxx.xh }
			</td>
		</logic:notPresent>
		<logic:present name="lssq">
			<th width="20%"><font style="color: red">*</font>ѧ��</th>
			<td width="30%">
				<input type="text" name="xh" id="xh" readonly="readonly" value="${stuJbxx.xh }"
							onchange="checkXhExists($('getStuInfo').value);"
							onkeypress="autoFillTeaInfo(event.keyCode)" />
				<button type="button" onclick="showTopWin('/xgxt/pjpy_comm_xmsq.do?method=getStuInfo',750,550);"
					class="btn_01" id="buttonFindStu">
					ѡ��
				</button>
			</td>
		</logic:present>
		<th width="20%">����</th>
		<td width="30%">${stuJbxx.xm }</td>
	</tr>
	<tr>
		<th>�Ա�</th>
		<td>${stuJbxx.xb }</td>
		<th>������ò</th>
		<td>${stuJbxx.zzmmmc }</td>
	</tr>
	<tr>
		<th>�꼶</th>
		<td>${stuJbxx.nj }</td>
		<th><bean:message key="lable.xb" /></th>
		<td>${stuJbxx.pjxymc }</td>
	</tr>
	<tr>
		<th>רҵ</th>
		<td>${stuJbxx.pjzymc }</td>
		<th>�༶</th>
		<td>${stuJbxx.pjbjmc }</td>
	</tr>
	<tr>
		<th>���֤��</th>
		<td>${stuJbxx.sfzh }</td>
		<th>�ֻ�����</th>
		<td>${stuJbxx.sjhm }</td>
	</tr>
</tbody>
