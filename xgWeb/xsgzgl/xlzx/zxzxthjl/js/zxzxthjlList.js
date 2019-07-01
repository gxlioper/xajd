var DCCLBH='xlzx_thjl_zxzxthjl.do';
//增加的方法
function addZxzxthjl() {
	showDialog('增加心理普查谈话记录', 750, 520, 'xlzx_thjl_zxzxthjlgl.do?method=zxzxthjlAdd');
}
//增加保存方法
function save(){
	var ybwtlbIds = "";
	var checkYbwtlb = "";
	var zajbIds = "";
	var checkZajb = "";
	var xh = jQuery("#xh").val();//学号
	var thsj = jQuery("#thsj").val();//约谈时间时间
	var jbqkms = jQuery("#jbqkms").val();//基本情况描述
	var cbpgdm = jQuery("#cbpgdm").val();//初步评估
	var cbpgjg = jQuery("#cbpgjg").val();//初步评估结果
	var zajbsmzx = jQuery("#zajbsmzx").val();//初步评估结果sfzj
	var sfzj = jQuery("#sfzj").val();//是否转介
	//控制学号、约谈时间！
	if(xh==null||xh==""){
		showAlert("请选择学号！");
		return false;
	}
	if(thsj==null||thsj==""){
		showAlert("请选择约谈时间！");
		return false;
	}
	if(jbqkms==null||jbqkms==""){
		showAlert("请填写基本情况描述！");
		return false;
	}
	if(cbpgdm==null||cbpgdm==""){
		showAlert("请选择初步评估对应选项！");
		return false;
	}
	
	
	//初步评估
	var checkYbwtlb1 = false;
	jQuery("input[name='ybwtlb']:checked").each(function(){
		ybwtlbIds += jQuery(this).val()+ "," ;
		 if(jQuery(this).val()=="建议咨询"&&zajbsmzx=="2"){
			 checkYbwtlb1 =true;
			}
	});
	if(ybwtlbIds.length>0){
		ybwtlbIds = ybwtlbIds.substring(0, ybwtlbIds.length-1);
		checkYbwtlb ="1";
	}
	 if(cbpgdm=="2"&&jQuery.trim(checkYbwtlb) == ""){
			showAlert("请填写一般问题类别！");
			return false;
	}
	 //截取checkbox的值为“建议咨询”，如果是建议咨询判断是否预约咨询时间是否为空
	 if(cbpgdm=="2"&&checkYbwtlb1){
			showAlert("请选择是否预约咨询时间！");
			return false;
		}
	 
	//占位
	var checkZajb2 = false;
	var checkZajb3 = false;
	var checkZajb4 = false;
	
	//心理障碍和精神疾病
	jQuery("input[name='zajb']:checked").each(function(){
		zajbIds += jQuery(this).val()+ "," ;
		if(jQuery(this).val()=="初步评估"&&cbpgjg==""){
			checkZajb2 =true;
		}
		if(jQuery(this).val()=="建议咨询"&&zajbsmzx=="2"){
			checkZajb3 =true;
		}
		if(jQuery(this).val()=="其他建议"&&sfzj=="2"){
			checkZajb4 =true;
		}
	});
	//心理障碍疾病连动控制心理障碍和精神疾病、初步评估结果、是否预约咨询时间、是否转介
	if(zajbIds.length>0){
		zajbIds = zajbIds.substring(0, zajbIds.length-1);
		checkZajb ="1";
	}
	if(cbpgdm=="3"&&jQuery.trim(checkZajb) == ""){
		showAlert("请选择心理障碍和精神疾病！");
		return false;
	}
	if(cbpgdm=="3"&&checkZajb2){
		showAlert("请填写初步评估结果！");
		return false;
	}	if(cbpgdm=="3"&&checkZajb3){
		showAlert("请选择是否预约咨询时间");
		return false;
	}	if(cbpgdm=="3"&&checkZajb4){
		showAlert("请选择是否转介！");
		return false;
	}
	
	var url = "xlzx_thjl_zxzxthjlgl.do?method=zxzxthjlAdd&type=save&ybwtlb="+encodeURI(encodeURI(ybwtlbIds))+"&zajb="+encodeURI(encodeURI(zajbIds));
	ajaxSubFormWithFun("zxzxthjlForm",url,function(data){
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

//连动显示
function change_cbpgdm(){//选择第一个选项时
	var cbpgdm = jQuery('#cbpgdm').val();
	if(cbpgdm=="1"){
		jQuery("#ybwtlb1").hide();
		jQuery("#zajb1").hide();
	}else if(cbpgdm=="2"){//选择第二个选项时
		jQuery("#ybwtlb1").show();
		jQuery("#zajb1").hide();
	}else if(cbpgdm=="3"){//选择第三个选项时
		jQuery("#ybwtlb1").hide();
		jQuery("#zajb1").show();
	} 
	jQuery("input[name=ybwtlb]").each(function(){
		jQuery(this).attr("checked",false);
	});
	jQuery("input[name=zajb]").each(function(){
		jQuery(this).attr("checked",false);
	});
	jQuery("#zajbsmzx1").hide();
	jQuery("#cbpgjg1").hide();
	jQuery("#sfzj1").hide();
	jQuery("#zajbsmzx").val("");
	jQuery("#cbpgjg").val("");
	jQuery("#sfzj").val("");
}
function click_ybwtlb(obj){//点击一般问题类别，会显示 是否预约咨询时间
	jQuery("#zajbsmzx1").hide();
	jQuery("input[name=ybwtlb]").each(function(){
		if( jQuery(this).val() == "建议咨询" && this.checked){
			jQuery("#zajbsmzx1").show();
		}
	});
	jQuery("#zajbsmzx").val("");
}
function click_zajbcbpg(obj){//
	var zajb = obj.value;
	if(zajb == "初步评估" && obj.checked){
		jQuery("#cbpgjg1").show();
	}else{
		jQuery("#cbpgjg1").hide();
	}
	jQuery("#cbpgjg").val("");
}
function click_zajbjyzx(obj){//
	var zajb = obj.value;
	if(zajb == "建议咨询" && obj.checked){
		jQuery("#zajbsmzx1").show();
	}else{
		jQuery("#zajbsmzx1").hide();
	}
	jQuery("#zajbsmzx").val("");
}
function click_zajbqtjy(obj){//
	var zajb = obj.value;
	if(zajb == "其他建议" && obj.checked){
		jQuery("#sfzj1").show();
	}else{
		jQuery("#sfzj1").hide();
	}
	jQuery("#sfzj").val("");
}
//修改
function updateZxzxthjl(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条您要修改的记录！");
	} else {
		showDialog('修改心理普查谈话记录',750, 500,'xlzx_thjl_zxzxthjlgl.do?method=zxzxthjlUpdate&id='+rows[0]["id"]);
	}
}
//修改保存方法
function saveUpdate(){
	var ybwtlbIds = "";
	var checkYbwtlb = "";
	var zajbIds = "";
	var checkZajb = "";
	var xh = jQuery("#xh").val();//学号
	var thsj = jQuery("#thsj").val();//约谈时间时间
	var jbqkms = jQuery("#jbqkms").val();//基本情况描述
	var cbpgdm = jQuery("#cbpgdm").val();//初步评估
	var cbpgjg = jQuery("#cbpgjg").val();//初步评估结果
	var zajbsmzx = jQuery("#zajbsmzx").val();//初步评估结果sfzj
	var sfzj = jQuery("#sfzj").val();//是否转介
	//控制学号、约谈时间！
	if(xh==null||xh==""){
		showAlert("请选择学号！");
		return false;
	}
	if(thsj==null||thsj==""){
		showAlert("请选择约谈时间！");
		return false;
	}
	if(jbqkms==null||jbqkms==""){
		showAlert("请填写基本情况描述！");
		return false;
	}
	if(cbpgdm==null||cbpgdm==""){
		showAlert("请选择初步评估对应选项！");
		return false;
	}
	
	
	//初步评估
	var checkYbwtlb1 = false;
	jQuery("input[name='ybwtlb']:checked").each(function(){
		ybwtlbIds += jQuery(this).val()+ "," ;
		 if(jQuery(this).val()=="建议咨询"&&zajbsmzx=="2"){
			 checkYbwtlb1 =true;
			}
	});
	if(ybwtlbIds.length>0){
		ybwtlbIds = ybwtlbIds.substring(0, ybwtlbIds.length-1);
		checkYbwtlb ="1";
	}
	 if(cbpgdm=="2"&&jQuery.trim(checkYbwtlb) == ""){
			showAlert("请填写一般问题类别！");
			return false;
	}
	 //截取checkbox的值为“建议咨询”，如果是建议咨询判断是否预约咨询时间是否为空
	 if(cbpgdm=="2"&&checkYbwtlb1){
			showAlert("请选择是否预约咨询时间！");
			return false;
		}
	//占位
	var checkZajb2 = false;
	var checkZajb3 = false;
	var checkZajb4 = false;
	
	//心理障碍和精神疾病
	jQuery("input[name='zajb']:checked").each(function(){
		zajbIds += jQuery(this).val()+ "," ;
		if(jQuery(this).val()=="初步评估"&&cbpgjg==""){
			checkZajb2 =true;
		}
		if(jQuery(this).val()=="建议咨询"&&zajbsmzx=="2"){
			checkZajb3 =true;
		}
		if(jQuery(this).val()=="其他建议"&&sfzj=="2"){
			checkZajb4 =true;
		}
	});
	//心理障碍疾病连动控制心理障碍和精神疾病、初步评估结果、是否预约咨询时间、是否转介
	if(zajbIds.length>0){
		zajbIds = zajbIds.substring(0, zajbIds.length-1);
		checkZajb ="1";
	}
	if(cbpgdm=="3"&&jQuery.trim(checkZajb) == ""){
		showAlert("请选择心理障碍和精神疾病！");
		return false;
	}
	if(cbpgdm=="3"&&checkZajb2){
		showAlert("请填写初步评估结果！");
		return false;
	}	if(cbpgdm=="3"&&checkZajb3){
		showAlert("请选择是否预约咨询时间");
		return false;
	}	if(cbpgdm=="3"&&checkZajb4){
		showAlert("请选择是否转介！");
		return false;
	}
	var url="xlzx_thjl_zxzxthjlgl.do?method=zxzxthjlUpdate&type=save&ybwtlb="+encodeURI(encodeURI(ybwtlbIds))+"&zajb="+encodeURI(encodeURI(zajbIds));
	ajaxSubFormWithFun("zxzxthjlForm",url,function(data){
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
//点击学号查看相信信息
function viewZxzxthjl(id,cellValue){
	showDialog('查看心理普查谈话记录',666,520,'xlzx_thjl_zxzxthjlgl.do?method=zxzxthjlView&id='+id+"&xh="+cellValue,null);
}
//删除的方法
function deleteZxzxthjl(){
	var ZxzxThjl = '';
	var rowsValue = jQuery("#dataTable").getSeletRow();
	if (rowsValue.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		for ( var i = 0; i < rowsValue.length; i++) {
			if (i == (rowsValue.length - 1)) {
				ZxzxThjl += rowsValue[i]["id"];
			} else {
				ZxzxThjl += rowsValue[i]["id"] + ",";
			}
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.ajaxSetup( {
					async : false
				});
				jQuery.post("xlzx_thjl_zxzxthjlgl.do?method=zxzxthjlDelete", {
					ZxzxThjl : ZxzxThjl
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
				jQuery.ajaxSetup( {
					async : true
				});
			}
		});
	}
}
//导出方法
function exportData() {
	customExport(DCCLBH, config);
}
// 导出方法
function config() {
	setSearchTj();// 设置高级查询条件
	var url = "xlzx_thjl_zxzxthjlgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
//打印约谈记录表
function getWord(){
	var rows = jQuery("#dataTable").getSeletRow();
	var id = "";
	if (rows.length == 1) {
		id +=rows[0]["id"];
		var url = "xlzx_thjl_zxzxthjlgl.do?method=getZxzxthjl&id="+id;
		window.open(url);
	} else if (rows.length == 0) {
		showAlertDivLayer("请选择您要打印的记录！");
		return false;
	} else {
		for(var i=0;i<rows.length;i++){
			id +=rows[i]["id"]+",";
			}
		var url = "xlzx_thjl_zxzxthjlgl.do?method=getZxzxthjlZip&value="+id;
		window.open(url);
		}
}