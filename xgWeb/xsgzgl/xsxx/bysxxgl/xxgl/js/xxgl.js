var action="xsxx_bysxx_xxgl.do";
var gndm = "xsxx_update";

jQuery(function() {
	onShow();
	xsGkPic();
});
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function reload(){
	jQuery("#dataTable").reloadGrid();
}
//点击序号跳转
function dcmcLink(cellValue, rowObject) {
	var xh = rowObject.xh;
	if (xh == null) {
		xh = "";
	}
	cellValue = "<a  href='javascript:void(0);'  class='name'  onclick='bysXxCk(\""
			+ xh
			+ "\" );return false;' >" + xh + "</a>";
	return cellValue;
}
//华中师范高考照片个性化
function xsGkPic() {
	if ("10511" != jQuery("#xxdm").val()) {
		jQuery("#addGkPic").css("display", "none");
		jQuery("#stuGkImg").css("display", "none");
		jQuery("#gkzpscbtn").css("display", "none");
	}

}
//查看信息
function bysXxCk(xh) {
	
	var url = action+"?method=bysXxCk&xh=" + xh;
	var title = "毕业生详细信息";
	showDialog(title, 850, 550, url);
}
function onShow() {
	var url = "xsxx_bysxx_xxgl.do?method=bysXxXg&type=query";
	var xxdm = jQuery("#xxdm").val();
	jQuery.ajax( {
		type : "post",
		async : false,
		url : url,
		data : {
			xh : jQuery("#xh").val()
		},
		dataType : "json",
		success : function(data) {
			var xm = data.xm;
			jQuery("#xmView").html(xm);
			zdybdInit(gndm, data);
		}
	});
}
//增加
function bysXxAdd() {
	showDialog('生成毕业生',800,500,'xsxx_bysxx_xxgl.do?method=showStudents');
}

function bysXxSave(){
	var rows = jQuery("#dataTable").getSeletRow();	
	var counts = jQuery("#rowConut").html();
	if (rows.length == 0){
		var map = getSuperSearch();
		var url = "xsxx_bysxx_xxgl.do?method=bysXxAdd";			
		url +="&selected=all";
		
		confirmInfo("您确定要选择<font color='red'>"+counts+"</font>条记录吗?",function(ty){
			if(ty=="ok"){
				jQuery.post(url,map,function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						  if (parent.window){
						     refershParent();
						  }
					 }});
					jQuery("#dataTable").reloadGrid();
				},'json');
			}
		});	

	}else {
		var ids = jQuery("#dataTable").getSeletIds();	 
		jQuery.post(action+"?method=bysXxAdd", {
			values : ids.toString()
		}, function(data) {
			  showAlert(data["message"],{},{"clkFun":function(){
				  if (parent.window){
				     refershParent();
				  }
			 }});
		},"json");
	}

}
function chkNumInputForThis(obj){
	//这里是共用js 处理不同页面 有些对象不存在的问题
	if(null==obj||null==$(obj)){
		return false;
	}
	return chkNumInput(obj);
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
function bysXxEdit() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_bysxx_xxgl.do?method=bysXxXg";
		url += "&xh=" + ids;
		showDialog("毕业生信息修改", 850, 550, url);
	} else {
		showAlertDivLayer("请<font color='blue'>勾选一条</font>您希望修改的记录！");
		return false;
	}
}
function saveForm() {
	if (!zdybdCheck()) {
		return;
	}

	var sfbt = jQuery('#zpsfbt').val();
	var sfcz = jQuery('#zpsfcz').val();
	if (sfbt == "y" && "false" == sfcz) {
		alertError("请先上传一张照片！")
		return false;
	}
	initParam();
	var url = "xsxx_bysxx_xxgl.do?method=bysXxXgBc";
	ajaxSubFormWithFun("form1", url, function(data) {
		if (data["success"] != undefined && !data["success"]) {
			showAlert(data["message"]);
		} else {
			showAlert(data["message"], {}, {
				"clkFun" : function(tag) {
					if (tag == "ok") {
						refershParent();
					}
				}
			});
		}
	});

}
//删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("审核流程过来的记录不能删除！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post(action+"?method=del", {
					values : ids.toString()
				}, function(data) {
					var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
					mes+="</br>";
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}
function rcxwshLcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {		
		showDialog("请假审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['qjsqid']+"&splc="+rows[0]['splcid']);
	}
}
//下载登记表
function printByjdb(url){
	var xh="";
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <1) {
		showAlertDivLayer("请至少选择一条记录！");
	}
	else if (rows.length == 1) {
		
		url += "&xh=" + rows[0]["xh"];
		window.open(url);
	} else {
		for(var i=0;i<rows.length;i++){
			if(i==rows.length-1){
				xh +=rows[i]["xh"];
			}else{
				xh +=rows[i]["xh"]+",";
			}
		}
		var url = "xsxx_bysxx_xxgl.do?method=printByjdbZip" + "&xh=" +xh;
		window.open(url);
	}
}

//删除
function bysXxDel() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();	
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("xsxx_bysxx_xxgl.do?method=bysXxDel", {
					values : ids.toString()
				},
						function(data) {
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
			}
		});
	}
}


