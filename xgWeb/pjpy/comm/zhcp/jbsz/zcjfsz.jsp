<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		
		//���Ӽӷ���Ŀ
		function addJfxm(xmdm){
			var div_id = "div_"+xmdm;
			var count_id = "hid_count_"+xmdm;
			var count = parseInt($(count_id).value)+1;
			
			var divHtml = $(div_id).innerHTML;
				divHtml +="<div id=\"div_"+xmdm+"_"+count+"\">";
				divHtml +="<table width=\"100%\">";
				divHtml +="<tr>";
				divHtml +="<td width=\"5%\">";
				divHtml +="<input type=\"checkbox\" name=\"ck_"+xmdm+"\"id=\"ck_"+xmdm+"_"+count+"\"/>";
				divHtml +="<input type=\"hidden\" name=\"xmdm\" value=\""+xmdm+"\"/>";
				divHtml +="<input type=\"hidden\" name=\"jfdm\" style=\"width: 80px\" value=\""+count+"\" maxlength=\"10\"/>";
				divHtml +="</td>";

				divHtml +="<td width=\"25%\">";
				divHtml +="<input type=\"text\" name=\"jfmc\" id=\"jfmc_"+xmdm+"_"+count+"\" style=\"width: 80px\" maxlength=\"25\"/>";
				divHtml +="</td>";
				
				divHtml +="<td width=\"20%\">";
				divHtml +="<select name=\"jjf\">";
				
				var zcjflx = $("zcjflx").value;
				if(zcjflx == "jjf"){
					divHtml +="<option value=\"�ӷ�\" selected=\"selected\">�ӷ�</option>";
					divHtml +="<option value=\"����\">����</option>";
				}else if(zcjflx == "jf"){
					divHtml +="<option value=\"�ӷ�\" selected=\"selected\">�ӷ�</option>";
				}
				divHtml +="</select>";
				divHtml +="</td>";
						
				divHtml +="<td width=\"25%\">";
				divHtml +="<input type=\"text\" name=\"xxf\" id=\"xxf_"+xmdm+"_"+count+"\"";
				divHtml +=" onkeydown=\"return onlyNum(this,2)\" onmousedown=\"return onlyNum(this,2)\"";
				divHtml +=" maxlength=\"2\" style=\"width : 80px;ime-mode:disabled\"/>";
				divHtml +="</td>";
				
				divHtml +="<td width=\"25%\">";
				divHtml +="<input type=\"text\" name=\"sxf\" id=\"sxf_"+xmdm+"_"+count+"\"";
				divHtml +=" onkeydown=\"return onlyNum(this,2)\" onmousedown=\"return onlyNum(this,2)\"";
				divHtml +=" maxlength=\"2\" style=\"width : 80px;ime-mode:disabled\"/>";
				divHtml +="</td>";
						
				divHtml +="</tr>";
				divHtml +="</table>";	

				divHtml +="</div>";

			setTimeout("$('"+count_id+"').value = '"+count+"'",100);
			$(div_id).innerHTML = divHtml;
		}
		
		//ɾ���ӷ���Ŀ
		function delJfxm(xmdm){
			var ch_name = "ck_"+xmdm;
			var num =  document.getElementsByName(ch_name).length;

			for(var i=(num-1);i>=0;i--){
				var obj = document.getElementsByName(ch_name)[i];
				var id = obj.id;
				
				if(obj.checked){
					var div_id = "div_"+xmdm+"_"+id.replace("ck_"+xmdm+"_","");
					if(div_id){
						$(div_id).outerHTML = "";
					}
				}
			}
		}
		
		//ȫѡ
		function allChoose(xmdm){
			var count_id = "hid_count_"+xmdm;
			var ch_all_id = "ch_all_"+xmdm;

			var count = $(count_id).value;

			for(var i=1;i<=count;i++){
				var ch_id = "ck_"+xmdm+"_"+i;

				if($(ch_id)){
					$(ch_id).checked = $(ch_all_id).checked;
				}
			}
			
		}
		
		//����ӷ�����
		function saveJfsz(){
		
			if(checkInputValue()){
				if (confirm("ȷ������ά���ļӷ���Ŀ��")) {
					saveUpdate("/xgxt/zhcpJbsz.do?method=zcjfsz&doType=save","");
				}
			}
		}
		
		//���¼��ֵ
		function checkInputValue(){
			var num =  document.getElementsByName("zcxm").length;
			
			for(var i=0;i<num;i++){
				var xmdm_obj = document.getElementsByName("zcxm")[i];
				var xmmc_obj = document.getElementsByName("xmmc")[i];
				var count_obj = document.getElementsByName("hid_count")[i];
				
				var xmdm = xmdm_obj.value;
				var xmmc = xmmc_obj.value;
				var count = count_obj.value;
				
				for(var j=1;j<=count;j++){
					//�ӷ�����
					var jfmc_id = "jfmc_"+xmdm+"_"+j;
					
					if($(jfmc_id)){
						if($(jfmc_id).value == ""){
							$(jfmc_id).focus();
							alert(xmmc+"�ӷ���Ŀ���ڼӷ�����Ϊ�գ���ȷ��!");
							return false;
						}
					}
					
					//���޷�
					var xxf_id = "xxf_"+xmdm+"_"+j;
					
					if($(xxf_id)){
						if($(xxf_id).value == ""){
							$(xxf_id).focus();
							alert(xmmc+"�ӷ���Ŀ�������޷�Ϊ�գ���ȷ��!");
							return false;
						}
					}
					
					//���޷�
					var sxf_id = "sxf_"+xmdm+"_"+j;
					
					if($(sxf_id)){
						if($(sxf_id).value == ""){
							$(sxf_id).focus();
							alert(xmmc+"�ӷ���Ŀ�������޷�Ϊ�գ���ȷ��!");
							return false;
						}
					}
					
					if($(xxf_id) && $(sxf_id)){
						if(eval($(sxf_id).value) <= eval($(xxf_id).value)){
							$(sxf_id).focus();
							alert(xmmc+"�ӷ���Ŀ�������޷�С�ڵ������޷ֵ��������ȷ��!");
							return false;
						}
					}
				}
			}
			
			return true;
		}
		</script>
	</head>
	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- ���� end-->
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt">
			<h3>
				<span>�۲����ڣ�</span>
			</h3>
			<p>
				<logic:equal name="zczq" value="xn">
					����ѧ��(${pjxn })
				</logic:equal>
				<logic:equal name="zczq" value="xq">
					����ѧ��(${pjxn }) ����ѧ��(${pjxqmc })&nbsp;&nbsp;
				</logic:equal>		
			</p>
		</div>
		<!-- ��ʾ��Ϣ end-->	
		
		<html:form action="/zhcpJbsz">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>		
			<input type="hidden" id="zcjflx" value="${zcjflx }"/>
			<logic:empty name="rsList">
				<table align="center" width="100%">
					<tr>
						<td>
							<div align="center">
								<div class="page_prompt">
									<div class="page_promptcon">
										<h3>
											��ܰ��ʾ��
										</h3>
										<p>
											��ǰ�۲�����δ�����۲���Ŀ,����и�����Ŀ�������������Ա��ϵ��
										</p>
									</div>
									<p>
										&nbsp;
									</p>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</logic:empty>
			<logic:notEmpty name="rsList">
			<div class="tab">		
				<table class="formlist" border="0" align="center">
				
					<!-- ����������Ϣ -->
