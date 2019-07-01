/**
 * 年级学院专业班级联动NEW 
 *  @param[0]:njId[年级ID]
 *  @param[1]:xydmId[学院ID]
 *  @param[2]:zydmId[专业ID]
 *  @param[3]:bjdmId[班级ID]
 *  @param[4]:style[(nj:按年级联动,xy：按学院联动,zy:按专业联动,bj:按班级联动[班级回写联动暂不做])]
 * ===========================以下扩展字段===============================================
 *  @param[5]:sfkzSjfw[是否控制数据范围[0:控制; 1:不控制]]             默认：0 此参数可不传
 *  @param[6]:sfzx    [是否在校范围[0:在校学生数据集; 1:所有数据集]]    默认：0 此参数可不传
 * 
 * 示例：
 * 
 * 按年级联动
 * onchange="onChangJcsj('nj','xydm','zydm','bjdm','nj','1','1');"
 * onchange="onChangJcsj('nj','xydm','zydm','bjdm','nj');"
 * 
 * 按学院联动
 * onchange="onChangJcsj('nj','xydm','zydm','bjdm','xy','1','1');"
 * onchange="onChangJcsj('nj','xydm','zydm','bjdm','xy');"
 * 
 * 按专业联动
 * onchange="onChangJcsj('nj','xydm','zydm','bjdm','zy','1','1');" 
 * onchange="onChangJcsj('nj','xydm','zydm','bjdm','zy');" 
 * 
 *  @date 2013-12-25
 *  @964
 */
function onChangJcsj(){
	var njId = arguments[0];
	var xydmId = arguments[1];
	var zydmId = arguments[2];
	var bjdmId = arguments[3];
	var style = arguments[4];
	var sfkzSjfw = arguments[5];
	var sfzx = arguments[6];
	
	if(sfkzSjfw == null || sfkzSjfw == ""){
		sfkzSjfw = "0";
	}
	
	if("nj" == style){
		onChangJcsjNj(njId, xydmId, zydmId, bjdmId, sfkzSjfw, sfzx);
	}else if("xy" == style){
		onChangJcsjXy(njId, xydmId, zydmId, bjdmId, sfkzSjfw, sfzx);
	
	}else if("zy" == style){
		onChangJcsjZy(njId, xydmId, zydmId, bjdmId, sfkzSjfw, sfzx);

	}else if("bj" == style){
		onChangJcsjBj(njId, xydmId, zydmId, bjdmId, sfkzSjfw, sfzx);
	}else{
		return false;
	}
}

//年级联动 sfkzSjfw:是否控制数据范围[0:控制;1:不控制]	
function onChangJcsjNj(njId, xydmId, zydmId, bjdmId, sfkzSjfw, sfzx){
	// 历史选择的代码
	var xydmOldSelect = "";
	if(xydmId != null && xydmId != "" && xydmId != "undefined"){
	    xydmOldSelect = jQuery("#"+xydmId).val();
	}
	var zydmOldSelect = "";
	if(zydmId != null && zydmId != "" && zydmId != "undefined"){
		zydmOldSelect = jQuery("#"+zydmId).val();
	}			
	var bjdmOldSelect = "";
	if(bjdmId != null && bjdmId != "" && bjdmId != "undefined"){
		bjdmOldSelect = jQuery("#"+bjdmId).val();
	}
	// 年级选值
	var njGet = jQuery("#"+njId).val();
	
	if(njGet == ""){
		jQuery("#"+xydmId).empty();
		jQuery("#"+xydmId).append("<option value=''>&nbsp;</option>");
		jQuery("#"+zydmId).empty();
		jQuery("#"+zydmId).append("<option value=''>&nbsp;</option>");
		jQuery("#"+bjdmId).empty();
		jQuery("#"+bjdmId).append("<option value=''>&nbsp;</option>");
	}else{
		
		// 调用方法返回xylist;zylist;bjlist
		jQuery.post("jcsj.do?method=onChangJcsjNj",{nj:njGet,sfkzSjfw:sfkzSjfw,sfzx:sfzx},function(data){
	
			if(xydmId != null && xydmId != "" && xydmId != "undefined"){
				jQuery("#"+xydmId).empty();
				jQuery("#"+xydmId).append("<option value=''>&nbsp;</option>");
				jQuery(data["xyList"]).each(function(){
					if(xydmOldSelect == this.xydm){
						jQuery("#"+xydmId).append(jQuery("<option/>").text(this.xymc).attr("value",this.xydm).attr("selected","true"));
					}else{
						jQuery("#"+xydmId).append(jQuery("<option/>").text(this.xymc).attr("value",this.xydm));
					}
					
				});
			}
			
			if(zydmId != null && zydmId != "" && zydmId != "undefined"){
				jQuery("#"+zydmId).empty();
				jQuery("#"+zydmId).append("<option value=''>&nbsp;</option>");
				jQuery(data["zyList"]).each(function(){
	
					if(zydmOldSelect == this.zydm){
						jQuery("#"+zydmId).append(jQuery("<option/>").text(this.zymc).attr("value",this.zydm).attr("selected","true"));
					}else{
						jQuery("#"+zydmId).append(jQuery("<option/>").text(this.zymc).attr("value",this.zydm));
					}
				});
			}
			
			if(bjdmId != null && bjdmId != "" && bjdmId != "undefined"){
				jQuery("#"+bjdmId).empty();
				jQuery("#"+bjdmId).append("<option value=''>&nbsp;</option>");
				jQuery(data["bjList"]).each(function(){
					if(bjdmOldSelect == this.bjdm){
						jQuery("#"+bjdmId).append(jQuery("<option/>").text(this.bjmc).attr("value",this.bjdm).attr("selected","true"));
					}else{
						jQuery("#"+bjdmId).append(jQuery("<option/>").text(this.bjmc).attr("value",this.bjdm));
					}
				});
			}	
			
		},'json');
	}
	
}

