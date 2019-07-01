jQuery(function() {
	onShow();
	changeJgzq();
});

function onShow() {
	jQuery("#splcOld").val(jQuery("#splc").val());

	jQuery("input:radio[name=sqkg]").change(function() {// 申请开关控制
				setSqkg(jQuery(this).val());
			});

	jQuery("input:radio[name=rskg]").change(function() {// 人数控制开关控制
				setRskg(jQuery(this).val());
			});

	jQuery("select#splc").change(function() {// 人数控制级别，兼得控制级别，根据审核流程进行显示
		setRsJdView(jQuery(this).val());
			});
	jQuery("select#splc").change();

	jQuery("#rskzfwOld").val(jQuery("#rskzfw").val());//保存原来的人数控制范围
	
	//默认设置当前学年，学期
	var sqkgLength = jQuery("input:radio[name=sqkg]:checked").length;//申请开关
	if(sqkgLength == 0){//申请开关未设置
		jQuery("select#sqxn").val(jQuery("#currXn").val());
		jQuery("select#sqxq").val(jQuery("#currXq").val());
	}
}

/*
 * 根据审核状态，隐藏部分项
 * 
 */
function setSpzt(){
	var spzt = jQuery("#spzt").val();
	if(spzt == "true"){
		jQuery("#spztTip").css("display","");
		jQuery("table input:not(input:radio[name=sqkg],input:radio[name=xslb],#sqkssj,#sqjssj),select").attr(
				"disabled", "disabled");
		jQuery("#jgsqzq").attr("disabled",false);
	}
}

/*
 * 申请开关设置
 */
function setSqkg(value) {
	if (value == 0) {
		jQuery("table select,input:not(input:radio[name=sqkg])").attr(
				"disabled", "disabled");
		if("14008" == jQuery("#xxdm").val()) {
			jQuery("table div select,input:radio[name=shkg]").attr("disabled",false);
			jQuery("table div select,input:text[name=shkssj]").attr("disabled",false);
			jQuery("table div select,input:text[name=shjssj]").attr("disabled",false);
		}
		
	} else {
		jQuery("table select,input:not(input:radio[name=sqkg])").attr(
				"disabled",false);
		setRskg(jQuery("input:radio[name=rskg]:checked").val());// 人数控制开关默认
		setSpzt();//根据审核状态，隐藏部分项
		if("14008" == jQuery("#xxdm").val()) {
			jQuery("table div select,input:radio[name=shkg]").attr("disabled",false);
			jQuery("table div select,input:text[name=shkssj]").attr("disabled",false);
			jQuery("table div select,input:text[name=shjssj]").attr("disabled",false);
		}
	}
}

/*
 * 人数控制开关设置
 */
function setRskg(value) {
	if (value == 0) {
		jQuery("#rskzjbTd input").add("select#rskzfw").attr("disabled", "disabled");
	} else {
		jQuery("#rskzjbTd input").add("select#rskzfw").attr("disabled",false);
	}
}

/*
 * 兼得控制开关设置
 */
function setJdkg(value) {
	if (value == 0) {
		jQuery("#jdkzjbTd input").attr("disabled", "disabled");
	} else {
		jQuery("#jdkzjbTd input").attr("disabled",false);
	}
}


// 人数控制级别，兼得控制级别，根据审核流程进行显示
function setRsJdView(value) {
	var url = "xszz_xmwh.do?method=xmwhShfw";
	jQuery.post(url, {
		value : value
	}, function(data) {
		var radio1 = "";
		var radio2 = "";
		if(data != null){
			for ( var i = 0; i < data.length; i++) {
				var o = data[i];
				radio1 += "<label><input type='radio' name='rskzjbView' value='"
						+ o.spgwdm + "'/>";
				radio1 += o.spgwmc;
				radio1 += "</label>";
				if (i != data.length - 1) {
					radio1 += "<br/>";
				}
				
				radio2 += "<label><input type='radio' name='jekzfw' value='"
					+ o.spgwdm + "'/>";
				radio2 += o.spgwmc;
				radio2 += "</label>";
				if (i != data.length - 1) {
					radio2 += "<br/>";
				}
			}
		}
		jQuery("#rskzjbTd").html(radio1);
		jQuery("#jfkzjbTd").html(radio2);

		// 人数控制级别，兼得控制级别，设置初值
			jQuery("input:radio[name=rskzjbView][value=" + jQuery("#rskzjb").val() + "]").attr("checked","checked");
			jQuery("input:radio[name=jekzfw][value=" + jQuery("#jekzfwOld").val() + "]").attr("checked","checked");
			setSqkg(jQuery("input:radio[name=sqkg]:checked").val());// 申请开关默认
		}, 'json');
}

//根据困难生认定周期，查询可申请项目的困难档次
function setKnsrddc(value,type) {
	var xn,xq;
	if(type == "xn"){
		xn = value;
		xq = jQuery("#knsbdxq").val();
	}else if(type == "xq"){
		xn = jQuery("#knsbdxn").val();
		xq = value;
	}else{
		xn = jQuery("#knsbdxn").val();
		xq = jQuery("#knsbdxq").val();
	}	
	var url = "xszz_xmwh.do?method=xmwhKnsrddc";
	jQuery.post(url, {
		xn : xn,
		xq : xq
	}, function(data) {
		var html = "";
		for ( var i = 0; i < data.length; i++) {
			var o = data[i];
			html += "<input type='checkbox' name='knsbddcView' value='"+o.dm+"'/>";
			if(o.mc != null){
				html += o.mc + " ";
			}
		}
		jQuery("#knsrddcTd").html(html);
				
		//申请项目的困难档次，赋初值
		var knsbddc = jQuery("#knsbddc").val();
		var arr = knsbddc.split(",");
		jQuery.each(arr,function(index,value){
			jQuery("input:checkbox[name=knsbddcView][value="+value+"]").attr("checked","checked");
		});

		setSqkg(jQuery("input:radio[name=sqkg]:checked").val());// 申请开关默认
		}, 'json');
}

