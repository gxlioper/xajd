//��ʾ��������
function showEndPjpy(){
	var url = "general_pjpy.do?method=pjpyEnd";
	showTopWin(url,"800","650");
}

//ǰ���ҵ�����
function goPjpyWdpj(){
	var url = "pjpy_general_wdpj.do";
	refreshForm(url);
}

//ǰ��ѧ������
function goWdpjXssq(){
	var url = "general_pjpy.do?method=wdpjXssq";
	refreshForm(url);
}

//ǰ��ѧ�����
function goWdpjXsjg(ckxx){
	var url = "general_pjpy.do?method=wdpjXssqJgcx&ckxx="+ckxx;
	refreshForm(url);
}

//��ʦ�ϱ���Ϣ
function goWdpjLssb(){
	var url = "general_pjpy.do?method=xmsbManage";
	
	url+= "&xmdm="+$("xmdm").value;
	url+= "&bjdm="+$("bj").value;
			
	showWaitingDiv("30000");
	
	refreshForm(url);
}

//ǰ����Ŀ���
function goWdpjXmsh(xmdm,spgw){
	var url = "general_pjpy.do?method=xmshManage";
	url+="&xmdm="+xmdm;
	url+="&spgw="+spgw;
	refreshForm(url);
}

//��ʾ��Ŀ�����ϸ
function showWdpjXmsh(xh){

	var xmdm=$("xmdm").value;
	var spgw=$("spgw").value;
	
	var url = "general_pjpy.do?method=xmshDetail";
	url+="&xh="+xh;
	url+="&xmdm="+xmdm;
	url+="&spgw="+spgw;
	showTopWin(url,"800","600");
}

//ǰ����������
function goWdpjBcpj(){
	var url = "general_pjpy.do?method=wdpjBcpj";
	refreshForm(url);
}

//��ʾ�����������
function showWdpjBcpj(){
	var url = "general_pjpy.do?method=bcpjDetail";
	showTopWin(url,"800","600");
}

//================�������������á�begin=================================

//��ʾ���̶���
function showLcdy(){
	var url = "general_pjpy.do?method=pjlcSetting";
	showTopWin(url,"800","650");
}

//================�������������á�end=================================

//================����ʼ��������begin=================================

//��ʾ��ʼ������
function showNewPjpy(){
	var url = "general_pjpy.do?method=pjpyStart";
	showTopWin(url,"800","650");
}

//���radio�ؼ�
function checkRadio(obj){
	var id = obj.id.split("_")[0]+"_checked";
	var value = obj.value;
	jQuery("#"+id).val(value);
}

//��Ᵽ����������
function checkSaveStart(){
	
	var pjzq = $("pjzq_checked").value;//��������
	var pjxn = $("pjxn").value;//����ѧ��
	var pjxq = "";//����ѧ��
	if(pjzq == "xn"){
		pjxq = "";
	}else if(pjzq == "xq"){
		pjxq = $("pjxq").value;
	}
	
	var flag = true;
	
	if(pjxn == ""){
		alertError("����ѧ�겻��Ϊ��,��ȷ��");
		flag = false;
	}else if(pjzq == "xq" && pjxq == ""){
		alertError("����ѧ�ڲ���Ϊ��,��ȷ��");
		flag = false;
	}
	
	if(flag){
		confirmInfo('����ȷ������������',saveStart);
	}
}

//���濪ʼ������
function saveStart(tag){

	if(tag == "ok"){
		var pjzq = $("pjzq_checked").value;//��������
		var pjxn = $("pjxn").value;//����ѧ��
		var pjxq = "";//����ѧ��
		if(pjzq == "xn"){
			pjxq = "";
		}else if(pjzq == "xq"){
			pjxq = $("pjxq").value;
		}
		
		var cpz = $("cpz_checked").value;//������
		var zcpm = $("zcpm_checked").value;//�۲�����
		var zypm = $("zypm_checked").value;//��������
		var start = $("hidden_start").value;//�Ƿ�ʼ������
		
		var url = "general_pjpy_index_ajax.do?method=saveStart";

		//����
	 	var parameter = {
	 		"pjzq":pjzq,
	 		"pjxn":pjxn,
	 		"pjxq":pjxq,
	 		"cpz":cpz,
	 		"zcpm":zcpm,
	 		"zypm":zypm,
	 		"start":start
		};

	 	$("divWaiting").style.display="";
		$("divDisable").style.display="";
		
		jQuery.post(url,
			parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				alertInfo(result);
				if(window.dialogArguments.document.getElementById("btn_sx")){
					window.dialogArguments.document.getElementById("btn_sx").click();
				}
			}
		);
	}
}

//���ÿ�ʼ������ע
function setStartBz(){
	var pjzq = jQuery("#hidden_pjzq").val();
	var pjxn = jQuery("#hidden_pjxn").val();
	var pjxq = jQuery("#hidden_pjxq").val();

	var flag_pjzq = false;

	if($("pjzq_checked").value != pjzq){
		flag_pjzq = true;
	}else{
		if($("pjzq_checked").value == "xn"){
			if($("pjxn").value != pjxn){
				flag_pjzq = true;
			}
		}else{
			if($("pjxn").value != pjxn || $("pjxq").value != pjxq){
				flag_pjzq = true;
			}
		}
	}

	if(flag_pjzq){
		$("div_csh_yes").style.display = "";
		$("div_csh_no").style.display = "none";
		$("hidden_start").value="yes";
	}else{
		$("div_csh_yes").style.display = "none";
		$("div_csh_no").style.display = "";
		$("hidden_start").value="no";
	}
}

//���������
function clickCpz(cpz,lx){

	if(cpz == "yes"){
		jQuery("#zcpm_checked").val("0");
		jQuery("#zypm_checked").val("0");
		
		jQuery("#tr_zcpm").children().find("input[type=radio]").each(function(){
			jQuery(this).attr("disabled","true");
			if(jQuery(this).attr("id") == "zcpm_cpz"){
				jQuery(this).attr("checked","true");
			}
		});
		
		jQuery("#tr_zypm").children().find("input[type=radio]").each(function(){
			jQuery(this).attr("disabled","true");
			if(jQuery(this).attr("id") == "zypm_cpz"){
				jQuery(this).attr("checked","true");
			}
		});
	}else{
		if(lx == ""){
			jQuery("#zcpm_checked").val("3");
			jQuery("#zypm_checked").val("3");
			
			
			jQuery("#tr_zcpm").children().find("input[type=radio]").each(function(){
				jQuery(this).attr("disabled",false);
				if(jQuery(this).attr("id") == "zcpm_bj"){
					jQuery(this).attr("checked","true");
				}
				if(jQuery(this).attr("id") == "zcpm_cpz"){
					jQuery(this).attr("disabled","true");
				}
			});
			
			jQuery("#tr_zypm").children().find("input[type=radio]").each(function(){
				jQuery(this).attr("disabled",false);
				if(jQuery(this).attr("id") == "zypm_bj"){
					jQuery(this).attr("checked","true");
				}
				if(jQuery(this).attr("id") == "zypm_cpz"){
					jQuery(this).attr("disabled","true");
				}
			});
		}else{
			jQuery("#tr_zcpm").children().find("input[type=radio]").each(function(){
				if(jQuery(this).attr("id") == "zcpm_cpz"){
					jQuery(this).attr("disabled","true");
				}
			});
			
			jQuery("#tr_zypm").children().find("input[type=radio]").each(function(){
				if(jQuery(this).attr("id") == "zypm_cpz"){
					jQuery(this).attr("disabled","true");
				}
			});
		}
	}
}
//================����ʼ��������end===================================

