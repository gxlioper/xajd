function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add() {
	var url = "ttxm_sq.do?method=add";
	var title = "������չ��Ŀ����";
	showDialog(title, 770, 550, url);
}


//�����޸��������뱣��
function saveTtxmSq(type){
	var ids = "xmmc"+"-"+"tdmc"+"-"+"sqly"+"-"+"dzxh";
	if(!checkNotNull(ids)){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		return false;
	}
	if(jQuery("#usertype").val() != 'stu'){
		if(!checkDzIsSelect()){
			showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
			return false;
		}
	}
	if(!checkContentIsNull()){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		return false;
	}
	if(checkzs() == false){
		return false;
	}
	var url = "ttxm_sq.do?method=saveTtsq&type=" + type;
	ajaxSubFormWithFun("TttzxmForm", url, function(data) {
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


//�鿴�Ŷ�����
function ttsqLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='TtsqView(\""
			+ rowObject["ttsqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function TtsqView(ttsqid, tdmc) {
	showDialog("������չ��Ŀ����鿴", 770, 450, "ttxm_sq.do?method=TtsqView&ttsqid="
			+ ttsqid);
}

//ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	}  
	var flag = true;
	for(var i=0;i<rows.length;i++){
		if(rows[i]["shzt"] != "0" && rows[i]["shzt"] != "3"){
			flag =false;
			break;
		}
	}
	if(!flag){
		showAlertDivLayer(jQuery("#lable_wjt_sc").val());
		return false;
	}
	showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
		jQuery.post("ttxm_sq.do?method=delTtsq",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
	
}



//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	} else {
		if(rows[0]['sqkg'] != '1'){
			showAlertDivLayer("��Ӧ��Ŀ���뿪���ѹرգ������޸ģ�");
			return false;
		}
		if ("0" != rows[0]['shzt']&&"3" !=rows[0]['shzt']) {
			showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
			return false;
		}
		var url = 'ttxm_sq.do?method=editTtsq&ttsqid=' + rows[0]["ttsqid"];
		var title = "������չ��Ŀ����";
		showDialog(title, 770, 550, url);
	}
}

var DCCLBH = "sztz_ttxm_sq.do";

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, ttsqExportData);
}

//��������
function ttsqExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "ttxm_sq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//�ύ
function submitBusi() {
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if(rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
		return false;
	}
	
	if ("1" != rows[0]['sqkg']){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}
	if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
		return false;
	}
	showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
		"okFun" : function() {
			jQuery.post("ttxm_sq.do?method=submitBusi", {
				values : ids.toString()
			}, function(data) {
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			}, 'json');
		}
	});
}

//����
function cancel() {
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else if (ids.length > 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	} else {
		if ("1" != rows[0]['sqkg']){
			showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
			return false;
		}
		var rows = jQuery("#dataTable").getSeletRow();
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['shzt'] != '5') {
				showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
				return false;
			}
		}

		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("ttxm_sq.do?method=cancelZssq", {
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

/*
 * ���̸���
 */
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
		showDialog("�������̸���",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['ttsqid'] + "&splc=" + rows[0]['splc']);
	}
}



