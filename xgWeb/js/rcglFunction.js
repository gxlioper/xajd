function rcglChkPriseOne(url, w, h) {
	if (w == null) {
		w = 700;
	}
	if (h == null) {
		h = 400;
	}
	if (curr_row == null) {
		alert("请选择要操作的行！");
		return false;
	} else {
		var val = curr_row.cells[0].getElementsByTagName("input")[0].value;
		var pk = document.forms[0].pk.value;
		var realTable = document.forms[0].realTable.value;
		var tableName = document.forms[0].tableName.value;
		url += val;
		url += "&pk=";
		url += pk;
		url += "&realTable=";
		url += realTable;
		url += "&tableName=";
		url += tableName;
		showTopWin(url, w, h);	
	}	
}
	