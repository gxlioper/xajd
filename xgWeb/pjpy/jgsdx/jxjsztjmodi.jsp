<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<body onload="chgysf()">
<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }">
	<html:form action="/pjpyjgsdxwh" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰ����λ�ã��������� - �������� - ��������
			</div>
		</div>
		<table width='100%' align='center' class='tbstyle' id="rsTable">
				<thead>
				<tr style="width:22px">
					<td colspan="2" align="center">
						<b>�����޸�</b>
					</td>
				</tr>
				</thead>
			<tr style="width:22px">
				<td align="right">
					��ѧ��
				</td>
				<td align="left">
					<bean:write name="rs" property="jxjmc"/>
				</td>
			</tr>
			<tr style="width:22px">
				<td align="right">
					ѧ�꣺
				</td>
				<td align="left">
					<bean:write name="rs" property="xn"/>
				</td>
			</tr>
			<tr style="width:22px">
				<td align="right">
					�����ֶ�����
				</td>
				<td align="left">
					<div id="zdm"><bean:write name="rs" property="zdm"/></div>
				</td>
			</tr>
			<%--<tr style="width:22px">
				<td align="right">
					<font color="red">*</font>�ֶβ�����
				</td>
				<td align="left">
				<html:select property="zdcz" styleId="zdcz" style="width:100px">
						<html:option value=""></html:option>
						<html:option value="sum">�ܺ�</html:option>
						<html:option value="avg">ƽ��ֵ</html:option>
						<html:option value="max">���ֵ</html:option>
						<html:option value="min">��Сֵ</html:option>
				</html:select>
				</td>
			</tr>
			--%><tr style="width:22px">
				<td align="right">
					<font color="red">*</font>������
				</td>
				<td align="left">
					<html:select property="ysf" styleId="ysf" style="width:100px">
						<html:option value=""></html:option>
						<html:option value="&gt;=">���ڻ����</html:option>
						<html:option value="&gt;">����</html:option>
						<html:option value="=">����</html:option>
						<html:option value="&lt;">С��</html:option>
						<html:option value="&lt;=">С�ڻ����</html:option>
								</html:select>
				</td>
			</tr>
			<tr style="width:22px">
				<td align="right">
					<font color="red">*</font>��Ӧ���ݣ�
				</td>
				<td align="left">
					<html:text property="val" styleId="val" style="width:120px"></html:text>
				</td>
			</tr>
			<%--<tr style="width:22px">
				<td align="right">
					Υ���������ƣ�
				</td>
				<td align="left">
					<html:radio property="sfwj" value="1"></html:radio>����&nbsp;&nbsp;&nbsp;
												<html:radio property="sfwj" value="0"></html:radio>������
				</td>
			</tr>
		--%></table>
		<br/>
		<div class="buttontool" align="center" >
				<button class="button2" id="btn_save" onclick="savetj('jxjsztjedit.do')" style="width:80px">
					����
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" id="btn_close" onclick="window.close();return false;" style="width:80px">
					�ر�
				</button>
		</div>
	</html:form>
	<logic:equal value="yes" name="inserted">
		<script>
			alert('�����ɹ�!');
			Close();
			window.dialogArguments.document.getElementById('go').click();
		</script>
	</logic:equal>
	<logic:equal value="no" name="inserted">
		<script>
			alert('����ʧ��!');
			Close();
			window.dialogArguments.document.getElementById('go').click();
		</script>
	</logic:equal>
	<script language="javascript">
function savetj(url) {
	if (document.getElementById('ysf').selectedIndex<=0 
	|| document.getElementById('val').value=='' || document.getElementById('val').value==null) {
		alert('��*��Ϊ�����ȷ�ϣ�');
	}else {
		url += '?pkValue=';
		url += document.getElementById('pkValue').value;
		refreshForm(url);
	}
}
function chgysf(){
	var zdm = document.getElementById('zdm').innerText;
	if (zdm=='�ۺ����ʲ�������(��)') {
		var ysf = document.getElementById('ysf');
		var option1 = new Option("ǰ","&lt;=");
		var option0 = new Option("","");
		ysf.options[0] = option0;
		ysf.options[1] = option1;
		ysf.options[1].selected = true;
		ysf.disabled="true";
	}
}
</script>
</body>