


//删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("xpj_zhpxf.do?method=del",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}
//查看学生链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='zhpxfview(\""
			+ rowObject["zcfid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//查看学生ajaxurl跳转
function zhpxfview(id, xh) {
		showDialog("综合品行分查看", 600, 370, "xpj_zhpxf.do?method=zhpxfck&zcfid="
				+ id + "&xh=" + xh);
}

/* 导入 */
function importXX(){
	var url = "xpj_zhpxf.do?method=importYlbx";
	var title = "导入";
	showDialog(title, 550, 350, url);
}
/* 导出 */
function exportXX() {
	customExport('pj_zcqkcx.do', exportData);
}
function exportData(){
	setSearchTj();// 设置高级查询条件
	var url = "xpj_zhpxf.do?method=exportData&dcclbh=" + 'pj_zcqkcx.do';// dcclbh,导出功能编号,数据表字段
	url = addSuperSearchParams(url);// 设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
//下载
function downloadxzmb(){
	window.open("xpj_zhpxf.do?method=downloadFile");
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
	var url = "xpj_zhpxf.do?method=SaveDrForm";
	if(jQuery("#drmb").val() == "" ||　jQuery("#drmb").val() == null){
		showAlert("请选择导入文件！");
		return false;
	}
	ajaxSubFormWithFun("zhpxfForm", url, function(data) {
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
   				    		  jQuery("#errora").attr("data_file","xpj_zhpxf.do?method=downloadFileError&filename="+data["gid"]);
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