var gridSetting = {
			caption:"������������б�",
			pager:"pager",
			url:"sspysh.do?method=pyShList&type=query",
			colList:[
			   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
			   {label:'��������',name:'splc', index: 'splc',hidden:true},
			   {label:'������ѧ��',name:'xh', index: 'xh',width:'8%'},
			   {label:'����������',name:'xm', index: 'xm',width:'8%'},
			   {label:'¥��',name:'ldmc', index: 'ldmc',width:'8%'},
			   {label:'���Һ�',name:'qsh', index: 'qsh',width:'8%'},
			   {label:'ѧ��',name:'xn', index: 'xn',width:'8%'},
			   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'8%'},
			   {label:'������Ŀ',name:'pyxmmc', index: 'pyxmmc',width:'8%'},
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
		caption:"������������б�",
		pager:"pager",
		url:"sspysh.do?method=pyShList&type=query",
		colList:[
				   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
				   {label:'��������',name:'splc', index: 'splc',hidden:true},
				   {label:'������ѧ��',name:'xh', index: 'xh',width:'8%'},
				   {label:'����������',name:'xm', index: 'xm',width:'8%'},
				   {label:'¥��',name:'ldmc', index: 'ldmc',width:'8%'},
				   {label:'���Һ�',name:'qsh', index: 'qsh',width:'8%'},
				   {label:'ѧ��',name:'xn', index: 'xn',width:'8%'},
				   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'8%'},
				   {label:'������Ŀ',name:'pyxmmc', index: 'pyxmmc',width:'8%'},
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
	
function getSscyxx(){
	jQuery("#xsList").empty();
	var ldqsxx=jQuery('#lddm').val()+jQuery('#qsh').val();
	var html = "";
	jQuery.post("sspysq.do?method=getCwxx", {
		ldqsxx : ldqsxx
	}, function(data) {
		for(var i =0;i<data.length;i++){
			html+="<tr><td align='center'>"+data[i]["xh"]+"</td><td align='center'>"+data[i]["xm"]+"</td><td align='center'>"+data[i]["xsbjmc"]+"</td><td align='center'>"+data[i]["cwh"]+"</td></tr>";
		}
		jQuery("#xsList").append(html);
	}, 'json');
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
		var url = "sspysh.do?method=sspyDgsh&sqid="+rows[0]["sqid"]+'&xh=' + rows[0]["xh"]+'&shid=' +rows[0]["shid"] ;
		showDialog("���",700,500,url);
	} else{
		showDialog("�������",500,300,"sspysh.do?method=sspyPlsh");
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
					jQuery.post("sspysh.do?method=cancelSh",{sqid:sqid,shzt:shzt},function(result){
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
		"sspysh.do?method=sspyPlsh&type=save",
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



//���̸���
function shLcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {		
		showDialog("���������������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}

var DCCLBH = "sspy_sh.do";//dcclbh,�������ܱ��
//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, ExportData);
}

// ��������
function ExportData() {
	var shlx = jQuery("#shlx").val();
	setSearchTj();//���ø߼���ѯ����
	var url = "sspysh.do?method=exportData&dcclbh=" + DCCLBH + "&shlx=" + shlx;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}