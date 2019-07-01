function changeTab(obj,tabName){
    jQuery("#tabName").val(tabName);
    if("1" == tabName){
    	jQuery("#zj").css("display","none");
    	jQuery("#xg").css("display","none");
    	jQuery("#sc").css("display","none");
    	
    	jQuery("#dc_Nlsy").css("display","");
    	jQuery("#dc_Szsz").css("display","none");
    	var map = getSuperSearch();
    	gridSetting1["params"] = map;
    	jQuery("#dataTable").initGrid(gridSetting1);
    }else{
    	jQuery("#zj").css("display","");
    	jQuery("#xg").css("display","");
    	jQuery("#sc").css("display","");
    	
    	jQuery("#dc_Nlsy").css("display","none");
    	jQuery("#dc_Szsz").css("display","");
    	var map = getSuperSearch();
    	gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
    }
    jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}


/**
 * ��ѯ
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * ���ݵ��밴ť
 * @return
 */
function xspjjgImport(){
	var url = "xspj_xspjjg.do?method=xspjjgImport";
	var title = "ѧ��������Ϣ����";
	showDialog(title, 500, 309, url);
}

/**
 * ����ģ��
 * @return
 */
function mbDownload(){
	var url = "xspj_xspjjg.do?method=downloadFile";
	window.open(url);
}

/**
 * ѡ���ļ������ļ����Ƹ�ֵ��input��
 */
function selectFiles(obj){
	var filefullpath = getFullPath(obj);
	checkFileType(obj);
}

/**
 * ��ȡinput file������
 * @param obj
 * @return
 */
function getFullPath(obj){ 
	if(obj){ 
	 if(window.navigator.userAgent.indexOf("Firefox")>=1){ 
		 if(obj.files) 
		 { 
		   return obj.files.item(0).getAsDataURL(); 
		 } 
		 return obj.value; 
	 }
	 return obj.value; 
	}
}

/**
 * �ļ����Ϳ���
 * @param obj
 * @return
 */
function checkFileType(obj){
	var type = obj.value.substr(obj.value.lastIndexOf(".")+1);
	var types = ["xls"];
	if (jQuery.inArray(type, types) == -1){
		/*����������ϴ�����,���input file��������д��������ie��chrome*/
		var file = jQuery(obj);
		file.after(file.clone().val("")); 
		file.remove(); 
		showAlert("ֻ�����ϴ�xls���͵��ļ���");
		return false;
	}
}


/**
 * �ļ����ݵ��뱣��
 * @return
 */
function importSave(){
	var url = "xspj_xspjjg.do?method=SaveDrForm";
	
	if(jQuery("#drmb").val() == "" ||��jQuery("#drmb").val() == null){
		showAlert("��ѡ�����ļ���");
		return false;
	}
	ajaxSubFormWithFun("xspjjgForm", url, function(data) {
		if(data["message"]=="����ɹ���"){
		 jQuery("#errortr").hide();
		 jQuery("#errora").attr("href","");
		 showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
			  refershParent();
			}
		 }});
   	 }else{
		 showAlert(data["message"],{},{"clkFun":function(){
		      if(data["message"] == "����ʧ��,����ϸ�˶ԡ���������.xls����"){
		    	  if(data["gid"] != "" && data["gid"] != null && data["gid"] != "null"){
		    		  jQuery("#errortr").show();
		    		  jQuery("#errora").attr("data_file","xspj_xspjjg.do?method=downloadFileError&filename="+data["gid"]);
		    	  }
		      }else{
		    	 jQuery("#errortr").hide();
		    	jQuery("#errora").attr("data_file","");
		      }
		      jQuery("#drmb").val("");
			}});
   		}
	});
}

/**
 * ����
 * @return
 */
function downloadxzmb(){
	window.open("xspj_xspjjg.do?method=downloadFile");
}

/**
 * ������������
 * @return
 */
function downloaderror(){
	var url = jQuery("#errora").attr("data_file");
	window.open(url);
}

/*����*/
function xspjjgAdd(){
	var url = "xspj_xspjjg.do?method=xspjjgAdd";
	var title = "���۽������";
	showDialog(title, 680, 490, url);
}

/**
 * ��������ֶμ���
 */
var ids = "xmmc-bmmc-xmlbdm-cysj-xh-xn-dxqdm-fjid";

/**
 * ���ӡ��޸ı���
 * @return
 */
