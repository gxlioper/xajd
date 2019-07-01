var checkId = 'xn-xq-xmmc-xmjbdm-sbbmdm-sskmdm-kcyrs-xmkssj-jcxf-sbr-lxdh';
jQuery(function() {
	changeSljx();
	initCyxy();//项目参与学院初始化
	CsmsradioCheck();
});

// 隐藏奖项
function changeSljx() {
	var sfsljx = jQuery("input[name='sfsljx']:checked").val();
	if ("0" == sfsljx) {
		jQuery("#jxDiv").css('display', 'none');
	} else {
		jQuery("#jxDiv").css('display', '');
	}
}
/**
 * 增加行
 * 
 * @return
 */
function addRow() {
	var html = "";
	var qqxsTrLen = jQuery("#tbody_xmjxxx tr").length;
	html += "<tr><td><input type='checkbox' id='checkbox_" + qqxsTrLen
			+ "'/></td>";
	html += "<td name='jxmc'><input type='text' maxlength='20' name='jxmc' id='jxmc'/></td>";
	html += "<td name='fjxf'><input type='text' name='fjxf' maxlength='5' id='fjxf' maxlength='5' onkeyup=\"value=value.replace(/(?:\\D*)?(\\d*)?(\\.)?(\\d*)?(?:\\d*)?/ig,'$1$2$3')\"/></td>";
	html += "<td><input type='text' name='xssx'  id='xssx' maxlength='2' onkeyup=\"value=value.replace(/[^\\d]/g,\'\');\"/></td></tr>";
	jQuery("#tbody_xmjxxx").append(html);
}

/**
 * 删除行
 * 
 * @return
 */
function delRow() {
	var checkbox = jQuery('input[type=checkbox]:checked[name!=th]');
	if (checkbox.length == 0) {
		showAlertDivLayer("请选择需要删除的奖项！");
		return false;
	}
	for ( var i = 0; i < checkbox.length; i++) {
		jQuery(checkbox[i]).parents("tr:eq(0)").remove();
	}
	jQuery('input[type=checkbox][name=th]').attr('checked', false);
}
// 全选
function selectAll(obj) {
	jQuery('input[type=checkbox]').attr('checked', jQuery(obj).attr('checked'));
}

/**
 * 验证是否存在空项
 * 
 * @param ids
 *            要验证的控件id "-"分隔
 * @return
 */

function check() {
	var flag=true;
	var id = checkId.split("-");
	for ( var i = 0; i < id.length; i++) {
		var lddm = jQuery("#" + id[i]).val();
		if (lddm == null || "" == lddm) {
			flag= false;
		}
	}
	
	var sfsljx=jQuery("input[name='sfsljx']:checked").val();
	if("0" != sfsljx){
		jQuery.each(jQuery("#tbody_xmjxxx tr"), function(i, n) {
			var jxmc = jQuery(n).find("input[name=jxmc]").val();
			var fjxf = jQuery(n).find("input[name=fjxf]").val();
			var xssx = jQuery(n).find("input[name=xssx]").val();
			if(null==jxmc||null==fjxf||null==xssx||""==jxmc||""==fjxf||""==xssx){
				flag=false;
			}
		});
		
	}
	if(!flag){
		showAlert("请将必填项填写完整！");
		flag= false;
		return flag;
	}
	if (jQuery("#xmms").val().length>500) {
		showAlert("项目描述最多输入500字！");
		flag=false;
		return flag;
	}
	if (jQuery("#dkfyj").val().length>500) {
		showAlert("得/扣分依据最多输入500字！");
		flag=false;
		return flag;
	}
	if (jQuery("#cyyq").val().length>500) {
		showAlert("参与要求最多输入500字！");
		flag=false;
		return flag;
	}
	return flag;
}

