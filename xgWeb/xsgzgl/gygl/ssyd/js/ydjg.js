
// �鿴ѧ���춯��Ϣ
function xhLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='seeXsydInfo(\""+row["xh"]+"\")'>"+cellValue+"</a>";
}

//��ע������ȡ
function bzSubstring(cellValue,row){
	if(cellValue==null){
		cellValue="";
	}
	var strValue = cellValue;
	if(strValue.length > 10){
		strValue = strValue.substring(0, 10)+"...";
	}
	return "<span title='"+cellValue+"'>"+strValue+"</span>";;
}
//�鿴���޴�λ��Ϣ
function tscwLink(cellValue,row){
	if( cellValue!=null && cellValue!=""){
		return "<a href='javascript:void(0);' class='name' onClick='seeCwInfo(\""+row["ydqlddm"]+"\",\""+row["ydqqsh"]+"\",\""+row["ydqcwh"]+"\")'>"+cellValue+"</a>";
	}else{
		return "";
	}
}

//�鿴��ס��λ��Ϣ
function rzcwLink(cellValue,row){
	if( cellValue!=null && cellValue!=""){
		return "<a href='javascript:void(0);' class='name' onClick='seeCwInfo(\""+row["ydhlddm"]+"\",\""+row["ydhqsh"]+"\",\""+row["ydhcwh"]+"\")'>"+cellValue+"</a>";
	}else{
		return "";
	}
}


//��סʱ��
function rzsjLink(cellValue,row){
	if( cellValue!=null && cellValue!=""){
		return cellValue.substr(0,10);
	}else{
		return "";
	}
}




//�鿴��λ��Ϣ
function seeCwInfo(lddm,qsh,cwh){
	showDialog("�鿴��λ��Ϣ",850,350,"ydjg.do?method=ckQsydInfo&ydqlddm="+lddm+"&ydqqsh="+qsh+"&ydqcwh="+cwh,null);
}


//�鿴ѧ���춯��Ϣ
function seeXsydInfo(xh){
	showDialog("�鿴ѧ�������춯��Ϣ",650,480,"ydjg.do?method=ckXsydInfo&xh="+xh,null);
}
//�鿴���������춯��Ϣ
function ckYdjg(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ�鿴�ļ�¼��");
	}  else{
		showDialog("�����춯��Ϣ",760,390,'ydjg.do?method=ydjgck&ssydid='+rows[0]["ssydid"]);
	}
}

//����
function addYdjg() {
		var url ="ydjg.do?method=add";
		var title = "�����춯����";
		showDialog(title, 800, 490, url);
}

//��ѯ
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function exportConfig() {
	customExport("ydjgbase.do", exportData,690,500);
}
// ��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "ydjg.do?method=exportData&dcclbh=ydjgbase.do";//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//��ӡ����
function printTstzd() {
	var ssydid = jQuery("#dataTable").getSeletIds();
	if (ssydid.length <=0) {
		showAlertDivLayer("��ѡ��һ����¼��");
		return false;
	}
	
	var rows = jQuery("#dataTable").getSeletRow();
	
	for(var i=0; i<ssydid.length; i++){
		if("����"==rows[i]['ssydlxmc'] || "��ס"==rows[i]['ssydlxmc']){
			showAlertDivLayer('"��ס"/"����"���ܴ�ӡ"����֪ͨ��"');
			return false;
		}
	}
	
	var url = "ydjg.do?method=printTstzd&ssydid=" +ssydid.toString();
	
	window.open(url);
}

function printTsd() {
	var ssydid = jQuery("#dataTable").getSeletIds();
	if (ssydid.length <=0) {
		showAlertDivLayer("��ѡ��һ����¼��");
		return false;
	}
	
	var rows = jQuery("#dataTable").getSeletRow();
	
	for(var i=0; i<ssydid.length; i++){
		if("�������"==rows[i]['ssydlxmc'] || "��ס"==rows[i]['ssydlxmc']){
			showAlertDivLayer('"�������"/"��ס"���ܴ�ӡ"���޵�"');
			return false;
		}
	}
	
	var url = "ydjg.do?method=printTsd&ssydid=" +ssydid.toString();
	
	window.open(url);
}
