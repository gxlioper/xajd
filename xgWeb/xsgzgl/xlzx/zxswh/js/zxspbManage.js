var gridSetting = {
				caption:"咨询师列表",
				pager:"pager",
				url:"xlzx_zxspb.do?method=zxspbManage&doType=query",
				colList:[
				   {label:'序号',name:'r', index: 'r',width:'4%',formatter:zghpbLink},
				   {label:'主键',name:'id', index: 'id',width:'4%',hidden:true},
				   {label:'星期',name:'weekday', index: 'weekday',width:'8%'},
				   {label:'排班日期',name:'pbdate', index: 'pbdate',width:'8%'},
				   {label:'排班时间段',name:'sjddm', index: 'sjddm',width:'8%',hidden:true},
				   {label:'排班时间段',name:'sjdmc', index: 'sjdmc',width:'8%'},
				   {label:'职工号',name:'zgh', index: 'zgh',width:'8%',formatter:zghLink},
				   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
				   {label:'联系电话',name:'lxdh', index: 'lxdh',width:'10%'},
				   {label:'部门',name:'bmmc', index: 'bmmc',width:'10%'}
				],
				sortname: "pbdate,zgh",
			 	sortorder: "desc"
			};
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();

				jQuery("#dataTable").reloadGrid(map);
			}
			
			function zghpbLink(cellValue,rowObject){
			
				return "<a href='javascript:void(0);' class='name' onclick='showPbDetail(\""+rowObject["id"]+"\");'>"+cellValue+"</a>";
			}
			
			function zghLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name' onclick='showDetail(\""+cellValue+"\");'>"+cellValue+"</a>";
			}
			
			
			function showPbDetail(id){
				showDialog("咨询师排班详情",670,320,"xlzx_zxspb.do?method=zxspbDetail&id="+id);
			}
			
			function showDetail(zgh){
				showDialog("咨询师详情",670,350,"xlzx_zxs.do?method=zxsglDetail&doType=view&zgh="+zgh);
			}
			
			
			function setPbInfo(){
				showDialog("新增咨询师",670,440,"xlzx_zxspb.do?method=zxsPbDeal&doType=add");
				
				searchRs();
			}
			
			
			function updateZxs(){
				var zgh= '';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				 if(rowsValue.length != 1){
					showAlert("请选择1条您要修改的条目！");
					return false;
				}else{
					zgh = rowsValue[0]["zgh"];
				}
				showDialog("咨询师详情",670,440,"xlzx_zxs.do?method=zxsglDetail&doType=update&zgh="+zgh);
				searchRs();
			}
			
			function deleteZxs(){
				var delZgh = '';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				if (rowsValue.length == 0){
					showAlert("请选择您要删除的记录！");
				} else {
					for(var i=0;i<rowsValue.length;i++){
						if(i==(rowsValue.length-1)){
							delZgh += rowsValue[i]["zgh"];
						}else{
							delZgh += rowsValue[i]["zgh"]+",";
						}
					}
					showConfirm("您确定要删除选择的记录吗？",{"okFun":function(){
						jQuery.post("xlzx_zxs.do?method=deleteZxsInfo",{delZgh:delZgh},function(data){
							showAlert(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}

			function zxsDetail(cell){
				var zgh= '';
				if(typeof(cell)!="undefined"){
					zgh=cell;
				}else{
					var rowsValue = jQuery("#dataTable").getSeletRow();
					 if(rowsValue.length != 1){
						showAlert("请选择1条您要查看的条目");
						return false;
					}else{
						zgh = rowsValue[0]["zgh"];
					}
				}
					showDialog("咨询师详情",670,440,"xlzx_zxs.do?method=zxsglDetail&doType=view&zgh="+zgh);
			}
			
			