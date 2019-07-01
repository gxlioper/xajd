/**
 * 查询
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 酬金输入框值改变自动计算工时数
 * @return
 */
function cjchange(){
	var bcje = jQuery("#bcje").val();
	if(jQuery.trim(bcje) == "" || jQuery.trim(bcje) == null){
		jQuery("#gss").val("0");
		return;
	}
	var cjbz = parseFloat(jQuery("#cjbz").val());
	var gss = (parseFloat(bcje)/cjbz).toFixed(1);
	jQuery("#gss").val(gss);
}

/**
 * 删除
 * @return
 */
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sftj']=='1'){
				showAlertDivLayer("已经提交的数据不能删除！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("cjff_zjdx.do?method=deljg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "qgzx_jfcjgl_cjff_zjdx.do";//dcclbh,导出功能编号

/**
 * 自定义导出
 * @return
 */
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, jgExportData);
}

/**
 * 导出
 * @return
 */
function jgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "cjff_zjdx.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * 保存
 * @param type
 * @return
 */
function saveForm(type){
	var ids = "xh"+"-"+"xm"+"-"+"ffndyf"+"-"+"yrdwdm"+"-"+"gwxzdm"+"-"+"gwlbdm"+"-"+"xqdm"+"-"+"bcje"+"-"+"gss"+"-"+"gznr";
	if(checkNotNull(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		return false;
	}
	var bcje = jQuery("#bcje").val();
	var sxsz = jQuery("#sxsz").val();
	var sfyxcgcjsx = jQuery("#sfyxcgcjsx").val();
	
	if("否" == sfyxcgcjsx){
		if(parseInt(bcje) > parseInt(sxsz)){
			showAlert("本月发放金额不能大于酬金上限！");
			return false;
		}
	}else{
		if(parseInt(bcje) > parseInt(sxsz) && (jQuery("#bz").val() == null || jQuery("#bz").val() == "")){
			showAlert("本月发放金额大于酬金上限，必须填写备注！");
			return false;
		}
	}
	
	var url = "cjff_zjdx.do?method=saveForm&type=" + type;
	ajaxSubFormWithFun("CjffForm", url, function(data) {
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
 * 提交
 * @return
 */
function tj(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要提交的记录！");
	} else {
		showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
			jQuery.post("cjff_zjdx.do?method=tj",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * 增加
 * @return
 */
function add(){
	var url = "cjff_zjdx.do?method=cjffAdd";
	var title = "酬金发放";
	showDialog(title, 770, 550, url);
}

/**
 * 修改
 * @return
 */
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
	    if(rows[0]["sftj"] == "1"){
	    	showAlertDivLayer("已经提交的数据不能修改！");
			return false;
		}
		var url = 'cjff_zjdx.do?method=cjffEdit&id=' + rows[0]["id"]
				+ '&xh=' + rows[0]["xh"];
		var title = "酬金发放修改";
		showDialog(title, 770, 550, url);
	}
}

/**
 * 查看
 * @return
 */
function ck(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要查看的记录！");
	} else {
		var url = 'cjff_zjdx.do?method=cjffck&id=' + rows[0]["id"]
				+ '&xh=' + rows[0]["xh"];
		var title = "酬金发放查看";
		showDialog(title, 770, 550, url);
	}
}

/**
 * 导入
 * @return
 */
function dr(){
	var url = "cjff_zjdx.do?method=dr";
	var title = "导入";
	showDialog(title, 770, 350, url);
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
	var url = "cjff_zjdx.do?method=SaveDrForm";
	if(jQuery("#drmb").val() == "" ||　jQuery("#drmb").val() == null){
		showAlert("请选择导入文件！");
		return false;
	}
	ajaxSubFormWithFun("CjffForm", url, function(data) {
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
   				      if(data["message"] == "导入失败,请仔细核对【错误数据.xls】！"){
   				    	  if(data["gid"] != "" && data["gid"] != null && data["gid"] != "null"){
   				    		  jQuery("#errortr").show();
   				    		  jQuery("#errora").attr("data_file","cjff_zjdx.do?method=downloadFileError&filename="+data["gid"]);
   				    		  
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

//下载
function downloadxzmb(){
	window.open("cjff_zjdx.do?method=downloadFile");
}

//错误数据下载
function downloaderror(){
	var url = jQuery("#errora").attr("data_file");
	window.open(url);
}


/**
 * 取消提交
 * @return
 */
function cancel() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要取消提交的记录！");
	} else {
		showConfirmDivLayer("您确定要取消提交选择的记录吗？",{"okFun":function(){
			jQuery.post("cjff_zjdx.do?method=CancelTjRecord",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}