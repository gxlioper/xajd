<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<body onload="chgysf()">
<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }">
	<html:form action="/pjpyjgsdxwh" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置：评奖评优 - 参数设置 - 条件设置
			</div>
		</div>
		<table width='100%' align='center' class='tbstyle' id="rsTable">
				<thead>
				<tr style="width:22px">
					<td colspan="2" align="center">
						<b>条件修改</b>
					</td>
				</tr>
				</thead>
			<tr style="width:22px">
				<td align="right">
					奖学金：
				</td>
				<td align="left">
					<bean:write name="rs" property="jxjmc"/>
				</td>
			</tr>
			<tr style="width:22px">
				<td align="right">
					学年：
				</td>
				<td align="left">
					<bean:write name="rs" property="xn"/>
				</td>
			</tr>
			<tr style="width:22px">
				<td align="right">
					条件字段名：
				</td>
				<td align="left">
					<div id="zdm"><bean:write name="rs" property="zdm"/></div>
				</td>
			</tr>
			<%--<tr style="width:22px">
				<td align="right">
					<font color="red">*</font>字段操作：
				</td>
				<td align="left">
				<html:select property="zdcz" styleId="zdcz" style="width:100px">
						<html:option value=""></html:option>
						<html:option value="sum">总和</html:option>
						<html:option value="avg">平均值</html:option>
						<html:option value="max">最大值</html:option>
						<html:option value="min">最小值</html:option>
				</html:select>
				</td>
			</tr>
			--%><tr style="width:22px">
				<td align="right">
					<font color="red">*</font>条件：
				</td>
				<td align="left">
					<html:select property="ysf" styleId="ysf" style="width:100px">
						<html:option value=""></html:option>
						<html:option value="&gt;=">大于或等于</html:option>
						<html:option value="&gt;">大于</html:option>
						<html:option value="=">等于</html:option>
						<html:option value="&lt;">小于</html:option>
						<html:option value="&lt;=">小于或等于</html:option>
								</html:select>
				</td>
			</tr>
			<tr style="width:22px">
				<td align="right">
					<font color="red">*</font>对应数据：
				</td>
				<td align="left">
					<html:text property="val" styleId="val" style="width:120px"></html:text>
				</td>
			</tr>
			<%--<tr style="width:22px">
				<td align="right">
					违处处分限制：
				</td>
				<td align="left">
					<html:radio property="sfwj" value="1"></html:radio>包含&nbsp;&nbsp;&nbsp;
												<html:radio property="sfwj" value="0"></html:radio>不包含
				</td>
			</tr>
		--%></table>
		<br/>
		<div class="buttontool" align="center" >
				<button class="button2" id="btn_save" onclick="savetj('jxjsztjedit.do')" style="width:80px">
					保存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" id="btn_close" onclick="window.close();return false;" style="width:80px">
					关闭
				</button>
		</div>
	</html:form>
	<logic:equal value="yes" name="inserted">
		<script>
			alert('操作成功!');
			Close();
			window.dialogArguments.document.getElementById('go').click();
		</script>
	</logic:equal>
	<logic:equal value="no" name="inserted">
		<script>
			alert('操作失败!');
			Close();
			window.dialogArguments.document.getElementById('go').click();
		</script>
	</logic:equal>
	<script language="javascript">
function savetj(url) {
	if (document.getElementById('ysf').selectedIndex<=0 
	|| document.getElementById('val').value=='' || document.getElementById('val').value==null) {
		alert('带*号为必填，请确认！');
	}else {
		url += '?pkValue=';
		url += document.getElementById('pkValue').value;
		refreshForm(url);
	}
}
function chgysf(){
	var zdm = document.getElementById('zdm').innerText;
	if (zdm=='综合素质测评排名(名)') {
		var ysf = document.getElementById('ysf');
		var option1 = new Option("前","&lt;=");
		var option0 = new Option("","");
		ysf.options[0] = option0;
		ysf.options[1] = option1;
		ysf.options[1].selected = true;
		ysf.disabled="true";
	}
}
</script>
</body>