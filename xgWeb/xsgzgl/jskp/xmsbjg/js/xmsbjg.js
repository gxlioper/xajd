/**切换能力素养、思政素质*/
function changeTab(obj,tabName){
    jQuery("#tabName").val(tabName);
    if("1" == tabName){
    	jQuery("#li_dr").css("display","none");
    	jQuery("#li_exportOne").css("display","");
    	jQuery("#li_exportTwo").css("display","none");
    	var map = getSuperSearch();
    	gridSetting1["params"] = map;
    	jQuery("#dataTable").initGrid(gridSetting1);
    }else{
    	jQuery("#li_dr").css("display", "");
    	jQuery("#li_exportOne").css("display","none");
    	jQuery("#li_exportTwo").css("display","");
    	var map = getSuperSearch();
    	gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
    }
    jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**点击学号查看*/
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='XmsbjgView(\""
			+ rowObject["jgid"] + "\");'>" + cellValue
			+ "</a>";
}

// 保存
function saveXmsbjg(type) {
	var xmid = jQuery("#xmid").val();
	if (jQuery("#hjsj").val() == ""||jQuery("#hjsj").val() == null||jQuery("#sbly").val() == ""||jQuery("#sbly").val() == null||
			xmid==null||xmid=="") {
		showAlert("请将必填项填写完整！");
		return false;
	}
	var url = "jskpXmjg.do?method=saveXmsbjg&type=" + type;
	ajaxSubFormWithFun("jskpXmsbjgForm", url, function(data) {
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
//增加
function add() {
	var url = "jskpXmjg.do?method=addXmsbjg";
	var title = "申报结果填写";
	showDialog(title, 750, 550, url);
}
function checkXmxx(){
	if(jQuery("#xmid").val()==""){
		showAlert("请先选择申报项目！");
		return false;
	}
}
function showXmxxCallBack(rowData){
	var xmid = rowData.xmid;
	var xmmc = rowData.xmmc;
	jQuery("#xmid").val(xmid);
	jQuery("#xmmc").val(xmmc);
}
//修改
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		if(rows[0]['sjly']=='1'){
			showAlertDivLayer("审核流程过来的记录不能修改！");
			return false;
		}
		var url = 'jskpXmjg.do?method=editXmsbjg&jgid=' + rows[0]["jgid"];
		var title = "申报结果修改";
		showDialog(title, 750, 550, url);
	}
}
//查看
function XmsbjgView(id, xh) {
	showDialog("申报结果查看", 750, 550, "jskpXmjg.do?method=viewXmsbjg&jgid="
			+ id);
}
// 删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sbly']=='1'){
				showAlertDivLayer("审核流程过来的记录不能删除！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("jskpXmjg.do?method=delXmsbjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "jskp_xmjg.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, mrgzkhXmsbjgExportData);
}

//导出方法
function mrgzkhXmsbjgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "jskpXmjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/*纪实考评分统计*/
function DataStatistics(){
	var url ="jskpXmjg.do?method=dataStatistics";
	
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


/**================思政素质结果导入开始================*/
/*思政素质结果导入*/
function szszDataImport(){
	var url = "jskpXmjg.do?method=szszDataImport";
	var title = "导入";
	showDialog(title, 770, 350, url);
}

/*导入模板下载*/
function downloadxzmb(){
	window.open("jskpXmjg.do?method=downloadFile");
}

/*将文件名称赋值到input框*/
function selectFiles(obj){
	var filefullpath = getFullPath(obj);
	checkFileType(obj);
}

/*获取input file的名称*/
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

/*文件类型控制*/
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

/*导入保存*/
function saveRr(){
	var url = "jskpXmjg.do?method=SaveDrForm";
	
	if(jQuery("#drmb").val() == "" ||　jQuery("#drmb").val() == null){
		showAlert("请选择导入文件！");
		return false;
	}
	ajaxSubFormWithFun("jskpXmsbjgForm", url, function(data) {
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
		    		  jQuery("#errora").attr("data_file","jskpXmjg.do?method=downloadFileError&filename="+data["gid"]);
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

/*错误数据下载*/
function downloaderror(){
	var url = jQuery("#errora").attr("data_file");
	window.open(url);
}
/**================思政素质结果导入结束================*/


/*dcdlbh,导出功能编号*/
var DCDLBH = "jskp_szszjg.do";
/*自定义导出 功能*/
function szszExport() {
	/*DCCLBH导出功能编号,执行导出函数*/
	customExport(DCDLBH, szszExportData);
}

/*导出方法*/
function szszExportData() {
	/*设置高级查询条件*/
	setSearchTj();
	var url = "jskpXmjg.do?method=szszExportData&dcclbh=" + DCDLBH;
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
