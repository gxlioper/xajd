//��ѯ
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='RtjgView(\""
			+ rowObject["rtid"] + "\",\"" + cellValue + "\",\""
			+ rowObject["stid"] + "\");'>" + cellValue
			+ "</a>";
}

function xmmcLink(cellValue, rowObject) {
	if(jQuery("input[name='usertype']").val() != "stu"){
		return "<a href='javascript:void(0);' class='name' onclick='RtjgViewv2(\""
		+ rowObject["stid"] + "\");'>" + cellValue
		+ "</a>";
	}else{
		return cellValue;
	}
}


//�鿴ѧ��ajaxurl��ת
function RtjgView(id, xh,stid) {
	showDialog("���ų�Ա��ϸ��ѯ", 770, 440, "stglRtjg.do?method=Rtjgck&rtid="
			+ id + "&xh=" + xh+"&stid="+stid);
}

//�鿴ѧ��v2(��ѡһ��ѧ����¼������鿴)
function RtjgViewv2(stid){
	showDialog("���ų�Ա��ϸ��ѯ", 770, 440, "stglRtjg.do?method=Rtjgck"
			+"&stid="+stid);
}

//ɾ�����ų�Ա��Ϣ
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("������̹����ļ�¼����ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("stglRtjg.do?method=delCyxx",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "stgl_rtgl_rtjg.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, stcyxxExportData);
}

//��������
function stcyxxExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "stglRtjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//����
function importConfig(){
	toImportDataNew("IMPORT_RTJG");
	return false;
}