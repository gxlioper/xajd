// ��ѯ�ѷ�����б�
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

//��Ա�б�
function searchRy(cxzt){
	if(cxzt == '1'){
		jQuery("#currentPage").val("1");
	}
	var url = "zyhdry.do?method=searchRy";
	ajaxSubFormWithFun("qnzyryForm", url, function(data) {
			createHtmlForRy(data);
	});
}

//�ϴ�
function sc(){
	jQuery("#upload").click();
}

//����
function fh(){
	window.location.href="zyhd.do?method=yfbhdList";
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
						showAlertDivLayer("ͼƬ�����������ϴ���");
					}else{
						showAlertDivLayer("ͼƬ��ʽ���ԣ��������ϴ���");
					}
				}
				bindUpload();
			}
		})
	})
}

//�޸ķ��������
function updateBc(){
	editor.sync();
	var ids;
	var url;
	var message;
	
	ids = "hdmc-fwlx-hddd-fwdx-xdrs-hdkssj-xm-zzbm-bmjzsj-jbfwgs-hdfzrlxfs";
	//ids = "hdmc-fwlx-hddd-fwdx-xdrs-hdkssj-xm-zzbm-fjpath-hdfzrlxfs";
	if(check(ids) == false){
		showAlertDivLayer("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	if(jQuery("#zgh").val() == null || jQuery("#zgh").val() == ''){
		showAlertDivLayer("������ĸ����˲����ڣ����������룡");
		return false;
	}
	
	var checkboxs = jQuery("[name='xydms']:checked");
	if(checkboxs.length < 1){
		showAlertDivLayer("�빴ѡ����ѧԺ��");
		return false;
	}
	var html=editor.html();
	if(html==null||html==""){
		showAlertDivLayer("����д����飡");
		return false;
	}
	url = 'zyhd.do?method=updateBcHdfb';
	message = jQuery("#oldZgh").val() == jQuery("#zgh").val() ? "��ȷʵҪ������" : "ע�⣺���Ѹ����˸����ˣ�������ԭ�и����˲���ά������Ŀ����Ա���Ƿ��޸ģ�";
	showConfirmDivLayer(message, {
		"okFun" : function() {				
			ajaxSubFormWithFun("qnzyhdForm", url, function(data) {
				if(data["message"]=="����ɹ���"){
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

//�������
function bgfb(type){
	var message;
	var result;
	if(type == '0'){
		message = "ȷ��ȡ����ǰ��Ŀ�ķ�����";
		result = "ȡ�������ɹ���";
	}else{
		message = "ȷ��Ҫ������ǰ��Ŀ��";
		result = "�����ɹ���";
	}
	showConfirmDivLayer(message, {
		"okFun" : function() {
			jQuery.post("zyhd.do?method=bgFb", 
			{
			  hdid:jQuery("#hdid").val(),
			  fbzt:type
			},
			function(data) {
				if(data["message"] == "����ɹ���"){
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

//�л�����״̬
function showFbzt(){
	if(jQuery("#fbzt").val() == '1'){
		jQuery("#cancel-btn").css("display","");
		jQuery("#confirm-btn").css("display","none");
	}else{
		jQuery("#cancel-btn").css("display","none");
		jQuery("#confirm-btn").css("display","");
	}
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

//�������
function bcsh(bmzt){
	var checkboxs = jQuery("[name='ids']:checked");
	if(checkboxs.length<1){
		showAlertDivLayer("��ѡ��Ҫ������ѧ����");
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
		//showAlertDivLayer("��ѧ����ʱ���ύ����ʱ����ˣ���������˲�ͨ����");
		showAlertDivLayer("����ʱ����ˣ���������˲�ͨ����");
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
//			showAlert("��ʱ����ˣ���������˲�ͨ����");
//			return false;
//		}
//	}
	showConfirmDivLayer("��ȷ��Ҫ����ò�����", {
		"okFun" : function() {
			var url = "zyhdry.do?method=bcBmPlsh&bmzt="+bmzt;
			ajaxSubFormWithFun("qnzyryForm", url, function(data) {
				if(data["message"]=="����ɹ���"){
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
//		if(data["message"]=="����ɹ���"){
//   		 showAlert(data["message"],{},{"clkFun":function(){
//   			searchRy();
//   			jQuery("#qx").attr("checked",false);
//		 }});
//	   	 }else{
//	   		 showAlert(data["message"]);
//	   	 }
//	});
}

//�ѷ�����б�
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
		var content = "<ul>";
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
			content+='<li><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">';
			//content+='<img src="'+o['fjpath']+'" class="img-responsive"></div>';
			content+='<img src="'+ (o['fjpath'] == null ? 'default_dekt.jpg' : o['fjpath'])+ '" style="width:170px;height:130px" id="img_'+o["hdid"]+'"></div>';
			content+='<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">';
			content+='<h4 class="title">���״̬��'+o['shztmc']+'</h4>';
			content+='<h4 class="title">'+o['hdmc']+'</h4>';
			content+='<div class="row activity-num">';
			content+='<div class="col-lg-5 col-md-5 col-sm-6 col-xs-12"><p>�����ˣ�'+o['fzrxm']+(o['hdfzrlxfs'] != null ? '/'+o['hdfzrlxfs']+'</p></div>' : '</p></div>');
			content+='<div class="col-lg-5 col-md-4 col-sm-6 col-xs-8"><p>�ѱ�/�޶���������<span class="num">'+o['ybrs']+'</span>/'+o['xdrs']+'��</p></div>';
			content+='<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12"><p>��֯���ţ�'+o['zzbm']+'</p></div>';
			content+='<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12"><p>�ʱ�䣺'+o['hdkssj'];
			if(o['hdjssj'] != null){
				content+=' ��  ';
				content+=o["hdjssj"];
			}
			content+='</p></div>';
			content+='<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12"><p>������ֹʱ�䣺'+o['bmjzsj']+'</p></div>';
			content+='<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12"><p>����ѧԺ��'+o['xymc']+'</p></div>';
			content+='<div class="col-lg-5 col-md-5 col-sm-6 col-xs-12"><p>��ص㣺'+o['hddd']+'</p></div>';
//			content+='<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12">����״̬��'+o['fbztmc']+'</div>';
			content+='<div class="col-lg-5 col-md-5 col-sm-6 col-xs-12">';
			if(o['shzt'] == '1' ){
				content+='<input type="file" name="file" id="upload_'+ o["hdid"] +'" style="display: none" onchange="sctp(\''+o["hdid"]+'\')" />';
				content+='<button type="button" class="btn btn-primary green-bg-btn btn-xs" onclick="sc(\''+o['hdid']+'\');return false;" style="width:100px">�ϴ�����</button>';
			}else if(o['shzt'] == '2'){
				
			}else{				
				content+='<button type="button" class="btn btn-primary green-bg-btn btn-xs" onclick="xg(\''+o['hdid']+'\');return false;" style="width:100px">�޸Ļ</button>';
			}
			//content+='<span><a href="javascript:void(0);return false;" onclick="xg(\''+o['hdid']+'\');return false;">�޸Ļ</a></span>';
			if(o['ryzt'] == 'fzr' && o['shzt'] != '2'){
				content+='<button type="button" style="margin-left:30px" class="btn btn-primary red-bg-btn btn-xs" onclick="rygl(\''+o['hdid']+'\');return false;" style="width:100px">��������</button>';
				//content+='<span style="margin-left:30px"><a href="javascript:void(0);return false;" onclick="rygl(\''+o['hdid']+'\');return false;">��������</a></span>';
			}	
			content+='</div></li>';
		}
		content+="</ul>";
		jQuery(listTbody).append(content);
	}	
}

//�޸Ļ
function xg(hdid){
	var url = "zyhd.do?method=updateHdfb&hdid="+hdid;
	window.location.href=url;
	scrollTop();
}

//��Ա���� 
function rygl(hdid){
	var url = "zyhdry.do?method=rygl&hdid="+hdid;
	window.location.href=url;
	scrollTop();
}



//�Զ����
function fzrAutoComplete(){
	// ����
	//ȡ�����ݱ�xg_pjpy_new_pjxmb; �ֶΣ�xmmc																							
	var autoSetting = {
			dataTable:"view_xsfdyxx",
			dataField:"xm",
			dataFieldKey:"zgh",
			dataFieldKeyId:"zgh",
			scrollHeight:135										
	}
	// ģ��������������Ŀ���ơ�
	jQuery("#xm").setAutocomplete(autoSetting);
}

//�Զ���为������ϵ��ʽ
function lxfsAutoComplete(){
	
	var autoSetting = {
			dataTable:"view_xsfdyxx",
			dataField:"xm",
			dataFieldKey:"hdfzrlxfs",
			dataFieldKeyId:"hdfzrlxfs",
			scrollHeight:180										
	}
	// ģ��������������Ŀ���ơ�
	jQuery("#xm").setAutocomplete(autoSetting);
}

//��֤
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

//�޸Ļ������֤����
function checkRs(obj){
	if(obj.value == null || obj.value == '' || obj.value == 0){
		showAlertDivLayer("�޶������������0!", {}, {
			"clkFun" : function() {
				obj.focus();
			}
		})
		return;
	}
	var tgrs = parseInt(jQuery("#tgrs").val());
	if((parseInt(jQuery(obj).val())) < tgrs){
		showAlertDivLayer("����"+tgrs+"�˱���ͨ��������������!", {}, {
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


//�ϴ�
function sc(hdid){
	jQuery("#upload_"+hdid).click();
}


//�ϴ�ͼƬ
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
				showAlertDivLayer("�ϴ��ɹ�");
			}else{
				if(data["message"] == "oversize"){
					showAlertDivLayer("ͼƬ�ߴ�����������ϴ���");
				}else{
					showAlertDivLayer("ͼƬ��ʽ���ԣ��������ϴ���");
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

//�ϴ�
function scForUpate(){
	jQuery("#upload").click();
}

