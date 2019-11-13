//ѧ�Ѽ��������б�
var status;
jQuery(function(){
    initGridSetting();
});

function initGridSetting(){
    status  = jQuery("#status").val();
    if(status == null || status.length == 0){
        status ='dsh';
    }
    gridSetting["params"]=getSuperSearch();
    jQuery("#dataTable").initGrid(gridSetting(status));
}

function gridSetting(type){
    return {
        caption:"ѧ�Ѽ�������б�",
        pager:"pager",
        url:"xszz_new_xfjm.do?method=getShPageList&doType=query&status="+type,
        colList:[
            {label:'key',name:'id', index: 'id',key:true ,hidden:true},
            {label:'ѧ��',name:'xh', index: 'xh',width:'11%',formatter:xhLink},
            {label:'����',name:'xm', index: 'xm',width:'8%'},
            {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
            {label:'ѧԺ',name:'xymc', index: 'xydm',width:'13%'},
            {label:'shlc',name:'shlc', index: 'shlc',hidden:true},
            {label:'��Ժ',name:'symc', index: 'symc',width:'13%'},
            {label:'�����༶',name:'bjmc', index: 'bjdm',width:'13%'},
            {label:'רҵ�༶',name:'zybjmc', index: 'bjdm',width:'13%'},
            {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
            {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'9%'},
            {label:'�������ѳ̶�',name:'dcmc', index: 'dcmc',width:'8%'},
            {label:'���״̬',name:'shzt', index: 'shzt',hidden:true},
            {label:'�������״̬',name:'shlzt', index: 'shlzt',hidden:true},
            {label:'���id',name:'shid', index: 'shid',hidden:true},
            {label:'��λid',name:'gwid', index: 'gwid',hidden:true},
            {label:'shzt',name:'shlgwmc', index: 'shlgwmc',hidden:true},
            {label:'���״̬',name:'shztmc', index: 'shztmc',width:'6%',formatter:shztLink}
        ],
        sortname: "sqsj",
        sortorder: "desc"
    };
}

function selectTab(obj,type){
    status = type;
    if(status=='ysh'){
        jQuery("#xfjmsh").hide();
        jQuery("#xfjmcx").show();
    }else{
        jQuery("#xfjmsh").show();
        jQuery("#xfjmcx").hide();
    }
    jQuery(".ha").removeClass("ha");
    jQuery(obj).parent().addClass("ha");
    gridSetting["params"]=getSuperSearch();
    jQuery("#dataTable").initGrid(gridSetting(status));
}

function xhLink(value,row){
    return "<a href='javascript:void(0);' class='name' onclick='xfjmView(\""
        + row["id"] + "\",\"" + value + "\");'>" + value
        + "</a>";
}

function shztLink(value,row){
    return row.shlgwmc+"["+value+"]";
}


function xfjmView(id,xh){
    showDialog("ѧ�Ѽ�����Ϣ�鿴",  720, 520, "xszz_new_xfjm.do?method=xfjmSq&status=view&id=" + id
        + "&xh=" + xh);
}

function xfjmsh(){
    var row = getSelectRow();
    if(!row){
        return false;
    }
    showDialog("ѧ�Ѽ�����Ϣ���",  720, 520, "xszz_new_xfjm.do?method=xfjmSh&status=dsh&id=" + row.id
        + "&xh=" + row.xh+"&shlc="+row.shlc+"&shid="+row.shid+"&xtgwid="+row.gwid);
}
//�������
function cancelSh(){
    var rows = jQuery("#dataTable").getSeletRow();

    if (rows.length != 1){
        showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
    } else {

        //���һ��������˺���õ�·��
        var cancelPath = "xszz_new_xfjm.do?method=qxsh&status=dsh&id="+rows[0]["id"];
        var msg="<div><ul>";
        var callBackMsg="";
        confirmInfo("��ȷ��Ҫ����������?",function(ty){
            if(ty=="ok"){
                jQuery.post("comm_spl.do?method=cxshnew",{shlc:rows[0]["shlc"],shid:rows[0]["shid"]},function(data){
                    // �ж��Ƿ����һ������(1:���һ�������ɹ���
                    if("1" == data["cancelFlg"]){
                        jQuery.post(cancelPath,{id:rows[0]["id"]},function(result){
                            callBackMsg=result["message"];
                        },'json');
                    }else{
                        callBackMsg=data["message"];
                    }
                    msg+="<li><span style='width:40%;display:inline-block'>"+rows[0]["xh"]+"</span><font class='have'>"+callBackMsg+"</font></li>";
                    msg+="</ul></div>";
                    showAlertDivLayer(msg,{},{"clkFun":function(){
                        jQuery("#dataTable").reloadGrid();
                    }});
                },'json');
            }
        });

    }
}

function searchRs(){
    var map = getSuperSearch();
    map.status = status;
    jQuery("#dataTable").reloadGrid(map);
}
//������̸���
function shlcck(){
    var row = getSelectRow();
    if(!row){
        return false;
    }
    if(row.shzt=="0"){
        showAlertDivLayer("δ�ύ����������������!");
        return false;
    }
    showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+row.id+"&splc="+row.shlc);

}

function getSelectRow(){
    var rows = jQuery("#dataTable").getSeletRow();
    if(rows == null || rows.length != 1){
        showAlertDivLayer("��ѡ��һ����¼��");
        return false;
    }
    return rows[0];
}

function reload(){
    searchRs();
}

