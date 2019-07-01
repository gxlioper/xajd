<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		//�����������
		function saveJbsz(){
			if (confirm("ȷ������ѡ��Ŀ���״̬��")) {
				saveUpdate("/xgxt/zhcpJbsz.do?method=dypfsz&doType=save","");
			}
		}
		
		//��ʾ�������ò�
		function showPlszDiv(){
			viewTempDiv("��ȷ�����а༶���������","plszDiv",300,100);
		}
		
		//��������
		function plsz(){
			
			var kgzt = $("plsz_kgzt").value;
			var num =  document.getElementsByName("bjdm").length;

			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("bjdm")[i];
				var bjdm = obj.value;	
				var yes_id = "rad_kgzt_yes_"+bjdm;
				var no_id = "rad_kgzt_no_"+bjdm;
				var kgzt_id = "hid_kgzt_"+bjdm;
				
				if(kgzt == "yes"){
					$(yes_id).checked=true;
					$(no_id).checked=false;
				}else{
					$(yes_id).checked=false;
					$(no_id).checked=true;
				}
				$(kgzt_id).value = kgzt;
			}
			
			hiddenMessage(true,true);
		}
		</script>
	</head>
	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/zhcpJbsz">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>		
			<div class="tab">		
				<table class="formlist" border="0" align="center">
				
					<!-- ����������Ϣ -->
					<thead>
						<tr>
							<th colspan="4">
								<span>�۲������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>		
						<tr>
							<th width="16%">
								����ѧ��
							</th>
							<td width="34%">							
								${pjxn }
							</td>
							<th>
								�۲�����
							</th>
							<td>
								<logic:equal name="zczq" value="xn">
									ѧ��
								</logic:equal>
								<logic:equal name="zczq" value="xq">
									ѧ��
								</logic:equal>		
							</td>
						</tr>
					</tbody>
					<!-- ����������Ϣ end-->
					
					<!-- ��ʦ������Ϣ -->
					<thead>
						<tr>
							<th colspan="4">
								<span>�û�������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>		
						<tr>
							<th width="16%">
								ְ����
							</th>
							<td width="34%">							
								${rs.zgh }
							</td>
							<th width="16%">
								����
							</th>
							<td width="34%">							
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td>							
								${rs.xb }
							</td>
							<th>
								���ڲ���
							</th>
							<td>							
								${rs.bmmc }
							</td>
						</tr>
					</tbody>
					<!-- ��ʦ������Ϣ end-->
					
					<!-- �༶��������-->
					<thead>
						<tr>
							<th colspan="4">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<tbody>		
						<tr>
							<td colspan="4">							
								<div style="width:100%;height:200px;overflow-x:hidden;overflow-y:auto;">
									<table width="100%">
										<tr>
											<td>
												�꼶
											</td>
											<td>
												�༶����
											</td>
											<td>
												����״̬
											</td>
										</tr>
										<logic:iterate name="rs" property="bjList" id="bjInfo">
											<tr>
												<td>
													${bjInfo.nj }&nbsp;
												</td>
												<td>
													${bjInfo.bjmc }&nbsp;
													<logic:notEmpty name="bjInfo" property="bjdm">
														<input type="hidden" name="bjdm" value="${bjInfo.bjdm }"/>
													</logic:notEmpty>
												</td>
												<td>
													<!-- ѧ������ -->
													<logic:equal name="bjInfo" property="kgzt" value="yes">
														<input type="radio" name="rad_kgzt_${bjInfo.bjdm }" 
															id="rad_kgzt_yes_${bjInfo.bjdm }"
															onclick="$('hid_kgzt_${bjInfo.bjdm }').value=this.value"
															value="yes" checked="checked"/>
														ѧ������
														<input type="radio" name="rad_kgzt_${bjInfo.bjdm }" 
															id="rad_kgzt_no_${bjInfo.bjdm }"
															onclick="$('hid_kgzt_${bjInfo.bjdm }').value=this.value"
															value="no"/>
														��ʦȷ��
													</logic:equal>
													<!-- ��ʦȷ�� -->
													<logic:equal name="bjInfo" property="kgzt" value="no">
														<input type="radio" name="rad_kgzt_${bjInfo.bjdm }" 
															id="rad_kgzt_yes_${bjInfo.bjdm }"
															onclick="$('hid_kgzt_${bjInfo.bjdm }').value=this.value"
															value="yes"/>
														ѧ������
														<input type="radio" name="rad_kgzt_${bjInfo.bjdm }" 
															id="rad_kgzt_no_${bjInfo.bjdm }"
															onclick="$('hid_kgzt_${bjInfo.bjdm }').value=this.value"
															value="no" checked="checked"/>
														��ʦȷ��
													</logic:equal>
													<logic:notEmpty name="bjInfo" property="bjdm">
														<input type="hidden" name="kgzt" id="hid_kgzt_${bjInfo.bjdm }" value="${bjInfo.kgzt }"/>
													</logic:notEmpty>
												</td>
											</tr>
										</logic:iterate>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
					<!-- �༶�������� end-->
					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="showPlszDiv();" id="buttonSetup" style="width: 80px">
										��������
									</button>
									<button type="button" onclick="saveJbsz()" id="buttonSave" style="width: 80px">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>	
			
			<!-- �Զ�����Div-->
			<div id="plszDiv" style="display: none">
				<table class="formlist">
					<tbody>
						<tr>
							<td>
								<input type="radio" 
									onclick="$('plsz_kgzt').value=this.value"
									name="rad_kgzt" 
									value="yes" 
									checked="checked"/>
								ѧ������
								<input type="radio" 
									onclick="$('plsz_kgzt').value=this.value"
									name="rad_kgzt" 
									value="no"/>
								��ʦȷ��
								<input type="hidden" id="plsz_kgzt" value="yes"/>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td>
								<div class="btn">
									<button type="button" onclick="plsz();">
										ȷ ��
									</button>
									<button type="button" onclick="hiddenMessage(true,true);return false;" id="buttonClose">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- �Զ�����Div end-->
					
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>