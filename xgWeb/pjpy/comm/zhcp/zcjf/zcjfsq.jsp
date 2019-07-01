<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		//����ӷ���Ŀ
		function clickJf(xmdm,jfdm){
		
			var sqfs_id = "sqfs_"+xmdm+"_"+jfdm;
			var sqfs_real_id = "sqfs_real_"+xmdm+"_"+jfdm;
			
			if($(sqfs_id)){
				if($(sqfs_id).disabled == true){
					$(sqfs_id).disabled = false;
					$(sqfs_id).value = "";
					$(sqfs_id).focus();
				}else{
					$(sqfs_id).value = "�빴ѡ";
					$(sqfs_real_id).value = "";
					$(sqfs_id).disabled = true;	
				}
			}
		}
		
		//��֤�ӷ�
		function checkJf(xmdm,jfdm){
		
			var sqfs_id = "sqfs_"+xmdm+"_"+jfdm;
			var xxf_id = "xxf_"+xmdm+"_"+jfdm;
			var sxf_id = "sxf_"+xmdm+"_"+jfdm;
			
			var sqfs = $(sqfs_id).value;
			var xxf = $(xxf_id).value;
			var sxf = $(sxf_id).value;
			
			if(parseInt(sqfs)<parseInt(xxf)){
				$(sqfs_id).value = xxf;
			}else if(parseInt(sqfs)>parseInt(sxf)){
				$(sqfs_id).value = sxf;
			}
		}
		
		//����ӷ�����
		function saveJfsq(){
			if(checkInputValue()){
				if (confirm("ȷ����������ļӷ֣�")) {
					saveUpdate("/xgxt/zhcpJfsq.do?method=zcjfsq&doType=save","");
				}
			}else{
				alert("����������һ��ӷ�!");
			}
		}
		
		//���¼��ֵ
		function checkInputValue(){
			var num =  document.getElementsByName("sqfs").length;
			var flag = false;
			
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("sqfs")[i];
				if(obj.disabled == false && obj.value !=""){
					flag = true;
					break;
				}
			}
			
			return flag;
		}
		
		//�����������
		function setSqfs(xmdm,jfdm){
		
			var sqfs_id = "sqfs_"+xmdm+"_"+jfdm;
			var sqfs_real_id = "sqfs_real_"+xmdm+"_"+jfdm;

			$(sqfs_real_id).value = $(sqfs_id).value;
		}
		
		//������������
		function setSqly(xmdm){
			var sqly_id = "sqly_"+xmdm;
			var sqly = $(sqly_id).value;
			
			var num =  document.getElementsByName("sqly").length;
			
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("sqly")[i];
				var id = obj.id;
				var inputId = id.split('_')[0]+"_"+id.split('_')[1];
				if(inputId == sqly_id){
					obj.value=sqly;
				}
			}
		}
		
		function setDivHeight(){
			if($("div_jfxm")){
				if($("tb_xsxx").style.display == "none"){
					$("div_jfxm").style.height="400px";
				}else{
					$("div_jfxm").style.height="250px";
				}
			}			
		}
		
		function showZcjfsm(){
		
			tipsWindown("ϵͳ��ʾ","id:div_zcjfsm","570","390","true","","true","id");
		}
		</script>
	</head>
	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title}</a>
			</p>
		</div>
		<!-- ���� end-->
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt">
			<h3>
				<span>�۲����ڣ�</span>
			</h3>
			<p>
				����ѧ��(${pjxn })&nbsp;&nbsp;
				<logic:equal name="zczq" value="xq">
				����ѧ��(${pjxqmc })&nbsp;&nbsp;
				</logic:equal>
				
				�۲�����
				<logic:equal name="zczq" value="xn">
					(ѧ��)
				</logic:equal>
				<logic:equal name="zczq" value="xq">
					(ѧ��)
				</logic:equal>		
			</p>
		</div>
		<!-- ��ʾ��Ϣ end-->	
		
		<html:form action="/zhcpJfsq">
			<!-- ������ -->
			<input type="hidden" id="tableName" name="tableName" value="view_xsjbxx"/>
			<input type="hidden" id="lx" name="lx" value="ѧ��"/>
			<input type="hidden" id="url" name="url" value="/xgxt/zhcpJfsq.do?method=zcjfsq"/>
			<%@ include file="/comm/hiddenValue.jsp"%>		
			<input type="hidden" id="zcjflx" value="${zcjflx }"/>
			<div class="tab">		
				<table class="formlist" border="0" align="center">
				
					<!-- ����������Ϣ -->
