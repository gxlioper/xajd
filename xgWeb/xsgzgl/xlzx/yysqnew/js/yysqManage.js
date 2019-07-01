			var gridSetting = {
				caption:"预约咨询列表",
				pager:"pager",
				url:"xlzx_yysqnew.do?method=yysqManage&doType=query",
				colList:[
					{label:'预约编号',name:'id', index: 'id',hidden:true},
				   {label:'学号',name:'xh', index: 'xh',formatter:xhLink},
				   {label:'姓名',name:'xsxm', index: 'xsxm'},
				   {label:'性别',name:'xsxb', index: 'xsxb'},
				   {label:'咨询师姓名',name:'zxsxm', index: 'zxsxm'},
				   {label:'预约咨询日期',name:'yyzxrq', index: 'yyzxrq'},
				   {label:'预约状态code',name:'status', index: 'status',hidden:true},
				   {label:'预约状态',name:'statusmc', index: 'status',formatter:getYyColor},
				   {label:'咨询安排时间',name:'zxrq', index: 'zxrq'},
				   {label:'zxzt',name:'zxzt', index: 'zxzt',hidden:true},
				   {label:'咨询状态',name:'zxztmc', index: 'zxztmc'},
				   {label:'咨询评价',name:'xspjzt', index: 'xspjzt',hidden:true},
				   {label:'咨询评价',name:'pjztmc', index: 'pjztmc',formatter:getPjColor}
				],
				sortname: "zxrq,yyzxrq",
			 	sortorder: "desc"
			};
			
			function xhLink(cellValue,rowObject){
				return "<a href=\"javascript:void(0);\" class=\"name\" onclick=\"showDetail('"+rowObject["id"]+"');\">"+cellValue+"</a>";
			}
			
			function showDetail(id){
				showDialog("预约咨询详情",750,500,"xlzx_yysqnew.do?method=yyzxDetail&doType=view&id="+id);
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
			function getPjColor(cellValue,rowObject){
					if(rowObject["xspjzt"]=="1"){
						return "<font color='blue'>"+cellValue+"</font>";
					}else if(rowObject["xspjzt"]=="2"){
						return "<font color='red'>"+cellValue+"</font>";
					}else{
						return cellValue;
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
				showDialog("申请预约",750,580,"xlzx_zxspb.do?method=zxsPbbForXs");
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
				showDialog("查看预约详情",750,500,"xlzx_yysqnew.do?method=yyzxDetail&doType=view&id="+id);
			}	
			function updateYyzxInfo(){
				var id= '';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				 if(rowsValue.length != 1){
					showAlert("请选择一条您要修改的记录！");
					return false;
				}else{
					if(rowsValue[0]["status"]!="1"){
						showAlert("只能修改【预约中】的记录！");
						return false;
					}
					id = rowsValue[0]["id"];
				}
				showDialog("修改预约申请信息",750,500,"xlzx_yysqnew.do?method=yyzxDetail&doType=update&id="+id);
			}
			//取消預約
			function qxYyzxInfo(){
				var id= '';
				var status='';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				 if(rowsValue.length != 1){
					showAlert("请选择一条您要取消的预约！");
					return false;
				}else{

					if(rowsValue[0]["status"]=="1"){
						status = "3";
					}else if(rowsValue[0]["status"]=="2" && rowsValue[0]["zxzt"]=="1"){//预约成功，但未咨询才可取消。（线下老师忘了反馈咨询，咨询状态待咨询，则也可取消）
						status = "4";
					}else if(rowsValue[0]["status"]=="3" || rowsValue[0]["status"]=="4"){
						return showAlert("不能取消该条预约！");
					}else{
						return showAlert("不能取消该条预约！");
					}
					id = rowsValue[0]["id"];
				}
				 
				var url ="xlzx_yysqnew.do?method=updateYysqInfo";
				var parameter={id:id,status:status};
				
				showConfirm("确认取消预约？",{"okFun":function(){
					jQuery.ajaxSetup({async:false});
					jQuery.post(url,parameter,function(result){
								if(data = true){
									showAlert("预约取消成功！",{},{"clkFun":function(){
										searchRs();
									}});
								}else{
									showAlert("预约取消失败！");
								}
						},"json"
					);
					jQuery.ajaxSetup({async:true});
				}});
			}
			
			function pjYyzxInfo(){
				var id= '';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				 if(rowsValue.length != 1){
					showAlert("请选择一条您要评价的记录！");
					return false;
				}else{
					if(rowsValue[0]["xspjzt"]!="1"){
						showAlert("不能评价该条预约记录！");
						return false;
					}
					id = rowsValue[0]["id"];
				}
				showDialog("咨询评价",750,160,"xlzx_yysqnew.do?method=xspjInfo&doType=update&id="+id);
			}
			