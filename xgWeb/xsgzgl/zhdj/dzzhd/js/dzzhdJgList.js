//����֯�ճ������
jQuery(function(){
    initGridSetting();
});

function initGridSetting(){
    var userType = jQuery("#userType").val();
    var hdxdtxt = "��ĵ�";
    if("stu" != userType){
        hdxdtxt += "<br/>(���ύ/δ�ύ)"
    }
    var gridSetting = {
        caption:"�ҵĻ�б�",
        pager:"pager",
        url:"zhdj_dzzhd.do?method=getHdPageList&doType=query",
        colList:[
            {label:'key',name:'id', index: 'id',key:true ,hidden:true},
            {label:'�����',name:'hdmc', index: 'hdmc',width:'11%',formatter:hdLink},
            {label:'��ʼʱ��',name:'kssj', index: 'kssj',width:'10%'},
            {label:'����ʱ��',name:'jssj', index: 'jssj',width:'10%'},
            {label:'�������',name:'mxdxmc', index: 'mxdxmc',width:'13%'},
            {label:'����������',name:'mxdx', index: 'mxdx',hidden:true},
            {label:'������',name:'cjrxm', index: 'cjrxm',width:'10%'},
            {label:'����ʱ��',name:'cjsj', index: 'cjsj',width:'13%'},
            {label:hdxdtxt,name:'xdtj', index: 'xdtj',width:'10%',formatter:hdxdLink},
            {label:'ѧ���ĵ�����Ƿ��ύ',name:'xdtj',index:'xdtj',hidden:true},
            {label:'���ύ��',name:'ytj',index:'ytj',hidden:true},
            {label:'δ�ύ��',name:'wtj',index:'wtj',hidden:true},
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

function hdxdLink(value,row){
    var userType = jQuery("#userType").val();
    if("stu" == userType){
        var xdtj = row.xdtj;
        var isTj = "δ�ύ";
        if(xdtj != null && "1" == xdtj){
            isTj = '���ύ';
        }
        return "<a class='name' href='#' onclick='showHdxdInfo(\""+row.id+"\");'>"+isTj+"</a>";
    }else{
        return '<a class="name" href="zhdj_dzzhd.do?method=getHdryList&joinStatus=1&hdid='+row.id+'">'+row.ytj+'/'+row.wtj+'</a>';
    }
}
function showHdxdInfo(id){
    showDialog("��ĵ�",  620, 420, "zhdj_dzzhd.do?method=hdXdInfo&status=add&hdid="+id);
}

//���ϸ
function dzzhdView(id){
    showDialog("���Ϣ�鿴",  720, 520, "zhdj_dzzhd.do?method=hdInfo&status=view&id=" + id);
}
function searchRs(){
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}
//���Ա����
function hdRygl(){
    var data= getSelectRow();
    if(!data){
        return;
    }
    document.location.href = "zhdj_dzzhd.do?method=getHdryList&joinStatus=0&hdid="+data.id;
}

//��ĵ����
function hdXd(){
    var data= getSelectRow();
    if(!data){
        return;
    }
    showDialog("��ĵ����",  620, 420, "zhdj_dzzhd.do?method=hdXdInfo&status=add&hdid="+data.id);
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

