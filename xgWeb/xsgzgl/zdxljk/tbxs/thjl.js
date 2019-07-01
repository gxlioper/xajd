jQuery(function(){
			gxlxFormatter();
		  });
function saveForm(){
	var flg = true;
	jQuery.each(jQuery(".dateline"),function(i,n){
		var sfkxg=jQuery(n).find("input[name=sfkxg]").val();
		if (i >1 && flg&&"Y"==sfkxg){
			var thsj = jQuery(n).find("input[name=thsjArr]").val();
			var gxlx="";
			jQuery.each(jQuery(n).find("input[type=checkbox]"),function(j,m){
				if(jQuery(m).prop("checked")){
					if(gxlx!=""){
						gxlx+=",";
						}
					gxlx+=jQuery(m).val();
					}
			});
			
			var gxsj = jQuery(n).find("input[name=gzlx]").val();
			var thnr = jQuery(n).find("textarea[name=thnrArr]").val();
			var cljg = jQuery(n).find("textarea[name=cljgArr]").val();
			var ftr =  jQuery(n).find("input[name=ftrArr]").val();
			 jQuery(n).find("input[name=gxlxArr]").val(gxlx);
			flg = (gxlx!="" &&  thsj !="" && gxsj !=""  && thnr !="" && cljg !=""&&ftr !="");
		}
	});
	if (jQuery.trim(jQuery("#xh").val()) == "" || !flg||"undefined"==typeof(jQuery("[name='gzlx']:checked").val())){
		showAlert("请将必填项填写完整。");
		return false;
	}
	if("取消关注"==jQuery("[name='gzlx']:checked").val()&&""==jQuery("#qxyy").val().trim()){
		showAlert("请将必填项填写完整。");
		return false;
		}
	var url = "zdxljkTbxs.do?method=saveThjl";
	ajaxSubFormWithFun("tbxsForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			refershParent();
		}});
		
	});
}

//关注类型切换事件
function gzlxChange(obj){
	var gzlx = jQuery(obj).val();
	if("取消关注"==gzlx){
		jQuery(obj).parents(".gzlxTr").next("tr").css("display","");
	}else{
		jQuery(obj).parents(".gzlxTr").next("tr").find("[name='qxyyArr']").val("");
		jQuery(obj).parents(".gzlxTr").next("tr").css("display","none");
	}
}

function addRow(){
	var tr = jQuery("#dataTbody tr:eq(3)").clone();
	jQuery("#dataTbody").append(tr.show());
	var index = tr.index();
	tr.find("input[name=thsjArr]").attr("id","trsj"+index);
	tr.find("input[name=gxsjArr]").attr("id","gxsj"+index);
	//tr.find("input[name=gzsjArr]").attr("id","gzsj"+index);
}

function delRow(obj){
	jQuery(obj).parents("tr:eq(0)").remove();
}

function gxlxFormatter(){
	jQuery.each(jQuery(".dateline"),function(i,n){
	if(jQuery(n).find("input[name=gxlxArr]").val()){
		var dataObj = jQuery(n).find("input[name=gxlxArr]").val().split(",");
		jQuery.each(jQuery(n).find("input[type=checkbox]"),function(j,m){
			for ( var i = 0; i < dataObj.length; i++) {
				var tjObj=dataObj[i];
				if(tjObj==jQuery(m).val()){
					jQuery(m).attr("checked",true);
				}
				
			}
		});
	}
	});
}