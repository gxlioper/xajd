<%@ page language="java" contentType="text/html; charset=GBK"%>
<!-- ��ʾ��Ϣ -->
<logic:present name="delMessage">
				<!-- ���������ʾ�� -->
				<div id="delMesDiv" style="display: none">
					<div class="tab">
						<table class="formlist">
							<thead>
								<tr>
									<th>
										<span>
										<logic:iterate name="delMessage" id="mes" offset="0" length="1">
											${mes}
										</logic:iterate>
											�����޷�ɾ������ȷ����һ������
										</span>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<input type="hidden"  name="next" id="next_cz" value="gb"/>
										<logic:iterate name="delMessage" id="mes" indexId="index" offset="1">
											<input type="radio" name="next" id="next_${index}" value="${mes.path}" onclick="$('next_cz').value = this.value" />${mes.message}<br/>
										</logic:iterate>
										<input type="radio" name="next" id="next_gb" checked="checked" value="gb" onclick="$('next_cz').value = this.value" />�ر�
									</td>
								</tr>
							</tbody>
							<tfoot>
							<tr>
								<td>
									<div class="btn">
										<!-- ȷ�� -->
										<button type="button" onclick="nextCz()">
											<bean:message key="lable.btn_qd_space" />
										</button>
									</div>
								</td>
							</tr>
							</tfoot>
						</table>
					</div>
				</div>
				<script defer="defer">
					function showNextDiv(){
						viewTempDiv("��ѡ����һ������","delMesDiv",410,200);
						if($("message")){
							$("message").value = "";
						}
						if($("doType")){
							$("doType").value = "";	
						}
					}
					setTimeout("showNextDiv()",100);
				
					
					function nextCz(){
						var next = $("next_cz").value;
						
						if(next==""){
							
							next=$("next_1").value;
						}
						
						if(next == "gb"){//�ر�
							hiddenMessage(true,true);
						}else {
							if(next.indexOf("?")==-1){
								next+="?doType=scjc";
							}else{
								next+="&doType=scjc";
							}
							refreshForm(next);
						}
					}
				</script>
			</logic:present>