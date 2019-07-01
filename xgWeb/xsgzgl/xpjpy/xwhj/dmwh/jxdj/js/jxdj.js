var gridSetting = {
				caption:"奖项等级维护列表",
				pager:"pager",
				url:"xpj_jxdj.do?method=jxdjList&type=query",
				colList:[
				   {label:'等级代码',name:'jxdjdm', index: 'jxdjdm',key:true},
				   {label:'等级名称',name:'jxdjmc', index: 'jxdjmc',width:'25%'},
				   {label:'所属类别',name:'jxlbmc', index: 'jxlbmc',width:'25%'},
				   {label:'竞赛方式',name:'jsfsmc', index: 'jsfsmc',width:'25%'}
				],
				sortname: "jxdjdm",
			 	sortorder: "asc"
			}

			function dcmcLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
			}
//查询
function query(){
	var map = {};
	map["jxdjmc"] = jQuery("#jxdjmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add(){
	var url = "xpj_jxdj.do?method=addJxdj";
	var title = "增加奖项等级";
	showDialog(title,350,200,url);
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'xpj_jxdj.do?method=updateJxdj&jxdjdm='+rows[0]["jxdjdm"];
		var title = "修改奖项等级";
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
				jQuery.post("xpj_jxdj.do?method=delJxdj",{values:ids.toString()},function(data){
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

//奖项名次
function goJxmc(){
	var url="pjpy_hjgl_jxmc.do";
	refreshForm(url);
}

//新增保存
function saveForm(){
	
	if(!checkNull("jxdjmc-jxlbdm-jsfs")){
		return false;
	}
   var url = "xpj_jxdj.do?method=addJxdj&type=save";
    ajaxSubFormWithFun("dmwhJxdjForm",url,function(data){
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
	
	if(!checkNull("jxdjmc-jxlbdm-jsfs")){
		return false;
	}
	var url = "xpj_jxdj.do?method=updateJxdj&type=update"
	ajaxSubFormWithFun("dmwhJxdjForm",url,function(data){
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