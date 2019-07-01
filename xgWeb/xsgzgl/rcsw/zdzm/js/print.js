//检查基础基础设置Sq
function checkCsszSq(){
	var xzkg = jQuery("#xzkg").val();
	var kxzkz = jQuery("#kxzkz").val();
	if(xzkg == '0'){
		return false;
	}
	if(kxzkz == '1'){
		return false;
	}
	return true;
}

//检查基础基础设置Jg
function checkCsszJg(){
	var xzkg = jQuery("#xzkg").val();
	var kxzkz = jQuery("#kxzkz").val();
	if(xzkg == '0'){
		return false;
	}
	
	return true;
}

// 打印报表
function printZdzmSq(url) {
	var xxdm = jQuery("input[name='xxdm']").val();
	var xzkg = jQuery("#xzkg").val();
	var kxzkz = jQuery("#kxzkz").val();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <=0) {
		showAlertDivLayer("请选择一条记录！");
		return false;
	}
	if(xzkg == '0'){
		showAlertDivLayer("当前基础设置不能下载在读证明！");
		return false;
	}

	var xhs = [];
	var pyccmc = rows[0]['pyccmc']||'';
	for(i=0;i<rows.length;i++){
		if(rows[i]['shzt'] != '1' && kxzkz == '1'){
			showAlertDivLayer("审核通过后方可下载在读证明！");
			return false;
		}
		if((rows[i]['pyccmc']||'') != pyccmc && "10052" == xxdm){
				
			showAlertDivLayer("培养层次必须相同 , 请确认!");
			return false;
		}
		xhs.push(rows[i]['xh']);
	}

	if("10052" == xxdm){
		showDialog("在读证明登记表下载",380,170,'rcsw_zdzm_sqgl.do?method=print&xhs=' + xhs.toString() + '&pyccmc='+pyccmc);
		return false;
	}
	var url = url + "&xhs=" +xhs.toString();
	window.open(url);
}

//打印报表
function printZdzmJg(url) {
	var xxdm = jQuery("input[name='xxdm']").val();
	
	if(!checkCsszJg()){
		showAlertDivLayer("当前基础设置不能下载在读证明！");
		return false;
	}
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <=0) {
		showAlertDivLayer("请选择一条记录！");
		return false;
	}
	var xhs = [];
	var pyccmc = rows[0]['pyccmc']||'';
	for(i=0;i<rows.length;i++){
		if((rows[i]['pyccmc']||'') != pyccmc && "10052" == xxdm){
			showAlertDivLayer("培养层次必须相同 , 请确认!");
			return false;
		}
		xhs.push(rows[i]['xh']);
	}

	if("10052" == xxdm){
		showDialog("在读证明登记表下载",380,170,'rcsw_zdzm_jggl.do?method=print&xhs=' + xhs.toString() + '&pyccmc='+pyccmc);
		return false;
	}
	var url = url + "&xhs=" +xhs.toString();
	window.open(url);
}


function print(url){
	var url2 = url + '&type=' + jQuery('#type').val() + '&xhs=' + jQuery('#xhs').val();
	window.open(url2);
}