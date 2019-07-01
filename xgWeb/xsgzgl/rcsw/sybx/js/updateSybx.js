function saveForm(){
	//先验证学号
	var xh = jQuery("#xh").val();
	var xn = jQuery("#xn").val();
	
	var czjmylbxje = jQuery("#czjmylbxje").val();
	var sybxje = jQuery("#sybxje").val();
	var czjmylbxcbqsrq = jQuery("#czjmylbxcbqsrq").val();
	//var czjmylbxcbjsrq = jQuery("#czjmylbxcbjsrq").val();
	var sybxcbqsrq = jQuery("#sybxcbqsrq").val();
	//var sybxcbjsrq = jQuery("#sybxcbjsrq").val();
	var sfzqfjg = jQuery("#sfzqfjg").val();
	var sfzyxqxqsrq = jQuery("#sfzyxqxqsrq").val();
	var sfzyxqxjzrq = jQuery("#sfzyxqxjzrq").val();

	var jhrxm = jQuery("#jhrxm").val();
	var jhrsfzh = jQuery("#jhrsfzh").val();
	var txdz = jQuery("#txdz").val();

	
	if (jQuery.trim(xh) == ""){
		showAlert("请先选择学生！");
		return false;
	}
	if (jQuery.trim(xn) == ""){
		showAlert("请先选择学年！");
		return false;
	}
	
	if (jQuery.trim(czjmylbxje) == ""){
		showAlert("请输入城镇居民医疗保险金额！");
		return false;
	}
	if (jQuery.trim(czjmylbxcbqsrq) == ""){
		showAlert("请选择城镇居民医疗保险参保起始日期！");
		return false;
	}
	//if (jQuery.trim(czjmylbxcbjsrq) == ""){
	//	showAlert("请选择城镇居民医疗保险参保结束日期！");
	//	return false;
	//}
	if (jQuery.trim(sybxje) == ""){
		showAlert("请输入商业保险金额！");
		return false;
	}
	if (jQuery.trim(sybxcbqsrq) == ""){
		showAlert("请选择商业保险参保起始日期！");
		return false;
	}
	//if (jQuery.trim(sybxcbjsrq) == ""){
	//	showAlert("请选择商业保险参保结束日期！");
	//	return false;
	//}
	if (jQuery.trim(sfzqfjg) == ""){
		showAlert("请输入身份证签发机关！");
		return false;
	}
	if (jQuery.trim(sfzyxqxqsrq) == ""){
		showAlert("请选择身份证有效期限起始日期！");
		return false;
	}
	if (jQuery.trim(sfzyxqxjzrq) == ""){
		showAlert("请选择身份证有效期限截止日期！");
		return false;
	}
	//if (parseFloat(bxje) > parseFloat(99999)){
	//	showAlert("保险金额不能超过99999元！");
	//	return false;
	//}
	
	if (jQuery.trim(txdz) == ""){
		showAlert("请输入通讯地址！");
		return false;
	}
	
     var url = "rcsw_sybx.do?method=updateSybx&type=update";
      ajaxSubFormWithFun("sybxForm",url,function(data){
    	  
    	  if (data["success"] == "false"){
    		  showAlert("该学生在"+jQuery.trim(xn)+"学年，已有商业保险信息存在。");
    	  } else {
    		  showAlert(data["message"],{},{"clkFun":function(){
        			if (parent.window){
     				 refershParent();
        			}
      		  }});
    	  }
    	  
      });
  }

/**
 * 计算保险总金额
 */
function changeBxje() {
	var czjmylbxje = jQuery("#czjmylbxje").val();
	var sybxje = jQuery("#sybxje").val();
	var czjmylbxjeV1 = parseFloat(czjmylbxje == "" ? 0 : czjmylbxje);
	var sybxjeV1 = parseFloat(sybxje == "" ? 0 : sybxje);
	var czjmylbxjeV = czjmylbxjeV1*10000;
	var sybxjeV =sybxjeV1*10000;
	var bxje = (czjmylbxjeV + sybxjeV)/10000; 
	jQuery("#bxje").val(bxje);
	jQuery("#bxjeTd").html(bxje);
}