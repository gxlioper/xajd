<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/zjly/ylbx/js/ylbx.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form method="post" styleId="form"	action="/zjly_ylbx.do?method=edit&type=save">
		<html:hidden property="id" />
			<div	style='width: 100%; overflow-x: hidden; overflow-y: auto;'>
				<table width="" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>ҽ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><span class="red">*</span>���������</th>
						    <td>
								<div>
									<html:radio property="cxblb" value="�²α�">�²α�</html:radio>
									<html:radio property="cxblb" value="����">����</html:radio>
								</div>
							</td>
							<th><font color="red">*</font>ѧ��
							</th>
							<td>
								<html:select property="xn"  styleId="xn" onchange="" >
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>֤������</th>
							<td>
								<html:text property="zlbh" maxlength="30"></html:text>
							</td>
								<th>��˱�־</th>
							<td>
								<div>
									<html:radio property="shbz" value="���ͨ��">���ͨ��</html:radio>
									<html:radio property="shbz" value="��˲�ͨ��">��˲�ͨ��</html:radio>
								</div>
							</td>
						</tr>
						<tr>
							<th>
								�ӳ�ԭ��
								<br><font color="red">��500������</br></font>
							</th>
							<td colspan="3">
								<html:textarea property="ycyy" style="width:94%" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								������
								<br><font color="red">��500������</br></font>
							</th>
							<td colspan="3">
								<html:textarea property="shyj" style="width:94%" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
						
					</tbody>
				</table>
			</div>
			<div style="height:30px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
								</div>
								<div class="btn">
									<button type="button"
										onclick="save('zjly_ylbx.do?method=Ylbxedit&type=save','xh-xn-zlbh-cxblb');return false;"
										id="buttonSave">
										�� ��
									</button>
									<button type="button" onclick="iFClose();"  id="buttonClose">
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