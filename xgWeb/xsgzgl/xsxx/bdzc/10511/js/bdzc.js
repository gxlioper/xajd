//加载学生信息
function getStuInfo(){
	jQuery.ajaxSetup({async:false});
	var parameter = {};
	parameter["xh"]=escape(jQuery("#xh").val());
	var url="xsxx_xqbdzcgl.do?method=dtXqbdzc";
	var message="";
	jQuery.getJSON(url,parameter,
			function(data){
				if(data!=null){
					jQuery("#xm").html(data.xm);
					jQuery("#nj").html(data.nj);
					jQuery("#xymc").html(data.xymc);
					jQuery("#zymc").html(data.zymc);
					jQuery("#bjmc").html(data.bjmc);
					jQuery("#zzmm").html(data.ldmc);
					jQuery("#mz").html(data.qsh);
					jQuery("#yhmc").html(data.cwh);
					jQuery("#yhkh").html(data.qsdh);
					changeTqs();
				}
			}
		);
	jQuery.ajaxSetup({async:true});
}

//保存注册
//zczt 注册状态 1 注册 -1 未注册
function saveForm(zczt,bdZc){
	var api = frameElement.api;
	W = api.opener;
//	else if(zczt == '0'){   当初为什么填写这个我不知道，暂时删除
//		message="该学生"+bdZc+"已撤销,请确认!";
//		showAlert(message);
//		return false;
//	}
	
	
	var xh = jQuery("#xh").val();
	var xn = jQuery("input[name='searchXn']").val();
	var xq = jQuery("input[name='searchXq']").val();
	var zcsj = jQuery("#zcsj").val();
	if("" == xh){
		showAlert("学号不能为空");
		return false;
	}
	if("" == zcsj){
		showAlert("注册时间不能为空！");
		return false;
	}
	
	 var url = "xsxx_xqbdzcgl.do?method=doDtZc";
     
	 ajaxSubFormWithFun("xqbdzcForm",url,function(data){
    	  
    	  if (data["success"] == "false"){
    		  showAlert(bdZc+"成功！");
    	  } else {
    		  showAlert(data["message"],{},{"clkFun":function(){
        			if (parent.window){
        			 W.jQuery("#dataTable").reloadGrid();
        			 iFClose();
        			}
      		  }});
    	  }
    	  
      });
}


//单个注册
function dgzc(xh,bdZc,zczt){
	if("false"==jQuery("#zczt").val()){
		message = "当前"+bdZc+"已关闭，请联系管理员！";
		showAlertDivLayer(message);
		return false;
	}
	if(zczt == '1' ){
		message="该学生已经"+bdZc+",请确认!";
		showAlert(message);
		return false;
	}
	var url = "xsxx_xqbdzcgl.do?method=dtXqbdzc&xh="+xh;
	var title = "学期"+bdZc;
	showDialog(title,800,400,url);
}



