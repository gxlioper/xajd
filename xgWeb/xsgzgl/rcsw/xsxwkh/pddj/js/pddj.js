
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='pddjView(\""
			+ rowObject["jbfid"]+"\",\"" + rowObject["xn"]+"\");'>" + cellValue
			+ "</a>";
}
function pddjView(jbfid,xn) {
	showDialog("评定等级查看", 650, 450, "xsxwkhDjpd.do?method=pddjView&jbfid="+jbfid+"&xn="+xn);
}
function getZpdj() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlertDivLayer("请选择您要操作的记录");
		return false;
	}
	 if (rows.length == 1) {
		var url = "xsxwkhDjpd.do?method=pddjSingle&jbfid=" + rows[0]["jbfid"] + '&xn=' + rows[0]["xn"];
		showDialog("评定等级", 700, 480, url);
	} else {
		showDialog("批量评定等级", 500, 250, "xsxwkhDjpd.do?method=pddjPl&num="+rows.length);
	}
}


//批量审核
function savePddjPl(pddj) {
	var rows = jQuery("#dataTable").getSeletRow();
	var xh = new Array();
	var xn = new Array();
	jQuery.each(rows, function(i, row) {
		xh.push(row["xh"]);
		xn.push(row["xn"]);
	});
	jQuery.post("xsxwkhDjpd.do?method=pddjPl&type=save", {
		pddj : pddj,
		xhs : xh,
		xns : xn
	}, function(data) {
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				jQuery("#dataTable").reloadGrid();
			}
		});
	}, 'json');
}
var DCCLBH="xsxwkh_djpd.do";
//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, pddjExportData);
}

//导出方法
function pddjExportData() {
	setSearchTj();//设置高级查询条件
	var url = "xsxwkhDjpd.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
