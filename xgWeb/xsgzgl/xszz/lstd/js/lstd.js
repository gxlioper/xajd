
function lstdsq(){
    /*var isopen = jQuery("#isopen").val();
    if ("false" == isopen || isopean == ''){
        showAlert("��ǰδ�������������룬����ϵ����Ա��");
        return false;
    }*/
    showDialog('��ɫͨ������',780,450,'xszz_lstd.do?method=lstdsqZj');
}

function lstdxg(){
    /*var isopen = jQuery("#isopen").val();
    if ("false" == isopen || isopean == ''){
        showAlert("��ǰδ�������������룬����ϵ����Ա��");
        return false;
    }*/
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
        return false;
    }
    var shzt = rows[0]["shzt"];
    if ("0" != shzt&&"3" != shzt){
        showAlertDivLayer("��������ݲ����޸ģ�");
        return false;
    }

    showDialog('��ɫͨ�������޸�',780,450,'xszz_lstd.do?method=lstdsqXg&sqid='+ rows[0]["sqid"]);
}

function selectChange(target){
    if(target.value == '01'){
        jQuery("#dkjeTh").removeAttr("style");
        jQuery("#dkjeTd").removeAttr("style");
        jQuery("#sqhjje").attr("disabled","disabled");
        jQuery("#sqhjje").val("");
    } else if(target.value == '02'){
        jQuery("#dkjeTh").attr("style","display:none");
        jQuery("#dkjeTd").attr("style","display:none");
        jQuery("#dkje").val("");
        jQuery("#sqhjje").attr("disabled","disabled");
    } else {
        jQuery("#dkjeTh").attr("style","display:none");
        jQuery("#dkjeTd").attr("style","display:none");
        jQuery("#dkje").val("");
        jQuery("#sqhjje").removeAttr("disabled");
    }
}
function selectTab(obj,shzt){
    jQuery("#shzt").val(shzt);

    if (shzt == "dsh"){
        // jQuery("#dataTable").initGrid(gridSetting);
        jQuery("#li_sh").css("display","");
        jQuery("#li_qx").css("display","none");
    } else {
        // jQuery("#dataTable").initGrid(gridSetting2);
        jQuery("#li_sh").css("display","none");
        jQuery("#li_qx").css("display","");
    }

    jQuery(".ha").removeClass("ha");
    jQuery(obj).parent().addClass("ha");

    searchRs();
}

function lstdsqDelete() {
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length == 0){
        showAlertDivLayer("��ѡ����Ҫɾ���������¼��");
    } else {
        var rows = jQuery("#dataTable").getSeletRow();

        for(var i=0;i<ids.length;i++){
            if(rows[i]['shzt']!='0'&&rows[i]['shzt']!='3'){
                showAlertDivLayer("��������ݲ���ɾ����");
                return false;
            }
        }

        showConfirmDivLayer("��ȷ��Ҫɾ����������",{"okFun":function(){
            jQuery.post("xszz_lstd.do?method=delLstdsq",{values:ids.toString()},function(data){
                showAlertDivLayer(data["message"]);
                jQuery("#dataTable").reloadGrid();
            },'json');
        }});
    }
}

function lstdsh(){
    var rows = jQuery("#dataTable").getSeletRow();
    if(rows.length == 0){
        showAlertDivLayer("������ѡ��һ����¼��");
        return false;
    } else if(rows.length == 1){
        var url = "xszz_lstd.do?method=lstdsh&sqid="+rows[0]["sqid"]+"&xh="+rows[0]["xh"]+"&gwid="+rows[0]["gwid"]+"&shid="+rows[0]["shid"]+"&shlc="+rows[0]["shlc"];
        showDialog("��ɫͨ�����",750,700,url);
    } else {
        showDialog("��ɫͨ���������",500,300,"xszz_lstd.do?method=lstdplsh");
    }
}

function lstdshSave(){
    if(jQuery("#shzt").val() != "1" && jQuery("#shzt").val() != "2"){
        showAlertDivLayer("����д���״̬��");
        return false;
    }

    if(jQuery("#shyj").val() == ""){
        showAlertDivLayer("����д��������");
        return false;
    }
    var message;
    if(jQuery("#shzt").val() == "1"){
        message = "ͨ��";
    }
    if(jQuery("#shzt").val() == "2"){
        message = "��ͨ��";
    }
    showConfirmDivLayer("��ȷ��" + message + "��������",{"okFun":function(){
        var url = "xszz_lstd.do?method=lstdsh&type=save";
        ajaxSubFormWithFun("lstdForm",url,function(data){
            showAlertDivLayer(data["message"],{},{"clkFun":function(){
                if (parent.window){
                    refershParent();
                }
            }});
        });
    }});
}

//�������
function cancelSh(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1){
        showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
    } else {
        var splc = rows[0]["shlc"];
        var shid = rows[0]["shid"];
        var sqid = rows[0]["sqid"];
        var shzt = rows[0]["shzt"];
        showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
            jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
                // �ж��Ƿ����һ������(1:���һ�������ɹ���
                if("1" == data["cancelFlg"]){
                    jQuery.post("xszz_lstd.do?method=cancelSh",{sqid:sqid,shzt:shzt},function(result){
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

function cancel(){
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length == 0) {
        showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
    } else if (ids.length >1 ) {
        showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
    } else {
        var rows = jQuery("#dataTable").getSeletRow();
        for(var i=0;i<ids.length;i++){
            if(rows[i]['shzt']!='5'){
                showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
                return false;
            }
        }
        showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��",{"okFun":function(){
            jQuery.post("xszz_lstd.do?method=cancel",
                {
                    values:ids.toString(),
                    splcid : rows[0]['shlc']
                },function(data){
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                },'json');
        }});
    }
}
function submit(){
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length != 1){
        showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
    }else{
        var rows = jQuery("#dataTable").getSeletRow();
        var url = "xszz_lstd.do?method=submit";
        for(var i=0;i<ids.length;i++){
            if(rows[i]['shzt']!='0' && rows[i]['shzt']!='3' ){
                showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
                return false;
            }
        }
        showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
            jQuery.post(url,
                {
                    values:ids.toString(),
                    xh : rows[0]['xh'],
                    shlc : rows[0]['shlc'],
                    shzt : rows[0]['shzt']
                },function(data){
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                },'json');
        }});
    }
}

function Lcinfo(){
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length != 1){
        showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
    } else {
        var shzt = rows[0]["shzt"];
        if ("0" == shzt){
            showAlertDivLayer(jQuery("#lable_wxglcxx").val());
            return false;
        }
        showDialog("��ɫͨ���������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['shlc']);
    }
}

function print() {
    var t = jQuery("#dataTable");
    var ids = t.getSeletIds();
    if (ids.length == 0) {
        showAlertDivLayer('������ѡ��һ����¼!');
        return false;
    }

    window.open("xszz_lstd.do?method=print&sqid=" + ids);
}