<%@ page language="java" contentType="text/html; charset=GBK"%>
<table class="formlist" border="0" align="center" style="width: 100%">
	<thead>
		<tr>
			<th colspan="4">
				<span>家庭基本情况</span>				
			</th>
		</tr>
	</thead>
	<tbody>
		<!-- 家庭成员情况 -->
		<tr>
			<td colspan="4" style="" id="jtcyTrId">
				<p id="jtcyCtrl">
				<input  value="+" onclick="trAdd('jtcyqk','add','cyAdd')" style="width: 20px"/>
				<input  value="-" onclick="trDel('jtcyqk','delRow')" style="width: 20px"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
				<input type="text" name="cyAdd" id="cyAdd" style="width: 20px" onblur="trAdd('jtcyqk','madd','cyAdd')">
				&nbsp;行 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 清除&nbsp;&nbsp;
				<input type="text" name="cyDel" id="cyDel" style="width: 20px" onblur="trDelAll('jtcyqk','cyDel')">
				&nbsp;行&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</p>	
				<table class="tbstyle" align="center" width="100%" id="tTb">
					<tr>
						<td>
							<div class="mid_box">
								<table align="center" style="width: 100%" id="rsT" class="tbstyle">
									<thead style="height: 23px">
										<tr>
											<logic:iterate name="jtcyTitleList" id="jtcyTitle">
												<td align="center" nowrap="nowrap" style='2%'>
													${jtcyTitle.cn }
												</td>
											</logic:iterate>
										</tr>
									</thead>		
									<tbody width="100%" class="tbstyle" id="jtcyqk">
			
									</tbody>	
								</table>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<!-- 家庭成员情况 end-->
		<!-- 占一个单元格的字段 -->			
		<logic:iterate name="xmnr" property="nrList" id="zdxx">
			<logic:equal name="zdxx" property="zdlx" value="short">
				<tr style="height: 23px">
					<logic:iterate id="nr" name="zdxx" property="zdList" indexId="index">
						<logic:notEqual name="nr" property="lastZd" value="yes">
							<th align="right" width="15%" style="word-break:break-all;">
								${nr.zdm }
								<logic:equal name="nr" property="bxlr" value="是">
									<input type="hidden" name="bxlr" value="${nr.zdm }!!@@!!${nr.zd }">
								</logic:equal>
							</th>
							<td align="left" width="35%">
								<!-- 文本框 -->
								<logic:equal name="nr" property="kjlx" value="text">
									<!-- 数字限制 -->
									<logic:equal name="nr" property="lrxz" value="数字">
										<input type="text" name="save_${nr.zd }" 
											id="${nr.zd }" 
											value="${nr.zdz }" 
											onkeypress="return chkonlynum(this,20)" 
											style="ime-mode:disabled"/>
									</logic:equal>
									<!-- 金额限制 -->
									<logic:equal name="nr" property="lrxz" value="金额">
										<input type="text" name="save_${nr.zd }" 
											id="${nr.zd }" 
											value="${nr.zdz }" 
											onkeypress="return sztzNumInputValue(this,20)" 
											maxlength="20" 
											style="ime-mode:disabled"/>
									</logic:equal>
									<!-- 日期限制 -->
									<logic:equal name="nr" property="lrxz" value="日期">
										<input type="text" name="save_${nr.zd }" 
											id="${nr.zd }" 
											value="${nr.zdz }" 
											readonly="true"  style="cursor:hand;"
											onblur="dateFormatChg(this)"
											onclick="time(this.id)"/>
									</logic:equal>
									<!-- 无限制 -->
									<logic:equal name="nr" property="lrxz" value="无限制">
										<input type="text" name="save_${nr.zd }" id="${nr.zd }" value="${nr.zdz }" maxlength="20"/>
									</logic:equal>
								</logic:equal>
								<!-- 下拉框 -->
								<logic:equal name="nr" property="kjlx" value="select">
									<select name="save_${nr.zd }" id="${nr.zd }" ></select>
									<input type="hidden" id="select_${nr.zd }" value="${nr.zdz }">
								</logic:equal>								
							</td>
							<%if((index.intValue()+1)%2==0){%>
								<% out.print("</tr>"); %>
							<%}%> 
						</logic:notEqual>
						<logic:equal name="nr" property="lastZd" value="yes">
							<%if((index.intValue()+1)%2==0){%>
								<th align="right" width="15%"  style="word-break:break-all;">
									${nr.zdm }
									<logic:equal name="nr" property="bxlr" value="是">
										<input type="hidden" name="bxlr" value="${nr.zdm }!!@@!!${nr.zd }">
									</logic:equal>
								</th>
								<td align="left" width="35%">
								<!-- 文本框 -->
								<logic:equal name="nr" property="kjlx" value="text">
									<!-- 数字限制 -->
									<logic:equal name="nr" property="lrxz" value="数字">
										<input type="text" name="save_${nr.zd }" 
											id="${nr.zd }" 
											value="${nr.zdz }" 
											onkeypress="return onlyNum(this,20)" 
											style="ime-mode:disabled"/>
									</logic:equal>
									<!-- 金额限制 -->
									<logic:equal name="nr" property="lrxz" value="金额">
										<input type="text" name="save_${nr.zd }" 
											id="${nr.zd }" 
											value="${nr.zdz }" 
											onkeypress="return sztzNumInputValue(this,20)" 
											maxlength="20" 
											style="ime-mode:disabled"/>
									</logic:equal>
									<!-- 日期限制 -->
									<logic:equal name="nr" property="lrxz" value="日期">
										<input type="text" name="save_${nr.zd }" 
											id="${nr.zd }" 
											value="${nr.zdz }" 
											readonly="true"  style="cursor:hand;"
											onblur="dateFormatChg(this)"
											onclick="time(this.id)"/>
									</logic:equal>
									<!-- 无限制 -->
									<logic:equal name="nr" property="lrxz" value="无限制">
										<input type="text" name="save_${nr.zd }" id="${nr.zd }" value="${nr.zdz }" maxlength="20"/>
									</logic:equal>
								</logic:equal>
								<!-- 下拉框 -->
								<logic:equal name="nr" property="kjlx" value="select">
									<select name="save_${nr.zd }" id="${nr.zd }" value=""></select>
									<input type="hidden" id="select_${nr.zd }" value="${nr.zdz }">
								</logic:equal>
								</td>
							<%}%> 	
							<%if((index.intValue()+1)%2==1){%>
								<th align="right" width="15%"  style="word-break:break-all;">
									${nr.zdm }
									<logic:equal name="nr" property="bxlr" value="是">
										<input type="hidden" name="bxlr" value="${nr.zdm }!!@@!!${nr.zd }">
									</logic:equal>
								</th>
								<td align="left" width="35%">
								<!-- 文本框 -->
								<logic:equal name="nr" property="kjlx" value="text">
									<!-- 数字限制 -->
									<logic:equal name="nr" property="lrxz" value="数字">
										<input type="text" name="save_${nr.zd }" 
											id="${nr.zd }" 
											value="${nr.zdz }" 
											onkeypress="return onlyNum(this,20)" 
											style="ime-mode:disabled"/>
									</logic:equal>
									<!-- 金额限制 -->
									<logic:equal name="nr" property="lrxz" value="金额">
										<input type="text" name="save_${nr.zd }" 
											id="${nr.zd }" 
											value="${nr.zdz }" 
											onkeypress="return sztzNumInputValue(this,20)" 
											maxlength="20" 
											style="ime-mode:disabled"/>
									</logic:equal>
									<!-- 日期限制 -->
									<logic:equal name="nr" property="lrxz" value="日期">
										<input type="text" name="save_${nr.zd }" 
											id="${nr.zd }" 
											value="${nr.zdz }" 
											readonly="true"  style="cursor:hand;"
											onblur="dateFormatChg(this)"
											onclick="time(this.id)"/>
									</logic:equal>
									<!-- 无限制 -->
									<logic:equal name="nr" property="lrxz" value="无限制">
										<input type="text" name="save_${nr.zd }" id="${nr.zd }" value="${nr.zdz }" maxlength="20"/>
									</logic:equal>
								</logic:equal>
								<!-- 下拉框 -->
								<logic:equal name="nr" property="kjlx" value="select">
									<select name="save_${nr.zd }" id="${nr.zd }" value=""></select>
									<input type="hidden" id="select_${nr.zd }" value="${nr.zdz }">
								</logic:equal>	
								</td>
								<td></td>
								<td></td>
							<%}%> 	
							<% out.print("</tr>"); %>
						</logic:equal>
					</logic:iterate>		
				</tr>
			</logic:equal>
		</logic:iterate>
		<!-- 占多个单元格的字段 -->
		<logic:iterate name="xmnr" property="nrList" id="zdxx">
			<logic:equal name="zdxx" property="zdlx" value="long">
				<logic:iterate id="nr" name="zdxx" property="zdList" indexId="index">	
					<logic:equal name="nr" property="zdlx" value="long">
						<tr style="height: 23px">						
							<th align="right" width="15%" style="word-break:break-all;">
								${nr.zdm }
								<logic:equal name="nr" property="bxlr" value="是">
									<input type="hidden" name="bxlr" value="${nr.zdm }!!@@!!${nr.zd }">
								</logic:equal>
							</th>
							<td align="left" colspan="3">
								<!-- 数字限制 -->
								<logic:equal name="nr" property="lrxz" value="数字">
									<input type="text" name="save_${nr.zd }" 
										id="${nr.zd }" 
										value="${nr.zdz }" 
										onkeypress="return onlyNum(this,20)" 
										style="ime-mode:disabled;width: 90%"/>
								</logic:equal>
								<!-- 金额限制 -->
								<logic:equal name="nr" property="lrxz" value="金额">
									<input type="text" name="save_${nr.zd }" 
										id="${nr.zd }" 
										value="${nr.zdz }" 
										onkeypress="return sztzNumInputValue(this,20)" 
										maxlength="20" 
										style="width:90%;ime-mode:disabled"/>
								</logic:equal>
								<!-- 日期限制 -->
								<logic:equal name="nr" property="lrxz" value="日期">
									<input type="text" name="save_${nr.zd }" 
										id="${nr.zd }" 
										value="${nr.zdz }" 
										readonly="true"  style="cursor:hand;width: 90%"
										onblur="dateFormatChg(this)"
										onclick="time(this.id)"/>
								</logic:equal>
								<!-- 无限制 -->
								<logic:equal name="nr" property="lrxz" value="无限制">
									<input type="text" name="save_${nr.zd }" id="${nr.zd }" value="${nr.zdz }" maxlength="50" style="width: 90%"/>
								</logic:equal>
							</td>				
						</tr>
					</logic:equal>
					<logic:equal name="nr" property="zdlx" value="textarea">
						<tr style="height: 23px">						
							<th align="right" width="15%" style="word-break:break-all;">
								${nr.zdm }
								<logic:equal name="nr" property="bxlr" value="是">
									<input type="hidden" name="bxlr" value="${nr.zdm }!!@@!!${nr.zd }">
								</logic:equal>
								<br>
								<font color="red">(限制录入500字)</font>
							</th>
							<td align="left" colspan="3">
								<textarea rows="5" style="width:600px" name="save_${nr.zd }" id="${nr.zd }" onblur="chLeng(this,500)" type="_moz">${nr.zdz }</textarea>
							</td>				
						</tr>
					</logic:equal>
				</logic:iterate>		
			</logic:equal>
		</logic:iterate>
	</tbody>
</table>
