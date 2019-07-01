

jQuery(function(){
	var gridSetting = {
			caption:"证件补办结果列表",
	pager:"pager",
	url:"rcsw_xszbb_bbjggl.do?method=xszbbjgManage&type=query",
	colList:[
	   {label:'key',name:'bbjgid', index: 'bbjgid',key:true ,hidden:true},
		{label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
		{label:'姓名',name:'xm', index: 'xm',width:'8%'},
		{label:'年级',name:'nj', index: 'nj',width:'5%'},
		{label:jQuery("#xbmc").val(),name:'xymc', index: 'xymc',width:'10%'},
		{label:'专业',name:'zymc', index: 'zymc',width:'13%'},
		{label:'班级',name:'bjmc', index: 'bjdm',width:'8%'},
		{label:'申请时间',name:'sqsj', index: 'sqsj',width:'10%'},
		{label:'补办证件',name:'xszbblxmc', index: 'xszbblxmc',width:'8%'},
		{label:'领取',name:'sflq', index: 'sflq',width:'8%'},
		{label:'流程数据',name:'sjly', index: 'sjly',hidden:true}
	],
	sortname: "sqsj",
 	sortorder: "desc"
}
	var gridSetting1 = {
			caption:"证件补办结果列表",
			pager:"pager",
			url:"rcsw_xszbb_bbjggl.do?method=xszbbjgManage&type=query",
			colList:[
			        {label:'key',name:'bbjgid', index: 'bbjgid',key:true ,hidden:true},
					{label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
					{label:'姓名',name:'xm', index: 'xm',width:'8%'},
					{label:'年级',name:'nj', index: 'nj',width:'5%'},
					{label:jQuery("#xbmc").val(),name:'xymc', index: 'xymc',width:'10%'},
					{label:'专业',name:'zymc', index: 'zymc',width:'13%'},
					{label:'班级',name:'bjmc', index: 'bjdm',width:'8%'},
					{label:'申请时间',name:'sqsj', index: 'sqsj',width:'10%'},
					{label:'补办证件',name:'xszbblxmc', index: 'xszbblxmc',width:'8%'},
					{label:'领取',name:'sflq', index: 'sflq',width:'8%'},
					{label:'审核完成时间',name:'shwcsj', index: 'shwcsj',width:'8%'},
					{label:'流程数据',name:'sjly', index: 'sjly',hidden:true}
					],
			sortname: "sqsj",
			sortorder: "desc"
	}
	if(jQuery("#xxdm").val() == '13011'){//青岛酒店个性化
		jQuery("#dataTable").initGrid(gridSetting1);
	}else{		
		jQuery("#dataTable").initGrid(gridSetting);			
	}
	
});

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function add(){
	var url = "rcsw_xszbb_bbjggl.do?method=addXszbbsqjg";
	var title = "增加证件补办结果信息";
	showDialog(title,790,525,url);
}



function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	var sjly = rows[0]["sjly"];
	
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else if(sjly == '1'){
		showAlertDivLayer("流程数据不能修改！");
	}else {
		var url = 'rcsw_xszbb_bbjggl.do?method=updateXszbbjg&bbjgid='+ rows[0]["bbjgid"]
		+ '&xh=' + rows[0]["xh"];
		var title = "修改证件补办结果信息";
		showDialog(title, 720, 525, url);
	}

}

function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	}else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("流程数据不能删除！");
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("rcsw_xszbb_bbjggl.do?method=delXszbbjg", {
					values : ids.toString()
				}, function(data) {
					var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+="流程数据不能修改！";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});		
	}
}

function xszbbjgView(bbjgid, xh) {
	showDialog("证件补办结果查询", 720, 460, "rcsw_xszbb_bbjggl.do?method=viewOneXszbbjg&bbjgid=" + bbjgid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='xszbbjgView(\""
			+ rowObject["bbjgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

var DCCLBH = "rcsw_xszbb_bbjg.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, xszbbjgExportData);
}

// 导出方法
function xszbbjgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "rcsw_xszbb_bbjggl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//领取
function lingq(){
	
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length ==0){
		showAlertDivLayer("请选择您要领取的记录！");
	}else if (rows.length ==1){

		var ids = jQuery("#dataTable").getSeletIds();
		showDialog("证件领取",400,250,"rcsw_xszbb_bbjggl.do?method=lingqXszbbjg&ids="+ids);
	}else {
		showDialog("证件领取",400,250,"rcsw_xszbb_bbjggl.do?method=lingqXszbbjg");
	}
}

//西安培华学生证打印
function dyXszXaph(csdm) {
	 var rows = jQuery("#dataTable").getSeletRow();
	
	 if (rows.length == 0){
		showAlertDivLayer("请选择您要打印的记录！");
	 } else {
		 var  flag = true;
		 var  ids = "";
		 jQuery(rows).each(function(i,row){
			 if(row["xszbblxmc"] != "学生证"){
				 flag = false;
				 return false;
			 }
			 ids += row["xh"];
			 if(i != rows.length-1){
				 ids += ",";
			 }
		 })
		 if(!flag){
			 showAlertDivLayer("请选取学生证进行打印！");
			 return false;
		 }
		 jQuery.post("xsxx_xsxxgl.do?method=dyXszXaph",{csdm:csdm, value:ids},function(data){
				 var csz = data["csz"];
				 if(csz == null){
					 showAlertDivLayer("学生信息未填写完整，请填写后再打印！");
					 return false;
				 }else{
					// 初始化组件
					var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
					LODOP.PRINT_INIT("打印");
					// 布局模板元素
					eval(csz);
					// 打印预览
					LODOP.PREVIEW();
				 }
			},'json');
     }
}


function getWord(){
	 var rows = jQuery("#dataTable").getSeletRow();
	 if (rows.length == 0){
		showAlertDivLayer("请选择一条记录！");
	 } else if (rows.length > 1){
		var url="rcsw_xszbb_bbjggl.do?method=getZxzmZip";
		var ids = jQuery("#dataTable").getSeletIds();
		var url= url+"&value="+ids;
		window.open(url);
	 } else {
		var url="rcsw_xszbb_bbjggl.do?method=getZxzmWord";
		var url= url+"&xh="+rows[0]["xh"];
		window.open(url);
    }
}
