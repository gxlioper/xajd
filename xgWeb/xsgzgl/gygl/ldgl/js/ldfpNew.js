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
	var checkVal = new Array();
	var checkValObj = jQuery("[name='checkVal']");
	for(var i=0;i<checkValObj.length;i++){
		checkVal.push(jQuery(checkValObj[i]).val());
	}
	map["checkVal"]=checkVal;
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
		var checkVal = new Array();
		var checkValObj = jQuery("[name='checkVal']");
		for(var i=0;i<checkValObj.length;i++){
			checkVal.push(jQuery(checkValObj[i]).val());
		}
		map["checkVal"]=checkVal;
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_bc").css("display", "none");
		jQuery("#li_qx").css("display", "");
		var map = getSuperSearch();
		map["sffp"]="1";
		var checkVal = new Array();
		var checkValObj = jQuery("[name='checkVal']");
		for(var i=0;i<checkValObj.length;i++){
			checkVal.push(jQuery(checkValObj[i]).val());
		}
		map["checkVal"]=checkVal;
		gridSetting2["params"] = map;
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
	var url = "gyglnew_ldgl.do?method=saveFp";
	if(rows.length == 0){
		showAlert("请选择要分配的人员！");
		return false;
	}
	var checkVal = new Array();
	var yhmArray = new Array();
	var checkValObj = jQuery("[name='checkVal']");
	for(var i=0;i<checkValObj.length;i++){
		checkVal.push(jQuery(checkValObj[i]).val());
	}
	jQuery(rows).each(function(i,row){
		yhmArray.push(row['yhm']);
	})
	jQuery.post(url, {
		checkVal : checkVal,
		yhms : yhmArray
	}, function(data) {
		
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				jQuery("#dataTable").reloadGrid();
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
	var url = "gyglnew_ldgl.do?method=cancelFp";
	if(rows.length == 0){
		showAlert("请选择要取消分配的公寓管理员！");
	}
	var lddm = jQuery("[name='checkVal']").val();
	var yhmArray = new Array();
	jQuery(rows).each(function(i,row){
		yhmArray.push(row['yhm']);
	})
	jQuery.post(url, {
		lddm : lddm,
		yhms : yhmArray
	}, function(data) {
		
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				jQuery("#dataTable").reloadGrid();
			}
		});
	}, 'json');
}
