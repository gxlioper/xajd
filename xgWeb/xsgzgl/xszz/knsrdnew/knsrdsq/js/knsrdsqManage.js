
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function add(){
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("false" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}
	var url = "xg_xszz_knsrd_knsqgl.do?method=addKnsrdsq";
	var title = "�������϶�����";
	showDialog(title,790,450,url);
}



function update() {
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	}
	
	var shzt = rows[0]["shzt"];
	
	if (rows.length == 1) {
		
		if ("0" != shzt&&"3" != shzt) {
			showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
			return false;
		}
		var url = 'xg_xszz_knsrd_knsqgl.do?method=updateKnsrdsq&sqid=' + rows[0]["sqid"] + '&zbid=' + rows[0]["zbid"] + '&xh=' + rows[0]["xh"];
		var title = "�޸��������϶�����";
		showDialog(title, 720, 450, url);
	}

}


//ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("xg_xszz_knsrd_knsqgl.do?method=delKnsrdzbsq", {
					values : ids.toString()
				}, function(data) {
					var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>������";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+="�������Ѿ��ύ����ɾ��!";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

function viewKnsrdsq(sqid, xh) {
	showDialog("�鿴������ָ������", 720, 520, "xg_xszz_knsrd_knsqgl.do?method=viewKnsrdsq&sqid=" + sqid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewKnsrdsq(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

var DCCLBH = "xg_xszz_knsrd_knsq.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, knsrdsqExportData);
}

// ��������
function knsrdsqExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xg_xszz_knsrd_knsqgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}