//================�������������á�begin===============================

//ǰ��������������
function goPjpyJbsz(){
	var url = "pjpy_general_index.do";
	refreshForm(url);
}

//��ʼ���Ѷ�����������
function defaultCustomPjlc(){
	
	//·��
	var url = "general_pjpy_index_ajax.do?method=defaultCustomPjlc";
	//����
 	var parameter = {
		"stylePath":stylePath
	};
	
	$("divWaiting").style.display="";
	$("divDisable").style.display="";
		
	jQuery("#div_custom_pjlc").load(
		url,
		parameter,
		function(){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
		}
	);
}

//����ύ��������
function checkSubmitPjlc(obj){
	var id = obj.id;
	var lcdj = id.split("_")[1];
	
	jQuery("#lcdj_submit").val(lcdj);
	
	if(lcdj == "2"){
		var message = checkCpxzSubmit();
		
		if(message !="" && message !=null ){
			alertError(message);
			return false;
		}
	}
	
	confirmInfo('����ȷ���Ƿ��ύ��������',submitPjlc);
}

//�ύ��������
function submitPjlc(tag){

	var lcdj = "";
	
	if($("lcdj_submit")){
		lcdj = jQuery("#lcdj_submit").val();//���̵ȼ�
	}else{
		lcdj = "6";
	}

	if(tag == "ok"){
		
		var url = "general_pjpy_index_ajax.do?method=submitPjlc";

		//����
	 	var parameter = {
	 		"lcdj":lcdj
		};

	 	$("divWaiting").style.display="";
		$("divDisable").style.display="";
		
		jQuery.post(url,
			parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				alertInfo(result);
				onShow();
			}
		);
	}
}

//================�������������á�end=================================

//================��������Ա���á�begin===================================

//ǰ��������Ա����
function goPjszPjry(){
	var url = "general_pjpy.do?method=pjszPjry&forward=jbsz";
	refreshForm(url);
}

//��ʾ�༶����Div
function showBjtzDiv(){

	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;

	if(num == 0){
		alertError("����<font color='blue'>��ѡ</font>��Ҫ�����༶��ѧ����¼");
		return false;
	}else{
		tipsWindown("ϵͳ��ʾ","id:div_bjtz","360","300","true","","true","id");
	}
}

//��Ᵽ��༶����
function checkSaveBjtz(){
	var bj = jQuery("#bj").val();
	var bjmc = "";
	
	if(bj == ""){
		alertError("�༶����Ϊ�գ�����ȷ����<font color='blue'>��ѡ</font>ѧ����Ҫ������ʲô<font color='blue'>�༶</font>");
		return false;
	}else{
		jQuery("#bjdm_check").val(bj);
		bjmc = jQuery('option[selected=true]',jQuery("#bj")).text();
	}
	
	var message = "";
		message+= "��ȷ����<font color='blue'>����ѡ</font>��ѧ��������<font color='blue'>";
		message+= bjmc;
		message+= "</font>����������<br/>";
		message+= "ע���������������������������Ƚ��Ե����༶Ϊ׼��";
		
	confirmInfo(message,saveBjtz);
}

//����༶����
function saveBjtz(tag){
	if(tag == "ok"){

		jQuery.ajaxSetup({async:false});
		
		var xh = new Array();//ѧ��
		var xh_num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
		var count = 0;
		for(var i=0;i<xh_num;i++){
			var obj = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked")[i];
			xh[count] = obj.value;
			count++;
		}

		var bjdm = jQuery("#bjdm_check").val();//�༶����
		var bjmc = $("bj").options[$("bj").selectedIndex].text;//�༶����
		
		var url = "general_pjsz_pjry_ajax.do?method=saveBjtz";
		
		//����
	 	var parameter = {
	 		"bjdm":bjdm,
	 		"bjmc":escape(bjmc),
	 		"xh":xh.join("!!@@!!")
		};

	 	$("divWaiting").style.display="";
		$("divDisable").style.display="";
		
		jQuery.post(url,
			parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				alertInfo(result);

				searchRs();
				closeWindown();
				
			}
		);

		jQuery.ajaxSetup({async:true});
	}
}

//��ʾ�Ƿ����Div
function showSfcpDiv(){
	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;

	if(num == 0){
		alertError("����<font color='blue'>��ѡ</font>��Ҫ���õ�ѧ����¼");
		return false;
	}else{
		tipsWindown("ϵͳ��ʾ","id:div_cpsz","300","200","true","","true","id");
	}
}

//��Ᵽ���������
function checkSaveCpsz(){
	var sfcp = jQuery("input[type=radio][name=sfcp]:checked").val();
	var message = "";
	
	if(sfcp == "yes"){
		message+= "ִ��ȷ��������<font color='blue'>��ѡ</font>��ѧ����<font color='blue'>���</font>�ʸ�������";
	}else{
		message+= "ִ��ȷ��������<font color='blue'>��ѡ</font>��ѧ����<font color='blue'>ʧȥ</font>�ʸ�������";
	}
	
	confirmInfo(message,saveCpsz);
}

//�����������
function saveCpsz(tag){
	if(tag == "ok"){

		jQuery.ajaxSetup({async:false});
		
		var xh = new Array();//ѧ��
		var xh_num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
		var count = 0;
		for(var i=0;i<xh_num;i++){
			var obj = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked")[i];
			xh[count] = obj.value;
			count++;
		}

		var sfcp = jQuery("#sfcp_check").val();//�Ƿ����
		
		var url = "general_pjsz_pjry_ajax.do?method=saveCpsz";
		
		//����
	 	var parameter = {
	 		"sfcp":sfcp,
	 		"xh":xh.join("!!@@!!")
		};

	 	$("divWaiting").style.display="";
		$("divDisable").style.display="";
		
		jQuery.post(url,
			parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				alertInfo(result);
				searchRs();
				closeWindown();		
			}
		);

		jQuery.ajaxSetup({async:true});
	}
}
//================��������Ա���á�end===================================

//================������С�����á�begin============================
//ǰ������С������
function goPjszCpxz(){
	var url = "general_pjpy.do?method=pjszCpxz&forward=jbsz";
	refreshForm(url);
}

//��ʾ�������Զ�����DIV
function showCpxzZdszDiv(){
	tipsWindown("ϵͳ��ʾ","id:div_cpxz_zdsz","300","300","true","","true","id");
}

//����������Զ�����
function saveCpxzZdsz(){
	
	var url = "general_pjsz_cpxz_ajax.do?method=saveCpxzZdsz";
	var cpzgz = jQuery("input[type=radio][name=cpzgz]:checked").eq(0).val();

	//����
	var parameter = {
		"cpzgz":cpzgz
	};
	
	$("divWaiting").style.display="";
	$("divDisable").style.display="";
	
	jQuery.post(url,
		parameter,
		function(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			alertInfo(result);
			goPjszCpxz();
			closeWindown();	
		}
	);
	
	jQuery.ajaxSetup({async:true});
}

//������С���ύ
function checkCpxzSubmit(){

	var message = "";
	
	jQuery.ajaxSetup({async:false});
	
	var url = "general_pjsz_cpxz_ajax.do?method=checkCpxzSubmit";
	
	//����
 	var parameter = {};

	jQuery.post(url,
		parameter,
		function(result){
			message = result;
		}
	);

	jQuery.ajaxSetup({async:true});
	
	return message;
}
//================������С�����á�end============================

