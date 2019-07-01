

/**
 * 查询
 */
function searchRs() {
	var map = getSuperSearch();
	var shzt = jQuery("#shlx").val();
	if (null!=shzt&&shzt != "") {
		map["shlx"] = shzt;
	}else{
		map["shlx"] = "dsh";
	}
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 查看
 */
function zyfwShShow(fdid, xh) {
	var title = jQuery("#gnmkmc").val()+"查看";
	var url = "xsxx_zyfwgl_sh.do?method=zyfwShShow&fwid="+fwid+ "&xh=" + xh;
	showDialog(title, 750, 550, url);
}

/**
 * 学号格式化为链接
 */
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='zyfwShShow(\""
			+ rowObject["fwid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

/**
 * 服务地点过长截取
 */
function fwddSubString(cellValue, rowObject){
	var cellValueNotNull = cellValue ? cellValue : "";
	var cellValueNew = cellValueNotNull.length > 20 ? cellValue.substring(0,20)+"..." : cellValueNotNull;
	return "<span title='"+cellValueNotNull+"' >" + cellValueNew + "</span>";
}

/**
 * 审核
 */
function sh() {
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = jQuery("#shlx").val();
	if (rows.length == 0) {
		showAlertDivLayer("请选择您要审核的记录")
		return false;
	}
	if (shzt == "ysh") {
		showAlertDivLayer("已处理记录不能再次审核");
		return false;
	}
	if (rows.length == 1) {
		var title = "审核";
		var url = "ztbhgl_ztbhsh.do?method=ztbhShDgsh&sqid=" + rows[0]["sqid"]  + '&shid=' + rows[0]["shid"] + '&gwid=' + rows[0]["gwid"];
		showDialog(title, 700, 480, url);
	} else {
		showDialog("批量审核", 500, 250, "ztbhgl_ztbhsh.do?method=ztbhShPlsh");
	}
}

/**
 * 单个审核保存
 */
function saveForDgsh(){
	if (jQuery("#shyj").val() == ""){
		showAlert("请将必填项填写完整！");
		return false;
	}
	var url = "ztbhgl_ztbhsh.do?method=saveForDgsh";
	ajaxSubFormWithFun("ZtbhShForm",url,function(data){
		 if(data["message"]=="保存成功！"){
    		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
    	 }else{
    		 showAlert(data["message"]);
    		}
		});
}

/**
 * 批量审核保存
 */
function saveForPlsh(shjg,shyj) {
	
	var rows = jQuery("#dataTable").getSeletRow();
	var sqids = new Array();
	var gwids = new Array();
	var sqrs = new Array();
	jQuery.each(rows, function(i, row) {
        sqids.push(row["sqid"]);
		gwids.push(row["gwid"]);
        sqrs.push(row["sqr"]);
	});
	jQuery.post("ztbhgl_ztbhsh.do?method=saveForPlsh", {
		shjg : shjg,
		splc : rows[0]["splc"],
		sqids : sqids,
		gwids : gwids,
        xhs : sqrs,
		shyj : shyj
	}, function(data) {
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				jQuery("#dataTable").reloadGrid();
			}
		});
	}, 'json');
}

/**
 * 切换Tab页
 */
function selectTab(obj, shlx) {
	jQuery("#shlx").val(shlx);
	if (shlx == "dsh") {
		jQuery("#li_sh").css("display", "");
		jQuery("#li_qx").css("display", "none");
		var map = getSuperSearch();
		map["shlx"]="dsh";
		gridSetting["params"] = map;
		gridSetting["radioselect"] = false;
	} else {
		jQuery("#li_sh").css("display", "none");
		jQuery("#li_qx").css("display", "");
		var map = getSuperSearch();
		map["shlx"]="ysh";
		gridSetting["params"] = map;
		gridSetting["radioselect"] = true;
	}
	jQuery("#dataTable").initGrid(gridSetting);
	
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

/**
 * 审核撤销
 */
function cancelSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要撤消的审核记录！");
	} else {
		var splc = rows[0]["splc"];
		var shid = rows[0]["shid"];
		var sqid = rows[0]["sqid"];
		var shzt = rows[0]["shzt"];
		showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
			jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
				// 判断是否最后一级撤销(1:最后一级撤销成功）
				if("1" == data["cancelFlg"]){
					jQuery.post("ztbhgl_ztbhsh.do?method=cancelShLast",{sqid:sqid,shzt:shzt},function(result){
						showAlertDivLayer(result["message"],{},{"clkFun":function(){
							jQuery("#dataTable").reloadGrid();
						}});
					},'json');
				}else{
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						jQuery("#dataTable").reloadGrid();
					}});
				}
			
		},'json');
		}});
	}
}

/**
 * 流程跟踪
 */
function lcgz(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (1!=ids.length){
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {		
		showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}






