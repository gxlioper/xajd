

//查询结果集
		function searchRs(){
			jQuery.ajaxSetup({async:false});
			
			var url = "xtwh_qxgl_yhgl_ajax.do?method=yhxxSearch";
			var ie = "ie";
		
			var parameter = {"ie":ie};
			
			jQuery("select,input",jQuery("#tbody_search_query")).each(function(){		
				parameter["str_"+jQuery(this).attr("name")]=escape(jQuery(this).val());
			});
			
			
				var zdm = jQuery("#oldZdm").val();
				parameter["str_zdm"]=escape(zdm);
			
				var fpzt = jQuery("#fpzt").val();
				parameter["str_fpzt"]=escape(fpzt);
			
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchGo(url,parameter);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
			
		}
		
		function reBack(){
			refreshForm("/xgxt/xtwh_qxgl_yhzglManage.do");
		}
		
		//分配用户
		function fpyh(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
			var flag = true;
			var yhm = "";
			var zdm = jQuery("#oldZdm").val();
			if(len>=1){
				jQuery("[name=primarykey_checkVal]:checked").each(function(i){
					if(i==len-1){
						yhm +=jQuery(this).val();
					}else{
						yhm +=jQuery(this).val()+",";
					}
				});
				jQuery.ajaxSetup({async:false});
				jQuery.post("xtwh_qxgl_yhzgl.do?method=yhzglFpyh",{doType:"save",qx:"1",zdm:zdm,pkValue:yhm},function(data){
						if(data==true){
							confirmInfo("操作成功！",function(ok){
								refreshForm("xtwh_qxgl_yhzgl.do?method=yhzglFpyh&pkValue="+zdm);
							});
						}else{
							alertInfo("操作失败！");
						}
				},'json');
				jQuery.ajaxSetup({async:true});
			}else {				
				alertInfo("请勾选需要修改的数据！");
			}
		}
		
		//取消分配用户
		function qxfpyh(){
			var url = "xtwh_qxgl_yhgl_ajax.do?method=yhxxPlupdate";
			var n = jQuery("[name=primarykey_checkVal]:checked").length;
			var ifGly = "";
			if(n>0){
				var i = 0;
				var parameter = {};
				var array = new Array();
				jQuery("[name=primarykey_checkVal]:checked").each(function(i){
						if(jQuery(this).val() == "zf01"){
							ifGly = "yes";
							return false;
						}else{	
							array[i] = escape(jQuery(this).val());
						}
					});
				jQuery.ajaxSetup({async:false});
				if(ifGly == "yes"){
					alertError("zf01为系统默认用户，不可停用！");
					return false; 
				}else{
					parameter["array_primarykey_checkVal"]=array.join('!!array!!');
					
					parameter["str_qx"]='0';
					parameter["str_zdm"]=jQuery("#oldZdm").val();
					
					confirmInfo("确定要取消分配所选用户吗?",function(ok){
						if(ok=="ok"){
							jQuery.post(url,parameter,function(result){
								var oldRs = jQuery("span[id=yhs]").text();
								var newRs = Number(oldRs)-Number(n);
								jQuery("span[id=yhs]").text(newRs);
								gofpList('yfp');
							});					
						}else{
							return false;
						}
					});
				}
			}else {				
				alertInfo("请勾选需要取消分配的用户！");
				return false;
			}
		}
		
		jQuery(function(){
			searchRs();
		});
		function gofpList(type){
			if("wfp"==type){
				// jQuery("#searchTjId").show();
				jQuery("#fpButtonId").show();
				jQuery("#tyButtonId").hide();
				jQuery("#kfpTabId").addClass("ha");
				jQuery("#yfpTabId").removeClass("ha");
				jQuery("#fpzt").val()
				
			}
			else{
				// jQuery("#searchTjId").hide();
				jQuery("#fpButtonId").hide();
				jQuery("#tyButtonId").show();
				jQuery("#yfpTabId").addClass("ha");
				jQuery("#kfpTabId").removeClass("ha");
			}
			jQuery("#fpzt").val(type);
			searchRs(type);
			
		}
		
		