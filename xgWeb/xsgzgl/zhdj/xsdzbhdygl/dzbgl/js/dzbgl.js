function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}


/**
 * ����
 * @return
 */
function addDzb() {
    url = "dzdy_dzbgl.do?method=add";
    showDialog("���ӵ�֧��", 770, 550, url, {
        close: function () {
            jQuery("#dataTable").reloadGrid();
        }
    });

}

/**
 * �޸�
 * @return
 */
function update() {
    var rows = jQuery("#dataTable").getSeletRow();
    document.location.href = "gyjc_ccrcsz.do?method=update&ccid=" + rows[0]["ccid"];
}

function updateDzb() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("��ѡ��һ����¼��");
        return false;
    }
    var url = "dzdy_dzbgl.do?method=updateDzb&dzbhjid=" + rows[0]["dzbhjid"];
    var title = "�޸ĵ�֧����Ϣ";
    showDialog(title, 770, 552, url);
}


function hjDzb() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("��ѡ��һ����¼��");
        return false;
    }
    var url = "dzdy_dzbgl.do?method=hjDzb&dzbhjid=" + rows[0]["dzbhjid"];
    var title = "��֧������";
    showDialog(title, 770, 552, url);
}


function ljDzb() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("��ѡ��һ����¼��");
        return false;
    }
    var url = "dzdy_dzbgl.do?method=ljDzb&dzbhjid=" + rows[0]["dzbhjid"];
    var title = "������Ϣ";
    showDialog(title, 770, 552, url);
}


/**
 * ֻ�޸�����
 * @return
 */
function saveCcrcUpdate() {
    var url = "gyjc_ccrcsz.do?method=saveCcrcUpdate";
    ajaxSubFormWithFun("CcrcForm", url, function (data) {
        if (data["message"] == "����ɹ���") {
            showAlert(data["message"], {}, {
                "clkFun": function () {
                    if (parent.window) {
                        refershParent();
                    }
                }
            });
        } else {
            showAlert(data["message"]);
        }
    });
}

/**
 * ɾ��
 * @return
 */
function delDzb() {
    var row = jQuery("#dataTable").getSeletRow();
    if (row.length == 0) {
        showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
    } else {
        var para = {
            dzbid: row[0]["dzbid"]

        };
        showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
            "okFun": function () {
                jQuery.post("dzdy_dzbgl.do?method=delDzb", para, function (data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
            }
        });
    }
}


jQuery(function () {
    onShow();
});

function onShow() {
    var url = "dzdy_dzbgl.do?method=add&type=query";
    jQuery.post(url, {}, function (data) {
        initTslx(data);//��ʼ����������
    }, 'json');
}

//��ʼ����������
function initTslx(data) {
    if (data == null || data.length == 0) {
        return;
    }
    var sHtml = "";

    for (var i = 0; i < data.length; i++) {
        var o = data[i];
        var dm = o.dm;
        var mc = o.mc;
        if (dm != null && dm != "") {
            sHtml += "<option value='" + dm + "'>" + mc + "</option>";
        }
    }
    jQuery("#jcdwdm").html(sHtml);

    jQuery("#jcdwdm").change(function () {//
        var jcdwdm = jQuery(this).val();
        if (jcdwdm === "") {
            jQuery("#symc").html("");

        }
        for (var i = 0; i < data.length; i++) {
            var o = data[i];
            var dm = o.dm;
            var symc = o.symc;
            if (dm != null && dm != "" && jcdwdm === dm) {
                jQuery("#symc").html(symc);
            }
        }
    });
    jQuery("#jcdwdm").change();
    
}


function choseZg(aaa) {
	var rows = jQuery("#dataTable").getSeletRow();
	var number = aaa.value;
    if (rows.length == 0) {
        showAlertDivLayer("��ѡ��һ��ְ����");
        return false;
    }
    var api = frameElement.api;
    var parentsW = api.get('parentDialog');
    parentsW.jQuery("#jzgmc"+number).val(rows[0]['xm']);
    parentsW.jQuery("#lxrzgh"+number).val(rows[0]['zgh']);
    parentsW.jQuery("#jzgdh"+number).val(rows[0]['lxdh']);
    closeDialog();
}

