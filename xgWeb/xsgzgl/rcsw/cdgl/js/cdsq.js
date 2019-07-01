
/**
 * 场地使用情况查询
 * @return
 */
function cdxxck(){
	showDialog('场地使用信息',700,450,'rcsw_cdgl_cdsqck.do?method=cdsqjlCx')
}

/**
 * 高级查询
 * @return
 */
function searchRs() {
  var map = getSuperSearch();
  jQuery("#dataTable").reloadGrid(map);
}

/**
 * 新增场地申请
 * @return
 */
function addCdsq(){
	showDialog('场地申请',780,500,'rcsw_cdgl_cdsq.do?method=cdsqSq');
}

/**
 * 新增场地申请保存操作
 * @return
 */
function addCqsqAction(){
	var xxdm = jQuery("#xxdm").val();
	var xh = jQuery('#xh').val();
	var cdid = jQuery('#cdid').val();
	
	if (xh==""){
		showAlert("请选择一个学生！");
		return false;
	}
	if (cdid==""){
		showAlert("请选择一个场地！");
		return false;
	}
	
	var checkids = "sqly-sqsjdkssj-sqsjdjssj";
	
	if(!checkNotNull(checkids)){
		showAlert("请将带<font color='red'>*</font>的项目填写完整!");
		return false;
	}
	
	if(jQuery('#sqly').val().length > 500){
		showAlert("申请理由最大字数不超过"+500+",请确认！");
		return false;
	}
	if(xxdm == "10351"){
		if(jQuery('#sqly').val().length < 50){
			showAlert("申请理由最小字数"+50+",请确认！");
			return false;
		}
	}
	var xysfilepath = jQuery("#xysfilepath").val();
	if (xysfilepath && jQuery("#xys_checkbox") && !jQuery("#xys_checkbox").attr('checked')){
		showAlert("请先接受《场地申请协议》 ！");
		return false;
	}
	var url = "rcsw_cdgl_cdsq.do?method=addCqsqAction";
		ajaxSubFormWithFun("rcswCdsqForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if(data['flag'] == 'checkerror'){
					return false;
				}else{
					if (parent.window){
						refershParent();
					}
				}
			}});
		});
}

/**
 * 新增并提交场地申请保存操作
 * @return
 */
function addSubmitCdsqAction(){
	var xxdm = jQuery("#xxdm").val();
	var xh = jQuery('#xh').val();
	var cdid = jQuery('#cdid').val();
	
	if (xh==""){
		showAlert("请选择一个学生！");
		return false;
	}
	if (cdid==""){
		showAlert("请选择一个场地！");
		return false;
	}
	
	var checkids = "sqly-sqsjdkssj-sqsjdjssj";
	
	if(!checkNotNull(checkids)){
		showAlert("请将带<font color='red'>*</font>的项目填写完整!");
		return false;
	}
	
	if(jQuery('#sqly').val().length > 500){
		showAlert("申请理由最大字数不超过"+500+",请确认！");
		return false;
	}
	if(xxdm == "10351"){
		if(jQuery('#sqly').val().length < 50){
			showAlert("申请理由最小字数"+50+",请确认！");
			return false;
		}
	}
	var xysfilepath = jQuery("#xysfilepath").val();
	if (xysfilepath && jQuery("#xys_checkbox") && !jQuery("#xys_checkbox").attr('checked')){
		showAlert("请先接受《场地申请协议》 ！");
		return false;
	}
	var url = "rcsw_cdgl_cdsq.do?method=addSubmitCqsqAction";
		ajaxSubFormWithFun("rcswCdsqForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if(data['flag'] == 'checkerror'){
					return false;
				}else{
					if (parent.window){
						refershParent();
					}
				}
			}});
		});
}

/**
 * 提交场地申请申请
 * @return
 */
function submitBusi(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条要提交的记录！");
		return false;
	} else {
		if(rows[0]['shzt']!='0'&&rows[0]['shzt']!='3'){
			showAlertDivLayer("请选择未提交或者已退回的记录！");
			return false;
		}

		var shzt = rows[0]["shzt"];
		showConfirmDivLayer("您确定要提交选择的记录吗？", {
			"okFun" : function() {
				if(shzt!="3"){
					// 验证是否可提交
					jQuery.post("rcsw_cdgl_cdsq.do?method=checkSfktj", {
						cdid:rows[0]["cdid"]
					}, function(data) {
						if(data ==null || data=="false"){
							showAlertDivLayer("您申请的场地已关闭申请，无法提交，详情请联系管理员。");
							return false;
						}else{
	
							// 提交
							jQuery.post("rcsw_cdgl_cdsq.do?method=submitCdsqAction", {
								sqid : rows[0]['sqid']
							}, function(data) {
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							}, 'json');
						}
					});
					
				}else{
					jQuery.post("rcsw_cdgl_cdsq.do?method=submitCdsqAction", {
						sqid : rows[0]['sqid']
					}, function(data) {
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					}, 'json');
				}
			
			}
		});
	}
}

/**
 * 删除场地申请
 * @return
 */
