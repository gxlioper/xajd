<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/jtff/js/jtff.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#selsz").click(function(){
				showDialog("ѡ���糤", 770, 520, "stgl_zjsr.do?method=getStu&type=selsz");
			});
			jQuery("#selcwfzr").click(function(){
				showDialog("ѡ���������", 770, 520, "stgl_zjsr.do?method=getStu&type=selcwfzr");
			});
			jQuery("#selzdls").click(function(){
				showDialog("ѡ��ָ����ʦ", 770, 520, "stgl_zjsr.do?method=getTea");
			});
		});
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form method="post" styleId="form"	action="/stgl_zjsr">
			<html:hidden property="id" />
			<div	style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<!-- <thead>
						<tr>
							<th colspan="4">
								<span>���Ź���</span>
							</th>
						</tr>
					</thead> -->
					
					<tbody>
						<tr>
							<th width="20%">
								��������
							</th>
							<td colspan="3">
								${stglZjsrForm.stmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								���ű��
							</th>
							<td width="30%">
								${stglZjsrForm.bh}
							</td>
							<th width="20%">
								<font color="red">*</font>��Ч״̬
							</th>
							<td width="30%">
								<html:radio property="yxzt" value="1" styleId="yxzt">��Ч</html:radio>
								<html:radio property="yxzt" value="0" styleId="yxzt">��Ч</html:radio>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>�������
							</th>
							<td width="30%">
								<html:select property="stlb" styleId="stlb" >
									<html:options collection="stlbList" property="stlbdm" labelProperty="stlbmc" />
								</html:select>
							</td>
							<th width="20%">
								<font color="red">*</font>ָ����ʦ
							</th>
							<td width="30%">
								<html:text property="zdls" styleId="zdls" maxlength="25" style="width:98%"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>�糤
							</th>
							<td width="30%">
								<input type="text"  style="width:100px;" id="szxm" readonly="true" value="${szxm }"/>
								<html:hidden property="sz" styleId="sz" />
								<button class="btn_01" id="selsz" type="button">ѡ��</button>
							</td>
							<th width="20%">
								<font color="red">*</font>��������
							</th>
							<td width="30%">
								<input type="text"  style="width:100px;" id="cwfzrxm" readonly="true" value="${cwfzrxm }"/>
								<html:hidden property="cwfzr" styleId="cwfzr" />
								<button class="btn_01" id="selcwfzr" type="button">ѡ��</button>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>��������
							</th>
							<td colspan="3">
								<html:select property="zd1" styleId="zd1" >
									<html:options collection="bmList" property="bmmc" labelProperty="bmmc" />
								</html:select>
							</td>
						</tr>
						<tr id="fjtr">
							<th>
								����
							</th>
							<td colspan="3">
								<html:hidden property="filepath" styleId="filepath" />
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
	                               <script type="text/javascript">
										//���ø��� 
										jQuery(function(){
											jQuery.MultiUploader({
												maxcount : 3,//��׺
												accept : 'png|gif|jpg|zip|rar|doc|docx',//����ļ���С ��λM
												maxsize: 10,//��Ÿ������������id
												elementid : 'filepath'
											});
										});
								</script>
							</td>
						</tr>
						<tr>
							<th>
								��ע
								<br><font color="red">��500������</br></font>
							</th>
							<td colspan="3" >
								<html:textarea property="bz" style="width:94%;height:100px"  styleId="bz" onblur="chLengs(this,500);" ></html:textarea>
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
									<button type="button" onclick="save('stgl_zjsr.do?method=stglSave','stmc-bh-yxzt-stlb-zdls-sz-cwfzr-zd1');" id="buttonSave">
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