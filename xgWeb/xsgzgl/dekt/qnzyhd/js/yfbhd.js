// 查询已发布活动列表
function searchRs(cxzt){
	deleteFile();
	if(cxzt == '1'){
		jQuery("#currentPage").val("1");
	}
	var url = "zyhd.do?method=yfbhdList&type=query";
	ajaxSubFormWithFun("qnzyhdForm", url, function(data) {
			createHtml(data);
	});
}

//人员列表
function searchRy(cxzt){
	if(cxzt == '1'){
		jQuery("#currentPage").val("1");
	}
	var url = "zyhdry.do?method=searchRy";
	ajaxSubFormWithFun("qnzyryForm", url, function(data) {
			createHtmlForRy(data);
	});
}

//上传
function sc(){
	jQuery("#upload").click();
}

//返回
function fh(){
	window.location.href="zyhd.do?method=yfbhdList";
	scrollTop();
}

//上传绑定
function bindUpload(){
	jQuery("#upload").bind("change",function(){
		jQuery.ajaxFileUpload({
			url : 'zyhd.do?method=upLoadPic',
			type: 'post',
			data: {  
					maxcount: '1',
					maxsize: '4', 
					accept: 'png,gif,jpg,bmp',
					lastPath:jQuery("#lastPath").val()
				  },
			secureuri : false,
			fileElementId : 'upload',
			dataType : "json",
			success : function(data,status) {
				if(data["result"] == "true"){
					jQuery("#fjpath").val(data["path"]);
					jQuery("img").attr("src",data["path"]);
					jQuery("#lastPath").val(data["path"]);
				}else{
					if(data["message"] == "oversize"){
						showAlertDivLayer("图片过大，请重新上传！");
					}else{
						showAlertDivLayer("图片格式不对，请重新上传！");
					}
				}
				bindUpload();
			}
		})
	})
}

//修改发布活动保存
function updateBc(){
	editor.sync();
	var ids;
	var url;
	var message;
	
	ids = "hdmc-fwlx-hddd-fwdx-xdrs-hdkssj-xm-zzbm-bmjzsj-jbfwgs-hdfzrlxfs";
	//ids = "hdmc-fwlx-hddd-fwdx-xdrs-hdkssj-xm-zzbm-fjpath-hdfzrlxfs";
	if(check(ids) == false){
		showAlertDivLayer("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	if(jQuery("#zgh").val() == null || jQuery("#zgh").val() == ''){
		showAlertDivLayer("您输入的负责人不存在，请重新输入！");
		return false;
	}
	
	var checkboxs = jQuery("[name='xydms']:checked");
	if(checkboxs.length < 1){
		showAlertDivLayer("请勾选开放学院！");
		return false;
	}
	var html=editor.html();
	if(html==null||html==""){
		showAlertDivLayer("请填写活动详情！");
		return false;
	}
	url = 'zyhd.do?method=updateBcHdfb';
	message = jQuery("#oldZgh").val() == jQuery("#zgh").val() ? "您确实要保存吗？" : "注意：您已更改了负责人，将导致原有负责人不能维护该项目的人员，是否修改？";
	showConfirmDivLayer(message, {
		"okFun" : function() {				
			ajaxSubFormWithFun("qnzyhdForm", url, function(data) {
				if(data["message"]=="保存成功！"){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						fh();
					}});
				}else{
					showAlertDivLayer(data["message"]);
				}
			});
		}
	});
	
}

//变更发布
function bgfb(type){
	var message;
	var result;
	if(type == '0'){
		message = "确认取消当前项目的发布吗？";
		result = "取消发布成功！";
	}else{
		message = "确认要发布当前项目吗？";
		result = "发布成功！";
	}
	showConfirmDivLayer(message, {
		"okFun" : function() {
			jQuery.post("zyhd.do?method=bgFb", 
			{
			  hdid:jQuery("#hdid").val(),
			  fbzt:type
			},
			function(data) {
				if(data["message"] == "保存成功！"){
					showAlertDivLayer(result,{},{"clkFun":function(){
						jQuery("#fbzt").val(type);
						showFbzt();
					}});
				}else{
					showAlertDivLayer(data["message"]);
				}				
			}, 'json');
		}
	});	
}

//切换发布状态
function showFbzt(){
	if(jQuery("#fbzt").val() == '1'){
		jQuery("#cancel-btn").css("display","");
		jQuery("#confirm-btn").css("display","none");
	}else{
		jQuery("#cancel-btn").css("display","none");
		jQuery("#confirm-btn").css("display","");
	}
}

