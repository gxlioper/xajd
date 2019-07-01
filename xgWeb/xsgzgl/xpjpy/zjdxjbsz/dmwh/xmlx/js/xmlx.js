//查询列表
var gridSetting = {
	caption:"评奖项目类型",
	pager:"pager",
	url:"xpjpy_xmlx.do?method=listXmlx&type=query",
	colList:[
	   {label:'项目类型代码',name:'lxdm', index: 'lxdm',key:true,hidden:true},
	   {label:'项目类型名称',name:'lxmc', index: 'lxmc',width:'50%'}
	],
	sortname: "lxmc",
 	sortorder: "asc"
}

//查询
function query(){
	var map = {};
	map["lxmc"] = jQuery("#lxmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add(){
	var url = "xpjpy_xmlx.do?method=addXmlx";
	var title = "增加项目类型";
	showDialog(title,380,200,url);
}

//新增保存
function saveForm(){
	var lxmc = jQuery("#lxmc").val();
	if(lxmc == null || lxmc == ''){
		showAlert("请填写项目类型名称！");
		return false;
	}
   var url = "xpjpy_xmlx.do?method=saveForAdd";
    ajaxSubFormWithFun("xmlxForm",url,function(data){
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

//删除方法，可支持批量删除
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
				jQuery.post("xpjpy_xmlx.do?method=delXmlx",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
	}
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'xpjpy_xmlx.do?method=updateXmlx&lxdm='+rows[0]["lxdm"];
		var title = "修改项目类型名称";
		showDialog(title,380,200,url);
	}
}

//修改保存
function updateSaveForm(){
	var lxmc = jQuery("#lxmc").val();
	if(lxmc == '' || lxmc == null){
		showAlert("请填写项目类型名称！");
		return false;
	}
	var url = "xpjpy_xmlx.do?method=saveForUpdate"
	ajaxSubFormWithFun("xmlxForm",url,function(data){
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

//前往项目性质
function goXmxz(){
	var url="xpjpy_pjxzdm.do";
	refreshForm(url);
}