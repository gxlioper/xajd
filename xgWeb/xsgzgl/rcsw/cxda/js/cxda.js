/**
 * @author ����Դ 
 * @����:��js
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add() {
	var url = "rcsw_cxda.do?method=add";
	var title = "���ӳ��ŵ���";
	showDialog(title, 900, 450, url);
}

//ɾ�����ŵ���
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	} 
	showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
		jQuery.post("rcsw_cxda.do?method=delCxda",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
}

//�޸�
function update(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'rcsw_cxda.do?method=editCxda&id=' + ids;
		var title = "�޸ĳ��ŵ���";
		showDialog(title, 800, 400, url);
	}
}

//�鿴ѧ������
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='CxdaView(\""
			+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}


//�鿴ѧ��ajaxurl��ת
function CxdaView(id, xh) {
	showDialog("�鿴ѧ���ɲ����˽��", 800, 350, "rcsw_cxda.do?method=CxdaView&id="
			+ id + "&xh=" + xh);
}

//�鿴ѧ��v2(��ѡһ��ѧ����¼������鿴)
function CxdaViewv2(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (parseInt(ids.length) != 1){
		showAlertDivLayer("��ѡ��һ��ѧ����¼���в鿴��");
		return false;
	}
	showDialog("�鿴ѧ���ɲ����˽��", 800, 350, "rcsw_cxda.do?method=CxdaView&id="
			+ ids);
}

var DCCLBH = "rcsw_cxda_cxda.do";
//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, exportData1);
}

//��������
function exportData1() {
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_cxda.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//����
function importConfig(){
	toImportDataNew("IMPORT_N732901_CXDA");
	return false;
}

//�����޸Ľ������
function saveCxda(){
	var ids = "xn"+"-"+"xq"+"-"+"cxfs";
	if(check(ids) == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	if(checkzs() == false){
		return false;
	}
	var url = "rcsw_cxda.do?method=saveCxda";
	ajaxSubFormWithFun("CxdaForm", url, function(data) {
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

/**
 * ��֤�Ƿ���ڿ���
 * @param ids Ҫ��֤�Ŀؼ�id "-"�ָ�
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}

function checkzs(){
	if(jQuery("#bz").val().length > 500){
		var num = jQuery("#bz").val().substr(0,50);
		showAlert("��ע��������500,��ȷ�ϣ�");
		return false;
	}
}


function addrowclick(){
	var value = jQuery("#rownum").val();
	if(value >= 100){
		jQuery("#rownum").val(100);
		showAlert("�������ó���100��");
		return false;
	}else if(value == ''){
		value = 0;
	}
	var value1 = parseInt(value)+1;
	jQuery("#rownum").val(value1);
}

function delrowclick(){
	var value = jQuery("#rownum").val();
	var value1 = parseInt(value)-1;
	if(value <= 1){
		jQuery("#rownum").val(1);
		showAlert("������������1��");
		return false;
	}
	jQuery("#rownum").val(value1);
}

function checkrownum(){
	var value = jQuery("#rownum").val();
	if(value>100){
		jQuery("#rownum").val(100);
		showAlert("�������ó���100��");
		return false;
	}
	if(value<1 || value == ''){
		jQuery("#rownum").val(1);
		showAlert("������������1��");
		return false;
	}
}

function saveCxdaAdd(){
	var arrlen = jQuery("#tbody_self tr").length;
	if(arrlen == 0){
		showAlert("���κ�ѧ���������ݣ��������棡");
		return false;
	};
	if(jQuery("#xn").val() == ''){
		showAlert("��"+"<font color='red'>*</font>"+"�������Ϊ�գ�");
		return false;
	}
	var xh = '';
	var cxfs = '';
	var bz = '';
	var flag = true;
	jQuery("#tbody_self tr").each(function(b){
		var tempxh=jQuery(this).find("td:eq(1) input").val();
		var tempcxfs=jQuery(this).find("td:eq(4) input").val();
		var tempbz =jQuery(this).find("td:eq(5) textarea").val();
		if(tempxh == '' || tempcxfs == ''){
			flag = false;
			var falserow = b+1;
			showAlert("��"+falserow+"�����ݴ�"+"<font color='red'>*</font>"+"�ű����ֶΣ�����д������");
			return false;
		}else if(tempbz.length>500){
			flag = false;
			var falserow = b+1;
			showAlert("��"+falserow+"�����ݱ�ע�ֶγ��ȳ���500����ȷ�ϣ�");
			return false;
		}else if(tempcxfs < 0){
			flag = false;
			var falserow = b+1;
			showAlert("��"+falserow+"�����ݳ��ŷ���Ϊ��������ȷ�ϣ�");
			return false;
		}
	})
	if(flag == false){
		return false;
	}
	var url = "rcsw_cxda.do?method=saveCxdaAdd";
	ajaxSubFormWithFun("CxdaForm",url,function(data) {
		 if(data["message"]=="����ɹ���"){
    		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
    	 }else{
    		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
    		}
		});
}

/**
 * ��֤�Ƿ���ڿ���
 * @param ids Ҫ��֤�Ŀؼ�id "-"�ָ�
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}

//��ע�ȳ��ֶ�����
function checkzs(){
	if(jQuery("#bz").val().length > 500){
		var num = jQuery("#bz").val().substr(0,50);
		showAlert("��ע��������500,��ȷ�ϣ�");
		return false;
	}
}

function checkzsonKeyUp(obj){
	if(obj.value.length>500){
		showAlert("��ע��������500,��ȷ�ϣ�");
		return false;
	}
}

//onblurʱ��֤���ŷ��� 
function checkfs(obj){
	if(obj.value>100){
		obj.value = '';
		showAlert("���ŷ������ó���100��,��ȷ�ϣ�");
		return false;
	}
}

