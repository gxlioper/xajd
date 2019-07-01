var gridSetting = {
		caption:"ҽ�Ʊ��մ����",
		pager:"pager",
		url:"rcsw_dxsylbx_ylbxshgl.do?method=ylbxshManage&type=query",
		colList:[
			{label:'key',name:'ylsqid', index: 'ylsqid',key:true ,hidden:true},
			{label:'��������',name:'splc', index: 'splc',hidden:true},
			{label:'ѧ��',name:'xh', index: 'xh',width:'14%',formatter:xhLink},
			{label:'����',name:'xm', index: 'xm',width:'12%'},
			{label:'�Ա�',name:'xb', index: 'xb',width:'9%'},
			{label:'�༶',name:'bjmc', index: 'bjdm',width:'22%'},
			{label:'ѧ��',name:'xn', index: 'xn',width:'12%'},
			{label:'ѧ��',name:'xqmc', index: 'xqmc',width:'11%'},
//			{label:'�α�״��',name:'cbzkmc', index: 'cbzkmc',width:'6%',formatter:cbzkLink},
			{label:'���״̬',name:'shztmc', index: 'shztmc',width:'20%'},
			{label:'���״̬����',name:'shzt', index: 'shzt',hidden:true},
			{label:'�α�״������',name:'cbzkdm', index: 'cbzkdm',hidden:true},
			{label:'��������',name:'czqebzdm', index: 'czqebzdm',hidden:true},
			{label:'ѧ��',name:'xq', index: 'xq',hidden:true},
			{label:'���Id',name:'shid', index: 'shid',hidden:true},
			{lable:'��������Id',name:'splc', index:'splc',hidden:true},
			{label:'��λId',name:'gwid', index: 'gwid',hidden:true}
		],
		params:{shzt:"dsh"},//Ĭ�ϴ����
		sortname: "cbsj",
	 	sortorder: "desc",
	 	radioselect:false
}
//{label:'�α�ʱ��',name:'cbsj', index: 'cbsj',width:'8%'},
var gridSetting2 = {
		caption:"ҽ�Ʊ��������",
		pager:"pager",
		url:"rcsw_dxsylbx_ylbxshgl.do?method=ylbxshManage&type=query",
		colList:[
			{label:'key',name:'ylsqid', index: 'ylsqid',key:true ,hidden:true},
			{label:'��������',name:'splc', index: 'splc',hidden:true},
			{label:'ѧ��',name:'xh', index: 'xh',width:'15%',formatter:xhLink},
			{label:'����',name:'xm', index: 'xm',width:'9%'},
			{label:'�Ա�',name:'xb', index: 'xb',width:'6%'},
			{label:'�༶',name:'bjmc', index: 'bjdm',width:'17%'},
			{label:'ѧ��',name:'xn', index: 'xn',width:'9%'},
			{label:'ѧ��',name:'xqmc', index: 'xqmc',width:'8%'},
			{label:'�α�״��',name:'cbzkmc', index: 'cbzkmc',width:'9%',formatter:cbzkLink},
			{label:'�α�ʱ��',name:'cbsj', index: 'cbsj',width:'11%'},
			{label:'���״̬',name:'shztmc', index: 'shztmc',width:'16%'},
			{label:'���״̬����',name:'shzt', index: 'shzt',hidden:true},
			{label:'�α�״������',name:'cbzkdm', index: 'cbzkdm',hidden:true},
			{label:'��������',name:'czqebzdm', index: 'czqebzdm',hidden:true},
			{label:'ѧ��',name:'xq', index: 'xq',hidden:true},
			{label:'���Id',name:'shid', index: 'shid',hidden:true}
		],
		params:{shzt:"ysh"},//Ĭ�ϴ����
		sortname: "cbsj",
	 	sortorder: "desc",
	 	radioselect:true
}
	
jQuery(function(){
	jQuery("#dataTable").initGrid(gridSetting);
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
	if (shzt == "dsh"){
		jQuery("#li_sh").css("display","");
		jQuery("#li_qx").css("display","none");
		jQuery("#dataTable").initGrid(gridSetting);
	} else {			
		jQuery("#li_sh").css("display","none");
		jQuery("#li_qx").css("display","");
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	searchRs();
}

/**
 * ҽ�Ʊ������
 * @return
 */
function ylbxSh(){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = jQuery("#shzt").val();
	if(shzt=="ysh"){
		showAlertDivLayer("�Ѵ����¼�����ٴ����");
		return false;
	} else if(rows.length == 0){
		showAlertDivLayer("��ѡ��һ����Ҫ��˵ļ�¼��");
		return false;
	} else if (rows.length == 1){
		var url = "rcsw_dxsylbx_ylbxshgl.do?method=ylbxDgsh&ylsqid="+rows[0]["ylsqid"]+'&xh=' + rows[0]["xh"]+'&shid=' +rows[0]["shid"] ;
		showDialog("ҽ�Ʊ������",700,500,url);
	} else{
		showDialog("ҽ�Ʊ����������",500,300,"rcsw_dxsylbx_ylbxshgl.do?method=ylbxPlsh");
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
		guid.push(row["ylsqid"]);
		gwid.push(row["gwid"]);
		xhs.push(row["xh"]);
		splc.push(row["splc"]);
	});

	jQuery.post(
		"rcsw_dxsylbx_ylbxshgl.do?method=ylbxPlsh&type=save",
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

function ylbxshLcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {		
		showDialog("ѧ��֤�����������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['ylsqid']+"&splc="+rows[0]['splc']);
	}
}

function cancelShnew(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
	} else {
		var splc = rows[0]["splc"];
		var shid = rows[0]["shid"];
		var ylsqid = rows[0]["ylsqid"];
		showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
			jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
				// �ж��Ƿ����һ������(1:���һ�������ɹ���
				if("1" == data["cancelFlg"]){
					jQuery.post("rcsw_dxsylbx_ylbxshgl.do?method=cancelYlbxsh",{ylsqid:ylsqid},function(result){
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

function viewYlbxsh(ylsqid, xh) {
	showDialog("ҽ�Ʊ�����˲鿴", 700, 480, "rcsw_dxsylbx_ylbxshgl.do?method=viewYlbxsh&ylsqid=" + ylsqid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewYlbxsh(\""
			+ rowObject["ylsqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function viewCbzk(ylsqid,xm) {
	showDialog("�α�״���鿴", 450, 220, "rcsw_dxsylbx_ylbxshgl.do?method=viewCbzk&ylsqid="+ylsqid+"&xm="+xm);
}

function cbzkLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewCbzk(\""
			+ rowObject["ylsqid"] + "\",\""+ rowObject["xm"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

var DCCLBH = "rcsw_dxsylbx_ylbxsh.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, ylbxshExportData);
}

// ��������
function ylbxshExportData() {
	var shlx = jQuery("#shzt").val();
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_dxsylbx_ylbxshgl.do?method=exportData&dcclbh=" + DCCLBH + "&shlx=" + shlx;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}