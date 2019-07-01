//奖项审核

var shzt='0';
/**
 * 获取待处理表格参数
 * @returns {___anonymous54_1173}
 */
function getDclGird(){
	
	var colList = [
	   {label:'年级',name:'nj', index: 'nj',width:'14%'},
	   {label:jQuery("#xbmc").val(),name:'xymc', index: 'xymc',width:'20%'},
	   {label:'专业',name:'zymc', index: 'zymc',width:'20%'},
	   {label:'班级',name:'bjmc', index: 'bjmc',width:'20%'},
	   {label:'bjdm',name:'bjdm', index: 'bjdm',hidden:true},
	   {label:'班级总人数',name:'bjrs', index: 'bjrs',width:'13%'}
	   
	];
		
	return {
		caption:"申请学生列表 ",
		pager:"pager",
		url:"xpj_sqsh.do?method=viewJxsqList_new&type=query",
		colList:colList,
		sortname:"nj",
		sortorder:"asc",
		radioselect:false
	};
}


/**
 * 显示申请页面
 */
function viewJxsq(){
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length ==0){
		showAlertDivLayer("请先勾选需要上报的班级！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		var xn = rows[0]["xn"];
		var xq = rows[0]["xq"];
		var result = true;
		var bjdms = "";
		for(var i=0;i<ids.length;i++){
			bjdms+=rows[i]["bjdm"]+",";
			if(rows[i]["xn"]!=xn&&rows[i]["xq"]!=xq){
				result = false;
			}
		}
		
		if(bjdms.length>0){
			bjdms = bjdms.substring(0, bjdms.length-1);
		}
		
		if(!result){
			showAlertDivLayer("请选择学年学期一致的记录！");
			return false;
		}
		//showDialog("奖项审核",700,500,"xpj_sqsh.do?method=toCheckPjpy&xn="+xn+"&xq="+xq+"&bjdms="+bjdms);
		//修改功能后，查询速度有点慢，加入【数据加载中】显示
		var tips = loading();	
		tips.show(true);
		refreshForm("xpj_sqsh.do?method=toCheckPjsq&bjdms="+bjdms);
	}
	
	
}


/**
 * 获取已处理表格参数
 * @returns {___anonymous1466_2571}
 */
function getYclGrid(){
	
	var colList = [
	   {label:'年级',name:'nj', index: 'nj',width:'14%'},
	   {label:jQuery("#xbmc").val(),name:'xymc', index: 'xymc',width:'20%'},
	   {label:'专业',name:'zymc', index: 'zymc',width:'20%'},
	   {label:'班级',name:'bjmc', index: 'bjmc',width:'20%'},
	   {label:'xn',name:'xn', index: 'xn',hidden:true},
	   {label:'xq',name:'xq', index: 'xq',hidden:true},
	   {label:'bjdm',name:'bjdm', index: 'bjdm',hidden:true},
	   {label:'班级总人数',name:'bjzrs', index: 'bjzrs',width:'13%'},
	   {label:'已审核人数',name:'gs', index: 'gs',width:'13%'}
	];
	
	
	return {
		caption:"申请学生列表 ",
		pager:"pager",
		url:"xpj_sqsh.do?method=viewJxplshList&type=query",
		colList:colList,
		sortname:"nj",
		sortorder:"asc",
		radioselect:false
	};
}
		

/**
 * 取消审核
 */
function cancelAuding(){
	
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要审核的记录！");
	} else {
		jQuery.post("xpj_sqsh.do?method=runCancel",{sqid:rows[0]["sqid"],shid:rows[0]["shid"]},function(data){
			showAlert(data["message"],{},{"clkFun":function(){
				var yclGrid = getYclGrid();
				jQuery("#dataTable").initGrid(yclGrid);
			}});
		},"json");
	}
	
	
	
}



/**
 * 切换已处理、未处理
 * @param obj
 * @param shzt
 */
function chageShzt(obj,shzt){
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	
	if (shzt=="0"){
		var dclGrid = getDclGird();
		var map = getSuperSearch();
			map["shzt"] = "0"; 
		dclGrid["params"] = map;
		jQuery("#dataTable").initGrid(dclGrid);
		
		jQuery("#li_qx").hide();
		jQuery("#li_sh").show();
		jQuery("#shzt").val("0");
	} else {
		var yclGrid = getYclGrid();
		var map = getSuperSearch();
			map["shzt"] = "1"; 
		yclGrid["params"] = map;
		jQuery("#dataTable").initGrid(yclGrid);
		
		jQuery("#li_qx").show();
		jQuery("#li_sh").hide();
		jQuery("#shzt").val("1");
	}
}


