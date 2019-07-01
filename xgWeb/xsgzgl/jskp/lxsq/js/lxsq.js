
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 增加
 * @return
 */
function add(){
	var sfsh = jQuery("#sfsh").val();
	var url = "jskp_lxsq.do?method=addLxsq";
	var title = "立项申请";
	if("0" == sfsh){
		showDialog(title, 670, 438, url);
	}else{
		showDialog(title, 770, 552, url);
	}
	
}

/**
 * 修改
 * @return
 */
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	var sfsh = jQuery("#sfsh").val();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		if("0" == sfsh){
			if ("0" != rows[0]['shzt1']&&"3" !=rows[0]['shzt1']) {
				showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
				return false;
			}
		}else{
			if ("0" != rows[0]['shzt']&&"3" !=rows[0]['shzt']) {
				showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
				return false;
			}
		}
		var url = 'jskp_lxsq.do?method=updateLxsq&sqid=' + rows[0]["sqid"];
		var title = "立项申请修改";
		if("0" == sfsh){
			showDialog(title, 670, 438, url);
		}else{
			showDialog(title, 770, 552, url);
		}
	}
}

/**
 * 保存立项申请
 * @param saveFlag
 * @return
 */
function saveLxsq(saveFlag){
	/*取参数设置中的是否审核值，0：不审核、1：审核*/
	var sfsh = document.getElementById('sfsh').value;
	var ids = null;
	/*参数设置为否时，【指导老师zdls】、【指导老师联系方式zdlslxfs】、【分值区间zxf - zdf】不用判空*/
	if("0" == sfsh){
		ids = "xmmc"+"-"+"bmmc"+"-"+"xmlb"+"-"+"lxsj"+"-"+"filepath";
	}else{
		ids = "xmmc"+"-"+"bmmc"+"-"+"xmlb"+"-"+"lxsj"+"-"+"fzrlxfs"+"-"+"zdls"+"-"+"zdlslxfs"+"-"+"zxf"+"-"+"zdf";
	}
	if(!checkNotNull(ids)){
		return showAlert("请将带<font class='red'>*</font>的项目填写完整！");
	}
	/*部门判断*/
	if(jQuery("#zdbm").val() == ""){
		return showAlert("系统无该指导部门名称，请重新填写！");
	}
	/*不管参数设置为是或否，不影响此判断*/
	if(parseInt(jQuery("#zdf").val()) < parseInt(jQuery("#zxf").val())){
		return showAlert("最大分必须不得小于最小分！");
	}
	
	var url = null;
	if("0" == sfsh){
		var tjsf = "Edit";
		var tjxs = "submit";
		if("submitAdd" == saveFlag){
			url = "jskp_lxsq.do?method=saveForLxsq&saveFlag=" + tjxs;
		}
		if("submitUpdate" == saveFlag){
			url = "jskp_lxsq.do?method=saveForLxsq&saveFlag=" + tjxs +"&tjsf=" + tjsf;
		}
	}else{
		url = "jskp_lxsq.do?method=saveForLxsq&saveFlag=" + saveFlag;
	}
	ajaxSubFormWithFun("LxsqForm", url, function(data) {
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
function submitBusi() {
	var ids = jQuery("#dataTable").getSeletIds();
	var sfsh = jQuery("#sfsh").val();
	var shztndd;
	if (ids.length != 1){
		showAlertDivLayer("请选择一条您要提交的记录！");
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		
		if("0" == sfsh){
			shztndd = rows[0]["shzt1"];
		}else{
			shztndd = rows[0]["shzt"];
		}
		
		if("0" == sfsh){
			if (rows[0]["shzt1"] != "0" && rows[0]["shzt1"] != "3") {
				showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
				return false;
			}
		}else{
			if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
				showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
				return false;
			}
		}
		showConfirmDivLayer("您确定要提交选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("jskp_lxsq.do?method=submit", {
					values : ids.toString(),shzt:shztndd
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

/**
 * 老师提出要求要增加批量提交功能，2018-04-02，MengWei
 * 由于改了很多次，判断较多，单独增加个按钮
 */
function batchSubmission(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(0 == ids.length){
		showAlertDivLayer("请选择您要提交的记录");
	}else{
		for(var i=0;i<rows.length;i++){
			if("0" != rows[i]["shzt1"] && "3" != rows[i]["shzt1"]){
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
			jQuery.post("jskp_lxsq.do?method=batchSubmission",{values:ids.toString()},function(data){
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
function cancel() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要撤销的记录！");
	} else if (ids.length > 1) {
		showAlertDivLayer("请选择一条您要撤销的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		var sfsh = jQuery("#sfsh").val();
		if("1" == sfsh){
			if (rows[0]['shzt'] != '5') {
				showAlertDivLayer("只有审核中的记录才能被撤销！");
				return false;
			}
		}else{
			if (rows[0]['shzt1'] != '5') {
				showAlertDivLayer("只有审核中的记录才能被撤销！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要撤销选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("jskp_lxsq.do?method=cancel", {
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
 * 删除
 * @return
 */
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var sfsh = jQuery("#sfsh").val();
	if (ids.length == 0){
		return showAlertDivLayer("请选择您要删除的记录！");
	}
	if("0" == sfsh){
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]["shzt1"] != "0" && rows[i]["shzt1"] != "3") {
				showAlertDivLayer(jQuery("#lable_wjt_sc").val());
				return false;
			}
		}
	}else{
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]["shzt"] != "0" && rows[i]["shzt"] != "3") {
				showAlertDivLayer(jQuery("#lable_wjt_sc").val());
				return false;
			}
		}
	}
	showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
		jQuery.post("jskp_lxsq.do?method=del",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
}

/*
 * 流程跟踪
 */
function lcgz() {
	var rows = jQuery("#dataTable").getSeletRow();
	var sfsh = jQuery("#sfsh").val();
	var shzt = null;
	if (1 != rows.length) {
		showAlertDivLayer("请选择一条流程跟踪记录！");
		return false;
	} else {
		if("0" == sfsh){
			shzt = rows[0]["shzt1"];
		}else{
			shzt = rows[0]["shzt"];
		}
		if ("0" == shzt) {
			showAlertDivLayer(jQuery("#lable_wxglcxx").val());
			return false;
		}
		showDialog("审批流程跟踪",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splcid']);
	}
}

/**
 * 查看立项申请
 * @return
 */
function ckLxsq(sqid){
	showDialog("立项申请查看", 770, 450, "jskp_lxsq.do?method=ckLxsq&sqid="
			+ sqid );
}

/**
 * 项目名称Link
 * @return
 */
function xmmcLink(cellValue, rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='ckLxsq(\""
	+ rowObject["sqid"] + "\");'>" + cellValue
	+ "</a>";
}

/**
 * 查看立项申请
 * @return
 */
function rysz(sqid){
	showDialog("人员设置", 770, 450, "jskp_lxsq.do?method=rysz&sqid="
			+ sqid );
}

/**
 * 人员设置Link
 * @return
 */
function ryszLink(cellValue, rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='rysz(\""
	+ rowObject["sqid"] + "\");'>" + "人员设置"
	+ "</a>";
}

/**
 * 数据导入
 */
function dataImport(){
	var url = "jskp_lxsq.do?method=dataImport";
	var title = "纪实考评信息导入";
	showDialog(title, 500, 309, url);
}

/**
 * 下载模板
 * @return
 */
function mbDownload(){
	var url = "jskp_lxsq.do?method=downloadFile";
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
	var url = "jskp_lxsq.do?method=SaveDrForm";
	
	if(jQuery("#drmb").val() == "" ||　jQuery("#drmb").val() == null){
		showAlert("请选择导入文件！");
		return false;
	}
	
	ajaxSubFormWithFun("LxsqForm", url, function(data) {
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
		    		  jQuery("#errora").attr("data_file","jskp_lxsq.do?method=downloadFileError&filename="+data["gid"]);
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