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
function xmmcLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='XmsbView(\""
			+ rowObject["xmdm"]+"\");'>" + cellValue
			+ "</a>";
}
function XmsbView(xmdm) {
	showDialog("项目申报查看", 800, 500, "xmsbXmsb.do?method=viewXmsb&xmdm="+xmdm);
}
// 单个审核
function sbsh() {
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = jQuery("#shzt").val();
	if (rows.length == 0) {
		showAlertDivLayer("请选择您要审核的记录")
		return false;
	}
	if (shzt == "ysh") {
		showAlertDivLayer("已处理记录不能再次审核");
		return false;
	} else if (rows.length == 1) {
		var url = "xmsbXmsh.do?method=sbDgsh&xmdm=" + rows[0]["xmdm"] + '&shid=' + rows[0]["shid"];
		showDialog("审核", 700, 480, url);
	} else {
		showDialog("批量审核", 500, 250, "xmsbXmsh.do?method=sbPlsh");
	}
}
// 批量审核
function savePlsh(shzt, shyj) {
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	jQuery.each(rows, function(i, row) {
		guid.push(row["xmdm"]);
		gwid.push(row["gwid"]);
	});
	jQuery.post("xmsbXmsh.do?method=sbPlsh&type=save", {
		shzt : shzt,
		id : guid,
		gwids : gwid
	}, function(data) {
		
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				jQuery("#dataTable").reloadGrid();
			}
		});
	}, 'json');
}

// 切换Tab页
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
//审核撤销
function cancelSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要撤消的审核记录！");
	} else if("no"==rows[0]["sfkcx"]){
		showAlertDivLayer("该项目已被申请，不允许撤销！");
	}else {
		var splc = rows[0]["splc"];
		var shid = rows[0]["shid"];
		var xmdm = rows[0]["xmdm"];
		var shzt = rows[0]["shzt"];
		showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
			jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
				// 判断是否最后一级撤销(1:最后一级撤销成功）
				if("1" == data["cancelFlg"]){
					jQuery.post("xmsbXmsh.do?method=cancelSh",{xmdm:xmdm,shzt:shzt},function(result){
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

function splcInfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (1!=ids.length){
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {		
		showDialog("审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['xmdm']+"&splc="+rows[0]['splc']);
	}
}

var DCCLBH = "xmsbXmsh.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, khshExportData);
}

//导出方法
function khshExportData() {
	setSearchTj();//设置高级查询条件
	var shzt = jQuery("#shzt").val();
	var url = "xmsbXmsh.do?method=exportData&shzt="+shzt+"&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}