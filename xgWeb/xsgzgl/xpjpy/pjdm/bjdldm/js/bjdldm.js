var gridSetting = {
				caption:"评奖班级大类",
				pager:"pager",
				url:"xpj_bjdldm.do?method=viewBjdldmList&type=query",
				colList:[
				   {label:'班级大类代码',name:'lbdm', index: 'lbdm',formatter:dcmcLink,key:true,hidden:true},
				   {label:'班级大类名称',name:'lbmc', index: 'lbmc',width:'50%'}
				],
				sortname: "lbmc",
			 	sortorder: "asc"
			};

			function dcmcLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
			}
//查询
function query(){
	var map = {};
	map["lbmc"] = jQuery("#lbmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add(){
	var url = "xpj_bjdldm.do?method=addBjlbdm";
	var title = "增加班级大类";
	showDialog(title,380,200,url);
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'xpj_bjdldm.do?method=updateBjlbdm&lbdm='+rows[0]["lbdm"];
		var title = "修改班级大类名称";
		showDialog(title,380,200,url);
	}
}


//删除
function del(){
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
				jQuery.post("xpj_bjdldm.do?method=deleteBjlbdm",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
		
	}
}

//go项目类型
function goXmlx(){
	var url="pj_dmwh.do";
	refreshForm(url);
}

//前往项目性质
function goXmxz(){
	var url="pj_pjxzdm.do";
	refreshForm(url);
}

//新增保存
function saveForm(){
	  var lbmc=jQuery("#lbmc").val();
	  if(lbmc==""){
		  showAlert("请将带*的项目填写完整！");
			return false;
	  }
   var url = "xpj_bjdldm.do?method=addBjlbdm&type=save";
    ajaxSubFormWithFun("bjdldmForm",url,function(data){
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
		showAlert("请将带*的项目填写完整！");
		return false;
	}
	var url = "xpj_bjdldm.do?method=updateBjlbdm&type=update";
	ajaxSubFormWithFun("bjdldmForm",url,function(data){
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