
var gridSetting = {
		caption:"��������ά��",
		pager:"pager",
		url:"xszz_sqlywh.do?method=sqlywhQuery",
		colList:[
		   {label:'�������ɴ���',name:'sqlydm', index: 'sqlydm',key:true,width:'50%', hidden : true },
		   {label:'������������',name:'sqlymc', index: 'sqlymc',width:'50%'}
		],
		sortname: "sqlydm",
	 	sortorder: "asc"
	};

//��ѯ
function query(){
	var map = {};
	map["sqlymc"] = jQuery("#sqlymc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add(){
	var url = "xszz_sqlywh.do?method=addSqlywh";
	var title = "����";
	showDialog(title,350,200,url);
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'xszz_sqlywh.do?method=updateSqlywh&sqlydm='+rows[0]["sqlydm"];
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
				jQuery.post("xszz_sqlywh.do?method=delSqlywh",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
		
	}
}


//��������
function saveForm(){
	if (!checkNull("sqlymc")) {
		return false;
	}
	var url = "xszz_sqlywh.do?method=addSqlywh&type=save";
	ajaxSubFormWithFun("sqlyDmwhForm",url,function(data){
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
	if (!checkNull("sqlymc")) {
		return false;
	}
	var url = "xszz_sqlywh.do?method=updateSqlywh&type=update";
	ajaxSubFormWithFun("sqlyDmwhForm",url,function(data){
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