//学院联动
function onChangJcsjXy(njId, xydmId, zydmId, bjdmId, sfkzSjfw, sfzx){
	// 历史选择的代码
	var njOldSelect = "";
	if(njId != null && njId != "" && njId != "undefined"){
		njOldSelect = jQuery("#"+njId).val();
	}
	var zydmOldSelect = "";
	if(zydmId != null && zydmId != "" && zydmId != "undefined"){
		zydmOldSelect = jQuery("#"+zydmId).val();
	}			
	var bjdmOldSelect = "";
	if(bjdmId != null && bjdmId != "" && bjdmId != "undefined"){
		bjdmOldSelect = jQuery("#"+bjdmId).val();
	}

	// 学院选值
	var xydmGet = jQuery("#"+xydmId).val();
	
	// 调用方法返回njlist;zylist;bjlist
	jQuery.post("jcsj.do?method=onChangJcsjXy",{nj:njOldSelect,xydm:xydmGet,sfkzSjfw:sfkzSjfw,sfzx:sfzx},function(data){

//		if(njId != null && njId != "" && njId != "undefined"){
//			jQuery("#"+njId).empty();
//			jQuery("#"+njId).append("<option value=''>&nbsp;</option>");
//			jQuery(data["njList"]).each(function(){
//				if(njOldSelect == this.nj){
//					jQuery("#"+njId).append(jQuery("<option/>").text(this.nj).attr("value",this.nj).attr("selected","true"));
//				}else{
//					jQuery("#"+njId).append(jQuery("<option/>").text(this.nj).attr("value",this.nj));
//				}
//				
//			});
//		}
		
		if(zydmId != null && zydmId != "" && zydmId != "undefined"){
			jQuery("#"+zydmId).empty();
			jQuery("#"+zydmId).append("<option value=''>&nbsp;</option>");
			jQuery(data["zyList"]).each(function(){

				if(zydmOldSelect == this.zydm){
					jQuery("#"+zydmId).append(jQuery("<option/>").text(this.zymc).attr("value",this.zydm).attr("selected","true"));
				}else{
					jQuery("#"+zydmId).append(jQuery("<option/>").text(this.zymc).attr("value",this.zydm));
				}
			});
		}
		
		if(bjdmId != null && bjdmId != "" && bjdmId != "undefined"){
			jQuery("#"+bjdmId).empty();
			jQuery("#"+bjdmId).append("<option value=''>&nbsp;</option>");
			jQuery(data["bjList"]).each(function(){
				if(bjdmOldSelect == this.bjdm){
					jQuery("#"+bjdmId).append(jQuery("<option/>").text(this.bjmc).attr("value",this.bjdm).attr("selected","true"));
				}else{
					jQuery("#"+bjdmId).append(jQuery("<option/>").text(this.bjmc).attr("value",this.bjdm));
				}
			});
		}
		
	},'json');
}

