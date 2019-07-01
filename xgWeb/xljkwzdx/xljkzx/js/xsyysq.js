

/**
 * 新增学生预约咨询申请
 */
function addXsyysq(){
	showDialog('添加学生预约咨询申请',750,440,'xljk_xsyyzx.do?method=addXsyysq');
}

/**
 * 新增学生预约咨询申请保存操作
 */
function addXsyysqAction(){
	if(!checkNotNull("yyzxsj-xslxdh-yyzxzt")){
		showAlert("请将必填项填写完整！");
		return false;
	}
	
	var yyzxzt = jQuery('#yyzxzt').val();
	if(yyzxzt.length > 500){
		showAlertDivLayer("问题简要描述最大字数不超过"+500+",请确认！");
		return false;
	}
	var yyzxxq = jQuery('#yyzxxq').val();
	if(yyzxxq.length > 500){
		showAlertDivLayer("其他备注最大字数不超过"+500+",请确认！");
		return false;
	}
	var url = "xljk_xsyyzx.do?method=addXsyysqAction";
	ajaxSubFormWithFun("xsyysqForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParentXsyysq();
			}
		}});
	});
}

/**
 * 修改学生预约咨询申请
 */
function updateXsyysq(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	} else{
		if(rows[0]['yyzt'] != '1'){
			showAlertDivLayer("只能对预约中的记录进行修改！");
			return false;
		}
		showDialog('修改学生预约咨询申请',750,440,'xljk_xsyyzx.do?method=updateXsyysq&sqid=' + rows[0]['sqid']);
	}
}

/**
 * 修改学生预约咨询申请保存操作
 */
function updateXsyysqAction(){
	if(!checkNotNull("yyzxsj-xslxdh-yyzxzt")){
		showAlert("请将必填项填写完整！");
		return false;
	}
	
	var yyzxzt = jQuery('#yyzxzt').val();
	if(yyzxzt.length > 500){
		showAlertDivLayer("问题简要描述最大字数不超过"+500+",请确认！");
		return false;
	}
	var yyzxxq = jQuery('#yyzxxq').val();
	if(yyzxxq.length > 500){
		showAlertDivLayer("其他备注最大字数不超过"+500+",请确认！");
		return false;
	}
	var url = "xljk_xsyyzx.do?method=updateXsyysqAction";
	ajaxSubFormWithFun("xsyysqForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParentXsyysq();
			}
		}});
	});
}

/**
 * 取消学生预约咨询申请
 */
function cancelXsyysq(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	} else{
		if((rows[0]['yyzt'] == '1' || rows[0]['yyzt'] == '2') && rows[0]['zxzt'] != '1'){
			showConfirmDivLayer("您确定要取消预约咨询申请？",{"okFun":function(){
				showDialog('取消预约咨询申请',550,190,'xljk_xsyyzx.do?method=cancelXsyysq&sqid='+rows[0]['sqid']+'&yyzt='+rows[0]['yyzt']);
			}});
		}else{
			showAlertDivLayer("只能对预约中或预约成功(待咨询或未咨询)的记录操作！");
		}
	}
}

/**
 * 咨询评价
 */
function setZxpj(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要评价的心理咨询记录！");
		return false;
	} else{
		var zxzt = rows[0]['zxzt'];
		if(zxzt != '1'){
			showAlertDivLayer("该条心理咨询预约未实际咨询！不能评价。");
			return false;
		}
		if(rows[0]['sqid']!=null){
			showDialog('咨询评价',530,210,'xljk_xsyyzx.do?method=setZxpj&sqid=' + rows[0]['sqid']);
		}else{
			showDialog('咨询评价',530,210,'xljk_xsyyzx.do?method=setZxpj&zxid=' + rows[0]['zxid']);
		}
	}
}

function changeZxsjj(node){
	var zxsjj = jQuery(node).parent().find(".zxsjj").html();
	jQuery("#zxsjjTd").html(zxsjj=="null"?"":zxsjj);
}

/**
 * 在弹出心理咨询预约信息添加/修改窗口中刷新父页面，并关闭窗口
 */
function refershParentXsyysq(){
	if (frameElement.api){
		var api = frameElement.api,W = api.opener;
		W.reloadXsyysqDataTable();
		iFClose();
	} 
}