var xxdm;

var gridSetting = {
	caption:"日常行为信息维护列表",
	pager:"pager",
	url:"rcsw_rcxwwh_rcxwxxwhgl.do?method=rcxwxxwhManage&type=query",
	colList:[
	   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
	   {label:'splc',name:'splc', index: 'splc',hidden:true},
	   {label:'rcxwlbdm',name:'rcxwlbdm', index: 'rcxwlbdm',hidden:true},
	   {label:'学号',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
	   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
	   {label:'性别',name:'xb', index: 'xb',width:'5%'},
	   {label:'班级',name:'bjmc', index: 'bjdm',width:'13%'},
	   {label:'行为大类',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'8%'},
	   {label:'行为类别',name:'rcxwlbmc', index: 'rcxwlbmc',width:'8%'},
	   {label:'发生时间',name:'fssj', index: 'fssj',width:'15%'},
	   {label:'申请评定分值',name:'fz', index: 'fz',width:'12%'},
	   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'6%'},
	   {label:'行为大类代码',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
	   {label:'shzt',name:'shzt', index: 'shzt',hidden:true}
	],
	sortname: "rcxwjlsj",
 	sortorder: "desc"
}
var gridSetting1 = {
    caption:"加分申请信息维护列表",
    pager:"pager",
    url:"rcsw_rcxwwh_rcxwxxwhgl.do?method=rcxwxxwhManage&type=query",
    colList:[
        {label:'key',name:'id', index: 'id',key:true ,hidden:true},
        {label:'splc',name:'splc', index: 'splc',hidden:true},
        {label:'rcxwlbdm',name:'rcxwlbdm', index: 'rcxwlbdm',hidden:true},
        {label:'学号',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
        {label:'姓名',name:'xm', index: 'xm',width:'8%'},
        {label:'性别',name:'xb', index: 'xb',width:'5%'},
        {label:'班级',name:'bjmc', index: 'bjdm',width:'13%'},
        {label:'加分大类',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'8%'},
        {label:'加分类别',name:'rcxwlbmc', index: 'rcxwlbmc',width:'8%'},
        {label:'发生时间',name:'fssj', index: 'fssj',width:'15%'},
        {label:'申请评定分值',name:'fz', index: 'fz',width:'12%'},
        {label:'审核状态',name:'shztmc', index: 'shztmc',width:'6%'},
        {label:'加分大类代码',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
        {label:'shzt',name:'shzt', index: 'shzt',hidden:true}
    ],
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
	if(xxdm == "10504"){
		var userStatus = jQuery("#userStatus").val();
		if(userStatus == "stu"){
			jQuery("#btn_zj").hide();
			jQuery("#btn_xg").hide();
			jQuery("#btn_sc").hide();
		}else{
			jQuery("#btn_shuc").hide();
			jQuery("#btn_sr").hide();
		}
	}
});

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function add(){
	var url = "rcsw_rcxwwh_rcxwxxwhgl.do?method=addXwxx";
	var title = "增加日常行为信息";
	if(xxdm=="13815"){
		title = "增加素质学分信息";
	}
    if(xxdm=="13431"){
        title = "增加加分申请信息";
    }
	showDialog(title,950,450,url);
}



function update() {
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var shzt = rows[0]["shzt"];
		if(shzt=='0'||shzt=='3'){
			var url = 'rcsw_rcxwwh_rcxwxxwhgl.do?method=updateXwxx&id=' + rows[0]["id"]
      		+ '&xh=' + rows[0]["xh"] +'&rcxwlbdldm=' + rows[0]["rcxwlbdldm"] 
      		+ '&rcxwlbdlmc=' + rows[0]["rcxwlbdlmc"] +'&rcxwlbdlmc=' + rows[0]["rcxwlbmc"];
      		var title = "修改日常行为信息";
      		if(xxdm=="13815"){
      			title = "修改素质学分信息";
      		}
            if(xxdm=="13815"){
                title = "修改加分申请信息";
            }
      		showDialog(title, 850, 450, url);
		}else{
			showAlertDivLayer("只能修改'未提交'、'退回'的记录！");
			return false;
		}
	}
}

function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var shzt = rows[0]["shzt"];
		
		if(shzt=='0'){
			showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
				jQuery.post("rcsw_rcxwwh_rcxwxxwhgl.do?method=delXwxx",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}});
		}else{
			showAlertDivLayer("只能删除'未提交'的记录！");
			return false;
		}
	}
}


function xwxxView(id, xh) {
	var title = "日常行为信息查看";
	if(xxdm=="13815"){
			title = "素质学分信息查看";
		}
    if(xxdm=="13431"){
        title = "加分申请信息查看";
    }
	showDialog(title, 720, 520, "rcsw_rcxwwh_rcxwxxwhgl.do?method=viewXwxx&id=" + id
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='xwxxView(\""
			+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

var DCCLBH = "rcsw_rcxwwh_rcxwxxwhgl.do";//dcclbh,导出功能编号

/* 导入 */
function importXX(){
	toImportDataNew("IMPORT_N155102_RCXWXXWH");
	return false;
}

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, rcxwxxwhExportData);
}

// 导出方法
function rcxwxxwhExportData() {
	setSearchTj();//设置高级查询条件
	var url = "rcsw_rcxwwh_rcxwxxwhgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
function submitPl(){
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if (ids.length == 0){
				showAlertDivLayer("请选择您要提交的记录！");
				return false;
			}else {
				for(var i=0;i<rows.length;i++){
					if (rows[i]["shzt"] != "0"&&rows[i]["shzt"] != "3"){
						showAlertDivLayer("请选择未提交或者已退回的记录！");
						return false;
					}
				}
			showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
				var tips = submitLoading();
				try{ // 解决：先增加，关闭增加窗口，再提交时等待条无法关闭
					tips.show();
				}catch(e){
				}
				jQuery.post("rcsw_rcxwwh_rcxwxxwhgl.do?method=submitXwxx",{
					values:ids.toString()},function(data){
					tips.close();
					showAlertDivLayerSize(data["message"], 340, 200);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}});	
			}
		}
