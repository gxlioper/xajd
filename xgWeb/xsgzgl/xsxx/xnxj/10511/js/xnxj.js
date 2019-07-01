
/**
 * 保存学年小结
 * @return
 */
function saveXnxj(saveType){

	var id = jQuery('#id').val();
	
	if(id != ""){
		showAlertDivLayer("该学生已填写学生小结，请确认！");
		return false;
	}

	if (jQuery("#xjnr").val()=="" || jQuery("#xh").val() == ""){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}

	
	//小结内容不得超过2000字符
	var obj = document.getElementById("xjnr");
	if(obj.value.length > 2000){
		showAlertDivLayer("小结内容最大字长度为"+2000+",现已经超过，请确认！");
		obj.focus();
		return false;
	}
	
	var url = "xsxx_xnxj_xjtxgl.do?method=doXnxjsq&type="+saveType;
	
	ajaxSubFormWithFun("xnxjForm",url,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	});
}

/**
 * 更新学年小结
 * @param type
 * @return
 */
function updateXnxj(type){
	
	if (!checkNull('xjnr')){
		return false;
	}
	//小结内容不得超过2000字符
	var obj = document.getElementById("xjnr");
	if(obj.value.length > 2000){
		showAlertDivLayer("自我小结最多"+2000+"字！");
		obj.focus();
		return false;
	}
	var url = "xsxx_xnxj_xjtxgl.do?method=doUpdateXnxj&type="+type;
	ajaxSubFormWithFun("xnxjForm",url,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	});
}

/**
 * 学年小结申请
 * @return
 */
function xnxjsq(){
	var kgzt = jQuery("#kgzt").val();
	if(kgzt==null||kgzt==''){
		showAlertDivLayer('基础设置未初始化，请联系管理员！');
		return false;
	}
	if ("n" == kgzt){
		showAlertDivLayer("当前未开放学年小结填写，请联系管理员！");
		return false;
	}
	
	showDialog('学年小结填写',780,440,'xsxx_xnxj_xjtxgl.do?method=xnxjsq');
}


/**
 * 学年小结--修改
 * @return
 */
function xnxjUpdate(){
	var kgzt = jQuery("#kgzt").val();
	if(kgzt==null||kgzt==''){
		showAlertDivLayer('基础设置未初始化，请联系管理员！');
		return false;
	}
	
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		
		var shjg = rows[0]["shjg"];
		
		if("-1" == shjg){
			showAlertDivLayer("该员工未填写学年小结，请先填写！");
			return false;
		}
		
		if ("0" != shjg && "3" != shjg){
			showAlertDivLayer("该申请已经被审核，不能修改！");
			return false;
		}
		showDialog("学年小结修改",780,450,"xsxx_xnxj_xjtxgl.do?method=xnxjxg&type=update&id="+rows[0]["id"]);
	}
}

/**
 * 学年小结--删除
 * @return
 */
