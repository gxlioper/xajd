

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function bjmcLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='xszyView(\""
			+ rowObject["jgid"] + "\",\"" + rowObject["bjdm"] + "\");'>" + cellValue
			+ "</a>";
}

function ppqsLink(cellValue, rowObject) {
	var ppqs =null;
	var ppqsdm =null;
	if(null!=cellValue){
	ppqs=cellValue.split(";");
	ppqsdm = rowObject["ppqsdm"].split(";");
	var lddm = "";
	var qsh = "";
	var hrefHtml="";
	for ( var i = 0; i < ppqs.length; i++) {
		lddm=ppqsdm[i].split("-")[0];
		qsh=ppqsdm[i].split("-")[1];
		hrefHtml+="<a href='javascript:void(0);' class='name' onclick='qsxxView(\""
			+ lddm + "\",\"" + qsh + "\",\""
			+ rowObject["nj"] + "\");'>" + ppqs[i]
			+ "</a>"+" ";
	}
	return hrefHtml;
	}
	
	
}

// �鿴
function viewXszy(id) {
	showDialog("����֮����Ϣ�鿴", 650, 350, "xszygl.do?method=viewXszy&id="
			+ id );
}


// ����֮����Ϣ�鿴
function zghLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewXszy(\""
			+ rowObject["id"] + "\");'>" + cellValue
			+ "</a>";
}

function kyxbjFormatter(cellValue, rowObject) {
	var value=cellValue;
	if(null!=cellValue&&""!=cellValue){
	value = "�ѱ��[<font color='red'>"+cellValue+"</font>]";
	}
	return value;
}


// ����
function saveXszy(type) {
	var ids = null;
	if("save" == type){
		ids = "zgh-xm-xb-zwzc-dwdm-lxdh-zzmmdm-dzyx-dlyq";
	}else{
		ids = "xm-xb-zwzc-dwdm-lxdh-zzmmdm-dzyx-dlyq";
	}
	if(!checkNotNull(ids)){
		showAlert("�뽫��<font class='red'>*</font>����Ŀ��д������");
		return false;
	}
	var url = "xszygl.do?method=saveXszy&type="+type;
	ajaxSubFormWithFun("XszyglForm", url, function(data) {
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
// ����
function add() {
	var url = "xszygl.do?method=addXszy";
	var title = "����֮����Ϣ����";
	showDialog(title, 700, 450, url);
}


// �޸�
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�޸ĵļ�¼��");
		return false;
	}else if(rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	} else {
// if(rows[0]['sjly']=='1'){
// showAlertDivLayer("������̹����ļ�¼�����޸ģ�");
// return false;
// }
		var url = 'xszygl.do?method=editXszy&id=' + rows[0]["id"];
		var title = "����֮����Ϣ�޸�";
		showDialog(title, 700, 450, url);
	}
}

// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<rows.length;i++){
			if(""!=rows[i]['ppqs']&&null!=rows[i]['ppqs']){
				showAlertDivLayer("��ƥ�����ҵ�����֮�Ѳ���ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("xszygl.do?method=delXszy",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

// ��Ժϵ���
function kyxbj() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else {
		showDialog("��Ժϵ���", 450, 200, "xszygl.do?method=kyxbj");
}
}



// ��Ժϵ��Ǳ���
function saveKyxbj(dlyq) {
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else {
		jQuery.post("xszygl.do?method=kyxbj&type="+"save", {
			kyxbj : dlyq,
			ids : ids.toString()
		}, function(data) {
			showAlertDivLayer(data["message"], {}, {
				"clkFun" : function() {
					jQuery("#dataTable").reloadGrid();
				}
			});
		}, 'json');
}
}

//Ժϵ����
function fpyx() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
		return false;
	} else {
		 var xm = new Array();
			jQuery.each(rows, function(i, row) {
				xm.push(row["xm"]);
			});
		showDialog("����Ժϵ", 450, 300, "xszygl.do?method=fpyx&xm="+xm);
}
}

//Ժϵ���䱣��
function saveFpyx(bjyx) {
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else {
		jQuery.post("xszygl.do?method=fpyx&type="+"save", {
			bjyx : bjyx,
			ids : ids.toString()
		}, function(data) {
			showAlertDivLayer(data["message"], {}, {
				"clkFun" : function() {
					jQuery("#dataTable").reloadGrid();
				}
			});
		}, 'json');
}
}
//����
function dr() {
	// ����ͨ�õĵ���function�������ǵ��빦��ģ����롣
	toImportDataNew("IMPORT_N950103_XSZYGL");
	return false;

}

var DCCLBH = "xszy_xszygl.do";// dcclbh,�������ܱ��

// �Զ��嵼�� ����
function exportConfig() {
	// DCCLBH�������ܱ��,ִ�е�������
	customExport(DCCLBH, xszyglExportData);
}

// ��������
function xszyglExportData() {
	setSearchTj();// ���ø߼���ѯ����
	var url = "xszygl.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,�������ܱ��
	url = addSuperSearchParams(url);// ���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}



// ��֤������
function checkNulls() {
	var fields = jQuery("#XszyglForm").serializeArray();
	var isNull=false;
	jQuery.each(fields, function(i, field) {
			if (("" == jQuery(field).val() || null == jQuery(field).val())&&"bz"!=jQuery(field).attr("name")) {
				isNull = true;
			}
		});
	return isNull;

}