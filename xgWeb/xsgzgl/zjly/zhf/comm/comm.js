/**
 * 项目模块代码下拉框值变动时通用联动方法
 * 注意计分项目代码jfxmdm下来框的option迭代不能用Struts1<html:options>，需要用<logic:iterate>去遍历
 * 因为<html:options>不支持name属性
 * @return
 */
function xmmkdmChange(){
	
	jQuery("#xmmkdm").change(function(){
		var xmmkdm = jQuery(this).val();
		jQuery("#jfxmdm").val("");
		if(xmmkdm == ""){
			jQuery("#jfxmdm").val("");
			jQuery("#jfxmdm > option").remove();
		}else{
			jQuery("#jfxmdm > option").remove();
			var jfxmdmOption = jQuery("#jfxmdms > option[name='"+xmmkdm+"']");
			var jfxmdmhtml = "";
			var objlen  = jfxmdmOption.length;
			for(var i=0;i<objlen;i++){
				 jfxmdmhtml += jfxmdmOption[i].outerHTML;
			}
			
			if(objlen>0){
				jQuery("#jfxmdm").append("<option value='' data-fs = ''  data-khyd = '' selected='selected'  xmmkdm=''></option>").append(jfxmdmhtml);
			}else{
				jQuery("#jfxmdm").append("<option value='' data-fs = ''  data-khyd = '' selected='selected' xmmkdm=''></option>");
			}
			jQuery("#jfxmdm").val("");
			jQuery("#jfxmdm").change();
			
		}
	})


}


function jfxmdmChange(){
	jQuery("#jfxmdm").change(function(){
		var value = jQuery(this).val();
		if(value == "" || value == null){
			jQuery("#khyd").text("");
			jQuery("#fs").text("");
			return false;
		}
		var selectoption = jQuery("option[value='"+value+"']")
		var fs = jQuery(selectoption).attr("data-fs");
		var khyd = jQuery(selectoption).attr("data-khyd");
		jQuery("#khyd").text(khyd);
		jQuery("#fs").text(fs);
	})

}