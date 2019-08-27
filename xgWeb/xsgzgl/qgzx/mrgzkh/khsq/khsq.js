
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='khsqView(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}
function khsqView(sqid, xh) {
	showDialog("ÿ�չ�����������鿴", 750, 550, "mrgzkhKhsq.do?method=khsqView&sqid="
			+ sqid + "&xh=" + xh);
}
function saveKhsq(type) {	
	if("0" == jQuery("#gs").val()) {
		showAlert("��ʱ����Ϊ�㣡");
		return false;
	}
	// if(parseInt(jQuery("#gs").val())  > 8){
     //    showAlert("ÿ�칤��ʱ��<=8Сʱ��");
     //    return false;
	// }
	if(checkZdz()){
	var url = "mrgzkhKhsq.do?method=saveKhsq&type=" + type;
	ajaxSubFormWithFun("GzkhKhsqForm", url, function(data) {
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

}
/**
 * ������֤
 * @return
 */
function checkZdz(){
	var xh = jQuery("#xh").val();
	var flag=true;
	if (jQuery("#yrdw").val() == ""||jQuery("#yrdw").val() == null||jQuery("#gwdm").val() == ""||jQuery("#gwdm").val() == null || xh == ""||jQuery("#gzrq").val() == ""||jQuery("#gzkssj").val() == ""
		||jQuery("#gzjssj").val() == ""||jQuery("#gs").val() == "") {
		showAlert("�뽫��<font color=\"red\">*</font>����д������");
		flag= false;
		return flag;
	}
	/*if (jQuery("#gznr").val().length>1000) {
		showAlert("���������������1000�֣�");
		flag=false;
		return flag;
	}*/
	var gzkssj = parseInt(jQuery("#gzkssj").val(),10);
	var gzjssj = parseInt(jQuery("#gzjssj").val(),10);
	var gzsjd = gzjssj-gzkssj;
	
	if(gzkssj>gzjssj){
		showAlert("������ʼʱ�䲻�ܴ��ڹ�������ʱ�䣡");
		flag=false;
		return flag;
	}
	return flag;
}
function add() {
	var isopen = jQuery("#sqkg").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("0" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}
	
	var url = "mrgzkhKhsq.do?method=addKhsq";
	var title = "ÿ�չ�����������";
	showDialog(title, 750, 480, url);
}
function checkXh(){
	if(jQuery("#xh").val()==""){
		showAlert("����ѡ��ѧ����");
		return false;
	}
}
//��λ��������
function getGwxx(){
	jQuery.ajaxSetup({async:false});
	var parameter ={};
	parameter["xh"]=escape(jQuery("#xh").val());
    parameter["yrdw"]=escape(jQuery("#yrdw").val());
	jQuery.getJSON('mrgzkhKhsq.do?method=getGwxx',parameter,function(data){
		jQuery('#gwdm').empty();
		jQuery('#gwdm').append("<option value=''>--------��ѡ��--------</option>");
		if(data != null && data.length != 0){
			for(var i=0; i<data.length; i++){
				var option = "<option value=\"" + data[i].gwdm + "\">" + data[i].gwmc + "</option>";
				jQuery('#gwdm').append(option);
			}
		}
	});
	jQuery.ajaxSetup({async:true});
}


function update() {
	var isopen = jQuery("#sqkg").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var sqkg = jQuery("#sqkg").val();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {

		var shzt = rows[0]["shzt"];
		if ("0" != shzt&&"3" != shzt) {
			showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
			return false;
		}

		var url = 'mrgzkhKhsq.do?method=editKhsq&sqid=' + rows[0]["sqid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "ÿ�չ������������޸�";
		showDialog(title, 750, 480, url);
	}

}

// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer(jQuery("#lable_wjt_sc").val());
		return false;
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("mrgzkhKhsq.do?method=delKhsq", {
					values : ids.toString()
				},
						function(data) {
							var mes = "�ɹ�ɾ����<font color='green'>&nbsp;"
									+ data["num"] + "&nbsp;</font>������";
							showAlertDivLayer(mes);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
			}
		});
	}
}

// �ύ
function submitBusi() {
	var isopen = jQuery("#sqkg").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1){
		if ("0" == isopen){
			showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
			return false;
		}
		showAlertDivLayer(jQuery("#lable_one_tj").val());
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		if ('3'!=rows[0]["shzt"] && "0" == isopen){
			showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
			return false;
		}
		if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
			showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
			return false;
		}
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("mrgzkhKhsq.do?method=saveEditKhsq&type=tj", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}

}
// ����
function cancel() {
	var isopen = jQuery("#sqkg").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("0" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
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
				jQuery.post("mrgzkhKhsq.do?method=cancelKhsq", {
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
	} else {
		var shzt = rows[0]["shzt"];
		if ("0" == shzt) {
			showAlertDivLayer(jQuery("#lable_wxglcxx").val());
			return false;
		}
		showDialog("ÿ�չ��������������̸���",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splc']);
	}
}


var DCCLBH = "qgzx_mrgzkh_khsq.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, khsqExportData);
}

//��������
function khsqExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "mrgzkhKhsq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}