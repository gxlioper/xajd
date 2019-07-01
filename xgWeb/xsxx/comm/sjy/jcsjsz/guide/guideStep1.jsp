<%@ page language="java" contentType="text/html; charset=GBK"%>
<script language="javascript">
	//选择字段
	function clickStep1Zd(id,zdm){
	
		var chId = id.replace("ch_zd_","");
		
		if(chId == "allChoose"){
			var num =  document.getElementsByName("ch_zd").length;
			if($(id).checked){
				for(var i=0;i<num;i++){
					var obj = document.getElementsByName("ch_zd")[i];
					obj.checked = true;
				}
			}else{
				for(var i=0;i<num;i++){
					var obj = document.getElementsByName("ch_zd")[i];
					obj.checked = false;
				}
			}
		}
	}
	
	//验证步骤1
	function checkStep1(){
	
		var num =  document.getElementsByName("ch_zd").length;
		var flag = false;
		
		for(var i=0;i<num;i++){
			var obj = document.getElementsByName("ch_zd")[i];
			if(obj.checked){
				flag = true;
			}
		}
		
		return flag;
	}
	
	//创建字段名控件
	function creatZdm(){
		var num =  document.getElementsByName("ch_zd").length;
		
		for(var i=0;i<num;i++){
			var obj = document.getElementsByName("ch_zd")[i];
			if(obj.checked){
				var zdm = $("hid_zd_"+obj.value).value;

				if(zdm != "全选"){
					var tmp = document.createElement("input");
					tmp.type = "hidden";
					tmp.name = "ch_zdm";
					tmp.value = zdm;
					document.forms[0].appendChild(tmp);
				}
			}
		}
	}
</script>
<div id="div_step1_nr" style="width:100%;height:300px;overflow-x:hidden;overflow-y:auto;vertical-align: top;">
	<table>
		<logic:iterate name="dszZdList" id="zdtrRs">
			<tr>
				<logic:iterate name="zdtrRs" property="tdList" id="zdtdRs">
					<td width="15%">
						<logic:notEmpty name="zdtdRs" property="zd">
							<logic:equal name="zdtdRs" property="checked" value="yes">
								<input type="checkbox" name="ch_zd" onclick="clickStep1Zd(this.id,'${zdtdRs.zdm }');"
									id="ch_zd_${zdtdRs.zd}" value="${zdtdRs.zd}" checked="checked"/>	
							</logic:equal>
							<logic:notEqual name="zdtdRs" property="checked" value="yes">
								<input type="checkbox" name="ch_zd" onclick="clickStep1Zd(this.id,'${zdtdRs.zdm }');"
									id="ch_zd_${zdtdRs.zd}" value="${zdtdRs.zd}"/>
							</logic:notEqual>
						</logic:notEmpty>
						${zdtdRs.zdm }
						<input type="hidden" name="hid_zdm" id="hid_zd_${zdtdRs.zd}" value="${zdtdRs.zdm }"/>	
					</td>
				</logic:iterate>
			</tr>
		</logic:iterate>
	</table>
</div>
</br>
<table style="width:100%;" id="tb_sznr">
	<tr>
		<td>
			<!-- 具体字段设置end-->	
			<p>	<font color="blue">
				&nbsp;&nbsp;&nbsp;&nbsp;
				注：勾选希望进行设置的字段，按照向导步骤，一步步完成对其的相关设置。</br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					勾选“全部”，则将对全部字段进行设置，如果不希望如此操作，请勾选你希望操作的字段。</br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					如果没有勾选的字段，将不会出现在之后的设置中，请注意。</br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					每步设置的底部，都有详细的说明，该步设置是为什么操作，在何处控制等，请注意查看。</br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					如果你已经完全明白了各项设置的内容，可以关闭本页面，选择“字段设置”进行批量设置操作。</br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					完成操作后请进行“下一步”操作。
					</br></br>
				</font>
			</p>
		</td>
	</tr>
</table>	

