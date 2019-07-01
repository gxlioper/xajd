/**
 * 查询
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	if (null!=shzt&&shzt != "") {
		map["shzt"] = shzt;
	}else{
		map["shzt"] = "dsh";
	}
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 切换Tab页
 * @param obj
 * @param shzt
 * @return
 */
function selectTab(obj, shzt) {
	jQuery("#shzt").val(shzt);
	if (shzt == "dsh") {
		jQuery("#li_sh").css("display", "");
		jQuery("#li_qx").css("display", "none");
		var map = getSuperSearch();
		map["shzt"]="dsh";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_sh").css("display", "none");
		jQuery("#li_qx").css("display", "");
		var map = getSuperSearch();
		map["shzt"]="ysh";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

/**
 * 学号连接
 * @param cellValue
 * @param rowObject
 * @return
 */
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='dazcshView(\""+rowObject["sqid"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}

/**
 * 点击链接查看详细信息
 * @param id
 * @param xh
 * @return
 */
function dazcshView(sqid) {
	var title = "档案转出审核查看";
	var url = "dagl_dazcsq.do?method=dazcsqView&sqid="+sqid;
	showDialog(title,690,535,url);
}

/**
 * 审核(单个、批量)
 * @return
 */
function dazcsh(){
	/*勾选记录*/
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = jQuery("#shzt").val();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要审核的记录")
		return false;
	}else if (shzt == "ysh") {
		showAlertDivLayer("已处理记录不能再次审核");
		return false;
	}else{
		var url = "dagl_dazcsh.do?method=dazcshSingle&sqid=" + rows[0]["sqid"] + '&shid=' + rows[0]["shid"];
		showDialog("档案转出审核", 800, 500, url);
	}
}

/**
 * 审核撤销
 * @return
 */
function dazcshRevoke(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要撤消的审核记录！");
	} else {
		var splc = rows[0]["splcid"];
		var shid = rows[0]["shid"];
		var sqid = rows[0]["sqid"];
		var shzt = rows[0]["shzt"];
		showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
			jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
				// 判断是否最后一级撤销(1:最后一级撤销成功）
				if("1" == data["cancelFlg"]){
					jQuery.post("dagl_dazcsh.do?method=dazcshRevoke",{sqid:sqid,shzt:shzt},function(result){
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
 * @return
 */
function dazcshTrack(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(1!=ids.length){
		showAlertDivLayer("请选择一条流程跟踪记录！");
		return false;
	}else{		
		showDialog("审批流程跟踪",540,320,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splcid']);
	}
}