function choseXx(bbb) {
    var rows = jQuery("#dataTable").getSeletRow();
	var number = bbb.value;
    if (rows.length == 0) {
        showAlertDivLayer("��ѡ��һ��ѧ����");
        return false;
    }
    var api = frameElement.api;
    var parentsW = api.get('parentDialog');
    parentsW.jQuery("#xsmc"+number).val(rows[0]['xm']);
    parentsW.jQuery("#xh"+number).val(rows[0]['xh']);
    parentsW.jQuery("#xsdh"+number).val(rows[0]['lxdh']);
    closeDialog();
}


function saveDzb(dzblx,jgcount,xscount) {
	var ids = null;
	var value = dzblx.value;
	var length = jgcount.value;
	var lengthN = xscount.value;
	if(value == "�̹���֧��"){
		if(length ==0){
			ids = "dzbmc" + "-" + "jcdwdm" + "-" + "clsj" + "-" + "dzblx";
			if (!checkNotNull(ids)) {
		        return showAlert("�뽫��<font color='red'>*</font>��Ŀ��д������");
		    }   
		}else{
			for(var i=0;i<length;i++){
				ids = "dzbmc" + "-" + "jcdwdm" + "-" + "clsj" + "-" + "jgzwmc"+i+ "-" + "jzgdh"+i;
				if (!checkNotNull(ids)) {
			        return showAlert("�뽫��<font color='red'>*</font>��Ŀ��д������");
			    }   
			}
		}
	}else if(value == "ѧ����֧��"){
		if(lengthN ==0){
			ids = "dzbmc" + "-" + "jcdwdm" + "-" + "clsj" + "-" + "dzblx"; 
			if (!checkNotNull(ids)) {
		        return showAlert("�뽫��<font color='red'>*</font>��Ŀ��д������");
		    }   
		}else{
			for(var i=0;i<lengthN;i++){
				ids = "dzbmc" + "-" + "jcdwdm" + "-" + "clsj" + "-" + "zwmc"+i + "-" + "xsdh"+i;
				if (!checkNotNull(ids)) {
			        return showAlert("�뽫��<font color='red'>*</font>��Ŀ��д������");
			    }   
			}	
		}

	}
     
    var url = "dzdy_dzbgl.do?method=saveDzb";
    ajaxSubFormWithFun("DzbglForm", url, function (data) {
        if (data["message"] == "����ɹ���") {
            showAlert(data["message"], {}, {
                "clkFun": function () {
                    var api = frameElement.api, W = api.opener;
                    W.jQuery("#dataTable").reloadGrid();
                    closeDialog();
                }
            });
        } else {
            showAlert(data["message"]);
        }
    });
}


function updateSaveDzb(dzblx,jgcount,xscount) {
	
	var ids = null;
	var value = dzblx.value;
	var length = jgcount.value;
	var lengthN = xscount.value;
	length=0;
	lengthN=0;
	if(value == "�̹���֧��"){
		if(length ==0){
			ids = "";
			if (!checkNotNull(ids)) {
		        return showAlert("�뽫��<font color='red'>*</font>��Ŀ��д������");
		    }
		}else{
			for(var i=0;i<length;i++){
				ids = "jgzwmc"+i+ "-" + "jzgdh"+i;
				if (!checkNotNull(ids)) {
			        return showAlert("�뽫��<font color='red'>*</font>��Ŀ��д������");
			    }
			}
		}

	}else if(value == "ѧ����֧��"){
		if(lengthN ==0){
			ids = "";
			if (!checkNotNull(ids)) {
		        return showAlert("�뽫��<font color='red'>*</font>��Ŀ��д������");
		    }
		}else{
			for(var i=0;i<lengthN;i++){
				ids = "zwmc"+i + "-" + "xsdh"+i;
				if (!checkNotNull(ids)) {
			        return showAlert("�뽫��<font color='red'>*</font>��Ŀ��д������");
			    }
			}
		}
	}
    var url = "dzdy_dzbgl.do?method=updateSaveDzb";
    ajaxSubFormWithFun("DzbglForm", url, function (data) {
        if (data["message"] == "����ɹ���") {
            showAlert(data["message"], {}, {
                "clkFun": function () {
                    var api = frameElement.api, W = api.opener;
                    W.jQuery("#dataTable").reloadGrid();
                    closeDialog();
                }
            });
        } else {
            showAlert(data["message"]);
        }
    });
}


