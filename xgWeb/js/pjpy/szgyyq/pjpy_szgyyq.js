//����������
function hiddenOtherDiv(xmdm){

	var allxm = ["szyq_dshdjzb","szyq_yybdjzb","szyq_ivtltb","szyq_xthddjb","szyc_zznlfzb","szyc_shsjfzb"];
	
	for(var i=0;i<allxm.length;i++){
	
		var div_id = "div_"+allxm[i];
		
		if(xmdm == allxm[i]){
			if($(div_id)){
				$(div_id).style.display="";
			}
		}else {
			if($(div_id)){
				$(div_id).style.display="none";
			}
		}
	}
}

//ѡ��ȫ��
function clickAll(xmdm){

	var cb_id = "cb_"+xmdm;
	
	var num =  jQuery("input[name="+cb_id+"]").length;
			
	for(var i=0;i<num;i++){
		var obj = jQuery("input[name="+cb_id+"]")[i];
		if($(cb_id).checked){
			obj.checked = true;
		}else{
			obj.checked = false;
		}
	}
}

//��������������ʾDiv
function createFssqDiv(){
	
	//��Ŀ����
	var xmdm = $("shxm").value;
	//Div
	var div_id = "div_"+xmdm;
	var div_fssq_id = "div_fssq_"+xmdm;
	var max_text_id = "max_"+xmdm;
	hiddenOtherDiv(xmdm);
	
	if(!$(div_id)){
	
		if(xmdm == "szyq_dshdjzb"){//����
		
			var divHtml = $("div_fssq").innerHTML;
			
				divHtml+="<div id=\""+div_id+"\">";
				divHtml+="<input type=\"hidden\" id=\""+max_text_id+"\" value=\"0\" />";
				divHtml+="<table align=\"center\" style=\"width: 100%\">";
				divHtml+="<thead>";
				divHtml+="<tr>";
				divHtml+="<td width=\"5px\"><input type='checkbox' id=\"cb_"+xmdm+"\" name=\"cb_all_"+xmdm+"\" onclick=\"clickAll('"+xmdm+"')\"/></td>";
				divHtml+="<td width=\"15%\">����</td>";
				divHtml+="<td width=\"15%\">��������</td>";
				divHtml+="<td>�����ĵ�<font color=\"red\">(������150��)</font></td>";
				divHtml+="<td width=\"15%\">�Ƿ��</td>";
				divHtml+="<td width=\"15%\">����ӷ�</td>";
				divHtml+="</tr>";
				divHtml+="</thead>";
				divHtml+="</table>";
				divHtml+="<div id=\""+div_fssq_id+"\" style=\"height:330px;overflow-x:auto;overflow-y:auto;\">";
				divHtml+="</div>";
				divHtml+="</div>";
			
			$("div_fssq").innerHTML = "";
			$("div_fssq").innerHTML = divHtml;
			
		}else if(xmdm == "szyq_yybdjzb"){//���Ա��
		
			var divHtml = $("div_fssq").innerHTML;
			
				divHtml+="<div id=\""+div_id+"\">";
				divHtml+="<input type=\"hidden\" id=\""+max_text_id+"\" value=\"0\" />";
				divHtml+="<table align=\"center\" style=\"width: 100%\">";
				divHtml+="<thead>";
				divHtml+="<tr>";
				divHtml+="<td width=\"5px\"><input type='checkbox' id=\"cb_"+xmdm+"\" name=\"cb_all_"+xmdm+"\" onclick=\"clickAll('"+xmdm+"')\"/></td>";		
				divHtml+="<td>���Ա������<font color=\"red\">(������150��)</font></td>";
				divHtml+="<td width=\"15%\">����</td>";
				divHtml+="<td width=\"15%\">����ӷ�</td>";
				divHtml+="</tr>";
				divHtml+="</thead>";
				divHtml+="</table>";
				divHtml+="<div id=\""+div_fssq_id+"\" style=\"height:330px;overflow-x:auto;overflow-y:auto;\">";
				divHtml+="</div>";
				divHtml+="</div>";
			
			$("div_fssq").innerHTML = "";
			$("div_fssq").innerHTML = divHtml;
			
		}else if(xmdm == "szyq_ivtltb"){//IVT��̳
		
			var divHtml = $("div_fssq").innerHTML;
			
				divHtml+="<div id=\""+div_id+"\">";
				divHtml+="<input type=\"hidden\" id=\""+max_text_id+"\" value=\"0\" />";
				divHtml+="<table align=\"center\" style=\"width: 100%\">";
				divHtml+="<thead>";
				divHtml+="<tr>";
				divHtml+="<td width=\"5px\"><input type='checkbox' id=\"cb_"+xmdm+"\" name=\"cb_all_"+xmdm+"\" onclick=\"clickAll('"+xmdm+"')\"/></td>";
				divHtml+="<td width=\"25%\">������Ŀ</td>";
				divHtml+="<td width=\"15%\">����</td>";
				divHtml+="<td width=\"\">�����Ǽ�</td>";
				divHtml+="<td width=\"20%\">�����Ǽ�</td>";
				divHtml+="<td width=\"15%\">����ӷ�</td>";
				divHtml+="</tr>";
				divHtml+="</thead>";
				divHtml+="</table>";
				divHtml+="<div id=\""+div_fssq_id+"\" style=\"height:330px;overflow-x:auto;overflow-y:auto;\">";
				divHtml+="</div>";
				divHtml+="</div>";
			
			$("div_fssq").innerHTML = "";
			$("div_fssq").innerHTML = divHtml;
			
		}else if(xmdm == "szyq_xthddjb"){//����
		
			var divHtml = $("div_fssq").innerHTML;
			
				divHtml+="<div id=\""+div_id+"\">";
				divHtml+="<input type=\"hidden\" id=\""+max_text_id+"\" value=\"0\" />";
				divHtml+="<table align=\"center\" style=\"width: 100%\">";
				divHtml+="<thead>";
				divHtml+="<tr>";
				divHtml+="<td width=\"5px\"><input type='checkbox' id=\"cb_"+xmdm+"\" name=\"cb_all_"+xmdm+"\" onclick=\"clickAll('"+xmdm+"')\"/></td>";		
				divHtml+="<td>�����<font color=\"red\">(������150��)</font></td>";
				divHtml+="<td width=\"15%\">����</td>";
				divHtml+="<td width=\"15%\">�����ȼ�</td>";
				divHtml+="<td width=\"15%\">����ӷ�</td>";
				divHtml+="</tr>";
				divHtml+="</thead>";
				divHtml+="</table>";
				divHtml+="<div id=\""+div_fssq_id+"\" style=\"height:330px;overflow-x:auto;overflow-y:auto;\">";
				divHtml+="</div>";
				divHtml+="</div>";
			
			$("div_fssq").innerHTML = "";
			$("div_fssq").innerHTML = divHtml;
			
		}else if(xmdm == "szyc_zznlfzb"){//��֯����
		
			var divHtml = $("div_fssq").innerHTML;
			
				divHtml+="<div id=\""+div_id+"\">";
				divHtml+="<input type=\"hidden\" id=\""+max_text_id+"\" value=\"0\" />";
				divHtml+="<table align=\"center\" style=\"width: 100%\">";
				divHtml+="<thead>";
				divHtml+="<tr>";
				divHtml+="<td width=\"5px\"><input type='checkbox' id=\"cb_"+xmdm+"\" name=\"cb_all_"+xmdm+"\" onclick=\"clickAll('"+xmdm+"')\"/></td>";		
				divHtml+="<td>�����<font color=\"red\">(������150��)</font></td>";
				divHtml+="<td width=\"15%\">�����</td>";
				divHtml+="<td width=\"15%\">��ȼ�</td>";
				divHtml+="<td width=\"15%\">����ӷ�</td>";
				divHtml+="</tr>";
				divHtml+="</thead>";
				divHtml+="</table>";
				divHtml+="<div id=\""+div_fssq_id+"\" style=\"height:330px;overflow-x:auto;overflow-y:auto;\">";
				divHtml+="</div>";
				divHtml+="</div>";
			
			$("div_fssq").innerHTML = "";
			$("div_fssq").innerHTML = divHtml;
			
		}else if(xmdm == "szyc_shsjfzb"){//���ʵ��
		
			var divHtml = $("div_fssq").innerHTML;
			
				divHtml+="<div id=\""+div_id+"\">";
				divHtml+="<input type=\"hidden\" id=\""+max_text_id+"\" value=\"0\" />";
				divHtml+="<table align=\"center\" style=\"width: 100%\">";
				divHtml+="<thead>";
				divHtml+="<tr>";
				divHtml+="<td width=\"5px\"><input type='checkbox' id=\"cb_"+xmdm+"\" name=\"cb_all_"+xmdm+"\" onclick=\"clickAll('"+xmdm+"')\"/></td>";
				divHtml+="<td width=\"15%\">��ص�</td>";
				divHtml+="<td width=\"15%\">�����</td>";
				divHtml+="<td>�����<font color=\"red\">(������500��)</font></td>";
				divHtml+="<td width=\"15%\">�ʱ��</td>";
				divHtml+="<td width=\"15%\">����ӷ�</td>";
				divHtml+="</tr>";
				divHtml+="</thead>";
				divHtml+="</table>";
				divHtml+="<div id=\""+div_fssq_id+"\" style=\"height:330px;overflow-x:auto;overflow-y:auto;\">";
				divHtml+="</div>";
				divHtml+="</div>";
			
			$("div_fssq").innerHTML = "";
			$("div_fssq").innerHTML = divHtml;
		}
	}
	
	//��ʼ����Ŀ
	defalutSqxm();
}	

