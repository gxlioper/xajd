<%@ page language="java" contentType="text/html; charset=GBK"%>
<div id="div_step2_nr" style="width:100%;height:160px;overflow-x:hidden;overflow-y:auto;">
	<table class="formlist" border="0" align="center" style="width: 100%;">
		<thead>
			<tr>
				<td colspan="2">
					<span>
						�Ϸ�����
					</span>	
				</td>
			</tr>
			<tr>
				<td width="30%">
					�ƶ�ǰ
				</td>
				<td>
					�ƶ���
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
						�����ϵĹ���
					</span>	
				</td>
			</tr>
			<tr>
				<td width="30%">
					�ƶ�ǰ
				</td>
				<td>
					�ƶ���
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
							��ѡ��
							<input type="radio" name="rad_rule_${nohfRs.dm }" 
								onclick="$('hid_rule_${nohfRs.dm }').value=this.value" 
								value="1"/>
							У��(���룺1)
							<input type="radio" name="rad_rule_${nohfRs.dm }" 
								onclick="$('hid_rule_${nohfRs.dm }').value=this.value" 
								value="5"/>
							Ժ��(���룺5)
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