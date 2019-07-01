// 查询
function searchRs(cxzt){
	if(cxzt == '1'){
		jQuery("#currentPage").val("1");
	}
	var url = "zyhd.do?method=zyhdList&type=query";
	ajaxSubFormWithFun("qnzyhdForm", url, function(data) {
			createHtml(data);
	});
}

//上传
function sc(){
	jQuery("#upload").click();
}

//返回
function fh(){
	window.location.href="zyhd.do?method=zyhdList";
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
					accept: 'png,gif,jpg,bmp'
				  },
			secureuri : false,
			fileElementId : 'upload',
			dataType : "json",
			success : function(data,status) {
				if(data["result"] == "true"){
//					if(jQuery("#oldPath").length<1){
//						jQuery("#fjpath").after("<input type='hidden' id='oldPath' name='oldPath'/>");
//					}else{
//						var oldpath = jQuery("#oldPath").val();
//						oldpath+=data["path"];
//						oldpath+="||";
//					}
					jQuery("#fjpath").val(data["path"]);
					jQuery("img").css("height","100%");
					jQuery("img").css("width","100%");
					jQuery("img").attr("src",data["path"]);
				}else{
					if(data["message"] == "oversize"){
						showAlertDivLayer("图片尺寸过大，请重新上传！");
					}else{
						showAlertDivLayer("图片格式不对，请重新上传！");
					}
				}
				bindUpload();
			}
		})
	})
}

//发布保存
function bc(type){
	editor.sync();
	var ids;
	var url;
	
	ids = "hdmc-fwlx-hddd-fwdx-xdrs-hdkssj-xm-zzbm-bmjzsj-jbfwgs-hdfzrlxfs";
	//ids = "hdmc-fwlx-hddd-fwdx-xdrs-hdkssj-xm-zzbm-fjpath-hdfzrlxfs";
	if(check(ids) == false){
		showAlertDivLayer("请将带<font color='red'>*</font>的项目填写完整！");
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
	url = 'zyhd.do?method=save';
	ajaxSubFormWithFun("qnzyhdForm", url, function(data) {
		 if(data["message"]=="保存成功！"){
			 showAlertDivLayer(data["message"],{},{"clkFun":function(){
    			 fh();
//						if (parent.window){
//							refershParent();
//						}
				}});
    	 }else{
    		 showAlertDivLayer(data["message"]);
    	}
	});	
}

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
			content+='<img src="'+ (o['fjpath'] == null ? 'default_dekt.jpg' : o['fjpath'])+ '" style="width:170px;height:130px"></div>';
			content+='<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">';
			content+='<h4 class="title">'+o['hdmc']+'</h4>';
			content+='<div class="row activity-num">';
			content+='<div class="col-lg-5 col-md-5 col-sm-6 col-xs-12"><p>负责人：'+o['fzrxm']+(o['hdfzrlxfs'] != null ? '/'+o['hdfzrlxfs']+'</p></div>' : '</p></div>');
			content+='<div class="col-lg-5 col-md-4 col-sm-4 col-xs-8" style="margin-left:-20px"><p>已报/限定人数：【<span class="num">'+o['ybrs']+'</span>/'+o['xdrs']+'】</p></div>';
			content+='<div class="col-lg-2 col-md-3 col-sm-2 col-xs-4 text-right padding-r0">';
			if(o['jxzt'] == '已结束' || o['jxzt'] == '报名已截止' || o['jxzt'] == '人数已满'){
				if(jQuery("#userStatus").val() == "stu" && o['xszt'] == '1'){
					content+='<button type="button" class="btn btn-primary grey-bg-btn" disabled="disabled" style="width:100px">已报名</button></div>';
				}else{					
					content+='<button type="button" class="btn btn-primary grey-bg-btn" disabled="disabled" style="width:100px">'+o['jxzt']+'</button></div>';
				}
			}else{
				if(jQuery("#userStatus").val() == "stu"){
					if(o['xszt'] == '1'){
						content+='<button type="button" class="btn btn-primary grey-bg-btn" disabled="disabled" style="width:100px">已报名</button></div>';
					}else{						
						content+='<button type="button" class="btn btn-primary green-bg-btn" onclick="bm(\''+o['hdid']+'\');return false;" style="width:100px">'+o['jxzt']+'</button></div>';
					}
				}else{
					content+='<button type="button" class="btn btn-primary green-bg-btn" disabled="disabled" style="width:100px">'+o['lsjxzt']+'</button></div>';
				}
			}
			content+='</div><div><p>组织部门：'+o['zzbm']+'</p>';
			content+='<p>活动时间：'+o['hdkssj'];
			if(o['hdjssj'] != null){
				content+=' 至  ';
				content+=o["hdjssj"];
			}
			content+='</p>';
			content+='<p>报名截止时间：'+o["bmjzsj"]+'</p>';
			content+='<p>开放学院：'+o["xymc"]+'</p>';
			content+='<p>活动地点：'+o['hddd']+'</p></div>';
			content+='</div></li>';
		}
		content+="</ul>";
		jQuery(listTbody).append(content);
	}	
}

//活动报名
function bm(hdid){
	var url = "zyhd.do?method=qnhdrybm&hdid="+hdid;
	window.location.href=url;
	scrollTop();
}

//报名保存
function savebm(hdid){
	var url = "zyhdry.do?method=saveJl&hdid="+hdid+"&gs="+jQuery("#gs").val();
	jQuery.ajaxSetup({async:false});
		jQuery.post(url, {hdid:hdid}, function(data) {
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				fh();
			}});				
		}, 'json');
	jQuery.ajaxSetup({async:true});
}

//发布活动
function add(){
	var url = "zyhd.do?method=addHdfb";
	window.location.href=url;
	scrollTop();
}


//自动填充负责人工号
function fzrAutoComplete(){
	// 重置
	//取得数据表：xg_pjpy_new_pjxmb; 字段：xmmc																							
	var autoSetting = {
			dataTable:"view_xsfdyxx",
			dataField:"xm",
			dataFieldKey:"zgh",
			dataFieldKeyId:"zgh",
			scrollHeight:180										
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


//切换Tab页
function selectTab(obj, shzt) {
	jQuery("#shzt").val(shzt);
	if (shzt == "dsh") {
		jQuery("#li_sh").css("display", "");
		jQuery("#li_qx").css("display", "none");
		var map = getSuperSearch();
		map["shzt"]="dsh";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_sh").css("display", "none");
		jQuery("#li_qx").css("display", "");
		var map = getSuperSearch();
		map["shzt"]="ysh";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

function sbsh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlertDivLayer("请选择您要审核的记录")
		return false;
	} else if (rows.length == 1) {
		var url = "zyhd.do?method=sbDgsh&hdid=" + rows[0]["hdid"];
		showDialog("审核", 700, 480, url);
	} else {
		showDialog("批量审核", 500, 250, "zyhd.do?method=sbPlsh");
	}
}









