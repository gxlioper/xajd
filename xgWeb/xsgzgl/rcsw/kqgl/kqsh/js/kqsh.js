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

//检测申请审核开关
function checkKg(type){
	var result = false;
	jQuery.ajaxSetup({async:false});
		jQuery.post("zjsy_kqcssz.do?method=checkSqsh&type="+type,{},function(data){
			if("1" == data){
				result = true;
			}
		},'json');
	jQuery.ajaxSetup({async:true});
	return result;
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


//审核
function kqsh() {
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	
	var shzt = jQuery("#shzt").val();
	if (!checkKg("sh")) {
		showAlertDivLayer("审核已关闭，请联系管理员！");
		return false;
	}
	if (shzt == "ysh") {
		showAlertDivLayer("已处理记录不能再次审核");
		return false;
	} else if (rows.length == 1) {
		var url = "zjsy_kqsh.do?method=kqDgsh&id=" + rows[0]["id"] + '&shid=' + rows[0]["shid"];
		showDialog(jQuery("#gnmkmc").val(), 800, 600, url);
	} else {
		showDialog("批量审核", 500, 250, "zjsy_kqsh.do?method=kqPlsh&values="+ids.toString());
	}
}

/**
 * 单个审核保存
 * @return
 */
function saveSh(){
	var shzt = jQuery("#shjg").val();
	if (jQuery("#shzt").val() == "" || jQuery("#shyj").val() == ""){
		showAlert("请将必填项填写完整！");
		return false;
	}
	var url = "zjsy_kqsh.do?method=kqDgsh&type=save";
	ajaxSubFormWithFun("KqshForm",url,function(data){
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

//批量审核保存
function savePlsh(shzt, shyj) {
	
	var ids = jQuery("#dataTable").getSeletIds();
	var map = getSuperSearch();
	map['values']=ids.toString();
	map['shjg'] = shzt;
	map['shyj'] = shyj;
	
	jQuery.post("zjsy_kqsh.do?method=kqPlsh&type=save", map, function(data) {
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				jQuery("#dataTable").reloadGrid();
			}
		});
	}, 'json');
}

/**
 * 流程查看
 * @return
 */
function splcInfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (1!=ids.length){
		showAlertDivLayer("请选择一条流程跟踪记录！");
		return false;
	} else {		
		showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['id']+"&splc="+rows[0]['splc']);
	}
}

//审核撤销
function cancelSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要撤消的审核记录！");
	} else {
		var splc = rows[0]["splc"];
		var id = rows[0]["id"];
		var shid = rows[0]["shid"];
		
		showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
			jQuery.post("zjsy_kqsh.do?method=kqshcx",{shlc:splc,id:id,shid:shid},function(data){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					jQuery("#dataTable").reloadGrid();
				}});
		},'json');
		}});
	}
}


function bjLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='kqshCk(\""
			+ rowObject["id"] + "\");'>" + cellValue
			+ "</a>";
}

function kqshCk(id) {
	showDialog("日常行为结果查询", 720, 520,
			"zjsy_kqsh.do?method=kqshCk&id=" + id);
}