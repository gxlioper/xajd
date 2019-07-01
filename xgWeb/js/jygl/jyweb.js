
document.title = jywebXxmc+"就业网";

var pattern = /^\w{6,15}/;


function checkYhm() {
	var yhm = $('yhm').value;
	
	if(yhm != ""){
		if (!pattern.test(yhm)) {
			$('div_yhm').className = "msg_error";
			$('yhm_error_info').innerHTML = "用户名格式不正确！";
		} else {
			isYhmExists();
		}
	}
}

function checkMm() {
	var mm = $('mm').value;
	
	if(mm != ""){
		if (!pattern.test(mm)) {
			$('mm').value = "";
			$('div_mm').className = "msg_error";
		} else {
			$('div_mm').className = "hide";
		}
	}
}

function checkMm2() {
	var mm = $('mm').value;
	var mm2 = $('mm2').value;

	if (mm != mm2) {
		$('mm2').value = "";

		$('div_mm2').className = "msg_error";
	} else {
		$('div_mm2').className = "hide";
	}
}

function isYhmExists() {
	var yhm = $('yhm').value;
	dwr.engine.setAsync(false);

	jyweb.isUserExists('yhm', yhm, function(data) {
		if (data) {
			$('div_yhm').className = "msg_error";
			$('yhm_error_info').innerHTML = "用户名已存在！";
			$('savebutton').disabled = true;
		} else {
			$('div_yhm').className = "hide";
			$('savebutton').disabled = false;
		}
	});
	dwr.engine.setAsync(true);
}

function isDwmcExists() {
	var dwmc = $('dwmc').value;
	dwr.engine.setAsync(false);

	jyweb.isUserExists('dwmc', dwmc, function(data) {
		if (data) {
			$('div_dwmc').className = "msg_error";
			$('dwmc_error_info').innerHTML = "该单位已存在！";
			$('savebutton').disabled = true;
		} else {
			$('div_dwmc').className = "hide";
			$('savebutton').disabled = false;
		}

	});
	dwr.engine.setAsync(true);
}

function init(){
	if($('select_gsmc')){	
	var options = [{dm:"",mc:"----请选择----"}];
	dwr.engine.setAsync(false);
	jyweb.getAllGsmc(function(data){	
		if(data!=null){
			DWRUtil.removeAllOptions('select_gsmc');
			DWRUtil.addOptions('select_gsmc', options,"dm","mc");
			DWRUtil.addOptions('select_gsmc',data,"dwmc","dwmc");
		}
	});
	dwr.engine.setAsync(true);
}
}


function getData(){
var tableName = "view_jygl_dwxxb";
var colList = [ "dwmc", "email", "dz", "dwxzmc", "dwlx", "wz", "hyflmc", "cz", "dwjj" ];
var pk = "dwmc";
var pkValue = $('gsmc').value;
var query = "";

if(pkValue==null || pkValue=="" ){
	$('dz').value = "";
	$('dwxz').value = "";
	$('wz').value = "";
	$('hyfl').value = "";
	$('dwjj').value = "";
	$('cz').value = "";
}else{
dwr.engine.setAsync(false);
getOtherData.getTableListInfo(tableName, colList, pk, pkValue, query,
function(data) {
	gsInfo = data[0];
	if($('dz')){
		if(gsInfo.dz==null){
			gsInfo.dz="";
		}
		$('dz').value=gsInfo.dz;
	}
	if($('dwxz')){
		if(gsInfo.dwxzmc==null){
			gsInfo.dwxzmc="";
		}
		$('dwxz').value=gsInfo.dwxzmc;
	}
	if($('wz')){
		if(gsInfo.wz==null){
			gsInfo.wz="";
		}
		$('wz').value=gsInfo.wz;
	}
	if($('hyfl')){
		if(gsInfo.hyfl==null){
			gsInfo.hyfl="";
		}	
		$('hyfl').value=gsInfo.hyflmc;
	}
	if($('cz')){
		if(gsInfo.cz==null){
			gsInfo.cz="";
		}
		$('cz').value=gsInfo.cz;
	}
	
	if($('dwjj')){
		if(gsInfo.dwjj==null){
			gsInfo.dwjj="";
		}
		$('dwjj').value=gsInfo.dwjj;
	}
});
dwr.engine.setAsync(true);
}
}

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

