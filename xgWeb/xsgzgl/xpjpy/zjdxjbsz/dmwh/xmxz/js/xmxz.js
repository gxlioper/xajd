//��ѯ�б�
var gridSetting = {
	caption:"������Ŀ����",
	pager:"pager",
	url:"xpjpy_xmxz.do?method=listXmxz&type=query",
	colList:[
	   {label:'��Ŀ���ʴ���',name:'xzdm', index: 'xzdm',key:true,hidden:true},
	   {label:'��Ŀ��������',name:'xzmc', index: 'xzmc',width:'50%'}
	],
	sortname: "xzmc",
 	sortorder: "asc"
}

//��ѯ
function query(){
	var map = {};
	map["xzmc"] = jQuery("#xzmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add(){
	var url = "xpjpy_xmxz.do?method=addXmxz";
	var title = "������Ŀ����";
	showDialog(title,380,200,url);
}

//��������
function saveForm(){
	
  var xzmc = jQuery("#xzmc").val();
  if(xzmc == '' || xzmc == null){
	showAlert("����д��Ŀ�������ƣ�");
	return false;
  }
   var url = "xpjpy_xmxz.do?method=saveForAdd";
    ajaxSubFormWithFun("xmxzForm",url,function(data){
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
				jQuery.post("xpjpy_xmxz.do?method=delXmxz",{values:ids.toString()},function(data){
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
		var url = 'xpjpy_xmxz.do?method=updateXmxz&xzdm='+rows[0]["xzdm"];
		var title = "�޸���Ŀ��������";
		showDialog(title,380,200,url);
	}
}

//�޸ı���
function updateSaveForm(){
	var xzmc = jQuery("#xzmc").val();
	if(xzmc == "" || xzmc == null){
		showAlert("����д��Ŀ�������ƣ�");
		return false;
	}
	var url = "xpjpy_xmxz.do?method=saveForUpdate"
	ajaxSubFormWithFun("xmxzForm",url,function(data){
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
function goXmlx(){
	var url="xpjpy_dmwh.do";
	refreshForm(url);
}