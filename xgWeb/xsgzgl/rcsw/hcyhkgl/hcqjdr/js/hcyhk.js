
/*刪除*/
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var xhs="";
	for(var i=0;i<rows.length;i++){
		xhs+=rows[i]["xh"]+",";
	}	
	xhs=xhs.substring(0,xhs.length-1);
	if (ids.length == 0) {
		alertInfo("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("hcyhk_hcyhqjdr.do?method=HcqjDelete", {
					values : ids.toString(),
					xhs : xhs.toString()
				}, function(data) {
					alertInfo(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

/* 导入 */
function importXX(){
	var url = "hcyhk_hcyhqjdr.do?method=importhcyhk";
	var title = "导入";
	showDialog(title, 550, 350, url);
}
//下载
function downloadxzmb(){
	window.open("hcyhk_hcyhqjdr.do?method=downloadFile");
}

//错误数据下载
function downloaderror(){
	var url = jQuery("#errora").attr("data_file");
	window.open(url);
}


//文件类型控制
function attachfilename(obj){
	var type = obj.value.substr(obj.value.lastIndexOf(".")+1);
	var types = ["xls"];
	if (jQuery.inArray(type, types) == -1){ //包含在数组中返回 index,如果不包含在数组中,则返回 -1;
		/*如果不符合上传类型,清空input file，兼容性写法，兼容ie和chrome*/
		var file = jQuery(obj);
		file.after(file.clone().val("")); 
		file.remove(); 
		showAlert("只允许上传xls类型的文件!");
		return false;
	}
}
function  saveRr(){
	var url = "hcyhk_hcyhqjdr.do?method=SaveDrForm";
	if(jQuery("#drmb").val() == "" ||　jQuery("#drmb").val() == null){
		showAlert("请选择导入文件！");
		return false;
	}
	ajaxSubFormWithFun("hcqjForm", url, function(data) {
		 if(data["cw"]=="none"){
			 jQuery("#errortr").hide();
		    jQuery("#errora").attr("href","");
   		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
   	 }else{
   			 showAlert(data["message"],{},{"clkFun":function(){
   				      if(data["cw"] == "yes"){
   				    	  if(data["gid"] != "" && data["gid"] != null && data["gid"] != "null"){
   				    		  jQuery("#errortr").show();
   				    		  jQuery("#errora").attr("data_file","hcyhk_hcyhqjdr.do?method=downloadFileError&filename="+data["gid"]);
   				    	  }
   				      }else{
   				    	 jQuery("#errortr").hide();
   				    	jQuery("#errora").attr("data_file","");
   				      }
   				      jQuery("#drmb").val("");
				}});
   		}
		});
	
}