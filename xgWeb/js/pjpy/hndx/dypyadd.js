var count = 1;
var max = 0;
 
function onShow() {
	dwr.engine.setAsync(false);
	var tableName1 = "hndx_dypyxmb";
	var colList1 = ["xmdm","xmmc", "bl"];
	var pk = "1";
	var pkValue = "1";
	var query = " ";
	getOtherData.getTableListInfo(tableName1, colList1, pk, pkValue, query, function (data) {
		if (data != null && data.length > 0) {
			$("numAdd1").value = data.length;
			trAdd("flag1", "madd", "numAdd1", "rzqk");
			for (var i = 1; i <= data.length; i++) {
				if ($("xmdm" + i)) {
					var xmdm = data[i - 1].xmdm;
					if (xmdm == null) {
						xmdm = "";
					}
					$("xmdm" + i).value = xmdm;
				}
				
				if ($("xmmc" + i)) {
					var xmmc = data[i - 1].xmmc;
					if (xmmc == null) {
						xmmc = "";
					}
					$("xmmc" + i).value = xmmc;
				}
				
				if ($("bl" + i)) {
					var bl = data[i - 1].bl;
					if (bl == null) {
						bl = "";
					}
					$("bl" + i).value = bl;
				}
			}
		}
	});
	dwr.engine.setAsync(true);
}
function trAdd(the_tab, type, numAdd, lb) {
	var len = document.getElementById(the_tab).rows.length + 1;
	var num = $(numAdd).value;
	var doType = $("doType").value;
	var cellfu = getCellfu(lb);
	count = len;
	if (type == "add") {
		max++;
		DWRUtil.addRows(the_tab, [""], cellfu, {escapeHtml:false});
	} else {
		if (num == "" || num == null) {
			return false;
		}
		for (i = count; i <= num; i++) {
			max++;
			DWRUtil.addRows(the_tab, [""], cellfu, {escapeHtml:false});
			count++;
		}
		$(numAdd).value = "";
	}
}
function getCellfu(lb) {
	var cellfu = new Array();
	cellfu = [function (data) {
		var htmltext = "<input type='text' style='width:100%'  name='xmmc' id='xmmc" + max + "'";
		htmltext += " style='cursor:hand;' readonly='readonly'";
		htmltext += "/>";
		htmltext += "<input type='hidden' name='xmdm' id='xmdm" + max + "'/>";
		return htmltext;
	}, function (data) {
		var htmltext = "<input type='text' style='width:100%'  name='bl' id='bl" + max + "'";
		htmltext += " style='cursor:hand;' readonly='readonly'";
		htmltext += "/>";
		return htmltext;
	}, function (data) {
		var htmltext = "<input type='text' style='width:100%'  name='xmjf' id='xmjf" + max + "'";
		htmltext += " style='cursor:hand;' onblur='checkInputData(this);' ";
		htmltext += "/>";
		return htmltext;
	}];
	return cellfu;
}
	