//================���༶�������á�begin===================================

//ǰ���༶��������
function goPjszBjdl(){
	var url = "general_pjpy.do?method=pjszBjdl&forward=jbsz";
	refreshForm(url);
}

//ǰ������ά��
function goPjpjDmwh(){
	var url = "xtwhOther.do?method=xtDmwhNew&ssmk=pjpy";
	refreshForm(url);
}

//��Ᵽ�����С������
function checkSaveBjdl(){
	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
	
	if(num == 0){//����
		//�꼶
		var nj_num = jQuery("a[name=a_name_nj]").length; 			  		
		//ѧԺ
		var xy_num = jQuery("a[name=a_name_xy]").length; 		
		//רҵ
		var zy_num = jQuery("a[name=a_name_zy]").length; 
		//�༶
		var bj_num = jQuery("a[name=a_name_bj]").length; 
		
		confirmInfo("��ȷ���Ƿ�<font color='blue'>������������ָ���İ༶</font>����Ϊ�ð༶����",saveBjdlNoChecked);
		
	}else{//��ѡ
		confirmInfo("��ȷ���Ƿ�<font color='blue'>����ѡ�İ༶</font>����Ϊ�ð༶����",saveBjdlChecked);
	}
}

//����༶�������ã�δѡ�У�
function saveBjdlNoChecked(tag){
	if(tag == "ok"){
		jQuery.ajaxSetup({async:false});

		//�꼶
		var nj = new Array();  
		var i = 0;				  
		jQuery("a[name=a_name_nj]").each(function(){
			var nj_id = jQuery(this).attr("id");
			nj[i] = nj_id.replace("a_id_","");
			i++;
		});
		
		//ѧԺ
		var xy = new Array(); 
		i = 0;			  
		jQuery("a[name=a_name_xy]").each(function(){
			var xy_id = jQuery(this).attr("id");
			xy[i] = xy_id.replace("a_id_","");
			i++;
		});

		//רҵ
		var zy = new Array(); 
		i = 0;					  
		jQuery("a[name=a_name_zy]").each(function(){
			var zy_id = jQuery(this).attr("id");
			zy[i] = zy_id.replace("a_id_","");
			i++;
		});

		//�༶
		var bj = new Array();  				  
		jQuery("a[name=a_name_bj]").each(function(){
			var bj_id = jQuery(this).attr("id");
			bj[i] = bj_id.replace("a_id_","");
			i++;
		});
		
		var bjdlmc = jQuery("#select_bjdlmc").val();//�༶��������

		var url = "general_pjsz_bjdl_ajax.do?method=saveBjdlNoChecked";
		
		//����
	 	var parameter = {
	 		"bjdlmc":escape(bjdlmc),
	 		"nj":nj.join("!!@@!!"),
	 		"xy":xy.join("!!@@!!"),
	 		"zy":zy.join("!!@@!!"),
	 		"bj":bj.join("!!@@!!")
		};

	 	$("divWaiting").style.display="";
		$("divDisable").style.display="";
		
		jQuery.post(url,
			parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				alertInfo(result);
				searchRs();
				closeWindown();	
			}
		);
		
		jQuery.ajaxSetup({async:true});
	}
}

//����༶�������ã�ѡ�У�
function saveBjdlChecked(tag){
	if(tag == "ok"){
		jQuery.ajaxSetup({async:false});
		
		var bjdm = new Array();//�༶����
		var bjdm_num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
		var count = 0;
		for(var i=0;i<bjdm_num;i++){
			var obj = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked")[i];
			bjdm[count] = obj.value;
			count++;
		}

		var bjdlmc = jQuery("#select_bjdlmc").val();//�༶��������				
		var url = "general_pjsz_bjdl_ajax.do?method=saveBjdlChecked";
		
		//����
	 	var parameter = {
	 		"bjdlmc":escape(bjdlmc),
	 		"bjdm":bjdm.join("!!@@!!")
		};

	 	$("divWaiting").style.display="";
		$("divDisable").style.display="";
		
		jQuery.post(url,
			parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				alertInfo(result);
				searchRs();
				closeWindown();	
			}
		);

		jQuery.ajaxSetup({async:true});
	}
}

//���ɾ���༶��������
function checkDeleteBjdl(){
	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;

	if(num == 0){//����
		alertError("����<font color='blue'>��ѡ</font>��Ҫ���õİ༶��");
		return false;
	}else{
		confirmInfo("����ȷ���Ƿ�ȡ��<font color='blue'>����ѡ�༶</font>�İ༶��������",deleteBjdlChecked);
	}
}

//ɾ���༶�������ã�ѡ�У�
function deleteBjdlChecked(tag){
	if(tag == "ok"){
		jQuery.ajaxSetup({async:false});
		
		var bjdm = new Array();//�༶����
		var bjdm_num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
		var count = 0;
		for(var i=0;i<bjdm_num;i++){
			var obj = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked")[i];
			bjdm[count] = obj.value;
			count++;
		}
			
		var url = "general_pjsz_bjdl_ajax.do?method=deleteBjdlChecked";
		
		//����
	 	var parameter = {
	 		"bjdm":bjdm.join("!!@@!!")
		};

	 	$("divWaiting").style.display="";
		$("divDisable").style.display="";
		
		jQuery.post(url,
			parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				alertInfo(result);
				searchRs();
				closeWindown();	
			}
		);

		jQuery.ajaxSetup({async:true});
	}
}

//ȡ������С�����ã�δѡ�У�
function deleteBjdlNoChecked(tag){
	if(tag == "ok"){

		jQuery.ajaxSetup({async:false});

		//�꼶
		var nj = new Array();  
		var i = 0;				  
		jQuery("a[name=a_name_nj]").each(function(){
			var nj_id = jQuery(this).attr("id");
			nj[i] = nj_id.replace("a_id_","");
			i++;
		});
		
		//ѧԺ
		var xy = new Array(); 
		i = 0;			  
		jQuery("a[name=a_name_xy]").each(function(){
			var xy_id = jQuery(this).attr("id");
			xy[i] = xy_id.replace("a_id_","");
			i++;
		});

		//רҵ
		var zy = new Array(); 
		i = 0;					  
		jQuery("a[name=a_name_zy]").each(function(){
			var zy_id = jQuery(this).attr("id");
			zy[i] = zy_id.replace("a_id_","");
			i++;
		});

		//�༶
		var bj = new Array();  				  
		jQuery("a[name=a_name_bj]").each(function(){
			var bj_id = jQuery(this).attr("id");
			bj[i] = bj_id.replace("a_id_","");
			i++;
		});

		var url = "general_pjsz_bjdl_ajax.do?method=deleteBjdlNoChecked";
		
		//����
	 	var parameter = {
	 		"nj":nj.join("!!@@!!"),
	 		"xy":xy.join("!!@@!!"),
	 		"zy":zy.join("!!@@!!"),
	 		"bj":bj.join("!!@@!!")
		};

	 	$("divWaiting").style.display="";
		$("divDisable").style.display="";
		
		jQuery.post(url,
			parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				alertInfo(result);
				searchRs();
				closeWindown();	
			}
		);
		
		jQuery.ajaxSetup({async:true});
	}
}

