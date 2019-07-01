/**
 * �꼶ѧԺרҵ�༶����NEW 
 *  @param[0]:njId[�꼶ID]
 *  @param[1]:xydmId[ѧԺID]
 *  @param[2]:zydmId[רҵID]
 *  @param[3]:bjdmId[�༶ID]
 *  @param[4]:style[(nj:���꼶����,xy����ѧԺ����,zy:��רҵ����,bj:���༶����[�༶��д�����ݲ���])]
 * ===========================������չ�ֶ�===============================================
 *  @param[5]:sfkzSjfw[�Ƿ�������ݷ�Χ[0:����; 1:������]]             Ĭ�ϣ�0 �˲����ɲ���
 *  @param[6]:sfzx    [�Ƿ���У��Χ[0:��Уѧ�����ݼ�; 1:�������ݼ�]]    Ĭ�ϣ�0 �˲����ɲ���
 * 
 * ʾ����
 * 
 * ���꼶����
 * onchange="onChangJcsj('nj','xydm','zydm','bjdm','nj','1','1');"
 * onchange="onChangJcsj('nj','xydm','zydm','bjdm','nj');"
 * 
 * ��ѧԺ����
 * onchange="onChangJcsj('nj','xydm','zydm','bjdm','xy','1','1');"
 * onchange="onChangJcsj('nj','xydm','zydm','bjdm','xy');"
 * 
 * ��רҵ����
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

//�꼶���� sfkzSjfw:�Ƿ�������ݷ�Χ[0:����;1:������]	
function onChangJcsjNj(njId, xydmId, zydmId, bjdmId, sfkzSjfw, sfzx){
	// ��ʷѡ��Ĵ���
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
	// �꼶ѡֵ
	var njGet = jQuery("#"+njId).val();
	
	if(njGet == ""){
		jQuery("#"+xydmId).empty();
		jQuery("#"+xydmId).append("<option value=''>&nbsp;</option>");
		jQuery("#"+zydmId).empty();
		jQuery("#"+zydmId).append("<option value=''>&nbsp;</option>");
		jQuery("#"+bjdmId).empty();
		jQuery("#"+bjdmId).append("<option value=''>&nbsp;</option>");
	}else{
		
		// ���÷�������xylist;zylist;bjlist
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

//ѧԺ����
function onChangJcsjXy(njId, xydmId, zydmId, bjdmId, sfkzSjfw, sfzx){
	// ��ʷѡ��Ĵ���
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

	// ѧԺѡֵ
	var xydmGet = jQuery("#"+xydmId).val();
	
	// ���÷�������njlist;zylist;bjlist
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

//רҵ���� 
function onChangJcsjZy(njId, xydmId, zydmId, bjdmId, sfkzSjfw, sfzx){
	// ��ʷѡ��Ĵ���
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

	// רҵѡֵ
	var zydmGet = jQuery("#"+zydmId).val();
	
	// ���÷�������njlist;zylist;bjlist
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

//�༶����
function onChangJcsjBj(njId, xydmId, zydmId, bjdmId, sfkzSjfw, sfzx){
	// ��ʷѡ��Ĵ���
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
	// �༶ѡֵ
	var bjdmGet = jQuery("#"+bjdmId).val();
	
	// ���÷�������njlist;zylist;bjlist
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