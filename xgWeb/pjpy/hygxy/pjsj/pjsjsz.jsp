<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script type="text/javascript">
<!--
	function loadjxjxn() {
		var jxjsqxn = document.getElementById('jxjsqxn').value;
		if (jxjsqxn == null || jxjsqxn == '') {
			alert('����ѧ����δ����,�����ڲ���������������ѧ��!');
		}
	}
//-->
</script>
<body onload="loadjxjxn()">
	<html:form action="/pjpyhygxywh" method="post">
	<div class="title">
		��ǰλ�ã��������� - �������� - ˼��Ʒ������ʱ��
	</div>
	<fieldset>
				<legend>
					ʱ������
				</legend>
		<table width='40%' align='center' class='tbstyle' id="rsTable">
			<tr>
				<td align="right">
					����ѧ��:
				</td>
				<td align="left">
					${jxjsqxn }
					<input type="hidden" name="jxjsqxn" id="jxjsqxn" value="${jxjsqxn }"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					�������:
				</td>
				<td align="left">
					${jxjsqnd }
				</td>
			</tr>
			<tr>
				<td align="right">
					����ѧ��:
				</td>
				<td align="left">
					${jxjsqxq }
				</td>
			</tr>
			<tr>
				<td align="right">
					��ѧ������ʱ��:
				</td>
				<td align="left">
					<html:radio property="jxjpdsj" value="1"></html:radio>��
					<html:radio property="jxjpdsj" value="0"></html:radio>��
				</td>
			</tr>
			<tr>
				<td align="right">
					�����ƺ�����ʱ��:
				</td>
				<td align="left">
					<html:radio property="rychpdsj" value="1"></html:radio>��
					<html:radio property="rychpdsj" value="0"></html:radio>��
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center" >
			<logic:equal value="yes" name="writable">
				<button type="button" class="button2" style="width: 80px" id="btn_save" 
						onclick="refreshForm('savepjsj.do')">
						��&nbsp;&nbsp;��
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
			</logic:notEmpty>
	<script type="text/javascript">
	</script>
</body>
