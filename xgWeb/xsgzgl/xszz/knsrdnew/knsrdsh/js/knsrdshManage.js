
	
function searchRs(){
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	if (shzt != ""){
		map["shzt"] = shzt;
	}
	jQuery("#dataTable").reloadGrid(map);
}
	
	
/**
 * �������϶����
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
 * �������϶����
 * @return
 */
function knsrdSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = jQuery("#shzt").val();
	if(shzt=="ysh"){
		showAlertDivLayer("�Ѵ����¼�����ٴ����");
		return false;
	}
	else if (rows.length == 0){
		showAlertDivLayer("��ѡ��һ����Ҫ��˵ļ�¼��");
	}
	else if(rows.length>1){
		showDialog("�������",500,300,"xg_xszz_knsrd_knshgl.do?method=knsrdPlsh");
	}
		else{
		var url = "xg_xszz_knsrd_knshgl.do?method=knsrdDgsh&sqid="+rows[0]["sqid"]+'&xh=' + rows[0]["xh"]+'&shid=' +rows[0]["shid"]+'&zbid=' +rows[0]["zbid"] ;
		showDialog("�������϶����",760,525,url);
	} 
}
/**
 * ������˱���
 * @param 
 * @return
 */
function savePlsh(shzt,rddc,shyj){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var sqid = new Array();
	var gwid = new Array();
	var xhs = new Array();
	
	jQuery.each(rows,function(i,row){
		sqid.push(row["sqid"]);
		gwid.push(row["gwid"]);
		xhs.push(row["xh"]);
	});

	jQuery.post(
		"xg_xszz_knsrd_knshgl.do?method=savePlsh",
		{
		 splc:rows[0]["splc"],
		 shzt:shzt,
		 sqids:sqid,
		 gwids:gwid,
		 xhs:xhs,
		 rddc:rddc,
		 shyj:shyj
		},function(data){
			
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				jQuery("#dataTable").reloadGrid();
			}});
		},
		'json'
	);
}
function knsrdshLcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {		
		showDialog("�������϶��������̸���",520,400,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}

function cancelShnew(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫ��������˼�¼��");
	} else {
		var msg="<div><ul>";
		var callBackMsg="";
		showConfirmDivLayer("��ȷ��Ҫ��������ѡ��¼����˲�����",{"okFun":function(){
			jQuery.ajaxSetup({async:false});
			for ( var i = 0; i < rows.length; i++) {
			jQuery.post("comm_spl.do?method=cxshnew",{shlc:rows[i]["splc"],shid:rows[i]["shid"]},function(data){
				// �ж��Ƿ����һ������(1:���һ�������ɹ���
				if("1" == data["cancelFlg"]){
					jQuery.post("xg_xszz_knsrd_knshgl.do?method=cancelKnsrdsh",{sqid:rows[i]["sqid"]},function(result){
						callBackMsg=result["message"];
					},'json');
				}else{
					
					callBackMsg=data["message"];
				}
				msg+="<li><span style='width:40%;display:inline-block'>"+rows[i]["xh"]+"</span><font class='have'>"+callBackMsg+"</font></li>";
		},'json');
		}
			msg+="</ul></div>";
			showAlertDivLayer(msg,{},{"clkFun":function(){
				jQuery("#dataTable").reloadGrid();	
		}});
	
		jQuery.ajaxSetup({async:true});
		}});
	}
}

function viewKnsrdsh(sqid, xh) {
	showDialog("�������϶���˲�ѯ", 700, 480, "xg_xszz_knsrd_knshgl.do?method=viewKnsrdsh&sqid=" + sqid+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewKnsrdsh(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}


var DCCLBH = "xg_xszz_knsrd_knsh.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, rcxwxshExportData);
}

// ��������
function rcxwxshExportData() {
	var shlx = jQuery("#shzt").val();
	setSearchTj();//���ø߼���ѯ����
	var url = "xg_xszz_knsrd_knshgl.do?method=exportData&dcclbh=" + DCCLBH + "&shlx=" + shlx;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}