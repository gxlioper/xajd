function checkMustNotNull(){
	var xn = DWRUtil.byId('xn');
	var jxj = DWRUtil.byId('xmdm');
	if((xn.value === null || xn.value.length===0) || (jxj.value === null || jxj.value.length===0)){
		alert("学年,奖学金是必选项！请选择后查询！");
		return false;
	}	
	return true;
}
/**
 * 根据后台传入的值，打复选框的选中标记
 */
function shResult(){
	var table = DWRUtil.byId('rsTable');
	var tableTrs = null;
	var tableRows = null;
	if(table){
		tableTrs  = table.getElementsByTagName("tr");
		tableRows = table.rows;
	}
	if(tableTrs){
		for(var i=0;i<tableTrs.length;i++){
			if(tableTrs[i].style.backgroundColor == '#99ffcc'){//审核通过的记录行颜色是#99FFCC
				if(tableRows[i].cells[0].getElementsByTagName){
					tableRows[i].cells[0].getElementsByTagName('input')[0].checked=true;//将审核通过的项选中	
				}
			}
		}
	}
}

/**
 * 审核按钮提交操作
 * @param res 审核结果：tg or btg
 */
function shSubmit(res){
	var checkBoxArr = document.getElementsByName("cbv");
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (flag){
		document.forms[0].action="/xgxt/xyjxjSh.do?param1="+res;
		document.forms[0].submit();
	}else{
		alert("没有选择相应记录，请选择之后再确定！！");
	}
}

/**
 * 用户多项选择
 */
function selectAll(){
	var checkBoxArr = document.getElementsByName("cbv");
	var selall = document.getElementById('cb');
	if(selall.checked==true){
		for(var i=0;i<checkBoxArr.length;i++){
			if (checkBoxArr[i].disabled==true) {
				checkBoxArr[i].checked = false;	
			} else {
				checkBoxArr[i].checked = true;
			}
		}
	} else {
		for(var i=0;i<checkBoxArr.length;i++){
			checkBoxArr[i].checked = false;
		}
	}
}

function pjpySbExport(){
	var xn = DWRUtil.byId('xn');
	var jxj = DWRUtil.byId('xmdm');
	if((xn.value === null || xn.value.length===0) || (jxj.value === null || jxj.value.length===0)){
		alert("学年,奖学金是必选项！请选择后查询！");
		return false;
	}	
	document.forms[0].action = "/xgxt/xysbjxj.do?method=dataExport";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}
