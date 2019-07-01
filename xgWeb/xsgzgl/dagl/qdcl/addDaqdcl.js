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

/**
 * 保存
 * @return
 */
function  saveForm(){
	if(!check("daqdcl_mc")){
		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
	}
	if(resumeFlag()=="1"){
		showAlert("该档案清单材料名称已存在！");
		return false;
	}
	// 得到JSON对象
	var parameter ={daqdcl_mc:jQuery("#daqdcl_mc").val()};
	var url = "daqdcl.do?method=addDaqdcl&type=save";

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
//    
// 	jQuery("#form").ajaxSubmit({
//		url:url,			
//		type:"post",
//		dataType:"json",
//		success:function(data){
//	 		 if(data["message"]=="保存成功！"){
//	    		 showAlert(data["message"],{},{"clkFun":function(){
//						frameElement.api.opener.refreshForm("daqdcl.do?method=daqdclList");
//						iFClose();
//	    			}});
//	    	 }
//	 		 else{
//	    		 showAlert(data["message"]);
//	    	 }
//		},
//		contentType:"application/x-www-form-urlencoded;charset=utf-8"
//	});	
 	
}

function resumeFlag(){
	var flag="";
	jQuery.ajaxSetup({async:false});
	jQuery.post("daqdcl.do?method=resumeFlag",{daqdcl_mc:jQuery("#daqdcl_mc").val()},function(data){
		flag = data;
	},'json');
	jQuery.ajaxSetup({async:true});
	return flag;
	
}