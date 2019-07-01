  var gridSetting = {
				caption:"行为大类列表",
				pager:"pager",
				url:"rcsw_rcxwwh_rcxwdmwhgl.do?method=rcxwdlManage&type=query",
				colList:[
				   {label:'行为大类代码',name:'rcxwlbdldm', index: 'rcxwlbdldm',key:true,width:'25%'},
				   {label:'行为大类名称',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'25%'},
				   {label:'审核流程',name:'lcxx', index: 'lcxx',width:'50%'},
				   {label : '申请开关',name : 'sqkg',index : 'sqkg',width : '8%',formatter:setSqkg},
				   {label : '申请开始时间',name : 'sqkssj',index : 'sqkssj',hidden : true},
				   {label : '申请结束时间',name : 'sqjssj',index : 'sqjssj',hidden : true}
				],
				sortname: "rcxwlbdldm",
			 	sortorder: "asc"
			}
  var gridSetting1 = {
      caption:"大类列表",
      pager:"pager",
      url:"rcsw_rcxwwh_rcxwdmwhgl.do?method=rcxwdlManage&type=query",
      colList:[
          {label:'加分大类代码',name:'rcxwlbdldm', index: 'rcxwlbdldm',key:true,width:'25%'},
          {label:'加分大类名称',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'25%'},
          {label:'审核流程',name:'lcxx', index: 'lcxx',width:'50%'},
          {label : '申请开关',name : 'sqkg',index : 'sqkg',width : '8%',formatter:setSqkg},
          {label : '申请开始时间',name : 'sqkssj',index : 'sqkssj',hidden : true},
          {label : '申请结束时间',name : 'sqjssj',index : 'sqjssj',hidden : true}
      ],
      sortname: "rcxwlbdldm",
      sortorder: "asc"
  }

			function dcmcLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
			}


			jQuery(function(){
				var xxdm = jQuery("#xxdm").val();
				if("13431" == xxdm){
                    jQuery("#dataTable").initGrid(gridSetting1);
				}else{
                    jQuery("#dataTable").initGrid(gridSetting);
				}

			});

			function query(){
				var map = {};
				map["rcxwlbdlmc"] = jQuery("#rcxwlbdlmc").val();
				jQuery("#dataTable").reloadGrid(map);
			}

			
		

			function del(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0){
					showAlertDivLayer("请选择您要删除的记录！");
				} else {
					showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
							jQuery.post("rcsw_rcxwwh_rcxwdmwhgl.do?method=delRcxwdl",{values:ids.toString()},function(data){
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							},'json');
					}});
					
				}
			}
		
			function newChgCode(obj){
				allNotEmpThenGo(obj.id);
			}
			
			/*
			 *申请开关 
			 */
			function setSqkg(cellValue,rowObject){
				var rcxwlbdldm = rowObject.rcxwlbdldm;
				var value = "未开启";
				var status = '0';
				var color;
				if(cellValue == '1'){
					var currDate = jQuery("#currDate").val();
					if(rowObject.sqkssj != null && currDate < rowObject.sqkssj || rowObject.sqjssj != null && currDate > rowObject.sqjssj ){
					}else{
						value = "已开启";
						status = '1';
					}
				}
				value = setColor(value,status);
				value = "<a  href='javascript:void(0);' onclick='return sjkg(\""+rcxwlbdldm+"\");' >"+value+"</a>";
				return value;
			}
			//由于外层样式影响，颜色必须写在元素上
			function setColor(value,status){
				var color;
				if(status == '1'){
					color = "#004400";
				}else{
					color = "red";
				}
				return value = "<font color='"+color+"'>" + value + "</font>";
			}
			/*
			 * 时间开关
			 */
			function sjkg(rcxwlbdldm) {
				if(rcxwlbdldm == null){//点击按钮
					var rows = jQuery("#dataTable").getSeletRow();
					if (rows.length != 1) {
						showAlertDivLayer("请选择一条您要设置的记录！");
						return false;
					}
					rcxwlbdldm = rows[0]["rcxwlbdldm"];
				}
				var url = 'rcsw_rcxwwh_rcxwdmwhgl.do?method=rcxwlbdldmSjkg&rcxwlbdldm=' + rcxwlbdldm;
				var title = "申请时间控制";
				showDialog(title, 600, 230, url);
			}