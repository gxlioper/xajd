var action="qjsh.do";

//切换待处理/已处理页面
function selectTab(obj, shzt) {
	jQuery("#shzt").val(shzt);
	var xxdm = $("xxdm").value;
	if("12303" == xxdm){
		if (shzt == "dsh") {
			jQuery("#li_sh").css("display", "");
			jQuery("#li_qx").css("display", "none");
			jQuery("#title").html("请假待审核列表");
			var map = getSuperSearch();
			map["shzt"]="dsh";
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting4);
		} else {
			jQuery("#li_sh").css("display", "none");
			jQuery("#li_qx").css("display", "");
			jQuery("#title").html("请假已审核列表");
			var map = getSuperSearch();
			map["shzt"]="ysh";
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting5);
		}
	}else{
		if (shzt == "dsh") {
			jQuery("#li_sh").css("display", "");
			jQuery("#li_qx").css("display", "none");
			jQuery("#title").html("请假待审核列表");
			var map = getSuperSearch();
			map["shzt"]="dsh";
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		} else {
			jQuery("#li_sh").css("display", "none");
			jQuery("#li_qx").css("display", "");
			jQuery("#title").html("请假已审核列表");
			var map = getSuperSearch();
			map["shzt"]="ysh";
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting2);
		}
	}
	reload();
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");

	searchRs();
}
function searchRs() {
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	if (shzt != "") {
		map["shzt"] = shzt;
	}

	jQuery("#dataTable").reloadGrid(map);
}
function reload() {
	jQuery("#dataTable").reloadGrid();
}
// 点击序号跳转
function dcmcLink(cellValue, rowObject) {
	var qjsqid = rowObject["qjsqid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + qjsqid
			+ "')\" class='name'>" + cellValue + "</a>";
}
// 查看信息
function ckxx(qjsqid) {
	var query = jQuery("#query").val();
	var url = action + "?method=showView&qjsqid=" + qjsqid;
	var title = "请假申请信息";
	showDialog(title, 800, 500, url);
}
// 申请
function add() {
	var url = action + "?method=add";
	var title = "请假申请";
	showDialog(title, 800, 500, url);
	jQuery("#dataTable").reloadGrid();
}
/**
 * 保存审核操作
 * @param shzt
 * @param message
 * @return
 */
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
	showConfirmDivLayer("您确定" + text + "该申请吗？",{"okFun":function(){
			zx(shzt,text);
		}});
	
}
function zx(shzt,text){
	var url = "qjsh.do?method=qjsh&type=save&tt="+new Date();
/*	if(shzt=="3"||shzt==3){
		var shid = jQuery("#shid").val();
		var splc = jQuery("#splc").val();
		var shyj = jQuery("#shyj").val();
		splc_th(shid,splc,shyj);
		return false;
	}*/
	
	jQuery("#form").ajaxSubmit( {
		url : url,
		type : "post",
		dataType : "json",
		success : function(data) {
			if (data["message"] == "保存成功！") {
				showAlert("操作成功！", {}, {
					"clkFun" : function() {
						if (parent.window) {
							refershParent();
						}
					}
				});
			} else {
				showAlert("操作失败！");
			}
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8"
		});
}
function save(url, checkId) {
	if (!check(checkId)) {
		return alertError("请将带<font color='red'>*</font>的项目填写完整！");
	}
	
	var shzt=jQuery("#shzt").val();
	if(shzt=="3"){//退回操作
		var shid = jQuery("#shid").val();
		var splc = jQuery("#splcid").val();
		var shyj = jQuery("#shyj").val();
		splc_th(shid,splc,shyj);
		return false;
	}
	
	var text=jQuery("#shzt option:selected").text();
	showConfirmDivLayer("您确定" + text + "该申请吗？",{"okFun":function(){
	jQuery("#form").ajaxSubmit( {
		url : url,
		type : "post",
		dataType : "json",
		success : function(data) {
			if (data["message"] == "保存成功！") {
				showAlert(data["message"], {}, {
					"clkFun" : function() {
						if (parent.window) {
							refershParent();
						}
					}
				});
			} else {
				showAlert(data["message"]);
			}
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8"
		});
	}});
}
/**
 * 验证是否存在空项
 * 
 * @param ids
 *            要验证的控件id "-"分隔
 * @return
 */
function check(ids) {
	var id = ids.split("-");
	for ( var i = 0; i < id.length; i++) {
		var lddm = jQuery("#" + id[i]).val();
		if (lddm == null || "" == lddm) {
			// alert(id[i]);
			return false;
		}
	}
	return true;
}
// 修改
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlert("请选择一条您要修改的记录！");
	} else {
		var xh = rows[0]["xh"];
		jQuery.post("qjsq.do?method=isCanAdd", {}, function(data) {
			if (data["success"] == "true") {
				var url = action + '?method=update&xh=' + xh + '&qjsqid='
						+ rows[0]["qjsqid"];
				var title = "修改请假类型";
				showDialog(title, 700, 500, url);
				jQuery("#dataTable").reloadGrid();
			} else {
				showAlert("已经存在此请假类型!");
			}
		}, 'json');
	}
}
// 删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlert("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post(action + "?method=del", {
					values : ids.toString()
				}, function(data) {
					showAlert(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}
//审核
function qjsh() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlert("请选择一条您要审核的记录！");
	} else if(rows.length == 1){
		var xh = rows[0]["xh"];
		var url = action + '?method=qjsh&xh=' + xh + '&qjsqid='
		+ rows[0]["qjsqid"]+"&gwid="+rows[0]["gwid"];
		showDialog("请假审核",800,500,url);
	}else {
		showDialog("请假批量审核",500,300,action + '?method=toPlsh');
	}
	
	
	
	/**
	 * 显示审核页面
	 */
	function viewJxsh(){
		var rows = jQuery("#dataTable").getSeletRow();

		if (rows.length ==0){
			showAlertDivLayer("请选择一条您要审核的记录！");
		}else if (rows.length == 1){
			var sqid = rows[0]["sqid"];
			var shid = rows[0]["shid"];
			var gwid = rows[0]["gwid"];
			
			showDialog("奖项审核",700,500,"xpj_sqsh.do?method=viewJxsh&sqid="+sqid+"&shid="+shid+"&gwid="+gwid);
		}else {
			showDialog("批量审核",500,300,"xpj_sqsh.do?method=toPlsh");
		}
	}
}
//撤销审核
function cancelSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要撤消的审核记录！");
	} else {
		var xjzt = rows[0]["xjzt"];
		if(xjzt=="1"){
			showAlertDivLayer("此申请已经销假，不能撤销！");
			return false;
		}
		showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
			jQuery.post("qjsh.do?method=cancelSh",
				{
				 qjsqid:rows[0]["qjsqid"],
				 gwid:rows[0]["gwid"],
				 shr:rows[0]["shr"],
				 splcid:rows[0]["splcid"]
				},
				function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},
			'json');
		}});
	}
}
function rcxwshLcinfo() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {
		showDialog("请假审批流程跟踪", 600, 400, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['qjsqid'] + "&splc=" + rows[0]['splcid']);
	}
}
//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport('qjshbase.do',exportData);
}

// 导出方法
function exportData(dcclbh) {
	var shlx = jQuery("#shzt").val();
	setSearchTj();//设置高级查询条件
	var url = "qjsh.do?method=exportData&dcclbh=" + dcclbh + "&shlx=" + shlx;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
