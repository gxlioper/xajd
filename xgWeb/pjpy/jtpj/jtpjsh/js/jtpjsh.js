var action="jtpjsh.do";
var gridSetting = {
	caption:"��������������б�",
	pager:"pager",
	url:"jtpjsh.do?method=list&type=query",
	colList:[
	   {label:'��������id',name:'sqid', index: 'sqid',key:true,hidden:true},
	   {label:'��������',name:'splcid', index: 'splcid',hidden:true},
	   {label:'���뼯�� ',name:'pjjtmc', index: 'pjjtmc',formatter:pjjtmc},
	   {label:'����',name:'rs', index: 'rs'},
	   {label:'���뽱������',name:'jxmc', index: 'jxmc'},
	   {label:'����ѧ��',name:'pdxn', index: 'pdxn',hidden:true},
	   {label:'����ѧ��',name:'pdxqmc', index: 'pdxqmc',hidden:true},
	   {label:'������������',name:'jxpdzq', index: 'pdxn,pdxq',formatter:jxpdzq},
	   {label:'����ʱ��',name:'sqsj', index: 'sqsj'},
	   {label:'��λid',name:'gwid', index: 'gwid',hidden:true},
	   {label:'���״̬',name:'shzt', index: 'shzt',hidden:true},
	   {label:'���״̬',name:'shztmc', index: 'shzt',formatter:shzt},
	   {label:'�����',name:'shr', index: 'shr',hidden:true},
	   {label:'���������',name:'mc', index: 'mc',hidden:true},
	   {label:'shid',name:'shid', index: 'shid',hidden:true}
			],
	sortname: "sqsj",
 	sortorder: "asc"
}
function pjjtmc(cellValue, rowObject) {
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + rowObject["sqid"]
			+ "')\" class='name'>" + cellValue + "</a>";
}
// �鿴��Ϣ
function ckxx(sqid) {
	var url = action + "?method=showView&sqid=" + sqid;
	showDialog("������Ϣ", 800, 500, url);
}

function jxpdzq(cellValue, rowObject) {
	var pdxq=rowObject["pdxqmc"];
	if(!pdxq){
		pdxq="";
	}
	return rowObject["pdxn"] + pdxq;
}
function shzt(cellValue, rowObject) {
	var shzt = rowObject["shzt"];
	var mc = rowObject["mc"];
	var shztmc = "";
	switch (shzt) {
	case "1":
		shztmc = "ͨ��";
		break;
	case "2":
		shztmc = "��ͨ��";
		break;
	case "3":
		shztmc = "���˻�";
		break;
	case "5":
		shztmc = "�����";
		break;
	default:
		shztmc = "�����";
		break;
	}
	return mc+"["+shztmc+"]";
}
//�л�������/�Ѵ���ҳ��
function selectTab(obj, shzt) {
	jQuery("#shzt").val(shzt);
	if (shzt == "dsh") {
		jQuery("#li_sh").css("display", "");
		jQuery("#li_qx").css("display", "none");
		jQuery("#title").html("��������������б�");
		var map = getSuperSearch();
		map["shzt"]="dsh";
		jQuery("#dataTable").reloadGrid(map);
	} else {
		jQuery("#li_sh").css("display", "none");
		jQuery("#li_qx").css("display", "");
		jQuery("#title").html("��������������б�");
		var map = getSuperSearch();
		map["shzt"]="ysh";
		jQuery("#dataTable").reloadGrid(map);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}
//���
function jtpjsh() {
	var rows = jQuery("#dataTable").getSeletRow();
	var xxdm=jQuery("#xxdm").val();
	if (rows.length != 1) {
		if("10704"==xxdm){
			showAlertDivLayer("��ѡ��һ����Ҫ��˵ļ�¼��");
		}else if (rows.length== 0) {
			showAlertDivLayer("��ѡ����Ҫ��˵ļ�¼��");
		}else{
			showDialog("�������",500,250,"jtpjsh.do?method=jtpjPlsh");
		}
	} else {
		var xh = rows[0]["xh"];
		var url = action + '?method=jtpjsh&sqid='
		+ rows[0]["sqid"]+"&gwid="+rows[0]["gwid"];
		showDialog("�����������",800,500,url);
	}
}
function save_sh(){
	var shzt=jQuery("#shjg").val();
	jQuery("#shzt").val(shzt);
	if (jQuery("#shyj").val() == ""){
		showAlertDivLayer("����д��������");
		return false;
	}
	if (jQuery("#shyj").val().length>200){
		showAlertDivLayer("���������ܳ���200��");
		return false;
	}
	var text=jQuery("#shjg").find("option:selected").text();
	//�ύ���
	showConfirmDivLayer("��ȷ��<font color='red'>[" + text + "]</font>��������",{"okFun":function(){
			zx(shzt,text);
	}});
	
}
function zx(shzt,text){
	var url = "jtpjsh.do?method=jtpjsh&type=save&tt="+new Date();
	jQuery("#form").ajaxSubmit( {
		url : url,
		type : "post",
		dataType : "json",
		success : function(data) {
			if (data["message"] == "����ɹ���") {
				showAlert("<font color='red'>["+text+"]</font>�����ɹ���", {}, {
					"clkFun" : function() {
						if (parent.window) {
							refershParent();
						}
					}
				});
			} else {
				showAlert("<font color='red'>["+text+"]</font>����ʧ�ܣ�");
			}
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8"
		});
}
function searchRs() {
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	if (shzt != "") {
		map["shzt"] = shzt;
	}
	jQuery("#dataTable").reloadGrid(map);
}
function rcxwshLcinfo() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {
		showDialog("���������������̸���", 600, 400, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splcid']);
	}
}
function reloadsh(){
	jQuery("#dataTable").reloadGrid();
}

function savePlsh(shzt,shyj){
	var rows = jQuery("#dataTable").getSeletRow();
	var sqids = new Array();
	var gwids = new Array();
	var splcs = new Array();
	var pjjtmcs=new Array();
	jQuery.each(rows,function(i,row){
		sqids.push(row["sqid"]);
		gwids.push(row["gwid"]);
		splcs.push(row["splcid"]);
		pjjtmcs.push(row["pjjtmc"]);
	});
	jQuery.post(
		"jtpjsh.do?method=jtpjPlsh&type=save",
		{
			sqids:sqids,
			gwids:gwids,
			splcs:splcs,
			pjjtmcs:pjjtmcs,
			shzt:shzt,
			shyj:shyj
		},function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				jQuery("#dataTable").reloadGrid();
			}});
		},
		'json'
	);
}
