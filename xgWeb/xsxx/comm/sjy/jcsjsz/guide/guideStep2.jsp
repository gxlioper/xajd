<%@ page language="java" contentType="text/html; charset=GBK"%>
<script language="javascript">
	
	//ѡ���ֶ�
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
	
	//������ʾ����
	function setXsmc(zd,value){
		var id = "text_xsmc_"+zd;
		$(id).value = value;
	}
	
	//��֤����2
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

<div><b>��ѡ���ֶν�������,���������빴ѡ��ȫ����</b></div>
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
			<p><b>��ȷ����ѡ�ֶε�ҳ����ʾ</b></p>
			<!-- ȫ���ֶ����� -->
			<div id="div_zdsz_allChoose" style="">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rad_zd_sz_allChoose" id="rad_zd_sz_allChoose_notdo" onclick="clickZdsz('allChoose',this.value);" value="notdo" checked="checked"/>	
				������
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rad_zd_sz_allChoose" onclick="clickZdsz('allChoose',this.value);" value="same"/>	
				ͬ�ֶ���
			</div>
			<!-- ȫ���ֶ����� end-->
			
			<!-- �����ֶ����� -->	
			<logic:iterate name="kczzdList" id="zdszRs">	
				<div id="div_zdsz_${zdszRs.zd}" style="display: none">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="rad_zd_sz_${zdszRs.zd}" id="rad_zd_sz_${zdszRs.zd}_same" onclick="clickZdsz('${zdszRs.zd}',this.value);" value="same"/>	
					ͬ�ֶ���
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="rad_zd_sz_${zdszRs.zd}" onclick="clickZdsz('${zdszRs.zd}',this.value);" value="other"/>	
					����
					<input type="text" id="text_${zdszRs.zd}" style="width: 100px"
						onblur="setXsmc('${zdszRs.zd}',this.value)" disabled="disabled">
					<input type="hidden" name="xsmc" id="text_xsmc_${zdszRs.zd}" value="${zdszRs.xsmc}">
				</div>
			</logic:iterate>
			</br>
			<!-- �����ֶ�����end-->	
			<p>	<font color="blue">
				&nbsp;&nbsp;&nbsp;&nbsp;
				ע�������ý�Ӧ���ڡ�ѧ����Ϣ������ģ���µġ�������Ϣά����ҳ����ֶ�չʾ������ĳ�ֶ�����Ϊ��ѧ�š���</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					��ѧ����Ϣҳ��Ҳϣ��չ��Ϊ��ѧ�š�����ô���뽫���ֶ�����Ϊ��ͬ�ֶ����������ĳ�ֶ�����Ϊ��<bean:message key="lable.xb" />����</br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					��ϣ��չ�ֵ�����ȴ��<bean:message key="lable.xb" />����ô������ѡ�и��ֶε�ǰ���£�ѡ���������������������ά��ϣ��</br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					չ�ֵ����ݡ�</br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					���������빴ѡ��ȫ����������ֻ��ѡ��ͬ�ֶ�������</br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					��ɲ���������С���һ����������
					</br></br>
				</font>
			</p>
		</td>
	</tr>
</table>
	

