var stlbmc = "";
var xmlbmc = "";
var rylbmc = "";
var gridSetting = {
	caption : "列表",
	pager : "pager",
	url : "stglStlbDmwh.do?method=getStlbList&type=query&stlbmc="+stlbmc,
	colList : [ {
		label : '社团类别代码',
		name : 'stlbdm',
		index : 'stlbdm',
		key : true,
		width : '50%'
	}, {
		label : '社团类别名称',
		name : 'stlbmc',
		index : 'stlbmc',
		width : '50%'
	}],
	sortname : "stlbdm",
	sortorder : "asc"
}	

var gridSetting1 = {
		caption : "项目类别列表",
		pager : "pager",
		url : "stglXmlbDmwh.do?method=getXmlbList&type=query&stlbmc="+stlbmc+"&xmlbmc="+xmlbmc,
		colList : [ {
			label : '项目类别代码',
			name : 'xmlbdm',
			index : 'xmlbdm',
			key : true,
			width : '25%'
		}, {
			label : '项目类别名称',
			name : 'xmlbmc',
			index : 'xmlbmc',
			width : '25%'
		},{
			label : '社团类别名称',
			name : 'stlbmc',
			index : 'stlbmc',
			width : '25%'
		},{
			label : '审批流程',
			name : 'lcxx',
			index : 'lcxx',
			width : '25%'
		},{
			label : '申请开关',
			name : 'sqkg',
			index : 'sqkg',
			width : '25%',
			formatter : setSqkg
		},{
			label : 'kssj',
			name : 'kssj',
			index : 'kssj',
			hidden : true
		},{
			label : 'jssj',
			name : 'jssj',
			index : 'jssj',
			hidden : true
		},{
			label : 'currdate',
			name : 'currdate',
			index : 'currdate',
			hidden : true
		}],
		sortname : "xmlbdm",
		sortorder : "asc"
	}

var gridSetting3 = {
		caption : "人员类别列表",
		pager : "pager",
		url : "stglRylbDmwh.do?method=getRylblist&type=query&rylbmc="+rylbmc,
		colList : [ {
			label : '社团人员类别代码',
			name : 'rylbdm',
			index : 'rylbdm',
			key : true,
			width : '50%'
		}, {
			label : '社团人员类别名称',
			name : 'rylbmc',
			index : 'rylbmc',
			width : '50%'
		}],
		sortname : "rylbdm",
		sortorder : "asc"
	}	
function setSqkg(cellValue, rowObject) {
	var xmlbdm = rowObject.xmlbdm;
	var value = "未设置";
	var status = '0';
	var color;
	var currDate = rowObject.currdate;
	if ((null == rowObject.kssj || currDate >= rowObject.kssj)
			&& (null == rowObject.jssj || currDate <= rowObject.jssj) &&  '1'==rowObject.sqkg) {
		value = "已设置";
		status = '1';
	}
	value = setColor(value, status);
	value = "<a  href='javascript:void(0);' onclick='return xmsz(\"" + xmlbdm
			+ "\");' >" + value + "</a>";
	return value;
}
function setColor(value, status) {
	var color;
	if (status == '1') {
		color = "#004400";
	} else {
		color = "red";
	}
	return value = "<font color='" + color + "'>" + value + "</font>";
}
function xmsz(xmlbdm) {
	if (xmlbdm == null) {// 点击按钮
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("请选择一条您要设置的记录！");
			return false;
		}
		xmlbdm = rows[0]["xmlbdm"];
	}
	var url = 'stglXmlbDmwh.do?method=xmsz&xmlbdm=' + xmlbdm;
	var title = "项目设置";
	showDialog(title, 600, 360, url);
}
function dcmcLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name'>" + cellValue + "</a>";
}


