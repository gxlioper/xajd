//控制申诉解除日期显示
function sfkyss(type, obj) {
	var check = jQuery(obj);
	if (type == 'sfkss') {
		var bkss = jQuery('#bkss');
		var xskss = jQuery('#xskss');
		var jskss = jQuery('#jskss');
		if (check.attr('id') == 'bkss' && check.attr('checked')) {
			xskss.removeAttr('checked');
			jskss.removeAttr('checked');
			jQuery('#ssgzr').css('display', 'none');
		} else if (check.attr('id') != 'bkss' && check.attr('checked')) {
			bkss.removeAttr('checked');
			jQuery('#ssgzr').css('display', '');
		} else if (check.attr('id') != 'bkss' && !check.attr('checked')) {
			if (!xskss.attr('checked') && !jskss.attr('checked')) {
				bkss.attr('checked', true);
				jQuery('#ssgzr').css('display', 'none');
			}
		}
		var sfkss = jQuery('#sfkss');
		var val = "";
		if (xskss.prop("checked")) {
			val = xskss.val();
		}
		if (jskss.prop("checked")) {
			if (val == "") {
				val = jskss.val();
			} else {
				val = val + "," + jskss.val();
			}
		}
		if(bkss.prop("checked")){
			val="no";
			jQuery("#ssslgzr").val("");
		}
		sfkss.val(val);

	} else if (type == 'sfksqjc') {
		var bksqjc = jQuery('#bksqjc');
		var xsksqjc = jQuery('#xsksqjc');
		var jsksqjc = jQuery('#jsksqjc');
		if (check.attr('id') == 'bksqjc' && check.attr('checked')) {
			xsksqjc.removeAttr('checked');
			jsksqjc.removeAttr('checked');
			jQuery('#jcgzr').css('display', 'none');
		} else if (check.attr('id') != 'bksqjc' && check.attr('checked')) {
			bksqjc.removeAttr('checked');
			jQuery('#jcgzr').css('display', '');
		} else if (check.attr('id') != 'bksqjc' && !check.attr('checked')) {
			if (!xsksqjc.attr('checked') && !jsksqjc.attr('checked')) {
				bksqjc.attr('checked', true);
				jQuery('#jcgzr').css('display', 'none');
			}
		}
		var sfksqjc = jQuery('#sfksqjc');
		var val = "";
		if (xsksqjc.prop("checked")) {
			val = xsksqjc.val();
		}
		if (jsksqjc.prop("checked")) {
			if (val == "") {
				val = jsksqjc.val();
			} else {
				val = val + "," + jsksqjc.val();
			}
		}
		if(bksqjc.prop("checked")){
			val="no";
			jQuery("#jsslqsr").val("");
		}
		sfksqjc.val(val);
	}
}

function save(){
	var sfkyss=jQuery("input[type=checkbox][name=sfkyss]:checked");
	var sfkysqjc=jQuery("input[type=checkbox][name=sfkysqjc]:checked");
	if(!check('cflbmc-spl')){
		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		return false;
	}
	var cfqx = "";
	if(jQuery("input[type=radio][name=cfqxsz][value=1]").prop("checked")==true){
		if(!checkAllBlank("year-month-day")){
			return showAlert("请选择处分期限！");
		}
		if(jQuery("input[type=radio][name=sfkzz][value=1]").prop("checked")==true){
			jQuery("#qxnsfkzz").val("1");
		}else if(jQuery("input[type=radio][name=sfkzz][value=2]").prop("checked")==true){
			jQuery("#qxnsfkzz").val("0");
		}
		cfqx = getCfqx();
	}else if(jQuery("input[type=radio][name=cfqxsz][value=1]").prop("checked")==true){
		jQuery("#sfszcfqx").val("0");
	}
	if(!jQuery('#bkss').prop("checked")&&!jQuery('#xskss').prop("checked")&&!jQuery('#jskss').prop("checked")){
		return showAlert("请选择是否可申诉！");
		return false;
	}
	if(!jQuery('#bksqjc').prop("checked")&&!jQuery('#xsksqjc').prop("checked")&&!jQuery('#jsksqjc').prop("checked")){
		return showAlert("请选择是否可申请"+jQuery("#wjcf_text").val()+"！");
		return false;
	}
	jQuery("#cfqx").val(cfqx);
	var url="wjcf_cflbdmwh.do?method=cflbdmAdd&type=save";
    ajaxSubFormWithFun("cflbdmwhForm",url,function(data){
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


//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'wjcf_cflbdmwh.do?method=cflbdmUpdate&cflbdm='+rows[0]["cflbdm"];
		var title = "修改处分类别";
		showDialog(title,450,400,url);
	}
	
}
//修改保存
function saveUpdate(){
	if(!check('cflbmc-spl')){
		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		return false;
	}
	if(jQuery("input[type=radio][name=cfqxsz][value=1]").prop("checked")==true){
		if(!checkAllBlank("year-month-day")){
			return showAlert("请选择处分期限！");
		}
		var cfqx = getCfqx();
		jQuery("#cfqx").val(cfqx);
		if(jQuery("input[type=radio][name=sfkzz][value=1]").prop("checked")==true){
			jQuery("#qxnsfkzz").val("1");
		}else if(jQuery("input[type=radio][name=sfkzz][value=2]").prop("checked")==true){
			jQuery("#qxnsfkzz").val("0");
		}
	}else if(jQuery("input[type=radio][name=cfqxsz][value=1]").prop("checked")==true){
		jQuery("#sfszcfqx").val("0");
	}
	if(!jQuery('#bkss').prop("checked")&&!jQuery('#xskss').prop("checked")&&!jQuery('#jskss').prop("checked")){
		return showAlert("请选择是否可申诉！");
		return false;
	}
	if(!jQuery('#bksqjc').prop("checked")&&!jQuery('#xsksqjc').prop("checked")&&!jQuery('#jsksqjc').prop("checked")){
		return showAlert("请选择是否可申请"+jQuery("#wjcf_text").val()+"！");
		return false;
	}
	var url="wjcf_cflbdmwh.do?method=cflbdmUpdate&type=save";
    ajaxSubFormWithFun("cflbdmwhForm",url,function(data){
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
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
		jQuery.post("wjcf_cflbdmwh.do?method=cflbdmDelete",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
		}});
	}
}

