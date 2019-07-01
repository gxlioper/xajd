var action="rcsw_txhd_sh.do";

//点击序号跳转
function dcmcLink(cellValue, rowObject) {
	var sqid = rowObject["sqid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + sqid
			+ "')\" class='name'>" + cellValue + "</a>";
}
// 查看信息
function ckxx(sqid) {
	var url = action + "?method=showView&sqid=" + sqid;
	showDialog("查看活动项目", 800, 500, url);
}
function shzt(cellValue, rowObject) {
	var shzt = rowObject["shzt"];
	var mc = rowObject["mc"];
	var shztmc = "";
	switch (shzt) {
	case "1":
		shztmc = "通过";
		break;
	case "2":
		shztmc = "不通过";
		break;
	case "3":
		shztmc = "已退回";
		break;
	case "5":
		shztmc = "审核中";
		break;
	default:
		shztmc = "待审核";
		break;
	}
	return mc+"["+shztmc+"]";
}
//切换待处理/已处理页面
function selectTab(obj, shzt) {
	jQuery("#shzt").val(shzt);
	if (shzt == "dsh") {
		jQuery("#li_sh").css("display", "");
		jQuery("#li_qx").css("display", "none");
		jQuery("#title").html("团学待审核列表");
		var map = getSuperSearch();
		map["shzt"]="dsh";
		jQuery("#dataTable").reloadGrid(map);
	} else {
		jQuery("#li_sh").css("display", "none");
		jQuery("#li_qx").css("display", "");
		jQuery("#title").html("团学已审核列表");
		var map = getSuperSearch();
		map["shzt"]="ysh";
		jQuery("#dataTable").reloadGrid(map);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}
//审核
function rcsw_txhd_sh() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要审核的记录！");
	} else if(rows.length == 1){
		var xh = rows[0]["xh"];
		var url = action + '?method=txhdXmSh&sqid='
		+ rows[0]["sqid"]+"&gwid="+rows[0]["gwid"];
		showDialog("项目审核",800,500,url);
	}
}
function save_sh(){
	var shzt=jQuery("#shjg").val();
	jQuery("#shzt").val(shzt);
	if (jQuery("#shyj").val() == ""){
		showAlertDivLayer("请填写审核意见！");
		return false;
	}
	if (jQuery("#shyj").val().length>200){
		showAlertDivLayer("审核意见不能超过200字");
		return false;
	}
	var text=jQuery("#shjg").find("option:selected").text();
	//提交审核
	showConfirmDivLayer("您确定<font color='red'>[" + text + "]</font>该申请吗？",{"okFun":function(){
			zx(shzt,text);
	}});
	
}
function zx(shzt,text){
	var url = "rcsw_txhd_sh.do?method=txhdXmSh&type=save&tt="+new Date();
	jQuery("#form").ajaxSubmit( {
		url : url,
		type : "post",
		dataType : "json",
		success : function(data) {
			if (data["message"] == "保存成功！") {
				showAlert("<font color='red'>["+text+"]</font>操作成功！", {}, {
					"clkFun" : function() {
						if (parent.window) {
							refershParent();
						}
					}
				});
			} else {
				
				showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
			}
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8"
		});
}
function searchRs() {
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	if (shzt != "") {
		map["shzt"] = shzt;
	}
	jQuery("#dataTable").reloadGrid(map);
}
function rcxwshLcinfo() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {
		showDialog("活动审批流程跟踪", 600, 400, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splc']);
	}
}
function reloadsh(){
	jQuery("#dataTable").reloadGrid();
}