//人员列表展示
function createHtmlForRy(obj) {
	var listTbody = jQuery("#ryTbody");
	jQuery(listTbody).empty();
	if(obj.length < 1){
		jQuery(listTbody).html("<tr align='center'><td colspan='8'>未找到任何记录！</td></tr>");
		jQuery("#total").text(0);
		jQuery("#pageno").val(0);
		jQuery("#pageCount").text(0);
	}else{
		var content = "";
		for ( var i = 0; i < obj.length; i++) {
			if(i == obj.length -1){
				var total = obj[i]["total"];
				jQuery("#total").empty();
				jQuery("#total").text(total);
				jQuery("#pageno").val((obj[0]["rowindex"]-1)/jQuery("#pagesize").val()+1);//第几页
				var temp = total/jQuery("#pagesize").val();
				var pageCont;
				if (total%jQuery("#pagesize").val() == 0 && temp != 0){
					pageCont = temp;
				} else {
					pageCont = temp == 0 ? temp : parseInt(temp)+1;
				}
				jQuery("#pageCount").empty();
				jQuery("#pageCount").text(pageCont);
				if(jQuery("#pageno").val() == pageCont){
					jQuery("#next").attr("href","javascript:void(0)");
					jQuery("#last").attr("href","javascript:void(0)");
				}else{
					jQuery("#next").attr("href","javascript:submitNextPage()");
					jQuery("#last").attr("href","javascript:submitLastPage()");
				}
			}
			var o = obj[i];
			//content+="";
			content+="<tr><td><input type='checkbox' value='"+o["id"]+"' name='ids'/><input type='hidden' value='"+o["gsshzt"]+"'><input type='hidden' value='"+o["sftj"]+"'/></td>";
			content+="<td>"+o["xh"]+"</td>";
			content+="<td>"+o["xm"]+"</td>";
			content+="<td>"+o["sjhm"]+"</td>";
			content+="<td>"+o["xymc"]+"</td>";
			content+="<td>"+o["bmsj"]+"</td>";
//			if(o["gsshzt"] == '1'){				
//				content+="<td>"+o["gss"]+"</td>";
//			}else{
//				content+="<td></td>";
//			}
			content+="<td>"+o["gss"]+"</td>";
			content+="<td>"+o["bmztmc"]+"</td></tr>";
		}
		jQuery(listTbody).append(content);
	}
	
}

//全选
function selectAll(obj){
	var checkboxs = jQuery("#ryTbody input[type='checkbox']");
	if(obj.checked){
		jQuery(checkboxs).each(function(){			
			if(jQuery(this).prop("checked") == false){
				jQuery(this).attr("checked",true);
			}
		})
		
	}else{
		jQuery(checkboxs).each(function(i,n){			
			if(jQuery(n).prop("checked") == true){
				jQuery(n).attr("checked",false);				
			}		
		})
	}
}

//保存审核
function bcsh(bmzt){
	var checkboxs = jQuery("[name='ids']:checked");
	if(checkboxs.length<1){
		showAlertDivLayer("请选择要审批的学生！");
		return false;
	}
	var flg = true;
	jQuery(checkboxs).each(function(i){
		var gsshzt = jQuery(checkboxs[i]).siblings("input").eq(0).val();
		var sftj = jQuery(checkboxs[i]).siblings("input").eq(1).val();
//		if(gsshzt == '1' || sftj == '1'){
//			flg = false;
//			return false;
//		}
		if(gsshzt == '1'){
			flg = false;
			return false;
		}
		
	})
	if(!flg){
		//showAlertDivLayer("有学生工时已提交，或工时已审核，不允许审核不通过！");
		showAlertDivLayer("服务工时已审核，不允许审核不通过！");
		return false;	
	}
//	if(jQuery("[name='bmzt']:checked").val() == '2'){
//		var flg = true;
//		jQuery.each(checkboxs,function(i,n){
//			if(jQuery(n).next("input").val() == '1'){
//				flg = false;
//				return false;
//			}
//		})
//		if(!flg){
//			showAlert("工时已审核，不允许审核不通过！");
//			return false;
//		}
//	}
	showConfirmDivLayer("您确定要保存该操作吗？", {
		"okFun" : function() {
			var url = "zyhdry.do?method=bcBmPlsh&bmzt="+bmzt;
			ajaxSubFormWithFun("qnzyryForm", url, function(data) {
				if(data["message"]=="保存成功！"){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
		   			searchRy('1');
		   			jQuery("#qx").attr("checked",false);
				 }});
			   	 }else{
			   		showAlertDivLayer(data["message"]);
			   	 }
			});
		}
	});