//数据验证  只能是数字
function onyInt(obj){
	obj.value = obj.value.replace(/[^(\d]/g,'');
	return true;
}


/**
 * 验证是否存在空项
 * @param ids 要验证的控件id "-"分隔
 * @return
 */
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

/**
 * 验证是否全都不为空项(也不能为0)
 * @param ids 要验证的控件id "-"分隔
 * @return
 */
function checkAllBlank(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var qxArr=jQuery("#"+id[i]).val();
		if(qxArr!=null && qxArr!="" && qxArr!="0"){
			return true;
		}
	}
	return false;
}

function getCfqx(){
	var year = jQuery("input[name=cfqxV][id=year]").val()===""?"0":jQuery("input[name=cfqxV][id=year]").val();
	var month = jQuery("input[name=cfqxV][id=month]").val()===""?"0":jQuery("input[name=cfqxV][id=month]").val();
	var day = jQuery("input[name=cfqxV][id=day]").val()===""?"0":jQuery("input[name=cfqxV][id=day]").val();
	var cfqx = year+"-"+month+"-"+day;
	return cfqx;
}
function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='cfsbView(\""+rowObject["cfid"]+"\");'>"+cellValue+"</a>";
}

function showCfqx(cellValue,rowObject){
	if(cellValue==null || cellValue==""){
		return "";
	}
	var year = cellValue.split("-")[0];
	var month = cellValue.split("-")[1];
	var day = cellValue.split("-")[2];
	if(year!=0){
		return year+"年"+month+"月"+day+"天";
	}else if(month!=0){
		return month+"月"+day+"天";
	}else{
		return day+"天";
	}
}

function sfFlag(obj){
	if(obj=='cfqxsz'){
		if(jQuery("input[type=radio][name=cfqxsz][value=1]").prop("checked")==true){
			jQuery("input[type=radio][name=cfqxsz][value=2]").removeAttr('checked');
			jQuery("tr[name=showCfqx]").css('display', '');
			jQuery("#sfszcfqx").val('1');
		}else if(jQuery("input[type=radio][name=cfqxsz][value=2]").prop("checked")==true){
			jQuery("input[type=radio][name=cfqxsz][value=1]").removeAttr('checked');
			jQuery("tr[name=showCfqx]").css('display', 'none');
			jQuery("#sfszcfqx").val('0');
		}
	}else if(obj=='sfkzz'){
		if(jQuery("input[type=radio][name=sfkzz][value=1]").prop("checked")==true){
			jQuery("input[type=radio][name=sfkzz][value=2]").removeAttr('checked');
			jQuery("#qxnsfkzz").val('1');
		}else if(jQuery("radio[name=sfkzz][value=2]").prop("checked")){
			jQuery("input[type=radio][name=sfkzz][value=1]").removeAttr('checked');
			jQuery("#qxnsfkzz").val('0');
		}
	}
}

/**
 * 更新处分发文权限下拉列表
 */
function updateCffwqxList(){
	var splcId = jQuery("#spl").val();
	jQuery.post("wjcf_cflbdmwh.do?method=getSpjbListById",{splcId:splcId},function(data){
		//更新处分发文权限控制的下拉审核级别列表
		var html;
		jQuery.each(data,function (index,domEle){
			  html += "<option value='"+domEle["spgwdm"]+"'>"+domEle["spgwmc"]+"</option>";
			});
		jQuery("#cffwqx").html(html);
	},'json');
}

/**
 * 文档对象加载完毕绑定处理函数
 */
jQuery(function(){
	//对审批流下拉列表绑定onChange处理函数，发文权限下拉列表与之联动
	jQuery("#spl").bind("change",updateCffwqxList);
});

