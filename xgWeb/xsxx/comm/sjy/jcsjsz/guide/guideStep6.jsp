<%@ page language="java" contentType="text/html; charset=GBK"%>
<script language="javascript">
	
	//����������ֶ�
	function clickStep6Zd(zd,showFlag){
		//��ȫѡ
		if(zd != "allChoose"){
			var div_id = "div_zdsz_"+zd;
			var lyb_id = "ch_lybNum_"+zd;
			var divHtml = "";
			
			var bm_arr = ["nj","xydm","zydm","bjdm"];
			var qx_arr = ["syd","jg","hkszd"];
			
			var flag = true;
			
			//��������ֶΣ���֧�����������������ʽ
			for(var i=0;i<bm_arr.length;i++){
				if(zd == bm_arr[i]){
					
					divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
					divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
					divHtml+="<input type=\"radio\"";
					divHtml+="name=\"rad_zd_sz_"+zd+"\"";
					divHtml+="id=\"rad_zd_sz_"+zd+"_tsgs\"";
					divHtml+="onclick=\"clickZdsz('"+zd+"',this.value)\"";
					divHtml+="value=\"�����ʽ\" checked=\"checked\"/>";
					divHtml+="�����ʽ";
					
					divHtml+="<input type=\"hidden\"";
					divHtml+="name=\"lrxs\"";
					divHtml+="id=\"text_lrxs_"+zd+"\"";
					divHtml+="value=\"�����ʽ\"/>";
					
					flag = false; 
				}
			}
			
			//��������ֶΣ�����������������
			for(var i=0;i<qx_arr.length;i++){
				if(zd == qx_arr[i]){
					
					divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
					divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
					divHtml+="<input type=\"radio\"";
					divHtml+="name=\"rad_zd_sz_"+zd+"\"";
					divHtml+="id=\"rad_zd_sz_"+zd+"_srk\"";
					divHtml+="onclick=\"clickZdsz('"+zd+"',this.value)\"";
					divHtml+="value=\"�����\" checked=\"checked\"/>";
					divHtml+="�����";
					
					divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
					divHtml+="<input type=\"radio\"";
					divHtml+="name=\"rad_zd_sz_"+zd+"\"";
					divHtml+="id=\"rad_zd_sz_"+zd+"_tsgs\"";
					divHtml+="onclick=\"clickZdsz('"+zd+"',this.value)\"";
					divHtml+="value=\"�����ʽ\" checked=\"checked\"/>";
					divHtml+="�����ʽ";
					
					divHtml+="<input type=\"hidden\"";
					divHtml+="name=\"lrxs\"";
					divHtml+="id=\"text_lrxs_"+zd+"\"";
					divHtml+="value=\"�����ʽ\"/>";
					
					flag = false; 			
				}
			}
			
			var lybNum = $(lyb_id).value;
			
			//��Դ������
			if(lybNum == 0 && flag){
			
				divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				divHtml+="<input type=\"radio\"";
				divHtml+="name=\"rad_zd_sz_"+zd+"\"";
				divHtml+="id=\"rad_zd_sz_"+zd+"_srk\"";
				divHtml+="onclick=\"clickZdsz('"+zd+"',this.value)\"";
				divHtml+="value=\"�����\" checked=\"checked\"/>";
				divHtml+="�����";
					
				divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				divHtml+="<input type=\"radio\"";
				divHtml+="name=\"rad_zd_sz_"+zd+"\"";
				divHtml+="id=\"rad_zd_sz_"+zd+"_sjgs\"";
				divHtml+="onclick=\"clickZdsz('"+zd+"',this.value)\"";
				divHtml+="value=\"ʱ���ʽ\"/>";
				divHtml+="ʱ���ʽ";
				
				divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				divHtml+="<input type=\"radio\"";
				divHtml+="name=\"rad_zd_sz_"+zd+"\"";
				divHtml+="id=\"rad_zd_sz_"+zd+"_wby\"";
				divHtml+="onclick=\"clickZdsz('"+zd+"',this.value)\"";
				divHtml+="value=\"�ı���\"/>";
				divHtml+="�ı���";
				
				divHtml+="<input type=\"hidden\"";
				divHtml+="name=\"lrxs\"";
				divHtml+="id=\"text_lrxs_"+zd+"\"";
				divHtml+="value=\"�����\"/>";

			}else if(lybNum > 0 && flag){
			
				divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				divHtml+="<input type=\"radio\"";
				divHtml+="name=\"rad_zd_sz_"+zd+"\"";
				divHtml+="id=\"rad_zd_sz_"+zd+"_srk\"";
				divHtml+="onclick=\"clickZdsz('"+zd+"',this.value)\"";
				divHtml+="value=\"�����\"/>";
				divHtml+="�����";
				
				divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				divHtml+="<input type=\"radio\"";
				divHtml+="name=\"rad_zd_sz_"+zd+"\"";
				divHtml+="id=\"rad_zd_sz_"+zd+"_xllb\"";
				divHtml+="onclick=\"clickZdsz('"+zd+"',this.value)\"";
				divHtml+="value=\"�����б�\" checked=\"checked\"/>";
				divHtml+="�����б�";
				
				divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				divHtml+="<input type=\"radio\"";
				divHtml+="name=\"rad_zd_sz_"+zd+"\"";
				divHtml+="id=\"rad_zd_sz_"+zd+"_dxk\"";
				divHtml+="onclick=\"clickZdsz('"+zd+"',this.value)\"";
				divHtml+="value=\"��ѡ��\"/>";
				divHtml+="��ѡ��";
				
				divHtml+="<input type=\"hidden\"";
				divHtml+="name=\"lrxs\"";
				divHtml+="id=\"text_lrxs_"+zd+"\"";
				divHtml+="value=\"�����б�\"/>";
			}
			
			$(div_id).innerHTML = divHtml;
		}
		
		if(showFlag){
			clickStepZd(zd);
		}
	}
	
	//ѡ���ֶ�
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
						if(value == "�����"){
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
	
	//��֤����6
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

<div><b>��ѡ���ֶν������ã��ֶ�Ĭ��¼�����ͣ������ʽ>�����б�>�����</b></div>
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
			<p><b>��ȷ����ѡ�ֶε�¼����ʽ</b></p>
			<!-- ȫ���ֶ����� -->
			<div id="div_zdsz_allChoose" style="">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rad_zd_sz_allChoose" id="rad_zd_sz_allChoose_notdo" value="notdo" checked="checked"/>	
				������
			</div>
			<!-- ȫ���ֶ����� end-->
			
			<!-- �����ֶ����� -->	
			<logic:iterate name="kczzdList" id="zdszRs">	
				<div id="div_zdsz_${zdszRs.zd}" style="display: none">

				</div>
			</logic:iterate>
			</br>
			<!-- �����ֶ�����end-->	
			<p style="width:100%;height:130px;overflow-x:hidden;overflow-y:auto;">
				<font color="blue">
				&nbsp;&nbsp;&nbsp;&nbsp;
				ע�������ý�Ӧ���ڡ�ѧ����Ϣ������ģ���µ������޸�ѧ������,ȷ����¼����ʽ��</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(1)����򣺼򵥵ĵ����ı����롣</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(2)�ı��򣺶����ı���ס��</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(3)ʱ���ʽ��ֻ����ֻ�ܹ�ͨ��ʱ��ؼ�����ѡ����Ҫ��ʱ�䡣</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(4)�����б�������"������Դ��"��ά�����ֶΣ��Ż���ָ�ѡ���ҳ���������б�չ�֡�</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(5)��ѡ�򣺱�����"������Դ��"��ά�����ֶΣ��Ż���ָ�ѡ���ҳ���Ե�ѡ��չ�֡�</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				(6)�����ʽ��ֻ�и����ֶ��и�ѡ���������ֶ�Ϊ��ʡ���ء�������������������ֶ�Ϊ</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				���꼶��<bean:message key="lable.xb" />��רҵ���༶���ļ�������</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				���������빴ѡ��ȫ������</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				��ɲ���������С���һ����������
				</font>
			</p>
		</td>
	</tr>
</table>
<script language="javascript">
//��ʼ��
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

