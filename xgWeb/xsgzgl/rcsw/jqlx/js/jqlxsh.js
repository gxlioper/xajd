function searchRs(){
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	if (shzt != ""){
		map["shzt"] = shzt;
	}
	jQuery("#dataTable").reloadGrid(map);
}

function selectTab(obj,shzt){
	jQuery("#shzt").val(shzt);
	var map = getSuperSearch();
	map["shzt"] = shzt;
	if (shzt == "dsh"){
		jQuery("#li_sh").css("display","");
		jQuery("#li_qx").css("display","none");
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {			
		jQuery("#li_sh").css("display","none");
		jQuery("#li_qx").css("display","");
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	searchRs();
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewXsJqlxSq(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}


function viewXsJqlxSq(sqid, xh) {
	showDialog("学生假期留校申请查询", 750, 420, "rcsw_jqlx.do?method=viewJqlxsq&sqid=" + sqid
			+ "&xh=" + xh);
}

function lxsqSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0){
		alertInfo("请选择一条您要审核的记录！");
	} else if (rows.length == 1) {
		showDialog("假期留校审核",760,520,"rcsw_jqlx.do?method=toViewShDetail&sqid="+rows[0]["sqid"]+"&shid="+rows[0]["shid"]+"&gwid="+rows[0]["gwid"]+"&xh="+rows[0]["xh"]);
	} else{
		showDialog("批量审核",500,200,"rcsw_jqlx.do?method=jqlxPlsh");
	}
}

function savePlsh(shzt,shyj){
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwids = new Array();
	var xhs = new Array();
	var lcids = new Array();
	
	jQuery.each(rows,function(i,row){
		guid.push(row["sqid"]);
		gwids.push(row["gwid"]);
		xhs.push(row["xh"]);
		lcids.push(row["lcid"]);
	});

	jQuery.post(
		"rcsw_jqlx.do?method=savePlsh",
		{
		 shzt:shzt,
		 id:guid,
		 gwids:gwids,
		 xhs:xhs,
		 lcids:lcids,
		 shyj:shyj
		},function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				jQuery("#dataTable").reloadGrid();
			}});
		},
		'json'
	);
}

function cancelShnew(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要撤消的审核记录！");
	} else {
		var splc = rows[0]["lcid"];
		var shid = rows[0]["shid"];
		var sqid = rows[0]["sqid"];
		showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
			jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
				// 判断是否最后一级撤销(1:最后一级撤销成功）
				if("1" == data["cancelFlg"]){
					jQuery.post("rcsw_jqlx.do?method=cancelJqlxsh",{sqid:sqid},function(result){
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

function lcgzInfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {	
		showDialog("假期留校审批流程跟踪",530,400,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['lcid']);
	}
}

//自定义导出 功能
function exportConfig() {
	var exportBh = "rcsw_jqlxsh.do";
	if("11488" == jQuery("#xxdm").val()){
		exportBh = "rcsw_jqlxsh_11488.do";
	}
	//DCCLBH导出功能编号,执行导出函数 
	customExport(exportBh, jqlxshExportData);
}

// 导出方法
function jqlxshExportData() {
	var shzt=jQuery("#shzt").val();
	setSearchTj();//设置高级查询条件
	var exportBh = "rcsw_jqlxsh.do";
	if("11488" == jQuery("#xxdm").val()){
		exportBh = "rcsw_jqlxsh_11488.do";
	}
	var url = "rcsw_jqlx.do?method=exportSqshData&shzt="+shzt+"&dcclbh="+exportBh;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}