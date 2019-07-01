			var gridSetting = {
				caption:"ԤԼ��ѯ�б�",
				pager:"pager",
				url:"xlzx_yysqnew.do?method=yysqManage&doType=query",
				colList:[
					{label:'ԤԼ���',name:'id', index: 'id',hidden:true},
				   {label:'ѧ��',name:'xh', index: 'xh',formatter:xhLink},
				   {label:'����',name:'xsxm', index: 'xsxm'},
				   {label:'�Ա�',name:'xsxb', index: 'xsxb'},
				   {label:'��ѯʦ����',name:'zxsxm', index: 'zxsxm'},
				   {label:'ԤԼ��ѯ����',name:'yyzxrq', index: 'yyzxrq'},
				   {label:'ԤԼ״̬code',name:'status', index: 'status',hidden:true},
				   {label:'ԤԼ״̬',name:'statusmc', index: 'status',formatter:getYyColor},
				   {label:'��ѯ����ʱ��',name:'zxrq', index: 'zxrq'},
				   {label:'zxzt',name:'zxzt', index: 'zxzt',hidden:true},
				   {label:'��ѯ״̬',name:'zxztmc', index: 'zxztmc'},
				   {label:'��ѯ����',name:'xspjzt', index: 'xspjzt',hidden:true},
				   {label:'��ѯ����',name:'pjztmc', index: 'pjztmc',formatter:getPjColor}
				],
				sortname: "zxrq,yyzxrq",
			 	sortorder: "desc"
			};
			
			function xhLink(cellValue,rowObject){
				return "<a href=\"javascript:void(0);\" class=\"name\" onclick=\"showDetail('"+rowObject["id"]+"');\">"+cellValue+"</a>";
			}
			
			function showDetail(id){
				showDialog("ԤԼ��ѯ����",750,500,"xlzx_yysqnew.do?method=yyzxDetail&doType=view&id="+id);
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
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();

				jQuery("#dataTable").reloadGrid(map);
			}

			function addYyzxInfo(){
				showDialog("����ԤԼ",750,580,"xlzx_zxspb.do?method=zxsPbbForXs");
			}
			function ckYyzxInfo(){
				var id= '';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				 if(rowsValue.length != 1){
					showAlert("��ѡ��һ����Ҫ�鿴�ļ�¼��");
					return false;
				}else{
					id = rowsValue[0]["id"];
				}
				showDialog("�鿴ԤԼ����",750,500,"xlzx_yysqnew.do?method=yyzxDetail&doType=view&id="+id);
			}	
			function updateYyzxInfo(){
				var id= '';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				 if(rowsValue.length != 1){
					showAlert("��ѡ��һ����Ҫ�޸ĵļ�¼��");
					return false;
				}else{
					if(rowsValue[0]["status"]!="1"){
						showAlert("ֻ���޸ġ�ԤԼ�С��ļ�¼��");
						return false;
					}
					id = rowsValue[0]["id"];
				}
				showDialog("�޸�ԤԼ������Ϣ",750,500,"xlzx_yysqnew.do?method=yyzxDetail&doType=update&id="+id);
			}
			//ȡ���A�s
			function qxYyzxInfo(){
				var id= '';
				var status='';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				 if(rowsValue.length != 1){
					showAlert("��ѡ��һ����Ҫȡ����ԤԼ��");
					return false;
				}else{

					if(rowsValue[0]["status"]=="1"){
						status = "3";
					}else if(rowsValue[0]["status"]=="2" && rowsValue[0]["zxzt"]=="1"){//ԤԼ�ɹ�����δ��ѯ�ſ�ȡ������������ʦ���˷�����ѯ����ѯ״̬����ѯ����Ҳ��ȡ����
						status = "4";
					}else if(rowsValue[0]["status"]=="3" || rowsValue[0]["status"]=="4"){
						return showAlert("����ȡ������ԤԼ��");
					}else{
						return showAlert("����ȡ������ԤԼ��");
					}
					id = rowsValue[0]["id"];
				}
				 
				var url ="xlzx_yysqnew.do?method=updateYysqInfo";
				var parameter={id:id,status:status};
				
				showConfirm("ȷ��ȡ��ԤԼ��",{"okFun":function(){
					jQuery.ajaxSetup({async:false});
					jQuery.post(url,parameter,function(result){
								if(data = true){
									showAlert("ԤԼȡ���ɹ���",{},{"clkFun":function(){
										searchRs();
									}});
								}else{
									showAlert("ԤԼȡ��ʧ�ܣ�");
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
					showAlert("��ѡ��һ����Ҫ���۵ļ�¼��");
					return false;
				}else{
					if(rowsValue[0]["xspjzt"]!="1"){
						showAlert("�������۸���ԤԼ��¼��");
						return false;
					}
					id = rowsValue[0]["id"];
				}
				showDialog("��ѯ����",750,160,"xlzx_yysqnew.do?method=xspjInfo&doType=update&id="+id);
			}
			