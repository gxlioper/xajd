<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript" src="js/xszzFunction.js"></script>
<body>
	<html:form action="/czxxPjpyCssz" method="post">

		<script>
		</script>
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ�ã��������� - �������� - �������ſ�������
			</div>
		</div>
		<fieldset >
			<br><br>
			<div align="center">
			<table width="95%"  class="tbstyle" >
				<thead>
					<tr height="35">
						<td align="right" width="50%">
							��ѧ�����뿪�أ�
						</td>
						<td width="50%">
							<html:radio property="jxjsq" value="1" >��</html:radio>
							<html:radio property="jxjsq" value="0" >��</html:radio>
						</td>
					</tr>
					<tr height="35">
						<td align="right">
							��ѧ����˿��أ�
						</td>
						<td>
							<html:radio property="jxjsh" value="1" >��</html:radio>
							<html:radio property="jxjsh" value="0" >��</html:radio>
						</td>
					</tr>
					<tr height="35">
						<td align="right">
							�����ƺ����뿪�أ�
						</td>
						<td>
							<html:radio property="rychsq" value="1" >��</html:radio>
							<html:radio property="rychsq" value="0" >��</html:radio>
						</td>
					</tr>
					<tr height="35">
						<td align="right">
							�����ƺ���˿��أ�
						</td>
						<td>
							<html:radio property="rychsh" value="1" >��</html:radio>
							<html:radio property="rychsh" value="0" >��</html:radio>
						</td>
					</tr>
				</thead>
				<logic:equal value="yes" name="writeAble">
				<tr height="35">
					<td align="center" colspan="2">
						<button type="button" class="button2" onclick="refreshForm('pjpy_czxx_kgsz.do?act=save')"
							style="width: 60px">
							�� ��
						</button>
						&nbsp;&nbsp;
						<button type="button" class="button2" onclick="refreshForm('pjpy_czxx_kgsz.do')"
							style="width: 60px">
							�� �� 
						</button>
					</td>
				</tr>
				</logic:equal>
			</table>
			</div>
		</fieldset>
		<logic:present name="inserted">
		<logic:equal value="yes" name="inserted">
			<script>
				alert('����ɹ�!');
			</script>
		</logic:equal>
		<logic:equal value="no" name="inserted">
			<script>
				alert('����ʧ��!');
			</script>
		</logic:equal>
		</logic:present>
		</html:form>
	</body>
