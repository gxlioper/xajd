/**
 * ־Ը�����������js
 */

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * ѧ�Ÿ�ʽ��
 */
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='zyfwSqShow(\""
			+ rowObject["fwid"]+"\");'>" + cellValue
			+ "</a>";
}

/**
 * ����ص������ȡ
 */
function fwddSubString(cellValue, rowObject){
	var cellValueNotNull = cellValue ? cellValue : "";
	var cellValueNew = cellValueNotNull.length > 20 ? cellValue.substring(0,20)+"..." : cellValueNotNull;
	return "<span title='"+cellValueNotNull+"' >" + cellValueNew + "</span>";
}


/**
 * �����ı���
 */
var checkId = 'hdmc-hdzt-hdrq-bjmc-hdfzrxm-hdfzrlxdh';

function bjhdJgSave() {
	if(!checkNotNull(checkId)){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}

	var url = "bjhdgl_bjhdjg.do?method=bjhdJgSave";
	ajaxSubFormWithFun("BjhdJgForm", url, function(data) {
		 if(data["message"]=="����ɹ���"||data["message"]=="�ύ�ɹ���"){
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
 * �༭�ı���
 */
function bjhdJgSaveForEdit() {
	if(!checkNotNull(checkId)){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}
	var url = "bjhdgl_bjhdjg.do?method=bjhdJgSaveForEdit";
	ajaxSubFormWithFun("BjhdJgForm", url, function(data) {
		 if(data["message"]=="����ɹ���"||data["message"]=="�ύ�ɹ���"){
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


function bjhdJgSaveForUpload() {

    var url = "bjhdgl_bjhdjg.do?method=bjhdJgSaveForEdit";
    ajaxSubFormWithFun("BjhdJgForm", url, function(data) {
        if(data["message"]=="����ɹ���"||data["message"]=="�ύ�ɹ���"){
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


function rdjpbjhd() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("��ѡ��һ����Ҫ�϶���Ʒ�༶��ļ�¼��");
    }
    if (rows.length == 1) {
        var url = 'bjhdgl_bjhdjg.do?method=rdjpbjhd&jgid=' + rows[0]["jgid"];
        var title = "�϶�ʾ����������";
        showDialog(title, 400, 250, url);
    }
    else {
        showDialog("�����϶�", 500, 250, "bjhdgl_bjhdjg.do?method=bjhdJgPlrd");
    }


}

function saveForPlRd(sfjp) {

    var rows = jQuery("#dataTable").getSeletRow();
    var jgids = new Array();

    jQuery.each(rows, function(i, row) {
        jgids.push(row["jgid"]);
    });
    jQuery.post("bjhdgl_bjhdjg.do?method=saveForPlRd", {
        jgids : jgids,
        sfjp : sfjp
    }, function(data) {
        showAlertDivLayer(data["message"], {}, {
            "clkFun" : function() {
                jQuery("#dataTable").reloadGrid();
            }
        });
    }, 'json');
}




function saveSfjp() {

    var url = "bjhdgl_bjhdjg.do?method=bjhdJgSaveForEdit";
    ajaxSubFormWithFun("BjhdJgForm", url, function(data) {
        if(data["message"]=="����ɹ���"||data["message"]=="�ύ�ɹ���"){
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
 * ���뵯��ҳ��
 */
function add() {
	var url = "bjhdgl_bjhdjg.do?method=bjhdJgAdd";
	showDialog("�༶����", 800, 550, url);
}

/**
 * �޸ĵ���ҳ��
 */
function edit() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
    } else {

        var sjly = rows[0]["sjly"];
        if ("1" == sjly) {
            showAlertDivLayer("������̹����ļ�¼�����޸ģ�");
            return false;
        }

        var url = 'bjhdgl_bjhdjg.do?method=bjhdJgEdit&jgid=' + rows[0]["jgid"];
        var title = "�༶�����޸�";
        showDialog(title, 800, 550, url);
    }

}


/*
�ϴ�����
 */
function upload() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�ϴ����ϵļ�¼��");
    } else {
        var url = 'bjhdgl_bjhdjg.do?method=bjhdJgUpload&jgid=' + rows[0]["jgid"];
        var title = "�༶������ϴ�";
        showDialog(title, 800, 550, url);
    }

}

/**
 * ɾ��
 */
function del() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length == 0) {
        showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
        return false;
    }
    for(var i=0;i<rows.length;i++){
        if (rows[i]["sjly"] == "1") {
            showAlertDivLayer("������̹����ļ�¼����ɾ����");
            return false;
        }
    }

    showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
        "okFun" : function() {
            jQuery.post("bjhdgl_bjhdjg.do?method=bjhdJgDel", {
                    values : ids.toString()
                },
                function(data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
        }
    });
}


//dcglbh,�������ܱ��
var DCGLBH = "sxzzjy_bjhdgl_bjhdjg.do";

//�Զ��嵼�� ����
function exportConfig() {
    //DCCLBH�������ܱ��,ִ�е�������
    customExport(DCGLBH, exprotData);
}

//��������
function exprotData() {
    setSearchTj();//���ø߼���ѯ����
    var url = "bjhdgl_bjhdjg.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}







