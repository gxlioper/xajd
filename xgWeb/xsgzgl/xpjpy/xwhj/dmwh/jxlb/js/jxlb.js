var gridSetting = {
	caption:"�������ά���б�",
	pager:"pager",
	url:"xpj_jxlb.do?method=jxlbList&type=query",
	colList:[
	   {label:'������',name:'jxlbdm', index: 'jxlbdm',key:true},
	   {label:'�������',name:'jxlbmc', index: 'jxlbmc',width:'50%'}
	],
	sortname: "jxlbdm",
 	sortorder: "asc"
}

function jxlbLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
}


//����
function add(){
	var url = "xpj_jxlb.do?method=addJxlb";
	var title = "���ӽ������";
	showDialog(title,350,180,url);
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'xpj_jxlb.do?method=updateJxlb&jxlbdm='+rows[0]["jxlbdm"];
		var title = "�޸Ľ������";
		showDialog(title,350,180,url);
	}
}

//ɾ��
function del(){
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
				jQuery.post("xpj_jxlb.do?method=delJxlb",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
		
	}
}

//����ȼ�
function goJxdj(){
	var url="pjpy_hjgl_jxdj.do";
	refreshForm(url);
}

//��������
function goJxmc(){
	var url="pjpy_hjgl_jxmc.do";
	refreshForm(url);
}

//��ѯ
function query(){
	var map = {};
	map["jxlbmc"] = jQuery("#jxlbmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//�޸ı���
function updSaveForm(){	
	
	if(!checkNull("jxlbmc")){
		return false;
	}
	
	var url = "xpj_jxlb.do?method=updateJxlb&type=update"
	ajaxSubFormWithFun("dmwhJxlbForm",url,function(data){
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
	  
	if(!checkNull("jxlbmc")){
		return false;
	}
	
   var url = "xpj_jxlb.do?method=addJxlb&type=save";
    ajaxSubFormWithFun("dmwhJxlbForm",url,function(data){
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