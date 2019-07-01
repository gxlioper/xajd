<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/syddk/js/dkxx.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript">
			function saveDksq(url){
				
				var xn = jQuery("#xn").val();
				var dkje = jQuery("#dkje").val();
				var dkqx = jQuery("#dkqx").val();
				var sqly = jQuery("#sqly").val();
				var xfyss = jQuery("#xfyss").val();
				var zsfyss = jQuery("#zsfyss").val();
				var hzjym = jQuery("#hzjym").val();
				var dkyh = jQuery("#dkyh").val();
				
				if (xfyss==""||zsfyss==""||xn == "" || dkje == "" || dkqx == "" || sqly == ""){
					showAlertDivLayer("�뽫��������д������");
					return false;
				}

				if("���ҿ�������"==dkyh.trim()&&""==hzjym.trim()){
					showAlertDivLayer("���ҿ������б�����д��ִ��");
					return false;
				}
				
				
				ajaxSubFormWithFun("xyddkForm",url,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						refershParent();
					}});
				});
			}
			
			jQuery(function(){
				
				var _getAfterXn = function(xn,c){
					
					var xnInfo = xn.split("-");
					var star = Number(jQuery.trim(xnInfo[0]))+c;
					var end = Number(jQuery.trim(xnInfo[1]))+c;
					
					return star + "-" + end;
				};
				
				
				jQuery("#xn").bind("change",function(){
					jQuery("#dkxxTable tr").remove();
					
					var xn = jQuery(this).val();
					var xz = jQuery("#xz").val();
										
					if (xn != "" && xz != ""){
						
						for (var i = 0 ; i < Number(xz); i++){
							var newXn = _getAfterXn(xn,i);
							
							var tr = jQuery("<tr></tr>");
							var td = jQuery("<td><input type='hidden' name='dkxn' value='"+newXn+"'/>"+newXn+"</td>");
							var td1 = jQuery("<td><input type='text' name='xf' maxlength='10' onkeyup='value=value.replace(/[^\\d]/g,\"\")'/></td>");
							var td2 = jQuery("<td><input type='text' name='zsf' maxlength='10' onkeyup='value=value.replace(/[^\\d]/g,\"\")'/></td>");
							var td3 = jQuery("<td><input type='text' name='shf' maxlength='10' onkeyup='value=value.replace(/[^\\d]/g,\"\")'/></td>");
							tr.append(td).append(td1).append(td2).append(td3);
							jQuery("#dkxxTable").append(tr);
						}
					}
				});
				//ȡ�����ݱ�xg_pjpy_new_pjxmb; �ֶΣ�xmmc																							
				var autoSetting = {
					dataTable:"xg_zxdk_syddk",
					dataField:"dkyh",
					scrollHeight:135										
				}
				// ģ��������������Ŀ���ơ�
				jQuery("#dkyh").setAutocomplete(autoSetting);
			});
			
		</script>
	</head>
	<body>
		<html:form action="/zxdkSyddk" method="post" styleId="xyddkForm">
			<input type="hidden" name="xz" id="xz" value="${jbxx.xz }"/>
			<html:hidden property="id" />
			<html:hidden property="dkzesx" styleId="dkzesx" value="${cssz.dkzesx }"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:20%;"><font color="red">*</font>����ѧ��</th>
							<td>
								<html:select property="xn" styleId="xn">
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
							</td>
							<th style="width:20%;"><font color="red">*</font>��������</th>
							<td>
								<html:text  property="dkyh" styleId="dkyh" maxlength="50" ></html:text>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>�����Ԫ��</th>
							<td>
								<html:text property="dkje" styleId="dkje" maxlength="10" onblur="checkZje(this);" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th>��ִ������</th>
							<td colspan="3">
								<html:text property="hzjym" styleId="hzjym" maxlength="20" ></html:text>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<font color="red">ע�⣺�밴ѧ���ÿ������ʵ��д���������ա�</font>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<table style="width:100%;text-align:center;" >
									<thead>
										<tr>
											<td align="center">ѧ��</td>
											<td align="center">ѧ�ѣ�Ԫ��</td>
											<td align="center">ס�޷ѣ�Ԫ��</td>
											<td align="center">����ѣ�Ԫ��</td>
										</tr>
									</thead>
									<tbody id="dkxxTable">
										<logic:present name="dkxxList">
											<logic:iterate id="d" name="dkxxList">
												<tr>
													<td>
														${d.xn }
														<input type="hidden" name="dkxn" value="${d.xn }"/>
													</td>
													<td>
														<input value="${d.xf }" name="xf" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"/>
													</td>
													<td>
														<input value="${d.zsf }" name="zsf" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"/>
													</td>
													<td>
														<input value="${d.shf }" name="shf" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"/>
													</td>
												</tr>
											</logic:iterate>
										</logic:present>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>��������
								<br/><font color="red">(������500��)</font>
							</th>
							<td colspan="3">
								<html:textarea property="sqly" styleId="sqly" 
											   onblur="checkLen(this,500);"
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
				<div style="height: 30px"></div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveDksq('zxdkSyddk.do?method=update');">
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