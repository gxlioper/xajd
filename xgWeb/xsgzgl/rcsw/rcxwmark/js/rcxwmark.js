/*��ѯ*/
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/*ѡ��л�*/
function selectTab(obj, shzt) {
	if (shzt == "wsz") {
		document.location.href = 'xg_rcsw_rcxwmark_wcl.do';
	} else {
		document.location.href = 'xg_rcsw_rcxwmark_ycl.do';
	}
}

//�������ý��
function saveSzjg(type){
	//���ý��
	var jxdm = jQuery("[name='jxdm']:checked").val();
	if(jxdm == "" || jxdm == null){
		showAlertDivLayer("�������ò���Ϊ�գ�");
		return false;
	}
	var url = "rcsw_rcxwmark.do?method=saveSzjg&type=" + type;
	ajaxSubFormWithFun("RcxwmarkForm", url, function(data) {
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
	return "<a href='javascript:void(0);' class='name' onclick='ckView(\""
			+ rowObject["rcxwjgid"] + "\",\""+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//�鿴ѧ��ajaxurl��ת
function ckView(rcxwjgid,id,xh) {
	var title = "";
	var paras = "";
	var parasName = "";
	var cxlx = jQuery("#cxlx").val();
	if(cxlx == "yclcx"){
		title = "�����ò鿴";
	    paras = id;
		parasName = "id";
	}else{
		title = "δ���ò鿴";
		paras = rcxwjgid;
		parasName = "rcxwjgid";
	}
	showDialog(title, 600, 450, "rcsw_rcxwmark.do?method=ck&"+parasName+"="+paras+"&type="+cxlx);
}
var DCCLBH = "";
//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	var cxlx  = jQuery("#cxlx").val();
	if(cxlx == "yclcx"){
		DCCLBH = "xg_rcsw_rcxwmark_ycl.do";
	}else{
		DCCLBH = "xg_rcsw_rcxwmark_wcl.do";
	}
	customExport(DCCLBH, jgExportData);
}

//��������
function jgExportData() {
	var cxlx  = jQuery("#cxlx").val();
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_rcxwmark.do?method=exportData&dcclbh=" + DCCLBH+"&type="+cxlx;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//�޸�
function xg(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'rcsw_rcxwmark.do?method=xg&id=' + rows[0]["id"];
		var title = "�޸�";
		showDialog(title, 600, 450, url);
	}
}

//����
function sz(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ���õļ�¼��");
	} else {
		var url = 'rcsw_rcxwmark.do?method=Sz&rcxwjgids=' +ids.toString();
		var title = "����";
		showDialog(title, 600, 450, url);
	}
}

//ȡ������
function qxsz(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫȡ�����õļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫȡ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("rcsw_rcxwmark.do?method=qxsz",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

