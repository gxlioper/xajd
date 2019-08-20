function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}
function view(sqid) {
    var height = jQuery(window).height();
    showDialog("��˲�ѯ", 600, height-250, "xyfd_fdkcsq.do?method=fdkcsqView&sqid="+sqid+"&t="+new Date().getTime());
}

function xhLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='view(\""
        + rowObject["sqid"] + "\");'>" + cellValue
        + "</a>";
}
//�������Ӽ�¼ҳ��
function zjjl() {
    var height = jQuery(window).height();
    showDialog("�����¼�¼", 600, height-250, "xyfd_xyzyzxjl.do?method=addZxjl&t="+new Date().getTime());

}
function check() {
    var len = jQuery("#jtjc").val().length;
    if(len<20){
        showAlertDivLayer("����������20���ַ���");
        return false;
    }
    if(len>500){
        showAlertDivLayer("�������500���ַ���");
        return false;
    }
}

function saveZxjl() {
    var zxyy = jQuery("#zxyy").val();
    var checkId = 'xh';
    if(zxyy=='01'){//�ҿ�
        checkId += '-gks'
    }
    if(zxyy=='07'){//����
        checkId += '-jtyy';
    }
    if (!checkNotNull(checkId)) {
        showAlertDivLayer("�뽫��������д������");
        return false;
    }
    check();
    var url = 'xyfd_xyzyzxjl.do?method=addZxjl&type=save';
    ajaxSubFormWithFun("demoForm",url,function(data){
        showAlertDivLayer(data["message"],{},{"clkFun":function(){
            if (parent.window){
                refershParent();
            }
            iFClose();
        }});
    });

}
function xgjl() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    var userName = jQuery("#userName").val();
    if(ids.length != 1){
        showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
        return false;
    }
    if(rows[0]['zxzt']=='1'){
        showAlertDivLayer("���޷��޸ĸü�¼���ü�¼�ѱ����ۣ�");
        return false;
    }
    var height = jQuery(window).height();
    showDialog("�޸ļ�¼", 600, height-250, "xyfd_xyzyzxjl.do?method=updateZxjl&zxid=" + rows[0]['zxid']+ "&t=" + new Date().getTime());
}

function updateZxjl() {
    var zxyy = jQuery("#zxyy").val();
    var checkId = 'zxyy';
    if(zxyy=='01'){//�ҿ�
        checkId += '-gks'
    }
    if(zxyy=='07'){//����
        checkId += '-jtyy';
    }
    if (!checkNotNull(checkId)) {
        showAlertDivLayer("�뽫��������д������");
        return false;
    }
    check();
    if(zxyy=='01'){//�ҿ�
        jQuery("#jtyy").val('');
    }
    if(zxyy=='07'){//����
        jQuery("#gks").val('');
    }

    var url = 'xyfd_xyzyzxjl.do?method=updateZxjl&type=save';
    ajaxSubFormWithFun("demoForm",url,function(data){
        showAlertDivLayer(data["message"],{},{"clkFun":function(){
            if (parent.window){
                refershParent();
            }
            iFClose();
        }});
    });

}
function del() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    var userName = jQuery("#userName").val();
    if(ids.length < 1){
        showAlertDivLayer("������ѡ��һ����Ҫɾ���ļ�¼��");
        return false;
    }
    for(var i=0;i<ids.length;i++){
        if(rows[i]['zxzt']=='1'){
            showAlertDivLayer("���޷�ɾ����" + (i+1) + "��ѡ�еļ�¼���ü�¼�ѱ����ۣ�");
            return false;
        }
    }
    showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
        "okFun" : function() {
            jQuery.post("xyfd_xyzyzxjl.do?method=delZxjl", {
                values : ids.toString()
            }, function(data) {
                var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>������";
                mes+="</br>";
                if(data["nodel"]!="-1"){
                    mes+="<font color='red'>"+data["nodel"]+"</font>";
                    mes+="���������в���ɾ��!";
                }
                showAlertDivLayer(mes);
                jQuery("#dataTable").reloadGrid();
            }, 'json');
        }
    });
}


function ckjl() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if(ids.length != 1){
        showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
        return false;
    }
    var height = jQuery(window).height();
    showDialog("�鿴��¼", 600, height-250, "xyfd_xyzyzxjl.do?method=viewZxjl&zxid=" + rows[0]['zxid']+ "&t=" + new Date().getTime());
}
