var gridSetting = {
				caption:"奖项名次维护列表",
				pager:"pager",
				url:"xpj_jxmc.do?method=jxmcList&type=query",
				colList:[
				   {label:'名次代码',name:'jxmcdm', index: 'jxmcdm',key:true},
				   {label:'名次名称',name:'jxmcmc', index: 'jxmcmc'},
				   {label:'所属类别',name:'jxlbmc', index: 'jxlbmc'},
				   {label:'所属等级',name:'jxdjmc', index: 'jxdjmc'},
				   {label:'竞赛方式',name:'jsfsmc', index: 'jsfsmc'},
				   {label:'金额',name:'je', index: 'je'}
				],
				sortname: "jxmcdm",
			 	sortorder: "asc"
			}

			function dcmcLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
			}
//查询
function query(){
	var map = {};
	map["jxmcmc"] = jQuery("#jxmcmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add(){
	var url = "xpj_jxmc.do?method=addJxmc";
	var title = "增加奖项名次";
	showDialog(title,350,250,url);
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'xpj_jxmc.do?method=updateJxmc&jxmcdm='+rows[0]["jxmcdm"];
		var title = "修改奖项名次";
		showDialog(title,350,250,url);
	}
}

//删除
function del(){
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
				jQuery.post("xpj_jxmc.do?method=delJxmc",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
		
	}
}

//奖项类别
function goJxlb(){
	var url="pjpy_hjgl_dmwh.do";
	refreshForm(url);
}

//奖项等级
function goJxdj(){
	var url="pjpy_hjgl_jxdj.do";
	refreshForm(url);
}

//新增保存
function saveForm(){
	
	if(!checkNull("jxmcmc-jxlbdm-jsfs-jxdjdm")){
		return false;
	}
   var url = "xpj_jxmc.do?method=addJxmc&type=save";
    ajaxSubFormWithFun("dmwhJxmcForm",url,function(data){
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
	
	if(!checkNull("jxmcmc-jxlbdm-jsfs-jxdjdm")){
		return false;
	}
	var url = "xpj_jxmc.do?method=updateJxmc&type=update"
	ajaxSubFormWithFun("dmwhJxmcForm",url,function(data){
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

//金额校验不能以0开头
function ismoney(obj){
	if(obj.value.indexOf('0') == 0){
		showAlert("金额不能以0开头！",{},{"clkFun":function(){
				jQuery(obj).val("");
				jQuery(obj).focus();
		}});
	}
}