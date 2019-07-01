
var gridSetting = {
		caption:"�������ʹ���",
		pager:"pager",
		url:"rcsw_kqgl_dmwh.do?method=kqlxQuery",
		colList:[
		   {label:'���ڴ���',name:'kqlxdm', index: 'kqlxdm',key:true,width:'50%'},
		   {label:'��������',name:'kqlxmc', index: 'kqlxmc',width:'50%'}
		],
		sortname: "kqlxdm",
	 	sortorder: "asc"
	};

//��ѯ
function query(){
	var map = {};
	map["kqlxmc"] = jQuery("#kqlxmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add(){
	var url = "rcsw_kqgl_dmwh.do?method=addKqlx";
	var title = "����";
	showDialog(title,350,200,url);
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'rcsw_kqgl_dmwh.do?method=updateKqlx&kqlxdm='+rows[0]["kqlxdm"];
		var title = "�޸�";
		showDialog(title,350,200,url);
	}
}


//ɾ��
function del(){
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
				jQuery.post("rcsw_kqgl_dmwh.do?method=delKqlx",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
		
	}
}


//��������
function saveForm(){
	if (!checkNull("kqlxmc")) {
		return false;
	}
	var url = "rcsw_kqgl_dmwh.do?method=addKqlx&type=save";
	ajaxSubFormWithFun("KqlxForm",url,function(data){
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


//�޸ı���
function updSaveForm(){
	if (!checkNull("kqlxmc")) {
		return false;
	}
	var url = "rcsw_kqgl_dmwh.do?method=updateKqlx&type=update";
	ajaxSubFormWithFun("KqlxForm",url,function(data){
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