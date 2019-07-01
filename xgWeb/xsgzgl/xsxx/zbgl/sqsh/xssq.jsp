<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript">
			function saveForm(url){
				var checkStr = "xh-dszn-hyzk-sl-sg-tz-yzd-yzly-cjyy";
				<logic:equal name="xxdm" value="14073">
					checkStr += "-ylzd1-ylzd2-ylzd3-ylzd4-ylzd5-ylzd6-ylzd7-ylzd8"; 
				</logic:equal>
				if (checkNull(checkStr)){
					ajaxSubFormWithFun("form",url,function(data){
						showAlert(data["message"],{},{"clkFun":function(){
							refershParent();
						}});
					});
				}
			}
		</script>
	</head>
	<body>
		<html:form action="/zbglSqsh" method="post" styleId="form">
			<input type="hidden" name="splcid" value="${cssz.splc }"/>
			<html:hidden property="xn" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:466px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><span class="red">*</span>������Ů</th>
							<td>
								<html:select property="dszn" styleId="dszn">
									<html:option value=""></html:option>
									<html:option value="������Ů">������Ů</html:option>
									<html:option value="�Ƕ�����Ů">�Ƕ�����Ů</html:option>
								</html:select>
							</td>
							<th><span class="red">*</span>����״��</th>
							<td>
								<html:select property="hyzk" styleId="hyzk">
									<html:option value=""></html:option>
									<html:option value="�ѻ�">�ѻ�</html:option>
									<html:option value="δ��">δ��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>����</th>
							<td>
								<html:text property="sl" maxlength="20" styleId="sl"></html:text>
							</td>
							<th><span class="red">*</span>���(cm)</th>
							<td>
								<html:text property="sg" maxlength="3" styleId="sg" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>����(kg)</th>
							<td>
								<html:text property="tz" maxlength="3" styleId="tz" onkeyup="checkInputNum(this);"></html:text>
							</td>
							<th><span class="red">*</span>Ӧ����</th>
							<td>
								<html:text property="yzd" maxlength="50" styleId="yzd"></html:text>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>Ӧ����Դ</th>
							<td>
								<html:text property="yzly" maxlength="50" styleId="yzly"></html:text>
							</td>
							<th><span class="red">*</span>�ξ���Ը</th>
							<td>
								<html:text property="cjyy" maxlength="50" styleId="cjyy"></html:text>
							</td>
						</tr>
						<logic:equal name="xxdm" value="14073">
							<tr>
								<th><span class="red">*</span>�Ͷ���ֹ����</th>
								<td>
									<html:text  style="width:70px" styleId="ylzd1" property="ylzd1" onclick="return showCalendar('ylzd1','yyyy-MM-dd',true,'ylzd2');"  readonly="true"></html:text>
									��
									<html:text  style="width:70px" styleId="ylzd2" property="ylzd2" onclick="return showCalendar('ylzd2','yyyy-MM-dd',false,'ylzd1');" readonly="true"></html:text>
								</td>
								<th><span class="red">*</span>ѧϰ����</th>
								<td>
									<html:text property="ylzd3" maxlength="50" styleId="ylzd3"></html:text>
								</td>
							</tr>
							<tr>
								<th><span class="red">*</span>ѧҵ���</th>
								<td>
									<html:text property="ylzd4" maxlength="100" styleId="ylzd4"></html:text>
								</td>
								<th><span class="red">*</span>ѧУ����</th>
								<td>
									<html:text property="ylzd5" maxlength="50" styleId="ylzd5"></html:text>
								</td>
							</tr>
							<tr>
								<th><span class="red">*</span>ѧУ�������ŵ�ַ</th>
								<td colspan="3">
									<html:text property="ylzd7" maxlength="100" styleId="ylzd7" style="width: 488px;"></html:text>
								</td>
							</tr>
							<tr>
								<th><span class="red">*</span>�ʱ�</th>
								<td>
									<html:text property="ylzd8" maxlength="10" styleId="ylzd8" onkeyup="checkInputNum(this);"></html:text>
								</td>
								<th><span class="red">*</span>ԺУ���ڵ�</th>
								<td>
									<html:text property="ylzd6" maxlength="100" styleId="ylzd6"></html:text>
								</td>
							</tr>
						</logic:equal>
						<tr>
							<th>��ҵ���</th>
							<td>
								<html:text property="cylb" maxlength="50"></html:text>
							</td>
							<th>ְҵ�ʸ�֤��</th>
							<td>
								<html:text property="zgzs" maxlength="50"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								��ע
								<br/><span class="red">(��400��)</span>
							</th>
							<td colspan="3">
								<html:textarea property="bz" styleId="bz" 
											   onblur="checkLen(this,400);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
						<tr>
							<th align="right" width="16%">
								������Ϣ
							</th>
							<td colspan="3">
								<html:hidden property="filepath" styleId="filepath" />
								<input type="file" id="filepath_f" name="filepath" />
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
											elementid : 'filepath',

											eid : 'filepath_f'
											});
									});
								</script>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		
			<div>
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="saveForm('zbglSqsh.do?method=save');">
										����ݸ�
									</button>
									<button type="button" onclick="saveForm('zbglSqsh.do?method=saveAndSubmit');">
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

