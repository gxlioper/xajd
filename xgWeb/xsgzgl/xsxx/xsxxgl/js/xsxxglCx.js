var cxjgzdpzList;
var DCCLBH = "xsxx_xsxxgl.do";// dcclbh,导出功能编号

var gridSetting = {
	caption : "学生信息列表",
	pager : "pager",
	url : "",
	colList : [],
	sortname : "",
	sortorder : ""
}

jQuery(function() {
	onShow();
});

function onShow() {
	// 列表显示字段设置
	var url = "xsxx_xsxxgl.do?method=getCxjgzdpz";
	jQuery.ajax( {
		type : "post",
		// async: false,
		url : url,
		data : {},
		dataType : "json",
		success : function(data) {
			cxjgzdpzList = data;// 查询结果字段
		xsxxsj();
	}
	});
	
	if (jQuery("#path").val() == "xsxx_xsxxgl_cxfzxs.do") {// 非在校生
		jQuery(".toolbox .buttonbox ul li:not(:has(a[id='btn_ck']))").hide();
		jQuery(".toolbox .buttonbox ul li:has(a[id='btn_ck'])").show();
		jQuery(".toolbox .buttonbox ul li:has(a[id='btn_dc'])").show();
        jQuery(".toolbox .buttonbox ul li:has(a[id='btn_xsxxdy'])").show();
        jQuery("#buttonbox2").hide();
	}
	
}

/**
 * 学生信息数据
 * 
 * @return
 */
function xsxxsj() {
	if (cxjgzdpzList != null) {
		gridSetting.colList = eval(createColModelJson());
		if('10352' == jQuery('#xxdm').val() || "10351" == jQuery("#xxdm").val()) {
			gridSetting.sortname = "nj desc nulls last,xydm";
			gridSetting.sortorder = "desc nulls last";
		} else {
			gridSetting.sortname = getSortname();
		}
		gridSetting.url = "xsxx_xsxxgl.do?type=query&method="
				+ jQuery("#method").val();
	}
	gridSetting["params"]=getSuperSearch();
	jQuery("#dataTable").initGrid(gridSetting);
}

/*
 * 生成colModel的json格式
 */
function createColModelJson() {
	var str = "[";
	str += "{label : '',name : 'xh',index : 'xh',key : true,hidden : true,formatter:setKey}";
	for ( var i = 0; i < cxjgzdpzList.length; i++) {
		var o = cxjgzdpzList[i];
		var zd = o.zd;
		var zdmc = o.zdmc;
		str += ",";
		str += "{";
		str += "label:'" + zdmc + "'";
		str += ",name:'" + zd + "'";
		str += ",index:'" + zd + "'";
		if (zd == "xh") {
			str += ",width:'13%',formatter:setXh";
		}
		if(zd=="nj"){
			str +=",width:'8%'";
		}
		if(zd=="xm"){
			str +=",width:'8%'";
		}
		if(zd=="xb"){
			str +=",width:'4%'";
		}
		str += "}";
	}
	str += "]";
	return str;
}

function setKey(cellValue, rowObject) {
	var xh = rowObject.xh;
	if (xh == null) {
		xh = "";
	}
	cellValue = "<a  href='javascript:void(0);' onclick='zxsxxView(\"" + xh
			+ "\");return false;' >" + xh + "</a>";
	cellValue += "<input type='hidden' name='key_xh' value='" + xh + "' >";
	return cellValue;
}

function setXh(cellValue, rowObject) {
	var xh = rowObject.xh;
	if (xh == null) {
		xh = "";
	}
	cellValue = "<a  href='javascript:void(0);'  class='name'   onclick='zxsxxView(\"" + xh
			+ "\");return false;' >" + xh + "</a>";
	return cellValue;
}

/*
 * 获取排序列
 */
function getSortname() {
	var str = "xymc,bjmc,xh";
	return str;
}

// 查询
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

// 增加
function showzxsxxAdd() {
	var url = "xsxx_xsxxgl.do?method=xsxxglZj";
	showDialog("增加学生信息", 850, 360, url);
}

// 修改
function showzxsxxEdit() {

	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_xsxxgl.do?method=xsxxglXg";
		url += "&xh=" + ids;
		showDialog("修改学生信息", 950, 550, url);
	} else {
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	}
}

function ycsjTs(){
	var url = "xsxx_xsxxgl.do?method=ycsjTs";
	showConfirmDivLayer("您将同步异常数据", {
		"okFun" : function() {
			jQuery.post(url, {
			}, function(data) {
				alertInfo(data["message"]);
				jQuery("#dataTable").reloadGrid();
			}, 'json');
		}
	});
}

// 查看
function showzxsxxView() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		zxsxxView(ids);
	} else {
		showAlertDivLayer("请<font color='blue'>勾选一条</font>您希望查看的记录！");
		return false;
	}
}

// 新版查看弹出层
function zxsxxView(xh) {
	showDialog("学生信息查询", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh);
}

