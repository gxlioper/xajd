

function dcmcLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
}


//����
function add(){
	var url = "xpj_pjdm.do?method=addXmlx";
	var title = "������Ŀ����";
	showDialog(title,380,200,url);
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'xpj_pjdm.do?method=updateXmlx&xmlxdm='+rows[0]["xmlxdm"];
		var title = "�޸���Ŀ��������";
		showDialog(title,380,200,url);
	}
}

//ɾ��
function del(){
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
				jQuery.post("xpj_pjdm.do?method=delXmlx",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
		
	}
}

//ǰ����Ŀ����
function goXmxz(){
	var url="pj_pjxzdm.do";
	refreshForm(url);
}

//ǰ���༶����
function goBjdl(){
	var url="pj_bjdldm.do";
	refreshForm(url);
}

//��ѯ
function query(){
	var map = {};
	map["xmlxmc"] = jQuery("#xmlxmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//�޸ı���
function updSaveForm(){
	var xmlxmc=jQuery("#xmlxmc").val();
	if(xmlxmc==""){
		showAlert("�뽫��*����Ŀ��д������");
		return false;
	}
	var url = "xpj_pjdm.do?method=updateXmlx&type=update"
	ajaxSubFormWithFun("xpjPjdmModel",url,function(data){
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

//��������

function saveForm(){
	  var xmlxmc=jQuery("#xmlxmc").val();
	  if(xmlxmc==""){
		  showAlert("�뽫��*����Ŀ��д������");
			return false;
	  }
   var url = "xpj_pjdm.do?method=addXmlx&type=save";
    ajaxSubFormWithFun("xpjPjdmModel",url,function(data){
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