function deleteCdsq(){
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
			jQuery.post("rcsw_cdgl_cdsq.do?method=deleteCdsqAction",{sqids:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * 修改场地申请
 * @return
 */
function updateCdsq(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	} else{
		if(rows[0]['shzt'] != '0' && rows[0]['shzt'] != '3'){
			showAlertDivLayer("请选择未提交或者已退回的记录！");
			return false;
		}
		showDialog('场地申请修改',780,500,'rcsw_cdgl_cdsq.do?method=cdsqXg&sqid=' + rows[0]['sqid']);
	}
}

/**
 * 更新场地申请保存操作
 * @return
 */
function updateCdsqAction(type){
	var xxdm = jQuery("#xxdm").val();
	var checkids = "sqly-sqsjdkssj-sqsjdjssj";
	
	if(!checkNotNull(checkids)){
		showAlert("请将带<font color='red'>*</font>的项目填写完整!");
		return false;
	}
	
	if(jQuery('#sqly').val().length > 500){
		showAlert("申请理由最大字数不超过"+500+",请确认！");
		return false;
	}
	if(xxdm == "10351"){
		if(jQuery('#sqly').val().length < 50){
			showAlert("申请理由最小字数"+50+",请确认！");
			return false;
		}
	}
	var xysfilepath = jQuery("#xysfilepath").val();
	if (xysfilepath && jQuery("#xys_checkbox") && !jQuery("#xys_checkbox").attr('checked')){
		showAlert("请先接受《场地申请协议》 ！");
		return false;
	}
	var url = "rcsw_cdgl_cdsq.do?method=cdsqXgAction&type="+type;
	
	var shzt = jQuery("#shzt").val();
	if(shzt!="3" && type=='submit'){
		// 验证是否可提交
		jQuery.post("rcsw_cdgl_cdsq.do?method=checkSfktj", {
			cdid:jQuery("#cdid").val()
		}, function(data) {
			if(data ==null || data=="false"){
				showAlertDivLayer("您申请的场地已关闭申请，无法提交，详情请联系管理员。");
				return false;
			}else{

				ajaxSubFormWithFun("rcswCdsqForm",url,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						if(data['flag'] == 'checkerror'){
							return false;
						}else{
							if (parent.window){
								refershParent();
							}
						}
					}});
				});
			}
		});
		
	}else{

		ajaxSubFormWithFun("rcswCdsqForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if(data['flag'] == 'checkerror'){
					return false;
				}else{
					if (parent.window){
						refershParent();
					}
				}
			}});
		});
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
				jQuery.post("rcsw_cdgl_cdsq.do?method=cancelCdsqAction", {
					sqid : rows[0]['sqid']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}



/**
 * 场地申请审核
 */
function cdshSh(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length == 0){
		showAlertDivLayer("请选择一条您要审核记录！");
		return false;
	} else if(rows.length == 1){
		showDialog("场地申请审核",750,485,"rcsw_cdgl_cdsh.do?method=cdshSh&sqid="+rows[0]["sqid"]+"&xtgwid="+rows[0]["xtgwid"]+"&shid="+rows[0]["shid"]+"&splid="+rows[0]["splcid"]);
	} else {
		showDialog("场地申请审核",500,300,"rcsw_cdgl_cdsh.do?method=cdsqPlsh");
	}
}


/**
 * 场地申请批量审核
 * @param shzt
 * @param shyj
 * @return
 */
function savePlsh(shzt,shyj){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var xhs  = new Array();
	var splc = new Array();
	
	jQuery.each(rows, function(i,row){
		guid.push(row["sqid"]);
		gwid.push(row["xtgwid"]);
		xhs.push(row["xh"]);
		splc.push(row["splcid"]);
		
	});
	
	jQuery.post(
			"rcsw_cdgl_cdsh.do?method=cdsqPlsh&type=save",
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
 * 提交场地申请审核
 * @return
 */
function submitCdshSh(){
	if (jQuery("#shyj").val() == ""){
		showAlertDivLayer("请填写审核意见！");
		return false;
	}
	
	var message;
	if(jQuery("#shjg").val() == "1"){
		message = "通过";
	}
	if(jQuery("#shjg").val() == "2"){
		message = "不通过";
	}
	if(jQuery("#shjg").val() == "3"){
		message = "退回";
	}
	
	
	showConfirmDivLayer("您确定“"+message+"”该申请吗？",{"okFun":function(){
		var url = "rcsw_cdgl_cdsh.do?method=cdshShAction";
		ajaxSubFormWithFun("rcswCdshForm",url,function(data){
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
function cancelCdshSh(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要撤消的审核记录！");
	} else {
		
		//最后一级撤销审核后调用的路径
		var cancelPath = "rcsw_cdgl_cdsh.do?method=cancelCdshAction";
		confirmInfo("您确定要撤销操作吗?",function(ty){
			if(ty=="ok"){
				jQuery.post("comm_spl.do?method=cxshnew",{shlc:rows[0]["splcid"],shid:rows[0]["shid"]},function(data){
						// 判断是否最后一级撤销(1:最后一级撤销成功）
						if("1" == data["cancelFlg"]){
							jQuery.post(cancelPath,{sqid:rows[0]["sqid"]},function(result){
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
			showAlertDivLayer("请选择已提交的记录！");
			return false;
		}
		showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splcid']);
	}
}

/**
 * 杭师大个性化   评价
 */
function pj(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条记录进行评价！");
	} else {
		if(rows[0]['shzt'] != '1'){
			showAlertDivLayer("只有审核通过的记录才能评价！");
			return false;
		}else{
			jQuery.post("rcsw_cdgl_cdjg.do?method=sfpj",{sqid:rows[0]["sqid"]},function(data){
				if(data["flag"]=="0"){
					showAlertDivLayer("已评价！");
					return false;
				}else{
					showDialog("评价",530,310,'rcsw_cdgl_cdjg.do?method=pj&sqid='+rows[0]['sqid']);
				}
			},'json');
		}
	}
}

/**
 * 流程跟踪
 * @return
 */
function lcinfoSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择已提交的记录！");
	} else {
		showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splcid']);
	}
}

var DCCLBH = "rcsw_cdgl_cdsq.do";//dcclbh,导出功能编号


//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, exportData);
}

// 导出方法
function exportData() {
	setSearchTj();//设置高级查询条件
	var url = "rcsw_cdgl_cdsq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}