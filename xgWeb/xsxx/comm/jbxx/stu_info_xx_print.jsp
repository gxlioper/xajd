<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/studetailFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/dwjlgl.js'></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type="text/javascript">
	
	</script>
	
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
		.noPrin{
			display:none;
		}
	</style>
</head>
	<body onload="showMk()">		
		<input type="hidden" id="xh" name="xh" value="${xh}" />
		<input type="hidden" id="zzxh" name="zzxh" value="${xh}" />
		<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}" />
		<input type="hidden" name="jgmc" id="jgmc" value="${rs.jgmc}"/>
		<input type="hidden" name="sydmc" id="sydmc" value="${rs.sydmc}"/>
		<input type="hidden" name="hkszdmc" id="hkszdmc" value="${rs.hkszdmc}"/>
		<logic:iterate id="mk" name="printMk">
			<input type="hidden" name="hidden_mk" value="${mk }"/>
		</logic:iterate>
		
		<div class="tab">
		<table width="100%" id="tabdd">
					<tbody>
						<tr>
						<td>
							<logic:iterate name="xsqList" id="xsq">
									<logic:equal name="xsq" property="sfzd" value="��">
										<table class="formlist" border="0" align="center"
											style="width: 98%" id="xsq${xsq.xsqdm }">
											<thead>
												<tr>
													<th colspan="4">
														<span>${xsq.xsqmc }</span>

													</th>
												</tr>
											</thead>
											<tbody>
												<tr id="xsq${xsq.xsqdm }_1_tr">
													<td colspan="4">
														<div id="xsq${xsq.xsqdm }_1_div">
															<table width="100%">
																<!-- ��Ƭ��ʾ���ұ�-->
																<logic:equal name="xsq" property="zpxs" value="��">
																	<logic:equal name="xsq" property="zpwz" value="��">
																		<tr>
																			<logic:iterate name="xsq" property="xshxx" id="xsh">
																			<logic:equal name="xsh" property="leftMap.dygl" value="1">
																				<logic:equal name="xsh" property="leftMap.dygh" value="1">
																				<th width="16%">
																					${xsh.leftMap.zdm }
																				</th>
																				<td width="34%" id="td_${xsh.leftMap.zd}">
																					<bean:write name="rs" property="${xsh.leftMap.zd}" />
																				</td>
																				</logic:equal>
																			</logic:equal>
																			</logic:iterate>
																			<!-- �� -->
																			<th width="16%" rowspan="${xsq.zpszhs }">
																				ѧ
																				</br>
																				��
																				</br>
																				��
																				</br>
																				Ƭ
																			</th>
																			<td width="34%" rowspan="${xsq.zpszhs }">
																				<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}" width="96" height="128"/>
																			</td>
																		</tr>
																		<logic:iterate name="xsq" property="xshxx" id="xsh" offset="1" length="${xsq.zpszhs-1}">
																			<logic:empty name="xsh" property="leftMap">
																			<tr>
																					<th width="16%">
																						&nbsp;
																					</th>
																					<td width="34%" >
																						&nbsp;
																					</td>
																			</tr>
																			</logic:empty>
																			<logic:notEmpty name="xsh" property="leftMap">
																			<tr>
																				<th width="16%" >
																					${xsh.leftMap.zdm }
																				</th>
																				<td width="34%" id="td_${xsh.leftMap.zd}">
																					<bean:write name="rs" property="${xsh.leftMap.zd}" />
																				</td>
																			</tr>
																			</logic:notEmpty>
																		</logic:iterate>
																	</logic:equal>
																	<!-- ��Ƭ��ʾ�����-->
																	<logic:equal name="xsq" property="zpwz" value="��">
																		<tr>
																			<!-- �� -->
																			<th width="16%" rowspan="${xsq.zpszhs }">
																				ѧ
																				</br>
																				��
																				</br>
																				��
																				</br>
																				Ƭ
																			</th>
																			<td width="34%" rowspan="${xsq.zpszhs }">
																				<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}" width="96" height="128"/>
																			</td>
																			<!-- �� -->
																			<logic:iterate name="xsq" property="xshxx" id="xsh" length="1">
																				<logic:notEmpty name="xsh" property="rightMap">
																					<logic:equal name="xsh" property="rightMap.dygl" value="3">
																						<logic:equal name="xsh" property="rightMap.dygh" value="1">
																						<th width="16%" >
																								${xsh.rightMap.zdm }
																						</th>
																						<td width="34%" id="td_${xsh.rightMap.zd}">
																							<bean:write name="rs" property="${xsh.rightMap.zd}" />
																						</td>
																						</logic:equal>
																					</logic:equal>
																				</logic:notEmpty>
																				<logic:empty name="xsh" property="rightMap">
																					<th width="16%" >
																						&nbsp;
																					</th>
																					<td width="34%" >
																						&nbsp;
																					</td>
																				</logic:empty>
																			</logic:iterate>
																		</tr>
																		<logic:iterate name="xsq" property="xshxx" id="xsh" offset="1" length="${xsq.zpszhs-1}">
																			<logic:empty name="xsh" property="rightMap">
																			<tr>
																					<th width="16%">
																						&nbsp;
																					</th>
																					<td width="34%" >
																						&nbsp;
																					</td>
																			</tr>
																			</logic:empty>
																			<logic:notEmpty name="xsh" property="rightMap">
																			<tr>
																				<th width="16%" >
																					${xsh.rightMap.zdm}
																				</th>
																				<td width="34%" id="td_${xsh.rightMap.zd}">
																					<bean:write name="rs" property="${xsh.rightMap.zd}" />
																				</td>
																			</tr>
																			</logic:notEmpty>
																		</logic:iterate>
																	</logic:equal>
																	
																</logic:equal>
																<logic:iterate name="xsq" property="xshxx" id="xsh"
																	indexId="index">
																	<logic:empty name="xsh">
																		<tr>
																			<th width="16%">
																				&nbsp;
																			</th>
																			<td width="34%">
																				&nbsp;
																			</td>
																			<th width="16%">
																				&nbsp;
																			</th>
																			<td width="34%">
																				&nbsp;
																			</td>
																		</tr>
																	</logic:empty>
																	<logic:notEmpty name="xsh">
																		<logic:equal name="xsh" property="sfhb" value="��">
																			<tr>
																				<logic:empty name="xsh" property="leftMap">
																					<th width="16%">
																						&nbsp;
																					</th>
																					<td colspan="3" width="84%">
																						&nbsp;
																					</td>
																				</logic:empty>
																				<logic:notEmpty name="xsh" property="leftMap">
																					<th width="16%">
																						${xsh.leftMap.zdm }
																					</th>
																					<td colspan="3" width="84%"  id="td_${xsh.leftMap.zd}">
																						<bean:write name="rs" property="${xsh.leftMap.zd}" />
																					</td>
																				</logic:notEmpty>
																			</tr>
																		</logic:equal>
																		<logic:equal name="xsh" property="sfhb" value="��">
																			<tr>
																				<logic:empty name="xsh" property="leftMap">
																					<th width="16%">
																						&nbsp;
																					</th>
																					<td width="34%">
																						&nbsp;
																					</td>
																				</logic:empty>
																				<logic:notEmpty name="xsh" property="leftMap">
																					<th width="16%">
																						${xsh.leftMap.zdm }
																					</th>
																					<td width="34%" id="td_${xsh.leftMap.zd}">
																						<bean:write name="rs" property="${xsh.leftMap.zd}" />
																					</td>
																				</logic:notEmpty>
																				<logic:empty name="xsh" property="rightMap">
																					<th width="16%">
																						&nbsp;
																					</th>
																					<td width="34%">
																						&nbsp;
																					</td>
																				</logic:empty>
																				
																				<logic:notEmpty name="xsh" property="rightMap">
																					<th width="16%">
																						${xsh.rightMap.zdm }
																					</th>
																					<td width="34%" id="td_${xsh.rightMap.zd}">
																						<bean:write name="rs" property="${xsh.rightMap.zd}" />
																					</td>
																				</logic:notEmpty>
																			</tr>
																		</logic:equal>
																	</logic:notEmpty>
																</logic:iterate>
															</table>
														</div>
													</td>
												</tr>

											</tbody>
										</table>
									</logic:equal>
								</logic:iterate>
							</td>
						</tr>
					</tbody>
				</table>
					
		<table class="formlist" border="0" align="center" style="width: 98%">
			<tr>
				<td>
					<%@ include file="/xsxx/comm/qtxx/qtxxPrint.jsp"%>
				</td>
			</tr>
		</table>	
		
		
		<table width="100%" class="formlist">
			
			<tfoot>
		      <tr>
		        <td colspan="4">
		          <div class="btn noPrin">
		            <input type='button' value='ҳ������' onclick="try{WebBrowser.ExecWB(8,1)}catch(e){alert('�����ð�ȫ��������ActiveX�ؼ��Ͳ����')}">
					<input type='button' value='��ӡԤ��' onclick="try{WebBrowser.ExecWB(7,1)}catch(e){alert('�����ð�ȫ��������ActiveX�ؼ��Ͳ����')}">
					<input type='button' value='ֱ�Ӵ�ӡ' onclick="try{WebBrowser.ExecWB(6,6)}catch(e){alert('�����ð�ȫ��������ActiveX�ؼ��Ͳ����')}">
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
		</div>
	</body>
</html>
