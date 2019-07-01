//－－－－地质大学报到注册－－－－
var array = new Array();

//检测是否有勾中记录,如果是勾中的放入数组供批量操作用
function isChecked(tagName) {
	var checkBoxArr = document.getElementsByName(tagName);
	var flag = false;
	var n=0;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
			array[n]=checkBoxArr[i];
			n++;
		}
	}
	if (flag){
		if (confirm('确定要批量设置所选择的数据吗？')){
			return true;
		}
	}else{
		alert("没有选择相应记录，请选择之后再进行操作!");
		return false;
	}
}

//关闭临时DIV
function closeDiv() {
	$('tempDiv').style.display='none';
}


//检查时间设置是否完整、是否合理
function checkSjsz() {
	var zckssj = $('sz_zckssj').value;
	var zcjssj = $('sz_zcjssj').value;
	var hzcjssj = $('sz_hzcjssj').value;
	
	if (''==zckssj || ''==zcjssj) {
		alert('请把带*项填写完整');
		return false;
	} else if (zckssj > zcjssj) {
		alert('注册结束时间不能早于开始时间');
		return false;
	} else if (''!=hzcjssj && zckssj > hzcjssj) {
		alert('缓注册结束时间不能早于开始时间');
		return false;
	}
	
	plsz(zckssj,zcjssj,hzcjssj);
}

//批量设置注册开始时间、注册结束时间、缓注册结束时间
function plsz(zckssj,zcjssj,hzcjssj) {
	if (array.length > 0) {
		for ( var i=0 ; i<array.length; i++) {
			array[i].parentNode.parentNode.getElementsByTagName('input')[2].value=zckssj;
			array[i].parentNode.parentNode.getElementsByTagName('input')[3].value=zcjssj;
			array[i].parentNode.parentNode.getElementsByTagName('input')[4].value=hzcjssj;
		}
	}
	hiddenMessage(true,true);
}



//时间设置DIV
function showPlszDiv() {

	if (!isChecked("primarykey_cbv")){
		return false;
	}

	$('tempDiv').style.display='';
	viewTempDiv('时间设置','sjszDiv',300, 200);
}	


function showCalen(id){
	return showCalendar(id,'y-mm-dd');
}