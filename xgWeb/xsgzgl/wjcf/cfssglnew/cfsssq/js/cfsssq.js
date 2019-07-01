function add(){
	var rows=jQuery("#dataTable").getSeletRow();
	if(rows.length!=1){
		alertInfo("请选择一条您要申诉的记录！");
	}else{
		if(rows[0]["shztmc"]!="未提交申请"){
			alertInfo("不能重复申请申诉！");
			return false;
		}
		var url = "";
		if(rows[0]['shztmc']=='退回' ){
			url = 'wjcf_cfsssq.do?method=cfsssqAdd&cfid='+rows[0]["cfid"]+'&ssid='+rows[0]["ssid"]+'&xh='+rows[0]["xh"]+'&returnflag=return';
		}else{
			url = 'wjcf_cfsssq.do?method=cfsssqAdd&cfid='+rows[0]["cfid"]+'&xh='+rows[0]["xh"];
		}
		showDialog("处分申诉申请",760,500,url);
	}
}

function save(url){
	var sqly=jQuery("#sqly").val();
	if(sqly==""){
		showAlert("请填写申请理由！");
		return false;
	}
	
	var returnflag = jQuery("#returnflag").val();
	ajaxSubFormWithFun("cfsssqForm",url,function(data){
	  	 if(data["message"]=="保存成功！"){
	  		 showAlert(data["message"],{},{"clkFun":function(){
	  				if (parent.window){
	  					refershParent();
	  				}
	  			}});
	  	 }else{
	  		 showAlert(data["message"]);
	  		 
	  	 }
	});
}

function update(){
	var rows=jQuery("#dataTable").getSeletRow();
	if(rows.length!=1){
		alertInfo("请选择一条您要修改的记录！");
	}else{
		if(rows[0]["shztmc"]=="未提交申请"){
			alertInfo("未提交申诉申请！");
			return false;
		}
		if(rows[0]["shzt"]!="0"&&rows[0]["shzt"]!="3"){
			alertInfo("申诉申请已审核，不能修改！");
			return false;
		}
		showDialog("处分申诉修改",760,500,'wjcf_cfsssq.do?method=cfsssqUpdate&cfid='+rows[0]["cfid"]+'&xh='+rows[0]["xh"]+'&ssid='+rows[0]["ssid"]);
	}
}

function updatesave(url){
	var sqly=jQuery("#sqly").val();
	if(sqly==""){
		showAlert("请填写申请理由！");
		return false;
	}
	
	ajaxSubFormWithFun("cfsssqForm",url,function(data){
	  	 if(data["message"]=="保存成功！"){
	  		 showAlert(data["message"],{},{"clkFun":function(){
	  				if (parent.window){
	  					refershParent();
	  				}
	  			}});
	  	 }else{
	  		 showAlert(data["message"]);
	  		 
	  	 }
		});
	
}

function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();

	if(ids.length==0){
		alertInfo("请选择您要删除的记录！");
	}else if (ids.length >1 ) {
		showAlertDivLayer("请选择一条您要删除的记录！");
	}else if(rows[0]['shztmc']!='未提交'){
		showAlertDivLayer("请选择未提交的记录！");
		return false;
	}else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("wjcf_cfsssq.do?method=cfsssqDelete",
					{values:ids.toString(),
					 splcid : rows[0]['splcid'],
					 shzt : rows[0]['shzt']},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
			}});
	}
	
}

//审批流查看
function lcgz(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条记录！");
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if(ids[0]==null){
		alertInfo("还未申请申诉，不能跟踪流程！");
		return false;
	}
	
	var shzt = rows[0]["shztmc"];
	if ("未提交" == shzt){
				showAlertDivLayer("该记录为未提交状态，请先提交！");
				return false;
			}
	showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['ssid']+"&splc="+rows[0]['splcid']);
}


function showCfqxFlag(cflbdm){
	//对于青岛酒店管理职业技术学院屏蔽该功能
	if(jQuery("#xxdm").val()=='13011') return false;
	
	if(cflbdm==""){return false;}
	jQuery.post("wjcf_cflbdmwh.do?method=getCfqx",{cflbdm:cflbdm},function(data){
		jQuery("#showCfqx").html(data["message"]);
	},'json');
}
