function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add(){
	var url = "jxsq.do?method=add";
	var title = "��������";
	showDialog(title, 770, 550, url);
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
	if(rows[0]['shzt'] != '0' && rows[0]['shzt'] != '3'){
		showAlertDivLayer("ֻ���޸�δ�ύ�����˻صļ�¼��");
		return false;
	}
	var url = "jxsq.do?method=update&id="+rows[0]["id"];
	var title = "���������޸�";
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
			if(row["shzt"] != "0" && row["shzt"] != "3"){
				flag = false;
				return;
			}
		});
		if(!flag){
			showAlertDivLayer(jQuery("#lable_wjt_sc").val());
			return false;
		}
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("jxsq.do?method=del",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//�ύ
function submitBusi(){
	var isopen = jQuery("#isopen").val();
	if(isopen=='false'){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1){
		return showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
			showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
			return false;
		}
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("jxsq.do?method=submitBusi", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

//����
function cancel() {
	var isopen = jQuery("isopen").val();
	if(isopen=='false'){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else if (ids.length > 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['shzt'] != '5') {
				showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
				return false;
			}
		}

		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("jxsq.do?method=cancelSq", {
					values : ids.toString(),
					splcid : rows[0]['splc']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}

}

//���̸���
function lcgz() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (1 != rows.length) {
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
		return false;
	} else {
		var shzt = rows[0]["shzt"];
		if ("0" == shzt) {
			showAlertDivLayer(jQuery("#lable_wxglcxx").val());
			return false;
		}
		showDialog("�����������̸���",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['id'] + "&splc=" + rows[0]['splc']);
	}
}
var DCCLBH = "pjpy_jxsq.do";
//�Զ��嵼�� ����
function exportConfig(){
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, jgExportData);
}

//��������
function jgExportData(){
	setSearchTj();//���ø߼���ѯ����
	var url = "jxsq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
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
	showDialog("�鿴", 770, 500, "jxsq.do?method=view&id="
			+ sqid + "&xh=" + xh);
}