//反选
function turnSelect(){
	var checkBoxArr = document.getElementsByName("cbv");
	
	for(var i=0;i<checkBoxArr.length;i++){
		if (checkBoxArr[i].checked==true) {
			checkBoxArr[i].checked = false;	
		} else {
			checkBoxArr[i].checked = true;
		}
	}
}

function saveUpdate1(url,pk){
	var key = new Array();
	if(pk!=""){
		key=pk.split("-");
	}
	
	if(key.length > 0){
		for(var i=0;i<key.length;i++){
			if($(key[i])){
				if($(key[i]).value == ""){
					alert("带\"*\"项目不能为空，请确认");
					return false;
				}
			}
		}
	}
	refreshForm(url);
}

//打开新窗口
function showInfo(url,doType,w,h){
	
	if(curr_row != null){
		var dis = curr_row.getElementsByTagName('input')[0].disabled;
	
		if(doType != "view" && dis){
			alert('上级部门审核通过，不能操作！');
			return false;
		}
		
		var pk = curr_row.getElementsByTagName('input')[0].value;
		url+="&doType="+doType;
		url+="&pkValue="+pk;
		showTopWin(url,w,h);
	
	} else {
		alert('请选择一行！');
	}
	
}

//就业网通用登录
function checkLogin(){
		var userName = $('userName').value;
		var password = $('password').value;
		var userType = $('userType').value;
		
		if (""==userName || null==userName){
			alert("用户名不能为空!");
		} else if(""==password || null==password){
			alert("密码不能为空!");
		} else {
			refreshForm('/xgxt/jyweb.do?method=login&doType=login');
		}
	}

	
	
	
	
	
	
var array = [];	
	
	//检测是否有勾中记录,如果是勾中的放入数组供批量操作用
function isChecked(tagName) {
	var checkBoxArr = document.getElementsByName(tagName);
	var flag = false;
	var n = 0;
	for (var i = 0; i < checkBoxArr.length; i++) {
		if (checkBoxArr[i].checked == true) {
			flag = true;
			array[n] = checkBoxArr[i].value;
			n++;
		}
	}
	if (flag) {
		return true;
	} else {
		alert("\u6ca1\u6709\u9009\u62e9\u76f8\u5e94\u8bb0\u5f55\uff0c\u8bf7\u9009\u62e9\u4e4b\u540e\u518d\u8fdb\u884c\u64cd\u4f5c!");
		return false;
	}
}



//选择子节点
function selectSubNode(obj){
	var subNodes = obj.parentNode.parentNode.getElementsByTagName('li');

	for (var i = 0 ; i < subNodes.length ; i++){
		var o = subNodes[i].getElementsByTagName('input')[0];
		if (obj.checked){
			o.checked = true;
		} else {
			o.checked = false;
		}
	}
}

//全部右移
function allRight(){
	var options = new Array();
	var gwdmList = document.getElementsByName("gwdm");

	for (var i = 0 ; i < gwdmList.length ; i++){
		var mc = gwdmList[i].value.split("!!@!!")[0];
		var dm = gwdmList[i].value.split("!!@!!")[1];
		var dwmc = gwdmList[i].value.split("!!@!!")[2];
		
		options[i]={dm:dm,mc:mc+"("+dwmc+")"};
	}
	DWRUtil.removeAllOptions('yxz');
	DWRUtil.addOptions('yxz',options,"dm","mc");
}

//清除
function allleft(){
	DWRUtil.removeAllOptions('yxz');
}

