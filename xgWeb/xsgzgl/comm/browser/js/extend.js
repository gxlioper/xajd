var api = frameElement.api, W = api.opener;
function browser(basefunction) {
	/*
	 * if($("rzksrq").value==""){ showAlertDivLayer("请选择任职时间！"); return false; }
	 * eval(basefunction);
	 */
}
/*****************************浏览器新弹出窗口兼容旧版本div弹出******************************/
/*
 * 原div弹出方法
 * 			tipsWindown("保存提示","id:tempDiv","350","200","true","","true","id");
	替换为	tipsWindownNew("保存提示","id:tempDiv",450,260);
	id 为 tempDiv 的div 如果有相关保存方法 例如 save
	save方法中队div中内容作相关验证，请在本方法中增加新方法 如：save_browser
	然后更改div调用方法为save_browser
	在此新方法中增加原验证(可以直接copy过来)
	后直接通过 W对象调用原save方法 例如 W.save();
	
	这里是把原div html 代码copy了新页面 所以相关验证要在这里重新验证，然后调用原页面的原方法
	
	如果是多层弹出 请 调用 W=getBase();方法
	如果有需要传递的参数可以使用 getParam(id)方法获取对应值
	setParam 设置返回值(这里是自动根据传递过来的键值对设置，如果需要可以自己参考设置特定值) 参考browser.js
 * [982] 张昌路
 * */
/**
 * 公寓管理-寝室长维护-寝室长维护：寝室长分配界面的确认分配窗口样式
 * @author 982 张昌路
 * @return
 */
function szdwjr() {
	W = getBase();
	if ($("rzksrq").value == "") {
		showAlert("请选择任职时间！");
		return false;
	}
	//将验证后的值传递回原页面
	W.document.getElementById('rzksrq').value = jQuery("#rzksrq").val();
	W.jQuery("input[type=radio][name=czfs]:checked").val(jQuery("input[type=radio][name=czfs]:checked").val());
	W.document.getElementById('bz').value = jQuery("#bz").val();
	
	//关闭弹出层
	iFClose();
	//调用原方法
	W.glryfp_submit();
}

function backform() {
	var thyj = jQuery("#thyj").val();
	if(thyj==""){
		showAlert("请填写退回意见！");
		return false;
	}
	W = getBase();
	//将验证后的值传递回原页面
	W.document.getElementById('thyj').value = jQuery("#thyj").val();
	//关闭弹出层
	iFClose();
	//调用原方法
	W.backForm();
}
/**
 *公寓管理-住宿管理-床位入住管理 ：进入学生入住分配界面/取消界面
 * @author 982 张昌路
 * @return
 */
function saveQscwfpxx_submit_browser() {
	var doType = W.doType_temp;
	
	var rzsj = $("rzsj").value;
	var rzyy = $("rzyy").value;
	if ("qxfp" == doType) {
		if (rzsj == "") {
			showAlert("请选择退宿时间！");
			return false;
		}
		var tsyy = $("tsyy").value;
		if (tsyy == "") {
			showAlert("请选择退宿原因！");
			return false;
		}

		var xn=$("xn").value;
		if(xn==""){
			alertInfo("请选择学年！");
			return false;
		}
		var xq=$("xq").value;
		if(xq==""){
			alertInfo("请选择学期！");
			return false;
		}
		
		W.jQuery("#tsyy").val(tsyy);
		W.jQuery("#xn").val(xn);
		W.jQuery("#xq").val(xq);
		var bz=$("bz").value;
		W.jQuery("#bz").val(bz);
	} else {
		if (rzsj == "") {
			showAlert("请选择入住时间！");
			return false;
		}
	}
	W.jQuery("#rzyy").val(rzyy);
	W.jQuery("#rzsj").val(rzsj);
	// setParam(W);
	api.close();
	W.saveQscwfpxx_submit();
}
/**
 *评奖评优(New)-基本设置-项目设置 -人数设置-分配设置
 * @return
 */
function saveForm(){
	W = getBase();
	var index=0;
	var fpfs = jQuery.trim(jQuery("input:radio[name=fpfs]:checked")[index].value);
	if (fpfs == "bl") {// 比例方式
		var blView = jQuery.trim(jQuery("input[name=blView]")[index].value);
		if (blView == "") {
			jQuery("span[name=blTip]").html("请输入比例数值");
			return false;
		}
		var reg = /^(([1-9]\d{0,2})|[0-9])([.]\d{1,2})?$/;
		if (!blView.match(reg) || blView > 100) {
			jQuery("span[name=blTip]").html("请输入0-100的数字，最多两位小数");
			return false;
		}
		W.jQuery("input[name=blView]")[index].value=blView;
	} else if (fpfs == "zme") {// 总名额分配
		var zmeView = jQuery.trim(jQuery("input[name=zmeView]")[index].value);
		if (zmeView == "") {
			jQuery("span[name=zmeTip]").html("请输入总名额");
			return false;
		}
		var reg = /^\d*$/;
		if (!zmeView.match(reg) || zmeView < 0) {
			jQuery("span[name=zmeTip]").html("请输入整数");
			return false;
		}
		W.jQuery("#zme").val(zmeView);
	}
	var rsfpfs = W.jQuery("#rsfpfs").val();
	if (rsfpfs == W.RSFPFS_XY) {// 学院
		var rsfpnj = jQuery(jQuery("tr[name=njTr]")[0]).find("input:checkbox[name=nj]:checked").map(function() {
		  return jQuery(this).val();
		}).get().join(',');
		if (rsfpnj == "") {
			jQuery("span[name=njTip]").html("请选择需要控制的年级");
			return false;
		}
		W.jQuery("#rsfpnj").val(rsfpnj);
	}
	W.jQuery("span[name$=Tip]").html("");
	W.jQuery("#fpfs").val(fpfs);
	W.saveForm();
	api.close();
}
//分配方式选择开关
/*评奖评优(New)-基本设置-项目设置 -人数设置-分配设置
 * 弹出页面使用相关js方法
 * 根据具体情况微调 例如获取tr代码从 1下标微调为0
 * */
