var gridSetting = {
				caption:"评奖项目性质",
				pager:"pager",
				url:"xpj_pjxzdm.do?method=viewPjxzdmList&type=query",
				colList:[
				   {label:'项目性质代码',name:'xmxzdm', index: 'xmxzdm',formatter:dcmcLink,key:true,hidden:true},
				   {label:'项目性质名称',name:'xmxzmc', index: 'xmxzmc',width:'50%'}
				],
				sortname: "xmxzdm",
			 	sortorder: "asc"
			}

			function dcmcLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
			}
//查询
function query(){
	var map = {};
	map["xmxzmc"] = jQuery("#xmxzmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add(){
	var url = "xpj_pjxzdm.do?method=addPjxzdm";
	var title = "增加项目性质";
	showDialog(title,380,200,url);
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'xpj_pjxzdm.do?method=updatePjxzdm&xmxzdm='+rows[0]["xmxzdm"];
		var title = "修改项目性质名称";
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
				jQuery.post("xpj_pjxzdm.do?method=delPjxzdm",{values:ids.toString()},function(data){
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

//前往班级大类
function goBjdl(){
	var url="pj_bjdldm.do";
	refreshForm(url);
}

//新增保存
function saveForm(){
	  var xmlxmc=jQuery("#xmxzmc").val();
	  if(xmlxmc==""){
		  showAlert("请将带*的项目填写完整！");
			return false;
	  }
   var url = "xpj_pjxzdm.do?method=addPjxzdm&type=save";
    ajaxSubFormWithFun("pjxzdmForm",url,function(data){
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
	var xmxzmc=jQuery("#xmxzmc").val();
	if(xmxzmc==""){
		showAlert("请将带*的项目填写完整！");
		return false;
	}
	var url = "xpj_pjxzdm.do?method=updatePjxzdm&type=update"
	ajaxSubFormWithFun("pjxzdmForm",url,function(data){
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