function saveForm() {
	var sqkg = jQuery("input:radio[name=sqkg]:checked").val();//申请开关
	var splc = jQuery("select#splc").val();
	var rskzfw = jQuery("#rskzfw").val();
	var jgsqzq = jQuery("select#jgsqzq").val();
	// 人数开关级别设置值，以便提交用
	var rskzjb = jQuery("input:radio[name=rskzjbView]:checked").val();
	var jekzfw = jQuery("input:radio[name=jekzfw]:checked");
	jQuery("#rskzjb").val(rskzjb);
	
	if (sqkg == undefined || splc == null || rskzfw == null || rskzfw == "" || rskzjb == null || rskzjb == "" || jekzfw.size() == 0|| jgsqzq == "") {
		showAlert("请将带*的项目填写完整！");
		return false;
	}
	if("14008" == jQuery("#xxdm").val()) {
		var shkg = jQuery("input:radio[name=shkg]:checked").val();
		if(shkg == undefined) {
			showAlert("请将带*的项目填写完整！");
			return false;
		}
	}
	if(sqkg == 1){//申请开关开启状态，需要验证
		var sqkssj = jQuery("#sqkssj").val();
		var sqjssj = jQuery("#sqjssj").val();
		
//		2014.7.4 取消时间必填控制
//		if (splc == null|| sqkssj == null|| sqjssj == null || 
//				splc == ""|| sqkssj == ""|| sqjssj == "") {
//			showAlert("请将带*的项目填写完整！");
//			return false;
//		}
		
		if(sqkssj != "" && sqjssj != "" && sqkssj > sqjssj){
			showAlert("开始时间不能大于结束时间！");
			return false;
		}
		var pdxn = jQuery("#pdxn").val();
		if(pdxn == null|| pdxn == ""){
			showAlert("项目评定学年不能为空！");
			return false;
		}
		
		var sfkns = jQuery("input:radio[name=sfkns]:checked").val();//是否困难生开关
		if(sfkns == 1){//若选中
			var knsbdxn = jQuery("select#knsbdxn").val();//困难生认定周期-学年
			if (knsbdxn == null || knsbdxn == "") {
				showAlert("请填写[困难生认定周期-学年]！");
				return false;
			}
			var knsbdxq = jQuery("select#knsbdxq").val();//困难生认定周期-学期
			if (knsbdxq == null || knsbdxq == "") {
				showAlert("请填写[困难生认定周期-学期]！");
				return false;
			}
			
			var rskgLeng = jQuery("input:radio[name=rskg]:checked").length;//人数控制开关
			if(rskgLeng == 0){
				showAlert("请选择[人数控制开关]！");
				return false;
			}
			
			var jdkgLeng = jQuery("input:radio[name=jdkg]:checked").length;//兼得控制开关
			if(jdkgLeng == 0){
				showAlert("请选择[兼得控制开关]！");
				return false;
			}
		}
		
		var rskg = jQuery("input:radio[name=rskg]:checked").val();//人数开关
		if(rskg == 1){//若选中
			var rskzjbView = jQuery("input:radio[name=rskzjbView]:checked").length;//人数控制级别
			if(rskzjbView == 0){
				showAlert("请选择[人数控制级别]！");
				return false;
			}
		}
	}
	
	//困难生档次选定值作处理，以便提交后台
	var knsbddcView = "";
	var flag = false;
	jQuery("input:checkbox[name=knsbddcView]:checked").each(function(index){
		if(flag){
			knsbddcView += ",";
		}else{
			flag = true;
		}
		knsbddcView += jQuery(this).val();
	});
	jQuery("#knsbddc").val(knsbddcView);	
	
//取消不可用，以便提交
	jQuery("table select,input").attr(
			"disabled",false);
	
	//兼得开关级别设置值，以便提交用
	var jdkzjb = jQuery("input:radio[name=jdkzjbView]:checked").val();
	jQuery("#jdkzjb").val("sq");
	//人数开关级别设置值，以便提交用
	var rskzjb = jQuery("input:radio[name=rskzjbView]:checked").val();
	jQuery("#rskzjb").val(rskzjb);
	
	var url = "xszz_xmwh.do?method=xmwhJbsz&type=save";
	ajaxSubFormWithFun("form1", url, function(data) {
		showAlert(data["message"],{},{"clkFun":  function(tag) {
			if (tag == "ok") {
				refershParent();
			}
		}});
	});


}

function changeJgzq(){
	
	if(""==jQuery("#pdxq").val()){
		jQuery("#jgsqzq option[value=0]").show();
	}else{
		if("0"==jQuery("#jgsqzq").val()){
			jQuery("#jgsqzq").val("1");
		}
		
		jQuery("#jgsqzq option[value=0]").hide();
	}
}