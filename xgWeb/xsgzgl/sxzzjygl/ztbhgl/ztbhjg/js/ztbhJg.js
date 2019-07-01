/**
 * ־Ը�����������js
 */

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


/**
 * �����ı���
 */
var checkId = 'hdzt-hdrq-bhmd-bhyc-bhsj-dd';

function ztbhJgSave() {
	if(!checkNotNull(checkId)){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}

	var url = "ztbhgl_ztbhjg.do?method=ztbhJgSave";
	ajaxSubFormWithFun("ZtbhJgForm", url, function(data) {
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
function ztbhJgSaveForEdit() {
	if(!checkNotNull(checkId)){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}
	var url = "ztbhgl_ztbhjg.do?method=ztbhJgSaveForEdit";
	ajaxSubFormWithFun("ZtbhJgForm", url, function(data) {
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


function ztbhJgSaveForupload() {

    var url = "ztbhgl_ztbhjg.do?method=ztbhJgSaveForEdit";
    ajaxSubFormWithFun("ZtbhJgForm", url, function(data) {
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


function rdsfxztbh() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("��ѡ��һ����Ҫ�϶�ʾ����������ļ�¼��");
    }
    if (rows.length == 1) {
        var url = 'ztbhgl_ztbhjg.do?method=rdsfxztbh&jgid=' + rows[0]["jgid"];
        var title = "�϶�ʾ����������";
        showDialog(title, 400, 250, url);
    }
    else {
        showDialog("�����϶�", 500, 250, "ztbhgl_ztbhjg.do?method=ztbhJgPlrd");
    }

}



function saveSfsfx() {

    var url = "ztbhgl_ztbhjg.do?method=ztbhJgSaveForEdit";
    ajaxSubFormWithFun("ZtbhJgForm", url, function(data) {
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


function saveForPlRd(sfsfx) {

    var rows = jQuery("#dataTable").getSeletRow();
    var jgids = new Array();

    jQuery.each(rows, function(i, row) {
        jgids.push(row["jgid"]);
    });
    jQuery.post("ztbhgl_ztbhjg.do?method=saveForPlRd", {
        jgids : jgids,
        sfsfx : sfsfx
    }, function(data) {
        showAlertDivLayer(data["message"], {}, {
            "clkFun" : function() {
                jQuery("#dataTable").reloadGrid();
            }
        });
    }, 'json');
}


/**
 * ���뵯��ҳ��
 */
function add() {
	var url = "ztbhgl_ztbhjg.do?method=ztbhJgAdd";
	showDialog("�����������", 800, 550, url);
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

        var url = 'ztbhgl_ztbhjg.do?method=ztbhJgEdit&jgid=' + rows[0]["jgid"];
        var title = "���������޸�";
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

        // var sjly = rows[0]["sjly"];
        // if ("1" == sjly) {
        //     showAlertDivLayer("������̹����ļ�¼�����޸ģ�");
        //     return false;
        // }

        var url = 'ztbhgl_ztbhjg.do?method=ztbhJgUpload&jgid=' + rows[0]["jgid"];
        var title = "�����������ϴ�";
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
            jQuery.post("ztbhgl_ztbhjg.do?method=ztbhJgDel", {
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
var DCGLBH = "sxzzjy_ztbhgl_ztbhjg.do";

//�Զ��嵼�� ����
function exportConfig() {
    //DCCLBH�������ܱ��,ִ�е�������
    customExport(DCGLBH, exprotData);
}

//��������
function exprotData() {
    setSearchTj();//���ø߼���ѯ����
    var url = "ztbhgl_ztbhjg.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}

var i = 0;//�к�
var clickRowId;//������к�
var table;
function onShow(type){
    table = jQuery("#shlccx_table");

    if("update"==type){
        jQuery.post("ztbhgl_ztbhjg.do?method=getBjxxUpdate",{jgid:jQuery("#jgid").val()},function(data){
            if(data.length > 0){
                for(var a=0;a<data.length;a++){
                    addTr();

                    jQuery("#bjmc"+a).val(data[a].bjmc);
                    jQuery("#bjdm"+a).val(data[a].bjdm);
                    jQuery("#ydrs"+a).val(data[a].ydrs);
                    jQuery("#sdrs"+a).val(data[a].sdrs);
                    jQuery("#qqrs"+a).val(data[a].qqrs);
                    jQuery("#symc"+a).html(data[a].symc);
                    jQuery("#fdyxm"+a).html(data[a].fdyxm);
                }
            }
        },'json');
    }
}
function addTr(){
    var tr;
    tr += "<tr id='"+i+"'>";
    tr += "<td><input type='text' id='bjmc"+i+"' readonly='readonly' class='text_nor'>";
    tr += "<input type='hidden' name='bjxxs["+i+"].bjdm' id='bjdm"+i+"' />";
    tr += "<button class=\"btn_01\" type=\"button\" onclick=\"butClick(this)\">ѡ��</button>";
    tr += "</td>";
    tr += "<td><input name='bjxxs["+i+"].ydrs' id='ydrs"+i+"' maxlength='4' style=\"width: 50px;\"></td>";
    tr += "<td><input name=\"bjxxs["+i+"].sdrs\" id='sdrs"+i+"' maxlength=\"4\" style=\"width: 50px;\"></td>";
    tr += "<td><input name=\"bjxxs["+i+"].qqrs\" id='qqrs"+i+"' maxlength=\"4\" style=\"width: 50px;\"></td>";
    tr += "<td><span id='symc"+i+"'></span></td>";
    tr += "<td><span id='fdyxm"+i+"'></span></td>";
    tr += "<td><a href='javascript:void(0);' onclick='delTr(this);return false;'>ɾ��</a></td>";
    tr += "</tr>";
    table.append(tr);
    i++;
}
function butClick(tar){
    clickRowId = jQuery(tar).parent().parent().attr("id");
    showDialog('��ѡ��һ���༶',800,500,'ztbhgl_ztbhjg.do?method=getBj');
}

function delTr(td) {
    jQuery(td).parent().parent().remove();
    i--;
}

function setBjxxCallBack(row){
    jQuery("#bjmc"+clickRowId).val(row[0]['bjmc']);
    jQuery("#bjdm"+clickRowId).val(row[0]['bjdm']);
    jQuery("#ydrs"+clickRowId).val(row[0]['bjrs']);
    jQuery("#symc"+clickRowId).html(row[0]['symc']);
    jQuery("#fdyxm"+clickRowId).html(row[0]['fdyxm']);
}


