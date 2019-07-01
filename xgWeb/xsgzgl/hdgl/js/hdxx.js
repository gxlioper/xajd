// 查询
function searchRs(cxzt,pxfs){
	if(cxzt == '1'){
		jQuery("#currentPage").val("1");
	}
	var url = "hdgl_hdxx.do?method=hdxxList&type=query&pxfs="+pxfs;
	ajaxSubFormWithFun("hdxxForm", url, function(data) {
		if(pxfs == 'wcj'){
			jQuery("#zxsx").parent().attr('class','boder-right');
			jQuery("#zjjb").parent().attr('class','boder-right');
			jQuery("#wcj").parent().attr('class','boder-right active');
			jQuery("#ymmc").val('wcj');
			createHtmlForWcj(data);			
		}else if(pxfs == 'zjjb'){
			jQuery("#zxsx").parent().attr('class','boder-right');
			jQuery("#zjjb").parent().attr('class','boder-right active');
			jQuery("#wcj").parent().attr('class','boder-right');
			jQuery("#ymmc").val('zjjb');
			createHtmlForZxsx(data);
		}else{
			jQuery("#zxsx").parent().attr('class','boder-right active');
			jQuery("#zjjb").parent().attr('class','boder-right');
			jQuery("#wcj").parent().attr('class','boder-right');
			jQuery("#ymmc").val('zxsx');
			createHtmlForZxsx(data);
		}
	});
}

//上传
function sc(){
	jQuery("#upload").click();
}

//返回
function fh(){
	window.location.href="hdgl_hdxx.do?method=hdxxList";
	scrollTop();
	
}

