var xxdm;

var gridSetting = {
	caption:"�ճ���Ϊ��Ϣά���б�",
	pager:"pager",
	url:"rcsw_rcxwwh_rcxwxxwhgl.do?method=rcxwxxwhManage&type=query",
	colList:[
	   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
	   {label:'splc',name:'splc', index: 'splc',hidden:true},
	   {label:'rcxwlbdm',name:'rcxwlbdm', index: 'rcxwlbdm',hidden:true},
	   {label:'ѧ��',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
	   {label:'����',name:'xm', index: 'xm',width:'8%'},
	   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
	   {label:'�༶',name:'bjmc', index: 'bjdm',width:'13%'},
	   {label:'��Ϊ����',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'8%'},
	   {label:'��Ϊ���',name:'rcxwlbmc', index: 'rcxwlbmc',width:'8%'},
	   {label:'����ʱ��',name:'fssj', index: 'fssj',width:'15%'},
	   {label:'����������ֵ',name:'fz', index: 'fz',width:'12%'},
	   {label:'���״̬',name:'shztmc', index: 'shztmc',width:'6%'},
	   {label:'��Ϊ�������',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
	   {label:'shzt',name:'shzt', index: 'shzt',hidden:true}
	],
	sortname: "rcxwjlsj",
 	sortorder: "desc"
}
var gridSetting1 = {
    caption:"�ӷ�������Ϣά���б�",
    pager:"pager",
    url:"rcsw_rcxwwh_rcxwxxwhgl.do?method=rcxwxxwhManage&type=query",
    colList:[
        {label:'key',name:'id', index: 'id',key:true ,hidden:true},
        {label:'splc',name:'splc', index: 'splc',hidden:true},
        {label:'rcxwlbdm',name:'rcxwlbdm', index: 'rcxwlbdm',hidden:true},
        {label:'ѧ��',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
        {label:'����',name:'xm', index: 'xm',width:'8%'},
        {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
        {label:'�༶',name:'bjmc', index: 'bjdm',width:'13%'},
        {label:'�ӷִ���',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'8%'},
        {label:'�ӷ����',name:'rcxwlbmc', index: 'rcxwlbmc',width:'8%'},
        {label:'����ʱ��',name:'fssj', index: 'fssj',width:'15%'},
        {label:'����������ֵ',name:'fz', index: 'fz',width:'12%'},
        {label:'���״̬',name:'shztmc', index: 'shztmc',width:'6%'},
        {label:'�ӷִ������',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
        {label:'shzt',name:'shzt', index: 'shzt',hidden:true}
    ],
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
	if(xxdm == "10504"){
		var userStatus = jQuery("#userStatus").val();
		if(userStatus == "stu"){
			jQuery("#btn_zj").hide();
			jQuery("#btn_xg").hide();
			jQuery("#btn_sc").hide();
		}else{
			jQuery("#btn_shuc").hide();
			jQuery("#btn_sr").hide();
		}
	}
});

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function add(){
	var url = "rcsw_rcxwwh_rcxwxxwhgl.do?method=addXwxx";
	var title = "�����ճ���Ϊ��Ϣ";
	if(xxdm=="13815"){
		title = "��������ѧ����Ϣ";
	}
    if(xxdm=="13431"){
        title = "���Ӽӷ�������Ϣ";
    }
	showDialog(title,950,450,url);
}



function update() {
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var shzt = rows[0]["shzt"];
		if(shzt=='0'||shzt=='3'){
			var url = 'rcsw_rcxwwh_rcxwxxwhgl.do?method=updateXwxx&id=' + rows[0]["id"]
      		+ '&xh=' + rows[0]["xh"] +'&rcxwlbdldm=' + rows[0]["rcxwlbdldm"] 
      		+ '&rcxwlbdlmc=' + rows[0]["rcxwlbdlmc"] +'&rcxwlbdlmc=' + rows[0]["rcxwlbmc"];
      		var title = "�޸��ճ���Ϊ��Ϣ";
      		if(xxdm=="13815"){
      			title = "�޸�����ѧ����Ϣ";
      		}
            if(xxdm=="13815"){
                title = "�޸ļӷ�������Ϣ";
            }
      		showDialog(title, 850, 450, url);
		}else{
			showAlertDivLayer("ֻ���޸�'δ�ύ'��'�˻�'�ļ�¼��");
			return false;
		}
	}
}

function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var shzt = rows[0]["shzt"];
		
		if(shzt=='0'){
			showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
				jQuery.post("rcsw_rcxwwh_rcxwxxwhgl.do?method=delXwxx",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}});
		}else{
			showAlertDivLayer("ֻ��ɾ��'δ�ύ'�ļ�¼��");
			return false;
		}
	}
}


function xwxxView(id, xh) {
	var title = "�ճ���Ϊ��Ϣ�鿴";
	if(xxdm=="13815"){
			title = "����ѧ����Ϣ�鿴";
		}
    if(xxdm=="13431"){
        title = "�ӷ�������Ϣ�鿴";
    }
	showDialog(title, 720, 520, "rcsw_rcxwwh_rcxwxxwhgl.do?method=viewXwxx&id=" + id
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='xwxxView(\""
			+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

var DCCLBH = "rcsw_rcxwwh_rcxwxxwhgl.do";//dcclbh,�������ܱ��

/* ���� */
function importXX(){
	toImportDataNew("IMPORT_N155102_RCXWXXWH");
	return false;
}

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, rcxwxxwhExportData);
}

// ��������
function rcxwxxwhExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_rcxwwh_rcxwxxwhgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
function submitPl(){
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if (ids.length == 0){
				showAlertDivLayer("��ѡ����Ҫ�ύ�ļ�¼��");
				return false;
			}else {
				for(var i=0;i<rows.length;i++){
					if (rows[i]["shzt"] != "0"&&rows[i]["shzt"] != "3"){
						showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
						return false;
					}
				}
			showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
				var tips = submitLoading();
				try{ // ����������ӣ��ر����Ӵ��ڣ����ύʱ�ȴ����޷��ر�
					tips.show();
				}catch(e){
				}
				jQuery.post("rcsw_rcxwwh_rcxwxxwhgl.do?method=submitXwxx",{
					values:ids.toString()},function(data){
					tips.close();
					showAlertDivLayerSize(data["message"], 340, 200);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}});	
			}
		}
