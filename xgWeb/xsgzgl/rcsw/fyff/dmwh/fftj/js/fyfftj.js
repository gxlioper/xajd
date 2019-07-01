var gridSetting = {
				caption:"评奖性质代码",
				pager:"pager",
				url:"rcsw_fyff_fftj.do?method=fftjQuery",
				colList:[
				   {label:'发放途径代码',name:'fftjdm', index: 'fftjdm',formatter:dcmcLink,key:true,hidden:true},
				   {label:'发放途径',name:'fftj', index: 'fftj',width:'50%'},
				   {label:'发放账号',name:'ffzh', index: 'ffzh', width:'50%'}
				],
				sortname: "fftjdm",
			 	sortorder: "asc"
			};

			function dcmcLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
			}
//查询
function query(){
	var map = {};
	map["fftj"] = jQuery("#fftj").val();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add(){
	var url = "rcsw_fyff_fftj.do?method=addFftj";
	var title = "增加";
	showDialog(title,350,200,url);
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'rcsw_fyff_fftj.do?method=updateFftj&fftjdm='+rows[0]["fftjdm"];
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
				jQuery.post("rcsw_fyff_fftj.do?method=delFftj",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
		
	}
}

//go项目类型
function goFfxm(){
	var url="rcsw_fyff_dmwh_ffxm.do";
	refreshForm(url);
}



//新增保存
function saveForm(){
	
	  var fftj=jQuery("#fftj").val();
	  if(fftj==""){
		  showAlert("请将带<font color='red'>*</font>的项目填写完整！");
			return false;
	  }
	   var url = "rcsw_fyff_fftj.do?method=addFftj&type=save";
	    ajaxSubFormWithFun("FyfftjForm",url,function(data){
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
	var url = "rcsw_fyff_fftj.do?method=updateFftj&type=update";
	ajaxSubFormWithFun("FyfftjForm",url,function(data){
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