/**
 * ��ѯ
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * ����
 * @return
 */
function dazcjgAdd(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�Ǽǵļ�¼��");
	}else{
		if("δ�Ǽ�" != rows[0]["dazcxxmc"]){
			showAlertDivLayer("ֻ������δ�Ǽǵļ�¼��");
			return false;
		}
		var url = "dagl_dazcjg.do?method=dazcjgAdd&xh=" + rows[0]["xh"];
		var title = "����ת������";
		showDialog(title, 680, 480, url);
	}
}

/**
 * �޸�
 * @return
 */
function dazcjgUpdate(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else{
//		if("1" == rows[0]["sjly"]){
//			showAlertDivLayer("����������ݲ����޸ģ�");
//			return false;
//		}
		if("δ�Ǽ�" == rows[0]["dazcxxmc"]){
			showAlertDivLayer("δ�Ǽǵļ�¼�����޸ģ�");
			return false;
		}
		var title = "����ת���޸�";
		var url = "dagl_dazcjg.do?method=dazcjgUpdate&guid="+rows[0]["guid"];
		showDialog(title, 680, 480, url);
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
		jQuery("#mailedFour").show();
		jQuery("#mailedFive").hide();
		jQuery("#mailedSix").hide();
		jQuery("#byoOne").hide();
		jQuery("#byoTwo").hide();
	}else{
		/*�����ֶ�*/
		jQuery("#mailedOne").hide();
		jQuery("#mailedTwo").hide();
		jQuery("#mailedThree").hide();
		jQuery("#mailedFour").hide();
		jQuery("#mailedFive").hide();
		jQuery("#mailedSix").hide();
		jQuery("#byoOne").show();
		jQuery("#byoTwo").show();
	}
	/*���ֵ*/
	jQuery("#yjdz").val("");
	jQuery("#yzbm").val("");
	jQuery("#sjr").val("");
	jQuery("#sjrdh").val("");
	jQuery("#dwmc").val("");
	jQuery("#dwdz").val("");
	jQuery("#yjzt").attr("checked", false);
	jQuery("#kdfs").val("");
	jQuery("#kddh").val("");
	jQuery("#yjsj").val("");
	jQuery("#zddacn").attr("checked", false);
	jQuery("#yqtdrq").val("");
	jQuery("#sjtdrq").val("");
	jQuery("#sjtdr").val("");
}

/**
 * �ʼ�״̬onchange�¼�
 * @return
 */
function changeYjzt(){
	var yjzt = jQuery("input[name='yjzt']:checked").val();
	if('1' == yjzt){
		jQuery("#mailedFive").show();
		jQuery("#mailedSix").show();
	}else{
		jQuery("#mailedFive").hide();
		jQuery("#mailedSix").hide();
		jQuery("#kdfs").val("");
		jQuery("#kddh").val("");
		jQuery("#yjsj").val("");
	}
}

/**
 * ���ӡ��޸ı���
 * @return
 */
function dazcjgSave(){
	
	var xh = jQuery("#xh").val();//ѧ��
	var zcfs = jQuery("input[name='zcfs']:checked").val();//ת����ʽ
	var yjdz = jQuery("#yjdz").val();//�ʼĵ�ַ
	var yzbm = jQuery("#yzbm").val();//��������
	var sjr = jQuery("#sjr").val();//�ռ���
	var sjrdh = jQuery("#sjrdh").val();//�ռ��˵绰
	var dwmc = jQuery("#dwmc").val();//��λ����
	var dwdz = jQuery("#dwdz").val();//��λ��ַ
	var kdfs = jQuery("#kdfs").val();//��ݷ�ʽ
	var kddh = jQuery("#kddh").val();//��ݵ���
	var yjsj = jQuery("#yjsj").val();//�ʼ�ʱ��
	var yjzt = jQuery("input[name='yjzt']:checked").val();//�ʼ�״̬
	var zddacn = jQuery("input:checkbox[name='zddacn']:checked").val();//�Դ�������ŵ
	var yqtdrq = jQuery("#yqtdrq").val();//Ԥ���ᵵ����
	var sjtdrq = jQuery("#sjtdrq").val();//ʵ���ᵵ����
	var sjtdr = jQuery("#sjtdr").val();//ʵ���ᵵ��
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
		if(yjzt == null || yjzt == ''){
			showAlert("����ѡ���ʼ�״̬��");
			return false;
		}
		
		if('1' == yjzt){
			if(kdfs == null || kdfs == ''){
				showAlert("��������д��ݷ�ʽ��");
				return false;
			}
			if(kddh == null || kddh == ''){
				showAlert("��������д��ݵ��ţ�");
				return false;
			}
			if(yjsj == null || yjsj == ''){
				showAlert("��������д�ʼ�ʱ�䣡");
				return false;
			}
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
	
	var url = "dagl_dazcjg.do?method=dazcjgSave";
	ajaxSubFormWithFun("dazcjgForm", url, function(data){
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
 * ɾ��
 * @return
 */
function dazcjgDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	if(ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['dazcxxmc']=='δ�Ǽ�'){
				showAlertDivLayer("δ�Ǽǵļ�¼����ɾ����");
				return false;
			}
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("������̹����ļ�¼����ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("dagl_dazcjg.do?method=dazcjgDelete",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * ѧ������
 */
function xhLink(cellValue, rowObject){
	return "<a hrer='javascript:void(0);' class='name' onclick='dazcjgView(\""+rowObject["xh"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
}

/**
 * ������Ӳ鿴��ϸ��Ϣ
 * @param id
 * @param xh
 * @return
 */
function dazcjgView(xh) {
	var title = "����ת���鿴";
	var url = "dagl_dazcjg.do?method=dazcjgView&xh="+xh;
	showDialog(title, 660, 430, url);
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
		var url = 'dagl_dazcjg.do?method=dazcjgView&guid='+ rows[0]["guid"]+"&xh="+rows[0]["xh"];	
		var title = "����ת���鿴";
		showDialog(title, 660, 390, url);
	}
}

/*dcdlbh,�������ܱ��*/
var DCDLBH = "xsxx_dagl_dazcjg.do";

/**
 * ����
 * @return
 */
function dazcjgExport() {
	/*DCCLBH�������ܱ��,ִ�е�������*/
	customExport(DCDLBH, dazcjgExportData);
}

/**
 * ����ִ��
 */
function dazcjgExportData() {
	/*���ø߼���ѯ����*/
	setSearchTj();
	var url = "dagl_dazcjg.do?method=dazcjgExport&dcclbh=" + DCDLBH;
	/*���ø߼���ѯ����*/
	url = addSuperSearchParams(url);
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * ����
 * @return
 */
function dazcjgImport(){
	toImportDataNew("IMPORT_N712604_DAZCJG");
	return false;
}