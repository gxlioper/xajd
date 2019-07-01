var DCCLBH = "zxdk_xnwxjkhk.do";//dcclbh,导出功能编号

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewJkhk(\""
			+ rowObject["jgid"]+"\");'>" + cellValue
			+ "</a>";
}
function viewJkhk(jgid) {
	showDialog("项目申报查看", 800, 500, "zxdk_jkhk.do?method=viewJkhk&jgid="+jgid);
}

function add() {
	var url = "zxdk_jkhk.do?method=addJkhk";
	var title = "校内无息借款还款申请";
	showDialog(title, 800, 508, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else if('1'==rows[0]["sjly"]){
		showAlertDivLayer("该记录已提交不能修改！");
	}else {
		var url = 'zxdk_jkhk.do?method=editJkhk&jgid=' + rows[0]["jgid"];
		var title = "校内无息借款还款申请修改";
		showDialog(title, 800, 508, url);
	}

}

// 删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var flag = false;
		jQuery.each(rows,function(i,n) {
			if(n['sjly'] == '1') {
				flag = true;
				return false;						
				}			
			})
		if(flag) {
			showAlertDivLayer("已提交的记录不能删除");
			return false;
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("zxdk_jkhk.do?method=delJkhk", {
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

function saveSqjg(type){
	var ids = null;
	if(type=='save'||type=='submit'){
		ids = "xh-hkzt-hksj"
	}else{
		ids = "hkzt-hksj"
	}
	if(check(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	var url = "zxdk_jkhk.do?method=saveSqjg&type=" + type;
	ajaxSubFormWithFun("xnwxjkhkForm", url, function(data) {
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
			jQuery.post("xmsbXmsb.do?method=submitXmsb", {
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
				jQuery.post("xmsbXmsb.do?method=cancelXmsb", {
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
		showDialog("项目申报审批流程跟踪",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['xmdm'] + "&splc=" + rows[0]['splc']);
}

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, jkhkExportData);
}

//导出方法
function jkhkExportData() {
	setSearchTj();//设置高级查询条件
	var url = "zxdk_jkhk.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
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

function dr(){
	toImportDataNew("IMPORT_XNWXJKHK");
	return false;
}
