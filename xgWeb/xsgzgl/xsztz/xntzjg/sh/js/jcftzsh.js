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
	return "<a href='javascript:void(0);' class='name' onclick='XmsbjgView(\""
			+ rowObject["jgid"] + "\");'>" + cellValue + "</a>";
}

function XmsbjgView(jgid) {
	showDialog("项目申报查看", 800, 500, "xmsbXmsbjg.do?method=viewXmsbjg&jgid="
			+ jgid);
}

function rsLink(cellValue, rowObject){
	if(rowObject["csms"] == "1" ){
		return "<a href='javascript:void(0);' class='name' onclick='rsView(\""
		+ rowObject["xmdm"] + "\");'>" + cellValue + "</a>";
	}else{
		return "<a href='javascript:void(0);' class='name' onclick='TtsView(\""
		+ rowObject["xmdm"] + "\");'>" + cellValue + "</a>";
	}
	
}

function rsView(xmdm) {
	showDialog("学分认定查看", 800, 500, "jcftz_sq.do?method=viewRs&xmdm="
			+ xmdm);
}

function TtsView(xmdm){
	showDialog("学分认定查看", 800, 500,"jcftz_sq.do?method=TtrenDing&flag=view&xmdm="
			+ xmdm);
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
		var url = "jcftz_sh.do?method=sbDgsh&rdsqid=" + rows[0]["rdsqid"] + '&shid=' + rows[0]["shid"]+"&xmlx="+rows[0]["csms"]+"&xn="+rows[0]["xn"]+"&xq="+rows[0]["xq"]+"&xmdm="+rows[0]["xmdm"];
		showDialog("审核", 700, 400, url);
	} else {
		showDialog("批量审核", 500, 250, "jcftz_sh.do?method=sbPlsh");
	}
}
// 批量审核
function savePlsh(shzt, shyj) {
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	jQuery.each(rows, function(i, row) {
		guid.push(row["rdsqid"]);
		gwid.push(row["gwid"]);
	});
	jQuery.post("jcftz_sh.do?method=sbPlsh&type=save", {
		shzt : shzt,
		id : guid,
		gwids : gwid,
		shyj:shyj
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
	}else{	
		var splc = rows[0]["rdsplc"];
		var shid = rows[0]["shid"];
		var sqid = rows[0]["rdsqid"];
		var shzt = rows[0]["shzt"];
		var xmdm = rows[0]["xmdm"];
		showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
			jQuery.post("jcftz_sh.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
				// 判断是否最后一级撤销(1:最后一级撤销成功）
				if("1" == data["cancelFlg"]){
					jQuery.post("jcftz_sh.do?method=cancelSh",{sqid:sqid,shzt:shzt},function(result){
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
		showDialog("审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['rdsqid']+"&splc="+rows[0]['rdsplc']);
	}
}
