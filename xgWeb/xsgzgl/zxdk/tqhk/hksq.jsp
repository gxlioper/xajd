<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript">
			function saveHksq(url){
				if (jQuery("#hkzh").val() == "" || jQuery("#hkje").val() == "" || jQuery("#hkbj").val() == ""){
					showAlert("�뽫��������д������");
					return false;
				}
				
				//��ǰ�������ɲ��ó���250�ַ�
				if(jQuery("#hkly").val().length > 250){
					showAlertDivLayer("��ǰ������������ֳ���Ϊ"+250+",���Ѿ���������ȷ�ϣ�");
					return false;
				}
				
				//��ע���ó���250�ַ�
				if(jQuery("#bz").val().length > 250){
					showAlertDivLayer("��ע����ֳ���Ϊ"+250+",���Ѿ���������ȷ�ϣ�");
					return false;
				}
				
				ajaxSubFormWithFun("hksqForm",url,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						refershParent();
					}});
				});
			}
			
		</script>
	</head>
	<body>
		<div style="tab;overflow-x:hidden;overflow-y:auto;height:480px;margin-bottom: 0px;" >
		<html:form action="/zxdkTqhk" method="post" styleId="hksqForm">
			<input type="hidden" name="xh" value="${userName }"/>
			<input type="hidden" name="splcid" value="${cssz.tqhksplc }"/>
			<div>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="2">
									<logic:equal value="10511" name="xxdm">
								<table style="width:100%;text-align: center;">
									<tr>
										<th style="text-align: center;">ѧ��</th>
										<th style="text-align: center;">��ͬ���</th>
										<th style="text-align: center;">�����ܽ��(Ԫ)</th>
										<th style="text-align: center;">�ۼƷſ���(Ԫ)</th>
										<th style="text-align: center;">��������</th>
										<th style="text-align: center;">�����˺�</th>
									</tr>
									<logic:iterate id="dkxx" name="khkList">
										<tr>
											<td>${dkxx.xn }</td>
											<td>${dkxx.htbh }</td>
											<td>${dkxx.dkje }</td>
											<td>${dkxx.fkze }</td>
											<td>${dkxx.dkqx }</td>
											<td>${dkxx.dkzh }</td>
										</tr>
									</logic:iterate>
								</table>
							</logic:equal>
							<logic:notEqual value="10511" name="xxdm">
								<table style="width:100%;text-align: center;">
									<tr>
										<th style="text-align: center;">ѧ��</th>
										<th style="text-align: center;">��ͬ���</th>
										<th style="text-align: center;">�����ܽ��</th>
										<th style="text-align: center;">�ۼƷſ���</th>
										<th style="text-align: center;">��������</th>
									</tr>
									<logic:iterate id="dkxx" name="khkList">
										<tr>
											<td>${dkxx.xn }</td>
											<td>${dkxx.htbh }</td>
											<td>${dkxx.dkje }</td>
											<td>${dkxx.fkze }</td>
											<td>${dkxx.dkqx }</td>
										</tr>
									</logic:iterate>
								</table>
							</logic:notEqual>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>��ǰ��������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>�����˺�</th>
							<td>
								<input type="text" name="hkzh" id="hkzh" maxlength="20"
									   onkeyup="value=value.replace(/[^\d]/g,'')"
								/>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>������</th>
							<td>
								<input type="text" name="hkje" id="hkje" maxlength="10"
									   onkeyup="value=value.replace(/[^\d]/g,'')"
								/>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>������</th>
							<td>
								<select name="hkbj" id="hkbj">
									<option value=""></option>
									<option value="ȫ������">ȫ������</option>
									<option value="���ֻ���">���ֻ���</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>��ǰ��������
								<font color="red">(������250��)</font>
							</th>
							<td>
								<html:textarea property="hkly" styleId="hkly" 
											   onblur="checkLen(this,250);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>��ע
								<font color="red">(������250��)</font>
							</th>
							<td>
								<html:textarea property="bz" styleId="bz" 
											   onblur="checkLen(this,250);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
						<logic:equal value="10511" name="xxdm">
						  <tr>
							<th>������Ϣ
								
							</th>
							<td>
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
						</logic:equal>
					</tbody>
				</table>
			</div>
		</html:form>
		</div>
		<div>
			<table class="formlist">
				<tfoot>
					<tr>
						<td colspan="2">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button" onclick="saveHksq('zxdkTqhk.do?method=save');">
									����ݸ�
								</button>
								<button type="button" onclick="saveHksq('zxdkTqhk.do?method=saveAndSubmit');">
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
	</body>
	
</html>