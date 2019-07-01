
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add() {
	var cfbz = jQuery("#cfbz").val();
	if( "1"  == cfbz){
		showAlertDivLayer("您本学期已有申请记录，请勿重复填写！");
		return false;
	}
	var url = "qmlxdj.do?method=add";
	var title = "离校去向登记";
	showDialog(title, 770, 550, url);
}

function fxjtgjdmChange(){
	var fxjtgj = jQuery("#fxjtgjdm").val();
	if(fxjtgj == "03"){
		jQuery("#fxcchbspan").hide();
	} else{
        jQuery("#fxcchbspan").show();
	}
}
/**
 * 保存申请
 * @param type
 * @return
 */
function saveSq(type){
	if(jQuery("#sflxdm").val() == "是"){
        var ids = "lxlx-xh-jhrxm-jhrlxfs-lxsj-lxjtgjdm-mddssx";
        if(checkNotNull(ids) == false){
            showAlert("请将带<font color='red'>*</font>的项目填写完整");
            return false;
        }
        if(jQuery("[name='sflx']:checked").val() == "" || jQuery("[name='sflx']:checked").val() == null){
            showAlert("请将带<font color='red'>*</font>的项目填写完整");
            return false;
        }
        /*if(jQuery("#lxjtgjdm").val() != "03"){
            if(checkNotNull("lxcchb") == false){
                showAlert("请将带<font color='red'>*</font>的项目填写完整");
                return false;
            }
		}*/
        if(jQuery("#fxjtgjdm").val() != "03"){
            if(checkNotNull("fxcchb") == false){
                showAlert("请将带<font color='red'>*</font>的项目填写完整");
                return false;
            }
        }
	}

	var url = "qmlxdj.do?method=saveSq&type=" + type;
	ajaxSubFormWithFun("LxdjForm", url, function(data) {
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

//查看学生链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='sqView(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

/**
 * 查看
 * @param sqid
 * @param xh
 * @return
 */
function sqView(sqid, xh) {
	showDialog("离校去向登记查看", 770, 450, "qmlxdj.do?method=ckSq&sqid="
			+ sqid + "&xh=" + xh);
}

/**
 * 删除
 * @return
 */
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		var flag = true;
		jQuery(rows).each(function(i,row){
			if(row["shzt"] != "0" && row["shzt"] != "3"){
				flag = false;
				return flag;
			}
		});
		if(!flag){
			showAlertDivLayer(jQuery("#lable_wjt_sc").val());
			return false;
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("qmlxdj.do?method=delSq",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	} else {
		if ("0" != rows[0]['shzt']&&"3" !=rows[0]['shzt']) {
			showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
			return false;
		}
		var url = 'qmlxdj.do?method=editSq&sqid=' + rows[0]["sqid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "离校去向登记修改";
		showDialog(title, 770, 550, url);
	}
}

var DCCLBH = "rcsw_qmlxdj.do";

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, sqExportData);
}

//导出方法
function sqExportData() {
	setSearchTj();//设置高级查询条件
	var url = "qmlxdj.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//提交
function submitBusi() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1){
		showAlertDivLayer("请选择一条您要提交的记录！");
		return false;
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		if (jQuery("#sqkg").val() == "0" && rows[0]["shzt"] != "3"){
			showAlertDivLayer(jQuery("#lable_dqwkfsq_prompt").val());
			return false;
		}
		if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
			showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
			return false;
		}
		showConfirmDivLayer("您确定要提交选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("qmlxdj.do?method=submitBusi", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

//撤销
function cancel() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要撤销的记录！");
	} else if (ids.length > 1) {
		showAlertDivLayer("请选择一条您要撤销的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['shzt'] != '5') {
				showAlertDivLayer("只有审核中的记录才能被撤销！");
				return false;
			}
		}

		showConfirmDivLayer("您确定要撤销选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("qmlxdj.do?method=cancelSq", {
					values : ids.toString(),
					splcid : rows[0]['splcid']
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
		showDialog("申请审批流程跟踪",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splcid']);
	}
}