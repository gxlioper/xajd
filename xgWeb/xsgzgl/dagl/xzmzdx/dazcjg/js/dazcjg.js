/**
 * 查询
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 增加
 * @return
 */
function dazcjgAdd(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("请选择一条您要登记的记录！");
	}else{
		if("未登记" != rows[0]["dazcxxmc"]){
			showAlertDivLayer("只能增加未登记的纪录！");
			return false;
		}
		var url = "dagl_dazcjg.do?method=dazcjgAdd&xh=" + rows[0]["xh"];
		var title = "档案转出增加";
		showDialog(title, 680, 480, url);
	}
}

/**
 * 修改
 * @return
 */
function dazcjgUpdate(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else{
//		if("1" == rows[0]["sjly"]){
//			showAlertDivLayer("审核流程数据不能修改！");
//			return false;
//		}
		if("未登记" == rows[0]["dazcxxmc"]){
			showAlertDivLayer("未登记的记录不能修改！");
			return false;
		}
		var title = "档案转出修改";
		var url = "dagl_dazcjg.do?method=dazcjgUpdate&guid="+rows[0]["guid"];
		showDialog(title, 680, 480, url);
	}
}

/**
 * 转出方式onchange事件
 * @return
 */
function changeZcfs(){
	var zcfs = jQuery("input[name='zcfs']:checked").val();
	if("1" == zcfs){
		/*隐藏字段*/
		jQuery("#mailedOne").show();
		jQuery("#mailedTwo").show();
		jQuery("#mailedThree").show();
		jQuery("#mailedFour").show();
		jQuery("#mailedFive").hide();
		jQuery("#mailedSix").hide();
		jQuery("#byoOne").hide();
		jQuery("#byoTwo").hide();
	}else{
		/*隐藏字段*/
		jQuery("#mailedOne").hide();
		jQuery("#mailedTwo").hide();
		jQuery("#mailedThree").hide();
		jQuery("#mailedFour").hide();
		jQuery("#mailedFive").hide();
		jQuery("#mailedSix").hide();
		jQuery("#byoOne").show();
		jQuery("#byoTwo").show();
	}
	/*清空值*/
	jQuery("#yjdz").val("");
	jQuery("#yzbm").val("");
	jQuery("#sjr").val("");
	jQuery("#sjrdh").val("");
	jQuery("#dwmc").val("");
	jQuery("#dwdz").val("");
	jQuery("#yjzt").attr("checked", false);
	jQuery("#kdfs").val("");
	jQuery("#kddh").val("");
	jQuery("#yjsj").val("");
	jQuery("#zddacn").attr("checked", false);
	jQuery("#yqtdrq").val("");
	jQuery("#sjtdrq").val("");
	jQuery("#sjtdr").val("");
}

/**
 * 邮寄状态onchange事件
 * @return
 */
function changeYjzt(){
	var yjzt = jQuery("input[name='yjzt']:checked").val();
	if('1' == yjzt){
		jQuery("#mailedFive").show();
		jQuery("#mailedSix").show();
	}else{
		jQuery("#mailedFive").hide();
		jQuery("#mailedSix").hide();
		jQuery("#kdfs").val("");
		jQuery("#kddh").val("");
		jQuery("#yjsj").val("");
	}
}

/**
 * 增加、修改保存
 * @return
 */
