<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/kycxgl/kycxxm/js/kycxxm.js"></script>
		<script	type="text/javascript">
			function saveForm(type){
				var xn = jQuery("#xn").val();
				if (jQuery.trim(xn) == ""){
					showAlert("��ѡ��ѧ�꣡");
					return false;
				}	
				var xmmc = jQuery("#xmmc").val();
				if (jQuery.trim(xmmc) == ""){
					showAlert("������Ŀ���Ʋ���Ϊ�գ�");
					return false;
				}	
				var zdls = jQuery("#zdls").val();
				if (jQuery.trim(zdls) == ""){
					showAlert("��ѡ��ָ����ʦ��");
					return false;
				}	
				var lbdm = jQuery("#lbdm").val();
				if (jQuery.trim(lbdm) == ""){
					showAlert("��ѡ��������");
					return false;
				}	
				var xmsqrxm = jQuery("#xmsqrxm").val();
				if (jQuery.trim(xmsqrxm) == ""){
					showAlert("��Ŀ�����˲���Ϊ�գ�");
					return false;
				}	
				var xmsqsj = jQuery("#xmsqsj").val();
				if (jQuery.trim(xmsqsj) == ""){
					showAlert("����ʱ�䲻��Ϊ�գ�");
					return false;
				}	
				var xbjf = jQuery("#xbjf").val();
				if (jQuery.trim(xbjf) == ""){
					showAlert("�²����Ѳ���Ϊ�գ�");
					return false;
				}
				var url = "kycxgl_kycxxm_kycxxmjggl.do?method=addKycxxmjg&type="+type;
		      	ajaxSubFormWithFun("kycxxmjgForm",url,function(data){
		    	 if(data["message"]=="����ɹ���"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	 }});
		  	}
			jQuery(function(){
			});
		</script>
	</head>
	<body>
		<html:form action="/kycxgl_kycxxm_kycxxmjggl" method="post" styleId="kycxxmjgForm">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ŀ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="15%"><font class="red">*</font>ѧ��</th>
							<td width="35%">
								<html:select name="rs" property="xn" styleId="xn" style="width:130px">
									<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<th width="15%"><span class="red">*</span>������Ŀ����</th>
							<td width="35%">
								<html:text property="xmmc" styleId="xmmc" maxlength="50"/>
							</td>
					    </tr>
					    <tr>
							<th><font class="red">*</font>ָ����ʦ</th>
							<td>
								<input type="text" readonly="true" id="zdlsxm" value=""/>
								<button class="btn_01" type="button" onclick="showDialog('��ѡ��ָ����ʦ',680,480,'szdw_fdyjtff.do?method=showFdysNotF5');return false;">ѡ��</button>
								<input type="hidden" name="zdls" id="zdls" value=""/>
							</td>
							<th><font class="red">*</font>�������</th>
							<td>
								<html:select property="lbdm"  styleId="lbdm">
									<html:options collection="lbList" property="lbdm" labelProperty="lbmc" />
								</html:select>
							</td>
					    </tr>
					    <tr>
							<th><span class="red">*</span>��Ŀ������</th>
							<td>
								<html:text property="xmsqrxm" styleId="xmsqrxm" maxlength="10"/>
							</td>
							<th><span class="red">*</span>����ʱ��</th>
							<td>
								<html:text property="xmsqsj" styleId="xmsqsj" onclick="return showCalendar('xmsqsj','yyyy-MM-dd');" readonly="true" ></html:text>
							</td>
					    </tr>
					    <tr>
							<th><span class="red">*</span>�²����ѣ�Ԫ��</th>
							<td colspan="3">
								<html:text property='xbjf' styleId="xbjf" maxlength="20" onblur="checkMoneyForBlur(this)"
							onkeyup="checkMoneyForKeyup(this)"/>
							</td>
					    </tr>
					    <tr>
							<th>������Ϣ</th>
							<td colspan="3">
								<html:hidden property="fjxx" styleId="fjxx" />
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										jQuery.MultiUploader({
											maxcount : 3,
											//��׺
											accept : 'png|gif|jpg|zip|rar|doc|docx|pdf',
											//����ļ���С ��λM
											maxsize: 10,
											//��Ÿ������������id
											elementid : 'fjxx'
											});
									});
								</script>
							</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ŀ��Ա</span>
							</th>
						</tr>
					</thead>
				 </table>
				</div>
				<div>
					<table width="100%" border="0" class="datelist" style="margin:2px auto;">
						<thead>
							<tr>
								<td colspan="9">
								<button type="button" onclick="addKycxxmcy();return false;" class="btn_01">����ѧ��</button>
								<button type="button" onclick="delKycxxmcy();return false;" class="btn_01">ɾ��ѧ��</button>
								</td>
							</tr>
							<tr>
								<td width='5%'><input type="checkbox" name="selectAll" onclick="selectAllKycxxmcy(this);" /></td>
								<td width='11%'>ѧ��</td>
								<td width='11%'>����</td>
								<td width='6%'>�Ա�</td>
								<td width='6%'>�꼶</td>
								<td width='15%'><bean:message key="lable.xb" /></td>
								<td width='16%'>רҵ</td>
								<td width='20%'>�༶</td>
								<td width='15%'>��ϵ��ʽ</td>
							</tr>
						</thead>
						<tbody id="tbody_kycxxm_xs"></tbody>
					</table>
				</div>
				<div style="height: 40px"></div>
				<div>
					<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="bz">"<span class="red">*</span>"Ϊ������</div>
									<div class="btn">
										<button type="button" type="button" onclick="saveForm('save');">
											����
										</button>
										<button type="button" type="button" onclick="iFClose();">
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

