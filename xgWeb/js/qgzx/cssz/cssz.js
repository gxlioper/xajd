jQuery(document).ready(function(){ 
	var sxzd = jQuery("#sxzd").val();
	if(sxzd=="je"){
		jQuery("#font_sxsz").text("元(学生每月酬金不得超过该值)");
	}else if(sxzd=="gs"){
		jQuery("#font_sxsz").text("小时(学生每月工时不得超过该值)");
	}else{
		jQuery("#font_sxsz").text();
	}
	changeGwsq();
	changexsGwsq();
	jQuery("[name=xsgwsqsplc]").change(function() {// 人数控制级别，兼得控制级别，根据审核流程进行显示
		setKzlc(jQuery(this).val()); 
	});
	jQuery("[name=xsgwsqsplc]").change(function() {
		setYjKzlc(jQuery(this).val()); 
	});
	//获取当前要选中的
	jQuery("[name=xsgwsqsplc]").change();
	jQuery("[name=xsyjgwsqsplc]").change();
	changeSqkg();
});
//设置监听流程数据，如果存在流程中数据则不可更改
function checkSplc(){
	var splc=jQuery("#xsgwsqsplc").val();
	jQuery.ajax({
	   type: "POST",
	   url: "qgzx_xsgwsh.do?method=checkSplc",
	   data: {splc:splc,type:"1"},
	   dataType:"json",
	   success: function(data){
		   if(data["message"]=="true"){
				jQuery("#xsgwsqsplc").attr("disabled","disabled");
				jQuery("input[name=rskzjbView]").each(function(){
					jQuery(this).attr("disabled","disabled");
				});
		   }else{
				jQuery("#xsgwsqsplc").removeAttr("disabled");
				jQuery("input[name=rskzjbView]").each(function(){
					jQuery(this).removeAttr("disabled");
				});
		   }
	   }
	});
}

function checkSplcYj(){
	var splc=jQuery("#xsyjgwsqsplc").val();
	jQuery.ajax({
	   type: "POST",
	   url: "qgzx_xsgwsh.do?method=checkSplc",
	   data: {splc:splc,type:"5"},
	   dataType:"json",
	   success: function(data){
		   if(data["message"]=="true"){
				jQuery("#xsyjgwsqsplc").attr("disabled","disabled");
				jQuery("input[name=yjrskzjbView]").each(function(){
					jQuery(this).attr("disabled","disabled");
				});
		   }else{
				jQuery("#xsyjgwsqsplc").removeAttr("disabled");
				jQuery("input[name=yjrskzjbView]").each(function(){
					jQuery(this).removeAttr("disabled");
				});
		   }
	   }
	});
}
function setKzlc(value) {
	if (value == "") {
		jQuery("#rskzjbTd").html("");
		jQuery("#qxfwTd").html("");
		return;
	}
	var url = "qgzx_xsgwsh.do?method=loadRskz";
	jQuery.post(url, {
		value : value
	}, function(data) {
		var sHtml = "";
		var radio1 = "";
		if (data != null) {
			for ( var i = 0; i < data.length; i++) {
				var o = data[i];
				radio1 += "<label><input type='radio' name='rskzjbView' value='"
						+ o.spgwdm + "'/>";
				radio1 += o.spgwmc;
				radio1 += "</label>";
				if (i != data.length - 1) {
					radio1 += "<br/>";
				}
			}
			//设置数据范围
			setSjfw(data);
		}
		sHtml += radio1;
		jQuery("#rskzjbTd").html(sHtml);
		//添加监听获取选中的值
		addRskzJt();
		var isH=false;
		var rskzjb=jQuery("#rskzjb").val();
			//设置保存的当前值
			jQuery("input[name=rskzjbView]").each(function(){
				var v=jQuery(this).val();
				if(v==rskzjb){
					jQuery(this).attr("checked","checked");
					isH=true;
					return false;
				}
			});
		if(!isH){
			//默认最后一级选中
			var last=jQuery("input[name=rskzjbView]").last();
			last.attr("checked","checked");
			jQuery("#rskzjb").val(last.val());
		}
		checkSplc();
		//jQuery().atrr("checked","checked");
	}, 'json');
}
function setYjKzlc(value) {
	if (value == "") {
		jQuery("#yjqxfwTd").html("");
		return;
	}
	var url = "qgzx_xsgwsh.do?method=loadRskz";
	jQuery.post(url, {
		value : value
	}, function(data) {
		// var sHtml = "";
		// var radio1 = "";
		if (data != null) {
			/*for ( var i = 0; i < data.length; i++) {
				var o = data[i];
				radio1 += "<label><input type='radio' name='yjrskzjbView' value='"
						+ o.spgwdm + "'/>";
				radio1 += o.spgwmc;
				radio1 += "</label>";
				if (i != data.length - 1) {
					radio1 += "<br/>";
				}
			}*/
			//设置数据范围
			setYjSjfw(data);
		}
		// sHtml += radio1;
		// jQuery("#yjrskzjbTd").html(sHtml);
		//添加监听获取选中的值
		// addRskzJtYj();
		/*var isH=false;
		var rskzjb=jQuery("#yjrskzjb").val();
			//设置保存的当前值
			jQuery("input[name=yjrskzjbView]").each(function(){
				var v=jQuery(this).val();
				if(v==rskzjb){
					jQuery(this).attr("checked","checked");
					isH=true;
					return false;
				}
			});
		if(!isH){
			//默认最后一级选中
			var last=jQuery("input[name=yjrskzjbView]").last();
			last.attr("checked","checked");
			jQuery("#yjrskzjb").val(last.val());
		}
		checkSplcYj();*/
		//jQuery().atrr("checked","checked");
	}, 'json');
}

