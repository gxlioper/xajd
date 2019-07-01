/**
 * 模块信息（维护页面）中下拉选项加载和赋值
 * @return
 */
function loadMkxxSelectOptions(){
	var selectList = jQuery("select[type = 'source']");
	jQuery.each(selectList,function(i,n){
		var selectNode = jQuery(n);
		var sjy = selectNode.attr("source");//数据源配置详情
		//根据数据源配置详情，后台解析并返回List
		jQuery.post("xszz_bdpz.do?method=getSjyList",{sjy:sjy},function(data){
			if (data != null){
				jQuery.each(data,function(i,e){
					var option = jQuery("<option value='"+e["dm"]+"'>"+e["mc"]+"</option>");

					if (e["dm"] == selectNode.attr("tempValue")){
						option.attr("selected","selected");
					}
					selectNode.append(option);
				});
			}
		},'json');
	});
} 




/**
 * 模块信息（维护页面）中单选项加载和赋值
 * @return
 */
function loadMkxxRadioOptions(){
	var tds = jQuery("td[name=radioTd]");

	jQuery.each(tds,function(i,n){
		
		var sjy = jQuery(n).attr("source");
		var radioName = jQuery(n).attr("property");
		
		jQuery.post("xszz_bdpz.do?method=getSjyList",{sjy:sjy},function(data){
			if (data != null){
				jQuery.each(data,function(i,e){
					var radioHtml = "<input type='radio' name='"+radioName+"' value='"+e["dm"]+"' ";

					if (e["dm"] == jQuery(n).attr("tempValue")){
						radioHtml+="checked='checked'";
					}
					
					radioHtml+="/>";
					
					var label = jQuery("<label></label>");
					label.append(radioHtml);
					label.append(e["mc"]);
					jQuery(n).append(label);
				});
			}
		},'json');
	});
}



/**
 * 模块信息(查看页面)中下拉选项加载和赋值
 * @return
 */
function loadViewMkxxSelectOptions(){
	var selectList = jQuery("select[type = 'source']");

	jQuery.each(selectList,function(i,n){
		var selectNode = jQuery(n);
		var sjy = selectNode.attr("source");//数据源配置详情
		//根据数据源配置详情，后台解析并返回List
		jQuery.post("xszz_bdpz.do?method=getSjyList",{sjy:sjy},function(data){
			if (data != null){
				jQuery.each(data,function(i,e){
					
					if (selectNode.attr("tempValue") == ""){
						selectNode.parent().html("");
						return ;
					}
					
					if (e["dm"] == selectNode.attr("tempValue")){
						selectNode.parent().html(e["mc"]);
						return ;
					}
				});
			}
		},'json');
	});
} 


/**
 * 模块信息（查看页面）中单选项加载和赋值
 * @return
 */
function loadViewMkxxRadioOptions(){
	var tds = jQuery("td[name=radioTd]");

	jQuery.each(tds,function(i,n){
		
		var sjy = jQuery(n).attr("source");
		var radioName = jQuery(n).attr("property");
		
		jQuery.post("xszz_bdpz.do?method=getSjyList",{sjy:sjy},function(data){
			if (data != null){
				jQuery.each(data,function(i,e){
					if (e["dm"] == jQuery(n).attr("tempValue")){
						jQuery(n).html(e["mc"]);
						return ;
					}
				});
			}
		},'json');
	});
}




/**
 * 家庭成员(查看页面)中下拉选项加载和赋值
 * @return
 */
function loadViewJtcySelectOptions(){
	var sjyList = jQuery("input[name=sjy]");
	
	jQuery.each(sjyList,function(i,n){
		var sjy = jQuery(n).val();//数据源配置详情
		var selectName = jQuery(n).attr("selectName");
		var selectNode = jQuery("select[labName="+selectName+"]");
		
		//根据数据源配置详情，后台解析并返回List
		jQuery.post("xszz_bdpz.do?method=getSjyList",{sjy:sjy},function(data){
			if (data != null){
				jQuery.each(data,function(i,e){
					var option = jQuery("<option value='"+e["dm"]+"'>"+e["mc"]+"</option>");

					selectNode.append(option);
				});

				jQuery.each(selectNode,function(i,e){
					
					var node = jQuery(e);
					if (node.attr("tempValue") == ""){
						node.parent().html("");
						return ;
					}
					
					node.parent().html(node.find("option[value="+node.attr("tempValue")+"]").html());
					return ;
				});
			}
		},'json');
	});
}



/**
 * 家庭成员（维护页面）中下拉选项加载和赋值
 * @return
 */
function loadJtcySelectOptions(){
	var sjyList = jQuery("input[name=sjy]");
	
	jQuery.each(sjyList,function(i,n){
		var sjy = jQuery(n).val();//数据源配置详情
		var selectName = jQuery(n).attr("selectName");
		var selectNode = jQuery("select[labName="+selectName+"]");
		
		//根据数据源配置详情，后台解析并返回List
		jQuery.post("xszz_bdpz.do?method=getSjyList",{sjy:sjy},function(data){
			if (data != null){
				jQuery.each(data,function(i,e){
					var option = jQuery("<option value='"+e["dm"]+"'>"+e["mc"]+"</option>");

					selectNode.append(option);
				});

				jQuery.each(selectNode,function(i,e){
					var node = jQuery(e);
					node.find("option[value="+node.attr("tempValue")+"]").attr("selected","selected");
				});
			}
		},'json');
	});
}


/**
 * 验证模块必填项
 * @return
 */
function checkMustNotNull(){
	
	//验证textarea
	var textarea = jQuery("textarea[sfbt=yes]");
	//验证radio
	var radioTds = jQuery("td[name=radioTd][sfbt=yes]");
	//验证select
	var select = jQuery("select[sfbt=yes]");
	//验证input
	var text = jQuery("input[sfbt=yes]");

	var textareaFlg = true;
	jQuery.each(textarea,function(i,e){
		var textareaVal = jQuery(e).val();
		if (textareaVal == null || textareaVal == ''){
			textareaFlg = false;
			return;
		}
	});
		
	var textFlg = true;
	jQuery.each(text,function(i,e){
		var textVal = jQuery(e).val();
		if (textVal == null || textVal == ''){
			textFlg = false;
			return;
		}
	});
	
	var selectFlg = true;
	jQuery.each(select,function(i,e){
		var selectVal = jQuery(e).val();
		if (selectVal == null || selectVal == ''){
			selectFlg = false;
			return;
		}
	});
	
	var radioFlg = true;
	jQuery.each(radioTds,function(i,e){
		var radios = jQuery(e).find(":radio:checked");
		if (radios.length == 0){
			radioFlg = false;
			return;
		}
	});
	
	return textareaFlg && radioFlg && selectFlg && textFlg;
}






