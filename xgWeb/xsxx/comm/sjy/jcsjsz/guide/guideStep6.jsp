<%@ page language="java" contentType="text/html; charset=GBK"%>
<script language="javascript">
	
	//点击第六步字段
	function clickStep6Zd(zd,showFlag){
		//非全选
		if(zd != "allChoose"){
			var div_id = "div_zdsz_"+zd;
			var lyb_id = "ch_lybNum_"+zd;
			var divHtml = "";
			
			var bm_arr = ["nj","xydm","zydm","bjdm"];
			var qx_arr = ["syd","jg","hkszd"];
			
			var flag = true;
			
			//部门相关字段，仅支持三级联动的特殊格式
			for(var i=0;i<bm_arr.length;i++){
				if(zd == bm_arr[i]){
					
					divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
					divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
					divHtml+="<input type=\"radio\"";
					divHtml+="name=\"rad_zd_sz_"+zd+"\"";
					divHtml+="id=\"rad_zd_sz_"+zd+"_tsgs\"";
					divHtml+="onclick=\"clickZdsz('"+zd+"',this.value)\"";
					divHtml+="value=\"特殊格式\" checked=\"checked\"/>";
					divHtml+="特殊格式";
					
					divHtml+="<input type=\"hidden\"";
					divHtml+="name=\"lrxs\"";
					divHtml+="id=\"text_lrxs_"+zd+"\"";
					divHtml+="value=\"特殊格式\"/>";
					
					flag = false; 
				}
			}
			
			//区域相关字段，仅输入框和三级联动
			for(var i=0;i<qx_arr.length;i++){
				if(zd == qx_arr[i]){
					
					divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
					divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
					divHtml+="<input type=\"radio\"";
					divHtml+="name=\"rad_zd_sz_"+zd+"\"";
					divHtml+="id=\"rad_zd_sz_"+zd+"_srk\"";
					divHtml+="onclick=\"clickZdsz('"+zd+"',this.value)\"";
					divHtml+="value=\"输入框\" checked=\"checked\"/>";
					divHtml+="输入框";
					
					divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
					divHtml+="<input type=\"radio\"";
					divHtml+="name=\"rad_zd_sz_"+zd+"\"";
					divHtml+="id=\"rad_zd_sz_"+zd+"_tsgs\"";
					divHtml+="onclick=\"clickZdsz('"+zd+"',this.value)\"";
					divHtml+="value=\"特殊格式\" checked=\"checked\"/>";
					divHtml+="特殊格式";
					
					divHtml+="<input type=\"hidden\"";
					divHtml+="name=\"lrxs\"";
					divHtml+="id=\"text_lrxs_"+zd+"\"";
					divHtml+="value=\"特殊格式\"/>";
					
					flag = false; 			
				}
			}
			
			var lybNum = $(lyb_id).value;
			
			//来源表数量
			if(lybNum == 0 && flag){
			
				divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				divHtml+="<input type=\"radio\"";
				divHtml+="name=\"rad_zd_sz_"+zd+"\"";
				divHtml+="id=\"rad_zd_sz_"+zd+"_srk\"";
				divHtml+="onclick=\"clickZdsz('"+zd+"',this.value)\"";
				divHtml+="value=\"输入框\" checked=\"checked\"/>";
				divHtml+="输入框";
					
				divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				divHtml+="<input type=\"radio\"";
				divHtml+="name=\"rad_zd_sz_"+zd+"\"";
				divHtml+="id=\"rad_zd_sz_"+zd+"_sjgs\"";
				divHtml+="onclick=\"clickZdsz('"+zd+"',this.value)\"";
				divHtml+="value=\"时间格式\"/>";
				divHtml+="时间格式";
				
				divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				divHtml+="<input type=\"radio\"";
				divHtml+="name=\"rad_zd_sz_"+zd+"\"";
				divHtml+="id=\"rad_zd_sz_"+zd+"_wby\"";
				divHtml+="onclick=\"clickZdsz('"+zd+"',this.value)\"";
				divHtml+="value=\"文本域\"/>";
				divHtml+="文本域";
				
				divHtml+="<input type=\"hidden\"";
				divHtml+="name=\"lrxs\"";
				divHtml+="id=\"text_lrxs_"+zd+"\"";
				divHtml+="value=\"输入框\"/>";

			}else if(lybNum > 0 && flag){
			
				divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				divHtml+="<input type=\"radio\"";
				divHtml+="name=\"rad_zd_sz_"+zd+"\"";
				divHtml+="id=\"rad_zd_sz_"+zd+"_srk\"";
				divHtml+="onclick=\"clickZdsz('"+zd+"',this.value)\"";
				divHtml+="value=\"输入框\"/>";
				divHtml+="输入框";
				
				divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				divHtml+="<input type=\"radio\"";
				divHtml+="name=\"rad_zd_sz_"+zd+"\"";
				divHtml+="id=\"rad_zd_sz_"+zd+"_xllb\"";
				divHtml+="onclick=\"clickZdsz('"+zd+"',this.value)\"";
				divHtml+="value=\"下拉列表\" checked=\"checked\"/>";
				divHtml+="下拉列表";
				
				divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				divHtml+="<input type=\"radio\"";
				divHtml+="name=\"rad_zd_sz_"+zd+"\"";
				divHtml+="id=\"rad_zd_sz_"+zd+"_dxk\"";
				divHtml+="onclick=\"clickZdsz('"+zd+"',this.value)\"";
				divHtml+="value=\"单选框\"/>";
				divHtml+="单选框";
				
				divHtml+="<input type=\"hidden\"";
				divHtml+="name=\"lrxs\"";
				divHtml+="id=\"text_lrxs_"+zd+"\"";
				divHtml+="value=\"下拉列表\"/>";
			}
			
			$(div_id).innerHTML = divHtml;
		}
		
		if(showFlag){
			clickStepZd(zd);
		}
	}
	
	//选择字段
	function clickZdsz(zd,value){

		if(zd == "allChoose"){
			if(value != "notdo"){
				var div_num = $("tb_sznr").getElementsByTagName('div').length;
				for(var i=0;i<div_num;i++){
					var obj = $("tb_sznr").getElementsByTagName('div')[i];
					var obj_id = obj.id;
					var obj_zd = obj.id.replace("div_zdsz_","");
					
					var lrxs_id = "text_lrxs_"+obj_zd;

					if(obj_zd != "allChoose"){
						if(value == "输入框"){
							if($("rad_zd_sz_"+obj_zd+"_srk")){
								$("rad_zd_sz_"+obj_zd+"_srk").checked = true;
							}	
						}
						$(lrxs_id).value = value;	
					}
				}
			}
		}else{
			var lrxs_id = "text_lrxs_"+zd;
			
			$(lrxs_id).value = value;	
			$("rad_zd_sz_allChoose_notdo").checked = true;
		}
	}
	
	//验证步骤6
	function checkStep6(){
	
		var num =  document.getElementsByName("lrxs").length;
		var zdm = "";
		
		for(var i=0;i<num;i++){
			var obj = document.getElementsByName("lrxs")[i];
			if(obj.value ==  ""){
				var zdm_id = "ch_zd_"+obj.id.replace("text_lrxs_","");
				zdm = $(zdm_id).value;
				break;
			}
		}

		return zdm;
	}
