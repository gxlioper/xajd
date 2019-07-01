/**
	 * 列表展开/收起
	 * @param obj
	 * @return
	 */
	function showYsmx(obj,index){
		var className = jQuery(obj).attr("class");
		var newClass = className == "up" ? "down" : "up";
		jQuery(obj).attr("class",newClass);
		jQuery(obj).parents(".formlist").children(".gwTbody").toggle();
	}
	
	function initCyrs(){
		var jhcyrs='0';//计划参与人数
		jQuery.each(jQuery("#tbody_xmgw tr"),function(i,n){
				var gwrs = jQuery(n).find("td").find("input[name=zcyrs]").val();
				if(gwrs==null||gwrs==""){
					gwrs='0';
				}
				jhcyrs=parseInt(jhcyrs)+parseInt(gwrs);
		});
		jQuery("#jhcyrs").val(jhcyrs);
	}
	function selectAll(obj) {
		jQuery(obj).parents(".datelist").find('input[type=checkbox]').attr('checked', jQuery(obj).attr('checked'));
	}