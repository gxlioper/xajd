//�����������ʴ�ѧ����ע�ᣭ������
var array = new Array();

//����Ƿ��й��м�¼,����ǹ��еķ������鹩����������
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
		if (confirm('ȷ��Ҫ����������ѡ���������')){
			return true;
		}
	}else{
		alert("û��ѡ����Ӧ��¼����ѡ��֮���ٽ��в���!");
		return false;
	}
}

//�ر���ʱDIV
function closeDiv() {
	$('tempDiv').style.display='none';
}


//���ʱ�������Ƿ��������Ƿ����
function checkSjsz() {
	var zckssj = $('sz_zckssj').value;
	var zcjssj = $('sz_zcjssj').value;
	var hzcjssj = $('sz_hzcjssj').value;
	
	if (''==zckssj || ''==zcjssj) {
		alert('��Ѵ�*����д����');
		return false;
	} else if (zckssj > zcjssj) {
		alert('ע�����ʱ�䲻�����ڿ�ʼʱ��');
		return false;
	} else if (''!=hzcjssj && zckssj > hzcjssj) {
		alert('��ע�����ʱ�䲻�����ڿ�ʼʱ��');
		return false;
	}
	
	plsz(zckssj,zcjssj,hzcjssj);
}

//��������ע�Ὺʼʱ�䡢ע�����ʱ�䡢��ע�����ʱ��
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



//ʱ������DIV
function showPlszDiv() {

	if (!isChecked("primarykey_cbv")){
		return false;
	}

	$('tempDiv').style.display='';
	viewTempDiv('ʱ������','sjszDiv',300, 200);
}	


function showCalen(id){
	return showCalendar(id,'y-mm-dd');
}