<%--					<thead>--%>
<%--						<tr onclick="showHiddenNr('tb_zcxx')" style="cursor: hand">--%>
<%--							<th colspan="4">--%>
<%--								<span>�۲������Ϣ</span>--%>
<%--							</th>--%>
<%--						</tr>--%>
<%--					</thead>--%>
<%--					<tbody id="tb_zcxx">		--%>
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
					
					<!-- ѧ��������Ϣ -->
					<thead>
						<tr onclick="showHiddenNr('tb_xsxx');setDivHeight();" style="cursor: hand">
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tb_xsxx">		
						<tr>
							<th width="16%">
								ѧ��	
							</th>
							<td width="34%">		
								<!-- ѧ���û� -->
								<logic:equal name="userType" value="stu">
									${rs.xh }
								</logic:equal>					
								<!-- ѧ���û� -->
								<logic:notEqual name="userType" value="stu">
									<input type="text" name="xh" id="xh" value="${rs.xh }" readonly="readonly"/>
									<button type="button" onclick="sendXx();return false;"
										class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</logic:notEqual>	
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
								�꼶
							</th>
							<td>							
								${rs.nj }
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								${rs.xymc }
							</td>
						</tr>
						<tr>
							<th>
								רҵ
							</th>
							<td>							
								${rs.zymc }
							</td>
							<th>
								�༶
							</th>
							<td>
								${rs.bjmc }
							</td>
						</tr>
					</tbody>
					<!-- ѧ��������Ϣ end-->
					
					<!-- �۲�ӷ���Ϣ -->
					<logic:notEmpty name="rs" property="zcxmList">
						<thead>
							<tr  style="cursor: hand"  onclick="showHiddenNr('tb_zcjf');">
								<th colspan="4">
									<span>����ӷ���Ŀ</span>
								</th>
							</tr>
						</thead>
						<tbody id="tb_zcjf">		
							<tr>
								<td colspan="4">
									<!-- ��ʾ��Ϣ end-->
									<div class="prompt">
											��ʾ��1����Ҫ������صļӷ����ȶ�����й�ѡ��2�����ڣ�С�ڣ����޷֣����޷֣��Ļ��������޷֣����޷֣�Ϊ׼��<br/>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3���粻ע���ӷ����ɣ��ӷ���Ч��
											<a href="#" onclick="showZcjfsm();return false;"><font color="blue"><U>�۲�ӷ�˵��</U></font></a>
										<a class="close" title="����"
										   onclick="this.parentNode.style.display='none';"></a>
									</div>
									<!-- ��ʾ��Ϣ end-->									
									<div style="width:100%;height:250px;overflow-x:hidden;overflow-y:auto;" id="div_jfxm">		
										<logic:iterate name="rs" property="zcxmList" id="zcxm">
											<table width="100%">
												<thead>
													<tr onclick="showHiddenNr('tb_jfxm_${zcxm.xmdm }')" style="cursor: hand">
														<td colspan="2">
															${zcxm.xmmc }
														</td>
													</tr>
												</thead>
												<tbody id="tb_jfxm_${zcxm.xmdm }">
													<tr>
														<td width="16%">
															�ӷ���Ŀ
														</td>
														<td>
															<table>
																<%int n=0; %>
																<tr>
																<logic:iterate name="zcxm" property="xmList" id="jfxm">
																	<%n++; %>
																	<logic:empty name="jfxm" property="sqfs">
																		<td>
																			<input type="checkbox" id="ch_${zcxm.xmdm }_${jfxm.jfdm }" onclick="clickJf('${zcxm.xmdm }','${jfxm.jfdm }')"/>
																			<span title="${jfxm.jfmc }">${jfxm.showmc }</span>
																			<input type="hidden" name="xmdm" value="${zcxm.xmdm }"/>
																			<input type="hidden" name="jfdm" value="${jfxm.jfdm }"/>
																			<input type="hidden" name="sqfs" id="sqfs_real_${zcxm.xmdm }_${jfxm.jfdm }" value="${jfxm.sqfs }"/>
																			<input type="hidden" name="sqly" id="sqly_${zcxm.xmdm }_${jfxm.jfdm }" value="${jfxm.sqly }"/>
																			<input type="hidden" name="hid_count" id="hid_count_${rs.xmdm }" value="${rs.jfxmNum }"/>
																		</td>
																		<td>
																			<input type="text" style="width: 50px"
																				disabled="disabled" value="�빴ѡ" 
																				onblur="checkJf('${zcxm.xmdm }','${jfxm.jfdm }');setSqfs('${zcxm.xmdm }','${jfxm.jfdm }')"
																				id="sqfs_${zcxm.xmdm }_${jfxm.jfdm }"
																				onkeydown="return onlyNum(this,2)"
																				onmousedown="return onlyNum(this,2)"
																				maxlength="2" 
																				style="width : 80px;ime-mode:disabled"/>
																		</td>
																		<td>
																			(���뷶Χ��${jfxm.xxf }-${jfxm.sxf }��)
																			<input type="hidden" id="xxf_${zcxm.xmdm }_${jfxm.jfdm }" value="${jfxm.xxf }"/>
																			<input type="hidden" id="sxf_${zcxm.xmdm }_${jfxm.jfdm }" value="${jfxm.sxf }"/>
																		</td>
																	</logic:empty>
																	<logic:notEmpty name="jfxm" property="sqfs">
																		<td>
																			<input type="checkbox" id="ch_${zcxm.xmdm }_${jfxm.jfdm }" checked="checked" onclick="clickJf('${zcxm.xmdm }','${jfxm.jfdm }')"/>
																			<span title="${jfxm.jfmc }">${jfxm.showmc }</span>
																			<input type="hidden" name="xmdm" value="${zcxm.xmdm }"/>
																			<input type="hidden" name="jfdm" value="${jfxm.jfdm }"/>
																			<input type="hidden" name="sqfs" id="sqfs_real_${zcxm.xmdm }_${jfxm.jfdm }" value="${jfxm.sqfs }"/>
																			<input type="hidden" name="sqly" id="sqly_${zcxm.xmdm }_${jfxm.jfdm }" value="${jfxm.sqly }"/>
																			<input type="hidden" name="hid_count" id="hid_count_${rs.xmdm }" value="${rs.jfxmNum }"/>
																		</td>
																		<td>
																			<input type="text" style="width: 50px"
																				value="${jfxm.sqfs }" 
																				onblur="checkJf('${zcxm.xmdm }','${jfxm.jfdm }');setSqfs('${zcxm.xmdm }','${jfxm.jfdm }')"
																				id="sqfs_${zcxm.xmdm }_${jfxm.jfdm }"
																				onkeydown="return onlyNum(this,2)"
																				onmousedown="return onlyNum(this,2)"
																				maxlength="2" 
																				style="width : 80px;ime-mode:disabled"/>
																		</td>
																		<td>
																			(���뷶Χ��${jfxm.xxf }-${jfxm.sxf }��)
																			<input type="hidden" id="xxf_${zcxm.xmdm }_${jfxm.jfdm }" value="${jfxm.xxf }"/>
																			<input type="hidden" id="sxf_${zcxm.xmdm }_${jfxm.jfdm }" value="${jfxm.sxf }"/>
																		</td>
																	</logic:notEmpty>
																	<%if(n%2==0){%>
																	</tr>
																	<%}%>
																</logic:iterate>
															</table>
														</td>
													</tr>
													<tr>
														<td>
															��������
															</br>
															<font color="red">(����¼��500��)</font>
														</td>
														<td>
															<textarea id="sqly_${zcxm.xmdm }" rows="5" 
																style="word-break:break-all;width:90%" 
																onblur="setSqly('${zcxm.xmdm }');chLeng(this,500)" type="_moz">${zcxm.sqly }</textarea>
														</td>
													</tr>
												</tbody>
											</table>
										</logic:iterate>
									</div>
								</td>
							</tr>
						</tbody>
					</logic:notEmpty>
					<!-- �۲�ӷ���Ϣ end-->
					
					<tfoot>
						<tr>
							<td colspan="4">
								<logic:notEmpty name="rs" property="xh">
									<logic:notEmpty name="rs" property="shzt1">
										<logic:equal name="rs" property="shzt1" value="δ���">
											<div class="btn">
												<button type="button" onclick="saveJfsq()" id="buttonSave" style="width: 80px">
													�� ��
												</button>									
											</div>
										</logic:equal>
										<logic:notEqual name="rs" property="shzt1" value="δ���">
											<div class="bz"><span class="red">ע�������ѱ����</span></div>
										</logic:notEqual>
									</logic:notEmpty>
									<logic:empty name="rs" property="shzt1">
										<div class="btn">
											<button type="button" onclick="saveJfsq()" id="buttonSave" style="width: 80px">
												�� ��
											</button>									
										</div>
									</logic:empty>
								</logic:notEmpty>
								<logic:empty name="rs" property="xh">
									<div class="bz"><span class="red">ע����ѡ����Ҫ����ӷֵ�ѧ��</span></div>
								</logic:empty>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>	
					
					
					<!-- �����ּܷ���ѡ��DIV -->
			<div id="div_zcjfsm" style="display:none">
				<div class="open_win01" style="overflow-x:hidden;overflow-y:hidden;height: 380px ">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�۲�ӷ�˵��</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="2">
								<div  style="overflow-x:hidden;overflow-y:auto;height:300px ">
								һ��Ʒ�±��ַּӷַ�����
								��һ��ע������־Ը�ߣ�����־Ը����ʱ�䣬�ɼ�0��10�֣�УԺ��ί���𣩡�<br/>
								�������������ʵ����ܵ�Ժ��Ժ���ϱ����߼�5�֡���У��ί����<br/>
								������������ѧ���ɲ���ͬѧ��������һ�꣬���ݹ����ɼ��͹���̬�ȣ������ڲ��Ű��涨�ھ���<br/>
								��Ѯ�����淽ʽ֪ͨ�����μӷ֣������ְ��ѧ���ɲ�ֻȡ���һ��ӷ֣���<br/>��
								1. У��Ժѧ���ᡢ�������ϻ���ϯ������ϯ��0��15�֣���ί���𣩡�<br/>
								2. У��Ժѧ���Ჿ����Ժ��ίίԱ��ϵ��רҵ����꼶�飩����֧����ǡ�У��ѧ�����Ÿ����˼�<br/>
								0��12�֣���ί���𣩡�<br/>
								3. ϵ��רҵ����꼶�飩����֧ίԱ������೤����֧����Ǽ�0��9�֣������θ��𣩡�<br/>
								4. �����ί����֧��ίԱ��У��Ժѧ�����Ա��Ժ��ѧ�����Ÿ����˼�0��6�֣������θ��𣩡�<br/>
								5. �δ����0��3�֣������θ��𣩡�<br/>
								���ģ��޳���Ѫ�ߣ���10�֡�<br/>

								����ѧҵ���ַּӷַ�����<br/>
								��һ��ѧϰ���ӷ֣�<br/>
								1��ͨ�������м�����ͳ��ͳ�ⰴ���±�׼�ӷ֣�<br/>
								��1��������רҵѧ����ר�ơ����ƶ��꼶ʱͨ��CET��4��2�֣�ͨ��CET��6��4�֡�<br/>
								��2���Ǽ������רҵѧ����ͨ��������򼶿��Լ�2�֣�ͨ��������󼶿��Լ�4�֡�<br/>
								2���μ���ѧ��Ӣ���ѧ�ƾ���������Ҽ�һ���������Ƚ����ֱ��5��4��3�֣����м�һ��������<br/>
								�Ƚ����ֱ��3��2��1�֣���У��һ���������Ƚ����ֱ��2��1��0.5�֡�<br/>
								���������и��ӷ֣�<br/>
								�μӿ���Ƽ�������о��ɹ����м����Ͻ����ߣ�������<bean:message key="lable.xb" />ѧ�������칫����׼����0.5��3�֣�<br/>
								��У������ʽ�����ѧ�����﷢�����ĵģ���0.5��3�֡�<br/>
								���ϸ�����ۼƼӷ֡�<br/>

								����������ָ��ӷֵļӷַ�����<br/>
								������ֵļӷ֣�������������С����20�֡�<br/>
								��һ�����μ��˶�����˶�Ա������Ա��ÿ�μ�1�֣���ǰ�������˶�Աÿ��ÿ���������7��6��<br/>
								5��4��3��2�֣��Ƽ�¼�����2�֡�<br/>
								������УԺ��֯�Ĵ��������ݳ���Ĳμ��ߣ�ÿ�μ�3�֡������վ����һ���������Ƚ��Ľ�<br/>
								Ŀ�μ��߷ֱ��5��4��3�֣��μ��߷ֱ��2��1�֡��μӸ�У�����ݳ���һ���������Ƚ��ߣ�����<br/>
								�影��ÿλ��Ա���ֱ��10��9��8�֣��μ��߼�6�֡�<br/>
								</td>
								</div>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									
									<div class="btn">
										<button type="button" name="ȡ ��" onclick="closeWindown();return false;">
											ȷ ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>