var xxdm;

var gridSetting = {
			caption:"日常行为审核",
		pager:"pager",
		url:"rcsw_rcxwwh_rcxwshgl.do?method=rcxwshManage&type=query",
		colList:[
		   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
		   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
		   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
		   {label:'性别',name:'xb', index: 'xb',width:'5%'},
		   {label:'班级',name:'bjmc', index: 'bjdm',width:'12%'},
		   {label:'行为大类',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'12%'},
		   {label:'行为类别',name:'rcxwlbmc', index: 'rcxwlbmc',width:'12%'},
		   {label:'发生时间',name:'fssj', index: 'fssj',width:'12%'},
		   {label:'申请评定分值',name:'fz', index: 'fz',width:'8%'},
		   {label:'给分理由',name:'gfly', index: 'gfly',width:'8%',formatter:gflyText},
		   {label:'审核状态代码',name:'shzt', index: 'shzt' ,hidden:true},
		   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'14%'},
		   {label:'行为大类代码',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
		   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
		   {label:'审核Id',name:'shid', index: 'shid',hidden:true}
		],
		params:{shzt:"dsh"},//默认待审核
		sortname: "rcxwjlsj",
	 	sortorder: "desc"
	}
var gridSetting2 = {
		caption:"日常行为审核",
		pager:"pager",
		url:"rcsw_rcxwwh_rcxwshgl.do?method=rcxwshManage&type=query",
		colList:[
		   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
		   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
		   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
		   {label:'性别',name:'xb', index: 'xb',width:'5%'},
		   {label:'班级',name:'bjmc', index: 'bjdm',width:'12%'},
		   {label:'行为大类',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'12%'},
		   {label:'行为类别',name:'rcxwlbmc', index: 'rcxwlbmc',width:'12%'},
		   {label:'发生时间',name:'fssj', index: 'fssj',width:'12%'},
		   {label:'申请评定分值',name:'fz', index: 'fz',width:'8%'},
		   {label:'给分理由',name:'gfly', index: 'gfly',width:'8%',formatter:gflyText},
		   {label:'审核状态代码',name:'shzt', index: 'shzt' ,hidden:true},
		   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'14%'},
		   {label:'岗位id',name:'gwid', index: 'gwid',hidden:true},
		   {label:'审核人',name:'shr', index: 'shr',hidden:true},
		   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
		   {label:'审核Id',name:'shid', index: 'shid',hidden:true}
		],
		params:{shzt:"ysh"},//默认待审核
		sortname: "rcxwjlsj",
	 	sortorder: "desc"
	}
