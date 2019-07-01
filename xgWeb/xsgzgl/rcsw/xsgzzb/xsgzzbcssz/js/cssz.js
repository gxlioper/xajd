var wjlxmc = "";
var gridSetting = {
	caption : "上报文件类型列表 ",
	pager : "pager",
	url : "rcsw_xsgzzb_csszgl.do?method=getCsszList&type=query&wjlxmc="+wjlxmc,
	colList : [ {
		label : '上报文件类型代码',
		name : 'wjlxdm',
		index : 'wjlxdm',
		key : true,
		width : '50%'
	}, {
		label : '上报文件类型名称',
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
	var title = "增加";
	showDialog(title, 470, 180, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	var url="";
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	}
	url = 'rcsw_xsgzzb_csszgl.do?method=edit&wjlxdm=' + rows[0]["wjlxdm"];
	var title = "修改";
	showDialog(title, 470, 180, url);
}

function del() {
	var tabType=jQuery("#tabType").val();
	var ids = jQuery("#dataTable").getSeletIds();
    url = "rcsw_xsgzzb_csszgl.do?method=delWjlx";
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
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

//新增保存
function saveFormwjlx(){
	var wjlxmc = jQuery.trim(jQuery("#wjlxmc").val());
	if(wjlxmc == null || wjlxmc == ""){
		showAlert("请输入文件类型名称！");
		return false;
	}
	 var url = "rcsw_xsgzzb_csszgl.do?method=saveNewWjlx";
	    ajaxSubFormWithFun("CsszForm",url,function(data){
		  	 if(data["message"]=="保存成功！"){
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

//修改保存
function saveUpdateFormwjlx(){
	var wjlxmc = jQuery.trim(jQuery("#wjlxmc").val());
	if(wjlxmc == null || wjlxmc == ""){
		showAlert("请输入文件类型名称！");
		return false;
	}
	 var url = "rcsw_xsgzzb_csszgl.do?method=saveWjlx_update";
	    ajaxSubFormWithFun("CsszForm",url,function(data){
		  	 if(data["message"]=="保存成功！"){
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