
function xhLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='view(\""+row["sqid"]+"\",\""+row["xh"]+"\")'>"+cellValue+"</a>";
}

function add(){
	jQuery.post("szdw_zwsq.do?method=yzZwsq",{},function(data){
		var message = data["message"];
		if(message=="true"){
			showDialog("学生干部申请职务申请",760,505,"szdw_zwsq.do?method=zwSq");
		}else{
			alertInfo(message);
		}
	},'json');
	
}

/**
 * 删除申请
 * @return
 */
function del(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (rows.length == 0){
		showAlertDivLayer("请选择一条您要删除的记录！");
		return false;
	} else{
		for(i=0 ; i< rows.length; i++){
			if(rows[i]['shztdm'] != '0' && rows[i]['shztdm'] != '3'){
				showAlertDivLayer("只能删除未提交或者已退回的记录！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除所选记录？",{"okFun":function(){
			jQuery.post("szdw_zwsq.do?method=deleteZwsqAction",{sqids:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function view(sqid,xh){
	showDialog("学生干部申请职务查看",760,460,'szdw_zwsh.do?method=zwsh&type=ck&sqid='+sqid+"&xh="+xh+"&tt="+new Date().getTime());
}

function save_bak(method){
	if(yanzheng()){
		jQuery.post("szdw_zwsq.do?method=yzZwsq",{zwid:jQuery("#zwid").val(),xh:jQuery("#xh").val()},function(data){
			if(data.message!="true"){
				alertInfo(data.message);
			}else{
				//验证成功后才能进行保存
				var url = "szdw_zwsq.do?method="+method+"&type=save";
				ajaxSubFormWithFun("demoForm",url,function(data){
					 showAlertDivLayer(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
							iFClose();
						}});
				});
			}
		},'json');
		
	}
}
function yanzheng(){
	var xh = jQuery("#xh").val();
	var zwid = jQuery("#zwid").val();
	var sqly = jQuery("#sqly").val();
	if(xh=="" || xh == undefined){
		alertInfo("请选择一个学生");
	}else if(zwid=="" || zwid == undefined){
		alertInfo("请选择申请的职务");
	}else if(sqly.length>=200){
		alertInfo("申请理由不能超过200字");
	}else{
		return true;
	}
	return false;
}
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function on_change(){
	jQuery("#tbody_zwxx").load("szdw_xsgb_zwwh.do?method=zwView&lxdm="+jQuery("#lxdm").val());
}
function on_getZwwh_model(){
	jQuery("#tbody_zwxx").load("szdw_xsgb_zwwh.do?method=zwView&zwid="+jQuery("#zwid").val());
}
//审批流查看
function lcgz(){
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = rows[0]["shzt"];
	if (rows.length != 1){
		alertInfo("请选择一条记录！");
	} else {
		if ("未提交" == shzt){
			showAlertDivLayer("该记录为未提交状态，请先提交！");
			return false;
		}
		showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}
//取消申请
function qx_sh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length>=1){
		var sqids = "";
		//获取选择的申请编号并用 ,拼接
		var check = false;
		jQuery(rows).each(function(i){
			sqids = sqids+","+rows[i]["sqid"];
			var shzt = rows[i]['shzt'];
			if(shzt!='待审核'){
				alertInfo("您的申请已进入审批流程不能撤销");
				return false;
			}else{
				check = true;
			}
		});
		if(check){
			confirmInfo("您确定要取消"+rows.length +"条申请记录吗?",function(ty){
				//alert(ty);
				if(ty=="ok"){
					jQuery.post("szdw_zwsq.do?method=qxsq",{sqids:sqids},function(data){
						if(data["success"]=="true"){
							alertInfo("取消申请成功");
						}else{
							alertInfo("取消申请失败");
						}
						jQuery("#dataTable").reloadGrid();
					},'json');
				}
			});
		}
		
		
	}else{
		alertInfo("请选择一条需要取消的记录");
	}
}

//修改申请
function update() {
	
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		
		var shzt = rows[0]["shzt"];
		if ("审核中" == shzt){
			showAlertDivLayer("该信息记录正在审核中，不能修改！");
			return false;
		}
		if ("通过" == shzt){
			showAlertDivLayer("该信息记录审核已经通过，不能修改！");
			return false;
		}
		if ("不通过" == shzt){
			showAlertDivLayer("该信息记录审核已经不通过，不能修改！");
			return false;
		}
		var url = 'szdw_zwsq.do?method=zwsqXg&sqid='+rows[0]["sqid"]+'&xh='+rows[0]["xh"];
		var title = "修改学生干部申请职务";
		showDialog(title, 720, 505, url);
		
	}
	
}

//打印备案表
function printXsgbbab(url){
	var xh="";
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <1) {
		showAlertDivLayer("请至少选择一条记录！");
	} else {
		for(var i=0;i<rows.length;i++){
			if(i==rows.length-1){
				xh +=rows[i]["xh"];
			}else{
				xh +=rows[i]["xh"]+",";
			}
		}
		var url = url + "&xh=" +xh;
		window.open(url);
	}
}