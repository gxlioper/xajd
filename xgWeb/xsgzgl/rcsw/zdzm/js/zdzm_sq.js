
/**
 * 高级查询
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 新增在读证明
 * @return
 */
function addZdzmSq(){
	var isopen = jQuery("#isopen").val();
	
	if("true"!=isopen){
		showAlertDivLayer("当前申请未开放，请联系管理员！");
		return false;
	}
	
	showDialog('在读证明申请',780,350,'rcsw_zdzm_sqgl.do?method=addZdzmSq');
}
/**
 * 新增在读证明保存操作
 * @return
 */
function addZdzmSqAction(){
	var xh = jQuery('#xh').val();
	var sqly = jQuery('#sqly').val();
	
	if (xh=="" || sqly == ""){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}
	
	if(sqly.length > 500){
		showAlertDivLayer("申请理由最大字数不超过"+500+",请确认！");
		return false;
	}
	
	var url = "rcsw_zdzm_sqgl.do?method=addZdzmSqAction&type=save";
		ajaxSubFormWithFun("rcswZdzmSqForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
}

/**
 * 新增并提交在读证明保存操作
 * @return
 */
function addSubmitZdzmSqAction(){
	var xh = jQuery('#xh').val();
	var sqly = jQuery('#sqly').val();
	
	if (xh=="" || sqly == ""){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}
	
	if(sqly.length > 500){
		showAlertDivLayer("申请理由最大字数不超过"+500+",请确认！");
		return false;
	}
	
	var url = "rcsw_zdzm_sqgl.do?method=addZdzmSqAction&type=submit";
		ajaxSubFormWithFun("rcswZdzmSqForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
}

/**
 * 修改在读证明
 * @return
 */
function updateZdzmSq(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	} else{
		if(rows[0]['shzt'] != '0' && rows[0]['shzt'] != '3'){
			showAlertDivLayer("该申请已经被审核，不能修改！");
			return false;
		}
		showDialog('在读证明修改',780,350,'rcsw_zdzm_sqgl.do?method=updateZdzmSq&zdzmsqid=' + rows[0]['zdzmsqid']);
	}
}

/**
 * 更新在读证明保存操作
 * @return
 */
function updateZdzmSqAction(){
	var sqly = jQuery('#sqly').val();
	
	if (sqly == ""){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}
	if(sqly.length > 500){
		showAlertDivLayer("申请理由最大字数不超过"+500+",请确认！");
		return false;
	}
	var url = "rcsw_zdzm_sqgl.do?method=updateZdzmSqAction&type=save";
		ajaxSubFormWithFun("rcswZdzmSqForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
}

/**
 * 更新并提交在读证明保存操作
 * @return
 */
function updateSubmitZdzmSqAction(){
	var sqly = jQuery('#sqly').val();
	var shzt = jQuery('#shzt').val();
	var isopen = jQuery('#isopen').val();
	
	if("3"!=shzt&&"true"!=isopen){
		showAlertDivLayer("当前申请未开放，请联系管理员！");
		return false;
	}
	
	if (sqly == ""){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}
	
	if(sqly.length > 500){
		showAlertDivLayer("申请理由最大字数不超过"+500+",请确认！");
		return false;
	}
	var url = "rcsw_zdzm_sqgl.do?method=updateZdzmSqAction&type=submit";
		ajaxSubFormWithFun("rcswZdzmSqForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
}

/**
 * 删除在读证明申请记录
 * @return
 */
function deleteZdzmSq(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (rows.length == 0){
		showAlertDivLayer("请选择一条您要删除的记录！");
		return false;
	} else{
		for(i=0 ; i< rows.length; i++){
			if(rows[i]['shzt'] != '0'){
				showAlertDivLayer("只能删除未提交的记录！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除所选记录？",{"okFun":function(){
			jQuery.post("rcsw_zdzm_sqgl.do?method=deleteZdzmSqAction",{sqids:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * 撤销
 * @return
 */
function cancle(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条要撤销的记录！");
	} else {
		for(var i=0;i<rows.length;i++){
			if(rows[i]['shzt'] != '5'){
				showAlertDivLayer("只有审核中的记录才能被撤销！");
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要撤销选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("rcsw_zdzm_sqgl.do?method=cancelZdzmSqAction", {
					zdzmsqid : rows[0]['zdzmsqid'] , splcid : rows[0]['splcid']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

/**
 * 提交在读证明申请
 * @return
 */
function submitBusi(){
	var rows = jQuery("#dataTable").getSeletRow();
	var isopen = jQuery("#isopen").val();
	if('3' != rows[0]['shzt']&&"true"!=isopen){
		showAlertDivLayer("当前申请未开放，请联系管理员！");
		return false;
	}
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条要提交的记录！");
	} else {
		if(rows[0]['shzt']!='0'&&rows[0]['shzt']!='3'){
			showAlertDivLayer("请选择未提交或者已退回的记录！");
			return false;
		}
		
		showConfirmDivLayer("您确定要提交选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("rcsw_zdzm_sqgl.do?method=submitZdzmSqAction", {
					zdzmsqid : rows[0]['zdzmsqid'] , splcid : rows[0]['splcid'] , xh : rows[0]['xh'], shzt : rows[0]['shzt']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

/**
 * 在读证明审核
 */
function zdzmSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	if(rows.length == 0){
		showAlertDivLayer("请选择您要审核记录！");
		return false;
	}
	
	if (rows.length == 1){
		showDialog("在读证明审核",750,500,"rcsw_zdzm_shgl.do?method=shZdzmsq&zdzmsqid="+rows[0]["zdzmsqid"]+"&xtgwid="+rows[0]["xtgwid"]+"&shid="+rows[0]["shid"]+"&splid="+rows[0]["splcid"]);
	} else {
		showDialog("在读证明批量审核",500,300,"rcsw_zdzm_shgl.do?method=zdzmPlsh");
	} 
}



/**
 * 批量审核保存
 * @param shzt
 * @return
 */
function savePlsh(shzt,shyj){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var xhs = new Array();
	var splc = new Array();
	
	jQuery.each(rows,function(i,row){
		guid.push(row["zdzmsqid"]);
		gwid.push(row["xtgwid"]);
		xhs.push(row["xh"]);
		splc.push(row["splcid"]);
	});

	jQuery.post(
		"rcsw_zdzm_shgl.do?method=zdzmPlsh&type=save",
		{
		 shzt:shzt,
		 id:guid,
		 gwids:gwid,
		 xhs:xhs,
		 shyj:shyj,
		 splcs:splc
		},function(data){
			
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				jQuery("#dataTable").reloadGrid();
			}});
		},
		'json'
	);
}

/**
 * 提交在读证明审核
 * @return
 */
function submitZdzmSh(){
	if (jQuery("#shyj").val() == ""){
		showAlertDivLayer("请填写审核意见！");
		return false;
	}
	showConfirmDivLayer("您确定审核该申请吗？",{"okFun":function(){
		var url = "rcsw_zdzm_shgl.do?method=shZdzmsqAction";
		ajaxSubFormWithFun("rcswZdzmShForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
	}});
}

/**
 * 撤消审核
 * @return
 */
function cancelZdzmSh(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要撤消的审核记录！");
	} else {
		
		//最后一级撤销审核后调用的路径
		var cancelPath = "rcsw_zdzm_shgl.do?method=cancelZdzmsqAction";
		confirmInfo("您确定要撤销操作吗?",function(ty){
			if(ty=="ok"){
				jQuery.post("comm_spl.do?method=cxshnew",{shlc:rows[0]["splcid"],shid:rows[0]["shid"]},function(data){
						// 判断是否最后一级撤销(1:最后一级撤销成功）
						if("1" == data["cancelFlg"]){
							jQuery.post(cancelPath,{zdzmsqid:rows[0]["zdzmsqid"]},function(result){
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
}

/**
 * 流程跟踪
 * @return
 */
function lcinfo(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条记录！");
	} else {
		if(rows[0]["shzt"]=="0"){
			showAlertDivLayer("无相关流程信息！");
			return false;
		}
		showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['zdzmsqid']+"&splc="+rows[0]['splcid']);
	}
}

/**
 * 流程跟踪
 * @return
 */
function lcinfoSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条记录！");
	} else {
		showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['zdzmsqid']+"&splc="+rows[0]['splcid']);
	}
}

var DCCLBH = "rcsw_zdzm_sqgl.do";//dcclbh,导出功能编号


//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, exportData);
}

// 导出方法
function exportData() {
	setSearchTj();//设置高级查询条件
	var url = "rcsw_zdzm_sqgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}