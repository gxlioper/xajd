var cxjgzdpzList;
var DCCLBH = "xsxx_xsxxgl.do";// dcclbh,导出功能编号

var gridSetting = {
	caption : "学生信息列表",
	pager : "pager",
	url : "",
	colList : [],
	sortname : "",
	sortorder : "asc"
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
		gridSetting.sortname = getSortname();
		gridSetting.url = "xsxx_xsxxgl.do?type=query&method="
				+ jQuery("#method").val();
	}
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
		//设置身份证号不显示
		if(zd!="sfzh"){
			str += ",";
			str += "{";
			str += "label:'" + zdmc + "'";
			str += ",name:'" + zd + "'";
			str += ",index:'" + zd + "'";
			if (zd == "xh") {
				str += ",formatter:setXh";
			}
			str += "}";
		}
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
	var str = "";
	for ( var i = 0; i < cxjgzdpzList.length; i++) {
		var o = cxjgzdpzList[i];
		var zd = o.zd;
		var zdmc = o.zdmc;
		if (i > 0) {
			str += ",";
		}
		str += zd;
		if (i > 2) {
			break;
		}
	}
	return str;
}

// 查询
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
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
	showDialog("学生信息查询", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCkByCfxy&xh=" + xh
			+ "&xs");
}

// 删除
function deletezxsxx() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len != 0) {
		showConfirmDivLayer("请您确认<font color='blue'>是否删除</font>所勾选的记录？", {
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
		showAlertDivLayer("请<font color='blue'>勾选</font>您希望删除的记录！");
		return false;
	}
}
// 密码初始化
function mmcsh() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请<font color='blue'>勾选</font>您需要初始化的数据！");
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
		showAlertDivLayer("请<font color='blue'>勾选一条</font>您希望下载的记录！");
		return false;
	} else {
		var url = "xsxx_tygl.do?method=getXjkZipTy";
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
		showAlertDivLayer("请<font color='blue'>勾选一条</font>您希望修改的记录！");
		return false;
	}
}

function getWord() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_tygl.do?method=getXjkWord";
		var url = url + "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("请<font color='blue'>勾选一条</font>您希望下载的记录！");
		return false;
	} else {
		var url = "xsxx_tygl.do?method=getXjkZip";
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
		alertInfo("请<font color='blue'>勾选一条</font>您希望修改的记录！");
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
	url = addSuperSearchParams(url);// 设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
