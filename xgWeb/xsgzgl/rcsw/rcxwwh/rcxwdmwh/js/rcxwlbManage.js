  var gridSetting = {
				caption:"行为类别列表",
				pager:"pager",
				url:"rcsw_rcxwwh_rcxwdmwhgl.do?method=rcxwlbManage&type=query",
				colList:[
				   {label:'行为类别代码',name:'rcxwlbdm', index: 'rcxwlbdm',key:true,width:'15%'},
				   {label:'行为类别名称',name:'rcxwlbmc', index: 'rcxwlbmc',width:'18%'},
				   {label:'所属行为大类',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'18%'},
				   {label:'分值类型',name:'fzlxmc', index: 'fzlxmc',width:'7%'},
				   {label:'分值',name:'fzqj', index: 'fzqj',width:'12%'},
				   {label:'评分说明',name:'rcxwlbbz', index: 'rcxwlbbz',width:'30%',formatter:format},
				   {label:'所属行为大类',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
				   {label:'启用状态',name:'sfqymc', index: 'sfqymc',width:'10%'}
				],
				sortname: "rcxwlbdm",
			 	sortorder: "asc"
			}
  var gridSetting1 = {
      caption:"加分类别列表",
      pager:"pager",
      url:"rcsw_rcxwwh_rcxwdmwhgl.do?method=rcxwlbManage&type=query",
      colList:[
          {label:'加分类别代码',name:'rcxwlbdm', index: 'rcxwlbdm',key:true,width:'15%'},
          {label:'加分类别名称',name:'rcxwlbmc', index: 'rcxwlbmc',width:'18%'},
          {label:'所属加分大类',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'18%'},
          {label:'分值类型',name:'fzlxmc', index: 'fzlxmc',width:'7%'},
          {label:'分值',name:'fzqj', index: 'fzqj',width:'12%'},
          {label:'评分说明',name:'rcxwlbbz', index: 'rcxwlbbz',width:'30%',formatter:format},
          {label:'所属加分大类',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
          {label:'启用状态',name:'sfqymc', index: 'sfqymc',width:'10%'}
      ],
      sortname: "rcxwlbdm",
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
				var rcxwlbdlmc = jQuery("#rcxwlbdlmc").val();
				map["rcxwlbmc"] = jQuery("#rcxwlbmc").val();
				if (jQuery.trim(rcxwlbdlmc) != ""){
					map["rcxwlbdlmc"] = jQuery("#rcxwlbdlmc").val();
				}
				map["sfqy"] = jQuery("#sfqy").val();
				jQuery("#dataTable").reloadGrid(map);
			}

			
			
			
			function queryRcxwlbdldm(){
				var map = {};
				var rcxwlbmc = jQuery("#rcxwlbmc").val();
				map["rcxwlbdlmc"] = jQuery("#rcxwlbdlmc").val();
				
				if (jQuery.trim(rcxwlbmc) != ""){
					map["rcxwlbmc"] = jQuery("#rcxwlbmc").val();
				}
				map["sfqy"] = jQuery("#sfqy").val();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			

			
			function del(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0){
					showAlertDivLayer("请选择您要删除的记录！");
				} else {
					showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
							jQuery.post("rcsw_rcxwwh_rcxwdmwhgl.do?method=delRcxwlb",{values:ids.toString()},function(data){
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							},'json');
					}});
					
				}
			}
		
			function newChgCode(obj){
				allNotEmpThenGo(obj.id);
			}
			String.prototype.replaceAll = function(s1,s2) { 
			    return this.replace(new RegExp(s1,"gm"),s2); 
			}
			function format(cellValue,rowObject){
				if(cellValue==null){
					return cellValue;
				}else{
					return cellValue.replaceAll('\\n','<br>');
				}
			}
			