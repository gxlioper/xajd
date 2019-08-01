//ѧ�Ѽ��������б�
jQuery(function(){
    initGridSetting();
});

function initGridSetting(){
    var gridSetting = {
        caption:"ѧ�Ѽ������б�",
        pager:"pager",
        multiselect:true,
        radioselect:true,
        url:"xszz_new_xfjm.do?method=getJgPageList&doType=query",
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
            {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
            {label:'���״̬',name:'shztmc', index: 'shztmc',width:'6%'}
        ],
        sortname: "sqsj",
        sortorder: "desc"
    };

    gridSetting["params"]=getSuperSearch();
    jQuery("#dataTable").initGrid(gridSetting);
}

function xhLink(value,row){
    return "<a href='javascript:void(0);' class='name' onclick='xfjmView(\""
        + row["id"] + "\",\"" + value + "\");'>" + value
        + "</a>";
}

function xfjmView(id,xh){
    showDialog("ѧ�Ѽ�����Ϣ�鿴",  720, 520, "xszz_new_xfjm.do?method=xfjmSq&status=view&id=" + id
        + "&xh=" + xh);
}
function searchRs(){
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}
//��תѧ�Ѽ�������ҳ��
function xfjmzj(){
    showDialog("ѧ�Ѽ�����Ϣ����",  720, 560, "xszz_new_xfjm.do?method=xfjmJgEdit&status=add");
}
//��תѧ�Ѽ����޸�ҳ��
function xfjmxg(){
    var row = getSelectRow();
    if(!row){
        return false;
    }
    showDialog("ѧ�Ѽ�����Ϣ�޸�",  720, 520, "xszz_new_xfjm.do?method=xfjmJgEdit&status=update&id="+row.id);
}
//ɾ��ѧ�Ѽ�������Ϣ
function xfjmsc(){
    var row = getSelectRow();
    if(!row){
        return false;
    }
    var url = "xszz_new_xfjm.do?&method=removeJg&id="+row.id;
    showConfirmDivLayer("��ȷ��ɾ���ü�¼��?",{"okFun":function(){
            jQuery.post(url,function(result){
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
        }});
}
//�ύ��������
function xfjmtj(){
    var row = getSelectRow();
    if(!row){
        return false;
    }
    if(row.shzt != '0'){
        showAlertDivLayer("ֻ�ܶ�δ�ύ�ļ�¼���д˲�����");
        return false;
    }
    var url = "xszz_new_xfjm.do?&method=sqztxg&id="+row.id+"&shzt=5";
    showConfirmDivLayer("��ȷ��Ҫ�ύ��������?",{"okFun":function(){
            jQuery.post(url,function(result){
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
        }});
}

//������������
function xfjmcx(){
    var row = getSelectRow();
    if(!row){
        return false;
    }
    if(row.shzt != '5' && row.shzt != '3'){
        showAlertDivLayer("ֻ�ܳ���δ����˺��˻صļ�¼��");
        return false;
    }
    var url = "xszz_new_xfjm.do?&method=sqztxg&id="+row.id+"&shzt=3";
    showConfirmDivLayer("��ȷ��������������?",{"okFun":function(){
            jQuery.post(url,function(result){
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
        }});
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

//����ѧ�Ѽ���ѧ����Ϣ
function xfjmdr(){
    var url="xszz_new_xfjm.do?method=importXfjmjg";
    showDialog("ѧ�Ѽ���������", 600, 340, url);
}
//����ѧ�Ѽ�����Ϣ
function xfjmdc(){
    var DCCLBH='xszz_xfjm_jg.do';
    customExport(DCCLBH, exportData);
}

function exportData(){
    var DCCLBH='xszz_xfjm_jg.do';
    setSearchTj();//���ø߼���ѯ����
    var url = "xszz_new_xfjm.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}

