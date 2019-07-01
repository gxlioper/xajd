//编辑我的页面打开应用数据初始化
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

//我的应用窗口中查询时数据获取
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

//应用数据查询
function search(){
	var JsonData = getQueryData();
	var htmlObj = document.getElementById("all_list");
	var html = "";
	if(JsonData == 'null' || JsonData == null || JsonData.length == 0 || !JsonData){
		html = "暂无数据...";
	}else{
		for(var i=0;i < JsonData.length;i++){
			html += "<li><a href='"+JsonData[i]['dyym']+"' target='_blank'><img src='images/wdyy/"+JsonData[i]['gnmklj']+"' width='48' height='48'><h5>"+JsonData[i]['gnmkmc']+"</h5></a><span data-flag = 'add' onclick=add_delApp(this) data-gnmkdm = '"+JsonData[i]['gnmkdm']+"' class='ico'></span></li>";
		}
	}
	
	htmlObj.innerHTML = html;
}

//添加删除我的应用
function add_delApp(obj){
	var len = jQuery(".connectedSortable li").length;
	/*添加*/
	if(jQuery(obj).attr('data-flag') == 'add'){
		if(len >= 12){
			showAlert("我的应用列表最多可添加12个！");
			return false;
		}
	    jQuery(obj).attr('data-flag','del');
		var html = jQuery(obj).parent();
		jQuery(obj).parent().remove();
		jQuery(".connectedSortable").append(html);
	}else{/*删除*/
		if(len == 1){
			showAlert("请至少保留一个应用！");
			return false;
		}
		if(jQuery(".app_con #all_list li").length == 0){
			jQuery(".app_con > #all_list").text("");
		}
		jQuery(".xxk").find('li').removeClass('cur');
		jQuery("#lione").addClass('cur');
		jQuery('.all').find('a').text('所有');
		search();
		jQuery(obj).attr('data-flag','add');;
		var html = jQuery(obj).parent();
		jQuery(obj).parent().remove();
		jQuery("#all_list").append(html);
	}
	
}

//点击关闭按钮时首先进行数据保存
function saveData(){
	var url = "wdyygl.do?method=close_saveData";
	var htmlgnmkdm = new Array();
	var xssxs = new Array();
	var rows = jQuery(".connectedSortable > li > span");
	jQuery.each(rows, function(i, row) {
		htmlgnmkdm.push(encodeURI(encodeURI(jQuery(row).attr('data-gnmkdm'))));
		/*索引+1赋值给顺序数组*/
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
				//成功才刷新页面
				if(result == '设置成功！'){
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

//刷新首页我的应用入口
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
				indexhtml = "暂无数据！";
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

//编辑我的应用
function editwdyy(){
	jQuery.zfDialog.Alert();
}

//防止鼠标触发
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