//切换Tab页
function selectTab(obj, tabType) {
		var map = {};
	jQuery("#tabType").val(tabType);
	if (tabType == "stlb") {
		map["tabType"]="stlb";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
		jQuery("#flag").val("1");
		jQuery("#th_xmlb").hide(); 
		jQuery("#td_xmlb").hide(); 
		jQuery("#rylb_th").hide();
		jQuery("#rylb_td").hide();
		jQuery("#stlb_th").show(); 
		jQuery("#stlb_td").show(); 
		
	}else if(tabType == 'xmlb') {
		map["tabType"]="xmlb";
		map["type"]="query";
		gridSetting1["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting1);
		jQuery("#flag").val("0");
		jQuery("#rylb_th").hide();
		jQuery("#rylb_td").hide();
		jQuery("#stlb_th").show(); 
		jQuery("#stlb_td").show(); 
		jQuery("#th_xmlb").show(); 
		jQuery("#td_xmlb").show(); 
		
	}else if(tabType == 'rylb'){
		map["tabType"]="rylb";
		map["type"]="query";
		gridSetting1["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting3);
		jQuery("#flag").val("2");
		jQuery("#th_xmlb").hide(); 
		jQuery("#td_xmlb").hide(); 
		jQuery("#stlb_th").hide(); 
		jQuery("#stlb_td").hide(); 
		jQuery("#rylb_th").show();
		jQuery("#rylb_td").show();
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}
function query() {
	var map = {};
	map["stlbmc"] = jQuery("#stlbmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

function add() {
	var tabType=jQuery("#tabType").val();
	var url="";
	if("xmlb"==tabType){
		url = "stglXmlbDmwh.do?method=addXmlb";
	}else if("stlb" == tabType){
		url = "stglStlbDmwh.do?method=addStlb";
	}else{
		url = "stglRylbDmwh.do?method=addRylb";
	}
	var title = "增加";
	showDialog(title, 470, 200, url);
}

function update() {
	var tabType=jQuery("#tabType").val();
	var rows = jQuery("#dataTable").getSeletRow();
	var url="";
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	} else if("stlb"==tabType){
		 url = 'stglStlbDmwh.do?method=updateStlb&stlbdm=' + rows[0]["stlbdm"];
	}else if("xmlb"==tabType){
		url = 'stglXmlbDmwh.do?method=updateXmlb&xmlbdm=' + rows[0]["xmlbdm"];
	}else{
		url = 'stglRylbDmwh.do?method=updateRylb&rylbdm=' + rows[0]["rylbdm"];
	}
	var title = "修改";
	showDialog(title, 470, 180, url);
}

function del() {
	var tabType=jQuery("#tabType").val();
	var ids = jQuery("#dataTable").getSeletIds();
	if("xmlb"==tabType){
		url = "stglXmlbDmwh.do?method=delXmlb";
	}else if("stlb" == tabType){
		url = "stglStlbDmwh.do?method=delStlb";
	}else{
		url = "stglRylbDmwh.do?method=delRylb";
	}
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

function newChgCode(obj) {
	allNotEmpThenGo(obj.id);
}

function saveFormstlb(){	  
	  var mc=jQuery("#stlbmc").val();
	  var dm=jQuery("#stlbdm").val();
	  if(jQuery.trim(dm)==""){
		  showAlert("请输入社团类别代码！");
			return false;
	  }
	  if(jQuery.trim(mc)==""){
		  showAlert("请输入社团类别名称！");
			return false;
	  }
   var url = "stglStlbDmwh.do?method=addStlb&type=save";
    ajaxSubFormWithFun("StlbglForm",url,function(data){
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

function setStlbmc_stdmwh(){
	stlbmc = encodeURI(encodeURI(jQuery("#stlbmc").val()));
	if(jQuery("#flag").val() == '1'){
		gridSetting.url = "stglStlbDmwh.do?method=getStlbList&type=query&stlbmc="+stlbmc;
	}else if(jQuery("#flag").val() == '0'){
		xmlbmc = encodeURI(encodeURI(jQuery("#xmlbmc").val()));
		gridSetting1.url = "stglXmlbDmwh.do?method=getXmlbList&type=query&stlbmc="+stlbmc+"&xmlbmc="+xmlbmc;
	}else{
		rylbmc = encodeURI(encodeURI(jQuery("#rylbmc").val()));
		gridSetting3.url = "stglRylbDmwh.do?method=getRylblist&type=query&rylbmc="+rylbmc;
	}
}

function keydown_search(keyEvent){
	  if(keyEvent.keyCode == 13){
		  setStlbmc_stdmwh();
		  searchRs();
	  }else{
		  return false;
	  }
}

//社团人员类别新增保存
function saveFormrylb(){
	var rylbmc = jQuery("#rylbmc").val();
	if(rylbmc == null || rylbmc == ""){
		showAlert("请输入社团人员类别！");
		return false;
	}
	 var url = "stglRylbDmwh.do?method=saveNewRylb";
	    ajaxSubFormWithFun("RylbglForm",url,function(data){
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

//社团人员类别新增保存
function saveUpdateFormrylb(){
	var rylbmc = jQuery("#rylbmc").val();
	if(rylbmc == null || rylbmc == ""){
		showAlert("请输入社团人员类别！");
		return false;
	}
	 var url = "stglRylbDmwh.do?method=saveRylb_update";
	    ajaxSubFormWithFun("RylbglForm",url,function(data){
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