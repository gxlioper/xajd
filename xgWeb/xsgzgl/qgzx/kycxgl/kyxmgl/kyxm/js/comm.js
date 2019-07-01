/**
	 * 列表展开/收起
	 * @param obj
	 * @return
	 */
	function showYsmx(obj,index){
		var className = jQuery(obj).attr("class");
		var newClass = className == "up" ? "down" : "up";
		jQuery(obj).attr("class",newClass);
		jQuery(obj).parents(".formlist").children(".ysTbody").toggle();
	}
	
	function initFyxj(){
		var bxfy='0';//报销经费
		var zjfy='0';//追加经费
		var zjf = '0';//总经费
		var pzjf = jQuery("#pzjf").val();
		jQuery.each(jQuery("#tbody_xmfy tr"),function(i,n){
				var fylb= jQuery(n).find("select[name=fylb] option:selected").val();;
				var fyje = jQuery(n).find("td").find("input[name=fyje]").val();
				if(""!=fyje&&null!=fyje){
				if('1'==fylb){
					zjfy=parseFloat(zjfy)+parseFloat(fyje);
				}else{
					bxfy=parseFloat(bxfy)+parseFloat(fyje);
					}
				}
		});
		zjf = parseFloat(zjfy)+parseFloat(pzjf);
		
		jQuery("#ybxjf").val(bxfy);
		jQuery("#zjjf").val(zjfy);
		jQuery("#xmhjzjf").val(zjf);
		}
	
	function setXs(xh,xm,xymc,xydm,nj,sjhm) {
		var W;
		var api = frameElement.api;
		if (api) {
			if (api.get('childDialog')) {
				W = api.get('parentDialog')
			} else {
				W = api.opener;
			}
		} else if (parent.window) {
			W = parent.window;
		}
		W.xsZj(xh,xm,xymc,xydm,nj,sjhm);

		iFClose();
	}
	
