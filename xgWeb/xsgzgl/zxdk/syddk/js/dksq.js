/**
 * ��д�����
 */
function editSqb(){
	showDialog("��д�����",800,500,"zxdk_sydk.do?method=dksq");
}

function saveDksq(url){
	
	var xxdm = jQuery("#xxdm").val();
	var yhdm = jQuery("#yhdm").val();
	var hzjym = jQuery("#hzjym").val();
	
	if("10335"==xxdm&&"���ҿ�������"==yhdm.trim()&&""==hzjym.trim()){
		showAlertDivLayer("���ҿ������б�����д��ִ��");
		return false;
	}
	//checkNull("xh-xn-yhdm-dkje-dkqx-zsysf-xfysf-dkkssj-sqly")�����Ի��ϳ�
	if("10335"==xxdm&&!checkNull("xh-xn-yhdm-dkje-sqly")){
		return false;
	}else if("10335"!=xxdm&&!checkNull("xh-xn-dkje-dkqx-sqly-zsysf-xfysf-yhdm-htbh-dkkssj-hzjym")){
		return false;
	}
	
	/*
	var dkzs=jQuery("#dkje").val();
	
	var zsys = jQuery("#zsysf").val();
	
	var xfys = jQuery("#xfysf").val();
	
	if(parseInt(dkzs)<(parseInt(zsys)+parseInt(xfys))){
		showAlertDivLayer("Ӧ�շѴ��ڴ����ܽ��!");
		jQuery("#dkje").focus();
			return false;
		}
	*/
	var xh=jQuery("#xh").val();
	var xn=jQuery("#xn").val();
	
	jQuery.post("zxdk_sydk.do?method=isExitsByXhAndXn",{xh:xh,xn:xn},function(data){
		if(data=="true"){
			showAlertDivLayer("��ѧ����ѧ����������߽�����Ѵ���");
			return false;
		}else{
			ajaxSubFormWithFun("zxdkSydksqshForm",url,function(data){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					refershParent();
				}});
			});
		}
	 });
}

function saveXgDksq(url){
	
	var xxdm = jQuery("#xxdm").val();
	var yhdm = jQuery("#yhdm").val();
	var hzjym = jQuery("#hzjym").val();
	
	if("10335"==xxdm&&"���ҿ�������"==yhdm.trim()&&""==hzjym.trim()){
		showAlertDivLayer("���ҿ������б�����д��ִ��");
		return false;
	}
	
	if("10335"==xxdm&&!checkNull("xh-xn-yhdm-dkje-dkqx-zsysf-xfysf-dkkssj-sqly")){
		return false;
	}else if("10335"!=xxdm&&!checkNull("xh-xn-dkje-dkqx-sqly-zsysf-xfysf-yhdm-htbh-dkkssj-hzjym")){
		return false;
	}
	/*
	var dkzs=jQuery("#dkje").val();
	
	var zsys = jQuery("#zsysf").val();
	
	var xfys = jQuery("#xfysf").val();
	
	if(parseInt(dkzs)<(parseInt(zsys)+parseInt(xfys))){
		showAlertDivLayer("Ӧ�շѴ��ڴ����ܽ��!");
		jQuery("#dkje").focus();
			return false;
		}
	*/
	var xh=jQuery("#xh").val();
	var xn=jQuery("#xn").val();
	
    ajaxSubFormWithFun("zxdkSydksqshForm",url,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			refershParent();
		}});
	});
}

/**
 * ��ѯ
 */
function searchRs(){
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
 * �޸������
 * @returns {Boolean}
 */
function xgSqb(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
			showAlertDivLayer("��������������У������޸ģ�");
			return false;
		}
		showDialog("�޸������",800,500,"zxdk_sydk.do?method=xgDksq&id="+rows[0]["id"]);
	}
}

//�ύ
function submitBusi(){
	
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (ids.length != 1){
		showAlertDivLayer("��ѡ����Ҫ�ύ�ļ�¼����");
	}
	
	if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
		showAlertDivLayer("��ѡ��ļ�¼�Ѿ�������У������ظ��ύ��");
		return false;
	}
	jQuery.post("zxdk_sydk.do?method=submit",{id:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
}


function cancelSubmit(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("��ѡ����Ҫȡ������ļ�¼��");
	} else {
		jQuery.post("zxdk_sydk.do?method=cancelSubmit",{id:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}
}


/**
 * ȡ������
 */
function qxsq(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫȡ������ļ�¼��");
	} else if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
		showAlertDivLayer("��ѡ��ļ�¼����ˣ�����ɾ����");
		return false;
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ����������",{"okFun":function(){
			jQuery.post("zxdk_sydk.do?method=delDksq",{id:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * ���̸���
 */
function viewLcgz(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����¼��");
	} else {
		showDialog("���̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['id']+"&splc="+rows[0]['splcid']);
	}
	
}

function selectTab(obj,zt){
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	jQuery("#shzt").val(zt);
	
	if (zt == "dsh"){
		jQuery("#li_sh").css("display","");
		jQuery("#li_qx").css("display","none");
		var map = getSuperSearch();
		map["shzt"]="dsh";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_sh").css("display","none");
		jQuery("#li_qx").css("display","");
		var map = getSuperSearch();
		map["shzt"]="ysh";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
}


function showAuding() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlert("��ѡ��һ����Ҫ��˵ļ�¼��");
		return false;
	} else if(rows.length == 1){
		var id = rows[0]["id"];
		var url = "zxdk_sydk.do?method=dksh&id="+id;
		showDialog("��Դ�������",800,500,url);
	} else if(jQuery("#xxdm").val() == "10335" && rows.length > 0){
		//�㽭��ѧ���Ի��жϣ���ת����������˽���
		showDialog("�������", 500, 250, "zxdk_sydk.do?method=sydPlsh");
	} else{
		showAlert("��ѡ��һ����Ҫ��˵ļ�¼��");
		return false;
	}
}

/**
 * �鿴�����
 * @param id
 */
function ckSqb(id){
	showDialog("�鿴�����",800,500,"zxdk_sydk.do?method=ckDksq&id="+id);
}

//����
function exportConfig(){
	var DCCLBH='zxdk_sydk_dksq.do';
	customExport(DCCLBH, exportData);
}

function exportData(){
	var DCCLBH='zxdk_sydk_dksq.do';
	setSearchTj();//���ø߼���ѯ����
	
	var url = "zxdk_sydk.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function savePlsh(shzt, shyj) {
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var xhs = new Array();

	jQuery.each(rows, function(i, row) {
		guid.push(row["id"]);
		gwid.push(row["xtgwid"]);
		xhs.push(row["xh"]);
	});
	jQuery.post("zxdk_sydk.do?method=sydPlsh&type=save", {
		shzt : shzt,
		splcid : rows[0]["splcid"],
		ids : guid,
		gwids : gwid,
		xhs : xhs,
		shyj : shyj,
	}, function(data) {
		
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				jQuery("#dataTable").reloadGrid();
			}
		});
	}, 'json');
}