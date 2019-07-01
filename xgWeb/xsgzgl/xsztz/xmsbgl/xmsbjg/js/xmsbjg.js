function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function xmmcLink(cellValue, rowObject) {
	if('1'==rowObject["sfrm"]){
		cellValue+="<font color='red'>【热门】</font>";
	}
	return "<a href='javascript:void(0);' class='name' onclick='XmsbjgView(\""
			+ rowObject["jgid"] + "\");'>" + cellValue + "</a>";
}

function xmcxLink(cellValue, rowObject) {
	if('1'==rowObject["sfrm"]){
		cellValue+="<font color='red'>【热门】</font>";
	}
	return "<a href='javascript:void(0);' class='name' onclick='xmcxView(\""
			+ rowObject["jgid"] + "\",\""
			+ rowObject["csms"] + "\");'>" + cellValue + "</a>";
}
function xmcxView(jgid,csms) {
	showDialog("项目信息查看", 800, 500, "xmsbXmsbjg.do?method=xmcxView&jgid="
			+ jgid+"&csms="+csms);
}
function XmsbjgView(jgid) {
//	showDialog("项目申报查看", 800, 350, "xmsbXmsbjg.do?method=viewXmsbjg&jgid="
//			+ jgid);
	showDialog("项目申报查看", 800, 500, "xmsbXmsbjg.do?method=viewXmsbjg&jgid="
			+ jgid);
}
//项目设置格式化
function setXmsz(cellValue, rowObject) {
	var jgid = rowObject.jgid;
	var value = "未设置";
	var status = '0';
	var color;
	var currDate = jQuery("#currDate").val();
	if ((null == rowObject.sqkssj || currDate >= rowObject.sqkssj)
			&& (null == rowObject.sqjssj || currDate <= rowObject.sqjssj)&&'1'==rowObject.sqkg) {
		value = "已设置";
		status = '1';
	}
	value = setColor(value, status);
	value = "<a  href='javascript:void(0);' onclick='return xmsz(\"" + jgid
			+ "\");' >" + value + "</a>";
	return value;
}
function setColor(value, status) {
	var color;
	if (status == '1') {
		color = "#004400";
	} else {
		color = "red";
	}
	return value = "<font color='" + color + "'>" + value + "</font>";
}
/*
 * 项目设置详情
 */
function xmsz(jgid) {
	if (jgid == null) {// 点击按钮
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("请选择一条您要设置的记录！");
			return false;
		}
		jgid = rows[0]["jgid"];
	}
	var url = 'xmsbXmsbjg.do?method=xmsz&jgid=' + jgid;
	var title = "项目设置";
	showDialog(title, 680, 360, url);
}
function saveXmsbjg(type) {
	var flg = true;
	jQuery.ajaxSetup({async:false});
	jQuery.post("xmsbXmsbjg.do?method=sfxg", {xmdm:jQuery("#xmdm").val()}, function(data) {
		if(!data){
			flg = false;					
		}	      		
		}, 'json');
	jQuery.ajaxSetup({async:true});
	if(!flg){
		showAlertDivLayer("该项目已学分认定，不能修改");
		return false;
	}
	if (check()) {
	jxToJson();// 封装奖项信息
	var url = "xmsbXmsbjg.do?method=saveXmsbjg&type="+type;
	ajaxSubFormWithFun("XmsbjgForm", url, function(data) {
		if (data["message"] == "保存成功！") {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		} else {
			showAlert(data["message"]);
		}
	});
	}
}
function add() {
	var sqkg = jQuery("#sqkg").val();
	if ("0" == sqkg) {
		showAlertDivLayer("当前已关闭，请与管理员联系！");
		return false;
	}
	var url = "xmsbXmsbjg.do?method=addXmsbjg";
	var title = "项目申报";
	showDialog(title, 800, 508, url);
}
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else if ('false' == rows[0]["sfkxg"]) {
		showAlertDivLayer("请选择用户本人申报的项目！");
	} else if(rows[0]['sjly']=='1'){
			showAlertDivLayer("审核流程过来的记录不能修改！");
	}else {
		var url = 'xmsbXmsbjg.do?method=editXmsbjg&jgid=' + rows[0]["jgid"];
		var title = "项目申报修改";
		showDialog(title, 800, 508, url);
	}

}

