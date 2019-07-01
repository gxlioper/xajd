//显示评奖结束
function showEndPjpy(){
	var url = "general_pjpy.do?method=pjpyEnd";
	showTopWin(url,"800","650");
}

//前往我的评奖
function goPjpyWdpj(){
	var url = "pjpy_general_wdpj.do";
	refreshForm(url);
}

//前往学生申请
function goWdpjXssq(){
	var url = "general_pjpy.do?method=wdpjXssq";
	refreshForm(url);
}

//前往学生结果
function goWdpjXsjg(ckxx){
	var url = "general_pjpy.do?method=wdpjXssqJgcx&ckxx="+ckxx;
	refreshForm(url);
}

//老师上报信息
function goWdpjLssb(){
	var url = "general_pjpy.do?method=xmsbManage";
	
	url+= "&xmdm="+$("xmdm").value;
	url+= "&bjdm="+$("bj").value;
			
	showWaitingDiv("30000");
	
	refreshForm(url);
}

//前往项目审核
function goWdpjXmsh(xmdm,spgw){
	var url = "general_pjpy.do?method=xmshManage";
	url+="&xmdm="+xmdm;
	url+="&spgw="+spgw;
	refreshForm(url);
}

//显示项目审核详细
function showWdpjXmsh(xh){

	var xmdm=$("xmdm").value;
	var spgw=$("spgw").value;
	
	var url = "general_pjpy.do?method=xmshDetail";
	url+="&xh="+xh;
	url+="&xmdm="+xmdm;
	url+="&spgw="+spgw;
	showTopWin(url,"800","600");
}

//前往本次评奖
function goWdpjBcpj(){
	var url = "general_pjpy.do?method=wdpjBcpj";
	refreshForm(url);
}

//显示本次评奖结果
function showWdpjBcpj(){
	var url = "general_pjpy.do?method=bcpjDetail";
	showTopWin(url,"800","600");
}

//================【评奖流程设置】begin=================================

//显示流程定义
function showLcdy(){
	var url = "general_pjpy.do?method=pjlcSetting";
	showTopWin(url,"800","650");
}

//================【评奖流程设置】end=================================

//================【开始新评奖】begin=================================

//显示开始新评奖
function showNewPjpy(){
	var url = "general_pjpy.do?method=pjpyStart";
	showTopWin(url,"800","650");
}

//点击radio控件
function checkRadio(obj){
	var id = obj.id.split("_")[0]+"_checked";
	var value = obj.value;
	jQuery("#"+id).val(value);
}

//检测保存评奖流程
function checkSaveStart(){
	
	var pjzq = $("pjzq_checked").value;//评奖周期
	var pjxn = $("pjxn").value;//评奖学年
	var pjxq = "";//评奖学期
	if(pjzq == "xn"){
		pjxq = "";
	}else if(pjzq == "xq"){
		pjxq = $("pjxq").value;
	}
	
	var flag = true;
	
	if(pjxn == ""){
		alertError("评奖学年不能为空,请确认");
		flag = false;
	}else if(pjzq == "xq" && pjxq == ""){
		alertError("评奖学期不能为空,请确认");
		flag = false;
	}
	
	if(flag){
		confirmInfo('请您确认您的设置项',saveStart);
	}
}

