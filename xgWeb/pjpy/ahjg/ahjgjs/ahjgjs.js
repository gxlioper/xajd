
//��֤���ݸ�ʽ�Ƿ�������
function ckdata(obj){
		obj.value = obj.value.replace(/[^(\d|\.)]/g,'');
		var inputStr = obj.value;
		if(inputStr >= 100){
			alert('���ݲ��ܴ����100��');
			obj.value = '';
			return;
		}
		if(!(inputStr.match(/\d+/g) || inputStr.match(/\d+\.?\d{0,1}/g)) || inputStr >= 100){
			alert('���ݸ�ʽ����ȷ��ֻ������������С����');
			obj.value = '';
			return;
		}
	}

//��ǩ����¼�
function ahjg_changePage(defaultId){
var title = defaultId.id.substr(0,defaultId.id.length-1);
	var titleName,anotherName;
	var mainTitle = document.getElementById("mainTitle");
	if (title == "xjbj"){
		document.getElementById('xjjtinfo').style.display = "block";
		document.getElementById('xjgrinfo').style.display = "none";
		titleName = "xjbj";
		document.getElementById("titName").value = "xjbj";				
		document.getElementById(titleName+"l").className = "xxk_on_l";
		document.getElementById(titleName+"m").className = "xxk_on_m";
		document.getElementById(titleName+"r").className = "xxk_on_r";
		anotherName = "wmss";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
		mainTitle.innerText = "���ս�����ҵѧԺ�Ƚ��༶�Ƽ���";
	} else if (title == "wmss") {
		document.getElementById('xjjtinfo').style.display = "none";
		document.getElementById('xjgrinfo').style.display = "block";
		titleName = "wmss";
		document.getElementById("titName").value = "wmss";	
		document.getElementById(titleName+"l").className = "xxk_on_l";
		document.getElementById(titleName+"m").className = "xxk_on_m";
		document.getElementById(titleName+"r").className = "xxk_on_r";
		anotherName = "xjbj";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
		mainTitle.innerText = "���ս�����ҵѧԺ���������Ƽ���";
	}
}

//����༶������
function savebjbkl(url) {
	var bjdm = document.getElementById('bjdm').value;
	var bjbkl = document.getElementById('bjbkl').value;
	if (bjdm==''||bjdm==null||bjbkl==''||bjbkl==null||document.getElementById("xn").selectedIndex<=0) {
		alert('�뽫��*�ŵ�������������');
	}else{
		refreshForm(url);
	}
}

//������ݺϷ���
function chkData6(obj){
	var thisdata = obj.value;
	if(thisdata.match(/^\d+\.{0,1}\d{0,3}$/) == null){
		alert("���ݸ�ʽ����ȷ,ֻ����������!");
		obj.value = "";
		return false;
	}
}

//�޸�����
function modidata(url,w,h){
	if (curr_row == null) {
		alert('��ѡ����Ҫ��������!');
	}else{
		if (w==''||w==null||h==''||h==null){
			w=500;
			h=400;
		}
		var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
		url += pkValue;
		showTopWin(url,w,h);
	}
}

//������ٳ�����
function savezccql(url){
var bjdm = document.getElementById('bjdm').value;
	var bjbkl = document.getElementById('zccql').value;
	if (bjdm==''||bjdm==null||bjbkl==''||bjbkl==null||document.getElementById("xn").selectedIndex<=0) {
		alert('�뽫��*�ŵ�������������');
	}else{
		refreshForm(url);
	}
}

//��ֻ֤����������
function chkData1(){
	if (event.keyCode >=48 && event.keyCode <=57){
		event.returnValue = true;
	}else{
		event.returnValue = false;
	}
}

//����༶������
function savezccql(url) {
	var bjdm = document.getElementById('bjdm').value;
	var zccql = document.getElementById('zccql').value;
	if (bjdm==''||bjdm==null||zccql==''||zccql==null||document.getElementById("xn").selectedIndex<=0) {
		alert('�뽫��*�ŵ�������������');
	}else{
		refreshForm(url);
	}
}

//����Ƿ���ѡ�������Ŀ
function chdatanull(){
	if (document.getElementById('shxm').selectedIndex <= 0) {
		alert('�����ĿΪ��ѡ�����ѡ����ٲ�ѯ��');
		return false;
	}
	return true;
}

//�ύ���
function shandSubmit(url,res){
	var checkBoxArr = document.getElementsByName("cbv");
	var flag = false;
	
	for(var i=0;i<checkBoxArr.length;i++){
	if(checkBoxArr[i].checked==true){
			flag = true;
	}
	}
	if (flag){
		BatAlert.showTips('���ڲ�������ȴ�...');
		document.forms[0].action = url+'?act=sh&param1='+res;
		document.forms[0].submit();
		if ($('ts')) {
			BatAlert.showTips('���ڲ�������ȴ�...');
		}
	}else{
		alert("û��ѡ����Ӧ��¼����ѡ��֮����ȷ������");
	}
}


//�޸�����
function modidata1(url,w,h){
	if (curr_row == null) {
		alert('û��ѡ����Ӧ��¼����ѡ��֮����ȷ������');
	}else{
		if (w==''||w==null||h==''||h==null){
			w=500;
			h=400;
		}
		if (curr_row.cells[0].getElementsByTagName("input")[2].value == 'ͨ��ͨ��ͨ��'){
			alert('�������Ѿ���ز�����˲�ͨ�����������޸ģ�');
			return;
		}
		var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
		url += pkValue;
		showTopWin(url,w,h);
	}
}

//��������
function savedata(url){
	var xh = document.getElementById('xh').value;
	if (xh==null || xh=='' || document.getElementById('xn').selectedIndex<=0 || document.getElementById('xxjsdm').selectedIndex<=0 || document.getElementById('nd').selectedIndex<=0){
		alert('��*������Ϊ���');
	}else{
		refreshForm(url);
	}
}

function chkPriseOne2(url, w, h) {
	var xxdm = "";
	if($('xxdm')){
		xxdm = document.getElementById("xxdm").value;
	}
	if (w == null) {
		w = 700;
	}
	if (h == null) {
		h = 500;
	}	
	if (curr_row == null) {
		alert("��ѡ��Ҫ�������У�");
		return false;
	} else {		
		var val = curr_row.cells[0].getElementsByTagName("input")[0].value;		
		url += val;
		url += '&xh=';
		url += curr_row.cells[0].getElementsByTagName("input")[1].value;
		url += '&xn=';
		url += curr_row.cells[0].getElementsByTagName("input")[2].value;
		showTopWin(url, w, h);
}
}

//�����ۺ������ܻ���
function countzf1(){
	var dyjf = document.getElementById('dcj').value;
	var zyjf = document.getElementById('zcj').value;
	var tyjf = document.getElementById('tcj').value;
	if(dyjf == null || dyjf == "" || dyjf == " "){
		dyjf = '0';
		document.getElementById('dcj').value = "";
	}
	if(zyjf == null || zyjf == "" || zyjf == " "){
		zyjf = '0';
		document.getElementById('zcj').value = "";
	}
	if(tyjf == null || tyjf == "" || tyjf == " "){
		tyjf = '0';
		document.getElementById('tcj').value = "";
	}
	document.getElementById('cpf').value = (Math.round(parseInt(dyjf) * 0.3) + parseInt(zyjf) * 0.5 + parseInt(tyjf) * 0.2);

}