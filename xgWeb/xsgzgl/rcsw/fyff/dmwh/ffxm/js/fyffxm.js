var gridSetting = {
				caption:"评奖性质代码",
				pager:"pager",
				url:"rcsw_fyff_ffxm.do?method=ffxmQuery",
				colList:[
				   {label:'发放项目代码',name:'ffxmdm', index: 'ffxmdm',formatter:dcmcLink,key:true,hidden:true},
				   {label:'发放项目',name:'ffxmmc', index: 'ffxmmc',width:'30%'},
				   {label:'发放方式',name:'fffs', index: 'fffs', width:'30%'},
				   {label:'默认发放金额',name:'mrffje', index: 'mrffje', width:'30%'}
				],
				sortname: "ffxmdm",
			 	sortorder: "asc"
			};

			function dcmcLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
			}
//查询
function query(){
	var map = {};
	map["ffxmmc"] = jQuery("#ffxmmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add(){
	var url = "rcsw_fyff_ffxm.do?method=addFfxm";
	var title = "增加";
	showDialog(title,350,200,url);
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'rcsw_fyff_ffxm.do?method=updateFfxm&ffxmdm='+rows[0]["ffxmdm"];
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
				jQuery.post("rcsw_fyff_ffxm.do?method=delFfxm",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
		
	}
}

//go项目类型
function goFftj(){
	var url="rcsw_fyff_dmwh_fftj.do";
	refreshForm(url);
}



//新增保存
function saveForm(){
	
	  var ffxmmc=jQuery("#ffxmmc").val();
	  if(ffxmmc==""){
		  showAlert("请将带<font color='red'>*</font>的项目填写完整！");
			return false;
	  }
	   var url = "rcsw_fyff_ffxm.do?method=addFfxm&type=save";
	    ajaxSubFormWithFun("FyffxmForm",url,function(data){
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
	var ffxmmc=jQuery("#ffxmmc").val();
	if(ffxmmc==""){
		showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		return false;
	}
	var url = "rcsw_fyff_ffxm.do?method=updateFfxm&type=update";
	ajaxSubFormWithFun("FyffxmForm",url,function(data){
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