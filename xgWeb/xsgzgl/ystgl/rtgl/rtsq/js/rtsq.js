var ids = "ystxmmc"+"-"+"sqly"+"-"+"tc";
//查询
function searchRs() {
	var map = getSuperSearch();
	var flag = jQuery("#flag").val();
	if (null!=flag&&flag != "") {
		map["flag"] = flag;
	}else{
		map["flag"] = "wsq";
	}
	jQuery("#dataTable").reloadGrid(map);
}

//标签页选项卡
function selectTab(obj, flag) {
	jQuery("#flag").val(flag);
	if (flag == "wsq") {
		var map = getSuperSearch();
		map["flag"]="wsq";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		var map = getSuperSearch();
		map["flag"]="ysq";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='RtsqView(\""
			+ rowObject["rtid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}


//查看学生ajaxurl跳转
function RtsqView(id, xh) {
	showDialog("社团成员明细查询", 770, 450, "ystglRtsq.do?method=viewYstRtsq&rtid="
			+ id + "&xh=" + xh);
}

//删除申请信息
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		for(var i=0;i<ids.length;i++){
			if(rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3"){
				showAlertDivLayer(jQuery("#lable_wjt_sc").val());
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("ystglRtsq.do?method=delYstRtsq",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "ystgl_rtgl_rtsq.do";

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, RtsqExportData);
}

//导出方法
function RtsqExportData() {
	setSearchTj();//设置高级查询条件
	var url = "ystglRtsq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//导入
function importConfig(){
	toImportDataNew("IMPORT_RTSQ");
	return false;
}


function submitBusi() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条您要提交的记录！");
		return false;
	}
	if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("请选择未提交或者已退回的记录！");
		return false;
	} else {
		if ('3' != rows[0]['shzt'] && "0" == rows[0]['sqkg']) {
			showAlertDivLayer("当前申请已关闭，请与管理员联系！");
			return false;
		}
		showConfirmDivLayer("您确定要提交选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("ystglRtsq.do?method=submitBusi", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

function cancel() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要撤销的记录！");
	} else if (ids.length > 1) {
		showAlertDivLayer("请选择一条您要撤销的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['shzt'] != '5') {
				showAlertDivLayer("只有审核中的记录才能被撤销！");
				return false;
			}
		}

		showConfirmDivLayer("您确定要撤销选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("ystglRtsq.do?method=cancelYstRtsq", {
					values : ids.toString(),
					splcid : rows[0]['splc']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}

}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var sqkg = rows[0]['sqkg'];
		if ('3' != rows[0]['shzt'] && "0" == sqkg) {
			showAlertDivLayer("当前申请已关闭，请与管理员联系！");
			return false;
		}
		if ("0" != rows[0]['shzt']&&"3" !=rows[0]['shzt']) {
			showAlertDivLayer("只有未提交和已退回的记录才能修改！");
			return false;
		}
		var url = 'ystglRtsq.do?method=editYstRtsq&rtid=' + rows[0]["rtid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "学生入团申请";
		showDialog(title, 770, 552, url);
	}
}

//增加
function add() {
	var url = "ystglRtsq.do?method=add";
	var title = "学生入团申请";
    showDialog(title, 770, 552, url);
}

//验证字数
function checkzs(obj){
	if(jQuery(obj).val().length > 100){
		showAlert("最大字数为100，现已经超过，请确认！");
		return false;
	}
}


//增加结果保存
function saveYstRtsq(type){
	if(checkNotNull(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	if(checkzs(jQuery("#sqly")) == false || checkzs(jQuery("#tc")) == false ) {
		return false;
	}
	var url = "ystglRtsq.do?method=saveYstRtsq&type=" + type;
	ajaxSubFormWithFun("YstRtsqForm", url, function(data) {
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

/*
 * 流程跟踪
 */
function lcgz() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (1 != rows.length) {
		showAlertDivLayer("请选择一条流程跟踪记录！");
		return false;
	} else {
		var shzt = rows[0]["shzt"];
		if ("0" == shzt) {
			showAlertDivLayer("该记录为未提交状态，请先提交！");
			return false;
		}
		showDialog("学生入团申请审批流程跟踪",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['rtid'] + "&splc=" + rows[0]['splc']);
	}
}

//toggle收起展开
function showYstmx(obj){
	var className = jQuery(obj).attr("class");
	var newClass = className == "up" ? "down" : "up";
	jQuery(obj).attr("class",newClass);
	jQuery("#tbody_toggle").toggle();
}


