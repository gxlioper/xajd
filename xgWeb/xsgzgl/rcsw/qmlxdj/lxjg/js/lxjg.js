
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add() {
	var url = "qmlxjg.do?method=add";
	var title = "��ĩ��У������";
	showDialog(title, 770, 550, url);
}

/**
 * ������
 * @param type
 * @return
 */
function saveJg(){
    if(jQuery("#sflxdm").val() == "��"){
        var ids = "xh"+"-"+"jhrxm"+"-"+"jhrlxfs"+"-"+"lxsj"+"-"+"lxjtgjdm"
            +"-"+"fxsj" +"-"+"fxjtgjdm"+"-"+"fxcchb"+"-"+"mddssx"+"-"+"xn"+"-"+"xq";
        if(checkNotNull(ids) == false){
            showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
            return false;
        }
        if(jQuery("[name='sflx']:checked").val() == "" || jQuery("[name='sflx']:checked").val() == null){
            showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
            return false;
        }
	}

	var url = "qmlxjg.do?method=saveJg";
	ajaxSubFormWithFun("LxjgForm", url, function(data) {
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
	return "<a href='javascript:void(0);' class='name' onclick='jgView(\""
			+ rowObject["jgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

/**
 * �鿴
 * @param sqid
 * @param xh
 * @return
 */
function jgView(jgid, xh) {
	showDialog("��ĩ��У�������鿴", 770, 450, "qmlxjg.do?method=ckSq&jgid="
			+ jgid + "&xh=" + xh);
}

/**
 * ɾ��
 * @return
 */
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		var flag = true;
		jQuery(rows).each(function(i,row){
			if(row["sjly"] == "1"){
				flag = false;
				return flag;
			}
		});
		if(!flag){
			showAlertDivLayer("����������ݲ���ɾ����");
			return false;
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("qmlxjg.do?method=delJg",{values:ids.toString()},function(data){
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
		if ("1" == rows[0]['sjly']) {
			showAlertDivLayer("����������ݲ����޸ģ�");
			return false;
		}
		var url = 'qmlxjg.do?method=editJg&jgid=' + rows[0]["jgid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "��ĩ��У�������޸�";
		showDialog(title, 770, 550, url);
	}
}


var DCCLBH = "rcsw_qmlxjg.do";

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, jgExportData);
}

//��������
function jgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "qmlxjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}



