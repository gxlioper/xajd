
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add() {
	var cfbz = jQuery("#cfbz").val();
	if( "1"  == cfbz){
		showAlertDivLayer("����ѧ�����������¼�������ظ���д��");
		return false;
	}
	var url = "qmlxdj.do?method=add";
	var title = "��Уȥ��Ǽ�";
	showDialog(title, 770, 550, url);
}

function fxjtgjdmChange(){
	var fxjtgj = jQuery("#fxjtgjdm").val();
	if(fxjtgj == "03"){
		jQuery("#fxcchbspan").hide();
	} else{
        jQuery("#fxcchbspan").show();
	}
}
/**
 * ��������
 * @param type
 * @return
 */
function saveSq(type){
	if(jQuery("#sflxdm").val() == "��"){
        var ids = "lxlx-xh-jhrxm-jhrlxfs-lxsj-lxjtgjdm-mddssx";
        if(checkNotNull(ids) == false){
            showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
            return false;
        }
        if(jQuery("[name='sflx']:checked").val() == "" || jQuery("[name='sflx']:checked").val() == null){
            showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
            return false;
        }
        /*if(jQuery("#lxjtgjdm").val() != "03"){
            if(checkNotNull("lxcchb") == false){
                showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
                return false;
            }
		}*/
        if(jQuery("#fxjtgjdm").val() != "03"){
            if(checkNotNull("fxcchb") == false){
                showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
                return false;
            }
        }
	}

	var url = "qmlxdj.do?method=saveSq&type=" + type;
	ajaxSubFormWithFun("LxdjForm", url, function(data) {
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

//�鿴ѧ������
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='sqView(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

/**
 * �鿴
 * @param sqid
 * @param xh
 * @return
 */
function sqView(sqid, xh) {
	showDialog("��Уȥ��Ǽǲ鿴", 770, 450, "qmlxdj.do?method=ckSq&sqid="
			+ sqid + "&xh=" + xh);
}

/**
 * ɾ��
 * @return
 */
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		var flag = true;
		jQuery(rows).each(function(i,row){
			if(row["shzt"] != "0" && row["shzt"] != "3"){
				flag = false;
				return flag;
			}
		});
		if(!flag){
			showAlertDivLayer(jQuery("#lable_wjt_sc").val());
			return false;
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("qmlxdj.do?method=delSq",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	} else {
		if ("0" != rows[0]['shzt']&&"3" !=rows[0]['shzt']) {
			showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
			return false;
		}
		var url = 'qmlxdj.do?method=editSq&sqid=' + rows[0]["sqid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "��Уȥ��Ǽ��޸�";
		showDialog(title, 770, 550, url);
	}
}

var DCCLBH = "rcsw_qmlxdj.do";

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, sqExportData);
}

//��������
function sqExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "qmlxdj.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//�ύ
function submitBusi() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
		return false;
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		if (jQuery("#sqkg").val() == "0" && rows[0]["shzt"] != "3"){
			showAlertDivLayer(jQuery("#lable_dqwkfsq_prompt").val());
			return false;
		}
		if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
			showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
			return false;
		}
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("qmlxdj.do?method=submitBusi", {
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
				jQuery.post("qmlxdj.do?method=cancelSq", {
					values : ids.toString(),
					splcid : rows[0]['splcid']
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
		showDialog("�����������̸���",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splcid']);
	}
}