//��ʾ�༶������ϸDiv
function showBjdlDetail(){
	jQuery("#bjdlmc").val("");
	tipsWindown("ϵͳ��ʾ","id:div_bjdl_detail","300","250","true","","true","id");
}

//================���༶�������á�end===================================

//================���ۺ����ʲ�����begin===================================

//ǰ���۲���Ŀ����
function goPjszZcxm(){
	var url = "general_pjpy.do?method=pjszZcxm&forward=jbsz";
	refreshForm(url);
}

//ǰ���ۺϲ���ά��
function goZhcpZcwh(){
	var url = "general_pjpy.do?method=zhcpZcxx&forward=jbsz";
	refreshForm(url);
}

//ǰ���ۺϲ������
function goZhcpZcjg(){
	var url = "general_pjpy.do?method=zhcpResult&forward=jbsz";
	refreshForm(url);
}

//��ʾ������ĿDIV
function showAddZcxmDiv(){
	
	jQuery("#input_xmmc").val("");
	jQuery("#hidden_xmmc").val("");
	jQuery("#input_jjf").val("+");
	//jQuery("#input_lrly").val("no");

	//-------------------������ֵ--------------------
	var i = 0;
	var bldm = new Array();				  
	jQuery("input[name=bldm]").each(function(){bldm[i] = jQuery(this).val();i++;});
	
	for(var j=0;j<bldm.length;j++){
		$("input_bl_"+bldm[j]).disabled = false;
		jQuery("#input_bl_"+bldm[j]).val("100");
	}
	//-------------------������ֵ end--------------------
	
	$("btn_add_save").style.display = "";
	$("btn_edit_save").style.display = "none";
	tipsWindown("ϵͳ��ʾ","id:div_zcxm","350","250","true","","true","id");
	
	if($("input_xmmc")){
		$("input_xmmc").focus();
	}
}

//��ʾ�޸���ĿDIV
function showEditZcxmDiv(){
	
	var xmdm = jQuery("#czxm").val();//��Ŀ����
	var xmmc = jQuery("#xmmc_"+xmdm).val();//��Ŀ����	
	var xmjb = jQuery("#xmjb_"+xmdm).val();//��Ŀ����
	var jjf = jQuery("#jjf_"+xmdm).val();//�Ӽ���	
	//var lrly = jQuery("#lrly_"+xmdm).val();//¼������
	
	jQuery("#input_xmmc").val(xmmc);
	jQuery("#hidden_xmmc").val(xmmc);
	jQuery("#input_jjf").val(jjf);
	//jQuery("#input_lrly").val(lrly);

	//-------------------������ֵ--------------------
	var i = 0;
	var bldm = new Array();				  
	jQuery("input[name=bldm]").each(function(){bldm[i] = jQuery(this).val();i++;});

	for(var j=0;j<bldm.length;j++){
		var bl_id = "bl_"+xmdm+"_"+bldm[j];
		var bl = jQuery("#"+bl_id).val();
		
		if(bl == ""){
			bl = "0";
		}

		if(xmjb == "1"){
			$("input_bl_"+bldm[j]).value="0";
			$("input_bl_"+bldm[j]).disabled = true;
		}else{
			$("input_bl_"+bldm[j]).disabled = false;
			jQuery("#input_bl_"+bldm[j]).val(bl);
		}		
	}
	//-------------------������ֵ end--------------------

	$("btn_add_save").style.display = "none";
	$("btn_edit_save").style.display = "";
	tipsWindown("ϵͳ��ʾ","id:div_zcxm","350","250","true","","true","id");
}

//��Ᵽ���۲���Ŀ����һ����
function checkSaveNextZcxm(){
	var sjdm = jQuery("#czxm").val();
	var xmmc = jQuery("#input_xmmc").val();

	if(xmmc == ""){
		alertError("��Ŀ���Ʋ���Ϊ�գ�����ȷ��");
		return false;
	}else{
		var xmmc_num = jQuery("input[name=xmmc]").length;
		for(var i=0;i<xmmc_num;i++){
			var obj=jQuery("input[name=xmmc]").eq(i);
			if(jQuery(obj).val() == xmmc){
				alertError("����Ŀ�Ѿ����ڣ���ȷ��");
				return false;
			}
		}
	}

	confirmInfo('��ȷ���Ƿ����Ӹ��۲���Ŀ?',saveNextZcxm);
}

//�����۲���Ŀ����һ����
function saveNextZcxm(tag){
	if(tag == "ok"){
		jQuery.ajaxSetup({async:false});
		
		var url = "general_pjsz_zcxm_ajax.do?method=saveNextZcxm";
		//�ϼ�����
		var sjdm = jQuery("#czxm").val();
		//��Ŀ����
		var xmmc = jQuery("#input_xmmc").val();
		//�Ӽ���
		var jjf = jQuery("#input_jjf").val();
		//¼������
		//var lrly = jQuery("#input_lrly").val();
		//��������	
		var i = 0;
		var bldm = new Array();				  
		jQuery("input[name=bldm]").each(function(){bldm[i] = jQuery(this).val();i++;});
		//����
		i = 0;
		var bl = new Array();
		jQuery("input[name=bl]").each(function(){
			if(jQuery(this).val() !=""){
				bl[i] = jQuery(this).val();
			}else{
				bl[i] = "0";
			}	
			i++;
		});
		
		//����
	 	var parameter = {
	 		"sjdm":sjdm,
	 		"xmmc":escape(xmmc),
	 		"jjf":jjf,
	 		//"lrly":lrly,
	 		"bldm":bldm.join("!!@@!!"),
	 		"bl":bl.join("!!@@!!")
		};

	 	$("divWaiting").style.display="";
		$("divDisable").style.display="";
		
		jQuery.post(url,
			parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";						
				alertInfo(result,function(){goPjszZcxm();});
				closeWindown();	
			}
		);
		
		jQuery.ajaxSetup({async:true});
	}
}

//ɾ���۲���Ŀ
function deleteZcxm(tag){
	if(tag == "ok"){
		jQuery.ajaxSetup({async:false});
		
		var url = "general_pjsz_zcxm_ajax.do?method=deleteZcxm";
		//��Ŀ����
		var xmdm = jQuery("#czxm").val();
		
		//����
	 	var parameter = {
	 		"xmdm":xmdm
		};

	 	$("divWaiting").style.display="";
		$("divDisable").style.display="";
		
		jQuery.post(url,
			parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";						
				alertInfo(result,function(){goPjszZcxm();});
				closeWindown();	
			}
		);
		
		jQuery.ajaxSetup({async:true});
	}
}

//��Ᵽ���۲���Ŀ���޸ı�����
function checkSaveEditZcxm(){
	var xmdm = jQuery("#czxm").val();
	var xmmc = jQuery("#input_xmmc").val();
	var hidden_xmmc = jQuery("#hidden_xmmc").val();
	if(xmmc == ""){
		alertError("��Ŀ���Ʋ���Ϊ�գ�����ȷ��");
		return false;
	}else if(hidden_xmmc != xmmc){
		var xmmc_num = jQuery("input[name=xmmc]").length;
		for(var i=0;i<xmmc_num;i++){
			var obj=jQuery("input[name=xmmc]").eq(i);
			if(jQuery(obj).val() == xmmc){
				alertError("����Ŀ�Ѿ����ڣ���ȷ��");
				return false;
			}
		}
	}
	confirmInfo('��ȷ���Ƿ񱣴汾���޸�?',saveEditZcxm);
}

