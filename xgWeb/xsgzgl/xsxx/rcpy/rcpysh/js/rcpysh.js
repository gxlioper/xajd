var gridSetting = {
			caption:"�˲�����������б�",
			pager:"pager",
			url:"rcpy_rcpysh.do?method=getshList&type=query",
			colList:[
			   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
			   {label:'��������',name:'splc', index: 'splc',hidden:true},
			   {label:'ѧ��',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
			   {label:'����',name:'xm', index: 'xm',width:'8%'},
			   {label:'������Ŀ',name:'xmmc', index: 'xmmc',width:'8%'},
			   {label:'�������',name:'pylbmc', index: 'pylbmc',width:'8%'},
			   {label:'����ָ��',name:'khzbmc', index: 'khzbmc',width:'8%'},
			   {label:'��������',name:'xztjmc', index: 'xztjmc',width:'8%'},
			   {label:'���״̬',name:'shztmc', index: 'shztmc',width:'6%'},
			   {label:'���״̬',name:'shzt', index: 'shzt',hidden:true},
			   {label:'���Id',name:'shid', index: 'shid',hidden:true},
			   {label:'��λId',name:'gwid', index: 'gwid',hidden:true},
			   {label:'��������',name:'splc',index:'splc',hidden:true}
			],
			params:{shlx:"dsh"},//Ĭ�ϴ����
			sortname: "sqsj",
		 	sortorder: "desc",
		 	radioselect:false
}
var gridSetting2 = {
		caption:"�˲�����������б�",
		pager:"pager",
		url:"rcpy_rcpysh.do?method=getshList&type=query",
		colList:[
		   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
		   {label:'��������',name:'splc', index: 'splc',hidden:true},
		   {label:'ѧ��',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
		   {label:'����',name:'xm', index: 'xm',width:'8%'},
		   {label:'������Ŀ',name:'xmmc', index: 'xmmc',width:'8%'},
		   {label:'�������',name:'pylbmc', index: 'pylbmc',width:'8%'},
		   {label:'����ָ��',name:'khzbmc', index: 'khzbmc',width:'8%'},
		   {label:'��������',name:'xztjmc', index: 'xztjmc',width:'8%'},
		   {label:'���״̬',name:'shztmc', index: 'shztmc',width:'6%'},
		   {label:'���״̬',name:'shzt', index: 'shzt',hidden:true},
		   {label:'���Id',name:'shid', index: 'shid',hidden:true},
		   {label:'��λId',name:'gwid', index: 'gwid',hidden:true},
		   {label:'��������',name:'splc',index:'splc',hidden:true}
		],
		params:{shlx:"ysh"},//Ĭ�ϴ����
		sortname: "sqsj",
	 	sortorder: "desc",
	 	radioselect:true
}
	
function searchRs(){
	var map = getSuperSearch();
	var shlx = jQuery("#shlx").val();
	if (shlx != ""){
		map["shlx"] = shlx;
	}
	jQuery("#dataTable").reloadGrid(map);
}
	


function viewRcpy(sqid,xh) {
	showDialog("�鿴", 720, 520, "rcpy_rcpysq.do?method=ckRcpysq&sqid=" + sqid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewRcpy(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}


//���
function Sh(){
	var rows = jQuery("#dataTable").getSeletRow();
	var shlx = jQuery("#shlx").val();
	if(shlx=="ysh"){
		showAlertDivLayer("�Ѵ����¼�����ٴ����");
		return false;
	}else if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫ��˵ļ�¼");
		return false;
	}else if (rows.length == 1){
		var url = "rcpy_rcpysh.do?method=rcpyDgsh&sqid="+rows[0]["sqid"]+'&xh=' + rows[0]["xh"]+'&shid=' +rows[0]["shid"] ;
		showDialog("���",700,500,url);
	} else{
		showDialog("�������",500,300,"rcpy_rcpysh.do?method=rcpyPlsh");
	} 
}

//�������
function cancelSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
	} else {
		var splc = rows[0]["splc"];
		var shid = rows[0]["shid"];
		var sqid = rows[0]["sqid"];
		var shzt = rows[0]["shzt"];
		showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
			jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
				// �ж��Ƿ����һ������(1:���һ�������ɹ���
				if("1" == data["cancelFlg"]){
					jQuery.post("rcpy_rcpysh.do?method=cancelSh",{sqid:sqid,shzt:shzt},function(result){
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

//�������
function savePlsh(shzt,shyj){
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var xhs = new Array();
	var splc = new Array();
	
	jQuery.each(rows,function(i,row){
		guid.push(row["sqid"]);
		gwid.push(row["gwid"]);
		xhs.push(row["xh"]);
		splc.push(row["splc"]);
	});

	jQuery.post(
		"rcpy_rcpysh.do?method=rcpyPlsh&type=save",
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


//�л�lab
function selectTab(obj,shlx){
	jQuery("#shlx").val(shlx);
	if (shlx == "dsh"){
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




function shLcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {		
		showDialog("�˲������������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}



var DCCLBH = "xsxx_rcpy_rcpysh.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, ExportData);
}

// ��������
function ExportData() {
	var shlx = jQuery("#shlx").val();
	setSearchTj();//���ø߼���ѯ����
	var url = "rcpy_rcpysh.do?method=exportData&dcclbh=" + DCCLBH + "&shlx=" + shlx;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}