//返回
function xsfh(){
	window.history.back();//去另一个页面不刷新
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

//创建最新上线、最近发布div
function createHtmlForZxsx(obj) {
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
			var xy = jQuery("#xy").val();
			var sy = jQuery("#sy").val();
			if(o["bmdx"]=="特定学院报名"||o["bmdx"]=="特定学院报名"){
				if(jQuery("#xy").val()==o["bmtddx"]){
					content+='<div class="active-item row">';
					//content+='<img src="'+o['fjpath']+'" class="img-responsive"></div>';
					//content+='<img src="'+ (o['fjpath'] == null ? 'default_dekt.jpg' : o['fjpath'])+ '" style="width:170px;height:130px"></div>';
					content+='<div class="col-md-8">';
					content+='<div class="pic"><img src="'+o['hb']+'" style="width:97px;height:127px;"/></div>';
					content+='<div class="content">';
					content+='<p class="title">';
					content+=o['hdmc']+'活动等你加入';
					content+='</p>';
					
					content+='<div>';
					content+='<div class="tag"  style="display: inline"><span>'+o['hdlxmc']+'</span></div>';
					if(o['hdbqmc'] != "" && o['hdbqmc'] != null){
					    var arr = o['hdbqmc'].split(",");
					    for(var x=0;x<arr.length;x++){
					        content+='<div class="tag" style="display: inline;margin-left: 2px;"><span>'+arr[x]+'</span></div>';
					    }
					}
					content+='</div>';
					
					content+='<div class="detail">';
					content+='<div>活动时间:'+o['hdkssj']+'~'+o['hdjssj']+'</div>';
					if(o['bmkssj'] == null || o['bmjssj'] == null){
						content+='<div>报名开始时间:</div>';
					}else{
						content+='<div>报名开始时间:'+o['bmkssj']+'至'+o['bmjssj']+'</div>';
					}
					content+='<div>活动地点:'+o['hddd']+'</div>';
					content+='</div>';
					content+='</div>';
					content+='</div>';
					content+='<div class="col-md-4">';
					if(o['bmsf']=='0'){
						content+='<button type="button" class="btn btn-primary grey-bg-btn" disabled="disabled" >无需报名</button>';
					}else{
						if(o['bmztmc'] == 'bm'){				
							content+='<button type="button" class="btn btn-primary"  onclick="bm(\''+o['hdid']+'\');">报名</button>';
                            content+='<button type="button" class="btn btn-primary"  onclick="getBmlj(\''+o['hdid']+'\');">生成报名链接</button>';
						}else if(o['bmztmc'] == 'wks'){
							content+='<button type="button" class="btn btn-primary grey-bg-btn" disabled="disabled" style="width:100px">报名未开始</button>';
						}else if(o['bmztmc'] == 'yjs'){
							content+='<button type="button" class="btn btn-primary grey-bg-btn" disabled="disabled" style="width:100px">报名已结束</button>';
						}
					}
					content+='<button type="button" class="btn btn-primary"  onclick="ckHdxx(\''+o['hdid']+'\');">活动详情</button>';
					content+='</div>';
					content+='</div>';
				}
			}else if(o["bmdx"]=="特定书院报名"||o["bmdx"]=="特定书院报名"){
				if(jQuery("#sy").val()==o["bmtddx"]){
					content+='<div class="active-item row">';
					//content+='<img src="'+o['fjpath']+'" class="img-responsive"></div>';
					//content+='<img src="'+ (o['fjpath'] == null ? 'default_dekt.jpg' : o['fjpath'])+ '" style="width:170px;height:130px"></div>';
					content+='<div class="col-md-8">';
					content+='<div class="pic"><img src="'+o['hb']+'" style="width:97px;height:127px;"/></div>';
					content+='<div class="content">';
					content+='<p class="title">';
					content+=o['hdmc']+'活动等你加入';
					content+='</p>';
					
					content+='<div>';
					content+='<div class="tag"  style="display: inline"><span>'+o['hdlxmc']+'</span></div>';
					if(o['hdbqmc'] != "" && o['hdbqmc'] != null){
					    var arr = o['hdbqmc'].split(",");
					    for(var x=0;x<arr.length;x++){
					        content+='<div class="tag" style="display: inline;margin-left: 2px;"><span>'+arr[x]+'</span></div>';
					    }
					}
					content+='</div>';
					
					content+='<div class="detail">';
					content+='<div>活动时间:'+o['hdkssj']+'~'+o['hdjssj']+'</div>';
					if(o['bmkssj'] == null || o['bmjssj'] == null){
						content+='<div>报名开始时间:</div>';
					}else{
						content+='<div>报名开始时间:'+o['bmkssj']+'至'+o['bmjssj']+'</div>';
					}
					content+='<div>活动地点:'+o['hddd']+'</div>';
					content+='</div>';
					content+='</div>';
					content+='</div>';
					content+='<div class="col-md-4">';
					if(o['bmsf']=='0'){
						content+='<button type="button" class="btn btn-primary grey-bg-btn" disabled="disabled" >无需报名</button>';
					}else{
						if(o['bmztmc'] == 'bm'){				
							content+='<button type="button" class="btn btn-primary"  onclick="bm(\''+o['hdid']+'\');">报名</button>';
                            content+='<button type="button" class="btn btn-primary"  onclick="getBmlj(\''+o['hdid']+'\');">生成报名链接</button>';
						}else if(o['bmztmc'] == 'wks'){
							content+='<button type="button" class="btn btn-primary grey-bg-btn" disabled="disabled" style="width:100px">报名未开始</button>';
						}else if(o['bmztmc'] == 'yjs'){
							content+='<button type="button" class="btn btn-primary grey-bg-btn" disabled="disabled" style="width:100px">报名已结束</button>';
						}
					}
					content+='<button type="button" class="btn btn-primary"  onclick="ckHdxx(\''+o['hdid']+'\');">活动详情</button>';
					content+='</div>';
					content+='</div>';
				}
			}else{
				content+='<div class="active-item row">';
				//content+='<img src="'+o['fjpath']+'" class="img-responsive"></div>';
				//content+='<img src="'+ (o['fjpath'] == null ? 'default_dekt.jpg' : o['fjpath'])+ '" style="width:170px;height:130px"></div>';
				content+='<div class="col-md-8">';
				content+='<div class="pic"><img src="'+o['hb']+'" style="width:97px;height:127px;"/></div>';
				content+='<div class="content">';
				content+='<p class="title">';
				content+=o['hdmc']+'活动等你加入';
				content+='</p>';
				
				content+='<div>';
				content+='<div class="tag"  style="display: inline"><span>'+o['hdlxmc']+'</span></div>';
				if(o['hdbqmc'] != "" && o['hdbqmc'] != null){
				    var arr = o['hdbqmc'].split(",");
				    for(var x=0;x<arr.length;x++){
				        content+='<div class="tag" style="display: inline;margin-left: 2px;"><span>'+arr[x]+'</span></div>';
				    }
				}
				content+='</div>';
				
				content+='<div class="detail">';
				content+='<div>活动时间:'+o['hdkssj']+'~'+o['hdjssj']+'</div>';
				if(o['bmkssj'] == null || o['bmjssj'] == null){
					content+='<div>报名开始时间:</div>';
				}else{
					content+='<div>报名开始时间:'+o['bmkssj']+'至'+o['bmjssj']+'</div>';
				}
				content+='<div>活动地点:'+o['hddd']+'</div>';
				content+='</div>';
				content+='</div>';
				content+='</div>';
				content+='<div class="col-md-4">';
				if(o['bmsf']=='0'){
					content+='<button type="button" class="btn btn-primary grey-bg-btn" disabled="disabled" >无需报名</button>';
				}else{
					if(o['bmztmc'] == 'bm'){				
						content+='<button type="button" class="btn btn-primary"  onclick="bm(\''+o['hdid']+'\');">报名</button>';
                        content+='<button type="button" class="btn btn-primary"  onclick="getBmlj(\''+o['hdid']+'\');">生成报名链接</button>';
					}else if(o['bmztmc'] == 'wks'){
						content+='<button type="button" class="btn btn-primary grey-bg-btn" disabled="disabled" style="width:100px">报名未开始</button>';
					}else if(o['bmztmc'] == 'yjs'){
						content+='<button type="button" class="btn btn-primary grey-bg-btn" disabled="disabled" style="width:100px">报名已结束</button>';
					}
				}
				content+='<button type="button" class="btn btn-primary"  onclick="ckHdxx(\''+o['hdid']+'\');">活动详情</button>';
				content+='</div>';
				content+='</div>';
			}
			
		}
		jQuery(listTbody).append(content);
	}	
}

