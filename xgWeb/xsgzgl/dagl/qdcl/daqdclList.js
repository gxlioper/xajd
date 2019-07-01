var gridSetting = {
	caption:"�����嵥�����б�",
	pager:"pager",
	url:"daqdcl.do?method=daqdclList&type=query",
	colList:[
	   {label:'�����嵥���ϱ��',name:'daqdcl_id', index: 'daqdcl_id',hidden:true},
	   {label:'�����嵥��������',name:'daqdcl_mc', index: 'daqdcl_mc'},
	   {label:'��ģ����',name:'ybdmbs', index: 'ybdmbs'},
	   {label:'��ѧ����',name:'ybdxss', index: 'ybdxss'}
	],
	sortname: "",
 	sortorder: ""
};

jQuery(function(){
	jQuery("#dataTable").initGrid(gridSetting);
});

function searchRs(){
	var map = getSuperSearch();

	jQuery("#dataTable").reloadGrid(map);
}

function addDaqdcl(){
	showDialog("���ӵ����嵥����",420,180,"daqdcl.do?method=addDaqdcl");			
}

function updateDaqdcl(){
	var daqdclId= '';
	var rowsValue = jQuery("#dataTable").getSeletRow();
	 if(rowsValue.length != 1){
		 showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	}else{
		if(rowsValue[0]["ybdmbs"]!=0){
			showAlertDivLayer("��ѡ��ļ�¼�Ѱ�ģ�壬�����޸ģ�");
			return false;
		}
		if(rowsValue[0]["ybdxss"]!=0){
			showAlertDivLayer("��ѡ��ļ�¼�Ѱ�ѧ���������޸ģ�");
			return false;
		}
		daqdclId = rowsValue[0]["daqdcl_id"];
	}
	showDialog("�޸ĵ����嵥����",420,180,"daqdcl.do?method=updateDaqdcl&daqdcl_id="+daqdclId);
}

function delDaqdcl(){
	var delDaqdclId = '';	
	var rowsValue = jQuery("#dataTable").getSeletRow();
	if (rowsValue.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	}
	for(var i=0;i<rowsValue.length;i++){
		if(rowsValue[i]["ybdmbs"]!=0){
			showAlertDivLayer("��ѡ��ļ�¼�Ѱ�ģ�壬����ɾ����");
			return false;
		}
		if(rowsValue[i]["ybdxss"]!=0){
			showAlertDivLayer("��ѡ��ļ�¼�Ѱ�ѧ��������ɾ����");
			return false;
		}
		if(i==(rowsValue.length-1)){
			delDaqdclId += rowsValue[i]["daqdcl_id"];
		}else{
			delDaqdclId += rowsValue[i]["daqdcl_id"]+",";
		}
	}
	confirmInfo("��ȷ��Ҫɾ��<font color='red'>"+rowsValue.length +"</font>����¼��?",function(ty){
		if(ty=="ok"){
			jQuery.post("daqdcl.do?method=delDaqdcl",{daqdclIds:delDaqdclId},function(data){
				alertInfo(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}
	});
}

			
