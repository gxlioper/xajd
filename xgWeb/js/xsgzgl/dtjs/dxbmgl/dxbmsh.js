//切换待处理/已处理页面
function selectTab(obj, shzt) {
	jQuery("#shzt").val(shzt);
	if (shzt == "dsh") {
		jQuery("#li_sh").css("display", "");
		jQuery("#li_qx").css("display", "none");
		jQuery("#title").html("党校培训待审核列表");
		var map = getSuperSearch();
		map["shzt"]="dsh";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_sh").css("display", "none");
		jQuery("#li_qx").css("display", "");
		jQuery("#title").html("党校培训已审核列表");
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
	var sqid = rowObject["sqid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + sqid+ "')\" class='name'>" + cellValue + "</a>";
}
// 查看信息
function ckxx(sqid) {
	var query = jQuery("#query").val();
	var url = "dtjs_dxbmgl_dxbmshCk.do?sqid=" + sqid;
	var title = "党校培训申请信息";
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
	var url = "dtjs_dxbmgl_dxbmshBc.do?shzt="+shzt+"&tt="+new Date();
	lock();
	jQuery("#form").ajaxSubmit( {
		url : url,
		type : "post",
		dataType : "json",
		success : function(data) {
			if (data||data=='true') {
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
			return false;
		}
	}
	return true;
}
//审核
function dtxxsh() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要审核记录！");
	} else if (rows.length == 1){
		var xh = rows[0]["xh"];
		var url ='dtjs_dxbmgl_dxbmshSh.do?xh=' + xh + '&sqid='+ rows[0]["sqid"]+"&gwid="+rows[0]["gwid"];
		showDialog("党团培训信息审核",700,480,url);
	}
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
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splc']);
	}
}
//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport('dxbmgl_dxbmsh.do',exportData);
}

// 导出方法
function exportData(dcclbh) {
	var shlx = jQuery("#shzt").val();
	setSearchTj();//设置高级查询条件
	var url = "dtjs_dxbmgl_dxbmshDc.do?dcclbh=" + dcclbh + "&shlx=" + shlx;//dcclbh,导出功能编号
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
	jQuery.each(rows,function(i,row){
		dtxxsqid.push(row["dtxxsqid"]);
		gwid.push(row["gwid"]);
		xhs.push(row["xh"]);
		splcs.push(row["splc"]);
	});
	jQuery.post(
		"dtxxsh.do?method=savePlsh",
		{
		 shzt:shzt,
		 ids:dtxxsqid,
		 gwids:gwid,
		 xhs:xhs,
		 splcs:splcs,
		 shyj:shyj
		},function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				jQuery("#dataTable").reloadGrid();
			}});
		},
		'json'
	);
	function cxshnews_splc(obj){
		var sfkq=obj.data.sfkq;
		var rows = jQuery("#dataTable").getSeletRow();
		if(sfkq=="1"){//开启 则最后一级可撤销
			if (rows.length != 1){
				alertInfo("请选择一条您要撤销审核的记录！");
			} else {
				splc_cx_news(rows[0][obj.data.splc],rows[0]["shid"],rows[0][obj.data.dtxxsqid]);
			}
		}else{
			splc_cxs(rows[0][obj.data.splc],rows[0]["shid"],rows[0][obj.data.dtxxsqid]);

		}
	}
	/*
	 * 审批流程撤销[最后一级可撤销]
	 * shid 审核id
	 * splc 审批流程id 
	 */
	function splc_cx_news(splc,shid,dtxxsqid){
		//最后一级撤销审核后调用的路径
		var cancelPath = jQuery("#cancelPath").val();
		confirmInfo("您确定要撤销操作吗?",function(ty){
			if(ty=="ok"){
				jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid,dtxxsqid:dtxxsqid},function(data){
						// 判断是否最后一级撤销(1:最后一级撤销成功）
						if("1" == data["cancelFlg"]){
							jQuery.post(cancelPath,{splc:splc,shid:shid,dtxxsqid:dtxxsqid},function(result){
								showAlertDivLayer(result["message"],{},{"clkFun":function(){
									jQuery("#dataTable").reloadGrid();
								}});
							},'json');
						}else{
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								jQuery("#dataTable").reloadGrid();
							}});
						}
					
				},'json');
			}
		});
	}

	/*
	 * 审批流程撤销
	 * shid 审核id
	 * splc 审批流程id 
	 */
	function splc_cxs(splc,shid,dtxxsqid){
		confirmInfo("您确定要撤销操作吗?",function(ty){
			//alert(ty);
			if(ty=="ok"){
				jQuery.post("comm_spl.do?method=cxsh",{shlc:splc,shid:shid,dtxxsqid:dtxxsqid},function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						//if (parent.window){
							//refersh();
							jQuery("#dataTable").reloadGrid();
						//}
					}});
				},'json');
			}
		});
	}
}
