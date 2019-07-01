var gridSetting = {
			caption:"火车乘车区间审核",
			pager:"pager",
			url:"rcsw_hcyhk_hcccqjshgl.do?method=hcccqjshManage&type=query",
			colList:[
				{label:'key',name:'ccqjtxid', index: 'ccqjtxid',key:true,hidden:true },
				{label:'审批流程',name:'splc', index: 'splc',hidden:true},
				{label:'学号',name:'xh', index: 'xh',width:'6%',formatter:xhLink},
				{label:'姓名',name:'xm', index: 'xm',width:'6%'},
				{label:'性别',name:'xb', index: 'xb',width:'3%'},
				{label:'行政班级',name:'bjmc', index: 'bjdm',width:'8%'},
                {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'8%'},
				{label:'火车乘车区间',name:'hcccqjmc', index: 'hcccqjmc',width:'12%'},
				{label:'填写时间',name:'txsj', index: 'txsj',width:'10%'},
				{label:'审核状态',name:'shztmc', index: 'shztmc',width:'10%'},
				{label:'审核状态名称',name:'shzt', index: 'shzt',hidden:true},
				{label:'审核Id',name:'shid', index: 'shid',hidden:true},
				{label:'岗位Id',name:'gwid', index: 'gwid',hidden:true},
				{label:'审批流程',name:'splc',index:'splc',hidden:true}
			],
			params:{shzt:"dsh"},//默认待审核
			sortname: "txsj",
		 	sortorder: "desc",
		 	radioselect:false
}

var gridSetting2 = {
		caption:"火车乘车区间审核",
		pager:"pager",
		url:"rcsw_hcyhk_hcccqjshgl.do?method=hcccqjshManage&type=query",
		colList:[
			{label:'key',name:'ccqjtxid', index: 'ccqjtxid',key:true ,hidden:true},
			{label:'审批流程',name:'splc', index: 'splc',hidden:true},
			{label:'学号',name:'xh', index: 'xh',width:'6%',formatter:xhLink},
			{label:'姓名',name:'xm', index: 'xm',width:'6%'},
			{label:'性别',name:'xb', index: 'xb',width:'3%'},
            {label:'行政班级',name:'bjmc', index: 'bjdm',width:'8%'},
            {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'8%'},
			{label:'火车乘车区间',name:'hcccqjmc', index: 'hcccqjmc',width:'12%'},
			{label:'填写时间',name:'txsj', index: 'txsj',width:'10%'},
			{label:'审核状态',name:'shztmc', index: 'shztmc',width:'10%'},
			{label:'审核状态名称',name:'shzt', index: 'shzt',hidden:true},
			{label:'审核Id',name:'shid', index: 'shid',hidden:true}
		],
		params:{shzt:"ysh"},//默认待审核
		sortname: "txsj",
	 	sortorder: "desc",
	 	radioselect:true
}

var gridSetting3 = {
		caption:"火车乘车区间审核",
		pager:"pager",
		url:"rcsw_hcyhk_hcccqjshgl.do?method=hcccqjshManage&type=query",
		colList:[
			{label:'key',name:'ccqjtxid', index: 'ccqjtxid',key:true,hidden:true },
			{label:'审批流程',name:'splc', index: 'splc',hidden:true},
			{label:'学号',name:'xh', index: 'xh',width:'6%',formatter:xhLink},
			{label:'姓名',name:'xm', index: 'xm',width:'6%'},
			{label:'性别',name:'xb', index: 'xb',width:'3%'},
			{label:'班级',name:'bjmc', index: 'bjdm',width:'8%'},
			{label:'起始站',name:'ccqdz', index: 'ccqdz',width:'8%'},
			{label:'终点站',name:'cczdz', index: 'cczdz',width:'8%'},
			{label:'填写时间',name:'txsj', index: 'txsj',width:'10%'},
			{label:'审核状态',name:'shztmc', index: 'shztmc',width:'10%'},
			{label:'审核状态名称',name:'shzt', index: 'shzt',hidden:true},
			{label:'审核Id',name:'shid', index: 'shid',hidden:true},
			{label:'岗位Id',name:'gwid', index: 'gwid',hidden:true},
			{label:'审批流程',name:'splc',index:'splc',hidden:true}
		],
		params:{shzt:"dsh"},//默认待审核
		sortname: "txsj",
	 	sortorder: "desc",
	 	radioselect:false
}

