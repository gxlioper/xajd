function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//�׶�ά������
function jdwhsz(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		if(rows[0]['rdzt']=='���϶�'){
			showAlertDivLayer("����Ŀѧ�����϶������ܽ��в�����");
			return false;
		}
		if(rows[0]['csmsmc'] == '����'){
			var url = "grttxm_jdsz.do?method=getJdszGrList&xmmc="+rows[0]['xmmc']+"&jdmc="+rows[0]['jdmc']+"&jdid="+rows[0]['jdid']+"&xmdm="+rows[0]['xmdm'];
			document.location.href = url;
		}else{
			var url = "grttxm_jdsz.do?method=getJdszTtList&xmmc="+rows[0]['xmmc']+"&jdmc="+rows[0]['jdmc']+"&jdid="+rows[0]['jdid']+"&xmdm="+rows[0]['xmdm'];
			document.location.href = url;
		}
		
	}
	
}

/**
 * xmmc�鿴
 * @return
 */
function xmmcLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='xmmcView(\""
	+ rowObject["jdmc"] + "\",\"" + rowObject["jdid"] + "\",\"" + rowObject["xmdm"] + "\",\"" + rowObject["csmsmc"] + "\",\"" + cellValue + "\");'>" + cellValue
	+ "</a>";
}

//�鿴ѧ��ajaxurl��ת
function xmmcView(jdmc,jdid,xmdm,csmsmc,xmmc) {
	if(csmsmc == '����'){
		var url = "grttxm_jdsz.do?method=getJdszGrList&xmmc="+xmmc+"&jdmc="+jdmc+"&jdid="+jdid+"&xmdm="+xmdm+"&flag=view";
		showDialog("�鿴", 770, 450, url);
	}else{
		var url = "grttxm_jdsz.do?method=getJdszTtList&xmmc="+xmmc+"&jdmc="+jdmc+"&jdid="+jdid+"&xmdm="+xmdm+"&flag=view";
		showDialog("�鿴", 770, 450, url);
	}
}

/*��ѯ*/
function doQuery(){
	var map = {};
	map['jdid']=jQuery('#jdid').val();
	map['xh']=jQuery('#xh').val();
	jQuery("#dataTable").reloadGrid(map);
}	

//��ע
function bzLink(cellValue,rowObject){
	var bz = cellValue == null ? "" : cellValue;
	var html = "<textarea name='bz'  data-jdwhid ='"+rowObject["jdwhid"]+"'  onblur='saveThisRow(this)' style='width:80%;height:30px' />";
	var obj = jQuery(html);
	jQuery(obj).text(bz);
	return obj;
}

//�ʱ��
function hdscLink(cellValue,rowObject){
	var hdsc = cellValue == null ? "" : cellValue;
	var html = "<input name='hdsc' maxlength = '25' value = '"+hdsc+"' data-jdwhid ='"+rowObject["jdwhid"]+"'  style='width:80%'  onblur='saveThisRow(this)' />";
	var obj = jQuery(html);
	return obj;
}

//�׶η�
function jbfLink(cellValue,rowObject){
	var html = "<input name='jbf' onkeyup='checkInputNum(this)' maxlength = '3' value = '"+cellValue+"'  data-jdwhid ='"+rowObject["jdwhid"]+"' onblur='saveThisRow(this)'  style='width:80%;' />";
	var obj = jQuery(html);
	return obj;
}

//����׶�ά����Ա
function drjdwhcy(){
	
}

//�޸�
function saveThisRow(obj){
	var xh = jQuery(obj).attr("data-xh");
	var jdwhid = jQuery(obj).attr("data-jdwhid");
	var nameflag = obj.name;
	var inputvalue = obj.value;
    var jsonPara = null;
	if(nameflag == 'jbf'){
		if(jQuery.trim(inputvalue) == "" || inputvalue == null){
			showAlertDivLayer("�׶ηֲ���Ϊ��!");
			return false;
		}
		if(parseFloat(inputvalue) > parseFloat(jQuery("#jdf").val())){
			showAlertDivLayer("�׶η�����Ϊ"+jQuery("#jdf").val()+"��!");
			return false;
		}
		 jsonPara = {jbf:inputvalue,jdwhid:jdwhid};
	}
	if(nameflag == 'bz'){
		if(inputvalue.length > 500){
			showAlertDivLayer("��ע���ó���500��!");
			return false;
		}
		 jsonPara = {bz:inputvalue,jdwhid:jdwhid};
	}
	
	if(nameflag == 'hdsc'){
		 jsonPara = {hdsc:inputvalue,jdwhid:jdwhid};
	}
	
	jQuery.post("grttxm_jdsz.do?method=updateJdszCy",jsonPara, function(data) {
		if(data["message"] == '����ɹ���'){
			//jQuery("#dataTable").reloadGrid();
		}else{
			showAlertDivLayer(data["message"]);
		}
		
		
	}, 'json');
	
}

