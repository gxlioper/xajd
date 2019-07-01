<%@ page language="java" contentType="text/html; charset=GBK"%>
<script language="javascript">
	
	//选择字段
	function clickZdsz(zd,value){

		if(zd == "allChoose"){
			if(value != "notdo"){
				var div_num = $("tb_sznr").getElementsByTagName('div').length;
				for(var i=0;i<div_num;i++){
					var obj = $("tb_sznr").getElementsByTagName('div')[i];
					var obj_id = obj.id;
					var obj_zd = obj.id.replace("div_zdsz_","");
					
					var wkxz_id = "text_wkxz_"+obj_zd;

					if(obj_zd != "allChoose"){
						if(value == "可以为空"){
							$("rad_zd_sz_"+obj_zd+"_yes").checked = true;						
						}else{
							$("rad_zd_sz_"+obj_zd+"_no").checked = true;
						}
						$(wkxz_id).value = value;	
					}
				}
			}
		}else{
			var wkxz_id = "text_wkxz_"+zd;

			if(value == "可以为空"){
				$("rad_zd_sz_"+zd+"_yes").checked = true;
				
			}else{
				$("rad_zd_sz_"+zd+"_no").checked = true;
			}
			
			$(wkxz_id).value = value;	
			$("rad_zd_sz_allChoose_notdo").checked = true;
		}
	}
	
	//验证步骤5
	function checkStep5(){
	
		var num =  document.getElementsByName("wkxz").length;
		var flag = true;
		
		for(var i=0;i<num;i++){
			var obj = document.getElementsByName("wkxz")[i];
			if(obj.value ==  ""){
				flag = false;
			}
		}
		
		return flag;
	}
</script>

<div><b>请选择字段进行设置,批量设置请勾选“全部”</b></div>
<div id="div_step2_nr" style="width:100%;height:250px;overflow-x:hidden;overflow-y:auto;">
	<table style="width: 100%">
		<logic:iterate name="xszZdList" id="zdtrRs" indexId="trNum">
			<tr>
				<logic:iterate name="zdtrRs" property="tdList" id="zdtdRs" indexId="tdNum">
					<td width="15%">
						<logic:notEmpty name="zdtdRs" property="zd">
							<input type="radio" name="rad_zd" id="rad_zd_${zdtdRs.zd}" value="${zdtdRs.zd}" 
							<logic:equal name="trNum" value="0"><logic:equal name="tdNum" value="0">checked="checked"</logic:equal></logic:equal>
							onclick="clickStepZd('${zdtdRs.zd}')"/>	
						</logic:notEmpty>
						${zdtdRs.zdm }
					</td>
				</logic:iterate>
			</tr>
		</logic:iterate>
	</table>
</div>
<table style="width:100%;" id="tb_sznr">
	<tr>
		<td>
			<p><b>请确定勾选字段在维护时可否为空</b></p>
			<!-- 全部字段设置 -->
			<div id="div_zdsz_allChoose" style="">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rad_zd_sz_allChoose" id="rad_zd_sz_allChoose_notdo" value="notdo" checked="checked"/>	
				不处理
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rad_zd_sz_allChoose" onclick="clickZdsz('allChoose',this.value)" value="可以为空"/>	
				可以为空
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rad_zd_sz_allChoose" onclick="clickZdsz('allChoose',this.value)" value="不可为空"/>	
				不可为空
			</div>
			<!-- 全部字段设置 end-->
			
			<!-- 具体字段设置 -->	
			<logic:iterate name="kczzdList" id="zdszRs">	
				<div id="div_zdsz_${zdszRs.zd}" style="display: none">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="rad_zd_sz_${zdszRs.zd}" id="rad_zd_sz_${zdszRs.zd}_yes" onclick="clickZdsz('${zdszRs.zd}',this.value)" value="可以为空"/>	
					可以为空
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="rad_zd_sz_${zdszRs.zd}" id="rad_zd_sz_${zdszRs.zd}_no" onclick="clickZdsz('${zdszRs.zd}',this.value)" value="不可为空"/>	
					不可为空
					<input type="hidden" name="wkxz" id="text_wkxz_${zdszRs.zd}" value="${zdszRs.wkxz}">
				</div>
			</logic:iterate>
			</br>
			<!-- 具体字段设置end-->	
			<p style="width:100%;height:130px;overflow-x:hidden;overflow-y:auto;">
				<font color="blue">
				&nbsp;&nbsp;&nbsp;&nbsp;
				注：本设置将应用于“学生信息”功能模块下的增加修改学生操作,控制该字段可以录入可否为空。</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(1)可以为空：该字段非重要字段，可以不维护内容。</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(2)不可为空：该字段为重要字段，必须录入数据，不可为空。</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				批量操作请勾选“全部”。</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				完成操作后请进行“下一步”操作。
				</font>
			</p>
		</td>
	</tr>
</table>
	

