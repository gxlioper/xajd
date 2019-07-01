 
var action = "fbgl.do";
var title = "分班管理";
function searchRs() {
	var map = getSuperSearch();
	var fbzt = jQuery("#fbzt").val();
	if (fbzt != "") {
		map["fbzt"] = fbzt;
	}
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
// 切换
function selectTab(obj, bbzt) {
	if (bbzt == "wfb") {
		window.location.href="fbgl.do?method=list"; 
	} else {
		window.location.href="fbgl.do?method=yfblist"; 
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	// searchRs();
}
// 查看规则
function ckgz(cellValue, rowObject) {
	var pzgzid = rowObject["fbgz"];
	if(pzgzid==""||pzgzid==null||pzgzid=="null"){
		return "";
	}
	return "<a href='javascript:void(0);' onclick=\"ckpzgz('" + pzgzid
			+ "')\" class='name'>" + cellValue + "</a>";
}
// 查看配置信息
function ckpzgz(pzgzid){
	var url ="fbglgzpztj.do?method=showView&pzgzid=" + pzgzid;
	var cktitle ="查看规则信息";
	showDialog(cktitle, 800, 500, url);
}
// 分班
function fb() {
	// 没有分班规则
	var map = getSuperSearch();
	if (!isHaveGzxx("FBGZ")) {
		return showAlertDivLayer("分班规则未设定或未启用！");
	}
	var ids = jQuery("#dataTable").getRowCount();
	if(ids<=0){
		return showAlertDivLayer("不存在学生信息！");
	}
	var url = action + "?method=fb";
	var title = "分班管理";
	var ids = jQuery("#dataTable").getSeletIds();
	url += "&pk=" + ids.toString();
	showDialog(title, 500, 300, url);
}
//保存分班
function saveFb() {
	var pzgzid = jQuery("#pzgzid").val();
	var pk = jQuery("#pk").val();
	if(jQuery("#wfbxs").val()=="0"){
		return showAlert("不存在未分班学生！");
	}
	lock();
	// 加载进度条
 	var nowValue=parseInt(jQuery("#yfb").text());
 	loadBar("fb"+pzgzid,function(data){
 		jQuery("#yfb").text(data.now+nowValue);
 		jQuery("#wfb").text(data.all-data.now);
 		if(data.finish&&data.message=="-1"){
 			showAlert("分班完成！",{},{"clkFun":function(){
 				if (parent.window){
 					refershParent();
 				}
 			}});
 		}
 		if(data.message!="-1"){
 			showAlert(data.message,{},{"clkFun":function(){
 				if (parent.window){
 					refershParent();
 				}
 			}});
 			return false;
 		}
 		return true;
 	},100);
	jQuery.ajax( {
		url : "fbgl.do?method=createFb",
		data : {
			pzgzid : pzgzid,
			pk : pk
		},
		type : "post",
		dataType : "json",
		success : function(data) {
			unlock();
		}
	});
}
// 取消分班
function qxfb(){
	var ids = jQuery("#dataTable").getRowCount();
	if(ids<=0){
		return showAlertDivLayer("不存在学生信息！");
	}
		var fbzt=jQuery("#fbzt").val();
		var ids = jQuery("#dataTable").getSeletIds();
		var title="您确定要取消选择的分班记录吗？";
		if (ids.length == 0) {
			title="您确定要取消所有的分班记录吗！";
		}
		showConfirmDivLayer(title, {
			"okFun" : function() {
				jQuery.post(action+"?method=del", {
					values : ids.toString(),fbzt:fbzt
				}, function(data) {
					var mes="成功取消了<font color='green'>&nbsp;"+data["successDel"]+"&nbsp;</font>条分班";
					mes+="</br>";
					alertMessage(mes);
				}, 'json');
			}
		});
}
// 调整分班
function tzbj(){
	var rows = jQuery("#dataTable").getSeletRow();
	var zydm="";
	jQuery.each(rows,function(i){
		//如果为空或者zydm已有则记录专业代码（通过）
		if(zydm==""||rows[i]["zydm"]==zydm){
			zydm=rows[i]["zydm"];
		}else{//不同专业代码
			zydm="-1";
			return false;
		}
	});
	if(zydm=="-1"){
		showAlertDivLayer("请选择同一专业的学生！");
	}else{
		var ids = jQuery("#dataTable").getSeletIds();
		if (ids.length == 0) {
			showAlertDivLayer("请选择您要调整的分班记录！");
		} else {
			var url = action + "?method=tzbj&values="+ids.toString()+"&zydm="+zydm;
			showDialog("分班调整", 428, 184, url);
		}
	}
}
// 保存调班
function saveTb(){
	var yxxs=jQuery("#yxxs").val();
	var bjdm=jQuery("#bjdm").val();
	var bjmc=jQuery("#bjdm option:selected").text();
	var ids=jQuery("#ids").val();
	showConfirm("确认将这<font color='red'>"+yxxs+"</font>个学生分至【<font color='blue'>"+bjmc+"</font>】？", {
		"okFun" : function() {
			jQuery.post(action+"?method=tzbj&type=save", {
				values : ids.toString(),bjdm:bjdm,bjmc:bjmc
			}, function(data) {
				var message;
				if(data["message"]=="true"){
					message="调班成功！";
				}else{
					message="调班失败！";
				}
				showAlert(message, {}, {
					"clkFun" : function() {
						var api = frameElement.api;
						api.reload();
					}
				});
			}, 'json');
		}
	});
}