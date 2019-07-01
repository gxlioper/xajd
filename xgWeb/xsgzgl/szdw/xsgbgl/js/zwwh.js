var gridSetting = {
		caption:"学生干部职务列表",
		pager:"pager",
		url:"szdw_xsgb_zwwh.do?method=zwwhList&type=query",
		colList:[
		   {label:'zwid',name:'zwid', index: 'zwid',width:'20%',key:true,hidden:true},
		   {label:'职务名称',name:'zwmc', index: 'zwmc',width:'20%',formatter:zwmcLink},
		   {label:'职务职责',name:'zwzz', index: 'zwzz',width:'20%'},
		   {label:'职务类型',name:'lxmc', index: 'lxmc',width:'20%'},
		   {label:'备注',name:'bz', index: 'bz',width:'20%'}
		],
		sortname: "lxdm",
	 	sortorder: "desc"
}
function add(){
	showDialog("增加学生干部职务",700,400,"szdw_xsgb_zwwh.do?method=zwwhAdd");
}
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条您要修改的记录！");
	} else {
		showDialog("修改学生干部职务",700,300,'szdw_xsgb_zwwh.do?method=zwwhUpdate&zwid='+rows[0]["zwid"]);
	}
}
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function deletes(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		alertInfo("请选择您要删除的记录！");
	} else {
		confirmInfo("您确定要删除"+ids.length +"条记录吗?",function(ty){
			//alert(ty);
			if(ty=="ok"){
				jQuery.post("szdw_xsgb_zwwh.do?method=zwwhDelete",{values:ids.toString()},function(data){
					alertInfo(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}
		});
	}
}
function save(method){
	if(yanzheng()){
		var url = "szdw_xsgb_zwwh.do?method="+method+"&type=save";
		ajaxSubFormWithFun("demoForm",url,function(data){
			showAlert(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
	}
}
function yanzheng(){
	var zwmc = jQuery("#zwmc").val();
	var lxdm = jQuery("#lxdm").val();
	var zwzz = jQuery("#zwzz").val();
	var bz = jQuery("#bz").val();
	if(zwmc==""){
		alertInfo("请输入职务名称");
	}else if(lxdm==""){
		alertInfo("请选择职务类型");
	}else if(zwzz==""){
		alertInfo("请输入职务职责");
	}else if(zwzz.length>=200){
		alertInfo("职务职责不能超过200字");
	}else if(bz.length>=200){
		alertInfo("备注不能超过200字");
	}else{
		return true;
	}
	return false;
}

//职务名称链接
function zwmcLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='view(\""+row["zwid"]+"\")'>"+cellValue+"</a>";
}

//查看
function view(zwid){
	showDialog("查看学生干部职务",700,300,'szdw_xsgb_zwwh.do?method=zwck&zwid='+zwid);
}