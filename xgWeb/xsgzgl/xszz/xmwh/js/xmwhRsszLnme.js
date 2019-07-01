var dataObj;// 源数据
var colsList;// 表格表头列
var COLS = 4;// 默认显示列数
jQuery(function() {
	onShow();
});

function onShow() {
	var url = "xszz_xmwh_rssz.do?method=xmwhRsszLnme&type=query";
	url += "&xmdm=" + jQuery("#xmdm").val();
	jQuery.post(url, {
		xmdm : jQuery("#xmdm").val()
	}, function(data) {
		dataObj = data;
		setCols();// 设置表头列，checkbox 显示
			createHtml();// 生成表格内容
			setInit();// 初始化设置列数
		}, 'json');
}

// 设置表头列，checkbox 显示
function setCols() {
	var xyList = dataObj['xyList'];
	var tjrsList = dataObj['tjrsList'];
	colsList = new Array();
	var checkbox = "";
	var checkNum = 0;
	for ( var i = 0; i < tjrsList.length; i++) {// 获取不重复的学年学期列
		var o = tjrsList[i];
		var flag = false;
		for ( var j = 0; j < colsList.length; j++) {
			var colsObj = colsList[j];
			if (o.xn == colsObj.xn && o.xq == colsObj.xq) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			colsList.push(o);
			if (checkNum != 0 && checkNum % 4 == 0) {
				checkbox += "<br/>";
			}
			checkNum++;
			var name = getXnXqName(o.xn, o.xq);
			var text = getXnXqText(o.xn, o.xq);
			checkbox += "<input type='checkbox' name='" + name + "'>" + text
					+ "&nbsp;";
		}
	}
	if (checkbox == "") {
		checkbox = "无历史数据！";
	}
	jQuery("#checkboxDiv").html(checkbox);

}

// 初始化设置列数
function setInit() {
	jQuery("#checkboxDiv input:checkbox").change(function() {// 
				var name = jQuery(this).attr("name");
				if (jQuery(this).prop("checked")) {// 选中
					jQuery("#dataTable thead tr td[name$='" + name + "']")
							.show();
					jQuery("#dataTable tbody tr td[name$='" + name + "']")
							.show();
				} else {// 取消选中
					jQuery("#dataTable thead tr td[name$='" + name + "']")
							.hide();
					jQuery("#dataTable tbody tr td[name$='" + name + "']")
							.hide();
				}
			});

	// 默认显示
	jQuery("#checkboxDiv input:checkbox").each(function(index) {
		if (index < COLS) {
			jQuery(this).attr("checked", "checked");
		} else {
			jQuery(this).attr("checked",false);
		}
		jQuery(this).change();
	});
}

// 生成表格内容
function createHtml() {
	var xyList = dataObj['xyList'];
	var tjrsList = dataObj['tjrsList'];

	var html = "";
	html += "<tr>";
	html += "<td >"+jQuery("#xbmc").val()+"</td>";
	for ( var i = 0; i < colsList.length; i++) {
		var o = colsList[i];
		var xn = o.xn;
		var xq = o.xq;
		html += "<td name='title_" + getXnXqName(xn, xq) + "'>"
				+ getXnXqText(xn, xq);
		html += "</td>";
	}
	html += "</tr>";
	jQuery("#dataTable thead").html(html);// 生成表头

	html = "";
	for ( var i = 0; i < xyList.length; i++) {
		var xyObj = xyList[i];
		var xydm = xyObj.xydm;
		var xymc = xyObj.xymc;
		html += "<tr>";
		html += "<td>" + xymc + "</td>";
		for ( var k = 0; k < colsList.length; k++) {// 每一列
			var colsObj = colsList[k];
			var cell = "0";
			for ( var j = 0; j < tjrsList.length; j++) {
				var tjrsObj = tjrsList[j];
				if (xydm == tjrsObj.xydm && colsObj.xn == tjrsObj.xn
						&& colsObj.xq == tjrsObj.xq) {
					cell = "" + tjrsObj.rs + "";
					break;
				}
			}
			html += "<td name='" + getXnXqName(colsObj.xn, colsObj.xq) + "'>"
					+ cell + "</td>";
		}
		html += "</tr>";
	}

	jQuery("#dataTable tbody").html(html);

	html = "<tr>";
	html += "<td><font color='red'>合计</font></td>";
	for ( var k = 0; k < colsList.length; k++) {// 每一列
		var colsObj = colsList[k];
		var zrs = 0;
		jQuery(
				"#dataTable tbody tr td[name='"
						+ getXnXqName(colsObj.xn, colsObj.xq) + "']")
				.each(
						function(index) {
							zrs = parseInt(jQuery(this).html(), 10)
									+ parseInt(zrs, 10);
						});
		html += "<td name='total_" + getXnXqName(colsObj.xn, colsObj.xq)
				+ "'> <font color='red' >" + zrs + "</font></td>";
	}
	html += "</tr>";
	jQuery("#dataTable tbody").append(html);
}

// 设置学年学期生成的名称串
function getXnXqName(xn, xq) {
	return xn + ";" + xq;
}

// 设置学年学期生成的显示text
function getXnXqText(xn, xq) {
	var xqList = dataObj['xqList'];
	var text = xn + "学年";
	for ( var i = 0; i < xqList.length; i++) {
		var o = xqList[i];
		if (xq == o.xqdm) {
			text += o.xqmc;
		}
	}
	text += "学期";

	return text;
}
