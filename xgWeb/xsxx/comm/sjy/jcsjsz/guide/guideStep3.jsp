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
					
					var xgwz_id = "text_xgwz_"+obj_zd;

					if(obj_zd != "allChoose"){
						if(value == "��"){
							$("rad_zd_sz_"+obj_zd+"_yes").checked = true;
							$(xgwz_id).value = "��";	
						}else{
							$("rad_zd_sz_"+obj_zd+"_no").checked = true;
							$(xgwz_id).value = "��";
						}
					}
				}
			}
		}else{
			var xgwz_id = "text_xgwz_"+zd;

			if(value == "��"){
				$("rad_zd_sz_"+zd+"_yes").checked = true;
				$(xgwz_id).value = "��";	
			}else{
				$("rad_zd_sz_"+zd+"_no").checked = true;
				$(xgwz_id).value = "��";
			}
			
			$("rad_zd_sz_allChoose_notdo").checked = true;
		}
	}
	
	//��֤����3
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
			<p><b>��ȷ����ѡ�ֶ��Ƿ���ѧ��ϵͳΪ׼</b></p>
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
					<input type="hidden" name="xgwz" id="text_xgwz_${zdszRs.zd}" value="${zdszRs.xgwz}">
				</div>
			</logic:iterate>
			</br>
			<!-- �����ֶ�����end-->	
			<p style="width:100%;height:130px;overflow-x:hidden;overflow-y:auto;">
				<font color="blue">
				&nbsp;&nbsp;&nbsp;&nbsp;
				ע�������ý�Ӧ���ڡ�ѧ����Ϣ������ģ���µ��ֶ��Ժδ�Ϊ��׼��</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(1)����ѧ�����ⲻ����ĳѧ���������ӿڡ�����ڣ������۴˴�������ã���ѧ������ͬ������ѧ�����⡣</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(2)����ѧ�������롰�ӿڡ��ⶼ����ĳѧ��ʱ����ĳ�ֶ���˫�������ݲ�ͬ����ô�������ֶβ���
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				��ѧ��Ϊ׼����ʱ�򣬽���ͬ�����ӿڡ������ݣ����ǵ�ѧ���⣬���ԡ�ѧ��Ϊ׼����ʱ���򲻹�</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				���ӿڡ�����ֶε�������ʲô������ִ��ͬ��������</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(3)�ر�ע�⣬��ѧ���ֶβ��ԡ�ѧ��Ϊ׼��������£���ѧ����Ϣ��ģ���µġ����ӡ����ܣ����ɲ�����</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				�����ѧ���������ĳѧ�������ӿڡ��ⲻ���ڣ���ô���������������ѧ����Ϣ��ʽ�⣬�κ�ģ��</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				�������ܲ������������ݡ�</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				���������빴ѡ��ȫ������</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				��ɲ���������С���һ����������
				</font>
			</p>
		</td>
	</tr>
</table>
	

