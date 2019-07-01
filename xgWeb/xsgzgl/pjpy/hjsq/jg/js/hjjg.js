function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add(){
	var url = "jxjg.do?method=add";
	var title = "������";
	showDialog(title, 770, 550, url);
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
	if(rows[0]['sjly'] == '1' ){
		showAlertDivLayer("������̹��������ݲ����޸ģ�");
		return false;
	}
	var url = "jxjg.do?method=update&id="+rows[0]["id"];
	var title = "�������޸�";
	showDialog(title, 770, 550, url);
	}
}

//ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var flag = true;
		jQuery(rows).each(function(i,row){
			if(row["sjly"] == "1"){
				flag = false;
				return;
			}
		});
		if(!flag){
			showAlertDivLayer("������̹��������ݲ��ܱ�ɾ����");
			return false;
		}
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("jxjg.do?method=del",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


var DCCLBH = "pjpy_jxjg.do";
//�Զ��嵼�� ����
function exportConfig(){
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, jgExportData);
}

//��������
function jgExportData(){
	setSearchTj();//���ø߼���ѯ����
	var url = "jxjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//�鿴ѧ������
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='sqView(\""
			+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function sqView(sqid, xh) {
	showDialog("�鿴", 770, 500, "jxjg.do?method=view&id="
			+ sqid + "&xh=" + xh);
}

