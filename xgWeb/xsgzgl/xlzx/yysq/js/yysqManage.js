			var gridSetting = {
				caption:"预约咨询列表",
				pager:"pager",
				url:"xlzx_yysq.do?method=yysqManage&doType=query",
				colList:[
					{label:'预约编号',name:'id', index: 'id',hidden:true},
				   {label:'学号',name:'xh', index: 'xh',formatter:xhLink},
				   {label:'姓名',name:'xsxm', index: 'xsxm'},
				   {label:'性别',name:'xsxb', index: 'xsxb'},
				   {label:'咨询师姓名',name:'zxsxm', index: 'zxsxm'},
				   {label:'预约咨询日期',name:'yyzxrq', index: 'yyzxrq'},
				   {label:'预约状态code',name:'status', index: 'status',hidden:true},
				   {label:'预约状态',name:'statusmc', index: 'status',formatter:getYyColor},
				   {label:'咨询安排日期',name:'zxrq', index: 'zxrq'},
				   {label:'zxzt',name:'zxzt', index: 'zxzt',hidden:true},
				   {label:'咨询状态',name:'zxztmc', index: 'zxztmc'},
				   {label:'咨询评价',name:'xspjzt', index: 'xspjzt',hidden:true},
				   {label:'咨询评价',name:'pjztmc', index: 'pjztmc',formatter:getPjColor}
				],
				sortname: "zxrq,yyzxrq",
			 	sortorder: "desc"
			};
			
			function xhLink(cellValue,rowObject){
				return "<a href = 'javascript:void(0);' class='name' onclick=\"showDialog('查看预约信息' , 750,440 , 'xlzx_yysqnew.do?method=yyfkDetail&doType=view&id=" + rowObject['id'] + "')\" >" + cellValue + "</a>";
			}
			
			function showDetail(id){
				showDialog("预约咨询详情",750,500,"xlzx_yysq.do?method=yyzxDetail&doType=view&id="+id);
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
				if(jQuery("#xxdm").val() == "10026"){
					gridSetting = {
							caption:"预约咨询列表",
							pager:"pager",
							url:"xlzx_yysq.do?method=yysqManage&doType=query",
							colList:[
								{label:'预约编号',name:'id', index: 'id',hidden:true},
							   {label:'学号',name:'xh', index: 'xh',formatter:xhLink},
							   {label:'姓名',name:'xsxm', index: 'xsxm'},
							   {label:'性别',name:'xsxb', index: 'xsxb'},
							   {label:'咨询师姓名',name:'zxsxm', index: 'zxsxm'},
							   {label:'预约咨询日期',name:'yyzxrq', index: 'yyzxrq'},
							   {label:'预约状态code',name:'status', index: 'status',hidden:true},
							   {label:'预约状态',name:'statusmc', index: 'status',formatter:getYyColor},
							   {label:'咨询安排日期',name:'zxrq', index: 'zxrq'},
							   {label:'zxzt',name:'zxzt', index: 'zxzt',hidden:true},
							   {label:'咨询时间段',name:'yysjdmc', index: 'yysjdmc'},
							   {label:'咨询状态',name:'zxztmc', index: 'zxztmc'},
							   {label:'咨询评价',name:'xspjzt', index: 'xspjzt',hidden:true},
							   {label:'咨询评价',name:'pjztmc', index: 'pjztmc',formatter:getPjColor}
							],
							sortname: "zxrq,yyzxrq",
						 	sortorder: "desc"
						};
				}
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();

				jQuery("#dataTable").reloadGrid(map);
			}

			function addYyzxInfo(){
                showDialog("咨询须知",750,560,"xlzx_yysq.do?method=zxxz");
			}
			function ckYyzxInfo(){
				var id= '';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				 if(rowsValue.length != 1){
					 showAlertDivLayer("请选择一条您要查看的记录！");
					return false;
				}else{
					id = rowsValue[0]["id"];
				}
				 showDialog('查看预约信息' , 750,440 , "xlzx_yysqnew.do?method=yyfkDetail&doType=view&id=" +id);
			}	
			function updateYyzxInfo(){
				var id= '';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				 if(rowsValue.length != 1){
					 showAlertDivLayer("请选择一条您要修改的记录！");
					return false;
				}else{
					if(rowsValue[0]["status"]!="1"){
						showAlertDivLayer("只能修改【预约中】的记录！");
						return false;
					}
					id = rowsValue[0]["id"];
				}
				showDialog("修改预约申请信息",750,510,"xlzx_yysq.do?method=yyzxDetail&doType=update&id="+id);
			}
			//取消預約
			function qxYyzxInfo(){
				var id= '';
				var status='';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				 if(rowsValue.length != 1){
					 showAlertDivLayer("请选择一条您要取消的预约！");
					return false;
				}else{

					if(rowsValue[0]["status"]=="1"){
						status = "3";
					}else if(rowsValue[0]["status"]=="2" && rowsValue[0]["zxzt"]=="1"){//预约成功，但未咨询才可取消。（线下老师忘了反馈咨询，咨询状态待咨询，则也可取消）
						status = "4";
					}else if(rowsValue[0]["status"]=="3" || rowsValue[0]["status"]=="4"){
						return showAlertDivLayer("该预约己经被取消！");
					}else{
						return showAlertDivLayer("不能取消该条预约！");
					}
					id = rowsValue[0]["id"];
				}
				 
				var url ="xlzx_yysq.do?method=updateYysqInfo";
				var parameter={id:id,status:status};
				
				showConfirmDivLayer("确认取消预约？",{"okFun":function(){
					jQuery.ajaxSetup({async:false});
					jQuery.post(url,parameter,function(result){
								if(data = true){
									showAlertDivLayer("预约取消成功！",{},{"clkFun":function(){
										searchRs();
									}});
								}else{
									showAlertDivLayer("预约取消失败！");
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
					 showAlertDivLayer("请选择一条您要评价的记录！");
					return false;
				}else{
					if(rowsValue[0]["xspjzt"]!="1"){
						showAlertDivLayer("不能评价该条预约记录！");
						return false;
					}
					id = rowsValue[0]["id"];
				}
				showDialog("咨询评价",620,300,"xlzx_yysq.do?method=xspjInfo&doType=update&id="+id);
			}
			

			//保存基本信息
			function save(type) {
				var ids = null;
				var url = "";
				if(type=='add'){
					ids = "xh-sfdszn-jtszd-jtjjzk-fqwhcd-mqwhcd-fmhyzk-jtjsbs-jtxhcd-sfzl-djrq-zxqw";
					url = "xlzx_yysq.do?method=addZxJbxx&type=save";
				}
				if(!check(ids)){
					showAlert("请将带<font color='red'>*</font>的项目填写完整");
					return false;
				}
				var checkboxs = jQuery("input[name='yzxwts']:checked");
				if(checkboxs.length == 0){
					if(jQuery("#wtbc").val().length == 0){
						showAlert("请勾选咨询的问题或填写咨询问题补充");
						return false;
					}
				}
				
				ajaxSubFormWithFun("yysqForm", url, function(data) {
					 if(data["message"]=="保存成功！"){
			    		 showAlert(data["message"],{},{"clkFun":function(){
			    			if(frameElement.api){
			    				var api = frameElement.api,W = api.opener;			    				
			    				//W.location.href="xlzx_yysq.do?method=yysqManage";
			    				W.reload();
			    				closeDialog();
			    			} else {
			    				parent.reload();
			    				//parent.window.location.href = "xlzx_yysq.do?method=yysqManage";
			    				iFClose();
			    			}
						}});
			    	 }else{
			    		 showAlert(data["message"]);
			    		}
					});	
			}
			
			/**
			 * 验证是否存在空项
			 * @param ids 要验证的控件id "-"分隔
			 * @return
			 */
			function check(ids){
				var id=ids.split("-");
				for(var i=0;i<id.length;i++){
					var lddm=jQuery("#"+id[i]).val().trim();
					if(lddm==null||""==lddm){
						return false;
					}
				}
				return true;
			}
			
			//咨询记录维护
			function ckgrInfo(xh){	
					var url = 'xlzx_yysq.do?method=ckZxzxjl&xh=' + xh;
					var title = "查看";
					showDialog(title, 800, 550, url);	
			}
			