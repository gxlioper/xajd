function searchRs() {
	var map = getSuperSearch();
	var shzt = jQuery("#sqzt").val();
	if (null!=shzt&&shzt != "") {
		map["sqzt"] = shzt;
	}else{
		map["sqzt"] = "wsq";
	}
	jQuery("#dataTable").reloadGrid(map);
}
function jxLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='jxView(\""
			+ rowObject["id"]+"\");'>" + cellValue
			+ "</a>";
}
function jxView(id) {
	showDialog("奖项申请查看", 820, 500, "jxgl_xnjxsq.do?method=viewJx&id="+id);
}

//增加
function add() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要申请的记录！");
	}
	if(rows[0]["shzt"] == '0' || rows[0]["shzt"] == '4'){
		showAlertDivLayer("请选择一条申请状态未申请的记录！");
		return false;
	}
	var url = "jxgl_xsxnjxsq.do?method=xsxnjxsqAdd&jgid="+rows[0]["jgid"];
	var title = "申请";
    showDialog(title, 770, 520, url);	
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		if ("0" != rows[0]['shzt']&&"3" !=rows[0]['shzt']) {
			showAlertDivLayer("只有申请状态为未提交和已退回的记录才能修改！");
			return false;
		}
		var url = 'jxgl_xsxnjxsq.do?method=editSqjg&id=' + rows[0]["id"]
				+ '&xh=' + rows[0]["xh"];
		var title = "奖项申请修改";
		showDialog(title, 770, 500, url);
	}
}

//增加保存结果
function saveSqjg(type){
	var ids = "jxid";
	if(check(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	var url = "jxgl_xsxnjxsq.do?method=saveSqjg&type=" + type;
	ajaxSubFormWithFun("XsxnjxsqForm", url, function(data) {
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

// 切换Tab页
function selectTab(obj, shzt) {
	jQuery("#sqzt").val(shzt);
	if (shzt == "wsq") {
		jQuery("#li_sq").css("display", "");
		jQuery("#li_xg").css("display", "");
		jQuery("#li_sc").css("display", "");
		jQuery("#li_tj").css("display", "");
		jQuery("#li_qx").css("display", "none");
		jQuery("#li_gz").css("display", "none");
		var map = getSuperSearch();
		map["sqzt"]="wsq";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_sq").css("display", "none");
		jQuery("#li_xg").css("display", "none");
		jQuery("#li_sc").css("display", "none");
		jQuery("#li_tj").css("display", "none");
		jQuery("#li_qx").css("display", "");
		jQuery("#li_gz").css("display", "");
		var map = getSuperSearch();
		map["sqzt"]="ysq";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

//撤销记录
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
				jQuery.post("jxgl_xnjxsq.do?method=cancelXmsq", {
					values : rows[0]['id'],
					splcid : rows[0]['shlc']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}

}


function splcInfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (1!=ids.length){
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {		
		showDialog("审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['id']+"&splc="+rows[0]['shlc']);
	}
}

var DCCLBH = "xmsbXmsh.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, khshExportData);
}

//导出方法
function khshExportData() {
	setSearchTj();//设置高级查询条件
	var shzt = jQuery("#shzt").val();
	var url = "xmsbXmsh.do?method=exportData&shzt="+shzt+"&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function showxmxx(obj){
	var className = jQuery(obj).attr("class");
	var newClass = className == "up" ? "down" : "up";

	jQuery(obj).attr("class",newClass);
	jQuery("#t_xmxx").toggle();
}

/**
 * 验证是否存在空项
 * @param ids 要验证的控件id "-"分隔
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}

/**
 * 验证是否存在空项
 * @param ids 要验证的控件id "-"分隔
 * @return
 */
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
		showConfirmDivLayer("您确定要提交选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("jxgl_xnjxsq.do?method=submitBusi", {
					values : rows[0]["id"]
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

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
		showDialog("校内奖项申请审批流程跟踪",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['id'] + "&splc=" + rows[0]['shlc']);
	}
}

//删除结果
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("只能删除未提交或者已退回的记录！");
		return false;
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		var sqids = new Array();
		for(var i=0;i<rows.length;i++){
			sqids.push(rows[i]['id']);
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("jxgl_xnjxsq.do?method=delSqjl",{values:sqids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function xmmcLink(cellValue, rowObject) {
	if('1'==rowObject["sfrm"]){
		cellValue+="<font color='red'>【热门】</font>";
	}
	return "<a href='javascript:void(0);' class='name' onclick='XmsbjgView(\""
			+ rowObject["jgid"] + "\");'>" + cellValue + "</a>";
}


