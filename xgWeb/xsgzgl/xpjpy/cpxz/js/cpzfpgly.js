/**
 * 查询
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	var sffp = jQuery("#sffp").val();
	if (null!=sffp&&sffp != "") {
		map["sffp"] = sffp;
	}else{
		map["sffp"] = "0";
	}
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 页签选择
 * @param obj
 * @param shzt
 * @return
 */
function selectTab(obj, shzt) {
	jQuery("#sffp").val(shzt);
	if (shzt == "0") {
		jQuery("#li_bc").css("display", "");
		jQuery("#li_qx").css("display", "none");
		var map = getSuperSearch();
		map["sffp"]="0";
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_bc").css("display", "none");
		jQuery("#li_qx").css("display", "");
		var map = getSuperSearch();
		map["sffp"]="1";
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

/**
 * 保存分配
 * @return
 */
function saveFp(){
	var rows = jQuery("#dataTable").getSeletRow();
	var url = "xpj_cpxz.do?method=saveFp";
	if(rows.length == 0){
		showAlert("请选择要分配的人员！");
		return false;
	}
	var yhmArray = new Array();

	jQuery(rows).each(function(i,row){
		yhmArray.push(row['zgh']);
	})
	jQuery.post(url, {
        cpzdms:jQuery("#cpzdms").val(),
        zghs : yhmArray
	}, function(data) {
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
                refershParent();
			}
		});
	}, 'json');
}

/**
 * 取消分配
 * 取消页签页面只有当父页面楼栋记录单选的时候才存在
 * @return
 */
function cancelFp(){
	var rows = jQuery("#dataTable").getSeletRow();
	var url = "xpj_cpxz.do?method=cancelFp";
	if(rows.length == 0){
		showAlert("请选择要取消分配的参评组管理员！");
	}
	var yhmArray = new Array();
	jQuery(rows).each(function(i,row){
		yhmArray.push(row['zgh']);
	})
	jQuery.post(url, {
        cpzdms:jQuery("#cpzdms").val(),
		zghs : yhmArray
	}, function(data) {
		
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
                refershParent();
			}
		});
	}, 'json');
}