//批量注册
function plzc(bdZc){
	if("false"==jQuery("#zckg").val()){
		message = "当前"+bdZc+"已关闭，请联系管理员！";
		showAlertDivLayer(message);
		return false;
	}
	var xn2 = jQuery("#xn").val();
	var xq2 = jQuery("#xq").val();
	var search_xn = jQuery("a[name='a_name_xn']").attr("id").replace("a_id_","");
	var search_xq = jQuery("a[name='a_name_xq']").attr("id").replace("a_id_","");
	if(xn2 != search_xn || xq2 != search_xq){
		showAlertDivLayer("非当前学年学期数据不能进行操作！" );
		return false;
	}
	var rows = jQuery('#dataTable').getSeletRow();
	if(rows.length == 1){
		xh = rows[0].xh;
		zczt = rows[0].zczt;
		return dgzc(xh,bdZc,zczt);
	}
	if (rows.length == 0){
		if(jQuery("#dataTable").getRowCount() == '0'){
				message = "没有学生可"+bdZc+"，请重新查询！";
				showAlertDivLayer(message);
				return ;
			}
		var url = "xsxx_xqbdzcgl.do?method=plXqbdzc&type=zc_all&path=xsxx_xqbdzc.do";
		var map = getSuperSearch();
//		//高级查询
//		url +="&searchTj=";
//		url +=map["searchTj"];
//		url +="&searchTjz=";
//		url +=map["searchTjz"];
//		url +="&mhcx_lx=";
//		url +=map["mhcx_lx"];
//		url +="&searchLx=";
//		url +=map["searchLx"];
//
//		//模糊查询
//		url +="&input_mhcx=";
//		url +=map["input_mhcx"];
//		url +="&mhcx_lx=";
//		url +=map["mhcx_lx"];
//		
//		showDialog("批量"+bdZc,500,280,url);
		map["zcsj"] = jQuery('#zcsj').val();
		jQuery.post(url,map,
		function(data){
			url ="xsxx_xqbdzcgl.do?method=plXqbdzc&path=xsxx_xqbdzc.do&rownum_w=" + data["rownum_w"];
			url +="&rownum_x=0" +"+&rownum_wx="+ data["rownum_w"];
			showDialog("批量"+bdZc,500,280,url);
		},'json');
		
	}else{
		
		var er = true;
		var rownum_x = 0;
		var rownum_w = 0;
		for(var i =0 ;i < rows.length ; i++){
			var zt = rows[i]['zczt'];
			if(zt == '1'){
				er	=  false;
				break;
			}if(zt == '0'){
				rownum_x++;
			}else{
				rownum_w++;
			}
		}
		if(!er){
			message = "选中的记录存在已"+bdZc+"，请确认！";
			showAlertDivLayer(message);
			return false;
		}
		
		var rownum_wx = rownum_x + rownum_w;
		var url ="xsxx_xqbdzcgl.do?method=plXqbdzc&type=zc_select&rownum_w=" + rownum_w +"&rownum_x="+ rownum_x +"&rownum_wx="+ rownum_wx;
		
		showDialog("批量"+bdZc,500,280,url);
	}
	
}
function wbdyywh(bdZc){
	
	var xn2 = jQuery("#xn").val();
	var xq2 = jQuery("#xq").val();
	var search_xn = jQuery("a[name='a_name_xn']").attr("id").replace("a_id_","");
	var search_xq = jQuery("a[name='a_name_xq']").attr("id").replace("a_id_","");
	if(xn2 != search_xn || xq2 != search_xq){
		showAlertDivLayer("非当前学年学期数据不能进行操作！" );
		return false;
	}
	var rows = jQuery('#dataTable').getSeletRow();
	if (rows.length == 0){
		showAlertDivLayer("请选择需要操作的学生！");
		return false;
	}else{
		var er = true;
		for(var i =0 ;i < rows.length ; i++){
			var zt = rows[i]['zczt'];
			if(zt == '1'){
				er	=  false;
				break;
			}
		}
		if(!er){
			message = "选中的记录存在已"+bdZc+"的学生，请确认！";
			showAlertDivLayer(message);
			return false;
		}
		var url = "xsxx_xqbdzcgl.do?method=wbdyywh";
		var title = "未"+bdZc+"原因维护";
		showDialog(title,550,250,url);
		
	}
}

function saveWbdyy(wbdyy,wbdlbdm,yjbdsj){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = new Array();
	jQuery.each(rows,function(i,row){
		ids.push(row["xh"]);
	});
	jQuery.post(
		"xsxx_xqbdzcgl.do?method=saveWbdyy",
		{plIds:ids.join(","),wbdyy:wbdyy,wbdlbdm:wbdlbdm,yjbdsj:yjbdsj},
		function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				jQuery("#dataTable").reloadGrid();
			}});
		},
		'json'
	)
}

