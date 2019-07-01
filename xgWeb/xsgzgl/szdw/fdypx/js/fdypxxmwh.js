var gridSetting = {
		caption:"辅导员培训项目维护",
		pager:"pager",
		url:"szdw_fdypxxmwh.do?method=fdypxxmList&type=query",
		colList:[
		   {label:'项目代码',name:'xmdm', index: 'xmdm',width:'1%',key:true,hidden:true},
		   {label:'项目名称',name:'xmmc', index: 'xmmc',width:'20%',formatter:xhLink},
		   {label:'培训地点',name:'pxdd', index: 'pxdd',width:'20%'},
            {label:'组织部门',name:'bmdm', index: 'bmdm',width:'20%',hidden:true},
			{label:'组织部门',name:'zzbm', index: 'zzbm',width:'20%'},
		   {label:'培训时间',name:'pxsj', index: 'pxsj',width:'20%'},
		   {label:'发布时间',name:'fbsj', index: 'fbsj',width:'20%'},
		   {label:'发布人',name:'fbr', index: 'fbr',width:'20%'},
            {label:'培训学时',name:'pxxs', index: 'pxxs',width:'20%'}
		],
		sortname: "fbsj",
	 	sortorder: "desc"
	}
var gridSetting_xz = {
		caption:"辅导员培训项目",
		pager:"pager",
		url:"szdw_fdypxxmwh.do?method=fdypxxmsqList&type=query",
		colList:[
		   {label:'项目代码',name:'xmdm', index: 'xmdm',width:'1%',key:true,hidden:true},
		   {label:'项目名称',name:'xmmc', index: 'xmmc',width:'20%'},
		   {label:'培训地点',name:'pxdd', index: 'pxdd',width:'20%'},
            {label:'组织部门',name:'bmdm', index: 'bmdm',width:'20%',hidden:true},
            {label:'组织部门',name:'zzbm', index: 'zzbm',width:'20%'},
		   {label:'培训时间',name:'pxsj', index: 'pxsj',width:'20%'},
		   {label:'发布时间',name:'fbsj', index: 'fbsj',width:'20%'},
		   {label:'发布人',name:'fbr', index: 'fbr',width:'20%'},
            {label:'培训学时',name:'pxxs', index: 'pxxs',width:'20%'}
		],
		sortname: "fbsj",
	 	sortorder: "desc"
	}
function xhLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='view(\""+row["xmdm"]+"\")'>"+cellValue+"</a>";
}
function add(){
	showDialog("增加培训项目",700,350,"szdw_fdypxxmwh.do?method=fdypxxmAdd");
}
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条您要修改的记录！");
	} else {
		showDialog("修改培训项目",700,350,'szdw_fdypxxmwh.do?method=fdypxxmUpdate&xmdm='+rows[0]["xmdm"]);
	}
}

function deletes(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		alertInfo("请选择您要删除的记录！");
	} else {
		
		confirmInfo("您确定要删除"+ids.length +"条记录吗?",function(ty){
			//alert(ty);
			if(ty=="ok"){
				jQuery.post("szdw_fdypxxmwh.do?method=fdypxxmDelete",{values:ids.toString()},function(data){
					alertInfo(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}
		});
		
		
	}
	
}
function view(xmdm){
	showDialog("查看培训项目",600,260,'szdw_fdypxxmwh.do?method=fdypxxmView&xmdm='+xmdm+"&view_type=1",null);
}
function save(method){
	if(yanzheng()){
		var url = "szdw_fdypxxmwh.do?method="+method+"&type=save";
		ajaxSubFormWithFun("demoForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
	}
}
function yanzheng(){
	var xmmc = jQuery("#xmmc").val();
	var pxdd = jQuery("#pxdd").val();
	var sqkg = jQuery("input[name='sqkg']:checked").val();
	var sqkssj = jQuery("#sqkssj").val();
	var sqjssj = jQuery("#sqjssj").val();
	var pxsj = jQuery("#pxsj").val();
	var pxjj = jQuery("#pxjj").val();
	
	if(xmmc==""){
		alertInfo("培训项目名称不能为空！");
	}else if(pxdd==""){
		alertInfo("请填写培训地点");
	}else if(sqkg==undefined){
		alertInfo("请选择培训开关");
	}
//	else if(sqkssj=="" || sqjssj ==""){
//		alertInfo("请输入申请起止时间");
//	}
	else if(!sqjssj ==""&&sqkssj>sqjssj){
		alertInfo("申请开始时间不能大于结束时间");
	}else if(pxsj==""){
		alertInfo("请输入培训时间");
	}else if(pxjj.length>=2000){
		alertInfo("培训简介不能超过2000字");
	}else{
		return true;
	}
	return false;
}
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
//选择项目
function xzxm(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条您要申请的项目！");
	} else {
		frameElement.api.get('parentDialog').jQuery("#xzpxxm").load('szdw_fdypxxmwh.do?method=fdypxxmxzView&xmdm='+rows[0]["xmdm"],function(){
			iFClose();
		});
	}
}