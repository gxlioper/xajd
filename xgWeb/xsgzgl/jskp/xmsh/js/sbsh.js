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
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='sbsqView(\""
			+ rowObject["sqid"] + "\");'>" + cellValue
			+ "</a>";
}

function sbsqView(sqid) {
	showDialog("项目申报查看", 800, 500, "jskpXmsb.do?method=viewXmsb&sqid="
			+ sqid);
}
//审核按钮（单个、批量）
function sbsh() {
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = jQuery("#shzt").val();
	var shkg = jQuery("#shkg").val();
	var sfsh = jQuery("#sfsh").val();
	if (rows.length == 0) {
		showAlertDivLayer("请选择您要审核的记录")
		return false;
	}
	if ("0" == shkg) {
		showAlertDivLayer("审核已关闭，请联系管理员！");
		return false;
	}
	if (shzt == "ysh") {
		showAlertDivLayer("已处理记录不能再次审核");
		return false;
	} else if (rows.length == 1) {
		var url = "jskpXmsh.do?method=sbDgsh&sqid=" + rows[0]["sqid"] +  '&shid=' + rows[0]["shid"];
		showDialog(jQuery("#gnmkmc").val(), 800, 500, url);
	} else {
		if("1" == sfsh){
			var flag=false;
			for ( var int = 0; int < rows.length; int++) {
				if(rows[int]["xmid"]!=rows[0]["xmid"]){
					flag=true;
					break;
				}
			}
			if(flag){
				showAlertDivLayer("请选择相同的申报项目！");
				return false;
			}
		}
		
		var ids = jQuery("#dataTable").getSeletIds();
		
		showDialog("批量审核", 500, 300, "jskpXmsh.do?method=sbPlsh&zdf="+rows[0]["zdf"]+"&zxf="+rows[0]["zxf"]+"&id="+ids);
	}
}
// 批量审核
function savePlsh(shzt, shyj,fs, ffgz) {
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var xhs = new Array();
	jQuery.each(rows, function(i, row) {
		guid.push(row["sqid"]);
		gwid.push(row["gwid"]);
		xhs.push(row["xh"]);
	});
	jQuery.post("jskpXmsh.do?method=sbPlsh&type=save", {
		shzt : shzt,
		splcid : rows[0]["splc"],
		id : guid,
		gwids : gwid,
		xhs : xhs,
		shyj : shyj,
		fs:fs,
		ffgz:ffgz
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
	} else {
		var splc = rows[0]["splc"];
		var shid = rows[0]["shid"];
		var sqid = rows[0]["sqid"];
		var shzt = rows[0]["shzt"];
		showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
			jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
				// 判断是否最后一级撤销(1:最后一级撤销成功）
				if("1" == data["cancelFlg"]){
					jQuery.post("jskpXmsh.do?method=cancelSh",{sqid:sqid,shzt:shzt},function(result){
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
		return false;
	} else {		
		showDialog("审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}


