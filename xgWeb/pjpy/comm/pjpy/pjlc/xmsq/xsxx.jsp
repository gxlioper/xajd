<%@ page language="java" contentType="text/html; charset=GBK"%>
<thead onclick="hiddenMk('mk_xsxx')">
	<tr><th colspan="4" style="cursor:hand"><span>学生基本信息</span></th></tr>
</thead>
<tbody id="mk_xsxx">
	<tr>
		<logic:notPresent name="lssq">
			<th width="20%">学号</th>
			<td width="30%">
				<input type="hidden" name="xh" value="${stuJbxx.xh }" />
				${stuJbxx.xh }
			</td>
		</logic:notPresent>
		<logic:present name="lssq">
			<th width="20%"><font style="color: red">*</font>学号</th>
			<td width="30%">
				<input type="text" name="xh" id="xh" readonly="readonly" value="${stuJbxx.xh }"
							onchange="checkXhExists($('getStuInfo').value);"
							onkeypress="autoFillTeaInfo(event.keyCode)" />
				<button type="button" onclick="showTopWin('/xgxt/pjpy_comm_xmsq.do?method=getStuInfo',750,550);"
					class="btn_01" id="buttonFindStu">
					选择
				</button>
			</td>
		</logic:present>
		<th width="20%">姓名</th>
		<td width="30%">${stuJbxx.xm }</td>
	</tr>
	<tr>
		<th>性别</th>
		<td>${stuJbxx.xb }</td>
		<th>政治面貌</th>
		<td>${stuJbxx.zzmmmc }</td>
	</tr>
	<tr>
		<th>年级</th>
		<td>${stuJbxx.nj }</td>
		<th><bean:message key="lable.xb" /></th>
		<td>${stuJbxx.pjxymc }</td>
	</tr>
	<tr>
		<th>专业</th>
		<td>${stuJbxx.pjzymc }</td>
		<th>班级</th>
		<td>${stuJbxx.pjbjmc }</td>
	</tr>
	<tr>
		<th>身份证号</th>
		<td>${stuJbxx.sfzh }</td>
		<th>手机号码</th>
		<td>${stuJbxx.sjhm }</td>
	</tr>
</tbody>
