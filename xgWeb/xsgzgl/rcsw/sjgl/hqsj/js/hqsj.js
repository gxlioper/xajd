var DCCLBH = "rcswx_sjgl_hqsj.do";//dcclbh,�������ܱ��
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function add(){
	var url = "hqsj.do?method=hqsjAdd";
	var title = "������������";
	showDialog(title,800,500,url);
}

function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	}
	else {	
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("hqsj.do?method=hqsjDel", {
					values : ids.toString()
				}, function(data) {
					var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>������";
					mes+="</br>";
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});		
	}
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();	
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	}else{
		var url = 'hqsj.do?method=hqsjUpdate&id='+ rows[0]["id"]
		+ '&xh=' + rows[0]["xh"];
		var title = "�޸Ŀ�������";
		showDialog(title, 800, 500, url);
	}
}

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, hqsjExportData);
}

//��������
function hqsjExportData() {	
	setSearchTj();//���ø߼���ѯ����
	var url = "hqsj.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();	
}



function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='hqsjView(\""
	+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
	+ "</a>";
}

//�鿴
function hqsjView(id,xh){
	showDialog('�������ݲ鿴',800,400,'hqsj.do?method=hqsjView&id='+id + "&xh=" + xh);
}

function importHqsj(){
	toImportDataNew("IMPORT_HQSJ");
	return false;
}

