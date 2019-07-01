//单个学生信息查看

function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onClick='viewGjxx(\""+rowObject["gjxxid"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
}
function viewGjxx(gjxxid,cellValue){
	showDialog('学生公假信息',800,420,'xsgjgl.do?method=gjxxView&gjxxid='+gjxxid+"&xh="+cellValue,null);
}
/*刪除*/
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		alertInfo("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("xsgjgl.do?method=GjxxDelete", {
					values : ids.toString()
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
	var url = "xsgjgl.do?method=importGjxxPrepare";
	var title = "导入";
	showDialog(title, 550, 350, url);
}
//下载
function downloadxzmb(){
	window.open("xsgjgl.do?method=downloadFile");
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
	var url = "xsgjgl.do?method=SaveDrForm";
	if(jQuery("#drmb").val() == "" ||　jQuery("#drmb").val() == null){
		showAlert("请选择导入文件！");
		return false;
	}
	ajaxSubFormWithFun("gjjgForm", url, function(data) {
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
   				    		  jQuery("#errora").attr("data_file","xsgjgl.do?method=downloadFileError&filename="+data["gid"]);
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

/* 导出相关 */
function exportXX() {
	customExport('xsgjxxgl.do', exportData);
}
function exportData(){
	setSearchTj();// 设置高级查询条件
	var url = "xsgjgl.do?method=exportData&dcclbh=" + 'ydxxgl.do';// dcclbh,导出功能编号,数据表字段
	url = addSuperSearchParams(url);// 设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
