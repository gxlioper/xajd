

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='khjgView(\""
			+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

// ����
function saveKhjg(type) {
	var xh = jQuery("#xh").val();
	if (jQuery("#yrdw").val() == ""||jQuery("#yrdw").val() == null||jQuery("#gwdm").val() == ""||jQuery("#gwdm").val() == null||
			jQuery("#gzdd").val() == "" || xh == ""||jQuery("#gzrq").val() == ""||jQuery("#gzkssj").val() == ""
		||jQuery("#gzjssj").val() == ""||jQuery("#gs").val() == ""||jQuery("#gznr").val() == "") {
		showAlert("�뽫��������д������");
		return false;
	}
	if("0" == jQuery("#gs").val()) {
		showAlert("��ʱ����Ϊ�㣡");
		return false;
	}
	if (jQuery("#gznr").val().length>1000) {
		showAlert("���������������1000�֣�");
		return false;
	}
	var url = "mrgzkhKhjg.do?method=saveKhjg&type=" + type;
	ajaxSubFormWithFun("GzkhKhjgForm", url, function(data) {
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
//����
function add() {
	var url = "mrgzkhKhjg.do?method=addKhjg";
	var title = "ÿ�տ�����д";
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
	jQuery.getJSON('mrgzkhKhsq.do?method=getGwxx&t='+new Date().getTime(),parameter,function(data){
		jQuery('#gwdm').empty();
		//jQuery('#gwdm').append("<option value=''>--------��ѡ��--------</option>");
		if(data != null && data.length != 0){
			for(var i=0; i<data.length; i++){
				var option = "<option value=\"" + data[i].gwdm + "\">" + data[i].gwmc + "</option>";
				jQuery('#gwdm').append(option);
			}
		}
	});
	jQuery.ajaxSetup({async:true});
}
//�޸�
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		if(rows[0]['sjly']=='1'){
			showAlertDivLayer("������̹����ļ�¼�����޸ģ�");
			return false;
		}
		var url = 'mrgzkhKhjg.do?method=editKhjg&id=' + rows[0]["id"]
				+ '&xh=' + rows[0]["xh"];
		var title = "ÿ�տ�����д";
		showDialog(title, 750, 480, url);
	}
}
//�鿴
function khjgView(id, xh) {
	showDialog("ÿ�տ��˲鿴", 750, 550, "mrgzkhKhjg.do?method=khjgView&id="
			+ id + "&xh=" + xh);
}
// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("������̹����ļ�¼����ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("mrgzkhKhjg.do?method=delKhjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "qgzx_mrgzkh_khjg.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, mrgzkhKhjgExportData);
}

//��������
function mrgzkhKhjgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "mrgzkhKhjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
