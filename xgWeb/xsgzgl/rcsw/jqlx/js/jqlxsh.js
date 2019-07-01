function searchRs(){
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	if (shzt != ""){
		map["shzt"] = shzt;
	}
	jQuery("#dataTable").reloadGrid(map);
}

function selectTab(obj,shzt){
	jQuery("#shzt").val(shzt);
	var map = getSuperSearch();
	map["shzt"] = shzt;
	if (shzt == "dsh"){
		jQuery("#li_sh").css("display","");
		jQuery("#li_qx").css("display","none");
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {			
		jQuery("#li_sh").css("display","none");
		jQuery("#li_qx").css("display","");
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	searchRs();
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewXsJqlxSq(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}


function viewXsJqlxSq(sqid, xh) {
	showDialog("ѧ��������У�����ѯ", 750, 420, "rcsw_jqlx.do?method=viewJqlxsq&sqid=" + sqid
			+ "&xh=" + xh);
}

function lxsqSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0){
		alertInfo("��ѡ��һ����Ҫ��˵ļ�¼��");
	} else if (rows.length == 1) {
		showDialog("������У���",760,520,"rcsw_jqlx.do?method=toViewShDetail&sqid="+rows[0]["sqid"]+"&shid="+rows[0]["shid"]+"&gwid="+rows[0]["gwid"]+"&xh="+rows[0]["xh"]);
	} else{
		showDialog("�������",500,200,"rcsw_jqlx.do?method=jqlxPlsh");
	}
}

function savePlsh(shzt,shyj){
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwids = new Array();
	var xhs = new Array();
	var lcids = new Array();
	
	jQuery.each(rows,function(i,row){
		guid.push(row["sqid"]);
		gwids.push(row["gwid"]);
		xhs.push(row["xh"]);
		lcids.push(row["lcid"]);
	});

	jQuery.post(
		"rcsw_jqlx.do?method=savePlsh",
		{
		 shzt:shzt,
		 id:guid,
		 gwids:gwids,
		 xhs:xhs,
		 lcids:lcids,
		 shyj:shyj
		},function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				jQuery("#dataTable").reloadGrid();
			}});
		},
		'json'
	);
}

function cancelShnew(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
	} else {
		var splc = rows[0]["lcid"];
		var shid = rows[0]["shid"];
		var sqid = rows[0]["sqid"];
		showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
			jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
				// �ж��Ƿ����һ������(1:���һ�������ɹ���
				if("1" == data["cancelFlg"]){
					jQuery.post("rcsw_jqlx.do?method=cancelJqlxsh",{sqid:sqid},function(result){
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

function lcgzInfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {	
		showDialog("������У�������̸���",530,400,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['lcid']);
	}
}

//�Զ��嵼�� ����
function exportConfig() {
	var exportBh = "rcsw_jqlxsh.do";
	if("11488" == jQuery("#xxdm").val()){
		exportBh = "rcsw_jqlxsh_11488.do";
	}
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(exportBh, jqlxshExportData);
}

// ��������
function jqlxshExportData() {
	var shzt=jQuery("#shzt").val();
	setSearchTj();//���ø߼���ѯ����
	var exportBh = "rcsw_jqlxsh.do";
	if("11488" == jQuery("#xxdm").val()){
		exportBh = "rcsw_jqlxsh_11488.do";
	}
	var url = "rcsw_jqlx.do?method=exportSqshData&shzt="+shzt+"&dcclbh="+exportBh;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}