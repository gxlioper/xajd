
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
	}else if(rows.length==1){
		showDialog("ѧ���ɲ�����ְ�����",760,500,'szdw_zwsh.do?method=zwsh&sqid='+rows[0]["sqid"]+"&shid="+rows[0]["shid"]+"&xh="+rows[0]["xh"]+"&tt="+new Date().getTime());
	}else{
		showDialog("ѧ���ɲ�����ְ���������",500,300,"szdw_zwsh.do?method=zwPlsh");
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
	var zwid = new Array();
	
	jQuery.each(rows, function(i,row){
		guid.push(row["sqid"]);
		gwid.push(row["gwid"]);
		xhs.push(row["xh"]);
		splc.push(row["splc"]);
		zwid.push(row["zwid"]);
		
	});
	
	jQuery.post(
			"szdw_zwsh.do?method=zwPlsh&type=save",
			{
			 shzt:shzt,
			 id:guid,
			 gwids:gwid,
			 xhs:xhs,
			 shyj:shyj,
			 splcs:splc,
			 zwids:zwid
			 
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
function save_sh_bak(shzt,message){
	jQuery("#shzt").val(shzt);
	
	if (jQuery("#shyj").val() == ""){
		showAlertDivLayer("����д��������");
		return false;
	}
	if (jQuery("#shyj").val().length>200){
		showAlertDivLayer("���������ܳ���200��");
		return false;
	}
	if(shzt=="3"||shzt==3){
		var shid = jQuery("#shid").val();
		var splc = jQuery("#splc").val();
		var shyj = jQuery("#shyj").val();
		splc_th(shid,splc,shyj);
		return false;
	}
	//�ύ���
	showConfirmDivLayer("��ȷ����"+message+"����������",{"okFun":function(){
		var url = "szdw_zwsh.do?method=zwsh&type=save&tt="+new Date();
		ajaxSubFormWithFun("demoForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				//if (parent.window){
					refershParent();
				//}
			}});
		});
	}});
	
}

//ѧ������
function xhLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='view(\""+row["sqid"]+"\",\""+row["xh"]+"\")'>"+cellValue+"</a>";
}

//�鿴
function view(sqid,xh){
	showDialog("ѧ���ɲ�ְ����˲鿴",760,460,'szdw_zwsh.do?method=zwsh&type=ck&sqid='+sqid+"&xh="+xh+"&tt="+new Date().getTime());
}