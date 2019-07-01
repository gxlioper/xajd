function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add() {
	var url = "ttxm_sq.do?method=add";
	var title = "团体拓展项目申请";
	showDialog(title, 770, 550, url);
}


//增加修改团体申请保存
function saveTtxmSq(type){
	var ids = "xmmc"+"-"+"tdmc"+"-"+"sqly"+"-"+"dzxh";
	if(!checkNotNull(ids)){
		showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		return false;
	}
	if(jQuery("#usertype").val() != 'stu'){
		if(!checkDzIsSelect()){
			showAlert("请将带<font color='red'>*</font>的项目填写完整！");
			return false;
		}
	}
	if(!checkContentIsNull()){
		showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		return false;
	}
	if(checkzs() == false){
		return false;
	}
	var url = "ttxm_sq.do?method=saveTtsq&type=" + type;
	ajaxSubFormWithFun("TttzxmForm", url, function(data) {
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


//查看团队链接
function ttsqLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='TtsqView(\""
			+ rowObject["ttsqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function TtsqView(ttsqid, tdmc) {
	showDialog("团体拓展项目申请查看", 770, 450, "ttxm_sq.do?method=TtsqView&ttsqid="
			+ ttsqid);
}

//删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	}  
	var flag = true;
	for(var i=0;i<rows.length;i++){
		if(rows[i]["shzt"] != "0" && rows[i]["shzt"] != "3"){
			flag =false;
			break;
		}
	}
	if(!flag){
		showAlertDivLayer(jQuery("#lable_wjt_sc").val());
		return false;
	}
	showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
		jQuery.post("ttxm_sq.do?method=delTtsq",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
	
}



//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	} else {
		if(rows[0]['sqkg'] != '1'){
			showAlertDivLayer("对应项目申请开关已关闭，不能修改！");
			return false;
		}
		if ("0" != rows[0]['shzt']&&"3" !=rows[0]['shzt']) {
			showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
			return false;
		}
		var url = 'ttxm_sq.do?method=editTtsq&ttsqid=' + rows[0]["ttsqid"];
		var title = "团体拓展项目申请";
		showDialog(title, 770, 550, url);
	}
}

var DCCLBH = "sztz_ttxm_sq.do";

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, ttsqExportData);
}

//导出方法
function ttsqExportData() {
	setSearchTj();//设置高级查询条件
	var url = "ttxm_sq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//提交
function submitBusi() {
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if(rows.length != 1){
		showAlertDivLayer("请选择一条您要提交的记录！");
		return false;
	}
	
	if ("1" != rows[0]['sqkg']){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}
	if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
		return false;
	}
	showConfirmDivLayer("您确定要提交选择的记录吗？", {
		"okFun" : function() {
			jQuery.post("ttxm_sq.do?method=submitBusi", {
				values : ids.toString()
			}, function(data) {
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			}, 'json');
		}
	});
}

//撤销
function cancel() {
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要撤销的记录！");
	} else if (ids.length > 1) {
		showAlertDivLayer("请选择一条您要撤销的记录！");
	} else {
		if ("1" != rows[0]['sqkg']){
			showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
			return false;
		}
		var rows = jQuery("#dataTable").getSeletRow();
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['shzt'] != '5') {
				showAlertDivLayer("只有审核中的记录才能被撤销！");
				return false;
			}
		}

		showConfirmDivLayer("您确定要撤销选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("ttxm_sq.do?method=cancelZssq", {
					values : ids.toString(),
					splcid : rows[0]['splc']
				}, function(data) {
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
	} else {
		var shzt = rows[0]["shzt"];
		if ("0" == shzt) {
			showAlertDivLayer(jQuery("#lable_wxglcxx").val());
			return false;
		}
		showDialog("审批流程跟踪",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['ttsqid'] + "&splc=" + rows[0]['splc']);
	}
}



