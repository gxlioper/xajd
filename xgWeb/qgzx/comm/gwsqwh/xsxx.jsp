<%@ page language="java" contentType="text/html; charset=GBK"%>
<thead onclick="hiddenMk('mk_xsxx')">
	<tr><th colspan="4" style="cursor:hand"><span>学生基本信息</span></th></tr>
</thead>
<tbody id="mk_xsxx">
	<tr>
		<logic:equal value="stu" scope="session" name="userType">
			<th width="20%">学号</th>
			<td width="30%">
				<input type="hidden" name="save_xh" id="save_xh" value="${rs.xh }" />
				${rs.xh }
			</td>
		</logic:equal>
		<logic:notEqual value="stu" scope="session" name="userType">
			<th width="20%"><font style="color: red">*</font>学号</th>
			<td width="30%">
				<logic:equal value="sh" scope="request" name="act">
					<input type="hidden" name="save_xh" id="save_xh" value="${rs.xh }" />
					${rs.xh }
				</logic:equal>
				<logic:notEqual value="sh" scope="request" name="act">
				<input type="text" name="save_xh" id="save_xh" readonly="readonly" value="${rs.xh }"
							onchange="checkXhExists($('getStuInfo').value);"
							onkeypress="autoFillTeaInfo(event.keyCode)" />
				<button type="button" onclick="showTopWin('/xgxt/qgzx_gwsqwh.do?method=getStuInfo',750,550);"
					class="btn_01" id="buttonFindStu">
					选择
				</button>
				</logic:notEqual>
			</td>
		</logic:notEqual>
		<th width="20%">姓名</th>
		<td width="30%">${rs.xm }</td>
	</tr>
	<tr>
		<th>性别</th>
		<td>${rs.xb }</td>
		<th>政治面貌</th>
		<td>${rs.zzmmmc }</td>
	</tr>
	<tr>
		<th>年级</th>
		<td>${rs.nj }</td>
		<th><bean:message key="lable.xb" /></th>
		<td>${rs.xymc }</td>
	</tr>
	<tr>
		<th>专业</th>
		<td>${rs.zymc }</td>
		<th>班级</th>
		<td>${rs.bjmc }</td>
	</tr>
	<tr>
		<th>身份证号</th>
		<td>${rs.sfzh }</td>
		<th>手机号码</th>
		<td>${rs.sjhm }</td>
	</tr>
</tbody>
