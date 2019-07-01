function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//dcclbh,导出功能编号
var DCCLBH = "";
//自定义导出 功能
function exportConfig(bz) {
	//DCCLBH导出功能编号,执行导出函数 
	
	if(bz == '1'){
		DCCLBH = "xg_zjly_ylbx.do";
		jQuery("#cxblb").val("新参保");
	}else{
		DCCLBH = "xg_zjly_ylbx_xb.do";
		jQuery("#cxblb").val("续保");
	}
	customExport(DCCLBH, ExportDatas);
}

//导出方法
function ExportDatas() {
	setSearchTj();//设置高级查询条件
	var url = "zjly_ylbx.do?method=exportData&dcclbh=" + DCCLBH+"&cxblb="+jQuery("#cxblb").val();//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


//导入
//function importConfig(){
//	toImportDataNew("IMPORT_YLBX");
//	return false;
//}

//删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("zjly_ylbx.do?method=del",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}
//查看学生链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='ylbxview(\""
			+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//查看学生ajaxurl跳转
function ylbxview(id, xh) {
	if(id.length<32){
		alertInfo("该学生未参保！");
		return false;
	}else{
		showDialog("医疗保险查看", 750, 350, "zjly_ylbx.do?method=ylbxck&id="
				+ id + "&xh=" + xh);
	}
}

//增加页面跳转
function add(){
	showDialog('增加',600,450,'zjly_ylbx.do?method=Ylbxadd');
}


//编辑页面跳转
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条您要修改的记录！");
	} else {
		if(rows[0]["id"]==null){
			alertInfo("该学生未参保！");
			return false;
		}else{
			showDialog('修改学生医保信息',600,450,'zjly_ylbx.do?method=Ylbxedit&id=' + rows[0]["id"]+"&xh="+rows[0]["xh"]);
		}
	}
}

//保存编辑
function save(url,checkId) {
	if (!checkNull(checkId)) {
		return false;
	}
	//lock();
	jQuery("#form").ajaxSubmit( {
		url : url,
		type : "post",
		dataType : "json",
		success : function(data) {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8"
	});
	//unlock();
}
/* 导入 */
function importXX(){
	var url = "zjly_ylbx.do?method=importYlbx";
	var title = "导入";
	showDialog(title, 550, 350, url);
}
//下载
function downloadxzmb(){
	window.open("zjly_ylbx.do?method=downloadFile");
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
	var url = "zjly_ylbx.do?method=SaveDrForm";
	if(jQuery("#drmb").val() == "" ||　jQuery("#drmb").val() == null){
		showAlert("请选择导入文件！");
		return false;
	}
	ajaxSubFormWithFun("ylbxForm", url, function(data) {
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
   				    		  jQuery("#errora").attr("data_file","zjly_ylbx.do?method=downloadFileError&filename="+data["gid"]);
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