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

<%--<div id="div_step2_nr" style="width:100%;height:165px;overflow-x:hidden;overflow-y:auto;">--%>
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
							<logic:empty name="nohfRs" property="dm">
								 空
							</logic:empty>
							<logic:notEmpty name="nohfRs" property="dm">
								${nohfRs.dm }
							</logic:notEmpty>
						</td>
						<td>
							<div id="div_dz_${nohfRs.dm }" style="">
								<a href="#" onclick="dz('${nohfRs.dm }');return false;">开始定制</a>
							</div>
							<div id="div_${nohfRs.dm }" style="display: none">
							省：
							<select name="sheng" id="sheng_${nohfRs.dm }" style="width: 80px">
										
							</select>
							
							市：
							<select name="shi" id="shi_${nohfRs.dm }" style="width: 80px">
							
							</select>

							县：
							<select name="xian" id="xian_${nohfRs.dm }" style="width: 80px">
										
							</select>
							</div>
							<input type="hidden" id="zdh_${nohfRs.dm }" name="zdh" value="${nohfRs.zdh }"/>
							<input type="hidden" name="zdq" value="${nohfRs.dm }"/>
						</td>
					</tr>
				</logic:equal>
			</logic:iterate>
		</tbody>
	</table>
<%--</div>--%>

<script language="javascript" defer="defer">

	
	function dz(dm){
		
		var dz_id = "div_dz_"+dm;
		var div_id = "div_"+dm;
		
		$(dz_id).style.display = "none";
		$(div_id).style.display = "";
		
		var sheng_id = "sheng_"+dm;
		var shi_id = "shi_"+dm;
		var xian_id = "xian_"+dm;
		
		$(sheng_id).className="easyui-combobox";
		$(shi_id).className="easyui-combobox";
		$(xian_id).className="easyui-combobox";
		
		setShengList(sheng_id);
	}
	//var num =  document.getElementsByName("sheng").length;
	
	//for(var i=0;i<num;i++){
		//var obj = document.getElementsByName("sheng")[i];
		//var id=obj.id;
		//setShengList(id);
	//}
	
	function setShengList(id){
	
		jQuery(function(){
			var bmdm = jQuery('#'+id);
			var url = "sjyJcsjcsh.do?method=setXzqkOption";
				url+= "&id="+id;
				url+= "&lx=sheng";
	
			bmdm.combobox({
				valueField:'dm',
				textField:'mc',
				url:url,
				data:{url:url},
				panelHeight:"auto",
				onChange:function(){	
					setShiList(id,bmdm.combobox('getValue'));
					var ssx = id.replace("sheng_","");
					setXianList(id,ssx);	
				}
			});			
		});
	}
	
	function setShiList(dm,sheng){

		jQuery(function(){
			var id = "shi"+dm.replace("sheng","");
			var bmdm = jQuery('#'+id);
			
			var url = "sjyJcsjcsh.do?method=setXzqkOption";
				url+= "&id="+id;
				url+= "&sheng="+sheng;
				url+= "&lx=shi";
			
			bmdm.combobox({
				valueField:'dm',
				textField:'mc',
				url:url,
				data:{url:url},
				panelHeight:"auto",
				onChange:function(){
					var ssx = id.replace("shi_","");
					setXianList(id,ssx);	
				}
			});	
		});
	}
	
	function setXianList(dm,ssx){

		jQuery(function(){
			var id = "xian_"+ssx;
			var bmdm = jQuery('#'+id);

			var url = "sjyJcsjcsh.do?method=setXzqkOption";
				url+= "&id="+id;
				url+= "&sheng="+jQuery('#sheng_'+ssx).combobox('getValue');
				url+= "&shi="+jQuery('#shi_'+ssx).combobox('getValue');
				url+= "&lx=xian";

			bmdm.combobox({
				valueField:'dm',
				textField:'mc',
				url:url,
				data:{url:url},
				panelHeight:"auto"
			});	
		});
	}
	
	function setZdhValue(){
	
		var num =  document.getElementsByName("zdh").length;
	
		for(var i=0;i<num;i++){
			var obj = document.getElementsByName("zdh")[i];
			var ssx = obj.id.replace("zdh_","");
			
			var sheng = "";
			
			if(jQuery('#sheng_'+ssx) && jQuery('#sheng_'+ssx).attr("class")!=""){	
				sheng = jQuery('#sheng_'+ssx).combobox('getValue');
			}
			var shi = "";
			
			if(jQuery('#shi_'+ssx) && jQuery('#shi_'+ssx).attr("class")!=""){
				shi= jQuery('#shi_'+ssx).combobox('getValue');
			}
			var xian = "";
			
			if(jQuery('#xian_'+ssx) && jQuery('#xian_'+ssx).attr("class")!=""){
				xian = jQuery('#xian_'+ssx).combobox('getValue');
			}

			var zdh = "";
			if(sheng != "" || shi != "" || xian != ""){
				zdh = sheng+"/"+shi+"/"+xian;
			}
			obj.value = zdh;
		}
	}
</script>