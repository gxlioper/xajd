//��һ�μ���
var isFirst=true;
//�����ѯҳ��
var gridSetting = {
    pager:"pager",
	url:"rcsw_kqgl_kqgljg.do?method=viewKqjgList&type=query",
	colList:[
		{label:'key',name:'id', index: 'id',key:true,hidden:true},
		{label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:xhLink },
		{label:'����',name:'xm', index: 'xm',width:'10%'},
		{label:'�Ա�',name:'xb', index: 'xb',width:'6%'},
		{label:'�༶',name:'bjmc', index: 'bjdm',width:'12%'},
		{label:'רҵ',name:'zymc', index: 'zydm',width:'12%'},
		{label:'ѧԺ',name:'xymc', index: 'xydm',width:'12%'},
		{label:'��������',name:'kqrq', index: 'kqrq',width:'12%'},
		{label:'ȱ������',name:'qqts', index: 'qqts',width:'15%'},
		{label:'ȱ�����',name:'kqlbmc', index: 'kqlbdm',width:'15%'}
	],
	sortname: "kqrq",
 	sortorder: "desc"
};


//ѧ������
function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='KqjgView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}


function KqjgView(id,xh){
	showDialog("���ڽ����ѯ",800,470,"rcsw_kqgl_kqgljg.do?method=viewKqjg&id="+id+"&xh="+xh);
}

//����
function saveForm(method,type){
    var qqlb = jQuery("#kqlbdm").find("option:selected").text();
	if("����"!=qqlb){
		if (!checkNull("xh-kqrq-kqlbdm-qqts-kkjs")) {
			 return false;
		 }
	}else{
		 if (!checkNull("xh-kqrq-kqlbdm-qqts-kkjs-qkjblbdm-ybqkjbdm-dqztdm")) {
			 return false;
		 }
	}
	if(!checkother()){
		return false;
	} 
	 var url = "rcsw_kqgl_kqgljg.do?method="+method+"&type="+type;
     ajaxSubFormWithFun("KqgljgForm",url,function(data){
    	  if (data["success"] == "false"){
    		  showAlert("��ѧ����"+jQuery("#kqlbdm").find("option:selected").text()+"��"+jQuery('#kqrq').val()+"��ά��" );
    	  } else {
    		  showAlert(data["message"],{},{"clkFun":function(){
        			if (parent.window){
     				 refershParent();
        			}
      		  }});
    	  }
    	  
     });
}
function checkother(){
	if(chkNumInputForThis("qqts")){
		showAlert("ȱ����������Ϊ����!");
		return false;
	}
	
	if(chkNumInputForThis("kkjs")){
		showAlert("���ν�������Ϊ����!");
		return false;
	}
	return true;
}
function chkNumInputForThis(obj){
	//�����ǹ���js ����ͬҳ�� ��Щ���󲻴��ڵ�����
	if(null==obj||null==$(obj)){
		return false;
	}
	return chkNumInput(obj);
}
//����
function updateForm(method,type){
    var qqlb = jQuery("#kqlbdm").val();
	if("����"!=qqlb){
		if (!checkNull("qqts-kkjs")) {
			 return false;
		 }
	}else{
		 if (!checkNull("qqts-kkjs-qkjblbdm-ybqkjbdm-dqztdm")) {
			 return false;
		 }
	}
	if(!checkother()){
		return false;
	}
	 var url = "rcsw_kqgl_kqgljg.do?method="+method+"&type="+type;
     ajaxSubFormWithFun("KqgljgForm",url,function(data){
    	  if (data["success"] == "false"){
    		  showAlert("��ѧ����"+jQuery("#kqlbmc").text()+"��"+jQuery('#kqrq').val()+"��ά��" );
    	  } else {
    		  showAlert(data["message"],{},{"clkFun":function(){
        			if (parent.window){
     				 refershParent();
        			}
      		  }});
    	  }
    	  
     });
}

//����
function add(){
	var url = "rcsw_kqgl_kqgljg.do?method=addKqjg";
	var title = "���ӿ�����Ϣ";
	showDialog(title,800,430,url);
}

//�޸�һ����¼
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'rcsw_kqgl_kqgljg.do?method=updateKqjg&id='+rows[0]["id"]+'&xh='+rows[0]["xh"];
		var title = "�޸Ŀ�����Ϣ";
		showDialog(title,800,430,url);
	}
}


//ɾ��
function del(){
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("rcsw_kqgl_kqgljg.do?method=deleteKqjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


//����
function exportConfig(){
	var DCCLBH='rcsw_kqgl_kqgljg.do';
	customExport(DCCLBH, exportData);
}
function exportData(){
	var DCCLBH='rcsw_kqgl_kqgljg.do';
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_kqgl_kqgljg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//����
function importConfig(){
	toImportDataNew("IMPORT_RCSW_KQGLJG");
	return false;
}