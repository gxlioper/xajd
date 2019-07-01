function choosezcxm(obj,aa){
	
	if(obj.checked){
		jQuery("td[name='fs"+aa+"']").show();
		jQuery("td[name='pm"+aa+"']").show();
	}else{
		jQuery("td[name='fs"+aa+"']").hide();
		jQuery("td[name='pm"+aa+"']").hide();
	}
	
	
}

function tocheck(sqid,shid,gwid){
	
	showDialog("奖项审核",700,500,"xpj_sqsh.do?method=viewJxsh&sqid="+sqid+"&shid="+shid+"&gwid="+gwid);
}

function plshtg(){
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	var bjdms = jQuery("#bjdms").val();
	showDialog("批量审核",700,300,"xpj_sqsh.do?method=toPlshy&xn="+xn+"&xq="+xq+"&bjdms="+bjdms);
}


/**
 * 删除
 */
function qxsq(id){
	
	showConfirmDivLayer("您确定要取消该申请吗？",{"okFun":function(){
		jQuery.post("xpj_sqsh.do?method=cancelXmsq",{values:id},function(data){
			jQuery('#search_go').click();
			showAlertDivLayer(data["message"]);
		},'json');
	}});
}

//提交
function submitBusi(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要提交的记录！");
	} else if (ids.length >1 ) {
		showAlertDivLayer("请选择一条您要提交的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'&&rows[i]['shzt']!='3'){
				showAlertDivLayer("请选择未提交或者已退回的记录！");
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要提交选择的记录吗？", {
			"okFun" : function() {
			
				jQuery.post(action+"?method=subBusi", {
					values : ids.toString(),
					lcid : rows[0]['splcid'],
					ydlx : rows[0]['ssydlx'],
					xh : rows[0]['xh']
				}, function(data) {
					
					showAlertDivLayer(data["message"]);
					reload();
				}, 'json');
				
			}
		});
	}
	
}

//撤销
function cancle(){
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
		
		showConfirmDivLayer("您确定要撤销选择的记录吗？", {
			"okFun" : function() {
			
				jQuery.post(action+"?method=cancle", {
					values : ids.toString(),
					lcid : rows[0]['splcid']
				}, function(data) {
					
					showAlertDivLayer(data["message"]);
					reload();
				}, 'json');
				
			}
		});
	}
	
}

//选择评奖项目
function choosepjxm(obj,aa){
	
	if(obj.checked){
		jQuery("td[name='pjxmtd"+aa+"']").show();
	}else{
		jQuery("td[name='pjxmtd"+aa+"']").hide();
	}
	
	
}