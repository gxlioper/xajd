//查询结果集
function searchRs(){

	var url = "gyglnew_jcrcgl_ajax.do?method=jcrcglCx";

	var ie = "10.0";

	var otherValue = [ie];

	searchRsByAjax(url,otherValue);
	
	setTimeout("setDivHeight()","1000")
}

function setDivHeight(){
	if($("table_rs")){
		jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
	}
}

function save(tag){
	if(tag=="ok"){

		//主键
		var pkValue=new Array();

		jQuery.ajaxSetup({async:false});
		
		// 得到JSON对象
        var parameter ={};;
	
		jQuery("[name=div_pkValue]:checked").each(function(i){
					
			pkValue[i] =escape(jQuery(this).val());
					
		});
		
		var xh=new Array();
		jQuery("[name=div_pkValue]:checked").each(function(i){
					
			xh[i] =escape(jQuery(this).val());
					
		});
		
		var url = "xljk_hzny_ajax.do?method=zxsglModi";
      	 
	 	parameter["str_byjd"]=escape(byjdV);
		parameter["array_pkValue"]=pkValue.join("!!array!!");
		parameter["array_xh"]=xh.join("!!array!!");

	 	$("divWaiting").style.display="";
		$("divDisable").style.display="";
		
		jQuery.post(url,
			parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				alertInfo(result,function(tag){
				
					if(tag=="ok"){
				
						closeWindown();	
						searchRs();
						
					}
				});
				
				
			}
		);
		
		jQuery.ajaxSetup({async:true});
	}
	

}

function showModi(){

	var len=jQuery("[name=div_pkValue]:checked").length;
	
	if(len==1){	

		var nullflg = false;
		jQuery("[name=div_pkValue]:checked").each(function(i){
			 var sfwh = jQuery(this).attr("sfwh");
			 if(sfwh!=""){
				 nullflg = true;
				}
		});
		if(nullflg){
			alertInfo("勾选的日程当中已有记录录入，不允许修改！");
			return false;
		}else{
			var pkValue=jQuery("[name=div_pkValue]:checked").val();
			
			var xysh=jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(5).html();
			
			var url="gyglnew_jcrcgl.do?method=jcrcglXg";
			
			url+="&pkValue="+pkValue;
			
			//showTopWin(url,600,450);
			showDialog('修改检查日程', 600, 380, url);
		}
	}else{
		
		alertInfo("请勾选一条需要修改的记录！");
		
		return false;
	}
}

function showView(){

	var len=jQuery("[name=div_pkValue]:checked").length;
	
	if(len==1){	
		
		var pkValue=jQuery("[name=div_pkValue]:checked").val();
		
		var xysh=jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(5).html();
		
		var url="gyglnew_jcrcgl.do?method=jcrcglCk";
		
		url+="&pkValue="+pkValue;
		showDialog('查看检查日程', 600, 380, url);
		//showTopWin(url,600,450);
	}else{
		
		alertInfo("请勾选一条需要查看的记录！");
		
		return false;
	}
}

function deleteJcrcgl(){
	
	var n=jQuery("[name=div_pkValue]:checked").length;
	
	var blog=true;
	if(n>0){
		var nullflg = false;
		jQuery("[name=div_pkValue]:checked").each(function(i){
			 var sfwh = jQuery(this).attr("sfwh");
			 if(sfwh!=""){
				 nullflg = true;
				}
		});
		if(nullflg){
			blog = false;
			alertInfo("勾选的日程当中已有记录录入，不允许删除！");
			return false;
		}
		if(blog){
			confirmInfo("该操作将会删除卫生检查日程信息，是否确定继续操作？",function(tag){

				if(tag=="ok"){
					
					var pkValue=new Array();
					
					var xh=new Array();
					
					jQuery("[name=div_pkValue]:checked").each(function(i){
						
						pkValue[i]=jQuery(this).val();
					
					});
					
					var parameter={}

					parameter["pkValue"]=escape(pkValue.join("!!array!!"));
					
					var url= "gyglnew_jcrcgl_ajax.do?method=delete";
					
					jQuery.ajaxSetup({async:false});	
					
					jQuery.post(url,
						parameter,
						function(result){
						
							alertInfo(result,function(tag){
								
								if(tag=="ok"){
									searchRs();
								}
							
							});
							
						}
					);
					
					jQuery.ajaxSetup({async:true});
				}
			});
		}
	}else{
		
		alertInfo("请勾选需要删除的数据！",function(tag){
			
			if(tag=="ok"){
				return false;
			}
		
		});
	}
}

//提交
function tijiao(){
	var n=jQuery("[name=div_pkValue]:checked").length;
	if(n>0){
		confirmInfo("该操作将会锁定卫生检查日程，不允许分数录入，是否确定继续操作？",function(tag){
			
			if(tag=="ok"){
				
				var pkValue=new Array();
				
				var xh=new Array();
				
				jQuery("[name=div_pkValue]:checked").each(function(i){
					
					pkValue[i]=jQuery(this).val();
				
				});
				
				var parameter={}

				parameter["pkValue"]=escape(pkValue.join("!!array!!"));
				
				var url= "gyglnew_jcrcgl.do?method=tjJcrcgl";
				
				jQuery.ajaxSetup({async:false});	
				
				jQuery.post(url,
					parameter,
					function(result){
					
						alertInfo(result,function(tag){
							
							if(tag=="ok"){
								searchRs();
							}
						
						});
						
					}
				);
				
				jQuery.ajaxSetup({async:true});
			}
		});
	}else{
		
		alertInfo("请勾选需要删除的数据！",function(tag){
			
			if(tag=="ok"){
				return false;
			}
		
		});
	}
}

//取消提交
function qxtj(){
	var n=jQuery("[name=div_pkValue]:checked").length;
	if(n>0){
		confirmInfo("该操作将会取消卫生检查日程锁定，允许分数录入，是否确定继续操作？",function(tag){
			
			if(tag=="ok"){
				
				var pkValue=new Array();
				
				var xh=new Array();
				
				jQuery("[name=div_pkValue]:checked").each(function(i){
					
					pkValue[i]=jQuery(this).val();
				
				});
				
				var parameter={}

				parameter["pkValue"]=escape(pkValue.join("!!array!!"));
				
				var url= "gyglnew_jcrcgl.do?method=qxtjJcrcgl";
				
				jQuery.ajaxSetup({async:false});	
				
				jQuery.post(url,
					parameter,
					function(result){
					
						alertInfo(result,function(tag){
							
							if(tag=="ok"){
								searchRs();
							}
						
						});
						
					}
				);
				
				jQuery.ajaxSetup({async:true});
			}
		});
	}else{
		
		alertInfo("请勾选需要删除的数据！",function(tag){
			
			if(tag=="ok"){
				return false;
			}
		
		});
	}
}

jQuery(function(){
	onShow();
})

function jcrcglExportConfig() {
//DCCLBH导出功能编号,执行导出函数 
customExport("gyglnew_wsjc_jcrcgl.do", jcrcglExportData);
}
	

	
// 导出方法
function jcrcglExportData() {
	setSearchTj();//设置高级查询条件
	var url = "gyglnew_jcrcgl_ajax.do?method=jcrcglExportData&dcclbh=" + "gyglnew_wsjc_jcrcgl.do";//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}