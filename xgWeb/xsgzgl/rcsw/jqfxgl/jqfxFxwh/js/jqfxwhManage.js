
		jQuery(function() {
			var gridSetting = {
					caption:"返校维护结果列表",
			pager:"pager",
			params:getSuperSearch(),
			url:"rcsw_jqfxFxwh.do?method=jqfxwhManage&type=query",
			colList:[	
			   {label:'key',name:'id', index: 'id',key:true ,hidden:true},					   		  
			   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
			   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
			   {label:'学院',name:'xsxymc', index: 'xszymc',width:'10%'},
			   {label:'专业',name:'xszymc', index: 'xszymc',width:'10%'},
			   {label:'班级',name:'xsbjmc', index: 'xsbjmc',width:'10%'},
			   {label:'返校状态',name:'fxztmc', index: 'fxztmc',width:'5%'},
			   {label:'fxzt',name:'fxzt', index: 'fxzt',hidden:true},
			   {label:'未报道原因',name:'wfxyymc', index: 'wfxyymc',width:'6%'},
			   {label:'预计返校时间',name:'yjfxsj', index: 'yjfxsj',width:'11%'},
			   {label:'wfxyydm',name:'wfxyydm', index: 'wfxyydm',hidden:true},
			   {label:'返校时间',name:'fxsj', index: 'fxsj',width:'11%'}
			   
			],
			sortname: "xh",
		 	sortorder: "desc"
		}
		jQuery("#dataTable").initGrid(gridSetting);
		});

		
		function searchRs() {
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}
				
/*		function xhLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='xwjgView(\""
					+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
					+ "</a>";
		}
*/
		var DCCLBH = "rcsw_jqfx_fxwh.do";// dcclbh,导出功能编号

		// 自定义导出 功能
		function exportConfig() {
			// DCCLBH导出功能编号,执行导出函数
			customExport(DCCLBH, fxglExportData);
		}

		// 导出方法
		function fxglExportData() {
			setSearchTj();// 设置高级查询条件
			var url = "rcsw_jqfxFxwh.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,导出功能编号
			url = addSuperSearchParams(url);// 设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}				

		function xhLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='viewXhfxgl(\""
					+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
					+ "</a>";
		}

		function viewXhfxgl(id, xh) {
			showDialog("查看返校处理", 720, 420,
					"rcsw_jqfxFxwh.do?method=viewxsJqfx&id=" + id + "&xh=" + xh);			
		}				
		
		function viewfxgl(){
			
			var rows = jQuery("#dataTable").getSeletRow();			
			if (rows.length != 1) {
				showAlertDivLayer("请选择一条您要查看的记录！");
			}else {
				var url = 'rcsw_jqfxFxwh.do?method=viewxsJqfx&id='
						+ rows[0]["id"]+'&xh='+rows[0]["xh"];				
				var title = "查看返校处理";
				showDialog(title, 720, 400, url);
			}
			
		}
		

	
		

		// 批量审核保存
		function savePl(fxsj,bz){
			var rows = jQuery("#dataTable").getSeletRow();
			var xhs = new Array();	
			jQuery.each(rows,function(i,row){
				xhs.push(row["xh"]);			
			});
			//传值到后台会乱码，需要两次转码
			bz = encodeURI(bz);
			bz = encodeURI(bz);
			jQuery.post("rcsw_jqfxFxwh.do?method=pldgxsJqfx&type=save"+'&fxsj='+fxsj+'&bz='+bz,{				
				xhs:xhs
				},function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						jQuery("#dataTable").reloadGrid();
					}});
				}, 'json');
		}
		
		function addwfxgl(){
		
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer('基础设置未初始化，请联系管理员！');
				return false;
			}			
			if ("false" == isopen){
				showAlertDivLayer("当前未开放申请，请联系管理员！");
				return false;
			}																	
			var rows = jQuery("#dataTable").getSeletRow();	
			if (rows.length == 0){																				
				showAlertDivLayer("请选择您要修改的记录");																
			}
																													
			if (rows.length > 1){				
				var url = 'rcsw_jqfxFxwh.do?method=addplxsJqwfx';																					
				var title = "未返校批量处理";
				showDialog(title, 529,350, url);
				return false;
			}else{														
				var fxzt = rows[0]["fxzt"];													
			    if(jQuery.trim(fxzt) == "0" || jQuery.trim(fxzt) == "1"){
			        var url = 'rcsw_jqfxFxwh.do?method=addxsJqwfx&id='
					    + rows[0]["id"]+'&xh='+rows[0]["xh"]+'&fxzt='+rows[0]["fxzt"];				    
			    }else{				  
			    	var url = 'rcsw_jqfxFxwh.do?method=addxsJqwfx&xh='+rows[0]["xh"]+'&fxzt='+rows[0]["fxzt"];				    	
			    }					
				var title = "未返校处理";
				showDialog(title, 720,470, url);												
			}	
			
		}


		// 批量保存假期未返校
		function saveWfxplsh(wfxyy,sfqdlx,yjfxsj,bz){	
			var rows = jQuery("#dataTable").getSeletRow();
			var xhs = new Array();
				
			jQuery.each(rows,function(i,row){
				xhs.push(row["xh"]);			
			});
			jQuery.post("rcsw_jqfxFxwh.do?method=addplxsJqwfx&type=save",{				
				xhs:xhs,
				wfxyy:wfxyy,
				sfqdlx:sfqdlx,
				bz:bz,
				yjfxsj:yjfxsj
				},function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						jQuery("#dataTable").reloadGrid();
					}});
				}, 'json');
			
		}
		
		
		// 批量保存假期返校
		function savePlfx(fxsj,bz){	
			bz = encodeURI(bz);
			bz = encodeURI(bz);
			var map = getSuperSearch();
			jQuery.post("rcsw_jqfxFxwh.do?method=plxsJqfx&type=save"+'&fxsj='+fxsj+'&bz='+bz,map,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						jQuery("#dataTable").reloadGrid();
					}});
				}, 'json');				
			
			
		}
		
		//统计表
		function bdqktj(){
			//var url ="rcsw_rcxwwh_rcxwjggl.do?method=sxpdcjhzDc";
			var url="rcsw_jqfxFxwh.do?method=bdqktjPrint";
			
			/*var xnLength=jQuery("a[name=a_name_xn]").length;
			var xmmcLength=jQuery("a[name=a_name_xmmc]").length;
			
			if(xnLength != "1"){
				showAlertDivLayer("请选择一个学年查询条件！");
				return false;
			}
			if(xmmcLength < "1"){
				showAlertDivLayer("请至少选择一个项目名称查询条件！");
				return false;
			}*/
			setSearchTj();
			url = addSuperSearchParams(url);
			document.forms[0].action = url;
			document.forms[0].submit();
	}