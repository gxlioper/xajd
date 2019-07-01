var gridSetting = {
    caption:"����ѧ�罨������",
    pager:"pager",
    url:"sxzzjy_grxfjssh.do?method=getList&type=query&shzt=dsh",
    colList:[
        {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
        {label:'��������',name:'splc', index: 'splc',hidden:true},
        {label:'ѧ��',name:'xh', index: 'xh',width:'6%',formatter:xhLink},
        {label:'����',name:'xm', index: 'xm',width:'6%'},
        {label:'�༶',name:'bjmc', index: 'bjmc',width:'9%'},
        {label:'ѧԺ',name:'xymc', index: 'xymc',width:'9%'},
        {label:'����',name:'xfjsmc', index: 'xfjsmc',width:'9%'},
        {label:'�걨����',name:'sblxmc', index: 'sblxmc',width:'9%'},
        {label:'ѧ��ѧ��',name:'xnxq', index: 'xnxq',width:'10%'},
        {label:'���״̬',name:'shztmc', index: 'shztmc',width:'8%'},
        {label:'���״̬����',name:'shzt', index: 'shzt',hidden:true},
        {label:'����',name:'sqlxmc', index: 'sqlxmc',width:'6%'},
        {label:'sqlx',name:'sqlx', index: 'sqlx',hidden:true},
        {label:'jgid',name:'jgid', index: 'jgid',hidden:true},
        {label:'ѧ��',name:'xq', index: 'xq',hidden:true},
        {label:'ѧ��',name:'xn', index: 'xn',hidden:true},
        {label:'���Id',name:'shid', index: 'shid',hidden:true},
        {label:'������λid',name:'gwid', index: 'gwid',hidden:true},
        {lable:'��������Id',name:'splc', index:'splc',hidden:true}
    ],
    params:{shzt:"dsh"},//Ĭ�ϴ����
    sortname: "sqsj",
    sortorder: "desc",
    radioselect:false
};
var gridSetting2 = {
    caption:"����ѧ�罨�������",
    pager:"pager",
    url:"sxzzjy_grxfjssh.do?method=getList&type=query",
    colList:[
        {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
        {label:'��������',name:'splc', index: 'splc',hidden:true},
        {label:'ѧ��',name:'xh', index: 'xh',width:'6%',formatter:xhLink},
        {label:'����',name:'xm', index: 'xm',width:'6%'},
        {label:'�༶',name:'bjmc', index: 'bjmc',width:'10%'},
        {label:'ѧԺ',name:'xymc', index: 'xymc',width:'10%'},
        {label:'����',name:'xfjsmc', index: 'xfjsmc',width:'10%'},
        {label:'�걨����',name:'sblxmc', index: 'sblxmc',width:'10%'},
        {label:'ѧ��ѧ��',name:'xnxq', index: 'xnxq',width:'10%'},
        {label:'���״̬',name:'shztmc', index: 'shztmc',width:'8%'},
        {label:'���״̬����',name:'shzt', index: 'shzt',hidden:true},
        {label:'����',name:'sqlxmc', index: 'sqlxmc',width:'6%'},
        {label:'sqlx',name:'sqlx', index: 'sqlx',hidden:true},
        {label:'jgid',name:'jgid', index: 'jgid',hidden:true},
        {label:'ѧ��',name:'xq', index: 'xq',hidden:true},
        {label:'ѧ��',name:'xn', index: 'xn',hidden:true},
        {label:'���Id',name:'shid', index: 'shid',hidden:true},
        {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
        {lable:'��������Id',name:'splc', index:'splc',hidden:true}
    ],
    params:{shzt:"ysh"},//Ĭ�ϴ����
    sortname: "sqsj",
    sortorder: "desc",
    radioselect:true
};

jQuery(function(){
    var map = getSuperSearch();
    gridSetting["params"] = map;
    jQuery("#dataTable").initGrid(gridSetting);
});

function searchRs(){
    var map = getSuperSearch();
    var shzt = jQuery("#shzt").val();
    if (shzt != ""){
        map["shzt"] = shzt;
    }
    jQuery("#dataTable").reloadGrid(map);
}


function selectTab(obj,shzt){
    jQuery("#shzt").val(shzt);
    if (shzt == "dsh"){
        jQuery("#li_sh").css("display","");
        jQuery("#li_qx").css("display","none");
        jQuery("#dataTable").initGrid(gridSetting);
    } else {
        jQuery("#li_sh").css("display","none");
        jQuery("#li_qx").css("display","");
        jQuery("#dataTable").initGrid(gridSetting2);
    }
    jQuery(".ha").removeClass("ha");
    jQuery(obj).parent().addClass("ha");
    searchRs();
}

function grxfjsSh(){

    var rows = jQuery("#dataTable").getSeletRow();
    var shzt = jQuery("#shzt").val();
    if(shzt=="ysh"){
        showAlertDivLayer("�Ѵ����¼�����ٴ����");
        return false;
    } else if(rows.length == 0){
        showAlertDivLayer("��ѡ��һ����Ҫ��˵ļ�¼��");
        return false;
    } else if (rows.length == 1){
        var url = "sxzzjy_grxfjssh.do?method=grxfjsSh&sqid="+rows[0]["sqid"]+'&xn=' +rows[0]["xn"]+
            '&shid=' +rows[0]["shid"]+'&gwid=' +rows[0]["gwid"]+'&splc=' +rows[0]["splc"] +
            '&xh=' + rows[0]["xh"]+'&sqlx=' + rows[0]["sqlx"];
        showDialog("����ѧ�罨�����",720,520,url);
    } else{
        showDialog("����ѧ�罨���������",500,300,"sxzzjy_grxfjssh.do?method=grxfjsPlsh");
    }
}

/**
 * ������˱���
 * @param shzt
 * @return
 */
function savePlsh(shzt,shyj){

    var rows = jQuery("#dataTable").getSeletRow();
    var guid = new Array();
    var bjdms = new Array();
    var splc = new Array();
    var gwids = new Array();
    var sqlxs = new Array();

    jQuery.each(rows,function(i,row){
        guid.push(row["sqid"]);
        bjdms.push(row["bjdm"]);
        splc.push(row["splc"]);
        gwids.push(row["gwid"]);
        sqlxs.push(row["sqlx"]);
    });

    jQuery.post(
        "sxzzjy_grxfjssh.do?method=grxfjsPlsh&type=save",
        {
            shzt:shzt,
            id:guid,
            bjdms:bjdms,
            shyj:shyj,
            gwids:gwids,
            splcs:splc,
            sqlxs:sqlxs
        },function(data){

            showAlertDivLayer(data["message"],{},{"clkFun":function(){
                jQuery("#dataTable").reloadGrid();
            }});
        },
        'json'
    );
}

function grxfjsshLcinfo(){
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length != 1){
        showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
    } else {
        showDialog("���˰�罨���������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
    }
}

function cancelShnew(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1){
        showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
    } else {
        var splc = rows[0]["splc"];
        var shid = rows[0]["shid"];
        var sqid = rows[0]["sqid"];
        var sqlx = rows[0]["sqlx"];
        showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
            jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
                // �ж��Ƿ����һ������(1:���һ�������ɹ���
                if("1" == data["cancelFlg"]){
                    jQuery.post("sxzzjy_grxfjssh.do?method=cancelgrxfjssh",{sqid:sqid,sqlx:sqlx},function(result){
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

function viewsh(sqid,sqlx,jgid,xh ){

    var url = "sxzzjy_grxfjssq.do?method=grxfjssqView&sqid=" + sqid;
    if("sq" != sqlx)
        url = "sxzzjy_grxfjshb.do?method=grxfjshbView&jgid=" + jgid+"&hblx="+sqlx+"&xh="+xh;
    showDialog("�鿴", 720,550, url);

}

function xhLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick=\"viewsh(" +
        "'"+rowObject["sqid"]+"','"+rowObject["sqlx"]+"','"+rowObject["jgid"]+"','"+rowObject["xh"]+"')\">" + cellValue
        + "</a>";
}



var DCCLBH = "rcsw_dxsylbx_grxfjssh.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
    //DCCLBH�������ܱ��,ִ�е������� 
    customExport(DCCLBH, grxfjsshExportData);
}

// ��������
function grxfjsshExportData() {
    var shlx = jQuery("#shzt").val();
    setSearchTj();//���ø߼���ѯ����
    var url = "sxzzjy_grxfjssh.do?method=exportData&dcclbh=" + DCCLBH + "&shlx=" + shlx;//dcclbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}