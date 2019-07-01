
/**
 * 新增辅导类型
 */
function addFdlx(){
	showDialog('添加辅导类型',370,190,'xljk_fdlxwh.do?method=addFdlx');
}

/**
 * 新增辅导类型保存操作
 */
function addFdlxAction(){
	var fdlxdm = jQuery('#fdlxdm').val();
	if (jQuery.trim(fdlxdm)==""){
		showAlert("请将必填项填写完整！");
		return false;
	}
	var url = "xljk_fdlxwh.do?method=addFdlxAction";
	ajaxSubFormWithFun("fdlxwhForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParentFdlx();
			}
		}});
	});
}

/**
 * 修改辅导类型
 */
function updateFdlx(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	} else{
		showDialog('修改辅导类型',370,190,'xljk_fdlxwh.do?method=updateFdlx&fdlxdm=' + rows[0]['fdlxdm']);
	}
}

/**
 * 修改辅导类型保存操作
 */
function updateFdlxAction(){
	var url = "xljk_fdlxwh.do?method=updateFdlxAction";
	ajaxSubFormWithFun("fdlxwhForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParentFdlx();
			}
		}});
	});
}

/**
 * 删除辅导类型
 */
function deleteFdlx(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (rows.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	} else{
		showConfirmDivLayer("您确定要删除所选记录？",{"okFun":function(){
			jQuery.post("xljk_fdlxwh.do?method=deleteFdlx",{fdlxdms:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * 在弹出辅导类型添加/修改窗口中刷新父页面，并关闭窗口
 */
function refershParentFdlx(){
	if (frameElement.api){
		var api = frameElement.api,W = api.opener;
		W.reloadFdlxDataTable();
		iFClose();
	} 
}