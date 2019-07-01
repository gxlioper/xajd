function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

var DCCLBH = "dekt_xfsq_list.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, ExportDatas);
}

//��������
function ExportDatas() {
	setSearchTj();//���ø߼���ѯ����
	var url = "dekt_xfsq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


//ɾ��
function del() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	 }
	var flag = false;
	var ids = jQuery("#dataTable").getSeletIds();
	showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
		jQuery.post("qgzx_jtff.do?method=jtffDel",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
	
}

//����ҳ����ת
function add(){
	showDialog('������������',600,450,'qgzx_jtff.do?method=jtffAdd');
}


//�༭ҳ����ת
function edit(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		showDialog('���������޸�',600,450,'qgzx_jtff.do?method=jtffEdit&id=' + rows[0]["id"]);
	}
}

//����
function save(url,checkId) {
	if (!checkNull(checkId)) {
		return false;
	}
	lock();
	jQuery("#form").ajaxSubmit( {
		url : url,
		type : "post",
		dataType : "json",
		success : function(data) {
			if (data["success"] == "false"){
				showAlert(data["message"]);
			} else {
				showAlert(data["message"], {}, {	"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}});
			}
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8"
	});
	unlock();
}

//����
function importConfig(){
	toImportDataNew("IMPORT_JTFF");
	return false;
}

//���Ի�����
function xfsqZdyImport(){
    var drmkdm = 'IMPORT_DEKT_XFSQ';
    var url = "dekt_xfsq.do?method=xfsqZdyImport" + "&drmkdm=" + drmkdm;
    showDialog('����',720,580,url,{close:function(){
        if (jQuery("#search_go")){
            jQuery("#search_go").click();
        }
    }});
    return false;
}

/**
 * �ύ
 */
function submitBusi() {
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
        return false;
    }
    var rows = jQuery("#dataTable").getSeletRow();
    var xh = rows[0]["xh"];
    if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
        showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
        return false;
    }

    showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
        "okFun" : function() {
            jQuery.post("dekt_xfsq.do?method=xfSqSubmit", {
                sqid : ids.toString(),
				xh:xh
            }, function(data) {
                showAlertDivLayer(data["message"]);
                jQuery("#dataTable").reloadGrid();
            }, 'json');
        }
    });
}

function loadRdnrbz(){
	jQuery.ajaxSetup( {
		async : false
	});
	jQuery("#rdnrbz").empty();
	jQuery("#dj").empty();
	var lx=jQuery('#lx').val();
	var rdxm=jQuery('#rdxm').val();
	var option = "<option value=''></option>";
	if(rdxm!=null&&rdxm!=""){
		jQuery.post('dekt_xfsq.do?method=getRdnrbzList', {lx:lx,rdxm:rdxm},	function(data) {
			if (data != null && data.length != 0) {
				for ( var i = 0; i < data.length; i++) {
					option +="<option value='"+data[i].rdnrbz+"'>"	+data[i].rdnrbz+"</option>";
				}
			}
		},'json');
	}
	jQuery('#rdnrbz').append(option);
	jQuery.ajaxSetup( {
		async : true
	});
}

function loadDj() {
	jQuery.ajaxSetup( {
		async : false
	});
	jQuery("#dj").empty();
	var lx=jQuery('#lx').val();
	var rdxm=jQuery('#rdxm').val();
	var rdnrbz=jQuery('#rdnrbz').val();
	var option = "<option value=''></option>";
	if(rdnrbz!=null&&rdnrbz!=""){
		jQuery.post('dekt_xfsq.do?method=getDjList', {lx:lx,rdxm:rdxm,rdnrbz:rdnrbz},	function(data) {
			if (data != null && data.length != 0) {
				for ( var i = 0; i < data.length; i++) {
					option +="<option value='"+data[i].dj+"'>"+data[i].dj+"</option>";
				}
			}
		},'json');
	}
	jQuery('#dj').append(option);
	jQuery.ajaxSetup( {
		async : true
	});
}


function viewXfsq(sqid, xh) {
	showDialog("��˲�ѯ", 700, 480, "dekt_xfsq.do?method=xfsqView&sqid=" + sqid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewXfsq(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue	+ "</a>";
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