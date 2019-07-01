function  saveForm(){
		if(!validate()){
			return false;
		}
		// 得到JSON对象
		var url = "daxxgl.do?method=updateDaxxgl&type=save";

	    ajaxSubFormWithFun("form",url,function(data){
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

/**
 * 验证是否存在空项
 * @param ids 要验证的控件id "-"分隔
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}


function validate(){
	if(!check("zrfsbh")){
		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
	}	
	return true;
}
