
		jQuery(function() {
			var gridSetting = {
					caption:"返校维护结果列表",
			pager:"pager",
			params:getSuperSearch(),
			url:"jlkjxy_jqfxwh.do?method=jqfxwhManage&type=query",
			colList:[	
			   {label:'key',name:'id', index: 'id',key:true ,hidden:true},					   		  
			   {label:'楼栋名称',name:'ldmc', index: 'ldmc',width:'10%'},
			   {label:'寝室号',name:'qsh', index: 'qsh',width:'7%'},
			   {label:'床位号',name:'cwh', index: 'cwh',width:'5%'},
			   {label:'性别',name:'qsxb', index: 'qsxb',width:'5%'},
			   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
			   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
			   {label:'专业名称',name:'xszymc', index: 'xszymc',width:'10%'},
			   {label:'返校状态',name:'fxztmc', index: 'fxztmc',width:'6%'},
			   {label:'fxzt',name:'fxzt', index: 'fxzt',hidden:true},
			   {label:'返校时间',name:'fxsj', index: 'fxsj',width:'10%'}
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
				
		function xhLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='xwjgView(\""
					+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
					+ "</a>";
		}

		var DCCLBH = "jlkjxy_jqfxwh.do";// dcclbh,导出功能编号

		// 自定义导出 功能
		function exportConfig() {
			// DCCLBH导出功能编号,执行导出函数
			customExport(DCCLBH, fxglExportData);
		}

		// 导出方法
		function fxglExportData() {
			setSearchTj();// 设置高级查询条件
			var url = "jlkjxy_jqfxwh.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,导出功能编号
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
			showDialog("查看返校处理", 720, 498,
					"jlkjxy_jqfxwh.do?method=viewxsJqfx&id=" + id + "&xh=" + xh);			
		}				
		
		function viewfxgl(){
			
			var rows = jQuery("#dataTable").getSeletRow();			
			if (rows.length != 1) {
				showAlertDivLayer("请选择一条您要查看的记录！");
			}else {
				var url = 'jlkjxy_jqfxwh.do?method=viewxsJqfx&id='
						+ rows[0]["id"]+'&xh='+rows[0]["xh"];				
				var title = "查看返校处理";
				showDialog(title, 720, 498, url);
			}
			
		}
		

	


		// 批量审核保存
		function savedgPlsh(fxsj){
			var rows = jQuery("#dataTable").getSeletRow();
			var xhs = new Array();	
			jQuery.each(rows,function(i,row){
				xhs.push(row["xh"]);			
			});
			jQuery.post("jlkjxy_jqfxwh.do?method=pldgxsJqfx&type=save"+'&fxsj='+fxsj,{				
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
				var url = 'jlkjxy_jqfxwh.do?method=addplxsJqwfx';																					
				var title = "未返校批量处理";
				showDialog(title, 529,220, url);
				return false;
			}else{														
				var fxzt = rows[0]["fxzt"];													
			    if(jQuery.trim(fxzt) == "0" || jQuery.trim(fxzt) == "1"){
			        var url = 'jlkjxy_jqfxwh.do?method=addxsJqwfx&id='
					    + rows[0]["id"]+'&xh='+rows[0]["xh"]+'&fxzt='+rows[0]["fxzt"];				    
			    }else{				  
			    	var url = 'jlkjxy_jqfxwh.do?method=addxsJqwfx&xh='+rows[0]["xh"]+'&fxzt='+rows[0]["fxzt"];				    	
			    }					
				var title = "未返校处理";
				showDialog(title, 720,518, url);												
			}	
			
		}


		// 批量保存假期未返校
		function savedgWfxplsh(wfxyy){	
			
			var rows = jQuery("#dataTable").getSeletRow();
			var xhs = new Array();
				
			jQuery.each(rows,function(i,row){
				xhs.push(row["xh"]);			
			});
			
			jQuery.post("jlkjxy_jqfxwh.do?method=addplxsJqwfx&type=save",{				
				xhs:xhs,
				wfxyy:wfxyy
				},function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						jQuery("#dataTable").reloadGrid();
					}});
				}, 'json');
			
		}
		