//单个右移
function turnRight(){
	var options = new Array();
	var gwdmList = document.getElementsByName("gwdm");
	var n = 0;
	
	for (var i = 0 ; i < gwdmList.length ; i++){
		var mc = gwdmList[i].value.split("!!@!!")[0];
		var dm = gwdmList[i].value.split("!!@!!")[1];
		var dwmc = gwdmList[i].value.split("!!@!!")[2];
		
		if (gwdmList[i].checked && !isExists('yxz',dm)){
			options[n]={dm:dm,mc:mc+"("+dwmc+")"};
			n++
		}
	}
	DWRUtil.addOptions('yxz',options,"dm","mc");
}

//单个清除
function turnLeft(){
	isExists('yxz',$('yxz').value,true);
}

//判断是否存在
function isExists(id,value,isDel){
	var options = $(id).options;

	for (var i=0 ; i<options.length ; i++){
		if (options[i].value==value){
			if (isDel){
				options.remove(i);
			}
			return true;
		}
	}
	return false;
}


function loginClass(text){
			
	var type = ''==text ? "stu" : text;
	var realName = $('realName').value;
	
	if (''==realName || null == realName) {
		if ('stu'==type) {
			$('dw').className = "";
			$('xy').className = "";
			$('btn_zc').disabled=true;
			$('userType').value='stu';
		} else if('dw'==type){
			$('stu').className = "";
			$('xy').className = "";
			$('btn_zc').disabled=false;
			$('userType').value='dw';
		} else if('xy'==type){
			$('stu').className = "";
			$('dw').className = "";
			$('btn_zc').disabled=true;
			$('userType').value='xy';
		}
		if ($(type)){
			$(type).className = "current";
		}
	}
	
}


function setZpxx(liId,urId){
			
	var lis = $(urId).getElementsByTagName('li');
	
	for (var i = 0 ; i < lis.length ; i++){
		if (lis[i].id == liId){
			lis[i].className = "current";
		} else {
			lis[i].className = "";
		}
	}
	
	
	if ("xnzp" == liId){
		$('xnzpList').style.display="";
		$('qyzpList').style.display="none";
		$('zphList').style.display="none";
		$('jyzsRs').style.display="none";
	} else if("qyzp" == liId){
		$('xnzpList').style.display="none";
		$('qyzpList').style.display="";
		$('zphList').style.display="none";
		$('jyzsRs').style.display="none";
	} else if("jyzs" == liId){
		$('xnzpList').style.display="none";
		$('qyzpList').style.display="none";
		$('zphList').style.display="none";
		$('jyzsRs').style.display="";
	} else{
		$('xnzpList').style.display="none";
		$('qyzpList').style.display="none";
		$('zphList').style.display="";
		$('jyzsRs').style.display="none";
		
		var n=0;
		var xtzpList = $('zphList').getElementsByTagName('li');

		for (var i=0 ; i<xtzpList.length ;i++){
			if (xtzpList[i].value == liId && n!=7){
				xtzpList[i].style.display="";
				n++;
			} else{
				xtzpList[i].style.display="none";
			}
		}
	}
	
}

function setRctj(liId,urId){

	var lis = $(urId).getElementsByTagName('li');
	
	for (var i = 0 ; i < lis.length ; i++){
		if (lis[i].id == liId){
			lis[i].className = "current";
		} else {
			lis[i].className = "";
		}
	}

	if ("rctj" == liId){
		$('rcTab').style.display = "";
		$('xtzpDiv').style.display = "none";
		$('div6').className = "index_news03";
		$('rcHerf').href = "jyweb_qzyx.do";
	} else {
		$('rcTab').style.display = "none";
		$('xtzpDiv').style.display = "";
		$('div6').className = "index_news03";
		$('rcHerf').href = "jyweb_csyg.do";
		
		var n=0;
		var xtzpList = $('xtzpList').getElementsByTagName('li');

		for (var i=0 ; i<xtzpList.length ;i++){
			if (xtzpList[i].value == liId && n!=7){
				xtzpList[i].style.display="";
				n++;
			} else{
				xtzpList[i].style.display="none";
			}
		}
		
	}
	
}
