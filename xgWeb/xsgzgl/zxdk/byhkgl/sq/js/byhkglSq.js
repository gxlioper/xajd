
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='byhkglSqView(\""
			+ rowObject["sqid"]+"\");'>" + cellValue
			+ "</a>";
}
function byhkglSqView(sqid) {
	
	if(sqid == "null") {
		showAlertDivLayer("该记录为未申请状态，请先申请！");
		return false;
	}
	
	showDialog("申请查看", 800, 500, "byhkgl_sq.do?method=viewByhkglSq&sqid="+sqid);
}


function saveByhkgl(type) {
	var flg = true;
	var ids = null;
	var sfzq = jQuery("#sfzq").val();
	
	if("是" == sfzq){
		ids = 'hkje-sfzq-zqyy-zqqx';
	}else{
		ids = "hkje-sfzq";
	}
	
	if(check(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	var url = "byhkgl_sq.do?method=saveByhkgl&type=" + type +"&sfzq=" + sfzq;
	ajaxSubFormWithFun("byhkglSqForm", url, function(data) {
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

function add() {
	var sqkg = jQuery("#sqkg").val();
	var rows = jQuery("#dataTable").getSeletRow();
	if ("0" == sqkg) {
		showAlertDivLayer("当前已关闭，请与管理员联系！");
		return false;
	}else if(rows.length != 1) {
		showAlertDivLayer("请选择一条您要申请的记录！");
		return false;
	}else {
		var shzt = rows[0]["shzt"];
		if(shzt != null) {
			showAlertDivLayer("该学生已经申请过,请确认！");
			return false;
		}
	}
	var url = "byhkgl_sq.do?method=addByhkglSq&xh=" + rows[0]["xh"];
	var title = "申请";
	showDialog(title, 800, 508, url);
}

function update() {
	var sqkg = jQuery("#sqkg").val();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else {

		var shzt = rows[0]["shzt"];
		if ('3' != rows[0]['shzt'] && "0" == sqkg) {
			showAlertDivLayer("当前申请已关闭，请与管理员联系！");
			return false;
		}
		if ("0" != shzt&&"3" != shzt) {
			showAlertDivLayer("只有未提交和已退回的记录才能修改！");
			return false;
		}

		var url = 'byhkgl_sq.do?method=editByhkglSq&sqid=' + rows[0]["sqid"];
		var title = "申请修改";
		showDialog(title, 800, 508, url);
	}

}

// 删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("只能删除未提交或者已退回的记录！");
		return false;
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("byhkgl_sq.do?method=delByhkglSq", {
					values : ids.toString()
				},
						function(data) {
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
			}
		});
	}
}

// 提交
function submitBusi() {
	var flg=true;
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var sqkg = jQuery("#sqkg").val();
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条您要提交的记录！");
		return false;
	}
	if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("请选择未提交或者已退回的记录！");
		return false;
	}
	if ('3' != rows[0]['shzt'] && "0" == sqkg) {
		showAlertDivLayer("当前申请已关闭，请与管理员联系！");
		return false;
	}
	showConfirmDivLayer("您确定要提交选择的记录吗？", {
		"okFun" : function() {
			jQuery.post("byhkgl_sq.do?method=submitByhkglsq", {
				values : ids.toString()
			}, function(data) {
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			}, 'json');
		}
	});	

}
// 撤销
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
				jQuery.post("byhkgl_sq.do?method=cancelByhkglsq", {
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
/*
 * 流程跟踪
 */
function lcgz() {
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (1 != rows.length) {
		showAlertDivLayer("请选择一条流程跟踪记录！");
		return false;
	} 
	var shzt = rows[0]["shzt"];
	if ("0" == shzt) {
		showAlertDivLayer("该记录为未提交状态，请先提交！");
		return false;
	}
	if(shzt == null) {
		showAlertDivLayer("该记录为未申请状态，请先申请！");
		return false;
	}
	
	showDialog("项目申报审批流程跟踪",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splc']);
}


var DCCLBH = "zxdk_byhkgl_byhksq.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, exportData);
}

//导出方法
function exportData() {
	setSearchTj();//设置高级查询条件
	var url = "byhkgl_sq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
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

//金额校验不能以0开头
function ismoney(obj){
  if(obj.value.indexOf('0') == 0){
		 showAlert("金额不能以0开头！",{},{"clkFun":function(){
				jQuery(obj).val("");
				jQuery(obj).focus();
			}});
  }
}

//月份校验不能以0开头
function isyf(obj){
  if(obj.value.indexOf('0') == 0){
		 showAlert("月份不能以0开头！",{},{"clkFun":function(){
				jQuery(obj).val("");
				jQuery(obj).focus();
			}});
  }
}