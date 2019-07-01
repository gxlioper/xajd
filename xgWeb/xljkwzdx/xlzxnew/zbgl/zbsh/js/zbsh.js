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
		showDialog("审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sbsqid']+"&splc="+rows[0]['splcid']);
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
		var url = "xlzxnew_zbsh.do?method=zbsh&sbsqid=" + rows[0]["sbsqid"] + '&shid=' + rows[0]["shid"];
		showDialog("审核", 770, 550, url);
	} else {
		showDialog("批量审核", 500, 250, "xlzxnew_zbsh.do?method=zbPlsh");
	}
}
// 批量审核
function savePlsh(shzt, shyj) {
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var xhs = new Array();
	var splcids = new Array();
	jQuery.each(rows, function(i, row) {
		guid.push(row["sbsqid"]);
		gwid.push(row["gwid"]);
		xhs.push(row["xh"]);
		splcids.push(row["splcid"]);
	});
	jQuery.post("xlzxnew_zbsh.do?method=saveZbplsh", {
		shzt : shzt,
		splcids : splcids,
		id : guid,
		gwids : gwid,
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
		var splc = rows[0]["splcid"];
		var shid = rows[0]["shid"];
		var sqid = rows[0]["sbsqid"];
		var shzt = rows[0]["shzt"];
		showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
			jQuery.post("xlzxnew_zbsh.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
				// 判断是否最后一级撤销(1:最后一级撤销成功）
				if("1" == data["cancelFlg"]){
					jQuery.post("xlzxnew_zbsh.do?method=cancelSh",{sqid:sqid,shzt:shzt},function(result){
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
	var onclickfn = "onclick=\"" + "showDialog('详细信息' , 680,500 , 'xlzxnew_zbsb.do?method=ckZbsb&sbsqid=" + rowObject['sbsqid'] + "')" + "\"";
	if(rowObject['sbsqid'] == '' || rowObject['sbsqid'] == null){
		onclickfn = "onclick=\"" + "showAlertDivLayer('信息未上报！')" + "\"";
	}
	var href = "href = 'javascript:void(0);'";

	var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
	
	return el;
}