//创建我参加div
function createHtmlForWcj(obj) {
	var listTbody = jQuery("#activity-list");
	listTbody.empty();
	//jQuery(listTbody).empty();
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
			content+='<div class="active-item row">';
			//content+='<img src="'+o['fjpath']+'" class="img-responsive"></div>';
			//content+='<img src="'+ (o['fjpath'] == null ? 'default_dekt.jpg' : o['fjpath'])+ '" style="width:170px;height:130px"></div>';
			content+='<div class="col-md-8">';
			content+='<div class="pic"><img src="'+o['hb']+'" style="width:97px;height:127px;"/></div>';
			content+='<div class="content">';
			content+='<p class="title">';
			content+=o['hdmc']+'活动等你加入';
			content+='</p>';

            content+='<div>';
            content+='<div class="tag"  style="display: inline"><span>'+o['hdlxmc']+'</span></div>';
            if(o['hdbqmc'] != "" && o['hdbqmc'] != null){
                var arr = o['hdbqmc'].split(",");
                for(var x=0;x<arr.length;x++){
                    content+='<div class="tag" style="display: inline;margin-left: 2px;"><span>'+arr[x]+'</span></div>';
                }
            }
            content+='</div>';

			content+='<div class="detail">';
			content+='<div>活动时间:'+o['hdkssj']+'至'+o['hdjssj']+'</div>';
			content+='<div>报名开始时间:'+o['bmkssj']+'至'+o['bmjssj']+'</div>';
			content+='<div>活动地点:'+o['hddd']+'</div>';
			content+='</div>';
			content+='</div>';
			content+='</div>';
			content+='<div class="col-md-4">';
			//content+='<button type="button" class="btn btn-primary">详情</button>';
			content+='<button id="pl_btn" type="button" class="btn btn-primary" onclick="pl(\''+ o["hdid"] +'\',\''+o['hdmc']+'\');">评论</button>';
			//如果是个人阶段
			if(o['bmlx'] == '1' || o['bmlx'] == 1){
				//审核状态为不通过
				if(o['shzt'] == 2 && o['hdbmzt'] == '0'){
					content+='<button type="button" class="btn btn-primary" onclick="ckJd(\''+o['hdid']+'\',\''+o['btgjdid']+'\',\''+o['prejdid']+'\')">'+o['btgjdmc']+'审核不通过</button>';
				}else if(o['shzt'] == 1 && o['hdbmzt'] == '0'){//审核状态通过				
					if(o['jdsx'] > o['xsjdsx'] && (o['nextjdlx'] == '2' || o['nextjdlx'] == 2)){//如果下一审核阶段为老师的情况下(已进入下一阶段的情况下)
						content+='<button type="button" class="btn btn-primary" onclick="ckJd(\''+o['hdid']+'\',\''+o['nextjdid']+'\',\''+o['prejdid']+'\')">'+o['nextjdmc']+'</button>';
					}else if(o['jdsx'] > o['xsjdsx'] && (o['nextjdlx'] == '1' || o['nextjdlx'] == 1)){//如果下一审核阶段为学生的情况下(已进入下一阶段的情况下)
						content+='<button type="button" class="btn btn-primary" onclick="jdtx(\''+o['nextjdid']+'\',\''+o['hdid']+'\');">'+o['nextjdmc']+'填写</button>';
					}else if(o['jdsx'] > o['xsjdsx'] && (o['nextjdlx'] == null || o['nextjdlx'] == "")){
						content+='<button type="button" class="btn btn-primary" onclick="ckJd(\''+o['hdid']+'\',\''+o['jdid']+'\',\''+o['jdid']+'\')">'+o['jdmc']+'</button>';
					}else{//如果为当前阶段的情况下
						content+='<button type="button" class="btn btn-primary" onclick="jdtx(\''+o['xsjdid']+'\',\''+o['hdid']+'\');">'+o['xsjdmc']+'填写</button>';
					}
				}else if(o['shzt'] == 5 && o['hdbmzt'] == '0'){//审核状态为审核中
					//content+='<button type="button" class="btn btn-primary" onclick="jdtx(\''+o['xsjdid']+'\',\''+o['hdid']+'\');">'+o['xsjdmc']+'审核中</button>';
					if(o['jdsx'] > o['xsjdsx'] && (o['nextjdlx'] == '2' || o['nextjdlx'] == 2)){//如果下一审核阶段为老师的情况下(已进入下一阶段的情况下)
						content+='<button type="button" class="btn btn-primary" onclick="ckJd(\''+o['hdid']+'\',\''+o['nextjdid']+'\',\''+o['prejdid']+'\')">'+o['nextjdmc']+'</button>';
					}else if(o['jdsx'] > o['xsjdsx'] && (o['nextjdlx'] == '1' || o['nextjdlx'] == '1')){//如果下一审核阶段为学生的情况下(已进入下一阶段的情况下)
						content+='<button type="button" class="btn btn-primary" onclick="jdtx(\''+o['nextjdid']+'\',\''+o['hdid']+'\');">'+o['nextjdmc']+'填写</button>';
					}else{//如果为当前阶段的情况下
						content+='<button type="button" class="btn btn-primary" onclick="jdtx(\''+o['xsjdid']+'\',\''+o['hdid']+'\');">'+o['xsjdmc']+'填写</button>';
					}
				}else if(o['shzt'] == 3 && o['hdbmzt'] == '0'){//审核状态为退回
					content+='<button type="button" class="btn btn-primary" onclick="jdtx(\''+o['xsjdid']+'\',\''+o['hdid']+'\');">'+'已退回，'+o['xsjdmc']+'重新填写</button>';
				}else{
//					else if(!!o['jdmc'] && (o['jdlx'] == '2' || o['jdlx'] == 2)){//处在第一阶段，且第一阶段为老师
//						content+='<button type="button" class="btn btn-primary" disabled="disabled">'+o['jdmc']+'</button>';
//					}
					if(o['hdbmzt'] == '1' || o['hdbmzt'] == '1'){//活动未开始
						if(o['hdbmshzt'] == '1' || o['hdbmshzt'] == '1'){//报名审核通过
							if(o['hdpp'] == '0' || o['hdpp'] == '0'){
								content+='<button type="button" class="btn btn-primary" disabled="disabled">派票中</button>';
							}else{
								content+='<button type="button" class="btn btn-primary" disabled="disabled">活动未开始</button>';
							}
						}else if(o['hdbmshzt'] == '5' || o['hdbmshzt'] == '5'){//审核中
							content+='<button type="button" class="btn btn-primary" disabled="disabled">审核中</button>';
						}else{
							content+='<button type="button" class="btn btn-primary"  onclick="cxbm(\''+o['hdid']+'\');">重新报名</button>';
						}
					}else if(o['hdbmzt'] == '2' || o['hdbmzt'] == '2'){//活动已结束
						content+='<button type="button" class="btn btn-primary" disabled="disabled">活动已结束</button>';
					}else{//活动已开始
						if(o['hdbmshzt'] == '1' || o['hdbmshzt'] == '1'){//报名审核通过
							if(o['hdpp'] == '0' || o['hdpp'] == '0'){
								content+='<button type="button" class="btn btn-primary" disabled="disabled">无票</button>';
							}else{
								if(o['hdkclx'] == '参与类' || o['hdkclx' == '参与类']){//参与类单阶段活动
									content+='<button type="button" class="btn btn-primary" disabled="disabled">活动已开始</button>';
								}else{
									if(!!o['jdmc']){//处在第一阶段，且第一阶段为学生
										content+='<button type="button" class="btn btn-primary" onclick="jdtx(\''+o['firstjdid']+'\',\''+o['hdid']+'\');">'+o['firstjdmc']+'填写</button>';
									}else if(!!o['jdmc'] && (o['jdlx'] == '2' || o['jdlx'] == 2)){//处在第一阶段，且第一阶段为老师
										content+='<button type="button" class="btn btn-primary" disabled="disabled">'+o['jdmc']+'</button>';
									}else{
										content+='<button type="button" class="btn btn-primary" disabled="disabled">活动已开始</button>';
									}
								}
							}
						}else if(o['hdbmshzt'] == '5' || o['hdbmshzt'] == '5'){//审核中
							content+='<button type="button" class="btn btn-primary" disabled="disabled">审核未通过</button>';
						}else{
							content+='<button type="button" class="btn btn-primary" disabled="disabled">审核未通过</button>';
						}
					}
					
				}
			}else{//如果是组队阶段
				content+='<button id="pl_btn" type="button" class="btn btn-primary" onclick="ckdw(\''+ o["hdid"] +'\',\''+o['dwid']+'\');">队伍信息</button>';
				if(o['zdshzt'] == 2 && o['hdbmzt'] == '0'){
//					content+=o['xszdjdmc']+'审核不通过';
					content+='<button type="button" class="btn btn-primary" onclick="ckJdzd(\''+o['hdid']+'\',\''+o['zdbtgjdid']+'\',\''+o['zdprejdid']+'\')">'+o['zdbtgjdmc']+'审核不通过</button>';
				}else if(o['zdshzt'] == 1 && o['hdbmzt'] == '0'){//审核状态通过				
					if(o['jdsx'] > o['xszdjdsx'] && (o['zdnextjdlx'] == '2' || o['zdnextjdlx'] == 2)){
						content+='<button type="button" class="btn btn-primary" onclick="ckJdzd(\''+o['hdid']+'\',\''+o['zdnextjdid']+'\',\''+o['zdprejdid']+'\')">'+o['zdnextjdmc']+'</button>';
					}else if(o['jdsx'] > o['xszdjdsx'] && (o['zdnextjdlx'] == '1' || o['zdnextjdlx'] == 1)){//活动节点大于组队节点（表示已进入一下流程）
						if(o['dwzw'] == 1 || o['dwzw'] == '1'){//如果为队长职务
							content+='<button type="button" class="btn btn-primary" onclick="zdjdtx(\''+o['zdnextjdid']+'\',\''+o['hdid']+'\');">'+o['zdnextjdmc']+'填写</button>';
						}else{
							content+='<button type="button" class="btn btn-primary" disabled="disabled">'+o['zdnextjdmc']+'</button>';
						}
					}else if(o['jdsx'] > o['xsjdsx'] && (o['nextjdlx'] == null || o['nextjdlx'] == "")){
						content+='<button type="button" class="btn btn-primary" onclick="ckJdzd(\''+o['hdid']+'\',\''+o['jdid']+'\',\''+o['jdid']+'\')">'+o['jdmc']+'</button>';
					}else{//如果为当前阶段情况下
						content+='<button type="button" class="btn btn-primary" onclick="zdjdtx(\''+o['xszdjdid']+'\',\''+o['hdid']+'\');">'+o['xszdjdmc']+'填写</button>';
					}
				}else if(o['zdshzt'] == 5 && o['hdbmzt'] == '0'){
					if(o['jdsx'] > o['xszdjdsx'] && (o['zdnextjdlx'] == '2' || o['zdnextjdlx'] == 2)){
						content+='<button type="button" class="btn btn-primary" onclick="ckJdzd(\''+o['hdid']+'\',\''+o['zdnextjdid']+'\',\''+o['zdprejdid']+'\')">'+o['zdnextjdmc']+'</button>';
					}else if(o['jdsx'] > o['xszdjdsx'] && (o['zdnextjdlx'] == '1' || o['zdnextjdlx'] == '1')){//活动节点大于组队节点（表示已进入一下流程）
						if(o['dwzw'] == 1 || o['dwzw'] == '1'){//如果为队长职务							
							content+='<button type="button" class="btn btn-primary" onclick="zdjdtx(\''+o['zdnextjdid']+'\',\''+o['hdid']+'\');">'+o['zdnextjdmc']+'填写</button>';
						}else{
							content+='<button type="button" class="btn btn-primary" disabled="disabled">'+o['zdnextjdmc']+'</button>';
						}
					}else{
						content+='<button type="button" class="btn btn-primary" onclick="zdjdtx(\''+o['xszdjdid']+'\',\''+o['hdid']+'\');">'+o['xszdjdmc']+'填写</button>';
					}				
				}else if(o['zdshzt'] == 3 && o['hdbmzt'] == '0'){//审核状态为退回
					if(o['dwzw'] == 1 || o['dwzw'] == '1'){						
						content+='<button type="button" class="btn btn-primary" onclick="zdjdtx(\''+o['xszdjdid']+'\',\''+o['hdid']+'\');">'+'已退回，'+o['xszdjdmc']+'重新填写</button>';
					}else{
						content+='<button type="button" class="btn btn-primary" onclick="ckJdzd(\''+o['hdid']+'\',\''+o['xszdjdid']+'\',\''+o['zdprejdid']+'\');">'+'已退回'+o['xszdjdmc']+'</button>';
					}
				}else{
					if(o['hdbmzt'] == '1' || o['hdbmzt'] == '1'){//活动未开始
						content+='<button type="button" class="btn btn-primary" disabled="disabled">活动未开始</button>';
					}else if(o['hdbmzt'] == '2' || o['hdbmzt'] == '2'){//活动已结束
						content+='<button type="button" class="btn btn-primary" disabled="disabled">活动已结束</button>';
					}else{//活动进行中
						if(o['hdkclx'] == '参与类' || o['hdkclx' == '参与类']){//参与类单阶段活动(正常情况组队活动均为多阶段)
							content+='<button type="button" class="btn btn-primary" disabled="disabled">活动已开始</button>';
						}else{
							if(!!o['jdmc']){//处在第一阶段，且第一阶段为学生
								if(o['dwzw'] == 1 || o['dwzw'] == '1'){	//队长						
									content+='<button type="button" class="btn btn-primary" onclick="zdjdtx(\''+o['firstjdid']+'\',\''+o['hdid']+'\');">'+o['firstjdmc']+'填写</button>';
								}else{
									content+='<button type="button" class="btn btn-primary" disabled="disabled">'+o['firstjdmc']+'</button>';
								}
							}else if(!!o['jdmc'] && (o['jdlx'] == '2' || o['jdlx'] == 2)){//处在第一阶段，且第一阶段为老师
								content+='<button type="button" class="btn btn-primary" disabled="disabled">'+o['jdmc']+'</button>';
							}else{
								content+='<button type="button" class="btn btn-primary" disabled="disabled">活动已开始</button>';
							}
						}
						
					}
				}
			}
			content+='<button type="button" class="btn btn-primary"  onclick="ckHdxx(\''+o['hdid']+'\',\''+o['bmlx']+'\');">活动详情</button>';
			content+='</div>';
			content+='</div>';
		}
		jQuery(listTbody).append(content);
	}	
}
function cxbm(hdid) {
    var url = "hdgl_hdxx.do?method=bm&hdid="+hdid+"&lx=grbm";//个人报名
    showDialog("活动报名表单", 800, 550, url);
}
//队伍信息
function ckdw(hdid,dwid){
	var url = "hdgl_hdxx.do?method=ckdw&hdid="+ hdid +"&dwid="+ dwid;
	showDialog("我的队伍", 400, 300, url);
}
function ckHdxx(hdid,bmlx){
	window.location.href="hdgl_hdxx.do?method=ckHdxx&hdid="+hdid+"&bmlx="+bmlx;
	scrollTop();
}