//分配方式选择开关
function setFpfs(obj){
	W = getBase();
	var index=0;
	jQuery(obj).attr("checked","checked");
	var fpfs = jQuery(obj).val();
	if (fpfs == "bl") {// 比例方式
		jQuery("tr[name=blTr]").eq(index).show();
		jQuery("tr[name=zmeTr]").eq(index).hide();
	}else if (fpfs == "zme") {// 总名额
		jQuery("tr[name=zmeTr]").eq(index).show();
		jQuery("tr[name=blTr]").eq(index).hide();
		jQuery("input[name=zmeView]").eq(index).val(W.jQuery("#zme").val());
	}
}

//违纪处分-处分正式库-数据维护
//处分申诉弹出层
/**********************begin**********************/
function ssjgsave(){
	var sswh = $("sswh").value;//申诉文号
	var sssj = $("sssj").value;//申诉时间
	var ssjg = $("ssjg").value;//申诉结果
	
	var cflbdm = W.$("cflbdm").value;//处分类别代码
	if("block"==W.document.getElementById('cfggw').style.display){
		if(null==sssj||""==sssj||null==sswh||""==sswh||null==ssjg||""==ssjg||null==cflbdm||""==cflbdm){
			alertError("请将带*的项目填写完整！");
			return false;
		}
	}else{
		if(null==sssj||""==sssj||null==sswh||""==sswh||null==ssjg||""==ssjg){
			alertError("请将带*的项目填写完整！");
			return false;
		}
	}
	var id=jQuery("#content").val();
	callBack(W,id);
	//W.ssjgsave();
	//var pkValue=jQuery("[name=div_pkValue]:checked").val();
	//refreshForm('saveWjcfssjg.do?cfid='+pkValue);
}
function discfgg() {
	var ssjg = jQuery("#ssjg").val();
	if (ssjg =='更改处分') {
		W.document.getElementById('3we').style.display = "block";
	} else {
		document.getElementById('cfggw').style.display = "none";
		document.getElementById('cflbdm').value = "";
	}
}
/***************************end**********************************/
/***************公寓管理-公寓纪律-公寓纪律信息审核*******************/
//批量审核
function gyjlPlsh(){
		var shzt = jQuery("#shzt").val();
		confirmInfo("确定要审核已勾选的记录吗?",function(tag){
			if(tag=="ok"){
				//当前页面参数返回给原页面。调用原方法
				W.jQuery("#shzt").val(shzt);
				W.jQuery("#shyj").val(jQuery("#shyj").val());
//				var id=jQuery("#content").val();
//				callBack(W,id);
				W.gyjlPlsh();
			}else {
				return false;
			}
			Close();
		});
	}
/*******************************end*************************/
/***************公寓管理-公寓纪律-公寓纪律信息处理*******************/

//批量保存
function saveShzt(){
	var cljg=jQuery("#cljg").val();
	if (cljg == null || cljg == "") {
		alertInfo("请选择处理结果!",function(){return false;});
		return false;
	} 
	if (jQuery("#dcqk").val()==null ||jQuery("#dcqk").val()=='') {
		alertInfo("请填写调查情况！",function(){return false;});
		return false;
	}
	confirmInfo("确定要处理已勾选的记录吗?",function(tag){
		if(tag=="ok"){
			W.jQuery("#cljg").val(cljg);
			W.jQuery("#dcqk").val(jQuery("#dcqk").val());
			W.jQuery("#ylzd1").val(jQuery("#ylzd1").val());
			W.saveShzt();
		}else {
			return false;
		}
		Close();
	});
}
/*******************************end*************************/
/**********************8*/
 //照片上传
function uploadStuPic(){
	W=getBase();
	jQuery.ajaxSetup({async:false,dataType:'text'});
	var zgh = W.jQuery("#zgh").val();
	jQuery.ajaxFileUpload({
	  url:'szdw_teaInfo.do?method=uploadTeaPic&zgh='+zgh,//服务器端程序
	  secureuri:false,
	  fileElementId:'teaPic',//input框的ID
	  success:function(data,type){
		if (type=='success'){
			W.jQuery("#zhaopian").attr("src","");
			W.jQuery("#zhaopian").attr("src","/xgxt/teaPic.jsp?zgh="+zgh+"&tt"+new Date());
			Close();
		}
	  }
	});
	jQuery.ajaxSetup({async:true});
}
