//��ѯ�б�
var gridSetting = {
	caption:"������Ŀ����",
	pager:"pager",
	url:"xpjpy_xmlx.do?method=listXmlx&type=query",
	colList:[
	   {label:'��Ŀ���ʹ���',name:'lxdm', index: 'lxdm',key:true,hidden:true},
	   {label:'��Ŀ��������',name:'lxmc', index: 'lxmc',width:'50%'}
	],
	sortname: "lxmc",
 	sortorder: "asc"
}

//��ѯ
function query(){
	var map = {};
	map["lxmc"] = jQuery("#lxmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add(){
	var url = "xpjpy_xmlx.do?method=addXmlx";
	var title = "������Ŀ����";
	showDialog(title,380,200,url);
}

//��������
function saveForm(){
	var lxmc = jQuery("#lxmc").val();
	if(lxmc == null || lxmc == ''){
		showAlert("����д��Ŀ�������ƣ�");
		return false;
	}
   var url = "xpjpy_xmlx.do?method=saveForAdd";
    ajaxSubFormWithFun("xmlxForm",url,function(data){
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

//ɾ����������֧������ɾ��
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
				jQuery.post("xpjpy_xmlx.do?method=delXmlx",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
	}
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'xpjpy_xmlx.do?method=updateXmlx&lxdm='+rows[0]["lxdm"];
		var title = "�޸���Ŀ��������";
		showDialog(title,380,200,url);
	}
}

//�޸ı���
function updateSaveForm(){
	var lxmc = jQuery("#lxmc").val();
	if(lxmc == '' || lxmc == null){
		showAlert("����д��Ŀ�������ƣ�");
		return false;
	}
	var url = "xpjpy_xmlx.do?method=saveForUpdate"
	ajaxSubFormWithFun("xmlxForm",url,function(data){
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

//ǰ����Ŀ����
function goXmxz(){
	var url="xpjpy_pjxzdm.do";
	refreshForm(url);
}