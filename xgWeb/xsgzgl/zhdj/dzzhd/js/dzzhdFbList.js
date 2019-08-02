//����֯�ճ������
jQuery(function(){
    initGridSetting();
});

function initGridSetting(){
    var gridSetting = {
        caption:"�ҵĻ�б�",
        pager:"pager",
        url:"zhdj_dzzhd.do?method=getHdPageList&doType=query",
        colList:[
            {label:'key',name:'id', index: 'id',key:true ,hidden:true},
            {label:'�����',name:'hdmc', index: 'hdmc',width:'11%',formatter:hdLink},
            {label:'��ʼʱ��',name:'kssj', index: 'kssj',width:'8%'},
            {label:'����ʱ��',name:'jssj', index: 'jssj',width:'8%'},
            {label:'�������',name:'mxdxmc', index: 'mxdxmc',width:'13%'},
            {label:'����������',name:'mxdx', index: 'mxdx',hidden:true},
            {label:'������',name:'cjrxm', index: 'cjrxm',width:'13%'},
            {label:'����ʱ��',name:'cjsj', index: 'cjsj',width:'13%'}
        ],
        radioselect:true,
        sortname: "cjsj",
        sortorder: "desc"
    };

    gridSetting["params"]=getSuperSearch();
    jQuery("#dataTable").initGrid(gridSetting);
}

function hdLink(value,row){
    return "<a href='javascript:void(0);' class='name' onclick='dzzhdView(\""
        + row["id"] + "\");'>" + value + "</a>";
}

//���ϸ
function dzzhdView(id){
    showDialog("���Ϣ�鿴",  720, 520, "zhdj_dzzhd.do?method=hdInfo&status=view&id=" + id);
}
function searchRs(){
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}
//��ת���Ϣ���ҳ��
function dzzhdAdd(){
    showDialog("���Ϣ����",  720, 560, "zhdj_dzzhd.do?method=hdInfo&status=add");
}
//��ת�޸�ҳ��
function dzzhdXg(){
    var row = getSelectRow();
    if(!row){
        return false;
    }
    showDialog("���Ϣ�޸�",  720, 520, "zhdj_dzzhd.do?method=hdInfo&status=update&id="+row.id);
}
//ɾ�����Ϣ
function dzzhdSc(){
    var row = getSelectRow();
    if(!row){
        return false;
    }
    var url = "zhdj_dzzhd.do?&method=removeById&id="+row.id;
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

