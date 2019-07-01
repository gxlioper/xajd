var gridSetting;
var api; var W;
var toWho; var toWhoIndex;
function searchRs() {
	//jQuery("form[name='newsManageForm']").submit();
	var map = getSuperSearch();
	
	W.jQuery("#searchTj").val(map["searchTj"]);
	W.jQuery("#searchTjz").val(map["searchTjz"]);
	W.jQuery("#mhcx_lx").val(map["mhcx_lx"]);
	W.jQuery("#searchLx").val(map["searchLx"]);
	setQxshow();
	Close();
	/*jQuery.post("xtwh_news.do?method=selectQx&type=query", map, function(data) {
		Close();
	});*/
}
function myClose(){
	W.autoSetText(toWho, toWhoIndex);
	Close();
}
/**
 * 设置已选权限条件
 * @return
 */
function setQxshow(){
	var yxtjhtml=jQuery("#yxtj_dl").html();
	W.jQuery("#yxtj_dl").html(yxtjhtml);
	//去掉取消操作
	W.jQuery("#yxtj_dl").find("a").each(function(){
		jQuery(this).attr("onclick","");
		jQuery(this).find("span").remove();
	});
	W.jQuery("#sztj").show();
	W.autoSetText(toWho, toWhoIndex);
}
jQuery(function(){
	var map = getSuperSearch();
	api = frameElement.api;
	W =api.get("parentDialog");
	
	//加载列表数据
	map["searchTj"]=W.jQuery("#searchTj").val();
	map["searchTjz"]=W.jQuery("#searchTjz").val();
	map["mhcx_lx"]=W.jQuery("#mhcx_lx").val();
	map["searchLx"]=W.jQuery("#searchLx").val();
	
	toWho=jQuery("#toWho").val();
	toWhoIndex=jQuery("#toWhoIndex").val();
	var url = "xtwh_news.do?method=selectQx&type=query&toWho="+toWho+"&toWhoIndex="+toWhoIndex;
	gridSetting = {
			caption:"以选择对象",
			pager:"pager",
			url:url,
			colList:[
			         {label:'是否保存成功',name:'isSaveQx', index: 'isSaveQx',key:true,hidden:true}
			         ],
			         radioselect:false
	}
	gridSetting["params"] = map;
	
	jQuery("#dataTable").initGrid(gridSetting);
	//加载完成
	jQuery("#a_id_more").click();
	jQuery(".adv_filter").hide();
	jQuery("#body").hide();
	jQuery(".more--item_bottom").hide();
});
function reset(){
	showConfirmDivLayer("确定重置所有已选对象吗？", {
		"okFun" : function() {
			//重置模糊查询
			if($("input_mhcx")){
				$("input_mhcx").value = "";
			}
			
			//重置高级查询
			$("yxtj_div").style.display = "none";
			$("yxtj_dl").innerHTML = "";
			if($("yxtj_gxh_div")){
				$("yxtj_gxh_div").style.display  = "none";
				$("yxtj_gxh_dl").innerHTML = "";
			}
			
			var a_num = document.getElementsByTagName('a').length;

			for(var i=0;i<a_num;i++){
				
				var a_className = document.getElementsByTagName('a')[i].className;
				
				if(a_className == "selectedValue"){
					document.getElementsByTagName('a')[i].className = "";
				}
			}
			
			var kssj_count = document.getElementsByName("searchModel.search_tj_kssj").length;
			var jssj_count = document.getElementsByName("searchModel.search_tj_jssj").length;
			
			//开始时间
			for(var i=0;i<kssj_count;i++){
				var obj = document.getElementsByName("searchModel.search_tj_kssj")[i];
				obj.value = "";
			}
			
			//结束时间
			for(var i=0;i<jssj_count;i++){
				var obj = document.getElementsByName("searchModel.search_tj_jssj")[i];
				obj.value = "";
			}
		}
	});
}