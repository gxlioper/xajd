/**
 * ��ѯ
 * @return
 */
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add() {
	var url = "xmsqgl_xmjg.do?method=add";
	var title = "���";
    showDialog(title, 770, 500, url);
	
}

//���ӽ������
function saveSqjg(type){
	var ids = "xh"+"-"+"xmmc";
	if(check(ids) == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	if(checkzs() == false){
		return false;
	}
	var url = "xmsqgl_xmjg.do?method=saveSqjg&type=" + type;
	ajaxSubFormWithFun("XsXmJgForm", url, function(data) {
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

function saveSqjgUpdate(type){
	if(jQuery("#xmmc").val() == ''){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	if(checkzs() == false){
		return false;
	}
	var url = "xmsqgl_xmjg.do?method=saveSqjg&type=" + type;
	ajaxSubFormWithFun("XsXmJgForm", url, function(data) {
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
	return "<a href='javascript:void(0);' class='name' onclick='SqjgView(\""
			+ rowObject["jgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function SqjgView(jgid, xh) {
	showDialog("�鿴", 770, 450, "xmsqgl_xmjg.do?method=XmjgView&jgid="
			+ jgid + "&xh=" + xh);
}

//ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("������̹����ļ�¼����ɾ����");
				return false;
			}
			if((rows[0]['xfrdsqzt']!='0' && rows[0]['xfrdsqzt']!='3') || rows[0]['xfrdjgzt']!='0'){
				showAlertDivLayer("��Ŀѧ�����϶�������ɾ����");
				return false;
			}
			
		}
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("xmsqgl_xmjg.do?method=delSqjl",{values:ids.toString()},function(data){
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
	} else {
		if(rows[0]['sjly']=='1'){
			showAlertDivLayer("������̹����ļ�¼�����޸ģ�");
			return false;
		}
		if((rows[0]['xfrdsqzt']!='0' && rows[0]['xfrdsqzt']!='3') || rows[0]['xfrdjgzt']!='0'){
			showAlertDivLayer("����Ŀѧ�����϶��������޸ģ�");
			return false;
		}
		var url = 'xmsqgl_xmjg.do?method=editSqjg&jgid=' + rows[0]["jgid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "ѧ����Ŀ�����޸�";
		showDialog(title, 770, 500, url);
	}
}

var DCCLBH = "sztz_xmsqgl_xmjg.do";

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, xsxmsqJgExportData);
}

//��������
function xsxmsqJgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xmsqgl_xmjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//����
function importConfig(){
	toImportDataNew("IMPORT_XSTZXM");
	return false;
}



function checkzs(){
	if(jQuery("#sqly").val().length > 500){
		showAlert("�������Ϊ500�����Ѿ���������ȷ�ϣ���");
		return false;
	}
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


function auotSetCanCheck(){
	jQuery("tr[name=checkxm]").each(function(){
		var kcyrs = parseInt(jQuery.trim(jQuery(this).find("td:eq(3)").text()));
		var tgrs  = parseInt(jQuery.trim(jQuery(this).find("td:eq(4)").text()));
		if(kcyrs-tgrs == 0){
			jQuery(this).find("[name=checkbox]").attr("disabled",true);
		}
	});
}

/**
 * ѡ����Ŀҳ���л������롢δ����
 * @param obj
 * @param tabId
 * @return
 */
function selectSqxm(obj,tabId){

	jQuery("#comp_title li").removeClass("ha");
	jQuery(obj).parent().addClass("ha");

	jQuery("#xmList tbody").css("display","none");
	jQuery("#"+tabId).css("display","");
	
	if (tabId == "ysq"){
		jQuery("#titleTr td").last().css("display","none");
	} else {
		jQuery("#titleTr td").last().css("display","");
	}
}

/**
 * ȷ��ѡ����Ŀ
 * @return
 */
function selectXm(){
	var api = frameElement.api;
	var selectBox = jQuery("#wsq input:checkbox:checked");
	var test = api.get('parentDialog');
	if(selectBox.length != 1){
		showAlert("��ѡ��һ��������ѡ��");
		return false;
	}
	    var xmmc = test.jQuery(selectBox).parent().parent().find("td:eq(4) input[name = 'xmmc']").val();
		test.jQuery("input [name='xmmc']").val(jQuery(selectBox).parent().parent().find("td:eq(1)").text());
		test.jQuery("#xmjbmc").text(jQuery(selectBox).parent().parent().find("td:eq(4) input[name = 'xmjbmc']").val());
		test.jQuery("#xn").text(jQuery(selectBox).parent().parent().find("td:eq(4) input[name = 'xn']").val());
		test.jQuery("#xq").text(jQuery(selectBox).parent().parent().find("td:eq(4) input[name = 'xqmc']").val());
		test.jQuery("#sbbmmc").text(jQuery(selectBox).parent().parent().find("td:eq(4) input[name = 'sbbmmc']").val());
		test.jQuery("#lxdh").text(jQuery(selectBox).parent().parent().find("td:eq(4) input[name = 'lxdh']").val());
		test.jQuery("#sskmmc").text(jQuery(selectBox).parent().parent().find("td:eq(4) input[name = 'sskmmc']").val());
		test.jQuery("#jcxf").text(jQuery(selectBox).parent().parent().find("td:eq(4) input[name = 'jcxf']").val());
		test.jQuery("#kcyrs").text(jQuery(selectBox).parent().parent().find("td:eq(3)").text());
		test.jQuery("#sqrs").text(jQuery(selectBox).parent().parent().find("td:eq(4) input[name = 'sqrs']").val());
		test.jQuery("#tgrs").text(jQuery(selectBox).parent().parent().find("td:eq(4) ").text());
		test.jQuery("#xmkssj").text(jQuery(selectBox).parent().parent().find("td:eq(2) ").text());
		test.jQuery("input[name='xmdm']").val(jQuery(selectBox).parent().parent().find("td:eq(0) input").val());
		//test.jQuery("#splc").val(jQuery(selectBox).parent().parent().find("td:eq(4) input[name = 'splc']").val());
		test.jQuery("#xmmc").val(xmmc);
	    iFClose();
}