// 删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("审核流程过来的记录不能删除！");
				return false;
			}
		}
		showConfirmDivLayer("是否确定删除勾选的记录？", {
		"okFun" : function() {
		var url = "xmsbXmsbjg.do?method=delXmsbjg";
		jQuery.post(url, {
			values : ids.toString()
		}, function(data) {
			if (data["success"] == false) {
				showAlertDivLayer(data["message"]);
			} else {
				showAlertDivLayer(data["message"], {}, {
					"clkFun" : function(tag) {
						jQuery("#dataTable").reloadGrid();
					}
				});
			}
		}, 'json');
	
}});
}
}
//设定热门
function setRmxm(value) {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var url = "xmsbXmsbjg.do?method=setRmxm";
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要操作的记录！");
	}  else {
		jQuery.post(url, {
			value : value,
			ids:ids.toString()
		}, function(data) {
			showAlertDivLayer(data["message"], {}, {
				"clkFun" : function(tag) {
					jQuery("#dataTable").reloadGrid();
				}
			});
		},'json');
	}
	
}
/**
 * 奖项颁发
 * @return
 */
function jxbf() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要颁发奖项的项目！");
	} else if ('false' == rows[0]["sfkxg"]) {
		showAlertDivLayer("请选择用户本人申报的项目！");
	} else {
		var url = 'xmsbXmsbjg.do?method=jxbf&jgid=' + rows[0]["jgid"];
		var title = "奖项颁发";
		showDialog(title, 800, 500, url);
	}

}
//导入
function dr() {
	// 调用通用的导入function，参数是导入功能模块代码。
	toImportDataNew("IMPORT_N960205_XMSBWH");
	return false;

}

var DCCLBH = "sztz_xmsbgl_xmwh.do";// dcclbh,导出功能编号

// 自定义导出 功能
function exportConfig() {
	// DCCLBH导出功能编号,执行导出函数
	customExport(DCCLBH, khsqExportData);
}

// 导出方法
function khsqExportData() {
	setSearchTj();// 设置高级查询条件
	var url = "xmsbXmsbjg.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,导出功能编号
	url = addSuperSearchParams(url);// 设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
//个人学分查询导出
function grxfExport() {
	// DCCLBH导出功能编号,执行导出函数
	customExport("sztz_rzjggl_grxfcx.do", grxfExportData);
}

function grxfExportData() {
	setSearchTj();// 设置高级查询条件
	var url = "xmsqgl_xmjg.do?method=grxfExportData&dcclbh=" + "sztz_rzjggl_grxfcx.do";// dcclbh,导出功能编号
	url = addSuperSearchParams(url);// 设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
/**
 * 素质拓展——认证结果查询——个人学分查询；学分统计表打印
 */
function dyXftjbCommon(){
			
		var rows = jQuery("#dataTable").getSeletRow();
		
		if (rows.length != 1){
			showAlertDivLayer("请选择一条您需要下载的记录！");
			return false;
		} else {
			var url = "xmsqgl_xmjg.do?method=dyXftjbCommon&xh="+ rows[0]["xh"];
			
			window.open(url);
		}
}

function xscjPrint(){
	var rows = jQuery("#dataTable").getSeletRow();
	var xh = "";
	if(rows.length ==0){
		showAlertDivLayer("请选择需要下载的记录！");
		return false;
	}
	
	else if(rows.length == 1){
		xh = rows[0]["xh"];
		var url = "xmsqgl_xmjg.do?method=xscjPrint&xh="+xh;
		window.open(url);
	}
	else{
		for ( var i = 0; i < rows.length; i++) {
			if(i==rows.length-1){
				xh += rows[i]["xh"];
			}else{
				xh += rows[i]["xh"]+',';
				
			}
		}
		var url ="xmsqgl_xmjg.do?method=xscjPrintZip&xh="+xh;
		window.open(url);
	}
	
}

/**
 * 导入
 */
function jxdr() {
	toImportDataNew("IMPORT_N960501_SZTZJXBF");
	return false;
}

//设置项目阶段
function setXmjd(cellValue, rowObject) {
	var xmdm = rowObject.xmdm;
	var xmmc = rowObject.xmmc;
	var status = cellValue;
	var value = "未设置";
	var color;
	if (status == '1') {
		value = "已设置";
	}
	value = setColor(value, status);
	value = "<a  href='javascript:void(0);' onclick='return jdsz(\"" + xmdm
			+ "\",\"" + xmmc + "\");' >" + value + "</a>";
	return value;
}

//项目阶段设置点击链接
function jdsz(xmdm,xmmc) {
	if (xmdm == null) {// 点击按钮
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("请选择一条您要设置的记录！");
			return false;
		}
		xmdm = rows[0]["xmdm"];
	}
	var url = 'xmsbXmsbjg.do?method=jdsz&xmdm=' + xmdm+"&xmmc="+xmmc;
	var title = "阶段设置";
	showDialog(title, 680, 360, url);
}

