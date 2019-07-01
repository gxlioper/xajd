/**
 * 填写申请表
 */
function editSqb(){
	showDialog("填写申请表",800,500,"zxdk_sydk.do?method=dksq");
}

function saveDksq(url){
	
	var xxdm = jQuery("#xxdm").val();
	var yhdm = jQuery("#yhdm").val();
	var hzjym = jQuery("#hzjym").val();
	
	if("10335"==xxdm&&"国家开发银行"==yhdm.trim()&&""==hzjym.trim()){
		showAlertDivLayer("国家开发银行必须填写回执码");
		return false;
	}
	//checkNull("xh-xn-yhdm-dkje-dkqx-zsysf-xfysf-dkkssj-sqly")浙大个性化废除
	if("10335"==xxdm&&!checkNull("xh-xn-yhdm-dkje-sqly")){
		return false;
	}else if("10335"!=xxdm&&!checkNull("xh-xn-dkje-dkqx-sqly-zsysf-xfysf-yhdm-htbh-dkkssj-hzjym")){
		return false;
	}
	
	/*
	var dkzs=jQuery("#dkje").val();
	
	var zsys = jQuery("#zsysf").val();
	
	var xfys = jQuery("#xfysf").val();
	
	if(parseInt(dkzs)<(parseInt(zsys)+parseInt(xfys))){
		showAlertDivLayer("应收费大于贷款总金额!");
		jQuery("#dkje").focus();
			return false;
		}
	*/
	var xh=jQuery("#xh").val();
	var xn=jQuery("#xn").val();
	
	jQuery.post("zxdk_sydk.do?method=isExitsByXhAndXn",{xh:xh,xn:xn},function(data){
		if(data=="true"){
			showAlertDivLayer("该学生该学年已申请或者结果库已存在");
			return false;
		}else{
			ajaxSubFormWithFun("zxdkSydksqshForm",url,function(data){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					refershParent();
				}});
			});
		}
	 });
}

function saveXgDksq(url){
	
	var xxdm = jQuery("#xxdm").val();
	var yhdm = jQuery("#yhdm").val();
	var hzjym = jQuery("#hzjym").val();
	
	if("10335"==xxdm&&"国家开发银行"==yhdm.trim()&&""==hzjym.trim()){
		showAlertDivLayer("国家开发银行必须填写回执码");
		return false;
	}
	
	if("10335"==xxdm&&!checkNull("xh-xn-yhdm-dkje-dkqx-zsysf-xfysf-dkkssj-sqly")){
		return false;
	}else if("10335"!=xxdm&&!checkNull("xh-xn-dkje-dkqx-sqly-zsysf-xfysf-yhdm-htbh-dkkssj-hzjym")){
		return false;
	}
	/*
	var dkzs=jQuery("#dkje").val();
	
	var zsys = jQuery("#zsysf").val();
	
	var xfys = jQuery("#xfysf").val();
	
	if(parseInt(dkzs)<(parseInt(zsys)+parseInt(xfys))){
		showAlertDivLayer("应收费大于贷款总金额!");
		jQuery("#dkje").focus();
			return false;
		}
	*/
	var xh=jQuery("#xh").val();
	var xn=jQuery("#xn").val();
	
    ajaxSubFormWithFun("zxdkSydksqshForm",url,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			refershParent();
		}});
	});
}

/**
 * 查询
 */
function searchRs(){
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	if (null!=shzt&&shzt != "") {
		map["shzt"] = shzt;
	}else{
		map["shzt"] = "dsh";
	}
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 修改申请表
 * @returns {Boolean}
 */
function xgSqb(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
			showAlertDivLayer("该申请正在审核中，不能修改！");
			return false;
		}
		showDialog("修改申请表",800,500,"zxdk_sydk.do?method=xgDksq&id="+rows[0]["id"]);
	}
}

//提交
function submitBusi(){
	
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (ids.length != 1){
		showAlertDivLayer("请选择您要提交的记录！！");
	}
	
	if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
		showAlertDivLayer("您选择的记录已经在审核中，无需重复提交！");
		return false;
	}
	jQuery.post("zxdk_sydk.do?method=submit",{id:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
}


function cancelSubmit(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("请选择您要取消申请的记录！");
	} else {
		jQuery.post("zxdk_sydk.do?method=cancelSubmit",{id:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}
}


/**
 * 取消申请
 */
function qxsq(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要取消申请的记录！");
	} else if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
		showAlertDivLayer("您选择的记录已审核，不能删除！");
		return false;
	} else {
		showConfirmDivLayer("您确定要删除该申请吗？",{"okFun":function(){
			jQuery.post("zxdk_sydk.do?method=delDksq",{id:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * 流程跟踪
 */
function viewLcgz(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条记录！");
	} else {
		showDialog("流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['id']+"&splc="+rows[0]['splcid']);
	}
	
}

function selectTab(obj,zt){
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	jQuery("#shzt").val(zt);
	
	if (zt == "dsh"){
		jQuery("#li_sh").css("display","");
		jQuery("#li_qx").css("display","none");
		var map = getSuperSearch();
		map["shzt"]="dsh";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_sh").css("display","none");
		jQuery("#li_qx").css("display","");
		var map = getSuperSearch();
		map["shzt"]="ysh";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
}


function showAuding() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlert("请选择一条您要审核的记录！");
		return false;
	} else if(rows.length == 1){
		var id = rows[0]["id"];
		var url = "zxdk_sydk.do?method=dksh&id="+id;
		showDialog("生源贷款审核",800,500,url);
	} else if(jQuery("#xxdm").val() == "10335" && rows.length > 0){
		//浙江大学个性化判断，跳转进入批量审核界面
		showDialog("批量审核", 500, 250, "zxdk_sydk.do?method=sydPlsh");
	} else{
		showAlert("请选择一条您要审核的记录！");
		return false;
	}
}

/**
 * 查看申请表
 * @param id
 */
function ckSqb(id){
	showDialog("查看申请表",800,500,"zxdk_sydk.do?method=ckDksq&id="+id);
}

//导出
function exportConfig(){
	var DCCLBH='zxdk_sydk_dksq.do';
	customExport(DCCLBH, exportData);
}

function exportData(){
	var DCCLBH='zxdk_sydk_dksq.do';
	setSearchTj();//设置高级查询条件
	
	var url = "zxdk_sydk.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function savePlsh(shzt, shyj) {
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var xhs = new Array();

	jQuery.each(rows, function(i, row) {
		guid.push(row["id"]);
		gwid.push(row["xtgwid"]);
		xhs.push(row["xh"]);
	});
	jQuery.post("zxdk_sydk.do?method=sydPlsh&type=save", {
		shzt : shzt,
		splcid : rows[0]["splcid"],
		ids : guid,
		gwids : gwid,
		xhs : xhs,
		shyj : shyj,
	}, function(data) {
		
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				jQuery("#dataTable").reloadGrid();
			}
		});
	}, 'json');
}