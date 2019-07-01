

function dcmcLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
}


//增加
function add(){
	var url = "xpj_pjdm.do?method=addXmlx";
	var title = "增加项目类型";
	showDialog(title,380,200,url);
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'xpj_pjdm.do?method=updateXmlx&xmlxdm='+rows[0]["xmlxdm"];
		var title = "修改项目类型名称";
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
				jQuery.post("xpj_pjdm.do?method=delXmlx",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
		
	}
}

//前往项目性质
function goXmxz(){
	var url="pj_pjxzdm.do";
	refreshForm(url);
}

//前往班级大类
function goBjdl(){
	var url="pj_bjdldm.do";
	refreshForm(url);
}

//查询
function query(){
	var map = {};
	map["xmlxmc"] = jQuery("#xmlxmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//修改保存
function updSaveForm(){
	var xmlxmc=jQuery("#xmlxmc").val();
	if(xmlxmc==""){
		showAlert("请将带*的项目填写完整！");
		return false;
	}
	var url = "xpj_pjdm.do?method=updateXmlx&type=update"
	ajaxSubFormWithFun("xpjPjdmModel",url,function(data){
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
	  var xmlxmc=jQuery("#xmlxmc").val();
	  if(xmlxmc==""){
		  showAlert("请将带*的项目填写完整！");
			return false;
	  }
   var url = "xpj_pjdm.do?method=addXmlx&type=save";
    ajaxSubFormWithFun("xpjPjdmModel",url,function(data){
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