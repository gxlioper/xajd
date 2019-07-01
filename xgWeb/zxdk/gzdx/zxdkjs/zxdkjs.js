//加载月份列表
function loadyf(val){
	if (document.getElementById(val).value=='') {
		document.getElementById('yf').disabled = true;
	} else {
		document.getElementById('yf').disabled = false;
	}
}

//修改和删除
function modiAndDel(url,type,w,h) {
	if ($('disli') && $('disli').value=='0') {
		
	} else {
		if (curr_row==null || curr_row=='') {
			alertInfo('请选择要操作的数据行！');
			return;
		} else {
			if (type=='modi') {
				var realTable;
				if ($('realTable')) {
					realTable = document.getElementById('realTable').value;
				}
				url += curr_row.cells[0].getElementsByTagName("input")[0].value;
				url += '&realTable=';
				url += realTable;
				url += '&xh=';
				url += curr_row.cells[2].innerText;
				url += '&xn=';
				url += curr_row.cells[4].innerText;
				showTopWin(url,w,h);
			} else {
				if (confirm('确认要删除所选择的数据吗？')) {
					refreshForm(url);
				} else {
					return;
				}
			}
		}	
	}
}

function jxjshtg() {
	var xysh = document.getElementById('xysh').value;
	var xxsh = document.getElementById('xxsh').value;
	if (xysh=='通过' || xxsh=='通过') {
		return true;
	} else {
		return false;
	}
}

//保存信息
function saveinfo() {
	
	var xh =document.getElementById('xh').value;
	if(xh==""){
		alertInfo("请选择学号！");
		return false;
	}
	var xn =document.getElementById('xn').value;
	if(xn==""){
		alertInfo("请选择学年！");
		return false;
	}
	var zxdkmc =document.getElementById('zxdkmc').value;
	if(zxdkmc==""){
		alertInfo("请选择助学贷款名称！");
		return false;
	}
	var dkje =document.getElementById('dkje').value;
	if(dkje==""){
		alertInfo("请输入贷款金额！");
		return false;
	}
	var bz =document.getElementById('bz').value;
	if(bz.length > 255){
		alertInfo("您输入的内容过多，请将字数限制在255个字符以内！");
		return false;
	}
	confirmInfo('是否确认保存',saveAddInfo);
	
}
function saveAddInfo(tag) {
	if(tag=="ok"){
		var val="xh-xn-nd";
		var arrayList = val.split('-');
		for (var i=0; i<arrayList.length;i++) {
			if ($(arrayList[i])) {
				if (jQuery('#'+arrayList[i]).value=='') {
					alertInfo("请将带\"*\"号的项目输入完整！");
					return false;
				}
			}
		}
		jQuery('#btn_save').attr('disabled',true);
		refreshForm("zxdk_gzdx_addZxdkSjwh.do?operType=save");
	}
}
//保存修改信息
function saveupdateinfo() {
	var xn =document.getElementById('xn').value;
	if(xn==""){
		alertInfo("请选择学年！");
		return false;
	}
	var zxdkmc =document.getElementById('zxdkmc').value;
	if(zxdkmc==""){
		alertInfo("请选择助学贷款名称！");
		return false;
	}
	var dkje =document.getElementById('dkje').value;
	if(dkje==""){
		alertInfo("请输入贷款金额！");
		return false;
	}
	var bz =document.getElementById('bz').value;
	if(bz.length > 255){
		alertInfo("您输入的内容过多，请将字数限制在255个字符以内！");
		return false;
	}
	confirmInfo('是否确认保存修改项',saveupdate);
}

