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
					
						var lrxz_id = "text_lrxz_"+obj_zd;
						
						if(value == "������"){
							$("rad_zd_sz_"+obj_zd+"_wxz").checked = true;
						}else if(value == "��������"){
							$("rad_zd_sz_"+obj_zd+"_zsxz").checked = true;
						}else if(value == "��������(�ɴ�С��)"){
							$("rad_zd_sz_"+obj_zd+"_szxz").checked = true;
						}else if(value == "Ӣ��������"){
							$("rad_zd_sz_"+obj_zd+"_yszxz").checked = true;
						}else if(value == "��������"){
							$("rad_zd_sz_"+obj_zd+"_zwxz").checked = true;
						}
						$(lrxz_id).value = value;
					}
				}
			}
		}else{
			var lrxz_id = "text_lrxz_"+zd;

			if(value == "������"){
				$("rad_zd_sz_"+zd+"_wxz").checked = true;
			}else if(value == "��������"){
				$("rad_zd_sz_"+zd+"_zsxz").checked = true;
			}else if(value == "��������(�ɴ�С��)"){
				$("rad_zd_sz_"+zd+"_szxz").checked = true;
			}else if(value == "Ӣ��������"){
				$("rad_zd_sz_"+zd+"_yszxz").checked = true;
			}else if(value == "��������"){
				$("rad_zd_sz_"+zd+"_zwxz").checked = true;
			}
			$(lrxz_id).value = value;
			
			$("rad_zd_sz_allChoose_notdo").checked = true;
		}
	}
	
	//��֤����4
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
			<p><b>��ȷ����ѡ�ֶε�¼������</b></p>
			<!-- ȫ���ֶ����� -->
			<div id="div_zdsz_allChoose" style="">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rad_zd_sz_allChoose" id="rad_zd_sz_allChoose_notdo" value="notdo" checked="checked"/>	
				������
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rad_zd_sz_allChoose" onclick="clickZdsz('allChoose',this.value)" value="������"/>	
				������
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rad_zd_sz_allChoose" onclick="clickZdsz('allChoose',this.value)" value="��������"/>	
				��������
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rad_zd_sz_allChoose" onclick="clickZdsz('allChoose',this.value)" value="��������(�ɴ�С��)"/>	
				��������(�ɴ�С��)
				</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rad_zd_sz_allChoose" onclick="clickZdsz('allChoose',this.value)" value="Ӣ��������"/>	
				Ӣ��������
				&nbsp;
				<input type="radio" name="rad_zd_sz_allChoose" onclick="clickZdsz('allChoose',this.value)" value="��������"/>	
				��������
			</div>
			<!-- ȫ���ֶ����� end-->
			
			<!-- �����ֶ����� -->	
			<logic:iterate name="kczzdList" id="zdszRs">	
				<div id="div_zdsz_${zdszRs.zd}" style="display: none">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="rad_zd_sz_${zdszRs.zd}" id="rad_zd_sz_${zdszRs.zd}_wxz" onclick="clickZdsz('${zdszRs.zd}',this.value)" value="������"/>	
					������
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="rad_zd_sz_${zdszRs.zd}" id="rad_zd_sz_${zdszRs.zd}_zsxz" onclick="clickZdsz('${zdszRs.zd}',this.value)" value="��������"/>	
					��������
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="rad_zd_sz_${zdszRs.zd}" id="rad_zd_sz_${zdszRs.zd}_szxz" onclick="clickZdsz('${zdszRs.zd}',this.value)" value="��������(�ɴ�С��)"/>	
					��������(�ɴ�С��)
					</br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="rad_zd_sz_${zdszRs.zd}" id="rad_zd_sz_${zdszRs.zd}_yszxz" onclick="clickZdsz('${zdszRs.zd}',this.value)" value="Ӣ��������"/>	
					Ӣ��������
					&nbsp;
					<input type="radio" name="rad_zd_sz_${zdszRs.zd}" id="rad_zd_sz_${zdszRs.zd}_zwxz" onclick="clickZdsz('${zdszRs.zd}',this.value)" value="��������"/>	
					��������
					<input type="hidden" name="lrxz" id="text_lrxz_${zdszRs.zd}" value="${zdszRs.lrxz}">
				</div>
			</logic:iterate>
			</br>
			<!-- �����ֶ�����end-->	
			<p style="width:100%;height:100px;overflow-x:hidden;overflow-y:auto;">
				<font color="blue">
				&nbsp;&nbsp;&nbsp;&nbsp;
				ע�������ý�Ӧ���ڡ�ѧ����Ϣ������ģ���µ������޸�ѧ������,���Ƹ��ֶο���¼�������ʽ��ֵ��</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(1)�����ƣ��ڲ������ֶ����޵�ǰ���£�����¼�룬�����κο��ơ�</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(2)�������ƣ�ֻ��¼�����������磺"8","10"�ȵȡ�</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(3)�������ƣ�ֻ��¼�����֣�����¼��С�������磺"1"��"2"��"3.5"�ȵȡ�</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(4)Ӣ�������ƣ�ֻ��¼��������Ӣ�ģ�С�����������룬���磺"1"��"2"��"3A"�ȵȡ�</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(5)�������ƣ�ֻ��¼�뺺�֣����磺"�ŷ�"��"��ҩʦ","�����ʦ"�ȵȡ�</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				��ɲ���������С���һ����������
				</font>
			</p>
		</td>
	</tr>
</table>
	

