<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<script type="text/javascript">
	function savetj() {
		var tjmc = document.getElementById('tjmc').value;
		var tjxz = document.getElementById('tjxz').value;
		if (tjmc==''||tjxz=='') {
			alert('��*��Ϊ������!');
			return;
		}
		refreshForm('pjpy_nblg_cjtjmodi.do?act=save');
		document.getElementById('btn_save').disabled=true;
	}
</script>
</head>
<body>
				<div class="tab_cur">
					<p class="location">
						<em>��ǰ����λ�ã�</em><a>�������� - ��Ϣά�� - �γ̳ɼ���ȼ����Գɼ�ά�� - �ɼ�������������(�޸�)</a>
					</p>
		</div>
	<html:form action="/pjpynblgwh" method="post">
		<table width="100%" class="formlist" >
			<thead >
				<tr>
					<th colspan="2">
						�����޸�
					</th>
				</tr>
			</thead>
			<tfoot>
								<tr>
									<td colspan="4">
										<div class="btn">
													<button
														onclick="savetj()" 
														id="btn_save">
														�� ��
													</button>
											<button onclick="Close();return false;" id="buttonClose">
												�� ��
											</button>
										</div>
									</td>
								</tr>
							</tfoot>
			<tr>
				<th width="16%">
					<font color="red">*</font>����:
				</th>
				<td width="34%">
					<html:text property="tjmc" styleId="tjmc"></html:text>
				</td>
			</tr>
			<tr>
				<th>
					<font color="red">*</font>����:
				</th>
				<td>
					<html:select property="tjxz" styleId="tjxz" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="tjxzList" property="tjxzdm" labelProperty="tjxzmc"/>
					</html:select>
				</td>
			</tr>
		</table>
		<!--  <div class="buttontool" align="center">
			<button id="btn_save" class="button2" onclick="savetj()" style="width:80px">����</button>
			&nbsp;&nbsp;
			<button id="btn_close" class="button2" onclick="window.close();return false;" style="width:80px">�ر�</button>
		</div>-->
	</html:form>
	<!-- ������ʾ -->
	<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
</body>
</html>