function saveupdate(tag) {
		if(tag=="ok"){
		var url ="zxdk_gzdx_updateZxdkSjwh.do?operType=save";
		var val ="xh-xn-zxdkmc-dkje-bz";
		var arrayList = val.split('-');
		for (var i=0; i<arrayList.length;i++) {
			if ($(arrayList[i])) {
				if (jQuery('#'+arrayList[i]).value=='') {
					alertInfo("请将带\"*\"号的项目输入完整！");
					return false;
				}
			}
		}
		jQuery('#btn_save').attr('disabled',true);
		refreshForm(url);
		}
}
//保存前数据检查是否有修改
function saveExists() {
	if (document.getElementById('oldxn').value==document.getElementById('xn').value
	&& document.getElementById('oldxq').value==document.getElementById('xq').value
	&& document.getElementById('oldyf').value==document.getElementById('yf').value
	&& document.getElementById('oldjf').value==document.getElementById('jf').value
	&& document.getElementById('oldkf').value==document.getElementById('kf').value
	&& document.getElementById('oldsx').value==document.getElementById('sx').value) {
		return true;
	} else {
		return false;
	}
}

//批量删除
function deldata(url) {
	var checkBoxArr = document.getElementsByName("cbv");
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (flag){
		if (confirm('确定要删除所选择的数据吗？')){
			document.forms[0].action = url;
			document.forms[0].submit();
			if ($("pt")) {
				BatAlert.showTips('正在操作，请等待...');
			}
		}
	}else{
		alertInfo("没有选择相应记录，请选择之后再进行操作！！");
	}
}

//验证数据格式是否是数字
function ckinpdata(obj){
         if(obj.value==""){		
		   return false;
		}
		obj.value = obj.value.replace(/[^(\d|\.)]/g,'');
		var inputStr = obj.value;		

		if(!(inputStr.match(/\d+/g) || inputStr.match(/\d+\.?\d{0,1}/g)) || inputStr > 100){
			alertInfo('数据格式不正确或者输入值大于100，请重新输入！');
			obj.value = '';
			return false;
		}
		return true;
	}
	
//审核前检查数据是否有修改	
function shExists() {
	if (document.getElementById('sh').value==document.getElementById('oldsh').value
	&& document.getElementById('yj').value==document.getElementById('oldyj').value) {
		return true;
	} else {
		return false;
	}
}	

//审核结果
function shRes(url) {
	document.getElementById('btn_Save').disabled = true;
	refreshForm(url);
}

//保存学生干部信息前检查是否有修改
function savexxExists() {
	if (document.getElementById('oldxn').value==document.getElementById('xn').value
	&& document.getElementById('oldxq').value==document.getElementById('xq').value
	&& document.getElementById('oldjf').value==document.getElementById('jf').value
	&& document.getElementById('olddrzw').value==document.getElementById('drzw').value
	&& document.getElementById('oldrzsj').value==document.getElementById('rzsj').value
	&& document.getElementById('oldkhdj').value==document.getElementById('khdj').value
	&& document.getElementById('oldbz').value==document.getElementById('bz').value) {
		return true;
	} else {
		return false;
	}
}

//数据查询时的提示
function qryChk() {
	var sel = document.getElementsByTagName("select");
	for (i = 0; i < sel.length; i++) {
		if (sel[i].value != null && sel[i].value != '') {
			return false;
		}
	}
	if (document.getElementById('xh').value != '' && document.getElementById('xh').value != null) {
		return false;
	}
	return true;
}

//查询提示
function dataQryChk(url) {
	if (qryChk()) {
		if (confirm("您没有选择任何条件，此次操作将返回全部数据，可能会耗费相当长的时间。确定要继续吗？")) {
			refreshForm(url);
			if ($('search_go')) {
				document.getElementById('search_go').disabled=true;
			}
			return;
		} else {
			return;
		}
	} else {
		refreshForm(url);
		if ($('search_go')) {
				document.getElementById('search_go').disabled=true;
			}
		return;
	}
}