//���ӷ�������
function addFssq(){

	if($("had_edit") && $("is_default") && $("is_default").value != ""){
		$("had_edit").value = "yes";
	}
	
	//��Ŀ����
	var xmdm = $("shxm").value;
	//Div
	var div_id = "div_"+xmdm;
	var div_fssq_id = "div_fssq_"+xmdm;
	//checkBox Name
	var cb_name = "cb_"+xmdm;
	
	if($(div_id)){
		if(xmdm == "szyq_dshdjzb"){//����	
			
			var max = parseInt($("max_"+xmdm).value)+1;
			var table_id = "tab_"+xmdm+"_"+max;
			
			var divHtml = $(div_fssq_id).innerHTML;
				divHtml+="<table align=\"center\" style=\"width: 100%\" id=\""+table_id+"\">";
				divHtml+="<tbody>";
				divHtml+="<tr>";
				divHtml+="<td align=\"center\" width=\"5px\"><input type='checkbox' name=\""+cb_name+"\"id=\"cb_"+xmdm+"_"+max+"\" value=\""+max+"\"/></td>";
				//��������
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"dsmc\" id=\"dsmc_"+xmdm+"_"+max+"\" style=\"width:60px\" maxLength=\"40\"/></td>";
				//��������
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"dsrq\" id=\"dsrq_"+xmdm+"_"+max+"\" style=\"width:60px\" onclick=\"return showCalendar('dsrq_"+xmdm+"_"+max+"','ymmdd');\" readOnly=\"true\" /></td>";
				//�����ĵ�
				divHtml+="<td align=\"center\" ><textarea name=\"dsxd\" style='word-break:break-all;' id=\"dsxd_"+xmdm+"_"+max+"\" rows=\"1\" onfocus=\"changeTextArea(this.id,'focus')\" onblur=\"changeTextArea(this.id,'blur');chLeng(this,150)\"></textarea></td>";
				//�Ƿ��
				divHtml+="<td align=\"center\" width=\"15%\"><select name=\"sfhj\" id=\"sfhj_"+xmdm+"_"+max+"\"><option value=\"��\">��</option><option value=\"��\">��</option></select></td>";
				//�����
				//divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"_"+max+"\" onkeydown=\"return onlyNum(this,3)\" onmousedown=\"return onlyNum(this,3)\" maxlength=\"3\" style=\"width : 30px;ime-mode:disabled\"/></td>";
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"_"+max+"\" onkeyup=\"checkInputNum(this)\" onblur=\"checkInputNum(this)\" maxlength=\"5\" style=\"width : 30px;ime-mode:disabled;\"/></td>";
				divHtml+="</tr>";
				divHtml+="</tbody>";
				divHtml+="</table>";
				
			$(div_fssq_id).innerHTML = "";
			$(div_fssq_id).innerHTML = divHtml;
			
			$("max_"+xmdm).value = max;
				
		}else if(xmdm == "szyq_yybdjzb"){//���Ա��
		
			var max = parseInt($("max_"+xmdm).value)+1;
			var table_id = "tab_"+xmdm+"_"+max;
			
			var divHtml = $(div_fssq_id).innerHTML;
				divHtml+="<table align=\"center\" style=\"width: 100%\" id=\""+table_id+"\">";
				divHtml+="<tbody>";
				divHtml+="<tr>";
				divHtml+="<td align=\"center\" width=\"5px\"><input type='checkbox' name=\""+cb_name+"\"id=\"cb_"+xmdm+"_"+max+"\" value=\""+max+"\"/></td>";
				//���Ա������
				divHtml+="<td align=\"center\" ><textarea  style='word-break:break-all;width:90%' name=\"yybdnr\" id=\"yybdnr_"+xmdm+"_"+max+"\" rows=\"1\" onfocus=\"changeTextArea(this.id,'focus')\" onblur=\"changeTextArea(this.id,'blur');chLeng(this,150)\"></textarea></td>";
				//����
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"xthdrq\" id=\"xthdrq_"+xmdm+"_"+max+"\" style=\"width:60px\" onclick=\"return showCalendar('xthdrq_"+xmdm+"_"+max+"','ymmdd');\" readOnly=\"true\" /></td>";
				//�����
				//divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"_"+max+"\" onkeydown=\"return onlyNum(this,3)\" onmousedown=\"return onlyNum(this,3)\" maxlength=\"3\" style=\"width : 30px;ime-mode:disabled\"/></td>";
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"_"+max+"\" onkeyup=\"checkInputNum(this)\" onblur=\"checkInputNum(this)\" maxlength=\"5\" style=\"width : 30px;ime-mode:disabled;\"/></td>";
				divHtml+="</tr>";
				divHtml+="</tbody>";
				divHtml+="</table>";
				
			$(div_fssq_id).innerHTML = "";
			$(div_fssq_id).innerHTML = divHtml;
			
			$("max_"+xmdm).value = max;
			
		}else if(xmdm == "szyq_ivtltb"){//IVT��̳
		
			var max = parseInt($("max_"+xmdm).value)+1;
			var table_id = "tab_"+xmdm+"_"+max;
			
			var divHtml = $(div_fssq_id).innerHTML;
				divHtml+="<table align=\"center\" style=\"width: 100%\" id=\""+table_id+"\">";
				divHtml+="<tbody>";
				divHtml+="<tr>";
				divHtml+="<td align=\"center\" width=\"5px\"><input type='checkbox' name=\""+cb_name+"\"id=\"cb_"+xmdm+"_"+max+"\" value=\""+max+"\"/></td>";
				//������Ŀ
				divHtml+="<td align=\"center\" width=\"25%\"><input type=\"text\" name=\"jztm\" id=\"jztm_"+xmdm+"_"+max+"\" style=\"width:100px\" maxLength=\"60\"/></td>";
				//����
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"xthdrq\" id=\"xthdrq_"+xmdm+"_"+max+"\" style=\"width:60px\" onclick=\"return showCalendar('xthdrq_"+xmdm+"_"+max+"','ymmdd');\" readOnly=\"true\" /></td>";
				//�����Ǽ�
				divHtml+="<td align=\"center\" ><input type=\"text\" name=\"jcdj\" id=\"jcdj_"+xmdm+"_"+max+"\" style=\"width:60px\" maxLength=\"40\"/></td>";
				//�����Ǽ�
				divHtml+="<td align=\"center\" width=\"20%\"><input type=\"text\" name=\"ccdj\" id=\"ccdj_"+xmdm+"_"+max+"\" style=\"width:60px\" maxLength=\"40\"/></td>";
				//�����
				//divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"_"+max+"\" onkeydown=\"return onlyNum(this,3)\" onmousedown=\"return onlyNum(this,3)\" maxlength=\"3\" style=\"width : 30px;ime-mode:disabled\"/></td>";
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"_"+max+"\" onkeyup=\"checkInputNum(this)\" onblur=\"checkInputNum(this)\" maxlength=\"5\" style=\"width : 30px;ime-mode:disabled;\"/></td>";
				divHtml+="</tr>";
				divHtml+="</tbody>";
				divHtml+="</table>";
				
			$(div_fssq_id).innerHTML = "";
			$(div_fssq_id).innerHTML = divHtml;
			
			$("max_"+xmdm).value = max;
			
		}else if(xmdm == "szyq_xthddjb"){//����
		
			var max = parseInt($("max_"+xmdm).value)+1;
			var table_id = "tab_"+xmdm+"_"+max;
			
			var divHtml = $(div_fssq_id).innerHTML;
				divHtml+="<table align=\"center\" style=\"width: 100%\" id=\""+table_id+"\">";
				divHtml+="<tbody>";
				divHtml+="<tr>";
				divHtml+="<td align=\"center\" width=\"5px\"><input type='checkbox' name=\""+cb_name+"\"id=\"cb_"+xmdm+"_"+max+"\" value=\""+max+"\"/></td>";
				//���ͬ
				divHtml+="<td align=\"center\" ><textarea style='word-break:break-all;width:90%' name=\"hdnr\" id=\"hdnr_"+xmdm+"_"+max+"\" rows=\"1\" onfocus=\"changeTextArea(this.id,'focus')\" onblur=\"changeTextArea(this.id,'blur');chLeng(this,150)\"></textarea></td>";
				//����
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"xthdrq\" id=\"xthdrq_"+xmdm+"_"+max+"\" style=\"width:60px\" onclick=\"return showCalendar('xthdrq_"+xmdm+"_"+max+"','ymmdd');\" readOnly=\"true\" /></td>";
				//�����ȼ�
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"jldj\" id=\"jldj_"+xmdm+"_"+max+"\" style=\"width:60px\" maxLength=\"20\"/></td>";
				//�����
				//divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"_"+max+"\" onkeydown=\"return onlyNum(this,3)\" onmousedown=\"return onlyNum(this,3)\" maxlength=\"3\" style=\"width : 30px;ime-mode:disabled\"/></td>";
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"_"+max+"\" onkeyup=\"checkInputNum(this)\" onblur=\"checkInputNum(this)\" maxlength=\"5\" style=\"width : 30px;ime-mode:disabled;\"/></td>";
				divHtml+="</tr>";
				divHtml+="</tbody>";
				divHtml+="</table>";
				
			$(div_fssq_id).innerHTML = "";
			$(div_fssq_id).innerHTML = divHtml;
			
			$("max_"+xmdm).value = max;
			
		}else if(xmdm == "szyc_zznlfzb"){//��֯�
		
			var max = parseInt($("max_"+xmdm).value)+1;
			var table_id = "tab_"+xmdm+"_"+max;
			
			var divHtml = $(div_fssq_id).innerHTML;
				divHtml+="<table align=\"center\" style=\"width: 100%\" id=\""+table_id+"\">";
				divHtml+="<tbody>";
				divHtml+="<tr>";
				divHtml+="<td align=\"center\" width=\"5px\"><input type='checkbox' name=\""+cb_name+"\"id=\"cb_"+xmdm+"_"+max+"\" value=\""+max+"\"/></td>";
				//�����
				divHtml+="<td align=\"center\" ><textarea style='word-break:break-all;width:90%' name=\"hdzt\" id=\"hdzt_"+xmdm+"_"+max+"\" rows=\"1\" onfocus=\"changeTextArea(this.id,'focus')\" onblur=\"changeTextArea(this.id,'blur');chLeng(this,150)\"></textarea></td>";
				//�����
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"hdrq\" id=\"hdrq_"+xmdm+"_"+max+"\" style=\"width:60px\" onclick=\"return showCalendar('hdrq_"+xmdm+"_"+max+"','ymmdd');\" readOnly=\"true\" /></td>";
				//��ȼ�
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"hddj\" id=\"hddj_"+xmdm+"_"+max+"\" style=\"width:60px\" maxLength=\"5\"/></td>";
				//�����
				//divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"_"+max+"\" onkeydown=\"return onlyNum(this,3)\" onmousedown=\"return onlyNum(this,3)\" maxlength=\"3\" style=\"width : 30px;ime-mode:disabled\"/></td>";
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"_"+max+"\" onkeyup=\"checkInputNum(this)\" onblur=\"checkInputNum(this)\" maxlength=\"5\" style=\"width : 30px;ime-mode:disabled;\"/></td>";
				divHtml+="</tr>";
				divHtml+="</tbody>";
				divHtml+="</table>";
				
			$(div_fssq_id).innerHTML = "";
			$(div_fssq_id).innerHTML = divHtml;
			
			$("max_"+xmdm).value = max;
			
		}else if(xmdm == "szyc_shsjfzb"){//���ʵ��
		
			var max = parseInt($("max_"+xmdm).value)+1;
			var table_id = "tab_"+xmdm+"_"+max;
			
			var divHtml = $(div_fssq_id).innerHTML;
				divHtml+="<table align=\"center\" style=\"width: 100%\" id=\""+table_id+"\">";
				divHtml+="<tbody>";
				divHtml+="<tr>";
				divHtml+="<td align=\"center\" width=\"5px\"><input type='checkbox' name=\""+cb_name+"\"id=\"cb_"+xmdm+"_"+max+"\" value=\""+max+"\"/></td>";
				//��ص�
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"hddd\" id=\"hddd_"+xmdm+"_"+max+"\" style=\"width:60px\" maxLength=\"50\"/></td>";
				//�����
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"hdrq\" id=\"hdrq_"+xmdm+"_"+max+"\" style=\"width:60px\" onclick=\"return showCalendar('hdrq_"+xmdm+"_"+max+"','ymmdd');\" readOnly=\"true\" /></td>";
				//�����
				divHtml+="<td align=\"center\" ><textarea style='word-break:break-all;width:90%' name=\"hdnr\" id=\"hdnr_"+xmdm+"_"+max+"\" rows=\"1\" onfocus=\"changeTextArea(this.id,'focus')\" onblur=\"changeTextArea(this.id,'blur');chLeng(this,500)\"></textarea></td>";
				//�ʱ��
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"hdsj\" id=\"hdsj_"+xmdm+"_"+max+"\" onkeydown=\"return onlyNum(this,3)\" onmousedown=\"return onlyNum(this,3)\" maxlength=\"3\" style=\"width : 30px;ime-mode:disabled\"/>(Сʱ)</td>";
				//�����
				//divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"_"+max+"\" onkeydown=\"return onlyNum(this,3)\" onmousedown=\"return onlyNum(this,3)\" maxlength=\"3\" style=\"width : 30px;ime-mode:disabled\"/></td>";
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"_"+max+"\" onkeyup=\"checkInputNum(this)\" onblur=\"checkInputNum(this)\" maxlength=\"5\" style=\"width : 30px;ime-mode:disabled;\"/></td>";
				divHtml+="</tr>";
				divHtml+="</tbody>";
				divHtml+="</table>";
				
			$(div_fssq_id).innerHTML = "";
			$(div_fssq_id).innerHTML = divHtml;
			
			$("max_"+xmdm).value = max;
		}
	}
}

