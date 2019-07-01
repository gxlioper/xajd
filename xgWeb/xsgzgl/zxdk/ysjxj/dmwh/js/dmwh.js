var gridSetting = {
				caption:"111",
				pager:"pager",
				url:"ysjxj_dmwh.do?method=dmwhQuery",
				colList:[
				   {label:'资金来源代码',name:'zjlydm', index: 'zjlydm',formatter:dcmcLink,key:true,hidden:true},
				   {label:'资金来源名称',name:'zjlymc', index: 'zjlymc',width:'50%'}
				],
				sortname: "zjlydm",
			 	sortorder: "asc"
			};
	function dcmcLink(cellValue,rowObject){
		return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
	}
//查询
function query(){
	var map = {};
	map["zjlymc"] = jQuery("#zjlymc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add(){
	var url = "ysjxj_dmwh.do?method=dmwhAdd";
	var title = "增加";
	showDialog(title,350,200,url);
}

//增加保存方法
function saveForm(){
	  var zjlymc = jQuery("#zjlymc").val();
	  if(zjlymc == ""||zjlymc == null){
		  showAlert("请将带<font color='red'>*</font>的项目填写完整！");
			return false;
	  }
	   var url = "ysjxj_dmwh.do?method=dmwhAdd&type=save";
	    ajaxSubFormWithFun("dmwhForm",url,function(data){
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

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'ysjxj_dmwh.do?method=dmwhUpdate&zjlydm='+rows[0]["zjlydm"];
		var title = "修改资金来源名称";
		showDialog(title,350,200,url);
	}
}

//修改保存
function updSaveForm(){
	var zjlymc=jQuery("#zjlymc").val();
	if(zjlymc == ""||zjlymc == null){
		showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		return false;
	}
	var url = "ysjxj_dmwh.do?method=dmwhUpdate&type=update";
	ajaxSubFormWithFun("dmwhForm",url,function(data){
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

//删除
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
				jQuery.post("ysjxj_dmwh.do?method=dmwhDelete",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
	}
}