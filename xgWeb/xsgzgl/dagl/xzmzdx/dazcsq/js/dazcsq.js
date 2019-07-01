/**
 * ��ѯ
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * ����ת������
 * @return
 */
function dazcsqApply(){
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
		return false;
	}
	if ("false" == isopen){
		showAlertDivLayer("��ǰδ����ѧ������ת�����룬����ϵ����Ա��");
		return false;
	}
	var url = "dagl_dazcsq.do?method=dazcsqApply";
	var title = "����ת������";
	showDialog(title, 680, 375, url);
}

/**
 * �޸�
 */
function dazcsqUpdate(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else{
		if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3"){
			showAlertDivLayer("ֻ���޸�δ�ύ�������˻صļ�¼��");
			return false;
		}
		var title = "����ת���޸�";
		var url = "dagl_dazcsq.do?method=dazcsqUpdate&sqid="+rows[0]["sqid"];
		showDialog(title, 680, 375, url);
	}
}

/**
 * ת����ʽonchange�¼�
 * @return
 */
function changeZcfs(){
	var zcfs = jQuery("input[name='zcfs']:checked").val();
	if("1" == zcfs){
		/*�����ֶ�*/
		jQuery("#mailedOne").show();
		jQuery("#mailedTwo").show();
		jQuery("#mailedThree").show();
		jQuery("#byoOne").hide();
		
		/*���ֵ*/
		jQuery("#zddacn").attr("checked", false);
		jQuery("#yqtdrq").val("");
	}else{
		/*�����ֶ�*/
		jQuery("#mailedOne").hide();
		jQuery("#mailedTwo").hide();
		jQuery("#mailedThree").hide();
		jQuery("#byoOne").show();
		
		/*���ֵ*/
		jQuery("#yjdz").val("");
		jQuery("#yzbm").val("");
		jQuery("#sjr").val("");
		jQuery("#sjrdh").val("");
		jQuery("#dwmc").val("");
		jQuery("#dwdz").val("");
	}
}

/**
 * ����
 * @param saveFlag
 * @return
 */
function dazcsqApplySave(saveFlag){
	
	var xh = jQuery("#xh").val();//ѧ��
	var zcfs = jQuery("input[name='zcfs']:checked").val();//ת����ʽ
	var yjdz = jQuery("#yjdz").val();//�ʼĵ�ַ
	var yzbm = jQuery("#yzbm").val();//��������
	var sjr = jQuery("#sjr").val();//�ռ���
	var sjrdh = jQuery("#sjrdh").val();//�ռ��˵绰
	var dwmc = jQuery("#dwmc").val();//��λ����
	var dwdz = jQuery("#dwdz").val();//��λ��ַ
	var zddacn = jQuery("input:checkbox[name='zddacn']:checked").val();//�Դ�������ŵ
	var yqtdrq = jQuery("#yqtdrq").val();//Ԥ���ᵵ����
	var ids = null;
	
	if(xh == null || xh == ''){
		showAlert("������ѡ��һ��ѧ����");
		return false;
	}
	
	if(zcfs == null || zcfs == ''){
		showAlert("����ѡ�񵵰�ת����ʽ��");
		return false;
	}
	if("1" == zcfs){
		ids = "yjdz-yzbm-sjr-sjrdh-dwmc-dwdz";
		/*��֤�����ֶ��Ƿ�δ��д*/
		if(!checkNotNull(ids)){
			showAlert("�뽫��<font class='red'>*</font>����Ŀ��д������");
			return false;
		}
	}else{
		if(zddacn == null || zddacn == ''){
			showAlert("���Ƚ��ܵ���ת��Э�飡");
			return false;
		}
		ids = "yqtdrq";
		/*��֤�����ֶ��Ƿ�δ��д*/
		if(!checkNotNull(ids)){
			showAlert("�뽫��<font class='red'>*</font>����Ŀ��д������");
			return false;
		}
	}
	
	var url = "dagl_dazcsq.do?method=dazcsqApplySave&saveFlag=" + saveFlag;
	ajaxSubFormWithFun("dazcsqForm", url, function(data){
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

/**
 * �鿴
 * @return
 */
function dazcsqView(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
	}else{
		var url = 'dagl_dazcsq.do?method=dazcsqView&sqid='+ rows[0]["sqid"];				
		var title = "����ת���鿴";
		showDialog(title, 680, 440, url);
	}
}

/**
 * ѧ������
 * @param cellValue
 * @param rowObject
 * @return
 */
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='dazcsqViewClick(\""+rowObject["sqid"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}

function dazcsqViewClick(sqid){
	var title = "����ת���鿴";
	var url = "dagl_dazcsq.do?method=dazcsqView&sqid="+sqid;
	showDialog(title, 680, 440, url);
}

/**
 * ɾ��
 * @return
 */
function dazcsqDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		for(var i=0;i<ids.length;i++){
			if (rows[i]["shzt"] != "0"){
				showAlertDivLayer("ֻ��ɾ��δ�ύ�ļ�¼��");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("dagl_dazcsq.do?method=dazcsqDelete",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


/**
 * �ύ��֧��������
 * @return
 */
function dazcsqSubmit(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(0 == ids.length){
		showAlertDivLayer("��ѡ����Ҫ�ύ�ļ�¼");
	}else{
		for(var i=0;i<rows.length;i++){
			if("0" != rows[i]["shzt"] && "3" != rows[i]["shzt"]){
				showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
			var tips = submitLoading();
			try{
				tips.show();
			}catch(e){
				
			}
			jQuery.post("dagl_dazcsq.do?method=dazcsqSubmit",{values:ids.toString()},function(data){
				tips.close();
				showAlertDivLayerSize(data["message"], 340, 200);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * ����
 * @return
 */
function dazcsqRevoke() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	}else if (ids.length > 1){
		showAlertDivLayer("ֻ��ѡ��һ����¼���г�����");
	}else{
		if (rows[0]['shzt'] != '5') {
			showAlertDivLayer("ֻ�ܳ������״̬Ϊ������С��ļ�¼��");
			return false;
		}
		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("dagl_dazcsq.do?method=dazcsqRevoke", {
					values : ids.toString(),
					splcid : rows[0]['splcid']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

/**
 * ���̸���
 * @return
 */
function dazcsqTrack(){
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = rows[0]["shzt"];
	if(rows.length != 1){
		showAlertDivLayer("��ѡ��һ����¼��");
	}else{
		if(shzt == "0"){
			showAlertDivLayer("�����������Ϣ��");
			return false;
		}
		showDialog("���̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splcid']);
	}
}

/*dcdlbh,�������ܱ��*/
var DCDLBH = "xsxx_dagl_dazcsq.do";

/**
 * ����
 * @return
 */
function dazcsqExport() {
	/*DCCLBH�������ܱ��,ִ�е�������*/
	customExport(DCDLBH, dazcsqExportData);
}

/**
 * ����ִ��
 */
function dazcsqExportData() {
	/*���ø߼���ѯ����*/
	setSearchTj();
	var url = "dagl_dazcsq.do?method=dazcsqExport&dcclbh=" + DCDLBH;
	/*���ø߼���ѯ����*/
	url = addSuperSearchParams(url);
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}