/**
 * 审核情况表格参数
 * @returns {___anonymous3504_3637}
 */
function getShqkGrid(){
	
	var tjdw = jQuery("#tjdw").val();
	var gridSetting = {
		caption:"列表 ",
		pager:"pager",
		url:"xpj_sqsh.do?method=viewShqkList&type=query",
		sortname:"dsrs",
		sortorder:"desc",
		radioselect:true
	};
	
	var colList;
	
	if ("bj" == tjdw){
		colList=[
		   {label:'bjdm',name:'bmdm', index: 'bmdm',key:true,hidden:true},
		   {label:'年级',name:'nj', index: 'nj',width:'10%'},
		   {label:jQuery("#xbmc").val(),name:'xymc', index: 'xydm',width:'20%'},
		   {label:'班级',name:'bjmc', index: 'bmdm',width:'20%'},
		   {label:'总人数',name:'bjrs', index: 'bjrs',width:'10%'},
		   {label:'申请/上报人数',name:'sqrs', index: 'sqrs',width:'15%'},
		   {label:'待审人数',name:'dsrs', index: 'dsrs',width:'10%'},
		   {label:'通过人数',name:'tgrs', index: 'tgrs',width:'10%'},
		   {label:'不通过人数',name:'btgrs', index: 'btgrs',width:'15%'}
		];
		
		
	} else if ("njzy" == tjdw){
		colList=[
		   {label:'bmdm',name:'bmdm', index: 'bmdm',key:true,hidden:true},
		   {label:'年级',name:'nj', index: 'nj',width:'10%'},
		   {label:'专业',name:'zymc', index: 'zydm',width:'20%'},
		   {label:'总人数',name:'zrs', index: 'zrs',width:'10%'},
		   {label:'申请/上报人数',name:'sqrs', index: 'sqrs',width:'15%'},
		   {label:'待审人数',name:'dsrs', index: 'dsrs',width:'10%'},
		   {label:'通过人数',name:'tgrs', index: 'tgrs',width:'10%'},
		   {label:'不通过人数',name:'btgrs', index: 'btgrs',width:'15%'}
		];
	} else {
		colList=[
		   {label:'bmdm',name:'bmdm', index: 'bmdm',key:true,hidden:true},
		   {label:'参评组',name:'cpzmc', index: 'bmdm',width:'20%'},
		   {label:'总人数',name:'cpzrs', index: 'cpzrs',width:'10%'},
		   {label:'申请/上报人数',name:'sqrs', index: 'sqrs',width:'15%'},
		   {label:'待审人数',name:'dsrs', index: 'dsrs',width:'10%'},
		   {label:'通过人数',name:'tgrs', index: 'tgrs',width:'10%'},
		   {label:'不通过人数',name:'btgrs', index: 'btgrs',width:'15%'}
		];
	}
	
	gridSetting["colList"] = colList;
	return gridSetting;
}



/**
 * 选择统计方式
 */
function showSeletTjdw(){
	showDialog("选择统计方式",300,150,"xpj_sqsh.do?method=selectTjdw",{max:false,min:false});
}


/**
 * 审核详细页面
 */
function showAudingList(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要审核的记录！");
	} else {
		var tjdw = jQuery("#tjdw").val();
		var shid = jQuery("#shid").val();
		var id = rows[0]["bmdm"];
		
		showDialog("奖项审核",750,500,"xpj_sqsh.do?method=viewJxshList&tjdw="+tjdw+"&shid="+shid+"&bmdm="+id);
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
		var url = "xpj_sqsh.do?method=viewLcgz&sqid="+rows[0]["sqid"];
		showDialog("流程跟踪",550,450,url,{max:false,min:false});
	}
}


/**
 * 查询
 */
function searchRs(){
	var map = getSuperSearch();
	map["shzt"] = jQuery("#shzt").val();
	jQuery("#dataTable").reloadGrid(map);
}


/**
 * 审核情况统计 
 */
function viewShqk(){
	showDialog("评奖评优审核情况统计",500,400,"xpj_sqsh.do?method=pjxmShtj");
}




//导出
function exportConfig(){
	var DCCLBH='pj_jxsh.do';
	customExport(DCCLBH, exportData);
}
function exportData(){
	var shzt=jQuery("#shzt").val();
	var DCCLBH='pj_jxsh.do';
	
	setSearchTj();//设置高级查询条件
	
	var url = "xpj_sqsh.do?method=exportData&shzt="+shzt+"&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}