<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsxx/comm/jbsz/ymsz.js"></script>
		<script language="javascript">

		</script>
	</head>
	<body onload="setQyzdValue()">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/xsxxJbsz">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ѡ���� -->
			<input type="hidden" name="checked_tr" id="checked_tr" value=""/>
			<!-- ����� -->
			<input type="hidden" name="last_tr" id="last_tr" value="no"/>
			<div class="tab">		
				<!-- ҳ�������� -->
				<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr>
							<th colspan="2">
								<span>ҳ������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr style="">
							<th align="right" width="30%">
								�������ֶ��б�
							</th>
							<td align="left" width="">
								<button type="button" onclick="saveYmsz();" id="buttonSave">
									�� ��
								</button>
									
								<button type="button" onclick="showZjDiv()" id="buttonSave">
									�� ��
								</button>
									
								<button type="button" onclick="delXsq();" id="buttonClose">
									ɾ ��
								</button>
									
								<button type="button" onclick="showSzDiv();" id="buttonClose">
									�� ��
								</button>
							</td>
						</tr>
						<tr style="height: 540px;">
							<td >
								<html:select property="qyzd" styleId="qyzd" style="width:100%;" size="35" onmouseover="null">
									<html:options collection="qyzdList" property="search_zd" labelProperty="search_ymxs" />
								</html:select>
							</td>
							<td>
								<div id="xsqNr" style="height: 500px;overflow:scroll;overflow-x:hidden">
									<!-- �����úõ���ʾ�� -->
									<logic:notEmpty name="xsqList">
										<logic:iterate name="xsqList" id="xsq">
											<!-- ��ʾ�� -->
											<div id="xsq${xsq.xsqdm }_div">
												<table class="formlist" border="0" align="center" style="width: 90%" id="xsq${xsq.xsqdm }">
													<thead>
														<tr>
															<th colspan="4">
																<span><a href="#" onclick="hiddenXsq('${xsq.xsqdm }')">${xsq.xsqmc }</a></span>
																<!-- ��ʾ������ -->
																<input type="hidden" name="xsqdm" value="${xsq.xsqdm }"/>
																<!-- ��ʾ������ -->
																<input type="hidden" name="xsqmc" value="${xsq.xsqmc }"/>
																<!-- ��ռ���� -->
																<input type="hidden" name="szhs" id="szhs${xsq.xsqdm }" value="${xsq.szhs }"/>
																<!-- ��Ƭ��ʾ -->
																<input type="hidden" name="zpxs" value="${xsq.zpxs }"/>
																<!-- ��Ƭλ�� -->
																<input type="hidden" name="zpwz" value="${xsq.zpwz }"/>
																<!-- ��Ƭ��ռ�� -->
																<input type="hidden" name="zpszhs" value="${xsq.zpszhs }"/>
																<!-- ��ʾ˳�� -->
																<input type="hidden" name="xssx" value="${xsq.xssx }"/>
																<input type="checkbox" id="xsq${xsq.xsqdm }_checkbox" name="xsq_checkbox" value="${xsq.xsqdm }"/>ѡ����ʾ��
															</th>
														</tr>
													</thead>
													<tbody id="xsq${xsq.xsqdm }_tb" style="">												
														<!-- ��Ҫ��Ƭ��ʾ -->
														<logic:equal name="xsq" property="zpxs" value="��">
															<tr id="xsq${xsq.xsqdm }_1_tr">
																<td colspan="4">
																	<div id="xsq${xsq.xsqdm }_1_div">
																		<table width="100%">
																			<!-- ��Ƭ��ʾ���ұ�-->
																			<logic:equal name="xsq" property="zpwz" value="��">
																				<tr>
																					<!-- �� -->
																					<logic:iterate name="xsq" property="rowList" id="row" length="1">
																						<!-- �������ֶ� -->
																						<logic:equal name="row" property="zdMap.left" value="yes">
																							<td width="20%">
																								<p id="xsq${xsq.xsqdm }_1_left_p">${row.zdMap.leftzdm }</p>
																								<input type="hidden" name="yyzd${xsq.xsqdm }" id="xsq${xsq.xsqdm }_1_left_text" value="${row.zdMap.leftzd }"/>
																							</td>
																							<td width="30%">
																								<button type="button" onclick="clickXsqBtn(this)" id="xsq${xsq.xsqdm }_1_left">ȡ��</button>
																							</td>
																						</logic:equal>
																						<!-- δ�����ֶ� -->
																						<logic:notEqual name="row" property="zdMap.left" value="yes">
																							<td width="20%">
																								<p id="xsq${xsq.xsqdm }_1_left_p"></p>
																								<input type="hidden" name="yyzd${xsq.xsqdm }" id="xsq${xsq.xsqdm }_1_left_text" value=""/>
																							</td>
																							<td width="30%">
																								<button type="button" onclick="clickXsqBtn(this)" id="xsq${xsq.xsqdm }_1_left">Ӧ��</button>
																							</td>
																						</logic:notEqual>
																					</logic:iterate>
																					<!-- �� end-->
																					
																					<!-- �� -->
																					<td width="20%" rowspan="${xsq.zpszhs }">
																						ѧ</br>��</br>��</br>Ƭ
																					</td>
																					<td width="30%" rowspan="${xsq.zpszhs }">
																						<img src="/xgxt/images/type_pic.gif"/>
																					</td>	
																					<!-- �� end-->
																				</tr>
																				<!-- ��Ƭ���������� -->
																				<logic:iterate name="xsq" property="rowList" id="row" indexId="rowNum">
																					<!-- ������ -->
																					<logic:notEqual name="row" property="isFirst" value="yes">
																						<!-- ��Ƭ������ -->
																						<logic:equal name="row" property="isZp" value="yes">
																							<tr>
																								<!-- �������ֶ� -->
																								<logic:equal name="row" property="zdMap.left" value="yes">
																									<td width="20%">
																										<p id="xsq${xsq.xsqdm }_${row.szh }_left_p">${row.zdMap.leftzdm }</p>
																										<input type="hidden" name="yyzd${xsq.xsqdm }" id="xsq${xsq.xsqdm }_${row.szh }_left_text" value="${row.zdMap.leftzd }"/>
																									</td>
																									<td width="30%">
																										<button type="button" onclick="clickXsqBtn(this)" id="xsq${xsq.xsqdm }_${row.szh }_left">ȡ��</button>
																									</td>
																								</logic:equal>
																								<!-- δ�����ֶ� -->
																								<logic:notEqual name="row" property="zdMap.left" value="yes">
																									<td width="20%">
																										<p id="xsq${xsq.xsqdm }_${row.szh }_left_p"></p>
																										<input type="hidden" name="yyzd${xsq.xsqdm }" id="xsq${xsq.xsqdm }_${row.szh }_left_text" value=""/>
																									</td>
																									<td width="30%">
																										<button type="button" onclick="clickXsqBtn(this)" id="xsq${xsq.xsqdm }_${row.szh }_left">Ӧ��</button>
																									</td>
																								</logic:notEqual>
																							</tr>
																						</logic:equal>
																						<!-- ��Ƭ������ end-->
																					</logic:notEqual>
																					<!-- ������ end-->
																				</logic:iterate>
																				<!-- ��Ƭ���������� end-->
																			</logic:equal>
																			<!-- ��Ƭ��ʾ���ұ� end-->
																			
																			<!-- ��Ƭ��ʾ�����-->
																			<logic:equal name="xsq" property="zpwz" value="��">
																				<tr>
																					<!-- �� -->
																					<td width="20%" rowspan="${xsq.zpszhs }">
																						ѧ</br>��</br>��</br>Ƭ
																					</td>
																					<td width="30%" rowspan="${xsq.zpszhs }">
																						<img src="/xgxt/images/type_pic.gif"/>
																					</td>	
																					<!-- �� end-->
																					
																					<!-- �� -->
																					<logic:iterate name="xsq" property="rowList" id="row" length="1">
																						<!-- �������ֶ� -->
																						<logic:equal name="row" property="zdMap.right" value="yes">
																							<td width="20%">
																								<p id="xsq${xsq.xsqdm }_1_right_p">${row.zdMap.rightzdm }</p>
																								<input type="hidden" name="yyzd${xsq.xsqdm }" id="xsq${xsq.xsqdm }_1_right_text" value="${row.zdMap.rightzd }"/>
																							</td>
																							<td width="30%">
																								<button type="button" onclick="clickXsqBtn(this)" id="xsq${xsq.xsqdm }_1_right">ȡ��</button>
																							</td>
																						</logic:equal>
																						<!-- δ�����ֶ� -->
																						<logic:notEqual name="row" property="zdMap.right" value="yes">
																							<td width="20%">
																								<p id="xsq${xsq.xsqdm }_1_right_p"></p>
																								<input type="hidden" name="yyzd${xsq.xsqdm }" id="xsq${xsq.xsqdm }_1_right_text" value=""/>
																							</td>
																							<td width="30%">
																								<button type="button" onclick="clickXsqBtn(this)" id="xsq${xsq.xsqdm }_1_right">Ӧ��</button>
																							</td>
																						</logic:notEqual>
																					</logic:iterate>
																					<!-- �� end-->
																				</tr>
																				<!-- ��Ƭ���������� -->
																				<logic:iterate name="xsq" property="rowList" id="row" indexId="rowNum">
																					<!-- ������ -->
																					<logic:notEqual name="row" property="isFirst" value="yes">
																						<!-- ��Ƭ������ -->
																						<logic:equal name="row" property="isZp" value="yes">
																							<tr>
																								<!-- �������ֶ� -->
																								<logic:equal name="row" property="zdMap.right" value="yes">
																									<td width="20%">
																										<p id="xsq${xsq.xsqdm }_${row.szh }_right_p">${row.zdMap.rightzdm }</p>
																										<input type="hidden" name="yyzd${xsq.xsqdm }" id="xsq${xsq.xsqdm }_${row.szh }_right_text" value="${row.zdMap.rightzd }"/>
																									</td>
																									<td width="30%">
																										<button type="button" onclick="clickXsqBtn(this)" id="xsq${xsq.xsqdm }_${row.szh }_right">ȡ��</button>
																									</td>
																								</logic:equal>
																								<!-- δ�����ֶ� -->
																								<logic:notEqual name="row" property="zdMap.right" value="yes">
																									<td width="20%">
																										<p id="xsq${xsq.xsqdm }_${row.szh }_right_p"></p>
																										<input type="hidden" name="yyzd${xsq.xsqdm }" id="xsq${xsq.xsqdm }_${row.szh }_right_text" value=""/>
																									</td>
																									<td width="30%">
																										<button type="button" onclick="clickXsqBtn(this)" id="xsq${xsq.xsqdm }_${row.szh }_right">Ӧ��</button>
																									</td>
																								</logic:notEqual>
																							</tr>
																						</logic:equal>
																						<!-- ��Ƭ������ end-->
																					</logic:notEqual>
																					<!-- ������ end-->
																				</logic:iterate>
																				<!-- ��Ƭ���������� end-->
																			</logic:equal>
																			<!-- ��Ƭ��ʾ����� end-->
																		</table>
																	</div>
																</td>
															</tr>
														</logic:equal>
														<!-- ��Ҫ��Ƭ��ʾ end-->
														
														<!-- ��ռ�� -->
														<logic:iterate name="xsq" property="rowList" id="row" indexId="rowNum">
															<!-- ������ -->
															<logic:notEqual name="row" property="isFirst" value="yes">
																<!-- ����Ƭ������ -->
																<logic:equal name="row" property="isZp" value="no">
																	<tr onclick="rowOnClick(this);" id="xsq${xsq.xsqdm }_${row.szh }_tr">
																		<td colspan="4">
																			<div id="xsq${xsq.xsqdm }_${row.szh }_div">
																				<table width="100%">
																					<tr>
																						<!-- ����Ҫ�ϲ���Ԫ�� -->
																						<logic:equal name="row" property="isHb" value="no">
																							<!-- �� -->
																							<!-- �������ֶ� -->
																							<logic:equal name="row" property="zdMap.left" value="yes">
																								<td width="20%">
																									<p id="xsq${xsq.xsqdm }_${row.szh }_left_p">${row.zdMap.leftzdm }</p>
																									<input type="hidden" name="yyzd${xsq.xsqdm }" id="xsq${xsq.xsqdm }_${row.szh }_left_text" value="${row.zdMap.leftzd }"/>
																								</td>
																								<td width="30%">
																									<button type="button" onclick="clickXsqBtn(this)" id="xsq${xsq.xsqdm }_${row.szh }_left">ȡ��</button>
																								</td>
																							</logic:equal>
																							<!-- δ�����ֶ� -->
																							<logic:notEqual name="row" property="zdMap.left" value="yes">
																								<td width="20%">
																									<p id="xsq${xsq.xsqdm }_${row.szh }_left_p"></p>
																									<input type="hidden" name="yyzd${xsq.xsqdm }" id="xsq${xsq.xsqdm }_${row.szh }_left_text"/>
																								</td>
																								<td width="30%">
																									<button type="button" onclick="clickXsqBtn(this)" id="xsq${xsq.xsqdm }_${row.szh }_left">Ӧ��</button>
																								</td>
																							</logic:notEqual>
																							<!-- �� end-->
																							
																							<!-- �� -->
																							<!-- �������ֶ� -->
																							<logic:equal name="row" property="zdMap.right" value="yes">
																								<td width="20%">
																									<p id="xsq${xsq.xsqdm }_${row.szh }_right_p">${row.zdMap.rightzdm }</p>
																									<input type="hidden" name="yyzd${xsq.xsqdm }" id="xsq${xsq.xsqdm }_${row.szh }_right_text" value="${row.zdMap.rightzd }"/>
																								</td>
																								<td width="30%">
																									<button type="button" onclick="clickXsqBtn(this)" id="xsq${xsq.xsqdm }_${row.szh }_right">ȡ��</button>
																								</td>
																							</logic:equal>
																							<!-- δ�����ֶ� -->
																							<logic:notEqual name="row" property="zdMap.right" value="yes">
																								<td width="20%">
																									<p id="xsq${xsq.xsqdm }_${row.szh }_right_p"></p>
																									<input type="hidden" name="yyzd${xsq.xsqdm }" id="xsq${xsq.xsqdm }_${row.szh }_right_text"/>
																								</td>
																								<td width="30%">
																									<button type="button" onclick="clickXsqBtn(this)" id="xsq${xsq.xsqdm }_${row.szh }_right">Ӧ��</button>
																								</td>
																							</logic:notEqual>
																							<!-- �� end-->																						
																						</logic:equal>
																						<!-- ����Ҫ�ϲ���Ԫ�� end-->
																						
																						<!-- ��Ҫ�ϲ���Ԫ�� -->
																						<logic:equal name="row" property="isHb" value="yes">
																							<!-- �� -->
																							<!-- �������ֶ� -->
																							<logic:equal name="row" property="zdMap.left" value="yes">
																								<td width="20%">
																									<p id="xsq${xsq.xsqdm }_${row.szh }_left_p">${row.zdMap.leftzdm }</p>
																									<input type="hidden" name="yyzd${xsq.xsqdm }" id="xsq${xsq.xsqdm }_${row.szh }_left_text" value="${row.zdMap.leftzd }"/>
																									<input type="hidden" name="hbh_szxsq" value="${xsq.xsqdm }"/>
																									<input type="hidden" name="hbh" value="${row.szh }"/>
																								</td>
																								<td colspan="3">
																									<button type="button" onclick="clickXsqBtn(this)" id="xsq${xsq.xsqdm }_${row.szh }_left">ȡ��</button>
																								</td>
																							</logic:equal>
																							<!-- δ�����ֶ� -->
																							<logic:notEqual name="row" property="zdMap.left" value="yes">
																								<td width="20%">
																									<p id="xsq${xsq.xsqdm }_${row.szh }_left_p"></p>
																									<input type="hidden" name="yyzd${xsq.xsqdm }" id="xsq${xsq.xsqdm }_${row.szh }_left_text"/>
																									<input type="hidden" name="hbh_szxsq" value="${xsq.xsqdm }"/>
																									<input type="hidden" name="hbh" value="${row.szh }"/>
																								</td>
																								<td colspan="3">
																									<button type="button" onclick="clickXsqBtn(this)" id="xsq${xsq.xsqdm }_${row.szh }_left">Ӧ��</button>
																								</td>
																							</logic:notEqual>
																							<!-- �� end-->
																						</logic:equal>
																						<!-- ��Ҫ�ϲ���Ԫ�� end-->
																					</tr>
																				</table>
																			</div>
																		</td>
																	</tr>
																</logic:equal>
																<!-- ����Ƭ������ end-->															
															</logic:notEqual>
															<!-- ������ end-->
														</logic:iterate>
														<!-- ��ռ�� end-->
													</tbody>
												</table>
											</div>
											<!-- ��ʾ�� end -->
										</logic:iterate>
									</logic:notEmpty>
								</div>
								<input type="hidden" name="xsqNum" id="xsqNum" value="${maxXsqdm }"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- ��ʾ������Div -->
			<div id="zjDiv" style="display: none">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>��������</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="35%"><font color="red">*</font>��ʾ������</th>
								<td>
									<input type="text" name="sz_xsqmc" id="sz_xsqmc_value" maxlength="10" style="width : 50%;" value=""/>
								</td>
							</tr>
							<tr>
								<th><font color="red">*</font>��ռ����(��)</th>
								<td>
									<input type="text" name="sz_szhs" id="sz_szhs_value"
										onkeydown="return onlyNum(this,2)"
										onmousedown="return onlyNum(this,2)"
										maxlength="2" value="6"
										style="width : 50%;ime-mode:disabled"/>
								</td>
							</tr>
							<tr>
								<th>��Ҫ��Ƭ</th>
								<td>
									<input type="radio" name="sz_zpxs" value="��" onclick="checkedZpxs(this.value);setDivHiddenValue('zpxs',this.value)" checked="checked"/>��
									<input type="radio" name="sz_zpxs" value="��" onclick="checkedZpxs(this.value);setDivHiddenValue('zpxs',this.value)"/>��
									<input type="hidden" id="sz_zpxs_value" value="��"/>
								</td>
							</tr>
							<tr id="zpwz_tr" style="display: none">
								<th>��Ƭλ��</th>
								<td>
									<input type="radio" name="sz_zpwz" value="��" onclick="setDivHiddenValue('zpwz',this.value)" checked="checked"/>��
									<input type="radio" name="sz_zpwz" value="��" onclick="setDivHiddenValue('zpwz',this.value)"/>��
									<input type="hidden" id="sz_zpwz_value" value="��"/>
								</td>
							</tr>
							<tr id="zpszhs_tr" style="display: none">
								<th><font color="red">*</font>��Ƭ��ռ��(��)</th>
								<td>
									<input type="text" name="sz_zpszhs" id="sz_zpszhs_value"
										onkeydown="return onlyNum(this,2)"
										onmousedown="return onlyNum(this,2)"
										maxlength="2" value="6"
										style="width : 50%;ime-mode:disabled"/>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan='2'>
									<div class="bz">
										"<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button type="button" onclick="setXsqBySz()" id="buttonSave">
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
			</div>
			<!-- ��ʾ������Div end-->
			
			<!-- ���в���Div -->
			<div id="czDiv" style="display: none">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<th>
									<span>��ѡ����Ҫִ�еĲ���</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td align="center">
									<button type="button" onclick="hbXsqTr()" style="width:100px">
										�ϲ���Ԫ��
									</button>
								</td>
							</tr>
							<tr>
								<td align="center">
									<button type="button" onclick="qxXsqTr()" style="width:100px">
										ȡ���ϲ�
									</button>
								</td>
							</tr>
							<tr id="czDiv_zjh" style="display: none">
								<td align="center">
									<button type="button" onclick="addXsqTr();" style="width:100px">
										������
									</button>
								</td>
							</tr>
							<tr id="czDiv_sch" style="display: none">
								<td align="center">
									<button type="button" onclick="delXsqTr();" style="width:100px">
										ɾ������
									</button>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td>
									<div class="bz">
										ע�������ɹ�������ѷ����ֶΡ�
									</div>
									<div class="btn">
										<button type="button" onclick="hiddenMessage(true,true);return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- ���в���Div end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>