//计算智育总分60%
function countzyZf(){
	var pjcj = document.getElementById('pjcj').value;
	var zyfjf = document.getElementById('zyfjf').value;
	if(pjcj == null || pjcj == '' || pjcj == ' '){
		pjcj = '0';
		document.getElementById('zyzf').value = '';
		document.getElementById('zyxj').value
	} 
	if (zyfjf == null || zyfjf == '' || zyfjf == ' ') {
		zyfjf = '0';
		document.getElementById('zyzf').value== '';
		document.getElementById('zyxj').value
	}
	var tmp = parseFloat(pjcj) + parseFloat(zyfjf);
	if(parseFloat(tmp) == tmp){
		tmp = Math.round(tmp * 100) / 100; 
	} 
	document.getElementById('zyzf').value = tmp;
	var zyzf = document.getElementById('zyzf').value;
	var tmpxj = parseFloat(zyzf)*60/100;
	if(parseFloat(tmpxj) == tmpxj){
		tmpxj = Math.round(tmpxj * 100) / 100; 
	} 
	document.getElementById('zyxj').value = tmpxj;
}

//计算体育总分10%
function counttyZf(){
	var tycj = document.getElementById('tycj').value;
	var tyfjf = document.getElementById('tyfjf').value;
	if (tycj == null || tycj =='' || tycj == ' ') {
		tycj = '0';
		document.getElementById('tyzf').value = '';
		document.getElementById('tyxj').value = '';
	}
	if (tyfjf == null || tyfjf == '' || tyfjf == ' ') {
		tyfjf = '0';
		document.getElementById('tyzf').value = '';
		document.getElementById('tyxj').value = '';
	}
	var tmp = parseFloat(tycj) + parseFloat(tyfjf);
	if(parseFloat(tmp) == tmp){
		tmp = Math.round(tmp * 100) / 100; 
	} 
	document.getElementById('tyzf').value = tmp;
	var tyzf = document.getElementById('tyzf').value;
	var tmpxj = parseFloat(tyzf)*10/100;
	if(parseFloat(tmpxj) == tmpxj){
		tmpxj = Math.round(tmpxj * 100) / 100; 
	} 
	document.getElementById('tyxj').value = tmpxj;
}

//综合素质测评保存前检查是否有修改
function zhszcpsavechk(){
	if (document.getElementById('oldxn').value==document.getElementById('xn').value
	&& document.getElementById('oldxq').value==document.getElementById('xq').value
	&& document.getElementById('oldnd').value==document.getElementById('nd').value
	&& document.getElementById('oldzyfjf').value==document.getElementById('zyfjf').value
	&& document.getElementById('oldtycj').value==document.getElementById('tycj').value
	&& document.getElementById('oldtyfjf').value==document.getElementById('tyfjf').value
	&& document.getElementById('oldbz').value==document.getElementById('bz').value) {
		return true;
	} else {
		return false;
	}
}

//综合素质数据导出
function zhszcpExp(url) {
	if (document.getElementById('bjdm').value == '' || document.getElementById('bjdm').value == null 
	|| document.getElementById('xn').value == '' || document.getElementById('xn').value == null
	|| document.getElementById('xq').value == '' || document.getElementById('xq').value == null) {
		alertInfo('该次数据导出是按学年，学期，班级为单位进行输出，请选择！');
		return;
	} else {
		document.forms[0].action = url;
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
		return;
	}
}



//奖学金修改保存前检查数据是否有修改
function jxjsavechk() {
	if (document.getElementById('oldtzjkbzdj').value == document.getElementById('tzjkbzdj').value
	&& document.getElementById('oldbjpddj').value == document.getElementById('bjpddj').value
	&& document.getElementById('oldjxjdm').value == document.getElementById('jxjdm').value
	&& document.getElementById('oldszxyj').value == document.getElementById('szxyj').value
	&& document.getElementById('oldfdyyj').value == document.getElementById('fdyyj').value){
		return true;
	} else {
		return false;
	}
}	

//检查奖学金是否已通过
function chkjxj(){
	if (curr_row == null || curr_row=='') {
		return false;
	} else {
	var xysh = curr_row.cells[0].getElementsByTagName("input")[1].value;
	var xxsh = curr_row.cells[0].getElementsByTagName("input")[2].value;
	if ((xysh == '通过') || (xxsh == '通过')) {
		return true;
	} else {
		return false;
	}
	}
}

