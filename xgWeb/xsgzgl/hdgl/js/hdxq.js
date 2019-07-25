// ��ѯ������б�
function searchHd(cxzt){
	if(cxzt == '1'){
		jQuery("#currentPage").val("1");
	}
	var url = "hdgl_hdxq.do?method=hdxqList&type=query";
	ajaxSubFormWithFun("hdxqForm", url, function(data) {
			createHtml(data);
	});
}

function searchRs(){
	var map = getSuperSearch();
	map['hdid'] = jQuery("#hdid").val();
	jQuery("#dataTable").reloadGrid(map);
}

//��Ա�б�
function searchRy(cxzt){
	if(cxzt == '1'){
		jQuery("#currentPage").val("1");
	}
	var url = "hdgl_hdxq.do?method=getHdcyList&type=query";
	ajaxSubFormWithFun("hdxqForm", url, function(data) {
			createHtmlForRy(data);
	});
}
function getBmlj(hdid) {
	var url = "hdgl_hdxx.do?method=getBmEwm&hdid="+hdid;
	showDialog("������ά������", 360, 200, url);
}
//��Ա�б�
function searchPl(cxzt){
	if(cxzt == '1'){
		jQuery("#currentPage").val("1");
	}
	var url = "hdgl_hdxq.do?method=getHdplList&type=query";
	ajaxSubFormWithFun("hdxqForm", url, function(data) {
			createHtmlForPl(data);
	});
}
//�鿴������Ϣ
function ckbmxx(obj){
	var xh = jQuery(obj).parents("tr").children("td").children("input[name='ids']").val();
	var ids = new Array();
	ids[0] = xh;
    var url = "hdgl_hdxq.do?method=sh&ids="+ids+"&bmlx="+jQuery("#bmlx").val()+"&cz=ck&hdid="+jQuery("#hdid").val();
    showDialog("������Ϣ", 800, 550, url);
}
function sh(){
    var checkboxs = jQuery("[name='ids']:checked");
    if(checkboxs.length<1){
        showAlertDivLayer("��ѡ��Ҫ������ѧ����");
        return false;
    } else if(checkboxs.length == 1){
        var url = "hdgl_hdxq.do?method=sh&ids="+checkboxs.val()+"&bmlx="+jQuery("#bmlx").val()+"&cz=sh&hdid="+jQuery("#hdid").val();
        showDialog("�������", 800, 550, url);
    } else {
    	var ids = "";
    	for(var i=0;i<checkboxs.length;i++){
            ids+=checkboxs.eq(i).val();
            if(i<checkboxs.length-1){
                ids+=",";
			}
		}
        var url = "hdgl_hdxq.do?method=plsh&id="+ids+"&bmlx="+jQuery("#bmlx").val();
        showDialog("�������", 500, 300, url);
	}
}
function plshSave(shzt){
    var url = "hdgl_hdxq.do?method=plsh&type=save&shzt="+shzt;
    ajaxSubFormWithFun("demoForm", url, function(data) {
        if(data["message"]=="����ɹ���"){
            showAlert(data["message"],{},{"clkFun":function(){
                if (parent.window){
                    if(frameElement.api){
                        var api = frameElement.api,W = api.opener;
                        W.searchRy('1');
                        closeDialog();
                    } else {
                        iFClose();
                    }
                }
            }});
        }else{
            showAlert(data["message"]);
        }
    });
}
//�������
function bcsh(shzt){
	var url = "hdgl_hdxq.do?method=saveShzt&shzt="+shzt;
	ajaxSubFormWithFun("demoForm", url, function(data) {
		if(data["message"]=="����ɹ���"){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
                if (parent.window){
                    if(frameElement.api){
                        var api = frameElement.api,W = api.opener;
                        W.searchRy('1');
                        W.jQuery("#qx").attr("checked",false);
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

//��Ա�б�չʾ
function createHtmlForRy(obj) {
	var listTbody = jQuery("#ryTbody");
	jQuery(listTbody).empty();
	if(obj.length < 1){
		jQuery(listTbody).html("<tr align='center'><td colspan='8'>δ�ҵ��κμ�¼��</td></tr>");
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
			content+="<tr><td><input type='checkbox' value='"+o["sqid"]+"' name='ids'/></td>";
			content+="<td name='xh'><a href='javascript:void(0)' onclick='ckbmxx(this)'>"+o['xh']+"</a></td>";
			content+="<td>"+o["xm"]+"</td>";
			content+="<td>"+o["xymc"]+"</td>";
			content+="<td>"+o["bmsj"]+"</td>";
			if(jQuery("#bmlx").val() == "0"){//��ӱ���
                content+="<td>"+o["dwid"]+"</td>";
                content+="<td>"+o["cymc"]+"</td>";
			}
			content+="<td>"+o["shztmc"]+"</td>";
		}
		jQuery(listTbody).append(content);
	}
	
}

function createHtmlForPl(obj){
	var listTbody = jQuery("#plContent");
	jQuery(listTbody).empty();
	jQuery("#pls").empty();
	if(obj.length < 1){
		jQuery(listTbody).html("<tr align='center'><td colspan='8'>δ�ҵ��κμ�¼��</td></tr>");
		jQuery("#pls").empty();
		jQuery("#total").text(0);
		jQuery("#pageno").val(0);
		jQuery("#pageCount").text(0);
	}else{
		jQuery("#pls").append("<label class='m-l-25'>���ۣ�</label><div class='sc_num'><span>"+obj.length+"</span></div>");
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
			content+="<tr>";
			content+="<td>";
			//content+="<img src='assets/images/user.png'/>";
			content+="<div class='title'>"+o["plrxm"];
			if(!!o["hfrxm"]){
				content+="�ظ�:"+o["hfrxm"];
			}else{
				content+="</div>";
			}
			content+="<div class='message'><a href ='javascript:void(0)' onclick='getPlInfo(\""+o['plid']+"\")'>"+o["plnrr"]+"</a></div>";
			content+="</td>";
			content+="<td>"+o["dzss"]+"</td>";
			content+="<td>"+o["plsj"]+"</td>";
			content+="<td><a href='#' onclick='delPl(\""+o['plid']+"\")'><i class='glyphicon glyphicon-trash'></i></a><a href='#' id='reply' onclick='reply(\""+o['plid']+"\");'><i class='glyphicon glyphicon-comment'></i></a><a href='#' id='dz' onclick='dz(\""+o['plid']+"\");'><i class='glyphicon glyphicon-thumbs-up'></i></a></td>";
			content+="</tr>";
		}
		jQuery(listTbody).append(content);
	}
}

//��ȡ������Ϣ
function getPlInfo(plid){
	var title = "�鿴����";
	var hdid = jQuery("#hdid").val();
	var url = "hdgl_hdxq.do?method=ckPl&plid="+plid;
	showDialog(title, 500, 300, url);
}

//ɾ������
function delPl(plid){
	var url = "hdgl_hdxq.do?method=delPl";
	var hdid = jQuery("#hdid").val();
	showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
		"okFun" : function() {
			jQuery.post(url, {plid:plid},
					function(data) {
						showAlertDivLayer(data["message"]);
						plgl(hdid);
					}, 'json');
		}
	});
}

//�ظ�����
function reply(plid){
	var title = "�ظ�";
	var hdid = jQuery("#hdid").val();
	var url = "hdgl_hdxq.do?method=plReply&hdid="+hdid+"&plid="+plid;
	showDialog(title, 600, 500, url);
}

//����
function dz(plid){
	var url = "hdgl_hdxq.do?method=dz&plid="+plid;
	showConfirmDivLayer("�Ƿ�Ҫ������", {
		"okFun" : function() {
			jQuery.post(url, {plid:plid},
					function(data) {
					//showAlertDivLayer(data["message"]);
					if(data['message'] == '����ɹ���'){
						showAlertDivLayer('���޳ɹ�!');
						searchPl('1');
					}
					}, 'json');
		}
	});
}


//����
function fh(){
	window.location.href="hdgl_hdxq.do?method=hdxqList";
	scrollTop();
	
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

//�����������ߡ�����ٰ�div
function createHtml(obj) {
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
			content+='<div class="active-item row">';
			//content+='<img src="'+o['fjpath']+'" class="img-responsive"></div>';
			//content+='<img src="'+ (o['fjpath'] == null ? 'default_dekt.jpg' : o['fjpath'])+ '" style="width:170px;height:130px"></div>';
			content+='<div class="col-md-8">';
			content+='<div class="pic"><img src="'+o['hb']+'" style="width:97px;height:127px;"/></div>';
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
            content+='</div>';
			content+='<div class="detail">';
			content+='<div>�ʱ��:'+o['hdkssj']+'��'+o['hdjssj']+'</div>';
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
			content+='<button type="button" class="btn btn-primary" onclick="hdxq(\''+o['hdid']+'\',\''+o['bmlx']+'\')">����</button>';
			content+='</div>';
			content+='</div>';
		}
		jQuery(listTbody).append(content);
	}	
}

//�����
function hdxq(hdid,bmlx){
	window.location.href="hdgl_hdxq.do?method=getHdInfo&hdid="+hdid+"&bmlx="+bmlx;
	scrollTop();
}

//��ȡ���Ϣ
function getHdInfo(){
	var url = "hdgl_hdxq.do?method=getHdxq&hdid="+jQuery("#hdid").val()+"&bmlx="+jQuery("#bmlx").val();
//	window.location.href=url;
	//scrollTop();
	jQuery("#content_hdxq").empty();
	jQuery("#content_bmgl").empty();
	jQuery("#content_plgl").empty();
	jQuery("#content_hdxq").load(url);
//	jQuery.ajax({
//		url:url,
//		dataType:'html',
//		success:function(data){
//			jQuery("#content_aa").empty();
//			jQuery("#content_aa").append(data);
//		}
//	});
}

//��������
function bmgl(){
	var url = "hdgl_hdxq.do?method=getHdcyList&hdid="+jQuery("#hdid").val()+"&bmlx="+jQuery("#bmlx").val();
	jQuery("#content_bmgl").empty();
	jQuery("#content_hdxq").empty();
	jQuery("#content_plgl").empty();
	jQuery("#content_bmgl").load(url);
}

//���۹���
function plgl(){
	var url = "hdgl_hdxq.do?method=getHdplList&hdid="+jQuery("#hdid").val();
	jQuery("#content_bmgl").empty();
	jQuery("#content_hdxq").empty();
	jQuery("#content_plgl").empty();
	jQuery("#content_plgl").load(url);
	//jQuery("#content_bmgl").append("<div class='tab-content col-sm-12 p-0'><div class='col-sm-7 num-list p-l-0 m-15-0'><label style='color: red;'>���ܿ����С�����</label></div></div>");
}


function createHtmlForWcj(obj) {
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
			content+='<div class="active-item row">';
			//content+='<img src="'+o['fjpath']+'" class="img-responsive"></div>';
			//content+='<img src="'+ (o['fjpath'] == null ? 'default_dekt.jpg' : o['fjpath'])+ '" style="width:170px;height:130px"></div>';
			content+='<div class="col-md-8">';
			content+='<div class="pic"><img src="'+o['hb']+'" style="width:97px;height:127px;"/></div>';
			content+='<div class="content">';
			content+='<p class="title">';
			content+=o['hdmc']+'��������';
			content+='</p>';
			content+='<div class="tag"><span>'+o['hdlxmc']+'</span></div>';
			content+='<div class="detail">';
			content+='<div>�ʱ��:'+o['hdkssj']+'��'+o['hdjssj']+'</div>';
			content+='<div>������ʼʱ��:'+o['bmkssj']+'��'+o['bmjssj']+'</div>';
			content+='<div>��ص�:'+o['hddd']+'</div>';
			content+='</div>';
			content+='</div>';
			content+='</div>';
			content+='<div class="col-md-4">';
			content+='<button type="button" class="btn btn-primary">����</button>';
			content+='<button type="button" class="btn btn-primary">����</button>';
			content+='<button type="button" class="btn btn-primary" onclick="bm(\''+o['hdid']+'\');">�׶���д</button>';
			content+='</div>';
			content+='</div>';
		}
		jQuery(listTbody).append(content);
	}	
}


