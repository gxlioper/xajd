var DCCLBH = "xszz_knsrdbjpy_sq.do";//dcclbh,导出功能编号
/**
 * 加载家庭情况调查信息
 * @param obj
 * @return
 */
function showJtqk(obj){
	var className = jQuery(obj).attr("class");
	var newClass = className == "up" ? "down" : "up";

	jQuery(obj).attr("class",newClass);
	jQuery("#t_jtqk").toggle();
}

/**
 * 加载困难生认定历史记录
 * @param obj
 * @return
 */
function showLsjl(obj){
	var className = jQuery(obj).attr("class");
	var newClass = className == "up" ? "down" : "up";

	jQuery(obj).attr("class",newClass);
	jQuery("#t_rdlsjl").toggle();
}

/**
 * 保存困难生申请
 * @return
 */
function saveKnssq(saveType){

	if (!checkMustNotNull() || jQuery("#xh").val() == ""){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}
	
	var shzt = jQuery("#shzt").val();
	var isopen = jQuery("#isopen").val();
	
	if(isopen==null||isopen==''){
		showAlertDivLayer('基础设置未初始化，请联系管理员！');
		return false;
	}
	if ("false" == isopen && '3'!=shzt){
		showAlertDivLayer("当前未开放困难生申请，请联系管理员！");
		return false;
	}
	
	
	//是否弹出家庭情况调查填写页面
	var openJtqk = jQuery("#openJtqk").val();

	if ("true" == openJtqk){
		var xh = jQuery("#xh").val();

		showAlertDivLayer("请先填写家庭情况调查表！",{},{"clkFun":function(){
			editJtqk();
		}});
		return false;
	}
	
    var sqsftxdc=jQuery("#sqsftxdc").val();
    var xxdm=jQuery("#xxdm").val();
	if(sqsftxdc=='1'&& (xxdm=='12861' || xxdm=='10718')){
		if(null==jQuery("#ylzd1").val()||""==jQuery("#ylzd1").val()){
			showAlertDivLayer("困难档次不能为空,请选择！");
			return false;
		}
	}
	if(xxdm=='12861'){
	   if(jQuery(".MultiFile-label").length<=0){
			showAlertDivLayer("请上传附件！");
			return false;
	   }
	}
	var shzt = jQuery("#shzt").val();
	var isopen = jQuery("#isopen").val();
	
	if("submit"== saveType && "3"!=shzt && "false" == isopen){
		showAlertDivLayer("当前未开放困难生申请，请联系管理员！");
		return false;
	}
	

	var url = "xszz_knsrdbjpy.do?method=saveKnssq&type="+saveType+"&shzt="+shzt;

	ajaxSubFormWithFun("knsrdbjpyForm",url,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			if("save"==saveType){
				showAlertDivLayer("已保存成功，需提交后才生效！",{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
				}});
			}
			else{
				if (parent.window){
					refershParent();
				}
				
			}
			
		}});
	});
}

/**
 * 困难生申请
 * @return
 */
function knssq(){
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("false" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}
	
	var sfysq = jQuery("#sfysq").val();
	
	if ("true" == sfysq){
		showAlertDivLayer("当前周期已有申请记录，无需重复申请");
		return false;
	}
	showDialog('困难生认定申请',780,520,'xszz_knsrdbjpy.do?method=knssq');
}


/**
 * 困难生申请管理--修改
 * @return
 */
function knssqUpdate(){
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var shzt = rows[0]["shzt"];
		if ("0" != shzt&&"3" != shzt){
			showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
			return false;
		}
		showDialog("困难生认定申请修改",780,450,"xszz_knsrdbjpy.do?method=knssq&type=update&xh="+rows[0]["xh"]+"&xn="+rows[0]["xn"]+"&xq="+rows[0]["xq"]);
	}
}

/**
 * 困难生申请管理--删除
 * @return
 */
function knssqDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的申请记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'&&rows[i]['shzt']!='3'){
				showAlertDivLayer(jQuery("#lable_wjt_sc").val());
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要删除该申请吗？",{"okFun":function(){
			jQuery.post("xszz_knsrdbjpy.do?method=delKnssq",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


function cancle(){
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("false" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要撤销的记录！");
	} else if (ids.length >1 ) {
		showAlertDivLayer("请选择一条您要撤销的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='6'){
				showAlertDivLayer("只有班级评议中的记录才能被撤销！");
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要撤销选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("xszz_knsrdbjpy.do?method=cancle", {
					values : ids.toString(),
					lcid : rows[0]['shlc']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

function submitBusi(){
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1){
		if ("false" == isopen){
			showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
			return false;
		}
		showAlertDivLayer(jQuery("#lable_one_tj").val());
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		if ('3'!=rows[0]["shzt"] && "false" == isopen){
			showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
			return false;
		}
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'&&rows[i]['shzt']!='3'){
				showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要提交选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("xszz_knsrdbjpy.do?method=subBusi", {
					values : ids.toString(),
					lcid : rows[0]['shlc'],
					xh : rows[0]['xh'],
					shzt : rows[0]['shzt']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

/**
 * 困难生申请管理--流程跟踪
 * @return
 */
function knssqLcinfo(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条记录！");
	} else {
		if(rows[0]["shzt"]=="0" || rows[0]["shzt"]=="6" || rows[0]["shzt"]=="7"){
			showAlertDivLayer(jQuery("#lable_wxglcxx").val());
			return false;
		}
		showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['guid']+"&splc="+rows[0]['shlc']);
	}
}

/**
 * 困难生审核--待处理、已处理面签切换
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
 * 困难生认定--审核
 * @return
 */
function knsrdbjpySh(){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var flag=true;
    
	if (rows.length == 0){
		showAlertDivLayer("请选择您要审核记录！");
	} else if (rows.length == 1){
		showDialog("困难生认定审核",750,500,"xszz_knsrdbjpy.do?method=knsrdbjpyDgsh&guid="+rows[0]["guid"]+"&xtgwid="+rows[0]["xtgwid"]+"&shid="+rows[0]["shid"]+"&shlc="+rows[0]["shlc"]);
	} else {
		for ( var i = 1; i < rows.length; i++) {
			if(rows[i]["sjdcmc"]!=rows[i-1]["sjdcmc"]){
				flag=false;
				break;
				
			}
		}
		if(!flag){
			showAlertDivLayer("请选择相同档次的困难生！");
			return false;
		}
		showDialog("批量审核",500,300,"xszz_knsrdbjpy.do?method=knsrdbjpyPlsh");
	}
}

/**
 * 撤消审核
 * @return
 */
function cancelKnssh(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要撤消的审核记录！");
	} else {
		
		//最后一级撤销审核后调用的路径
		var cancelPath = "xszz_knsrdbjpy.do?method=cancelKnssh";
		confirmInfo("您确定要撤销操作吗?",function(ty){
			if(ty=="ok"){
				jQuery.post("comm_spl.do?method=cxshnew",{shlc:rows[0]["shlc"],shid:rows[0]["shid"]},function(data){
						// 判断是否最后一级撤销(1:最后一级撤销成功）
						if("1" == data["cancelFlg"]){
							jQuery.post(cancelPath,{guid:rows[0]["guid"]},function(result){
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
		/*
		showConfirmDivLayer("您确定要撤消对该申请记录的审核操作吗？",{"okFun":function(){
			jQuery.post("xszz_knsrdbjpy.do?method=cancelKnssh",
				{
				 guid:rows[0]["guid"],
				 xtgwid:rows[0]["xtgwid"]
				},
				function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},
			'json');
		}});
		*/
	}
}

/**
 * 批量审核保存
 * @param shzt
 * @return
 */
function savePlsh(shzt,rddc,ylzd3,shyj){
	
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
		"xszz_knsrdbjpy.do?method=savePlsh",
		{
		 shzt:shzt,
		 id:guid,
		 gwid:gwid,
		 xhs:xhs,
		 rddc:rddc,
		 ylzd3:ylzd3,
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
 * @param shzt
 * @param message
 * @return
 */
function saveKnssh(){
	var shzt = jQuery("#shjg").val();

	if ("1" == shzt && jQuery("#rddc").val() == ""){
		showAlertDivLayer("请选择推荐档次！");
		return false;
	}
	if("10335"==jQuery("#xxdm").val()&&(jQuery("#ylzd3").val() == "")){
		showAlertDivLayer("请选择无偿资助金额！");
		return false;
	}

	if (jQuery("#shyj").val() == ""){
		showAlertDivLayer("请填写审核意见！");
		return false;
	}
	
	showConfirmDivLayer("您确定审核该申请吗？",{"okFun":function(){
		var url = "xszz_knsrdbjpy.do?method=saveKnssh";
		ajaxSubFormWithFun("knsrdbjpyForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
	}});
}

//打印报表
function printKnssq(url){

	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <=0) {
		showAlertDivLayer("请选择一条记录！");
	} else {
		var guid = jQuery("#dataTable").getSeletIds();
		var url = url + "&guid=" +guid;
		window.open(url);
	}
}

/**
 * 刷新困难生认定申请界面
 * @return
 */
function reloadWindow(){
	var xh = jQuery("#xh").val();
	document.location.href="xszz_knsrdbjpy.do?method=knssq&xh="+xh;
}

/**
 * 困难生认定申请界面编辑家庭情况
 * @return
 */
function editJtqk(){
	var xh = jQuery("#xh").val();
	showDialog('家庭情况调查',780,500,'xszz_jtqkdc.do?method=dcxxModify&writeAble=yes&type=update&xh='+xh,{
		close:function(){
			reloadWindow();
		}
	});
}

/**
 * 学号链接
 * @param cellValue
 * @param rowObject
 * @return
 */
function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='knsrdbjpyView(\""+rowObject["guid"]+"\");'>"+cellValue+"</a>";
}

/**
 * 困难生认定查询
 * @param guid
 * @return
 */
function knsrdbjpyView(guid){
	showDialog('困难生认定申请',780,520,'xszz_knsrdbjpy.do?method=knsrdbjpyView&guid='+guid);
}

/**
 * 审核统计
 */
function knsrdbjpyShqk(){
	showDialog("困难生认定审核统计情况",470,300,"xszz_knsrdbjpy.do?method=knsrdbjpyShqk",{max:false,min:false});
}



//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, exportData);
}

//导出方法
function exportData() {
	setSearchTj();//设置高级查询条件
	var url = "xszz_knsrdbjpy.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//自定义导出功能（审核）
function exportConfigSh() {	
	var DCCLBH = "xszz_knsrdbjpy_sh.do";//dcclbh,导出功能编号，执行导出函数 
	customExport(DCCLBH, exportDataSh);
}

//导出方法（审核）
function exportDataSh() {
	var shlx = jQuery("#shzt").val();
	setSearchTj();//设置高级查询条件
	var DCCLBH = "xszz_knsrdbjpy_sh.do";
	var url = "xszz_knsrdbjpy.do?method=exportDataSh&dcclbh=" + DCCLBH + "&shlx=" + shlx;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
