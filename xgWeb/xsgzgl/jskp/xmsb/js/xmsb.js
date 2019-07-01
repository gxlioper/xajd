var action="jskpXmsb.do";
//切换待处理/已处理页面
function selectTab(obj, sbzt) {
	jQuery("#sbzt").val(sbzt);

	if (sbzt == "0") {
		jQuery("#li_sb").css("display", "");
		jQuery("#li_qx").css("display", "none");
		jQuery("#li_lcgz").css("display", "none");
		var map = getSuperSearch();
		map["sbzt"]="0";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_sb").css("display", "none");
		jQuery("#li_qx").css("display", "");
		jQuery("#li_lcgz").css("display", "");
		var map = getSuperSearch();
		map["sbzt"]="1";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	reload();
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");

	searchRs();
}
function searchRs() {
	var map = getSuperSearch();
	var sbzt = jQuery("#sbzt").val();
	if (sbzt != "") {
		map["sbzt"] = sbzt;
	}

	jQuery("#dataTable").reloadGrid(map);
}
function reload() {
	jQuery("#dataTable").reloadGrid();
}

//项目申报
function xmsb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条你需要申报的记录！");
		return false;
	}
	if(rows[0]["sbkg"]=='false'){
		showAlertDivLayer("项目申报已关闭！");
		return false;
	}
	jQuery.post(action+"?method=xmsbCheck",{xmid:ids},function(data){
		// 重复申报
		if("1" == data["jgzqFlg"]){
				showAlertDivLayer(data["message"]);
		}else{
			var url =action+"?method=xmsb&xmid="+ids;
			var title = "项目申报";
			showDialog(title, 800, 500, url);
		}
	
},'json');
	
}
function saveXmsb() {	
	var hjsj =jQuery("#hjsj").val();
	var sbly =jQuery("#sbly").val();
	if(hjsj==null||hjsj==""||sbly==null||sbly=="") {
		showAlert("请将必填项填写完整！");
		return false;
	}
	 if(jQuery(".MultiFile-label").length<=0){
		showAlertDivLayer("请上传附件！");
		return false;
   }
	 
	var url = action+"?method=saveXmsb";
	ajaxSubFormWithFun("jskpXmsbForm", url, function(data) {
		 if(data["success"]=="true"){
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

//删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post(action+"?method=delXmsb", {
					values : ids.toString(),
					splcid:rows[0]["splcid"]
				},
				function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}
/*
 * 流程跟踪
 */
function lcgz() {
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (1 != rows.length) {
		showAlertDivLayer("请选择一条流程跟踪记录！");
		return false;
	} 
		showDialog("审批流程跟踪",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splcid']);
}
