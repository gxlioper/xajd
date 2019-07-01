var gridSetting = {
		caption:"档案清单模板列表",
		pager:"pager",
		url:"daqdmb.do?method=daqdmbList&type=query",
		colList:[
		   {label:'档案清单模板编号',name:'daqdmb_id', index: 'daqdmb_id',hidden:true},
		   {label:'档案清单模板名称',name:'daqdmb_mc', index: 'daqdmb_mc',formatter:mbLink},
		   {label:'绑定材料数',name:'ybdcls', index: 'ybdcls'},
		   {label:'启用状态代码',name:'qyzt', index: 'qyzt',hidden:true},
		   {label:'启用状态',name:'qyztmc', index: 'qyztmc'},
		   {label:'绑定学生数',name:'ybdxss', index: 'ybdxss'}
		],
		sortname: "",
	 	sortorder: ""
	};
		
		jQuery(function(){
			jQuery("#dataTable").initGrid(gridSetting);
		});
	
		function searchRs(){
			var map = getSuperSearch();
	
			jQuery("#dataTable").reloadGrid(map);
		}
		
		
		function mbLink(cellValue,rowObject){
			return "<a href='javascript:void(0);' class='name' onclick='showDetail(\""+rowObject["daqdmb_id"]+"\");'>"+rowObject["daqdmb_mc"]+"</a>";
		}
		
		function showDetail(daqdmb_id){
			
			showDialog("查看档案清单模板",580,475,"daqdmb.do?method=viewDaqdmb&daqdmb_id="+daqdmb_id);
		}
		
		function addDaqdmb(){
			showDialog("增加档案清单模板",510,475,"daqdmb.do?method=addDaqdmb");			
		}
		
		function updateDaqdmb(){
			var daqdmbId= '';
			var rowsValue = jQuery("#dataTable").getSeletRow();
			 if(rowsValue.length != 1){
				 showAlertDivLayer("请选择一条您要修改的记录！");
				return false;
			}else{
				if(rowsValue[0]["qyzt"]=="1"){
					showAlertDivLayer("已启用，不能修改！");
					return false;
				}
				daqdmbId = rowsValue[0]["daqdmb_id"];
			}
			showDialog("修改档案清单模板",510,475,"daqdmb.do?method=updateDaqdmb&daqdmb_id="+daqdmbId);
		}
		
		function delDaqdmb(){
			var daqdmbIds = '';
			var rowsValue = jQuery("#dataTable").getSeletRow();
			if (rowsValue.length == 0){
				showAlertDivLayer("请选择您要删除的记录！");
				return false;
			}
			for(var i=0;i<rowsValue.length;i++){
				if(rowsValue[i]["qyzt"]=="1"){
					showAlertDivLayer("已启用，不能删除！");
					return false;
				}
				if(i==(rowsValue.length-1)){
					daqdmbIds += rowsValue[i]["daqdmb_id"];
				}else{
					daqdmbIds += rowsValue[i]["daqdmb_id"]+",";
				}
			}
	
			confirmInfo("您确定要删除<font color='red'>"+rowsValue.length +"</font>条记录吗?",function(ty){
				if(ty=="ok"){
					jQuery.post("daqdmb.do?method=delDaqdmb",{daqdmbIds:daqdmbIds},function(data){
						alertInfo(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
					}
				});				
			}
		
			
	function  sfqy(type){
		var daqdmbIds = '';
	var rowsValue = jQuery("#dataTable").getSeletRow();
	if (rowsValue.length == 0){
		if(type=="1"){
			showAlertDivLayer("请选择您要启用的记录！");
		}else{
			showAlertDivLayer("请选择您要停用的记录！");
		}
		return false;
	}
	for(var i=0;i<rowsValue.length;i++){
		if(rowsValue[i]["ybdxss"]!=0 && type=="0"){
			showAlertDivLayer("您选择的记录已绑定学生，不能停用！");
			return false;
		}
		if(i==(rowsValue.length-1)){
			daqdmbIds += rowsValue[i]["daqdmb_id"];
		}else{
			daqdmbIds += rowsValue[i]["daqdmb_id"]+",";
		}
	}
	var message = "";
	if(type=="1"){
		message = "您确定要启用选择的记录吗？";
	}else{
		message = "您确定要停用选择的记录吗？";
	}
	
	confirmInfo(message,function(ty){
		if(ty=="ok"){
	
			jQuery.post("daqdmb.do?method=updateQdmbQyzt",{daqdmbIds:daqdmbIds,qyzt:type},function(data){
				alertInfo(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}
	});
}