// 删除
function deletezxsxx() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len != 0) {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				var url = "xsxx_xsxxgl.do?method=xsxxglSc";
				jQuery.post(url, {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"], {}, {
						"clkFun" : function() {
							jQuery("#dataTable").reloadGrid();
						}
					});
				}, 'json');
			}
		});
	} else {
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	}
}
// 密码初始化
function mmcsh() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		// 重置
		jQuery("#pksPlHidden").val("");
		// 根据查询结果进行批量增加
		var rowConut = jQuery("#rowConut").html();
		var url = "xsxx_xsxxgl.do?method=mmcshPl&len="+rowConut;
		var title = "密码初始化";
		showDialog(title,350,255,url);
	} else {
		showDialog("密码初始化", 350, 210, "xsxx_xsxxgl.do?method=mmcsh");
	}
}

// 打印通用学籍卡
function printTyXjk() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_tygl.do?method=getXjk";
		url += "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	} else {
		var url = "xsxx_tygl.do?method=getXjkZipTy";
		url += "&value=" + ids;
		window.open(url);
	}
}


//打印江西科技师范大学新生入学登记表、浙江传媒户籍登记表
function printRxdjb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_tygl.do?method=getDjb";
		jQuery("#xh").val(ids);
	} else if (len == 0) {
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	} else {
		var url = "xsxx_tygl.do?method=getDjbZip";
		jQuery("#value").val(ids);
	}
	
	document.forms[0].action = url;
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}


//湖南机电职业技术学院个性化登记表打印
function printDjb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_tygl.do?method=getXjk&hnjdDjb=djb";
		url += "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	} else {
		var url = "xsxx_tygl.do?method=getXjkZipTy&hnjdDjb=djb";
		url += "&value=" + ids;
		window.open(url);
	}
}

// 打印报表
function printZxsxx() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_tygl.do?method=printJsp";
		url += "&xh=" + ids;
		window.open(url);
	} else {
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	}
}
//打印团组织关系报表
function printZzjsx() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_tygl.do?method=printZzjsx";
		var url = url + "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("请<font color='blue'>勾选</font>您希望下载的记录！");
		return false;
	} else {
		var url = "xsxx_tygl.do?method=printZzjsxZip";
		url += "&value=" + ids;
		window.open(url);
	}
}

function tyqntj() {
		var url = "xsxx_xsxxgl.do?method=tyqntjExport";
		window.open(url);
}

function getWord() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_tygl.do?method=getXjkWord";
		var url = url + "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	} else {
		var url = "xsxx_tygl.do?method=getXjkZip";
		url += "&value=" + ids;
		window.open(url);
	}
}
// 华中师范大学 下载学生卡片
function getXskpWord() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_tygl.do?method=getXskpWord";
		var url = url + "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	} else {
		//这部分还没有想好
		var url = "xsxx_tygl.do?method=getXskpZip";
		url += "&value=" + ids;
		window.open(url);
	}
}

function printXjk() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len >= 1) {
		jQuery("#xhstr").val(ids);
		var url = "xsxx_tygl.do?method=plPrintJsp";
		document.forms[0].action = url;
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
	} else {
		alertInfo("请选择您要下载的记录！");
		return false;
	}
}

// 自定义导出 功能
function zxsxxExportConfig() {
	// DCCLBH导出功能编号,执行导出函数
	customExport(DCCLBH, zxsxxExportData, 1000, 500);
}

