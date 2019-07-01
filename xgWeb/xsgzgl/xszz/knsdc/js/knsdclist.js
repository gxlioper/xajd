           var gridSetting = {
				caption:"���ѵ����б�",
				pager:"pager",
				url:"xszz_knsdc.do?method=dcwhList&type=query",
				colList:[
				   {label:'���δ���',name:'dcdm', index: 'dcdm',key:true},
				   {label:'��������',name:'dcmc', index: 'dcdm',width:'20%'},
				   {label:'��Ŀ˵��',name:'xmsm', index: 'xmsm',width:'60%',formatter:xmsmSubstring}
				],
				sortname: "dcdm",
			 	sortorder: "asc"
			};

			function dcmcLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
			}


			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				map["dcmc"] = jQuery("#dcmc").val();
				jQuery("#dataTable").reloadGrid(map);
			}

			function add(){
				var url = "xszz_knsdc.do?method=addKnsdc";
				var title = "�������ѵ���";
				showDialog(title,400,250,url);
			}
			function update(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
				} else {
					var url = 'xszz_knsdc.do?method=updateKnsdc&dcdm='+rows[0]["dcdm"];
					var title = "�޸����ѵ���";
					showDialog(title,400,250,url);
				}
			}

			function del(){
				var ids = jQuery("#dataTable").getSeletIds();

				if (ids.length == 0){
					showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
				} else {
					showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
							jQuery.post("xszz_knsdc.do?method=delKnsdc",{values:ids.toString()},function(data){
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							},'json');
					}});
					
				}
			}
			
			//��Ŀ˵��������ȡ
			function xmsmSubstring(cellValue,row){
				if(cellValue==null){
					cellValue="";
				}
				var strValue = cellValue;
				if(strValue.length > 60){
					strValue = strValue.substring(0, 60)+"...";
				}
				return "<span title='"+cellValue+"'>"+strValue+"</span>";;
			}
			
			
		