<%@ page language="java" contentType="text/html; charset=GBK"%>
<script language="javascript">
	//ѡ���ֶ�
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
	
	//��֤����1
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
	
	//�����ֶ����ؼ�
	function creatZdm(){
		var num =  document.getElementsByName("ch_zd").length;
		
		for(var i=0;i<num;i++){
			var obj = document.getElementsByName("ch_zd")[i];
			if(obj.checked){
				var zdm = $("hid_zd_"+obj.value).value;

				if(zdm != "ȫѡ"){
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
			<!-- �����ֶ�����end-->	
			<p>	<font color="blue">
				&nbsp;&nbsp;&nbsp;&nbsp;
				ע����ѡϣ���������õ��ֶΣ������򵼲��裬һ������ɶ����������á�</br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					��ѡ��ȫ�������򽫶�ȫ���ֶν������ã������ϣ����˲������빴ѡ��ϣ���������ֶΡ�</br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					���û�й�ѡ���ֶΣ������������֮��������У���ע�⡣</br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					ÿ�����õĵײ���������ϸ��˵�����ò�������Ϊʲô�������ںδ����Ƶȣ���ע��鿴��</br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					������Ѿ���ȫ�����˸������õ����ݣ����Թرձ�ҳ�棬ѡ���ֶ����á������������ò�����</br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					��ɲ���������С���һ����������
					</br></br>
				</font>
			</p>
		</td>
	</tr>
</table>	