function setSjfw(data){
	var check="";
	for ( var i = 0; i < data.length; i++) {
		var o = data[i];
		check+="<label><input type=\"checkbox\" name=\"qxfwkz\" value='";
		check+=o.spgwdm+"'/>";
		check+=o.spgwmc;
		check+="</label>";
		if (i != data.length - 1) {
			check += "<br/>";
		}
	}
	jQuery("#qxfwTd").html(check);
	var isH=false;
    jQuery("input[name=qxfwkz]").each(function(){
    	var V=jQuery(this).val();
    	if(isHaveV(V)){
    		jQuery(this).attr("checked","checked");
    		isH=true;
    	}
    });
/*	if(!isH){
		//默认全部选中
		jQuery("input[name=qxfwkz]").eq(0).attr("checked","checked");
	}*/
	//监听权限级别只能选择一项
	jQuery("[name=qxfwkz]").bind("click",function(){
		var selectV=jQuery(this).val();
		jQuery("[name=qxfwkz]:checked").each(function(){
			var sv=jQuery(this).val();
			if(sv!=selectV){
				jQuery(this).removeAttr("checked");
			}
		});
	});
}
function setYjSjfw(data){

	var check="";
	for ( var i = 0; i < data.length; i++) {
		var o = data[i];
		check+="<label><input type=\"checkbox\" name=\"yjqxfwkz\" value='";
		check+=o.spgwdm+"'/>";
		check+=o.spgwmc;
		check+="</label>";
		if (i != data.length - 1) {
			check += "<br/>";
		}
	}
	jQuery("#yjqxfwTd").html(check);
	// var isH=false;
    jQuery("input[name=yjqxfwkz]").each(function(){
    	var V=jQuery(this).val();
    	if(isHaveVYj(V)){
    		jQuery(this).attr("checked","checked");
    		// isH=true;
    	}
    });
/*	if(!isH){
		//默认全部选中
		jQuery("input[name=qxfwkz]").eq(0).attr("checked","checked");
	}*/
	//监听权限级别只能选择一项
	jQuery("[name=yjqxfwkz]").bind("click",function(){
		var selectV=jQuery(this).val();
		jQuery("[name=yjqxfwkz]:checked").each(function(){
			var sv=jQuery(this).val();
			if(sv!=selectV){
				jQuery(this).removeAttr("checked");
			}
		});
	});
}
function isHaveV(V){
	var qxfw=jQuery("#qxfw").val();
	var qxfws=qxfw.split(",");
	for(var i=0;i<qxfws.length;i++){
		if(V==qxfws[i]){
			return true;
		}
	}
	return false;
}
function isHaveVYj(V){
	var qxfw=jQuery("#yjqxfw").val();
	var qxfws=qxfw.split(",");
	for(var i=0;i<qxfws.length;i++){
		if(V==qxfws[i]){
			return true;
		}
	}
	return false;
}