function ckJd(hdid,jdid,prejdid){
	var url = "hdgl_hdxx.do?method=ckjd&hdid="+hdid+"&jdid="+jdid+"&prejdid="+prejdid;
	window.location.href=url;
	scrollTop();
}

//查看组队阶段
function ckJdzd(hdid,jdid,prejdid){
	var url = "hdgl_hdxx.do?method=ckjdzd&hdid="+hdid+"&jdid="+jdid+"&prejdid="+prejdid;
	window.location.href=url;
	scrollTop();
}

//阶段填写
function jdtx(jdid,hdid){
	var url = "hdgl_hdxx.do?method=jdtx&hdid="+hdid+"&jdid="+jdid;
	window.location.href=url;
	scrollTop();
}

//组队阶段填写
function zdjdtx(jdid,hdid){
	var url = "hdgl_hdxx.do?method=zdjdtx&hdid="+hdid+"&jdid="+jdid;
	window.location.href=url;
	scrollTop();
}

//活动报名
function bm(hdid){
	var url = "hdgl_hdxx.do?method=getHdxx&hdid="+hdid;
	window.location.href=url;
	scrollTop();
}
function getBmlj(hdid) {
	var url = "hdgl_hdxx.do?method=getBmEwm&hdid="+hdid;
    showDialog("报名二维码链接", 360, 200, url);
}
function bmview(){
    var hdid = jQuery("#hdid").val();
    jQuery.post("hdgl_hdxx.do?method=geBmCheck",{hdid:hdid},function(data){
    	if(data["message"] == "true"){
            var url = "hdgl_hdxx.do?method=bm&hdid="+hdid+"&lx=grbm";//个人报名
            showDialog("活动报名表单", 800, 550, url);
		} else {
    		showAlert(data["message"]);
		}
	},'json');
}
function zdbmview(){
    var hdid = jQuery("#hdid").val();
    jQuery.post("hdgl_hdxx.do?method=gezdBmCheck",{hdid:hdid},function(data){
        if(data["message"] == "true"){
            showDialog('请选择队员', 800, 500, 'xsxx_xsgl.do?method=selectDy&hdid=' + hdid);
        } else {
            showAlert(data["message"]);
        }
    },'json');

}
/**
 * 选择队友页面 页签选择
 */
