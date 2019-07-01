 
function searchRs() {
		var map = getSuperSearch();
		var xhzt = jQuery("#xhzt").val();
	if (xhzt != "") {
		map["xhzt"] = xhzt;
	}
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
// 切换
function selectTab(obj, xhzt) {
	if (xhzt == "wbxh") {
		window.location.href="fbglbxh.do?method=list"; 
	} else {
		window.location.href="fbglbxh.do?method=jglist"; 
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}
function ckgz(cellValue, rowObject) {
	var pzgzid = rowObject["bxhgz"];
	if(pzgzid==""||pzgzid==null||pzgzid=="null"){
		return "";
	}
	return "<a href='javascript:void(0);' onclick=\"ckpzgz('" + pzgzid
			+ "')\" class='name'>" + cellValue + "</a>";
}
function ckpzgz(pzgzid){
	var url ="fbglgzpztj.do?method=showView&pzgzid=" + pzgzid;
	var cktitle ="查看规则信息";
	showDialog(cktitle, 800, 500, url);
}
function scxh(xhzt) {
	//alert(convertDate("20130927"));
	//不存在对应编学号规则信息
	if (!isHaveGzxx("BXHGZ")) {
		return showAlertDivLayer("编学号规则未设定或未启用！");
	}
	var ids = jQuery("#dataTable").getRowCount();
	if(ids<=0){
		return showAlertDivLayer("不存在学生信息！");
	}
	var url ="fbglbxh.do?method=scxh&bxhzt="+xhzt;
	//如果是结果列表生成
	if(xhzt!="wbxh"){
		 url ="fbglbxh.do?method=jgscxh&bxhzt="+xhzt;
	}
	var title = "编学号管理";
	var ids = jQuery("#dataTable").getSeletIds();
	url += "&pk=" + ids.toString();
	showDialog(title, 500, 280, url);
}
/**
 * 生成学号保存
 * @return
 */
function scxhSave(){
	var pzgzid=jQuery("#pzgzid").val();
	var bxhzt=jQuery("#bxhzt").val();
	var pk=jQuery("#pk").val();
	var barkey="scxh"+pk;
	var wbxh=jQuery("#wbxh").text();
	if(jQuery.trim(wbxh)=="0"){
		return showAlert("不存在未编学号学生！");
	}
	lock();
 	jQuery.ajax({
		url:"fbglbxh.do?method=scxh&type=save",
		data:{pzgzid:pzgzid,pk:pk,barkey:barkey,bxhzt:bxhzt},
		type:"post",
		dataType:"json",
		success:function(data){
			if(null!=data["message"]&&"null"!=data["message"]){
				showAlert(data["message"],{},{"clkFun":function(){
	 				if (parent.window){
	 					refershParent();
	 				}
	 			}});
			}
		}
	});	
 	var nowValue=parseInt(jQuery("#ybxh").text());
 	//加载进度条
 	loadBar(barkey,function(data){
 		jQuery("#ybxh").text(data.now+nowValue);
 		jQuery("#wbxh").text(data.all-data.now);
 		if(data.finish){
 			showAlert("编学号完成,如若存在未分班学生，则不编学号！",{},{"clkFun":function(){
 				if (parent.window){
 					refershParent();
 				}
 			}});
 		}
 		return true;
 	});
}
//删除
function delxh(){
	var ids = jQuery("#dataTable").getRowCount();
	if(ids<=0){
		return showAlertDivLayer("不存在学生信息！");
	}
	var xhzt=jQuery("#xhzt").val();
	if(xhzt=="ybxh"){
		return delYbxh();
	}
	var ids = jQuery("#dataTable").getSeletIds();
	var title="您确定要删除选择的编学号记录吗？";
	if (ids.length == 0) {
		title="是否删除所有编学号记录？";
	} 
	var rows = jQuery("#dataTable").getSeletRow();
	showConfirmDivLayer("您确定要删除选择的编学号记录吗？", {
		"okFun" : function() {
			jQuery.post("fbglbxh.do?method=del", {
				values : ids.toString()
			}, function(data) {
				var mes="成功删除了<font color='green'>&nbsp;"+data["successDel"]+"&nbsp;</font>条学号";
				mes+="</br>";
				showAlertDivLayer(mes);
				jQuery("#dataTable").reloadGrid();
				}
			, 'json');
		}
	});
}
/**
 * 删除已编学号
 */
function delYbxh(){
	var ids = jQuery("#dataTable").getSeletIds();
	var title="您确定要删除选择的编学号记录吗？";
	if (ids.length == 0) {
		title="是否删除所有编学号记录？";
	}
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("您确定要删除选择的学号记录吗？", {
			"okFun" : function() {
				jQuery.post("fbglbxh.do?method=del", {
					values : ids.toString(),type:'ybxh'
				}, function(data) {
					var mes="成功删除了<font color='green'>&nbsp;"+data["successDel"]+"&nbsp;</font>条数据";
					mes+="</br>";
					if(data["errorObject"]!="-1"){
						var list=data["errorObject"];
						for(var i=0;i<list.length;i++){
							var messageObj=list[i];
							mes+="<font color='blue'>"+messageObj["nj"]+messageObj["xymc"]+messageObj["bjmc"]+messageObj["xm"]+"</font>";
							mes+="不存在学号！";
							mes+="</br>";
						}
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
}