//ɾ����������
function delFssq(tag){

	if(tag == "ok"){
		//��Ŀ����
		var xmdm = $("shxm").value;
		//���ֵ
		var max = $("max_"+xmdm).value;
		
		for(var i=0;i<=max;i++){	
			var cb_id = "cb_"+xmdm+"_"+i;
			var table_id = "tab_"+xmdm+"_"+i;
			
			if($(cb_id) && $(cb_id).checked){
				if(xmdm == "szyq_dshdjzb"){//����
			
					var dsmc_id = "dsmc_"+xmdm+"_"+i;//��������
					var dsrq_id = "dsrq_"+xmdm+"_"+i;//��������
					
					var url="szgyyq_mypj_stu.do?method=delDshdSqf";
					
					//����
				 	var parameter = {
						"dsmc":escape($(dsmc_id).value),
						"dsrq":$(dsrq_id).value
					};
					
					jQuery.post(url,parameter,function(result){});
					
				}else if(xmdm == "szyq_yybdjzb"){//���Ա��
				
					var yybdnr_id = "yybdnr_"+xmdm+"_"+i;//���Ա������
					var xthdrq_id = "xthdrq_"+xmdm+"_"+i;//����
					
					var url="szgyyq_mypj_stu.do?method=delYybdSqf";
					
					//����
				 	var parameter = {
						"yybdnr":escape($(yybdnr_id).value),
						"xthdrq":$(xthdrq_id).value
					};
					
					jQuery.post(url,parameter,function(result){});
					
				}else if(xmdm == "szyq_ivtltb"){//IVT��̳
				
					var jztm_id = "jztm_"+xmdm+"_"+i;//������Ŀ
					var xthdrq_id = "xthdrq_"+xmdm+"_"+i;//����
					
					var url="szgyyq_mypj_stu.do?method=delIvtltSqf";
					
					//����
				 	var parameter = {
						"jztm":escape($(jztm_id).value),
						"xthdrq":$(xthdrq_id).value
					};
					
					jQuery.post(url,parameter,function(result){});
					
				}else if(xmdm == "szyq_xthddjb"){//����
					
					var hdnr_id = "hdnr_"+xmdm+"_"+i;//�����
					var xthdrq_id = "xthdrq_"+xmdm+"_"+i;//����
					
					var url="szgyyq_mypj_stu.do?method=delWthdSqf";
					
					//����
				 	var parameter = {
						"hdnr":escape($(hdnr_id).value),
						"xthdrq":$(xthdrq_id).value
					};
					
					jQuery.post(url,parameter,function(result){});
					
				}else if(xmdm == "szyc_zznlfzb"){//��֯�
				
					var hdzt_id = "hdzt_"+xmdm+"_"+i;//�����
					var hdrq_id = "hdrq_"+xmdm+"_"+i;//�����
					
					var url="szgyyq_mypj_stu.do?method=delZznlSqf";
					
					//����
				 	var parameter = {
						"hdzt":escape($(hdzt_id).value),
						"hdrq":$(hdrq_id).value
					};
					
					jQuery.post(url,parameter,function(result){});
					
				}else if(xmdm == "szyc_shsjfzb"){//���ʵ��
				
					var hddd_id = "hddd_"+xmdm+"_"+i;//��ص�
					var hdrq_id = "hdrq_"+xmdm+"_"+i;//�����
					
					var url="szgyyq_mypj_stu.do?method=delShsjSqf";
					
					//����
				 	var parameter = {
						"hddd":escape($(hddd_id).value),
						"hdrq":$(hdrq_id).value
					};
					
					jQuery.post(url,parameter,function(result){});
				}
	
				jQuery("#"+table_id).remove();
			}
		}
	}
}

