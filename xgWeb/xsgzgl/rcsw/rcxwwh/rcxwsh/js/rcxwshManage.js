var xxdm;

var gridSetting = {
			caption:"�ճ���Ϊ���",
		pager:"pager",
		url:"rcsw_rcxwwh_rcxwshgl.do?method=rcxwshManage&type=query",
		colList:[
		   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
		   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
		   {label:'����',name:'xm', index: 'xm',width:'10%'},
		   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
		   {label:'�༶',name:'bjmc', index: 'bjdm',width:'12%'},
		   {label:'��Ϊ����',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'12%'},
		   {label:'��Ϊ���',name:'rcxwlbmc', index: 'rcxwlbmc',width:'12%'},
		   {label:'����ʱ��',name:'fssj', index: 'fssj',width:'12%'},
		   {label:'����������ֵ',name:'fz', index: 'fz',width:'8%'},
		   {label:'��������',name:'gfly', index: 'gfly',width:'8%',formatter:gflyText},
		   {label:'���״̬����',name:'shzt', index: 'shzt' ,hidden:true},
		   {label:'���״̬',name:'shztmc', index: 'shztmc',width:'14%'},
		   {label:'��Ϊ�������',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
		   {label:'��������',name:'splc', index: 'splc',hidden:true},
		   {label:'���Id',name:'shid', index: 'shid',hidden:true}
		],
		params:{shzt:"dsh"},//Ĭ�ϴ����
		sortname: "rcxwjlsj",
	 	sortorder: "desc"
	}
var gridSetting2 = {
		caption:"�ճ���Ϊ���",
		pager:"pager",
		url:"rcsw_rcxwwh_rcxwshgl.do?method=rcxwshManage&type=query",
		colList:[
		   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
		   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
		   {label:'����',name:'xm', index: 'xm',width:'10%'},
		   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
		   {label:'�༶',name:'bjmc', index: 'bjdm',width:'12%'},
		   {label:'��Ϊ����',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'12%'},
		   {label:'��Ϊ���',name:'rcxwlbmc', index: 'rcxwlbmc',width:'12%'},
		   {label:'����ʱ��',name:'fssj', index: 'fssj',width:'12%'},
		   {label:'����������ֵ',name:'fz', index: 'fz',width:'8%'},
		   {label:'��������',name:'gfly', index: 'gfly',width:'8%',formatter:gflyText},
		   {label:'���״̬����',name:'shzt', index: 'shzt' ,hidden:true},
		   {label:'���״̬',name:'shztmc', index: 'shztmc',width:'14%'},
		   {label:'��λid',name:'gwid', index: 'gwid',hidden:true},
		   {label:'�����',name:'shr', index: 'shr',hidden:true},
		   {label:'��������',name:'splc', index: 'splc',hidden:true},
		   {label:'���Id',name:'shid', index: 'shid',hidden:true}
		],
		params:{shzt:"ysh"},//Ĭ�ϴ����
		sortname: "rcxwjlsj",
	 	sortorder: "desc"
	}
var gridSetting1 = {
    caption:"�ӷ��������",
    pager:"pager",
    url:"rcsw_rcxwwh_rcxwshgl.do?method=rcxwshManage&type=query",
    colList:[
        {label:'key',name:'id', index: 'id',key:true ,hidden:true},
        {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
        {label:'����',name:'xm', index: 'xm',width:'10%'},
        {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
        {label:'�༶',name:'bjmc', index: 'bjdm',width:'12%'},
        {label:'�ӷִ���',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'12%'},
        {label:'�ӷ����',name:'rcxwlbmc', index: 'rcxwlbmc',width:'12%'},
        {label:'����ʱ��',name:'fssj', index: 'fssj',width:'12%'},
        {label:'����������ֵ',name:'fz', index: 'fz',width:'8%'},
        {label:'��������',name:'gfly', index: 'gfly',width:'8%',formatter:gflyText},
        {label:'���״̬����',name:'shzt', index: 'shzt' ,hidden:true},
        {label:'���״̬',name:'shztmc', index: 'shztmc',width:'14%'},
        {label:'�ӷִ������',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
        {label:'��������',name:'splc', index: 'splc',hidden:true},
        {label:'���Id',name:'shid', index: 'shid',hidden:true}
    ],
    params:{shzt:"dsh"},//Ĭ�ϴ����
    sortname: "rcxwjlsj",
    sortorder: "desc"
}
jQuery(function(){
	xxdm = jQuery("#xxdm").val();
	if("13431" == xxdm){
        jQuery("#dataTable").initGrid(gridSetting1);
	}else{
        jQuery("#dataTable").initGrid(gridSetting);
	}
});
	
function gflyText(cellValue, rowObject){
	var cellValueNotNull = cellValue ? cellValue : "";
	var cellValueNew = cellValueNotNull.length > 10 ? cellValue.substring(0,10)+"..." : cellValueNotNull;
	return "<span title='"+cellValueNotNull+"' >" + cellValueNew + "</span>";
}


function searchRs(){
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	if (shzt != ""){
		map["shzt"] = shzt;
	}
	jQuery("#dataTable").reloadGrid(map);
}
	
	
	
	////////////////////////////////////
/**
 * �ճ���Ϊ���--�������Ѵ�����ǩ�л�
 * @param obj
 * @param shzt
 * @return
 */
function selectTab(obj,shzt){
	jQuery("#shzt").val(shzt);

	if (shzt == "dsh"){
		jQuery("#li_sh").css("display","");
		jQuery("#li_plsh").css("display","");
		jQuery("#li_qx").css("display","none");
		
		jQuery("#dataTable").initGrid(gridSetting);
	} else {			
		jQuery("#li_sh").css("display","none");
		jQuery("#li_plsh").css("display","none");
		jQuery("#li_qx").css("display","");
		
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	
	searchRs();
}

/**
 * �ճ���Ϊ���
 * @return
 */
function rcxwSh(){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = jQuery("#shzt").val();
	if(shzt=="ysh"){
		showAlertDivLayer("�Ѵ����¼�����ٴ����");
		return false;
	}
	else if (rows.length == 0){
		showAlertDivLayer("��ѡ��һ����Ҫ��˵ļ�¼��");
	}else if(rows.length == 1 ){
		var url = "rcsw_rcxwwh_rcxwshgl.do?method=rcxwDgsh&id="+rows[0]["id"]+'&xh=' + rows[0]["xh"]+'&rcxwlbdldm=' + rows[0]["rcxwlbdldm"] + '&shid=' +rows[0]["shid"] ;
		var title = "�ճ���Ϊ���";
		if(xxdm=="13815"){
			title = "����ѧ�����";
		}
        if(xxdm=="13431"){
            title = "�ӷ��������";
        }
		showDialog(title,700,500,url);
	} else {
		plsh();
	}
}


function rcxwshLcinfo(){
	
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {		
		showDialog("�������̸���",600,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['id']+"&splc="+rows[0]['splc']);
	}
}

function cancelSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
	} else {
		
		showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
			jQuery.post("rcsw_rcxwwh_rcxwshgl.do?method=cancelRcxwsh",
				{
				 id:rows[0]["id"],
				 gwid:rows[0]["gwid"],
				 shr:rows[0]["shr"],
				 splc:rows[0]["splc"]
				},
				function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},
			'json');
		}});
	}
}

function xwxxView(id, xh) {
	var title = "�ճ���Ϊ�����Ϣ�鿴";
	if(xxdm=="13815"){
		title = "����ѧ�������Ϣ�鿴";
	}
    if(xxdm=="13815"){
        title = "�ӷ����������Ϣ�鿴";
    }
	showDialog(title, 700, 480, "rcsw_rcxwwh_rcxwshgl.do?method=viewXwsh&id=" + id
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='xwxxView(\""
			+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}
var DCCLBH = "rcsw_rcxwwh_rcxwshgl.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, rcxwxshExportData);
}

// ��������
function rcxwxshExportData() {
	var shlx = jQuery("#shzt").val();
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_rcxwwh_rcxwshgl.do?method=exportData&dcclbh=" + DCCLBH + "&shlx=" + shlx;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}