//�����۲���Ŀ���޸ı�����
function saveEditZcxm(tag){
	if(tag == "ok"){
		jQuery.ajaxSetup({async:false});
		
		var url = "general_pjsz_zcxm_ajax.do?method=saveEditZcxm";
		//��Ŀ����
		var xmdm = jQuery("#czxm").val();
		//��Ŀ����
		var xmmc = jQuery("#input_xmmc").val();
		//�Ӽ���
		var jjf = jQuery("#input_jjf").val();
		//¼������
		//var lrly = jQuery("#input_lrly").val();
		//��������	
		var i = 0;
		var bldm = new Array();				  
		jQuery("input[name=bldm]").each(function(){bldm[i] = jQuery(this).val();i++;});
		//����
		i = 0;
		var bl = new Array();
		jQuery("input[name=bl]").each(function(){
			if(jQuery(this).val() !=""){
				bl[i] = jQuery(this).val();
			}else{
				bl[i] = "0";
			}	
			i++;
		});
		
		//����
	 	var parameter = {
	 		"xmdm":xmdm,
	 		"xmmc":escape(xmmc),
	 		"jjf":jjf,
	 		//"lrly":lrly,
	 		"bldm":bldm.join("!!@@!!"),
	 		"bl":bl.join("!!@@!!")
		};

	 	$("divWaiting").style.display="";
		$("divDisable").style.display="";
		
		jQuery.post(url,
			parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";						
				alertInfo(result,function(){goPjszZcxm();});
				closeWindown();	
			}
		);
		
		jQuery.ajaxSetup({async:true});
	}
}

//================���ۺ����ʲ�����end===================================

//================��������Ŀ���á�begin=================================

//ǰ��������Ŀ����
function goPjszPjxm(){
	var url = "general_pjpy.do?method=pjszPjxm";
	refreshForm(url);
}

//��ʾ������Ŀ����
function showPjszPjxm(){
	var url = "general_pjpy.do?method=pjxmSetting";
	showTopWin(url,"720","500");
}

//��ʾ������Ŀ�޸�
function showPjxmUpdate(){
	
	var len=jQuery("[name=primarykey_checkVal]:checked").length;
	
	if(len==1){	
		var pkValue=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();
		var url="general_pjpy.do?method=pjxmUpdate";
			url+="&pkValue="+pkValue;
		
		showTopWin(url,800,600);
	}else{	
		alertError("��<font color='blue'>��ѡһ��</font>��ϣ���޸ĵļ�¼��");	
		return false;
	}
}

//ɾ��������Ŀ
function deletePjxm(){
	
	var len=jQuery("[name=primarykey_checkVal]:checked").length;
	var flag = true;
	
	jQuery("[name=primarykey_checkVal]:checked").each(function(){
		var sfsq = jQuery(this).parents().children("td").eq(4).html();
		if(sfsq == "��"){
			flag = false;
			alertError("��ѡ��Ŀ�Ѿ�<font color='blue'>���������¼</font>���޷���ɾ��");
		}
	});
	
	if(flag){	
		if(len!=0){	
			confirmInfo("�ò�������<font color='blue'>ɾ���ѹ�ѡ</font>��������Ŀ,�Ƿ�ȷ��ִ�иò�����",function(tag){
				if(tag=="ok"){
					var pkValue=new Array();
					jQuery("[name=primarykey_checkVal]:checked").each(function(i){				
						pkValue[i]=jQuery(this).val();			
					});
					
					var parameter={}
					parameter["array_pkValue"]=escape(pkValue.join("!!array!!"));
					
					var url="general_pjsz_pjxm_ajax.do?method=deletePjxm";
					
					jQuery.ajaxSetup({async:false});	
					
					jQuery.post(url,
						parameter,
						function(result){
							alertInfo(result,function(tag){
								if(tag=="ok"){
									searchRs();
							}
						});
					}
				);
			}
		});	
	}else{
		alertError("����<font color='blue'>��ѡ</font>ϣ��ɾ���ļ�¼��");	
		return false;
	}
	}
}

//��ʾ��Ŀ������ϸDiv
function showXmxzDetail(){
	jQuery("#bjdlmc").val("");
	tipsWindown("ϵͳ��ʾ","id:div_xmxz_detail","300","250","true","","true","id");
}
//================��������Ŀ���á�end===================================

//================�������������á�begin=================================

//��ʾ������������
function showXmszPjtj(xmdm){

	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
	if(num == "0" && xmdm==""){
		alertError("��<font color='blue'>��ѡ</font>ϣ��������������Ŀ");
		return false;
	}else if(num != "1"  && xmdm==""){
		alertError("ֻ��<font color='blue'>��ѡһ��</font>��Ŀ�������ã���ȷ��");
		return false;
	}else if(xmdm==""){
		var xmdm = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").eq(0).val();
	}
	var url = "general_pjpy.do?method=xmszPjtj";
		url+= "&xmdm="+xmdm; 
	showTopWin(url,"800","600");
}

//������������
function addTjsz(){

	var num = jQuery("#num").val();
	
	var divHtml = jQuery("#div_tjsz").html();
		divHtml += "<div id=\"div_tjsz_"+num+"\">";
		divHtml += "<table width=\"100%\" border=\"0\">";
		divHtml += "<tr>";
		
		divHtml += "<td width=\"5%\">";//checkBox
		divHtml += "<input type=\"checkbox\" name=\"checkVal\" value=\""+num+"\"/>";
		divHtml += "</td>";

		divHtml += "<td width=\"40%\">";//����
		divHtml += "";
		divHtml += "<select name=\"array_tjdm_sz\" ";
		divHtml += "onchange=\"defaultPjtjInfo(this.value,'"+num+"')\" ";
		divHtml += "id=\"select_tjdm_"+num+"\">";
		divHtml += jQuery("#select_pjtj").html();
		divHtml += "</select>";
		divHtml += "</td>";

		divHtml += "<td width=\"10%\">";//��ϵ
		divHtml += "<select name=\"array_gx_sz\" id=\"select_gx_"+num+"\">";
		divHtml += "</select>";
		divHtml += "</td>";

		divHtml += "<td width=\"10%\">";//����ֵ
		divHtml += "<input type=\"text\" name=\"array_tjz_sz\" ";
		divHtml += "onkeydown=\"return onlyNum(this,5)\" ";
		divHtml += "onmousedown=\"return onlyNum(this,5)\" ";
		divHtml += "maxlength=\"5\"";
		divHtml += "style=\"width:40px;ime-mode:disabled\" ";
		divHtml += "id=\"input_tjz_"+num+"\" />";
		divHtml += "<span id=\"span_tsgs_"+num+"\"></span>";
		divHtml += "</td>";

		divHtml += "<td width=\"\">";//���÷�Χ����
		divHtml += "<select name=\"array_tjfw_sz\" id=\"select_tjfw_"+num+"\">";
		divHtml += jQuery("#select_tjfw").html();
		divHtml += "</select>";
		divHtml += "</td>";
		
		divHtml += "</tr>";
		divHtml += "</table>";
		divHtml += "</div>";

		jQuery("#div_tjsz").html("");
		jQuery("#div_tjsz").html(divHtml);
		
		num++;

		jQuery("#num").val(num);
}

//ɾ����������
function delTjsz(){
	
	var num = jQuery("input[type=checkbox][name=checkVal]:checked").length;
	
	for(var i=(num-1);i>=0;i--){
		var div_id = "div_tjsz_"+jQuery("input[type=checkbox][name=checkVal]:checked").eq(i).val();
		if($(div_id)){
			jQuery("#"+div_id).remove();
		}
	}
}

