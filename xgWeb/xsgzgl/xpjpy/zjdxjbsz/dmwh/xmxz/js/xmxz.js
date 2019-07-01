//查询列表
var gridSetting = {
	caption:"评奖项目类型",
	pager:"pager",
	url:"xpjpy_xmxz.do?method=listXmxz&type=query",
	colList:[
	   {label:'项目性质代码',name:'xzdm', index: 'xzdm',key:true,hidden:true},
	   {label:'项目性质名称',name:'xzmc', index: 'xzmc',width:'50%'}
	],
	sortname: "xzmc",
 	sortorder: "asc"
}

//查询
function query(){
	var map = {};
	map["xzmc"] = jQuery("#xzmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add(){
	var url = "xpjpy_xmxz.do?method=addXmxz";
	var title = "增加项目性质";
	showDialog(title,380,200,url);
}

//新增保存
function saveForm(){
	
  var xzmc = jQuery("#xzmc").val();
  if(xzmc == '' || xzmc == null){
	showAlert("请填写项目性质名称！");
	return false;
  }
   var url = "xpjpy_xmxz.do?method=saveForAdd";
    ajaxSubFormWithFun("xmxzForm",url,function(data){
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
				jQuery.post("xpjpy_xmxz.do?method=delXmxz",{values:ids.toString()},function(data){
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
		var url = 'xpjpy_xmxz.do?method=updateXmxz&xzdm='+rows[0]["xzdm"];
		var title = "修改项目性质名称";
		showDialog(title,380,200,url);
	}
}

//修改保存
function updateSaveForm(){
	var xzmc = jQuery("#xzmc").val();
	if(xzmc == "" || xzmc == null){
		showAlert("请填写项目性质名称！");
		return false;
	}
	var url = "xpjpy_xmxz.do?method=saveForUpdate"
	ajaxSubFormWithFun("xmxzForm",url,function(data){
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

//前往项目类型
function goXmlx(){
	var url="xpjpy_dmwh.do";
	refreshForm(url);
}