function selectTab(obj, shzt) {
    jQuery("#sffp").val(shzt);
    if (shzt == "0") {
        jQuery("#li_bc").css("display", "");
        jQuery("#li_qx").css("display", "none");
        var map = getSuperSearch();
        map["sffp"]="0";
        gridSetting["params"] = map;
        jQuery("#dataTable").initGrid(gridSetting);
    } else {
        jQuery("#li_bc").css("display", "none");
        jQuery("#li_qx").css("display", "");
        var map = getSuperSearch();
        map["sffp"]="1";
        map["xhs"]=jQuery("#xhs").val();
        gridSetting2["params"] = map;
        jQuery("#dataTable").initGrid(gridSetting2);
    }
    jQuery(".ha").removeClass("ha");
    jQuery(obj).parent().addClass("ha");
}
//创建活动团队
function createGroup(){
	var url = 'hdgl_hdxx.do?method=createGroup';
    ajaxSubFormWithFun("demoForm", url, function(data) {
        if(data["message"]=="保存成功！"){
            showAlert(data["message"],{},{"clkFun":function(){
                if (parent.window){
                    if(frameElement.api){
                        var api = frameElement.api,W = api.opener;
                        W.fh();
                        closeDialog();
                    } else {
                        iFClose();
                    }
                }
            }});

        }else if(data["message"]=="队友人数超出队伍上限！"){
			showAlert(data["message"],{},{"clkFun":function(){
				iFClose();
			}});
		}else{
			showAlertDivLayer(data["message"]);
        }
    });
}