// 导出方法
function zxsxxExportData() {
	setSearchTj();// 设置高级查询条件
	var url = "xsxx_xsxxgl.do?dcclbh=" + DCCLBH + "&method=";
	if (jQuery("#path").val() == "xsxx_xsxxgl_cxfzxs.do") {// 非在校生
		url += "fzxsxxExportData";
	} else {
		url += "zxsxxExportData";
	}
	url+="&rowConut="+jQuery("#rowConut").html();
	url = addSuperSearchParams(url);// 设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
// 旧版导入
function drxx(){
	toImportData("IMPORT_N100101");
	return false;
}
// 新版导入
function drxxNew(){
	toImportDataNew("IMPORT_N100101_NEW");
	return false;
}

//导出方法
function zjjcXshzExportData() {
	setSearchTj();// 设置高级查询条件
	var url = "xsxx_xsxxgl.do?method=exportDataZjjc";
	url = addSuperSearchParams(url);// 设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
//导出方法
function zjjcZcfExportData() {
	var ids = jQuery("#dataTable").getSeletRow();
	var len = ids.length;
	var url = "xsxx_xsxxgl.do?method=exportZcfDataZjjc";
	if(len==0){
		showAlertDivLayer("请勾选需要导出的学生");
		return false;
	}
	if(len>=100){
		showAlertDivLayer("勾选的数量请勿超过100个学生");
		return false;
	}
	if(len==1){
		url = url+"&xhs="+ids[0]["xh"];
	}else if(len>1){
		var xhAll = new Array();
		for(var i=0;i<len;i++){
			xhAll.push(ids[i]["xh"]);
		}
		url = url+"&xhs="+xhAll.join(",");
	}
	 
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


//导出方法
function zjjcZhqkExportData() {
	setSearchTj();// 设置高级查询条件
	var url = "xsxx_xsxxgl.do?method=exportZhqkDataZjjc";
	url = addSuperSearchParams(url);// 设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}



//杭州汽车技工成绩单打印
function printCjd() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_tygl.do?method=printCjd";
		url += "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	} else {
		var url = "xsxx_tygl.do?method=getCjdZip";
		url += "&value=" + ids;
		window.open(url);
	}
}


//上海海洋新生登记表
function printXsdjb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_tygl.do?method=printXsdjb";
		url += "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	} else {
		var url = "xsxx_tygl.do?method=getXsdjbZip";
		url += "&value=" + ids;
		window.open(url);
	}
}
/**杭州汽车高级技工学校学籍卡_个性化！*/
function printHzqcxjk() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_tygl.do?method=printHzqcxjk";
		var url = url + "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	} else {
		var url = "xsxx_tygl.do?method=getHzqcxjkZip";
		url += "&value=" + ids;
		window.open(url);
	}
}
/** 徐州医药学籍卡_个性化 */
function printXzyyXjk() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_tygl.do?method=printXzyyxjk";
		var url = url + "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	} else {
		var url = "xsxx_tygl.do?method=getXzyyxjkZip";
		url += "&value=" + ids;
		window.open(url);
	}
}
/**浙江大学新生入学登记表_个性化*/
function printXsrxdjb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_tygl.do?method=Xsrxdjb";
		var url = url + "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	} else {
		var url = "xsxx_tygl.do?method=XsrxdjbZip";
		url += "&value=" + ids;
		window.open(url);
	}
}
/**北京林业大学本科生基本信息登记表_个性化*/
function printJbxxdjb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_tygl.do?method=Jbxxdjb";
		var url = url + "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	} else {
		var url = "xsxx_tygl.do?method=JbxxdjbZip";
		url += "&value=" + ids;
		window.open(url);
	}
}







/**南通农业职业技术学院_12684学生三年制成绩表个性化*/
	function printXscjb(){
		var ids = jQuery("#dataTable").getSeletIds();
		var len = ids.length;
		if(len == 1){
			var url = "xsxx_tygl.do?method=getPrintXscjb";
			var url = url + "&xh=" + ids;
			window.open(url);
		} else if (len == 0) {
			showAlertDivLayer("请选择您要下载的记录！");
			return false;
		} else {
			var url = "xsxx_tygl.do?method=getPrintXscjbZip";
			url += "&value=" + ids;
			window.open(url);
		}
	}
/**南通农业职业技术学院_12684学生五年制成绩表个性化*/
function printXscjbwu(){
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if(len == 1){
		var url = "xsxx_tygl.do?method=getPrintXscjbwu";
		var url = url + "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	} else {
		var url = "xsxx_tygl.do?method=getPrintXscjbwuZip";
		url += "&value=" + ids;
		window.open(url);
	}
}

/**咸宁职业技术学院_13265打印班级花名册*/
function xnzyjsxyhmcExportData() {
	setSearchTj();
	var map = getSuperSearch();	
	var searchTj = map["searchTj"];
	if(searchTj.indexOf("searchModel.search_tj_xy") == -1){
		showAlertDivLayer("打印班级花名册必须选择一个学院!");
		return false;
	}else{
		var url = "xsxx_tygl.do?method=xnzyjsxyhmcExportData"
		url = addSuperSearchParams(url);//设置高级查询参数
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}
}


// 导出方法
function getPrintTY() {
	setSearchTj();//设置高级查询条件
	var url = "xsxx_tygl.do?method=getPrintTY&dcclbh=" + "xsxx_tyxx_cxfzxs.do";//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

	

	
// 导出方法
function getPrintTGB() {
	setSearchTj();//设置高级查询条件
	var url = "xsxx_tygl.do?method=getPrintTGB&dcclbh=" + "xsxx_tgbxx_cxfzxs.do";//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
/**
 * 学生身份证明打印（重庆工程职业技术学院）
 */
function printSI() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_tygl.do?method=getSI";
		url += "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	} else {
		var url = "xsxx_tygl.do?method=getSIZip";
		url += "&value=" + ids;
		window.open(url);
	}
}

function saveSxzt(zd1) {
	var rows = jQuery("#dataTable").getSeletRow();
	var xhs = new Array();
	zd1 = encodeURI(encodeURI(zd1));
	jQuery.each(rows, function(i, row) {
		xhs.push(row["xh"]);
	});
	jQuery.post("xsxx_xsxxgl.do?method=updateSxzt", {
		zd1 : zd1,
		xhs : xhs
	}, function(data) {
		
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				jQuery("#dataTable").reloadGrid();
			}
		});
	}, 'json');
}