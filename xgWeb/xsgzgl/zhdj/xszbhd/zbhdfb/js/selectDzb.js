var  gridSetting,gridSetting2;

jQuery(function () {
    var a = jQuery("#hdid").val();
    gridSetting = {
        caption: "��֧���б�",
        pager: "pager",
        url: "xszbhd_hdfb.do?method=selectDzb&type=query&flag=wxz&hdid="+jQuery("#hdid").val(),
        colList: [
            {label: 'dzbid', name: 'dzbid', index: 'dzbid',hidden:true,key:true},
            {label: '��֧������', name: 'dzbmc', index: 'dzbmc', width: '10%'},
            {label: '�������㵳ί����', name: 'jcdwmc', index: 'jcdwdm', width: '10%'},
            {label: '����ʱ��', name: 'clsj', index: 'clsj', width: '4%'},
            {label: '����ѧԺ', name: 'xymc', index: 'xymc', width: '10%'}
        ],
        sortname: "clsj",
        sortorder: "asc"
    };
    gridSetting2 = {
        caption: "��֧���б�",
        pager: "pager",
        url: "xszbhd_hdfb.do?method=selectDzb&type=query&flag=yxz&hdid="+jQuery("#hdid").val(),
        colList: [
            {label: 'dzbid', name: 'dzbid', index: 'dzbid',hidden:true,key:true},
            {label: '��֧������', name: 'dzbmc', index: 'dzbmc', width: '10%'},
            {label: '�������㵳ί����', name: 'jcdwmc', index: 'jcdwdm', width: '10%'},
            {label: '����ʱ��', name: 'clsj', index: 'clsj', width: '4%'},
            {label: '����ѧԺ', name: 'xymc', index: 'xymc', width: '10%'}
        ],
        sortname: "clsj",
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
    if(lx =='yxz'){
        jQuery("#buttonSave").hide();
        jQuery("#buttonDel").show();
        jQuery("#dataTable").initGrid(gridSetting2);
    }else{
        jQuery("#buttonSave").show();
        jQuery("#buttonDel").hide();
        jQuery("#dataTable").initGrid(gridSetting);
    }
    searchRs();
}

function edit(type){
    var ids = jQuery("#dataTable").getSeletIds();
    var hdid = jQuery("#hdid").val();
    if (ids.length == 0) {
        showAlertDivLayer("��ѡ����Ҫѡ��ļ�¼��");
    }else{
        jQuery.post("xszbhd_hdfb.do?method=editDzb", {type:type,hdid:hdid,values: ids.toString()}, function (data) {
            showAlertDivLayer(data["message"]);
            if(type == 'save')
                jQuery("#dataTable").initGrid(gridSetting);
            if(type == 'del')
                jQuery("#dataTable").initGrid(gridSetting2);
            searchRs();
        }, 'json');
    }

}