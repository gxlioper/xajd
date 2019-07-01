<%@ page language="java" contentType="text/html; charset=GBK"%>
<div id="div_step2_nr" style="width:100%;height:160px;overflow-x:hidden;overflow-y:auto;">
	<table class="formlist" border="0" align="center" style="width: 100%;">
		<thead>
			<tr>
				<td colspan="2">
					<span>
						合法规则
					</span>	
				</td>
			</tr>
			<tr>
				<td width="30%">
					制定前
				</td>
				<td>
					制定后
				</td>
			</tr>
		</thead>
		<tbody>
			<logic:iterate name="ruleList" id="hfRs">
				<logic:equal name="hfRs" property="isHf" value="yes">
					<tr>
						<td>
							${hfRs.dm }
						</td>
						<td>
							${hfRs.mc }
						</td>
					</tr>
				</logic:equal>
			</logic:iterate>
		</tbody>
		<tfoot>
			<tr><td colspan="2"></td></tr>
		</tfoot>
	</table>
</div>

</br>

<div id="div_step2_nr" style="width:100%;height:165px;overflow-x:hidden;overflow-y:auto;">
	<table class="formlist" border="0" align="center" style="width: 100%;">
		<thead>
			<tr>
				<td colspan="2">
					<span>
						不符合的规则
					</span>	
				</td>
			</tr>
			<tr>
				<td width="30%">
					制定前
				</td>
				<td>
					制定后
				</td>
			</tr>
		</thead>
		<tbody>
			<logic:iterate name="ruleList" id="nohfRs">
				<logic:equal name="nohfRs" property="isHf" value="no">
					<tr>
						<td>
							${nohfRs.dm }
						</td>
						<td>
							请选择：
							<input type="radio" name="rad_rule_${nohfRs.dm }" 
								onclick="$('hid_rule_${nohfRs.dm }').value=this.value" 
								value="1"/>
							校级(代码：1)
							<input type="radio" name="rad_rule_${nohfRs.dm }" 
								onclick="$('hid_rule_${nohfRs.dm }').value=this.value" 
								value="5"/>
							院级(代码：5)
							<input type="hidden" name="zdq" value="${nohfRs.dm }"/>
							<input type="hidden" name="zdh" id="hid_rule_${nohfRs.dm }"/>
						</td>
					</tr>
				</logic:equal>
			</logic:iterate>
		</tbody>
	</table>
</div>
<%--		<logic:present name="rowNum">--%>
<%--			<%int rowNum = Integer.parseInt(request.getAttribute("rowNum").toString());%>--%>
<%--			<%for(int i=0;i<rowNum;i++){ %>--%>
<%--				<tr>--%>
<%--					<td>--%>
<%--						&nbsp;--%>
<%--					</td>--%>
<%--					<td>--%>
<%--						&nbsp;--%>
<%--					</td>--%>
<%--				</tr>--%>
<%--			<%}%>--%>
<%--		</logic:present>--%>