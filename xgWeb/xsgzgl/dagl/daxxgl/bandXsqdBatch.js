/**
 * 重载面页
 * @return
 */
function czmy(){
	var url = "daxxgl.do?method=bandXsqdBatch";
	document.forms[0].action = url;
	document.forms[0].submit();
}

function  saveForm(){
	var values = jQuery("#values").val();
	var daqdmb_id = jQuery("#daqdmb_id").val();
	var selected = jQuery("#selected").val();
	var yxzxss = jQuery("#yxzxss").val();

	if(yxzxss=="0"){
		alertInfo("请选择至少1个学生！");
		return false;
	}
	var info = new Array();
	var sx=1;
	var retunflg = false;

	//获取模板外材料信息
	jQuery("#xmInfo").find("tr").find("select[name=clid]").each(function(n){
		var daqdcl_id = jQuery(this).val();
		if(daqdcl_id == null || daqdcl_id ==""){
			alertInfo("请选择材料！");
			jQuery(this).parents("tr:first").css("background-color","#b0cbe0").siblings().css("background-color","#FFFFFF");
			retunflg = true;
			return false;	
		}
	});
	// 有错误信息则直接返回
	if(retunflg){
		return false;
	}
	
	//获取模板内材料信息
	jQuery("#mbnTable tr").each(function(n){
		if(n>0){
			var clInfo = new Object();
			clInfo.daqdcl_id = jQuery(this).find("input[name=daqdcl_id]").val();
			clInfo.sx = sx;
			clInfo.fs = jQuery(this).find("input[name=fs]").val();
			clInfo.ys = jQuery(this).find("input[name=ys]").val();
			clInfo.gdrq = jQuery(this).find("input[name=gdrq]").val();
			clInfo.bz = jQuery(this).find("input[name=bz]").val();
			info[sx]=clInfo;
			sx ++;
		}
	});
	//获取模板外材料信息
	jQuery("#xmInfo").find("tr").each(function(n){
		
		// 未保存档案清单材料外材料的保存
		var daqdcl_id = jQuery(this).find("select[name=clid]").val();		
		if(daqdcl_id!=null && ""!=daqdcl_id){
			var clInfo = new Object();
			clInfo.daqdcl_id = daqdcl_id;
			clInfo.sx = sx;
			clInfo.fs = jQuery(this).find("input[name=_fs]").val();
			clInfo.ys = jQuery(this).find("input[name=_ys]").val();
			clInfo.gdrq = jQuery(this).find("input[name=_gdrq]").val();
			clInfo.bz = jQuery(this).find("input[name=_bz]").val();
			info[sx]=clInfo;
			sx ++;
		}
	});
	
	if(info==""){
		confirmInfo("您未选择材料，确定清除<font color='red'>"+yxzxss +"</font>条记录的材料吗?",function(ty){
			if(ty=="ok"){
				var json=JSON.stringify(info);
				//获取模板外材料信息
				// 得到JSON对象
				var url = "daxxgl.do?method=bandXsqdBatch&type=save";
				jQuery("#form").ajaxSubmit({
					   type: "POST",
					   url: url,
					   dataType:"json",
					   data:{json:json,values:values,daqdmb_id:daqdmb_id,selected:selected},
					   success: function(data){
						   if(data["message"]=="保存成功！"){
					    		 showAlert(data["message"],{},{"clkFun":function(){
						  				if (parent.window){
						  					refershParent();
						  				}
					    			}});
					    	 }
					 		 else{
					    		 showAlert(data["message"]);
					    	 }
					   }
				});
				
			}else{
				return false;
			}
		});
	}else{
		var json=JSON.stringify(info);
		//获取模板外材料信息
		// 得到JSON对象
		var url = "daxxgl.do?method=bandXsqdBatch&type=save&selected=all";
				
		//高级查询
		url +="&searchTj=";
		url +=jQuery("#searchTj").val();
		url +="&searchTjz=";
		url +=jQuery("#searchTjz").val();
		url +="&mhcx_lx=";
		url +=jQuery("#mhcx_lx").val();
		url +="&searchLx=";
		url +=jQuery("#searchLx").val();

		//模糊查询
		url +="&input_mhcx=";
		url +=jQuery("#input_mhcx").val();
		url +="&path=";
		url +=jQuery("#path").val();
		
		//alert(url);
		jQuery("#form").ajaxSubmit({
			   type: "POST",
			   url: url,
			   dataType:"json",
			   data:{json:json,values:values,daqdmb_id:daqdmb_id,selected:selected},
			   success: function(data){
				   if(data["message"]=="保存成功！"){
			    		 showAlert(data["message"],{},{"clkFun":function(){
				  				if (parent.window){
				  					refershParent();
				  				}
			    			}});
			    	 }
			 		 else{
			    		 showAlert(data["message"]);
			    	 }
			   }
		});
	}
}

jQuery(function(){

	var dazrsj = jQuery("#dazrsj").val();
	if(dazrsj == ""){
		var mydate = new Date();
		jQuery("#dazrsj").val(jQuery("#sysdate").val());
	}
	
	// 是否显示档案清单模板的材料列表
	var daqdmb_id = jQuery("#daqdmb_id").val();
	if(daqdmb_id==""){
		jQuery("table[id=mbnTable]").parent().hide();
	}else{
		jQuery("table[id=mbnTable]").parent().show();
	}

	// 档案清单模板列表
	jQuery("#mbnTable tr").each(function(n){
		if(n>0){
			jQuery(this).find("input[name=gdrq]").val(jQuery("#dazrsj").val());
			jQuery(this).find("input[name=fs]").val("1");
		}
	});
	
	//setQxshow();
	sfzjMbwcl();
});