//专业联动 
function onChangJcsjZy(njId, xydmId, zydmId, bjdmId, sfkzSjfw, sfzx){
	// 历史选择的代码
	var njOldSelect = "";
	if(njId != null && njId != "" && njId != "undefined"){
		njOldSelect = jQuery("#"+njId).val();
	}
	var xydmOldSelect = "";
	if(xydmId != null && xydmId != "" && xydmId != "undefined"){
		xydmOldSelect = jQuery("#"+xydmId).val();
	}			
	var bjdmOldSelect = "";
	if(bjdmId != null && bjdmId != "" && bjdmId != "undefined"){
		bjdmOldSelect = jQuery("#"+bjdmId).val();
	}

	// 专业选值
	var zydmGet = jQuery("#"+zydmId).val();
	
	// 调用方法返回njlist;zylist;bjlist
	jQuery.post("jcsj.do?method=onChangJcsjZy",{nj:njOldSelect,xydm:xydmOldSelect,zydm:zydmGet,sfkzSjfw:sfkzSjfw,sfzx:sfzx},function(data){

//		if(njId != null && njId != "" && njId != "undefined"){
//			jQuery("#"+njId).empty();
//			jQuery("#"+njId).append("<option value=''>&nbsp;</option>");
//			jQuery(data["njList"]).each(function(){
//				if(njOldSelect == this.nj){
//					jQuery("#"+njId).append(jQuery("<option/>").text(this.nj).attr("value",this.nj).attr("selected","true"));
//				}else{
//					jQuery("#"+njId).append(jQuery("<option/>").text(this.nj).attr("value",this.nj));
//				}
//				
//			});
//		}
//		
//		if(xydmId != null && xydmId != "" && xydmId != "undefined"){
//			jQuery("#"+xydmId).empty();
//			jQuery("#"+xydmId).append("<option value=''>&nbsp;</option>");
//			jQuery(data["xyList"]).each(function(){
//				if(xydmOldSelect == this.xydm){
//					jQuery("#"+xydmId).append(jQuery("<option/>").text(this.xymc).attr("value",this.xydm).attr("selected","true"));
//				}else{
//					jQuery("#"+xydmId).append(jQuery("<option/>").text(this.xymc).attr("value",this.xydm));
//				}
//				
//			});
//		}
		
		if(bjdmId != null && bjdmId != "" && bjdmId != "undefined"){
			jQuery("#"+bjdmId).empty();
			jQuery("#"+bjdmId).append("<option value=''>&nbsp;</option>");
			jQuery(data["bjList"]).each(function(){
				if(bjdmOldSelect == this.bjdm){
					jQuery("#"+bjdmId).append(jQuery("<option/>").text(this.bjmc).attr("value",this.bjdm).attr("selected","true"));
				}else{
					jQuery("#"+bjdmId).append(jQuery("<option/>").text(this.bjmc).attr("value",this.bjdm));
				}
			});
		}
		
	},'json');
}

//班级联动
function onChangJcsjBj(njId, xydmId, zydmId, bjdmId, sfkzSjfw, sfzx){
	// 历史选择的代码
	var njOldSelect = "";
	if(njId != null && njId != "" && njId != "undefined"){
		njOldSelect = jQuery("#"+njId).val();
	}
	var xydmOldSelect = "";
	if(xydmId != null && xydmId != "" && xydmId != "undefined"){
		xydmOldSelect = jQuery("#"+xydmId).val();
	}
	var zydmOldSelect = "";
	if(zydmId != null && zydmId != "" && zydmId != "undefined"){
		zydmOldSelect = jQuery("#"+zydmId).val();
	}
	// 班级选值
	var bjdmGet = jQuery("#"+bjdmId).val();
	
	// 调用方法返回njlist;zylist;bjlist
	jQuery.post("jcsj.do?method=onChangJcsjBj",{nj:njOldSelect,xydm:xydmOldSelect,zydm:zydmOldSelect,bjdm:bjdmGet,sfkzSjfw:sfkzSjfw,sfzx:sfzx},function(data){

//		if(njId != null && njId != "" && njId != "undefined"){
//			jQuery("#"+njId).empty();
//			jQuery("#"+njId).append("<option value=''>&nbsp;</option>");
//			jQuery(data["njList"]).each(function(){
//				if(njOldSelect == this.nj){
//					jQuery("#"+njId).append(jQuery("<option/>").text(this.nj).attr("value",this.nj).attr("selected","true"));
//				}else{
//					jQuery("#"+njId).append(jQuery("<option/>").text(this.nj).attr("value",this.nj));
//				}
//				
//			});
//		}
//		
//		if(xydmId != null && xydmId != "" && xydmId != "undefined"){
//			jQuery("#"+xydmId).empty();
//			jQuery("#"+xydmId).append("<option value=''>&nbsp;</option>");
//			jQuery(data["xyList"]).each(function(){
//				if(xydmOldSelect == this.xydm){
//					jQuery("#"+xydmId).append(jQuery("<option/>").text(this.xymc).attr("value",this.xydm).attr("selected","true"));
//				}else{
//					jQuery("#"+xydmId).append(jQuery("<option/>").text(this.xymc).attr("value",this.xydm));
//				}
//				
//			});
//		}
//		if(bjdmId != null && bjdmId != "" && bjdmId != "undefined"){
//			jQuery("#"+bjdmId).empty();
//			jQuery("#"+bjdmId).append("<option value=''>&nbsp;</option>");
//			jQuery(data["bjList"]).each(function(){
//				if(bjdmOldSelect == this.bjdm){
//					jQuery("#"+bjdmId).append(jQuery("<option/>").text(this.bjmc).attr("value",this.bjdm).attr("selected","true"));
//				}else{
//					jQuery("#"+bjdmId).append(jQuery("<option/>").text(this.bjmc).attr("value",this.bjdm));
//				}
//			});
//		}
		
	},'json');
}