function searchCy(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}



//�����
function bm(hdid){
	jQuery("#content").empty();
	var url = "hdgl_hdxx.do?method=getHdxx&hdid="+hdid;
	jQuery("#content").load(url);
//	window.location.href=url;
//	scrollTop();
}


//��������
function saveBm(){
	var hdid = jQuery("#hdid").val();
	var url = "hdgl_hdxx.do?method=saveBm";
	
	showConfirmDivLayer("ȷ��Ҫ������", {
		"okFun" : function() {
			jQuery.post(url, {hdid : hdid},
				function(data) {
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						fh();
					}});
				}, 'json');
		}
	});
}

//�����
function add(){
	var url = "zyhd.do?method=addHdfb";
	window.location.href=url;
	scrollTop();
}

function scrollTop(){
	top.scrollTo(0, 0);
}


//�л�Tabҳ
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


//ȫѡ
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

//�������ۻظ�
function savePlReply(){
	if(!!jQuery("#plnr").val()){
		var hdid = jQuery("#hdid").val();
		var url = "hdgl_hdxq.do?method=savePlReply";
		ajaxSubFormWithFun("hdxxForm", url, function(data) {
			 if(data["message"]=="����ɹ���"){
	    		 showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							if(frameElement.api){
								var api = frameElement.api,W = api.opener;
								//jQuery(W.document).find('#search_go').click();
								W.plgl(hdid);
								closeDialog();
							} else {
								//jQuery(parent.window.document).find('#search_go').click();
								jQuery(parent.window).plgl(hdid);
								iFClose();
							}
						}
					}});
	    	 }else{
	    		 showAlert(data["message"]);
	    	 }
		});
	}else{
		showAlert("����д�������ݣ�");
		return false;
	}
}
/**
 * ��¼�
 */
