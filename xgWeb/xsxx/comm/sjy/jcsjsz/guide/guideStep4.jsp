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
					
						var lrxz_id = "text_lrxz_"+obj_zd;
						
						if(value == "无限制"){
							$("rad_zd_sz_"+obj_zd+"_wxz").checked = true;
						}else if(value == "整数限制"){
							$("rad_zd_sz_"+obj_zd+"_zsxz").checked = true;
						}else if(value == "数字限制(可带小数)"){
							$("rad_zd_sz_"+obj_zd+"_szxz").checked = true;
						}else if(value == "英数字限制"){
							$("rad_zd_sz_"+obj_zd+"_yszxz").checked = true;
						}else if(value == "中文限制"){
							$("rad_zd_sz_"+obj_zd+"_zwxz").checked = true;
						}
						$(lrxz_id).value = value;
					}
				}
			}
		}else{
			var lrxz_id = "text_lrxz_"+zd;

			if(value == "无限制"){
				$("rad_zd_sz_"+zd+"_wxz").checked = true;
			}else if(value == "整数限制"){
				$("rad_zd_sz_"+zd+"_zsxz").checked = true;
			}else if(value == "数字限制(可带小数)"){
				$("rad_zd_sz_"+zd+"_szxz").checked = true;
			}else if(value == "英数字限制"){
				$("rad_zd_sz_"+zd+"_yszxz").checked = true;
			}else if(value == "中文限制"){
				$("rad_zd_sz_"+zd+"_zwxz").checked = true;
			}
			$(lrxz_id).value = value;
			
			$("rad_zd_sz_allChoose_notdo").checked = true;
		}
	}
	
	//验证步骤4
	function checkStep4(){
	
		var num =  document.getElementsByName("lrxz").length;
		var flag = true;
		
		for(var i=0;i<num;i++){
			var obj = document.getElementsByName("lrxz")[i];
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
			<p><b>请确定勾选字段的录入限制</b></p>
			<!-- 全部字段设置 -->
			<div id="div_zdsz_allChoose" style="">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rad_zd_sz_allChoose" id="rad_zd_sz_allChoose_notdo" value="notdo" checked="checked"/>	
				不处理
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rad_zd_sz_allChoose" onclick="clickZdsz('allChoose',this.value)" value="无限制"/>	
				无限制
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rad_zd_sz_allChoose" onclick="clickZdsz('allChoose',this.value)" value="整数限制"/>	
				整数限制
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rad_zd_sz_allChoose" onclick="clickZdsz('allChoose',this.value)" value="数字限制(可带小数)"/>	
				数字限制(可带小数)
				</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rad_zd_sz_allChoose" onclick="clickZdsz('allChoose',this.value)" value="英数字限制"/>	
				英数字限制
				&nbsp;
				<input type="radio" name="rad_zd_sz_allChoose" onclick="clickZdsz('allChoose',this.value)" value="中文限制"/>	
				中文限制
			</div>
			<!-- 全部字段设置 end-->
			
			<!-- 具体字段设置 -->	
			<logic:iterate name="kczzdList" id="zdszRs">	
				<div id="div_zdsz_${zdszRs.zd}" style="display: none">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="rad_zd_sz_${zdszRs.zd}" id="rad_zd_sz_${zdszRs.zd}_wxz" onclick="clickZdsz('${zdszRs.zd}',this.value)" value="无限制"/>	
					无限制
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="rad_zd_sz_${zdszRs.zd}" id="rad_zd_sz_${zdszRs.zd}_zsxz" onclick="clickZdsz('${zdszRs.zd}',this.value)" value="整数限制"/>	
					整数限制
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="rad_zd_sz_${zdszRs.zd}" id="rad_zd_sz_${zdszRs.zd}_szxz" onclick="clickZdsz('${zdszRs.zd}',this.value)" value="数字限制(可带小数)"/>	
					数字限制(可带小数)
					</br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="rad_zd_sz_${zdszRs.zd}" id="rad_zd_sz_${zdszRs.zd}_yszxz" onclick="clickZdsz('${zdszRs.zd}',this.value)" value="英数字限制"/>	
					英数字限制
					&nbsp;
					<input type="radio" name="rad_zd_sz_${zdszRs.zd}" id="rad_zd_sz_${zdszRs.zd}_zwxz" onclick="clickZdsz('${zdszRs.zd}',this.value)" value="中文限制"/>	
					中文限制
					<input type="hidden" name="lrxz" id="text_lrxz_${zdszRs.zd}" value="${zdszRs.lrxz}">
				</div>
			</logic:iterate>
			</br>
			<!-- 具体字段设置end-->	
			<p style="width:100%;height:100px;overflow-x:hidden;overflow-y:auto;">
				<font color="blue">
				&nbsp;&nbsp;&nbsp;&nbsp;
				注：本设置将应用于“学生信息”功能模块下的增加修改学生操作,控制该字段可以录入何种形式的值。</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(1)无限制：在不超过字段上限的前提下，随意录入，不做任何控制。</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(2)整数限制：只能录入整数，比如："8","10"等等。</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(3)数字限制：只能录入数字，可以录入小数，比如："1"，"2"，"3.5"等等。</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(4)英数字限制：只能录入数字与英文，小数不可以输入，比如："1"，"2"，"3A"等等。</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(5)中文限制：只能录入汉字，比如："张飞"，"黄药师","智深大师"等等。</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				完成操作后请进行“下一步”操作。
				</font>
			</p>
		</td>
	</tr>
</table>
	

