<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function saveForm(){
				if (jQuery.trim(jQuery("#ecmm").val()) == "" 
						|| jQuery.trim(jQuery("#cfmm").val()) == "" 
						|| jQuery.trim(jQuery("#zgh").val()) == ""){
					showAlert("�뽫��������д������");
					return false;
				}
				
				if (jQuery.trim(jQuery("#ecmm").val()) != jQuery.trim(jQuery("#cfmm").val())){
					showAlert("�����������벻һ�£�");
					return false;
				}
				
				var isUpdate = jQuery("#isUpdate").val();
				
				var url = Boolean(isUpdate) ? "zdxljkEcmm.do?method=edit" : "zdxljkEcmm.do?method=save";
				ajaxSubFormWithFun("form",url,function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						refershParent();
					}});
				});
			}
			
		</script>
	</head>
	<body>
		<html:form action="/zdxljkEcmm" method="post" styleId="form">
				<input type="hidden" value="${update }" id="isUpdate"/>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��ְ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:18%"><font color="red">*</font>ְ����</th>
							<td style="width:32%"><html:text property="zgh" readonly="true" styleId="zgh" style="width:60%" />
							<button class="btn_01" type="button"  
										onclick="showDialog('��ѡ��һ����ְ��',680,480,'szdw_fdyjtff.do?method=showFdys&goto=${path}');return false;">ѡ��</button>
							</td>
							<th style="width:18%">����</th>
							<td style="width:32%">
								${jbxx.xm }
							</td>
						</tr>
						
						<tr>
							<th>�Ա�</th>
							<td>
								${jbxx.xbmc }
							</td>
							<th>��ϵ�绰</th>
							<td>
								${jbxx.lxdh }
							</td>
						</tr>
						<tr>
							<th>����</th>
							<td>
								${jbxx.bmmc }
							</td>
							<th></th>
							<td>
								
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>
									��������ά��
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>��������</th>
							<td>
								<input type="password" name="ecmm" id="ecmm" maxlength="20" autocomplete="off" value=""/>
							</td>
							<th><font color="red">*</font>ȷ�϶�������</th>
							<td>
								<input type="password" name="cfmm" id="cfmm" maxlength="20"/>
							</td>
						</tr>
						<tr>
							<th>
								��ע
								<font color="red">(��200��)</font>
							</th>
							<td colspan="3">
								<html:textarea property="bz" styleId="bz" 
											   onblur="checkLen(this,200);"
											   style="width:99%;" rows="5"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
										�� ��
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

