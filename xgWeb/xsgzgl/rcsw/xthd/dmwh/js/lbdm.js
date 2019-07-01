var lbmc = "";
var hdggmc = "";
var gridSetting = {
				caption:"",
				pager:"pager",
				url:"rcsw_txhd.do?method=lbdmList&type=query&lbmc="+lbmc,
				colList:[
				   {label:'类别代码',name:'lbdm', index: 'lbdm',formatter:lbmcLink,key:true,hidden:true},
				   {label:'类别名称',name:'lbmc', index: 'lbmc',width:'30%'},
				   {label:'类别说明',name:'lbsm', index: 'lbsm',width:'40%'},
				   {label:'是否使用',name:'sfsy', index: 'sfsy',width:'30%'}
				],
				sortname: "lbdm",
			 	sortorder: "asc"
}

var gridSetting1 = {
		caption:"",
		pager:"pager",
		url:"rcsw_txhd.do?method=getHdgglist&type=query&hdggmc="+hdggmc,
		colList:[
		   {label:'活动规格代码',name:'hdggdm', index: 'hdggdm',width:'50%',key:true},
		   {label:'活动规格名称',name:'hdggmc', index: 'hdggmc',width:'50%'},
		   {label:'是否使用',name:'sfsy', index: 'sfsy',hidden:true}
		],
		sortname: "hdggdm",
	 	sortorder: "asc"
}

function lbmcLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
}

//查询
//function query(){
//	var map = {}; 
//	map["lbmc"] = jQuery("#lbmc").val();
//	jQuery("#dataTable").reloadGrid(map);
//}

//增加
function add(){
	var tabType = jQuery("#tabType").val();
	if(tabType == 'lbwh'){
		var url = "rcsw_txhd.do?method=addLbdm";
		var title = "增加类别";
	}else{
		var url = "rcsw_txhd.do?method=addHdgg";
		var title = "增加规格";
	}
	showDialog(title,400,250,url);
}

//修改
function update(){
	var tabType = jQuery("#tabType").val();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	}
	if(tabType == 'lbwh'){
		var url = 'rcsw_txhd.do?method=updateLbdm&lbdm='+rows[0]["lbdm"];
		var title = "修改类别";
	}else{
		var url = 'rcsw_txhd.do?method=updateHdgg&hdggdm='+rows[0]["hdggdm"]+'&hdggmc='+rows[0]["hdggmc"];
		var title = "修改规格";
	}
	showDialog(title,400,250,url);
}

//删除
function del(){
	var tabType = jQuery("#tabType").val();
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	} 
	//console.log(tabType);
	if(tabType == 'lbwh'){
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("rcsw_txhd.do?method=delLbdm",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
	    }});
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		var flag = true
		var hdggmc = "";
		for(var i=0;i<rows.length;i++){
			if(rows[i]['sfsy']=='是'){
				var flag = false;
				hdggmc = rows[i]['hdggmc'];
				return false;
			}
		}
		if(!flag){
			showAlertDivLayer(rows[i]['hdggmc']+"正在使用中，不允许删除！");
			return false;
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("rcsw_txhd.do?method=delhdgg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
	    }});
	}
}

//新增保存
function saveForm(){
	  var lbmc=jQuery("#lbmc").val();
	  if(lbmc==""){
		  showAlert("请将带<font color='#ff0000;'>*</font>的项目填写完整！");
			return false;
	  }
	  if(jQuery("#lbsm").val().length > 200){
		  	showAlert("类别说明不能超过200字！");
			return false;
	  }
   var url = "rcsw_txhd.do?method=addLbdm&type=save";
    ajaxSubFormWithFun("TxhdDmwhForm",url,function(data){
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
function updSaveForm(){
	var lbmc=jQuery("#lbmc").val();
	if(lbmc==""){
		showAlert("请将带<font color='#ff0000;'>*</font>的项目填写完整！");
		return false;
	}
	if(jQuery("#lbsm").val().length > 200){
	  	showAlert("类别说明不能超过200字！");
		return false;
  }
	var url = "rcsw_txhd.do?method=updateLbdm&type=update"
	ajaxSubFormWithFun("TxhdDmwhForm",url,function(data){
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

//切换标签页选项卡
function selectTab(obj, tabType) {
	var map = {};
	jQuery("#tabType").val(tabType);
	if (tabType == "lbwh") {
		map["tabType"]="lbwh";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
		jQuery("#th_hdggmc").hide(); 
		jQuery("#td_hdggmc").hide(); 
		jQuery("#th_lbmc").show(); 
		jQuery("#td_lbmc").show(); 
		jQuery("#spanmc").text("类别维护列表");
	}else if(tabType == 'hdgg') {
		map["tabType"]="hdgg";
		gridSetting1["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting1);
		jQuery("#th_lbmc").hide(); 
		jQuery("#td_lbmc").hide(); 
		jQuery("#th_hdggmc").show(); 
		jQuery("#td_hdggmc").show(); 
		jQuery("#spanmc").text("活动规格维护列表");
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

//设置查询url路径以及查询条件
function setSearch_url(){
	lbmc = encodeURI(encodeURI(jQuery("#lbmc").val()));
	if(jQuery("#tabType").val() == 'lbwh'){
		gridSetting.url = "rcsw_txhd.do?method=lbdmList&type=query&lbmc="+lbmc;
	}else if(jQuery("#tabType").val() == 'hdgg'){
		hdggmc = encodeURI(encodeURI(jQuery("#hdggmc").val()));
		gridSetting1.url = "rcsw_txhd.do?method=getHdgglist&type=query&hdggmc="+hdggmc;
	}
}

//查询键盘事件绑定
function keydown_search(keyEvent){
	  if(keyEvent.keyCode == 13){
		  setSearch_url();
		  searchRs();
	  }else{
		  return false;
	  }
}

//规格新增保存
function saveHdggNew(){
  var hdggmc =jQuery("#hdggmc").val();
  if(hdggmc=="" || hdggmc == null){
	  showAlert("活动规格名称不得为空！");
		return false;
  }
  var url = "rcsw_txhd.do?method=saveAddNewHdgg";
   ajaxSubFormWithFun("TxhdDmwhForm",url,function(data){
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

//规格修改保存
function saveHdggUpdate(){
  var hdggmc =jQuery("#hdggmc").val();
  if(hdggmc=="" || hdggmc == null){
	  showAlert("活动规格名称不得为空！");
		return false;
  }
  var url = "rcsw_txhd.do?method=saveUpdateNewHdgg";
  ajaxSubFormWithFun("TxhdDmwhForm",url,function(data){
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