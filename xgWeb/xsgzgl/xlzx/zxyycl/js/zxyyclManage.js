var sysDate = new Date().getTime();
		
			var gridSetting = {
				caption:"预约咨询列表",
				pager:"pager",
				url:"xlzx_yysq.do?method=yysqManage&doType=query",
				colList:[
					//{label:'序号',name:'r', index: 'r',width:'4%'},
					{label:'预约编号',name:'id', index: 'id',key:true,hidden:true},
				   {label:'学号',name:'xh', index: 'xh',formatter:xhLink},
				   {label:'姓名',name:'xsxm', index: 'xsxm'},
				  // {label:'性别',name:'xsxb', index: 'xsxb',width:'4%'},
				   // {label:'职工号',name:'zgh', index: 'zgh',width:'6%'},
				  /* {label:'咨询师',name:'zxsxm', index: 'zxsxm'},
				   {label:'所在部门',name:'bmmc', index: 'bmmc'},*/
				   {label:'预约咨询日期',name:'yyzxrq', index: 'yyzxrq'},
				   {label:'预约状态code',name:'status', index: 'status',hidden:true},
				   {label:'预约状态',name:'statusmc', index: 'status',formatter:getYyColor},
				   {label:'咨询安排日期',name:'zxrq', index: 'zxrq'},
				    {label:'咨询状态',name:'zxzt', index: 'zxzt',hidden:true},
				   {label:'咨询状态',name:'zxztmc', index: 'zxztmc',formatter:getZxColor},
				    {label:'咨询评价',name:'xspjzt', index: 'xspjzt',hidden:true},
				   {label:'咨询评价',name:'pjztmc', index: 'pjztmc',width:'6%',formatter:getPjColor},
				   {label:'学生申请',name:'yyzxzt', index: 'yyzxzt',align:'center',formatter:getSfxssq}
				],
				sortname: "zxrq,yyzxrq",
			 	sortorder: "desc"
			};
			
			function getSfxssq(cellValue,rowObject){
				if(!cellValue){
					return "否";
				}else{
					return "是";
				}
			}
			
			function xhLink(cellValue,rowObject){
				return "<a href=\"javascript:void(0);\" class=\"name\" onclick=\"showDetail('"+rowObject["id"]+"');\">"+cellValue+"</a>";
			}
			
			function showDetail(id){
				showDialog("预约咨询详情",750,560,"xlzx_yysq.do?method=yyzxDetail&doType=view&id="+id);
			}
			
			
			function getYyColor(cellValue,rowObject){
					if(rowObject["status"]=="1"){
						return "<font color='blue'>"+cellValue+"</font>";
					}else if(rowObject["status"]=="2"){
						return "<font color='red'>"+cellValue+"</font>";
					}else{
						return cellValue;
					}
			}
			
			function getZxColor(cellValue,rowObject){
				if(rowObject["zxzt"]=="1"){
					return "<font color='blue'>"+cellValue+"</font>";
				}else if(rowObject["zxzt"]=="2" || rowObject["zxzt"]=="3"){
					return "<font color='red'>"+cellValue+"</font>";
				}else{
					return cellValue;
				}
			}
			
			function getPjColor(cellValue,rowObject){
				if(rowObject["xspjzt"]=="1"){
					return "<font color='blue'>"+cellValue+"</font>";
				}else if(rowObject["xspjzt"]=="2"){
					return "<font color='red'>"+cellValue+"</font>";
				}
			}
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();

				jQuery("#dataTable").reloadGrid(map);
			}

			function addYyzxInfo(){
				showDialog("新增咨询",750,520,"xlzx_yysq.do?method=addYyzxInfo");
			}
			
			// 删除预约咨询
			function delYyzxInfo(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0) {
					showAlert("请选择您要删除的记录！");
				} else {
					var rows = jQuery("#dataTable").getSeletRow();
					
					for(var i=0;i<ids.length;i++){
						if(rows[i]['yyzxzt'] || rows[i]['zxzt']=='2'){
							showAlert("已咨询或者学生申请的记录不能删除！");
							return false;
						}
					}
					
					showConfirmDivLayer("您确定要删除选择的记录吗？", {"okFun" : function() {
							jQuery.post("xlzx_yysq.do?method=delYyzxInfo", {
								values : ids.toString()
							}, function(data) {
								showAlert(data["message"],{},{"clkFun":function(){
									searchRs();
								}});
							}, 'json');
							
					}});
				}
			}
			function ckYyzxInfo(){
				var id= '';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				 if(rowsValue.length != 1){
					showAlert("请选择一条您要查看的记录！");
					return false;
				}else{
					id = rowsValue[0]["id"];
				}
				showDialog("预约咨询详情",750,560,"xlzx_yysq.do?method=yyzxDetail&doType=view&id="+id);
			}	
			
			function updateYyzxInfo(){
				var id= '';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				 if(rowsValue.length != 1){
					showAlert("请选择一条您要反馈的记录！");
					return false;
				}else{
					if(rowsValue[0]["status"]=="3" ||rowsValue[0]["status"]=="4" || (rowsValue[0]["status"]=="2"&& rowsValue[0]["zxzt"]!="1" )){
						showAlert("该条记录不能反馈！");
						return false;
					}
					id = rowsValue[0]["id"];
				}
				showDialog("预约反馈",750,560,"xlzx_yysq.do?method=yyzxDetail&doType=yyfk&id="+id);
			}
			
			function zxfkInfo(){
				var id= '';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				 if(rowsValue.length != 1){
					showAlert("请选择一条您要反馈的记录！");
					return false;
				}else{
					if(rowsValue[0]["status"]!="2"){
						showAlert("不能反馈该条预约！");
						return false;
					}
					if(rowsValue[0]["xspjzt"]=="2"){
						return showAlert("该咨询学生已经评价、无法修改咨询反馈！");
					}
					id = rowsValue[0]["id"];
				}
				showDialog("咨询反馈",750,560,"xlzx_yysq.do?method=yyzxDetail&doType=zxfk&id="+id);
			}
			

			function exportConfig() {
				customExport("xlzx_zxyycl_zxyycl.do", exportData,750,500);
			}
			// 导出方法
			function exportData() {
				setSearchTj();//设置高级查询条件
				var url = "xlzx_yysq.do?method=exportData&dcclbh=xlzx_zxyycl_zxyycl.do";//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
