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
							<select name="zdh" id="zdh_${nohfRs.dm }" style="width: 150px"">
										
							</select>
							<input type="hidden" name="zdq" value="${nohfRs.dm }"/>
						</td>
					</tr>
				</logic:equal>
			</logic:iterate>
		</tbody>
	</table>
</div>
<script language="javascript" defer="defer">

	var num =  document.getElementsByName("zdh").length;
	
	for(var i=0;i<num;i++){
		var obj = document.getElementsByName("zdh")[i];
		var id=obj.id;
		setXyList(id);
	}
	
	function setXyList(id){
		jQuery(function(){
			var bmdm = jQuery('#'+id);
			var url = "sjyJcsjcsh.do?method=setBmOption&id="+id;
			
			bmdm.combobox({
				valueField:'dm',
				textField:'mc',
				url:url,
				data:{url:url}
			});
						
		});
	}
</script>