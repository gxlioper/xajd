var gridSetting = {
	caption:"奖项类别维护列表",
	pager:"pager",
	url:"xpj_jxlb.do?method=jxlbList&type=query",
	colList:[
	   {label:'类别代码',name:'jxlbdm', index: 'jxlbdm',key:true},
	   {label:'类别名称',name:'jxlbmc', index: 'jxlbmc',width:'50%'}
	],
	sortname: "jxlbdm",
 	sortorder: "asc"
}

function jxlbLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
}


//增加
function add(){
	var url = "xpj_jxlb.do?method=addJxlb";
	var title = "增加奖项类别";
	showDialog(title,350,180,url);
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'xpj_jxlb.do?method=updateJxlb&jxlbdm='+rows[0]["jxlbdm"];
		var title = "修改奖项类别";
		showDialog(title,350,180,url);
	}
}

//删除
function del(){
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
				jQuery.post("xpj_jxlb.do?method=delJxlb",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
		
	}
}

//奖项等级
function goJxdj(){
	var url="pjpy_hjgl_jxdj.do";
	refreshForm(url);
}

//奖项名次
function goJxmc(){
	var url="pjpy_hjgl_jxmc.do";
	refreshForm(url);
}

//查询
function query(){
	var map = {};
	map["jxlbmc"] = jQuery("#jxlbmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//修改保存
function updSaveForm(){	
	
	if(!checkNull("jxlbmc")){
		return false;
	}
	
	var url = "xpj_jxlb.do?method=updateJxlb&type=update"
	ajaxSubFormWithFun("dmwhJxlbForm",url,function(data){
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

//新增保存

function saveForm(){
	  
	if(!checkNull("jxlbmc")){
		return false;
	}
	
   var url = "xpj_jxlb.do?method=addJxlb&type=save";
    ajaxSubFormWithFun("dmwhJxlbForm",url,function(data){
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