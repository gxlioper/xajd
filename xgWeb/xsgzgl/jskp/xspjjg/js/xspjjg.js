function changeTab(obj,tabName){
    jQuery("#tabName").val(tabName);
    if("1" == tabName){
    	jQuery("#zj").css("display","none");
    	jQuery("#xg").css("display","none");
    	jQuery("#sc").css("display","none");
    	
    	jQuery("#dc_Nlsy").css("display","");
    	jQuery("#dc_Szsz").css("display","none");
    	var map = getSuperSearch();
    	gridSetting1["params"] = map;
    	jQuery("#dataTable").initGrid(gridSetting1);
    }else{
    	jQuery("#zj").css("display","");
    	jQuery("#xg").css("display","");
    	jQuery("#sc").css("display","");
    	
    	jQuery("#dc_Nlsy").css("display","none");
    	jQuery("#dc_Szsz").css("display","");
    	var map = getSuperSearch();
    	gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
    }
    jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}


/**
 * 查询
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 数据导入按钮
 * @return
 */
function xspjjgImport(){
	var url = "xspj_xspjjg.do?method=xspjjgImport";
	var title = "学生评价信息导入";
	showDialog(title, 500, 309, url);
}

/**
 * 下载模板
 * @return
 */
function mbDownload(){
	var url = "xspj_xspjjg.do?method=downloadFile";
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
 * 文件数据导入保存
 * @return
 */
function importSave(){
	var url = "xspj_xspjjg.do?method=SaveDrForm";
	
	if(jQuery("#drmb").val() == "" ||　jQuery("#drmb").val() == null){
		showAlert("请选择导入文件！");
		return false;
	}
	ajaxSubFormWithFun("xspjjgForm", url, function(data) {
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
		    		  jQuery("#errora").attr("data_file","xspj_xspjjg.do?method=downloadFileError&filename="+data["gid"]);
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
 * 下载
 * @return
 */
function downloadxzmb(){
	window.open("xspj_xspjjg.do?method=downloadFile");
}

/**
 * 错误数据下载
 * @return
 */
function downloaderror(){
	var url = jQuery("#errora").attr("data_file");
	window.open(url);
}

/*增加*/
function xspjjgAdd(){
	var url = "xspj_xspjjg.do?method=xspjjgAdd";
	var title = "评价结果增加";
	showDialog(title, 680, 490, url);
}

/**
 * 定义必填字段集合
 */
var ids = "xmmc-bmmc-xmlbdm-cysj-xh-xn-dxqdm-fjid";

/**
 * 增加、修改保存
 * @return
 */
function xspjjgSave(){
	
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
	var url = "xspj_xspjjg.do?method=xspjjgSave";
	ajaxSubFormWithFun("xspjjgForm", url, function(data){
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
 * 修改
 * @return
 */
function xspjjgUpdate(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else{
		if (rows[0]["sjly"] == "申请审核"){
			showAlertDivLayer("审核流程数据不能修改！");
			return false;
		}
		var title = "修改自主申请";
		var url = "xspj_xspjjg.do?method=xspjjgUpdate&guid="+rows[0]["guid"];
		showDialog(title, 680, 490, url);
	}
}

/**
 * 删除
 */
function xspjjgDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	if(ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='申请审核'){
				showAlertDivLayer("审核流程过来的记录不能删除！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("xspj_xspjjg.do?method=xspjjgDelete",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/*dcdlbh,导出功能编号*/
var DCDLBH = "xspj_xspj_xspjjg.do";

/**
 * 导出【能力素养】
 * @return
 */
function xspjjgExportNlsy() {
	/*DCCLBH导出功能编号,执行导出函数*/
	customExport(DCDLBH, xspjjgExportNlsyData);
}

/**
 * 导出执行【能力素养】
 */
function xspjjgExportNlsyData() {
	/*设置高级查询条件*/
	setSearchTj();
	var url = "xspj_xspjjg.do?method=xspjjgExport&dcclbh=" + DCDLBH + "&type=nlsy";
	/*设置高级查询参数*/
	url = addSuperSearchParams(url);
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * 导出【思政素质】
 * @return
 */
function xspjjgExportSzsz() {
	/*DCCLBH导出功能编号,执行导出函数*/
	customExport(DCDLBH, xspjjgExportSzszData);
}

/**
 * 导出执行【思政素质】
 */
function xspjjgExportSzszData() {
	/*设置高级查询条件*/
	setSearchTj();
	var url = "xspj_xspjjg.do?method=xspjjgExport&dcclbh=" + DCDLBH + "&type=szsz";
	/*设置高级查询参数*/
	url = addSuperSearchParams(url);
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * 学号链接
 */
function xhLink(cellValue, rowObject){
	return "<a hrer='javascript:void(0);' class='name' onclick='xspjjgView(\""+rowObject["guid"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
}

/**
 * 点击链接查看详细信息
 * @param id
 * @param xh
 * @return
 */
function xspjjgView(guid) {
	var title = "评价结果查看";
	var url = "xspj_xspjjg.do?method=xspjjgView&guid="+guid;
	showDialog(title,820,640,url);
}

/**
 * 纪实考评分统计
 * @return
 */
function DataStatistics(){
	var url ="xspj_xspjjg.do?method=dataStatistics";
	
	var xnLength=jQuery("a[name=a_name_xn]").length;
	if(xnLength != "1"){
		showAlertDivLayer("请选择一个学年查询条件！");
		return false;
	}
	setSearchTj();
	url = addSuperSearchParams(url);
	document.forms[0].action = url;
	document.forms[0].submit();
}
