//��������������Sq
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

//��������������Jg
function checkCsszJg(){
	var xzkg = jQuery("#xzkg").val();
	var kxzkz = jQuery("#kxzkz").val();
	if(xzkg == '0'){
		return false;
	}
	
	return true;
}

// ��ӡ����
function printZdzmSq(url) {
	var xxdm = jQuery("input[name='xxdm']").val();
	var xzkg = jQuery("#xzkg").val();
	var kxzkz = jQuery("#kxzkz").val();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <=0) {
		showAlertDivLayer("��ѡ��һ����¼��");
		return false;
	}
	if(xzkg == '0'){
		showAlertDivLayer("��ǰ�������ò��������ڶ�֤����");
		return false;
	}

	var xhs = [];
	var pyccmc = rows[0]['pyccmc']||'';
	for(i=0;i<rows.length;i++){
		if(rows[i]['shzt'] != '1' && kxzkz == '1'){
			showAlertDivLayer("���ͨ���󷽿������ڶ�֤����");
			return false;
		}
		if((rows[i]['pyccmc']||'') != pyccmc && "10052" == xxdm){
				
			showAlertDivLayer("������α�����ͬ , ��ȷ��!");
			return false;
		}
		xhs.push(rows[i]['xh']);
	}

	if("10052" == xxdm){
		showDialog("�ڶ�֤���ǼǱ�����",380,170,'rcsw_zdzm_sqgl.do?method=print&xhs=' + xhs.toString() + '&pyccmc='+pyccmc);
		return false;
	}
	var url = url + "&xhs=" +xhs.toString();
	window.open(url);
}

//��ӡ����
function printZdzmJg(url) {
	var xxdm = jQuery("input[name='xxdm']").val();
	
	if(!checkCsszJg()){
		showAlertDivLayer("��ǰ�������ò��������ڶ�֤����");
		return false;
	}
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <=0) {
		showAlertDivLayer("��ѡ��һ����¼��");
		return false;
	}
	var xhs = [];
	var pyccmc = rows[0]['pyccmc']||'';
	for(i=0;i<rows.length;i++){
		if((rows[i]['pyccmc']||'') != pyccmc && "10052" == xxdm){
			showAlertDivLayer("������α�����ͬ , ��ȷ��!");
			return false;
		}
		xhs.push(rows[i]['xh']);
	}

	if("10052" == xxdm){
		showDialog("�ڶ�֤���ǼǱ�����",380,170,'rcsw_zdzm_jggl.do?method=print&xhs=' + xhs.toString() + '&pyccmc='+pyccmc);
		return false;
	}
	var url = url + "&xhs=" +xhs.toString();
	window.open(url);
}


function print(url){
	var url2 = url + '&type=' + jQuery('#type').val() + '&xhs=' + jQuery('#xhs').val();
	window.open(url2);
}