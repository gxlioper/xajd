
/**
 * ��ѯ
 * @return
 */

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


//���ڵǼ�ά��
function XsKqdjWh(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var url = "";
	if(ids.length == 0){
		showAlertDivLayer("��ѡ������Ҫ���п��ڵǼǵļ�¼��");
		return false;
	}else if(ids.length == 1){
		 url = 'hdkhgl_hddj.do?method=XsKqdjWh&hdjgid=' + rows[0]["hdjgid"]+ '&xh=' + rows[0]["xh"];
		 var title = "���ڵǼ�";
		 showDialog(title, 500, 200, url);
	}else if(ids.length > 1){
	
		 url = 'hdkhgl_hddj.do?method=XsKqdjPlWh';
		 
		var title = "�������ڵǼ�";
		showDialog(title, 500, 200, url);
	}
}

//����ѧ�����ڵǼ���Ϣ
function saveKqdj(){
	var obj = jQuery("#sfcj");
	var obj1 = jQuery("#qqyy");
	if(jQuery(obj).val() == '��'){
		if(jQuery(obj1).val() == ''){
			showAlert("ȱ��ԭ�򲻿�Ϊ�գ�");
			return false;
		}else if(checkzsonsubmit(obj1) == false){
			return false;
		}
	}else if(jQuery(obj).val() == '' ){
		showAlert("�Ƿ�μӲ���Ϊ�գ�");
		return false;
	}
	var url = "hdkhgl_hddj.do?method=saveKqdj";
	ajaxSubFormWithFun("HdkhglForm", url, function(data) {
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

var DCCLBH = "rcsw_hdkhgl_hddj.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, xskqdjExportData);
}

//��������
function xskqdjExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "hdkhgl_hddj.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//���ڵǼ�״̬����
function kqdjOnchange(){
	if(jQuery("#sfcj").val() == '��'){
		jQuery("#qqblock").show();
	}else{
		jQuery("#qqblock").hide();
	}
}

//��֤����
function checkzsonsubmit(obj){
	if(jQuery(obj).val().length > 100){
		showAlert("�������Ϊ100�����Ѿ���������ȷ�ϣ�");
		return false;
	}
}

function xhLink(cellValue, rowObject) {
		 return "<a href='javascript:void(0);' class='name' onclick='KqdjView(\""
			+ rowObject["hdjgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
	 
}

//�鿴ѧ��ajaxurl��ת
function KqdjView(hdjgid,xh) {
	showDialog("���ڵǼǲ鿴", 700, 370, "hdkhgl_hddj.do?method=kqdjview&hdjgid="
			+ hdjgid + "&xh=" + xh);
}

//�����������ڵǼ�ά��
function savePlKqdj(){
	var obj = jQuery("#sfcj");
	var obj1 = jQuery("#qqyy");
	if(jQuery(obj).val() == '��'){
		if(jQuery(obj1).val() == ''){
			showAlert("ȱ��ԭ�򲻿�Ϊ�գ�");
			return false;
		}else if(checkzsonsubmit(obj1) == false){
			return false;
		}
	}else if(jQuery(obj).val() == '' ){
		showAlert("�Ƿ�μӲ���Ϊ�գ�");
		return false;
	}
	var api = frameElement.api,W = api.opener;
	var rows = W.jQuery("#dataTable").getSeletRow();
	var ids = new Array();
	var hdjgids = new Array();
	var xhs = new Array();
	var xmmcs = new Array();
	var xns = new Array();
	var xqs = new Array();
	var sfcj = jQuery(obj).val();
	var qqyy = jQuery(obj1).val();
	jQuery.each(rows, function(i, row) {
		if(row["id"] == null){
			ids.push("");
		}else{
			ids.push(row["id"]);
		}
		hdjgids.push(row["hdjgid"]);
		xhs.push(row["xh"]);
		xmmcs.push(row["xmmc"]);
		xns.push(row["xn"]);
		xqs.push(row["xq"]);
	});
	var data = {
		    xhs:xhs,
		    hdjgids:hdjgids,
		    ids:ids,
		    xmmcs:xmmcs,
		    sfcj:sfcj,
		    qqyy:qqyy,
		    xns:xns,
		    xqs:xqs
		};
	var url = "hdkhgl_hddj.do?method=saveXsKqdjPlWh";
	jQuery.ajax({
		type:'post',
		url:url,
		dataType:'json',
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		data:data,
		async: false,
		success: function(data) {
			 if(data["message"]=="����ɹ���"){
	    		 showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
	    	 }else{
	    		 showAlert(data["message"]);
	    		}
			}
	
   });
}