function hdxj() {
    var url = "hdgl_hdgl.do?method=hdxj";
    var hdid = jQuery("#hdid").val();
    var hdzt = jQuery("#hdzt").val();

    if(hdzt == 0){
        showAlertDivLayer("�û���¼ܣ�");
    }else {
        showConfirmDivLayer("��ȷ��Ҫ�¼ܸû��", {
            "okFun" : function() {
                jQuery.post(url,{hdid:hdid},function (data) {
                    if(data["success"] == "true"){
                        jQuery("#hdzt").val(0);
                        jQuery("#btn_hdxj").text("���¼�");
                        jQuery("#btn_hdxj").prop("class","btn btn-primary grey-bg-btn").prop("disabled","disabled");
                    }else{
                        showAlertDivLayer(data["message"]);
                    }
                },"json");
            }
        });
    }
}

/**
 * ��Ʊ
 */
function getPpView(){
	var hdid = jQuery("#hdid").val();
	var bmlx = jQuery("#bmlx").val();
    var url = "hdgl_hdxq.do?method=getPpView&hdid="+hdid + "&bmlx=" + bmlx;
    var title = "��Ʊ";
    showDialog(title,790,550,url);
}
var DCGLBH = "hdgl_hdxq_hdcyList.do";
//�Զ��嵼�� ����
function exportConfig() {
    //dcglbh,�������ܱ��
    //DCCLBH�������ܱ��,ִ�е�������
    customExport(DCGLBH, exprotData);
}
/**
 * �����ѻ��Ʊ����Ա
 */
function exprotData() {
	var hdid = jQuery("#hdid").val();
	var bmlx = jQuery("#bmlx").val();
	debugger;
    //setSearchTj();//���ø߼���ѯ����
    var url = "hdgl_hdxq.do?method=exportData&hdid="+hdid+"&dcglbh=" + DCGLBH;//dcglbh,�������ܱ��
    //url = addSuperSearchParams(url);//���ø߼���ѯ����
    window.location.href = url;
    // jQuery("form").eq(0).attr("action", url);
    // jQuery("form").eq(0).submit();
}

/**
 *����ʱ�����
 */
function openBmsjDialog(){
	var hdid = jQuery("#hdid").val();
	var url = "hdgl_hdxq.do?method=bmsjglView&hdid="+hdid;
    var title = "��������";
    showDialog(title,790,400,url);
}