//����
function addWyjl(){
	showDialog("����ΥԼ��¼",800,500,"zxdkWyjl.do?method=addWyjl");
}

//�޸�
function editWyjl(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {		
		showDialog("�޸�ΥԼ��¼",800,500,"zxdkWyjl.do?method=editWyjl&xh="+rows[0]["xh"]);
	}
}

//ɾ��
function delWyjl(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ����������",{"okFun":function(){
			jQuery.post("zxdkWyjl.do?method=delWyjl",{ids:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function exportConfig(){
	var DCCLBH='zxdk_wyjl.do';
	customExport(DCCLBH, exportData);
}

function exportData(){
	var DCCLBH='zxdk_wyjl.do';
	setSearchTj();//���ø߼���ѯ����
	
	var url = "zxdkWyjl.do?method=dcwy&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function ckWyjl(xh){
	showDialog("�鿴ΥԼ��¼",800,520,"zxdkWyjl.do?method=ckWyjl&xh="+xh);
}

function importYwxx(){
	toImportDataNew("ZXDK_XYDDK_WYJL");
	return false;
}