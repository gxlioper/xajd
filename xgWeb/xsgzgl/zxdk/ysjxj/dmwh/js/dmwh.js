var gridSetting = {
				caption:"111",
				pager:"pager",
				url:"ysjxj_dmwh.do?method=dmwhQuery",
				colList:[
				   {label:'�ʽ���Դ����',name:'zjlydm', index: 'zjlydm',formatter:dcmcLink,key:true,hidden:true},
				   {label:'�ʽ���Դ����',name:'zjlymc', index: 'zjlymc',width:'50%'}
				],
				sortname: "zjlydm",
			 	sortorder: "asc"
			};
	function dcmcLink(cellValue,rowObject){
		return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
	}
//��ѯ
function query(){
	var map = {};
	map["zjlymc"] = jQuery("#zjlymc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add(){
	var url = "ysjxj_dmwh.do?method=dmwhAdd";
	var title = "����";
	showDialog(title,350,200,url);
}

//���ӱ��淽��
function saveForm(){
	  var zjlymc = jQuery("#zjlymc").val();
	  if(zjlymc == ""||zjlymc == null){
		  showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
			return false;
	  }
	   var url = "ysjxj_dmwh.do?method=dmwhAdd&type=save";
	    ajaxSubFormWithFun("dmwhForm",url,function(data){
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

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'ysjxj_dmwh.do?method=dmwhUpdate&zjlydm='+rows[0]["zjlydm"];
		var title = "�޸��ʽ���Դ����";
		showDialog(title,350,200,url);
	}
}

//�޸ı���
function updSaveForm(){
	var zjlymc=jQuery("#zjlymc").val();
	if(zjlymc == ""||zjlymc == null){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		return false;
	}
	var url = "ysjxj_dmwh.do?method=dmwhUpdate&type=update";
	ajaxSubFormWithFun("dmwhForm",url,function(data){
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

//ɾ��
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
				jQuery.post("ysjxj_dmwh.do?method=dmwhDelete",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
	}
}