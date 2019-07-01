//��һ�μ���
var isFirst=true;
//�����ѯҳ��
var gridSetting = {
	caption:"�۲�������",
	pager:"pager",
	url:"rcsw_kqgl_xskqgl.do?method=viewKqdjList&type=query",
	colList:[
		{label:'key',name:'kqdjid', index: 'kqdjid',key:true,hidden:true},
		{label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:xhLink },
		{label:'����',name:'xm', index: 'xm',width:'10%'},
		{label:'�Ա�',name:'xb', index: 'xb',width:'3%'},
		{label:'�༶',name:'bjmc', index: 'bjdm',width:'12%'},
	    {label:'ѧ��',name:'xn', index: 'xn',hidden:true},
	    {label:'ѧ��',name:'xqmc', index: 'xq',hidden:true},
	    {label:'ѧ��ѧ��',name:'xnxq', index: 'xn,xq',width:'13%',formatter:xnxq},
		{label:'���ڿγ�',name:'kqkc', index: 'kqkc',width:'16%'},
		{label:'�ܴ�',name:'zc', index: 'zc',width:'8%',formatter:xszc},
		{label:'����ʱ��',name:'kqsj', index: 'kqsj',width:'15%'},
		{label:'�������ʹ���',name:'kqlxdm', index: 'kqlxdm',hidden:true},
		{label:'��������',name:'kqlxmc', index: 'kqlxdm',width:'10%'}
	],
	sortname: "kqsj",
 	sortorder: "desc"
};

function xnxq(cellValue, rowObject) {
	return rowObject["xn"] + " " +rowObject["xqmc"];
}

function xszc(cellValue, rowObject) {
	return "��" + cellValue + "��";
}

//ѧ������
function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='oneKqdjView(\""+rowObject["kqdjid"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}

//���÷��Ž���鿴
function oneKqdjView(kqdjid,xh){
	showDialog("���ڵǼǽ����ѯ",800,380,"rcsw_kqgl_xskqgl.do?method=oneKqdjView&kqdjid="+kqdjid+"&xh="+xh);
}

//����
function saveForm(method,type){

	 //ѧ��-ѧ��-ѧ��-�ܴ�-���ڿγ�-�Ͽεص�-����ʱ��-��������
	 if (!checkNull("xh-xn-xq-zc-kqkc-kqsj-kqlxdm")) {
		 return false;
	 }

	 var url = "rcsw_kqgl_xskqgl.do?method="+method+"&type="+type;
     ajaxSubFormWithFun("KqdjForm",url,function(data){
    	  
    	  if (data["success"] == "false"){
    		  showAlert("��ѧ����"+jQuery("#kqlxdm>option:selected").text()+"��"+jQuery('#kqsj').val()+"�ѵǼ�" );
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
	var url = "rcsw_kqgl_xskqgl.do?method=addKqdj";
	var title = "���ӿ��ڵǼ���Ϣ";
	showDialog(title,800,430,url);
}

//�޸�һ����¼
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'rcsw_kqgl_xskqgl.do?method=updateKqdj&kqdjid='+rows[0]["kqdjid"]+'&xh='+rows[0]["xh"];
		var title = "�޸Ŀ��ڵǼ���Ϣ";
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
			jQuery.post("rcsw_kqgl_xskqgl.do?method=deleteKqdj",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


//����
function exportConfig(){
	var DCCLBH='rcsw_kqgl_kqgl.do';
	customExport(DCCLBH, exportData);
}
function exportData(){
	var DCCLBH='rcsw_kqgl_kqgl.do';
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_kqgl_xskqgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//����
function importConfig(){
	toImportData("IMPORT_RCSW_KQGL");
	return false;
}