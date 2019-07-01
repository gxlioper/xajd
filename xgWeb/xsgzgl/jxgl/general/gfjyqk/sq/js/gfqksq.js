

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
	
}

function add(){
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("false" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}
	var url = "gfjyqk_sq.do?method=add";
	var title = "申请";
	showDialog(title,790,550,url);
	
}
function change(obj){
	if(obj.value==1){
		jQuery("#bydj").show();
		jQuery("#cjrw").hide();
		jQuery("#twfx").hide();
		jQuery("#pjpy").hide();
		jQuery("#jc").hide();
		jQuery("#cjhd").hide();
	}else if(obj.value==2){
		jQuery("#bydj").hide();
		jQuery("#cjrw").show();
		jQuery("#twfx").hide();
		jQuery("#pjpy").hide();
		jQuery("#jc").hide();
		jQuery("#cjhd").hide();
	}else if(obj.value==3){
		jQuery("#twfx").show();
		jQuery("#bydj").hide();
		jQuery("#cjrw").hide();
		jQuery("#pjpy").hide();
		jQuery("#jc").hide();
		jQuery("#cjhd").hide();
	}else if(obj.value==4){
		jQuery("#pjpy").show();
		jQuery("#twfx").hide();
		jQuery("#bydj").hide();
		jQuery("#cjrw").hide();
		jQuery("#jc").hide();
		jQuery("#cjhd").hide();
	}else if(obj.value==5){
		jQuery("#jc").show();
		jQuery("#pjpy").hide();
		jQuery("#twfx").hide();
		jQuery("#bydj").hide();
		jQuery("#cjrw").hide();
		jQuery("#cjhd").hide();
	}else if(obj.value==6){
		jQuery("#cjhd").show();
		jQuery("#jc").hide();
		jQuery("#pjpy").hide();
		jQuery("#twfx").hide();
		jQuery("#bydj").hide();
		jQuery("#cjrw").hide();
	}else{
		jQuery("#cjhd").hide();
		jQuery("#jc").hide();
		jQuery("#pjpy").hide();
		jQuery("#twfx").hide();
		jQuery("#bydj").hide();
		jQuery("#cjrw").hide();
	}
}
//附件格式验证
function postfixCheck(){
	var wjm=jQuery("#formfile").val();
	if(wjm==""||wjm==null){
		return true;
	}
	var wjms=wjm.split(".");
	var hz=",bmp,jpg,jpeg,gif,png,pdf,doc,BMP,JPG,JPEG,GIF,PNG,PDF,DOC";
	if(hz.indexOf(wjms[wjms.length-1])<0){
		return false;
	}
	return true;
}

//增加--保存草稿或提交
function save(url){
	var checkId ="xh-gfqkfl";
	if(jQuery("#gfqkfl").val()==1){
		checkId+="-bydjsj-bydjdd";
	}else if(jQuery("#gfqkfl").val()==2){
		checkId+="-cjrwsj-rwpzdw";
	}else if(jQuery("#gfqkfl").val()==3){
		checkId+="-twfxsj-twpzdw";
	}else if(jQuery("#gfqkfl").val()==4){
		checkId+="-pjpysj-pjpydw";
	}else if(jQuery("#gfqkfl").val()==5){
		checkId+="-jcsj-jcdw";
	}else if(jQuery("#gfqkfl").val()==6){
		checkId+="-cjhdsj-cjhddd";
	}
	if(!postfixCheck()){
		return showAlert("请上传支持的附件格式！");
	}
	if(!check(checkId)){
		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
	}
	
	ajaxSubFormWithFun("gfjysqForm", url, function(data) {
		if (data["message"] == "保存成功！") {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		} else {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		}
	});
}


//修改--保存草稿或提交
function saveXg(url){
	var checkId ="xh-gfqkfl";
	if(jQuery("#gfqkfl").val()==1){
		checkId+="-bydjsj-bydjdd";
	}else if(jQuery("#gfqkfl").val()==2){
		checkId+="-cjrwsj-rwpzdw";
	}else if(jQuery("#gfqkfl").val()==3){
		checkId+="-twfxsj-twpzdw";
	}else if(jQuery("#gfqkfl").val()==4){
		checkId+="-pjpysj-pjpydw";
	}else if(jQuery("#gfqkfl").val()==5){
		checkId+="-jcsj-jcdw";
	}else if(jQuery("#gfqkfl").val()==6){
		checkId+="-cjhdsj-cjhddd";
	}
	if(!postfixCheck()){
		return showAlert("请上传支持的附件格式！");
	}
	if(!check(checkId)){
		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
	}
	
	ajaxSubFormWithFun("gfjysqForm", url, function(data) {
		if (data["message"] == "保存成功！") {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		} else {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		}
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






function update() {
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		
		var shzt = rows[0]["shzt"];
		if (shzt=='0'||shzt=='3'){
			var url = 'gfjyqk_sq.do?method=update&xh='+rows[0]["xh"]+'&sqid=' + rows[0]["sqid"];
			showDialog("修改", 720, 450, url);
		}else{
			showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
			return false;
		}
	}

}


//删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("gfjyqk_sq.do?method=del", {
					values : ids.toString()
				}, function(data) {
					var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+="的申请已经提交不能删除!";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}


function submit(){
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1){
		if ("false" == isopen){
			showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
			return false;
		}
		showAlertDivLayer("请选择一条您要提交的记录！");
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		if ('3'!=rows[0]["shzt"] && "false" == isopen){
			showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
			return false;
		}
		var url = "gfjyqk_sq.do?method=submit";
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='3'&& "true"!= isopen){
				showAlertDivLayer("当前未开放申请，请联系管理员！");
				return false;
			}
			if(rows[i]['shzt']!='0' && rows[i]['shzt']!='3' ){
				showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
				return false;
			}
		}
		showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
			jQuery.post(url,
				{
				 values:ids.toString(),
				 xh : rows[0]['xh'],
				 splc : rows[0]['splc'],
				 shzt : rows[0]['shzt']
				},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function cancel(){
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("false" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要撤销的记录！");
	} else if (ids.length >1 ) {
		showAlertDivLayer("请选择一条您要撤销的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='5'){
				showAlertDivLayer("只有审核中的记录才能被撤销！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要撤销选择的记录吗？",{"okFun":function(){
			jQuery.post("gfjyqk_sq.do?method=cancel",
				{
				 values:ids.toString(),
				 splcid : rows[0]['splc'] 
				},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


function Lcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {	
		var shzt = rows[0]["shzt"];
		if ("0" == shzt){
			showAlertDivLayer(jQuery("#lable_wxglcxx").val());
			return false;
		}	
		showDialog("国防教育情况审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}








function viewGfjyqk(sqid,xh) {
	showDialog("查看", 720, 520, "gfjyqk_sq.do?method=view&sqid=" + sqid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewGfjyqk(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}



var DCCLBH = "gfjy_gfjyqk_sq.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, ExportData);
}

// 导出方法
function ExportData() {
	setSearchTj();//设置高级查询条件
	var url = "gfjyqk_sq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}