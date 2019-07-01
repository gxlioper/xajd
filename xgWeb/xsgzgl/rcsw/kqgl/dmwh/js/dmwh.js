
var gridSetting = {
		caption:"考勤类型代码",
		pager:"pager",
		url:"rcsw_kqgl_dmwh.do?method=kqlxQuery",
		colList:[
		   {label:'考勤代码',name:'kqlxdm', index: 'kqlxdm',key:true,width:'50%'},
		   {label:'考勤名称',name:'kqlxmc', index: 'kqlxmc',width:'50%'}
		],
		sortname: "kqlxdm",
	 	sortorder: "asc"
	};

//查询
function query(){
	var map = {};
	map["kqlxmc"] = jQuery("#kqlxmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add(){
	var url = "rcsw_kqgl_dmwh.do?method=addKqlx";
	var title = "增加";
	showDialog(title,350,200,url);
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'rcsw_kqgl_dmwh.do?method=updateKqlx&kqlxdm='+rows[0]["kqlxdm"];
		var title = "修改";
		showDialog(title,350,200,url);
	}
}


//删除
function del(){
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
				jQuery.post("rcsw_kqgl_dmwh.do?method=delKqlx",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
		
	}
}


//新增保存
function saveForm(){
	if (!checkNull("kqlxmc")) {
		return false;
	}
	var url = "rcsw_kqgl_dmwh.do?method=addKqlx&type=save";
	ajaxSubFormWithFun("KqlxForm",url,function(data){
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
	if (!checkNull("kqlxmc")) {
		return false;
	}
	var url = "rcsw_kqgl_dmwh.do?method=updateKqlx&type=update";
	ajaxSubFormWithFun("KqlxForm",url,function(data){
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