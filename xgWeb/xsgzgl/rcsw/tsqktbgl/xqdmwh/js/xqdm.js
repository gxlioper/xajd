var gridSetting = {
	caption:"������Ŀ����",
	pager:"pager",
	url:"xqdmwh.do?method=getXqdmList&type=query",
	colList:[
	   {label:'ѧ�����',name:'xqdm', index: 'xqdm',key:true},
	   {label:'ѧ������',name:'xqmc', index: 'xqmc',width:'50%'}
	],
	sortname: "xqdm",
 	sortorder: "asc"
}

//����
function add(){
	var url = "xqdmwh.do?method=addXqdm";
	var title = "����ѧ�����ά��";
	showDialog(title,350,200,url);
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var flg = true;
		jQuery.ajaxSetup({async:false});
		jQuery.post("xqdmwh.do?method=isOperate", {xqdm:rows[0]["xqdm"]}, function(data) {
			if(data != null && data!= ""){
				flg = false;					
			}	      		
			}, 'json');
		jQuery.ajaxSetup({async:true});
		if(!flg){
			showAlertDivLayer("ѧ�������ʹ�ã������޸Ļ�ɾ����");
			return false;
		}
		var url = 'xqdmwh.do?method=updateXqdm&xqdm='+rows[0]["xqdm"];
		var title = "�޸�ѧ�����ά��";
		showDialog(title,350,200,url);
	}
}

//ɾ��
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var flag = false;
		jQuery.ajaxSetup({async:false});
		jQuery.each(rows,function(i,n) {			
			jQuery.post("xqdmwh.do?method=isOperate", {xqdm:n["xqdm"]}, function(data) {
				if(data != null && data!= ""){
					flag = true;					
				}	      		
				}, 'json');
				if(flag){
					return false;
				}																	
		})
		jQuery.ajaxSetup({async:true});
		if(flag) {
			showAlertDivLayer("ѧ�������ʹ�ã������޸Ļ�ɾ����");
			return false;
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
				jQuery.post("xqdmwh.do?method=delXqdm",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
		
	}
}

//��ѯ
function query(){
	var map = {};
	map["xqmc"] = jQuery.trim(jQuery("#xqmc").val());
	jQuery("#dataTable").reloadGrid(map);
}

//�޸ı���
function updSaveForm(){
	var xqdm = jQuery("#xqdm").val();
	var xqmc=jQuery("#xqmc").val();
	if(xqdm==""|| xqmc=="" ){
		showAlert("�뽫��*����Ŀ��д������");
		return false;
	}
	var url = "xqdmwh.do?method=updateXqdm&type=update"
	ajaxSubFormWithFun("xqdmForm",url,function(data){
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
	  var xqmc=jQuery("#xqmc").val();
	  if(xqmc==""){
		  showAlert("�뽫��*����Ŀ��д������");
			return false;
	  }
   var url = "xqdmwh.do?method=addXqdm&type=save";
    ajaxSubFormWithFun("xqdmForm",url,function(data){
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