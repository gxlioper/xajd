//�л�������/�Ѵ���ҳ��
function selectTab(obj, shzt) {
	jQuery("#shzt").val(shzt);
	if (shzt == "dsh") {
		jQuery("#li_sh").css("display", "");
		jQuery("#li_qx").css("display", "none");
		jQuery("#title").html("��У��ѵ������б�");
		var map = getSuperSearch();
		map["shzt"]="dsh";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_sh").css("display", "none");
		jQuery("#li_qx").css("display", "");
		jQuery("#title").html("��У��ѵ������б�");
		var map = getSuperSearch();
		map["shzt"]="ysh";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}

	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");

	searchRs();
}
function searchRs() {
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	if (shzt != "") {
		map["shzt"] = shzt;
	}
	jQuery("#dataTable").reloadGrid(map);
}
function reload() {
	jQuery("#dataTable").reloadGrid();
}
// ��������ת
function dcmcLink(cellValue, rowObject) {
	var sqid = rowObject["sqid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + sqid+ "')\" class='name'>" + cellValue + "</a>";
}
// �鿴��Ϣ
function ckxx(sqid) {
	var query = jQuery("#query").val();
	var url = "dtjs_dxbmgl_dxbmshCk.do?sqid=" + sqid;
	var title = "��У��ѵ������Ϣ";
	showDialog(title, 700, 440, url);
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
	var text="ͨ��";
	if(shzt=="2"){
		text="��ͨ��";
	}else if(shzt=="3"){
		text="�˻�";
		zx(shzt);
		return false;
	}
	//�ύ���
	showConfirmDivLayer("��ȷ��<font color='red'>[" + text + "]</font>��������",{"okFun":function(){
			zx(shzt,text);
		}});
	
}
function zx(shzt,text){
	var url = "dtjs_dxbmgl_dxbmshBc.do?shzt="+shzt+"&tt="+new Date();
	lock();
	jQuery("#form").ajaxSubmit( {
		url : url,
		type : "post",
		dataType : "json",
		success : function(data) {
			if (data||data=='true') {
				showAlert("<font color='red'>["+text+"]</font>�����ɹ���", {}, {
					"clkFun" : function() {
						if (parent.window) {
							refershParent();
						}
					}
				});
			} else {
				showAlert("<font color='red'>[+"+text+"]</font>����ʧ�ܣ�");
			}
			unlock();
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8"
	});
}
/**
 * ��֤�Ƿ���ڿ���
 * 
 * @param ids
 *            Ҫ��֤�Ŀؼ�id "-"�ָ�
 * @return
 */
function check(ids) {
	var id = ids.split("-");
	for ( var i = 0; i < id.length; i++) {
		var lddm = jQuery("#" + id[i]).val();
		if (lddm == null || "" == lddm) {
			return false;
		}
	}
	return true;
}
//���
function dtxxsh() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ��˼�¼��");
	} else if (rows.length == 1){
		var xh = rows[0]["xh"];
		var url ='dtjs_dxbmgl_dxbmshSh.do?xh=' + xh + '&sqid='+ rows[0]["sqid"]+"&gwid="+rows[0]["gwid"];
		showDialog("������ѵ��Ϣ���",700,480,url);
	}
}
//�������
function cancelSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
			jQuery.post("dtxxsh.do?method=cancelSh",
				{
				 qjsqid:rows[0]["qjsqid"],
				 gwid:rows[0]["gwid"],
				 shr:rows[0]["shr"],
				 splcid:rows[0]["splcid"]
				},
				function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},
			'json');
		}});
	}
}
function rcxwshLcinfo() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {
		showDialog("�����������̸���", 600, 400, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splc']);
	}
}
//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport('dxbmgl_dxbmsh.do',exportData);
}

// ��������
function exportData(dcclbh) {
	var shlx = jQuery("#shzt").val();
	setSearchTj();//���ø߼���ѯ����
	var url = "dtjs_dxbmgl_dxbmshDc.do?dcclbh=" + dcclbh + "&shlx=" + shlx;//dcclbh,�������ܱ��
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
	var dtxxsqid = new Array();
	var gwid = new Array();
	var xhs = new Array();
	var splcs = new Array();
	jQuery.each(rows,function(i,row){
		dtxxsqid.push(row["dtxxsqid"]);
		gwid.push(row["gwid"]);
		xhs.push(row["xh"]);
		splcs.push(row["splc"]);
	});
	jQuery.post(
		"dtxxsh.do?method=savePlsh",
		{
		 shzt:shzt,
		 ids:dtxxsqid,
		 gwids:gwid,
		 xhs:xhs,
		 splcs:splcs,
		 shyj:shyj
		},function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				jQuery("#dataTable").reloadGrid();
			}});
		},
		'json'
	);
	function cxshnews_splc(obj){
		var sfkq=obj.data.sfkq;
		var rows = jQuery("#dataTable").getSeletRow();
		if(sfkq=="1"){//���� �����һ���ɳ���
			if (rows.length != 1){
				alertInfo("��ѡ��һ����Ҫ������˵ļ�¼��");
			} else {
				splc_cx_news(rows[0][obj.data.splc],rows[0]["shid"],rows[0][obj.data.dtxxsqid]);
			}
		}else{
			splc_cxs(rows[0][obj.data.splc],rows[0]["shid"],rows[0][obj.data.dtxxsqid]);

		}
	}
	/*
	 * �������̳���[���һ���ɳ���]
	 * shid ���id
	 * splc ��������id 
	 */
	function splc_cx_news(splc,shid,dtxxsqid){
		//���һ��������˺���õ�·��
		var cancelPath = jQuery("#cancelPath").val();
		confirmInfo("��ȷ��Ҫ����������?",function(ty){
			if(ty=="ok"){
				jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid,dtxxsqid:dtxxsqid},function(data){
						// �ж��Ƿ����һ������(1:���һ�������ɹ���
						if("1" == data["cancelFlg"]){
							jQuery.post(cancelPath,{splc:splc,shid:shid,dtxxsqid:dtxxsqid},function(result){
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
			}
		});
	}

	/*
	 * �������̳���
	 * shid ���id
	 * splc ��������id 
	 */
	function splc_cxs(splc,shid,dtxxsqid){
		confirmInfo("��ȷ��Ҫ����������?",function(ty){
			//alert(ty);
			if(ty=="ok"){
				jQuery.post("comm_spl.do?method=cxsh",{shlc:splc,shid:shid,dtxxsqid:dtxxsqid},function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						//if (parent.window){
							//refersh();
							jQuery("#dataTable").reloadGrid();
						//}
					}});
				},'json');
			}
		});
	}
}