function addRskzJt(){
	jQuery("[name=rskzjbView]").bind("click",function(){
		var v=jQuery(this).val();
		jQuery("#rskzjb").val(v);
	});
}

function addRskzJtYj(){
	jQuery("[name=yjrskzjbView]").bind("click",function(){
		var v=jQuery(this).val();
		jQuery("#yjrskzjb").val(v);
	});
}


function changKssj(value){
	if(value==""){
		$("kssj").focus();
		jQuery("span[id=cjffError]").text("酬金发放开放时间不能为空!");
 		return false;
	}
	if(Number(value)>Number(jQuery("#jssj").val())){
		$("kssj").focus();
		jQuery("span[id=cjffError]").text("时间区间不正确!");
		return false;
	}else{
		jQuery("span[id=cjffError]").text("");
	}
}

function changJssj(value){
	if(value==""){
		$("jssj").focus();
		jQuery("span[id=cjffError]").text("酬金发放结束时间不能为空!");
 		return false;
	}
	if(Number(value)<Number(jQuery("#kssj").val())){
		$("jssj").focus();
		jQuery("span[id=cjffError]").text("时间区间不正确!");
 		return false;
	}else{
		jQuery("span[id=cjffError]").text("");
	}
}

//验证酬金发放参数
function yz_cjffcs(){
	/*if($("sxsz") && $("sxsz").value==""){
 		alertInfo("设置上限不能为空!",function(tag){
 			if(tag=="ok"){
 				$("sxsz").focus();
 				return false;
 			}
 		});
 		return false;
	}*/
	
	/*if($("ksyf") && $("ksyf").value==""){
 		alertInfo("酬金发放月份设定不能为空!",function(tag){
 			if(tag=="ok"){
 				$("ksyf").focus();
 				return false;
 			}
 		});
 		return false;
	}*/
	
	if($("kssj") && $("kssj").value==""){
 		alertInfo("酬金发放开放时间不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	
	if($("jssj") && $("jssj").value==""){
 		alertInfo("酬金发放开放时间不能为空!",function(tag){
 			if(tag=="ok"){
 				$("jssj").focus();
 				return false;
 			}
 		});
 		return false;
	}
	
	if($("yrdwgwsqxn") && $("yrdwgwsqxn").value==""){
 		alertInfo("用人单位岗位申请学年不能为空!",function(tag){
 			if(tag=="ok"){
 				$("yrdwgwsqxn").focus();
 				return false;
 			}
 		});
 		return false;
	}
	
	if($("yrdwsplc") && $("yrdwsplc").value==""){
		alertInfo("用人单位审批流程不能为空!",function(tag){
 			if(tag=="ok"){
 				$("yrdwgwsqxn").focus();
 				return false;
 			}
 		});
 		return false;
	}

	if(jQuery("#sfsdgwcjsx").val() == "yes"){
		if(parseFloat($("cjbz").value)>parseFloat($("gwzgcjsx").value)){
			alertInfo("酬金标准不能高于岗位最高酬金上限!",function(tag){
	 			if(tag=="ok"){
	 				$("cjbz").focus();
	 				return false;
	 			}
	 		});
	 		return false;
		}
	}

	if(Number($("kssj").value) > Number($("jssj").value)){
 		alertInfo("酬金发放开始时间不能大于结束时间!",function(tag){
 			if(tag=="ok"){
 				$("jssj").focus();
 				return false;
 			}
 		});
 		return false;
	}
	
	if(($("jsyf").value !="") && $("ksyf").value > $("jsyf").value){
 		alertInfo("酬金发放开放开始月份不能大于结束月份!",function(tag){
 			if(tag=="ok"){
 				$("jsyf").focus();
 				return false;
 			}
 		});
 		return false;
	}
	
	return true;
}
//验证用人单位参数
function yz_yrdwcs(){

	if($("xsgws") && $("xsgws").value==""){
 		alertInfo("学生岗位数不能为空!",function(tag){
 			if(tag=="ok"){
 				$("xsgws").focus();
 				return false;
 			}
 		});
 		return false;
	}
	if(jQuery("[name=sfkfgwsq]:checked").length==0){
		alertInfo("岗位开关不能为空!");
		return false;
	}
	//2014.7.4 统一取消申请时间必填验证
//	if(("on"==jQuery("[name=sfkfgwsq]:checked").val()) && $("gwsqkssj") && $("gwsqkssj").value=="" ){
// 		alertInfo("岗位申请开始时间不能为空!",function(tag){
// 			if(tag=="ok"){
// 				$("gwsqkssj").focus();
// 				return false;
// 			}
// 		});
// 		return false;
//	}
//	
//	if(("on"==jQuery("[name=sfkfgwsq]:checked").val()) && $("gwsqjssj") && $("gwsqjssj").value=="" ){
// 		alertInfo("岗位申请结束时间不能为空!",function(tag){
// 			if(tag=="ok"){
// 				$("gwsqjssj").focus();
// 				return false;
// 			}
// 		});
// 		return false;
//	}
//	
//	if(("on"==jQuery("[name=sfkfgwsq]:checked").val()) && $("gwsqkssj").value > $("gwsqjssj").value){
// 		alertInfo("岗位申请开始时间不能大于结束时间!",function(tag){
// 			if(tag=="ok"){
// 				$("gwsqjssj").focus();
// 				return false;
// 			}
// 		});
// 		return false;
//	}

	if("off"==jQuery("[name=sfkfgwsq]:checked").val()){
		jQuery("#gwsqkssj").val("");
		jQuery("#gwsqjssj").val("");
	}
	return true;
	
}
//验证用学生岗位参数
function yz_xsgw(){
	if($("xsxsgws") && $("xsxsgws").value==""){
 		alertInfo("学生可申请岗位数不能为空!",function(tag){
 			if(tag=="ok"){
 				$("xsxsgws").focus();
 				return false;
 			}
 		});
 		return false;
	}
	if(jQuery("[name=sfkfxsgwsq]:checked").length==0){
		alertInfo("请选择是否开放学生岗位申请!");
		return false;
	}
//	if(("on"==jQuery("[name=sfkfxsgwsq]:checked").val()) && $("xsgwsqkssj") && $("xsgwsqkssj").value=="" ){
// 		alertInfo("学生岗位申请开始时间不能为空!",function(tag){
// 			if(tag=="ok"){
// 				$("xsgwsqkssj").focus();
// 				return false;
// 			}
// 		});
// 		return false;
//	}
//	
//	if(("on"==jQuery("[name=sfkfxsgwsq]:checked").val()) && $("xsgwsqjssj") && $("xsgwsqjssj").value=="" ){
// 		alertInfo("学生岗位申请结束时间不能为空!",function(tag){
// 			if(tag=="ok"){
// 				$("xsgwsqjssj").focus();
// 				return false;
// 			}
// 		});
// 		return false;
//	}
	
//	if(("on"==jQuery("[name=sfkfxsgwsq]:checked").val()) && $("xsgwsqkssj").value > $("xsgwsqjssj").value){
// 		alertInfo("岗位申请开始时间不能大于结束时间!",function(tag){
// 			if(tag=="ok"){
// 				$("xsgwsqjssj").focus();
// 				return false;
// 			}
// 		});
// 		return false;
//	}
	
	if("off"==jQuery("[name=sfkfxsgwsq]:checked").val()){
		jQuery("#xsgwsqkssj").val("");
		jQuery("#xsgwsqjssj").val("");
	}
	
	if($("xsxsgws") && $("xsxsgws").value==""){
 		alertInfo("学生可申请岗位数不能为空!",function(tag){
 			if(tag=="ok"){
 				$("xsxsgws").focus();
 				return false;
 			}
 		});
 		return false;
	}
	
	if($("xsgws") && $("xsgws").value==""){
 		alertInfo("学生可获得岗位数不能为空!",function(tag){
 			if(tag=="ok"){
 				$("xsgws").focus();
 				return false;
 			}
 		});
 		return false;
	}

	if($("xsgwsqsplc") && $("xsgwsqsplc").value==""){
 		alertInfo("请选择校级学生岗位申请审批流!",function(tag){
 			if(tag=="ok"){
 				
 				return false;
 			}
 		});
 		return false;
	}
	if($("xsyjgwsqsplc") && $("xsyjgwsqsplc").value==""){
 		alertInfo("请选择院级级学生岗位申请审批流!",function(tag){
 			if(tag=="ok"){
 				
 				return false;
 			}
 		});
 		return false;
	}
	jQuery("[name=rs]")
	return true;
}
function checkGwSx(){
	var selectV=jQuery("#sfsdgwcjsx").val();
	if("no"==selectV){
		return true;
	}
	var sxzdV=jQuery("#sxzd").val();
	var sxszV=jQuery("#sxsz").val();
	var cjbzV=jQuery("#cjbz").val();
	var pksbzV=jQuery("#pkscjbz").val();
	var xscjsx=sxszV;
	var xscjsxPks=sxszV;
	if(sxzdV=="gs"){
		xscjsx=parseInt(sxszV)*parseInt(cjbzV);
		xscjsxPks=parseInt(sxszV)*parseInt(pksbzV);
	}
	var gwzgcjsxV=jQuery("#gwzgcjsx").val();
	if(parseInt(gwzgcjsxV)>parseInt(xscjsx)){
 		alertInfo("岗位酬金设定上限不得超过【设置上限(工时&酬金)】的酬金金额("+xscjsx+"元)!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	if(jQuery("#xxdm").val() == "10351"){
		var gwzgcjsxPks = jQuery("#pkscjzgsx").val();
		if(parseInt(gwzgcjsxPks)>parseInt(xscjsxPks)){
	 		alertInfo("贫困生岗位最高酬金设定上限不得超过【设置上限(工时&酬金)】的酬金金额("+xscjsxPks+"元)!",function(tag){
	 			if(tag=="ok"){
	 				return false;
	 			}
	 		});
	 		return false;
		}
	}
	return true;
}
function checkGwcj(xxdm){
	if("12309"!=xxdm){
		var selectV=jQuery("#sfsdgwcjsx").val();
		if("no"==selectV){
			return true;
		}else{
			var gwzgcjsxV=jQuery("#gwzgcjsx").val();
			var sfkgggwcjsxV=jQuery('input[name="sfkgggwcjsx"]:checked').val();
			if(null==gwzgcjsxV||""==gwzgcjsxV){
				alertInfo("请设置岗位最高酬金上限!",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
				return false;
			}
			if(null==sfkgggwcjsxV||""==sfkgggwcjsxV){
				alertInfo("请设置用人单位申请岗位时可否更改岗位酬金上限!",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
				return false;
			}
		}
	}
	return true;
}
function Save(){
    var ids = "cjsx"+"-"+"gzscsx";
    if(check(ids)==false){
        showAlert("请将带<font color='red'>*</font>的项目填写完整");
        return false;
	}
	var gzscsx = jQuery("#gzscsx").val();
    if(gzscsx>40){
        showAlert("每月工作时长上限不得超过40小时");
        return false;
	}
	if(!jQuery("input[name='sfkfgwsq']").is(":checked")){
        showAlert("请选择校内岗位申请开关！");
        return;
	}
	if(jQuery("input[name='sfkfgwsq']:checked").val() == "on"){
        if(!checkNotNull("yrdwsplc")){
            showAlert("请选择校内岗位发布审批流程！");
            return;
        }
	}
/*	if(!jQuery("input[name='sfkfxwgwsq']").is(":checked")){
        showAlert("请选择校外岗位申请开关！");
        return;
	}
    if(jQuery("input[name='sfkfxwgwsq']:checked").val() == "on"){
        if(!checkNotNull("xwgwsplc")){
            showAlert("请选择校外岗位发布审批流程！");
            return;
        }
    }*/
    if(!jQuery("input[name='sfkfxsgwsq']").is(":checked")){
        showAlert("请选择学生岗位申请开关！");
        return;
    }
    if(jQuery("input[name='sfkfxsgwsq']:checked").val() == "on"){
        if(!checkNotNull("xsgwsqsplc-xwxsgwsqsplc-gzscwhsplc-xslzsplc")){
            showAlert("请选择学生岗位审批流程！");
            return;
        }
    }
    if(!jQuery("input[name='gzsqkg']").is(":checked")){
        showAlert("请选择工资申报开关！");
        return;
    }
    if(jQuery("input[name='gzsqkg']:checked").val() == "on"){
        if(!checkNotNull("gzsqsplc")){
            showAlert("请选择工资申报审批流程！");
            return;
        }
    }
    var url = "qgzx_jcsz_ajax.do?method=save";
    ajaxSubFormWithFun("qgzxCsszForm",url,function(data){
        if (data["message"] == "保存成功！") {
            showAlert(data["message"], {}, {
                "clkFun" : function() {
                    if (parent.window) {
                        refershParent();
                    }
                }
            });
        } else {
            showAlert(data["message"]);
        }
    });
}

//更新上限数值
function changSxsz(){
	var sxzd = jQuery("#sxzd").val();
	if(sxzd=="je"){
		jQuery("#font_sxsz").text("元(学生每月酬金不得超过该值)");
		jQuery("#sxsz").val("");
		jQuery("#sxsz").focus();
	}else if(sxzd=="gs"){
		jQuery("#font_sxsz").text("小时(学生每月工时不得超过该值)");
		jQuery("#sxsz").val("");
		jQuery("#sxsz").focus();
	}else{
		jQuery("#font_sxsz").text();
	}
}

//更新岗位申请
function changeGwsq(){
	var sfkfgwsq = jQuery("[name=sfkfgwsq]:checked").val();
	if("on"==sfkfgwsq){
		jQuery("#div_gwsqkfsj").show();
	}else if("off"==sfkfgwsq){
		jQuery("#div_gwsqkfsj").hide();
	}
}
//更新学生岗位申请
function changexsGwsq(){
	var sfkfgwsq = jQuery("[name=sfkfxsgwsq]:checked").val();
	if("on"==sfkfgwsq){
		jQuery("#div_xsgwsqkfsj").show();
	}else if("off"==sfkfgwsq){
		jQuery("#div_xsgwsqkfsj").hide();
	}
}

function changeSqkg(){
	var sqkg = jQuery("[name=sqkg]:checked").val();
	if("1"==sqkg){
		jQuery("[name=tr_cjff]").show();
	}else if("0"==sqkg){
		jQuery("[name=tr_cjff]").hide();
	}
}

//验证是否为空
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

