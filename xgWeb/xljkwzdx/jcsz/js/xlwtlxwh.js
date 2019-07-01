
/**
 * 新增心理问题类型
 */
function addXlwtlx(){
	showDialog('添加心理问题类型',370,190,'xljk_xlwtlxwh.do?method=addXlwtlx');
}

/**
 * 新增心理问题类型保存操作
 */
function addXlwtlxAction(){
	var lxdm = jQuery('#lxdm').val();
	if (jQuery.trim(lxdm)==""){
		showAlert("请将必填项填写完整！");
		return false;
	}
	var url = "xljk_xlwtlxwh.do?method=addXlwtlxAction";
	ajaxSubFormWithFun("xlwtlxwhForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParentXlwtlx();
			}
		}});
	});
}

/**
 * 修改心理问题类型
 */
function updateXlwtlx(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	} else{
		showDialog('修改心理问题类型',370,190,'xljk_xlwtlxwh.do?method=updateXlwtlx&lxdm=' + rows[0]['lxdm']);
	}
}

/**
 * 修改心理问题类型保存操作
 */
function updateXlwtlxAction(){
	var url = "xljk_xlwtlxwh.do?method=updateXlwtlxAction";
	ajaxSubFormWithFun("xlwtlxwhForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParentXlwtlx();
			}
		}});
	});
}


/**
 * 删除心理问题类型
 */
function deleteXlwtlx(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (rows.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	} else{
		showConfirmDivLayer("您确定要删除所选记录？",{"okFun":function(){
			jQuery.post("xljk_xlwtlxwh.do?method=deleteXlwtlx",{lxdms:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * 在弹出心理问题类型添加/修改窗口中刷新父页面，并关闭窗口
 */
function refershParentXlwtlx(){
	if (frameElement.api){
		var api = frameElement.api,W = api.opener;
		W.reloadXlwtlxDataTable();
		iFClose();
	} 
}