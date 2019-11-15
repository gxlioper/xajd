function searchHd() {
	var pxfs = jQuery("#ymmc").val();
	searchRs('1',pxfs);
}
// ��ѯ
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
			createHtmlForZjjb(data);
		}else{
			jQuery("#zxsx").parent().attr('class','boder-right active');
			jQuery("#zjjb").parent().attr('class','boder-right');
			jQuery("#wcj").parent().attr('class','boder-right');
			jQuery("#ymmc").val('zxsx');
			createHtmlForZxsx(data);
		}
	});
}

//�ϴ�
function sc(){
	jQuery("#upload").click();
}

//����
function fh(){
	window.location.href="hdgl_hdxx.do?method=hdxxList";
	scrollTop();
	
}

//����
function xsfh(){
	window.history.back();//ȥ��һ��ҳ�治ˢ��
}


//�ϴ���
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
						showAlertDivLayer("ͼƬ�ߴ�����������ϴ���");
					}else{
						showAlertDivLayer("ͼƬ��ʽ���ԣ��������ϴ���");
					}
				}
				bindUpload();
			}
		})
	})
}

//�����������ߡ��������div
function createHtmlForZxsx(obj) {
	var listTbody = jQuery("#activity-list");
	jQuery(listTbody).empty();
	if(obj.length < 1){
		jQuery(listTbody).html("<div><p align='center'>δ�ҵ��κμ�¼��</p></div>");
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
				jQuery("#pageno").val((obj[0]["rowindex"]-1)/jQuery("#pagesize").val()+1);//�ڼ�ҳ
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
			if(o["bmdx"]=="�ض�ѧԺ����"||o["bmdx"]=="�ض�ѧԺ����"){
				if(jQuery("#xy").val()==o["bmtddx"]){
					content+='<div class="active-item row">';
					//content+='<img src="'+o['fjpath']+'" class="img-responsive"></div>';
					//content+='<img src="'+ (o['fjpath'] == null ? 'default_dekt.jpg' : o['fjpath'])+ '" style="width:170px;height:130px"></div>';
					content+='<div class="col-md-8">';
					content+='<div class="pic"><img src="'+o['hb']+'" style="width:97px;height:127px;z-index: 1"  /></div>';
					content+='<div class="content">';
					content+='<p class="title">';
					content+=o['hdmc']+'��������';
					content+='</p>';
					
					content+='<div>';
					content+='<div class="tag"  style="display: inline"><span>'+o['hdlxmc']+'</span></div>';
					if(o['hdbqmc'] != "" && o['hdbqmc'] != null){
					    var arr = o['hdbqmc'].split(",");
					    for(var x=0;x<arr.length;x++){
					        content+='<div class="tag" style="display: inline;margin-left: 2px;"><span>'+arr[x]+'</span></div>';
					    }
					}
                    if(o['nlbqmc'] != "" && o['nlbqmc'] != null){
                        var arr = o['nlbqmc'].split(",");
                        for(var x=0;x<arr.length;x++){
                            content+='<div class="tag" style="display: inline;margin-left: 2px;"><span>'+arr[x]+'</span></div>';
                        }
                    }
					content+='</div>';
					
					content+='<div class="detail">';
					content+='<div>�ʱ��:'+o['hdkssj']+'~'+o['hdjssj']+'</div>';
					if(o['bmkssj'] == null || o['bmjssj'] == null){
						content+='<div>������ʼʱ��:</div>';
					}else{
						content+='<div>������ʼʱ��:'+o['bmkssj']+'��'+o['bmjssj']+'</div>';
					}
					content+='<div>��ص�:'+o['hddd']+'</div>';
					content+='</div>';
					content+='</div>';
					content+='</div>';
					content+='<div class="col-md-4">';
					if(o['bmsf']=='0'){
						content+='<button type="button" class="btn btn-primary grey-bg-btn" disabled="disabled" >���豨��</button>';
					}else{
						if(o['bmztmc'] == 'bm'){				
							content+='<button type="button" class="btn btn-primary"  onclick="bm(\''+o['hdid']+'\');">����</button>';
                            content+='<button type="button" class="btn btn-primary"  onclick="getBmlj(\''+o['hdid']+'\');">���ɱ�������</button>';
						}else if(o['bmztmc'] == 'wks'){
							content+='<button type="button" class="btn btn-primary grey-bg-btn" disabled="disabled" style="width:100px">����δ��ʼ</button>';
						}else if(o['bmztmc'] == 'yjs'){
							content+='<button type="button" class="btn btn-primary grey-bg-btn" disabled="disabled" style="width:100px">�����ѽ���</button>';
						}
					}
					content+='<button type="button" class="btn btn-primary"  onclick="ckHdxx(\''+o['hdid']+'\');">�����</button>';
					content+='</div>';
					content+='</div>';
				}
			}else if(o["bmdx"]=="�ض���Ժ����"||o["bmdx"]=="�ض���Ժ����"){
				if(jQuery("#sy").val()==o["bmtddx"]){
					content+='<div class="active-item row">';
					//content+='<img src="'+o['fjpath']+'" class="img-responsive"></div>';
					//content+='<img src="'+ (o['fjpath'] == null ? 'default_dekt.jpg' : o['fjpath'])+ '" style="width:170px;height:130px"></div>';
					content+='<div class="col-md-8">';
					content+='<div class="pic"><img src="'+o['hb']+'" style="width:97px;height:127px;z-index: 1" /></div>';
					content+='<div class="content">';
					content+='<p class="title">';
					content+=o['hdmc']+'��������';
					content+='</p>';
					
					content+='<div>';
					content+='<div class="tag"  style="display: inline"><span>'+o['hdlxmc']+'</span></div>';
					if(o['hdbqmc'] != "" && o['hdbqmc'] != null){
					    var arr = o['hdbqmc'].split(",");
					    for(var x=0;x<arr.length;x++){
					        content+='<div class="tag" style="display: inline;margin-left: 2px;"><span>'+arr[x]+'</span></div>';
					    }
					}
                    if(o['nlbqmc'] != "" && o['nlbqmc'] != null){
                        var arr = o['nlbqmc'].split(",");
                        for(var x=0;x<arr.length;x++){
                            content+='<div class="tag" style="display: inline;margin-left: 2px;"><span>'+arr[x]+'</span></div>';
                        }
                    }
					content+='</div>';
					
					content+='<div class="detail">';
					content+='<div>�ʱ��:'+o['hdkssj']+'~'+o['hdjssj']+'</div>';
					if(o['bmkssj'] == null || o['bmjssj'] == null){
						content+='<div>������ʼʱ��:</div>';
					}else{
						content+='<div>������ʼʱ��:'+o['bmkssj']+'��'+o['bmjssj']+'</div>';
					}
					content+='<div>��ص�:'+o['hddd']+'</div>';
					content+='</div>';
					content+='</div>';
					content+='</div>';
					content+='<div class="col-md-4">';
					if(o['bmsf']=='0'){
						content+='<button type="button" class="btn btn-primary grey-bg-btn" disabled="disabled" >���豨��</button>';
					}else{
						if(o['bmztmc'] == 'bm'){				
							content+='<button type="button" class="btn btn-primary"  onclick="bm(\''+o['hdid']+'\');">����</button>';
                            content+='<button type="button" class="btn btn-primary"  onclick="getBmlj(\''+o['hdid']+'\');">���ɱ�������</button>';
						}else if(o['bmztmc'] == 'wks'){
							content+='<button type="button" class="btn btn-primary grey-bg-btn" disabled="disabled" style="width:100px">����δ��ʼ</button>';
						}else if(o['bmztmc'] == 'yjs'){
							content+='<button type="button" class="btn btn-primary grey-bg-btn" disabled="disabled" style="width:100px">�����ѽ���</button>';
						}
					}
					content+='<button type="button" class="btn btn-primary"  onclick="ckHdxx(\''+o['hdid']+'\');">�����</button>';
					content+='</div>';
					content+='</div>';
				}
			}else{
				content+='<div class="active-item row">';
				//content+='<img src="'+o['fjpath']+'" class="img-responsive"></div>';
				//content+='<img src="'+ (o['fjpath'] == null ? 'default_dekt.jpg' : o['fjpath'])+ '" style="width:170px;height:130px"></div>';
				content+='<div class="col-md-8">';
				content+='<div class="pic"><img src="'+o['hb']+'" style="width:97px;height:127px;z-index: 1" /></div>';
				content+='<div class="content">';
				content+='<p class="title">';
				content+=o['hdmc']+'��������';
				content+='</p>';
				
				content+='<div>';
				content+='<div class="tag"  style="display: inline"><span>'+o['hdlxmc']+'</span></div>';
				if(o['hdbqmc'] != "" && o['hdbqmc'] != null){
				    var arr = o['hdbqmc'].split(",");
				    for(var x=0;x<arr.length;x++){
				        content+='<div class="tag" style="display: inline;margin-left: 2px;"><span>'+arr[x]+'</span></div>';
				    }
				}
                if(o['nlbqmc'] != "" && o['nlbqmc'] != null){
                    var arr = o['nlbqmc'].split(",");
                    for(var x=0;x<arr.length;x++){
                        content+='<div class="tag" style="display: inline;margin-left: 2px;"><span>'+arr[x]+'</span></div>';
                    }
                }
				content+='</div>';
				
				content+='<div class="detail">';
				content+='<div>�ʱ��:'+o['hdkssj']+'~'+o['hdjssj']+'</div>';
				if(o['bmkssj'] == null || o['bmjssj'] == null){
					content+='<div>������ʼʱ��:</div>';
				}else{
					content+='<div>������ʼʱ��:'+o['bmkssj']+'��'+o['bmjssj']+'</div>';
				}
				content+='<div>��ص�:'+o['hddd']+'</div>';
				content+='</div>';
				content+='</div>';
				content+='</div>';
				content+='<div class="col-md-4">';
				if(o['bmsf']=='0'){
					content+='<button type="button" class="btn btn-primary grey-bg-btn" disabled="disabled" >���豨��</button>';
				}else{
					if(o['bmztmc'] == 'bm'){				
						content+='<button type="button" class="btn btn-primary"  onclick="bm(\''+o['hdid']+'\');">����</button>';
                        content+='<button type="button" class="btn btn-primary"  onclick="getBmlj(\''+o['hdid']+'\');">���ɱ�������</button>';
					}else if(o['bmztmc'] == 'wks'){
						content+='<button type="button" class="btn btn-primary grey-bg-btn" disabled="disabled" style="width:100px">����δ��ʼ</button>';
					}else if(o['bmztmc'] == 'yjs'){
						content+='<button type="button" class="btn btn-primary grey-bg-btn" disabled="disabled" style="width:100px">�����ѽ���</button>';
					}
				}
				content+='<button type="button" class="btn btn-primary"  onclick="ckHdxx(\''+o['hdid']+'\');">�����</button>';
				content+='</div>';
				content+='</div>';
			}
			
		}
		jQuery(listTbody).append(content);
        jQuery('.pic img').zoomify();//ͼƬ�Ŵ�
	}	
}

