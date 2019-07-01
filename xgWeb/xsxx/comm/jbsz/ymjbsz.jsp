<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsxx/comm/jbsz/ymsz.js"></script>
		<script language="javascript">

		</script>
	</head>
	<body onload="setQyzdValue()">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/xsxxJbsz">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 选中行 -->
			<input type="hidden" name="checked_tr" id="checked_tr" value=""/>
			<!-- 最后行 -->
			<input type="hidden" name="last_tr" id="last_tr" value="no"/>
			<div class="tab">		
				<!-- 页面基本情况 -->
				<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr>
							<th colspan="2">
								<span>页面设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr style="">
							<th align="right" width="30%">
								待分配字段列表
							</th>
							<td align="left" width="">
								<button type="button" onclick="saveYmsz();" id="buttonSave">
									保 存
								</button>
									
								<button type="button" onclick="showZjDiv()" id="buttonSave">
									增 加
								</button>
									
								<button type="button" onclick="delXsq();" id="buttonClose">
									删 除
								</button>
									
								<button type="button" onclick="showSzDiv();" id="buttonClose">
									操 作
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
									<!-- 已配置好的显示区 -->
									<logic:notEmpty name="xsqList">
										<logic:iterate name="xsqList" id="xsq">
											<!-- 显示区 -->
											<div id="xsq${xsq.xsqdm }_div">
												<table class="formlist" border="0" align="center" style="width: 90%" id="xsq${xsq.xsqdm }">
													<thead>
														<tr>
															<th colspan="4">
																<span><a href="#" onclick="hiddenXsq('${xsq.xsqdm }')">${xsq.xsqmc }</a></span>
																<!-- 显示区代码 -->
																<input type="hidden" name="xsqdm" value="${xsq.xsqdm }"/>
																<!-- 显示区名称 -->
																<input type="hidden" name="xsqmc" value="${xsq.xsqmc }"/>
																<!-- 所占行数 -->
																<input type="hidden" name="szhs" id="szhs${xsq.xsqdm }" value="${xsq.szhs }"/>
																<!-- 照片显示 -->
																<input type="hidden" name="zpxs" value="${xsq.zpxs }"/>
																<!-- 照片位置 -->
																<input type="hidden" name="zpwz" value="${xsq.zpwz }"/>
																<!-- 照片所占行 -->
																<input type="hidden" name="zpszhs" value="${xsq.zpszhs }"/>
																<!-- 显示顺序 -->
																<input type="hidden" name="xssx" value="${xsq.xssx }"/>
																<input type="checkbox" id="xsq${xsq.xsqdm }_checkbox" name="xsq_checkbox" value="${xsq.xsqdm }"/>选择本显示区
															</th>
														</tr>
													</thead>
													<tbody id="xsq${xsq.xsqdm }_tb" style="">												
														<!-- 需要照片显示 -->
														<logic:equal name="xsq" property="zpxs" value="是">
															<tr id="xsq${xsq.xsqdm }_1_tr">
																<td colspan="4">
																	<div id="xsq${xsq.xsqdm }_1_div">
																		<table width="100%">
																			<!-- 照片显示在右边-->
																			<logic:equal name="xsq" property="zpwz" value="右">
																				<tr>
																					<!-- 左 -->
																					<logic:iterate name="xsq" property="rowList" id="row" length="1">
																						<!-- 已配置字段 -->
																						<logic:equal name="row" property="zdMap.left" value="yes">
																							<td width="20%">
																								<p id="xsq${xsq.xsqdm }_1_left_p">${row.zdMap.leftzdm }</p>
																								<input type="hidden" name="yyzd${xsq.xsqdm }" id="xsq${xsq.xsqdm }_1_left_text" value="${row.zdMap.leftzd }"/>
																							</td>
																							<td width="30%">
																								<button type="button" onclick="clickXsqBtn(this)" id="xsq${xsq.xsqdm }_1_left">取消</button>
																							</td>
																						</logic:equal>
																						<!-- 未配置字段 -->
																						<logic:notEqual name="row" property="zdMap.left" value="yes">
																							<td width="20%">
																								<p id="xsq${xsq.xsqdm }_1_left_p"></p>
																								<input type="hidden" name="yyzd${xsq.xsqdm }" id="xsq${xsq.xsqdm }_1_left_text" value=""/>
																							</td>
																							<td width="30%">
																								<button type="button" onclick="clickXsqBtn(this)" id="xsq${xsq.xsqdm }_1_left">应用</button>
																							</td>
																						</logic:notEqual>
																					</logic:iterate>
																					<!-- 左 end-->
																					
																					<!-- 右 -->
																					<td width="20%" rowspan="${xsq.zpszhs }">
																						学</br>生</br>照</br>片
																					</td>
																					<td width="30%" rowspan="${xsq.zpszhs }">
																						<img src="/xgxt/images/type_pic.gif"/>
																					</td>	
																					<!-- 右 end-->
																				</tr>
																				<!-- 照片所在其他行 -->
																				<logic:iterate name="xsq" property="rowList" id="row" indexId="rowNum">
																					<!-- 非首行 -->
																					<logic:notEqual name="row" property="isFirst" value="yes">
																						<!-- 照片所在行 -->
																						<logic:equal name="row" property="isZp" value="yes">
																							<tr>
																								<!-- 已配置字段 -->
																								<logic:equal name="row" property="zdMap.left" value="yes">
																									<td width="20%">
																										<p id="xsq${xsq.xsqdm }_${row.szh }_left_p">${row.zdMap.leftzdm }</p>
																										<input type="hidden" name="yyzd${xsq.xsqdm }" id="xsq${xsq.xsqdm }_${row.szh }_left_text" value="${row.zdMap.leftzd }"/>
																									</td>
																									<td width="30%">
																										<button type="button" onclick="clickXsqBtn(this)" id="xsq${xsq.xsqdm }_${row.szh }_left">取消</button>
																									</td>
																								</logic:equal>
																								<!-- 未配置字段 -->
																								<logic:notEqual name="row" property="zdMap.left" value="yes">
																									<td width="20%">
																										<p id="xsq${xsq.xsqdm }_${row.szh }_left_p"></p>
																										<input type="hidden" name="yyzd${xsq.xsqdm }" id="xsq${xsq.xsqdm }_${row.szh }_left_text" value=""/>
																									</td>
																									<td width="30%">
																										<button type="button" onclick="clickXsqBtn(this)" id="xsq${xsq.xsqdm }_${row.szh }_left">应用</button>
																									</td>
																								</logic:notEqual>
																							</tr>
																						</logic:equal>
																						<!-- 照片所在行 end-->
																					</logic:notEqual>
																					<!-- 非首行 end-->
																				</logic:iterate>
																				<!-- 照片所在其他行 end-->
																			</logic:equal>
																			<!-- 照片显示在右边 end-->
																			
																			<!-- 照片显示在左边-->
																			<logic:equal name="xsq" property="zpwz" value="左">
																				<tr>
																					<!-- 左 -->
																					<td width="20%" rowspan="${xsq.zpszhs }">
																						学</br>生</br>照</br>片
																					</td>
																					<td width="30%" rowspan="${xsq.zpszhs }">
																						<img src="/xgxt/images/type_pic.gif"/>
																					</td>	
																					<!-- 左 end-->
																					
																					<!-- 右 -->
																					<logic:iterate name="xsq" property="rowList" id="row" length="1">
																						<!-- 已配置字段 -->
																						<logic:equal name="row" property="zdMap.right" value="yes">
																							<td width="20%">
																								<p id="xsq${xsq.xsqdm }_1_right_p">${row.zdMap.rightzdm }</p>
																								<input type="hidden" name="yyzd${xsq.xsqdm }" id="xsq${xsq.xsqdm }_1_right_text" value="${row.zdMap.rightzd }"/>
																							</td>
																							<td width="30%">
																								<button type="button" onclick="clickXsqBtn(this)" id="xsq${xsq.xsqdm }_1_right">取消</button>
																							</td>
																						</logic:equal>
																						<!-- 未配置字段 -->
																						<logic:notEqual name="row" property="zdMap.right" value="yes">
																							<td width="20%">
																								<p id="xsq${xsq.xsqdm }_1_right_p"></p>
																								<input type="hidden" name="yyzd${xsq.xsqdm }" id="xsq${xsq.xsqdm }_1_right_text" value=""/>
																							</td>
																							<td width="30%">
																								<button type="button" onclick="clickXsqBtn(this)" id="xsq${xsq.xsqdm }_1_right">应用</button>
																							</td>
																						</logic:notEqual>
																					</logic:iterate>
																					<!-- 右 end-->
																				</tr>
																				<!-- 照片所在其他行 -->
																				<logic:iterate name="xsq" property="rowList" id="row" indexId="rowNum">
																					<!-- 非首行 -->
																					<logic:notEqual name="row" property="isFirst" value="yes">
																						<!-- 照片所在行 -->
																						<logic:equal name="row" property="isZp" value="yes">
																							<tr>
																								<!-- 已配置字段 -->
																								<logic:equal name="row" property="zdMap.right" value="yes">
																									<td width="20%">
																										<p id="xsq${xsq.xsqdm }_${row.szh }_right_p">${row.zdMap.rightzdm }</p>
																										<input type="hidden" name="yyzd${xsq.xsqdm }" id="xsq${xsq.xsqdm }_${row.szh }_right_text" value="${row.zdMap.rightzd }"/>
																									</td>
																									<td width="30%">
																										<button type="button" onclick="clickXsqBtn(this)" id="xsq${xsq.xsqdm }_${row.szh }_right">取消</button>
																									</td>
																								</logic:equal>
																								<!-- 未配置字段 -->
																								<logic:notEqual name="row" property="zdMap.right" value="yes">
																									<td width="20%">
																										<p id="xsq${xsq.xsqdm }_${row.szh }_right_p"></p>
																										<input type="hidden" name="yyzd${xsq.xsqdm }" id="xsq${xsq.xsqdm }_${row.szh }_right_text" value=""/>
																									</td>
																									<td width="30%">
																										<button type="button" onclick="clickXsqBtn(this)" id="xsq${xsq.xsqdm }_${row.szh }_right">应用</button>
																									</td>
																								</logic:notEqual>
																							</tr>
																						</logic:equal>
																						<!-- 照片所在行 end-->
																					</logic:notEqual>
																					<!-- 非首行 end-->
																				</logic:iterate>
																				<!-- 照片所在其他行 end-->
																			</logic:equal>
																			<!-- 照片显示在左边 end-->
																		</table>
																	</div>
																</td>
															</tr>
														</logic:equal>
														<!-- 需要照片显示 end-->
														
														<!-- 所占行 -->
														<logic:iterate name="xsq" property="rowList" id="row" indexId="rowNum">
															<!-- 非首行 -->
															<logic:notEqual name="row" property="isFirst" value="yes">
																<!-- 非照片所在行 -->
																<logic:equal name="row" property="isZp" value="no">
																	<tr onclick="rowOnClick(this);" id="xsq${xsq.xsqdm }_${row.szh }_tr">
																		<td colspan="4">
																			<div id="xsq${xsq.xsqdm }_${row.szh }_div">
																				<table width="100%">
																					<tr>
																						<!-- 不需要合并单元格 -->
																						<logic:equal name="row" property="isHb" value="no">
																							<!-- 左 -->
																							<!-- 已配置字段 -->
																							<logic:equal name="row" property="zdMap.left" value="yes">
																								<td width="20%">
																									<p id="xsq${xsq.xsqdm }_${row.szh }_left_p">${row.zdMap.leftzdm }</p>
																									<input type="hidden" name="yyzd${xsq.xsqdm }" id="xsq${xsq.xsqdm }_${row.szh }_left_text" value="${row.zdMap.leftzd }"/>
																								</td>
																								<td width="30%">
																									<button type="button" onclick="clickXsqBtn(this)" id="xsq${xsq.xsqdm }_${row.szh }_left">取消</button>
																								</td>
																							</logic:equal>
																							<!-- 未配置字段 -->
																							<logic:notEqual name="row" property="zdMap.left" value="yes">
																								<td width="20%">
																									<p id="xsq${xsq.xsqdm }_${row.szh }_left_p"></p>
																									<input type="hidden" name="yyzd${xsq.xsqdm }" id="xsq${xsq.xsqdm }_${row.szh }_left_text"/>
																								</td>
																								<td width="30%">
																									<button type="button" onclick="clickXsqBtn(this)" id="xsq${xsq.xsqdm }_${row.szh }_left">应用</button>
																								</td>
																							</logic:notEqual>
																							<!-- 左 end-->
																							
																							<!-- 右 -->
																							<!-- 已配置字段 -->
																							<logic:equal name="row" property="zdMap.right" value="yes">
																								<td width="20%">
																									<p id="xsq${xsq.xsqdm }_${row.szh }_right_p">${row.zdMap.rightzdm }</p>
																									<input type="hidden" name="yyzd${xsq.xsqdm }" id="xsq${xsq.xsqdm }_${row.szh }_right_text" value="${row.zdMap.rightzd }"/>
																								</td>
																								<td width="30%">
																									<button type="button" onclick="clickXsqBtn(this)" id="xsq${xsq.xsqdm }_${row.szh }_right">取消</button>
																								</td>
																							</logic:equal>
																							<!-- 未配置字段 -->
																							<logic:notEqual name="row" property="zdMap.right" value="yes">
																								<td width="20%">
																									<p id="xsq${xsq.xsqdm }_${row.szh }_right_p"></p>
																									<input type="hidden" name="yyzd${xsq.xsqdm }" id="xsq${xsq.xsqdm }_${row.szh }_right_text"/>
																								</td>
																								<td width="30%">
																									<button type="button" onclick="clickXsqBtn(this)" id="xsq${xsq.xsqdm }_${row.szh }_right">应用</button>
																								</td>
																							</logic:notEqual>
																							<!-- 右 end-->																						
																						</logic:equal>
																						<!-- 不需要合并单元格 end-->
																						
																						<!-- 需要合并单元格 -->
																						<logic:equal name="row" property="isHb" value="yes">
																							<!-- 左 -->
																							<!-- 已配置字段 -->
																							<logic:equal name="row" property="zdMap.left" value="yes">
																								<td width="20%">
																									<p id="xsq${xsq.xsqdm }_${row.szh }_left_p">${row.zdMap.leftzdm }</p>
																									<input type="hidden" name="yyzd${xsq.xsqdm }" id="xsq${xsq.xsqdm }_${row.szh }_left_text" value="${row.zdMap.leftzd }"/>
																									<input type="hidden" name="hbh_szxsq" value="${xsq.xsqdm }"/>
																									<input type="hidden" name="hbh" value="${row.szh }"/>
																								</td>
																								<td colspan="3">
																									<button type="button" onclick="clickXsqBtn(this)" id="xsq${xsq.xsqdm }_${row.szh }_left">取消</button>
																								</td>
																							</logic:equal>
																							<!-- 未配置字段 -->
																							<logic:notEqual name="row" property="zdMap.left" value="yes">
																								<td width="20%">
																									<p id="xsq${xsq.xsqdm }_${row.szh }_left_p"></p>
																									<input type="hidden" name="yyzd${xsq.xsqdm }" id="xsq${xsq.xsqdm }_${row.szh }_left_text"/>
																									<input type="hidden" name="hbh_szxsq" value="${xsq.xsqdm }"/>
																									<input type="hidden" name="hbh" value="${row.szh }"/>
																								</td>
																								<td colspan="3">
																									<button type="button" onclick="clickXsqBtn(this)" id="xsq${xsq.xsqdm }_${row.szh }_left">应用</button>
																								</td>
																							</logic:notEqual>
																							<!-- 左 end-->
																						</logic:equal>
																						<!-- 需要合并单元格 end-->
																					</tr>
																				</table>
																			</div>
																		</td>
																	</tr>
																</logic:equal>
																<!-- 非照片所在行 end-->															
															</logic:notEqual>
															<!-- 非首行 end-->
														</logic:iterate>
														<!-- 所占行 end-->
													</tbody>
												</table>
											</div>
											<!-- 显示区 end -->
										</logic:iterate>
									</logic:notEmpty>
								</div>
								<input type="hidden" name="xsqNum" id="xsqNum" value="${maxXsqdm }"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- 显示区设置Div -->
			<div id="zjDiv" style="display: none">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>设置内容</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="35%"><font color="red">*</font>显示区名称</th>
								<td>
									<input type="text" name="sz_xsqmc" id="sz_xsqmc_value" maxlength="10" style="width : 50%;" value=""/>
								</td>
							</tr>
							<tr>
								<th><font color="red">*</font>所占行数(行)</th>
								<td>
									<input type="text" name="sz_szhs" id="sz_szhs_value"
										onkeydown="return onlyNum(this,2)"
										onmousedown="return onlyNum(this,2)"
										maxlength="2" value="6"
										style="width : 50%;ime-mode:disabled"/>
								</td>
							</tr>
							<tr>
								<th>需要照片</th>
								<td>
									<input type="radio" name="sz_zpxs" value="否" onclick="checkedZpxs(this.value);setDivHiddenValue('zpxs',this.value)" checked="checked"/>否
									<input type="radio" name="sz_zpxs" value="是" onclick="checkedZpxs(this.value);setDivHiddenValue('zpxs',this.value)"/>是
									<input type="hidden" id="sz_zpxs_value" value="否"/>
								</td>
							</tr>
							<tr id="zpwz_tr" style="display: none">
								<th>照片位置</th>
								<td>
									<input type="radio" name="sz_zpwz" value="右" onclick="setDivHiddenValue('zpwz',this.value)" checked="checked"/>右
									<input type="radio" name="sz_zpwz" value="左" onclick="setDivHiddenValue('zpwz',this.value)"/>左
									<input type="hidden" id="sz_zpwz_value" value="右"/>
								</td>
							</tr>
							<tr id="zpszhs_tr" style="display: none">
								<th><font color="red">*</font>照片所占行(行)</th>
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
										"<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button type="button" onclick="setXsqBySz()" id="buttonSave">
											确 定
										</button>
										
										<button type="button" onclick="hiddenMessage(true,true);return false;" id="buttonClose">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 显示区设置Div end-->
			
			<!-- 单行操作Div -->
			<div id="czDiv" style="display: none">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<th>
									<span>请选择需要执行的操作</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td align="center">
									<button type="button" onclick="hbXsqTr()" style="width:100px">
										合并单元格
									</button>
								</td>
							</tr>
							<tr>
								<td align="center">
									<button type="button" onclick="qxXsqTr()" style="width:100px">
										取消合并
									</button>
								</td>
							</tr>
							<tr id="czDiv_zjh" style="display: none">
								<td align="center">
									<button type="button" onclick="addXsqTr();" style="width:100px">
										增加行
									</button>
								</td>
							</tr>
							<tr id="czDiv_sch" style="display: none">
								<td align="center">
									<button type="button" onclick="delXsqTr();" style="width:100px">
										删除本行
									</button>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td>
									<div class="bz">
										注：操作成功将清除已分配字段。
									</div>
									<div class="btn">
										<button type="button" onclick="hiddenMessage(true,true);return false;">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 单行操作Div end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>