var gridSetting4 = {
		caption:"火车乘车区间审核",
		pager:"pager",
		url:"rcsw_hcyhk_hcccqjshgl.do?method=hcccqjshManage&type=query",
		colList:[
			{label:'key',name:'ccqjtxid', index: 'ccqjtxid',key:true ,hidden:true},
			{label:'审批流程',name:'splc', index: 'splc',hidden:true},
			{label:'学号',name:'xh', index: 'xh',width:'6%',formatter:xhLink},
			{label:'姓名',name:'xm', index: 'xm',width:'6%'},
			{label:'性别',name:'xb', index: 'xb',width:'3%'},
			{label:'班级',name:'bjmc', index: 'bjdm',width:'8%'},
			{label:'起始站',name:'ccqdz', index: 'ccqdz',width:'8%'},
			{label:'终点站',name:'cczdz', index: 'cczdz',width:'8%'},
			{label:'填写时间',name:'txsj', index: 'txsj',width:'10%'},
			{label:'审核状态',name:'shztmc', index: 'shztmc',width:'10%'},
			{label:'审核状态名称',name:'shzt', index: 'shzt',hidden:true},
			{label:'审核Id',name:'shid', index: 'shid',hidden:true}
		],
		params:{shzt:"ysh"},//默认待审核
		sortname: "txsj",
	 	sortorder: "desc",
	 	radioselect:true
}
	
jQuery(function(){
	var xxdm = jQuery("#xxdm").val();
	if("11318" == xxdm) {
		jQuery("#dataTable").initGrid(gridSetting3);
	}else {
		jQuery("#dataTable").initGrid(gridSetting);
	}
});
	
function searchRs(){
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	if (shzt != ""){
		map["shzt"] = shzt;
	}
	jQuery("#dataTable").reloadGrid(map);
}
	
	
/**
 * 学生证补办审核
 * @param obj
 * @param shzt
 * @return
 */
function selectTab(obj,shzt){
	jQuery("#shzt").val(shzt);
	var xxdm = jQuery("#xxdm").val();
	if (shzt == "dsh"){
		jQuery("#li_sh").css("display","");
		jQuery("#li_qx").css("display","none");
		if("11318" == xxdm) {
			jQuery("#dataTable").initGrid(gridSetting3);
		}else {
			jQuery("#dataTable").initGrid(gridSetting);
		}
	} else {			
		jQuery("#li_sh").css("display","none");
		jQuery("#li_qx").css("display","");
		if("11318" == xxdm) {
			jQuery("#dataTable").initGrid(gridSetting4);
		}else {
			jQuery("#dataTable").initGrid(gridSetting2);
		}
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	searchRs();
}

/**
 * 学生证补办审核
 * @return
 */
function hcccqjSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = jQuery("#shzt").val();
	if(shzt=="ysh"){
		showAlertDivLayer("已处理记录不能再次审核");
		return false;
	}else if (rows.length == 0){
		showAlertDivLayer("请选择您要审核的记录");
		return false;
	}else if (rows.length == 1){
		var url = "rcsw_hcyhk_hcccqjshgl.do?method=hcccqjDgsh&ccqjtxid="+rows[0]["ccqjtxid"]+'&xh=' + rows[0]["xh"]+'&shid=' +rows[0]["shid"] ;
		showDialog("火车乘车区间审核",700,500,url);
	} else{
		showDialog("火车乘车区间批量审核",500,300,"rcsw_hcyhk_hcccqjshgl.do?method=hcccPlsh");
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
		guid.push(row["ccqjtxid"]);
		gwid.push(row["gwid"]);
		xhs.push(row["xh"]);
		splc.push(row["splc"]);
	});

	jQuery.post(
		"rcsw_hcyhk_hcccqjshgl.do?method=hcccPlsh&type=save",
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


function hcccqjshLcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {		
		showDialog("学生证补办审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['ccqjtxid']+"&splc="+rows[0]['splc']);
	}
}

function cancelShnew(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要撤消的审核记录！");
	} else {
		var splc = rows[0]["splc"];
		var shid = rows[0]["shid"];
		var ccqjtxid = rows[0]["ccqjtxid"];
		var shzt = rows[0]["shzt"];
		showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
			jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
				// 判断是否最后一级撤销(1:最后一级撤销成功）
				if("1" == data["cancelFlg"]){
					jQuery.post("rcsw_hcyhk_hcccqjshgl.do?method=cancelHcccqjsh",{ccqjtxid:ccqjtxid,shzt:shzt},function(result){
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
		}});
	}
}

function viewHcccqjsh(ccqjtxid, xh) {
	showDialog("学生证补办审核查询", 700, 480, "rcsw_hcyhk_hcccqjshgl.do?method=viewHcccqjsh&ccqjtxid=" + ccqjtxid+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewHcccqjsh(\""
			+ rowObject["ccqjtxid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}


var DCCLBH = "rcsw_hcyhk_hcccqjsh.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, rcxwxshExportData);
}

// 导出方法
function rcxwxshExportData() {
	var shlx = jQuery("#shzt").val();
	setSearchTj();//设置高级查询条件
	var url = "rcsw_hcyhk_hcccqjshgl.do?method=exportData&dcclbh=" + DCCLBH + "&shlx=" + shlx;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}