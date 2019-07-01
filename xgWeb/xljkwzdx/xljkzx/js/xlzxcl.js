
/**
 * 学号链接（显示预约咨询申请信息及安排咨询信息及咨询师反馈信息）
 * 
 * @param cellValue
 * @param rowObject
 * @return
 */
function xhLinkForZxcl(cellValue, rowObject) {
	var onclickfn = "onclick=\""
			+ "showDialog('查看心理咨询' , 750 , 360 , 'xljk_xlzxcl.do?method=viewXlzxcl&zxid="
			+ rowObject['zxid'] + "')" + "\"";

	var href = "href = 'javascript:void(0);'";

	var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue
			+ "</a>";

	return el;
}

/**
 * 新增心理咨询处理
 */
function addXlzxcl(){
	showDialog('添加心理咨询',750,440,'xljk_xlzxcl.do?method=addXlzxcl');
}

/**
 * 新增心理咨询处理保存操作
 */
function addXlzxclAction(){
	if(!checkNotNull("xh-zzaprq")){
		showAlert("请将必填项填写完整！");
		return false;
	}
	var bz = jQuery('#bz').val();
	if(bz.length > 500){
		showAlertDivLayer("备注最大字数不超过"+500+",请确认！");
		return false;
	}
	var url = "xljk_xlzxcl.do?method=addXlzxclAction";
	ajaxSubFormWithFun("xlzxclForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParentXlzxcl();
			}
		}});
	});
}

/**
 * 修改心理咨询处理
 */
function updateXlzxcl(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	} else{
		showDialog('修改心理咨询',750,440,'xljk_xlzxcl.do?method=updateXlzxcl&zxid=' + rows[0]['zxid']);
	}
}

/**
 * 修改心理咨询处理保存操作
 */
function updateXlzxclAction(){
	if(!checkNotNull("xh-zzaprq")){
		showAlert("请将必填项填写完整！");
		return false;
	}
	var bz = jQuery('#bz').val();
	if(bz.length > 500){
		showAlertDivLayer("备注最大字数不超过"+500+",请确认！");
		return false;
	}
	var url = "xljk_xlzxcl.do?method=updateXlzxclAction";
	ajaxSubFormWithFun("xlzxclForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParentXlzxcl();
			}
		}});
	});
}

/**
 * 删除心理咨询处理
 */
function deleteXlzxcl(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (rows.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	} else{
		for(var i = 0; i<rows.length;i++){
			if(rows[i]['sqid']!=null&&rows[i]['sqid']!=""){
				showAlertDivLayer("学生预约的心理咨询不能删除！请检查您所选的记录里是否包含学生预约的心理咨询。");
				return false;
			}
			if(rows[i]['xspjzt']=="已评价"){
				showAlertDivLayer("学生已评价的记录不能删除。");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除所选记录？",{"okFun":function(){
			jQuery.post("xljk_xlzxcl.do?method=deleteXlzxcl",{zxids:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}
/**
 * 
 * 咨询反馈
 */
function viewZxFk(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	} else{
		if(rows[0]['yyzt'] == '2' || rows[0]['sqid'] == null){
			showDialog('咨询反馈',750,440,'xljk_xlzxcl.do?method=viewZxFk&zxid=' + rows[0]['zxid']);
		}else{
			showAlertDivLayer("只能对预约成功或非学生申请的心理咨询记录进行咨询反馈！");
		}
	}
}
/**
 * 咨询反馈保存操作
 * 
 */
function zxFkAction(){
	var lfzzs = jQuery('#lfzzs').val();
	if(lfzzs.length > 500){
		showAlertDivLayer("来访者主诉最大字数不超过"+500+",请确认！");
		return false;
	}
	var xlhd = jQuery('#xlhd').val();
	if(xlhd.length > 500){
		showAlertDivLayer("咨询过程及主要的心理互动最大字数不超过"+500+",请确认！");
		return false;
	}
	var zxzj = jQuery('#zxzj').val();
	if(zxzj.length > 500){
		showAlertDivLayer("咨询后的总结最大字数不超过"+500+",请确认！");
		return false;
	}
	var url = "xljk_xlzxcl.do?method=zxFkAction";
	ajaxSubFormWithFun("xlzxclForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParentXlzxcl();
			}
		}});
	});
}

/**
 * 在弹出心理咨询处理信息添加/修改窗口中刷新父页面，并关闭窗口
 */
function refershParentXlzxcl(){
	if (frameElement.api){
		var api = frameElement.api,W = api.opener;
		W.reloadXlzxclDataTable();
		iFClose();
	} 
}