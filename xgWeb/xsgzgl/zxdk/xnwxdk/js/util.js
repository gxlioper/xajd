/**
 * 检验字数长度
 * @return
 */
function checkzs(){
	if(jQuery("#sqly").val().length > 500){
		showAlertDivLayer("申请理由最大字数为500，现已经超过，请确认！");
		return false;
	}
}

/**
 * 验证是否存在空项
 * @param ids 要验证的控件id "-"分隔
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery.trim(jQuery("#"+id[i]).val()) || jQuery.trim(jQuery("#"+id[i]).text());
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}

//金额校验不能以0开头
function ismoney(obj){
  if(obj.value.indexOf('0') == 0 && obj.value.length>=2){
		 showAlert("金额不能以0开头！",{},{"clkFun":function(){
				jQuery(obj).val("");
				jQuery(obj).focus();
			}});
  }
}