//�ı�TextArea��С
function changeTextArea(id,lx){
	if($(id)){
		if(lx == "focus"){
			$(id).rows = "5";
		}else{
			$(id).rows = "1";
		}
	}
}

//�����������
function saveFssq(tag){

	if(tag == "ok"){
		//��Ŀ����
		var xmdm = $("shxm").value;
		//Div
		var div_id = "div_"+xmdm;
		//CheckBox Name
		var cb_name = "cb_"+xmdm;
		var num = jQuery("input[name="+cb_name+"]",jQuery("#"+div_id)).length;
		
		var n = 0;
		
		var dsmc = new Array();//��������
		var dsrq = new Array();//��������
		var dsxd = new Array();//�����ĵ�
		var sfhj = new Array();//�Ƿ��
		var sqf = new Array();//�����
		
		var yybdnr = new Array();//���Ա������
		var xthdrq = new Array();//����
		
		var jztm = new Array();//������Ŀ
		var jcdj = new Array();//�����Ǽ�
		var ccdj = new Array();//�����Ǽ�
		
		var hdnr = new Array();//�����
		var jldj = new Array();//�����ȼ�
			
		var hdzt = new Array();//�����
		var hdrq = new Array();//�����
		var hddj = new Array();//��ȼ�
		
		var hddd = new Array();//��ص�
		var hdsj = new Array();//�ʱ��
		
		if(num  == 0){
			alertError("�����Ҫ��������Ļ�������������һ��");
			return false;
		}else{
			for(var i=0;i<num;i++){
			
				var obj = jQuery("input[name="+cb_name+"]",jQuery("#"+div_id))[i];
				var value = obj.value;
				
				if(xmdm == "szyq_dshdjzb"){//����
				
					var dsmc_id = "dsmc_"+xmdm+"_"+value;//��������
					var dsrq_id = "dsrq_"+xmdm+"_"+value;//��������
					var dsxd_id = "dsxd_"+xmdm+"_"+value;//�����ĵ�
					var sfhj_id = "sfhj_"+xmdm+"_"+value;//�Ƿ��
					var sqf_id = "sqf_"+xmdm+"_"+value;//�����
				
					if($(dsmc_id).value == ""){
						alertError("��"+(parseInt(i)+1)+"��,����Ϊ�գ���ȷ�ϣ�");
						$(dsmc_id).focus();
						return false;
					}else if($(dsrq_id).value == ""){
						alertError("��"+(parseInt(i)+1)+"��,��������Ϊ�գ���ȷ�ϣ�");
						$(dsrq_id).focus();
						return false;
					}else if($(dsxd_id).value == ""){
						alertError("��"+(parseInt(i)+1)+"��,�����ĵ�Ϊ�գ���ȷ�ϣ�");
						$(dsxd_id).focus();
						return false;
					}else if($(sqf_id).value == ""){
						alertError("��"+(parseInt(i)+1)+"��,�����Ϊ�գ���ȷ�ϣ�");
						$(sqf_id).focus();
						return false;
					}
					
					dsmc[n] = escape($(dsmc_id).value);
					dsrq[n] = $(dsrq_id).value;
					dsxd[n] = escape($(dsxd_id).value);
					sfhj[n] = escape($(sfhj_id).value);
					sqf[n] = $(sqf_id).value;
					n++;
					
				}else if(xmdm == "szyq_yybdjzb"){//���Ա��
				
					var yybdnr_id = "yybdnr_"+xmdm+"_"+value;//���Ա������
					var xthdrq_id = "xthdrq_"+xmdm+"_"+value;//����
					var sqf_id = "sqf_"+xmdm+"_"+value;//�����
				
					if($(yybdnr_id).value == ""){
						alertError("��"+(parseInt(i)+1)+"��,���Ա������Ϊ�գ���ȷ�ϣ�");
						$(yybdnr_id).focus();
						return false;
					}else if($(xthdrq_id).value == ""){
						alertError("��"+(parseInt(i)+1)+"��,����Ϊ�գ���ȷ�ϣ�");
						$(xthdrq_id).focus();
						return false;
					}else if($(sqf_id).value == ""){
						alertError("��"+(parseInt(i)+1)+"��,�����Ϊ�գ���ȷ�ϣ�");
						$(sqf_id).focus();
						return false;
					}
					
					yybdnr[n] = escape($(yybdnr_id).value);
					xthdrq[n] = $(xthdrq_id).value;
					sqf[n] = $(sqf_id).value;	
					n++;
					
				}else if(xmdm == "szyq_ivtltb"){//IVT��̳
				
					var jztm_id = "jztm_"+xmdm+"_"+value;//������Ŀ
					var xthdrq_id = "xthdrq_"+xmdm+"_"+value;//����
					var jcdj_id = "jcdj_"+xmdm+"_"+value;//�����Ǽ�
					var ccdj_id = "ccdj_"+xmdm+"_"+value;//�����Ǽ�
					var sqf_id = "sqf_"+xmdm+"_"+value;//�����
				
					if($(jztm_id).value == ""){
						alertError("��"+(parseInt(i)+1)+"��,������ĿΪ�գ���ȷ�ϣ�");
						$(jztm_id).focus();
						return false;
					}else if($(xthdrq_id).value == ""){
						alertError("��"+(parseInt(i)+1)+"��,����Ϊ�գ���ȷ�ϣ�");
						$(xthdrq_id).focus();
						return false;
					}else if($(sqf_id).value == ""){
						alertError("��"+(parseInt(i)+1)+"��,�����Ϊ�գ���ȷ�ϣ�");
						$(sqf_id).focus();
						return false;
					}
					
					jztm[n] = escape($(jztm_id).value);
					xthdrq[n] = $(xthdrq_id).value;
					jcdj[n] = escape($(jcdj_id).value);
					ccdj[n] = escape($(ccdj_id).value);
					sqf[n] = $(sqf_id).value;
					n++;
					
				}else if(xmdm == "szyq_xthddjb"){//����
				
					var hdnr_id = "hdnr_"+xmdm+"_"+value;//�����
					var xthdrq_id = "xthdrq_"+xmdm+"_"+value;//����
					var jldj_id = "jldj_"+xmdm+"_"+value;//�����ȼ�
					var sqf_id = "sqf_"+xmdm+"_"+value;//�����
				
					if($(hdnr_id).value == ""){
						alertError("��"+(parseInt(i)+1)+"��,�����Ϊ�գ���ȷ�ϣ�");
						$(hdnr_id).focus();
						return false;
					}else if($(xthdrq_id).value == ""){
						alertError("��"+(parseInt(i)+1)+"��,����Ϊ�գ���ȷ�ϣ�");
						$(xthdrq_id).focus();
						return false;
					}else if($(sqf_id).value == ""){
						alertError("��"+(parseInt(i)+1)+"��,�����Ϊ�գ���ȷ�ϣ�");
						$(sqf_id).focus();
						return false;
					}
					
					hdnr[n] = escape($(hdnr_id).value);
					xthdrq[n] = $(xthdrq_id).value;
					jldj[n] = escape($(jldj_id).value);
					sqf[n] = $(sqf_id).value;	
					n++;
					
				}else if(xmdm == "szyc_zznlfzb"){//��֯�
				
					var hdzt_id = "hdzt_"+xmdm+"_"+value;//�����
					var hdrq_id = "hdrq_"+xmdm+"_"+value;//�����
					var hddj_id = "hddj_"+xmdm+"_"+value;//��ȼ�
					var sqf_id = "sqf_"+xmdm+"_"+value;//�����
				
					if($(hdzt_id).value == ""){
						alertError("��"+(parseInt(i)+1)+"��,�����Ϊ�գ���ȷ�ϣ�");
						$(hdzt_id).focus();
						return false;
					}else if($(hdrq_id).value == ""){
						alertError("��"+(parseInt(i)+1)+"��,�����Ϊ�գ���ȷ�ϣ�");
						$(hdrq_id).focus();
						return false;
					}else if($(sqf_id).value == ""){
						alertError("��"+(parseInt(i)+1)+"��,�����Ϊ�գ���ȷ�ϣ�");
						$(sqf_id).focus();
						return false;
					}
					
					hdzt[n] = escape($(hdzt_id).value);
					hdrq[n] = $(hdrq_id).value;
					
					if($(hddj_id).value != ""){
						hddj[n] = escape($(hddj_id).value);
					}else{
						hddj[n] = " ";
					}
					sqf[n] = $(sqf_id).value;	
					n++;
					
				}else if(xmdm == "szyc_shsjfzb"){//���ʵ��
					var hddd_id = "hddd_"+xmdm+"_"+value;//��ص�
					var hdrq_id = "hdrq_"+xmdm+"_"+value;//�����
					var hdnr_id = "hdnr_"+xmdm+"_"+value;//�����
					var hdsj_id = "hdsj_"+xmdm+"_"+value;//�ʱ��
					var sqf_id = "sqf_"+xmdm+"_"+value;//�����
				
					if($(hddd_id).value == ""){
						alertError("��"+(parseInt(i)+1)+"��,��ص�Ϊ�գ���ȷ�ϣ�");
						$(hddd_id).focus();
						return false;
					}else if($(hdrq_id).value == ""){
						alertError("��"+(parseInt(i)+1)+"��,�����Ϊ�գ���ȷ�ϣ�");
						$(hdrq_id).focus();
						return false;
					}else if($(hdnr_id).value == ""){
						alertError("��"+(parseInt(i)+1)+"��,�����Ϊ�գ���ȷ�ϣ�");
						$(hdnr_id).focus();
						return false;
					}else if($(sqf_id).value == ""){
						alertError("��"+(parseInt(i)+1)+"��,�����Ϊ�գ���ȷ�ϣ�");
						$(sqf_id).focus();
						return false;
					}
					
					hddd[n] = escape($(hddd_id).value);
					hdrq[n] = $(hdrq_id).value;
					hdnr[n] = escape($(hdnr_id).value);
					if($(hdsj_id).value != ""){
						hdsj[n] = $(hdsj_id).value;
					}else{
						hdsj[n] = " ";
					}
					sqf[n] = $(sqf_id).value;	
					n++;
				}
			}
			
			jQuery.ajaxSetup({async:false});
			
			if(xmdm == "szyq_dshdjzb"){//����
			
				var url="szgyyq_mypj_stu.do?method=saveDshdSqf";
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				//����
			 	var parameter = {
					"dsmc":dsmc.join("!!@@!!"),
					"dsrq":dsrq.join("!!@@!!"),
					"dsxd":dsxd.join("!!@@!!"),
					"sfhj":sfhj.join("!!@@!!"),
					"sqf":sqf.join("!!@@!!")
				};
				
				jQuery.post(url,parameter,function(result){dcSuccess(result);});
				
			}else if(xmdm == "szyq_yybdjzb"){//���Ա��
			
				var url="szgyyq_mypj_stu.do?method=saveYybdSqf";
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				//����
			 	var parameter = {
					"yybdnr":yybdnr.join("!!@@!!"),
					"xthdrq":xthdrq.join("!!@@!!"),
					"sqf":sqf.join("!!@@!!")
				};
				
				jQuery.post(url,parameter,function(result){dcSuccess(result);});
				
			}else if(xmdm == "szyq_ivtltb"){//IVT��̳
			
				var url="szgyyq_mypj_stu.do?method=saveIvtltSqf";
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				//����
			 	var parameter = {
					"jztm":jztm.join("!!@@!!"),
					"xthdrq":xthdrq.join("!!@@!!"),
					"jcdj":jcdj.join("!!@@!!"),
					"ccdj":ccdj.join("!!@@!!"),
					"sqf":sqf.join("!!@@!!")
				};
				
				jQuery.post(url,parameter,function(result){dcSuccess(result);});
				
			}else if(xmdm == "szyq_xthddjb"){//����
			
				var url="szgyyq_mypj_stu.do?method=saveWthdSqf";
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				//����
			 	var parameter = {
					"hdnr":hdnr.join("!!@@!!"),
					"xthdrq":xthdrq.join("!!@@!!"),
					"jldj":jldj.join("!!@@!!"),
					"sqf":sqf.join("!!@@!!")
				};
				
				jQuery.post(url,parameter,function(result){dcSuccess(result);});
				
			}else if(xmdm == "szyc_zznlfzb"){//��֯�
			
				var url="szgyyq_mypj_stu.do?method=saveZznlSqf";
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				//����
			 	var parameter = {
					"hdzt":hdzt.join("!!@@!!"),
					"hdrq":hdrq.join("!!@@!!"),
					"hddj":hddj.join("!!@@!!"),
					"sqf":sqf.join("!!@@!!")
				};
				
				jQuery.post(url,parameter,function(result){dcSuccess(result);});
				
			}else if(xmdm == "szyc_shsjfzb"){//���ʵ��
			
				var url="szgyyq_mypj_stu.do?method=saveShsjSqf";
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				//����
			 	var parameter = {
					"hddd":hddd.join("!!@@!!"),
					"hdrq":hdrq.join("!!@@!!"),
					"hdnr":hdnr.join("!!@@!!"),
					"hdsj":hdsj.join("!!@@!!"),
					"sqf":sqf.join("!!@@!!")
				};
				
				jQuery.post(url,parameter,function(result){dcSuccess(result);});
			}
			
			jQuery.ajaxSetup({async:true});
		}
	}else if(tag == "cancel"){
		$("had_edit").value = "no";
	}
}

