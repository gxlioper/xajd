
var gridSetting = {
		caption:"申请理由维护",
		pager:"pager",
		url:"xszz_sqlywh.do?method=sqlywhQuery",
		colList:[
		   {label:'申请理由代码',name:'sqlydm', index: 'sqlydm',key:true,width:'50%', hidden : true },
		   {label:'申请理由名称',name:'sqlymc', index: 'sqlymc',width:'50%'}
		],
		sortname: "sqlydm",
	 	sortorder: "asc"
	};

//查询
function query(){
	var map = {};
	map["sqlymc"] = jQuery("#sqlymc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add(){
	var url = "xszz_sqlywh.do?method=addSqlywh";
	var title = "增加";
	showDialog(title,350,200,url);
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'xszz_sqlywh.do?method=updateSqlywh&sqlydm='+rows[0]["sqlydm"];
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
				jQuery.post("xszz_sqlywh.do?method=delSqlywh",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
		
	}
}


//新增保存
function saveForm(){
	if (!checkNull("sqlymc")) {
		return false;
	}
	var url = "xszz_sqlywh.do?method=addSqlywh&type=save";
	ajaxSubFormWithFun("sqlyDmwhForm",url,function(data){
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
	if (!checkNull("sqlymc")) {
		return false;
	}
	var url = "xszz_sqlywh.do?method=updateSqlywh&type=update";
	ajaxSubFormWithFun("sqlyDmwhForm",url,function(data){
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