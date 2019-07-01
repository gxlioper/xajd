
	
function searchRs(){
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	if (shzt != ""){
		map["shzt"] = shzt;
	}
	jQuery("#dataTable").reloadGrid(map);
}
	
	
/**
 * 证件补办审核
 * @param obj
 * @param shzt
 * @return
 */
function selectTab(obj,shzt){
	jQuery("#shzt").val(shzt);
	if (shzt == "dsh"){
		jQuery("#li_sh").css("display","");
		jQuery("#li_qx").css("display","none");
		jQuery("#dataTable").initGrid(gridSetting);
	} else {			
		jQuery("#li_sh").css("display","none");
		jQuery("#li_qx").css("display","");
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	searchRs();
}

/**
 * 证件补办审核
 * @return
 */
function xszbbSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = jQuery("#shzt").val();
	if(shzt=="ysh"){
		showAlertDivLayer("已处理记录不能再次审核");
		return false;
	} else if (rows.length == 0){
		showAlertDivLayer("请选择您要申请的记录")
		return false;
	} else if(rows.length == 1){
		var url = "rcsw_xszbb_bbshgl.do?method=xszbbDgsh&bbsqid="+rows[0]["bbsqid"]+'&xh=' + rows[0]["xh"]+'&shid=' +rows[0]["shid"] ;
		showDialog("证件补办审核",700,500,url);
	} else{
		showDialog("证件补办批量审核",500,300,"rcsw_xszbb_bbshgl.do?method=zjbbPlsh");
	} 
}

function xszbbshLcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (1!=ids.length){
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {		
		showDialog("证件补办审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['bbsqid']+"&splc="+rows[0]['splc']);
	}
}

function cancelShnew(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要撤消的审核记录！");
	} else {
		var splc = rows[0]["splc"];
		var shid = rows[0]["shid"];
		var bbsqid = rows[0]["bbsqid"];
		var shzt = rows[0]["shzt"];
		showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
			jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
				// 判断是否最后一级撤销(1:最后一级撤销成功）
				if("1" == data["cancelFlg"]){
					jQuery.post("rcsw_xszbb_bbshgl.do?method=cancelXszbbsh",{bbsqid:bbsqid,shzt:shzt},function(result){
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

function viewXszbbsh(bbsqid, xh) {
	showDialog("证件补办审核查询", 700, 480, "rcsw_xszbb_bbshgl.do?method=viewXszbbsh&bbsqid=" + bbsqid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewXszbbsh(\""
			+ rowObject["bbsqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}
var DCCLBH = "rcsw_xszbb_bbsh.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, rcxwxshExportData);
}

// 导出方法
function rcxwxshExportData() {
	var shlx = jQuery("#shzt").val();
	setSearchTj();//设置高级查询条件
	var url = "rcsw_xszbb_bbshgl.do?method=exportData&dcclbh=" + DCCLBH + "&shlx=" + shlx;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * 批量审核保存
 * @param shzt
 * @return
 */
function savePlsh(shzt,shyj){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var xhs = new Array();
	
	jQuery.each(rows,function(i,row){
		guid.push(row["bbsqid"]);
		gwid.push(row["gwid"]);
		xhs.push(row["xh"]);
	});

	jQuery.post(
		"rcsw_xszbb_bbshgl.do?method=zjbbPlsh&type=save",
		{
		 shzt:shzt,
		 id:guid,
		 gwids:gwid,
		 xhs:xhs,
		 shyj:shyj
		},function(data){
			
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				jQuery("#dataTable").reloadGrid();
			}});
		},
		'json'
	);
}
