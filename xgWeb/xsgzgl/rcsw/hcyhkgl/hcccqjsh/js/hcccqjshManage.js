var gridSetting = {
			caption:"�𳵳˳��������",
			pager:"pager",
			url:"rcsw_hcyhk_hcccqjshgl.do?method=hcccqjshManage&type=query",
			colList:[
				{label:'key',name:'ccqjtxid', index: 'ccqjtxid',key:true,hidden:true },
				{label:'��������',name:'splc', index: 'splc',hidden:true},
				{label:'ѧ��',name:'xh', index: 'xh',width:'6%',formatter:xhLink},
				{label:'����',name:'xm', index: 'xm',width:'6%'},
				{label:'�Ա�',name:'xb', index: 'xb',width:'3%'},
				{label:'�����༶',name:'bjmc', index: 'bjdm',width:'8%'},
                {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'8%'},
				{label:'�𳵳˳�����',name:'hcccqjmc', index: 'hcccqjmc',width:'12%'},
				{label:'��дʱ��',name:'txsj', index: 'txsj',width:'10%'},
				{label:'���״̬',name:'shztmc', index: 'shztmc',width:'10%'},
				{label:'���״̬����',name:'shzt', index: 'shzt',hidden:true},
				{label:'���Id',name:'shid', index: 'shid',hidden:true},
				{label:'��λId',name:'gwid', index: 'gwid',hidden:true},
				{label:'��������',name:'splc',index:'splc',hidden:true}
			],
			params:{shzt:"dsh"},//Ĭ�ϴ����
			sortname: "txsj",
		 	sortorder: "desc",
		 	radioselect:false
}

var gridSetting2 = {
		caption:"�𳵳˳��������",
		pager:"pager",
		url:"rcsw_hcyhk_hcccqjshgl.do?method=hcccqjshManage&type=query",
		colList:[
			{label:'key',name:'ccqjtxid', index: 'ccqjtxid',key:true ,hidden:true},
			{label:'��������',name:'splc', index: 'splc',hidden:true},
			{label:'ѧ��',name:'xh', index: 'xh',width:'6%',formatter:xhLink},
			{label:'����',name:'xm', index: 'xm',width:'6%'},
			{label:'�Ա�',name:'xb', index: 'xb',width:'3%'},
            {label:'�����༶',name:'bjmc', index: 'bjdm',width:'8%'},
            {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'8%'},
			{label:'�𳵳˳�����',name:'hcccqjmc', index: 'hcccqjmc',width:'12%'},
			{label:'��дʱ��',name:'txsj', index: 'txsj',width:'10%'},
			{label:'���״̬',name:'shztmc', index: 'shztmc',width:'10%'},
			{label:'���״̬����',name:'shzt', index: 'shzt',hidden:true},
			{label:'���Id',name:'shid', index: 'shid',hidden:true}
		],
		params:{shzt:"ysh"},//Ĭ�ϴ����
		sortname: "txsj",
	 	sortorder: "desc",
	 	radioselect:true
}

var gridSetting3 = {
		caption:"�𳵳˳��������",
		pager:"pager",
		url:"rcsw_hcyhk_hcccqjshgl.do?method=hcccqjshManage&type=query",
		colList:[
			{label:'key',name:'ccqjtxid', index: 'ccqjtxid',key:true,hidden:true },
			{label:'��������',name:'splc', index: 'splc',hidden:true},
			{label:'ѧ��',name:'xh', index: 'xh',width:'6%',formatter:xhLink},
			{label:'����',name:'xm', index: 'xm',width:'6%'},
			{label:'�Ա�',name:'xb', index: 'xb',width:'3%'},
			{label:'�༶',name:'bjmc', index: 'bjdm',width:'8%'},
			{label:'��ʼվ',name:'ccqdz', index: 'ccqdz',width:'8%'},
			{label:'�յ�վ',name:'cczdz', index: 'cczdz',width:'8%'},
			{label:'��дʱ��',name:'txsj', index: 'txsj',width:'10%'},
			{label:'���״̬',name:'shztmc', index: 'shztmc',width:'10%'},
			{label:'���״̬����',name:'shzt', index: 'shzt',hidden:true},
			{label:'���Id',name:'shid', index: 'shid',hidden:true},
			{label:'��λId',name:'gwid', index: 'gwid',hidden:true},
			{label:'��������',name:'splc',index:'splc',hidden:true}
		],
		params:{shzt:"dsh"},//Ĭ�ϴ����
		sortname: "txsj",
	 	sortorder: "desc",
	 	radioselect:false
}