//	var url = "zyhdry.do?method=bcBmPlsh&bmzt="+bmzt;
//	ajaxSubFormWithFun("qnzyryForm", url, function(data) {
//		if(data["message"]=="保存成功！"){
//   		 showAlert(data["message"],{},{"clkFun":function(){
//   			searchRy();
//   			jQuery("#qx").attr("checked",false);
//		 }});
//	   	 }else{
//	   		 showAlert(data["message"]);
//	   	 }
//	});
}

//已发布活动列表
function createHtml(obj) {
	var listTbody = jQuery("#activity-list");
	jQuery(listTbody).empty();
	if(obj.length < 1){
		jQuery(listTbody).html("<div><p align='center'>未找到任何记录！</p></div>");
		jQuery("#total").text(0);
		jQuery("#pageno").val(0);
		jQuery("#pageCount").text(0);
		jQuery("#next").attr("href","javascript:void(0)");
		jQuery("#last").attr("href","javascript:void(0)");
		jQuery("#pre").attr("href","javascript:void(0)");
		jQuery("#first").attr("href","javascript:void(0)");
	}else{
		var content = "<ul>";
		for ( var i = 0; i < obj.length; i++) {
			if(i == obj.length -1){
				var total = obj[i]["total"];
				jQuery("#total").empty();
				jQuery("#total").text(total);
				jQuery("#pageno").val((obj[0]["rowindex"]-1)/jQuery("#pagesize").val()+1);//第几页
				var temp = total/jQuery("#pagesize").val();
				var pageCont;
				if (total%jQuery("#pagesize").val() == 0 && temp != 0){
					pageCont = temp;
				} else {
					pageCont = temp == 0 ? temp : parseInt(temp)+1;
				}
				jQuery("#pageCount").empty();
				jQuery("#pageCount").text(pageCont);
				if(jQuery("#pageno").val() == pageCont){
					jQuery("#next").attr("href","javascript:void(0)");
					jQuery("#last").attr("href","javascript:void(0)");
				}else{
					jQuery("#next").attr("href","javascript:submitNextPage()");
					jQuery("#last").attr("href","javascript:submitLastPage()");
				}
			}
			var o = obj[i];
			content+='<li><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">';
			//content+='<img src="'+o['fjpath']+'" class="img-responsive"></div>';
			content+='<img src="'+ (o['fjpath'] == null ? 'default_dekt.jpg' : o['fjpath'])+ '" style="width:170px;height:130px" id="img_'+o["hdid"]+'"></div>';
			content+='<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">';
			content+='<h4 class="title">审核状态：'+o['shztmc']+'</h4>';
			content+='<h4 class="title">'+o['hdmc']+'</h4>';
			content+='<div class="row activity-num">';
			content+='<div class="col-lg-5 col-md-5 col-sm-6 col-xs-12"><p>负责人：'+o['fzrxm']+(o['hdfzrlxfs'] != null ? '/'+o['hdfzrlxfs']+'</p></div>' : '</p></div>');
			content+='<div class="col-lg-5 col-md-4 col-sm-6 col-xs-8"><p>已报/限定人数：【<span class="num">'+o['ybrs']+'</span>/'+o['xdrs']+'】</p></div>';
			content+='<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12"><p>组织部门：'+o['zzbm']+'</p></div>';
			content+='<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12"><p>活动时间：'+o['hdkssj'];
			if(o['hdjssj'] != null){
				content+=' 至  ';
				content+=o["hdjssj"];
			}
			content+='</p></div>';
			content+='<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12"><p>报名截止时间：'+o['bmjzsj']+'</p></div>';
			content+='<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12"><p>开放学院：'+o['xymc']+'</p></div>';
			content+='<div class="col-lg-5 col-md-5 col-sm-6 col-xs-12"><p>活动地点：'+o['hddd']+'</p></div>';
//			content+='<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12">发布状态：'+o['fbztmc']+'</div>';
			content+='<div class="col-lg-5 col-md-5 col-sm-6 col-xs-12">';
			if(o['shzt'] == '1' ){
				content+='<input type="file" name="file" id="upload_'+ o["hdid"] +'" style="display: none" onchange="sctp(\''+o["hdid"]+'\')" />';
				content+='<button type="button" class="btn btn-primary green-bg-btn btn-xs" onclick="sc(\''+o['hdid']+'\');return false;" style="width:100px">上传海报</button>';
			}else if(o['shzt'] == '2'){
				
			}else{				
				content+='<button type="button" class="btn btn-primary green-bg-btn btn-xs" onclick="xg(\''+o['hdid']+'\');return false;" style="width:100px">修改活动</button>';
			}
			//content+='<span><a href="javascript:void(0);return false;" onclick="xg(\''+o['hdid']+'\');return false;">修改活动</a></span>';
			if(o['ryzt'] == 'fzr' && o['shzt'] != '2'){
				content+='<button type="button" style="margin-left:30px" class="btn btn-primary red-bg-btn btn-xs" onclick="rygl(\''+o['hdid']+'\');return false;" style="width:100px">报名管理</button>';
				//content+='<span style="margin-left:30px"><a href="javascript:void(0);return false;" onclick="rygl(\''+o['hdid']+'\');return false;">报名管理</a></span>';
			}	
			content+='</div></li>';
		}
		content+="</ul>";
		jQuery(listTbody).append(content);
	}	
}

