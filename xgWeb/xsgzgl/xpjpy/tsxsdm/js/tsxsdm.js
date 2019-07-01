 var gridSetting = {
				caption:"特殊类型列表",
			pager:"pager",
			url:"xpj_tsxsdm.do?method=tsxsDmList&type=query",
			colList:[
			   {label:'类型代码',name:'lxdm', index: 'lxdm',formatter:lxdmLink,key:true,hidden:true},
			   {label:'类型名称',name:'lxmc', index: 'lxmc',width:'20%'},
			   {label:'类型属性',name:'lxsx', index: 'lxsx',width:'20%'},
			   {label:'类型说明',name:'lxsm', index: 'lxsm',width:'60%'}
			],
			sortname: "lxmc",
		 	sortorder: "asc"
		}

		function lxdmLink(cellValue,rowObject){
			return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
		}


//类型名称查询
function query(){
	var map = {};
	map["lxmc"] = jQuery("#lxmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add(){
	var url = "xpj_tsxsdm.do?method=addTsxsDm";
	var title = "增加特殊学生类型";
	showDialog(title,400,250,url);
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'xpj_tsxsdm.do?method=updateTsxsDm&lxdm='+rows[0]["lxdm"];
		var title = "修改特殊类型";
		showDialog(title,400,250,url);
	}
}

//删除
function del(){
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
				jQuery.post("xpj_tsxsdm.do?method=delTsxsDm",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
		
	}
}


//新增保存
function saveForm(){
	  var lxmc=jQuery("#lxmc").val();
	  if(lxmc==""){
		  showAlert("请将带*的项目填写完整！");
			return false;
	  }
     var url = "xpj_tsxsdm.do?method=addTsxsDm&type=save";
      ajaxSubFormWithFun("TsxsDmForm",url,function(data){
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
function delsaveForm(){
	 var lxmc=jQuery("#lxmc").val();
	  if(lxmc==""){
		  showAlert("请将带*的项目填写完整！");
			return false;
	  }
		var url = "xpj_tsxsdm.do?method=updateTsxsDm&type=update";
		ajaxSubFormWithFun("TsxsDmForm",url,function(data){
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