//撤销注册
function plcx(bdZc){
	var xn2 = jQuery("#xn").val();
	var xq2 = jQuery("#xq").val();
	var search_xn = jQuery("a[name='a_name_xn']").attr("id").replace("a_id_","");
	var search_xq = jQuery("a[name='a_name_xq']").attr("id").replace("a_id_","");
	if(xn2 != search_xn || xq2 != search_xq){
		showAlertDivLayer("非当前学年学期数据不能进行操作！" );
		return false;
	}
	var rows = jQuery('#dataTable').getSeletRow();
	
	/*if(rows.length == 0){
		showAlertDivLayer("请至少选择一条您要撤销的记录！");
		return false;
	}*/
	
	if (rows.length == 0){
		if(jQuery("#dataTable").getRowCount() == '0'){
				showAlertDivLayer("没有学生可撤销，请重新查询！");
				return ;
			}
		var url = "xsxx_xqbdzcgl.do?method=cxXqbdzc&type=cx_all&path=xsxx_xqbdzc.do";
		var map = getSuperSearch();
//		//高级查询
//		url +="&searchTj=";
//		url +=map["searchTj"];
//		url +="&searchTjz=";
//		url +=map["searchTjz"];
//		url +="&mhcx_lx=";
//		url +=map["mhcx_lx"];
//		url +="&searchLx=";
//		url +=map["searchLx"];
//
//		//模糊查询
//		url +="&input_mhcx=";
//		url +=map["input_mhcx"];
//		url +="&mhcx_lx=";
//		url +=map["mhcx_lx"];
//		
//		showDialog("批量撤销",400,180,url);
		map["zcsj"] = jQuery('#zcsj').val();
		jQuery.post(url,map,
			function(data){
				url ="xsxx_xqbdzcgl.do?method=cxXqbdzc&path=xsxx_xqbdzc.do&rownum_t=" + data["rownum_t"];
				url +="&rownum_y="+ data["rownum_y"] +"+&rownum_w="+ data["rownum_w"]+"+&rownum_x="+ data["rownum_x"];
				showDialog("批量撤销",400,180,url);
			},'json');
		
	}else{
		
		var er = true;
		for(var i =0 ;i < rows.length ; i++){
			var zt = rows[i]['zczt'];
			if(zt != '1'){
				er	=  false;
				break;
			}
		}
		if(!er){
			message = "选中的记录存在未"+bdZc+"或者未处理学生，请确认！";
			showAlertDivLayer(message);
			return false;
		}
		var url = "xsxx_xqbdzcgl.do?method=cxXqbdzc&type=cx_select&rownum_t=" + rows.length + "&rownum_y="+ rows.length +"+&rownum_w=0" +"+&rownum_x=0";
		var title = "批量撤销";
		showDialog(title,400,180,url);
	}

}

//查看
function ckzc(bdZc){
	
	var rows = jQuery('#dataTable').getSeletRow();
	
	var xh = '';
	var xn = jQuery("input[name='searchXn']").val();
	var xq = jQuery("input[name='searchXq']").val();
	
	if(rows.length !=1){
		showAlertDivLayer("请选择一条您要查看的记录！");
		return false;
	}
	xh = rows[0].xh;
	var url = "xsxx_xqbdzcgl.do?method=ckXqbdzc&xh=" + xh + "&xn=" + xn + "&xq=" + xq;
	var title = "学期"+bdZc;
	showDialog(title,800,400,url);
}



//导出
function exportConfig(){
	var DCCLBH='xsxx_xqbdzcgl.do';
	customExport(DCCLBH, exportData);
}
function exportData(){
	var DCCLBH='xsxx_xqbdzcgl.do';
	setSearchTj();//设置高级查询条件
	var url = "xsxx_xqbdzcgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//高级查询
function searchRs(){
	
	var search_xq = jQuery("a[name='a_name_xq']");
	
	var search_xn = jQuery("a[name='a_name_xn']");
	
	if(search_xq.length != 1 || search_xn.length != 1){
		showAlertDivLayer("请选择一个学年和一个学期！",{},{});
		return false;
	}
	
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
