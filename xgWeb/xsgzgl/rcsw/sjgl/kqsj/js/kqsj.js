var DCCLBH = "rcswx_sjgl_kqsj.do";//dcclbh,�������ܱ��
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function add(){
	var url = "kqsj.do?method=addKqsj";
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
				jQuery.post("kqsj.do?method=delKqsj", {
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
		var url = 'kqsj.do?method=updateKqsj&id='+ rows[0]["id"]
		+ '&xh=' + rows[0]["xh"];
		var title = "�޸Ŀ�������";
		showDialog(title, 800, 500, url);
	}
}

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, kqsjExportData);
}

//��������
function kqsjExportData() {	
	setSearchTj();//���ø߼���ѯ����
	var url = "kqsj.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();	
}



function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='kqsjView(\""
	+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
	+ "</a>";
}

//�鿴
function kqsjView(id,xh){
	showDialog('�������ݲ鿴',800,400,'kqsj.do?method=viewKqsj&id='+id + "&xh=" + xh);
}

function importKqsj(){
	toImportDataNew("IMPORT_KQSJ");
	return false;
}