//��������ٰ�
function createHtmlForZjjb(obj) {
    var listTbody = jQuery("#activity-list");
    jQuery(listTbody).empty();
    if(obj.length < 1){
        jQuery(listTbody).html("<div><p align='center'>δ�ҵ��κμ�¼��</p></div>");
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
                jQuery("#pageno").val((obj[0]["rowindex"]-1)/jQuery("#pagesize").val()+1);//�ڼ�ҳ
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
            if(o["bmdx"]=="�ض�ѧԺ����"||o["bmdx"]=="�ض�ѧԺ����"){
                if(jQuery("#xy").val()==o["bmtddx"]){
                    content+='<div class="active-item row">';
                    //content+='<img src="'+o['fjpath']+'" class="img-responsive"></div>';
                    //content+='<img src="'+ (o['fjpath'] == null ? 'default_dekt.jpg' : o['fjpath'])+ '" style="width:170px;height:130px"></div>';
                    content+='<div class="col-md-8">';
                    content+='<div class="pic"><img src="'+o['hb']+'" style="width:97px;height:127px;z-index: 1"  /></div>';
                    content+='<div class="content">';
                    content+='<p class="title">';
                    content+=o['hdmc']+'��������';
                    content+='</p>';

                    content+='<div>';
                    content+='<div class="tag"  style="display: inline"><span>'+o['hdlxmc']+'</span></div>';
                    if(o['hdbqmc'] != "" && o['hdbqmc'] != null){
                        var arr = o['hdbqmc'].split(",");
                        for(var x=0;x<arr.length;x++){
                            content+='<div class="tag" style="display: inline;margin-left: 2px;"><span>'+arr[x]+'</span></div>';
                        }
                    }
                    if(o['nlbqmc'] != "" && o['nlbqmc'] != null){
                        var arr = o['nlbqmc'].split(",");
                        for(var x=0;x<arr.length;x++){
                            content+='<div class="tag" style="display: inline;margin-left: 2px;"><span>'+arr[x]+'</span></div>';
                        }
                    }
                    content+='</div>';

                    content+='<div class="detail">';
                    content+='<div>�ʱ��:'+o['hdkssj']+'~'+o['hdjssj']+'</div>';
                    if(o['bmkssj'] == null || o['bmjssj'] == null){
                        content+='<div>������ʼʱ��:</div>';
                    }else{
                        content+='<div>������ʼʱ��:'+o['bmkssj']+'��'+o['bmjssj']+'</div>';
                    }
                    content+='<div>��ص�:'+o['hddd']+'</div>';
                    content+='</div>';
                    content+='</div>';
                    content+='</div>';
                    content+='<div class="col-md-4">';
                    if(o['bmsf']=='0'){
                        content+='<button type="button" class="btn btn-primary grey-bg-btn" disabled="disabled" >���豨��</button>';
                    }else{
                        if(o['bmztmc'] == 'bm'){
                            content+='<button type="button" class="btn btn-primary"  onclick="bm(\''+o['hdid']+'\');">����</button>';
                            content+='<button type="button" class="btn btn-primary"  onclick="getBmlj(\''+o['hdid']+'\');">���ɱ�������</button>';
                        }else if(o['bmztmc'] == 'wks'){
                            content+='<button type="button" class="btn btn-primary grey-bg-btn" disabled="disabled" style="width:100px">����δ��ʼ</button>';
                        }else if(o['bmztmc'] == 'yjs'){
                            content+='<button type="button" class="btn btn-primary grey-bg-btn" disabled="disabled" style="width:100px">�����ѽ���</button>';
                        }
                    }
                    content+='<button type="button" class="btn btn-primary"  onclick="ckHdxx(\''+o['hdid']+'\');">�����</button>';
                    content+='</div>';
                    content+='</div>';
                }
            }else if(o["bmdx"]=="�ض���Ժ����"||o["bmdx"]=="�ض���Ժ����"){
                if(jQuery("#sy").val()==o["bmtddx"]){
                    content+='<div class="active-item row">';
                    //content+='<img src="'+o['fjpath']+'" class="img-responsive"></div>';
                    //content+='<img src="'+ (o['fjpath'] == null ? 'default_dekt.jpg' : o['fjpath'])+ '" style="width:170px;height:130px"></div>';
                    content+='<div class="col-md-8">';
                    content+='<div class="pic"><img src="'+o['hb']+'" style="width:97px;height:127px;z-index: 1" /></div>';
                    content+='<div class="content">';
                    content+='<p class="title">';
                    content+=o['hdmc']+'��������';
                    content+='</p>';

                    content+='<div>';
                    content+='<div class="tag"  style="display: inline"><span>'+o['hdlxmc']+'</span></div>';
                    if(o['hdbqmc'] != "" && o['hdbqmc'] != null){
                        var arr = o['hdbqmc'].split(",");
                        for(var x=0;x<arr.length;x++){
                            content+='<div class="tag" style="display: inline;margin-left: 2px;"><span>'+arr[x]+'</span></div>';
                        }
                    }
                    if(o['nlbqmc'] != "" && o['nlbqmc'] != null){
                        var arr = o['nlbqmc'].split(",");
                        for(var x=0;x<arr.length;x++){
                            content+='<div class="tag" style="display: inline;margin-left: 2px;"><span>'+arr[x]+'</span></div>';
                        }
                    }
                    content+='</div>';

                    content+='<div class="detail">';
                    content+='<div>�ʱ��:'+o['hdkssj']+'~'+o['hdjssj']+'</div>';
                    if(o['bmkssj'] == null || o['bmjssj'] == null){
                        content+='<div>������ʼʱ��:</div>';
                    }else{
                        content+='<div>������ʼʱ��:'+o['bmkssj']+'��'+o['bmjssj']+'</div>';
                    }
                    content+='<div>��ص�:'+o['hddd']+'</div>';
                    content+='</div>';
                    content+='</div>';
                    content+='</div>';
                    content+='<div class="col-md-4">';
                    if(o['bmsf']=='0'){
                        content+='<button type="button" class="btn btn-primary grey-bg-btn" disabled="disabled" >���豨��</button>';
                    }else{
                        if(o['bmztmc'] == 'bm'){
                            content+='<button type="button" class="btn btn-primary"  onclick="bm(\''+o['hdid']+'\');">����</button>';
                            content+='<button type="button" class="btn btn-primary"  onclick="getBmlj(\''+o['hdid']+'\');">���ɱ�������</button>';
                        }else if(o['bmztmc'] == 'wks'){
                            content+='<button type="button" class="btn btn-primary grey-bg-btn" disabled="disabled" style="width:100px">����δ��ʼ</button>';
                        }else if(o['bmztmc'] == 'yjs'){
                            content+='<button type="button" class="btn btn-primary grey-bg-btn" disabled="disabled" style="width:100px">�����ѽ���</button>';
                        }
                    }
                    content+='<button type="button" class="btn btn-primary"  onclick="ckHdxx(\''+o['hdid']+'\');">�����</button>';
                    content+='</div>';
                    content+='</div>';
                }
            }else{
                content+='<div class="active-item row">';
                //content+='<img src="'+o['fjpath']+'" class="img-responsive"></div>';
                //content+='<img src="'+ (o['fjpath'] == null ? 'default_dekt.jpg' : o['fjpath'])+ '" style="width:170px;height:130px"></div>';
                content+='<div class="col-md-8">';
                content+='<div class="pic"><img src="'+o['hb']+'" style="width:97px;height:127px;z-index: 1" /></div>';
                content+='<div class="content">';
                content+='<p class="title">';
                content+=o['hdmc']+'��������';
                content+='</p>';

                content+='<div>';
                content+='<div class="tag"  style="display: inline"><span>'+o['hdlxmc']+'</span></div>';
                if(o['hdbqmc'] != "" && o['hdbqmc'] != null){
                    var arr = o['hdbqmc'].split(",");
                    for(var x=0;x<arr.length;x++){
                        content+='<div class="tag" style="display: inline;margin-left: 2px;"><span>'+arr[x]+'</span></div>';
                    }
                }
                if(o['nlbqmc'] != "" && o['nlbqmc'] != null){
                    var arr = o['nlbqmc'].split(",");
                    for(var x=0;x<arr.length;x++){
                        content+='<div class="tag" style="display: inline;margin-left: 2px;"><span>'+arr[x]+'</span></div>';
                    }
                }
                content+='</div>';

                content+='<div class="detail">';
                content+='<div>�ʱ��:'+o['hdkssj']+'~'+o['hdjssj']+'</div>';
                if(o['bmkssj'] == null || o['bmjssj'] == null){
                    content+='<div>������ʼʱ��:</div>';
                }else{
                    content+='<div>������ʼʱ��:'+o['bmkssj']+'��'+o['bmjssj']+'</div>';
                }
                content+='<div>��ص�:'+o['hddd']+'</div>';
                content+='</div>';
                content+='</div>';
                content+='</div>';
                content+='<div class="col-md-4">';
                if(o['bmsf']=='0'){
                    content+='<button type="button" class="btn btn-primary grey-bg-btn" disabled="disabled" >���豨��</button>';
                }else{
                    if(o['bmztmc'] == 'bm'){
                        content+='<button type="button" class="btn btn-primary"  onclick="bm(\''+o['hdid']+'\');">����</button>';
                        content+='<button type="button" class="btn btn-primary"  onclick="getBmlj(\''+o['hdid']+'\');">���ɱ�������</button>';
                    }else if(o['bmztmc'] == 'wks'){
                        content+='<button type="button" class="btn btn-primary grey-bg-btn" disabled="disabled" style="width:100px">����δ��ʼ</button>';
                    }else if(o['bmztmc'] == 'yjs'){
                        content+='<button type="button" class="btn btn-primary grey-bg-btn" disabled="disabled" style="width:100px">�����ѽ���</button>';
                    }
                }
                content+='<button type="button" class="btn btn-primary"  onclick="ckHdxx(\''+o['hdid']+'\');">�����</button>';
                content+='</div>';
                content+='</div>';
            }

        }
        jQuery(listTbody).append(content);
        jQuery('.pic img').zoomify();//ͼƬ�Ŵ�
    }
}

