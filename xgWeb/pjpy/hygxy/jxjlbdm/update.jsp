<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
<body onload="">
	<html:form action="/pjpyhygxywh" method="post">
	<div class="title">
		��ǰ����λ�ã�ϵͳά�� - ����ά�� - ��������
	</div>
	<fieldset>
				<legend>
					��������ά��
				</legend>
		<table width='98%' align='center' class='tbstyle' id="rsTable">
			<thead>
				<tr align='center'>
					<td colspan='2'>
						* �� �� �� �� *
					</td>
				</tr>
			</thead>
						<tr align="" style="width:35px">
						<td align="right"> 
							<font  color="red">*</font>��ѧ�������룺
						</td>
						<td align="left">
							<html:text property="jxjlbdm" styleId="jxjlbdm" styleClass="inputtext" maxlength="10" readonly="true"></html:text>
						</td>
						</tr>
						<tr align="" style="width:35px">
						<td align="right"> 
							<font  color="red">*</font>��ѧ��������ƣ�
						</td>
						<td align="left">
							<html:text property="jxjlbmc" styleId="jxjlbmc" styleClass="inputtext"></html:text>
						</td>
						</tr>
						<tr align="" style="width:35px">
						<td align="right"> 
							<font  color="red">*</font>ͬ�ཱѧ���Ƿ��ã�
						</td>
						<td align="left">
							<html:radio property="jxjjd" value="0">��</html:radio>
							<html:radio property="jxjjd" value="1">��</html:radio>
						</td>
						</tr>
		</table>
		<div class="buttontool" align="center" >
			<logic:equal value="yes" name="writable">
				<button type="button" class="button2" style="" id="btn_save" 
						onclick="savedm('hygxyjxjlbdmmodisave.do');">
						&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="window.close();return false;" >
						&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;
					</button>
			</logic:equal>
		</div>
	</fieldset>
	</html:form>
	<logic:notEmpty name="inserted">
				<logic:equal value="no" name="inserted">
					<script language="javascript">
						alert("����ʧ�ܣ�");
					</script>
				</logic:equal>
				<logic:equal value="yes" name="inserted">
					<script language="javascript">
						alert("�����ɹ���");
					</script>
				</logic:equal>
				<script language="javascript">
					Close();
					var tN = window.dialogArguments.document.forms[0].tname.value;
					window.dialogArguments.document.getElementById(tN).click();
		</script>
			</logic:notEmpty>
	<script type="text/javascript">
		function savedm(url) {
			if (document.getElementById('jxjlbdm').value=='' || document.getElementById('jxjlbdm').value==null 
				|| document.getElementById('jxjlbmc').value=='' || document.getElementById('jxjlbmc').value==null) 
				{
					alert('��*��Ϊ�����ȷ�ϣ�');
					return;
				}
			refreshForm(url);
			document.getElementById('btn_save').disabled = true;
			//window.close();
		}
		function onlynum() {
			if (event.keyCode>=48 && event.keyCode<=57) {
				alert('ֻ����������!');
				document.getElementById('jxjjb').value='';
				return;
			}
		}
	</script>
</body>
