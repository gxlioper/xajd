//�����·��б�
function loadyf(val){
	if (document.getElementById(val).value=='') {
		document.getElementById('yf').disabled = true;
	} else {
		document.getElementById('yf').disabled = false;
	}
}

//�޸ĺ�ɾ��
function modiAndDel(url,type,w,h) {
	if ($('disli') && $('disli').value=='0') {
		
	} else {
		if (curr_row==null || curr_row=='') {
			alertInfo('��ѡ��Ҫ�����������У�');
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
				if (confirm('ȷ��Ҫɾ����ѡ���������')) {
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
	if (xysh=='ͨ��' || xxsh=='ͨ��') {
		return true;
	} else {
		return false;
	}
}

//������Ϣ
function saveinfo() {
	
	var xh =document.getElementById('xh').value;
	if(xh==""){
		alertInfo("��ѡ��ѧ�ţ�");
		return false;
	}
	var xn =document.getElementById('xn').value;
	if(xn==""){
		alertInfo("��ѡ��ѧ�꣡");
		return false;
	}
	var zxdkmc =document.getElementById('zxdkmc').value;
	if(zxdkmc==""){
		alertInfo("��ѡ����ѧ�������ƣ�");
		return false;
	}
	var dkje =document.getElementById('dkje').value;
	if(dkje==""){
		alertInfo("����������");
		return false;
	}
	var bz =document.getElementById('bz').value;
	if(bz.length > 255){
		alertInfo("����������ݹ��࣬�뽫����������255���ַ����ڣ�");
		return false;
	}
	confirmInfo('�Ƿ�ȷ�ϱ���',saveAddInfo);
	
}
function saveAddInfo(tag) {
	if(tag=="ok"){
		var val="xh-xn-nd";
		var arrayList = val.split('-');
		for (var i=0; i<arrayList.length;i++) {
			if ($(arrayList[i])) {
				if (jQuery('#'+arrayList[i]).value=='') {
					alertInfo("�뽫��\"*\"�ŵ���Ŀ����������");
					return false;
				}
			}
		}
		jQuery('#btn_save').attr('disabled',true);
		refreshForm("zxdk_gzdx_addZxdkSjwh.do?operType=save");
	}
}
//�����޸���Ϣ
function saveupdateinfo() {
	var xn =document.getElementById('xn').value;
	if(xn==""){
		alertInfo("��ѡ��ѧ�꣡");
		return false;
	}
	var zxdkmc =document.getElementById('zxdkmc').value;
	if(zxdkmc==""){
		alertInfo("��ѡ����ѧ�������ƣ�");
		return false;
	}
	var dkje =document.getElementById('dkje').value;
	if(dkje==""){
		alertInfo("����������");
		return false;
	}
	var bz =document.getElementById('bz').value;
	if(bz.length > 255){
		alertInfo("����������ݹ��࣬�뽫����������255���ַ����ڣ�");
		return false;
	}
	confirmInfo('�Ƿ�ȷ�ϱ����޸���',saveupdate);
}

function saveupdate(tag) {
		if(tag=="ok"){
		var url ="zxdk_gzdx_updateZxdkSjwh.do?operType=save";
		var val ="xh-xn-zxdkmc-dkje-bz";
		var arrayList = val.split('-');
		for (var i=0; i<arrayList.length;i++) {
			if ($(arrayList[i])) {
				if (jQuery('#'+arrayList[i]).value=='') {
					alertInfo("�뽫��\"*\"�ŵ���Ŀ����������");
					return false;
				}
			}
		}
		jQuery('#btn_save').attr('disabled',true);
		refreshForm(url);
		}
}
//����ǰ���ݼ���Ƿ����޸�
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

//����ɾ��
function deldata(url) {
	var checkBoxArr = document.getElementsByName("cbv");
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (flag){
		if (confirm('ȷ��Ҫɾ����ѡ���������')){
			document.forms[0].action = url;
			document.forms[0].submit();
			if ($("pt")) {
				BatAlert.showTips('���ڲ�������ȴ�...');
			}
		}
	}else{
		alertInfo("û��ѡ����Ӧ��¼����ѡ��֮���ٽ��в�������");
	}
}

//��֤���ݸ�ʽ�Ƿ�������
function ckinpdata(obj){
         if(obj.value==""){		
		   return false;
		}
		obj.value = obj.value.replace(/[^(\d|\.)]/g,'');
		var inputStr = obj.value;		

		if(!(inputStr.match(/\d+/g) || inputStr.match(/\d+\.?\d{0,1}/g)) || inputStr > 100){
			alertInfo('���ݸ�ʽ����ȷ��������ֵ����100�����������룡');
			obj.value = '';
			return false;
		}
		return true;
	}
	
//���ǰ��������Ƿ����޸�	
function shExists() {
	if (document.getElementById('sh').value==document.getElementById('oldsh').value
	&& document.getElementById('yj').value==document.getElementById('oldyj').value) {
		return true;
	} else {
		return false;
	}
}	

//��˽��
function shRes(url) {
	document.getElementById('btn_Save').disabled = true;
	refreshForm(url);
}

//����ѧ���ɲ���Ϣǰ����Ƿ����޸�
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

//���ݲ�ѯʱ����ʾ
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

//��ѯ��ʾ
function dataQryChk(url) {
	if (qryChk()) {
		if (confirm("��û��ѡ���κ��������˴β���������ȫ�����ݣ����ܻ�ķ��൱����ʱ�䡣ȷ��Ҫ������")) {
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

//���������ܷ�60%
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

//���������ܷ�10%
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

//�ۺ����ʲ�������ǰ����Ƿ����޸�
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

//�ۺ��������ݵ���
function zhszcpExp(url) {
	if (document.getElementById('bjdm').value == '' || document.getElementById('bjdm').value == null 
	|| document.getElementById('xn').value == '' || document.getElementById('xn').value == null
	|| document.getElementById('xq').value == '' || document.getElementById('xq').value == null) {
		alertInfo('�ô����ݵ����ǰ�ѧ�꣬ѧ�ڣ��༶Ϊ��λ�����������ѡ��');
		return;
	} else {
		document.forms[0].action = url;
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
		return;
	}
}



//��ѧ���޸ı���ǰ��������Ƿ����޸�
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

//��齱ѧ���Ƿ���ͨ��
function chkjxj(){
	if (curr_row == null || curr_row=='') {
		return false;
	} else {
	var xysh = curr_row.cells[0].getElementsByTagName("input")[1].value;
	var xxsh = curr_row.cells[0].getElementsByTagName("input")[2].value;
	if ((xysh == 'ͨ��') || (xxsh == 'ͨ��')) {
		return true;
	} else {
		return false;
	}
	}
}

//�����ƺ��޸�ǰ��������Ƿ����޸�
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

//��֤���������ݲ��ܳ�����������
function checkLen(obj, len) {
	if (obj.value != null && obj.value.length >= len) {
		obj.value = obj.value.substring(0,len-1);
		alertInfo("�������ݳ��Ȳ��ܳ���" + len + "��!");
		obj.focus();
		return false;
	}
}

//�������
function shdata(url) {
	var checkBoxArr = document.getElementsByName("cbv");
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (flag){
		if (confirm('ȷ��Ҫ�����ѡ���������')){
			document.forms[0].action = url;
			document.forms[0].submit();
			if ($("pt")) {
				BatAlert.showTips('���ڲ�������ȴ�...');
			}
		}
	}else{
		alertInfo("û��ѡ����Ӧ��¼����ѡ��֮���ٽ��в�������");
	}
}

//����ɾ�� �����ͨ�ð��ɾ��
function deletedata(url) {
	var checkBoxArr = document.getElementsByName("primarykey_cbv");
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (flag){
		if (confirm('ȷ��Ҫɾ����ѡ���������')){
			document.forms[0].action = url;
			document.forms[0].submit();
			if ($("pt")) {
				BatAlert.showTips('���ڲ�������ȴ�...');
			}
		}
	}else{
		alertInfo("û��ѡ����Ӧ��¼����ѡ��֮���ٽ��в�������");
	}
}

//������� �����ͨ�ð����
function shformdata(url) {
	var checkBoxArr = document.getElementsByName("primarykey_cbv");
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (flag){
		if (confirm('ȷ��Ҫ�����ѡ���������')){
			document.forms[0].action = url;
			document.forms[0].submit();
			if ($("pt")) {
				BatAlert.showTips('���ڲ�������ȴ�...');
			}
		}
	}else{
		alertInfo("û��ѡ����Ӧ��¼����ѡ��֮���ٽ��в�������");
	}
}

//��֤���ݸ�ʽ�Ƿ�������
function ckinpdataonlynum(obj){
         if(obj.value==""){		
		   return false;
		}
		
		var mouse = event.button;
	var key = event.keyCode;
	//�˸�delete���������ң�����
	if ((key == 8 || key == 46 
		|| key == 37 || key == 48 
		|| key == 39 || key == 40 ) 
		|| mouse == 1){
		return true;
	}
		obj.value = obj.value.replace(/[^(\d|\.)]/g,'');
		var inputStr = obj.value;		

		if(!(inputStr.match(/\d+/g) || inputStr.match(/\d+\.?\d{0,1}/g))){
			alertInfo('���ݸ�ʽ����ȷ,ֻ����������,���������룡');
			obj.value = '';
			return false;
		}
		return true;
	}
	
	
//���´���
function showInfo(url,doType,w,h){
	
	if(curr_row == null){
		alertInfo('��ѡ��һ�У�');
		return false;
	}
	
	var dis = curr_row.getElementsByTagName('input')[0].disabled;
	
	if(doType != "view" && dis){
		alertInfo('�ϼ��������ͨ�������ܲ�����');
		return false;
	}
	
	var pk = curr_row.getElementsByTagName('input')[0].value;
	url+="&doType="+doType;
	url+="&pk="+pk;
	showTopWin(url,w,h);
}

/**
 * �ж��޸�״̬
 * */
function checkUpdateState(){
	var shjg = val("shjb");
	if(shjg != null && shjg != ""){
		var xxsh = val("xxsh");
		if(xxsh == "ͨ��"){
			alertInfo("����Ѿ�ͨ������ʱ�����޸ģ�");
			return false;
		} else {
			return true;
		}
	}else{
		return true;
	}
}