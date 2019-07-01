jQuery(function(){
	jQuery("#btn_fh").bind("click",function(){
		document.location.href='khglPfzgl.do?method=getPfzglList';
		
	});
});
function searchRs() {
	var map = getSuperSearch();
	var fpzt = jQuery("#fpzt").val();
	if (null!=fpzt&&fpzt != "") {
		map["fpzt"] = fpzt;
	}else{
		map["fpzt"] = "kfp";
	}
	jQuery("#dataTable").reloadGrid(map);
}
//考核对象详情查看
function yhck(khdxid,khlx,sfnz,khdxmc) {
	showDialog("考核对象查看", 750, 550, "khglKhdxgl.do?method=viewKhdxList&khdxid="
			+ khdxid + "&khlx=" + khlx+"&sfnz="+sfnz+"&khdxmc="+khdxmc);
}

function khdxrsLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='yhck(\""+rowObject["khdxid"]+"\",\""+rowObject["khlx"]+"\",\""+rowObject["khdxsfnz"]+"\",\""+rowObject["khdxmc"]+"\");'>"+cellValue+"</a>";
}
function pfcyStuLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='pfcy(\""+rowObject["xh"]+"\");'>"+cellValue+"</a>";
}
function pfcyTeaLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='pfcy(\""+rowObject["zgh"]+"\");'>"+cellValue+"</a>";
}
//评分组分配
function pfcySz(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var khdxid=jQuery("#khdxid").val();
	var pfzid=jQuery("#pfzid").val();
	var pflx=jQuery("#pflx").val();
	var khlx=jQuery("#khlx").val();
	var zgh="";
	var xh="";
	var sfqx='0';//
	if (ids.length == 0||ids.length>1) {
		sfqx='1';//不选或多选
		zgh="all";
		xh="all";
	}
	if('0'==sfqx){
		if("2"==khlx){//考核对象类型为教师
			zgh=rows[0]["zgh"];
		}else{
			xh=rows[0]["xh"];
		}
	}
	var url="khglPfzgl.do?method=showPfcy&pflx="+pflx+"&sfqx="+sfqx+"&khdxrs="+ids+"&khdxid="+khdxid+"&pfzid="+pfzid+"&zgh="+zgh+"&xh="+xh+"&khlx="+khlx;
	showDialog("评分成员", 800, 600, url);
		 	
}

//评分组按班级分配
function pfcybjSz(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var khdxid=jQuery("#khdxid").val();
	var pfzid=jQuery("#pfzid").val();
	var pflx=jQuery("#pflx").val();
	var khlx=jQuery("#khlx").val();
	var zgh="";
	var sfqx='0';//
	if (ids.length != 1) {
		showAlertDivLayer("请选择一个用户！");
		return false;
	}
	zgh=rows[0]["zgh"];
	var xm =rows[0]["xm"];
	var bmmc =rows[0]["bmmc"];
	var url="khglPfzgl.do?method=showPfcy&pflx="+pflx+"&khdxrs="+ids+"&khdxid="+khdxid+"&pfzid="+pfzid+"&zgh="+zgh+"&khlx="+khlx+"&xm="+xm+"&bmmc="+bmmc;
	url=url+"&bjfp=y";
	showDialog("评分成员班级", 700, 450, url);
		 	
}


//评分成员链接
function pfcy(yhm){
	var pfzid=jQuery("#pfzid").val();
	var pflx=jQuery("#pflx").val();
	var khlx=jQuery("#khlx").val();
	var zgh="";
	var xh="";
	if("2"==khlx){//考核对象类型为教师
		zgh=yhm;
	}else{
		xh=yhm;
	}
	var url="khglPfzgl.do?method=viewPfcy&pflx="+pflx+"&pfzid="+pfzid+"&zgh="+zgh+"&xh="+xh+"&khlx="+khlx;
	
	showDialog("评分成员查看", 800, 600, url);
		 	
}


//导入
function khdxDr(dxlx) {
	// 调用通用的导入function，参数是导入功能模块代码。
	if("1"==dxlx){
		toImportDataNew("IMPORT_N930101_KHDXGL_XS");
	}else{
		toImportDataNew("IMPORT_N930101_KHDXGL_JS");
	}
	return false;

}

