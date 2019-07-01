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
				
				var flg = true;
				
				jQuery.each(jQuery("#dataList tr"),function(i,n){
					if (i != 0 && flg){
						var fldm = jQuery(n).find("select[name=fldmArr] option:selected").val();
						var sbdm = jQuery(n).find("select[name=sbdmArr] option:selected").val();
						var jysj = jQuery(n).find("input[name=jysjArr]").val();
						
						flg = (fldm != "" && sbdm !="" && jysj !="");
					}
				});
				
				if (jQuery.trim(jQuery("#zgh").val()) == "" || !flg){
					showAlert("�뽫��������д������");
					return false;
				}
				
				if (jQuery("#dataList tr").size() == 1){
					showAlert("����д�����豸��Ϣ��");
					return false;
				}
				
				var url = "rcswSbglJyjl.do?method=save";
				ajaxSubFormWithFun("form",url,function(data){
					showAlert(data["message"]);
					refershParent();
				});
			}
			
			function addRow(){
				var zgh = jQuery("#zgh").val();
				
				if (zgh == ""){
					showAlert("����ѡ���ְ����");
					return false;
				}
				jQuery("#dataList").append(jQuery("#dataList tr:eq(0)").clone().show());
			}
			
			function delRow(obj){
				jQuery(obj).parents("tr:eq(0)").remove();
			}
			
			function getSbxx(obj){
				
				var sbdmSelect = jQuery(obj).parents("tr:eq(0)").find("select[name=sbdmArr]");
				var fldm = jQuery("option:selected",jQuery(obj)).val();
				
				sbdmSelect.find("option").remove();
				
				if (fldm == ""){
					return false;
				}
				
				jQuery.getJSON("rcswSbglSbxx.do?method=getSbxxByFldm",{fldm:fldm},function(data){
					
					sbdmSelect.append("<option value=''></option>");
					
					jQuery.each(data,function(i,n){
						var option = jQuery("<option value='"+n["dm"]+"'>"+n["mc"]+"</option>");
						sbdmSelect.append(option);
					});
				});
			}
			
		</script>
	</head>
	<body>
		<html:form action="/rcswSbglJyjl" method="post" styleId="form">
			<html:hidden property="jbr" value="${userName }"/>
		
			<div style='overflow-x:hidden;overflow-y:auto;height:410px;margin-bottom: 0px;'>
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
							<th style="width:15%"><font color="red">*</font>ְ����</th>
							<td style="width:35%"><html:text property="zgh" readonly="true" styleId="zgh" style="width:60%" />
							<button class="btn_01" type="button"  
										onclick="showDialog('��ѡ��һ����ְ��',680,480,'szdw_fdyjtff.do?method=showFdys&goto=${path}');return false;">ѡ��</button>
							</td>
							<th style="width:15%">����</th>
							<td style="width:35%">
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
								<span>�����豸��Ϣ
									<a onclick="addRow();" href="javascript:void(0);">
										<img src="images/knsrd/jiahao.gif" />
									</a>
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table class="dateline" width="100%">
									<thead>
										<tr>
											<td style="text-align: center;"><font color="red">*</font>�豸���� </td>
											<td style="text-align: center;"><font color="red">*</font>�豸���� </td>
											<td style="text-align: center;"><font color="red">*</font>����ʱ�� </td>
											<td style="text-align: center;">��ע </td>
											<td style="text-align: center;">���� </td>
										</tr>
									</thead>
									<tbody id="dataList">
										<tr style="display:none;">
											<td>
												<html:select property="fldmArr" onchange="getSbxx(this);">
													<html:option value=""></html:option>
													<html:options collection="sbflList" property="dm" labelProperty="mc"/>
												</html:select>
											</td>
											<td>
												<html:select property="sbdmArr" style="width:100px;">
													<html:option value=""></html:option>
												</html:select>
											</td>
											<td>
												<html:text property="jysjArr" readonly="true" 
												value="${now }" styleId="jysj"
												onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})"></html:text>
											</td>
											<td>
												<html:text property="bzArr" maxlength="50"></html:text>
											</td>
											<td>
												<a href="#" onclick="delRow(this);" class="name">ɾ��</a>
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<th>����ԭ��<br/>
								<font color="red">����400�֣�</font>
							</th>
							<td colspan="3">
								<html:textarea property="jyyy" styleId="jyyy" 
											   onblur="checkLen(this,400);"
											   style="width:99%;" rows="4"></html:textarea>
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

