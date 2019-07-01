/**
 * 志愿服务申请相关js
 */

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 学号格式化
 */
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='zyfwSqShow(\""
			+ rowObject["fwid"]+"\");'>" + cellValue
			+ "</a>";
}

/**
 * 服务地点过长截取
 */
function fwddSubString(cellValue, rowObject){
	var cellValueNotNull = cellValue ? cellValue : "";
	var cellValueNew = cellValueNotNull.length > 20 ? cellValue.substring(0,20)+"..." : cellValueNotNull;
	return "<span title='"+cellValueNotNull+"' >" + cellValueNew + "</span>";
}

/**
 * 查看
 */
function zyfwSqShow(fwid) {
	var title = jQuery("#gnmkmc").val()+"查看";
	var url = "xsxx_zyfwgl_sq.do?method=zyfwSqShow&fwid="+fwid;
	showDialog(title, 800, 500,url);
}

/**
 * 新增的保存
 */
var checkId = 'hdmc-hdzt-hdrq-bjmc-hdfzrxm-hdfzrlxdh';

function ztbhSqSaveForAdd(type) {
	if(!checkNotNull(checkId)){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}

	var url = "ztbhgl_ztbhsq.do?method=ztbhSqSaveForAdd&type=" + type;
	ajaxSubFormWithFun("ZtbhSqForm", url, function(data) {
		 if(data["message"]=="保存成功！"||data["message"]=="提交成功！"){
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

/**
 * 编辑的保存
 */
function ztbhSqSaveForEdit(type) {
	if(!checkNotNull(checkId)){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}
	var url = "ztbhgl_ztbhsq.do?method=ztbhSqSaveForEdit&type=" + type;
	ajaxSubFormWithFun("ZtbhSqForm", url, function(data) {
		 if(data["message"]=="保存成功！"||data["message"]=="提交成功！"){
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

/**
 * 申请弹框页面
 */
function add() {
	var sqkg = jQuery("#sqkg").val();
	if ("0" == sqkg) {
		showAlertDivLayer("当前已关闭，请与管理员联系！");
		return false;
	}
	var url = "ztbhgl_ztbhsq.do?method=ztbhSqAdd";
	showDialog("主题班会申请", 800, 550, url);
}

/**
 * 修改弹框页面
 */
function edit() {
	var sqkg = jQuery("#sqkg").val();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {

		var shzt = rows[0]["shzt"];
		if ('3' != rows[0]['shzt'] && "0" == sqkg) {
			showAlertDivLayer("当前申请已关闭，请与管理员联系！");
			return false;
		}
		if ("0" != shzt&&"3" != shzt) {
			showAlertDivLayer("只有未提交和已退回的记录才能修改！");
			return false;
		}

		var url = 'ztbhgl_ztbhsq.do?method=ztbhSqEdit&sqid=' + rows[0]["sqid"];
		var title = "主题班会修改";
		showDialog(title, 800, 550, url);
	}

}

/**
 * 删除
 */
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	} 
	for(var i=0;i<rows.length;i++){
		if (rows[i]["shzt"] != "0" && rows[i]["shzt"] != "3") {
			showAlertDivLayer("只能删除未提交或者已退回的记录！");
			return false;
		}
	}
	
	showConfirmDivLayer("您确定要删除选择的记录吗？", {
		"okFun" : function() {
			jQuery.post("ztbhgl_ztbhsq.do?method=ztbhSqDel", {
				values : ids.toString()
			},
					function(data) {
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					}, 'json');
		}
	});
}

/**
 * 提交
 */
function submit() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条您要提交的记录！");
		return false;
	}
	var rows = jQuery("#dataTable").getSeletRow();
	var sqkg = jQuery("#sqkg").val();
	if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("请选择未提交或者已退回的记录！");
		return false;
	}
	if ('3' != rows[0]['shzt'] && "0" == sqkg) {
		showAlertDivLayer("当前申请已关闭，请与管理员联系！");
		return false;
	}
	showConfirmDivLayer("您确定要提交选择的记录吗？", {
		"okFun" : function() {
			jQuery.post("ztbhgl_ztbhsq.do?method=ztbhSqSubmit", {
				sqid : ids.toString()

			}, function(data) {
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			}, 'json');
		}
	});
}

/**
 * 撤销
 */
function cancel() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条您要撤销的记录！");
		return false;
	}
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows[0]['shzt'] != '5') {
		showAlertDivLayer("只有审核中的记录才能被撤销！");
		return false;
	}

	showConfirmDivLayer("您确定要撤销选择的记录吗？", {
		"okFun" : function() {
			jQuery.post("ztbhgl_ztbhsq.do?method=ztbhSqCancel", {
				values : ids.toString(),
				splcid : rows[0]['splc']
			}, function(data) {
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			}, 'json');
		}
	});
}

/**
 * 流程跟踪
 */
function lcgz() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (1 != rows.length) {
		showAlertDivLayer("请选择一条流程跟踪记录！");
		return false;
	} 
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = rows[0]["shzt"];
	if ("0" == shzt) {
			showAlertDivLayer("该记录为未提交状态，请先提交！");
			return false;
		}
	var title = jQuery("#gnmkmc").val()+"审批流程跟踪";
	var url = "comm_spl.do?method=lcgz&sqid="+ rows[0]['sqid'] + "&splc=" + rows[0]['splc']
	showDialog(title,530,380,url);
}


//活动查看
function hdLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='View(\""
        + rowObject['sqid'] + "\");'>" + cellValue
        + "</a>";
}


function View(sqid) {
    showDialog("活动信息", 900, 450, "ztbhgl_ztbhsq.do?method=getHdInfo&sqid=" + sqid );
}

function ztbhLcinfo(){
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length != 1){
        showAlertDivLayer("请选择一条流程跟踪记录！");
    } else {
        showDialog("审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
    }
}


