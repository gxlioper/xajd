	
//保存分配
function pfzFp(){
	var ids = jQuery("#dataTable").getSeletIds();
	var pfzid=jQuery("#pfzid").val();
	if (ids.length == 0) {
		showAlertDivLayer("请至少选择一个用户！");
		return false;
	} 
		 	jQuery.post("cjWsfPfz.do?method=savePfcy", {
				values : ids.toString(),
				pfzid:pfzid
			}, function(data) {
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
	    			 jQuery("#dataTable").initGrid(gridSetting1);
			    			}});
				},"json");
}

//取消分配
function pfzQxFp(){
	var ids = jQuery("#dataTable").getSeletIds();
	var pfzid=jQuery("#pfzid").val();
	if (ids.length == 0) {
		showAlertDivLayer("请至少选择一个用户！");
		return false;
	}
 	jQuery.post("cjWsfPfz.do?method=savePfcyQx", {
		values : ids.toString(),
		pfzid:pfzid
	}, function(data) {
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			jQuery("#dataTable").initGrid(gridSetting2);
	    			}});
		},"json");
}

//切换Tab页
function selectTab(obj, fpzt) {
	jQuery("#fpzt").val(fpzt);
	if (fpzt == "2") {
		jQuery("#li_fp").css("display", "");
		jQuery("#li_qxfp").css("display", "none");
		var map = getSuperSearch();
		map["fpzt"]=fpzt;
		gridSetting1["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting1);
	}else{
		jQuery("#li_fp").css("display", "none");
		jQuery("#li_qxfp").css("display", "");
		var map = getSuperSearch();
		map["fpzt"]="1";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

function reBack(){
	refreshForm("/xgxt/cjWsf_pfz.do");
}

function query() {
	var map = {};
	map["pfzmc"] = jQuery("#pfzmc").val();
	jQuery("#dataTable").reloadGrid(map);
}


function add() {
	var url = "cjWsfPfz.do?method=addPfz";
	var title = "增加评分组";
	showDialog(title, 350, 200, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条记录");
		return false;
	} 
	var url = 'cjWsfPfz.do?method=updatePfz&pfzid=' + rows[0]["pfzid"];
	var title = "修改评分组";
	showDialog(title, 350, 200, url);
	
}

function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer(jQuery("#lable_some_sc").val());
		return false;
	}
	showConfirmDivLayer(jQuery("#lable_confirm_sc").val(), {
		"okFun" : function() {
			jQuery.post("cjWsfPfz.do?method=delPfz", {
				values : ids.toString()
			}, function(data) {
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			}, 'json');
		}
	});	
}

function rywh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您需要操作的记录！");
		return false;
	}
	var pfzid=rows[0]["pfzid"];
	var url="cjWsfPfz.do?method=showPfcyList&pfzid="+pfzid;
	document.forms[0].action=url;
	document.forms[0].submit();
}