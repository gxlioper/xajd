
/**
 * 新增谈话类型
 */
function addThlx(){
	var url = "szdw_thlx.do?method=addThlx";
	if("10351" == jQuery("#xxdm").val()){
		url += "&type="+jQuery("#type").val();
	}
	showDialog('添加谈话类型',600,350,url);
}

/**
 * 新增谈话类型保存操作
 */
function addThlxAction(){
	var lxdm = jQuery('#lxdm').val();
	if (jQuery.trim(lxdm)==""){
		showAlert("请将必填项填写完整！");
		return false;
	}
    var lxmc = jQuery('#lxmc').val();
    if (jQuery.trim(lxmc)==""){
        showAlert("请将必填项填写完整！");
        return false;
    }
    var wttg = jQuery('#wttg').val();
    if(jQuery.trim(wttg)==""){
        showAlert("请将必填项填写完整！");
        return false;
    }
	var url = "szdw_thlx.do?method=addThlxAction";
	ajaxSubFormWithFun("thlxForm",url,function(data){
        if (data["message"] == "保存成功！") {
            showAlert(data["message"], {}, {
                "clkFun" : function() {
                        refershParentThlx();
                }
            });
        } else {
            showAlert(data["message"]);
        }
	});
}

/**
 * 修改谈话类型
 */
function updateThlx(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	} else{
        var url = "szdw_thlx.do?method=updateThlx&lxdm=" + rows[0]['lxdm'];
        if("10351" == jQuery("#xxdm").val()){
            url += "&type="+jQuery("#type").val();
        }
		showDialog('修改谈话类型',600,350,url);
	}
}

/**
 * 修改谈话类型保存操作
 */
function updateThlxAction(){
	if(jQuery.trim(jQuery("#lxmc").val())==""){
        showAlert("请将必填项填写完整！");
        return false;
	}
    var wttg = jQuery('#wttg').val();
    if(jQuery.trim(wttg)==""){
        showAlert("请将必填项填写完整！");
        return false;
    }
	var url = "szdw_thlx.do?method=updateThlxAction";
	ajaxSubFormWithFun("thlxForm",url,function(data){
        if (data["message"] == "保存成功！") {
            showAlert(data["message"], {}, {
                "clkFun" : function() {
                    refershParentThlx();
                }
            });
        } else {
            showAlert(data["message"]);
        }
	});
}


/**
 * 删除谈话类型
 */
function deleteThlx(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (rows.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	} else{
		showConfirmDivLayer("您确定要删除所选记录？",{"okFun":function(){
			jQuery.post("szdw_thlx.do?method=deleteThlx",{lxdms:ids.toString(),type:jQuery("#type").val()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * 在弹出谈话类型添加/修改窗口中刷新父页面，并关闭窗口
 */

function refershParentThlx(){
	if (frameElement.api){
		var api = frameElement.api,W = api.opener;
		W.reloadThlxDataTable();
		iFClose();
	}
}
