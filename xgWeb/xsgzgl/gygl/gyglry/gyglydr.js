

//将文件名称赋值到input框
function attachfilename(obj){
	var filefullpath = getFullPath(obj);
//	jQuery(obj).parent().find("input[name='wjmc']").val(filefullpath);
	checkFileType(obj);
}

//获取input file的名称
function getFullPath(obj){ 
	if(obj) 
	{ 
		 if(window.navigator.userAgent.indexOf("Firefox")>=1) 
		 { 
			 if(obj.files) 
			 { 
				 return obj.files.item(0).getAsDataURL(); 
			 } 
			 return obj.value; 
		 } 
		 return obj.value; 
	 } 
} 

//文件类型控制
function checkFileType(obj){
	var type = obj.value.substr(obj.value.lastIndexOf(".")+1);
	var types = ["xls"];
	if (jQuery.inArray(type, types) == -1){
		/*如果不符合上传类型,清空input file，兼容性写法，兼容ie和chrome*/
		var file = jQuery(obj);
		file.after(file.clone().val("")); 
		file.remove(); 
		showAlert("只允许上传xls类型的文件。");
		return false;
	}
}

function  saveDr(){
	var url = "gyglnew_gyglry.do?method=dr";
	if(jQuery("#file").val() == "" ||　jQuery("#file").val() == null){
		showAlert("请选择导入文件！");
		return false;
	}
	ajaxSubFormWithFun("form", url, function(data) {
		if(data["drNum"] && !data["message"]){
			var message = "成功导入"+data["drNum"]+"条记录，存在"+data["errNum"]+"条错误记录！"
			 showAlert(message,{},{"clkFun":function(){
				  	if(data["fileName"] != "" && data["fileName"] != null && data["fileName"] != "null"){
			    		  jQuery("#errortr").show();
			    		  jQuery("#errora").attr("data_file","gyglnew_gyglry.do?method=downloadError&filename="+data["fileName"]);
			    		  
			    	  }else{
			    		  if (parent.window){
								refershParent();
							}
			    	  }
			 }});
		}else{
			return showAlert(data["message"]);
		}
	});
	
}








//下载
function downloadxzmb(){
	window.open("gyglnew_gyglry.do?method=downloadMb");
}

//错误数据下载
function downloaderror(){
	var url = jQuery("#errora").attr("data_file");
	window.open(url);
}