var gridSetting1 = {
    caption:"加分申请审核",
    pager:"pager",
    url:"rcsw_rcxwwh_rcxwshgl.do?method=rcxwshManage&type=query",
    colList:[
        {label:'key',name:'id', index: 'id',key:true ,hidden:true},
        {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
        {label:'姓名',name:'xm', index: 'xm',width:'10%'},
        {label:'性别',name:'xb', index: 'xb',width:'5%'},
        {label:'班级',name:'bjmc', index: 'bjdm',width:'12%'},
        {label:'加分大类',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'12%'},
        {label:'加分类别',name:'rcxwlbmc', index: 'rcxwlbmc',width:'12%'},
        {label:'发生时间',name:'fssj', index: 'fssj',width:'12%'},
        {label:'申请评定分值',name:'fz', index: 'fz',width:'8%'},
        {label:'给分理由',name:'gfly', index: 'gfly',width:'8%',formatter:gflyText},
        {label:'审核状态代码',name:'shzt', index: 'shzt' ,hidden:true},
        {label:'审核状态',name:'shztmc', index: 'shztmc',width:'14%'},
        {label:'加分大类代码',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
        {label:'审批流程',name:'splc', index: 'splc',hidden:true},
        {label:'审核Id',name:'shid', index: 'shid',hidden:true}
    ],
    params:{shzt:"dsh"},//默认待审核
    sortname: "rcxwjlsj",
    sortorder: "desc"
}
jQuery(function(){
	xxdm = jQuery("#xxdm").val();
	if("13431" == xxdm){
        jQuery("#dataTable").initGrid(gridSetting1);
	}else{
        jQuery("#dataTable").initGrid(gridSetting);
	}
});
	
function gflyText(cellValue, rowObject){
	var cellValueNotNull = cellValue ? cellValue : "";
	var cellValueNew = cellValueNotNull.length > 10 ? cellValue.substring(0,10)+"..." : cellValueNotNull;
	return "<span title='"+cellValueNotNull+"' >" + cellValueNew + "</span>";
}


function searchRs(){
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	if (shzt != ""){
		map["shzt"] = shzt;
	}
	jQuery("#dataTable").reloadGrid(map);
}
	
	
	
	////////////////////////////////////
/**
 * 日常行为审核--待处理、已处理面签切换
 * @param obj
 * @param shzt
 * @return
 */
function selectTab(obj,shzt){
	jQuery("#shzt").val(shzt);

	if (shzt == "dsh"){
		jQuery("#li_sh").css("display","");
		jQuery("#li_plsh").css("display","");
		jQuery("#li_qx").css("display","none");
		
		jQuery("#dataTable").initGrid(gridSetting);
	} else {			
		jQuery("#li_sh").css("display","none");
		jQuery("#li_plsh").css("display","none");
		jQuery("#li_qx").css("display","");
		
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	
	searchRs();
}

/**
 * 日常行为审核
 * @return
 */
function rcxwSh(){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = jQuery("#shzt").val();
	if(shzt=="ysh"){
		showAlertDivLayer("已处理记录不能再次审核");
		return false;
	}
	else if (rows.length == 0){
		showAlertDivLayer("请选择一条您要审核的记录！");
	}else if(rows.length == 1 ){
		var url = "rcsw_rcxwwh_rcxwshgl.do?method=rcxwDgsh&id="+rows[0]["id"]+'&xh=' + rows[0]["xh"]+'&rcxwlbdldm=' + rows[0]["rcxwlbdldm"] + '&shid=' +rows[0]["shid"] ;
		var title = "日常行为审核";
		if(xxdm=="13815"){
			title = "素质学分审核";
		}
        if(xxdm=="13431"){
            title = "加分申请审核";
        }
		showDialog(title,700,500,url);
	} else {
		plsh();
	}
}


function rcxwshLcinfo(){
	
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {		
		showDialog("审批流程跟踪",600,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['id']+"&splc="+rows[0]['splc']);
	}
}

function cancelSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要撤消的审核记录！");
	} else {
		
		showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
			jQuery.post("rcsw_rcxwwh_rcxwshgl.do?method=cancelRcxwsh",
				{
				 id:rows[0]["id"],
				 gwid:rows[0]["gwid"],
				 shr:rows[0]["shr"],
				 splc:rows[0]["splc"]
				},
				function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},
			'json');
		}});
	}
}

function xwxxView(id, xh) {
	var title = "日常行为审核信息查看";
	if(xxdm=="13815"){
		title = "素质学分审核信息查看";
	}
    if(xxdm=="13815"){
        title = "加分申请审核信息查看";
    }
	showDialog(title, 700, 480, "rcsw_rcxwwh_rcxwshgl.do?method=viewXwsh&id=" + id
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='xwxxView(\""
			+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}
var DCCLBH = "rcsw_rcxwwh_rcxwshgl.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, rcxwxshExportData);
}

// 导出方法
function rcxwxshExportData() {
	var shlx = jQuery("#shzt").val();
	setSearchTj();//设置高级查询条件
	var url = "rcsw_rcxwwh_rcxwshgl.do?method=exportData&dcclbh=" + DCCLBH + "&shlx=" + shlx;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}