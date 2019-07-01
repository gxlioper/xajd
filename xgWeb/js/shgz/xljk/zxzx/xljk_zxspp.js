function fdyxxCx(){
	var zxxxm=$("xm").value;
	var zxxbh=$("zgh").value;
	var bmdm=$("bmdm").value;
	xzsxxPp.getFdyListByTj(zxxbh,zxxxm,bmdm,function (data){
	DWRUtil.removeAllOptions($("fdyxx"));		
	DWRUtil.addOptions($("fdyxx"),data,"fdy","fdy");
	});
}
function zxsxxCx(){
	var zxxxm=$("zxxxm").value;
	var zxxbh=$("zxxbh").value;
	xzsxxPp.getZxsListByTj(zxxbh,zxxxm,function(data){
		DWRUtil.removeAllOptions($("zxsxx"));		
		DWRUtil.addOptions($("zxsxx"),data,"zxs","zxs");
	});

}

/** 
 * 添加一个对应好的字段
 */
function zxsFdyPp() {
	var excelListIndex = document.forms[0].fdyxx.selectedIndex;
	var oracleListIndex = document.forms[0].zxsxx.selectedIndex;
	if (excelListIndex < 0 || oracleListIndex < 0 || document.forms[0].ppzxs.options.length == document.forms[0].zxsxx.options.length || checkZxsFdy()) {
		return false;
	}
	var addExcelV = document.forms[0].fdyxx.options[excelListIndex].value;
	var addExcelT = document.forms[0].fdyxx.options[excelListIndex].text;
	var addOracleV = document.forms[0].zxsxx.options[oracleListIndex].value;
	var addOracleT = document.forms[0].zxsxx.options[oracleListIndex].text;
	document.forms[0].ppzxs.options[document.forms[0].ppzxs.options.length] = new Option(addExcelT + "------" + addOracleT, addExcelV + "!!SplitTwo!!" + addOracleV);
}

function checkZxsFdy() {
	var tmp = new Array();
	for (i = 0; i < document.forms[0].ppzxs.options.length; i++) {
		tmp = document.forms[0].ppzxs.options[i].value.split("!!SplitTwo!!");
		if (document.forms[0].zxsxx.options[document.forms[0].zxsxx.selectedIndex].value == tmp[1]) {
			return true;
		}
	}
	for (i = 0; i < document.forms[0].ppzxs.options.length; i++) {
		tmp = document.forms[0].ppzxs.options[i].value.split("!!SplitTwo!!");
		if (document.forms[0].fdyxx.options[document.forms[0].fdyxx.selectedIndex].value == tmp[0]) {
			return true;
		}
	}
}

/** 
 * 删除一个对应好的字段
 */
function deleteZxsFdyPp() {
	var mappingListIndex = document.forms[0].ppzxs.selectedIndex;
	if (mappingListIndex < 0) {
		return false;
	}
	document.forms[0].ppzxs.options[mappingListIndex] = null;
}

function saveZxsPp(){
	var ppzxs=$("ppzxs");
	
	for(var i = 0 ; i <ppzxs.options.length; i++){
			var tmp = document.createElement("input");
			tmp.type = "hidden";
			tmp.name = "ppzxsxx";
			tmp.value = ppzxs.options[i].value;
			document.forms[0].appendChild(tmp);
	}
	var url='/xgxt/xljk_zxsxx_pp.do?method=zxsPp&doType=zxspp';
	refreshForm(url);
}