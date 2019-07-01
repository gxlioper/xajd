
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function yfLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='yxybsqView(\""
			+ rowObject["sqid"]+"\");'>" + cellValue
			+ "</a>";
}
function yxybsqView(sqid) {
	showDialog("院级月报查看", 800, 600, "yxybgl_sq.do?method=viewYxybsq&sqid="+sqid);
}


function saveYxybsq(type) {
	var flg=true;
	var ids = null;
	if(type=='save'||type=='submit'){
		ids = 'yf-xydm-bygzkzqk-xsgzrd-xssxdt-xstsjgzjy';
	}else{
		ids = 'bygzkzqk-xsgzrd-xssxdt-xstsjgzjy';		
	}
	if(check(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	var url = "yxybgl_sq.do?method=saveYxybsq&type=" + type;
	ajaxSubFormWithFun("yxybsqForm", url, function(data) {
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
	if ("0" == sqkg) {
		showAlertDivLayer("当前已关闭，请与管理员联系！");
		return false;
	}
	var url = "yxybgl_sq.do?method=addYxybsq";
	var title = "月报填写";
	showDialog(title, 800, 600, url);
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

		var url = 'yxybgl_sq.do?method=editYxybsq&sqid=' + rows[0]["sqid"];
		var title = "院级月报修改";
		showDialog(title, 800, 600, url);
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
				jQuery.post("yxybgl_sq.do?method=delYxybsq", {
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
			jQuery.post("yxybgl_sq.do?method=submitYxybsq", {
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
				jQuery.post("yxybgl_sq.do?method=cancelYxybsq", {
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
		showDialog("院级月报审批流程跟踪",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splc']);
}


var DCCLBH = "rcsw_yxybgl_sq.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, yxybsqExportData);
}

//导出方法
function yxybsqExportData() {
	setSearchTj();//设置高级查询条件
	var url = "yxybgl_sq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
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