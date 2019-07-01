/*查询*/
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
		showDialog("审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['ttsqid']+"&splc="+rows[0]['splc']);
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
		var url = "ttxm_sh.do?method=TtxmDgsh&ttsqid=" + rows[0]["ttsqid"]+ '&shid=' + rows[0]["shid"];
		showDialog("审核", 770, 500, url);
	} else {
		showDialog("批量审核", 500, 250, "ttxm_sh.do?method=TtxmPlsh");
	}
}
// 批量审核
function savePlsh(shzt, shyj) {
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var sqrs = new Array();
	var splcs = new Array();
	var xmdms = new Array();

	jQuery.each(rows, function(i, row) {
		guid.push(row["ttsqid"]);
		gwid.push(row["gwid"]);
		sqrs.push(row["sqr"]);
		splcs.push(row["splc"]);
		xmdms.push(row["xmdm"]);
	});
	jQuery.post("ttxm_sh.do?method=TtxmPlsh&type=save", {
		shzt : shzt,
		id : guid,
		gwids : gwid,
		sqrs : sqrs,
		shyj : shyj,
		splcs :splcs,
		xmdms : xmdms,
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
	} else if((rows[0]['xfrdsqzt']!='0' && rows[0]['xfrdsqzt']!='3') || rows[0]['xfrdjgzt']!='0'){
		showAlertDivLayer("该项目学分已认定，不能撤销！");
		return false;
	}else {
		var splc = rows[0]["splc"];
		var shid = rows[0]["shid"];
		var sqid = rows[0]["ttsqid"];
		var shzt = rows[0]["shzt"];
		showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
			jQuery.post("ttxm_sh.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
				// 判断是否最后一级撤销(1:最后一级撤销成功）
				if("1" == data["cancelFlg"]){
					jQuery.post("ttxm_sh.do?method=cancelSh",{sqid:sqid,shzt:shzt},function(result){
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

//查看团队链接
function ttsqLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='TtsqView(\""
			+ rowObject["ttsqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function TtsqView(ttsqid, tdmc) {
	showDialog("团体拓展项目审核查看", 770, 450, "ttxm_sq.do?method=TtsqView&ttsqid="
			+ ttsqid);
}

//单个审核保存
function saveSh(){
	var shzt = jQuery("#shjg").val();
	if (jQuery("#shjg").val() == "" || jQuery("#shyj").val() == ""){
		showAlert("请将必填项填写完整！");
		return false;
	}
	var url = "ttxm_sh.do?method=TtxmDgsh&type=save";
	ajaxSubFormWithFun("TttzxmShForm",url,function(data){
		// if(data["message"]=="保存成功！"){
    		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
    	// }else{
    		// showAlert(data["message"]);
    		//}
		});
 }