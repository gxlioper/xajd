var  gridSetting,gridSetting2;
jQuery(function (){
      gridSetting = {
        caption: "�����ϱ���б�",
        pager: "pager",
        url: "xszbhd_hdsb.do?method=getSbList&type=query&flag=zxsb",
        colList: [
            {label: 'hdsbid', name: 'hdsbid', index: 'hdsbid',hidden:true,key:true},
            {label: 'dzbid', name: 'dzbid', index: 'dzbid',hidden:true},
            {label: 'ѧ��', name: 'xn', index: 'xn', width: '10%'},
            {label: 'ѧ��', name: 'xqmc', index: 'xqmc', width: '10%'},
            {label: '����һ��/���ջ', name: 'shykdrhdmc', index: 'shykdrhdmc', width: '10%'},
            {label: '�����', name: 'hdlxmc', index: 'hdlxmc', width: '10%'},
            {label: '����', name: 'hdzt', index: 'hdzt', width: '15%'},
            {label: '�ϱ�ʱ��', name: 'sbsj', index: 'sbsj', width: '10%'}
        ],
        sortname: "sbsj",
        sortorder: "asc"
    };
     gridSetting2 = {
        caption: "��ʱ�ϱ���б�",
        pager: "pager",
        url: "xszbhd_hdsb.do?method=getSbList&type=query&flag=xssb",
        colList: [
            {label: 'hdsbid', name: 'hdsbid', index: 'hdsbid',hidden:true,key:true},
            {label: 'dzbid', name: 'dzbid', index: 'dzbid',hidden:true},
            {label: 'hdid', name: 'hdid', index: 'hdid',hidden:true},
            {label: 'ѧ��', name: 'xn', index: 'xn', width: '7%'},
            {label: 'ѧ��', name: 'xqmc', index: 'xqmc', width: '6%'},
            {label: '����һ��/���ջ', name: 'shykdrhdmc', index: 'shykdrhdmc', width: '10%'},
            {label: '�����', name: 'hdlxmc', index: 'hdlxmc', width: '10%'},
            {label: '����', name: 'hdzt', index: 'hdzt', width: '12%'},
            {label: '��ʼʱ��', name: 'kssj', index: 'kssj', width: '7%'},
            {label: '��ֹʱ��', name: 'jzsj', index: 'jzsj', width: '7%'},
            //{label: '����', width: '5%',formatter:detailLink},
            {label: '�ϱ�ʱ��', name: 'sbsj', index: 'sbsj', width: '7%'},
            {label: 'zt', name: 'zt', index: 'zt', width: '10%',hidden:true},
            {label: '״̬', name: 'ztmc', index: 'ztmc', width: '5%'}
        ],
        sortname: "sbsj",
        sortorder: "asc"
    };
    jQuery("#dataTable").initGrid(gridSetting);
});
function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}
function query(obj,lx){
    jQuery("#comp_title li").removeClass();
    jQuery(obj).parent().attr("class","ha");
    // jQuery("#shlx").val(shlx);
    if(lx =='xssb'){
        jQuery("#type").val("xssb");
        jQuery("#dataTable").initGrid(gridSetting2);
    }else{
        jQuery("#type").val("zxsb");
        jQuery("#dataTable").initGrid(gridSetting);
    }
    searchRs();
}

function add() {
    var type = jQuery("#type").val();
    var url = "xszbhd_hdsb.do?method=add&flag=add&type="+type;
    if(type == "xssb"){
        var rows = jQuery("#dataTable").getSeletRow();
        if (rows.length != 1) {
            showAlertDivLayer("��ѡ��һ����Ҫ�ϱ��ļ�¼��");
            return;
        }
        url = "xszbhd_hdsb.do?method=add&flag=add&type="+type+"&hdid="+rows[0]["hdid"];
    }
    var title = "��ϱ�";
    showDialog(title, 600, 550, url);
}
function update() {
    var type = jQuery("#type").val();
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
        return;
    }
    if(type == "xssb" && isNull(rows[0]["hdsbid"])){
        showAlertDivLayer("��ѡ��һ�����ϱ��ļ�¼��");
        return;
    }
    if(type == "xssb"){
        var kssj = rows[0]["kssj"].replace(/-/g,"/");//�滻�ַ�����ɱ�׼��ʽ
        var jzsj = rows[0]["jzsj"].replace(/-/g,"/");//�滻�ַ�����ɱ�׼��ʽ
        var now=new Date();//ȡ���������
        //var now = new Date(Date.parse("2016/06/01 10:00:00"));
        var dkssj = new Date(Date.parse(kssj));
        var djzsj = new Date(Date.parse(jzsj));
        if(now<dkssj || now>djzsj){
            showAlertDivLayer("ֻ���ڻʱ�����޸ģ�");
            return;
        }
    }
    var title = "��ϱ��޸�";
    var url = "xszbhd_hdsb.do?method=update&flag=update&type="+type+"&hdsbid="+rows[0]["hdsbid"]+"&hdid="+rows[0]["hdid"];
    showDialog(title, 600, 550, url);
}
function del() {
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length == 0) {
        showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
    } else {
        showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
            "okFun": function () {
                 jQuery.post("xszbhd_hdsb.do?method=del", {values: ids.toString(),type:jQuery("#type").val()}, function (data) {
                     showAlertDivLayer(data["message"]);
                     jQuery("#dataTable").reloadGrid();
                 }, 'json');
            }
        });

    }
}
function view() {
    var type = jQuery("#type").val();
    var rows = jQuery("#dataTable").getSeletRow();

    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
        return;
    }
    var title = "�鿴";
    var url = "xszbhd_hdsb.do?method=view&type="+type+"&hdsbid="+rows[0]["hdsbid"];
    showDialog(title, 600, 420, url);
}


function isNull(value){
    if(value == "" || value == null){
        return true;
    } else{
        return false;
    }
}