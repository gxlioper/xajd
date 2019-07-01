//查询
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//进入导入页面方法
function importJfxm(){
	var url = "zjly_zhfdr.do?method=importJfxmPrepare";
	var title = "导入";
	showDialog(title, 770, 350, url);
}
//导出方法
function exportJfxm() {
	setSearchTj();//设置高级查询条件
	var url = "zjly_zhfdr.do?method=exportJfxm";
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
//合并导出【详细事项】
function exportXxsx() {
	setSearchTj();//设置高级查询条件
	var url = "zjly_zhfdr.do?method=exportXxsx";
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
//合并导出 【汇总】
function exportHzsx() {
	setSearchTj();//设置高级查询条件
	var url = "zjly_zhfdr.do?method=exportHZsx";
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

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

function  saveRr(){
	var url = "zjly_zhfdr.do?method=SaveDrForm";
	/**/
	if(!checkNotNull("xmmkdm-jfxmdm")){
		showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		return false;
	}
	if(jQuery("#drmb").val() == "" ||　jQuery("#drmb").val() == null){
		showAlert("请选择导入文件！");
		return false;
	}
	ajaxSubFormWithFun("ZhfDrForm", url, function(data) {
		 if(data["message"]=="导入成功！"){
			 jQuery("#errortr").hide();
		    jQuery("#errora").attr("href","");
   		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
   	 }else{
   			   
   			 showAlert(data["message"],{},{"clkFun":function(){
   				      if(data["message"] == "导入失败,请仔细核对【出错记录.xls】！"){
   				    	  if(data["gid"] != "" && data["gid"] != null && data["gid"] != "null"){
   				    		  jQuery("#errortr").show();
   				    		  jQuery("#errora").attr("data_file","zjly_zhfdr.do?method=downloadFileError&filename="+data["gid"]);
   				    		  
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



//删除结果
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
//		var rows = jQuery("#dataTable").getSeletRow();
//		for(var i=0;i<ids.length;i++){
//			if(rows[i]['shzt']=='1'){
//				showAlertDivLayer("已经审定的记录不能删除！");
//				return false;
//			}
//		}
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("zjly_zhfdr.do?method=delRecord",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
//		if(rows[0]['shzt']=='1'){
//			showAlertDivLayer("已经审定的记录不能修改！");
//			return false;
//		}
		var url = 'zjly_zhfdr.do?method=updateJg&id=' + rows[0]["id"]
				+ '&xh=' + rows[0]["xh"];
		var title = "修改综合素质分信息";
		showDialog(title, 770, 552, url);
	}
}

function savejg(type){
	if(!checkNotNull("xmmkdm-jfxmdm-sxsm-cysj")){
		showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		return false;
	}
	var url = "zjly_zhfdr.do?method=updateJg&type=" + type;
	ajaxSubFormWithFun("ZhfDrForm", url, function(data) {
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

//查看学生链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='View(\""
			+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//查看学生ajaxurl跳转
function View(id, xh) {
	showDialog("查看综合素质分信息", 770, 450,'zhf_sq.do?method=viewZhfsq&id='+id);
}

//下载
function downloadxzmb(){
	window.open("zjly_zhfdr.do?method=downloadFile");
}

//错误数据下载
function downloaderror(){
	var url = jQuery("#errora").attr("data_file");
	window.open(url);
}