<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript" src="js/xszzFunction.js"></script>
<body>
	<html:form action="/gzdxPjpy.do" method="post">
		<input type="hidden" name="act" value="save"/>
		<script>
			function savedata() {
				var cflb = document.getElementById('cflb').value;
				if (cflb =='' || cflb==null) {
					alert("��ѡ��Ҫ���õĴ������ȼ�");
					return false;
				}
				refreshForm('pjpy_gzdx_cfxzsz.do');
			}
			
		</script>
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ�ã��������� - �������� - ������������
			</div>
		</div>
		<fieldset >
			<br><br>
			<div align="center">
			<table width="100%"  class="tbstyle" >
				<thead>
					<tr >
						<td align="center" width="50%">
							��ʾ��Ϣ������ѧ�����ܴ��ֵȼ����ڻ���������õĴ������ȼ�ʱȡ��������ʸ�
							<br/>
							<html:select property="cflb" styleId="cflb" style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="cflbList" property="cflbdm" labelProperty="cflbmc"/>
							</html:select>
						</td>
					</tr>
				</thead>
				<tr >
					<td align="center" >
						<button class="button2" onclick="savedata()"
							style="width: 60px">
							�� ��
						</button>
						&nbsp;&nbsp;
						<button class="button2" onclick="window.close();return false;"
							style="width: 60px">
							�� ��
						</button>
					</td>
				</tr>
			</table>
			</div>
		</fieldset>
		<logic:equal value="true" name="result">
			<script>
				alert('����ɹ�!');
				window.close();
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert('����ʧ��!');
				window.close();
			</script>
		</logic:equal>
		</html:form>
	</body>
