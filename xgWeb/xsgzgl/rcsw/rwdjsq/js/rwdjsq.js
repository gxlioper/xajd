var action = "rwdjsq.do";
/**
 * 查询
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//点击序号跳转
function dcmcLink(cellValue, rowObject) {
	var rwdjid = rowObject["rwdjid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + rwdjid
			+ "')\" class='name'>" + cellValue + "</a>";
}
// 查看信息
function ckxx(rwdjid) {
	var url = action + "?method=showView&rwdjid=" + rwdjid;
	showDialog("入伍登记信息", 800, 500, url);
}
// 申请
function add() {
	if(jQuery("#notcfbz").val() == "false"){
		showAlertDivLayer("您已有入伍登记申请，请确认！");
		return false;
	}
	var url = action + "?method=add";
	showDialog("入伍登记增加", 800, 500, url);
}
/**
 * 修改
 * 
 * @return
 */
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlert("请选择一条您要修改的记录！");
	} else {
		if ("0" != rows[0]['shzt']&&"3" !=rows[0]['shzt']) {
			showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
			return false;
		}
		var url = action + '?method=update&rwdjid=' + rows[0]["rwdjid"]+"&xh="+ rows[0]["xh"];
		var title = "入伍登记修改";
		showDialog(title, 800, 500, url);
	}
}
/**
 * 保存
 * 
 * @param url
 * @param checkId
 * @return
 */
function save(url,checkId) {
	if (!checkNotNull(checkId)) {
		showAlert("请将带<font class='red'>*</font>的项目填写完整！")
		return false;
	}
	var ywqrwxy = jQuery("[name='ywqrwxy']:checked").val();
	if(ywqrwxy == "" ||  ywqrwxy == null){
		showAlert("请将带<font class='red'>*</font>的项目填写完整！")
		return false;
	}
	jQuery("#form").ajaxSubmit( {
		url : url,
		type : "post",
		dataType : "json",
		success : function(data) {
		if(data["message"] == "保存成功！" || data["message"] == "提交成功！"){
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		}else{
			showAlert(data["message"]);
			return false;
		}
			
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8"
	});
}
// 删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var flag = true;
		jQuery(rows).each(function(i,row){
			if(row["shzt"] != "0" && row["shzt"] != "3"){
				flag = false;
				return false;
			}
		});
		if(!flag){
			showAlertDivLayer(jQuery("#lable_wjt_sc").val());
			return false;
		}
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("rwdjsq.do?method=delSqjl",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}
// 自定义导出 功能
function exportConfig() {
	// DCCLBH导出功能编号,执行导出函数
	customExport('rwdjsq_export.do',exportData);
}

// 导出方法
function exportData(dcclbh) {
	setSearchTj();// 设置高级查询条件
	var url = "rwdjsq.do?method=exportData&dcclbh=rwdjsq_export.do";// dcclbh,导出功能编号
	url = addSuperSearchParams(url);// 设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//提交
function submitBusi() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1){
		showAlertDivLayer("请选择一条您要提交的记录！");
		return false;
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
			showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
			return false;
		}
		showConfirmDivLayer("您确定要提交选择的记录吗？", {
			"okFun" : function() {
				jQuery.post(action+"?method=submitBusi", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

//撤销
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
				jQuery.post(action+"?method=cancelSq", {
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
	} else {
		var shzt = rows[0]["shzt"];
		if ("0" == shzt) {
			showAlertDivLayer(jQuery("#lable_wxglcxx").val());
			return false;
		}
		showDialog("审批流程跟踪",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['rwdjid'] + "&splc=" + rows[0]['splc']);
	}
}