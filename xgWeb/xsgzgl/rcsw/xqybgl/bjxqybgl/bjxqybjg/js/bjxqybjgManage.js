jQuery(function(){
				var gridSetting = {
						caption:"班级学情结果列表",
						pager:"pager",
						url:"rcsw_xqybgl_bjxqybgl_bjxqybjggl.do?method=bjxqybjgManage&type=query",
						colList:[
							{label:'key',name:'jgid', index: 'jgid',key:true ,hidden:true},												 						 
							{label:'学年',name:'xn', index: 'xn',width:'12%'},
							{label:'学期',name:'xqmc', index: 'xqmc',width:'10%'},
							{label:'月份',name:'ny', index: 'ny',width:'10%',formatter:xhLink},
							{label:'学院',name:'xymc', index: 'xymc',width:'15%'},
							{label:'班级',name:'bjmc', index: 'bjmc',width:'18%'},
							{label:'填写人',name:'lrrxm', index: 'txr',width:'10%'},
							{label:'填写时间',name:'txsj', index: 'txsj',width:'15%'},
							{label:'数据来源',name:'sjly', index: 'sjly',hidden:true}
						],
						sortname: "txsj",
					 	sortorder: "desc"
				};
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);			
			});
	
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			function add(){
				var url = "rcsw_xqybgl_bjxqybgl_bjxqybjggl.do?method=addBjxqybjg";
				var title = "班级学情月报结果填写";
				showDialog(title,790,460,url);
			}

			function update() {
				var rows = jQuery("#dataTable").getSeletRow();
				var sjly = rows[0]["sjly"];			
				if (rows.length != 1) {
					showAlertDivLayer("请选择一条您要修改的记录！");
				}else if(sjly == '1'){
					showAlertDivLayer("审核流数据，不能修改！");
				}else {
					var url = 'rcsw_xqybgl_bjxqybgl_bjxqybjggl.do?method=updateBjxqybjg&jgid='+ rows[0]["jgid"]
					+ '&bjdm=' + rows[0]["bjdm"];
					var title = "班级学情月报结果修改";
					showDialog(title, 720, 460, url);
				}

			}

			function del(){
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				if (ids.length == 0){
					showAlertDivLayer("请选择您要删除的记录！");
				}else {
					var rows = jQuery("#dataTable").getSeletRow();
					showConfirmDivLayer("您确定要删除选择的记录吗？", {
						"okFun" : function() {
							jQuery.post("rcsw_xqybgl_bjxqybgl_bjxqybjggl.do?method=delBjxqybjg", {
								values : ids.toString()
							}, function(data) {
								var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
								mes+="</br>";
								if(data["nodel"]!="-1"){
									mes+="<font color='red'>"+data["nodel"]+"</font>";
									mes+="已走完审核流不能删除!";
								}
								showAlertDivLayer(mes);
								jQuery("#dataTable").reloadGrid();
							}, 'json');
						}
					});		
				}
			}

	
			var DCCLBH = "rcsw_xqybgl_bjxqybgl_bjxqybjg.do";//dcclbh,导出功能编号
			
			//自定义导出 功能
			function exportConfig() {
				//DCCLBH导出功能编号,执行导出函数 
				customExport(DCCLBH, bjxqybjgExportData);
			}
			
			// 导出方法
			function bjxqybjgExportData() {
				setSearchTj();//设置高级查询条件
				var url = "rcsw_xqybgl_bjxqybgl_bjxqybjggl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			
			//合并导出
			function hbdc() {
				setSearchTj();//设置高级查询条件
				var url = "rcsw_xqybgl_bjxqybgl_bjxqybjggl.do?method=hbdc";
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			
			//查看
			function xhLink(cellValue, rowObject) {
				return "<a href='javascript:void(0);' class='name' onclick='viewXqybjg(\""
						+ rowObject["jgid"] + "\",\"" + rowObject["bjdm"] + "\");'>" + cellValue
						+ "</a>";
			}
			
			function viewXqybjg(jgid,bjdm) {
				showDialog("班级学情月报详情", 720, 520, "rcsw_xqybgl_bjxqybgl_bjxqybjggl.do?method=viewXqybjg&jgid=" + jgid
						+ "&bjdm=" + bjdm);
			}