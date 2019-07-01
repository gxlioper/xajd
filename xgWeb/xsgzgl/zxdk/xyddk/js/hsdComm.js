
/**
 * js自动加载表单内容
 * 华师大校园地贷款(国家助学贷款)申请和结果都需要这几个函数,避免代码冗余,因此单独起一个js
 * by [1206]yxy date:2016-11-09
 * @return
 */
function autoProduce(){
	var rs = getDqqx();
	/**
	 * 贷款期数，贷款期限，金额上限，当前学年
	 */
	var dkqs = parseInt(rs['dkqs']);
	var dkqx = parseInt(rs['dkqx']);
	var jesx = parseInt(rs['jesx']);
	var currxn = rs['currxn'];
	jQuery("#jesx").val(jesx);
	
	
	produceDkqsqx(dkqs,dkqx,currxn,jesx);
}

/**
 * 获取贷款期限
 * @return
 */
function getDqqx(){
	var rs = null;
	var xh = jQuery("#xh").val();
	var url = "zxdkXyddk.do?method=getHsdDkqx";
		jQuery.ajax({
			type:'post',
			url:url,
			dataType:'json',
			contentType:"application/x-www-form-urlencoded; charset=UTF-8",
			data:'xh='+xh,
			async: false,
			success:function(result){
				if(result['rs'] == 'false'){
					showAlert(result['message'],{},{"clkFun":function(){
						jQuery("#bccg").hide();
						jQuery("#tjsq").hide();
					}});
					return false;
				}else{
					if(result['dkqs'] == '0' || (!result['dkqs']) || parseInt(result['dkqs']) <0){
						showAlert("贷款期数为0，不能进行申请！",{},{"clkFun":function(){
							jQuery("#bccg").hide();
							jQuery("#tjsq").hide();
						}});
					}
					rs = result;
				}
			}
			
		});
	   return rs;
}

/**
 * 生成贷款期限期数
 * @return
 */
function produceDkqsqx(dkqs,dkqx,currxn,jesx){
	/**
	 * 为了绑定事件，先清除select元素
	 */
	jQuery("#dkqxtd").empty();
	var html = "<select name='dkqx' style='width:98%'  id='dkqx'>";
	for(var i=0;i<dkqs;i++){
		if(i+1 == dkqs){
			html +="<option value='"+(i+1)+"' selected='selected'>"+(i+1)+"</option>";
		}else{
			html +="<option value='"+(i+1)+"'>"+(i+1)+"</option>";
		}
		
	}
	html += "</select>";
	jQuery("#jhr1").val(dkqx);
	jQuery("#tip").text(jesx);
	/**
	 * 绑定事件
	 */
	var htmlobj = jQuery(html);
	jQuery(htmlobj).change(function(){
		//将各款项置0
		jQuery("#zsfdks").val("0");
		jQuery("#xfdks").val("0");
		jQuery("#shfdks").val("0");
		jQuery("#dkje").val("0");
		produceTableContent(parseInt(this.value),currxn,jesx);
		/**
		 * 贷款期限置默认值，可改
		 */
		jQuery("#jhr1").val(parseInt(this.value)*12);
	});
	jQuery("#dkqxtd").append(htmlobj);
	produceTableContent(dkqs,currxn,jesx);
}

/**
 * 生成表格内容
 * @return
 */
