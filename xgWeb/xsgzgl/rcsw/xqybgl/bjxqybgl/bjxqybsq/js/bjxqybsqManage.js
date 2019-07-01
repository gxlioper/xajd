		jQuery(function(){
			var gridSetting = {
					caption:"班级学情月报列表",
					pager:"pager",
					url:"rcsw_xqybgl_bjxqybgl_bjxqybsqgl.do?method=bjxqybsqManage&type=query",
					params:getSuperSearch(),
					colList:[
					   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},					   
					   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
					   {label:'审核状态',name:'shzt', index: 'shzt',hidden:true},
					   {label:'班级',name:'bjdm', index: 'bjdm',hidden:true},
					   {label:'学年',name:'xn', index: 'xn',width:'12%'},
					   {label:'学期',name:'xqmc', index: 'xqmc',width:'10%'},
					   {label:'月份',name:'ny', index: 'ny',width:'10%',formatter:xhLink},
					   {label:'学院',name:'xymc', index: 'xymc',width:'15%'},
					   {label:'班级',name:'bjmc', index: 'bjmc',width:'18%'},					   
					   {label:'填写人',name:'lrrxm', index: 'txr',width:'10%'},
					   {label:'填写时间',name:'txsj', index: 'txsj',width:'10%'},
					   {label:'审核状态',name:'shztmc', index: 'shzt',width:'8%'}
					],
					sortname: "txsj",
				 	sortorder: "desc"
				}
			jQuery("#dataTable").initGrid(gridSetting);
			
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				jQuery("#prompt_isopen").show();
				jQuery("#prompt_null_isopen").show();
				return false;
			}
			if ("false" == isopen){
				jQuery("#prompt_isopen").show();
				jQuery("#prompt_false_isopen").show();
				return false;
			}
		});

		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
			
		}


		
		function submitBusi(){
			var isopen = jQuery("#isopen").val();
			
			if(isopen==null||isopen==''){
				showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
				return false;
			}
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length != 1){
				if ("false" == isopen){
					showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
					return false;
				}
				showAlertDivLayer("请选择一条您要提交的记录！");
			}else{
				var rows = jQuery("#dataTable").getSeletRow();
				if ('3'!=rows[0]["shzt"] && "false" == isopen){
					showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
					return false;
				}
				var url = "";
				for(var i=0;i<ids.length;i++){
					if(rows[i]['shzt']!='3'&& "true"!= isopen){
						showAlertDivLayer("当前未开放申请，请联系管理员！");
						return false;
					}
					if(rows[i]['shzt']!='0' && rows[i]['shzt']!='3' ){
						showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
						return false;
					}
					if(rows[i]['shzt']!='3'){
						url = "rcsw_xqybgl_bjxqybgl_bjxqybsqgl.do?method=submitBjxqybsq";
					}else{
						url = "rcsw_xqybgl_bjxqybgl_bjxqybsqgl.do?method=submitBjxqybsq";
					}
				}

				showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
					jQuery.post(url,
						{
						 values:ids.toString(),
						 bjdm : rows[0]['bjdm'],
						 splc : rows[0]['splc'],
						 shzt : rows[0]['shzt']
						},function(data){
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}});
			}
		}

		function cancel(){
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
				return false;
			}
			if ("false" == isopen){
				showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
				return false;
			}
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length == 0) {
				showAlertDivLayer("请选择您要撤销的记录！");
			} else if (ids.length >1 ) {
				showAlertDivLayer("请选择一条您要撤销的记录！");
			} else {
				var rows = jQuery("#dataTable").getSeletRow();
				for(var i=0;i<ids.length;i++){
					if(rows[i]['shzt']!='5'){
						showAlertDivLayer("只有审核中的记录才能被撤销！");
						return false;
					}
				}
				showConfirmDivLayer("您确定要撤销选择的记录吗？",{"okFun":function(){
					jQuery.post("rcsw_xqybgl_bjxqybgl_bjxqybsqgl.do?method=cancelBjxqybsq",
						{
						 values:ids.toString(),
						 splcid : rows[0]['splc'] 
						},function(data){
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}});
			}
		}

		function xqybsqLcinfo(){
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if (ids.length != 1){
				showAlertDivLayer("请选择一条流程跟踪记录！");
			} else {	
				var shzt = rows[0]["shzt"];
				if ("0" == shzt){
					showAlertDivLayer(jQuery("#lable_wxglcxx").val());
					return false;
				}	
				showDialog("班级学情月报管理审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
			}
		}

		function add(){
			
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
				return false;
			}
			if ("false" == isopen){
				showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
				return false;
			}
			var url = "rcsw_xqybgl_bjxqybgl_bjxqybsqgl.do?method=addBjxqybsq";
			var title = "班级月报申请填写";
			showDialog(title,790,450,url);
			
		}

		function update() {
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
				return false;
			}
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1) {
				showAlertDivLayer("请选择一条您要修改的记录！");
			} else {				
				var shzt = rows[0]["shzt"];
				if (shzt=='0'||shzt=='3'){
					var url = 'rcsw_xqybgl_bjxqybgl_bjxqybsqgl.do?method=updateBjxqybsq&sqid=' + rows[0]["sqid"];
					showDialog("班级月报申请修改", 720, 450, url);
				}else{
					showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
					return false;
				}
			}
		}

		//删除
		function del() {
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length == 0) {
				showAlertDivLayer("请选择您要删除的记录！");
			} else {
				var rows = jQuery("#dataTable").getSeletRow();
				showConfirmDivLayer("您确定要删除选择的记录吗？", {
					"okFun" : function() {
						jQuery.post("rcsw_xqybgl_bjxqybgl_bjxqybsqgl.do?method=delBjxqybsq", {
							values : ids.toString()
						}, function(data) {
							var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
							mes+="</br>";
							if(data["nodel"]!="-1"){
								mes+="<font color='red'>"+data["nodel"]+"</font>";
								mes+="的申请已经提交不能删除!";
							}
							showAlertDivLayer(mes);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
					}
				});
			}
		}

		function viewXqybsq(sqid,bjdm) {
			showDialog("班级学情月报详情", 720, 520, "rcsw_xqybgl_bjxqybgl_bjxqybsqgl.do?method=viewXqybsq&sqid=" + sqid
					+ "&bjdm=" + bjdm);
		}

		function xhLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='viewXqybsq(\""
					+ rowObject["sqid"] + "\",\"" + rowObject["bjdm"] + "\");'>" + cellValue
					+ "</a>";
		}

		var DCCLBH = "rcsw_xqybgl_bjxqybgl_bjxqybsq.do";//dcclbh,导出功能编号

		//自定义导出 功能
		function exportConfig() {
			//DCCLBH导出功能编号,执行导出函数 
			customExport(DCCLBH, bjybsqExportData);
		}

		// 导出方法
		function bjybsqExportData() {			
			setSearchTj();//设置高级查询条件
			var url = "rcsw_xqybgl_bjxqybgl_bjxqybsqgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();			
		}