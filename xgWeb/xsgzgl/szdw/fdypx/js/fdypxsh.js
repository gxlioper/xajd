//��ʼ����ѯ
var gridSetting = {
		caption:"����Ա��ְ����б�",
		pager:"pager",
		url:"szdw_fdypxxmsh.do?method=fdypxxmList&type=query",
		colList:[
		   {label:'ְ����',name:'sqr', index: 'sqr',width:'10%',formatter:zghLink},
		   {label:'����',name:'xm', index: 'xm',width:'10%'},
		   {label:'��������',name:'bmmc', index: 'bmmc',width:'20%'},
		   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'20%'},
		   {label:'��ѵ��Ŀ',name:'xmmc', index: 'xmmc',width:'20%'},
		   {label:'���״̬',name:'shzt', index: 'shzt',width:'20%'},
		   {label:'����',name:'shid', index: 'shid',hidden:true},
		   {label:'splc',name:'splc', index: 'splc',hidden:true},
		   {label:'����',name:'sqid', index: 'sqid',key:true,hidden:true},
		   {label:'gwid',name:'gwid', index:'gwid',hidden:true}
		],
		params:{shlx:"dsh"},
		sortname: "sqsj",
	 	sortorder: "asc",
	 	radioselect:false
}
var gridSetting2 = {
		caption:"����Ա��ְ����б�",
		pager:"pager",
		url:"szdw_fdypxxmsh.do?method=fdypxxmList&type=query",
		colList:[
		   {label:'ְ����',name:'sqr', index: 'sqr',width:'10%',formatter:zghLink},
		   {label:'����',name:'xm', index: 'xm',width:'10%'},
		   {label:'��������',name:'bmmc', index: 'bmmc',width:'20%'},
		   {label:'���ʱ��',name:'shsj', index: 'shsj',width:'20%'},
		   {label:'������ѵ��Ŀ',name:'xmmc', index: 'xmmc',width:'20%'},
		   {label:'���״̬',name:'shzt', index: 'shzt',width:'20%'},
		   {label:'����',name:'shid', index: 'shid',hidden:true},
		   {label:'splc',name:'splc', index: 'splc',hidden:true},
		   {label:'����',name:'sqid', index: 'sqid',key:true,hidden:true}
		],
		params:{shlx:"ysh"},
		sortname: "shsj",
	 	sortorder: "desc",
	 	radioselect:true
}


function zghLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='viewjgz(\""+rowObject["sqr"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}

function viewjgz(zgh){
	var url='szdw_dwwh.do?method=ckJzgxx&zgh='+zgh;
	showDialog('', 830, 500, url);
}

function knsView(id,xh){
	showDialog("��ְ����Ϣ",800,473,"xpj_pjjg.do?method=pjxmjgView&id="+id+"&xh="+xh);
}
function zghView(){
	
}


//
function query(obj,shlx){
	jQuery("#comp_title li").removeClass();
	jQuery(obj).parent().attr("class","ha");
	jQuery("#shlx").val(shlx);
	if(shlx=='ysh'){
		jQuery("#dataTable").initGrid(gridSetting2);
		jQuery("#btn_qxsh").show();
		jQuery("#btn_sh").hide();
	}else{
		jQuery("#dataTable").initGrid(gridSetting);
		jQuery("#btn_qxsh").hide();
		jQuery("#btn_sh").show();
	}
	searchRs();
}
//��ѯ
function searchRs(){
	var map = getSuperSearch();
	map["shlx"] = jQuery.trim(jQuery("#shlx").val());
	jQuery("#dataTable").reloadGrid(map);
}
function go_sh(){
	var rows = jQuery("#dataTable").getSeletRow();
	var shlx = jQuery("#shlx").val();
	if(shlx=="ysh"){
		alertInfo("�Ѵ���ļ�¼�����ٴ���ˣ�");
	}else if (rows.length == 0){
		alertInfo("��ѡ����Ҫ��˵ļ�¼��");
		return false;
	} else if (rows.length == 1){
		showDialog("����Ա��ѵ���",760,500,'szdw_fdypxxmsh.do?method=fdypxxmsh&sqid='+rows[0]["sqid"]+"&shid="+rows[0]["shid"]+"&tt="+new Date().getTime());
	} else{
		showDialog("����Ա��ѵ�������",500,300,"szdw_fdypxxmsh.do?method=fdypxxmPlsh");
	}
}

/**
 * �����������
 * @param shzt
 * @param shyj
 * @return
 */
function savePlsh(shzt,shyj){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var xhs  = new Array();
	var splc = new Array();
	
	jQuery.each(rows, function(i,row){
		guid.push(row["sqid"]);
		gwid.push(row["gwid"]);
		xhs.push(row["sqr"]);
		splc.push(row["splc"]);
		
	});
	
	jQuery.post(
			"szdw_fdypxxmsh.do?method=fdypxxmPlsh&type=save",
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


//�������鿴
function lcgz(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����¼��");
	} else {
		showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}
/**
 * ������˲���
 * @param shzt
 * @param message
 * @return
 */
function save_sh_bak(){
	jQuery("#shzt").val(shzt);
	
	if (jQuery("#shyj").val() == ""){
		showAlertDivLayer("����д��������");
		return false;
	}
	if (jQuery("#shyj").val().length>200){
		showAlertDivLayer("���������ܳ���200��");
		return false;
	}
	
	var message;
	if(jQuery("#shjg").val() == "1"){
		message = "ͨ��";
	}
	if(jQuery("#shjg").val() == "2"){
		message = "��ͨ��";
	}
	if(jQuery("#shjg").val() == "3"){
		message = "�˻�";
	}
	//�ύ���
	showConfirmDivLayer("��ȷ����"+message+"����������",{"okFun":function(){
		var url = "szdw_fdypxxmsh.do?method=fdypxxmsh&type=save&tt="+new Date();
		ajaxSubFormWithFun("demoForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				//if (parent.window){
					refershParent();
				//}
			}});
		});
	}});
	
}