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
 * ������ѡȨ������
 * @return
 */
function setQxshow(){
	var yxtjhtml=jQuery("#yxtj_dl").html();
	W.jQuery("#yxtj_dl").html(yxtjhtml);
	//ȥ��ȡ������
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
	
	//�����б�����
	map["searchTj"]=W.jQuery("#searchTj").val();
	map["searchTjz"]=W.jQuery("#searchTjz").val();
	map["mhcx_lx"]=W.jQuery("#mhcx_lx").val();
	map["searchLx"]=W.jQuery("#searchLx").val();
	
	toWho=jQuery("#toWho").val();
	toWhoIndex=jQuery("#toWhoIndex").val();
	var url = "xtwh_news.do?method=selectQx&type=query&toWho="+toWho+"&toWhoIndex="+toWhoIndex;
	gridSetting = {
			caption:"��ѡ�����",
			pager:"pager",
			url:url,
			colList:[
			         {label:'�Ƿ񱣴�ɹ�',name:'isSaveQx', index: 'isSaveQx',key:true,hidden:true}
			         ],
			         radioselect:false
	}
	gridSetting["params"] = map;
	
	jQuery("#dataTable").initGrid(gridSetting);
	//�������
	jQuery("#a_id_more").click();
	jQuery(".adv_filter").hide();
	jQuery("#body").hide();
	jQuery(".more--item_bottom").hide();
});
function reset(){
	showConfirmDivLayer("ȷ������������ѡ������", {
		"okFun" : function() {
			//����ģ����ѯ
			if($("input_mhcx")){
				$("input_mhcx").value = "";
			}
			
			//���ø߼���ѯ
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
			
			//��ʼʱ��
			for(var i=0;i<kssj_count;i++){
				var obj = document.getElementsByName("searchModel.search_tj_kssj")[i];
				obj.value = "";
			}
			
			//����ʱ��
			for(var i=0;i<jssj_count;i++){
				var obj = document.getElementsByName("searchModel.search_tj_jssj")[i];
				obj.value = "";
			}
		}
	});
}