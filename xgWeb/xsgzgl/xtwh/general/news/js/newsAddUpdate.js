var toWhoTemp = ""; // 保存上一次的接收对象
//打开浮层
function open(index){
	//console.log("open(index):"+index + " " + jQuery(".selectTextClass").length);
	jQuery(".selectDivClass").hide();
	jQuery("#selectDiv_"+index).show();
	
	jQuery(".selectTextClass").each(function(){
		jQuery(this).removeClass("bor1");
	});
	jQuery("#selectText_"+index).addClass("bor1");
}
//关闭浮层
function closeAll(){
	jQuery(".selectDivClass").hide();
	jQuery(".selectTextClass").each(function(){
		jQuery(this).removeClass("bor1");
	});
}
//打开、关闭浮层
function selectTextClick(index){
	var selectDiv = "#selectDiv_"+index;
	if(jQuery(selectDiv).is(":hidden")){
		open(index);
	}else{
		closeAll();
	}
}
//设置接收对象文字
function autoSetText(toWho, index){
	var yxtj_dl=jQuery("#yxtj_dl").html();
	yxtj_dl=jQuery.trim(yxtj_dl);
	if(yxtj_dl!=""){
		jQuery("#sztj").show();
	}else{
		jQuery("#sztj").hide();
	}
	closeAll();
	jQuery("#" + toWho).attr("checked","checked");
	// ====== 设置接收对象文字 begin ==========
	var select=jQuery("input[name='toWho']:checked");
	var text=jQuery(select).parent().text();
	jQuery(".selectTextClass").each(function(){
		var self = jQuery(this);
		self.text(self.attr("pName") + "可见");
	});
	//console.log("autoSetText toWho:" + toWho + " index:"+index + " text:"+text);
	jQuery("#selectText_" + index).html(text);
	// ====== 设置接收对象文字 end ==========
	jQuery("input[name='selectRadio']").eq(index).attr("checked","checked");
}
// 浮层（全校）
function selectDivAllClick(toWho, index){
	//console.log("selectDivAllClick toWho:" + toWho + " index:"+index);
	cleanTj();
	autoSetText(toWho, index);
}
// 浮层（指定）
function selectDivSomeClick(toWho, index){
	if(toWhoTemp != toWho){ // 切换接收对象，清空
		cleanTj();
		toWhoTemp = toWho;
	}
	var searchTj=jQuery("#searchTj").val();
	var searchTjz=jQuery("#searchTjz").val();
	var mhcx_lx=jQuery("#mhcx_lx").val();
	var searchLx=jQuery("#searchLx").val();
	var search={searchTj:searchTj,searchTjz:searchTjz,mhcx_lx:mhcx_lx,searchLx:searchLx};
	var searchUrl="searchTj="+searchTj+"&searchTjz="+searchTjz+"&mhcx_lx="+mhcx_lx+"&searchLx="+searchLx;
	var url = "xtwh_news.do?method=selectQx&toWho="+toWho+"&toWhoIndex="+index+"&"+searchUrl;;
	closeAll();
	showDialog("选择对象", 800, 500, url, search);
}
// 清空条件
function cleanTj(){
	jQuery("#sztj").hide();
	jQuery("#yxtj_dl").html("");
	jQuery("#searchTj").val("");
	jQuery("#searchTjz").val("");
	jQuery("#mhcx_lx").val("");
	jQuery("#searchLx").val("");
}
// 接收对象radio点击
function selectRadioClick(index){
	index = index * 2;
	jQuery("input[name='toWho']")[index].click();
}


