var DCCLBH = "pjpy_zyjgl_zyjwh.do";//dcclbh,�������ܱ��
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function add(){
	var url = "pjpy_zyjwhwh.do?method=addGrzyj";
	var title = "רҵ������";
	showDialog(title,800,500,url);
}

function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	}
	else {
		var flag = false;
		jQuery.each(rows,function(i,n) {
			if(n['rddjmc'] != 'δ�϶�') {
				flag = true;
				return false;						
				}			
			})
		if(flag) {
			showAlertDivLayer("�����ȼ��ѱ��϶�����ɾ��");
			return false;
		}	
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("pjpy_zyjwhwh.do?method=delGrzyj", {
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
	}else {
		var flag = false;
		jQuery.each(rows,function(i,n) {
			if(n['rddjmc'] != 'δ�϶�') {
				flag = true;
				return false;						
				}			
			})
		if(flag) {
			showAlertDivLayer("�����ȼ��ѱ��϶������޸�");
			return false;
		}
		var url = 'pjpy_zyjwhwh.do?method=updateGrzyj&id='+ rows[0]["id"]
		+ '&xh=' + rows[0]["xh"];
		var title = "�޸�רҵ����Ϣ";
		showDialog(title, 800, 500, url);
	}

}

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, xszbbjgExportData);
}

//��������
function xszbbjgExportData() {	
	setSearchTj();//���ø߼���ѯ����
	var url = "pjpy_zyjwhwh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();	
}

function rending() {
	var rows = jQuery("#dataTable").getSeletRow();	
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�϶��ļ�¼��");
	}else {
		var url = 'pjpy_zyjwhwh.do?method=rendingGrzyj&id='+ rows[0]["id"]
		+ '&xh=' + rows[0]["xh"];
		var title = "�ȼ��϶�";
		showDialog(title, 800, 600, url);
	}
}

function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='grzyjView(\""
	+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
	+ "</a>";
}

function grzyjView(id,xh){
	showDialog('����רҵ��ά���鿴',800,600,'pjpy_zyjwhwh.do?method=grzyjView&id='+id + "&xh=" + xh);
}

