<%@ page language="java" contentType="text/html; charset=GBK"%>	
<table class="formlist" border="0" align="center" style="width: 100%">
	<thead>
		<tr>
			<th colspan="4">
				<span>��ѧ���������Ϣ</span>				
			</th>
		</tr>
	</thead>
	<tbody>
	
		<!-- ռһ����Ԫ����ֶ� -->			
		<logic:iterate name="xmnr" property="nrList" id="zdxx">
			<logic:equal name="zdxx" property="zdlx" value="short">
				<tr style="height: 23px">
					<logic:iterate id="nr" name="zdxx" property="zdList" indexId="index">
						<logic:notEqual name="nr" property="lastZd" value="yes">
							<th align="right" width="15%">
								${nr.zdm }
								<logic:equal name="nr" property="bxlr" value="��">
									<input type="hidden" name="bxlr" value="${nr.zdm }!!@@!!${nr.zd }">
								</logic:equal>
							</th>
							<td align="left" width="35%">
								<!-- �ı��� -->
								<logic:equal name="nr" property="kjlx" value="text">
									<!-- �������� -->
									<logic:equal name="nr" property="lrxz" value="����">
										<input type="text" name="save_${nr.zd }" 
											id="${nr.zd }" 
											value="${nr.zdz }" 
											onkeypress="return onlyNum(this,20)" 
											style="ime-mode:disabled"/>
									</logic:equal>
									<!-- ������� -->
									<logic:equal name="nr" property="lrxz" value="���">
										<input type="text" name="save_${nr.zd }" 
											id="${nr.zd }" 
											value="${nr.zdz }" 
											onkeypress="return sztzNumInputValue(this,20)" 
											maxlength="20" 
											style="ime-mode:disabled"/>
									</logic:equal>
									<!-- �������� -->
									<logic:equal name="nr" property="lrxz" value="����">
										<input type="text" name="save_${nr.zd }" 
											id="${nr.zd }" 
											value="${nr.zdz }" 
											readonly="true"  style="cursor:hand;"
											onblur="dateFormatChg(this)"
											onclick="time(this.id)"/>
									</logic:equal>
									<!-- ������ -->
									<logic:equal name="nr" property="lrxz" value="������">
										<input type="text" name="save_${nr.zd }" id="${nr.zd }" value="${nr.zdz }" maxlength="20"/>
									</logic:equal>
								</logic:equal>
								<!-- ������ -->
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
								<th align="right" width="15%">
									${nr.zdm }
									<logic:equal name="nr" property="bxlr" value="��">
										<input type="hidden" name="bxlr" value="${nr.zdm }!!@@!!${nr.zd }">
									</logic:equal>
								</th>
								<td align="left" width="35%">
								<!-- �ı��� -->
								<logic:equal name="nr" property="kjlx" value="text">
									<!-- �������� -->
									<logic:equal name="nr" property="lrxz" value="����">
										<input type="text" name="save_${nr.zd }" 
											id="${nr.zd }" 
											value="${nr.zdz }" 
											onkeypress="return onlyNum(this,20)" 
											style="ime-mode:disabled"/>
									</logic:equal>
									<!-- ������� -->
									<logic:equal name="nr" property="lrxz" value="���">
										<input type="text" name="save_${nr.zd }" 
											id="${nr.zd }" 
											value="${nr.zdz }" 
											onkeypress="return sztzNumInputValue(this,20)" 
											maxlength="20" 
											style="ime-mode:disabled"/>
									</logic:equal>
									<!-- �������� -->
									<logic:equal name="nr" property="lrxz" value="����">
										<input type="text" name="save_${nr.zd }" 
											id="${nr.zd }" 
											value="${nr.zdz }" 
											readonly="true"  style="cursor:hand;"
											onblur="dateFormatChg(this)"
											onclick="time(this.id)"/>
									</logic:equal>
									<!-- ������ -->
									<logic:equal name="nr" property="lrxz" value="������">
										<input type="text" name="save_${nr.zd }" id="${nr.zd }" value="${nr.zdz }" maxlength="20"/>
									</logic:equal>
								</logic:equal>
								<!-- ������ -->
								<logic:equal name="nr" property="kjlx" value="select">
									<select name="save_${nr.zd }" id="${nr.zd }" value=""></select>
									<input type="hidden" id="select_${nr.zd }" value="${nr.zdz }">
								</logic:equal>
								</td>
							<%}%> 	
							<%if((index.intValue()+1)%2==1){%>
								<th align="right" width="15%">
									${nr.zdm }
									<logic:equal name="nr" property="bxlr" value="��">
										<input type="hidden" name="bxlr" value="${nr.zdm }!!@@!!${nr.zd }">
									</logic:equal>
								</th>
								<td align="left" width="35%">
								<!-- �ı��� -->
								<logic:equal name="nr" property="kjlx" value="text">
									<!-- �������� -->
									<logic:equal name="nr" property="lrxz" value="����">
										<input type="text" name="save_${nr.zd }" 
											id="${nr.zd }" 
											value="${nr.zdz }" 
											onkeypress="return onlyNum(this,20)" 
											style="ime-mode:disabled"/>
									</logic:equal>
									<!-- ������� -->
									<logic:equal name="nr" property="lrxz" value="���">
										<input type="text" name="save_${nr.zd }" 
											id="${nr.zd }" 
											value="${nr.zdz }" 
											onkeypress="return sztzNumInputValue(this,20)" 
											maxlength="20" 
											style="ime-mode:disabled"/>
									</logic:equal>
									<!-- �������� -->
									<logic:equal name="nr" property="lrxz" value="����">
										<input type="text" name="save_${nr.zd }" 
											id="${nr.zd }" 
											value="${nr.zdz }" 
											readonly="true"  style="cursor:hand;"
											onblur="dateFormatChg(this)"
											onclick="time(this.id)"/>
									</logic:equal>
									<!-- ������ -->
									<logic:equal name="nr" property="lrxz" value="������">
										<input type="text" name="save_${nr.zd }" id="${nr.zd }" value="${nr.zdz }" maxlength="20"/>
									</logic:equal>
								</logic:equal>
								<!-- ������ -->
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
		<!-- ռ�����Ԫ����ֶ� -->
		<logic:iterate name="xmnr" property="nrList" id="zdxx">
			<logic:equal name="zdxx" property="zdlx" value="long">
				<logic:iterate id="nr" name="zdxx" property="zdList" indexId="index">	
					<logic:equal name="nr" property="zdlx" value="long">
						<tr style="height: 23px">						
							<th align="right" width="15%">
								${nr.zdm }
								<logic:equal name="nr" property="bxlr" value="��">
									<input type="hidden" name="bxlr" value="${nr.zdm }!!@@!!${nr.zd }">
								</logic:equal>
							</th>
							<td align="left" colspan="3">
								<!-- �������� -->
								<logic:equal name="nr" property="lrxz" value="����">
									<input type="text" name="save_${nr.zd }" 
										id="${nr.zd }" 
										value="${nr.zdz }" 
										onkeypress="return onlyNum(this,20)" 
										style="ime-mode:disabled;width: 90%"/>
								</logic:equal>
								<!-- ������� -->
								<logic:equal name="nr" property="lrxz" value="���">
									<input type="text" name="save_${nr.zd }" 
										id="${nr.zd }" 
										value="${nr.zdz }" 
										onkeypress="return sztzNumInputValue(this,20)" 
										maxlength="20" 
										style="width:90%;ime-mode:disabled"/>
								</logic:equal>
								<!-- �������� -->
								<logic:equal name="nr" property="lrxz" value="����">
									<input type="text" name="save_${nr.zd }" 
										id="${nr.zd }" 
										value="${nr.zdz }" 
										readonly="true"  style="cursor:hand;width: 90%"
										onblur="dateFormatChg(this)"
										onclick="time(this.id)"/>
								</logic:equal>
								<!-- ������ -->
								<logic:equal name="nr" property="lrxz" value="������">
									<input type="text" name="save_${nr.zd }" id="${nr.zd }" value="${nr.zdz }" maxlength="50" style="width: 90%"/>
								</logic:equal>
							</td>				
						</tr>
					</logic:equal>
					<logic:equal name="nr" property="zdlx" value="textarea">
						<tr style="height: 23px">						
							<th align="right" width="15%">
								${nr.zdm }
								<logic:equal name="nr" property="bxlr" value="��">
									<input type="hidden" name="bxlr" value="${nr.zdm }!!@@!!${nr.zd }">
								</logic:equal>
								<br>
								<font color="red">(����¼��500��)</font>
							</th>
							<td align="left" colspan="3">
								<textarea rows="5" style="width:90%" name="save_${nr.zd }" id="${nr.zd }" onblur="chLeng(this,500)" type="_moz">${nr.zdz }</textarea>
							</td>				
						</tr>
					</logic:equal>
				</logic:iterate>		
			</logic:equal>
		</logic:iterate>
	</tbody>
</table>