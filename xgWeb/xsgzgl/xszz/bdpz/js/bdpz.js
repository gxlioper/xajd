
/**
 * ģ����Ϣ��ά��ҳ�棩������ѡ����غ͸�ֵ
 * @return
 */
function loadMkxxSelectOptions(){
	var selectList = jQuery("select[type = 'source']");

	jQuery.each(selectList,function(i,n){
		var selectNode = jQuery(n);
		var sjy = selectNode.attr("source");//����Դ��������
		//��������Դ�������飬��̨����������List
		jQuery.post("xszz_bdpz.do?method=getSjyList",{sjy:sjy},function(data){
			if (data != null){
				jQuery.each(data,function(i,e){
					e = e||{};
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
 * ģ����Ϣ��ά��ҳ�棩�е�ѡ����غ͸�ֵ
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
 * ģ����Ϣ(�鿴ҳ��)������ѡ����غ͸�ֵ
 * @return
 */
function loadViewMkxxSelectOptions(){
	var selectList = jQuery("select[type = 'source']");

	jQuery.each(selectList,function(i,n){
		var selectNode = jQuery(n);
		var sjy = selectNode.attr("source");//����Դ��������
		//��������Դ�������飬��̨����������List
		jQuery.post("xszz_bdpz.do?method=getSjyList",{sjy:sjy},function(data){
			if (data != null){
				jQuery.each(data,function(i,e){
					e = e||{};
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
 * ģ����Ϣ���鿴ҳ�棩�е�ѡ����غ͸�ֵ
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
 * ��ͥ��Ա(�鿴ҳ��)������ѡ����غ͸�ֵ
 * @return
 */
function loadViewJtcySelectOptions(){
	var sjyList = jQuery("input[name=sjy]");
	
	jQuery.each(sjyList,function(i,n){
		var sjy = jQuery(n).val();//����Դ��������
		var selectName = jQuery(n).attr("selectName");
		var selectNode = jQuery("select[labName="+selectName+"]");
		
		//��������Դ�������飬��̨����������List
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
 * ��ͥ��Ա��ά��ҳ�棩������ѡ����غ͸�ֵ
 * @return
 */
function loadJtcySelectOptions(){
	var sjyList = jQuery("input[name=sjy]");
	
	jQuery.each(sjyList,function(i,n){
		var sjy = jQuery(n).val();//����Դ��������
		var selectName = jQuery(n).attr("selectName");
		var selectNode = jQuery("select[labName="+selectName+"]");
		
		//��������Դ�������飬��̨����������List
		jQuery.post("xszz_bdpz.do?method=getSjyList",{sjy:sjy},function(data){
			if (data != null){
				jQuery.each(data,function(i,e){
					var option = jQuery("<option value='"+e["dm"]+"'>"+e["mc"]+"</option>");

					selectNode.append(option);
				});

				jQuery.each(selectNode,function(i,e){
					var node = jQuery(e);
					node.find("option[value='"+node.attr("tempValue")+"']").attr("selected","selected");
				});
			}
		},'json');
	});
}


/**
 * ��֤ģ�������
 * @return
 */
function checkMustNotNull(){
	//��֤textarea
	var textarea = jQuery("textarea[sfbt=yes]");
	//��֤input
	var text = jQuery("input[sfbt=yes]");
	//��֤select
	var select = jQuery("select[sfbt=yes]");
	//��֤radio
	var radioTds = jQuery("td[name=radioTd][sfbt=yes]");
	//��֤checkbox
	var checkboxTds = jQuery("td[name=checkboxTd][sfbt=yes]");
	
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
	var checkboxFlg = true;
	jQuery.each(checkboxTds,function(i,e){
		var radios = jQuery(e).find(":checkbox:checked");
		if (radios.length == 0){
			radioFlg = false;
			return;
		}
	});
//	if ("10026" == jQuery("#xxdm").val() && "jtqkdc" == jQuery("#flag").val()){
//		text = jQuery("input[sfbt=yes][value=][name!=ylzd14]");
//		if(jQuery("input[name!=ylzd14]").val().trim() == "")  return false;
//		if(jQuery("select[name=ylzd5]").val().trim() == "") return false;
//	}
	return textareaFlg && textFlg && selectFlg && radioFlg && checkboxFlg;
}

/**
 * �����Ի�����ģ����Ϣ���鿴ҳ�棩�е�ѡ����غ͸�ֵ
 * @return
 */
function loadViewMkxxRadioOptions_10335(){
	var tds = jQuery("td[name=radioTd]");

	jQuery.each(tds,function(i,n){
		
		var sjy = jQuery(n).attr("source");
		var radioName = jQuery(n).attr("property");
		
		jQuery.post("xszz_bdpz.do?method=getSjyList",{sjy:sjy},function(data){
			if (data != null){
				jQuery.each(data,function(i,e){
					if (e["dm"] == jQuery(n).attr("tempValue")){
						if(jQuery(n).prev().text().indexOf('�Ƿ�м�') != '-1'  &&��jQuery(n).attr("tempValue") == '1'){
							var cjbh = getCjbh_10335();
							jQuery(n).html(e["mc"]+"<font id='font_sfgc' color = 'blue'>&nbsp;&nbsp;&nbsp;�м�֤���:</font>"+cjbh);
						}else{
							jQuery(n).html(e["mc"]);
						}
						return ;
					}
				});
			}
		},'json');
	});
}



/**
 * ģ����Ϣ��ά��ҳ�棩�ж�ѡ����غ͸�ֵ
 * @return
 */
function loadMkxxCheckboxOptions(){
    var tds = jQuery("td[name = ckbTd]");
    jQuery.each(tds,function(i,n){
        var checkboxNode = jQuery(n);
        var sjy = checkboxNode.attr("source");//����Դ��������
        var checkboxName = checkboxNode.attr("property");
        var val = jQuery(n).attr("tempValue");
        var hidHtml = "<input  type='hidden' name='"+checkboxName+"' value='"+val+"' />";
        jQuery(n).append(hidHtml);
        //��������Դ�������飬��̨����������List
        jQuery.post("xszz_bdpz.do?method=getSjyList",{sjy:sjy},function(data){
            if (data != null){
                jQuery.each(data,function(i,e){
                    var checkboxHtml = "<div name='div_"+checkboxName+"' style='float:left;width:145px'><input onclick=\"hidHtml('"+checkboxName+"');\" type='checkbox' name='ckb_"+checkboxName+"' value='"+e["dm"]+"' ";
                    var arr = val.split(",");
                    for(i=0;i<arr.length;i++){
                        if (e["dm"] == arr[i]){
                            checkboxHtml+="checked='checked'";
                        }
                    }
                    checkboxHtml+="/>"+e["mc"]+"</div>";

                    var label = jQuery("<label></label>");
                    label.append(checkboxHtml);
                    jQuery(n).append(label);
                });
            }
        },'json');
    });
}

function hidHtml(name){
    var values = "";
    jQuery("input[name='ckb_"+name+"']:checked").each(function(){
        values = values + jQuery(this).val() + ",";
    });
    values = values.substring(0, values.length-1);
    jQuery("input[name='"+name+"']").val(values);
}