function saveForm(){
	//先验证学号
	var xh = jQuery("#xh").val();
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	var rddc = jQuery("#rddc").val();

	
	if (jQuery.trim(xh) == ""){
		showAlert("请先选择学生！");
		return false;
	}
	if (jQuery.trim(xn) == ""){
		showAlert("请先选择学年！");
		return false;
	}
	if (jQuery.trim(xq) == ""){
		showAlert("请先选择学期！");
		return false;
	}
	
	if (jQuery.trim(rddc) == ""){
		showAlert("请先选择认定档次！");
		return false;
	}
	//江西航空个性化字段判空
	if(jQuery("#xxdm").val() == "13871"){
		if(jQuery.trim(jQuery("#knpx").val()) == ""){
			showAlert("困难排序必填！");
			return false;
		}
	}
	if("10742"==jQuery("#xxdm").val()) {		
		var sqlydmIds = "";
		var checkSqlydm = "";
		jQuery("input[name=sqlydm]:checked").each(function(){
			sqlydmIds += jQuery(this).val()+ "," ;
		});
		if(sqlydmIds.length>0){
			sqlydmIds = sqlydmIds.substring(0, sqlydmIds.length-1);
			checkSqlydm ="1";
		}
		if (jQuery.trim(checkSqlydm) == ""){
			showAlert("请将必填项填写完整！");
			return false;
		}
	}
	
	if(jQuery("#xxdm").val()=='12861' || '10335'==jQuery("#xxdm").val()){
		if(jQuery(".MultiFile-label").length<=0){
			showAlertDivLayer("请上传附件！");
			return false;
		}
	}
	
	//杭州师范大学个性化
	if(jQuery("#xxdm").val() == '10346'){
		var ylzd4 = jQuery("#ylzd4").val();
		var ylzd5 = jQuery("#ylzd5").val();
		if(ylzd4 == null || ylzd4 == '' || ylzd5 == null || ylzd5 == ''){
			showAlert("请把带*的项填写完整！");
			return false;
		}
	}
	/**
	if("10335"==jQuery("#xxdm").val()&&(jQuery("#ylzd3").val() == "")){
		showAlertDivLayer("请选择无偿资助金额！");
		return false;
	}*/
	
	if("10742"==jQuery("#xxdm").val()) {
		var url = "xszz_knsjg.do?method=addKnsjg&type=save&sqlydm="+sqlydmIds;
	}else if("10277"==jQuery("#xxdm").val()){
		var values=pjyydm();
		var url = "xszz_knsjg.do?method=addKnsjg&type=save&ylzd5="+values;
	}else{
		var url = "xszz_knsjg.do?method=addKnsjg&type=save";
	}
      ajaxSubFormWithFun("knsjgForm",url,function(data){
    	 if(data["message"]=="保存成功！"){
    		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
    	 }else{
    		 showAlert(data["message"]);
    	 }
		});
  }

//拼接原因代码
function pjyydm(){
	var values=[];
	jQuery("input[name=ylzd5]:checked").each(function(){
		values.push(jQuery(this).val());
	});
	return values.join(",");
}


