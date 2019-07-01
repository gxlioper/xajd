/**
 * ��ѯ
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	if (null!=shzt&&shzt != "") {
		map["shzt"] = shzt;
	}else{
		map["shzt"] = "dsh";
	}
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * ѧ������
 * @param cellValue
 * @param rowObject
 * @return
 */
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='xspjshView(\""+rowObject["sqid"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}

/**
 * ������Ӳ鿴��ϸ��Ϣ
 * @param id
 * @param xh
 * @return
 */
function xspjshView(sqid) {
	var title = "��Ŀ��˲鿴";
	var url = "xspj_xspjsq.do?method=xspjsqView&sqid="+sqid;
	showDialog(title,690,535,url);
}

/**
 * �л�Tabҳ
 * @param obj
 * @param shzt
 * @return
 */
function selectTab(obj, shzt) {
	jQuery("#shzt").val(shzt);
	if (shzt == "dsh") {
		jQuery("#li_sh").css("display", "");
		jQuery("#li_qx").css("display", "none");
		var map = getSuperSearch();
		map["shzt"]="dsh";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_sh").css("display", "none");
		jQuery("#li_qx").css("display", "");
		var map = getSuperSearch();
		map["shzt"]="ysh";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

/**
 * ���(����������)
 * @return
 */
function xspjsh(){
	/*��ѡ��¼*/
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = jQuery("#shzt").val();
	
	if(rows.length == 1){
		var url = "xspj_xspjsh.do?method=xspjshSingle&sqid=" + rows[0]["sqid"] + '&shid=' + rows[0]["shid"];
		showDialog("��Ŀ���", 800, 500, url);
	} else if(rows.length > 1){
		var ids = jQuery("#dataTable").getSeletIds();
		showDialog("��Ŀ�������", 500, 300, "xspj_xspjsh.do?method=xspjshBatch&id="+ids);
	}else{
		var rowConut = jQuery("#rowConut").html();
		var url = "xspj_xspjsh.do?method=xspjshPlsh&len="+rowConut;
		var title = "�������";
		showDialog(title,500,300,url);
	}
}



/**
 * ������˱���
 * @param shzt
 * @param shyj
 * @param fs
 * @param ffgz
 * @return
 */
function xspjshBatchSave(shzt, shyj,fs, ffgz) {
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var xhs = new Array();
	jQuery.each(rows, function(i, row) {
		guid.push(row["sqid"]);
		gwid.push(row["gwid"]);
		xhs.push(row["xh"]);
	});
	jQuery.post("xspj_xspjsh.do?method=xspjshBatch&type=save", {
		shzt:shzt,
		splcid:rows[0]["splcid"],
		id:guid,
		gwids:gwid,
		xhs:xhs,
		shyj:shyj,
		fs:fs,
		ffgz:ffgz
	}, function(data) {
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				jQuery("#dataTable").reloadGrid();
			}
		});
	}, 'json');
}


/**
 * �޹�ѡ��������
 * @param fs
 * @param shyj
 * @param shzt
 * @return
 */
function xspjshPlshSave(fs,shyj,shzt){
	var map = getSuperSearch();
	map["fs"] = fs;
	map["shyj"] = shyj;
	map["shzt"] = shzt;
	jQuery.post("xspj_xspjsh.do?method=xspjshPlshSave",map,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			jQuery("#dataTable").reloadGrid();
		}});
	},'json');
}

/**
 * ��˳���
 * @return
 */
function xspjshRevoke(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
	} else {
		var splc = rows[0]["splcid"];
		var shid = rows[0]["shid"];
		var sqid = rows[0]["sqid"];
		var shzt = rows[0]["shzt"];
		showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
			jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
				// �ж��Ƿ����һ������(1:���һ�������ɹ���
				if("1" == data["cancelFlg"]){
					jQuery.post("xspj_xspjsh.do?method=xspjshRevoke",{sqid:sqid,shzt:shzt},function(result){
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

/**
 * ���̸���
 * @return
 */
function xspjshTrack(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(1!=ids.length){
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
		return false;
	}else{		
		showDialog("�������̸���",540,320,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splcid']);
	}
}
