<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript" src="xsgzgl/jskp/xspjsq/js/xspjsq.js"></script>
		<script type="text/javascript">
			function changeZdbmmc(){
				//ȡ�����ݱ�zxbz_xxbmdm; �ֶΣ�bmmc																							
				var autoSetting = {
					dataTable:"zxbz_xxbmdm",
					dataField:"bmmc",
					dataFieldKey:"bmdm",
					dataFieldKeyId:"zdbmdm",
					scrollHeight:135										
				}
				// ģ��������������Ŀ���ơ�
				jQuery("#bmmc").setAutocomplete(autoSetting);
			}
			jQuery(function(){
				changeZdbmmc();
			})
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/xspj_xspjsq" method="post" styleId="xspjsqForm">
		<input type="hidden" id="sqid" name="sqid" value="${xspjsqForm.sqid }"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:15%">
								<font color="red">*</font>��Ŀ����
							</th>
							<td style="width:35%">
								<input type="text" name="xmmc" id="xmmc" maxlength="64" value="${xspjsqForm.xmmc}"/>
							</td>
							<th style="width:15%">
								<font color="red">*</font>ָ������
							</th>
							<td style="width:35%">
								<input type="text" name="bmmc" id="bmmc" value="${bmmc}"/>
								<input type="hidden" id="zdbmdm" name="zdbmdm" value="${xspjsqForm.zdbmdm}" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��Ŀ���
							</th>
							<td>
								<html:select property="xmlbdm" styleId="xmlbdm" style="width:173px;">
									<html:option value="">---&nbsp;��ѡ����Ŀ���&nbsp;---</html:option>
									<html:options collection ="xmlbList" property="xmlbdm" labelProperty="xmlbmc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>����ʱ��
							</th>
							<td>
								<input type="hidden" name="maxtime" id="maxtime" maxlength="10" value="${maxtime}"/>
								<html:text property="cysj" styleId="cysj" onclick="return showCalendar('cysj','y-mm-dd',true,'maxtime');"  maxlength="10" ></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>������(ѧ��)
							</th>
							<logic:equal value="stu" name="userType">
								<td>
									<input type="text" name="xh" id="xh" value="${userName}"/>
								</td>
							</logic:equal>
							<logic:notEqual value="stu" name="userType">
								<td>
									<html:text property="xh" styleId="xh"  maxlength="10" onkeyup="checkInputLxfx(this)"></html:text>
								</td>
							</logic:notEqual>
							<th>
								<font color="red">*</font>ѧ��
							</th>
							<td>
								<html:select property="xn" styleId="xn" style="width:173px;">
										<html:options collection="xnList" labelProperty="xn" property="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��ѧ��
							</th>
							<td>
								<html:select property="dxqdm" styleId="dxqdm" style="width:173px;">
									<html:option value="">---&nbsp;��ѡ���ѧ��&nbsp;---</html:option>
									<html:options collection ="dxqList" property="dxqdm" labelProperty="dxqmc" />
								</html:select>
							</td>
							<logic:notEqual value="stu" name="userType">
								<th>
									��ֵ
								</th>
								<td>
									<html:text property="fz" styleId="fz"  maxlength="4" onblur="checkInputNum(this)"></html:text>
								</td>
							</logic:notEqual>
						</tr>
						<tr>
							<th>
								������
							</th>
							<td>
								<html:text property="fzrxm" styleId="fzrxm" maxlength="16"></html:text>
							</td>
							<th>
								������&nbsp;<br/>��ϵ��ʽ
							</th>
							<td>
								<html:text property="fzrlxfs" styleId="fzrlxfs" maxlength="12" onkeyup="checkInputLxfx(this)"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								ָ����ʦ
							</th>
							<td>
								<html:text property="zdlsxm" styleId="zdlsxm"  maxlength="16" ></html:text>
							</td>
							<th>
								ָ����ʦ<br/>��ϵ��ʽ
							</th>
							<td>
								<html:text property="zdlslxfs" styleId="zdlslxfs"  maxlength="12" onkeyup="checkInputLxfx(this)"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>�����ϴ�
							</th>
							<td colspan="3">
								<html:hidden property="fjid" styleId="fjid" />
								<input type="file" id="filepath_f" name="filepath_f" />
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										jQuery('#filepath_f').multiUploader({
											maxcount : 3,
											//��׺
											accept : 'png|gif|jpg|zip|rar|doc|docx',
											//����ļ���С ��λM
											maxsize: 10,
											//��Ÿ������������id
											elementid : 'fjid',
											eid : 'filepath_f'
										});
									});
								</script>
							</td>
						</tr>
						<tr>
							<th>
								��������
								<br/><font color="red">(��200��)</font>
							</th>
							<td colspan="3">
								<html:textarea property="sqly" styleId="sqly" onkeyup="checkLen(this,500);"  style="width:99%;" rows="5"></html:textarea>
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
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="xspjsqUpdateSave('save');">
										����ݸ�
									</button>
									<button type="button" onclick="xspjsqUpdateSave('submit');">
										�ύ����
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