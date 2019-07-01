 
 function searchRs() {
	var map = getSuperSearch();
	var fbzt = jQuery("#tjzt").val();
	if (fbzt != "") {
		map["tjzt"] = fbzt;
	}
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
//切换
 function selectTab(obj, bbzt) {
 	if (bbzt == "wtj") {
 		var map = getSuperSearch();
 		map["tjzt"] = "wtj";
 		gridSetting["params"] = map;
 		jQuery("#dataTable").initGrid(gridSetting);
 		jQuery("#cx").hide();
 		jQuery("#tj").show();
 	} else {
 		var map = getSuperSearch();
 		map["tjzt"] = "ytj";
 		gridSetting2["params"] = map;
 		jQuery("#dataTable").initGrid(gridSetting2);
 		jQuery("#cx").show();
 		jQuery("#tj").hide();
 	}
 	jQuery(".ha").removeClass("ha");
 	jQuery(obj).parent().addClass("ha");
 	// searchRs();
 }
 function tj(){
		showDialog("提交", 500,300, "fbglfbjg.do?method=tjzsk");
 }
 function cx(){
		showDialog("撤销", 500,300, "fbglfbjg.do?method=cxzsk");
}
 /**
  * 自动设置学生相关信息
  * @return
  */
 function autoSetXsxx(){
	 	var nj=jQuery("#nj").val();
	 	 if(!nj){
	 		 return ;
	 	 }
	 	jQuery.ajax({
			url:"fbglfbjg.do?method=getTjxx",
			data:{nj:nj},
			type:"post",
			dataType:"json",
			success:function(data){
				jQuery("#ytj").text(data.ytj);
				jQuery("#wtj").text(data.wtj);
			}
		});	
 }
 /**
  * 撤销正式库
  * @return
  */
 function cxzsk(){
	 var nj=jQuery("#nj").val();
	 if(!nj){
		return showAlert("请选择您要撤销的年级！");
	 }
	 var barkey="cxzsk"+nj;
	 lock();
	 jQuery("#nj").attr("disabled","disabled");
	 jQuery("#nj").css({color:"#cccccc"});
 	jQuery.ajax({
		url:"fbglfbjg.do?method=cxzsk",
		data:{nj:nj,type:'save'},
		type:"post",
		dataType:"json",
		success:function(data){
		}
	 });	
	var nowValue=parseInt(jQuery("#ytj").text());
	var wtj=parseInt(jQuery("#wtj").text());
 	loadBar(barkey,function(data){
 		jQuery("#ytj").text(nowValue-data.now);
 		jQuery("#wtj").text(data.now+wtj);
 		if(data.finish){
			showAlert("撤销完成！", {}, {
				"clkFun" : function() {
					var api = frameElement.api;
					api.reload();
				}
			});
 		}
 		return true;
 	});
 }
 /**
  * 提交正式库
  * @return
  */
 function tjzsk(){
	 var nj=jQuery("#nj").val();
	 if(!nj){
		return showAlert("请选择您要提交的年级！");
	 }
	 var barkey="tjzsk"+nj;
	 lock();
	 jQuery("#nj").attr("disabled","disabled");
	 jQuery("#nj").css({color:"#cccccc"});
	 	jQuery.ajax({
			url:"fbglfbjg.do?method=tjzsk",
			data:{nj:nj,type:'save'},
			type:"post",
			dataType:"json",
			success:function(data){
				if(data.message=="-1"){
					jQuery("#error").show();
					stopBar();
				}else if(data.message=="0"){
					jQuery("#error").hide();
					showAlert("您选择提交<font style='color:red'>["+nj+"]</font>年级的学生未编班或未编学号，请确认！");
					stopBar();
				}else if(data.message=="2"){
					jQuery("#error").hide();
					showAlert("您选择提交<font style='color:red'>["+nj+"]</font>年级的学生存在重复学号，请确认！");
					stopBar();
				}else{
					jQuery("#error").hide();
				}
			}
		});	
	 	//加载进度条
	 	var nowValue=parseInt(jQuery("#ytj").text());
		var wtj=parseInt(jQuery("#wtj").text());
	 	loadBar(barkey,function(data){
	 		jQuery("#ytj").text(data.now+nowValue);
	 		jQuery("#wtj").text(wtj-data.now);
	 		if(data.finish){
				showAlert("提交成功!", {}, {
					"clkFun" : function() {
						var api = frameElement.api;
						api.reload();
					}
				});
	 		}
	 		return true;
	 	});
 }