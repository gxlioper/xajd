var gridSetting = {
				caption:"��ѯʦ�б�",
				pager:"pager",
				url:"xlzx_zxs.do?method=zxsglManage&doType=query",
				colList:[
				   {label:'ְ����',name:'zgh', index: 'zgh',formatter:zghLink},
				   {label:'����',name:'xm', index: 'xm'},
				   {label:'�Ա�',name:'xb', index: 'xb'},
				   {label:'����',name:'age', index: 'age'},
				   {label:'��ϵ�绰',name:'lxdh', index: 'lxdh'},
				   {label:'����',name:'bmmc', index: 'bmmc'},
				   {label:'�ڸ�״̬',name:'statusmc', index: 'statusmc'},
				   {label:'��ְ����',name:'zxszg_info', index: 'zxszg_info'}
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
				showDialog("�鿴��ѯʦ����",750,410,"xlzx_zxs.do?method=zxsglDetail&doType=view&zgh="+zgh);
			}
			
			function addZxs(){
				showDialog("������ѯʦ",750,410,"xlzx_zxs.do?method=zxsglDetail&doType=add");			
			}
			
			function updateZxs(){
				var zgh= '';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				 if(rowsValue.length != 1){
					showAlert("��ѡ��һ����Ҫ�޸ĵļ�¼��");
					return false;
				}else{
					zgh = rowsValue[0]["zgh"];
				}
				showDialog("�޸���ѯʦ",750,410,"xlzx_zxs.do?method=zxsglDetail&doType=update&zgh="+zgh);
			}
			
			function deleteZxs(){
				var delZgh = '';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				if (rowsValue.length == 0){
					showAlert("��ѡ����Ҫɾ���ļ�¼��");
				} else {
					//��֤�Ƿ���̸����¼
					if(delValidate(rowsValue)){
						showAlert("��ѯʦ���Ű࣬����ɾ����");
						return false;
					}
					for(var i=0;i<rowsValue.length;i++){
						if(i==(rowsValue.length-1)){
							delZgh += rowsValue[i]["zgh"];
						}else{
							delZgh += rowsValue[i]["zgh"]+",";
						}
					}
					showConfirm("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
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
						showAlert("��ѡ��һ����Ҫ�鿴�ļ�¼");
						return false;
					}else{
						zgh = rowsValue[0]["zgh"];
					}
				}
					showDialog("��ѯʦ����",750,430,"xlzx_zxs.do?method=zxsglDetail&doType=view&zgh="+zgh);
			}
			
			
			function setBatchZgStatus(){
				var dealZgh = '';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				if (rowsValue.length == 0){
					showAlert("��ѡ����Ҫ���õļ�¼��");
				} else {
					for(var i=0;i<rowsValue.length;i++){
						if(i==(rowsValue.length-1)){
							dealZgh += rowsValue[i]["zgh"];
						}else{
							dealZgh += rowsValue[i]["zgh"]+",";
						}
					}
					showDialog("��ѯʦ״̬����",400,170,"xlzx_zxs.do?method=setZgStatus&dealZgh="+dealZgh);
				}
			}
			
			function exportZxsList() {
				customExport("xlzx_zxsgl_zxsgl.do", exportZxsData,750,500);
			}
			
			// ��������
			function exportZxsData() {
				setSearchTj();//���ø߼���ѯ����
				var url = "xlzx_zxs.do?method=exportZxsData&dcclbh=" + "xlzx_zxsgl_zxsgl.do";//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}