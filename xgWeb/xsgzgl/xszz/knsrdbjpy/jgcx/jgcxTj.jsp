<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/knsrdbjpy/knsrdbjpy/js/knsrdbjpy.js"></script>
		<script type="text/javascript">
			function queryParentDocumentJgcxTj(){
				if(frameElement.api){
					var api = frameElement.api,W = api.opener;
					return W.document;
				}
				return parent.window.document;
			}
			jQuery(function(){
				var parentD = queryParentDocumentJgcxTj();
				jQuery("#pjdjmc_td").html(jQuery("#pjdjmc_hid",parentD).val());
				jQuery("#pjdjdm_hid").val(jQuery("#pjdjdm_hid",parentD).val());

				//��������ѡ��
				loadViewMkxxSelectOptions();
				//����radioѡ��
				loadViewMkxxRadioOptions();

				var xh = jQuery("#xh").val();
				if (jQuery.trim(xh) != ""){
					jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfoForStu",{xh:xh},function(){
					});
				}
			});

			function submitBusi(){
				var pyhsj = jQuery('#pyhsj').val();
				if(jQuery.trim(pyhsj) == ''){
					showAlert("�����ʱ�䲻��Ϊ�գ�");
					return false;
				}
				var pyhdd = jQuery('#pyhdd').val();
				if(jQuery.trim(pyhdd) == ''){
					showAlert("�����ص㲻��Ϊ�գ�");
					return false;
				}
				var rdly = jQuery('#rdly').val();
				if(jQuery.trim(rdly) == ''){
					showAlert("�϶����ɲ���Ϊ�գ�");
					return false;
				}
				if(rdly.length > 100){
					showAlert("�϶��������100�֣�");
					return false;
				}
				var url = "xszz_knsrdbjpy_jgcxgl.do?method=jgcxTj&type=save&values=${mkxxForm.xh }";
			      ajaxSubFormWithFun("jgcxTjForm",url,function(data){
		    		  showAlert(data["message"],{},{"clkFun":function(){
		        			if (parent.window){
		     				 refershParent();
		        			}
		      		  }});
			      });
			}
		</script>
	</head>
	<body>
	
		<html:form action="/xszz_knsrdbjpy_jgcxgl" method="post" styleId="jgcxTjForm">
		<input type="hidden" id="xh" value="${mkxxForm.xh }" />
		<input type="hidden" name="sqid" value="${mkxxForm.guid }" />
		<div style='tab;width:100%;height:465px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>�϶���ʷ��¼
									<a onclick="showLsjl(this);" class="down" 
									   href="javascript:void(0);">
									   <font color="blue">���չ��/����</font>	
									</a>
								</span>
							</th>
						</tr>
					</thead>
					<tbody id="t_rdlsjl" style="display: table-row-group;">
						<tr>
							<td colspan="4">
							<table class="dateline" width="100%">
								<thead align="left">
									<tr align="left">
										<td ><b>ѧ��</b></td>
										<td ><b>ѧ��</b></td>
										<td><b>�϶�����</b></td>
										<td ><b>�϶�ʱ��</b></td>
									</tr>
								</thead>
								<tbody align="left">
							<logic:notEmpty name="rdlsjlList">
							<logic:iterate name="rdlsjlList" id="s">
										<tr  style="cursor:hand">
										<td >
												${s.xn}
											</td>
											<td >
												${s.xqmc}
											</td>
											<td >
												${s.dcmc}
											</td>
											<td >
												${s.sqsj}
											</td>
										</tr>
								</logic:iterate>
							</logic:notEmpty>
							</tbody>
							</table>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>��ͥ���
									<logic:notEqual value="" property="xh" name="mkxxForm">
										<a onclick="showJtqk(this);" class="up" 
										   href="javascript:void(0);">
										   <font color="blue">���չ��/����</font>	
										</a>
									</logic:notEqual>
								</span>
							</th>
						</tr>
					</thead>
					<tbody id="t_jtqk" style="display: none;">
						<tr>
							<td colspan="4">
								<div id="div_jtqk">
								
								</div>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�������϶�����</span>
							</th>
						</tr>
					</thead>
					
					<tr>
						<th>
							������Ϣ
						</th>
						<td colspan="3">
							<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<html:hidden name="mkxxForm" property="ylzd2" styleId="fjid"/>
							<script type="text/javascript">
								//���ø��� 
								jQuery(function(){
									var gid = jQuery('#fjid').val();
									jQuery.MultiUploader_q({
										gid : gid
										});
								});
							</script>
						</td>
					</tr>
					
					<logic:equal value="1" name="sqsftxdc">
						<tbody>
							<tr>
								<th align="right">���뵵��</th>
								<td colspan="3">
									${mkxxForm.sqdcmc }
								</td>
							</tr>
						</tbody>
					</logic:equal>
					<%@ include file="/xsgzgl/xszz/bdpz/mkxxView.jsp" %>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>�༶������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								����С���Ա
								<input type="hidden" name="bjpyxzcyxms" value="${bjpyxzdbMap.bjpyxzcyxms }"/>
							</th>
							<td colspan="3">
								${bjpyxzdbMap.bjpyxzcyxms }
							</td>
						</tr>
						<tr>
							<th>
								����С�����
								<input type="hidden" name="bjpyxzdbxms" value="${bjpyxzdbMap.bjpyxzdbxms }"/>
							</th>
							<td colspan="3">
								${bjpyxzdbMap.bjpyxzdbxms }
							</td>
						</tr>
						<tr>
							<th>
								�Ƽ�����
								<input type="hidden" id="pjdjdm_hid" name="pddc" value=""/>
							</th>
							<td colspan="3" id="pjdjmc_td">
								
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>������
							</th>
							<td colspan="3">
								<html:select property="shzt" styleId="shzt" style="width:155px">
									<html:options collection="pyjgList" labelProperty="mc" property="dm"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>�����ʱ��
							</th>
							<td colspan="3">
								<html:text styleId="pyhsj" property="pyhsj" name="jgcxForm" onclick="return showCalendar('pyhsj','yyyy-MM-dd HH:mm');"  readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>�����ص�
							</th>
							<td colspan="3">
								<html:text property="pyhdd" styleId="pyhdd" name="jgcxForm" maxlength="100" style="width:95%;"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>�϶�����
								<br />
								<font color="red">(��100��)</font>
							</th>
							<td colspan="3" style="word-break: break-all;">
								<html:textarea property="rdly" styleId="rdly" name="jgcxForm" style="width:95%;" rows="5"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" id="save_button" type="button"
										onclick="submitBusi();">
										�ύ
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

