/**
 * 家庭情况调查--查看
 * @param xh
 * @return
 */
function jtqkView(xh){
	showDialog("家庭情况调查",945,500,"xszz_jtqkdc.do?method=dcxxView&xh="+xh);
}

/**
 * 家庭情况调查查询列表--学号链接
 * @param cellValue
 * @param rowObject
 * @return
 */
function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='jtqkView(\""+cellValue+"\");'>"+cellValue+"</a>";
}

/**
 * 家庭情况调查--查询事件
 * @return
 */
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 家庭情况调查--修改按钮事件
 * @return
 */
function jtqkUpdate(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		showDialog("家庭情况调查",945,500,"xszz_jtqkdc.do?method=dcxxModify&type=update&xh="+rows[0]["xh"]);
	}
}

/**
 * 家庭情况调查--删除按钮事件
 * @return
 */
function jtqkDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("xszz_jtqkdc.do?method=dcxxDelete",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	var DCCLBH = "xszz_jtqkdc_cx.do";//dcclbh,导出功能编号
	customExport(DCCLBH, exportData);
}

//导出方法
function exportData() {
	setSearchTj();//设置高级查询条件
	var DCCLBH = "xszz_jtqkdc_cx.do";
	var url = "xszz_jtqkdc.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}




/**
 * 家庭情况调查--保存表单检测
 * @return
 */
function saveForm(){
	var xxdm = jQuery("#xxdm").val();
	//先验证学号
	var xh = jQuery("#xh").val();
	
	if (jQuery.trim(xh) == ""){
		showAlertDivLayer("请先选择学生！");
		return false;
	}
	if (!checkMustNotNull()){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}
	//验证家庭成员必填项
	var btx = jQuery("input[name=btx]");
	var jtcyFlg = true;
	var jtcyNum = true;

	jQuery.each(btx,function(i,e){
		var name = jQuery(e).val();
		var inputList = jQuery("input[labName="+name+"]");
		var selectList = jQuery("select[labName="+name+"]");

		var inputNum = 0;
		jQuery.each(inputList,function(i,e){
			var textVal = jQuery(e).val();
			if (textVal != null && textVal != ''){
				inputNum++;
			} else {
				//验证必填项值为空，同一行中其它元素是否有非空项
				var input = jQuery(e).parents("tr").eq(0).find("input").not(jQuery(e));
				var select = jQuery(e).parents("tr").eq(0).find("select");
				
				var textFlg = false;
				jQuery.each(input,function(i,e){
					var textValT = jQuery(e).val();
					if (textValT != null && textValT != ''){
						textFlg = true;
						return;
					}
				});
				
				var selectFlg = false;
				jQuery.each(select,function(i,e){
					var selectValT = jQuery(e).val();
					if (selectValT != null && selectValT != ''){
						selectFlg = true;
						return;
					}
				});
				
				if (textFlg || selectFlg){
					jtcyFlg = false;
					return;
				}				
			}
		});
		
		var selectNum = 0;
		jQuery.each(selectList,function(i,e){
			var selectVal = jQuery(e).val();
			if (selectVal != null && selectVal != ''){
				selectNum++;
			}else{
				//验证必填项值为空，同一行中其它元素是否有非空项
				var input = jQuery(e).parents("tr").eq(0).find("input");
				var select = jQuery(e).parents("tr").eq(0).find("select").not(jQuery(e));
				
				var textFlg = false;
				jQuery.each(input,function(i,e){
					var textValT = jQuery(e).val();
					if (textValT != null && textValT != ''){
						textFlg = true;
						return;
					}
				});
				
				var selectFlg = false;
				jQuery.each(select,function(i,e){
					var selectValT = jQuery(e).val();
					if (selectValT != null && selectValT != ''){
						selectFlg = true;
						return;
					}
				});
				
				if (textFlg || selectFlg){
					jtcyFlg = false;
					return ;
				}
			}
		});
		
		//验证是否全部必填项都为空（至少有一行的必填项不能为空。）
			if (inputNum == 0 && selectNum == 0){
				jtcyNum = false;
				return ;
			}

	});
	
		if (!jtcyNum){
			showAlertDivLayer("请至少填写一名家庭成员！");
			return false;
		}

	if (!jtcyFlg){
		showAlertDivLayer("家庭成员必填项不能为空！");
		return false;
	}

	var url = "xszz_jtqkdc.do?method=saveDcxx";
	ajaxSubFormWithFun("jtqkdcForm",url,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			if(parent.window){
				refershParent();
			}
		}});
	});
	
}

