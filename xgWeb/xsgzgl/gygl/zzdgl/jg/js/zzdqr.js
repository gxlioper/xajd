function searchRs() {
	var map = getSuperSearch();
	var qrzt = jQuery("#qrzt").val();
	if (null!=qrzt&&qrzt != "") {
		map["qrzt"] = qrzt;
	}else{
		map["qrzt"] = "dsh";
	}
	jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='zzdqrView(\""
			+ rowObject["jgid"]+"\");'>" + cellValue
			+ "</a>";
}

function zzdqrView(jgid) {
	showDialog("项目申报查看", 800, 500, "xgygl_zdjg.do?method=viewZzdjg&jgid="+jgid);
}

// 单个审核
function qr() {
	var rows = jQuery("#dataTable").getSeletRow();
	var qrzt = jQuery("#qrzt").val();
	if (rows.length == 0) {
		showAlertDivLayer("请选择您要确认的记录")
		return false;
	}
	if (qrzt == "ysh") {
		showAlertDivLayer("已确认记录不能再次确认");
		return false;
	} else if (rows.length == 1) {
		var url = "xgygl_zdqr.do?method=dgqr&jgid=" + rows[0]["jgid"];
		showDialog("转走读管理员确认", 700, 400, url);
	} else {
		showDialog("批量确认", 500, 250, "xgygl_zdqr.do?method=plqr");
	}
}

function saveQr(jg){
	var qryj = jQuery("#qryj").val();
	if (qryj == ""){
		showAlert("请将必填项填写完整！");
		return false;
	}
	var url = "xgygl_zdqr.do?method=dgbc&jg="+jg;
	ajaxSubFormWithFun("zzdjgForm",url,function(data){
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

// 批量审核
function savePlqr(qrzt, qryj) {
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	jQuery.each(rows, function(i, row) {
		guid.push(row["jgid"]);
	});
	jQuery.post("xgygl_zdqr.do?method=plqr&type=save", {
		qrzt:qrzt,
		qrids : guid,
		qryj:qryj
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
	jQuery("#qrzt").val(shzt);
	if (shzt == "dsh") {
		jQuery("#li_sh").css("display", "");
		jQuery("#li_qx").css("display", "none");
		var map = getSuperSearch();
		map["qrzt"]="dsh";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_sh").css("display", "none");
		jQuery("#li_qx").css("display", "");
		var map = getSuperSearch();
		map["qrzt"]="ysh";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}
//审核撤销
function cancel(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要取消的审核记录！");
	}else{		
		var jgid = rows[0]["jgid"];
		showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
			jQuery.post("xgygl_zdqr.do?method=qxqr",{jgid:jgid},function(data){
				if (data["success"] == false) {
					showAlertDivLayer(data["message"]);
				} else {
					showAlertDivLayer(data["message"], {}, {
						"clkFun" : function(tag) {
							jQuery("#dataTable").reloadGrid();
						}
					});
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
		showDialog("审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['zzdid']+"&splc="+rows[0]['splcid']);
	}
}

var DCCLBH = "xgygl_zzdgl_zdsh.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, zzdshExportData);
}

//导出方法
function zzdshExportData() {
	setSearchTj();//设置高级查询条件
	var shzt = jQuery("#shzt").val();
	var url = "xgygl_zdsh.do?method=exportData&shzt="+shzt+"&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}