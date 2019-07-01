//开关设置
function changeKg(div,obj){
	var val=jQuery(obj).val();
	jQuery("#rzsqkg").val(val);
	if(val=="1"){
		jQuery("#"+div).show();
	}else{
		jQuery("#"+div).hide();
	}
}
//保存参数设置
//需优化此代码
function save_cssz(){
	var fdyrz = yz_fdyrzsq();
	var fdypx = yz_fdypx();
	var zwsq = yz_zwsq();
	//保存辅导员任职申请
	if(fdyrz && fdypx && zwsq){
		var param=getRzParam();
		jQuery.post("szdw_cssz.do?method=cssz&type=save", param, function(result){
			
			 if(result["success"]!="true"){
				 alertInfo(result["message"]);
			 }else{
				//保存辅导员培训参数
				 var param2=getPxParam();
				 jQuery.post("szdw_cssz.do?method=cssz&type=save", param2, function(result2){
					 
					 if(result2["success"]!="true"){
						 alertInfo(result2["message"]);
					 }else{
						//保存辅导员职务参数
						 var param_zw=getZwParam();
						jQuery.post("szdw_cssz.do?method=cssz&type=save", param_zw, function(result_zw){
							 alertInfo(result_zw["message"]);
						},"json");
					 }
					 
				},"json");
			 }
			 
		},"json");
	}
}

//保存辅导员参数设置
function saveFdycssz(){
	var fdyrz = yz_fdyrzsq();
	//保存辅导员任职申请
	if(fdyrz){
		var param=getRzParam();
		jQuery.post("szdw_cssz.do?method=fdycssz&type=save", param, function(result){
			 alertInfo(result["message"]);
		},"json");
	}
}

//保存编班时间参数设置
function saveBbsjCssz(){
	var bbsjkg =jQuery("#bbsjkg").val();
	var bbsjkssj =jQuery("#bbsjkssj").val();
	var bbsjjssj = jQuery("#bbsjjssj").val();
	if(bbsjkg==null || bbsjkg==""){
		alertInfo("请选择编班时间开关");	
		return false;	
	}
	var param ={key:"szdw_bbsj",kg:bbsjkg,kssj:bbsjkssj,jssj:bbsjjssj};
	
	jQuery.post("szdw_cssz.do?method=bbsjCssz&type=save", param, function(result){
		alertInfo(result["message"]);
	},"json");
}

//保存班干部参数设置
function saveBgbcssz(){
	var zwsq = yz_zwsq();
	if(zwsq){
		//保存职务参数
		 var param_zw=getZwParam();
		jQuery.post("szdw_cssz.do?method=bgbcssz&type=save", param_zw, function(result_zw){
			 alertInfo(result_zw["message"]);
		},"json");
	}
}
function getRzParam(){
	var rzsqkg =jQuery("#rzsqkg").val();
	var rzsqkssj =jQuery("#rzsqkssj").val();
	var rzsqjssj = jQuery("#rzsqjssj").val();
	var rzsqSplc = jQuery("#rzsqSplc").val();
	var param ={key:"szdw_fdyrzsq",kg:rzsqkg,kssj:rzsqkssj,jssj:rzsqjssj,splc:rzsqSplc};
	return param;
}
function getPxParam(){
	var fdypxSplc = jQuery("#fdypxSplc").val();
	var param ={key:"szdw_fdypxsq",splc:fdypxSplc};
	return param;
}
function getZwParam(){
	var zwsqSplc = jQuery("#zwsqSplc").val();
	var param ={key:"szdw_xsgbzwsq",splc:zwsqSplc};
	return param;
}
//验证辅导员任职申请
function yz_fdyrzsq(){
	var rzsqkg =jQuery("#rzsqkg").val();
	var rzsqkssj =jQuery("#rzsqkssj").val();
	var rzsqjssj = jQuery("#rzsqjssj").val();
	var rzsqSplc = jQuery("#rzsqSplc").val();
	if(rzsqkg==null || rzsqkg==""){
		alertInfo("请选择辅导员任职开关");	
		return false;	
	}else if(rzsqkg=="1"){
		
		//2014-7-3 取消参数设置当中时间必填项
//		if(rzsqkssj==null || rzsqkssj==""){
//			alertInfo("辅导员任职申请开始时间不能为空");	
//			return false;	
//		}else if(rzsqjssj==null || rzsqjssj==""){
//			alertInfo("辅导员任职申请结束时间不能为空");		
//			return false;	
//		}else 
		if(!(rzsqjssj==null || rzsqjssj=="")&&rzsqjssj < rzsqkssj){
			alertInfo("辅导员任职申请开始时间不能小于结束时间");		
			return false;	
		}		
	}if(rzsqSplc==null || rzsqSplc==""){
		alertInfo("请选择辅导员任职审批流程");	
		return false;	
	}
	return true;
}
function yz_fdypx(){
	var fdypxSplc = jQuery("#fdypxSplc").val();
	if(fdypxSplc==null || fdypxSplc==""){
		alertInfo("请选择辅导员培训审批流程");	
		return false;	
	}
	return true;
}
function yz_zwsq(){
	var zwsqSplc = jQuery("#zwsqSplc").val();
	if(zwsqSplc==null || zwsqSplc==""){
		alertInfo("请选择学生干部职务申请审批流程");	
		return false;	
	}
	return true;
}


//保存辅导员培训参数设置
function saveFdypxCssz(){
	var fdypx = yz_fdypx();
	//保存辅导员任职申请
	if(fdypx){
		var param =getPxParam();
		jQuery.post("szdw_cssz.do?method=fdypxCssz&type=save", param, function(result){
			 alertInfo(result["message"]);
		},"json");
	}
}