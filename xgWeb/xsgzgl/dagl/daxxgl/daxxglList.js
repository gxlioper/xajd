

jQuery(function(){
	var gridSetting = {
			caption:"档案信息列表",
			pager:"pager",
			url:"daxxgl.do?method=daxxglList&type=query",
			colList:[
			   {label:'pk',name:'pk', index: 'pk',width:'12%',key:true,hidden:true},
			   {label:'学号',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
			   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
			   {label:'性别',name:'xb', index: 'xb',width:'4%'},
			   {label:'年级',name:'nj', index: 'nj',width:'8%'},
			   {label:jQuery("#xbmc").val(),name:'xymc', index: 'xymc',width:'10%'},
			   {label:'专业',name:'zymc', index: 'zymc',width:'10%'},
			   {label:'班级',name:'bjmc', index: 'bjmc',width:'10%'},
			   {label:'档案转入时间',name:'dazrsj', index: 'dazrsj',width:'8%'},
			   {label:'档案转出时间',name:'dazcsj', index: 'dazcsj',width:'8%'},
			   {label:'档案清单',name:'whzt', index: 'whzt',width:'5%'},
			   {label:'档案清单ID',name:'daqdmb_id', index: 'daqdmb_id',hidden:true}	   
			],
			sortname: "",
		 	sortorder: ""
		};
	jQuery("#dataTable").initGrid(gridSetting);
});

function searchRs(){
	var map = getSuperSearch();

	jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='showDetail(\""+rowObject["pk"]+"\");'>"+cellValue+"</a>";
}

function showDetail(pk){
	
	showDialog("查看档案信息",700,540,"daxxgl.do?method=viewDaxxgl&pk="+pk);
}

function addDaxxgl(){
	showDialog("增加档案信息",700,540,"daxxgl.do?method=addDaxxgl");			
}

function updateDaxxgl(){
	var pk='';
	var rowsValue = jQuery("#dataTable").getSeletRow();
	 if(rowsValue.length != 1){
		 showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	}else{
		pk = rowsValue[0]["pk"];
	}
	showDialog("修改档案信息",700,540,"daxxgl.do?method=updateDaxxgl&pk="+pk);
}

function qdwhDaxxgl(){

	var rows = jQuery("#dataTable").getSeletRow();
	
	// 单条记录维护
	if (rows.length == 1){
		var selectPks = rows[0]["pk"];
		var daqdmb_id = rows[0]["daqdmb_id"];
		// 
		showDialog("绑定清单",750,540,"daxxgl.do?method=bandXsqd&pk="+selectPks+"&daqdmb_id="+daqdmb_id);
		
		// 未选择记录
	}else if (rows.length == 0){
		var map = getSuperSearch();
		var url = "daxxgl.do?method=bandXsqdBatch";
		//高级查询
		url +="&searchTj=";
		url +=map["searchTj"];
		url +="&searchTjz=";
		url +=map["searchTjz"];
		url +="&mhcx_lx=";
		url +=map["mhcx_lx"];
		url +="&searchLx=";
		url +=map["searchLx"];

		//模糊查询
		url +="&input_mhcx=";
		url +=map["input_mhcx"];
		url +="&path=";
		url +=map["path"];					
		url +="&selected=all";
		showDialog("绑定清单",750,540,url);
		
		// 多选记录操作
	} else {
		var ids = jQuery("#dataTable").getSeletIds();
		showDialog("绑定清单",750,540,"daxxgl.do?method=bandXsqdBatch&values="+ids.toString());
	}
}

function delDaxxgl(){
	var pks = '';
	var rowsValue = jQuery("#dataTable").getSeletRow();
	if (rowsValue.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	}
	for(var i=0;i<rowsValue.length;i++){
		if(i==(rowsValue.length-1)){
			pks += rowsValue[i]["pk"];
		}else{
			pks += rowsValue[i]["pk"]+",";
		}
	}
	

	confirmInfo("您确定要删除<font color='red'>"+rowsValue.length +"</font>条记录吗?",function(ty){
		if(ty=="ok"){
			jQuery.post("daxxgl.do?method=delDaxxgl",{pks:pks},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}
	});		
	
}

function dcDaxxgl(){
	customExport("daxxgl.do?method=daxxglList", exportDaxxData,750,500);
}

// 导出方法
function exportDaxxData() {
	setSearchTj();//设置高级查询条件
	var url = "daxxgl.do?method=exportDaxxData&dcclbh=" + "daxxgl.do?method=daxxglList";//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function drDaxxgl(){
	toImportData("IMPORT_N711203");
	return false;
}