function produceTableContent(dkqs,currxn,jesx){
	jQuery("tr").remove(".showtr");
	/**
	 * 输入框需要绑定计算方法
	 */
	var html = "";
	var currnd1 = parseInt((currxn.split("-"))[0]);
	var currnd2 = parseInt((currxn.split("-"))[1]);
	for(var i = 0;i < dkqs;i++){
		/**
		 * 暂时学年下拉框只给两个值,并选中默认值
		 */
		var xn1 = (currnd1+i)+"-"+(currnd2+i);
		var xn2 = (currnd1+i+1)+"-"+(currnd2+i+1);
		html +="<tr class ='showtr'>";
		html +="<td>";
		html +="<select name='xn' style='width:98%'>";
		html +="<option value='"+xn1+"'>"+xn1+"</option>";
		html +="<option value='"+xn2+"'>"+xn2+"</option>";
		html +="</select>";
	    html +="</td>";
	    html +="<td>";
	    html +="<input name='nzsfdk' class='dk'  style='width:98%' maxlength='5' onkeyup='checkInput(this)' />";
	    html +="</td>";
	    html +="<td>";
	    html +="<input name='nxfdk'  class='dk'  style='width:98%' maxlength='5' onkeyup='checkInput(this)' />";
	    html +="</td>";
	    html +="<td>";
	    html +="<input name='nshfdk' class='dk'  style='width:98%' maxlength='5' onkeyup='checkInput(this)' />";
	    html +="</td>";
	    html +="<td>";
	    html +="<input name='nzsfyje'  style='width:98%' maxlength='5' onkeyup='checkInput(this)' />";
	    html +="</td>";
	    html +="<td>";
	    html +="<input name='nxfyje'  style='width:98%' maxlength='5' onkeyup='checkInput(this)' />";
	    html +="</td>";
		html +="</tr>";
	}
	/**
	 * 输入input
	 */
	var htmlobj = jQuery(html);
	jQuery(htmlobj).find(".dk").change(function(){
		checkNullInput(this);
		calculate(this);
	});
	jQuery("#tableshow").show();
	jQuery("#table").append(htmlobj);
}

/**
 * 根据输入金额自动计算贷款款项
 * @return
 */
function  calculate(obj){
	/**
	 * 计算各项贷款金额,需要判断是否超过金额上限
	 */
	var jesx = parseInt(jQuery("#jesx").val());
	if(obj.value > jesx){
		showAlert("超出金额上限！",{},{"clkFun":function(){
			jQuery(obj).val("0");
			return false;
		}});
	}
	if(obj.name == 'nzsfdk' || obj.name == 'nxfdk' || obj.name == 'nshfdk' ){
		calEverykx(obj);
	}else{
		return false;
	}
	
}


/**
 * 根据name计算
 * @return
 */	
function calEverykx(obj){
	/**
	 * 先置计算结果为0,超出金额上限
	 */
	var name = obj.name;
	var value = parseInt(obj.value);
	var jsjg = 0;
	var xnjsjg = 0;
	var jesx = parseInt(jQuery("#jesx").val());
	/**
	 * 计算总贷款
	 */
	jQuery(".dk").not(obj).each(function(i,row){
		var rowvalue = this.value;
		if(rowvalue == '' || rowvalue == null){
			rowvalue = 0;
		}else{
			rowvalue = parseInt(rowvalue);
		}
		jsjg = jsjg + rowvalue;
	})
	
	jQuery(obj).parent().parent().find(".dk").not(obj).each(function(i,row){
		var rowvalue = this.value;
		if(rowvalue == '' || rowvalue == null){
			rowvalue = 0;
		}else{
			rowvalue = parseInt(rowvalue);
		}
		xnjsjg = xnjsjg + rowvalue;
	})
	/**
	 * 如果计算金额累计超出金额上限, 输入框置为0
	 */
	xnjsjg = xnjsjg + value;
	if(xnjsjg >jesx){
		jQuery(obj).val("0");
	}else{
		jsjg = jsjg + value;
	}
	jQuery("#dkje").val(jsjg);
	
	/**
	 * 计算各项贷款款项
	 */
	value = parseInt(obj.value);
	
	jsjg = 0;

	jQuery("[name='"+name+"']").not(obj).each(function(i,row){
		var rowvalue = this.value;
		if(rowvalue == '' || rowvalue == null){
			rowvalue = 0;
		}else{
			rowvalue = parseInt(rowvalue);
		}
		jsjg = jsjg + rowvalue;
		
	});
	/**
	 * 如果计算金额累计超出金额上限, 输入框置为0
	 */
	jsjg = jsjg + value;
//	if(jsjg >jesx){
//		jsjg = jsjg-value;
//		jQuery(obj).val("0");
//	}
	
	/**
	 *判断赋值
	 */
	if(name == 'nzsfdk'){
		/**
		 * 住宿费
		 */
		jQuery("#zsfdks").val(jsjg);
	}else if(name == 'nxfdk'){
		/**
		 * 学费
		 */
		jQuery("#xfdks").val(jsjg);
	}else if(name == 'nshfdk'){
		/**
		 * 生活费
		 */
		jQuery("#shfdks").val(jsjg);
	}
	
	
}