//加入活动报名
function bmForGroup(){
	var hdid = jQuery("#hdid").val();
    jQuery.post("hdgl_hdxx.do?method=gezdBmCheck",{hdid:hdid},function(data){
        if(data["message"] == "true"){
            var url = "hdgl_hdxx.do?method=getDwList&hdid="+hdid;
            var title = "组队列表选择";
            showDialog(title, 600, 500, url);
        } else {
            showAlert(data["message"]);
        }
    },'json');

}

function jrdwSave(){
    var url = "hdgl_hdxx.do?method=jrdw";
    ajaxSubFormWithFun("demoForm", url, function(data) {
        if(data["message"]=="保存成功！"){
            showAlert(data["message"],{},{"clkFun":function(){
                if (parent.window){
                    if(frameElement.api){
                        var api = frameElement.api,W = api.opener;
                        W.fh();
                        closeDialog();
                    } else {
                        iFClose();
                    }
                }
            }});

        }else{
            showAlertDivLayer(data["message"]);
        }
    });
}

//报名保存
function saveBm(){
    var url = "hdgl_hdxx.do?method=saveBm";
	ajaxSubFormWithFun("demoForm", url, function(data) {
		if(data["message"]=="保存成功！"){
			showAlert(data["message"],{},{"clkFun":function(){
                if (parent.window){
                    if(frameElement.api){
                        var api = frameElement.api,W = api.opener;
                        W.fh();
                        closeDialog();
                    } else {
                        iFClose();
                    }
                }
			}});

		}else{
			showAlertDivLayer(data["message"]);
		}
	});
}