//荣誉称号修改前检查数据是否有修改
function chkrychdata() {
	if (document.getElementById('oldwydj').value == document.getElementById('wydj').value 
	&& document.getElementById('oldjsjdj').value == document.getElementById('jsjdj').value
	&& document.getElementById('oldbjpddj').value == document.getElementById('bjpddj').value
	&& document.getElementById('oldrychdm').value == document.getElementById('rychdm').value
	&& document.getElementById('oldbz').value == document.getElementById('bz').value) {
		return true;
	} else {
		return false;
	}
}

//验证输入字数据不能超过限制字数
function checkLen(obj, len) {
	if (obj.value != null && obj.value.length >= len) {
		obj.value = obj.value.substring(0,len-1);
		alertInfo("输入内容长度不能超过" + len + "字!");
		obj.focus();
		return false;
	}
}

//批量审核
function shdata(url) {
	var checkBoxArr = document.getElementsByName("cbv");
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (flag){
		if (confirm('确定要审核所选择的数据吗？')){
			document.forms[0].action = url;
			document.forms[0].submit();
			if ($("pt")) {
				BatAlert.showTips('正在操作，请等待...');
			}
		}
	}else{
		alertInfo("没有选择相应记录，请选择之后再进行操作！！");
	}
}

//批量删除 针对于通用版的删除
function deletedata(url) {
	var checkBoxArr = document.getElementsByName("primarykey_cbv");
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (flag){
		if (confirm('确定要删除所选择的数据吗？')){
			document.forms[0].action = url;
			document.forms[0].submit();
			if ($("pt")) {
				BatAlert.showTips('正在操作，请等待...');
			}
		}
	}else{
		alertInfo("没有选择相应记录，请选择之后再进行操作！！");
	}
}

//批量审核 针对于通用版审核
function shformdata(url) {
	var checkBoxArr = document.getElementsByName("primarykey_cbv");
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (flag){
		if (confirm('确定要审核所选择的数据吗？')){
			document.forms[0].action = url;
			document.forms[0].submit();
			if ($("pt")) {
				BatAlert.showTips('正在操作，请等待...');
			}
		}
	}else{
		alertInfo("没有选择相应记录，请选择之后再进行操作！！");
	}
}

//验证数据格式是否是数字
function ckinpdataonlynum(obj){
         if(obj.value==""){		
		   return false;
		}
		
		var mouse = event.button;
	var key = event.keyCode;
	//退格，delete，上下左右，放行
	if ((key == 8 || key == 46 
		|| key == 37 || key == 48 
		|| key == 39 || key == 40 ) 
		|| mouse == 1){
		return true;
	}
		obj.value = obj.value.replace(/[^(\d|\.)]/g,'');
		var inputStr = obj.value;		

		if(!(inputStr.match(/\d+/g) || inputStr.match(/\d+\.?\d{0,1}/g))){
			alertInfo('数据格式不正确,只能输入数字,请重新输入！');
			obj.value = '';
			return false;
		}
		return true;
	}
	
	
//打开新窗口
function showInfo(url,doType,w,h){
	
	if(curr_row == null){
		alertInfo('请选择一行！');
		return false;
	}
	
	var dis = curr_row.getElementsByTagName('input')[0].disabled;
	
	if(doType != "view" && dis){
		alertInfo('上级部门审核通过，不能操作！');
		return false;
	}
	
	var pk = curr_row.getElementsByTagName('input')[0].value;
	url+="&doType="+doType;
	url+="&pk="+pk;
	showTopWin(url,w,h);
}

/**
 * 判断修改状态
 * */
function checkUpdateState(){
	var shjg = val("shjb");
	if(shjg != null && shjg != ""){
		var xxsh = val("xxsh");
		if(xxsh == "通过"){
			alertInfo("审核已经通过，暂时不能修改！");
			return false;
		} else {
			return true;
		}
	}else{
		return true;
	}
}