var gridSetting4 = {
		caption:"�𳵳˳��������",
		pager:"pager",
		url:"rcsw_hcyhk_hcccqjshgl.do?method=hcccqjshManage&type=query",
		colList:[
			{label:'key',name:'ccqjtxid', index: 'ccqjtxid',key:true ,hidden:true},
			{label:'��������',name:'splc', index: 'splc',hidden:true},
			{label:'ѧ��',name:'xh', index: 'xh',width:'6%',formatter:xhLink},
			{label:'����',name:'xm', index: 'xm',width:'6%'},
			{label:'�Ա�',name:'xb', index: 'xb',width:'3%'},
			{label:'�༶',name:'bjmc', index: 'bjdm',width:'8%'},
			{label:'��ʼվ',name:'ccqdz', index: 'ccqdz',width:'8%'},
			{label:'�յ�վ',name:'cczdz', index: 'cczdz',width:'8%'},
			{label:'��дʱ��',name:'txsj', index: 'txsj',width:'10%'},
			{label:'���״̬',name:'shztmc', index: 'shztmc',width:'10%'},
			{label:'���״̬����',name:'shzt', index: 'shzt',hidden:true},
			{label:'���Id',name:'shid', index: 'shid',hidden:true}
		],
		params:{shzt:"ysh"},//Ĭ�ϴ����
		sortname: "txsj",
	 	sortorder: "desc",
	 	radioselect:true
}
	
jQuery(function(){
	var xxdm = jQuery("#xxdm").val();
	if("11318" == xxdm) {
		jQuery("#dataTable").initGrid(gridSetting3);
	}else {
		jQuery("#dataTable").initGrid(gridSetting);
	}
});
	
function searchRs(){
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	if (shzt != ""){
		map["shzt"] = shzt;
	}
	jQuery("#dataTable").reloadGrid(map);
}
	
	
/**
 * ѧ��֤�������
 * @param obj
 * @param shzt
 * @return
 */
function selectTab(obj,shzt){
	jQuery("#shzt").val(shzt);
	var xxdm = jQuery("#xxdm").val();
	if (shzt == "dsh"){
		jQuery("#li_sh").css("display","");
		jQuery("#li_qx").css("display","none");
		if("11318" == xxdm) {
			jQuery("#dataTable").initGrid(gridSetting3);
		}else {
			jQuery("#dataTable").initGrid(gridSetting);
		}
	} else {			
		jQuery("#li_sh").css("display","none");
		jQuery("#li_qx").css("display","");
		if("11318" == xxdm) {
			jQuery("#dataTable").initGrid(gridSetting4);
		}else {
			jQuery("#dataTable").initGrid(gridSetting2);
		}
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	searchRs();
}

/**
 * ѧ��֤�������
 * @return
 */
function hcccqjSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = jQuery("#shzt").val();
	if(shzt=="ysh"){
		showAlertDivLayer("�Ѵ����¼�����ٴ����");
		return false;
	}else if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫ��˵ļ�¼");
		return false;
	}else if (rows.length == 1){
		var url = "rcsw_hcyhk_hcccqjshgl.do?method=hcccqjDgsh&ccqjtxid="+rows[0]["ccqjtxid"]+'&xh=' + rows[0]["xh"]+'&shid=' +rows[0]["shid"] ;
		showDialog("�𳵳˳��������",700,500,url);
	} else{
		showDialog("�𳵳˳������������",500,300,"rcsw_hcyhk_hcccqjshgl.do?method=hcccPlsh");
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
	var gwid = new Array();
	var xhs = new Array();
	var splc = new Array();
	
	jQuery.each(rows,function(i,row){
		guid.push(row["ccqjtxid"]);
		gwid.push(row["gwid"]);
		xhs.push(row["xh"]);
		splc.push(row["splc"]);
	});

	jQuery.post(
		"rcsw_hcyhk_hcccqjshgl.do?method=hcccPlsh&type=save",
		{
		 shzt:shzt,
		 id:guid,
		 gwids:gwid,
		 xhs:xhs,
		 shyj:shyj,
		 splcs:splc
		},function(data){
			
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				jQuery("#dataTable").reloadGrid();
			}});
		},
		'json'
	);
}


function hcccqjshLcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {		
		showDialog("ѧ��֤�����������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['ccqjtxid']+"&splc="+rows[0]['splc']);
	}
}

function cancelShnew(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
	} else {
		var splc = rows[0]["splc"];
		var shid = rows[0]["shid"];
		var ccqjtxid = rows[0]["ccqjtxid"];
		var shzt = rows[0]["shzt"];
		showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
			jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
				// �ж��Ƿ����һ������(1:���һ�������ɹ���
				if("1" == data["cancelFlg"]){
					jQuery.post("rcsw_hcyhk_hcccqjshgl.do?method=cancelHcccqjsh",{ccqjtxid:ccqjtxid,shzt:shzt},function(result){
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

function viewHcccqjsh(ccqjtxid, xh) {
	showDialog("ѧ��֤������˲�ѯ", 700, 480, "rcsw_hcyhk_hcccqjshgl.do?method=viewHcccqjsh&ccqjtxid=" + ccqjtxid+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewHcccqjsh(\""
			+ rowObject["ccqjtxid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}


var DCCLBH = "rcsw_hcyhk_hcccqjsh.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, rcxwxshExportData);
}

// ��������
function rcxwxshExportData() {
	var shlx = jQuery("#shzt").val();
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_hcyhk_hcccqjshgl.do?method=exportData&dcclbh=" + DCCLBH + "&shlx=" + shlx;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}