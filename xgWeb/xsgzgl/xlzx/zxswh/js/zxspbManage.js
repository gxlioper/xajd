var gridSetting = {
				caption:"��ѯʦ�б�",
				pager:"pager",
				url:"xlzx_zxspb.do?method=zxspbManage&doType=query",
				colList:[
				   {label:'���',name:'r', index: 'r',width:'4%',formatter:zghpbLink},
				   {label:'����',name:'id', index: 'id',width:'4%',hidden:true},
				   {label:'����',name:'weekday', index: 'weekday',width:'8%'},
				   {label:'�Ű�����',name:'pbdate', index: 'pbdate',width:'8%'},
				   {label:'�Ű�ʱ���',name:'sjddm', index: 'sjddm',width:'8%',hidden:true},
				   {label:'�Ű�ʱ���',name:'sjdmc', index: 'sjdmc',width:'8%'},
				   {label:'ְ����',name:'zgh', index: 'zgh',width:'8%',formatter:zghLink},
				   {label:'����',name:'xm', index: 'xm',width:'8%'},
				   {label:'��ϵ�绰',name:'lxdh', index: 'lxdh',width:'10%'},
				   {label:'����',name:'bmmc', index: 'bmmc',width:'10%'}
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
				showDialog("��ѯʦ�Ű�����",670,320,"xlzx_zxspb.do?method=zxspbDetail&id="+id);
			}
			
			function showDetail(zgh){
				showDialog("��ѯʦ����",670,350,"xlzx_zxs.do?method=zxsglDetail&doType=view&zgh="+zgh);
			}
			
			
			function setPbInfo(){
				showDialog("������ѯʦ",670,440,"xlzx_zxspb.do?method=zxsPbDeal&doType=add");
				
				searchRs();
			}
			
			
			function updateZxs(){
				var zgh= '';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				 if(rowsValue.length != 1){
					showAlert("��ѡ��1����Ҫ�޸ĵ���Ŀ��");
					return false;
				}else{
					zgh = rowsValue[0]["zgh"];
				}
				showDialog("��ѯʦ����",670,440,"xlzx_zxs.do?method=zxsglDetail&doType=update&zgh="+zgh);
				searchRs();
			}
			
			function deleteZxs(){
				var delZgh = '';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				if (rowsValue.length == 0){
					showAlert("��ѡ����Ҫɾ���ļ�¼��");
				} else {
					for(var i=0;i<rowsValue.length;i++){
						if(i==(rowsValue.length-1)){
							delZgh += rowsValue[i]["zgh"];
						}else{
							delZgh += rowsValue[i]["zgh"]+",";
						}
					}
					showConfirm("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
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
						showAlert("��ѡ��1����Ҫ�鿴����Ŀ");
						return false;
					}else{
						zgh = rowsValue[0]["zgh"];
					}
				}
					showDialog("��ѯʦ����",670,440,"xlzx_zxs.do?method=zxsglDetail&doType=view&zgh="+zgh);
			}
			
			