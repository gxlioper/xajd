<%@ page language="java" contentType="text/html; charset=GBK"%>
<thead onclick="hiddenMk('mk_xsxx')">
	<tr><th colspan="4" style="cursor:hand"><span>学生基本信息</span></th></tr>
</thead>
<tbody id="mk_xsxx">
	<tr>
		<th width="20%">学号</th>
		<td width="30%">
			${wdpjXssqInfo.stuInfo.xh }
		</td>
		<th width="20%">姓名</th>
		<td width="30%">${wdpjXssqInfo.stuInfo.xm }</td>
	</tr>
	<tr>
		<th>性别</th>
		<td>${wdpjXssqInfo.stuInfo.xb }</td>
		<th>政治面貌</th>
		<td>${wdpjXssqInfo.stuInfo.zzmmmc }</td>
	</tr>
	<tr>
		<th>年级</th>
		<td>${wdpjXssqInfo.stuInfo.nj }</td>
		<th><bean:message key="lable.xb" /></th>
		<td>${wdpjXssqInfo.stuInfo.pjxymc }</td>
	</tr>
	<tr>
		<th>专业</th>
		<td>${wdpjXssqInfo.stuInfo.pjzymc }</td>
		<th>班级</th>
		<td>${wdpjXssqInfo.stuInfo.pjbjmc }</td>
	</tr>
	<tr>
		<th>身份证号</th>
		<td>${wdpjXssqInfo.stuInfo.sfzh }</td>
		<th>手机号码</th>
		<td>${wdpjXssqInfo.stuInfo.sjhm }</td>
	</tr>
</tbody>
