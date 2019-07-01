<%@ page language="java" contentType="text/html; charset=GBK"%>
<script language="javascript">
	
	//点击第六步字段
	function clickStep7Zd(zd,showFlag){
		//非全选
		if(zd != "allChoose"){
			var div_id = "div_zdsz_"+zd;
			var lrxs_id = "ch_lrxs_"+zd;
			
			var lrxs = $(lrxs_id).value;
			var divHtml = "";

			if(lrxs == "下拉列表" || lrxs == "单选框" || lrxs == "特殊格式"){
	
				dwr.engine.setAsync(false);
				var tableName="xg_xsxx_zdlyb";
				var colList =["lyb","lybm"];
				var pk = "zd";
				var pkValue = zd;
				var query =" ";
				getOtherData.getTableListInfo(tableName, colList,pk, pkValue,query,function(data){
					if( data != null && data.length > 0){	
						var lyb = data[0].lyb;	
						var lybm = data[0].lybm;	
						divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";				
						for(var i=0;i<data.length;i++){
							divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
							divHtml+="<input type=\"radio\"";
							divHtml+="name=\"rad_zd_sz_"+zd+"\"";
							divHtml+="id=\"rad_zd_sz_"+zd+"_"+data[i].lyb+"\"";
							divHtml+="onclick=\"clickZdsz('"+zd+"',this.value)\"";
							divHtml+="value=\""+data[i].lyb+"\" checked=\"checked\"/>";
							divHtml+=data[i].lybm;
						}
						divHtml+="<input type=\"hidden\"";
						divHtml+="name=\"lyb\"";
						divHtml+="id=\"text_lyb_"+zd+"\"";
						divHtml+="value=\""+lyb+"\"/>";
						
						divHtml+="<input type=\"hidden\"";
						divHtml+="name=\"lybm\"";
						divHtml+="id=\"text_lybm_"+zd+"\"";
						divHtml+="value=\""+lybm+"\"/>";
					}		
				});
				
				dwr.engine.setAsync(true);
			}else{
				divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				divHtml+="<input type=\"radio\"";
				divHtml+="name=\"rad_zd_sz_"+zd+"\"";
				divHtml+="id=\"rad_zd_sz_"+zd+"_bkxz\"";
				divHtml+="onclick=\"clickZdsz('"+zd+"',this.value)\"";
				divHtml+="value=\"不可选择\" checked=\"checked\"/>";
				divHtml+="不可选择";
				
				divHtml+="<input type=\"hidden\"";
				divHtml+="name=\"lyb\"";
				divHtml+="id=\"text_lyb_"+zd+"\"";
				divHtml+="value=\"\"/>";
				
				divHtml+="<input type=\"hidden\"";
				divHtml+="name=\"lybm\"";
				divHtml+="id=\"text_lybm_"+zd+"\"";
				divHtml+="value=\"\"/>";
			}
			
			$(div_id).innerHTML = divHtml;
		}
		
		if(showFlag){
			clickStepZd(zd);
		}
	}
	
	//选择字段
	function clickZdsz(zd,value){

		var lrxs_id = "text_lrxs_"+zd;
			
		$(lrxs_id).value = value;	
		$("rad_zd_sz_allChoose_notdo").checked = true;
	}
</script>

<div><b>请选择字段进行设置,只有录入形式为“下拉列表”，“单选框”，“特殊格式”的字段才可以设置来源表</b></div>
<div id="div_step2_nr" style="width:100%;height:250px;overflow-x:hidden;overflow-y:auto;">
	<table style="width: 100%">
		<logic:iterate name="xszZdList" id="zdtrRs" indexId="trNum">
			<tr>
				<logic:iterate name="zdtrRs" property="tdList" id="zdtdRs" indexId="tdNum">
					<td width="15%">
						<logic:notEmpty name="zdtdRs" property="zd">
							<input type="radio" name="rad_zd" id="rad_zd_${zdtdRs.zd}" value="${zdtdRs.zd}" 
							<logic:equal name="trNum" value="0"><logic:equal name="tdNum" value="0">checked="checked"</logic:equal></logic:equal>
							onclick="clickStep7Zd('${zdtdRs.zd}','true')"/>	
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
			<p><b>请确定勾选字段的来源表</b></p>
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
				注：本设置将应用于“学生信息”功能模块下的增加修改学生操作,确定其录入形式的内容。</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				只有录入形式为下拉列表，单选框，特殊格式才可以设置其控件的值。</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				控件值取自本模块所设置的表，例：政治面貌下拉列表取自“政治面貌代码表”中维护的数据。</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				如果某字段只有一处来源表，那么系统自动帮你选择该表。</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				如果某字段有多处来源表，那么系统自动帮你选择第一张表，可进行修改。</br>
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
			clickStep7Zd(obj.value,false);
		}
	}
}

setTimeout("onShow()",100);
</script>

