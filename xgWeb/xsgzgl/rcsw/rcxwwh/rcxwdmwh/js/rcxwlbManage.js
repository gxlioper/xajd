  var gridSetting = {
				caption:"��Ϊ����б�",
				pager:"pager",
				url:"rcsw_rcxwwh_rcxwdmwhgl.do?method=rcxwlbManage&type=query",
				colList:[
				   {label:'��Ϊ������',name:'rcxwlbdm', index: 'rcxwlbdm',key:true,width:'15%'},
				   {label:'��Ϊ�������',name:'rcxwlbmc', index: 'rcxwlbmc',width:'18%'},
				   {label:'������Ϊ����',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'18%'},
				   {label:'��ֵ����',name:'fzlxmc', index: 'fzlxmc',width:'7%'},
				   {label:'��ֵ',name:'fzqj', index: 'fzqj',width:'12%'},
				   {label:'����˵��',name:'rcxwlbbz', index: 'rcxwlbbz',width:'30%',formatter:format},
				   {label:'������Ϊ����',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
				   {label:'����״̬',name:'sfqymc', index: 'sfqymc',width:'10%'}
				],
				sortname: "rcxwlbdm",
			 	sortorder: "asc"
			}
  var gridSetting1 = {
      caption:"�ӷ�����б�",
      pager:"pager",
      url:"rcsw_rcxwwh_rcxwdmwhgl.do?method=rcxwlbManage&type=query",
      colList:[
          {label:'�ӷ�������',name:'rcxwlbdm', index: 'rcxwlbdm',key:true,width:'15%'},
          {label:'�ӷ��������',name:'rcxwlbmc', index: 'rcxwlbmc',width:'18%'},
          {label:'�����ӷִ���',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'18%'},
          {label:'��ֵ����',name:'fzlxmc', index: 'fzlxmc',width:'7%'},
          {label:'��ֵ',name:'fzqj', index: 'fzqj',width:'12%'},
          {label:'����˵��',name:'rcxwlbbz', index: 'rcxwlbbz',width:'30%',formatter:format},
          {label:'�����ӷִ���',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
          {label:'����״̬',name:'sfqymc', index: 'sfqymc',width:'10%'}
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
					showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
				} else {
					showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
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
			