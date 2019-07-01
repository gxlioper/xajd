
	
function searchRs(){
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	if (shzt != ""){
		map["shzt"] = shzt;
	}
	jQuery("#dataTable").reloadGrid(map);
}
	
	
/**
 * ���
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
 * ���
 * @return
 */
function lstdSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = jQuery("#shzt").val();
	if(shzt=="ysh"){
		showAlertDivLayer("�Ѵ����¼�����ٴ����");
		return false;
	} else if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫ����ļ�¼")
		return false;
	} else if(rows.length == 1){
		var url = "rcsw_lstd_shgl.do?method=lstdDgsh&sqid="+rows[0]["sqid"]+'&xh=' + rows[0]["xh"]+'&shid=' +rows[0]["shid"] ;
		showDialog("���",700,500,url);
	} else{
		showDialog("�������",500,300,"rcsw_lstd_shgl.do?method=lstdPlsh");
	} 
}

function shLcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (1!=ids.length){
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {		
		showDialog("�������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}

function cxsh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
	}else{
		var splc = rows[0]["splc"];
		var shid = rows[0]["shid"];
		var sqid = rows[0]["sqid"];
		var shzt = rows[0]["shzt"];
		showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
			jQuery.post("dekt_xfsh.do?method=cxsh",{splc:splc,shid:shid,sqid:sqid,shzt:shzt},function(data){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					jQuery("#dataTable").reloadGrid();
				}});
		},'json');
		}});
	}
}

function viewXfsh(sqid, xh) {
	showDialog("��˲�ѯ", 700, 480, "dekt_xfsh.do?method=xfshView&sqid=" + sqid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewXfsh(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue	+ "</a>";
}
var DCCLBH = "rcsw_lstd_bbsh.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, rcxwxshExportData);
}

// ��������
function rcxwxshExportData() {
	var shlx = jQuery("#shzt").val();
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_lstd_shgl.do?method=exportData&dcclbh=" + DCCLBH + "&shlx=" + shlx;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
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
	
	jQuery.each(rows,function(i,row){
		guid.push(row["sqid"]);
		gwid.push(row["gwid"]);
		xhs.push(row["xh"]);
	});

	jQuery.post(
		"rcsw_lstd_shgl.do?method=lstdPlsh&type=save",
		{
		 shzt:shzt,
		 id:guid,
		 gwids:gwid,
		 xhs:xhs,
		 shyj:shyj
		},function(data){
			
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				jQuery("#dataTable").reloadGrid();
			}});
		},
		'json'
	);
}