//参与评分
function  savePfcy(type){
	var api = frameElement.api,W = api.opener;
	var map = W.getSuperSearch();
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var khdxrs=jQuery("#khdxrs").val();
	var pfzid=jQuery("#pfzid").val();
	var khdxid=jQuery("#khdxid").val();
	var khlx=jQuery("#khlx").val();
	var pflx=jQuery("#pflx").val();
	var sfqx=jQuery("#sfqx").val();//考核对象是否默认全选
	var msg=null;
	var checkMsg ="";
	if(0==ids.length){
		showAlertDivLayer("请选择您需要操作的用户！");
		return false;
	}
	 if("1"!=sfqx){
		 if(type=='del'){
			 for ( var i = 0; i < rows.length; i++) {
					if("1"==rows[i]["sfydf"]){
						if(i!=0){
							checkMsg+=",";
						}
						//考核对象为学生
						if("1"==khlx){
							checkMsg+=rows[i]["xh"];
						}else{
							checkMsg+=rows[i]["zgh"];
						}
					}
					
				}
				if(""!=checkMsg){
					showAlertDivLayer("["+checkMsg+"]"+"已有评分记录，不允许取消评分！");
					return false;
				}
		   msg="是否确定取消选中人员的评分资格?"; 
		 }else{
		   msg="是否确定将选中人员添加至评分成员中?";
		 }
		}
	 else{
		 msg="是否取消原有评分成员的评分资格，添加选中人员?";
	 }
		map["values"] = ids.toString();
		map["pflx"] = pflx;
		map["khlx"] = khlx;
		map["khdxid"] = khdxid;
		map["khdxrs"] = khdxrs;
		map["pfzid"] = pfzid;
		map["sfqx"] = sfqx;
	showConfirmDivLayer(msg, {
		"okFun" : function() {
	jQuery.post("khglPfzgl.do?method=savePfcy&type="+type, map
		, function(data) {
	    		 showAlert(data["message"],{},{"clkFun":function(){
		    			 jQuery("#dataTable").reloadGrid();
		    			 var api = frameElement.api,W = api.opener;
		    				jQuery(W.document).find('#search_go').click();
		    			 
	    			}});
		},"json");
	}});
	
}

//保存评分成员班级
function  savePfcybj(type){
	var api = frameElement.api,W = api.opener;
	var map = W.getSuperSearch();
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var khdxrs=jQuery("#khdxrs").val();
	var pfzid=jQuery("#pfzid").val();
	var khdxid=jQuery("#khdxid").val();
	var pflx=jQuery("#pflx").val();
	var msg=null;
	var checkMsg ="";
	if(type=='del'){
		msg="是否确定取消选中班级的评分资格?"; 
	}else{
		msg="是否确定将选中班级添加至评分成员中?";
	}
	if(0==ids.length){
		showAlertDivLayer("请选择您需要操作的班级！");
		return false;
	}
	map["pfzid"] = pfzid;
	map["khdxrs"] = khdxrs;
	map["values"] = ids.toString();
	showConfirmDivLayer(msg, {
		"okFun" : function() {
	jQuery.post("khglPfzgl.do?method=savePfcy&type="+type+"&bjfp=y", map
		, function(data) {
	    		 showAlert(data["message"],{},{"clkFun":function(){
		    			 //jQuery("#dataTable").reloadGrid();
		    			 //var api = frameElement.api,W = api.opener;
		    			 //jQuery(W.document).find('#search_go').click();
	    				if (parent.window){
	    					refershParent();
	    				}		    			 
	    			}});
		},"json");
	}});
	
}

//清空评分人
function qkpfr(){
	
	var map = getSuperSearch();
	var ids = jQuery("#dataTable").getSeletIds();
	
	var pfzid=jQuery("#pfzid").val();
	var khdxid=jQuery("#khdxid").val();
	var pflx=jQuery("#pflx").val();
	var khlx=jQuery("#khlx").val();
	var sfqx="1";
		map["values"] = ids.toString();
		map["pflx"] = pflx;
		map["khlx"] = khlx;
		map["khdxid"] = khdxid;
		map["khdxrs"] = ids.toString();
		map["pfzid"] = pfzid;
		map["sfqx"] = sfqx;
	showConfirmDivLayer("已打分成员无法清空，是否确定清空未打分成员？", {
		"okFun" : function() {
	jQuery.post("khglPfzgl.do?method=savePfcy&type=del", map
		, function(data) {
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
	    			 jQuery("#dataTable").reloadGrid();
	    			}});
		},"json");
	}});
}

function pfcydr() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var khdxid=jQuery("#khdxid").val();
	var pfzid=jQuery("#pfzid").val();
	if (ids.length != 1) {
		showAlertDivLayer("请选择一个用户！");
		return false;
	}
	var zgh=rows[0]["zgh"];
	var xm =rows[0]["xm"];
	
	var url="khglPfzgl.do?method=pfcydr&khdxid="+khdxid+"&pfzid="+pfzid+"&zgh="+zgh+"&xm="+xm;
	showDialog("评分成员导入", 730, 550, url);
}