function dazcjgSave(){
	
	var xh = jQuery("#xh").val();//学号
	var zcfs = jQuery("input[name='zcfs']:checked").val();//转出方式
	var yjdz = jQuery("#yjdz").val();//邮寄地址
	var yzbm = jQuery("#yzbm").val();//邮政编码
	var sjr = jQuery("#sjr").val();//收件人
	var sjrdh = jQuery("#sjrdh").val();//收件人电话
	var dwmc = jQuery("#dwmc").val();//单位名称
	var dwdz = jQuery("#dwdz").val();//单位地址
	var kdfs = jQuery("#kdfs").val();//快递方式
	var kddh = jQuery("#kddh").val();//快递单号
	var yjsj = jQuery("#yjsj").val();//邮寄时间
	var yjzt = jQuery("input[name='yjzt']:checked").val();//邮寄状态
	var zddacn = jQuery("input:checkbox[name='zddacn']:checked").val();//自带档案承诺
	var yqtdrq = jQuery("#yqtdrq").val();//预期提档日期
	var sjtdrq = jQuery("#sjtdrq").val();//实际提档日期
	var sjtdr = jQuery("#sjtdr").val();//实际提档人
	var ids = null;
	
	if(xh == null || xh == ''){
		showAlert("请您先选择一名学生！");
		return false;
	}
	
	if(zcfs == null || zcfs == ''){
		showAlert("请您选择档案转出方式！");
		return false;
	}
	
	if("1" == zcfs){
		ids = "yjdz-yzbm-sjr-sjrdh-dwmc-dwdz";
		/*验证必填字段是否未填写*/
		if(!checkNotNull(ids)){
			showAlert("请将带<font class='red'>*</font>的项目填写完整！");
			return false;
		}
		if(yjzt == null || yjzt == ''){
			showAlert("请您选择邮寄状态！");
			return false;
		}
		
		if('1' == yjzt){
			if(kdfs == null || kdfs == ''){
				showAlert("请您先填写快递方式！");
				return false;
			}
			if(kddh == null || kddh == ''){
				showAlert("请您先填写快递单号！");
				return false;
			}
			if(yjsj == null || yjsj == ''){
				showAlert("请您先填写邮寄时间！");
				return false;
			}
		}
	}else{
		if(zddacn == null || zddacn == ''){
			showAlert("请先接受档案转出协议！");
			return false;
		}
		ids = "yqtdrq";
		/*验证必填字段是否未填写*/
		if(!checkNotNull(ids)){
			showAlert("请将带<font class='red'>*</font>的项目填写完整！");
			return false;
		}
	}
	
	var url = "dagl_dazcjg.do?method=dazcjgSave";
	ajaxSubFormWithFun("dazcjgForm", url, function(data){
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
function dazcjgDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	if(ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['dazcxxmc']=='未登记'){
				showAlertDivLayer("未登记的记录不能删除！");
				return false;
			}
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("审核流程过来的记录不能删除！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("dagl_dazcjg.do?method=dazcjgDelete",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * 学号链接
 */
function xhLink(cellValue, rowObject){
	return "<a hrer='javascript:void(0);' class='name' onclick='dazcjgView(\""+rowObject["xh"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
}

/**
 * 点击链接查看详细信息
 * @param id
 * @param xh
 * @return
 */
function dazcjgView(xh) {
	var title = "档案转出查看";
	var url = "dagl_dazcjg.do?method=dazcjgView&xh="+xh;
	showDialog(title, 660, 430, url);
}

/**
 * 查看
 * @return
 */
function dazcsqView(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要查看的记录！");
	}else{
		var url = 'dagl_dazcjg.do?method=dazcjgView&guid='+ rows[0]["guid"]+"&xh="+rows[0]["xh"];	
		var title = "档案转出查看";
		showDialog(title, 660, 390, url);
	}
}

/*dcdlbh,导出功能编号*/
var DCDLBH = "xsxx_dagl_dazcjg.do";

/**
 * 导出
 * @return
 */
function dazcjgExport() {
	/*DCCLBH导出功能编号,执行导出函数*/
	customExport(DCDLBH, dazcjgExportData);
}

/**
 * 导出执行
 */
function dazcjgExportData() {
	/*设置高级查询条件*/
	setSearchTj();
	var url = "dagl_dazcjg.do?method=dazcjgExport&dcclbh=" + DCDLBH;
	/*设置高级查询参数*/
	url = addSuperSearchParams(url);
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * 导入
 * @return
 */
function dazcjgImport(){
	toImportDataNew("IMPORT_N712604_DAZCJG");
	return false;
}