//�����Ҳμ�div
function createHtmlForWcj(obj) {
	var listTbody = jQuery("#activity-list");
	listTbody.empty();
	//jQuery(listTbody).empty();
	if(obj.length < 1){
		jQuery(listTbody).html("<div><p align='center'>δ�ҵ��κμ�¼��</p></div>");
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
				jQuery("#pageno").val((obj[0]["rowindex"]-1)/jQuery("#pagesize").val()+1);//�ڼ�ҳ
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
			content+='<div class="pic"><img src="'+o['hb']+'" style="width:97px;height:127px;z-index: 1" /></div>';
			content+='<div class="content">';
			content+='<p class="title">';
			content+=o['hdmc']+'��������';
			content+='</p>';

            content+='<div>';
            content+='<div class="tag"  style="display: inline"><span>'+o['hdlxmc']+'</span></div>';
            if(o['hdbqmc'] != "" && o['hdbqmc'] != null){
                var arr = o['hdbqmc'].split(",");
                for(var x=0;x<arr.length;x++){
                    content+='<div class="tag" style="display: inline;margin-left: 2px;"><span>'+arr[x]+'</span></div>';
                }
            }
            if(o['nlbqmc'] != "" && o['nlbqmc'] != null){
                var arr = o['nlbqmc'].split(",");
                for(var x=0;x<arr.length;x++){
                    content+='<div class="tag" style="display: inline;margin-left: 2px;"><span>'+arr[x]+'</span></div>';
                }
            }
            content+='</div>';

			content+='<div class="detail">';
			content+='<div>�ʱ��:'+o['hdkssj']+'��'+o['hdjssj']+'</div>';
			content+='<div>������ʼʱ��:'+o['bmkssj']+'��'+o['bmjssj']+'</div>';
			content+='<div>��ص�:'+o['hddd']+'</div>';
			content+='</div>';
			content+='</div>';
			content+='</div>';
			content+='<div class="col-md-4">';
			//content+='<button type="button" class="btn btn-primary">����</button>';
			content+='<button id="pl_btn" type="button" class="btn btn-primary" onclick="pl(\''+ o["hdid"] +'\',\''+o['hdmc']+'\');">����</button>';
			//����Ǹ��˽׶�
			if(o['bmlx'] == '1' || o['bmlx'] == 1){
				//���״̬Ϊ��ͨ��
				if(o['shzt'] == 2 && o['hdbmzt'] == '0'){
					content+='<button type="button" class="btn btn-primary" onclick="ckJd(\''+o['hdid']+'\',\''+o['btgjdid']+'\',\''+o['prejdid']+'\')">'+o['btgjdmc']+'��˲�ͨ��</button>';
				}else if(o['shzt'] == 1 && o['hdbmzt'] == '0'){//���״̬ͨ��				
					if(o['jdsx'] > o['xsjdsx'] && (o['nextjdlx'] == '2' || o['nextjdlx'] == 2)){//�����һ��˽׶�Ϊ��ʦ�������(�ѽ�����һ�׶ε������)
						content+='<button type="button" class="btn btn-primary" onclick="ckJd(\''+o['hdid']+'\',\''+o['nextjdid']+'\',\''+o['prejdid']+'\')">'+o['nextjdmc']+'</button>';
					}else if(o['jdsx'] > o['xsjdsx'] && (o['nextjdlx'] == '1' || o['nextjdlx'] == 1)){//�����һ��˽׶�Ϊѧ���������(�ѽ�����һ�׶ε������)
						content+='<button type="button" class="btn btn-primary" onclick="jdtx(\''+o['nextjdid']+'\',\''+o['hdid']+'\');">'+o['nextjdmc']+'��д</button>';
					}else if(o['jdsx'] > o['xsjdsx'] && (o['nextjdlx'] == null || o['nextjdlx'] == "")){
						content+='<button type="button" class="btn btn-primary" onclick="ckJd(\''+o['hdid']+'\',\''+o['jdid']+'\',\''+o['jdid']+'\')">'+o['jdmc']+'</button>';
					}else{//���Ϊ��ǰ�׶ε������
						content+='<button type="button" class="btn btn-primary" onclick="jdtx(\''+o['xsjdid']+'\',\''+o['hdid']+'\');">'+o['xsjdmc']+'��д</button>';
					}
				}else if(o['shzt'] == 5 && o['hdbmzt'] == '0'){//���״̬Ϊ�����
					//content+='<button type="button" class="btn btn-primary" onclick="jdtx(\''+o['xsjdid']+'\',\''+o['hdid']+'\');">'+o['xsjdmc']+'�����</button>';
					if(o['jdsx'] > o['xsjdsx'] && (o['nextjdlx'] == '2' || o['nextjdlx'] == 2)){//�����һ��˽׶�Ϊ��ʦ�������(�ѽ�����һ�׶ε������)
						content+='<button type="button" class="btn btn-primary" onclick="ckJd(\''+o['hdid']+'\',\''+o['nextjdid']+'\',\''+o['prejdid']+'\')">'+o['nextjdmc']+'</button>';
					}else if(o['jdsx'] > o['xsjdsx'] && (o['nextjdlx'] == '1' || o['nextjdlx'] == '1')){//�����һ��˽׶�Ϊѧ���������(�ѽ�����һ�׶ε������)
						content+='<button type="button" class="btn btn-primary" onclick="jdtx(\''+o['nextjdid']+'\',\''+o['hdid']+'\');">'+o['nextjdmc']+'��д</button>';
					}else{//���Ϊ��ǰ�׶ε������
						content+='<button type="button" class="btn btn-primary" onclick="jdtx(\''+o['xsjdid']+'\',\''+o['hdid']+'\');">'+o['xsjdmc']+'��д</button>';
					}
				}else if(o['shzt'] == 3 && o['hdbmzt'] == '0'){//���״̬Ϊ�˻�
					content+='<button type="button" class="btn btn-primary" onclick="jdtx(\''+o['xsjdid']+'\',\''+o['hdid']+'\');">'+'���˻أ�'+o['xsjdmc']+'������д</button>';
				}else{
//					else if(!!o['jdmc'] && (o['jdlx'] == '2' || o['jdlx'] == 2)){//���ڵ�һ�׶Σ��ҵ�һ�׶�Ϊ��ʦ
//						content+='<button type="button" class="btn btn-primary" disabled="disabled">'+o['jdmc']+'</button>';
//					}
					if(o['hdbmzt'] == '1' || o['hdbmzt'] == '1'){//�δ��ʼ
						if(o['hdbmshzt'] == '1' || o['hdbmshzt'] == '1'){//�������ͨ��
							if(o['hdpp'] == '0' || o['hdpp'] == '0'){
								content+='<button type="button" class="btn btn-primary" disabled="disabled">��Ʊ��</button>';
							}else{
								content+='<button type="button" class="btn btn-primary" disabled="disabled">��ͨ�����δ��ʼ��</button>';
							}
						}else if(o['hdbmshzt'] == '5' || o['hdbmshzt'] == '5'){//�����
							content+='<button type="button" class="btn btn-primary" disabled="disabled">�����</button>';
						}else{
							content+='<button type="button" class="btn btn-primary"  onclick="cxbm(\''+o['hdid']+'\');">���±���</button>';
						}
					}else if(o['hdbmzt'] == '2' || o['hdbmzt'] == '2'){//��ѽ���
						content+='<button type="button" class="btn btn-primary" disabled="disabled">��ѽ���</button>';
					}else{//��ѿ�ʼ
						if(o['hdbmshzt'] == '1' || o['hdbmshzt'] == '1'){//�������ͨ��
							if(o['hdpp'] == '0' || o['hdpp'] == '0'){
								content+='<button type="button" class="btn btn-primary" disabled="disabled">��Ʊ</button>';
							}else{
								if(o['hdkclx'] == '������' || o['hdkclx' == '������']){//�����൥�׶λ
									content+='<button type="button" class="btn btn-primary" disabled="disabled">��ѿ�ʼ</button>';
								}else{
									if(!!o['jdmc']){//���ڵ�һ�׶Σ��ҵ�һ�׶�Ϊѧ��
										content+='<button type="button" class="btn btn-primary" onclick="jdtx(\''+o['firstjdid']+'\',\''+o['hdid']+'\');">'+o['firstjdmc']+'��д</button>';
									}else if(!!o['jdmc'] && (o['jdlx'] == '2' || o['jdlx'] == 2)){//���ڵ�һ�׶Σ��ҵ�һ�׶�Ϊ��ʦ
										content+='<button type="button" class="btn btn-primary" disabled="disabled">'+o['jdmc']+'</button>';
									}else{
										content+='<button type="button" class="btn btn-primary" disabled="disabled">��ѿ�ʼ</button>';
									}
								}
							}
						}else if(o['hdbmshzt'] == '5' || o['hdbmshzt'] == '5'){//�����
							content+='<button type="button" class="btn btn-primary" disabled="disabled">���δͨ��</button>';
						}else{
							content+='<button type="button" class="btn btn-primary" disabled="disabled">���δͨ��</button>';
						}
					}
					
				}
			}else{//�������ӽ׶�
				content+='<button id="pl_btn" type="button" class="btn btn-primary" onclick="ckdw(\''+ o["hdid"] +'\',\''+o['dwid']+'\');">������Ϣ</button>';
				if(o['zdshzt'] == 2 && o['hdbmzt'] == '0'){
//					content+=o['xszdjdmc']+'��˲�ͨ��';
					content+='<button type="button" class="btn btn-primary" onclick="ckJdzd(\''+o['hdid']+'\',\''+o['zdbtgjdid']+'\',\''+o['zdprejdid']+'\')">'+o['zdbtgjdmc']+'��˲�ͨ��</button>';
				}else if(o['zdshzt'] == 1 && o['hdbmzt'] == '0'){//���״̬ͨ��				
					if(o['jdsx'] > o['xszdjdsx'] && (o['zdnextjdlx'] == '2' || o['zdnextjdlx'] == 2)){
						content+='<button type="button" class="btn btn-primary" onclick="ckJdzd(\''+o['hdid']+'\',\''+o['zdnextjdid']+'\',\''+o['zdprejdid']+'\')">'+o['zdnextjdmc']+'</button>';
					}else if(o['jdsx'] > o['xszdjdsx'] && (o['zdnextjdlx'] == '1' || o['zdnextjdlx'] == 1)){//��ڵ������ӽڵ㣨��ʾ�ѽ���һ�����̣�
						if(o['dwzw'] == 1 || o['dwzw'] == '1'){//���Ϊ�ӳ�ְ��
							content+='<button type="button" class="btn btn-primary" onclick="zdjdtx(\''+o['zdnextjdid']+'\',\''+o['hdid']+'\');">'+o['zdnextjdmc']+'��д</button>';
						}else{
							content+='<button type="button" class="btn btn-primary" disabled="disabled">'+o['zdnextjdmc']+'</button>';
						}
					}else if(o['jdsx'] > o['xsjdsx'] && (o['nextjdlx'] == null || o['nextjdlx'] == "")){
						content+='<button type="button" class="btn btn-primary" onclick="ckJdzd(\''+o['hdid']+'\',\''+o['jdid']+'\',\''+o['jdid']+'\')">'+o['jdmc']+'</button>';
					}else{//���Ϊ��ǰ�׶������
						content+='<button type="button" class="btn btn-primary" onclick="zdjdtx(\''+o['xszdjdid']+'\',\''+o['hdid']+'\');">'+o['xszdjdmc']+'��д</button>';
					}
				}else if(o['zdshzt'] == 5 && o['hdbmzt'] == '0'){
					if(o['jdsx'] > o['xszdjdsx'] && (o['zdnextjdlx'] == '2' || o['zdnextjdlx'] == 2)){
						content+='<button type="button" class="btn btn-primary" onclick="ckJdzd(\''+o['hdid']+'\',\''+o['zdnextjdid']+'\',\''+o['zdprejdid']+'\')">'+o['zdnextjdmc']+'</button>';
					}else if(o['jdsx'] > o['xszdjdsx'] && (o['zdnextjdlx'] == '1' || o['zdnextjdlx'] == '1')){//��ڵ������ӽڵ㣨��ʾ�ѽ���һ�����̣�
						if(o['dwzw'] == 1 || o['dwzw'] == '1'){//���Ϊ�ӳ�ְ��							
							content+='<button type="button" class="btn btn-primary" onclick="zdjdtx(\''+o['zdnextjdid']+'\',\''+o['hdid']+'\');">'+o['zdnextjdmc']+'��д</button>';
						}else{
							content+='<button type="button" class="btn btn-primary" disabled="disabled">'+o['zdnextjdmc']+'</button>';
						}
					}else{
						content+='<button type="button" class="btn btn-primary" onclick="zdjdtx(\''+o['xszdjdid']+'\',\''+o['hdid']+'\');">'+o['xszdjdmc']+'��д</button>';
					}				
				}else if(o['zdshzt'] == 3 && o['hdbmzt'] == '0'){//���״̬Ϊ�˻�
					if(o['dwzw'] == 1 || o['dwzw'] == '1'){						
						content+='<button type="button" class="btn btn-primary" onclick="zdjdtx(\''+o['xszdjdid']+'\',\''+o['hdid']+'\');">'+'���˻أ�'+o['xszdjdmc']+'������д</button>';
					}else{
						content+='<button type="button" class="btn btn-primary" onclick="ckJdzd(\''+o['hdid']+'\',\''+o['xszdjdid']+'\',\''+o['zdprejdid']+'\');">'+'���˻�'+o['xszdjdmc']+'</button>';
					}
				}else{
					if(o['hdbmzt'] == '1' || o['hdbmzt'] == '1'){//�δ��ʼ
						content+='<button type="button" class="btn btn-primary" disabled="disabled">�δ��ʼ</button>';
					}else if(o['hdbmzt'] == '2' || o['hdbmzt'] == '2'){//��ѽ���
						content+='<button type="button" class="btn btn-primary" disabled="disabled">��ѽ���</button>';
					}else{//�������
						if(o['hdkclx'] == '������' || o['hdkclx' == '������']){//�����൥�׶λ(���������ӻ��Ϊ��׶�)
							content+='<button type="button" class="btn btn-primary" disabled="disabled">��ѿ�ʼ</button>';
						}else{
							if(!!o['jdmc']){//���ڵ�һ�׶Σ��ҵ�һ�׶�Ϊѧ��
								if(o['dwzw'] == 1 || o['dwzw'] == '1'){	//�ӳ�						
									content+='<button type="button" class="btn btn-primary" onclick="zdjdtx(\''+o['firstjdid']+'\',\''+o['hdid']+'\');">'+o['firstjdmc']+'��д</button>';
								}else{
									content+='<button type="button" class="btn btn-primary" disabled="disabled">'+o['firstjdmc']+'</button>';
								}
							}else if(!!o['jdmc'] && (o['jdlx'] == '2' || o['jdlx'] == 2)){//���ڵ�һ�׶Σ��ҵ�һ�׶�Ϊ��ʦ
								content+='<button type="button" class="btn btn-primary" disabled="disabled">'+o['jdmc']+'</button>';
							}else{
								content+='<button type="button" class="btn btn-primary" disabled="disabled">��ѿ�ʼ</button>';
							}
						}
						
					}
				}
			}
			content+='<button type="button" class="btn btn-primary"  onclick="ckHdxx(\''+o['hdid']+'\',\''+o['bmlx']+'\');">�����</button>';
			content+='</div>';
			content+='</div>';
		}
		jQuery(listTbody).append(content);
        jQuery('.pic img').zoomify();//ͼƬ�Ŵ�
	}	
}
function cxbm(hdid) {
    var url = "hdgl_hdxx.do?method=bm&hdid="+hdid+"&lx=grbm";//���˱���
    showDialog("�������", 800, 550, url);
}
//������Ϣ
function ckdw(hdid,dwid){
	var url = "hdgl_hdxx.do?method=ckdw&hdid="+ hdid +"&dwid="+ dwid;
	showDialog("�ҵĶ���", 400, 300, url);
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

//�鿴��ӽ׶�
function ckJdzd(hdid,jdid,prejdid){
	var url = "hdgl_hdxx.do?method=ckjdzd&hdid="+hdid+"&jdid="+jdid+"&prejdid="+prejdid;
	window.location.href=url;
	scrollTop();
}

//�׶���д
function jdtx(jdid,hdid){
	var url = "hdgl_hdxx.do?method=jdtx&hdid="+hdid+"&jdid="+jdid;
	window.location.href=url;
	scrollTop();
}

//��ӽ׶���д
function zdjdtx(jdid,hdid){
	var url = "hdgl_hdxx.do?method=zdjdtx&hdid="+hdid+"&jdid="+jdid;
	window.location.href=url;
	scrollTop();
}

//�����
function bm(hdid){
	var url = "hdgl_hdxx.do?method=getHdxx&hdid="+hdid;
	window.location.href=url;
	scrollTop();
}
function getBmlj(hdid) {
	var url = "hdgl_hdxx.do?method=getBmEwm&hdid="+hdid;
    showDialog("������ά������", 360, 200, url);
}
function bmview(){
    var hdid = jQuery("#hdid").val();
    jQuery.post("hdgl_hdxx.do?method=geBmCheck",{hdid:hdid},function(data){
    	if(data["message"] == "true"){
            var url = "hdgl_hdxx.do?method=bm&hdid="+hdid+"&lx=grbm";//���˱���
            showDialog("�������", 800, 550, url);
		} else {
            jQuery("#msgContent").html(data["message"]);
            jQuery('#msgModal').modal('show');
    		// showAlert(data["message"]);
		}
	},'json');
}
function zdbmview(){
    var hdid = jQuery("#hdid").val();
    jQuery.post("hdgl_hdxx.do?method=gezdBmCheck",{hdid:hdid},function(data){
        if(data["message"] == "true"){
            showDialog('��ѡ���Ա', 800, 500, 'xsxx_xsgl.do?method=selectDy&hdid=' + hdid);
        } else {
            showAlert(data["message"]);
        }
    },'json');

}
/**
 * ѡ�����ҳ�� ҳǩѡ��
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
//������Ŷ�
function createGroup(){
	var url = 'hdgl_hdxx.do?method=createGroup';
    ajaxSubFormWithFun("demoForm", url, function(data) {
        if(data["message"]=="����ɹ���"){
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

        }else if(data["message"]=="�������������������ޣ�"){
			showAlert(data["message"],{},{"clkFun":function(){
				iFClose();
			}});
		}else{
			showAlertDivLayer(data["message"]);
        }
    });
}

//��������
function bmForGroup(){
	var hdid = jQuery("#hdid").val();
    jQuery.post("hdgl_hdxx.do?method=gezdBmCheck",{hdid:hdid},function(data){
        if(data["message"] == "true"){
            var url = "hdgl_hdxx.do?method=getDwList&hdid="+hdid;
            var title = "����б�ѡ��";
            showDialog(title, 600, 500, url);
        } else {
            showAlert(data["message"]);
        }
    },'json');

}

function jrdwSave(){
    var url = "hdgl_hdxx.do?method=jrdw";
    ajaxSubFormWithFun("demoForm", url, function(data) {
        if(data["message"]=="����ɹ���"){
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

//��������
function saveBm(){
    var url = "hdgl_hdxx.do?method=saveBm";
	ajaxSubFormWithFun("demoForm", url, function(data) {
		if(data["message"]=="����ɹ���"){
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
            jQuery("#msgContent").html(data["message"]);
            jQuery('#msgModal').modal('show');
			// showAlertDivLayer(data["message"]);
		}
	});
}

//�ύ�׶���д
function tj(){
	// showConfirmDivLayer("��ȷ��Ҫ����ò�����", {
	// 	"okFun" : function() {
			var url = "hdgl_hdxx.do?method=saveStudentStage";
			ajaxSubFormWithFun("hdxxForm", url, function(data) {
				if(data["message"]=="����ɹ���"){
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

//����
function pl(hdid,hdmc){
	var title = "����";
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
        fileElementId : "imgUpload", //file��id
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