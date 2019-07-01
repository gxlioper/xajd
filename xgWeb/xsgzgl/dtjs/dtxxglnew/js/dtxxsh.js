var action="dtxxsh.do";


//切换待处理/已处理页面
function selectTab(obj, shzt) {
	jQuery("#shzt").val(shzt);

	if (shzt == "dsh") {
		jQuery("#li_sh").css("display", "");
		jQuery("#li_qx").css("display", "none");
		jQuery("#title").html("党团待审核列表");
		var map = getSuperSearch();
		map["shzt"]="dsh";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_sh").css("display", "none");
		jQuery("#li_qx").css("display", "");
		jQuery("#title").html("党团已审核列表");
		var map = getSuperSearch();
		map["shzt"]="ysh";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}

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
	var dtxxsqid = rowObject["dtxxsqid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + dtxxsqid
			+ "')\" class='name'>" + cellValue + "</a>";
}
// 查看信息
function ckxx(dtxxsqid) {
	var query = jQuery("#query").val();
	var url = action + "?method=showView&dtxxsqid=" + dtxxsqid;
	var title = "党团申请信息";
	showDialog(title, 700, 440, url);
}
/**
 * 保存审核操作
 * @param shzt
 * @param message
 * @return
 */
function save_sh_bak(shzt,message){
	jQuery("#shzt").val(shzt);
	
	if (jQuery("#shyj").val() == ""){
		showAlertDivLayer("请填写审核意见！");
		return false;
	}
	if (jQuery("#shyj").val().length>200){
		showAlertDivLayer("审核意见不能超过200字");
		return false;
	}
	var text="通过";
	if(shzt=="2"){
		text="不通过";
	}else if(shzt=="3"){
		text="退回";
		zx(shzt);
		return false;
	}
	//提交审核
	showConfirmDivLayer("您确定<font color='red'>[" + text + "]</font>该申请吗？",{"okFun":function(){
			zx(shzt,text);
		}});
	
}
function zx(shzt,text){
	var url = "dtxxsh.do?method=dtxxsh&type=save&tt="+new Date();
	/*if(shzt=="3"||shzt==3){
		var shid = jQuery("#shid").val();
		var splc = jQuery("#splc").val();
		var shyj = jQuery("#shyj").val();
		splc_th(shid,splc,shyj);
		return false;
	}*/
	lock();
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
				showAlert("<font color='red'>[+"+text+"]</font>操作失败！");
			}
			unlock();
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8"
		});
	
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
//审核
function dtxxsh() {
	var rows = jQuery("#dataTable").getSeletRow();
	
	
	if (rows.length == 0){
		showAlertDivLayer("请选择您要审核记录！");
	} else if (rows.length == 1){
		var xh = rows[0]["xh"];
		var url = action + '?method=dtxxsh&xh=' + xh + '&dtxxsqid='+ rows[0]["dtxxsqid"]+"&gwid="+rows[0]["gwid"];
		showDialog("党团信息审核",700,480,url);
	} else {
		
		showDialog("党团信息审核批量审核",500,220,"dtxxsh.do?method=dtxxPlsh");
	}
	
	
	/*if (rows.length != 1) {
		showAlert("请选择一条您要审核的记录！");
	} else {
		var xh = rows[0]["xh"];
		var url = action + '?method=dtxxsh&xh=' + xh + '&dtxxsqid='
		+ rows[0]["dtxxsqid"]+"&gwid="+rows[0]["gwid"];
		showDialog("党团信息审核",700,480,url);
	}*/
	
}
//撤销审核
function cancelSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要撤消的审核记录！");
	} else {
		showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
			jQuery.post("dtxxsh.do?method=cancelSh",
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
		showDialog("党团审批流程跟踪", 600, 400, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['dtxxsqid'] + "&splc=" + rows[0]['splc']);
	}
}
//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport('dtxxshbase.do',exportData);
}

// 导出方法
function exportData(dcclbh) {
	var shlx = jQuery("#shzt").val();
	setSearchTj();//设置高级查询条件
	var url = "dtxxsh.do?method=exportData&dcclbh=" + dcclbh + "&shlx=" + shlx;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * 批量审核保存
 * @param shzt
 * @return
 */
function savePlsh(shzt,shyj){
	var rows = jQuery("#dataTable").getSeletRow();
	var dtxxsqid = new Array();
	var gwid = new Array();
	var xhs = new Array();
	var splcs = new Array();
	
	//修改bug
	var jddms = new Array();
	
	jQuery.each(rows,function(i,row){
		dtxxsqid.push(row["dtxxsqid"]);
		gwid.push(row["gwid"]);
		xhs.push(row["xh"]);
		splcs.push(row["splc"]);
		//修改bug
		jddms.push(row["jddm"]);
	});
	jQuery.post(
		"dtxxsh.do?method=savePlsh",
		{
		 shzt:shzt,
		 ids:dtxxsqid,
		 gwids:gwid,
		 xhs:xhs,
		 splcs:splcs,
		 //修改bug
		 jddms:jddms,
		 shyj:shyj
		},function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				jQuery("#dataTable").reloadGrid();
			}});
		},
		'json'
	);
}