//保存开始新评奖
function saveStart(tag){

	if(tag == "ok"){
		var pjzq = $("pjzq_checked").value;//评奖周期
		var pjxn = $("pjxn").value;//评奖学年
		var pjxq = "";//评奖学期
		if(pjzq == "xn"){
			pjxq = "";
		}else if(pjzq == "xq"){
			pjxq = $("pjxq").value;
		}
		
		var cpz = $("cpz_checked").value;//参评组
		var zcpm = $("zcpm_checked").value;//综测排名
		var zypm = $("zypm_checked").value;//智育排名
		var start = $("hidden_start").value;//是否开始新评奖
		
		var url = "general_pjpy_index_ajax.do?method=saveStart";

		//参数
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

//设置开始评奖备注
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

//点击参评组
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
//================【开始新评奖】end===================================

//================【评奖基本设置】begin===============================

//前往评奖基本设置
function goPjpyJbsz(){
	var url = "pjpy_general_index.do";
	refreshForm(url);
}

//初始化已定制评奖流程
function defaultCustomPjlc(){
	
	//路径
	var url = "general_pjpy_index_ajax.do?method=defaultCustomPjlc";
	//参数
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

//检测提交评奖流程
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
	
	confirmInfo('请您确认是否提交本步操作',submitPjlc);
}

//提交评奖流程
function submitPjlc(tag){

	var lcdj = "";
	
	if($("lcdj_submit")){
		lcdj = jQuery("#lcdj_submit").val();//流程等级
	}else{
		lcdj = "6";
	}

	if(tag == "ok"){
		
		var url = "general_pjpy_index_ajax.do?method=submitPjlc";

		//参数
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

//================【评奖基本设置】end=================================

//================【评奖人员设置】begin===================================

//前往评奖人员设置
function goPjszPjry(){
	var url = "general_pjpy.do?method=pjszPjry&forward=jbsz";
	refreshForm(url);
}

//显示班级调整Div
function showBjtzDiv(){

	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;

	if(num == 0){
		alertError("请您<font color='blue'>勾选</font>需要调整班级的学生记录");
		return false;
	}else{
		tipsWindown("系统提示","id:div_bjtz","360","300","true","","true","id");
	}
}

//检测保存班级调整
function checkSaveBjtz(){
	var bj = jQuery("#bj").val();
	var bjmc = "";
	
	if(bj == ""){
		alertError("班级不能为空，请您确认所<font color='blue'>勾选</font>学生需要调整到什么<font color='blue'>班级</font>");
		return false;
	}else{
		jQuery("#bjdm_check").val(bj);
		bjmc = jQuery('option[selected=true]',jQuery("#bj")).text();
	}
	
	var message = "";
		message+= "您确定将<font color='blue'>所勾选</font>的学生调整到<font color='blue'>";
		message+= bjmc;
		message+= "</font>进行评奖吗？<br/>";
		message+= "注：调整后，评奖条件，评奖人数等将以调整班级为准。";
		
	confirmInfo(message,saveBjtz);
}

//保存班级调整
function saveBjtz(tag){
	if(tag == "ok"){

		jQuery.ajaxSetup({async:false});
		
		var xh = new Array();//学号
		var xh_num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
		var count = 0;
		for(var i=0;i<xh_num;i++){
			var obj = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked")[i];
			xh[count] = obj.value;
			count++;
		}

		var bjdm = jQuery("#bjdm_check").val();//班级代码
		var bjmc = $("bj").options[$("bj").selectedIndex].text;//班级名称
		
		var url = "general_pjsz_pjry_ajax.do?method=saveBjtz";
		
		//参数
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

//显示是否参评Div
function showSfcpDiv(){
	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;

	if(num == 0){
		alertError("请您<font color='blue'>勾选</font>需要设置的学生记录");
		return false;
	}else{
		tipsWindown("系统提示","id:div_cpsz","300","200","true","","true","id");
	}
}

//检测保存参评设置
function checkSaveCpsz(){
	var sfcp = jQuery("input[type=radio][name=sfcp]:checked").val();
	var message = "";
	
	if(sfcp == "yes"){
		message+= "执行确定操作后，<font color='blue'>勾选</font>的学生将<font color='blue'>获得</font>资格评奖。";
	}else{
		message+= "执行确定操作后，<font color='blue'>勾选</font>的学生将<font color='blue'>失去</font>资格评奖。";
	}
	
	confirmInfo(message,saveCpsz);
}

//保存参评设置
function saveCpsz(tag){
	if(tag == "ok"){

		jQuery.ajaxSetup({async:false});
		
		var xh = new Array();//学号
		var xh_num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
		var count = 0;
		for(var i=0;i<xh_num;i++){
			var obj = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked")[i];
			xh[count] = obj.value;
			count++;
		}

		var sfcp = jQuery("#sfcp_check").val();//是否参评
		
		var url = "general_pjsz_pjry_ajax.do?method=saveCpsz";
		
		//参数
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
//================【评奖人员设置】end===================================

//================【参评小组设置】begin============================
//前往参评小组设置
function goPjszCpxz(){
	var url = "general_pjpy.do?method=pjszCpxz&forward=jbsz";
	refreshForm(url);
}

//显示参评组自动设置DIV
function showCpxzZdszDiv(){
	tipsWindown("系统提示","id:div_cpxz_zdsz","300","300","true","","true","id");
}

//保存参评组自动设置
function saveCpxzZdsz(){
	
	var url = "general_pjsz_cpxz_ajax.do?method=saveCpxzZdsz";
	var cpzgz = jQuery("input[type=radio][name=cpzgz]:checked").eq(0).val();

	//参数
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

//检测参评小组提交
function checkCpxzSubmit(){

	var message = "";
	
	jQuery.ajaxSetup({async:false});
	
	var url = "general_pjsz_cpxz_ajax.do?method=checkCpxzSubmit";
	
	//参数
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
//================【参评小组设置】end============================

//================【班级大类设置】begin===================================

//前往班级大类设置
function goPjszBjdl(){
	var url = "general_pjpy.do?method=pjszBjdl&forward=jbsz";
	refreshForm(url);
}

//前往代码维护
function goPjpjDmwh(){
	var url = "xtwhOther.do?method=xtDmwhNew&ssmk=pjpy";
	refreshForm(url);
}

//检测保存参评小组设置
function checkSaveBjdl(){
	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
	
	if(num == 0){//整体
		//年级
		var nj_num = jQuery("a[name=a_name_nj]").length; 			  		
		//学院
		var xy_num = jQuery("a[name=a_name_xy]").length; 		
		//专业
		var zy_num = jQuery("a[name=a_name_zy]").length; 
		//班级
		var bj_num = jQuery("a[name=a_name_bj]").length; 
		
		confirmInfo("您确定是否将<font color='blue'>过滤条件中所指定的班级</font>设置为该班级大类",saveBjdlNoChecked);
		
	}else{//勾选
		confirmInfo("您确定是否将<font color='blue'>所勾选的班级</font>设置为该班级大类",saveBjdlChecked);
	}
}

//保存班级大类设置（未选中）
function saveBjdlNoChecked(tag){
	if(tag == "ok"){
		jQuery.ajaxSetup({async:false});

		//年级
		var nj = new Array();  
		var i = 0;				  
		jQuery("a[name=a_name_nj]").each(function(){
			var nj_id = jQuery(this).attr("id");
			nj[i] = nj_id.replace("a_id_","");
			i++;
		});
		
		//学院
		var xy = new Array(); 
		i = 0;			  
		jQuery("a[name=a_name_xy]").each(function(){
			var xy_id = jQuery(this).attr("id");
			xy[i] = xy_id.replace("a_id_","");
			i++;
		});

		//专业
		var zy = new Array(); 
		i = 0;					  
		jQuery("a[name=a_name_zy]").each(function(){
			var zy_id = jQuery(this).attr("id");
			zy[i] = zy_id.replace("a_id_","");
			i++;
		});

		//班级
		var bj = new Array();  				  
		jQuery("a[name=a_name_bj]").each(function(){
			var bj_id = jQuery(this).attr("id");
			bj[i] = bj_id.replace("a_id_","");
			i++;
		});
		
		var bjdlmc = jQuery("#select_bjdlmc").val();//班级大类名称

		var url = "general_pjsz_bjdl_ajax.do?method=saveBjdlNoChecked";
		
		//参数
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

//保存班级大类设置（选中）
function saveBjdlChecked(tag){
	if(tag == "ok"){
		jQuery.ajaxSetup({async:false});
		
		var bjdm = new Array();//班级代码
		var bjdm_num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
		var count = 0;
		for(var i=0;i<bjdm_num;i++){
			var obj = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked")[i];
			bjdm[count] = obj.value;
			count++;
		}

		var bjdlmc = jQuery("#select_bjdlmc").val();//班级大类名称				
		var url = "general_pjsz_bjdl_ajax.do?method=saveBjdlChecked";
		
		//参数
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

//检测删除班级大类设置
function checkDeleteBjdl(){
	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;

	if(num == 0){//整体
		alertError("请您<font color='blue'>勾选</font>需要设置的班级！");
		return false;
	}else{
		confirmInfo("请您确认是否取消<font color='blue'>所勾选班级</font>的班级大类设置",deleteBjdlChecked);
	}
}

//删除班级大类设置（选中）
function deleteBjdlChecked(tag){
	if(tag == "ok"){
		jQuery.ajaxSetup({async:false});
		
		var bjdm = new Array();//班级代码
		var bjdm_num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
		var count = 0;
		for(var i=0;i<bjdm_num;i++){
			var obj = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked")[i];
			bjdm[count] = obj.value;
			count++;
		}
			
		var url = "general_pjsz_bjdl_ajax.do?method=deleteBjdlChecked";
		
		//参数
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

//取消参评小组设置（未选中）
function deleteBjdlNoChecked(tag){
	if(tag == "ok"){

		jQuery.ajaxSetup({async:false});

		//年级
		var nj = new Array();  
		var i = 0;				  
		jQuery("a[name=a_name_nj]").each(function(){
			var nj_id = jQuery(this).attr("id");
			nj[i] = nj_id.replace("a_id_","");
			i++;
		});
		
		//学院
		var xy = new Array(); 
		i = 0;			  
		jQuery("a[name=a_name_xy]").each(function(){
			var xy_id = jQuery(this).attr("id");
			xy[i] = xy_id.replace("a_id_","");
			i++;
		});

		//专业
		var zy = new Array(); 
		i = 0;					  
		jQuery("a[name=a_name_zy]").each(function(){
			var zy_id = jQuery(this).attr("id");
			zy[i] = zy_id.replace("a_id_","");
			i++;
		});

		//班级
		var bj = new Array();  				  
		jQuery("a[name=a_name_bj]").each(function(){
			var bj_id = jQuery(this).attr("id");
			bj[i] = bj_id.replace("a_id_","");
			i++;
		});

		var url = "general_pjsz_bjdl_ajax.do?method=deleteBjdlNoChecked";
		
		//参数
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

//显示班级大类详细Div
function showBjdlDetail(){
	jQuery("#bjdlmc").val("");
	tipsWindown("系统提示","id:div_bjdl_detail","300","250","true","","true","id");
}

//================【班级大类设置】end===================================

//================【综合素质测评】begin===================================

//前往综测项目设置
function goPjszZcxm(){
	var url = "general_pjpy.do?method=pjszZcxm&forward=jbsz";
	refreshForm(url);
}

//前往综合测评维护
function goZhcpZcwh(){
	var url = "general_pjpy.do?method=zhcpZcxx&forward=jbsz";
	refreshForm(url);
}

//前往综合测评结果
function goZhcpZcjg(){
	var url = "general_pjpy.do?method=zhcpResult&forward=jbsz";
	refreshForm(url);
}

//显示增加项目DIV
function showAddZcxmDiv(){
	
	jQuery("#input_xmmc").val("");
	jQuery("#hidden_xmmc").val("");
	jQuery("#input_jjf").val("+");
	//jQuery("#input_lrly").val("no");

	//-------------------比例赋值--------------------
	var i = 0;
	var bldm = new Array();				  
	jQuery("input[name=bldm]").each(function(){bldm[i] = jQuery(this).val();i++;});
	
	for(var j=0;j<bldm.length;j++){
		$("input_bl_"+bldm[j]).disabled = false;
		jQuery("#input_bl_"+bldm[j]).val("100");
	}
	//-------------------比例赋值 end--------------------
	
	$("btn_add_save").style.display = "";
	$("btn_edit_save").style.display = "none";
	tipsWindown("系统提示","id:div_zcxm","350","250","true","","true","id");
	
	if($("input_xmmc")){
		$("input_xmmc").focus();
	}
}

//显示修改项目DIV
function showEditZcxmDiv(){
	
	var xmdm = jQuery("#czxm").val();//项目级别
	var xmmc = jQuery("#xmmc_"+xmdm).val();//项目名称	
	var xmjb = jQuery("#xmjb_"+xmdm).val();//项目级别
	var jjf = jQuery("#jjf_"+xmdm).val();//加减分	
	//var lrly = jQuery("#lrly_"+xmdm).val();//录入理由
	
	jQuery("#input_xmmc").val(xmmc);
	jQuery("#hidden_xmmc").val(xmmc);
	jQuery("#input_jjf").val(jjf);
	//jQuery("#input_lrly").val(lrly);

	//-------------------比例赋值--------------------
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
	//-------------------比例赋值 end--------------------

	$("btn_add_save").style.display = "none";
	$("btn_edit_save").style.display = "";
	tipsWindown("系统提示","id:div_zcxm","350","250","true","","true","id");
}

//检测保存综测项目（下一级别）
function checkSaveNextZcxm(){
	var sjdm = jQuery("#czxm").val();
	var xmmc = jQuery("#input_xmmc").val();

	if(xmmc == ""){
		alertError("项目名称不能为空，请您确认");
		return false;
	}else{
		var xmmc_num = jQuery("input[name=xmmc]").length;
		for(var i=0;i<xmmc_num;i++){
			var obj=jQuery("input[name=xmmc]").eq(i);
			if(jQuery(obj).val() == xmmc){
				alertError("该项目已经存在，请确认");
				return false;
			}
		}
	}

	confirmInfo('您确定是否增加该综测项目?',saveNextZcxm);
}

//保存综测项目（下一级别）
function saveNextZcxm(tag){
	if(tag == "ok"){
		jQuery.ajaxSetup({async:false});
		
		var url = "general_pjsz_zcxm_ajax.do?method=saveNextZcxm";
		//上级代码
		var sjdm = jQuery("#czxm").val();
		//项目名称
		var xmmc = jQuery("#input_xmmc").val();
		//加减分
		var jjf = jQuery("#input_jjf").val();
		//录入理由
		//var lrly = jQuery("#input_lrly").val();
		//比例代码	
		var i = 0;
		var bldm = new Array();				  
		jQuery("input[name=bldm]").each(function(){bldm[i] = jQuery(this).val();i++;});
		//比例
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
		
		//参数
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

//删除综测项目
function deleteZcxm(tag){
	if(tag == "ok"){
		jQuery.ajaxSetup({async:false});
		
		var url = "general_pjsz_zcxm_ajax.do?method=deleteZcxm";
		//项目代码
		var xmdm = jQuery("#czxm").val();
		
		//参数
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

//检测保存综测项目（修改本级别）
function checkSaveEditZcxm(){
	var xmdm = jQuery("#czxm").val();
	var xmmc = jQuery("#input_xmmc").val();
	var hidden_xmmc = jQuery("#hidden_xmmc").val();
	if(xmmc == ""){
		alertError("项目名称不能为空，请您确认");
		return false;
	}else if(hidden_xmmc != xmmc){
		var xmmc_num = jQuery("input[name=xmmc]").length;
		for(var i=0;i<xmmc_num;i++){
			var obj=jQuery("input[name=xmmc]").eq(i);
			if(jQuery(obj).val() == xmmc){
				alertError("该项目已经存在，请确认");
				return false;
			}
		}
	}
	confirmInfo('您确定是否保存本次修改?',saveEditZcxm);
}

//保存综测项目（修改本级别）
function saveEditZcxm(tag){
	if(tag == "ok"){
		jQuery.ajaxSetup({async:false});
		
		var url = "general_pjsz_zcxm_ajax.do?method=saveEditZcxm";
		//项目代码
		var xmdm = jQuery("#czxm").val();
		//项目名称
		var xmmc = jQuery("#input_xmmc").val();
		//加减分
		var jjf = jQuery("#input_jjf").val();
		//录入理由
		//var lrly = jQuery("#input_lrly").val();
		//比例代码	
		var i = 0;
		var bldm = new Array();				  
		jQuery("input[name=bldm]").each(function(){bldm[i] = jQuery(this).val();i++;});
		//比例
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
		
		//参数
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

//================【综合素质测评】end===================================

//================【评奖项目设置】begin=================================

//前往评奖项目设置
function goPjszPjxm(){
	var url = "general_pjpy.do?method=pjszPjxm";
	refreshForm(url);
}

//显示评奖项目增加
function showPjszPjxm(){
	var url = "general_pjpy.do?method=pjxmSetting";
	showTopWin(url,"720","500");
}

//显示评奖项目修改
function showPjxmUpdate(){
	
	var len=jQuery("[name=primarykey_checkVal]:checked").length;
	
	if(len==1){	
		var pkValue=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();
		var url="general_pjpy.do?method=pjxmUpdate";
			url+="&pkValue="+pkValue;
		
		showTopWin(url,800,600);
	}else{	
		alertError("请<font color='blue'>勾选一条</font>您希望修改的记录！");	
		return false;
	}
}

//删除评奖项目
function deletePjxm(){
	
	var len=jQuery("[name=primarykey_checkVal]:checked").length;
	var flag = true;
	
	jQuery("[name=primarykey_checkVal]:checked").each(function(){
		var sfsq = jQuery(this).parents().children("td").eq(4).html();
		if(sfsq == "是"){
			flag = false;
			alertError("所选项目已经<font color='blue'>存在申请记录</font>，无法被删除");
		}
	});
	
	if(flag){	
		if(len!=0){	
			confirmInfo("该操作将会<font color='blue'>删除已勾选</font>的评奖项目,是否确定执行该操作？",function(tag){
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
		alertError("请您<font color='blue'>勾选</font>希望删除的记录！");	
		return false;
	}
	}
}

//显示项目性质详细Div
function showXmxzDetail(){
	jQuery("#bjdlmc").val("");
	tipsWindown("系统提示","id:div_xmxz_detail","300","250","true","","true","id");
}
//================【评奖项目设置】end===================================

//================【评奖条件设置】begin=================================

//显示评奖条件设置
function showXmszPjtj(xmdm){

	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
	if(num == "0" && xmdm==""){
		alertError("请<font color='blue'>勾选</font>希望设置条件的项目");
		return false;
	}else if(num != "1"  && xmdm==""){
		alertError("只能<font color='blue'>勾选一个</font>项目进行设置，请确认");
		return false;
	}else if(xmdm==""){
		var xmdm = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").eq(0).val();
	}
	var url = "general_pjpy.do?method=xmszPjtj";
		url+= "&xmdm="+xmdm; 
	showTopWin(url,"800","600");
}

//增加条件设置
function addTjsz(){

	var num = jQuery("#num").val();
	
	var divHtml = jQuery("#div_tjsz").html();
		divHtml += "<div id=\"div_tjsz_"+num+"\">";
		divHtml += "<table width=\"100%\" border=\"0\">";
		divHtml += "<tr>";
		
		divHtml += "<td width=\"5%\">";//checkBox
		divHtml += "<input type=\"checkbox\" name=\"checkVal\" value=\""+num+"\"/>";
		divHtml += "</td>";

		divHtml += "<td width=\"40%\">";//条件
		divHtml += "";
		divHtml += "<select name=\"array_tjdm_sz\" ";
		divHtml += "onchange=\"defaultPjtjInfo(this.value,'"+num+"')\" ";
		divHtml += "id=\"select_tjdm_"+num+"\">";
		divHtml += jQuery("#select_pjtj").html();
		divHtml += "</select>";
		divHtml += "</td>";

		divHtml += "<td width=\"10%\">";//关系
		divHtml += "<select name=\"array_gx_sz\" id=\"select_gx_"+num+"\">";
		divHtml += "</select>";
		divHtml += "</td>";

		divHtml += "<td width=\"10%\">";//条件值
		divHtml += "<input type=\"text\" name=\"array_tjz_sz\" ";
		divHtml += "onkeydown=\"return onlyNum(this,5)\" ";
		divHtml += "onmousedown=\"return onlyNum(this,5)\" ";
		divHtml += "maxlength=\"5\"";
		divHtml += "style=\"width:40px;ime-mode:disabled\" ";
		divHtml += "id=\"input_tjz_"+num+"\" />";
		divHtml += "<span id=\"span_tsgs_"+num+"\"></span>";
		divHtml += "</td>";

		divHtml += "<td width=\"\">";//启用范围级别
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

//删除条件设置
function delTjsz(){
	
	var num = jQuery("input[type=checkbox][name=checkVal]:checked").length;
	
	for(var i=(num-1);i>=0;i--){
		var div_id = "div_tjsz_"+jQuery("input[type=checkbox][name=checkVal]:checked").eq(i).val();
		if($(div_id)){
			jQuery("#"+div_id).remove();
		}
	}
}

//初始化评奖条件信息
function defaultPjtjInfo(tjdm,num){

	var xmdm = jQuery("#xmdm").val();
	var url = "general_xmsz_pjtj_ajax.do?method=defaultPjtjInfo";
		url+= "&tjdm="+tjdm;

	jQuery.ajaxSetup({async:false});
		
	jQuery.post(url,
		{},
		function(result){
			var tsgs = result.split("!!luojw!!")[0];//特殊格式
			var tjz = result.split("!!luojw!!")[1];//条件值
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

//检测保存评奖条件
function checkSavePjtj(){

	//条件数
	var num = jQuery("select[name=array_tjdm_sz]").length;
	var flag = true;
	
	//条件
	var i=0;
	if(flag){
		jQuery("select[name=array_tjdm_sz]").each(
			function(){
				var tjdm = jQuery(this).val();
				if(tjdm == "" && flag){
					alertError("第<font color='blue'>"+(i+1)+"</font>行<font color='blue'>条件</font>不能为空，请确认^_^||");
					flag = false ;
					
				}
				i++;
			}
		);
	}

	if(flag){
		//条件值
		i=0;
		jQuery("input[name=array_tjz_sz]").each(
			function(){
				var tjz = jQuery(this).val();
				if(tjz == ""  && flag){
					alertError("第<font color='blue'>"+(i+1)+"</font>行<font color='blue'>条件值</font>不能为空，请确认^_^||");
					flag = false;
				}
				i++;
			}
		);
	}

	//判断是否条件重复
	if(flag){
		for(var j=0;j<num;j++){
			//条件代码
			var tjdm = jQuery("select[name=array_tjdm_sz]").eq(j).val();
			//条件范围
			var tjfw = jQuery("select[name=array_tjfw_sz]").eq(j).val();
			
			for(var k=(j+1);k<num;k++){
				if(tjdm == jQuery("select[name=array_tjdm_sz]").eq(k).val()
				&& tjfw == jQuery("select[name=array_tjfw_sz]").eq(k).val()){
					alertError("第<font color='blue'>"+(j+1)+"</font>与第<font color='blue'>"+(k+1)+"</font>行<font color='blue'>条件与范围相同</font>，这是不允许的^_^||");
					flag = false;
					return false;
				}
			}
		}
	}

	if(flag){
		if(num == 0){
			confirmInfo("您希望<font color='blue'>清空</font>该项目的所有条件吗？",deletePjtj);
		} else{
			confirmInfo("即将执行<font color='blue'>保存</font>评奖条件操作，请您再次确认^_^!!",savePjtj);
		}
	}
}

//保存评奖条件
function savePjtj(tag){
	if(tag == "ok"){
		jQuery.ajaxSetup({async:false});
		
		var url = "general_xmsz_pjtj_ajax.do?method=savePjtj";

	 	//创建一个json对象
		var parameter={};
		
		//指定获取的控件类型，进行循环
		var hid_obj=jQuery("input").each(function(){
			
			//获取表单控件name
			var name=jQuery(this).attr("name");
			//构建json对象
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

//删除评奖条件
function deletePjtj(tag){
	if(tag == "ok"){
		
		jQuery.ajaxSetup({async:false});
		
		var url = "general_xmsz_pjtj_ajax.do?method=deletePjtj";
		//项目代码
		var xmdm = jQuery("#xmdm").val();
	 	//创建一个json对象
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

//================【评奖条件设置】end===================================

//================【评奖人数设置】begin===================================

//显示人数设置
function showXmszRssz(xmdm){
	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
	if(num == "0" && xmdm ==""){
		alertError("请<font color='blue'>勾选</font>希望设置人数的项目");
		return false;
	}else if(num != "1" && xmdm ==""){
		alertError("只能<font color='blue'>勾选一个</font>项目进行设置，请确认");
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
			var rssz = result.split("!!luojw!!")[0];//人数设置
			var kzfw = result.split("!!luojw!!")[1];//控制范围
			if(rssz == "no"){
				alertError("该项目<font color='blue'>不需要</font>人数控制，请您确认~^_^||");
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

//显示设置比例Div
function showSzblDiv(){
	tipsWindown("系统提示","id:div_szbl","300","200","true","","true","id");
}

//检测保存设置比例
function checkSaveSzbl(){

	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
	var szbl = jQuery("#szbl").val();

	if(szbl == ""){
		alertError("请输入您希望设置的<font color='blue'>比例</font>");
		return false;
	}
	
	if(num == 0){//整体
		//年级
		var nj_num = jQuery("a[name=a_name_nj]").length; 			  		
		//学院
		var xy_num = jQuery("a[name=a_name_xy]").length; 		
		//专业
		var zy_num = jQuery("a[name=a_name_zy]").length; 
		//班级
		var bj_num = jQuery("a[name=a_name_bj]").length; 
		
		confirmInfo("您确定是否将<font color='blue'>过滤条件中所指定的部门</font>设置为该比例?",saveSzblNoChecked);
		
	}else{//勾选
		confirmInfo("您确定是否将<font color='blue'>所勾选部门</font>设置为该比例?",saveSzplChecked);
	}
}

//保存设置比例设置（未选中）
function saveSzblNoChecked(tag){
	if(tag == "ok"){
		jQuery.ajaxSetup({async:false});

		//年级
		var nj = new Array();  
		var i = 0;				  
		jQuery("a[name=a_name_nj]").each(function(){
			var nj_id = jQuery(this).attr("id");
			nj[i] = nj_id.replace("a_id_","");
			i++;
		});
		
		//学院
		var xy = new Array(); 
		i = 0;			  
		jQuery("a[name=a_name_xy]").each(function(){
			var xy_id = jQuery(this).attr("id");
			xy[i] = xy_id.replace("a_id_","");
			i++;
		});

		//专业
		var zy = new Array(); 
		i = 0;					  
		jQuery("a[name=a_name_zy]").each(function(){
			var zy_id = jQuery(this).attr("id");
			zy[i] = zy_id.replace("a_id_","");
			i++;
		});

		//班级
		var bj = new Array();  				  
		jQuery("a[name=a_name_bj]").each(function(){
			var bj_id = jQuery(this).attr("id");
			bj[i] = bj_id.replace("a_id_","");
			i++;
		});

		var xmdm = jQuery("#xmdm").val();
		var szbl = jQuery("#szbl").val();
		var url = "general_xmsz_rssz_ajax.do?method=saveSzblNoChecked";
		
		//参数
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

//保存设置比例设置（选中）
function saveSzplChecked(tag){
	if(tag == "ok"){
		jQuery.ajaxSetup({async:false});
		
		var pkValue = new Array();//主键
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
		
		//参数
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

//检测保存最终人数
function checkSaveZzrs(){
	confirmInfo("请您确认是否<font color='blue'>保存最终人数</font>?",saveZzrs);
}

//保存最终人数
function saveZzrs(tag){
	if(tag == "ok"){
		jQuery.ajaxSetup({async:false});
		
		var qdrs = new Array();//确定人数
		var pkValue = new Array();//主键
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
		
		//参数
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

//================【评奖人数设置】end===================================

//================【项目兼得设置】begin===================================

//显示兼得设置
function showXmszXmjd(xmdm){
	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
	if(num == "0" && xmdm==""){
		alertError("请<font color='blue'>勾选</font>希望设置不可兼得的项目");
		return false;
	}else if(num != "1" && xmdm==""){
		alertError("只能<font color='blue'>勾选一个</font>项目进行设置，请确认");
		return false;
	}else if(xmdm==""){
		xmdm = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").eq(0).val();
	}

	jQuery("#xmdm").val(xmdm);
	
	jQuery.ajaxSetup({async:false});

	//路径
	var url = "general_xmsz_xmjd_ajax.do?method=defaultJdszSetting";
	//参数
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
		
	tipsWindown("系统提示","id:div_xmjd","500","300","true","","true","id");

	jQuery.ajaxSetup({async:true});
}

//验证保存项目兼得
function checkSaveXmjd(){
	var num = jQuery("input[type=checkbox][name=array_fjddm]:checked").length;
	if(num == "0"){
		confirmInfo('您未勾选任一项目，将会取消勾选项目的所有不可兼得情况，请您确认',deleteXmjd);
	}else {
		confirmInfo('将要执行保存操作，请您确认',saveXmjd);
	}
}

//保存项目兼得
function saveXmjd(tag){
	if(tag == "ok"){
		jQuery.ajaxSetup({async:false});
		
		var url = "general_xmsz_xmjd_ajax.do?method=saveXmjd";
		
	 	//创建一个json对象
		var parameter={};
		
		//指定获取的控件类型，进行循环
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

//删除项目兼得
function deleteXmjd(tag){
	if(tag == "ok"){
		jQuery.ajaxSetup({async:false});
		
		var url = "general_xmsz_xmjd_ajax.do?method=deleteXmjd";
		
	 	//创建一个json对象
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

//全选兼得项目
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
//================【项目兼得设置】end===================================

//================【评奖时间设置】begin===================================

//显示时间设置
function showXmszSjsz(xmdm){

	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
	if(num == "0" && xmdm==""){
		alertError("请<font color='blue'>勾选</font>希望设置时间控制的项目");
		return false;
	}else if(num != "1"  && xmdm==""){
		alertError("只能<font color='blue'>勾选一个</font>项目进行设置，请确认");
		return false;
	}else if(xmdm==""){
		xmdm = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").eq(0).val();
	}

	jQuery("#xmdm").val(xmdm);
	
	jQuery.ajaxSetup({async:false});

	//路径
	var url = "general_xmsz_sjsz_ajax.do?method=defaultSjszSetting";
	//参数
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
		
	tipsWindown("系统提示","id:div_sjsz","500","300","true","","true","id");

	jQuery.ajaxSetup({async:true});

}

//验证保存时间设置
function checkSaveSjsz(){
	var sqkssj = jQuery("#sqkssj").val();
	var sqjssj = jQuery("#sqjssj").val();
	var shkssj = jQuery("#shkssj").val();
	var shjssj = jQuery("#shjssj").val();

	if(sqkssj != "" && sqjssj !=""){
		if(sqkssj > sqjssj){
			alertError("申请开始时间<font color='blue'>晚于</font>结束时间，请确认^_^||");
			return false;
		}
	}

	if(shkssj != "" && shjssj !=""){
		if(shkssj > shjssj){
			alertError("审核开始时间<font color='blue'>晚于</font>结束时间，请确认^_^||");
			return false;
		}
	}
	
	confirmInfo('将要执行保存操作，请您确认',saveSjsz);
}

//保存时间设置
function saveSjsz(tag){

	if(tag == "ok"){

		jQuery.ajaxSetup({async:false});
		
		var url = "general_xmsz_sjsz_ajax.do?method=saveSjsz";
		var xmdm = jQuery("#xmdm").val();
		
		//创建一个json对象
		var parameter={};
		
		//指定获取的控件类型，进行循环
		var hid_obj=jQuery("input,textarea").not(jQuery("[name=array_fjddm]")).each(function(){
			
			//获取表单控件name
			var name=jQuery(this).attr("name");
			//构建json对象
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

//================【评奖时间设置】end===================================

//================【我的评奖】begin===================================

function goPjpyWdpj(){
	var url = "pjpy_general_wdpj.do";
	refreshForm(url);
}
//================【我的评奖】end===================================

//================【评奖历史信息】begin===================================

//前往历史评奖
function goWdpjLspj(){
	var url = "general_pjpy.do?method=wdpjLspj&forward=jbsz";
	refreshForm(url);
}

//显示历史评奖维护
function showWdpjLspj(doType){
	var url = "general_pjpy.do?method=lspjUpdate";
	url+= "&doType="+doType;
		
	if(doType == "edit" || doType == "view"){
		var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
		if(num == "0"){
			alertError("请勾选一条您希望操作的记录");
			return false;
		}else if(num != "1"){
			alertError("只能勾选一条记录进行操作，请确认");
			return false;
		}

		var pkValue = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").eq(0).val();
		url+= "&pkValue="+pkValue;
	}
		
	showTopWin(url,"600","480");
}

//检测删除历史评奖
function checkDelWdpjLspj(){

	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
	if(num == "0"){
		alertError("请勾选您希望删除的记录");
		return false;
	}
	
	confirmInfo("将要删除您勾选的记录，请确认",delWdpjLspj);
}

//删除历史评奖
function delWdpjLspj(tag){
	if(tag == "ok"){
	
		var parameter ={};
		var pkValue = "";//主键
		var i=0;
		jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").each(function(){
			pkValue+=escape(jQuery(this).val())+"!!array!!";
		});
		//构建json对象
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

//================【评奖历史信息】end===================================














//前往结果查询
function goWdpjJgcx(){
	var url = "general_pjpy.do?method=wdpjJgcx";
	refreshForm(url);
}

//前往项目审核By项目代码
function goWdpjXmshByXm(xmdm){
	var url = "general_pjpy.do?method=wdpjXmsh";
	url+="&xmdm="+xmdm;
	refreshForm(url);
}

//=================通用方法 begin==============================
//点击控件赋值
function setCheckedValue(obj){
	var id = obj.id;
	var value = id.split("_")[1];
	var checked_id = id.split("_")[0]+"_check";
	
	if($(checked_id)){
		$(checked_id).value = value;
	}
}
//=================通用方法 end================================