//提交阶段填写
function tj(){
	// showConfirmDivLayer("您确定要保存该操作吗？", {
	// 	"okFun" : function() {
			var url = "hdgl_hdxx.do?method=saveStudentStage";
			ajaxSubFormWithFun("hdxxForm", url, function(data) {
				if(data["message"]=="保存成功！"){
					showAlert(data["message"],{},{"clkFun":function(){
						jdfh();
					}});
//					showAlertDivLayer(data["message"]);
//					jdfh();
			   	 }else{
			   		showAlertDivLayer(data["message"]);
			   	 }
			});
		// }
	// });
}

function jdfh(){
//	window.history.back();
//	searchRs('1','wcj');
	window.location.href = "hdgl_hdxx.do?method=hdxxList&pxfs=wcj";
//	window.location.href = "hdgl_hdxx.do?method=hdxxList&type=query&pxfs=wcj";
	scrollTop();
}

function scrollTop(){
	top.scrollTo(0, 0);
}

//评论
function pl(hdid,hdmc){
	var title = "评论";
	// var hdmc = jQuery("#hdmc").val();
	var url = "hdgl_hdxx.do?method=hdpl&hdid="+hdid+"&hdmc="+encodeURIComponent(encodeURIComponent(hdmc));
	showDialog(title, 600, 500, url);
}

function scZp(){
    jQuery("#imgUpload").trigger("click");
}
function upload(){
    var xh = jQuery("#xh").val();
    jQuery.ajaxFileUpload({
        url : "hdgl_hdxx.do?method=uploadZp&xh=" + xh,
        secureuri : false,
        fileElementId : "imgUpload", //file的id
        dataType : "json",
        success : function(data, status) {
            if (data["message"] == 'true') {
                jQuery("#avatarPreview").attr(
                    "src",
                    "xsxx_xsgl.do?method=showPhoto&xh=" + xh + "&tt="
                    + new Date());
                jQuery("#imgCheck").val("1");
            } else {
                showAlert(data["message"]);
            }
        }
    });
}