//修改活动
function xg(hdid){
	var url = "zyhd.do?method=updateHdfb&hdid="+hdid;
	window.location.href=url;
	scrollTop();
}

//人员管理 
function rygl(hdid){
	var url = "zyhdry.do?method=rygl&hdid="+hdid;
	window.location.href=url;
	scrollTop();
}



//自动填充
function fzrAutoComplete(){
	// 重置
	//取得数据表：xg_pjpy_new_pjxmb; 字段：xmmc																							
	var autoSetting = {
			dataTable:"view_xsfdyxx",
			dataField:"xm",
			dataFieldKey:"zgh",
			dataFieldKeyId:"zgh",
			scrollHeight:135										
	}
	// 模糊搜索下拉【项目名称】
	jQuery("#xm").setAutocomplete(autoSetting);
}

//自动填充负责人联系方式
function lxfsAutoComplete(){
	
	var autoSetting = {
			dataTable:"view_xsfdyxx",
			dataField:"xm",
			dataFieldKey:"hdfzrlxfs",
			dataFieldKeyId:"hdfzrlxfs",
			scrollHeight:180										
	}
	// 模糊搜索下拉【项目名称】
	jQuery("#xm").setAutocomplete(autoSetting);
}

//验证
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

function scrollTop(){
	top.scrollTo(0, 0);
}

//修改活动发布验证人数
function checkRs(obj){
	if(obj.value == null || obj.value == '' || obj.value == 0){
		showAlertDivLayer("限定人数必须大于0!", {}, {
			"clkFun" : function() {
				obj.focus();
			}
		})
		return;
	}
	var tgrs = parseInt(jQuery("#tgrs").val());
	if((parseInt(jQuery(obj).val())) < tgrs){
		showAlertDivLayer("已有"+tgrs+"人报名通过，请重新输入!", {}, {
			"clkFun" : function() {
				obj.focus();
			}
		});
	}
}

function checkNumForPeopleForUpdate(obj){
	replaceAboveZero(obj);
	obj.value = obj.value.replace(/[^\d]/g,'').replace(/^(0)(.*)/g,'$1');
}


//上传
function sc(hdid){
	jQuery("#upload_"+hdid).click();
}


//上传图片
function sctp(hdid){
	jQuery.ajaxFileUpload({
		url : 'zyhd.do?method=upLoadPic',
		type: 'post',
		data: {  
				hdid:hdid,
				maxcount: '1',
				maxsize: '4', 
				accept: 'png,gif,jpg,bmp'
			  },
		secureuri : false,
		fileElementId : 'upload_'+hdid,
		dataType : "json",
		success : function(data,status) {
			if(data["result"] == "true"){
				jQuery("#img_"+hdid).attr("src",data["path"]);
				showAlertDivLayer("上传成功");
			}else{
				if(data["message"] == "oversize"){
					showAlertDivLayer("图片尺寸过大，请重新上传！");
				}else{
					showAlertDivLayer("图片格式不对，请重新上传！");
				}
			}
		}
	})
}

function deleteFile(){
	var listTbody = jQuery("#activity-list");
	var files = listTbody.find("input[name='file']");
	files.remove();
}

//上传
function scForUpate(){
	jQuery("#upload").click();
}

