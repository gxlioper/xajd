function add(){
    var isopen = jQuery("#isopen").val();
    if(isopen==null||isopen==''){
        showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
        return false;
    }
    if ("false" == isopen){
        showAlertDivLayer("��ǰδ�������룬����ϵ����Ա��");
        return false;
    }
    var url = "sxzzjy_grxfjssq.do?method=grxfjssqAdd";
    var title = "����";
    showDialog(title,700,550,url);
}

function update() {
    var isopen = jQuery("#isopen").val();
    if(isopen==null||isopen==''){
        showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
        return false;
    }
//			if ("false" == isopen){
//				showAlertDivLayer("��ǰδ�������룬����ϵ����Ա��");
//				return false;
//			}
    var rows = jQuery("#dataTable").getSeletRow();

    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
    } else {
        var shzt = rows[0]["shzt"];
        if ("0" != shzt&&"3" != shzt) {
            showAlertDivLayer("ֻ��δ�ύ�����˻صļ�¼�����޸ģ�");
            return false;
        }
        var url = 'sxzzjy_grxfjssq.do?method=grxfjssqUpdate&sqid=' + rows[0]["sqid"] +"&xh="+rows[0]["xh"];
        var title = "�޸�";
        showDialog(title,700,550,url);
    }
}

//ɾ��
function del() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length == 0) {
        showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
    } else {
        for (var i = 0; i < ids.length; i++) {
            if (rows[i]["shzt"] != "0" && rows[i]["shzt"] != "3") {
                showAlertDivLayer("ֻ��ɾ��δ�ύ�������˻صļ�¼��");
                return false;
            }
        }
        showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
            "okFun" : function() {
                jQuery.post("sxzzjy_grxfjssq.do?method=grxfjssqDel", { values : ids.toString() },
                    function(data) {
                        var mes = "�ɹ�ɾ����<font color='green'>&nbsp;" + data["num"] + "&nbsp;</font>������";
                        showAlertDivLayer(mes);
                        jQuery("#dataTable").reloadGrid();
                    }, 'json');
            }
        });
    }
}

function view(sqid,xh) {
    showDialog("�鿴", 700,550, "sxzzjy_grxfjssq.do?method=grxfjssqView&sqid=" + sqid+"&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='view(\""
        + rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
        + "</a>";
}

function submit(){
    var ids = jQuery("#dataTable").getSeletIds();
    if(ids.length != 1){
        showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
        return false;
    }else{
        var rows = jQuery("#dataTable").getSeletRow();
        var isopen = jQuery("#isopen").val();
        if(isopen==null||isopen==''){
            showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
            return false;
        }
        if('3'!=rows[0]["shzt"]&&"true"!=isopen){
            showAlertDivLayer("��ǰδ�������룬����ϵ����Ա��");
            return false;
        }
        var url = "sxzzjy_grxfjssq.do?method=grxfjssqSubmit";
        if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
            showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
            return false;
        }
        showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
            jQuery.post(url,
                {sqid:rows[0]["sqid"]},function(data){
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                },'json');
        }});
    }
}

function cancel(){
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
    } else {
        var rows = jQuery("#dataTable").getSeletRow();
        if (rows[0]['shzt'] != '5') {
            showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
            return false;
        }
        showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��",{"okFun":function(){
            jQuery.post("sxzzjy_grxfjssq.do?method=grxfjssqCancel",
                {
                    sqid:rows[0]['sqid'],
                    splc : rows[0]['splc']
                },function(data){
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                },'json');
        }});
    }
}


function grxfjsshLcinfo(){
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length != 1){
        showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
    } else {
        showDialog("����ѧ�罨���������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
    }
}

function printWord(){
    var rows = jQuery("#dataTable").getSeletRow();
    var ids = jQuery("#dataTable").getSeletIds();
    var len = rows.length;
    var url = "";
    if (len == 1) {
        url = "sxzzjy_grxfjssq.do?method=getPrint";
        url += "&sqid=" + rows[0]["sqid"];
        window.open(url);
    } else if (len == 0) {
        showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
        return false;
    } else {
        url = "sxzzjy_grxfjssq.do?method=getPrintZip";
        url += "&value=" + ids;
        window.open(url);
    }
}