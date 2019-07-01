function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//������ְ���˽��
function add() {
	var url = "szdw_xsgb_rzkhjg.do?method=add";
	var title = "����ѧ���ɲ����˽��";
	showDialog(title, 770, 535, url);
}

//�����޸���ְ���˽������
function saveKhjg(type){
	var ids = "zwmc"+"-"+"rzsj"+"-"+"xn"+"-"+"xq"+"-"+"grzp"+"-"+"zgdwyj";
	var idss = "grsz"+"-"+"qdyj"+"-"+"szdwyj"+"-"+"ddyj"+"-"+"xsgzcyj"+"-"+"bz";
	var names = "������ְ"+"-"+"�������"+"-"+"���ڵ�λ���"+"-"+"������"+"-"+"ѧ�����������"+"-"+"��ע";
	var lens = "50"+"-"+"500"+"-"+"500"+"-"+"500"+"-"+"500"+"-"+"500";
	if(check(ids) == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	if(checkzs(idss,names,lens) == false){
		return false;
	}
	var url = "szdw_xsgb_rzkhjg.do?method=saveKhjg&type=" + type;
	ajaxSubFormWithFun("rzkhjgForm", url, function(data) {
		 if(data["message"]=="����ɹ���"){
    		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
    	 }else{
    		 showAlert(data["message"]);
    		}
		});
}

//ɾ�����˽��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		/*
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("������̹����ļ�¼����ɾ����");
				return false;
			}
		}*/
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("szdw_xsgb_rzkhjg.do?method=delKhjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//�鿴ѧ������
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='KhjgView(\""
			+ rowObject["khjgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//�鿴ѧ��ajaxurl��ת��ֱ�ӵ��ѧ�����ӣ�
function KhjgView(khjgid, xh) {
	showDialog("�鿴ѧ���ɲ����˽��", 770, 535, "szdw_xsgb_rzkhjg.do?method=KhjgView&khjgid="
			+ khjgid + "&xh=" + xh);
}

//�鿴ѧ��v2(��ѡһ��ѧ����¼������鿴)
function KhjgViewv2(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (parseInt(ids.length) != 1){
		showAlertDivLayer("��ѡ��һ��ѧ����¼���в鿴��");
		return false;
	}
	showDialog("�鿴ѧ���ɲ����˽��", 770, 535, "szdw_xsgb_rzkhjg.do?method=KhjgView&khjgid="
			+ ids);
}

//�޸�ѧ���ɲ����˽��
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		/*
		if(rows[0]['sjly']=='1'){
			showAlertDivLayer("������̹����ļ�¼�����޸ģ�");
			return false;
		}*/
		var url = 'szdw_xsgb_rzkhjg.do?method=updateKhjg&khjgid=' + rows[0]["khjgid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "�޸�ѧ���ɲ����˽��";
		showDialog(title, 770, 535, url);
	}
}

var DCCLBH = "szdw_xsgb_rzkhjg.do";//dcclbh,�������ܱ��
//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, xyzsjgExportData);
}

//��������
function xyzsjgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "szdw_xsgb_rzkhjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//ѧ���ɲ���ְ���˱���
function printXyzsjgsqb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "szdw_xsgb_rzkhjg.do?method=getGbrzkhb";
		url += "&khjgid=" + ids+"&flag=jg";
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	} else {
		var url = "szdw_xsgb_rzkhjg.do?method=getKhjgkhbTy";
		url += "&value=" + ids+"&flag=jg";
		window.open(url);
	}
}

//ѧ���ɲ���ְ���˻��ܱ���
function printKhhzb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "szdw_xsgb_rzkhjg.do?method=getKhhzb";
		url += "&khjgid=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	} else {
		var url = "szdw_xsgb_rzkhjg.do?method=getKhhzbTy";
		url += "&value=" + ids;
		window.open(url);
	}
}

//����
function importConfig(){
	toImportDataNew("IMPORT_N470806_XSGBKH");
	return false;
}

/**
 * ��֤�Ƿ���ڿ���
 * @param ids Ҫ��֤�Ŀؼ�id "-"�ָ�
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}

//��ע�ȳ��ֶ�����
function checkzs(ids,names,lens){
	var id=ids.split("-");
	var name=names.split("-");
	var lenth =lens.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm.length>lenth[i]){
			showAlert(name[i]+"����ĳ��Ȳ��ܳ���"+lenth[i]+"����ȷ�ϣ�");
			jQuery("#"+id[i]).focus();
			return false;
		}
	}
}

function checkzsonKeyup(obj,lenth){
	if(obj.value.length>parseInt(lenth)){
		showAlert("����ĳ��Ȳ��ܳ���"+lenth+",��ȷ��");
	}
}