/**
 * 申请保存
 * @param type
 * @return
 */
function saveSqForHsd(type){
	if(!checkFormdata()){
		return false;
	}
	
	var url = "zxdkXyddk.do?method=saveDksqForHsd&type="+type;
	ajaxSubFormWithFun("xyddkForm",url,function(data){
		if(data["message"] == "保存成功！"){
			showAlert(data["message"],{},{"clkFun":function(){
				refershParent();
			}});
		}else{
			showAlert(data["message"],{},{"clkFun":function(){
				return false;
			}});
		}
		
	});
}

/**
 * 修改保存
 * @param type
 * @return
 */
function saveSqForHsdupdate(type){
	if(!checkFormdata()){
		return false;
	}
	
	var url = "zxdkXyddk.do?method=saveAndSubmitForHsd&type="+type;
	ajaxSubFormWithFun("xyddkForm",url,function(data){
		if(data["message"] == "保存成功！"){
			showAlert(data["message"],{},{"clkFun":function(){
				refershParent();
			}});
		}else{
			showAlert(data["message"],{},{"clkFun":function(){
				return false;
			}});
		}
		
	});
}

/**
 * 申请结果保存
 * @param type
 * @return
 */
function saveJgForHsd(url){
	if(jQuery.trim(jQuery("#htbh").val()) == ""){
		showAlert("合同编号不能为空！");
	     return false;
	}
	if(!checkFormdata()){
		return false;
	}
	
	ajaxSubFormWithFun("xyddkForm",url,function(data){
		if(data["message"] == "保存成功！"){
			showAlert(data["message"],{},{"clkFun":function(){
				refershParent();
			}});
		}else{
			showAlert(data["message"],{},{"clkFun":function(){
				return false;
			}});
		}
		
	});
}


/**
 * 保存前验证数据
 */
function checkFormdata(){
	var str = "xh"+"-"+"zsfdks"+"-"+"xfdks"+"-"+"shfdks"+"-"+"dkje"+"-"+"sqly"+"-"+"dkqx"+"-"+"jhr1";
	if(!checkNotNull(str)){
		showAlert("请将带<font class='red'>*</font>的项目填写完整！",{},{"clkFun":function(){
			
		}});
		return false;
	}
	var flag = true;
	jQuery(".showtr").find("input").each(function(i,row){
		var rowvalue = this.value;
		if(jQuery.trim(rowvalue) == null ||  jQuery.trim(rowvalue) == ""){
			flag = false;
			return flag;
		}
	})
	if(!flag){
		showAlert("请将带<font class='red'>*</font>的项目填写完整！",{},{"clkFun":function(){
			
		}});
		return false;
	}
	
//	if(parseInt(jQuery("#dkje").val()) > parseInt(jQuery("#jesx").val())){
//		showAlert("不得超过金额上限"+jQuery("#jesx").val()+"元！",{},{"clkFun":function(){
//			
//		}});
//		return false;
//	}
	
	jQuery("[name='xn']").each(function(i,row){
		var rowvalue = this.value;
		var num = jQuery("option[value='"+rowvalue+"']:selected").length;
		if(num > 1){
			flag = false;
			return flag;
		}
	})
	if(!flag){
		showAlert("学年不能重复！",{},{"clkFun":function(){
			
		}});
		return false;
	}
	if(jQuery("#sqly").val().length > 500){
		showAlertDivLayer("申请理由不得超过500字！",{},{"clkFun":function(){
			flag = false;
		}});
		if(!flag){
			return flag;
		}
	}
	//是否弹出家庭情况调查填写页面
	var openJtqk = jQuery("#openJtqk").val();
	if ("true" == openJtqk){
		var xh = jQuery("#xh").val();
		showAlertDivLayer("请先填写家庭情况调查表！",{},{"clkFun":function(){
			editJtqk();
		}});
		return false;
	}
	return flag;

}

