<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/xmsz/js/xmwhJxfz.js"></script>
	</head>
	<body>
		<html:form action="/xpj_xmwh" method="post" styleId="form1">
		<html:hidden property="xmdm" styleId="xmdm" />
		<div class="prompt">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					"<font color="red">��������</font>"���ɸ���
				</p>
				<p>
					���ƽ������<font color="red">ע���޸�</font>�������"<font color="red">��������</font>"��"<font color="red">�������</font>"��"<font color="red">�����������</font>"��
					�ر���"<font color="red">�������á�</font>"�и�������<font color="red">������Χ</font>����޸�
				</p>
				
				
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<div style=''>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>������Ŀ����</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th><font color="red">*</font>������Դ���</th>
							<td>
								<select id="jxfznd" name="jxfznd" style="width:155px"></select>
							</td>
						</tr>
						<tr>
							<th>����Ŀ�����</th>
							<td>
								${currXnXqmc }
							</td>
						</tr>

					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
										�� ��
									</button>
									<button type="button" onclick="iFClose();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>

