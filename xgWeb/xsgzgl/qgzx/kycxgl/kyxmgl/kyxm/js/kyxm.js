var checkId = 'xh-xmbh-xmmc-xmsxdm-xmssdw-ytsys-kssj-jssj-yjzq';
function check() {
	
	var flag=true;
	var id = checkId.split("-");
	for ( var i = 0; i < id.length; i++) {
		var lddm = jQuery("#" + id[i]).val();
		if (lddm == null || "" == lddm) {
			
			flag= false;
		}
	}
	if(!flag){
		showAlert("�뽫��������д������");
		flag= false;
		
	}
	return flag;
}
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function xsZj(xh,xm,xymc,xydm,nj,sjhm) {
	if(null==sjhm||"null"==sjhm){
		sjhm="";
	}
	jQuery("#xh").val(xh);
	jQuery("#xmssdw").val(xydm);
	jQuery("td[name='xsxm']").html(xm);
	jQuery("td[name='xsnj']").html(nj);
	jQuery("td[name='xsxy']").html(xymc);
}
function xmmcLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='KyxmView(\""
			+ rowObject["xmid"]+"\");'>" + cellValue
			+ "</a>";
}
function KyxmView(xmid) {
	showDialog("������Ŀ�鿴", 800, 550, "qgzx_kycxkyxmgl.do?method=viewKyxm&xmid="
			+ xmid);
}

/*
 * ��Ŀ��������
 */
function ztsz() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ���õļ�¼��");
		return false;
	}
	if(rows[0]["shzt"]!='1'){
			showAlertDivLayer("ֻ�����ͨ������Ŀ�ſ���������״̬��");
			return false;
	}
	var url = 'qgzx_kycxkyxmgl.do?method=ztsz&xmid=' + rows[0]["xmid"];
	var title = "��Ŀ״̬����";
	showDialog(title, 680, 450, url);
}

function saveZtsz(type) {
	var flg = true;
	var url = "qgzx_kycxkyxmgl.do?method=ztsz&type="+type;
	ajaxSubFormWithFun("KyxmglForm", url, function(data) {
		if (data["message"] == "����ɹ���") {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		} else {
			showAlert(data["message"]);
		}
	});
}
function saveKyxm(type) {
	var flg = true;
	if (check()) {
	var url = "qgzx_kycxkyxmgl.do?method=saveKyxm&type="+type;
	ajaxSubFormWithFun("KyxmglForm", url, function(data) {
		if (data["message"] == "����ɹ���") {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		} else {
			showAlert(data["message"]);
		}
	});
	}
}
function add() {
	var url = "qgzx_kycxkyxmgl.do?method=addKyxm";
	var title = "������Ŀ����";
	showDialog(title, 800, 300, url);
}
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else {
		var url = 'qgzx_kycxkyxmgl.do?method=editKyxm&xmid=' + rows[0]["xmid"];
		var title = "������Ŀ�޸�";
		showDialog(title, 800, 300, url);
	}

}

// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'){
				showAlertDivLayer("ֻ��δ�ύ�����ݲſ�ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("�Ƿ�ȷ��ɾ����ѡ�ļ�¼��", {
		"okFun" : function() {
		var url = "qgzx_kycxkyxmgl.do?method=delKyxm";
		jQuery.post(url, {
			values : ids.toString()
		}, function(data) {
			if (data["success"] == false) {
				showAlertDivLayer(data["message"]);
			} else {
				showAlertDivLayer(data["message"], {}, {
					"clkFun" : function(tag) {
						jQuery("#dataTable").reloadGrid();
					}
				});
			}
		}, 'json');
	
}});
}
}
//��Ŀ����ά��
function kyxmFywh() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	}  else if(rows[0]["shzt"]!='1'){
		showAlertDivLayer("ֻ�����ͨ������Ŀ�ſ����÷��ã�");
	}else {
		var url = "qgzx_kycxkyxmgl.do?method=kyxmFywh&xmid="+ids;
		showDialog("��Ŀ����ά��", 800, 550, url);
	}
	
}
function addXmfy() {
	var count = jQuery("#tbody_xmfy").find("tr").length;
	jQuery("tbody[name=xmfyTbody]").find("input[name=bxsj]").attr('id',"bxsj"+count);
	var html = jQuery("tbody[name=xmfyTbody]").html();
	jQuery("tbody[name=xmfyTbody]").find("input[name=bxsj]").attr('id',"");
	jQuery("#tbody_xmfy").append(html);	
	
}

function delXmfy() {
	var sjly="";
	var xhs="";
	var xh="";
	var checkbox = jQuery('input[type=checkbox]:checked[name!=th]');
	if (checkbox.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ����¼��");
		return false;
	}
	for ( var i = 0; i < checkbox.length; i++) {
	jQuery(checkbox[i]).parents("tr:eq(0)").remove();
	}
	jQuery('input[type=checkbox][name=th]').attr('checked', false);
	initFyxj();
}

function saveFywh(type){
	var flg=true;
	var fyxx= [];
	var nullsFlag=true;
	jQuery.each(jQuery("#tbody_xmfy tr"),function(i,n){
		var obj = {};
			var fymc = jQuery(n).find("td").find("input[name=fymc]").val();
			var fyje = jQuery(n).find("td").find("input[name=fyje]").val();
			var bxsj = jQuery(n).find("td").find("input[name=bxsj]").val();
			var bz = jQuery(n).find("td").find("input[name=bz]").val();
			var fylb = jQuery(n).find("select[name=fylb] option:selected").val();
			//��֤������
			if(""==fymc||null==fymc){
				nullsFlag=false;
				return false;
			}
			obj.fylb=fylb;
			obj.bxsj=bxsj;
			obj.fyje=fyje;
			obj.fymc=fymc;
			obj.bz=bz;
			fyxx.push(obj);
			
	});
	if(!nullsFlag){
		showAlertDivLayer("�뽫<span class='red'>*</span>��������д������");
		return false;
	}
	var fyxxStr = JSON.stringify(fyxx);
	jQuery("#fyxxStr").val(fyxxStr);
	var url = "qgzx_kycxkyxmgl.do?method=saveFywh"
	ajaxSubFormWithFun("KyxmglForm", url, function(data) {
		 if(data["message"]=="����ɹ���"){
    		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
    	 }else{
    		 showAlert(data["message"]);
    		}
		});
}
