var action="qjlx.do";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function reload(){
	jQuery("#dataTable").reloadGrid();
}
//申请
function add() {
		var url =action+"?method=add";
		var title = "增加请假类型";
		showDialog(title, 400, 200, url);
		jQuery("#dataTable").reloadGrid();
}
function save(url,checkId){
	if(!check(checkId)){
		return alertError("请将带<font color='red'>*</font>的项目填写完整！");
	}
	var qjlxmc=jQuery("#qjlxmc").val();
	var qjlxid=jQuery("#qjlxid").val();
	jQuery.post("qjlx.do?method=isCanAdd", {
		qjlxmc:qjlxmc,qjlxid:qjlxid
	}, function(data) {
		if(data["success"]=="true"){
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
				},
				contentType:"application/x-www-form-urlencoded;charset=utf-8"
			});	
		}else{
			showAlert("已经存在此请假类型!");
		}
	}, 'json');
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
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
				var url = action+'?method=update&qjlxid=' + rows[0]["qjlxid"];
				var title = "修改请假类型";
				showDialog(title, 400, 200, url);
				jQuery("#dataTable").reloadGrid();
	}
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
							mes+="请假类型代码为<font color='red'>["+data["nodel"]+"]</font>";
							mes+="的请假类型已经被使用不能删除!";
						}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}

//开关链接
function openLink(cellValue, rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='linkClick(\""
	+ rowObject["qjlxid"] + "\");'>" + cellValue
	+ "</a>";
}

//点击链接弹窗
function linkClick(qjlxid){
	showDialog("开启关闭开关", 400, 200, "qjlx.do?method=openZt&qjlxid="
			+ qjlxid);
}