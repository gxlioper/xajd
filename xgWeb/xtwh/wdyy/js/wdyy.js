//�༭�ҵ�ҳ���Ӧ�����ݳ�ʼ��
function getInitialData(){
	var url = "wdyygl.do?method=getAppView";
	var JsonData = null;
	jQuery.ajax({
		type:'post',
		url:url,
		dataType:'json',
		//data:paraMap,
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		async: false,
		success:function(result){
			JsonData = result;
		}
	});
	return JsonData;
}

//�ҵ�Ӧ�ô����в�ѯʱ���ݻ�ȡ
function getQueryData(){
	var url = "wdyygl.do?method=QueryData";
	var gnmkmc = encodeURI(encodeURI(jQuery(".tc_s_input").val()));
	var mkfldm = encodeURI(encodeURI(jQuery(".cur").attr('data-mkfldm')));
	var htmlgnmkdm = new Array();
	var rows = jQuery(".connectedSortable > li > span");
	jQuery.each(rows, function(i, row) {
		htmlgnmkdm.push(encodeURI(encodeURI(jQuery(row).attr('data-gnmkdm'))));
	});
	var paraMap = {
			"gnmkmc":gnmkmc,
			"gnmkms":htmlgnmkdm,
			"mkfldm":mkfldm
	}
	var JsonData = null;
	jQuery.ajax({
		type:'post',
		url:url,
		dataType:'json',
		data:paraMap,
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		async: false,
		success:function(result){
			JsonData = result;
		}
	});
	return JsonData;
}

//Ӧ�����ݲ�ѯ
function search(){
	var JsonData = getQueryData();
	var htmlObj = document.getElementById("all_list");
	var html = "";
	if(JsonData == 'null' || JsonData == null || JsonData.length == 0 || !JsonData){
		html = "��������...";
	}else{
		for(var i=0;i < JsonData.length;i++){
			html += "<li><a href='"+JsonData[i]['dyym']+"' target='_blank'><img src='images/wdyy/"+JsonData[i]['gnmklj']+"' width='48' height='48'><h5>"+JsonData[i]['gnmkmc']+"</h5></a><span data-flag = 'add' onclick=add_delApp(this) data-gnmkdm = '"+JsonData[i]['gnmkdm']+"' class='ico'></span></li>";
		}
	}
	
	htmlObj.innerHTML = html;
}

//���ɾ���ҵ�Ӧ��
function add_delApp(obj){
	var len = jQuery(".connectedSortable li").length;
	/*���*/
	if(jQuery(obj).attr('data-flag') == 'add'){
		if(len >= 12){
			showAlert("�ҵ�Ӧ���б��������12����");
			return false;
		}
	    jQuery(obj).attr('data-flag','del');
		var html = jQuery(obj).parent();
		jQuery(obj).parent().remove();
		jQuery(".connectedSortable").append(html);
	}else{/*ɾ��*/
		if(len == 1){
			showAlert("�����ٱ���һ��Ӧ�ã�");
			return false;
		}
		if(jQuery(".app_con #all_list li").length == 0){
			jQuery(".app_con > #all_list").text("");
		}
		jQuery(".xxk").find('li').removeClass('cur');
		jQuery("#lione").addClass('cur');
		jQuery('.all').find('a').text('����');
		search();
		jQuery(obj).attr('data-flag','add');;
		var html = jQuery(obj).parent();
		jQuery(obj).parent().remove();
		jQuery("#all_list").append(html);
	}
	
}

//����رհ�ťʱ���Ƚ������ݱ���
function saveData(){
	var url = "wdyygl.do?method=close_saveData";
	var htmlgnmkdm = new Array();
	var xssxs = new Array();
	var rows = jQuery(".connectedSortable > li > span");
	jQuery.each(rows, function(i, row) {
		htmlgnmkdm.push(encodeURI(encodeURI(jQuery(row).attr('data-gnmkdm'))));
		/*����+1��ֵ��˳������*/
		xssxs.push(encodeURI(encodeURI(i+1)));
	});
	var paraMap = {
			"gnmkms":htmlgnmkdm,
			"xssxs":xssxs
	}
	var JsonData = null;
	var innerhtml = "";
	var obj = "";
	jQuery.ajax({
		type:'post',
		url:url,
		dataType:'text',
		data:paraMap,
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		async: false,
		success:function(result){
			showAlert(result,{},{"clkFun":function(){
				save_close();
				//�ɹ���ˢ��ҳ��
				if(result == '���óɹ���'){
				   refreshindexHTML();
				}
			}});
	    }
		
	});
}

function save_close(){
	jQuery(".opacity_box").next().remove();
	jQuery(".opacity_box").remove();
}

//ˢ����ҳ�ҵ�Ӧ�����
function refreshindexHTML(){
	var url = "wdyygl.do?method=refreshIndexHTML";
	jQuery.ajax({
		type:'post',
		url:url,
		dataType:'json',
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		async: false,
		success:function(data){
		    var indexhtml = "";
		    var obj = ""
			if(data == 'null' || data == null || data.length == 0 || !data){
				indexhtml = "�������ݣ�";
			}else{
				for(var i= 0;i<data.length;i++){
					indexhtml +="<li title = '"+data[i]['title']+"'><a href='"+data[i]['dyym']+"' target='_blank'><img style='width:56px;height:56px' src='images/wdyy/"+data[i]['gnmklj']+"'/>";
					indexhtml +="<p class='text-center'>"+data[i]['gnmkmc']+"</p></a></li>";
				}
			}
			 obj = jQuery(".framecon").contents().find(".info-search");
			 jQuery(obj).find("li").remove();
			
			 jQuery(obj).append(indexhtml);
			 //jQuery(".framecon").contents().find(".ico_list > .title > span > b").text("("+jQuery(obj).find("li").length+")");
			 var wdyy_height = jQuery(".framecon").contents().find(".leftframe01").height() || jQuery(".framecon").contents().find(".leftframe02").height();
			 var wdyy_ul_height = jQuery(obj).height()+jQuery(".framecon").contents().find(".ico_list .title").height()+4;
			 wdyy_height = (wdyy_ul_height > wdyy_height) ? wdyy_ul_height :wdyy_height;
			 jQuery(".framecon").contents().find(".ico_list").height(wdyy_height);
		}
	});
}

//�༭�ҵ�Ӧ��
function editwdyy(){
	jQuery.zfDialog.Alert();
}

//��ֹ��괥��
function showsearch(obj){
	jQuery(obj).parent().find('li').removeClass('cur');
	jQuery(obj).addClass('cur');
	jQuery('.all').find('a').text(jQuery(obj).find('a').text());
	search();
}

function initWdyyCss(){
	jQuery(".ico_list > .title > span > b").text("("+jQuery(".ico_list > ul > li").length+")");
	var wdyy_height = jQuery(".leftframe01").height() || jQuery(".leftframe02").height();
	var wdyy_ul_height = jQuery(".ico_list > ul ").height()+jQuery(".ico_list > .title ").height()+4;
	wdyy_height = (wdyy_ul_height > wdyy_height) ? wdyy_ul_height :wdyy_height;
	jQuery(".ico_list").height(wdyy_height);
}


