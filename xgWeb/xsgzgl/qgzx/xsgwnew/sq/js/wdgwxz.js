//初始化查询
var gridSetting = {
		caption:"岗位选择列表",
		pager:"pager",
		multiselect:false,
		url:"xsgwsqnew_sq.do?method=wdgwxzCx&type=query",
		colList:[
		   {label:'学年',name:'xn', index: 'xn',hidden:true},
		   {label:'a',name:'gwdm', index: 'gwdm',hidden:true},
		   {label:'用人单位',name:'yrdwmc', index: 'yrdwmc',width:'15%'},
		   {label:'岗位名称',name:'gwmc', index: 'gwmc',width:'10%'},
		   {label:'岗位性质',name:'gwxzmc', index: 'gwxzmc',width:'10%'},
		   {label:'岗位有效时',name:'gwyxs', index: 'gwyxs',width:'10%'},
		   {label:'需求人数',name:'xqrs', index: 'xqrs',width:'10%'},
		   {label:'申请人数',name:'sqrs', index: 'sqrs',width:'10%'},
		   {label:'困难生数',name:'knss', index: 'knss',width:'10%'},
		   {label:'在岗人数',name:'zgrs', index: 'zgrs',width:'10%'},
		   {name:'sfksq', index: 'sfksq',hidden:true},
		   {label:'操作',name:'xh', index: '',width:'15%',noSort:true,formatter:czsz}
		],
		//dblclick:function(rowObject){
			//选择岗位
		//	xzGw(rowObject);
		//},
		sortname: "xn,sfksq",
	 	sortorder: "desc"
	}

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function czsz(cellValue,row){
	if("N"==row["sfksq"]){
	return "<button type='button' class='btn_01' onclick=\"warn();\">选择</button>";
	}
	else{
	 return "<label class='btn_01' onclick=\"xzGw('"+row["gwdm"]+"');\">选择</label>";
	}
	
}
function warn(){
	showAlert("该岗位申请人数已满，请重新选择！");
	return false;
}

//选择岗位
function xzGw(gwdm){
	var gotoPath = jQuery("#gotoPath").val();
	var loadid = jQuery("#loadid").val();
	//alert(gotoPath+loadid);
	if (gotoPath.split("?").length > 1){
		gotoPath = gotoPath+"&gwdm="+gwdm;
	} else {
		gotoPath = gotoPath+"?gwdm="+gwdm;
	}
	var api = frameElement.api, W = api.opener;
	
	if (api){
		if (api.get('childDialog')){
			api.get('parentDialog').jQuery("#"+loadid).load(gotoPath);
			//api.reload(api.get('parentDialog') ,gotoPath);
		} else {
			//W.location=gotoPath;	
			W.jQuery("#"+loadid).load(gotoPath);
		}
	} else if (parent.window){
		window.parent.jQuery("#"+loadid).load(gotoPath);
	}
	
	iFClose();
}
