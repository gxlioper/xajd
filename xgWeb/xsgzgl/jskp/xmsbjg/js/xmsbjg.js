/**�л�����������˼������*/
function changeTab(obj,tabName){
    jQuery("#tabName").val(tabName);
    if("1" == tabName){
    	jQuery("#li_dr").css("display","none");
    	jQuery("#li_exportOne").css("display","");
    	jQuery("#li_exportTwo").css("display","none");
    	var map = getSuperSearch();
    	gridSetting1["params"] = map;
    	jQuery("#dataTable").initGrid(gridSetting1);
    }else{
    	jQuery("#li_dr").css("display", "");
    	jQuery("#li_exportOne").css("display","none");
    	jQuery("#li_exportTwo").css("display","");
    	var map = getSuperSearch();
    	gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
    }
    jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**���ѧ�Ų鿴*/
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='XmsbjgView(\""
			+ rowObject["jgid"] + "\");'>" + cellValue
			+ "</a>";
}

// ����
function saveXmsbjg(type) {
	var xmid = jQuery("#xmid").val();
	if (jQuery("#hjsj").val() == ""||jQuery("#hjsj").val() == null||jQuery("#sbly").val() == ""||jQuery("#sbly").val() == null||
			xmid==null||xmid=="") {
		showAlert("�뽫��������д������");
		return false;
	}
	var url = "jskpXmjg.do?method=saveXmsbjg&type=" + type;
	ajaxSubFormWithFun("jskpXmsbjgForm", url, function(data) {
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
//����
function add() {
	var url = "jskpXmjg.do?method=addXmsbjg";
	var title = "�걨�����д";
	showDialog(title, 750, 550, url);
}
function checkXmxx(){
	if(jQuery("#xmid").val()==""){
		showAlert("����ѡ���걨��Ŀ��");
		return false;
	}
}
function showXmxxCallBack(rowData){
	var xmid = rowData.xmid;
	var xmmc = rowData.xmmc;
	jQuery("#xmid").val(xmid);
	jQuery("#xmmc").val(xmmc);
}
//�޸�
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		if(rows[0]['sjly']=='1'){
			showAlertDivLayer("������̹����ļ�¼�����޸ģ�");
			return false;
		}
		var url = 'jskpXmjg.do?method=editXmsbjg&jgid=' + rows[0]["jgid"];
		var title = "�걨����޸�";
		showDialog(title, 750, 550, url);
	}
}
//�鿴
function XmsbjgView(id, xh) {
	showDialog("�걨����鿴", 750, 550, "jskpXmjg.do?method=viewXmsbjg&jgid="
			+ id);
}
// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sbly']=='1'){
				showAlertDivLayer("������̹����ļ�¼����ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("jskpXmjg.do?method=delXmsbjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "jskp_xmjg.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, mrgzkhXmsbjgExportData);
}

//��������
function mrgzkhXmsbjgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "jskpXmjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/*��ʵ������ͳ��*/
function DataStatistics(){
	var url ="jskpXmjg.do?method=dataStatistics";
	
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


/**================˼�����ʽ�����뿪ʼ================*/
/*˼�����ʽ������*/
function szszDataImport(){
	var url = "jskpXmjg.do?method=szszDataImport";
	var title = "����";
	showDialog(title, 770, 350, url);
}

/*����ģ������*/
function downloadxzmb(){
	window.open("jskpXmjg.do?method=downloadFile");
}

/*���ļ����Ƹ�ֵ��input��*/
function selectFiles(obj){
	var filefullpath = getFullPath(obj);
	checkFileType(obj);
}

/*��ȡinput file������*/
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

/*�ļ����Ϳ���*/
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

/*���뱣��*/
function saveRr(){
	var url = "jskpXmjg.do?method=SaveDrForm";
	
	if(jQuery("#drmb").val() == "" ||��jQuery("#drmb").val() == null){
		showAlert("��ѡ�����ļ���");
		return false;
	}
	ajaxSubFormWithFun("jskpXmsbjgForm", url, function(data) {
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
		    		  jQuery("#errora").attr("data_file","jskpXmjg.do?method=downloadFileError&filename="+data["gid"]);
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

/*������������*/
function downloaderror(){
	var url = jQuery("#errora").attr("data_file");
	window.open(url);
}
/**================˼�����ʽ���������================*/


/*dcdlbh,�������ܱ��*/
var DCDLBH = "jskp_szszjg.do";
/*�Զ��嵼�� ����*/
function szszExport() {
	/*DCCLBH�������ܱ��,ִ�е�������*/
	customExport(DCDLBH, szszExportData);
}

/*��������*/
function szszExportData() {
	/*���ø߼���ѯ����*/
	setSearchTj();
	var url = "jskpXmjg.do?method=szszExportData&dcclbh=" + DCDLBH;
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
