//����֯���Ա����
var joinStatus;
var hdid;
jQuery(function(){
    joinStatus = jQuery("#joinStatus").val();
    hdid = jQuery("#hdid").val();
    //Ĭ����ʾ�ɼ����
    if(joinStatus == null){
        joinStatus == '0'
    }
    selectTab(joinStatus);

});


function gridSetting0(){
    var gridSetting = {
        caption: "�����ѧ���б�",
        pager: "pager",
        url: "zhdj_dzzhd.do?method=getHdryList&doType=query&joinStatus="+joinStatus+"&hdid="+hdid,
        colList: [
            {label: 'ѧ��', name: 'xh', index: 'xh', width: '15%',formatter:xhLink},
            {label: '����', name: 'xm', index: 'xm', width: '15%'},
            {label: '�Ա�', name: 'xbmc', index: 'xbmc', width: '10%'},
            {label: 'ѧԺ', name: 'xymc', index: 'xymc', width: '20%'},
            {label: 'רҵ', name: 'zymc', index: 'zymc', width: '20%'},
            {label: '�༶', name: 'bjmc', index: 'bjmc', width: '20%'},
            {label:'ѧԺ����',name:'xydm',index:'xydm',hidden:true},
            {label:'רҵ����',name:'zydm',index:'zydm',hidden:true},
            {label:'�༶����',name:'bjdm',index:'bjdm',hidden:true}
        ]
    };
    return gridSetting;
}

function gridSetting1(){
    var gridSetting = {
        caption: "�����ѧ����Ϣ",
        pager: "pager",
        url: "zhdj_dzzhd.do?method=getHdryList&doType=query&joinStatus="+joinStatus+"&hdid="+hdid,
        colList: [
            {label: 'ѧ��', name: 'xh', index: 'xh', width: '10%',formatter:xhLink},
            {label: '����', name: 'xm', index: 'xm', width: '15%'},
            {label: '�Ա�', name: 'xbmc', index: 'xbmc', width: '10%'},
            {label: 'ѧԺ', name: 'xymc', index: 'xymc', width: '20%'},
            {label: 'רҵ', name: 'zymc', index: 'zymc', width: '15%'},
            {label: '�༶', name: 'bjmc', index: 'bjmc', width: '20%'},
            {label: '��ĵ�', name: 'hdxdtx', index: 'hdxdtx', width: '10%',formatter:hdxdLink},
            {label:'�id',name:'hdid',index:'hdid',hidden:true}
        ]
    };
    return gridSetting;
}
function xhLink(value,row) {
    return '<a href="javascript:void(0);" class="name" onclick="zxsxxView(\''+value+'\');return false;">'+value+'</a>'
}
function zxsxxView(xh) {
    showDialog("ѧ����Ϣ��ѯ", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh
        + "&xs");
}
function hdxdLink(value,row){
    if('���ύ'== value){
        return "<a class='name' href='#' onclick='showHdxdInfo(\""+row.hdid+"\",\""+row.xh+"\");'>"+value+"</a>";
    }else{
        return value;
    }

}

function showHdxdInfo(id,xh){
    showDialog("��ĵ�",  620, 420, "zhdj_dzzhd.do?method=hdXdInfo&status=view&hdid="+id+"&xh="+xh);
}

//������ �Ѽ���Ⱥ��ѡ��
function selectTab(val){
    joinStatus = val;
    jQuery("#joinStatus").val(val);
    if(val =='0'){//�ɼ���
        jQuery("#li_sc").hide();
        jQuery("#li_sz").show();
        jQuery("#li_xd").hide();
        jQuery("#kjr").prop("class","ha");
        jQuery("#yjr").removeClass("ha");
        gridSetting0["params"]=getSuperSearch();
        jQuery("#dataTable").initGrid(gridSetting0());
    }else{//�Ѽ���
        jQuery("#li_sc").show();
        jQuery("#li_xd").show();
        jQuery("#li_sz").hide();
        jQuery("#kjr").removeClass("ha");
        jQuery("#yjr").prop("class","ha");
        gridSetting1["params"]=getSuperSearch();
        jQuery("#dataTable").initGrid(gridSetting1());
    }
}

function searchRs(){
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}
function reload(){
    searchRs();
}

//����
function addRy() {
    var rows = jQuery("#dataTable").getSeletRow();
    if(rows == null || rows.length ==0){
        showAlertDivLayer("������ѡ��һ����¼��");
        return false;
    }
    var xhs = "";
    jQuery.each(rows,function (index,item) {
        xhs += item.xh;
        if(index != rows.length-1){
            xhs+=',';
        }
    });
    var data = {
        xhs:xhs,
        hdid:hdid
    };
    var url = "zhdj_dzzhd.do?method=hdRySave";
    showConfirmDivLayer("��ȷ��Ҫ��ӹ�ѡ���û���?", {
        "okFun": function () {
            jQuery.post(url,data,function(result){
                if (result.code == 1) {
                    showAlert(result.msg,{},{"clkFun":function(){
                            reload();
                        }});
                } else {
                    //�洢ʧ��
                    showAlert(result.msg);
                    return false;
                }
            },'json');
        }
    });

}
//�Ƴ�
function removeRy() {
    var rows = jQuery("#dataTable").getSeletRow();
    if(rows == null || rows.length ==0){
        showAlertDivLayer("������ѡ��һ����¼��");
        return false;
    }
    var xhs = "";
    jQuery.each(rows,function (index,item) {
        xhs += item.xh;
        if(index != rows.length-1){
            xhs+=',';
        }
    });
    var data = {
        xhs:xhs,
        hdid:hdid
    };
    var url = "zhdj_dzzhd.do?method=hdRyRemove";
    showConfirmDivLayer("��ȷ��Ҫ�Ƴ�����ѡ���û���?", {
        "okFun": function () {
            jQuery.post(url, data, function (result) {
                if (result.code == 1) {
                    showAlert(result.msg, {}, {
                        "clkFun": function () {
                            reload();
                        }
                    });
                } else {
                    //�洢ʧ��
                    showAlert(result.msg);
                    return false;
                }
            }, 'json');
        }
    });
}
//�鿴��ĵ����
function viewHdxd(){
    var rows = jQuery("#dataTable").getSeletRow();
    if(rows == null || rows.length !=1){
        showAlertDivLayer("��ѡ��һ����¼��");
        return false;
    }
    showHdxdInfo(rows[0].hdid,rows[0].xh)

}