function hjAddDzb(dzblx,jgcount,xscount) {
    var hjsjOld = jQuery("#hjsjOld").val();
    var hjsj = jQuery("#hjsj").val();
    if (hjsjOld == hjsj) {
        showAlertDivLayer("���ܸ�֮ǰ����ʱ����ͬ��");
        return false;
    }
    var ids = null;
	var value = dzblx.value;
	var length = jgcount.value;
	var lengthN = xscount.value;
	if(value == "�̹���֧��"){
		if(length ==0){
			ids = "1";
			if (!checkNotNull(ids)) {
		        return showAlert("�뽫��<font color='red'>*</font>��Ŀ��д������");
		    }   
		}else{
			for(var i=0;i<length;i++){
				ids = "jgzwmc"+i+ "-" + "jzgdh"+i;
				if (!checkNotNull(ids)) {
			        return showAlert("�뽫��<font color='red'>*</font>��Ŀ��д������");
			    }   
			}
		}
		
	}else if(value == "ѧ����֧��"){
		if(lengthN ==0){
			ids = "1"; 
			if (!checkNotNull(ids)) {
		        return showAlert("�뽫��<font color='red'>*</font>��Ŀ��д������");
		    }   
		}else{

			for(var i=0;i<lengthN;i++){
				ids = "zwmc"+i + "-" + "xsdh"+i;
				if (!checkNotNull(ids)) {
			        return showAlert("�뽫��<font color='red'>*</font>��Ŀ��д������");
			    }   
			}
		}
	}
    var url = "dzdy_dzbgl.do?method=hjAddDzb";
    ajaxSubFormWithFun("DzbglForm", url, function (data) {
        if (data["message"] == "����ɹ���") {
            showAlert(data["message"], {}, {
                "clkFun": function () {
                    var api = frameElement.api, W = api.opener;
                    W.jQuery("#dataTable").reloadGrid();
                    closeDialog();
                }
            });
        } else {
            showAlert(data["message"]);
        }
    });
}


//����˲鿴
function dzbLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='View(\""
        + rowObject['dzbid'] + "\",\"" + rowObject['dzbhjid'] + "\");'>" + cellValue
        + "</a>";
}

//������޸�
function View(dzbid, dzbhjid) {
    showDialog("��֧����Ϣ", 900, 450, "dzdy_dzbgl.do?method=getDzbInfo&dzbid=" + dzbid + "&dzbhjid=" + dzbhjid);
}



//dcglbh,�������ܱ��
var DCGLBH = "zhdj_dzdy_dzbgl.do";

//�Զ��嵼�� ����
function exportConfig() {
    //DCCLBH�������ܱ��,ִ�е�������
    customExport(DCGLBH, exprotData);
}

//��������
function exprotData() {
    setSearchTj();//���ø߼���ѯ����
    var url = "dzdy_dzbgl.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}



//����
function importConfig(){
    toImportDataNew("IMPORT_DZBXX");
    return false;
}


/**
 * �����¿�ܵ��빦�����
 *
 * @param drmkdm ���빦�ܴ���
 * @return
 */
function toImportDataNew(drmkdm){
    var _SSO_DR_PATH = 'out_access.do?gnbh=import&toPage=/xgweb/dr/out_login.html';
    if(drmkdm == null || drmkdm == undefined || jQuery.trim(drmkdm) == ""){
        alert("����ģ�����δ����!");
        return false;
    }
    var url = _SSO_DR_PATH + "&drmkdm=" + drmkdm;
    showDialog('����',720,580,url,{close:function(){
        if (jQuery("#search_go")){
            jQuery("#search_go").click();
        }
    }});
    return false;
}



