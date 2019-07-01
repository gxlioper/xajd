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
					
					var xgwz_id = "text_xgwz_"+obj_zd;

					if(obj_zd != "allChoose"){
						if(value == "是"){
							$("rad_zd_sz_"+obj_zd+"_yes").checked = true;
							$(xgwz_id).value = "是";	
						}else{
							$("rad_zd_sz_"+obj_zd+"_no").checked = true;
							$(xgwz_id).value = "否";
						}
					}
				}
			}
		}else{
			var xgwz_id = "text_xgwz_"+zd;

			if(value == "是"){
				$("rad_zd_sz_"+zd+"_yes").checked = true;
				$(xgwz_id).value = "是";	
			}else{
				$("rad_zd_sz_"+zd+"_no").checked = true;
				$(xgwz_id).value = "否";
			}
			
			$("rad_zd_sz_allChoose_notdo").checked = true;
		}
	}
	
	//验证步骤3
	function checkStep3(){
	
		var num =  document.getElementsByName("xgwz").length;
		var flag = true;
		
		for(var i=0;i<num;i++){
			var obj = document.getElementsByName("xgwz")[i];
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
			<p><b>请确定勾选字段是否以学工系统为准</b></p>
			<!-- 全部字段设置 -->
			<div id="div_zdsz_allChoose" style="">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rad_zd_sz_allChoose" id="rad_zd_sz_allChoose_notdo" value="notdo" checked="checked"/>	
				不处理
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rad_zd_sz_allChoose" onclick="clickZdsz('allChoose',this.value)" value="是"/>	
				是
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rad_zd_sz_allChoose" onclick="clickZdsz('allChoose',this.value)" value="否"/>	
				否
			</div>
			<!-- 全部字段设置 end-->
			
			<!-- 具体字段设置 -->	
			<logic:iterate name="kczzdList" id="zdszRs">	
				<div id="div_zdsz_${zdszRs.zd}" style="display: none">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="rad_zd_sz_${zdszRs.zd}" id="rad_zd_sz_${zdszRs.zd}_yes" onclick="clickZdsz('${zdszRs.zd}',this.value)" value="是"/>	
					是
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="rad_zd_sz_${zdszRs.zd}" id="rad_zd_sz_${zdszRs.zd}_no" onclick="clickZdsz('${zdszRs.zd}',this.value)" value="否"/>	
					否
					<input type="hidden" name="xgwz" id="text_xgwz_${zdszRs.zd}" value="${zdszRs.xgwz}">
				</div>
			</logic:iterate>
			</br>
			<!-- 具体字段设置end-->	
			<p style="width:100%;height:130px;overflow-x:hidden;overflow-y:auto;">
				<font color="blue">
				&nbsp;&nbsp;&nbsp;&nbsp;
				注：本设置将应用于“学生信息”功能模块下的字段以何处为标准。</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(1)当“学工”库不存在某学生，而“接口”库存在，则无论此处如何设置，该学生都将同步到“学工”库。</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(2)当“学工”库与“接口”库都存在某学生时，而某字段在双方的数据不同，那么，当该字段不以
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				“学工为准”的时候，将会同步“接口”库数据，覆盖掉学生库，当以“学工为准”的时候，则不管</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				“接口”库该字段的内容是什么，都不执行同步操作。</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(3)特别注意，当学号字段不以“学工为准”的情况下，“学生信息”模块下的“增加”功能，不可操作。</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				如果“学工”库存在某学生而“接口”库不存在，那么，该生将被清除出学生信息正式库，任何模块</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				都将不能操作该生的数据。</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				批量操作请勾选“全部”。</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				完成操作后请进行“下一步”操作。
				</font>
			</p>
		</td>
	</tr>
</table>
	

