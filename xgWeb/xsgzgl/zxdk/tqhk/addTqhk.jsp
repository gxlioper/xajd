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
			function saveDksq(url){
				
				if (jQuery.trim(jQuery("#hkzh").val()) == "" || jQuery.trim(jQuery("#hkje").val()) == "" 
						|| jQuery("#hkbj").val() == "" || jQuery("#xh").val() == ""){
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
				
				ajaxSubFormWithFun("hkjgForm",url,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						refershParent();
					}});
				});
			}
		</script>
	</head>
	<body>
		<html:form action="/zxdkHkjg" method="post" styleId="hkjgForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:460px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<logic:present name="khkList">
						<thead>
							<tr>
								<th colspan="4">
									<span>������Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="4">
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
					</logic:present>
					<thead>
						<tr>
							<th colspan="4">
								<span>��ǰ��������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>�����˺�</th>
							<td>
								<input type="text" name="hkzh" id="hkzh" maxlength="20"/>
							</td>
							<th><font color="red">*</font>������</th>
							<td>
								<input type="text" name="hkje" id="hkje" maxlength="10"/>
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
							<th>����״̬</th>
							<td>
								<html:select property="hkzt" >
									<html:option value=""></html:option>
									<html:options collection="hkztList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>��ǰ��������
								<br/><font color="red">(������250��)</font>
							</th>
							<td colspan="3">
								<html:textarea property="hkly" styleId="hkly" 
											   onblur="checkLen(this,250);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>��ע
								<br/><font color="red">(������250��)</font>
							</th>
							<td colspan="3">
								<html:textarea property="bz" styleId="bz" 
											   onblur="checkLen(this,250);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
						<logic:equal value="10511" name="xxdm">
						  <tr>
							<th>������Ϣ
								
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
						</logic:equal>
					</tbody>
				</table>
			</div>
			<div>
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveDksq('zxdkHkjg.do?method=save');">
										��    ��
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