//��ʼ��������Ŀ
function defalutSqxm(){
	//��Ŀ����
	var xmdm = $("shxm").value;
	var max = $("max_"+xmdm).value;
	
	if(max == "0"){
		$("btn_add").click();
		$("had_edit").value = "no";
	}
	
	var url="szgyyq_mypj_stu.do?method=getSqxmInfo";
	
	//��������
 	var parameter = {
		"xmdm":xmdm
	};

	jQuery("#div_sqxm_info").load(url,parameter,function(){});
}

//��ʾ������Ŀ��ϸ���
function showSqxxDetail(xn,xq,xh,xmdm,doType){

	var url = "/xgxt/szgyyq_mypj_stu.do?method=sqxxDetail";
		url+="&xn="+xn;
		url+="&xq="+xq;
		url+="&xh="+xh;
		url+="&xmdm="+xmdm;
		url+="&doType="+doType;
		
	showTopWin(url,'800','600');		
}


//��ʼ�������Ŀ
function defalutShxm(){
	//��Ŀ����
	alert(1);
	
	var xmdm = $("shxm").value;
	
	var url="szgyyq_mypj_stu.do?method=getSqxmInfo";
	
	//��������
 	var parameter = {
		"xmdm":xmdm
	};

	jQuery("#div_shxm_info").load(url,parameter,function(){});
}