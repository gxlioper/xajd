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
					
					var sfqy_id = "text_sfqy_"+obj_zd;

					if(obj_zd != "allChoose"){
						if(value == "��"){
							$("rad_zd_sz_"+obj_zd+"_yes").checked = true;
							$(sfqy_id).value = "��";	
						}else{
							$("rad_zd_sz_"+obj_zd+"_no").checked = true;
							$(sfqy_id).value = "��";
						}
					}
				}
			}
		}else{
			var sfqy_id = "text_sfqy_"+zd;
			$(sfqy_id).value = value;	
			$("rad_zd_sz_allChoose_notdo").checked = true;
		}
	}
	
	//��֤����8
	function checkStep8(){
	
		var num =  document.getElementsByName("sfqy").length;
		var flag = true;
		
		for(var i=0;i<num;i++){
			var obj = document.getElementsByName("sfqy")[i];
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
			<p><b>��ȷ����ѡ�ֶ��Ƿ�����</b></p>
			<!-- ȫ���ֶ����� -->
			<div id="div_zdsz_allChoose" style="">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rad_zd_sz_allChoose" id="rad_zd_sz_allChoose_notdo" value="notdo" checked="checked"/>	
				������
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rad_zd_sz_allChoose" onclick="clickZdsz('allChoose',this.value)" value="��"/>	
				��
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rad_zd_sz_allChoose" onclick="clickZdsz('allChoose',this.value)" value="��"/>	
				��
			</div>
			<!-- ȫ���ֶ����� end-->
			
			<!-- �����ֶ����� -->	
			<logic:iterate name="kczzdList" id="zdszRs">	
				<div id="div_zdsz_${zdszRs.zd}" style="display: none">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="rad_zd_sz_${zdszRs.zd}" id="rad_zd_sz_${zdszRs.zd}_yes" onclick="clickZdsz('${zdszRs.zd}',this.value)" value="��"/>	
					��
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="rad_zd_sz_${zdszRs.zd}" id="rad_zd_sz_${zdszRs.zd}_no" onclick="clickZdsz('${zdszRs.zd}',this.value)" value="��"/>	
					��
					<input type="hidden" name="sfqy" id="text_sfqy_${zdszRs.zd}" value="${zdszRs.sfqy}">
				</div>
			</logic:iterate>
			</br>
			<!-- �����ֶ�����end-->	
			<p style="width:100%;height:130px;overflow-x:hidden;overflow-y:auto;">
				<font color="blue">
				&nbsp;&nbsp;&nbsp;&nbsp;
				ע�������ý�Ӧ���ڡ�ѧ����Ϣ�������޸Ĺ��ܣ��Լ���ҳ�����á����ܡ�</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(1)��ĳ�ֶ�����ʱ�����ֶο��Ա�Ӧ���ڡ�ҳ�����á�����ģ�飬�ɽ�������Ű棬ʹ���ڡ�ѧ����Ϣ��</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				ģ�����ά����չ��</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(2)��ĳ�ֶβ�����ʱ�����ֶβ��ɱ�Ӧ���ڡ�ҳ�����á�����ģ�飬�ڡ�ѧ����Ϣ��ά��ģ�飬���ɼ���</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				���������빴ѡ��ȫ������</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				��ɲ���������С���һ����������
				</font>
			</p>
		</td>
	</tr>
</table>
	

