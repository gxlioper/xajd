<%@ page language="java" contentType="text/html; charset=GBK"%>
<script language="javascript">
	
	//选择字段
	function clickZdsz(zd,value){
	
		if(zd == "allChoose"){
			if(value == "same"){
				var div_num = $("tb_sznr").getElementsByTagName('div').length;
				for(var i=0;i<div_num;i++){
					var obj = $("tb_sznr").getElementsByTagName('div')[i];
					var obj_id = obj.id;
					var obj_zd = obj.id.replace("div_zdsz_","");
					
					var text_id = "text_"+obj_zd;
					var xsmc_id = "text_xsmc_"+obj_zd;
					var zd_id = "ch_zd_"+obj_zd;
					var sanme_id = "rad_zd_sz_"+obj_zd+"_same";

					if(obj_zd != "allChoose"){
						$(text_id).value = "";
						$(text_id).disabled = "disabled";
						$(xsmc_id).value = $(zd_id).value;
						$(sanme_id).checked = true;
					}
				}
			}
		}else{
		
			var text_id = "text_"+zd;
			var xsmc_id = "text_xsmc_"+zd;
			var zdm_id = "ch_zd_"+zd;
		
			if(value == "same"){
				$(text_id).value = "";
				$(text_id).disabled = "disabled";
				$(xsmc_id).value = $(zdm_id).value;	
			}else{
				$(text_id).disabled = "";
				$(xsmc_id).value = "";
			}
			
			$("rad_zd_sz_allChoose_notdo").checked = true;
		}
	}
	
	//设置显示名称
	function setXsmc(zd,value){
		var id = "text_xsmc_"+zd;
		$(id).value = value;
	}
	
	//验证步骤2
	function checkStep2(){
	
		var num =  document.getElementsByName("xsmc").length;
		var flag = true;
		
		for(var i=0;i<num;i++){
			var obj = document.getElementsByName("xsmc")[i];
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
			<p><b>请确定勾选字段的页面显示</b></p>
			<!-- 全部字段设置 -->
			<div id="div_zdsz_allChoose" style="">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rad_zd_sz_allChoose" id="rad_zd_sz_allChoose_notdo" onclick="clickZdsz('allChoose',this.value);" value="notdo" checked="checked"/>	
				不处理
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rad_zd_sz_allChoose" onclick="clickZdsz('allChoose',this.value);" value="same"/>	
				同字段名
			</div>
			<!-- 全部字段设置 end-->
			
			<!-- 具体字段设置 -->	
			<logic:iterate name="kczzdList" id="zdszRs">	
				<div id="div_zdsz_${zdszRs.zd}" style="display: none">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="rad_zd_sz_${zdszRs.zd}" id="rad_zd_sz_${zdszRs.zd}_same" onclick="clickZdsz('${zdszRs.zd}',this.value);" value="same"/>	
					同字段名
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="rad_zd_sz_${zdszRs.zd}" onclick="clickZdsz('${zdszRs.zd}',this.value);" value="other"/>	
					其他
					<input type="text" id="text_${zdszRs.zd}" style="width: 100px"
						onblur="setXsmc('${zdszRs.zd}',this.value)" disabled="disabled">
					<input type="hidden" name="xsmc" id="text_xsmc_${zdszRs.zd}" value="${zdszRs.xsmc}">
				</div>
			</logic:iterate>
			</br>
			<!-- 具体字段设置end-->	
			<p>	<font color="blue">
				&nbsp;&nbsp;&nbsp;&nbsp;
				注：本设置将应用于“学生信息”功能模块下的“个人信息维护”页面的字段展示，比如某字段命名为“学号”，</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					在学生信息页面也希望展现为“学号”，那么，请将该字段设置为“同字段名”，如果某字段命名为“<bean:message key="lable.xb" />”，</br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					而希望展现的命名却是<bean:message key="lable.xb" />，那么，请在选中该字段的前提下，选择“其他”，并在输入框中维护希望</br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					展现的内容。</br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					批量操作请勾选“全部”，但是只能选择“同字段名”。</br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					完成操作后请进行“下一步”操作。
					</br></br>
				</font>
			</p>
		</td>
	</tr>
</table>
	

