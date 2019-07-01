/**
 * 查询
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 *申请 
 */
function xspjsqApply(){
	var url = "xspj_xspjsq.do?method=xspjsqApply";
	var title = "自主申请";
	showDialog(title, 680, 490, url);
}

/**
 * 定义必填字段集合
 */
var ids = "xmmc-bmmc-xmlbdm-cysj-xh-xn-dxqdm-fjid";

/**
 *申请保存(保存草稿、提交申请)
 */
function xspjsqApplySave(saveFlag){

	var xmmc = jQuery("#xmmc").val();
	var bmmc = jQuery("#bmmc").val();
	var xmlbdm = jQuery("#xmlbdm").val();
	var cysj = jQuery("#cysj").val();
	var xh = jQuery("#xh").val();
	var xn = jQuery("#xn").val();
	var dxqdm = jQuery("#dxqdm").val();
	var fjwt = jQuery('.MultiFile-title');
	
	if("" == xmmc || null == xmmc){
		showAlert("请填写项目名称！");
		return false;
	}
	if("" == bmmc || null == bmmc){
		showAlert("请选择指导部门！");
		return false;
	}
	if("" == xmlbdm || null == xmlbdm){
		showAlert("请选择项目类别！");
		return false;
	}
	if("" == cysj || null == cysj){
		showAlert("请填写参与时间！");
		return false;
	}
	if("" == xh || null == xh){
		showAlert("请填写学号！");
		return false;
	}
	if("" == xn || null == xn){
		showAlert("请选择学年！");
		return false;
	}
	if("" == dxqdm || null == dxqdm){
		showAlert("请选择短学期！");
		return false;
	}
	
	if(fjwt.length == 0){
		showAlert("请上传附件！");
		return false;
	}
	
	var url = "xspj_xspjsq.do?method=xspjsqApplySave&saveFlag=" + saveFlag;
	ajaxSubFormWithFun("xspjsqForm", url, function(data){
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
 * 删除
 * @return
 */
function xspjsqDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		for(var i=0;i<ids.length;i++){
			if (rows[i]["shzt"] != "0"){
				showAlertDivLayer("只能删除未提交的记录！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("xspj_xspjsq.do?method=xspjsqDelete",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * 修改
 * @return
 */
function xspjsqUpdate(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else{
		if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3"){
			showAlertDivLayer("只能修改未提交或者已退回的记录！");
			return false;
		}
		var title = "修改自主申请";
		var url = "xspj_xspjsq.do?method=xspjsqUpdate&sqid="+rows[0]["sqid"];
		showDialog(title, 680, 490, url);
	}
}

/**
 * 修改保存(保存草稿、提交申请)
 * 和申请的保存写在一个方法里了
 */
function xspjsqUpdateSave(saveFlag){
	
	/*验证必填字段是否未填写*/
	if(!checkNotNull(ids)){
		showAlert("请将带<font class='red'>*</font>的项目填写完整！");
		return false;
	}
	var url = "xspj_xspjsq.do?method=xspjsqApplySave&saveFlag=" + saveFlag;
	ajaxSubFormWithFun("xspjsqForm", url, function(data){
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
 * 学号连接
 * @param cellValue
 * @param rowObject
 * @return
 */
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='xspjsqView(\""+rowObject["sqid"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}

/**
 * 点击链接查看详细信息
 * @param id
 * @param xh
 * @return
 */
function xspjsqView(sqid) {
	var title = "自主申请查看";
	var url = "xspj_xspjsq.do?method=xspjsqView&sqid="+sqid;
	showDialog(title,690,535,url);
}

/**
 * 提交（支持批量）
 * @return
 */
function xspjsqSubmit(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(0 == ids.length){
		showAlertDivLayer("请选择您要提交的记录");
	}else{
		for(var i=0;i<rows.length;i++){
			if("0" != rows[i]["shzt"] && "3" != rows[i]["shzt"]){
				showAlertDivLayer("请选择未提交或者已退回的记录！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
			var tips = submitLoading();
			try{
				tips.show();
			}catch(e){
				
			}
			jQuery.post("xspj_xspjsq.do?method=xspjsqSubmit",{values:ids.toString()},function(data){
				tips.close();
				showAlertDivLayerSize(data["message"], 340, 200);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * 撤销
 * @return
 */
function xspjsqRevoke() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(ids.length == 0){
		showAlertDivLayer("请选择您要撤销的记录！");
	}else if (ids.length > 1){
		showAlertDivLayer("只能选择一条记录进行撤销！");
	}else{
		if (rows[0]['shzt'] != '5') {
			showAlertDivLayer("只能撤销审核状态为【审核中】的记录！");
			return false;
		}
		showConfirmDivLayer("您确定要撤销选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("xspj_xspjsq.do?method=xspjsqRevoke", {
					values : ids.toString(),
					splcid : rows[0]['splcid']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

/**
 * 流程跟踪
 * @return
 */
function xspjsqTrack(){
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = rows[0]["shzt"];
	if(rows.length != 1){
		showAlertDivLayer("请选择一条记录！");
	}else{
		if(shzt == "0"){
			showAlertDivLayer("无相关流程信息！");
			return false;
		}
		showDialog("流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splcid']);
	}
}

/*dcdlbh,导出功能编号*/
var DCDLBH = "xspj_xspj_xspjsq.do";

/**
 * 导出
 * @return
 */
function xspjsqExport() {
	/*DCCLBH导出功能编号,执行导出函数*/
	customExport(DCDLBH, xspjsqExportData);
}

/**
 * 导出执行
 */
function xspjsqExportData() {
	/*设置高级查询条件*/
	setSearchTj();
	var url = "xspj_xspjsq.do?method=xspjsqExport&dcclbh=" + DCDLBH;
	/*设置高级查询参数*/
	url = addSuperSearchParams(url);
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * 导入页面
 * @return
 */
function xspjsqImport(){
	var url = "xspj_xspjsq.do?method=xspjsqImport";
	var title = "学生评价申请信息导入";
	showDialog(title, 500, 309, url);
}

/**
 * 导入模板下载
 * @return
 */
function mbDownload(){
	var url = "xspj_xspjsq.do?method=downloadFile";
	window.open(url);
}

/**
 * 选择文件，将文件名称赋值到input框
 */
function selectFiles(obj){
	var filefullpath = getFullPath(obj);
	checkFileType(obj);
}

/**
 * 获取input file的名称
 * @param obj
 * @return
 */
function getFullPath(obj){ 
	if(obj){ 
	 if(window.navigator.userAgent.indexOf("Firefox")>=1){ 
		 if(obj.files) 
		 { 
		   return obj.files.item(0).getAsDataURL(); 
		 } 
		 return obj.value; 
	 }
	 return obj.value; 
	}
}

/**
 * 文件类型控制
 * @param obj
 * @return
 */
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

/**
 * 导入保存
 */
function importSave(){
	var url = "xspj_xspjsq.do?method=SaveDrForm";
	
	if(jQuery("#drmb").val() == "" ||　jQuery("#drmb").val() == null){
		showAlert("请选择导入文件！");
		return false;
	}
	
	ajaxSubFormWithFun("xspjsqForm", url, function(data) {
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
		    		  jQuery("#errora").attr("data_file","xspj_xspjsq.do?method=downloadFileError&filename="+data["gid"]);
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

/**
 * 错误数据下载
 * @return
 */
function downloaderror(){
	var url = jQuery("#errora").attr("data_file");
	window.open(url);
}