//��ʼ������������Ϣ
function defaultPjtjInfo(tjdm,num){

	var xmdm = jQuery("#xmdm").val();
	var url = "general_xmsz_pjtj_ajax.do?method=defaultPjtjInfo";
		url+= "&tjdm="+tjdm;

	jQuery.ajaxSetup({async:false});
		
	jQuery.post(url,
		{},
		function(result){
			var tsgs = result.split("!!luojw!!")[0];//�����ʽ
			var tjz = result.split("!!luojw!!")[1];//����ֵ
			var gx_id = "select_gx_"+num;
			var tsgs_id = "span_tsgs_"+num;
			var tjz_id = "input_tjz_"+num;
			
			jQuery("#"+gx_id).html("");
			jQuery("#"+tsgs_id).html("");
			if(tjz != "" && tjz != null && tjz != "null"){
				jQuery("#"+gx_id).append("<option value='='>=</option>");
				jQuery("#"+tjz_id).val(tjz);
				jQuery("#"+tjz_id).attr("readonly",true);
			}else{
				jQuery("#"+gx_id).append("<option value='>'>></option>");
				jQuery("#"+gx_id).append("<option value='>='>>=</option>");
				jQuery("#"+gx_id).append("<option value='='>=</option>");
				jQuery("#"+gx_id).append("<option value='<='><=</option>");
				jQuery("#"+gx_id).append("<option value='<'><</option>");

				jQuery("#"+tjz_id).val("");
				jQuery("#"+tjz_id).attr("readonly",false);
			}

			if(tsgs == "%"){
				jQuery("#"+tsgs_id).html("%");
			}
	});
	
	jQuery.ajaxSetup({async:true});
}

//��Ᵽ����������
function checkSavePjtj(){

	//������
	var num = jQuery("select[name=array_tjdm_sz]").length;
	var flag = true;
	
	//����
	var i=0;
	if(flag){
		jQuery("select[name=array_tjdm_sz]").each(
			function(){
				var tjdm = jQuery(this).val();
				if(tjdm == "" && flag){
					alertError("��<font color='blue'>"+(i+1)+"</font>��<font color='blue'>����</font>����Ϊ�գ���ȷ��^_^||");
					flag = false ;
					
				}
				i++;
			}
		);
	}

	if(flag){
		//����ֵ
		i=0;
		jQuery("input[name=array_tjz_sz]").each(
			function(){
				var tjz = jQuery(this).val();
				if(tjz == ""  && flag){
					alertError("��<font color='blue'>"+(i+1)+"</font>��<font color='blue'>����ֵ</font>����Ϊ�գ���ȷ��^_^||");
					flag = false;
				}
				i++;
			}
		);
	}

	//�ж��Ƿ������ظ�
	if(flag){
		for(var j=0;j<num;j++){
			//��������
			var tjdm = jQuery("select[name=array_tjdm_sz]").eq(j).val();
			//������Χ
			var tjfw = jQuery("select[name=array_tjfw_sz]").eq(j).val();
			
			for(var k=(j+1);k<num;k++){
				if(tjdm == jQuery("select[name=array_tjdm_sz]").eq(k).val()
				&& tjfw == jQuery("select[name=array_tjfw_sz]").eq(k).val()){
					alertError("��<font color='blue'>"+(j+1)+"</font>���<font color='blue'>"+(k+1)+"</font>��<font color='blue'>�����뷶Χ��ͬ</font>�����ǲ������^_^||");
					flag = false;
					return false;
				}
			}
		}
	}

	if(flag){
		if(num == 0){
			confirmInfo("��ϣ��<font color='blue'>���</font>����Ŀ������������",deletePjtj);
		} else{
			confirmInfo("����ִ��<font color='blue'>����</font>�������������������ٴ�ȷ��^_^!!",savePjtj);
		}
	}
}

//������������
function savePjtj(tag){
	if(tag == "ok"){
		jQuery.ajaxSetup({async:false});
		
		var url = "general_xmsz_pjtj_ajax.do?method=savePjtj";

	 	//����һ��json����
		var parameter={};
		
		//ָ����ȡ�Ŀؼ����ͣ�����ѭ��
		var hid_obj=jQuery("input").each(function(){
			
			//��ȡ���ؼ�name
			var name=jQuery(this).attr("name");
			//����json����
			parameter[name]=escape(jQuery(this).val());
		});

		var array = ["array_tjdm_sz","array_tjfw_sz","array_gx_sz","array_tjz_sz"];
		for(var i=0;i<array.length;i++){
			var array_value=new Array();
			
			jQuery("[name="+array[i]+"]").each(function(j){
				array_value[j] =escape(jQuery(this).val());
			});
			parameter[array[i]]=array_value.join("!!array!!");
		}

	 	$("divWaiting").style.display="";
		$("divDisable").style.display="";
		
		jQuery.post(url,
			parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				alertInfo(result);
			}
		);
		
		jQuery.ajaxSetup({async:true});
	}
}

//ɾ����������
function deletePjtj(tag){
	if(tag == "ok"){
		
		jQuery.ajaxSetup({async:false});
		
		var url = "general_xmsz_pjtj_ajax.do?method=deletePjtj";
		//��Ŀ����
		var xmdm = jQuery("#xmdm").val();
	 	//����һ��json����
		var parameter={"str_xmdm":xmdm};

	 	$("divWaiting").style.display="";
		$("divDisable").style.display="";
		
		jQuery.post(url,
			parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				alertInfo(result);
			}
		);
		
		jQuery.ajaxSetup({async:true});
	}
}

//================�������������á�end===================================

//================�������������á�begin===================================

//��ʾ��������
function showXmszRssz(xmdm){
	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
	if(num == "0" && xmdm ==""){
		alertError("��<font color='blue'>��ѡ</font>ϣ��������������Ŀ");
		return false;
	}else if(num != "1" && xmdm ==""){
		alertError("ֻ��<font color='blue'>��ѡһ��</font>��Ŀ�������ã���ȷ��");
		return false;
	}else if(xmdm==""){
		xmdm = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").eq(0).val();
	}

	var url = "general_pjsz_pjxm_ajax.do?method=getPjxmInfo";
	url+= "&xmdm="+xmdm;

	jQuery.ajaxSetup({async:false});
	
	jQuery.post(url,
		{},
		function(result){
			var rssz = result.split("!!luojw!!")[0];//��������
			var kzfw = result.split("!!luojw!!")[1];//���Ʒ�Χ
			if(rssz == "no"){
				alertError("����Ŀ<font color='blue'>����Ҫ</font>�������ƣ�����ȷ��~^_^||");
				return false;
			}else{

				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				url = "general_pjpy.do?method=xmszRssz";
				url+= "&xmdm="+xmdm;
				refreshForm(url);
			}
		}
	);
	
//	jQuery.ajax({
//		type:'post',
//		url:url,
//		dataType:'json',
//		async: false,
//		success:function(result){
//
//		}
//	});

	jQuery.ajaxSetup({async:true});
}

//��ʾ���ñ���Div
function showSzblDiv(){
	tipsWindown("ϵͳ��ʾ","id:div_szbl","300","200","true","","true","id");
}