<%--					<thead>--%>
<%--						<tr>--%>
<%--							<th colspan="4">--%>
<%--								<span>�۲������Ϣ</span>--%>
<%--							</th>--%>
<%--						</tr>--%>
<%--					</thead>--%>
<%--					<tbody>		--%>
<%--						<tr>--%>
<%--							<th width="16%">--%>
<%--								����ѧ��--%>
<%--							</th>--%>
<%--							<td width="34%">							--%>
<%--								${pjxn }--%>
<%--							</td>--%>
<%--							<th width="16%">--%>
<%--								����ѧ��--%>
<%--							</th>--%>
<%--							<td width="34%">							--%>
<%--								${pjxqmc }--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<th>--%>
<%--								�������--%>
<%--							</th>--%>
<%--							<td>							--%>
<%--								${pjnd }--%>
<%--							</td>--%>
<%--							<th>--%>
<%--								�۲�����--%>
<%--							</th>--%>
<%--							<td>--%>
<%--								<logic:equal name="zczq" value="xn">--%>
<%--									ѧ��--%>
<%--								</logic:equal>--%>
<%--								<logic:equal name="zczq" value="xq">--%>
<%--									ѧ��--%>
<%--								</logic:equal>		--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--					</tbody>--%>
					<!-- ����������Ϣ end-->
					
					<!-- �۲�ӷ����� -->
					<thead>
						<tr>
							<th colspan="4">
								<span>�۲�ӷ�����</span>
							</th>
						</tr>
					</thead>
					<tbody>		
						<tr>
							<td colspan="4">
								<div style="width:100%;height:400px;overflow-x:hidden;overflow-y:auto;">	
									<logic:notEmpty name="rsList">
										<logic:iterate name="rsList" id="rs">
											<table width="100%">
												<tr>
													<td colspan="6">
														${rs.xmmc }
														<input type="hidden" name="xmmc" value="${rs.xmmc }"/>
														<input type="hidden" name="zcxm" value="${rs.xmdm }"/>
														<span>
															<button type="button" onclick="addJfxm('${rs.xmdm }')" id="buttonAdd_${rs.xmdm }" style="width: 80px">
																������Ŀ
															</button>
															<button type="button" onclick="delJfxm('${rs.xmdm }')" id="buttonDel_${rs.xmdm }" style="width: 80px">
																ɾ����Ŀ
															</button>
														</span>
													</td>
												</tr>
												<tr>
													<td colspan="6">
														<div id="div_${rs.xmdm }">
															<div>
																<input type="hidden" name="hid_count" id="hid_count_${rs.xmdm }" value="${rs.jfxmNum }"/>
																<table width="100%">
																	<tr>
																		<td width="5%"><input type="checkbox" id="ch_all_${rs.xmdm }" onclick="allChoose('${rs.xmdm }')"/></td>
																		<td width="25%">�ӷ���Ŀ����</td>
																		<td width="20%">�Ӽ���</td>
																		<td width="25%">���޷�</td>
																		<td width="25%">���޷�</td>
																	</tr>
																</table>
															</div>
															<logic:notEmpty name="rs" property="xmList">
																<logic:iterate name="rs" property="xmList" id="jfxm">
																	<div id="div_${rs.xmdm }_${jfxm.num }">
																		<table width="100%">
																			<tr>
																				<td width="5%">
																					<input type="checkbox" name="ck_${rs.xmdm }" id="ck_${rs.xmdm }_${jfxm.num }"/>
																					<input type="hidden" name="xmdm" value="${jfxm.xmdm }"/>
																					<input type="hidden" name="jfdm" id="jfdm_${rs.xmdm }_${jfxm.num }" value="${jfxm.jfdm }" style="width: 80px" maxlength="10"/>
																				</td>
																				<td width="25%">
																					<input type="text" name="jfmc" id="jfmc_${rs.xmdm }_${jfxm.num }" value="${jfxm.jfmc }" style="width: 80px" maxlength="25"/>
																				</td>
																				<td width="20%">
																					<select name="jjf">
																						<!-- �Ӽ��� -->
																						<logic:equal name="zcjflx" value="jjf">
																							<logic:equal name="jfxm" property="jjf" value="�ӷ�">
																								<option value="�ӷ�" selected="selected">�ӷ�</option>
																								<option value="����">����</option>
																							</logic:equal>
																							<logic:equal name="jfxm" property="jjf" value="����">
																								<option value="�ӷ�">�ӷ�</option>
																								<option value="����" selected="selected">����</option>
																							</logic:equal>
																						</logic:equal>
																						<!-- ���ӷ� -->
																						<logic:equal name="zcjflx" value="jf">
																							<option value="�ӷ�" selected="selected">�ӷ�</option>
																						</logic:equal>
																					</select>
																				</td>
																				<td width="25%">
																					<input type="text" name="xxf" value="${jfxm.xxf }" 
																						id="xxf_${rs.xmdm }_${jfxm.num }"
																						onkeydown="return onlyNum(this,2)"
																						onmousedown="return onlyNum(this,2)"
																						maxlength="2" 
																						style="width : 80px;ime-mode:disabled"/>
																				</td>
																				<td width="25%">
																					<input type="text" name="sxf" value="${jfxm.sxf }" 
																						id="sxf_${rs.xmdm }_${jfxm.num }"
																						onkeydown="return onlyNum(this,2)"
																						onmousedown="return onlyNum(this,2)"
																						maxlength="2" 
																						style="width : 80px;ime-mode:disabled"/>
																				</td>
																			</tr>
																		</table>
																	</div>
																</logic:iterate>
															</logic:notEmpty>
														</div>
													</td>
												</tr>
											</table>
											</br>
										</logic:iterate>
									</logic:notEmpty>
								</div>
							</td>
						</tr>
					</tbody>
					<!-- �۲�ӷ����� end -->
					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="saveJfsz()" id="buttonSave" style="width: 80px">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>	
			</logic:notEmpty>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>