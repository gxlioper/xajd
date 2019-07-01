

jQuery(function(){
	var gridSetting = {
			caption:"������Ϣ�б�",
			pager:"pager",
			url:"daxxgl.do?method=daxxglList&type=query",
			colList:[
			   {label:'pk',name:'pk', index: 'pk',width:'12%',key:true,hidden:true},
			   {label:'ѧ��',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
			   {label:'����',name:'xm', index: 'xm',width:'8%'},
			   {label:'�Ա�',name:'xb', index: 'xb',width:'4%'},
			   {label:'�꼶',name:'nj', index: 'nj',width:'8%'},
			   {label:jQuery("#xbmc").val(),name:'xymc', index: 'xymc',width:'10%'},
			   {label:'רҵ',name:'zymc', index: 'zymc',width:'10%'},
			   {label:'�༶',name:'bjmc', index: 'bjmc',width:'10%'},
			   {label:'����ת��ʱ��',name:'dazrsj', index: 'dazrsj',width:'8%'},
			   {label:'����ת��ʱ��',name:'dazcsj', index: 'dazcsj',width:'8%'},
			   {label:'�����嵥',name:'whzt', index: 'whzt',width:'5%'},
			   {label:'�����嵥ID',name:'daqdmb_id', index: 'daqdmb_id',hidden:true}	   
			],
			sortname: "",
		 	sortorder: ""
		};
	jQuery("#dataTable").initGrid(gridSetting);
});

function searchRs(){
	var map = getSuperSearch();

	jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='showDetail(\""+rowObject["pk"]+"\");'>"+cellValue+"</a>";
}

function showDetail(pk){
	
	showDialog("�鿴������Ϣ",700,540,"daxxgl.do?method=viewDaxxgl&pk="+pk);
}

function addDaxxgl(){
	showDialog("���ӵ�����Ϣ",700,540,"daxxgl.do?method=addDaxxgl");			
}

function updateDaxxgl(){
	var pk='';
	var rowsValue = jQuery("#dataTable").getSeletRow();
	 if(rowsValue.length != 1){
		 showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	}else{
		pk = rowsValue[0]["pk"];
	}
	showDialog("�޸ĵ�����Ϣ",700,540,"daxxgl.do?method=updateDaxxgl&pk="+pk);
}

function qdwhDaxxgl(){

	var rows = jQuery("#dataTable").getSeletRow();
	
	// ������¼ά��
	if (rows.length == 1){
		var selectPks = rows[0]["pk"];
		var daqdmb_id = rows[0]["daqdmb_id"];
		// 
		showDialog("���嵥",750,540,"daxxgl.do?method=bandXsqd&pk="+selectPks+"&daqdmb_id="+daqdmb_id);
		
		// δѡ���¼
	}else if (rows.length == 0){
		var map = getSuperSearch();
		var url = "daxxgl.do?method=bandXsqdBatch";
		//�߼���ѯ
		url +="&searchTj=";
		url +=map["searchTj"];
		url +="&searchTjz=";
		url +=map["searchTjz"];
		url +="&mhcx_lx=";
		url +=map["mhcx_lx"];
		url +="&searchLx=";
		url +=map["searchLx"];

		//ģ����ѯ
		url +="&input_mhcx=";
		url +=map["input_mhcx"];
		url +="&path=";
		url +=map["path"];					
		url +="&selected=all";
		showDialog("���嵥",750,540,url);
		
		// ��ѡ��¼����
	} else {
		var ids = jQuery("#dataTable").getSeletIds();
		showDialog("���嵥",750,540,"daxxgl.do?method=bandXsqdBatch&values="+ids.toString());
	}
}

function delDaxxgl(){
	var pks = '';
	var rowsValue = jQuery("#dataTable").getSeletRow();
	if (rowsValue.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	}
	for(var i=0;i<rowsValue.length;i++){
		if(i==(rowsValue.length-1)){
			pks += rowsValue[i]["pk"];
		}else{
			pks += rowsValue[i]["pk"]+",";
		}
	}
	

	confirmInfo("��ȷ��Ҫɾ��<font color='red'>"+rowsValue.length +"</font>����¼��?",function(ty){
		if(ty=="ok"){
			jQuery.post("daxxgl.do?method=delDaxxgl",{pks:pks},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}
	});		
	
}

function dcDaxxgl(){
	customExport("daxxgl.do?method=daxxglList", exportDaxxData,750,500);
}

// ��������
function exportDaxxData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "daxxgl.do?method=exportDaxxData&dcclbh=" + "daxxgl.do?method=daxxglList";//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function drDaxxgl(){
	toImportData("IMPORT_N711203");
	return false;
}