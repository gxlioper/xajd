var wjlxmc = "";
var gridSetting = {
	caption : "�ϱ��ļ������б� ",
	pager : "pager",
	url : "rcsw_xsgzzb_csszgl.do?method=getCsszList&type=query&wjlxmc="+wjlxmc,
	colList : [ {
		label : '�ϱ��ļ����ʹ���',
		name : 'wjlxdm',
		index : 'wjlxdm',
		key : true,
		width : '50%'
	}, {
		label : '�ϱ��ļ���������',
		name : 'wjlxmc',
		index : 'wjlxmc',
		width : '50%'
	}],
	sortname : "wjlxdm",
	sortorder : "asc"
}	

function query() {
	var map = {};
	map["wjlxmc"] = jQuery("#wjlxmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

function add() {
	var url="";
	url = "rcsw_xsgzzb_csszgl.do?method=add";
	var title = "����";
	showDialog(title, 470, 180, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	var url="";
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	}
	url = 'rcsw_xsgzzb_csszgl.do?method=edit&wjlxdm=' + rows[0]["wjlxdm"];
	var title = "�޸�";
	showDialog(title, 470, 180, url);
}

function del() {
	var tabType=jQuery("#tabType").val();
	var ids = jQuery("#dataTable").getSeletIds();
    url = "rcsw_xsgzzb_csszgl.do?method=delWjlx";
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post(url, {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}


function setCssz_url(){
	wjlxmc = encodeURI(encodeURI(jQuery("#wjlxmc").val()));
    gridSetting.url = "rcsw_xsgzzb_csszgl.do?method=getCsszList&type=query&wjlxmc="+wjlxmc;
}

function keydown_search(keyEvent){
	  if(keyEvent.keyCode == 13){
		  setCssz_url();
		  searchRs();
	  }else{
		  return false;
	  }
}

//��������
function saveFormwjlx(){
	var wjlxmc = jQuery.trim(jQuery("#wjlxmc").val());
	if(wjlxmc == null || wjlxmc == ""){
		showAlert("�������ļ��������ƣ�");
		return false;
	}
	 var url = "rcsw_xsgzzb_csszgl.do?method=saveNewWjlx";
	    ajaxSubFormWithFun("CsszForm",url,function(data){
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
function saveUpdateFormwjlx(){
	var wjlxmc = jQuery.trim(jQuery("#wjlxmc").val());
	if(wjlxmc == null || wjlxmc == ""){
		showAlert("�������ļ��������ƣ�");
		return false;
	}
	 var url = "rcsw_xsgzzb_csszgl.do?method=saveWjlx_update";
	    ajaxSubFormWithFun("CsszForm",url,function(data){
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