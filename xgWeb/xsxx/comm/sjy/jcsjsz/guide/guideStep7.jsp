<%@ page language="java" contentType="text/html; charset=GBK"%>
<script language="javascript">
	
	//����������ֶ�
	function clickStep7Zd(zd,showFlag){
		//��ȫѡ
		if(zd != "allChoose"){
			var div_id = "div_zdsz_"+zd;
			var lrxs_id = "ch_lrxs_"+zd;
			
			var lrxs = $(lrxs_id).value;
			var divHtml = "";

			if(lrxs == "�����б�" || lrxs == "��ѡ��" || lrxs == "�����ʽ"){
	
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
				divHtml+="value=\"����ѡ��\" checked=\"checked\"/>";
				divHtml+="����ѡ��";
				
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
	
	//ѡ���ֶ�
	function clickZdsz(zd,value){

		var lrxs_id = "text_lrxs_"+zd;
			
		$(lrxs_id).value = value;	
		$("rad_zd_sz_allChoose_notdo").checked = true;
	}
</script>

<div><b>��ѡ���ֶν�������,ֻ��¼����ʽΪ�������б�������ѡ�򡱣��������ʽ�����ֶβſ���������Դ��</b></div>
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
			<p><b>��ȷ����ѡ�ֶε���Դ��</b></p>
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
				ע�������ý�Ӧ���ڡ�ѧ����Ϣ������ģ���µ������޸�ѧ������,ȷ����¼����ʽ�����ݡ�</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				ֻ��¼����ʽΪ�����б���ѡ�������ʽ�ſ���������ؼ���ֵ��</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				�ؼ�ֵȡ�Ա�ģ�������õı�����������ò�����б�ȡ�ԡ�������ò�������ά�������ݡ�</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				���ĳ�ֶ�ֻ��һ����Դ����ôϵͳ�Զ�����ѡ��ñ�</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				���ĳ�ֶ��жദ��Դ����ôϵͳ�Զ�����ѡ���һ�ű��ɽ����޸ġ�</br>
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
			clickStep7Zd(obj.value,false);
		}
	}
}

setTimeout("onShow()",100);
</script>

