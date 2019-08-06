function searchRs() {
    var map = getSuperSearch();
    var shzt = jQuery("#shzt").val();
    if (null!=shzt&&shzt != "") {
        map["shzt"] = shzt;
    }else{
        map["shzt"] = "dsh";
    }
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
// �������
function sh() {
    var rows = jQuery("#dataTable").getSeletRow();
    var shzt = jQuery("#shzt").val();
    var height = jQuery(window).height();
    if (rows.length == 0 ) {
        showAlertDivLayer("��ѡ����Ҫ��˵ļ�¼");
        return false;
    }
    if (shzt == "ysh") {
        showAlertDivLayer("�Ѵ����¼�����ٴ����");
        return false;
    } else if (rows.length == 1) {
        var url = "xyfd_fdkcsh.do?method=fdkcDgsh&sqid=" + rows[0]["sqid"] + '&xh='
            + rows[0]["xh"] + '&shid=' + rows[0]["shid"]+'&t='+new Date().getTime();
        showDialog(jQuery("#gnmkmc").val(), 600, height-250, url);
    } else {
        showDialog("�������", 500, 250, "xyfd_fdkcsh.do?method=fdkcPlsh");
    }
}
// �������
function savePlsh(shzt, shyj) {
    var rows = jQuery("#dataTable").getSeletRow();
    var guid = new Array();
    var gwid = new Array();
    var sqrids = new Array();
    var yxgss = new Array();
    var gss = new Array();
    jQuery.each(rows, function(i, row) {
        guid.push(row["sqid"]);
        gwid.push(row["gwid"]);
        sqrids.push(row["yhm"]);
    });
    jQuery.post("xyfd_fdkcsh.do?method=fdkcPlsh&type=save", {
        shzt : shzt,
        splc : rows[0]["splc"],
        id : guid,
        gwids : gwid,
        sqrids : sqrids,
        shyj : shyj
    }, function(data) {
        showAlertDivLayer(data["message"], {}, {
            "clkFun" : function() {
                jQuery("#dataTable").reloadGrid();
            }
        });
    }, 'json');
}

// �л�Tabҳ
function selectTab(obj, shzt) {
    jQuery("#shzt").val(shzt);
    if (shzt == "dsh") {
        jQuery("#li_sh").css("display", "");
        jQuery("#li_qx").css("display", "none");
        var map = getSuperSearch();
        map["shzt"]="dsh";
        gridSetting["params"] = map;
        jQuery("#dataTable").initGrid(gridSetting);
    } else {
        jQuery("#li_sh").css("display", "none");
        jQuery("#li_qx").css("display", "");
        var map = getSuperSearch();
        map["shzt"]="ysh";
        gridSetting2["params"] = map;
        jQuery("#dataTable").initGrid(gridSetting2);
    }
    jQuery(".ha").removeClass("ha");
    jQuery(obj).parent().addClass("ha");
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
            jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
                // �ж��Ƿ����һ������(1:���һ�������ɹ���
                if("1" == data["cancelFlg"]){
                    jQuery.post("xyfd_fdkcsh.do?method=cancelSh",{sqid:sqid,shzt:shzt},function(result){
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

function splcInfo(){
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (1!=ids.length){
        showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
    } else {
        showDialog("�������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
    }
}

function saveSh(){
    var shzt = jQuery("#shjg").val();
    if (jQuery("#shzt").val() == "" || jQuery("#shyj").val() == ""){
        showAlert("�뽫��������д������");
        return false;
    }
    var url = "xyfd_fdkcsh.do?method=fdkcDgsh&type=save";
    ajaxSubFormWithFun("demoForm",url,function(data){
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

var DCCLBH = "xyfd_xyfd_fdkcsq.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
    //DCCLBH�������ܱ��,ִ�е�������
    customExport(DCCLBH, hdshExportData);
}

//��������
function hdshExportData() {
    setSearchTj();//���ø߼���ѯ����
    var shzt = jQuery("#shzt").val();
    var url = "xyfd_fdkcsh.do?method=export&shzt="+shzt+"&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}