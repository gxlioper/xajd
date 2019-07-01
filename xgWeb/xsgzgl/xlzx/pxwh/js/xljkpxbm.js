function pxztLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='pxztView(\""+row["pxid"]+"\")'>"+cellValue+"</a>";
}

function pxztView(pxid){
	showDialog("查看培训详情",600, 400,"xlzx_pxwh.do?method=pxwhView&pxid="+pxid);
}

function bmcz(cellValue,row){
	if(row["sfkfbm"]=="0"){
		return "不可报名";
	}
	return "<a href='javascript:void(0);' class='name' onClick='bm(\""+row["pxid"]+"\",\""+1+"\")'>报名</a>";
}

function qxbmcz(cellValue,row){
	if("1"==row["bmyg"]){
		return "取消报名";
	}
	return "<a href='javascript:void(0);' class='name' onClick='bm(\""+row["pxid"]+"\",\""+0+"\")'>取消报名</a>";
}

function bm(pxid,bmtype){
	var xh=jQuery("#userName").val();
	if("1"==bmtype){
		var message="您确定要预约报名该培训？";
	}else{
		var message="您确定要取消报名该培训？";
	}
	showConfirmDivLayer(message,{"okFun":function(){
		jQuery.ajax({
			url:"xlzx_pxwh.do?method=bmcz",
			data:{xh:xh,bmtype:bmtype,pxid:pxid},
			type:"post",
			dataType:"json",
			success:function(data){
				showAlertDivLayer(data["message"], {}, {
					"clkFun" : function() {
						searchRs();
					}
				});
			}
		});	
	}});
}



//导出方法
function ExportDatas() {
	setSearchTj();//设置高级查询条件
	var url = "zjly_ylbx.do?method=exportData&dcclbh=" + DCCLBH+"&cxblb="+jQuery("#cxblb").val();//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function searchRs(){
	var map = getSuperSearch();	
	var sfybm = jQuery("#sfybm").val();
	map["sfybm"] = sfybm;
	if("1" ==sfybm){
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}else{
		gridSetting1["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting1);
	}
}

// 页签切换
function selectTab(obj,sfybm){
	jQuery("#sfybm").val(sfybm);
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	searchRs();
}	