</script>

<div><b>请选择字段进行设置，字段默认录入类型：特殊格式>下拉列表>输入框</b></div>
<div id="div_step2_nr" style="width:100%;height:250px;overflow-x:hidden;overflow-y:auto;">
	<table style="width: 100%">
		<logic:iterate name="xszZdList" id="zdtrRs" indexId="trNum">
			<tr>
				<logic:iterate name="zdtrRs" property="tdList" id="zdtdRs" indexId="tdNum">
					<td width="15%">
						<logic:notEmpty name="zdtdRs" property="zd">
							<input type="radio" name="rad_zd" id="rad_zd_${zdtdRs.zd}" value="${zdtdRs.zd}" 
							<logic:equal name="trNum" value="0"><logic:equal name="tdNum" value="0">checked="checked"</logic:equal></logic:equal>
							onclick="clickStep6Zd('${zdtdRs.zd}','true')"/>	
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
			<p><b>请确定勾选字段的录入形式</b></p>
			<!-- 全部字段设置 -->
			<div id="div_zdsz_allChoose" style="">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rad_zd_sz_allChoose" id="rad_zd_sz_allChoose_notdo" value="notdo" checked="checked"/>	
				不处理
			</div>
			<!-- 全部字段设置 end-->
			
			<!-- 具体字段设置 -->	
			<logic:iterate name="kczzdList" id="zdszRs">	
				<div id="div_zdsz_${zdszRs.zd}" style="display: none">

				</div>
			</logic:iterate>
			</br>
			<!-- 具体字段设置end-->	
			<p style="width:100%;height:130px;overflow-x:hidden;overflow-y:auto;">
				<font color="blue">
				&nbsp;&nbsp;&nbsp;&nbsp;
				注：本设置将应用于“学生信息”功能模块下的增加修改学生操作,确定其录入形式。</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(1)输入框：简单的单行文本输入。</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(2)文本域：多行文本入住。</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(3)时间格式：只读，只能够通过时间控件进行选择需要的时间。</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(4)下拉列表：必须在"数据来源表"有维护该字段，才会出现该选项，该页面以下拉列表展现。</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(5)单选框：必须在"数据来源表"有维护该字段，才会出现该选项，该页面以单选框展现。</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(6)特殊格式：只有个别字段有该选项，地区相关字段为“省市县”三级联动，部门相关字段为</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				“年级，<bean:message key="lable.xb" />，专业，班级”四级联动。</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				批量操作请勾选“全部”。</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				完成操作后请进行“下一步”操作。
				</font>
			</p>
		</td>
	</tr>
</table>
<script language="javascript">
//初始化
function onShow(){
	var num =  document.getElementsByName("rad_zd").length;
	for(var i=0;i<num;i++){
		var obj = document.getElementsByName("rad_zd")[i];
		if(obj.value !=  "allChoose"){
			clickStep6Zd(obj.value,false);
		}
	}
}

setTimeout("onShow()",100);
</script>