//��Ᵽ�����ñ���
function checkSaveSzbl(){

	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
	var szbl = jQuery("#szbl").val();

	if(szbl == ""){
		alertError("��������ϣ�����õ�<font color='blue'>����</font>");
		return false;
	}
	
	if(num == 0){//����
		//�꼶
		var nj_num = jQuery("a[name=a_name_nj]").length; 			  		
		//ѧԺ
		var xy_num = jQuery("a[name=a_name_xy]").length; 		
		//רҵ
		var zy_num = jQuery("a[name=a_name_zy]").length; 
		//�༶
		var bj_num = jQuery("a[name=a_name_bj]").length; 
		
		confirmInfo("��ȷ���Ƿ�<font color='blue'>������������ָ���Ĳ���</font>����Ϊ�ñ���?",saveSzblNoChecked);
		
	}else{//��ѡ
		confirmInfo("��ȷ���Ƿ�<font color='blue'>����ѡ����</font>����Ϊ�ñ���?",saveSzplChecked);
	}
}

//�������ñ������ã�δѡ�У�
function saveSzblNoChecked(tag){
	if(tag == "ok"){
		jQuery.ajaxSetup({async:false});

		//�꼶
		var nj = new Array();  
		var i = 0;				  
		jQuery("a[name=a_name_nj]").each(function(){
			var nj_id = jQuery(this).attr("id");
			nj[i] = nj_id.replace("a_id_","");
			i++;
		});
		
		//ѧԺ
		var xy = new Array(); 
		i = 0;			  
		jQuery("a[name=a_name_xy]").each(function(){
			var xy_id = jQuery(this).attr("id");
			xy[i] = xy_id.replace("a_id_","");
			i++;
		});

		//רҵ
		var zy = new Array(); 
		i = 0;					  
		jQuery("a[name=a_name_zy]").each(function(){
			var zy_id = jQuery(this).attr("id");
			zy[i] = zy_id.replace("a_id_","");
			i++;
		});

		//�༶
		var bj = new Array();  				  
		jQuery("a[name=a_name_bj]").each(function(){
			var bj_id = jQuery(this).attr("id");
			bj[i] = bj_id.replace("a_id_","");
			i++;
		});

		var xmdm = jQuery("#xmdm").val();
		var szbl = jQuery("#szbl").val();
		var url = "general_xmsz_rssz_ajax.do?method=saveSzblNoChecked";
		
		//����
	 	var parameter = {
	 		"xmdm":xmdm,
	 		"szbl":szbl,
	 		"nj":nj.join("!!@@!!"),
	 		"xy":xy.join("!!@@!!"),
	 		"zy":zy.join("!!@@!!"),
	 		"bj":bj.join("!!@@!!")
		};

	 	$("divWaiting").style.display="";
		$("divDisable").style.display="";
		
		jQuery.post(url,
			parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				alertInfo(result);
				searchRs();
				closeWindown();	
			}
		);
		
		jQuery.ajaxSetup({async:true});
	}
}

//�������ñ������ã�ѡ�У�
function saveSzplChecked(tag){
	if(tag == "ok"){
		jQuery.ajaxSetup({async:false});
		
		var pkValue = new Array();//����
		var pkValue_num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
		var count = 0;
		for(var i=0;i<pkValue_num;i++){
			var obj = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked")[i];
			pkValue[count] = obj.value;
			count++;
		}

		var xmdm = jQuery("#xmdm").val();
		var szbl = jQuery("#szbl").val();			
		var url = "general_xmsz_rssz_ajax.do?method=saveSzblChecked";
		
		//����
	 	var parameter = {
 			"xmdm":xmdm,
	 		"szbl":szbl,
	 		"pkValue":pkValue.join("!!@@!!")
		};

	 	$("divWaiting").style.display="";
		$("divDisable").style.display="";
		
		jQuery.post(url,
			parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				alertInfo(result);
				searchRs();
				closeWindown();	
			}
		);

		jQuery.ajaxSetup({async:true});
	}
}

//��Ᵽ����������
function checkSaveZzrs(){
	confirmInfo("����ȷ���Ƿ�<font color='blue'>������������</font>?",saveZzrs);
}

//������������
function saveZzrs(tag){
	if(tag == "ok"){
		jQuery.ajaxSetup({async:false});
		
		var qdrs = new Array();//ȷ������
		var pkValue = new Array();//����
		var num = jQuery("input[name=array_qdrs]").length;
		var count = 0;
		for(var i=0;i<num;i++){
			var obj = jQuery("input[name=array_qdrs]")[i];
			var zzrs = obj.value;
			if(zzrs == ""){
				zzrs = "0";
			}
			qdrs[count] = zzrs;
			pkValue[count] =escape( obj.id.replace("input_qdrs_",""));
			count++;
		}

		var xmdm = jQuery("#xmdm").val();
		var szbl = jQuery("#szbl").val();			
		var url = "general_xmsz_rssz_ajax.do?method=saveQdrs";
		
		//����
	 	var parameter = {
	 		"xmdm":xmdm,
	 		"qdrs":qdrs.join("!!@@!!"),
	 		"pkValue":pkValue.join("!!@@!!")
		};

	 	$("divWaiting").style.display="";
		$("divDisable").style.display="";
		
		jQuery.post(url,
			parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				alertInfo(result);
				searchRs();
				closeWindown();	
			}
		);

		jQuery.ajaxSetup({async:true});
	}
}

//================�������������á�end===================================

//================����Ŀ������á�begin===================================

//��ʾ�������
function showXmszXmjd(xmdm){
	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
	if(num == "0" && xmdm==""){
		alertError("��<font color='blue'>��ѡ</font>ϣ�����ò��ɼ�õ���Ŀ");
		return false;
	}else if(num != "1" && xmdm==""){
		alertError("ֻ��<font color='blue'>��ѡһ��</font>��Ŀ�������ã���ȷ��");
		return false;
	}else if(xmdm==""){
		xmdm = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").eq(0).val();
	}

	jQuery("#xmdm").val(xmdm);
	
	jQuery.ajaxSetup({async:false});

	//·��
	var url = "general_xmsz_xmjd_ajax.do?method=defaultJdszSetting";
	//����
 	var parameter = {
		"xmdm":xmdm
	};
	
	$("divWaiting").style.display="";
	$("divDisable").style.display="";

	jQuery("#div_xmjd").load(
		url,
		parameter,
		function(){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
		}
	);
		
	tipsWindown("ϵͳ��ʾ","id:div_xmjd","500","300","true","","true","id");

	jQuery.ajaxSetup({async:true});
}

//��֤������Ŀ���
function checkSaveXmjd(){
	var num = jQuery("input[type=checkbox][name=array_fjddm]:checked").length;
	if(num == "0"){
		confirmInfo('��δ��ѡ��һ��Ŀ������ȡ����ѡ��Ŀ�����в��ɼ�����������ȷ��',deleteXmjd);
	}else {
		confirmInfo('��Ҫִ�б������������ȷ��',saveXmjd);
	}
}

