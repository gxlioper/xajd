jQuery(document).ready(function(){ 
	
	changeSqkg();
	changeBjpykg();
	//根据审批流程加载人数控制级别
	initRskzjb();
	//绑定事件
	bandeven();
	//加载人数控制参数是否显示
	var rssfkz=jQuery("input:radio[name=rssfkz]:checked").val();
	initRskzcs(rssfkz);
	sfysz();
});
function saveJcsz(){
	var sqkglength = jQuery("[name=sqkg]:checked").length;
	
	// 梧州学院  不判断
	if("11354" != jQuery("#xxdm").val() && sqkglength==0){
		showAlertDivLayer("请设置申请开关!");
		return false;
	}
	
	var splc = jQuery("#splc").val();
	var sqkg = jQuery("[name=sqkg]:checked").val();
	
	if ("1"==sqkg && (splc == "" || splc == null)){
		showAlertDivLayer("请选择审核流程!");
		return false;
	}
	
//	if("1"==sqkg && (jQuery("#sqjssj").val()=="" || jQuery("#sqkssj").val()=="")){
//		showAlertDivLayer("开放时间和结束时间必须填写!");		
//		return false;
//	}
	var rssfkz = jQuery('input:radio[name=rssfkz]:checked').val();
	if(rssfkz=='1'){
		var rskzjb = jQuery('input:radio[name=rskzjbView]:checked').val();
		if(rskzjb==null||rskzjb==''){
			showAlertDivLayer("请选择人数控制级别！");
			return false;
		}
	}
	
	var bjpykglength = jQuery("[name=bjpykg]:checked").length;
	
	if(bjpykglength==0){
		showAlertDivLayer("请设置班级评议开关!");
		return false;
	}
	var bjpykg = jQuery("[name=bjpykg]:checked").val();
	var xzrsxx = jQuery.trim(jQuery("#xzrsxx").val());
	if("1"==bjpykg && xzrsxx == ''){
		showAlertDivLayer("小组人数下限不能为空!");
		return false;
	}
	var url = "xszz_knsjcszbjpy.do?method=saveJcsz";
	ajaxSubFormWithFun("jcszForm",url,function(data){
		showAlertDivLayer(data["message"]);
	});
			
}

//绑定事件
function bandeven(){
	//人数是否控制字段改变，显示、隐藏人数控制参数
	jQuery("input:radio[name=rssfkz]").change(function(){
		var rssfkz=jQuery(this).val();
		initRskzcs(rssfkz);
	});
}

//显示、隐藏人数控制参数
function initRskzcs(rssfkz){
	if(rssfkz=='1'){
		jQuery('.rskzcs').css('display','');
	}else{
		jQuery('.rskzcs').css('display','none');
	}
}

//更新岗位申请
function changeSqkg(){
	var sqkg = jQuery("[name=sqkg]:checked").val();
	if("1"==sqkg){
		jQuery("select", jQuery("#sq_table")).attr("disabled",false);
		jQuery("input:not(input:radio[name=sqkg])", jQuery("#sq_table")).attr("disabled",false);
		
	}else if("0"==sqkg){
		jQuery("select", jQuery("#sq_table")).attr("disabled","disabled");
		jQuery("input:not(input:radio[name=sqkg])", jQuery("#sq_table")).attr("disabled","disabled");
		
	}
}

function changeBjpykg(){
	var bjpykg = jQuery("[name=bjpykg]:checked").val();
	if("1"==bjpykg){
		jQuery("input:not(input:radio[name=bjpykg])", jQuery("#bjpy_table")).attr("disabled",false);
		
	}else if("0"==bjpykg){
		jQuery("input:not(input:radio[name=bjpykg])", jQuery("#bjpy_table")).attr("disabled","disabled");
		
	}
}

/*验证是否已设置人数*/
function sfysz(){
	jQuery.post('xszz_knsjcszbjpy.do?method=yzSfszrs',{'rskzfw':jQuery("#rskzfw").val()},function(data){
		if(data.message=='true'){
			jQuery('#sfysz').css('color','#004400');
			jQuery('#sfysz').text('已设置');
		}else{
			jQuery('#sfysz').css('color','red');
			jQuery('#sfysz').text('未设置');
		}
	},'json');
}

//加载人数控制级别
function initRskzjb(){
	var splc=jQuery("#splc").val()
	jQuery.post('xszz_knsjcszbjpy.do?method=getShgw',{'splc':splc},function(data){
		var radio1 = "";
		if(data != null){
			for ( var i = 0; i < data.length; i++) {
				var o = data[i];
				radio1 += "<label><input type='radio' name='rskzjbView' value='"
						+ o.spgwdm + "'/>";
				radio1 += o.spgwmc;
				radio1 += "</label>";
				if (i != data.length - 1) {
					radio1 += "<br/>";
				}
			}
		}
		jQuery("#rskzjbTd").html(radio1);
		jQuery(
				"input:radio[name=rskzjbView][value="
						+ jQuery("#rskzjb").val() + "]").attr("checked",
				"checked");
		
	},'json');
}

//困难生人数设置
function rssz(){
	var rskzfw=jQuery("#rskzfw").val();
	var rskznj=jQuery("#rskznj").val();
	var xn=jQuery("#xn").val();
	var xq=jQuery("#xq").val();
	if(rskzfw==null||rskzfw==''){
		showAlertDivLayer('请先选择人数控制范围！');
		return false;
	}
	showDialog("困难生人数设置",780,550,"xszz_knsjcszbjpy.do?method=knsRssz&rskzfw="+rskzfw+"&rskznj="+rskznj+"&xn="+xn+"&xq="+xq,{close:function(){sfysz();}});
}

//修改人数范围字段，及时保存
function saveKzfw(){
	var rskzfw = jQuery('#rskzfw').val();
	jQuery.post('xszz_knsjcszbjpy.do?method=changeRskzfw',{'rskzfw':rskzfw},function(data){
		if(data.message=='true'){
			jQuery('#sfysz').css('color','red');
			jQuery('#sfysz').text('未设置');
		}
	},'json');
}

