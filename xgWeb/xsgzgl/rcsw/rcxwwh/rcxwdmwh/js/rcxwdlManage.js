  var gridSetting = {
				caption:"��Ϊ�����б�",
				pager:"pager",
				url:"rcsw_rcxwwh_rcxwdmwhgl.do?method=rcxwdlManage&type=query",
				colList:[
				   {label:'��Ϊ�������',name:'rcxwlbdldm', index: 'rcxwlbdldm',key:true,width:'25%'},
				   {label:'��Ϊ��������',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'25%'},
				   {label:'�������',name:'lcxx', index: 'lcxx',width:'50%'},
				   {label : '���뿪��',name : 'sqkg',index : 'sqkg',width : '8%',formatter:setSqkg},
				   {label : '���뿪ʼʱ��',name : 'sqkssj',index : 'sqkssj',hidden : true},
				   {label : '�������ʱ��',name : 'sqjssj',index : 'sqjssj',hidden : true}
				],
				sortname: "rcxwlbdldm",
			 	sortorder: "asc"
			}
  var gridSetting1 = {
      caption:"�����б�",
      pager:"pager",
      url:"rcsw_rcxwwh_rcxwdmwhgl.do?method=rcxwdlManage&type=query",
      colList:[
          {label:'�ӷִ������',name:'rcxwlbdldm', index: 'rcxwlbdldm',key:true,width:'25%'},
          {label:'�ӷִ�������',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'25%'},
          {label:'�������',name:'lcxx', index: 'lcxx',width:'50%'},
          {label : '���뿪��',name : 'sqkg',index : 'sqkg',width : '8%',formatter:setSqkg},
          {label : '���뿪ʼʱ��',name : 'sqkssj',index : 'sqkssj',hidden : true},
          {label : '�������ʱ��',name : 'sqjssj',index : 'sqjssj',hidden : true}
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
					showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
				} else {
					showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
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
			 *���뿪�� 
			 */
			function setSqkg(cellValue,rowObject){
				var rcxwlbdldm = rowObject.rcxwlbdldm;
				var value = "δ����";
				var status = '0';
				var color;
				if(cellValue == '1'){
					var currDate = jQuery("#currDate").val();
					if(rowObject.sqkssj != null && currDate < rowObject.sqkssj || rowObject.sqjssj != null && currDate > rowObject.sqjssj ){
					}else{
						value = "�ѿ���";
						status = '1';
					}
				}
				value = setColor(value,status);
				value = "<a  href='javascript:void(0);' onclick='return sjkg(\""+rcxwlbdldm+"\");' >"+value+"</a>";
				return value;
			}
			//���������ʽӰ�죬��ɫ����д��Ԫ����
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
			 * ʱ�俪��
			 */
			function sjkg(rcxwlbdldm) {
				if(rcxwlbdldm == null){//�����ť
					var rows = jQuery("#dataTable").getSeletRow();
					if (rows.length != 1) {
						showAlertDivLayer("��ѡ��һ����Ҫ���õļ�¼��");
						return false;
					}
					rcxwlbdldm = rows[0]["rcxwlbdldm"];
				}
				var url = 'rcsw_rcxwwh_rcxwdmwhgl.do?method=rcxwlbdldmSjkg&rcxwlbdldm=' + rcxwlbdldm;
				var title = "����ʱ�����";
				showDialog(title, 600, 230, url);
			}