function xnxjDelete(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (rows.length == 0){
		showAlertDivLayer("请选择您要删除的申请记录！");
	} else {
		
		for(var i=0;i<rows.length;i++){
			if(rows[i]['shjg']!='0'&& "" != rows[i]['shjg'] && null != rows[i]['shjg']){
				showAlertDivLayer("只能删除未审核的记录！");
				return false;
			}else if("" == rows[i]['shjg'] || null == rows[i]['shjg']){
				showAlertDivLayer("未申请的记录,无需删除！");
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要删除该申请记录吗？",{"okFun":function(){
			jQuery.post("xsxx_xnxj_xjtxgl.do?method=doXnxjdel",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * 学年小结--撤销
 * @return
 */
function cancle(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要撤销的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shjg']!='5'){
				showAlertDivLayer("只有审核中的记录才能被撤销！");
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要撤销选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("xsxx_xnxj_xjtxgl.do?method=doXnxjCancel", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

/**
 * 学年小结--批量提交
 * @return
 */
function submitBusi(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条您要提交的记录！");
	} else {
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shjg']!='0'&&rows[i]['shjg']!='3' && "" != rows[i]['shjg'] && null != rows[i]['shjg']){
				showAlertDivLayer("请选择未提交或者已退回的记录！");
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要提交选择的记录吗？", {
			"okFun" : function() {
				if(rows[0]['shjg']=='3'){
					jQuery.post("xsxx_xnxj_xjtxgl.do?method=doSubmit", {
						values : ids.toString()
					}, function(data) {
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					}, 'json');
					
				}else{
					jQuery.post("xsxx_xnxj_xjtxgl.do?method=checkKg",{}, function(data){
						if(data == null||data=="false"){
							showAlertDivLayer("学年小结填写开关已关闭，无法提交，请联系管理员");
							return false;
						}else{
							jQuery.post("xsxx_xnxj_xjtxgl.do?method=doSubmit", {
								values : ids.toString()
							}, function(data) {
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							}, 'json');
						}
					});
				}
			}
		});
	}
}

/**
 * 学年小结--流程跟踪
 * @return
 */
function xnxjLcinfo(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条记录！");
	} else {
		if(rows[0]["shjg"]=="0" || rows[0]['shjg'] == '-1'){
			showAlertDivLayer("无相关流程信息！");
			return false;
		}
		showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['id']+"&splc="+rows[0]['splid']);
	}
}

/**
 * 待处理、已处理面签切换
 * @param obj
 * @param shzt
 * @return
 */
function selectTab(obj,shzt){
	jQuery("#shzt").val(shzt);

	if (shzt == "dsh"){
		jQuery("#li_sh").css("display","");
		jQuery("#li_qx").css("display","none");
		
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_sh").css("display","none");
		jQuery("#li_qx").css("display","");
		
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	
	searchRs();
}

/**
 * 学年小结--审核
 * @return
 */
function xnxjSh(){
	
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要审核记录！");
	} else {
		showDialog("学年小结审核",750,550,"xsxx_xnxj_xjtxgl.do?method=toXnxjDgsh&id="+rows[0]["id"]+"&xtgwid="+rows[0]["xtgwid"]+"&shid="+rows[0]["shid"]+"&splid="+rows[0]["splid"]);
	} 
}

/**
 * 撤消审核
 * @return
 */
function cancelXnxjSh(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要撤消的审核记录！");
	} else {
		
		//最后一级撤销审核后调用的路径
		var cancelPath = "xsxx_xnxj_xjtxgl.do?method=doXnxjShCancel";
		confirmInfo("您确定要撤销操作吗?",function(ty){
			if(ty=="ok"){
				jQuery.post("comm_spl.do?method=cxshnew",{shlc:rows[0]["splid"],shid:rows[0]["shid"]},function(data){
						// 判断是否最后一级撤销(1:最后一级撤销成功）
						if("1" == data["cancelFlg"]){
							jQuery.post(cancelPath,{id:rows[0]["id"]},function(result){
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
 * 批量审核保存
 * @param shzt
 * @return
 */
function savePlsh(shzt,rddc,shyj){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var xhs = new Array();
	
	jQuery.each(rows,function(i,row){
		guid.push(row["guid"]);
		gwid.push(row["xtgwid"]);
		xhs.push(row["xh"]);
	});

	jQuery.post(
		"xszz_knsrd.do?method=savePlsh",
		{
		 shzt:shzt,
		 id:guid,
		 gwid:gwid,
		 xhs:xhs,
		 rddc:rddc,
		 shyj:shyj
		},function(data){
			
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				jQuery("#dataTable").reloadGrid();
			}});
		},
		'json'
	);
}

/**
 * 保存审核操作
 */
function saveXnxjSh(){
	var shzt = jQuery("#shjg").val();
	
	if (jQuery("#shyj").val() == ""){
		showAlertDivLayer("请填写审核意见！");
		return false;
	}
	if (jQuery("#shyj").val().length > 200){
		showAlertDivLayer("审核意见不能超过200字！");
		return false;
	}
	
	showConfirmDivLayer("您确定审核该申请吗？",{"okFun":function(){
		var url = "xsxx_xnxj_xjtxgl.do?method=doXnxjSh";
		ajaxSubFormWithFun("xnxjForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
	}});
}

function saveShzt(){
	var shzt = jQuery("#shjg").val();
	if(jQuery("#shjg").val() == "0"){
		showAlertDivLayer("请选择审核状态！");
		return false;
	}
	
	var shyj = jQuery("#shyj").val();
	if (jQuery.trim(shyj) == ""){
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
	showConfirmDivLayer("您确定" + message + "该申请吗？",{"okFun":function(){
		var url = "rcsw_rcxwwh_rcxwshgl.do?method=rcxwDgsh&type=save";
		ajaxSubFormWithFun("rcxwshForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
	}});
}

//打印报表
function printXnxjSq(url) {
	
	var rows = jQuery("#dataTable").getSeletRow();
	
	var vals = [];
	
	if (rows.length <=0) {
		showAlertDivLayer("请选择一条记录！");
	} else {
		
		for(i = 0 ; i < rows.length ; i++){
			if(rows[i]['txzt'] != '1'){
				showAlertDivLayer("学年小结未填写！");
				return ;
			}
			vals[i] = rows[i]['xh'] + '|' + rows[i]['xn'];
		}
		
		var url = url + "&vals=" +vals.join(',');
		window.open(url);
	}
}