//������Ŀ���
function saveXmjd(tag){
	if(tag == "ok"){
		jQuery.ajaxSetup({async:false});
		
		var url = "general_xmsz_xmjd_ajax.do?method=saveXmjd";
		
	 	//����һ��json����
		var parameter={};
		
		//ָ����ȡ�Ŀؼ����ͣ�����ѭ��
		var array_value=new Array();
		
		jQuery("input[type=checkbox][name=array_fjddm]:checked").each(function(j){
			array_value[j] =escape(jQuery(this).val());
		});
		parameter["array_fjddm"]=array_value.join("!!array!!");

		var xmdm = jQuery("#xmdm").val();
		parameter["str_xmdm"]=escape(xmdm);
		parameter["xmdm"]=escape(xmdm);
		
	 	$("divWaiting").style.display="";
		$("divDisable").style.display="";
		
		jQuery.post(url,
			parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				alertInfo(result);
				searchRs();
				closeWindown();
			}
		);
		
		jQuery.ajaxSetup({async:true});
	}
}

//ɾ����Ŀ���
function deleteXmjd(tag){
	if(tag == "ok"){
		jQuery.ajaxSetup({async:false});
		
		var url = "general_xmsz_xmjd_ajax.do?method=deleteXmjd";
		
	 	//����һ��json����
	 	var xmdm = jQuery("#xmdm").val();
		var parameter={};
		parameter["str_xmdm"]=escape(xmdm);
		
	 	$("divWaiting").style.display="";
		$("divDisable").style.display="";
		
		jQuery.post(url,
			parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				alertInfo(result);
				searchRs();
				closeWindown();
			}
		);
		
		jQuery.ajaxSetup({async:true});
	}
}

//ȫѡ�����Ŀ
function clickAllJdxm(){
	var flag = $("chb_all").checked;
	if(flag){
		jQuery("input[name=array_fjddm]").each(function(){
			jQuery(this).attr("checked","true");
		});
	}else{
		jQuery("input[name=array_fjddm]").each(function(){
			jQuery(this).attr("checked",false);
		});
	}
}
//================����Ŀ������á�end===================================

//================������ʱ�����á�begin===================================

//��ʾʱ������
function showXmszSjsz(xmdm){

	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
	if(num == "0" && xmdm==""){
		alertError("��<font color='blue'>��ѡ</font>ϣ������ʱ����Ƶ���Ŀ");
		return false;
	}else if(num != "1"  && xmdm==""){
		alertError("ֻ��<font color='blue'>��ѡһ��</font>��Ŀ�������ã���ȷ��");
		return false;
	}else if(xmdm==""){
		xmdm = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").eq(0).val();
	}

	jQuery("#xmdm").val(xmdm);
	
	jQuery.ajaxSetup({async:false});

	//·��
	var url = "general_xmsz_sjsz_ajax.do?method=defaultSjszSetting";
	//����
 	var parameter = {
		"xmdm":xmdm
	};
	
	$("divWaiting").style.display="";
	$("divDisable").style.display="";
	
	jQuery("#div_sjsz").load(
		url,
		parameter,
		function(){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
		}
	);
		
	tipsWindown("ϵͳ��ʾ","id:div_sjsz","500","300","true","","true","id");

	jQuery.ajaxSetup({async:true});

}

//��֤����ʱ������
function checkSaveSjsz(){
	var sqkssj = jQuery("#sqkssj").val();
	var sqjssj = jQuery("#sqjssj").val();
	var shkssj = jQuery("#shkssj").val();
	var shjssj = jQuery("#shjssj").val();

	if(sqkssj != "" && sqjssj !=""){
		if(sqkssj > sqjssj){
			alertError("���뿪ʼʱ��<font color='blue'>����</font>����ʱ�䣬��ȷ��^_^||");
			return false;
		}
	}

	if(shkssj != "" && shjssj !=""){
		if(shkssj > shjssj){
			alertError("��˿�ʼʱ��<font color='blue'>����</font>����ʱ�䣬��ȷ��^_^||");
			return false;
		}
	}
	
	confirmInfo('��Ҫִ�б������������ȷ��',saveSjsz);
}

//����ʱ������
function saveSjsz(tag){

	if(tag == "ok"){

		jQuery.ajaxSetup({async:false});
		
		var url = "general_xmsz_sjsz_ajax.do?method=saveSjsz";
		var xmdm = jQuery("#xmdm").val();
		
		//����һ��json����
		var parameter={};
		
		//ָ����ȡ�Ŀؼ����ͣ�����ѭ��
		var hid_obj=jQuery("input,textarea").not(jQuery("[name=array_fjddm]")).each(function(){
			
			//��ȡ���ؼ�name
			var name=jQuery(this).attr("name");
			//����json����
			parameter[name]=escape(jQuery(this).val());
		});
		
	 	$("divWaiting").style.display="";
		$("divDisable").style.display="";
		
		jQuery.post(url,
			parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				alertInfo(result);
				searchRs();
				closeWindown();	
			}
		);

		jQuery.ajaxSetup({async:true});
	}
}

//================������ʱ�����á�end===================================

//================���ҵ�������begin===================================

function goPjpyWdpj(){
	var url = "pjpy_general_wdpj.do";
	refreshForm(url);
}
//================���ҵ�������end===================================

//================��������ʷ��Ϣ��begin===================================

//ǰ����ʷ����
function goWdpjLspj(){
	var url = "general_pjpy.do?method=wdpjLspj&forward=jbsz";
	refreshForm(url);
}

//��ʾ��ʷ����ά��
function showWdpjLspj(doType){
	var url = "general_pjpy.do?method=lspjUpdate";
	url+= "&doType="+doType;
		
	if(doType == "edit" || doType == "view"){
		var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
		if(num == "0"){
			alertError("�빴ѡһ����ϣ�������ļ�¼");
			return false;
		}else if(num != "1"){
			alertError("ֻ�ܹ�ѡһ����¼���в�������ȷ��");
			return false;
		}

		var pkValue = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").eq(0).val();
		url+= "&pkValue="+pkValue;
	}
		
	showTopWin(url,"600","480");
}

//���ɾ����ʷ����
function checkDelWdpjLspj(){

	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
	if(num == "0"){
		alertError("�빴ѡ��ϣ��ɾ���ļ�¼");
		return false;
	}
	
	confirmInfo("��Ҫɾ������ѡ�ļ�¼����ȷ��",delWdpjLspj);
}

//ɾ����ʷ����
function delWdpjLspj(tag){
	if(tag == "ok"){
	
		var parameter ={};
		var pkValue = "";//����
		var i=0;
		jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").each(function(){
			pkValue+=escape(jQuery(this).val())+"!!array!!";
		});
		//����json����
		parameter["array_pkValue"]=pkValue;
		
		var url = "general_wdpj_jgcx_ajax.do?method=deletePjlsxx";
		
	 	$("divWaiting").style.display="";
		$("divDisable").style.display="";
		
		jQuery.ajaxSetup({async:false});
		
		jQuery.post(url,
			parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				alertInfo(result);
				searchRs();
			}
		);

		jQuery.ajaxSetup({async:true});
	}
}

//================��������ʷ��Ϣ��end===================================














//ǰ�������ѯ
function goWdpjJgcx(){
	var url = "general_pjpy.do?method=wdpjJgcx";
	refreshForm(url);
}

//ǰ����Ŀ���By��Ŀ����
function goWdpjXmshByXm(xmdm){
	var url = "general_pjpy.do?method=wdpjXmsh";
	url+="&xmdm="+xmdm;
	refreshForm(url);
}

//=================ͨ�÷��� begin==============================
//����ؼ���ֵ
function setCheckedValue(obj){
	var id = obj.id;
	var value = id.split("_")[1];
	var checked_id = id.split("_")[0]+"_check";
	
	if($(checked_id)){
		$(checked_id).value = value;
	}
}
//=================ͨ�÷��� end================================