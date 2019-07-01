var action="shlcpz.do";
var gridSetting = {
		caption:"审核流程列表",
		pager:"pager",
		url:"shlcpz.do?method=list&type=query",
		colList:[
		   {label:'阶段代码',name:'jddm', index: 'jddm',key:true,hidden:true},
		   {label:'阶段名称',name:'jdmc', index: 'jdmc',width:'15%'},
		   {label:'审核流程id',name:'shlc', index: 'shlc',hidden:true},
		   {label:'审核流程',name:'lcxx', index: 'lcxx',width:'40%'},
		   {label:'可申请开关',name:'ksqkgxx', index: 'ksqkgxx',width:'15%'},
		   {label:'起止时间',name:'qzsj', index: 'qzsj',width:'30%'}
		],
		sortname: "to_number(jddm)",
	 	sortorder: "asc"
	}
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function reload(){
	jQuery("#dataTable").reloadGrid();
}
function query(){
	var map = {};
	map["jdmc"]=jQuery("#jdmc").val();
	jQuery("#dataTable").reloadGrid(map);
}
//申请
function add() {
		var url =action+"?method=add";
		var title = "增加审核流程配置";
		showDialog(title, 600, 370, url);
		jQuery("#dataTable").reloadGrid();
}
function save(url,checkId,keyid){
	if(!check(checkId)){
		return alertError("请将带<font color='red'>*</font>的项目填写完整！");
	}
	if(!checkother()){
		return false;
	}
	var select=jQuery("input[name='ksqkg']:checked").val();
	if(select=="0"){
		jQuery("#ksqkssj").val("");
		jQuery("#ksqjssj").val("");
	}
	lock();
 	jQuery("#form").ajaxSubmit({
		url:url,
		type:"post",
		dataType:"json",
		success:function(data){
	 		 if(data["message"]=="保存成功！"){
	    		 showAlert(data["message"],{},{"clkFun":function(){
	    				if (parent.window){
	    					refershParent();
	    				}
	    			}});
	    	 }else{
	    		 showAlert(data["message"]);
	    	 }
	 		 unlock();
		},
		contentType:"application/x-www-form-urlencoded;charset=utf-8"
	});	
}
/**
 * 验证是否存在空项
 * @param ids 要验证的控件id "-"分隔
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}
//修改
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlert("请选择一条您要修改的记录！");
	} else {
		var url = action+'?method=update&jddm=' + rows[0]["jddm"];
		var title = "修改审核流程配置";
		showDialog(title, 600, 370, url);
		jQuery("#dataTable").reloadGrid();
	}
}
function checkother(){
	return true;
}
//删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post(action+"?method=del", {
					values : ids.toString()
				}, function(data) {
					var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="区间为<font color='red'>["+data["nodel"]+"]</font>";
						mes+="的请假规则已经被使用不能删除!";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}
