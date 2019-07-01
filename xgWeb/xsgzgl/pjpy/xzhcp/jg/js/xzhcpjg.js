function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add(){
	var url = "xzhcp_zcjg.do?method=add";
	var title = "�ۺϲ����Ǽ�";
	showDialog(title, 770, 550, url);
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
	if(rows[0]['sjly'] != '0'){
		showAlertDivLayer("������̹��������ݲ����޸ģ�");
		return false;
	}
	var url = "xzhcp_zcjg.do?method=update&sqid="+rows[0]["sqid"];
	var title = "�ۺϲ����Ǽ�";
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
			if(row["sjly"] != "0"){
				flag = false;
				return;
			}
		});
		if(!flag){
			showAlertDivLayer("�������¼���ܱ�ɾ��");
			return false;
		}
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("xzhcp_zcjg.do?method=del",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}




var DCCLBH = "pjpy_xzhcp_zcjg.do";
//�Զ��嵼�� ����
function exportConfig(){
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, jgExportData);
}

//��������
function jgExportData(){
	setSearchTj();//���ø߼���ѯ����
	var url = "xzhcp_zcjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//�鿴ѧ������
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='sqView(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function sqView(sqid, xh) {
	showDialog("�鿴", 770, 500, "xzhcp_zcjg.do?method=view&sqid="
			+ sqid + "&xh=" + xh);
}

function printDjb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xzhcp_zcdj.do?method=getSqb";
		url += "&sqid=" + ids+"&flag=jg";
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	} else {
		var url = "xzhcp_zcdj.do?method=getSqbTy";
		url += "&value=" + ids+"&flag=jg";
		window.open(url);
	}
}

function printDjbAhny(){
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xzhcp_zcjg.do?method=getSqbAhny";
		url += "&sqid=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	} else {
		var url = "xzhcp_zcjg.do?method=getSqbTyAhny";
		url += "&value=" + ids;
		window.open(url);
	}
}
