var gridSetting = {
	caption:"评奖项目类型",
	pager:"pager",
	url:"xqdmwh.do?method=getXqdmList&type=query",
	colList:[
	   {label:'学情代码',name:'xqdm', index: 'xqdm',key:true},
	   {label:'学情名称',name:'xqmc', index: 'xqmc',width:'50%'}
	],
	sortname: "xqdm",
 	sortorder: "asc"
}

//增加
function add(){
	var url = "xqdmwh.do?method=addXqdm";
	var title = "增加学情代码维护";
	showDialog(title,350,200,url);
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var flg = true;
		jQuery.ajaxSetup({async:false});
		jQuery.post("xqdmwh.do?method=isOperate", {xqdm:rows[0]["xqdm"]}, function(data) {
			if(data != null && data!= ""){
				flg = false;					
			}	      		
			}, 'json');
		jQuery.ajaxSetup({async:true});
		if(!flg){
			showAlertDivLayer("学情代码已使用，不能修改或删除！");
			return false;
		}
		var url = 'xqdmwh.do?method=updateXqdm&xqdm='+rows[0]["xqdm"];
		var title = "修改学情代码维护";
		showDialog(title,350,200,url);
	}
}

//删除
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var flag = false;
		jQuery.ajaxSetup({async:false});
		jQuery.each(rows,function(i,n) {			
			jQuery.post("xqdmwh.do?method=isOperate", {xqdm:n["xqdm"]}, function(data) {
				if(data != null && data!= ""){
					flag = true;					
				}	      		
				}, 'json');
				if(flag){
					return false;
				}																	
		})
		jQuery.ajaxSetup({async:true});
		if(flag) {
			showAlertDivLayer("学情代码已使用，不能修改或删除！");
			return false;
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
				jQuery.post("xqdmwh.do?method=delXqdm",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
		
	}
}

//查询
function query(){
	var map = {};
	map["xqmc"] = jQuery.trim(jQuery("#xqmc").val());
	jQuery("#dataTable").reloadGrid(map);
}

//修改保存
function updSaveForm(){
	var xqdm = jQuery("#xqdm").val();
	var xqmc=jQuery("#xqmc").val();
	if(xqdm==""|| xqmc=="" ){
		showAlert("请将带*的项目填写完整！");
		return false;
	}
	var url = "xqdmwh.do?method=updateXqdm&type=update"
	ajaxSubFormWithFun("xqdmForm",url,function(data){
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
	  var xqmc=jQuery("#xqmc").val();
	  if(xqmc==""){
		  showAlert("请将带*的项目填写完整！");
			return false;
	  }
   var url = "xqdmwh.do?method=addXqdm&type=save";
    ajaxSubFormWithFun("xqdmForm",url,function(data){
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