function xspjjgSave(){
	
	var xmmc = jQuery("#xmmc").val();
	var bmmc = jQuery("#bmmc").val();
	var xmlbdm = jQuery("#xmlbdm").val();
	var cysj = jQuery("#cysj").val();
	var xh = jQuery("#xh").val();
	var xn = jQuery("#xn").val();
	var dxqdm = jQuery("#dxqdm").val();
	var fjwt = jQuery('.MultiFile-title');
	
	if("" == xmmc || null == xmmc){
		showAlert("����д��Ŀ���ƣ�");
		return false;
	}
	if("" == bmmc || null == bmmc){
		showAlert("��ѡ��ָ�����ţ�");
		return false;
	}
	if("" == xmlbdm || null == xmlbdm){
		showAlert("��ѡ����Ŀ���");
		return false;
	}
	if("" == cysj || null == cysj){
		showAlert("����д����ʱ�䣡");
		return false;
	}
	if("" == xh || null == xh){
		showAlert("����дѧ�ţ�");
		return false;
	}
	if("" == xn || null == xn){
		showAlert("��ѡ��ѧ�꣡");
		return false;
	}
	if("" == dxqdm || null == dxqdm){
		showAlert("��ѡ���ѧ�ڣ�");
		return false;
	}
	
	if(fjwt.length == 0){
		showAlert("���ϴ�������");
		return false;
	}
	var url = "xspj_xspjjg.do?method=xspjjgSave";
	ajaxSubFormWithFun("xspjjgForm", url, function(data){
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
 * �޸�
 * @return
 */
function xspjjgUpdate(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else{
		if (rows[0]["sjly"] == "�������"){
			showAlertDivLayer("����������ݲ����޸ģ�");
			return false;
		}
		var title = "�޸���������";
		var url = "xspj_xspjjg.do?method=xspjjgUpdate&guid="+rows[0]["guid"];
		showDialog(title, 680, 490, url);
	}
}

/**
 * ɾ��
 */
function xspjjgDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	if(ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='�������'){
				showAlertDivLayer("������̹����ļ�¼����ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("xspj_xspjjg.do?method=xspjjgDelete",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/*dcdlbh,�������ܱ��*/
var DCDLBH = "xspj_xspj_xspjjg.do";

/**
 * ����������������
 * @return
 */
function xspjjgExportNlsy() {
	/*DCCLBH�������ܱ��,ִ�е�������*/
	customExport(DCDLBH, xspjjgExportNlsyData);
}

/**
 * ����ִ�С�����������
 */
function xspjjgExportNlsyData() {
	/*���ø߼���ѯ����*/
	setSearchTj();
	var url = "xspj_xspjjg.do?method=xspjjgExport&dcclbh=" + DCDLBH + "&type=nlsy";
	/*���ø߼���ѯ����*/
	url = addSuperSearchParams(url);
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * ������˼�����ʡ�
 * @return
 */
function xspjjgExportSzsz() {
	/*DCCLBH�������ܱ��,ִ�е�������*/
	customExport(DCDLBH, xspjjgExportSzszData);
}

/**
 * ����ִ�С�˼�����ʡ�
 */
function xspjjgExportSzszData() {
	/*���ø߼���ѯ����*/
	setSearchTj();
	var url = "xspj_xspjjg.do?method=xspjjgExport&dcclbh=" + DCDLBH + "&type=szsz";
	/*���ø߼���ѯ����*/
	url = addSuperSearchParams(url);
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * ѧ������
 */
function xhLink(cellValue, rowObject){
	return "<a hrer='javascript:void(0);' class='name' onclick='xspjjgView(\""+rowObject["guid"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
}

/**
 * ������Ӳ鿴��ϸ��Ϣ
 * @param id
 * @param xh
 * @return
 */
function xspjjgView(guid) {
	var title = "���۽���鿴";
	var url = "xspj_xspjjg.do?method=xspjjgView&guid="+guid;
	showDialog(title,820,640,url);
}

/**
 * ��ʵ������ͳ��
 * @return
 */
function DataStatistics(){
	var url ="xspj_xspjjg.do?method=dataStatistics";
	
	var xnLength=jQuery("a[name=a_name_xn]").length;
	if(xnLength != "1"){
		showAlertDivLayer("��ѡ��һ��ѧ���ѯ������");
		return false;
	}
	setSearchTj();
	url = addSuperSearchParams(url);
	document.forms[0].action = url;
	document.forms[0].submit();
}
