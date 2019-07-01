function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewXsJqlxSq(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}


function viewXsJqlxSq(sqid, xh) {
	showDialog("学生假期留校申请查询", 750, 420, "rcsw_jqlx.do?method=viewJqlxsq&sqid=" + sqid
			+ "&xh=" + xh);
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
	var url = "rcsw_jqlx.do?method=addJqlx";
	var title = "新增假期留校申请";
	showDialog(title,750,530,url);
}

function update() {
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer(jQuery("#lable_one_xg").val());
	} else {
		var shzt = rows[0]["sqzt"];
		if (shzt=='0'||shzt=='3'){
			var url = 'rcsw_jqlx.do?method=updateJqlx&sqid='+rows[0]["sqid"]+'&xh='+rows[0]["xh"];
			var title = "修改假期留校申请";
			showDialog(title,750,530,url);
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
		showAlertDivLayer(jQuery("#lable_some_sc").val());
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer( jQuery("#lable_confirm_sc").val(), {
			"okFun" : function() {
				jQuery.post("rcsw_jqlx.do?method=delJqlxsq", {
					values : ids.toString()
				}, function(data) {
					var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据。";
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

function submitBusi(){
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
		showAlertDivLayer(jQuery("#lable_one_tj").val());
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		if ('3'!=rows[0]["sqzt"] && "false" == isopen){
			showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
			return false;
		}
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sqzt']!='0'&&rows[i]['sqzt']!='3'){
				showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
				return false;
			}
		}
		
		showConfirmDivLayer(jQuery("#lable_confirm_tj").val(), {
			"okFun" : function() {
				jQuery.post("rcsw_jqlx.do?method=submitJqlxsq", {
					sqid : ids.toString(),
					lcid : rows[0]['lcid'],
					xh : rows[0]['xh'],
					sqzt : rows[0]['sqzt']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
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
	if (ids.length != 1 ) {
		showAlertDivLayer(jQuery("#lable_one_cx").val());
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sqzt']!='5'){
				showAlertDivLayer(jQuery("#lable_shz_cx").val());
				return false;
			}
		}
		
		showConfirmDivLayer(jQuery("#lable_confirm_cx").val(), {
			"okFun" : function() {
				jQuery.post("rcsw_jqlx.do?method=cancelJqlxsq", {
					sqid : ids.toString(),
					lcid : rows[0]['lcid']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

function lcgzInfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer(jQuery("#lable_one_lcgz").val());
	} else {	
		if(rows[0]["sqzt"]=="0"){
			showAlertDivLayer(jQuery("#lable_wxglcxx").val());
			return false;
		}
		showDialog("假期留校审批流程跟踪",530,400,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['lcid']);
	}
}

//打印申请表
function printjqlxsqb(url){
	var sqid="";
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <1) {
		showAlertDivLayer("请至少选择一条记录！");
	} else {
		for(var i=0;i<rows.length;i++){
			if(i==rows.length-1){
				sqid +=rows[i]["sqid"];
			}else{
				sqid +=rows[i]["sqid"]+",";
			}
		}		
		var url = url + "&sqid=" +sqid;
		window.open(url);
	}
}