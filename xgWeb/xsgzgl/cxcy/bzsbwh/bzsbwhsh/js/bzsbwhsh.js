function view(sqid) {
    showDialog("���´�ҵ�����걨�鿴", 700, 450, "cxcy_bzsbwhsq.do?method=bzsbwhsqView&sqid="+sqid);
}

function sh(){

    var rows = jQuery("#dataTable").getSeletRow();
    var shzt = jQuery("#shzt").val();
    if(shzt=="ysh"){
        showAlertDivLayer("�Ѵ����¼�����ٴ����");
        return false;
    } else if(rows.length == 0){
        showAlertDivLayer("��ѡ��һ����Ҫ��˵ļ�¼��");
        return false;
    } else if (rows.length == 1){
        var url = "cxcy_bzsbwhsh.do?method=bzsbwhDgsh&sqid="+rows[0]["sqid"]+
            '&shid=' +rows[0]["shid"]+'&gwid=' +rows[0]["gwid"]+'&splc=' +rows[0]["splc"];
        showDialog("�����걨���",700,520,url);
    } else{
        showDialog("�����걨�������",500,300,"cxcy_bzsbwhsh.do?method=bzsbwhPlsh");
    }
}
function saveSh(){
    if (jQuery("#shjg").val() == "" || jQuery("#shyj").val() == ""){
        showAlert("�뽫��������д������");
        return false;
    }
    var url = "cxcy_bzsbwhsh.do?method=saveDgsh";
    ajaxSubFormWithFun("form",url,function(data){
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
//�������
function savePlsh(shzt, shyj) {
    var rows = jQuery("#dataTable").getSeletRow();
    var sqid = new Array();
    var gwid = new Array();
    var xhs = new Array();
    var splcs = new Array();
    jQuery.each(rows, function(i, row) {
        sqid.push(row["sqid"]);
        gwid.push(row["gwid"]);
        xhs.push(row["xh"]);
        splcs.push(row["splc"]);
    });
    jQuery.post("cxcy_bzsbwhsh.do?method=savePlsh", {
        shzt : shzt,
        splcs : splcs,
        ids : sqid,
        gwids : gwid,
        xhs : xhs,
        shyj : shyj
    }, function(data) {

        showAlertDivLayer(data["message"], {}, {
            "clkFun" : function() {
                jQuery("#dataTable").reloadGrid();
            }
        });
    }, 'json');
}

//��˳���
function cancelSh(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1){
        showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
    } else {
        var splc = rows[0]["splc"];
        var shid = rows[0]["shid"];
        var sqid = rows[0]["sqid"];
        var shzt = rows[0]["shzt"];
        showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
            jQuery.post("cxcy_bzsbwhsh.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
                // �ж��Ƿ����һ������(1:���һ�������ɹ���
                if("1" == data["cancelFlg"]){
                    jQuery.post("cxcy_bzsbwhsh.do?method=cancelSh",{sqid:sqid,shzt:shzt},function(result){
                        showAlertDivLayer(result["message"],{},{"clkFun":function(){
                            jQuery("#dataTable").reloadGrid();
                        }});
                    },'json');
                }else{
                    showAlertDivLayer(data["message"],{},{"clkFun":function(){
                        jQuery("#dataTable").reloadGrid();
                    }});
                }

            },'json');
        }});
    }
}

function lcgz(){
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (1!=ids.length){
        showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
    } else {
        showDialog("�������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
    }
}
function exportConfig(){
    var DCCLBH='cxcy_bzsbwh.do';
    customExport(DCCLBH, exportData);
}
function exportData(){
    var DCCLBH='cxcy_bzsbwh.do';
    setSearchTj();//���ø߼���ѯ����
    var url = "cxcy_bzsbwhsq.do?method=exportData&dcclbh=" + DCCLBH+
        "&pkValue="+jQuery("#pkValue").val();//dcclbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}