/*浙大家庭情况调查是否残疾联动个性化功能*/
function sfcj_cjzbh(){
	/*残疾编号*/
	var paraMap = {
			"xh":jQuery('#xh').val()
	}
	var cjbh = getCjbh_10335();
	if(jQuery("input:radio[name='sfgc']:checked").val() == '1'){
		jQuery("input:radio[name='sfgc']").parent().parent().append("<font id='font_sfgc' color = 'blue'>&nbsp;&nbsp;&nbsp;残疾证编号</font><input type='text' style = 'width:100px;' name='ylzd4' maxlength='20' style='' sfbt='yes' value = '"+cjbh+"' >");
	}else{
		//加入隐藏域是为了防止有错误数据，保证是否残疾和残疾证编号数据一致，下同
		jQuery("input:radio[name='sfgc']").parent().parent().append("<input type='hidden' id = 'ylzd4' name='ylzd4'  style='' sfbt='no' value=''/>");
	}
	jQuery("input:radio[name='sfgc']").change(function(){
		if(this.value == '1'){
			jQuery("#ylzd4").remove();
			jQuery("input:radio[name='sfgc']").parent().parent().append("<font id='font_sfgc' color = 'blue'>&nbsp;&nbsp;&nbsp;残疾证编号</font><input type='text' style = 'width:100px;' name='ylzd4' maxlength='20' style='' sfbt='yes' value = '"+cjbh+"' >");
		}else{
			jQuery("#ylzd4").remove();
			jQuery("input:text[name='ylzd4']").remove();
			jQuery("#font_sfgc").remove();
			jQuery("input:radio[name='sfgc']").parent().parent().append("<input type='hidden'  id = 'ylzd4' name='ylzd4'  style='' sfbt='no' value=''>");
		}
	});
}

/*浙江大学，建档立卡户点击是，则显示【如果是建档立卡户，请务必在本页面下方上传相关证明材料】*/
function jdlkh_Hint(){
	if(jQuery("input:radio[name='ylzd7']:checked").val() == '1'){
		jQuery("input:radio[name='ylzd7']").parent().parent().append("<font id='font_ylzd7' color = 'red'>&nbsp;&nbsp;&nbsp;请务必在本页面下方上传建档立卡户相关证明材料！</font>");
	}
	jQuery("input:radio[name='ylzd7']").change(function(){
		if(this.value == '1'){
			jQuery("#ylzd7").remove();
			jQuery("input:radio[name='ylzd7']").parent().parent().append("<font id='font_ylzd7' color = 'red'>&nbsp;&nbsp;&nbsp;请务必在本页面下方上传建档立卡户相关证明材料！</font>");
		}else{
			jQuery("#ylzd7").remove();
			jQuery("#font_ylzd7").remove();
		}
	});
}


function getCjbh_10335(){
	var cjbh = "" ;
	var url = "xszz_jtqkdc.do?method=getCjbh_10335";
	var paraMap = {
			"xh":jQuery('#xh').val()
	}
	jQuery.ajax({
		type:'post',
		url:url,
		dataType:'text',
		data:paraMap,
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		async: false,
		success:function(result){
			if(result == null || result == 'null'){
				cjbh = '';
			}else{
			    cjbh = result;
			}
		}
	});
	return cjbh;
}

function checkMoney_10026(obj){
		var text = obj.value;
		
		if (null !== text && ''!= text) {
			
			if (!/^[0-9]\d*\.[0-9]{2}$|^[0-9]\d*\.[0-9]{1}$|^[0-9]\d*$/.test(text) || text>999999) {
				
				showAlertDivLayer("您输入的数字不符合规则,请输入整数不超过6位，小数不超过两位的数字!",{},{"clkFun":function(){
					obj.focus();
				}});
			}
		}
}

/**
 * 北京医药申请理由多选加载和赋值
 * @return
 */
function loadCheckbox(){
	var tds = jQuery("td[name = checkboxTd]");
	jQuery.each(tds,function(i,n){
		var checkboxNode = jQuery(n);
		var sjy = checkboxNode.attr("source");//数据源配置详情
		var checkboxName = checkboxNode.attr("property");
		var val = jQuery(n).attr("tempValue");
		var hidHtml = "<input  type='hidden' name='"+checkboxName+"' value='"+val+"' />";
		jQuery(n).append(hidHtml);
		//根据数据源配置详情，后台解析并返回List
		jQuery.post("xszz_bdpz.do?method=getSjyList",{sjy:sjy},function(data){
			if (data != null){
				jQuery.each(data,function(i,e){
					var checkboxHtml = "<div style='float:left;width:145px'><input onclick=\"hidHtml('"+checkboxName+"');\" type='checkbox' name='ckb_"+checkboxName+"' value='"+e["dm"]+"' ";
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
//南京高等职业技术学院
function loadCheckbox_10874(){
	var tds = jQuery("td[name = checkboxTd]");
	jQuery.each(tds,function(i,n){
		var checkboxNode = jQuery(n);
		var sjy = checkboxNode.attr("source");//数据源配置详情
		//var checkboxName = checkboxNode.attr("property");
		//根据数据源配置详情，后台解析并返回List
		jQuery.post("xszz_bdpz.do?method=getSjyList",{sjy:sjy},function(data){
			if (data != null){
				jQuery.each(data,function(i,e){
					var checkboxHtml = "<div style='float:left;width:255px'><input  type='checkbox' name='jtknlx' value='"+e["dm"]+"' ";
					
					if (jQuery(n).attr("tempValue").indexOf(e["dm"]) != -1 ){
						checkboxHtml+="checked='checked'";
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

