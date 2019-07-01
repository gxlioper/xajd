function checkEmail(obj) {
    var dzyx = jQuery(obj).val();
    if(!isEmail(dzyx) && dzyx!=""){
        showAlertDivLayer("请输入正确的电子邮箱！");
        return false;
    }
}

function saveyyzxInfo(){
	if(jQuery("#xstell").val()==''){
		showAlert("手机号码不能为空！");
		return false;
	}
	if(jQuery("#yyzxzt").val()==''){
		showAlert("咨询主题不能为空！");
		return false;
	}
	if (jQuery("#xstxxx_tb").length > 0 ) {
		var checkId = 'dzyx-fqzy-fxl-mqzy-mxl-jtdz-zxmd';
		if(!checkNotNull(checkId)){
			showAlertDivLayer("请将带<font color='red'>*</font>的项目填写完整！");
			return false;
		}
		var hf = jQuery("input[type=radio][name='hf']:checked").length;
		var sfyzn = jQuery("input[type=radio][name='sfyzn']:checked").length;
		var sfdszn = jQuery("input[type=radio][name='sfdszn']:checked").length;
		var syd = jQuery("input[type=radio][name='syd']:checked").length;
		if(hf <=0 || sfyzn <=0 || sfdszn <=0 || syd <=0){
			showAlertDivLayer("请将带<font color='red'>*</font>的项目填写完整！");
			return false;
		}
	}

	// 得到JSON对象
	//以下变量处理完全是出于两种预约模式区分的需要，防止变量有undifined
	var qssj = (!jQuery("#qssj").val()) ? "" : jQuery("#qssj").val();
	var jssj = (!jQuery("#jssj").val()) ? "" : jQuery("#jssj").val();
	var sjddm = (!jQuery("#sjddm").val()) ? "" : jQuery("#sjddm").val();
	if(sjddm=='' && jQuery("#sjddm").length > 0){
		showAlert("预约咨询时段不能为空！");
		return false;
	}
	var parameter ={
		id:jQuery("#yyid").val(),
		xstell:jQuery("#sjhm").val(),
		qssj:qssj,
		jssj:jssj,
		sjddm:sjddm,
		yyzxzt:encodeURI(encodeURI(jQuery("#yyzxzt").val())),
		yyzxxq:encodeURI(encodeURI(jQuery("#yyzxxq").val()))
	};
	if (jQuery("#yytxxx_tb").length > 0 ) {
		var checkId = 'bczxwt-zxhzt';
		if(!checkNotNull(checkId)){
			showAlertDivLayer("请将带<font color='red'>*</font>的项目填写完整！");
			return false;
		}
		var qxztzt = jQuery("input[type=radio][name='qxztzt']:checked");
		var qxztjl = jQuery("input[type=radio][name='qxztjl']:checked");
		var qxztyy = jQuery("input[type=radio][name='qxztyy']:checked");
		var sczxhgb = jQuery("input[type=radio][name='sczxhgb']:checked");
		var zjzt = jQuery("input[type=radio][name='zjzt']:checked");
		if(qxztzt.length <=0 || qxztjl.length <=0 || qxztyy.length <=0 || sczxhgb.length <=0 || zjzt.length <=0){
			showAlertDivLayer("请将带<font color='red'>*</font>的项目填写完整！");
			return false;
		}
		parameter["qxztzt"] = qxztzt.val();
		parameter["qxztjl"] = qxztjl.val();
		parameter["qxztyy"] = qxztyy.val();
		parameter["sczxhgb"] = sczxhgb.val();
		parameter["zjzt"] = zjzt.val();
		parameter["bczxwt"] = encodeURI(encodeURI(jQuery("#bczxwt").val()));
		parameter["zxhzt"] = encodeURI(encodeURI(jQuery("#zxhzt").val()));
	}
	var url = "xlzx_yysq.do?method=updateYysqInfo&doType=update";

	showConfirm("确认保存预约信息？",{"okFun":function(){
		jQuery.ajaxSetup({async:false});
		ajaxSubFormWithFun("form", "xlzx_xstxxx.do?method=save", function(data) {
			if(data == true){
				jQuery.post(url,parameter,function(data){
					if(data == true){
						showAlert("保存成功！",{},{"clkFun":function(){
							frameElement.api.opener.refreshForm("xlzx_yysq_yysq.do");
							iFClose();
						}});
					}else{
						showAlert("保存失败！",{},{"clkFun":function(){
						}});
					}
				},'json');
			}else{
				showAlert("保存失败！",{},{"clkFun":function(){
				}});
			}
		});
		/* jQuery.post(url,parameter,function(data){
                       if(data == true){
                           showAlert("保存成功！",{},{"clkFun":function(){
                               frameElement.api.opener.refreshForm("xlzx_yysq_yysq.do");
                               iFClose();
                           }});
                       }else{
                           showAlert("保存失败！",{},{"clkFun":function(){
                           }});
                       }
                   },'json');*/
		jQuery.ajaxSetup({async:true});
	}});
}

function addYyInfo(zgh){
	var yyzxrq = $("yyzxrq").value;
	showDialog("预约信息",640,260,"xlzx_yysq.do?method=addyyzxInfo&yyzxrq="+yyzxrq+"&zgh="+zgh);
}

function disabledView(){
	if($("doType").value=="view"){
		jQuery("input,select,text,textarea").each(function(){
			jQuery(this).attr("readOnly","true");
		});
	}
}

function init(){
	if($("status").value=="1" || $("status").value=="3" || $("status").value=="4"){
		jQuery("tr[name=yyqkInfo]").hide();
		jQuery("tr[name=yysbyytr]").hide();
		jQuery("thead[name=zxxgInfo]").hide();
		jQuery("tbody[name=zxxgInfo]").hide();
	}else if($("status").value=="5"){
		//jQuery("tr[name=yysbyytr]").show();
		jQuery("tr[name=yyqkInfo]").hide();
		jQuery("thead[name=zxxgInfo]").hide();
		jQuery("tbody[name=zxxgInfo]").hide();
	}else{
		jQuery("tr[name=yysbyytr]").hide();
	}
	if($("doType").value=="view"){
		jQuery("#buttonSave").hide();
		jQuery("#btx").hide();
	}
}