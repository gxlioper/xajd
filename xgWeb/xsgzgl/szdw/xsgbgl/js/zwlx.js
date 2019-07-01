var gridSetting = {
		caption:"学生干部职务类型",
		pager:"pager",
		url:"szdw_xsgb_zwlx.do?method=zwlxList&type=query",
		colList:[
		   {label:'职务类型',name:'lxdm', index: 'lxdm',width:'1%',key:true,hidden:true},
		   {label:'类型名称',name:'lxmc', index: 'lxmc',width:'30%',formatter:zwlxLink},
		   {label:'备注',name:'bz', index: 'bz',width:'30%'},
		   {label:'审批流程',name:'splc', index: 'splc',width:'40%'}
		],
		sortname: "lxdm",
	 	sortorder: "desc"
}
function add(){
	showDialog("增加学生干部职务类型",700,300,"szdw_xsgb_zwlx.do?method=zwlxAdd");
}
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条您要修改的记录！");
	} else {
		showDialog("修改学生干部职务类型",700,300,'szdw_xsgb_zwlx.do?method=zwlxUpdate&lxdm='+rows[0]["lxdm"]);
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
				jQuery.post("szdw_xsgb_zwlx.do?method=zwlxDelete",{values:ids.toString()},function(data){
					alertInfo(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}
		});
	}
}
function save(method){
	if(yanzheng()){
		var url = "szdw_xsgb_zwlx.do?method="+method+"&type=save";
		ajaxSubFormWithFun("demoForm",url,function(data){
			alertInfo(data["message"],function(ty){
				//alert(ty);
				if(ty=="ok"){
					if (parent.window){
						refershParent();
					}
				}
			});
		});
	}
}
function yanzheng(){
	var lxmc = jQuery("#lxmc").val();
	var bz = jQuery("#bz").val();
	var splc = jQuery("#splc").val();
	if(lxmc==""){
		alertInfo("职务类型名称不能为空！");
	}else if(bz.length>=200){
		alertInfo("备注不能超过200字！");
	}else if(splc == "" ){
		alertInfo("审批流程不能为空！");
	}else{
		return true;
	}
	return false;
}

//学号链接
function zwlxLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='view(\""+row["lxdm"]+"\")'>"+cellValue+"</a>";
}

//查看
function view(lxdm){
	showDialog("学生干部类型查看",700,300,'szdw_xsgb_zwlx.do?method=zwlxck&lxdm='+lxdm);
}