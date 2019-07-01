var gridSetting = {
				caption:"咨询师列表",
				pager:"pager",
				url:"xlzx_zxs.do?method=zxsglManage&doType=query",
				colList:[
				   {label:'职工号',name:'zgh', index: 'zgh',formatter:zghLink},
				   {label:'姓名',name:'xm', index: 'xm'},
				   {label:'性别',name:'xb', index: 'xb'},
				   {label:'年龄',name:'age', index: 'age'},
				   {label:'联系电话',name:'lxdh', index: 'lxdh'},
				   {label:'部门',name:'bmmc', index: 'bmmc'},
				   {label:'在岗状态',name:'statusmc', index: 'statusmc'},
				   {label:'任职资质',name:'zxszg_info', index: 'zxszg_info'}
				],
				sortname: "",
			 	sortorder: ""
			};
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();

				jQuery("#dataTable").reloadGrid(map);
			}
			function zghLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name' onclick='showDetail(\""+cellValue+"\");'>"+cellValue+"</a>";
			}
			function showDetail(zgh){
				showDialog("查看咨询师详情",750,410,"xlzx_zxs.do?method=zxsglDetail&doType=view&zgh="+zgh);
			}
			
			function addZxs(){
				showDialog("增加咨询师",750,410,"xlzx_zxs.do?method=zxsglDetail&doType=add");			
			}
			
			function updateZxs(){
				var zgh= '';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				 if(rowsValue.length != 1){
					showAlert("请选择一条您要修改的记录！");
					return false;
				}else{
					zgh = rowsValue[0]["zgh"];
				}
				showDialog("修改咨询师",750,410,"xlzx_zxs.do?method=zxsglDetail&doType=update&zgh="+zgh);
			}
			
			function deleteZxs(){
				var delZgh = '';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				if (rowsValue.length == 0){
					showAlert("请选择您要删除的记录！");
				} else {
					//验证是否有谈话记录
					if(delValidate(rowsValue)){
						showAlert("咨询师已排班，不能删除！");
						return false;
					}
					for(var i=0;i<rowsValue.length;i++){
						if(i==(rowsValue.length-1)){
							delZgh += rowsValue[i]["zgh"];
						}else{
							delZgh += rowsValue[i]["zgh"]+",";
						}
					}
					showConfirm("您确定要删除选择的记录吗？",{"okFun":function(){
						jQuery.ajaxSetup({async:false});
						jQuery.post("xlzx_zxs.do?method=deleteZxsInfo",{delZgh:delZgh},function(data){
							showAlert(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
						jQuery.ajaxSetup({async:true});
					}});
				}
			}
			function delValidate(rowsValue){
				var flag = false;
				for(var i=0;i<rowsValue.length;i++){
					jQuery.ajaxSetup({async:false});
					jQuery.post("xlzx_zxspb.do?method=getZxspbInfoByZgh",{zgh:rowsValue[i]["zgh"]},function(data){
						if(data!=''){
							flag = true;
						}
					},'json');
					jQuery.ajaxSetup({async:true});
					if(flag==true){
						break;
					}
				}
				return flag;
			}
			
			function zxsDetail(cell){
				var zgh= '';
				if(typeof(cell)!="undefined"){
					zgh=cell;
				}else{
					var rowsValue = jQuery("#dataTable").getSeletRow();
					 if(rowsValue.length != 1){
						showAlert("请选择一条您要查看的记录");
						return false;
					}else{
						zgh = rowsValue[0]["zgh"];
					}
				}
					showDialog("咨询师详情",750,430,"xlzx_zxs.do?method=zxsglDetail&doType=view&zgh="+zgh);
			}
			
			
			function setBatchZgStatus(){
				var dealZgh = '';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				if (rowsValue.length == 0){
					showAlert("请选择您要设置的记录！");
				} else {
					for(var i=0;i<rowsValue.length;i++){
						if(i==(rowsValue.length-1)){
							dealZgh += rowsValue[i]["zgh"];
						}else{
							dealZgh += rowsValue[i]["zgh"]+",";
						}
					}
					showDialog("咨询师状态设置",400,170,"xlzx_zxs.do?method=setZgStatus&dealZgh="+dealZgh);
				}
			}
			
			function exportZxsList() {
				customExport("xlzx_zxsgl_zxsgl.do", exportZxsData,750,500);
			}
			
			// 导出方法
			function exportZxsData() {
				setSearchTj();//设置高级查询条件
				var url = "xlzx_zxs.do?method=exportZxsData&dcclbh=" + "xlzx_zxsgl_zxsgl.do";//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}