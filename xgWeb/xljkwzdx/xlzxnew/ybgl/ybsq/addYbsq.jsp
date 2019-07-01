<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xljkwzdx/xlzxnew/ybgl/comm/js/comm.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript" defer="defer">

			function saveAction(type){
				var checkids = "xydm-xn-yf-ztqk";
				if("${xxdm}" == "10704"){
					var sfywt;
					var radio = document.getElementsByName("sfywt");  
				    for (i=0; i<radio.length; i++) {  
				        if (radio[i].checked) {  
				          sfywt=radio[i].value;  
				        }  
				    }  
				    if(sfywt=="��"){
				    	if(!checkNotNull(checkids)){
							showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����!");
							return false;
						}
						if(jQuery("[name='xh']").length == 0){
							showAlert("��������дһλѧ��������״��!");
							return false;
						}
				    }else{
				    	var checkid = "xydm-xn-yf";
				    	if(!checkNotNull(checkid)){
							showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����!");
							return false;
						}
				    }
				}else{
					if(!checkNotNull(checkids)){
							showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����!");
							return false;
					}
					if(jQuery("[name='xh']").length == 0){
							showAlert("��������дһλѧ��������״��!");
							return false;
					}
				}
				var message = null;
				jQuery("[name='deltr']").each(function(){
					/*
					if(jQuery.trim(this.value).length == 0){
						message = "��Ҫ������������Ϊ�գ�";
						return false;
					}else if(this.value.length > 500){
						message = "��Ҫ����������500�֣�";
						return false;
					}	*/
					if("${xxdm}" != "10704"){
						if(jQuery.trim(jQuery(this).find("[name='ybwtms']").val()).length == 0){
							message = "���˵������Ϊ�գ�";
						}else if(jQuery(this).find("[name='ybwtms']").val().length > 500){
							message = "���˵����500�֣�";
							return false;
						}else if(jQuery.trim(jQuery(this).find("[name='ybgycs']").val()).length == 0){
							message = "��Ԥ��ʩ����Ϊ�գ�";
						}else if(jQuery(this).find("[name='ybgycs']").val().length > 500){
							message = "��Ԥ��ʩ��500�֣�";
							return false;
						}else if(jQuery.trim(jQuery(this).find("[name='ybgyhjg']").val()).length == 0){
							message = "��Ԥ��Ч������Ϊ�գ�";
						}else if(jQuery(this).find("[name='ybgyhjg']").val().length > 500){
							message = "��Ԥ��Ч��500�֣�";
							return false;
						}	
					}else{
						if(jQuery.trim(jQuery(this).find("[name='wtfsrq']").val()).length == 0){
							message = "�������ڲ���Ϊ�գ�";
						}else if(jQuery.trim(jQuery(this).find("[name='ybwtms']").val()).length == 0){
							message = "Σ���̶Ȳ���Ϊ�գ�";
						}else if(jQuery.trim(jQuery(this).find("[name='ybgyhjg']").val()).length == 0){
							message = "������̲���Ϊ�գ�";
						}else if(jQuery(this).find("[name='ybgyhjg']").val().length > 500){
							message = "���������500�֣�";
							return false;
						}	
					}
				})
				if(message){
					showAlert(message);
					return false;
				}
				var url = "xlzxnew_ybsb.do?method=saveYbsb&saveFlag="+type;
				ajaxSubFormWithFun("YbsbForm",url,function(data){
					if(data["message"] == "����ɹ���"){
						 showAlert(data["message"],{},{"clkFun":function(){
								if (parent.window){
									refershParent();
								}
							}});
					}else{
						showAlert(data["message"]);	
					}
					
				});
			}
			
			function show(obj){
				if(obj.value=="��"){
					jQuery(".h").hide();
				}else{
					jQuery(".h").show();	
				}
			
			}
		</script>
	</head>
	<body>
		<html:form action="/xlzxnew_ybsb" method="post" styleId="YbsbForm">
			<%--<html:hidden property="splcid" value="${sbxx.splcid}"/>
			<html:hidden property="splcidpz" value="${sbxx.splcidpz}"/>
			--%><div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�ϱ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
							<tr>
								<th  width="15%">
									ѧԺ
								</th>
								<td  width="35%">
									${xymc}
									<html:hidden property="xydm"  styleId="xydm"/>
								</td>
								<th  width="15%">
									��д��
								</th>
								<td  width="35%">
									${txr}
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>ѧ��
								</th>
								<td>
									<html:select property="xn" styleId="xn" style="width:150px;">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>
								</td>
								<th>
									<span class="red">*</span>�¶�
								</th>
								<td>
									<select name="yf" id="yf" style="width:150px;">
										<option></option>
										<option value="01">1��</option>
										<option value="02">2��</option>
										<option value="03">3��</option>
										<option value="04">4��</option>
										<option value="05">5��</option>
										<option value="06">6��</option>
										<option value="07">7��</option>
										<option value="08">8��</option>
										<option value="09">9��</option>
										<option value="10">10��</option>
										<option value="11">11��</option>
										<option value="12">12��</option>
									</select>
								</td>
							</tr>
							<logic:equal value="10704" name="xxdm">
							<tr>
								<th  width="15%">
									<span class="red">*</span>�Ƿ�������
								</th>
								<td >
									<input type="radio" name="sfywt" value="��" checked="checked" onclick="show(this)"/>��
									<input type="radio" name="sfywt" value="��" onclick="show(this)"/>��
								</td>
							</tr>
							</logic:equal>
						<tr class="h">
							<th>
								<span class="red">*</span>
								ѧԺ���������<br />
								�ش��¼�<br />
								<font color="red">(��1000��)</font>
							</th>
							<td colspan="3" style="word-break: break-all;">
								<html:textarea property="ztqk" styleId="ztqk" style="width:95%;" rows="5" onblur="checkLen(this,1000);"></html:textarea>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr class="h">
							<th colspan="4">
								<span>�����������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr class="h">
							<th colspan="4"> <!-- style="margin-top:2px;margin-left:30px"  -->
								<button type="button" onclick="addRowDialog();return false;" style="float:left">����</button>
									<button type="button" onclick="delRow();return false;" style="float:left">ɾ��</button>
							</th>
						</tr>
						<tr class="h">
							<th colspan="7">
								<table width="100%" >
									<thead>
									<logic:equal value="10704" name="xxdm">
										<tr>
											<th width='5%'><input name='chkAll' onclick="selectAll(this)" type="checkbox" /></th>
											<th width='15%' style="text-align:center">ѧ��</th>
											<th width='10%' style="text-align:center">����</th>
											<th width='18%' style="text-align:center">�༶</th>
											<th width='10%' style="text-align:center">��ϵ��ʽ</th>
											<th width='12%' style="text-align:center"><font class="red">*</font>��������</th>
											<th width='10%' style="text-align:center"><font class="red">*</font>Σ���̶�</th>
											<th width='20%' style="text-align:center"><font class="red">*</font>�������(��500��)</th>
										</tr>
									</logic:equal>
									<logic:notEqual value="10704" name="xxdm">
										<tr>
											<th width='5%'><input name='chkAll' onclick="selectAll(this)" type="checkbox" /></th>
											<th width='15%' style="text-align:center">ѧ��</th>
											<th width='10%' style="text-align:center">����</th>
											<th width='10%' style="text-align:center">�༶</th>
											<th width='20%' style="text-align:center"><font class="red">*</font>���˵��(��500��)</th>
											<th width='20%' style="text-align:center"><font class="red">*</font>��Ԥ��ʩ(��500��)</th>
											<th width='25%' style="text-align:center"><font class="red">*</font>��Ԥ��Ч��(��500��)</th>
										</tr>
									</logic:notEqual>
									</thead>
									<tbody id="tablebody">
									
									</tbody>
								</table>
							</th>
						</tr>
						<logic:equal value="10704" name="xxdm">
						<tr>
							<th>�����ϴ�</th>
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
			<div style="height:40px;"></div>
			<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button id="save_button" type="button"  onclick="saveAction('save');">
										����ݸ�
									</button>
									<button id="submit_button" type="button"  onclick="saveAction('submit');">
										�ύ�ϱ�
									</button>
									<button type="button" name="�� ��" onclick="iFClose();">
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

