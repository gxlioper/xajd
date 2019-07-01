<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript' src="xsgzgl/jjgl/zcyh/js/jzxx.js"></script>
	</head>
	<body style="width: 100%">
		<html:form action="/jjgl_zcyhgl" method="post" styleId="zcyhForm" onsubmit="return false;">
			<div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								�ҳ����
							</th>
							<td width="30%">
								${jjglZcyhForm.yhm}
								<html:hidden property="yhm" styleId="yhm" ></html:hidden>
							</td>
							<th width="20%">
								<font color="red">*</font>����
							</th>
							<td width="30%">
								<html:text property="xm" styleId="xm" ></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>�Ա�
							</th>
							<td width="30%">
								<html:radio property="xb" value="1" >��</html:radio>
								<html:radio property="xb" value="2" >Ů</html:radio>
							</td>
							<th width="20%">
								<font color="red">*</font>��ϵ�绰
							</th>
							<td width="30%">
								<html:text property="lxdh" styleId="lxdh"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>���֤��
							</th>
							<td width="30%">
								<html:text property="sfzh" styleId="sfzh"></html:text>
							</td>
							<th width="20%">
								������λ
							</th>
							<td width="30%">
								<html:text property="gzdw" styleId="gzdw"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>��ͥסַ
							</th>
							<td width="70%" colspan="3">
								<html:text property="jtzz" styleId="jtzz" style="width:95%"></html:text>
							</td>
						</tr>

					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ů��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<table class="dateline" width="100%">
							<thead>
								<tr>
									<th width="19%"><font color="red">*</font>����</th>
									<th width="12%"><font color="red">*</font>�Ա�</th>
									<th width="19%">��������</th>
									<th width="25%"><font color="red">*</font>�ڶ�ѧУ</th>
									<th width="15%"><font color="red">*</font>�꼶</th>
									<th width="10%">����</th>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="znxxList" id="znxx" indexId="index">
									<tr id="tr_znxx${index}" class="tr_znxx">
										<td><input style="width: 130px;" type="text" name="znxxModelList[${index}].xm" value="${znxx.xm}"/></td>
										<td>
											<%--����ֱ��д����Ů����Ϊ֮ǰ�ı�����������--%>
											<input type="radio" value="��" name="znxxModelList[${index}].xb" ${znxx.xb == "��"?"checked":""}/>��
											&nbsp;
											<input type="radio" value="Ů" name="znxxModelList[${index}].xb" ${znxx.xb == "Ů"?"checked":""}/>Ů
										</td>
										<td><input style="width: 130px;" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="znxxModelList[${index}].csrq" value="${znxx.csrq}"/></td>
										<td><input style="width: 180px;" type="text" name="znxxModelList[${index}].zdxx" value="${znxx.zdxx}"/></td>
										<td><input style="width: 130px;" type="text" name="znxxModelList[${index}].nj" value="${znxx.nj}"/></td>
										<td><a href="javascript:void(0)" onclick="scznxx(this)">ɾ��</a></td>
									</tr>
								</logic:iterate>
								<tr id="tr_zjznxx" onclick="zjznxx()">
									<td colspan="6" align="center">
										<a href="javascript:void(0);" class="btn_zj">+ ����</a>
									</td>
								</tr>
							</tbody>
						</table>
					</tbody>
				 </table>
				</div>
			  <div style="height:35px"></div>   
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button" onclick="save('edit');">
									����
								</button>
								<button type="button" onclick="iFClose();">
									�ر�
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

