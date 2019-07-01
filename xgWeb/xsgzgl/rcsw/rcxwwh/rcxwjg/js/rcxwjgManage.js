var xxdm;

jQuery(function() {
	xxdm = jQuery("#xxdm").val();
	var gridSetting = {
			caption:"日常行为结果列表",
	pager:"pager",
	params:getSuperSearch(),
	url:"rcsw_rcxwwh_rcxwjggl.do?method=rcxwjgManage&type=query",
	colList:[
	   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
	   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
	   {label:'姓名',name:'xm', index: 'xm',width:'7%'},
	   {label:'性别',name:'xb', index: 'xb',width:'5%'},
	   {label:jQuery("#xbmc").val(),name:'xymc', index: 'xymc',width:'12%'},
	   {label:'班级',name:'bjmc', index: 'bjmc',width:'12%'},
	   {label:'行为大类',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'10%'},
	   {label:'行为类别',name:'rcxwlbmc', index: 'rcxwlbmc',width:'10%'},
	   {label:'发生时间',name:'fssj', index: 'fssj',width:'10%'},
	   {label:'评定分值',name:'fz', index: 'fz',width:'8%'},
	   {label:'学年学期',name:'xnxq', index: 'xnxq',width:'15%'},
	   {label:'日常行为大类代码',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
	   {label:'行为类别代码',name:'rcxwlbdm', index: 'rcxwlbdm',hidden:true},
	   {label:'数据来源',name:'sjly', index: 'sjly',hidden:true}
	],
	sortname: "rcxwjlsj",
 	sortorder: "desc"
}
	var gridSetting1 = {
			caption:"日常行为结果列表",
	pager:"pager",
	params:getSuperSearch(),
	url:"rcsw_rcxwwh_rcxwjggl.do?method=rcxwjgManage&type=query",
	colList:[
	   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
	   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
	   {label:'姓名',name:'xm', index: 'xm',width:'7%'},
	   {label:'性别',name:'xb', index: 'xb',width:'5%'},
	   {label:jQuery("#xbmc").val(),name:'xymc', index: 'xymc',width:'12%'},
	   {label:'班级',name:'bjmc', index: 'bjmc',width:'12%'},
	   {label:'行为类别',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'10%'},
	   {label:'发生时间',name:'fssj', index: 'fssj',width:'10%'},
	   {label:'评定分值',name:'fz', index: 'fz',width:'8%'},
	   {label:'学年学期',name:'xnxq', index: 'xnxq',width:'15%'},
	   {label:'日常行为大类代码',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
	   {label:'行为类别代码',name:'rcxwlbdm', index: 'rcxwlbdm',hidden:true},
	   {label:'数据来源',name:'sjly', index: 'sjly',hidden:true}
	],
	sortname: "rcxwjlsj",
 	sortorder: "desc"
}
    var gridSetting2 = {
        caption:"加分申请结果列表",
        pager:"pager",
        params:getSuperSearch(),
        url:"rcsw_rcxwwh_rcxwjggl.do?method=rcxwjgManage&type=query",
        colList:[
            {label:'key',name:'id', index: 'id',key:true ,hidden:true},
            {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
            {label:'姓名',name:'xm', index: 'xm',width:'7%'},
            {label:'性别',name:'xb', index: 'xb',width:'5%'},
            {label:jQuery("#xbmc").val(),name:'xymc', index: 'xymc',width:'12%'},
            {label:'班级',name:'bjmc', index: 'bjmc',width:'12%'},
            {label:'加分大类',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'10%'},
            {label:'加分类别',name:'rcxwlbmc', index: 'rcxwlbmc',width:'10%'},
            {label:'发生时间',name:'fssj', index: 'fssj',width:'10%'},
            {label:'评定分值',name:'fz', index: 'fz',width:'8%'},
            {label:'学年学期',name:'xnxq', index: 'xnxq',width:'15%'},
            {label:'加分大类代码',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
            {label:'加分类别代码',name:'rcxwlbdm', index: 'rcxwlbdm',hidden:true},
            {label:'数据来源',name:'sjly', index: 'sjly',hidden:true}
        ],
        sortname: "rcxwjlsj",
        sortorder: "desc"
    }
	if(xxdm == '12867'){
		jQuery("#dataTable").initGrid(gridSetting1);
	}else if(xxdm == "13431"){
		jQuery("#dataTable").initGrid(gridSetting2);
	}else{
        jQuery("#dataTable").initGrid(gridSetting);
	}
	

});
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function add() {
	var url = "rcsw_rcxwwh_rcxwjggl.do?method=addXwjg";
	var title = "增加日常行为结果信息";
	if(xxdm=="13815"){
		title = "增加素质学分结果信息";
	}
    if(xxdm=="13431"){
        title = "增加加分结果信息";
    }
	showDialog(title, 950, 460, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	var sjly = rows[0]["sjly"];

	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else if (sjly == '1') {
		showAlertDivLayer("该条数据记录已走完审核流，不能修改！");
	} else {
		var url = 'rcsw_rcxwwh_rcxwjggl.do?method=updateXwjg&id='
				+ rows[0]["id"] + '&xh=' + rows[0]["xh"] + '&rcxwlbdldm='
				+ rows[0]["rcxwlbdldm"] + '&rcxwlbdm=' + rows[0]["rcxwlbdm"]
				+ '&xn=' + rows[0]["xn"] + '&xq=' + rows[0]["xq"];
		var title = "修改日常行为结果信息";
		if(xxdm=="13815"){
			title = "修改素质学分结果信息";
		}
        if(xxdm=="13431"){
            title = "修改加分结果信息";
        }
		showDialog(title, 720, 460, url);
	}

}

function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var sjly = rows[0]["sjly"];

	if (ids.length == 0) {
		alertInfo("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['sjly'] == '1') {
				showAlertDivLayer("流程数据不能删除！");
				return false;
			}
		}

		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("rcsw_rcxwwh_rcxwjggl.do?method=delXwjg", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}

function xwjgView(id, xh) {
	var title = "日常行为结果信息查看";
	if(xxdm=="13815"){
		title = "素质学分结果信息查看";
	}
    if(xxdm=="13431"){
        title = "加分结果信息查看";
    }
	showDialog(title, 720, 520,
			"rcsw_rcxwwh_rcxwjggl.do?method=viewXwjg&id=" + id + "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='xwjgView(\""
			+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

var DCCLBH = "rcsw_rcxwwh_rcxwjg.do";// dcclbh,导出功能编号

// 自定义导出 功能
function exportConfig() {
	// DCCLBH导出功能编号,执行导出函数
	customExport(DCCLBH, rcxwjgExportData);
}

// 导出方法
function rcxwjgExportData() {
	setSearchTj();// 设置高级查询条件
	var url = "rcsw_rcxwwh_rcxwjggl.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,导出功能编号
	url = addSuperSearchParams(url);// 设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//温大文明品行实践导出
function xsPxsjDc(){
	var url ="rcsw_rcxwwh_rcxwjggl.do?method=xsPxsjDc";
	var xqLength=jQuery("a[name=a_name_xq]").length;
	var xnLength=jQuery("a[name=a_name_xn]").length;
	var yqLength=jQuery("a[name=a_name_yqdm]").length;
	if(xqLength != "1"){
		showAlertDivLayer("请选择一个学期查询条件！");
		return false;
	}
	if(xnLength != "1"){
		showAlertDivLayer("请选择一个学年查询条件！");
		return false;
	}
	if(yqLength != "1"){
		showAlertDivLayer("请选择一个学区查询条件！");
		return false;
	}
	setSearchTj();
	url = addSuperSearchParams(url);
	document.forms[0].action = url;
	document.forms[0].submit();
	
}

function rcxwsjDc() {
	var url ="rcsw_rcxwwh_rcxwjggl.do?method=rcxwsjDc";
	var xnLength=jQuery("a[name=a_name_xn]").length;
	if(xnLength != "1"){
		showAlertDivLayer("请选择一个学年查询条件！");
		return false;
	}
	setSearchTj();
	url = addSuperSearchParams(url);
	document.forms[0].action = url;
	document.forms[0].submit();
}
//日常行为总分导出
function rcxwtjbDc() {
	var url ="rcsw_rcxwwh_rcxwjggl.do?method=rcxwtjbDc";
	var xnLength=jQuery("a[name=a_name_xn]").length;
	if(xnLength != "1"){
		showAlertDivLayer("请选择一个学年查询条件！");
		return false;
	}
	setSearchTj();
	url = addSuperSearchParams(url);
	document.forms[0].action = url;
	document.forms[0].submit();
}



// 导入方法
function importData() {
	toImportData("IMPORT_N730204");
	return false;
}

//导入
function importConfig(){
	toImportDataNew("IMPORT_RCSW_ZJLY");
	return false;
}

//青岛滨海学院思想品德课成绩汇总导出
function sxpdcjhzDc(){
	var url ="rcsw_rcxwwh_rcxwjggl.do?method=sxpdcjhzDc";
	
	var xnLength=jQuery("a[name=a_name_xn]").length;
	var xqLength=jQuery("a[name=a_name_xq]").length;
	var xyLength=jQuery("a[name=a_name_xy]").length;
	
	if(xnLength != "1"){
		showAlertDivLayer("请选择一个学年查询条件！");
		return false;
	}
	if(xqLength != "1"){
		showAlertDivLayer("请选择一个学期查询条件！");
		return false;
	}
	if(xyLength != "1"){
		showAlertDivLayer("请选择一个学院查询条件！");
		return false;
	}
	setSearchTj();
	url = addSuperSearchParams(url);
	document.forms[0].action = url;
	document.forms[0].submit();
}

/**
 * 浙江警官职业学院——个性化德育考评通知单
 * @return
 */
function printDykptzd(){
	/*选择的记录*/
	var rows = jQuery("#dataTable").getSeletRow();
	/*多选记录*/
	var ids = jQuery("#dataTable").getSeletIds();
	/*选择记录的条数（长度）*/
	var len = ids.length;
	if(0 == len ){/*选择0条提示*/
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	}else if(1 == len){/*选择一条记录*/
		var url = "rcsw_rcxwwh_rcxwjggl.do?method=getDykptzdOne&xh="+rows[0]["xh"]+"&id="+rows[0]["id"];
		window.open(url);
	}else{
		var url = "rcsw_rcxwwh_rcxwjggl.do?method=getDykptzdZip&value="+ids;
		window.open(url);
	}
}

/**
 * 浙江警官职业学院——个性化违纪处理单
 * @return
 */
function printWjcld(){
	/*选择的记录*/
	var rows = jQuery("#dataTable").getSeletRow();
	/*多选记录*/
	var ids = jQuery("#dataTable").getSeletIds();
	/*选择记录的条数（长度）*/
	var len = ids.length;
	if(0 == len ){/*选择0条提示*/
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	}else if(1 == len){/*选择一条记录*/
		var url = "wjcf_cfjg.do?method=getWjcldOne"+"&id="+rows[0]["id"];
		window.open(url);
	}else{
		var url = "wjcf_cfjg.do?method=getWjcldZip&value="+ids;
		window.open(url);
	}
}

/**
 * 浙江警官职业学院——奖励审批表
 * @return
 */
function printJlspb(){
	/*选择的记录*/
	var rows = jQuery("#dataTable").getSeletRow();
	/*多选记录*/
	var ids = jQuery("#dataTable").getSeletIds();
	/*选择记录的条数（长度）*/
	var len = ids.length;
	if(0 == len ){/*选择0条提示*/
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	}else if(1 == len){/*选择一条记录*/
		var url = "rcsw_rcxwwh_rcxwjggl.do?method=getJlspbOne"+"&id="+rows[0]["id"];
		window.open(url);
	}else{
		var url = "rcsw_rcxwwh_rcxwjggl.do?method=getJlspbZip&value="+ids;
		window.open(url);
	}
}