// 封装奖项信息
function jxToJson() {
	var sfsljx = jQuery("input[name='sfsljx']:checked").val();
	if ("0" != sfsljx) {
		var objArr = [];
		jQuery.each(jQuery("#tbody_xmjxxx tr"), function(i, n) {
			var obj = {};
			var jxmc = jQuery(n).find("input[name=jxmc]").val();
			var fjxf = jQuery(n).find("input[name=fjxf]").val();
			var xssx = jQuery(n).find("input[name=xssx]").val();
			obj.jxmc = jxmc;
			obj.fjxf = fjxf;
			obj.xssx = xssx;
			objArr.push(obj);
		});
		var objStr = JSON.stringify(objArr);
		jQuery("#objStr").val(objStr);
	}
}
//选择教师后，回调函数
function showFdysNotF5CallBack(rowData) {
	jQuery("#sbr").val(rowData["zgh"]);
	jQuery("#lxdh").val(rowData["lxdh"]);

}

function initCyxy(){
	var url = "xmsbXmsbjg.do?method=getCyxyList";
	jQuery.post(url, {
		xmdm:jQuery("#xmdm").val()
	}, function(data) {
		dataObj = data;
			initTj();
		}, 'json');
}
function initTj(){
	var sbbmdm = jQuery("#sbbmdm").val();
	//是否为修改界面
	var flag = jQuery("#editview").length == 1 ? true :false;
	var xjbm=true;//校级部门
	var url = "xmsbXmsbjg.do?method=getXylb";
	jQuery.ajaxSetup({async:false});
	jQuery.post(url, {
		sbbmdm:jQuery("#sbbmdm").val()
	}, function(data) {
		if(data["message"]=='5'){
			xjbm=false;
		}
		}, 'json');
	if(true==xjbm && !flag){//此次原来增加和修改一并处理，现在加上增加和修改界面的判断
		jQuery("input[type=checkbox][name=cyxy]").attr("checked",true);
	}else{
jQuery.each(jQuery("input[type=checkbox][name=cyxy]"),function(j,n){
	for ( var i = 0; i < dataObj.length; i++) {
		var tjObj=dataObj[i];
		if(tjObj.xydm==jQuery(n).val()){
			jQuery(n).attr("checked",true);
		}
		if(sbbmdm==jQuery(n).val()){
			jQuery(n).attr("disabled",true);
			jQuery("#cyxy").val(sbbmdm);
		}
		
	}
});
	}
	jQuery.ajaxSetup({async:true});

}

function getCyxy(){
	var sbbmdm = jQuery("#sbbmdm").val();
	var xjbm=true;//校级部门
	var url = "xmsbXmsbjg.do?method=getXylb";
	jQuery.ajaxSetup({async:false});
	jQuery.post(url, {
		sbbmdm:jQuery("#sbbmdm").val()
	}, function(data) {
		if(data["message"]=='5'){
			xjbm=false;
		}
		}, 'json');

	if(true==xjbm){
		jQuery("input[type=checkbox][name=cyxy]").attr("checked",true);
	}else{
	jQuery.each(jQuery("input[type=checkbox][name=cyxy]"),function(j,n){
			if(sbbmdm==jQuery(n).val()){
				jQuery(n).attr("checked",true);
				jQuery(n).attr("disabled",true);
				jQuery("#cyxy").val(sbbmdm);
			}else{
				jQuery(n).attr("checked",false);
				jQuery(n).attr("disabled",false);
			}
	});
	}
	jQuery.ajaxSetup({async:true});
}

//团体/个人切换
function CsmsradioCheck(){
	jQuery("input[name='csms']").change(function(){
		var objArr = jQuery("input[name='kcyrs']");
		if(jQuery(this).val() == '1'){
			jQuery(objArr[0]).removeAttr("disabled");
			jQuery(objArr[0]).show();
			jQuery(objArr[1]).attr("disabled","true");
			jQuery(objArr[1]).hide();
			jQuery(objArr[0]).attr("id","kcyrs");
			jQuery(objArr[1]).attr("id","kcyrss");
		}else{
			jQuery(objArr[1]).removeAttr("disabled");
			jQuery(objArr[1]).show();
			jQuery(objArr[0]).attr("disabled","true");
			jQuery(objArr[0]).hide();
			jQuery(objArr[0]).attr("id","kcyrss");
			jQuery(objArr[1]).attr("id","kcyrs");
		}
	});
}
