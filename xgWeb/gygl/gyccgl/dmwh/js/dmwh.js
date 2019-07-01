var gridSetting = {
	caption : "",
	pager : "pager",
	url : "gyccgl_dmwh.do?method=searchRs&type=query",
	colList : [ {
		label : '��Ʒ����',
		name : 'dm',
		index : 'dm',
		key : true,
		width : '50%'
	}, {
		label : '��Ʒ����',
		name : 'mc',
		index : 'mc',
		width : '50%'
	}],
	sortname : "dm",
	sortorder : "asc"
}	

var gridSetting1 = {
		caption : "",
		pager : "pager",
		url : "gyccgl_dmwh.do?method=searchRs&type=query",
		colList : [ {
			label : '����',
			name : 'shcddm',
			index : 'shcddm',
			key : true,
			width : '30%'
		}, {
			label : '�𻵳̶�',
			name : 'shcdmc',
			index : 'shcdmc',
			width : '40%'
		},{
			label : '���(Ԫ)',
			name : 'je',
			index : 'je',
			width : '30%'
		}],
		sortname : "shcddm",
		sortorder : "asc"
	}

//�л�Tabҳ
function selectTab(obj, tabType) {
		var map = {};
	jQuery("#tabType").val(tabType);
	map["cxlx"]=tabType;
	if (tabType == "wp") {
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	}else{
		gridSetting1["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting1);
		
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

function add() {
	var tabType=jQuery("#tabType").val();
	var url="";
	if("wp"==tabType){
		url = "gyccgl_dmwh.do?method=addWp";
	}else{
		url = "gyccgl_dmwh.do?method=addShcd";
	}
	var title = "����";
	showDialog(title, 470, 200, url);
}

function update() {
	var tabType=jQuery("#tabType").val();
	var rows = jQuery("#dataTable").getSeletRow();
	var url="";
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	} else if("wp"==tabType){
		url = "gyccgl_dmwh.do?method=updateWp&dm="+rows[0]["dm"];
	}else{
		url = "gyccgl_dmwh.do?method=updateShcd&shcddm="+rows[0]["shcddm"];
	}
	var title = "�޸�";
	showDialog(title, 470, 180, url);
}

function del() {
	var tabType=jQuery("#tabType").val();
	var ids = jQuery("#dataTable").getSeletIds();
	if("wp"==tabType){
		url = "gyccgl_dmwh.do?method=delWp";
	}else{
		url = "gyccgl_dmwh.do?method=delShcd";
	}
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
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

function saveWpForm(type){	  
	  var mc=jQuery("#dm").val();
	  var dm=jQuery("#mc").val();
	  if(jQuery.trim(dm)==""){
		  showAlert("��������Ʒ���룡");
			return false;
	  }
	  if(jQuery.trim(mc)==""){
		  showAlert("��������Ʒ���ƣ�");
			return false;
	  }
   var url = "gyccgl_dmwh.do?method=saveWpForm&type="+type;
    ajaxSubFormWithFun("GyccDmwhForm",url,function(data){
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



function saveShcdForm(type){
	var dm = jQuery("#shcddm").val();
	var mc = jQuery("#shcdmc").val();
	var je = jQuery("#je").val();
	if(dm == null || dm == ""){
		showAlert("��������룡");
		return false;
	}
	if(mc == null || mc == ""){
		showAlert("�������𻵳̶ȣ�");
		return false;
	}
	if(je == null || je == ""){
		showAlert("�������");
		return false;
	}
	 var url = "gyccgl_dmwh.do?method=saveShcdForm&type="+type;
	    ajaxSubFormWithFun("GyccDmwhForm",url,function(data){
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
