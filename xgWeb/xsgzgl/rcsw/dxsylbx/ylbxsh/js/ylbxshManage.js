var gridSetting = {
		caption:"医疗保险待审核",
		pager:"pager",
		url:"rcsw_dxsylbx_ylbxshgl.do?method=ylbxshManage&type=query",
		colList:[
			{label:'key',name:'ylsqid', index: 'ylsqid',key:true ,hidden:true},
			{label:'审批流程',name:'splc', index: 'splc',hidden:true},
			{label:'学号',name:'xh', index: 'xh',width:'14%',formatter:xhLink},
			{label:'姓名',name:'xm', index: 'xm',width:'12%'},
			{label:'性别',name:'xb', index: 'xb',width:'9%'},
			{label:'班级',name:'bjmc', index: 'bjdm',width:'22%'},
			{label:'学年',name:'xn', index: 'xn',width:'12%'},
			{label:'学期',name:'xqmc', index: 'xqmc',width:'11%'},
//			{label:'参保状况',name:'cbzkmc', index: 'cbzkmc',width:'6%',formatter:cbzkLink},
			{label:'审核状态',name:'shztmc', index: 'shztmc',width:'20%'},
			{label:'审核状态名称',name:'shzt', index: 'shzt',hidden:true},
			{label:'参保状况代码',name:'cbzkdm', index: 'cbzkdm',hidden:true},
			{label:'补助代码',name:'czqebzdm', index: 'czqebzdm',hidden:true},
			{label:'学期',name:'xq', index: 'xq',hidden:true},
			{label:'审核Id',name:'shid', index: 'shid',hidden:true},
			{lable:'审批流程Id',name:'splc', index:'splc',hidden:true},
			{label:'岗位Id',name:'gwid', index: 'gwid',hidden:true}
		],
		params:{shzt:"dsh"},//默认待审核
		sortname: "cbsj",
	 	sortorder: "desc",
	 	radioselect:false
}
//{label:'参保时间',name:'cbsj', index: 'cbsj',width:'8%'},
var gridSetting2 = {
		caption:"医疗保险已审核",
		pager:"pager",
		url:"rcsw_dxsylbx_ylbxshgl.do?method=ylbxshManage&type=query",
		colList:[
			{label:'key',name:'ylsqid', index: 'ylsqid',key:true ,hidden:true},
			{label:'审批流程',name:'splc', index: 'splc',hidden:true},
			{label:'学号',name:'xh', index: 'xh',width:'15%',formatter:xhLink},
			{label:'姓名',name:'xm', index: 'xm',width:'9%'},
			{label:'性别',name:'xb', index: 'xb',width:'6%'},
			{label:'班级',name:'bjmc', index: 'bjdm',width:'17%'},
			{label:'学年',name:'xn', index: 'xn',width:'9%'},
			{label:'学期',name:'xqmc', index: 'xqmc',width:'8%'},
			{label:'参保状况',name:'cbzkmc', index: 'cbzkmc',width:'9%',formatter:cbzkLink},
			{label:'参保时间',name:'cbsj', index: 'cbsj',width:'11%'},
			{label:'审核状态',name:'shztmc', index: 'shztmc',width:'16%'},
			{label:'审核状态名称',name:'shzt', index: 'shzt',hidden:true},
			{label:'参保状况代码',name:'cbzkdm', index: 'cbzkdm',hidden:true},
			{label:'补助代码',name:'czqebzdm', index: 'czqebzdm',hidden:true},
			{label:'学期',name:'xq', index: 'xq',hidden:true},
			{label:'审核Id',name:'shid', index: 'shid',hidden:true}
		],
		params:{shzt:"ysh"},//默认待审核
		sortname: "cbsj",
	 	sortorder: "desc",
	 	radioselect:true
}
	
jQuery(function(){
	jQuery("#dataTable").initGrid(gridSetting);
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
 * 医疗保险审核
 * @return
 */
function ylbxSh(){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = jQuery("#shzt").val();
	if(shzt=="ysh"){
		showAlertDivLayer("已处理记录不能再次审核");
		return false;
	} else if(rows.length == 0){
		showAlertDivLayer("请选择一条您要审核的记录！");
		return false;
	} else if (rows.length == 1){
		var url = "rcsw_dxsylbx_ylbxshgl.do?method=ylbxDgsh&ylsqid="+rows[0]["ylsqid"]+'&xh=' + rows[0]["xh"]+'&shid=' +rows[0]["shid"] ;
		showDialog("医疗保险审核",700,500,url);
	} else{
		showDialog("医疗保险批量审核",500,300,"rcsw_dxsylbx_ylbxshgl.do?method=ylbxPlsh");
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
		guid.push(row["ylsqid"]);
		gwid.push(row["gwid"]);
		xhs.push(row["xh"]);
		splc.push(row["splc"]);
	});

	jQuery.post(
		"rcsw_dxsylbx_ylbxshgl.do?method=ylbxPlsh&type=save",
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

function ylbxshLcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {		
		showDialog("学生证补办审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['ylsqid']+"&splc="+rows[0]['splc']);
	}
}

function cancelShnew(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要撤消的审核记录！");
	} else {
		var splc = rows[0]["splc"];
		var shid = rows[0]["shid"];
		var ylsqid = rows[0]["ylsqid"];
		showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
			jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
				// 判断是否最后一级撤销(1:最后一级撤销成功）
				if("1" == data["cancelFlg"]){
					jQuery.post("rcsw_dxsylbx_ylbxshgl.do?method=cancelYlbxsh",{ylsqid:ylsqid},function(result){
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

function viewYlbxsh(ylsqid, xh) {
	showDialog("医疗保险审核查看", 700, 480, "rcsw_dxsylbx_ylbxshgl.do?method=viewYlbxsh&ylsqid=" + ylsqid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewYlbxsh(\""
			+ rowObject["ylsqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function viewCbzk(ylsqid,xm) {
	showDialog("参保状况查看", 450, 220, "rcsw_dxsylbx_ylbxshgl.do?method=viewCbzk&ylsqid="+ylsqid+"&xm="+xm);
}

function cbzkLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewCbzk(\""
			+ rowObject["ylsqid"] + "\",\""+ rowObject["xm"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

var DCCLBH = "rcsw_dxsylbx_ylbxsh.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, ylbxshExportData);
}

// 导出方法
function ylbxshExportData() {
	var shlx = jQuery("#shzt").val();
	setSearchTj();//设置高级查询条件
	var url = "rcsw_dxsylbx_ylbxshgl.do?method=exportData&dcclbh=" + DCCLBH + "&shlx=" + shlx;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}