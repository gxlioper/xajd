
/**
 * 新增咨询师
 */
function addZxsxx(){
	showDialog('添加咨询师信息',750,440,'xljk_zxsgl.do?method=addZxsxx');
}

/**
 * 新增咨询师保存操作
 */
function addZxsxxAction(){
	var zgh = jQuery('#zgh').val();
	if (jQuery.trim(zgh)==""){
		showAlert("请将必填项填写完整！");
		return false;
	}
	var kjdrs = jQuery('#kjdrs').val();
	if(isNaN(kjdrs)){
		showAlert("日预约上限只能填写数字！");
		jQuery('#kjdrs').focus();
		return false;
	}
	var zxsjj = jQuery('#zxsjj').val();
	if(zxsjj.length > 500){
		showAlertDivLayer("备注最大字数不超过"+500+",请确认！");
		return false;
	}
	var url = "xljk_zxsgl.do?method=addZxsxxAction";
	ajaxSubFormWithFun("zxsxxForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParentZxsxx();
			}
		}});
	});
}

/**
 * 修改咨询师
 */
function updateZxsxx(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	} else{
		showDialog('修改咨询师信息',750,440,'xljk_zxsgl.do?method=updateZxsxx&zgh=' + rows[0]['zgh']);
	}
}

/**
 * 修改咨询师保存操作
 */
function updateZxsxxAction(){
	var zgh = jQuery('#zgh').val();
	if (jQuery.trim(zgh)==""){
		showAlert("请将必填项填写完整！");
		return false;
	}
	var kjdrs = jQuery('#kjdrs').val();
	if(isNaN(kjdrs)){
		showAlert("日预约上限只能填写数字！");
		jQuery('#kjdrs').focus();
		return false;
	}
	var zxsjj = jQuery('#zxsjj').val();
	if(zxsjj.length > 500){
		showAlertDivLayer("备注最大字数不超过"+500+",请确认！");
		return false;
	}
	var url = "xljk_zxsgl.do?method=updateZxsxxAction";
	ajaxSubFormWithFun("zxsxxForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParentZxsxx();
			}
		}});
	});
}

/**
 * 删除咨询师
 */
function deleteZxsxx(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (rows.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	} else {
		showConfirmDivLayer("您确定要删除所选记录？",{"okFun":function(){
			jQuery.post("xljk_zxsgl.do?method=deleteZxsxx",{zghs:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * 设置在岗状态
 */
function setZxsxxStatus(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (rows.length == 0){
		showAlertDivLayer("请选择您要设置的记录！");
		return false;
	} else {
		showDialog('设置在岗状态',370,170,'xljk_zxsgl.do?method=setZxsxxStatus&zghs=' + ids.toString());
	}
}

/**
 * 咨询预约说明
 */
function setZxyysm(){
	showDialog('咨询预约说明',650,230,'xljk_zxsgl.do?method=setZxyysm');
}

/**
 * 在弹出咨询师信息添加/修改窗口中刷新父页面，并关闭窗口
 */
function refershParentZxsxx(){
	if (frameElement.api){
		var api = frameElement.api,W = api.opener;
		W.reloadZxsxxDataTable();
		iFClose();
	} 
}