//��Ӹ�����Ŀ��Ա
function addGrcy(){
	 var xmdm = jQuery("#xmdm").val();
    var url = "grttxm_jdsz.do?method=getStu&jdid="+jQuery("#jdid").val()+"&xmdm="+xmdm;
    var title = "������Ŀ��Ա���";
	showDialog(title, 770, 550, url);
}

//ɾ���׶�ά����Ա
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("grttxm_jdsz.do?method=del",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//���������Ŀ��Ա
function addTtcy(){
    var xmdm = jQuery("#xmdm").val();
    var url = "grttxm_jdsz.do?method=getTtcy&jdid="+jQuery("#jdid").val()+"&xmdm="+xmdm;
    var title = "������Ŀ��Ա���";
	showDialog(title, 770, 550, url);
}

//���뵼��ҳ�淽��
function drjdwhcy(){
	var url = "grttxm_jdsz.do?method=drPrepare";
	var title = "����";
	showDialog(title, 770, 350, url);
}

//���ļ����Ƹ�ֵ��input��
function attachfilename(obj){
	var filefullpath = getFullPath(obj);
//	jQuery(obj).parent().find("input[name='wjmc']").val(filefullpath);
	checkFileType(obj);
}

//��ȡinput file������
function getFullPath(obj){ 
	if(obj) 
	{ 
		 if(window.navigator.userAgent.indexOf("Firefox")>=1) 
		 { 
			 if(obj.files) 
			 { 
				 return obj.files.item(0).getAsDataURL(); 
			 } 
			 return obj.value; 
		 } 
		 return obj.value; 
	 } 
} 

//�ļ����Ϳ���
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


//����

	function  saveRr(){
		var url = "grttxm_jdsz.do?method=SaveDrForm";
		/**/
		if(!checkNotNull("xmdm")){
			showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
			return false;
		}
		if(jQuery("#drmb").val() == "" ||��jQuery("#drmb").val() == null){
			showAlert("��ѡ�����ļ���");
			return false;
		}
		ajaxSubFormWithFun("JdwhSzForm", url, function(data) {
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
	   				      if(data["message"] == "����ʧ��,����ϸ�˶ԡ�error.xls����"){
	   				    	  if(data["gid"] != "" && data["gid"] != null && data["gid"] != "null"){
	   				    		  jQuery("#errortr").show();
	   				    		  jQuery("#errora").attr("data_file","grttxm_jdsz.do?method=downloadFileError&filename="+data["gid"]);
	   				    		  
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




//����
function downloadxzmb(){
	window.open("grttxm_jdsz.do?method=downloadFile");
}

//������������
function downloaderror(){
	var url = jQuery("#errora").attr("data_file");
	window.open(url);
}


//�Զ��嵼�� ����
function exportConfigGr() {
	//DCCLBH�������ܱ��,ִ�е������� 
	var DCCLBH = "grttxm_jdsz.do?method=getXsGrxmCx";
	customExport(DCCLBH, grxmExportData);
}

//��������
function grxmExportData() {
	var DCCLBH = "grttxm_jdsz.do?method=getXsGrxmCx";
	setSearchTj();//���ø߼���ѯ����
	var url = "grttxm_jdsz.do?method=exportDataGr&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//�Զ��嵼�� ����
function exportConfigTt() {
	//DCCLBH�������ܱ��,ִ�е������� 
	var DCCLBH = "grttxm_jdsz.do?method=getXsTtxmCx";
	customExport(DCCLBH, ttxmExportData);
}

//��������
function ttxmExportData() {
	var DCCLBH = "grttxm_jdsz.do?method=getXsTtxmCx";
	setSearchTj();//���ø߼���ѯ����
	var url = "grttxm_jdsz.do?method=exportDataTt&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//�鿴ѧ������
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='SqjgView(\""
			+ rowObject["jgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//�鿴ѧ������
function xssqLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='SqjgView(\""
			+ rowObject["xsjgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function SqjgView(jgid, xh) {
	showDialog("�鿴", 770, 450, "xmsqgl_xmjg.do?method=XmjgView&jgid="
			+ jgid + "&xh=" + xh);
}

//�鿴�Ŷ�����
function tdmcLink(cellValue, rowObject) {
	var jgid = rowObject["jgid"] || rowObject["xsjgid"] 
	return "<a href='javascript:void(0);' class='name' onclick='SqjgView(\""
			+ jgid + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function SqjgView(jgid, xh) {
	showDialog("�鿴", 770, 450, "xmsqgl_xmjg.do?method=XmjgView&jgid="
			+ jgid + "&xh=" + xh);
}

//�鿴�Ŷ�����
function ttsqLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='TtsqView(\""
			+ rowObject["ttjgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function TtsqView(ttjgid, tdmc) {
	showDialog("������չ��Ŀ����鿴", 770, 450, "ttxm_jg.do?method=TtsqView&ttjgid="
			+ ttjgid);
}