/**
 * 设置已选权限条件
 * @return
 */
function setQxshow(){
	var yxtjhtml='';
	if(frameElement.api){
		yxtjhtml = jQuery(frameElement.api.opener.document).find('#yxtj_dl').html();
		var selectCount =  jQuery("#selectCount").val();
		if(selectCount!=null && selectCount!=""){
			jQuery("tr[id=xsfwSelect]").hide();
		}else{
			alert(yxtjhtml == null || yxtjhtml=="");
			
			if(yxtjhtml == null || yxtjhtml==""){
				yxtjhtml="<dd><h5>全部</h5></dd>";
			}
			jQuery("tr[id=xsfwSelect]").show();
			jQuery("#yxtj_dl").html(yxtjhtml);
		}
	}
	//去掉取消操作
	jQuery("#yxtj_dl").find("a").each(function(){
		jQuery(this).attr("onclick","");
		jQuery(this).find("span").remove();
	});
}

function sfzjMbwcl(){
	// 追加模板外材料
	if(jQuery("input[type=radio][name=mbwclFlag][value=1]").prop("checked")){
		jQuery("tr[name=mbwclTr]").show();
		
	// 不追加模板外材料
	}else if(jQuery("input[type=radio][name=mbwclFlag][value=0]").prop("checked")){
		jQuery("tr[name=mbwclTr]").hide();
	}
}

function selectCl(obj){
	// 跟档案清单模板内材料是否重复判断
	jQuery("#mbnTable tr").each(function(n){
		if(n>0){
			if(obj.value == jQuery(this).find("input[name=daqdcl_id]").val()){
				alertInfo("材料不能重复设定，请重新选择");
				jQuery(obj).val("");
				return false;
			}
		}
	});

	// 跟已保存档案清单模板外材料是否重复判断
	jQuery("#xmInfo").find("input[name=daqdcl_id]").each(function(n){
		if(obj.value == jQuery(this).val()){
			alertInfo("材料不能重复设定，请重新选择");
			jQuery(obj).val("");
			return false;
		}
	});
	
	// 跟档案清单模板外列表材料（往后）是否重复判断
	jQuery(obj).parents("tr").nextAll().each(function(n){
			if(obj.value == jQuery(this).find("select[name=clid]").val()){
				alertInfo("材料不能重复设定，请重新选择");
				jQuery(obj).val("");
				return false;
			}
	});

	// 跟档案清单模板外列表材料（往前）是否重复判断
	jQuery(obj).parents("tr").prevAll().each(function(n){
			if(obj.value == jQuery(this).find("select[name=clid]").val()){
				alertInfo("材料不能重复设定，请重新选择");
				jQuery(obj).val("");
				return false;
			}
	});
}

/** ***********************按钮操作******************************* */
// 增加一行
function addMbwcl(){
	var xmInfo = jQuery('#xmInfo'); 
	var dazrsj = jQuery("#dazrsj").val();
	var row = jQuery("<tr onclick='clickTr(this);return false;'></tr>");			
	var trNew=jQuery("tr[id=mbwSpan]").html();
	row.append(trNew);
	xmInfo.append(row);
	//jQuery("#xmInfo>tr:last td input[name='_gdrq'").val(dazrsj);
}
// 删除选中的记录
function delMbwcl(){
	var length = jQuery('#xmInfo').find("input:checked").length;
	if (length !=0){
		confirmInfo("您确定要删除选中的<font color='red'>"+length +"</font>条记录吗?",function(ty){
			if(ty=="ok"){
				jQuery(xmInfo).find("input:checked").each(function(){
					jQuery(this).parent().parent().remove();
				});
			}
		});
	}
}

// 改变改行的选中状态
function clickTr(whick){
	jQuery('#xmInfo').find("tr").attr("selectrow",false);
	jQuery(jQuery(whick)).attr("selectrow",true);			
	jQuery(jQuery(whick)).css("background-color","#b0cbe0").siblings().css("background-color","#FFFFFF");
	if(jQuery(jQuery(whick)).find(":checkbox").prop("checked")){
		jQuery(jQuery(whick)).find(":checkbox").removeAttr("checked");
	}else{
		jQuery(jQuery(whick)).find(":checkbox").attr("checked","checked");
	}
}
// 删除所有记录
function delAllMbwcl(){
	confirmInfo("您确定要删除所有的记录吗?",function(ty){
		if(ty=="ok"){
			jQuery('#xmInfo').find("tr").remove();
		}
	});
}

// 上移动
function upMbwcl(){
	var length = jQuery('#xmInfo').find("tr[selectrow='true']").length;
	if(length ==1){
		var _cur = jQuery('#xmInfo').find("tr[selectrow='true']");
		var _pre = _cur.prev();
		_cur.insertBefore(_pre);
	}
}

// 下移动
function downMbwcl(){
	var length = jQuery('#xmInfo').find("tr[selectrow='true']").length;
	if(length ==1){
		var _cur = jQuery('#xmInfo').find("tr[selectrow='true']");
		var _pre = _cur.next();
		_cur.insertAfter(_pre);
	}
}

/** *********************************************************** */



