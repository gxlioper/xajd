<%@ page language="java" contentType="text/html; charset=GBK"%>
<script language="javascript">
	
	//ѡ���ֶ�
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
						if(value == "����Ϊ��"){
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

			if(value == "����Ϊ��"){
				$("rad_zd_sz_"+zd+"_yes").checked = true;
				
			}else{
				$("rad_zd_sz_"+zd+"_no").checked = true;
			}
			
			$(wkxz_id).value = value;	
			$("rad_zd_sz_allChoose_notdo").checked = true;
		}
	}
	
	//��֤����5
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
			<p><b>��ȷ����ѡ�ֶ���ά��ʱ�ɷ�Ϊ��</b></p>
			<!-- ȫ���ֶ����� -->
			<div id="div_zdsz_allChoose" style="">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rad_zd_sz_allChoose" id="rad_zd_sz_allChoose_notdo" value="notdo" checked="checked"/>	
				������
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rad_zd_sz_allChoose" onclick="clickZdsz('allChoose',this.value)" value="����Ϊ��"/>	
				����Ϊ��
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rad_zd_sz_allChoose" onclick="clickZdsz('allChoose',this.value)" value="����Ϊ��"/>	
				����Ϊ��
			</div>
			<!-- ȫ���ֶ����� end-->
			
			<!-- �����ֶ����� -->	
			<logic:iterate name="kczzdList" id="zdszRs">	
				<div id="div_zdsz_${zdszRs.zd}" style="display: none">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="rad_zd_sz_${zdszRs.zd}" id="rad_zd_sz_${zdszRs.zd}_yes" onclick="clickZdsz('${zdszRs.zd}',this.value)" value="����Ϊ��"/>	
					����Ϊ��
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="rad_zd_sz_${zdszRs.zd}" id="rad_zd_sz_${zdszRs.zd}_no" onclick="clickZdsz('${zdszRs.zd}',this.value)" value="����Ϊ��"/>	
					����Ϊ��
					<input type="hidden" name="wkxz" id="text_wkxz_${zdszRs.zd}" value="${zdszRs.wkxz}">
				</div>
			</logic:iterate>
			</br>
			<!-- �����ֶ�����end-->	
			<p style="width:100%;height:130px;overflow-x:hidden;overflow-y:auto;">
				<font color="blue">
				&nbsp;&nbsp;&nbsp;&nbsp;
				ע�������ý�Ӧ���ڡ�ѧ����Ϣ������ģ���µ������޸�ѧ������,���Ƹ��ֶο���¼��ɷ�Ϊ�ա�</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(1)����Ϊ�գ����ֶη���Ҫ�ֶΣ����Բ�ά�����ݡ�</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(2)����Ϊ�գ����ֶ�Ϊ��Ҫ�ֶΣ�����¼�����ݣ�����Ϊ�ա�</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				���������빴ѡ��ȫ������</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				��ɲ���������С���һ����������
				</font>
			</p>
		</td>
	</tr>
</table>
	

