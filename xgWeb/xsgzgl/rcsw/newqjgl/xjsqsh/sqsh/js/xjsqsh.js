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

function splcInfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (1!=ids.length){
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {		
		showDialog("审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['ywid']+"&splc="+rows[0]['splc']);
	}
}

//单个审核
function khsh() {
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = jQuery("#shzt").val();
	if (rows.length == 0) {
		showAlertDivLayer("请选择您要审核的记录！")
		return false;
	}
	if (shzt == "ysh") {
		showAlertDivLayer("已处理记录不能再次审核！");
		return false;
	} else if (rows.length == 1) {
		var url = "qjgl_xjsh.do?method=dgXjsh&ywid=" + rows[0]["ywid"] + '&shid=' + rows[0]["shid"]+"&xh="+rows[0]["xjr"];
		showDialog("审核", 770, 550, url);
	} else {
		showDialog("批量审核", 500, 250, "qjgl_xjsh.do?method=plXjsh");
	}
}
// 批量审核
function savePlsh(shzt, shyj) {
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = new Array();
	var gwids = new Array();
	var xhs = new Array();
	var splcs = new Array();
	jQuery.each(rows, function(i, row) {
		ids.push(row["ywid"]);
		gwids.push(row["gwid"]);
		xhs.push(row["xjr"]);
		splcs.push(row["splc"]);
	});
	jQuery.post("qjgl_xjsh.do?method=savePlsh", {
		shzt : shzt,
		splcs : splcs,
		ids : ids,
		gwids : gwids,
		xhs : xhs,
		shyj : shyj
	}, function(data) {
		
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				jQuery("#dataTable").reloadGrid();
			}
		});
	}, 'json');
}

//审核撤销
function cancelSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要撤消的审核记录！");
	} else {
		var splc = rows[0]["splc"];
		var shid = rows[0]["shid"];
		var sqid = rows[0]["ywid"];
		var qjjgid = rows[0]["qjjgid"];
		showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
			jQuery.post("qjgl_xjsh.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
				// 判断是否最后一级撤销(1:最后一级撤销成功）
				if("1" == data["cancelFlg"]){
					jQuery.post("qjgl_xjsh.do?method=cancelSh",{sqid:sqid,qjjgid:qjjgid},function(result){
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
 * 链接
 * @param cellValue
 * @param rowObject
 * @return
 */

function link(cellValue,rowObject){

	//return "<a href='javascript:void(0);' class='name' onclick='knsView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
	var onclickfn = "onclick=\"" + "showDialog('销假查看' , 680,500 , 'qjgl_xjsh.do?method=ckXjsh&ywid=" + rowObject['ywid'] +"&xh="+rowObject['xjr']+"')" + "\"";
	var href = "href = 'javascript:void(0);'";

	var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
	
	return el;
}


